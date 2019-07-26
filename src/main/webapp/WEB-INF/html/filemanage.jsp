<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/index.css" />
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/file.css" />
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrapValidator.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-paginator.js"></script>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/bootstrapValidator.min.css" />
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/zshop.css" />
    <script>
        $(function(){
            //上传图像预览
            $('#image_sc').on('change',function() {
                $('#img').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $('#pro-image').on('change',function() {
                $('#img2').attr('src', window.URL.createObjectURL(this.files[0]));
            });

            //服务端提示消息
            let successMsg='${successMsg}';
            let errorMsg='${errorMsg}';
            if(successMsg!=''){
                layer.msg(successMsg,{
                    time:2000,
                    skin:'successMsg'
                })
            }
            if(errorMsg!=''){
                layer.msg(errorMsg,{
                    time:2000,
                    skin:'errorMsg'
                })
            }

            //使用bootstrap validator插件进行客户端数据校验
            $('#addFileForm').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    fileName:{
                        validators:{
                            notEmpty:{
                                message:'文档名称不能为空'
                            }
                        }
                    },
                    file:{
                        validators:{
                            notEmpty:{
                                message:'请选择文档'
                            }
                        }
                    }
                }
            });

            //分页
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                itemTexts:function(type,page,current){
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
                },
                pageUrl:function(type,page,current){
                    return '<%=request.getContextPath()%>/backend/file/findAll?pageNum='+page;
                }
            })


        });

        //显示文件信息 点击修改发送file id给后台返回相关数据给到表单
        function showFile(id){
            $.post(
                '<%=request.getContextPath()%>/backend/file/findById',
                {'id':id},
                function(result){
                    if(result.success){
                        console.log("前台获取数据成功！");
                        $('#id').val(result.data.id);
                        console.log(result.data.fileName);
                        console.log(result.data.id);
                        $('#fileName').val(result.data.fileName);
                    }
                }
            );
        }

        //显示删除素材模态框
        function showDeleteModal(id){
            $('#deleteImageId').val(id);
            $('#deleteImageModal').modal('show');
        }

        //删除素材
        function deleteImage(){
            $.post(
                '<%=request.getContextPath()%>/backend/image/removeById',
                {'id':$('#deleteImageId').val()},
                function(result){
                    if(result.success){
                        layer.msg('删除成功',{
                            time:2000,
                            skin:'successMsg'
                        });
                        window.location.href='<%=request.getContextPath()%>/backend/image/findAll?pageNum='+${pageInfo.pageNum};
                    }
                }
            )
        }


    </script>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">SOP文档管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加Sop文档" class="btn btn-primary" id="doAddPro">
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">文档名称</th>
                    <th class="text-center">文档存入路径</th>
                    <th>文件上传者</th>
                    <th>文件上传时间</th>
                    <th class="text-center">操作</th>

                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="file">
                    <tr>
                        <td>${file.id}</td>
                        <td>${file.fileName}</td>
                        <td>${file.fileUrl}</td>
                        <td>${file.fileUploader}</td>
                        <td>
                                <fmt:formatDate value="${file.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProModify" value="修改" onclick="showFile(${file.id})">

                            <a id="download" type="button" class="btn btn-success btn-sm doProDelete" href="${file.fileUrl}">下载</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 添加文档 start -->
<div class="modal fade" tabindex="-1" id="Product">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="<%=request.getContextPath()%>/backend/file/add" class="form-horizontal" method="post" enctype="multipart/form-data" id="addFileForm">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加SOP文档</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                        <div class="form-group">
                            <label for="image-name" class="col-sm-4 control-label">文档名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="image-name" name="fileName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="image_sc" class="col-sm-4 control-label">文件：</label>
                            <div class="col-sm-8">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="file" id="image_sc">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input class="btn btn-primary" type="submit" value="添加">
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 添加素材 end -->

<!-- 修改素材 start -->
<div class="modal fade" tabindex="-1" id="myProduct">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="<%=request.getContextPath()%>/backend/file/modify" method="post" enctype="multipart/form-data" class="form-horizontal">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改文件</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                        <div class="form-group">
                            <label for="id" class="col-sm-4 control-label">文件编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="id" name="id" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fileName" class="col-sm-4 control-label">文件名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="fileName" name="fileName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-image" class="col-sm-4 control-label">文件：</label>
                            <div class="col-sm-8">
                                <a class="file">
                                    选择文件 <input type="file" name="file" id="pro-image">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updatePro" type="submit">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 修改素材 end -->

<!-- 确认删除 start -->
<div class="modal fade" tabindex="-1" id="deleteImageModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center row">
                <h4>确认要删除该素材吗</h4>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="deleteImageId">
                <button class="btn btn-primary updatePro" data-dimiss="modal" onclick="deleteImage()">确认</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 确认删除 end -->
</body>

</html>