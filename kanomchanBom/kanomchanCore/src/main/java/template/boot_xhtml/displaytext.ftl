<#-- 
<div<#rt/>

<#if #editView == "false">
 style="display:none;"<#rt/>
</#if>
<#if parameters.id??> id="${parameters.id?html}"<#rt/></#if>
<#if parameters.name??> name="${parameters.name?html}"<#rt/></#if>
<#if parameters.cssClass??> class="${parameters.cssClass?html}"<#rt/></#if>
<#if parameters.cssStyle??> style="${parameters.cssStyle?html}"<#rt/></#if>
<#if parameters.title??> title="${parameters.title?html}"<#rt/></#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/scripting-events.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/common-attributes.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/dynamic-attributes.ftl" />
>
-->
<displaytext<#rt/>
<#if parameters.id??> id="${parameters.id?html}"<#rt/></#if>
<#if parameters.value??> value="${parameters.value?html}"<#rt/></#if>
><@s.property value="parameters.nameValue"/></displaytext>