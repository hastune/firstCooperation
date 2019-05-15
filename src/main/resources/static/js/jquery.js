//登录操作
$(document).ready(function(){
if(screen.width < 780 && $(window).width() < 780)
{
	$(".content_list").hide();

}else{
	$(".content_list").show();
}
     $(window).resize(function(){	
	 $(".content_list").show();
	 });
});




//登录操作
var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
var validCode=true;
cliLogin = function () {
	var txtUser = $.trim($("#email").val());
	var txtPwd = $("#password").val();
	
	if ($.trim(txtUser) == "") {
	
		Tip('请输入邮箱号');
		$("#txtUser").focus();
		return;
		
	}
	if(!email.exec(txtUser)){
			
			Tip('邮箱格式不正确,请重新输入');
			$("#txtUser").focus();
		return;
		}
	
	if ($.trim(txtPwd) == "") {
		Tip('请输入密码！');
		$("#Userpwd").focus();
		return;
	}


	//完成登录操作
    $.ajax({
       url : "/user/selectOne" ,
       type : "POST",
       data : {
           email : txtUser,
           password : txtPwd
       },
       dataType : "json",
        success : function (data) {
            if(data.message == "邮箱或密码错误"){
                //説明账号或密码输入错误
                Tip(data.message);
                $("#email").focus();
                return;
            }else{
                //登录成功
                window.location.href = data.message;
				// console.info(data);
            }
        }
    });


	return false;
}

//忘记密码操作
function ForgotSendpwd(sender){
    var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var emails = $.trim($("#email").val());
    var code=$(sender);

    if ($.trim(email) == "") {
        Tip('请填写邮箱号！');
        $("#emails").focus();
        return;
    }
    if(!email.exec(emails)){

        Tip('邮箱输入格式不正确,请重新输入');
        $("#emails").focus();
        return;
    }

    if (validCode ){
        validCode = false;
        code.addClass("msgs1").attr("disabled",true);
        //在这里发送ajax请求
        $.ajax({
            url : "/user/getUserByEmail?email="+emails,
            type : "get",
            success : function (data) {

                if(data.message == "用户不存在，请重新输入！") {
                    Tip(data.message);
                    $("#email").focus();
                    validCode=true;
                    code.removeClass("msgs1").attr("disabled",false);
                    return;

                }else{
                    //如果输入正确，我们就发送请求

                    code.val("重新获取");

                    validCode=true;
                    code.removeClass("msgs1").attr("disabled",false);

                }
            }


        });
    }

}


//注册操作

function Sendpwd(sender) {

	var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var emails = $.trim($("#email").val());
    var code=$(sender);

	if ($.trim(email) == "") {
		Tip('请填写邮箱号！');
		$("#emails").focus();
		return;
	}
	if(!email.exec(emails)){

		Tip('邮箱输入格式不正确,请重新输入');
		$("#emails").focus();
		return;
	}

	if (validCode ){
		validCode = false;
        code.addClass("msgs1").attr("disabled",true);
		//在这里发送ajax请求
		$.ajax({
			url : "/user/getCheckCode?email="+emails,
			type : "get",
			success : function (data) {

				if(data.message == "邮箱已存在，请重新输入！") {
					Tip(data.message);
					$("#email").focus();
                    validCode=true;
                    code.removeClass("msgs1").attr("disabled",false);
					return;

				}else{
					//如果输入正确，我们就发送请求

					code.val("重新获取");

					validCode=true;
					code.removeClass("msgs1").attr("disabled",false);

					}
				}


		});
	}



}

