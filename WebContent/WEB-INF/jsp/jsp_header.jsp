<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/il.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/faq.css">
 --%>
<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Bootstrap CDN css -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Font Awesome -->
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet"> 

<!-- custom css -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">

<!-- <link rel="stylesheet" href="/css/bootstrap.min.css"> -->
<% 
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0);
%>