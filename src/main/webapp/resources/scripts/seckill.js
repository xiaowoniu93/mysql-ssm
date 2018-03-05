// 存放主要交互逻辑js代码
// js 模块化
var seckill={
		// 封装秒杀相关url
		URL : {
			now : function(){
				return '/seckill/time/now';
			},
			exposer : function(seckillId){
				return '/seckill/' + seckillId + '/exposer';
			},
			execution : function(seckillId, md5){
				return '/seckill/' + seckillId + '/'+ md5 + '/execution';
			}
		},
		handlerSeckill : function(seckillId, seckillBox){
			seckillBox.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
			$.post(seckill.URL.exposer(seckillId), {}, function(result){
				// 在回调函数中执行交互流程
				if(result && result['success']){
					var exposer = result['data'];
					if(exposer['exposed']){
						// 开启秒杀
						// 获取秒杀地址
						var md5 = exposer['md5'];
						var killUrl = seckill.URL.execution(seckillId, md5);
						console.log('killUrl:'+killUrl);
						// 绑定一次点击事件
						$('#killBtn').one('click', function(){
							// 执行秒杀请求
							// 1、先禁用按钮
							$(this).addClass('disabled');
							// 2、执行秒杀
							$.post(killUrl, {}, function(result){
								if(result && result['success']){
									var data = result['data'];
									var state = data['state'];
									var stateInfo = data['stateInfo'];
									// 显示秒杀结果
									seckillBox.html('<span class="label label-success">'+stateInfo+'</span>');
								}
							});
							seckillBox.show();
						});
					}else{
						// 秒杀未开始
						var now = exposer['nowTime'];
						var start = exposer['startTime'];
						var end = exposer['endTime'];
						// 重新计算计时逻辑
						seckill.countdown(seckillId, start, end, now);
					}
				}else{
					console.log('result:'+result);
				}
			});
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
					// 获取秒杀地址，控制显示逻辑，执行秒杀
					seckill.handlerSeckill(seckillId, seckillBox);
				});
			}else{
				// 秒杀开始
				seckill.handlerSeckill(seckillId, seckillBox);
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