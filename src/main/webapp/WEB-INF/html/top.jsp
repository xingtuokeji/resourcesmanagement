<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<script src="<%=request.getContextPath()%>/js/template.js"></script>
<script id="welcome" type="text/html">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
            <img class="img-circle" src="<%=request.getContextPath()%>/images/user.jpeg" height="30" />
            <span class="caret"></span>
        </a>
    </li>
</script>
<script id="loginAndRegist" type="text/html">
    <li>
        <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
    </li>
    <li>
        <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
    </li>
</script>

<script type="text/javascript">
    //根据用户名密码登陆
    function loginByAccount(){
        $.post(
            '<%=request.getContextPath()%>/front/customer/loginByAccount',
            $('#frmLoginByAccount').serialize(),
            function(result){
                if(result.status==1){
                    //刷新页面
                    //location.href='<%=request.getContextPath()%>/front/product/search';

                    //局部刷新
                    $('#loginModal').modal('hide'); //隐藏模态框
                    var content=template('welcome',result.data);
                    $('#navbarInfo').html(content);
                }else{
                    $('#loginInfo').html(result.message);
                }
            }
        );
    }

    //退出
    function logout(){
        $.post(
            '<%=request.getContextPath()%>/front/customer/logout',
            function(result){
                if(result.status==1){
                    $('#navbarInfo').html(template('loginAndRegist'));
                }
            }
        );
    }

    //发送短信验证码
    function sendVerificationCode(btn){
        $.post(
            '<%=request.getContextPath()%>/sms/sendVerificationCode',
            {'phone':$('#phone').val()},
            function(result){
                if(result.status==1){
                    let time=60;
                    timer=setInterval(function(){
                        if(time>0){
                            $(btn).attr('disabled',true);
                            $(btn).html('重新发送('+time+')');
                            time--;
                        }else{
                            $(btn).attr('disabled',false);
                            $(btn).html('重新发送');
                            clearInterval(timer);  //停止计时器
                        }
                    },1000)
                }
            }
        );
    }

    //短信快捷登陆
    function loginBySms(){
        $.post(
            '<%=request.getContextPath()%>/front/customer/loginBySms',
            $('#frmSms').serialize(),
            function(result){
                if(result.status==1){
                    $('#loginModal').modal('hide'); //隐藏模态框
                    var content=template('welcome',result.data);
                    $('#navbarInfo').html(content);
                }else{
                    $('#loginInfoSms').html(result.message);
                }
            }
        );
    }
</script>

<!-- navbar start -->
<div class="navbar navbar-default clear-bottom">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand logo-style" href="http://www.simtop.com.cn/">
                <img class="brand-img" src="<%=request.getContextPath()%>/images/com-logo1.png" alt="logo" height="70">
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
<%--                <li class="active">--%>
<%--                    <a href="#">商城主页</a>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                    <a href="myOrders.html">我的订单</a>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                    <a href="cart.html">购物车</a>--%>
<%--                </li>--%>
<%--                <li class="dropdown">--%>
<%--                    <a href="center.html">会员中心</a>--%>
<%--                </li>--%>
            </ul>
            <ul class="nav navbar-nav navbar-right" id="navbarInfo">
                <c:choose>
                    <c:when test="${empty customer}">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                        </li>
                        <li>
                            <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="userName">
                            您好：${user.name}
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="<%=request.getContextPath()%>/images/user.jpeg" height="30" />
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                        <i class="glyphicon glyphicon-cog"></i>修改密码
                                    </a>
                                </li>
                                <li>
                                    <a href="#" onclick="logout()">
                                        <i class="glyphicon glyphicon-off"></i> 退出
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
