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
            $("#user-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/user/findAll");
            });
            $("#image-type-set").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/imageType/findAll");
            });
            //后台素材管理CRUD
            $("#image-upload-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/image/findAll");
            });
            //点击素材查询与下载跳转至素材前端 在新的页面中显示前段主页
            $("#image-select-set").click(function () {
                window.open("<%=request.getContextPath()%>/front/image/findAll");
            });
            //点击菜单信息统计，跳转至信息统计页面
            $("#info-statistics").click(function () {
               $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/image/showAll");
            });
            //系统登陆安全日志信息
            $("#system-info").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/system/findAll");
            });
            //文件上传功能
            $("#file-sop").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/file/findAll");
            });
            //模型类型管理功能
            $("#model-upload-set").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/patternType/findAll");
            })
            //模型信息上传
            $("#model-info-set").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/pattern/findAll");
            })
            //模型查询与下载（详情页开发） todo
            $("#model-info-download").click(function () {
                window.open("<%=request.getContextPath()%>/front/pattern/findAll");
            })
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
                            <li class="list-group-item active" name="userPic" id="image-upload-set">
                                <i class="glyphicon glyphicon-arrow-up"></i> &nbsp;素材上传
                            </li>
                            <li class="list-group-item" name="userPic" id="model-info-set">
                                <i class="glyphicon glyphicon-arrow-up"></i> &nbsp;模型上传
                            </li>
                            <li class="list-group-item" name="userPic" id="model-info-download">
                                <i class="glyphicon glyphicon-arrow-down"></i> &nbsp;模型查询与下载
                            </li>
                            <li class="list-group-item" name="userInfo" id="image-select-set">
                                <i class="glyphicon glyphicon-arrow-down"></i> &nbsp;素材查询与下载
                            </li>
                            <li class="list-group-item" name="userInfo" id="image-type-set">
                                <i class="glyphicon glyphicon-heart"></i> &nbsp;素材类别管理
                            </li>
                            <li class="list-group-item" name="userPic" id="model-upload-set">
                                <i class="glyphicon glyphicon-heart"></i> &nbsp;模型类别管理
                            </li>
                            <!--信息统计功能 todo-->
                            <li class="list-group-item" name="userInfo" id="info-statistics">
                                <i class="glyphicon glyphicon-search"></i> &nbsp;信息统计
                            </li>
                            <!--系统使用相关SOP文件-->
                            <li class="list-group-item" name="uerInfo" id="file-sop">
                                <i class="glyphicon glyphicon-folder-open"></i> &nbsp;API/SOP文档管理
                            </li>
                            <li class="list-group-item" name="userSet" id="user-set">
                                <i class="glyphicon glyphicon-user"></i> &nbsp;系统用户管理
                            </li>
                            <li class="list-group-item" name="uerInfo" id="system-info">
                                <i class="glyphicon glyphicon-cog"></i> &nbsp;系统登陆安全日志
                            </li>
                            <!--todo 待开发-->
                            <li class="list-group-item" name="daikaifa" id="manager-set">
                                <i class="glyphicon glyphicon-globe"></i> &nbsp;待开发模块
                            </li>
                        </c:if>
                        <c:if test="${currentUser.roleId==2}">
                            <li class="list-group-item active" name="userPic" id="image-upload-set">
                                <i class="glyphicon glyphicon-arrow-up"></i> &nbsp;素材上传
                            </li>
                            <li class="list-group-item" name="userPic" id="model-info-set">
                                <i class="glyphicon glyphicon-arrow-up"></i> &nbsp;模型上传
                            </li>
                            <li class="list-group-item" name="userPic" id="model-info-download">
                                <i class="glyphicon glyphicon-arrow-down"></i> &nbsp;模型查询与下载
                            </li>
                            <li class="list-group-item" name="userInfo" id="image-select-set">
                                <i class="glyphicon glyphicon-arrow-down"></i> &nbsp;素材查询与下载
                            </li>
                            <li class="list-group-item" name="userInfo" id="image-type-set">
                                <i class="glyphicon glyphicon-heart"></i> &nbsp;素材类别管理
                            </li>
                            <li class="list-group-item" name="userPic" id="model-upload-set">
                                <i class="glyphicon glyphicon-heart"></i> &nbsp;模型类别管理
                            </li>
                            <li class="list-group-item" name="uerInfo" id="file-sop">
                                <i class="glyphicon glyphicon-folder-open"></i> &nbsp;API/SOP文档管理
                            </li>
                        </c:if>
                    </ul>
                </div>
                <div class="col-xs-10" id="userPanel">
                    <iframe id="frame-id" src="<%=request.getContextPath()%>/backend/image/findAll" width="100%" height="100%" frameborder="0" scrolling="no">
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
