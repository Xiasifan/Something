<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="icon" href="img/favicon.ico">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生管理系统登陆</title>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="css/demo.css"/>
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="css/component.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css">

    <style type="text/css">
        #login {

            background: #3fb9ff;
            font-size: 18px;
            width: 329px;
            height: 44px;
            outline: none;
            color: white;
            background: -webkit-linear-gradient(#3fb9ff, #099be7, #229de3);
            background: -o-linear-gradient(#3fb9ff, #099be7, #229de3);
            background: -moz-linear-gradient(#3fb9ff, #099be7, #229de3);
            background: linear-gradient(#3fb9ff, #099be7, #229de3);
            border-radius: 20px;
        }
        .yanzheng{
            height:30px;
            bottom:10px;
            position: relative;
        }
        .validateCode{
            margin-bottom:10px;
            height: 30px;
            border-radius: 10px;
            margin-top: 0px;
            position: absolute;
        }
        .image{
            display:block;
            right:0px;
            position: absolute;
            float: right;
        }

    </style>
    <script type="text/javascript">
        //刷新验证码
        function changeImg(obj,createTypeFlag){
            document.getElementById(obj.id).src="${pageContext.request.contextPath}/DrawImage?createTypeFlag="+createTypeFlag+"&"+Math.random();
        }    </script>

</head>

<body>


<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>欢迎你</h3>
                <form action="Login" method="post" id="form1">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input name="userName" value="${userName }" class="text" style="color: #FFFFFF !important"
                               type="text" placeholder="请输入账号">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input name="password" value="${password }" class="text"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" value=""
                               type="password" placeholder="请输入密码">
                    </div>
                    <div   class="yanzheng">
                        <input type="text" name="validateCode"  class="validateCode" placeholder="请输入验证码~"   />
                        <img   alt="验证码看不清，换一张"  src="${pageContext.request.contextPath}/DrawImage?createTypeFlag=n" id="validateCodeImg4" onclick="changeImg(this,'n')"   class="image">
                    </div>


                    <button id="login">登陆</button>


                    <td colspan="2">
                        <input type="hidden" checked="checked" name="day" value="0">
                        <input type="radio" name="day" value="7">一周
                        <input type="radio" name="day" value="30">一月
                        <input type="radio" name="day" value="90">三个月
                    </td>
                    <br>
                    <tr>
                        <td><input type="checkbox" name="mark" value="mark"/></td>
                        <td>下次自动登陆</td>
                    </tr>
                    <input type="hidden" name="oper" value="login">
                    <tr height="10">
                        <td width="40%"></td>
                        <td colspan="3">
                            <font color="red">${error }</font>
                        </td>
                    </tr>

                </form>


            </div>
        </div>
    </div>
</div><!-- /container -->
<div id="Model">
    <div id="TimeModel"></div>
</div>
<div id="DK_wxin" class="DK_wxin"><a href="javascript:void(0)">
    <div class="DK_wxinh"></div>
</a></div>
<div id="DK_wshare" class="DK_wshare"><a href="javascript:void(0)">
    <div class="DK_wshareh"></div>
</a></div>
<div class="container">
    <div class="demo1">
        <div id="slider1" class="slider"></div>
    </div>
    <script src="js/TweenLite.min.js"></script>
    <script src="js/EasePack.min.js"></script>
    <script src="js/rAF.js"></script>
    <script src="js/demo-1.js"></script>




    <script type="text/javascript">
        var scrolltotop = {
            setting: {
                startline: 100, //起始行
                scrollto: 0, //滚动到指定位置
                scrollduration: 400, //滚动过渡时间
                fadeduration: [500, 100] //淡出淡现消失
            },
            controlHTML: '<img src="img/top.png" style="width:30px; height:30px; border:0;" />', //返回顶部按钮
            controlattrs: {offsetx: 10, offsety: 10},//返回按钮固定位置
            anchorkeyword: "#top",
            state: {
                isvisible: false,
                shouldvisible: false
            }, scrollup: function () {
                if (!this.cssfixedsupport) {
                    this.$control.css({opacity: 0});
                }
                var dest = isNaN(this.setting.scrollto) ? this.setting.scrollto : parseInt(this.setting.scrollto);
                if (typeof dest == "string" && jQuery("#" + dest).length == 1) {
                    dest = jQuery("#" + dest).offset().top;
                } else {
                    dest = 0;
                }
                this.$body.animate({scrollTop: dest}, this.setting.scrollduration);
            }, keepfixed: function () {
                var $window = jQuery(window);
                var controlx = $window.scrollLeft() + $window.width() - this.$control.width() - this.controlattrs.offsetx;
                var controly = $window.scrollTop() + $window.height() - this.$control.height() - this.controlattrs.offsety;
                this.$control.css({left: controlx + "px", top: controly + "px"});
            }, togglecontrol: function () {
                var scrolltop = jQuery(window).scrollTop();
                if (!this.cssfixedsupport) {
                    this.keepfixed();
                }
                this.state.shouldvisible = (scrolltop >= this.setting.startline) ? true : false;
                if (this.state.shouldvisible && !this.state.isvisible) {
                    this.$control.stop().animate({opacity: 1}, this.setting.fadeduration[0]);
                    this.state.isvisible = true;
                } else {
                    if (this.state.shouldvisible == false && this.state.isvisible) {
                        this.$control.stop().animate({opacity: 0}, this.setting.fadeduration[1]);
                        this.state.isvisible = false;
                    }
                }
            }, init: function () {
                jQuery(document).ready(function ($) {
                    var mainobj = scrolltotop;
                    var iebrws = document.all;
                    mainobj.cssfixedsupport = !iebrws || iebrws && document.compatMode == "CSS1Compat" && window.XMLHttpRequest;
                    mainobj.$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $("html") : $("body")) : $("html,body");
                    mainobj.$control = $('<div id="topcontrol" >' + mainobj.controlHTML + "</div>").css({
                        position: mainobj.cssfixedsupport ? "fixed" : "absolute",
                        bottom: mainobj.controlattrs.offsety,
                        right: mainobj.controlattrs.offsetx,
                        opacity: 0,
                        cursor: "pointer"
                    }).attr({title: "返回顶部"}).click(function () {
                        mainobj.scrollup();
                        return false;
                    }).appendTo("body");
                    if (document.all && !window.XMLHttpRequest && mainobj.$control.text() != "") {
                        mainobj.$control.css({width: mainobj.$control.width()});
                    }
                    mainobj.togglecontrol();
                    $('a[href="' + mainobj.anchorkeyword + '"]').click(function () {
                        mainobj.scrollup();
                        return false;
                    });
                    $(window).bind("scroll resize", function (e) {
                        mainobj.togglecontrol();
                    });
                });
            }
        };
        scrolltotop.init();
    </script>
</body>
</html>