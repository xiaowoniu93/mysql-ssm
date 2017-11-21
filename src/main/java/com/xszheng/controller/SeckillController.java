package com.xszheng.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xszheng.domain.SkProductStock;
import com.xszheng.service.SkProductStockService;
import com.xszheng.vo.Exposer;
import com.xszheng.vo.SeckillExecution;
import com.xszheng.vo.SeckillResult;

@Controller
@RequestMapping(value="/seckill")
public class SeckillController {
	
	private static final Logger log = LoggerFactory.getLogger(SeckillController.class);
	
	@Autowired
	private SkProductStockService skProductStockService;
	
	/**
	 * 秒杀商品列表
	 * @descript 
	 * @author xszheng 2017年11月21日下午2:08:01
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model){
		List<SkProductStock> list = skProductStockService.getAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	/**
	 * 秒杀的商品详情
	 * @descript 
	 * @author xszheng 2017年11月21日下午2:23:42
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}/detail", method=RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model){
		if(id == null || id == 0){
			return "redirect:/seckill/list";
		}
		SkProductStock skProductStock = skProductStockService.getOneById(id);
		if(skProductStock == null){
			return "forward:/seckill/list";
		}
		model.addAttribute("detail", skProductStock);
		return "detail";
	}
	
	
	/**
	 * 获取商品秒杀地址（即：该商品是否已开启秒杀）
	 * @descript 
	 * @author xszheng 2017年11月21日下午2:48:27
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/exposer", method=RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("id") Long id){
		SeckillResult<Exposer> result = null;
		try {
			Exposer exposer = skProductStockService.exportSeckillUrl(id);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			log.info("#SeckillController #exposer has a error: {}", e.getMessage());
			result = new SeckillResult<>(false, e.getMessage());
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 执行秒杀操作
	 * @descript 
	 * @author xszheng 2017年11月21日下午6:36:38
	 * @param id
	 * @param md5
	 * @param userPhone
	 * @return
	 */
	@RequestMapping(value="/{id}/{md5}/execution", method=RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("id") Long id, 
												   @PathVariable("md5") String md5, 
												   @CookieValue(value="killPhone", required=false) String userPhone){
		SeckillResult<SeckillExecution> result = null;
		if(userPhone == null || userPhone.equals("")){
			return new SeckillResult<>(false, "手机号不能为空");
		}
		try {
			SeckillExecution execution = skProductStockService.executeSeckill(id, userPhone, md5);
			result = new SeckillResult<SeckillExecution>(true, execution);
		} catch (Exception e) {
			log.info("#SeckillController #execute has a error: {}", e.getMessage());
			result = new SeckillResult<SeckillExecution>(false, e.getMessage());
			throw e;
		}
		return result;
	}
}
