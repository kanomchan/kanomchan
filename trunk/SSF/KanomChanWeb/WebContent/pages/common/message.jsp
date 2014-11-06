<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="message!=null">
<div class="panel panel-default">
  <div class="panel-body">
  <h1>Message</h1>
  <hr>
   <div class="alert ${message.messageTypeCss}"><strong>${message.messageCode} : </strong> ${message.displayText}</div>
   <div class="row">
	<ul class="pager">
	<s:iterator value="buttonList">
				<li><s:a action="%{action}" namespace="%{namespace}" cssClass="btn-orange"><s:param name="%{paramName}" value="%{paramValue}"></s:param> ${name}</s:a></li>
	  </s:iterator>
	</ul>
   </div>
  </div>
</div>
	
</s:if>