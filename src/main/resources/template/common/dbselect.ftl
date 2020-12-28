<#macro dbselect  name="" id="" title="" length=10 typeCode="" alt="" defaultValue=""
	 style="" readonly="" onchange="">  
      <@dbSelectDirective typeCode=typeCode /> 
      <#if readonly == "true">
      		 <#list optionsList as item>
      		 		  <#if defaultValue == item.skey>${item.name}
      		 		  	<input type="hidden" id="${id}" name="${name}" length="${length}" style="${style}" value="${item.name}"/>
      		 		  </#if>
      		 </#list>
      <#else>
      		 <select id="${id}" name="${name}" length="${length}" style="${style}" alt="${alt}" data-required
      		  onchange="${onchange}" >
      		    <option value="">请选择</option>
		        <#list optionsList as item>  
		        	<option value="${item.skey}" <#if defaultValue == item.skey>selected</#if>>${item.name}</option>
		        </#list>
			</select>
      </#if>
</#macro>