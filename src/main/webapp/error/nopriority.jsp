<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <!-- custom-theme -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //custom-theme -->
    <link href="<%=request.getContextPath()%>/css/style1.css" rel="stylesheet" type="text/css" media="all" />
    <!-- js -->
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <!-- //js -->
    <!-- font-awesome icons -->
    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Alfa+Slab+One" rel="stylesheet">
</head>
<body>
<div class="main">
    <canvas id="myCanvas"></canvas>
    <div class="agileinfo_404_main">
        <h1>需要登录系统后访问！</h1>
        <div class="w3_agile_main">
            <h2>Oooops.... Could not find it</h2>
            <p>For some reason the page you requested could not be found on Our server.</p>
            <div class="agile_404 w3layouts">
                <div class="agile_404_pos">
                    <h3>4<span>0</span>4<img src="<%=request.getContextPath()%>/images/1.png" alt=" " /> </h3>
                </div>
                <img src="<%=request.getContextPath()%>/images/3.png" alt=" " class="w3l"/>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/particles.min.js"></script>
<script>
    window.onload = function() {
        Particles.init({
            selector: '#myCanvas',
            color: '#6b6b6b',
            connectParticles: true,
            minDistance: 100
        });
    };
</script>
</body>
</html>