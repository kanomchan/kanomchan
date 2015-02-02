
<#assign hasFieldErrors = parameters.name?? && fieldErrors?? && fieldErrors[parameters.name]??/>
	<#if hasFieldErrors>
	<#list fieldErrors[parameters.name] as error>
		<#if parameters.label??>
			<div class="rowError" <#if parameters.name??> name="${parameters.name?html}Error"<#rt/></#if>
			<#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >
				<div class="col-md-offset-4 col-md-4">
					<div class="message warning" role="alert">${error?html}</div>
				</div>
			</div>
			<#else>
				<div class="message warning" role="alert" <#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >${error?html}</div>
		</#if>
	</#list>
	<#else>
		<#if parameters.label??>
			<div class="rowError" <#if parameters.name??> name="${parameters.name?html}Error"<#rt/></#if>
			<#if parameters.id??>
		   		for="${parameters.id}"
			</#if> >
				<div style="display:none" class="col-md-offset-4 col-md-4">
					<div class="message warning" role="alert"></div>
				</div>
			</div>
			<#else>
				<div style="display:none" class="message warning" role="alert" <#if parameters.id??>
		   		for="${parameters.id}"
			</#if> ></div>
		</#if>
	</#if>
