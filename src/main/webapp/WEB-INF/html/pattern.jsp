<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>形拓企业资源管理系统-模型展示前端</title>
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
                    return '<%=request.getContextPath()%>/front/pattern/findAll?pageNum='+page;
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
                    <h3>模型列表</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-inline hot-search" action="<%=request.getContextPath()%>/front/pattern/findByParams">
                    <div class="form-group">
                        <label class="control-label">模型名称：</label>
                        <input type="text" class="form-control" placeholder="根据模型名称查询" name="patternName">
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--前端根据模型面数进行查询-->
                    <div class="form-group">
                        <label class="control-label">模型面数：</label>
                        <select class="form-control input-sm" name="patternSurfaceNumber">
                            <option value="" selected="selected">请选择</option>
                            <option value="5K以下">5K以下</option>
                            <option value="5K-10K">5K-10K</option>
                            <option value="10K-30K">10K-30K</option>
                            <option value="30K-100K">30K-100K</option>
                            <option value="100K以上">100K以上</option>
                        </select>
                    </div>
                    &nbsp;
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                    <div class="form-group">
                        <label class="control-label">模型类型：</label>
                        <select class="form-control input-sm" name="patternTypeId">
                            <option value="-1" selected="selected">查询全部</option>
                            <!--加载模型类型列表数据 todo zhoujie-->
                            <c:forEach items="${patternTypes}" var="patternType">
                                <option value="${patternType.id}">${patternType.patternTypeName}
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
                <c:forEach items="${pageInfo.list}" var="pattern">
                    <div class="col-xs-3  hot-item">
                        <div class="panel clear-panel">
                            <div class="panel-body">
                                <div class="art-back clear-back">
                                    <div class="add-padding-bottom">
                                        <!--点击图片跳转至详情页 todo important-->
                                        <a href="<%=request.getContextPath()%>/front/pattern/detail?id=${pattern.id}"><img src="${pattern.patternUrl}" class="shopImg"></a>
                                    </div>
                                    <h4><a href="">${pattern.patternName}</a></h4>
                                    <div class="attention pull-right">
                                        <a href="${pattern.patternUrl}" download="${pattern.patternName}">下载</a> <i class="icon iconfont icon-gouwuche"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!--前端模型页面分页功能 todo 2019年7月30日10:41:04-->
    <!--居中-->
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