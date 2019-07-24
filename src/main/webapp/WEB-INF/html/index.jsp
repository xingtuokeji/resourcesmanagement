<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>上海形拓科技管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/mycss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrapValidator.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layer/layer.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/zshop.css" />


    <script type="text/javascript">
        //验证码看不清验证
        function reloadImage() {
            $("#randCode").attr("src","<%=request.getContextPath()%>/backend/code/image?"+new Date().getTime());
            $("#code").val("");
        }

        //登录字段的验证使用bootstrapValidator
        $(function () {
            $("#frmLogin").bootstrapValidator({
                feedbackIcons:{
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    loginName: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    password:{
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            }
                        }
                    },
                    code:{
                        validators:{
                            notEmpty:{
                                message:'请输入验证码'
                            },
                            remote:{
                                url:'<%=request.getContextPath()%>/backend/code/checkCode',
                                message:'验证码不正确'
                            }
                        }
                    }
                }
            });

            //服务端提示消息
            let errorMsg='${errorMsg1}';
            if(errorMsg!=''){
                layer.msg(errorMsg,{
                    time:2000,
                    skin:'errorMsg'
                });
            }
        });

    </script>
</head>
<body>
<!-- 使用自定义css样式 div-signin 完成元素居中-->
<div class="container div-signin" style="margin-top: 160px">
    <div class="panel panel-primary div-shadow">
        <!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
        <div class="panel-heading">
            <h3>上海形拓科技资源管理系统</h3>
            <span>Resources Manager System</span>
        </div>
        <div class="panel-body">
            <!-- login form start -->
            <form action="<%=request.getContextPath()%>/user/login" class="form-horizontal" method="post" id="frmLogin">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名：</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" placeholder="请输入用户名" name="loginName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="password" placeholder="请输入密码" name="loginPassword">
                    </div>
                </div>
<%--                <div class="form-group">--%>
<%--                    <label class="col-sm-3 control-label">验证码：</label>--%>
<%--                    <div class="col-sm-4">--%>
<%--                        <input class="form-control" type="text" placeholder="验证码" id="code" name="code">--%>
<%--                    </div>--%>
<%--                    <div class="col-sm-2">--%>
<%--                        <!-- 验证码 -->--%>
<%--                        <img class="img-rounded" src="<%=request.getContextPath()%>/backend/code/image" id="randCode" style="height: 32px; width: 70px;"/>--%>
<%--                    </div>--%>
<%--                    <div class="col-sm-2">--%>
<%--                        <button type="button" class="btn btn-link" onclick="reloadImage()">看不清</button>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="form-group">
                    <div class="col-sm-3">
                    </div>
                    <div class="col-sm-9 padding-left-0">
                        <div class="col-sm-4">
                            <button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;陆</button>
                        </div>
                        <div class="col-sm-4">
                            <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
                        </div>
<%--                        <div class="col-sm-4">--%>
<%--                            <a type="button" class="btn btn-link btn-block">忘记密码联系管理员</a>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </form>
            <!-- login form end -->
        </div>
    </div>
</div>
<!-- 页尾 版权声明 -->
<%--<div class="container">--%>
<%--    <div class="col-sm-12 foot-css">--%>
<%--        <p class="text-muted credit">--%>
<%--            Copyright 上海形拓科技 版权所有--%>
<%--        </p>--%>
<%--    </div>--%>
<%--</div>--%>

</body>
</html>
