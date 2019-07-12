<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/index.css" />
<link type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link type="text/css" href="<%=request.getContextPath()%>/css/fileinput.min.css">
<link type="text/css" href="<%=request.getContextPath()%>/css/fileinput-rtl.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fileinput.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zh.js"></script>
<html>
<head>
    <title>文件上传</title>
    <script type="text/javascript">
        $(function () {
            initFileInput("input-id");
        })

        function initFileInput(ctrlName) {
            var control = $('#' + ctrlName);
            control.fileinput({
                language: 'zh', //设置语言
                uploadUrl: "", //上传的地址
                allowedFileExtensions: ['jpg', 'gif', 'png','doc','docx','pdf','ppt','pptx','txt'],//接收的文件后缀
                maxFilesNum : 5,//上传最大的文件数量
                //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
                uploadAsync: true, //默认异步上传
                showUpload: true, //是否显示上传按钮
                showRemove : true, //显示移除按钮
                showPreview : true, //是否显示预览
                showCaption: false,//是否显示标题
                browseClass: "btn btn-primary", //按钮样式
                //dropZoneEnabled: true,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                //maxFileCount: 10, //表示允许同时上传的最大文件个数
                enctype: 'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

            }).on('filepreupload', function(event, data, previewId, index) {     //上传中
                var form = data.form, files = data.files, extra = data.extra,
                    response = data.response, reader = data.reader;
                console.log('文件正在上传');
            }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
                console.log('文件上传成功！'+data.id);

            }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
                console.log('文件上传失败！'+data.id);
            })
        }
    </script>
    <style type="text/css">
        .images{
            background-image: url("<%=request.getContextPath()%>/images/3.jpg");
        }
    </style>
</head>
<body>
<div class="panel panel-default images" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">素材上传管理</h3>
    </div>
<%--    <div class="panel-body">--%>
<%--        <input type="button" value="添加素材类型" class="btn btn-primary" id="doAddProTpye">--%>
<%--        <br>--%>
<%--        <br>--%>
<%--        <div class="show-list">--%>
<%--            <table class="table table-bordered table-hover" style='text-align: center;'>--%>
<%--                <thead>--%>
<%--                <tr class="text-danger">--%>
<%--                    <th class="text-center">编号</th>--%>
<%--                    <th class="text-center">名称</th>--%>
<%--                    <th class="text-center">创建时间</th>--%>
<%--                    <th class="text-center">最后修改时间</th>--%>
<%--                    <th class="text-center">操作</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody id="tb">--%>
<%--                <c:forEach items="${pageInfo.list}" var="imageType">--%>
<%--                    <tr>--%>
<%--                        <td>${imageType.id}</td>--%>
<%--                        <td>${imageType.imageTypeName}</td>--%>
<%--                        <td>--%>
<%--                            <fmt:formatDate value="${imageType.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <fmt:formatDate value="${imageType.lastEditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--                        </td>--%>
<%--                        <td class="text-center">--%>
<%--                            <input type="button" class="btn btn-warning btn-sm doImageTypeModify" value="修改" onclick="showImageType(${imageType.id})">--%>
<%--                            <input type="button" class="btn btn-warning btn-sm doProTypeDelete" value="删除" onclick="showDeleteImageType(${imageType.id})">--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--            <!-- 使用bootstrap-paginator实现分页 -->--%>
<%--            <nav style="text-align: center">--%>
<%--                <ul id="pagination"></ul>--%>
<%--            </nav>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>
<%--<div class="container-fluid">--%>
<%--    <form id="form" action="" method="post" enctype="multipart/form-data">--%>
<%--        <div class="row form-group">--%>
<%--            <div class="panel panel-primary">--%>
<%--                <div class="panel-heading" align="center">--%>
<%--                    <label style="text-align: center;font-size: 18px;">文 件 上 传</label>--%>
<%--                </div>--%>
<%--                <div class="panel-body">--%>
<%--                    <div class="col-sm-12">--%>
<%--                        <input id="input-id" name="file" multiple type="file" data-show-caption="true">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>
</body>
</html>
