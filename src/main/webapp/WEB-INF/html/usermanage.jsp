<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/zshop.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrapValidator.min.css"/>
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-paginator.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrapValidator.min.js"></script>
    <script src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page, current){
                    console.log("page="+page);
                    return '<%=request.getContextPath()%>/user/findAll?pageNum='+page;
                },
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                }
            });

            <%--//服务端提示消息--%>
            <%--let successMsg='${successMsg}';--%>
            <%--let errorMsg='${errorMsg}';--%>
            <%--if(successMsg!=''){--%>
            <%--    layer.msg(successMsg,{--%>
            <%--        time:2000,--%>
            <%--        skin:'successMsg'--%>
            <%--    })--%>
            <%--}--%>
            <%--if(errorMsg!=''){--%>
            <%--    layer.msg(errorMsg,{--%>
            <%--        time:2000,--%>
            <%--        skin:'errorMsg'--%>
            <%--    })--%>
            <%--}--%>

            $('#addUser').click(function () {
                $.ajax({
                    type:'POST',
                    dataType:'json',
                    url:'<%=request.getContextPath()%>/user/add',
                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                    data:{loginName:$('#loginName').val(),loginPassword:$('#loginPassword').val(),name:$('#name').val(),roleId:$('#roleId').val()
                    ,enableStatus:$('#enableStatus').val()},
                    success:function (data) {
                        if(data.success){
                            layer.msg('添加成功',{
                                time:2000,
                                skin:'successMsg'
                            },function(){
                                location.href="<%=request.getContextPath()%>/user/findAll?pageNum="+${pageInfo.pageNum};
                            })
                        }else{
                            layer.msg('$(errorMsg)',{
                                time:2000,
                                skin:'errorMsg'
                            });
                        }
                    }
                });
            });
            //添加用户数据时候的表单验证
            $("#addUserForm").bootstrapValidator({
                message:'值不能为空',
                feedbackIcons:{
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    loginName:{
                        validators:{
                            notEmpty:{
                                message:'登陆账户不能为空'
                            }
                        }
                    },
                    loginPassword:{
                        validators:{
                            notEmpty:{
                                message:'登陆密码不能为空'
                            }
                        }
                    },
                    name:{
                        validators:{
                            notEmpty:{
                                message:'姓名不能为空'
                            }
                        }
                    },
                    roleId:{
                        validators:{
                            notEmpty:{
                                message:'用户角色不能为空'
                            }
                        }
                    },
                    enableStatus:{
                        validators:{
                            notEmpty:{
                                message:'用户状态不能为空'
                            }
                        }
                    }
                }
            })

        });

        function showUser(id) {
            $.post(
                '<%=request.getContextPath()%>/user/findById',
                {'id':id},
                function(result){
                    if(result.success){
                        $('#id').val(result.data.id);
                        $('#loginName1').val(result.data.loginName);
                        $('#pwd').val(result.data.loginPassword);
                        $('#name1').val(result.data.name);
                        $('#roleId1').val(result.data.roleId);
                    }
                }
            );
        }

        function modify() {
            $.ajax({
                type: 'post',
                url: '<%=request.getContextPath()%>/user/modify',
                data: {'id': $('#id').val(), 'loginName': $('#loginName1').val(),'loginPassword':$('#pwd').val(),'name':$('#name1').val()
                ,'roleId':$('#roleId1').val()},
                dataType: 'json',
                success: function (result) {
                    if (result.success) {
                        //重新加载数据
                        location.href = '<%=request.getContextPath()%>/user/findAll?pageNum='+${pageInfo.pageNum};
                        //服务端提示消息 没有起作用
                        let successMsg='${successMsg}';
                        if(successMsg!=''){
                            layer.msg(successMsg,{
                                time:2000,
                                skin:'successMsg'
                            });
                        }
                    } else {
                        alert("修改数据失败！")
                    }
                }
            })
        }

        //显示确认删除的提示
        function showDeleteUser(id){
            $('#deleteUserById').val(id);
            $('#deleteUser').modal('show');
        }

        //删除商品类型
        function removeUserById(){
            $.get(
                '<%=request.getContextPath()%>/user/removeById',
                {'id':$('#deleteUserById').val()},
                function(result){
                    if(result.success){
                        window.location.href='<%=request.getContextPath()%>/user/findAll?pageNum='+${pageInfo.pageNum};
                    }else{
                        alert("删除系统用户失败！")
                    }
                }
            );
        }

        //修改系统用户的使用状态
        function modifyStatus(id,btn){
            $.get(
                '<%=request.getContextPath()%>/user/modifyStatus',
                {'id':id},
                function(result){
                    if(result.success){
                        //局部刷新
                        let $td=$(btn).parent().prev();
                        if($td.text().trim()=='可用'){
                            $td.text('禁用');
                            $(btn).val('可用').removeClass('btn-danger').addClass('btn-success');
                        }else{
                            $td.text('可用');
                            $(btn).val('禁用').removeClass('btn-success').addClass('btn-danger');
                        }
                    }
                }
            );
        }

    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">系统用户管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加系统用户" class="btn btn-primary" id="doAddProTpye">
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">账号</th>
                    <th class="text-center">密码</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">角色</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.loginName}</td>
                        <td>${user.loginPassword}</td>
                        <td>${user.name}</td>
                        <td>
                            <c:if test="${user.roleId==1}">管理员</c:if>
                            <c:if test="${user.roleId==2}">普通用户</c:if>
                        </td>
                        <td>
                            <c:if test="${user.enableStatus==1}">可用</c:if>
                            <c:if test="${user.enableStatus==2}">禁用</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProTypeModify" value="修改" onclick="showUser(${user.id})">
                            <input type="button" class="btn btn-warning btn-sm doProTypeDelete" value="删除" onclick="showDeleteUser(${user.id})">
                            <c:if test="${user.enableStatus==1}">
                                <input type="button" class="btn btn-danger btn-sm doProTypeDisable" value="禁用" onclick="modifyStatus(${user.id},this)">
                            </c:if>
                            <c:if test="${user.enableStatus==2}"><!--表示修改素材类型-->
                                <input type="button" class="btn btn-success btn-sm doProTypeDisable" value="可用" onclick="modifyStatus(${user.id},this)">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 使用bootstrap-paginator实现分页 -->
            <nav style="text-align: center">
                <ul id="pagination"></ul>
            </nav>
        </div>
    </div>
