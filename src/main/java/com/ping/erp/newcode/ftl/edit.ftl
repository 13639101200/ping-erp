<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>

	<div class="layui-fluid ping-erp-edit-content-div">
		<form class="layui-form layui-form-pane" action="">
			<input name="${tabInfo.lowerTabKey}" type="hidden" th:value="${'${'}${tabInfo.lowerTabName}?.${tabInfo.lowerTabKey}}" />
			
			<#list tabInfo.columns as column>
			<#if tabInfo.tabKey != column.colName>
			<div class="layui-form-item">
				<label class="layui-form-label">${column.colComent}</label>
				<div class="layui-input-inline ping-erp-edit-input-div-lastinput">
					<input name="${column.lowerColName}" type="text" class="layui-input" placeholder="${column.colComent}" th:value="${'${'}${tabInfo.lowerTabName}?.${column.lowerColName}}" autocomplete="off" lay-verify="required" />
				</div>
			</div>
			</#if>
			</#list>
			
			<div class="layui-form-item ping-erp-edit-button-div">
				<button type="button" class="layui-btn" id="${parent_module}-${tabInfo.lowerTabName}-edit-btn-1" lay-filter="${parent_module}-${tabInfo.lowerTabName}-edit-btn-1" lay-submit="">保存</button>
			</div>
		</form>
	</div>

</body>
</html>