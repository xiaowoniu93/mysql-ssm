// 存放主要交互逻辑js代码
// js 模块化
var seckill={
		// 封装秒杀相关url
		URL : {
			now : function(){
				return '/seckill/time/now';
			}
		},
		handlerSeckill : function(){
			
		},
		// 验证手机号
		validatePhone : function(phone){
			if(phone && phone.length == 11 && !isNaN(phone)){
				return true;
			}else{
				return false;
			}
		},
		countdown : function(seckillId, startTime, endTime, nowTime){
			var seckillBox = $('#seckill-box');
			if(nowTime > endTime){
				// 秒杀结束
				seckillBox.html('秒杀结束');
			}else if(startTime > nowTime){
				// 秒杀未开始
				var killTime = new Date(startTime + 1000);
				seckillBox.countdown(killTime, function(event){
					// 时间格式
					var format = event.strftime('秒杀倒计时： %D天 %H小时 %M分钟 %S秒');
					seckillBox.html(format);
				}).on('finish.countdown', function(){	// 倒计时结束
					seckill.handlerSeckill();
				});
			}else{
				// 秒杀开始
				seckill.handlerSeckill();
			}
		},
		// 详情页秒杀逻辑
		detail : {
			// 详情页初始化
			init : function(params){
				// 手机登录验证，计时交互
				// 在 cookie 中查找手机号
				var killPhone = $.cookie('killPhone');
				// 验证手机号
				if(!seckill.validatePhone(killPhone)){	// 手机号未输入或不合法
					// 绑定手机号
					var killPhoneModal = $('#killPhoneModal');
					// 显示弹出层
					killPhoneModal.modal({
						show : true, // 显示弹出层
						backdrop : 'static', // 禁止位置关闭
						keyboard : false // 关闭键盘事件
					});
					$('#killPhoneBtn').click(function(){
						var inputPhone = $('#killPhoneKey').val();
						if(seckill.validatePhone(inputPhone)){
							// 手机号写入cookie
							$.cookie('killPhone', inputPhone, {expires : 7, path: '/seckill'});
							// 刷新页面
							window.location.reload();
						}else{
							$('#killPhoneMessage').hide().html('<label class="label label-danger>手机号不合法</label>').show(300);
						}
					});
				}
				// 已经登录
				// 倒计时显示面板
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				var seckillId = params['seckillId'];
				$.get(seckill.URL.now(), {}, function(result){
					if(result && result['success']){
						var nowTime = result['data'];
						// 时间判断
						countdown(seckillId, startTime, endTime, nowTime);
					}else{
						console.log('result:'+result);
					}
				});
			}
		}
}