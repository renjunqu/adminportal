function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}

jQuery(function($) {
	$(document).on('click', '.toolbar a[data-target]', function(e) {
		e.preventDefault();
		var target = $(this).data('target');
		$('.widget-box.visible').removeClass('visible');// hide others
		$(target).addClass('visible');// show target
	});

	// 登录页面切换背景图
	$('#btn-login-dark').on('click', function(e) {
		$('body').attr('class', 'login-layout');
		e.preventDefault();
	});
	$('#btn-login-light').on('click', function(e) {
		$('body').attr('class', 'login-layout light-login');
		e.preventDefault();
	});
	$('#btn-login-blur').on('click', function(e) {
		$('body').attr('class', 'login-layout blur-login');
		e.preventDefault();
	});

	// 按回车键触发登录事件
	$(document).keydown(function(event) {
		var key = window.event ? event.keyCode : event.which;
		if (key == 13) {
			$('#validationLoginForm').submit();
		}
	});

	// 验证登录表单
	$("#loginButton").bind("click", function() {
		$('#validationLoginForm').submit();
	});
	$('#validationLoginForm').validate({
		errorElement : 'div',
		errorClass : 'help-block',
		focusInvalid : false,
		ignore : "",
		rules : {
			loginAccount : {
				required : true
			},
			password : {
				required : true,
				minlength : 6,
				maxlength : 14
			}
		},
		messages : {
			loginAccount : {
				required : "请填写用户名"
			},
			password : {
				required : "请输入密码",
				minlength : "密码长度至少为6个字符",
				maxlength : "密码长度至多为14个字符"
			}
		},
		highlight : function(e) {
			$(e).closest('label').removeClass('has-info').addClass('has-error');
		},
		success : function(e) {
			$(e).closest('label').removeClass('has-error');// .addClass('has-info');
			$(e).remove();
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
			//error.appendTo('#invalid');
		},
		submitHandler : function(form) {
			$.ajax({
				dataType : "json",
				url : getContextPath() + "/sys/sysuser/login",
				type : "post",
				data : {
					loginAccount : $('#loginAccount').val(),
					password : $('#loginPassword').val()
				},
				complete : function(xmlRequest) {
					var returninfo = eval("(" + xmlRequest.responseText + ")");
					if (returninfo.result == 1) {
						document.location.href = getContextPath() + "/sys/sysuser/home";
					} else if (returninfo.result == -1) {
						$("#loginTip").html("用户名有误或已被禁用");
					} else if (returninfo.result == -2) {
						$("#loginTip").html("密码错误");
					} else {
						$("#loginTip").html("服务器错误");
					}
				}
			});
		},
		invalidHandler : function(form) {
		}
	});

	$('[data-rel=tooltip]').tooltip({
		container : 'body'
	});
	
	$('#birthday').datepicker({
	    format: 'yyyy-mm-dd',
	    language: 'zh-CN'
	});

});
