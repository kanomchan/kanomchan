

<#include "/${parameters.templateDir}/${parameters.expandTheme}/formajax-validate.ftl" />
<#if parameters.editview?default(false) && parameters.action?? >

<div class="box-align-center editable-edit"> 
<p>
<input type="button" Class="btn btn-save pull-right btnEdit"  value="Edit" id="btn_edit_${parameters.id?html}" style="display:block; " onclick="open_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();" />
<input type="button" Class="btn btn-save pull-right btnEdit"  value="Close" id="btn_close_${parameters.id?html}" style="display:none; " onclick="close_${parameters.id?replace('[^a-zA-Z0-9_]', '_', 'r')}();" />
</p>
</div>
</#if>
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