function Tip(msg) {
	$(".tishi").show().html("<div class='prompt'><i class='tishi_icon'></i>"+msg+"</div>");
}
function Tips(msg) {
	
	$(".tishis").show().html("<div class='prompt'><i class='tishi_icon'></i>"+msg+"</div>");
}
jQuery(function(){
	"use strict";
		$(".navList .navLi").hover(function(){
		$(this).addClass("active");
		$(this).find(".navSub").stop().slideDown();	
	},function(){
		$(this).removeClass("active");
		$(this).find(".navSub").stop().slideUp();
		$(".navThrList").slideUp();
		$(".navFouList").slideUp();
	});
	function scro(){
			if($(document).scrollTop()>50){$(".headerbg").addClass("active");}else{$(".headerbg").removeClass("active");}
		}
		scro();
		$(window).scroll(function(){
			scro();
		});
			$(".benefits_name").hover(function(){
			$(this).addClass("hover");
			$(this).stop().animate({bottom: "0px", opacity:1 , height:"400px"}, "fast");
		},function(){
			$(this).removeClass("hover");
			$(this).stop().animate({bottom: "0px", opacity:1 , height:"400px"}, "fast") ; 			
		}
	);
		$(".Program_name ").hover(function() {
	    $(this).addClass("hover");
		$(this).find(".Program_title").stop()
		.animate({ opacity:1 , height:"100%"}, "fast")
		.css("display","block");

	}, function() {
	    $(this).removeClass("hover");  
		$(this).find(".Program_title").stop()
		.animate({ opacity: 1,height:"40px"}, "fast")
		.css("display","block");
	});
		$(".user_casestyle").hover(function(){
			$(this).addClass("hover");
			$(this).find(".hd a").css("display","block");
			//$(this).stop().animate({bottom: "0px", opacity:1 , height:""}, "fast")
		},function(){
			$(this).removeClass("hover");
			$(this).find(".hd a").css("display","none");
			//$(this).stop().animate({bottom: "0px", opacity:1 , height:""}, "fast")  			
		}
	);
	//置顶图标显示
$('#top-back').hide();
$(window).scroll(function(){
	 if($(this).scrollTop() > 350){
		$("#top-back").fadeIn();
	 }
	 else{
		$("#top-back").fadeOut();
	 }
  });
$(".bannerNimg").css("background-image", "url(" + $(".bannerNimg").find("img").attr("src") + ")");
//置顶事件
function topBack(){
  $('body,html').animate({scrollTop:0},300);
}
});

$.fn.countTo = function (options) {
	"use strict";
	options = options || {};	
	return $(this).each(function () {
		// set options for current element
		var settings = $.extend({}, $.fn.countTo.defaults, {
			from:            $(this).data('from'),
			to:              $(this).data('to'),
			speed:           $(this).data('speed'),
			refreshInterval: $(this).data('refresh-interval'),
			decimals:        $(this).data('decimals')
		}, options);
		
		// how many times to update the value, and how much to increment the value on each update
		var loops = Math.ceil(settings.speed / settings.refreshInterval),
			increment = (settings.to - settings.from) / loops;
		
		// references & variables that will change with each update
		var self = this,
			$self = $(this),
			loopCount = 0,
			value = settings.from,
			data = $self.data('countTo') || {};
		
		$self.data('countTo', data);
		
		// if an existing interval can be found, clear it first
		if (data.interval) {
			clearInterval(data.interval);
		}
		data.interval = setInterval(updateTimer, settings.refreshInterval);
		
		// initialize the element with the starting value
		render(value);
		
		function updateTimer() {
			value += increment;
			loopCount++;
			
			render(value);
			
			if (typeof(settings.onUpdate) == 'function') {
				settings.onUpdate.call(self, value);
			}
			
			if (loopCount >= loops) {
				// remove the interval
				$self.removeData('countTo');
				clearInterval(data.interval);
				value = settings.to;
				
				if (typeof(settings.onComplete) == 'function') {
					settings.onComplete.call(self, value);
				}
			}
		}
		
		function render(value) {
			var formattedValue = settings.formatter.call(self, value, settings);
			$self.html(formattedValue);
		}
	});
};

$.fn.countTo.defaults = {
	from: 0,               // the number the element should start at
	to: 0,                 // the number the element should end at
	speed: 300,           // how long it should take to count between the target numbers
	refreshInterval: 100,  // how often the element should be updated
	decimals: 0,           // the number of decimal places to show
	formatter: formatter,  // handler for formatting the value before rendering
	onUpdate: null,        // callback method for every time the element is updated
	onComplete: null       // callback method for when the element finishes updating
};

function formatter(value, settings) {
	return value.toFixed(settings.decimals);
}


