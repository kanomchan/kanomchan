<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
  <head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<meta content="Website Job" name="description">
	<meta content="Job" name="keywords">
	<meta content="Jobs Matcher" name="author">
	
	<meta property="og:site_name" content="-CUSTOMER VALUE-">
	<meta property="og:title" content="-CUSTOMER VALUE-">
	<meta property="og:description" content="-CUSTOMER VALUE-">
	<meta property="og:type" content="website">
	<meta property="og:image" content="-CUSTOMER VALUE-"><!-- link to image for socio -->
	<meta property="og:url" content="-CUSTOMER VALUE-">
	
    <title><tiles:getAsString name="title"/></title>
	<jsp:include page="/pages/common/projectInclude.jsp"/>
  </head>
  <body>

      	<div class="container">
      		<!-- BEGIN BREADCRUMB -->
<%--         	<tiles:insertAttribute name="navigation"  ignore="true" /> --%>
			<tiles:insertAttribute name="body" />
		</div>


  </body>
</html>