</div>

<!-- 添加系统用户 start -->
<div class="modal fade" tabindex="-1" id="ProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <form id="addUserForm">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加系统用户</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="loginName" class="col-sm-4 control-label">账号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginName" name="loginName">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="loginPassword" class="col-sm-4 control-label">密码：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginPassword" name="loginPassword">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">姓名：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">角色：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="roleId" placeholder="请填入数字（1管理员 2普通用户）" name="roleId">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">状态：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="enableStatus" placeholder="请填入数字（1可用 2禁用）" name="enableStatus">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary addUser" id="addUser">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>
<!-- 添加系统用户 end -->

<!-- 修改用户 start -->
<div class="modal fade" tabindex="-1" id="myProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改商品类型</h4>
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
                    <label for="loginName1" class="col-sm-4 control-label">账号</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginName1">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="pwd" class="col-sm-4 control-label">密码</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="pwd">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name1" class="col-sm-4 control-label">姓名</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="name1" readonly>
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name1" class="col-sm-4 control-label" style="color: red">角色</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="roleId1">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name1" class="col-sm-4 control-label">提示</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="recommend" value=" 1：管理员 2：普通用户" readonly>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-warning updateProType" onclick="modify()">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改商品类型 end -->

<!-- 确认删除 start -->
<div class="modal fade" tabindex="-1" id="deleteUser">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <h4>确认要删除该系统用户吗？</h4>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="deleteUserById">
                <button class="btn btn-primary updateProType" onclick="removeUserById()" data-dismiss="modal">删除</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 确认删除 end -->
</body>

</html>