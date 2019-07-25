<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            $('#frmAddProduct').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    name:{
                        validators:{
                            notEmpty:{
                                message:'商品名称不能为空'
                            },
                            remote:{
                                type:'post',
                                url:'<%=request.getContextPath()%>/backend/product/checkName'
                            }
                        }
                    },
                    price:{
                        validators:{
                            notEmpty:{
                                message:'商品价格不能为空'
                            }
                        }
                    },
                    file:{
                        validators:{
                            notEmpty:{
                                message:'请选择商品图片'
                            }
                        }
                    },
                    productTypeId:{
                        validators:{
                            notEmpty:{
                                message:'请选择商品类型'
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
                    return '<%=request.getContextPath()%>/backend/image/findAll?pageNum='+page;
                }
            })


        });

        function showPattern(id){
            $.post(
                '<%=request.getContextPath()%>/backend/pattern/findById',
                {'id':id},
                function(result){
                    if(result.success){
                        $('#pattern-num').val(result.data.id);
                        $('#pattern-name').val(result.data.patternName);
                        $('#pattern-typeId').val(result.data.patternType.id);
                        $('#pattern-desc').val(result.data.patternDesc)
                        //点击修改，加载图片
                        $('#img2').attr('src','<%=request.getContextPath()%>/backend/pattern/getPattern?id='+result.data.id);
                    }
                }
            );
        }

        //显示删除素材模态框
        function showDeleteModal(id){
            $('#deletePatternId').val(id);
            $('#deleteImageModal').modal('show');
        }

        //删除素材
        function deleteImage(){
            $.post(
                '<%=request.getContextPath()%>/backend/pattern/removeById',
                {'id':$('#deletePatternId').val()},
                function(result){
                    if(result.success){
                        layer.msg('删除成功',{
                            time:2000,
                            skin:'successMsg'
                        });
                        window.location.href='<%=request.getContextPath()%>/backend/pattern/findAll?pageNum='+${pageInfo.pageNum};
                    }
                }
            )
        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">模型上传</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加模型" class="btn btn-primary" id="doAddPro">
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">模型名称</th>
                    <th class="text-center">模型分类</th>
                    <th class="text-center">模型存储路径</th>
                    <th class="text-center">模型作者</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="pattern">
                    <tr>
                        <td>${pattern.id}</td>
                        <td>${pattern.patternName}</td>
                        <td>${pattern.patternType.patternTypeName}</td>
                        <td>${pattern.patternUrl}</td>
                        <td>${pattern.patternUploader}</td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProModify" value="修改" onclick="showPattern(${pattern.id})">
                            <input type="button" class="btn btn-warning btn-sm doProDelete" value="删除" onclick="showDeleteModal(${pattern.id})">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 添加模型 start -->
<div class="modal fade" tabindex="-1" id="Product">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="<%=request.getContextPath()%>/backend/pattern/add" class="form-horizontal" method="post" enctype="multipart/form-data" id="frmAddProduct">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加模型</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                        <div class="form-group">
                            <label for="image-name" class="col-sm-4 control-label">模型名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="image-name" name="patternName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="image-desc" class="col-sm-4 control-label">模型描述：</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" id="image-desc" name="patternDesc">
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="image_sc" class="col-sm-4 control-label">模型：</label>
                            <div class="col-sm-8">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="file" id="image_sc">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="image-type" class="col-sm-4 control-label">模型类型：</label>
                            <div class="col-sm-8" id="image-type">
                                <select class="form-control" name="patternTypeId">
                                    <option value="">--请选择--</option>
                                    <c:forEach items="${patternTypes}" var="patternType">
                                        <option value="${patternType.id}">${patternType.patternTypeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!--模型上传预览 todo -->
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img">
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
<!-- 添加模型 end -->

<!-- 修改模型 start -->
<div class="modal fade" tabindex="-1" id="myProduct">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="<%=request.getContextPath()%>/backend/pattern/modify" method="post" enctype="multipart/form-data" class="form-horizontal">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改模型</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                        <div class="form-group">
                            <label for="pattern-num" class="col-sm-4 control-label">模型编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pattern-num" name="id" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pattern-name" class="col-sm-4 control-label">模型名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pattern-name" name="patternName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pattern-desc" class="col-sm-4 control-label">模型描述：</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" id="pattern-desc" name="patternDesc">
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-image" class="col-sm-4 control-label">模型文件：</label>
                            <div class="col-sm-8">
                                <a class="file">
                                    选择文件 <input type="file" name="file" id="pro-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pattern-typeId" class="col-sm-4 control-label">模型类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="pattern-typeId" name="patternTypeId">
                                    <option>--请选择--</option>
                                    <!--显示素材类型-->
                                    <c:forEach items="${patternTypes}" var="patternType">
                                        <option value="${patternType.id}">${patternType.patternTypeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示模型预览 -->
                        <img style="width: 160px;height: 180px;" id="img2">
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
<!-- 修改模型 end -->

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
                <h4>确认要删除该模型吗</h4>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="deletePatternId">
                <button class="btn btn-primary updatePro" data-dimiss="modal" onclick="deleteImage()">确认</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 确认删除 end -->
</body>

</html>