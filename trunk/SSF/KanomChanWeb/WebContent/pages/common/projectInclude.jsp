<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
    <%!
	String appBase    	= null;
	String imageBase  	= null;
	String jsBase 	= null;
	String cssBase  	= null;
	
	String agiImageBase = null;
	String agiJSBase = null;
	String agiCssBase = null;
%>
<%
	appBase    	= request.getContextPath();

	if(appBase.equals("/")){
		appBase = "";
	}
	
	
	request.setAttribute("appBase", appBase );

	request.setAttribute("imageBase", appBase + "/resource/images");
	request.setAttribute("jsBase", appBase + "/resource/js");
	request.setAttribute("cssBase", appBase + "/resource/css");
	request.setAttribute("iconBase", appBase + "/resource/icon");
	
	%>
	<jsp:include page="/pages/common/messageCode.jsp"></jsp:include>
    <!-- Bootstrap -->
<%--     <link href="${requestScope.cssBase}/bootstrap.css" rel="stylesheet"> --%>
<%--     <link href="${requestScope.cssBase}/themes.css" rel="stylesheet"> --%>
<%--     <link href="${requestScope.cssBase}/jquery.dataTables.css" rel="stylesheet"> --%>
<%--     <link href="${requestScope.cssBase}/ui-lightness/jquery-ui-1.10.4.css" rel="stylesheet"> --%>
<%--     <script src="${requestScope.jsBase}/jquery/jquery-1.11.0.js"></script> --%>
<%--     <script src="${requestScope.jsBase}/bootstrap.js"></script> --%>
<%--     <script src="${requestScope.jsBase}/jquery/jquery.dataTables.js"></script> --%>
<%-- 	<script src="${requestScope.jsBase}/jquery/jquery-ui-1.10.4.js"></script> --%>
	
	
<%-- 	<script src="${requestScope.jsBase}/webtrends.js"></script>
	<script src="${requestScope.jsBase}/slides.min.jquery.js"></script>
	<script src="${requestScope.jsBase}/jquery.touchwipe.min.js"></script>
	<script src="${requestScope.jsBase}/common.js"></script>
	<link href="${requestScope.cssBase}/common.css" rel="stylesheet"> --%>
	
	<sj:head compressed="false" jqueryui="true" />
	
  <link rel="shortcut icon" href="${requestScope.imageBase}/icon/favicon.ico">

  <!-- Fonts START -->
  <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css">
  <!-- Fonts END -->

  <!-- Global styles START -->          
  <link href="${requestScope.cssBase}/font-awesome.min.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/bootstrap.css" rel="stylesheet">
  <!-- Global styles END --> 
   
  <!-- Page level plugin styles START -->
  <link href="${requestScope.cssBase}/jquery.fancybox.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/owl.carousel.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/settings.css" rel="stylesheet">
<%--   <link href="${requestScope.cssBase}/bootstrap-select.css" rel="stylesheet" type="text/css"/> --%>
<%--   <link href="${requestScope.cssBase}/select2.css" rel="stylesheet" type="text/css"/> --%>
<%--   <link href="${requestScope.cssBase}/multi-select.css" rel="stylesheet" type="text/css"/> --%>
  <!-- Page level plugin styles END -->

  <!-- Theme styles START -->
  <link href="${requestScope.cssBase}/components.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/layout.css" rel="stylesheet" type="text/css"/>
  <link href="${requestScope.cssBase}/style.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/style-revolution-slider.css" rel="stylesheet"><!-- metronic revo slider styles -->
  <link href="${requestScope.cssBase}/style-responsive.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/red.css" rel="stylesheet" id="style-color">
  <link href="${requestScope.cssBase}/custom.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/jm-custom.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/fullcalendar.css" rel="stylesheet">
  
  
<%-- <script src="${requestScope.jsBase}/jquery-1.11.0.min.js" type="text/javascript"></script> --%>
<script src="${requestScope.jsBase}/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/bootstrap.min.js" type="text/javascript"></script>      
<script src="${requestScope.jsBase}/back-to-top.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/moment.min.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/fullcalendar.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/calendar.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
<script src="${requestScope.jsBase}/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
<script src="${requestScope.jsBase}/jquery-ui-slider.min.js" type="text/javascript"></script><!-- slider -->
<script src="${requestScope.jsBase}/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->

<!-- BEGIN RevolutionSlider -->

<script src="${requestScope.jsBase}/jquery.themepunch.plugins.min.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/jquery.themepunch.revolution.min.js" type="text/javascript"></script> 
<script src="${requestScope.jsBase}/revo-slider-init.js" type="text/javascript"></script>
<!-- END RevolutionSlider -->

<script src="${requestScope.jsBase}/metronic.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/layout.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/quick-sidebar.js" type="text/javascript"></script>

<!-- BEGIN Select2 -->
<%-- <script src="${requestScope.jsBase}/bootstrap-select.min.js" type="text/javascript"></script> --%>
<%-- <script src="${requestScope.jsBase}/select2.min.js" type="text/javascript"></script> --%>
<!-- BEGIN Select2 -->

<!-- BEGIN Custom -->
<script src="${requestScope.jsBase}/redirect.js" type="text/javascript"></script>
<!-- END Custom -->

<!-- BEGIN Custom Accordion -->
<script type="text/javascript" src="${requestScope.jsBase}/accordion.js"></script>
<!-- BEGIN Custom Accordion -->