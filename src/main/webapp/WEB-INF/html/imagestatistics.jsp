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
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-paginator.js"></script>
    <script src="<%=request.getContextPath()%>/js/echarts.min.js"></script>
    <script src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page, current){
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

            var columnar = echarts.init(document.getElementById('columnar'));
            $.ajax({
                url:"/resourcesmanagement/echarts/show",
                type:"GET",
                success:function(e){
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: e.title
                        },
                        tooltip: {},
                        legend: {
                            data: e.legendData
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            data: e.xData,
                            // axisLine:{
                            //     show:true,
                            //     lineStyle: {
                            //         color: "#00c7ff",
                            //         width: 0.2,
                            //         type: "solid"
                            //     }
                            // }
                        },
                        yAxis: {},
                        series: [{
                            name: '销量',
                            type: 'bar',
                            data: e.seriesData
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    columnar.setOption(option);
                },
                error:function (e) {
                    layer.msg(e);
                }
            });
            //     // 基于准备好的dom，初始化echarts实例
            //     // 如何将后台数据放入前台页面显示
            //     var myChart = echarts.init(document.getElementById('main'));
            //     // 指定图表的配置项和数据 构造如下一个json对向即可实现
            //     var option = {
            //     title: {
            //     text: '素材下载量统计'
            // },
            //     tooltip: {},
            //     legend: {
            //     data:['下载量']
            // },
            //     xAxis: {
            //     data: ["美女1号","美女2号","VR","高清","美女三号","美女四号"]
            // },
            //     yAxis: {},
            //     series: [{
            //     name: '下载量',
            //     type: 'bar',
            //     data: [5, 20, 36, 10, 10, 20]
            // }]
            // };
            //     // 使用刚指定的配置项和数据显示图表。
            //     myChart.setOption(option);

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
                            console.log("添加成功！");
                            window.location.href="/resourcesmanagement/user/findAll?pageNum="+${pageInfo.pageNum};
                        }else{
                            alert("添加用户数据失败！");
                        }
                    }
                });
            });
        })

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
        <h3 class="panel-title">素材下载数据统计</h3>
    </div>
    <div class="panel-body">
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">素材编号</th>
                    <th class="text-center">素材名称</th>
                    <th class="text-center">下载量</th>
                    <th class="text-center">素材上传者</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="count">
                    <tr>
                        <td>${count.id}</td>
                        <td>${count.image.id}</td>
                        <td>${count.image.imageName}</td>
                        <td>${count.imageDownloadNums}</td>
                        <td>${count.image.uploader}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 使用bootstrap-paginator实现分页 -->
            <nav style="text-align: center">
                <ul id="pagination"></ul>
            </nav>
        </div>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM todo 引入图标组件echarts 2019年7月19日16:53:53-->
        <div id="columnar" style="width: 600px;height:400px;"></div>

    </div>

</div>

<!-- 添加系统用户 start -->
<div class="modal fade" tabindex="-1" id="ProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加系统用户</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="loginName" class="col-sm-4 control-label">账号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginName">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="loginPassword" class="col-sm-4 control-label">密码：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginPassword">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">姓名：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="name">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">角色：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="roleId" placeholder="请填入数字（1管理员 2普通用户）">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">状态：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="enableStatus" placeholder="请填入数字（1可用 2禁用）">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary addUser" id="addUser">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
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