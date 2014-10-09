<#assign itemCount = 0/>
<#if parameters.list??>
<label class="checkbox-mobile" for="all${parameters.id?html}">
	<input type="checkbox" name="all${parameters.id?html}" value="0" id="all${parameters.id?html}" class="checkbox-inline"/><label for="all${parameters.id?html}" class="checkboxLabel"> All</label>
</label><br class="hidden-xs">
<@s.iterator value="parameters.list">
    <#assign itemCount = itemCount + 1/>
    <#if parameters.listKey??>
        <#assign itemKey = stack.findValue(parameters.listKey)/>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
    </#if>
    <#if parameters.listValue??>
        <#assign itemValue = stack.findString(parameters.listValue)?default("")/>
        <#else>
            <#assign itemValue = stack.findString('top')/>
    </#if>
    <#if parameters.listCssClass??>
        <#if stack.findString(parameters.listCssClass)??>
          <#assign itemCssClass= stack.findString(parameters.listCssClass)/>
        <#else>
          <#assign itemCssClass = ''/>
        </#if>
    </#if>
    <#if parameters.listCssStyle??>
        <#if stack.findString(parameters.listCssStyle)??>
          <#assign itemCssStyle= stack.findString(parameters.listCssStyle)/>
        <#else>
          <#assign itemCssStyle = ''/>
        </#if>
    </#if>
    <#if parameters.listTitle??>
        <#if stack.findString(parameters.listTitle)??>
          <#assign itemTitle= stack.findString(parameters.listTitle)/>
        <#else>
          <#assign itemTitle = ''/>
        </#if>
    </#if>
    <#assign itemKeyStr=itemKey.toString() />
<label class="checkbox-mobile" for="${parameters.id?html}-${itemCount}">
<input type="hidden" name="${parameters.beanName?html}[${itemCount-1}]<#if parameters.nameKey??>${parameters.nameKey}</#if>" value="${itemKeyStr?html}" id="hidden-${parameters.id?html}-${itemCount}"<#rt/>>
<input type="checkbox" 
		name="${parameters.beanName?html}[${itemCount-1}]<#if parameters.checkName??>${parameters.checkName}</#if>" 
		id="${parameters.id?html}-${itemCount}"<#rt/>
    <#if tag.contains(parameters.nameValue, itemKey)>
       checked="checked"<#rt/>
    </#if>
    <#if parameters.disabled?default(false)>
       disabled="disabled"<#rt/>
    </#if>
    <#if itemCssClass?if_exists != "">
     class="${itemCssClass?html}"<#rt/>
    <#else>
        <#if parameters.cssClass??>
     class="${parameters.cssClass?html} checkbox-${parameters.id?html}"<#rt/>
        </#if>
    </#if>
    <#if itemCssStyle?if_exists != "">
     style="${itemCssStyle?html}"<#rt/>
    <#else>
        <#if parameters.cssStyle??>
     style="${parameters.cssStyle?html}"<#rt/>
        </#if>
    </#if>
    <#if itemTitle?if_exists != "">
     title="${itemTitle?html}"<#rt/>
    <#else>
        <#if parameters.title??>
     title="${parameters.title?html}"<#rt/>
        </#if>
    </#if>
    <#include "/${parameters.templateDir}/${parameters.expandTheme}/css.ftl" />
    <#include "/${parameters.templateDir}/${parameters.expandTheme}/scripting-events.ftl" />
    <#include "/${parameters.templateDir}/${parameters.expandTheme}/common-attributes.ftl" />
        />
<label for="${parameters.id?html}-${itemCount}" class="checkboxLabel">${itemValue?html}</label></label><br class="hidden-xs">
</@s.iterator>
<script type="text/javascript">
	$('#all${parameters.id?html}').change(function() {
		if(this.checked) {
			$('[id^=${parameters.id?html}]').prop( "checked", true );
		}else{
			$('[id^=${parameters.id?html}]').prop( "checked", false );
		}

	});
	$('[id^=${parameters.id?html}]').click(function() {
		if(!this.checked) {
			$('#all${parameters.id?html}').prop( "checked", false );
		}
		if ($('.checkbox-${parameters.id?html}:checked').length == $('.checkbox-${parameters.id?html}').length) {
			$('#all${parameters.id?html}').prop( "checked", true );
		}
	});
	
</script>
    <#else>
    &nbsp;
</#if>
<input type="hidden" id="__multiselect_${parameters.id?html}" name="__multiselect_${parameters.name?html}"
       value=""<#rt/>
<#if parameters.disabled?default(false)>
       disabled="disabled"<#rt/>
</#if>
        />
