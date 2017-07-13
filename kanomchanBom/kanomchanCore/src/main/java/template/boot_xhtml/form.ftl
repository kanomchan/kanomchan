<#include "/${parameters.templateDir}/${parameters.expandTheme}/form-validate.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/form-common.ftl" />
<#if (parameters.validate?default(false))>
  onreset="${parameters.onreset?default('clearErrorMessages(this);clearErrorLabels(this);')}"
<#else>
  <#if parameters.onreset??>
  onreset="${parameters.onreset?html}"
  </#if>
</#if>
>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/control.ftl" />
