<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<h1 class="page-header">Login</h1>
<div class="row">
<s:if test="message!=null">
	<!-- 			<div class="panel panel-default"> -->
	<!-- 			  <div class="panel-body"> -->
	<!-- 			  <h1>Message</h1> -->
	<!-- 			  <hr> -->
	<div class="alert ${message.messageTypeCss}">
		<strong>${message.messageCode} : </strong> ${message.displayText}
	</div>
	<!-- 			   <div class="row"> -->
	<!-- 				<ul class="pager"> -->
	<%-- 				<s:iterator value="buttonList"> --%>
	<%-- 							<li><s:a action="%{action}" namespace="%{namespace}" cssClass="btn-orange"><s:param name="%{paramName}" value="%{paramValue}"></s:param> ${name}</s:a></li> --%>
	<%-- 				  </s:iterator> --%>
	<!-- 				</ul> -->
	<!-- 			   </div> -->
	<!-- 			  </div> -->
	<!-- 			</div> -->
</s:if>
<s:form action="login-login" namespace="/main">
	<s:textfield name="user" />
	<s:password name="password" />
	<s:submit />
</s:form>
</div>
