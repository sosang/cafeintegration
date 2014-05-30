<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/boilerplate.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/index_style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/style2.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/il.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/java_script/postcode.js"></script>
<!-- jQuery  -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Bootstrap CDN js -->
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>

<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Bootstrap CDN css -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">

<!-- custom css -->
<link href="../css/style.css" rel="stylesheet">

<!-- <link rel="stylesheet" href="/css/bootstrap.min.css"> -->
<% 
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0);
%>