<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=devic-width, initial-scale=1, maximum-scale=1" />
</head>
<body>

	<div class="ping-erp-index-content-div">
		<div class="layui-btn-group">
			<button id="${parent_module}-${tabInfo.lowerTabName}-index-btn-1" class="layui-btn">增加</button>
			<button id="${parent_module}-${tabInfo.lowerTabName}-index-btn-2" class="layui-btn">编辑</button>
			<button id="${parent_module}-${tabInfo.lowerTabName}-index-btn-3" class="layui-btn">删除</button>
		</div>
	
		<div class="layui-inline">
			<input id="${parent_module}-${tabInfo.lowerTabName}-index-key-1" type="text" class="layui-input" placeholder="请输入关键字" autocomplete="off" />
		</div>
		<button id="${parent_module}-${tabInfo.lowerTabName}-index-btn-4" class="layui-btn">搜索</button>
		
		<table id="${parent_module}-${tabInfo.lowerTabName}-index-table-1" lay-filter="${parent_module}-${tabInfo.lowerTabName}-index-table-1"></table>
	</div>

	<script th:src="@{/js/${parent_module}/${tabInfo.lowerTabName}/index.js}"></script>

</body>
</html>