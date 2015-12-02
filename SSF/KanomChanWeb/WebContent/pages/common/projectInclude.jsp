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

	request.setAttribute("imageBase", appBase + "/resource/common/img");
	request.setAttribute("jsBase", appBase + "/resource/common/js");
	request.setAttribute("cssBase", appBase + "/resource/common/css");
	request.setAttribute("iconBase", appBase + "/resource/common/icon");
	
	%>
	<jsp:include page="/pages/common/messageCode.jsp"></jsp:include>
    <!-- Bootstrap -->
    <link href="${requestScope.cssBase}/bootstrap.css" rel="stylesheet">
    <link href="${requestScope.cssBase}/bootstrap-theme.css" rel="stylesheet">
<%--     <link href="${requestScope.cssBase}/non-responsive.css" rel="stylesheet"> --%>


	<sj:head compressed="false" jqueryui="true" />
    <script src="${requestScope.jsBase}/bootstrap.js"></script>

	