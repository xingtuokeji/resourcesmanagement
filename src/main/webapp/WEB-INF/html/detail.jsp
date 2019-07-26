<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page isELIgnored="false" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>模型详情页</title>--%>
<%--    <style type="text/css">--%>
<%--        .div0{--%>
<%--            width: 1100px;/*给定一个宽度*/--%>
<%--            height: 400px;/*高度当然可以根据内容自适应，在这里设置为固定值只为结果显示直观*/--%>
<%--            /*background: #90A3A2;*/--%>
<%--            margin: 50px auto;/*设置水平居中*/--%>
<%--        }--%>
<%--        .div1{--%>
<%--            width: 800px;--%>
<%--            height: 400px;--%>
<%--            /*background: #BB9797;*/--%>
<%--            float: left;--%>
<%--            /*text-align: center;*/--%>
<%--        }--%>
<%--        .div2{--%>
<%--            width: 300px;--%>
<%--            height: 400px;--%>
<%--            background:#999999;--%>
<%--            float: right;/*左边布局设置浮动为left，右边布局设置浮动为right*/--%>
<%--            /*text-align: center;*/--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>

<%--<body>--%>
<%--<h1 align="center">模型详情页</h1>--%>
<%--    <div class="div0">--%>
<%--        <div class="div1">--%>
<%--            <img id="pattern" src="${pattern.patternUrl}" style="width: 750px;height: 600px"/>--%>
<%--        </div>--%>
<%--        <div class="div2">--%>
<%--            <p>模型名称：${pattern.patternName}</p>--%>
<%--            <p>作者：${pattern.patternUploader}</p>--%>
<%--            <p>描述：${pattern.patternDesc}</p>--%>
<%--            <p>创建时间：<fmt:formatDate value="${pattern.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>--%>
<%--            <p><a href="${pattern.patternUrl}" download="${pattern.patternName}" onclick="downloadData(${pattern.id})">下载模型</a> <i class="icon iconfont icon-gouwuche"></i></p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>模型详情页</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <style>

        body {
            padding-top: 50px;
            padding-bottom: 40px;
            color: #5a5a5a;
            background-image: url("../../images/timg.jpg");
        }

        /* 轮播广告 */

        .carousel {
            height: 500px;
            margin-bottom: 60px;
        }

        .carousel .item {
            height: 500px;
            background-color: #000;
        }

        .carousel .item img {
            width: 100%;
        }

        .carousel-caption {
            z-index: 10;
        }

        .carousel-caption p {
            margin-bottom: 20px;
            font-size: 20px;
            line-height: 1.8;
        }

        /* 简介 */

        .summary {
            padding-right: 15px;
            padding-left: 15px;
        }

        .summary .col-md-4 {
            margin-bottom: 20px;
            text-align: center;
        }

        /* 特性 */

        .feature-divider {
            margin: 40px 0;
        }

        .feature {
            padding: 30px 0;
        }

        .feature-heading {
            font-size: 50px;
            color: #2a6496;
        }

        .feature-heading .text-muted {
            font-size: 28px;
        }

        /* 响应式布局 */

        @media (max-width: 768px) {

            .summary {
                padding-right: 3px;
                padding-left: 3px;
            }

            .carousel {
                height: 300px;
                margin-bottom: 30px;
            }

            .carousel .item {
                height: 300px;
            }

            .carousel img {
                min-height: 300px;
            }

            .carousel-caption p {
                font-size: 16px;
                line-height: 1.4;
            }

            .feature-heading {
                font-size: 34px;
            }

            .feature-heading .text-muted {
                font-size: 22px;
            }
        }

        @media (min-width: 992px) {
            .feature-heading {
                margin-top: 120px;
            }
        }
        span{
            font-weight:bold;
        }
    </style>

</head>

<body>
<!-- 顶部导航 -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" id="menu-nav">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
<%--                <li class="active"><a href="#ad-carousel">综述</a></li>--%>
<%--                <li><a href="#summary-container">简述</a></li>--%>
<%--                <li class="dropdown">--%>
<%--                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">特点 <span class="caret"></span></a>--%>
<%--                    <ul class="dropdown-menu" role="menu">--%>
<%--                        <li><a href="#feature-tab" data-tab="tab-chrome">Chrome</a></li>--%>
<%--                        <li><a href="#feature-tab" data-tab="tab-firefox">Firefox</a></li>--%>
<%--                        <li><a href="#feature-tab" data-tab="tab-safari">Safari</a></li>--%>
<%--                        <li><a href="#feature-tab" data-tab="tab-opera">Opera</a></li>--%>
<%--                        <li><a href="#feature-tab" data-tab="tab-ie">IE</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
                <li class="active" style="margin-left: 80px"><a href="">模型详情页</a></li>
                <li><a href="#" data-toggle="modal" data-target="#about-modal">关于</a></li>
            </ul>
        </div>
    </div>
</div>

<!-- 主要内容 -->
<div class="container summary" style="width: 1000px">
    <!--图片展示-->
    <div style="float: left;" >
        <img id="pattern" src="${pattern.patternUrl}" style="width: 600px;height: 600px;margin-top: 50px"/>
    </div>
    <div style="float: right;margin-top: 50px; background-color: whitesmoke;width: 350px;height: 600px;">
        <div style="padding-left: 20px">
            <p><span>模型名称</span>：${pattern.patternName}</p>
            <p><span>作者</span>：${pattern.patternUploader}</p>
            <p><span>描述</span>：${pattern.patternDesc}</p>
            <p><span>创建时间</span>：<fmt:formatDate value="${pattern.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
            <!--模型下载功能 todo-->
            <p><a href="${pattern.patternUrl}" class="btn btn-success glyphicon glyphicon-save" download="${pattern.patternName}">模型下载</a> <i class="icon iconfont icon-gouwuche"></i></p>
        </div>
    </div>
    <!-- 关于 -->
    <div class="modal fade" id="about-modal" tabindex="-1" role="dialog" aria-labelledby="modal-label"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
                    <h4 class="modal-title" id="modal-label">关于</h4>
                </div>
                <div class="modal-body">
                    <p><span style="color: red">上海形拓科技有限公司</span>是一家坐落于徐汇区的高新技术企业，公司拥有一支高水平的研发团队，专门致力于<span style="color: red">VR(虚拟现实)、AR(增强现实)、沉浸交互</span>等技术的研发。产品主要涉及仿真应急演练、工业制造仿真以及定制化项目的开发，并已成功服务于石油化工、能源核电、高端制造、高校教育等各个行业。</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">了解了</button>
                </div>
            </div>
        </div>
    </div>


</div>

<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $('#ad-carousel').carousel();
        $('#menu-nav .navbar-collapse a').click(function (e) {
            var href = $(this).attr('href');
            var tabId = $(this).attr('data-tab');
            if ('#' !== href) {
                e.preventDefault();
                $(document).scrollTop($(href).offset().top - 70);
                if (tabId) {
                    $('#feature-tab a[href=#' + tabId + ']').tab('show');
                }
            }
        });
    });
</script>
</body>
</html>

