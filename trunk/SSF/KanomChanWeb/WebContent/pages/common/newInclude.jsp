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
	request.setAttribute("iconBase", appBase + "/resource/ico");
	
	%>
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
	
	<sj:head/>
	
  <link rel="shortcut icon" href="${requestScope.cssImage}/icon/favicon.ico">

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
  <!-- Page level plugin styles END -->

  <!-- Theme styles START -->
  <link href="${requestScope.cssBase}/components.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/plugins.css" rel="stylesheet" type="text/css"/>
  <link href="${requestScope.cssBase}/layout.css" rel="stylesheet" type="text/css"/>
  <link href="${requestScope.cssBase}/style.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/style-revolution-slider.css" rel="stylesheet"><!-- metronic revo slider styles -->
  <link href="${requestScope.cssBase}/style-responsive.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/red.css" rel="stylesheet" id="style-color">
  <link id="style_color" href="${requestScope.cssBase}/default.css" rel="stylesheet" type="text/css"/>
  <link href="${requestScope.cssBase}/custom.css" rel="stylesheet">
  <link href="${requestScope.cssBase}/jm-custom.css" rel="stylesheet">
  
<%-- <script src="${requestScope.jsBase}/jquery-1.11.0.min.js" type="text/javascript"></script> --%>
<script src="${requestScope.jsBase}/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/bootstrap.min.js" type="text/javascript"></script>      
<script src="${requestScope.jsBase}/back-to-top.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
<script src="${requestScope.jsBase}/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
<script src="${requestScope.jsBase}/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->

<!-- BEGIN RevolutionSlider -->

<script src="${requestScope.jsBase}/jquery.themepunch.plugins.min.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/jquery.themepunch.revolution.min.js" type="text/javascript"></script> 
<script src="${requestScope.jsBase}/revo-slider-init.js" type="text/javascript"></script>
<!-- END RevolutionSlider -->

<script src="${requestScope.jsBase}/metronic.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/layout.js" type="text/javascript"></script>
<script src="${requestScope.jsBase}/quick-sidebar.js" type="text/javascript"></script>
