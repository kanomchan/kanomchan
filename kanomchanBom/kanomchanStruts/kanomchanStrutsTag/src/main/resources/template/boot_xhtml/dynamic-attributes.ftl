<#if (parameters.dynamicAttributes?? && parameters.dynamicAttributes?size > 0)><#rt/>
<#assign aKeys = parameters.dynamicAttributes.keySet()><#rt/>
<#list aKeys as aKey><#rt/>
  <#assign keyValue = parameters.dynamicAttributes[aKey]/>
  <#if keyValue?is_string>
      <#assign value = struts.translateVariables(keyValue)!keyValue/>
  <#else>
      <#assign value = keyValue?string/>
  </#if>
 ${aKey}="<@s.text name="${value}"/>"<#rt/>
</#list><#rt/>
</#if><#rt/>