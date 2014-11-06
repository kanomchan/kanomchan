<%@page import="org.kanomchan.core.common.service.MessageService"%>
<%@page
	import="org.kanomchan.core.common.context.ApplicationContextUtil"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.lang.reflect.Modifier"%>
<%@page import="org.kanomchan.core.common.constant.CommonConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
// out.print(session.getAttribute(CommonConstant.SESSION.LANG_KEY));

MessageService messageService = (MessageService)ApplicationContextUtil.getBean("messageService");
// request.setAttribute("messageScript", messageService.getMessageList("EN"));
Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();
// out.print(gson.toJson(messageService.getMessageMap("EN")));

request.setAttribute("messageScript", gson.toJson(messageService.getMessageMap((String)session.getAttribute(CommonConstant.SESSION.LANG_KEY))));
%>
<script type="text/javascript">
message = <s:property value="%{#request.messageScript}" escapeHtml="false"  escapeJavaScript="false"/>;
</script>
