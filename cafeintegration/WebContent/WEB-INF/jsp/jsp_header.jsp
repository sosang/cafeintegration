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
<% 
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0);
%>