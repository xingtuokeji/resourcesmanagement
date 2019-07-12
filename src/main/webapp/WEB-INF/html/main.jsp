<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>上海形拓科技资源管理系统</title>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/index.css" />
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script type="text/javascript">

        /*
         * 页面中动态显示当前时间 js中Date内置对象的使用
         */
        function showTime(){
            var nowtime=new Date();
            var year=nowtime.getFullYear();
            var month=nowtime.getMonth()+1;
            var date=nowtime.getDate();
            document.getElementById("time").innerText=year+"年"+month+"月"+date+"日"+" "+nowtime.toLocaleTimeString();
        }
        //每1秒钟显示当前时间
        setInterval("showTime()",1000);

        $(function(){
            // 点击切换页面
            $("#product-type-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/user/findAll");
            });
            $("#product-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/product/findAll");
            });
            $("#user-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/customer/findAll");
            });
            $("#manager-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/sysuser/findAll");
            });
            $("#order-manager").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/order/findAll");
            });
        });
    </script>
</head>

<body>
<div class="wrapper-cc clearfix">
    <div class="content-cc">
        <!-- header start -->
        <div class="clear-bottom head">
            <div class="container head-cc">
                <p>上海形拓科技资源管理系统</p>
                <div class="welcome">
                    <div class="left">欢迎您：&nbsp;&nbsp;&nbsp;</div>
                    <div class="right">${currentUser.name}</div>
                    <div class="exit"><a style="text-decoration: none; color: white" href="<%=request.getContextPath()%>/user/logout">退出</a></div>
                </div>
            </div>
        </div>
        <!-- header end -->
        <!-- content start -->
        <div class="container-flude flude-cc" id="a">
            <div class="row user-setting">
                <div class="col-xs-2 user-wrap">
                    <ul class="list-group">
                        <!--role_id为1可以显示所有菜单，role_id为2显示模型管理和素材管理菜单-->
                        <c:if test="${currentUser.roleId==1}">
                            <li class="list-group-item active" name="userSet" id="product-type-set">
                                <i class="glyphicon glyphicon-lock"></i> &nbsp;系统用户管理
                            </li>
                            <li class="list-group-item" name="userPic" id="product-set">
                                <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;模型管理
                            </li>
                            <li class="list-group-item" name="userInfo" id="user-set">
                                <i class="glyphicon glyphicon-user"></i> &nbsp;素材管理
                            </li>
                            <!--todo 待开发-->
                            <li class="list-group-item" name="daikaifa" id="manager-set">
                                <i class="glyphicon glyphicon-globe"></i> &nbsp;待开发模块
                            </li>
                        </c:if>
                        <c:if test="${currentUser.roleId==2}">
                            <li class="list-group-item" name="userPic" id="product-set">
                                <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;模型管理
                            </li>
                            <li class="list-group-item" name="userInfo" id="user-set">
                                <i class="glyphicon glyphicon-user"></i> &nbsp;素材管理
                            </li>
                            <!--todo 待开发-->
                            <li class="list-group-item" name="daikaifa" id="manager-set">
                                <i class="glyphicon glyphicon-globe"></i> &nbsp;待开发模块
                            </li>
                        </c:if>



                    </ul>
                </div>
                <div class="col-xs-10" id="userPanel">
                    <iframe id="frame-id" src="<%=request.getContextPath()%>/daikaifa/findAll" width="100%" height="100%" frameborder="0" scrolling="no">
                    </iframe>
                </div>

            </div>
        </div>
        <!-- content end-->
    </div>
</div>
<!-- footers start -->
<div class="footer">
    <span>版权所有：上海形拓科技有限公司</span>
    <button style="margin-right: 0px;" class="btn btn-danger btn-xs">当前服务器时间：<span id="time"></span></button>
</div>
<!-- footers end -->
</body>

</html>
