<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>形拓企业资源管理系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/iconfont/iconfont.css">
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-paginator.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/zshop.js"></script>
    <script type="text/javascript">
        //素材下载数据量统计
        function downloadData(id) {
            $.ajax({
                url:'<%=request.getContextPath()%>/backend/image/downloadStatics',
                type:'POST',
                dataType:'json',
                data:{'imageId':id},
                success:function () {
                    console.log("前端发送下载量请求的数据统计已经到达后台");
                }
            });
        }
        $(function () {
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
                    return '<%=request.getContextPath()%>/front/image/findAll?pageNum='+page;
                }
            })
        })

    </script>
</head>

<body>
<div id="wrapper">
    <!-- navbar start -->
    <jsp:include page="top.jsp"/>
    <!-- navbar end -->
    <!-- content start -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>素材列表</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-inline hot-search" action="<%=request.getContextPath()%>/front/image/findByParams">
                    <div class="form-group">
                        <label class="control-label">素材名称：</label>
                        <input type="text" class="form-control" placeholder="根据素材名称查询" name="imageName">
                    </div>
                    &nbsp;
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                    <div class="form-group">
                        <label class="control-label">素材类型：</label>
                        <select class="form-control input-sm" name="imageTypeId">
                            <option value="-1" selected="selected">查询全部</option>
                            <!--加载素材类型列表数据 todo zhoujie-->
                            <c:forEach items="${imageTypes}" var="imageType">
                                <option value="${imageType.id}">${imageType.imageTypeName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="content-back">
        <div class="container" id="a">
            <div class="row">
                <c:forEach items="${pageInfo.list}" var="image">
                    <div class="col-xs-3  hot-item">
                        <div class="panel clear-panel">
                            <div class="panel-body">
                                <div class="art-back clear-back">
                                    <div class="add-padding-bottom">
                                        <img src="${image.imageUrl}" class="shopImg">
                                    </div>
                                    <h4><a href="">${image.imageName}</a></h4>
<%--                                    <div class="user clearfix pull-right">--%>
<%--                                        ￥${product.price}--%>
<%--                                    </div>--%>
<%--                                    <div class="desc">--%>
<%--                                        ${image.imageDesc}--%>
<%--                                    </div>--%>
                                    <div class="attention pull-right">
                                        <a href="${image.imageUrl}" download="${image.imageName}" onclick="downloadData(${image.id})">下载</a> <i class="icon iconfont icon-gouwuche"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <nav style="text-align: center">
        <ul id="pagination"></ul>
    </nav>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">
        版权所有：上海形拓科技有限公司
    </div>
    <!-- footers end -->
</div>

</body>

</html>