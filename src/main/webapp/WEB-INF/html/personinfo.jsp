<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/index.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrapValidator.min.css"/>
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-paginator.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript">
        function showPersonInfo(id) {
            $.post(
                '<%=request.getContextPath()%>/user/findById',
                {'id':id},
                function(result){
                    if(result.success){
                        $('#id').val(result.data.id);
                        $('#name').val(result.data.name);
                        $('#loginName').val(result.data.loginName);
                        $('#loginPassword').val(result.data.loginPassword);
                    }
                }
            );
        }

        //修改个人密码
        function modify() {
            $.ajax({
                type: 'post',
                url: '<%=request.getContextPath()%>/user/modifyPwd',
                data: {'id': $('#id').val(),'loginPassword':$('#loginPassword').val()},
                dataType: 'json',
                success: function (result) {
                    if (result.success) {
                        //重新加载数据
                        location.href = '<%=request.getContextPath()%>/user/findCurrent';
                    } else {
                        alert("修改数据失败！")
                    }
                }
            })
        }






    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">个人信息管理</h3>
    </div>
    <div class="panel-body">
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">登陆账号</th>
                    <th class="text-center">登陆密码</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.loginName}</td>
                        <td>${user.loginPassword}</td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doImageTypeModify" value="修改" onclick="showPersonInfo(${user.id})">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>


<!-- 修改个人信息 start -->
<div class="modal fade" tabindex="-1" id="myImageType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改个人信息</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="id" class="col-sm-4 control-label">编号</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="id" readonly>
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">姓名</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="name" readonly>
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="loginName" class="col-sm-4 control-label">账号</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginName" readonly>
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="loginPassword" class="col-sm-4 control-label">密码</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginPassword">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-warning updateImageType" onclick="modify()">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改个人信息 end -->

</body>
</html>