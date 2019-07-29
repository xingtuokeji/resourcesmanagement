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
    <script src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-paginator.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrapValidator.min.js"></script>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/zshop.css" />
    <script type="text/javascript">
        $(function () {
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page, current){
                    return '<%=request.getContextPath()%>/imageType/findAll?pageNum='+page;
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

            $('#addImageType').click(function () {
                console.log("发送ajax请求开始！");
                if($('#imageTypeName1').val()==null||$('#imageTypeName1').val()==""){
                    alert("请输入素材类型！");
                    //return false 等待用户输入信息！
                    return false;
                }
                $.ajax({
                    type:'POST',
                    dataType:'json',
                    url:'<%=request.getContextPath()%>/imageType/add',
                    data:{'imageTypeName':$('#imageTypeName1').val()},
                    success:function (data) {
                        console.log(data);
                        if(data.success){
                            alert("请求响应成功");
                            layer.msg('添加素材分类成功',{
                                time:2000,
                                skin:'successMsg'
                            });
                            window.location.href="/resourcesmanagement/imageType/findAll?pageNum="+${pageInfo.pageNum};
                        }else{
                            layer.msg('添加素材分类失败',{
                                time:2000,
                                skin:'errorMsg'
                            });
                        }
                    }
                });
            });

        });

        function showImageType(id) {
            $.post(
                '<%=request.getContextPath()%>/imageType/findById',
                {'id':id},
                function(result){
                    if(result.success){
                        $('#id').val(result.data.id);
                        console.log(result.data.imageTypeName)
                        $('#imageTypeName').val(result.data.imageTypeName);
                    }
                }
            );
        }

        //修改素材类型
        function modify() {
            $.ajax({
                type: 'post',
                url: '<%=request.getContextPath()%>/imageType/modify',
                data: {'id': $('#id').val(),'imageTypeName':$('#imageTypeName').val()},
                dataType: 'json',
                success: function (result) {
                    if (result.success) {
                        //重新加载数据
                        location.href = '<%=request.getContextPath()%>/imageType/findAll?pageNum='+${pageInfo.pageNum};
                    } else {
                        alert("修改数据失败！")
                    }
                }
            })
        }

        //显示确认删除的提示
        function showDeleteImageType(id){
            $('#deleteImageTypeById').val(id);
            $('#deleteImageType').modal('show');
        }

        //删除商品类型
        function removeImageTypeById(){
            $.get(
                '<%=request.getContextPath()%>/imageType/removeById',
                {'id':$('#deleteImageTypeById').val()},
                function(result){
                    if(result.success){
                        window.location.href='<%=request.getContextPath()%>/imageType/findAll?pageNum='+${pageInfo.pageNum};
                    }else{
                        alert("删除素材分类失败！")
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
        <h3 class="panel-title">素材类型管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加素材类型" class="btn btn-primary" id="doAddProTpye">
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">名称</th>
                    <th class="text-center">创建时间</th>
                    <th class="text-center">最后修改时间</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="imageType">
                    <tr>
                        <td>${imageType.id}</td>
                        <td>${imageType.imageTypeName}</td>
                        <td>
                            <fmt:formatDate value="${imageType.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${imageType.lastEditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doImageTypeModify" value="修改" onclick="showImageType(${imageType.id})">
                            <input type="button" class="btn btn-warning btn-sm doProTypeDelete" value="删除" onclick="showDeleteImageType(${imageType.id})">
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

<!-- 添加素材类型 start -->
<div class="modal fade" tabindex="-1" id="ProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <form id="imageTypeForm">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加素材类型</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="imageTypeName1" class="col-sm-4 control-label">素材分类名称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="imageTypeName1" name="imageTypeName1">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary addUser" id="addImageType">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>
<!-- 添加素材类型用户 end -->

<!-- 修改素材类型 start -->
<div class="modal fade" tabindex="-1" id="myImageType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改素材类型</h4>
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
                    <label for="imageTypeName" class="col-sm-4 control-label">素材名称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="imageTypeName">
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
<!-- 修改素材类型 end -->

<!-- 确认删除 start -->
<div class="modal fade" tabindex="-1" id="deleteImageType">
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
                <input type="hidden" id="deleteImageTypeById">
                <button class="btn btn-primary updateProType" onclick="removeImageTypeById()" data-dismiss="modal">删除</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 确认删除 end -->
</body>

</html>