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
  <body >
  	<!-- BEGIN STYLE CUSTOMIZER -->
<!--     <div class="color-panel hidden-sm"> -->
<!--       <div class="color-mode-icons icon-color"></div> -->
<!--       <div class="color-mode-icons icon-color-close"></div> -->
<!--       <div class="color-mode"> -->
<!--         <p>THEME COLOR</p> -->
<!--         <ul class="inline"> -->
<!--           <li class="color-red current color-default" data-style="red"></li> -->
<!--           <li class="color-blue" data-style="blue"></li> -->
<!--           <li class="color-green" data-style="green"></li> -->
<!--           <li class="color-orange" data-style="orange"></li> -->
<!--           <li class="color-gray" data-style="gray"></li> -->
<!--           <li class="color-turquoise" data-style="turquoise"></li> -->
<!--         </ul> -->
<!--       </div> -->
<!--     </div> -->
    <!-- END BEGIN STYLE CUSTOMIZER --> 
    <!-- BEGIN TOP BAR -->
<!--     <div class="pre-header"> -->
<!--         <div class="container"> -->
<%-- 			<tiles:insertAttribute name="preHeader"  ignore="true" /> --%>
<!-- 		</div> -->
<!-- 	</div> -->
	<!-- BEGIN HEADER -->
<!--     <div class="header"> -->
<!--       	<div class="container"> -->
<%-- 			<tiles:insertAttribute name="header"  ignore="true" /> --%>
			<!-- BEGIN MENU -->
<!--         	<div class="header-navigation pull-right font-transform-inherit"> -->
<!--         		<ul> -->
<%-- 					<tiles:insertAttribute name="menu"  ignore="true" /> --%>
<!-- 				</ul> -->
<!-- 			</div> -->
			<!-- END MENU -->
<!-- 		</div> -->
<!--     </div> -->
    <!-- Header END -->
    <!-- BEGIN SLIDER -->
<!--     <div class="page-slider"> -->
<%--     	<tiles:insertAttribute name="slider"  ignore="true" /> --%>
<!--     </div> -->
    <!-- Header END -->
    <!-- BEGIN MAIN -->
    <div class="main">
      	<div class="container">
      		<!-- BEGIN BREADCRUMB -->
        	<tiles:insertAttribute name="navigation"  ignore="true" />
        	<!-- END BREADCRUMB -->
			<tiles:insertAttribute name="body"  ignore="true" />
		</div>
	</div>
	<!-- MAIN END -->
	<!-- BEGIN PRE-FOOTER -->
<!--     <div class="pre-footer"> -->
<!--       	<div class="container"> -->
<%-- 			<tiles:insertAttribute name="preFooter"  ignore="true" /> --%>
<!-- 		</div> -->
<!--     </div> -->
    <!-- END PRE-FOOTER -->
    <!-- BEGIN FOOTER -->
<!--     <div class="footer"> -->
<!--       	<div class="container"> -->
<%-- 			<tiles:insertAttribute name="footer"  ignore="true" /> --%>
<!-- 		</div> -->
<!-- 	</div> -->
    <!-- END FOOTER -->

  </body>
</html>
