//# sourceURL=/${parent_module}/${tabInfo.lowerTabName}/index.js
var ${parent_module}${tabInfo.upperTabName}Index = {
	tableUrl : '/${parent_module}/${tabInfo.lowerTabName}/page',
	editUrl : '/${parent_module}/${tabInfo.lowerTabName}/edit',
	saveUrl : '/${parent_module}/${tabInfo.lowerTabName}/save',
	deleteUrl : '/${parent_module}/${tabInfo.lowerTabName}/delete',
	tableElem : '${parent_module}-${tabInfo.lowerTabName}-index-table-1',
	addBtnElem : '${parent_module}-${tabInfo.lowerTabName}-index-btn-1',
	editBtnElem : '${parent_module}-${tabInfo.lowerTabName}-index-btn-2',
	deleteBtnElem : '${parent_module}-${tabInfo.lowerTabName}-index-btn-3',
	selectBtnElem : '${parent_module}-${tabInfo.lowerTabName}-index-btn-4',
	selectKeyElem : '${parent_module}-${tabInfo.lowerTabName}-index-key-1',
	saveBtnElem : '${parent_module}-${tabInfo.lowerTabName}-edit-btn-1',
	dataId : '${tabInfo.lowerTabKey}',
	editWindowArea : ['329px', '375px'],
	tableCols : [[
		{
			fixed : 'left',
			type : 'checkbox'
		},
		{
			fixed : 'left',
			title : '序号',
			type : 'numbers'
		},
		<#list tabInfo.columns as column>
		<#if tabInfo.tabKey != column.colName>
		{
			field : '${column.lowerColName}',
			title : '${column.colComent}',
			align : 'center',
			sort : true,
			<#if column.colType = 'DATETIME'>
			templet : function(value) {
				return common.formatDate(value, '${column.lowerColName}');
			}
			</#if>
		},
		</#if>
		</#list>
	]],
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			autoSort : false,
			url : ${parent_module}${tabInfo.upperTabName}Index.tableUrl,
			elem : '#' + ${parent_module}${tabInfo.upperTabName}Index.tableElem,
			cols : ${parent_module}${tabInfo.upperTabName}Index.tableCols
		});
	},
	tableReload : function() {
		table.reload(${parent_module}${tabInfo.upperTabName}Index.tableElem, {
			initSort : ${parent_module}${tabInfo.upperTabName}Index.tableSort,
			where : {
				field : ${parent_module}${tabInfo.upperTabName}Index.tableSort.field,
				order : ${parent_module}${tabInfo.upperTabName}Index.tableSort.type,
				keyword : $('#' + ${parent_module}${tabInfo.upperTabName}Index.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + ${parent_module}${tabInfo.upperTabName}Index.tableElem + ')', function(tableSort) {
			${parent_module}${tabInfo.upperTabName}Index.tableSort = tableSort;
			${parent_module}${tabInfo.upperTabName}Index.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + ${parent_module}${tabInfo.upperTabName}Index.addBtnElem).on('click', function() {
			${parent_module}${tabInfo.upperTabName}Index.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + ${parent_module}${tabInfo.upperTabName}Index.editBtnElem).on('click', function() {
			${parent_module}${tabInfo.upperTabName}Index.editData(2);
		});
	},
	editData : function(type) {
		var url = ${parent_module}${tabInfo.upperTabName}Index.editUrl;
		var data = null;
		var content = null;
		
		if (type == 2) {
			var selectData = table.checkStatus(${parent_module}${tabInfo.upperTabName}Index.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + ${parent_module}${tabInfo.upperTabName}Index.dataId + '})');
			}
		}
		
		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;
		
		${parent_module}${tabInfo.upperTabName}Index.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		${parent_module}${tabInfo.upperTabName}Index.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : ${parent_module}${tabInfo.upperTabName}Index.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + ${parent_module}${tabInfo.upperTabName}Index.saveBtnElem + ')', function(data) {
			var url = ${parent_module}${tabInfo.upperTabName}Index.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					${parent_module}${tabInfo.upperTabName}Index.tableReload();
					layer.close(${parent_module}${tabInfo.upperTabName}Index.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + ${parent_module}${tabInfo.upperTabName}Index.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(${parent_module}${tabInfo.upperTabName}Index.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				${parent_module}${tabInfo.upperTabName}Index.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					${parent_module}${tabInfo.upperTabName}Index.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : ${parent_module}${tabInfo.upperTabName}Index.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					${parent_module}${tabInfo.upperTabName}Index.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(${parent_module}${tabInfo.upperTabName}Index.deleteWindowIndex);
	},
	selectBtnOn : function(){
		$('#' + ${parent_module}${tabInfo.upperTabName}Index.selectBtnElem).on('click', function() {
			${parent_module}${tabInfo.upperTabName}Index.tableReload();
		});
	}
};
${parent_module}${tabInfo.upperTabName}Index.tableInit();
${parent_module}${tabInfo.upperTabName}Index.tableSortOn();
${parent_module}${tabInfo.upperTabName}Index.addBtnOn();
${parent_module}${tabInfo.upperTabName}Index.editBtnOn();
${parent_module}${tabInfo.upperTabName}Index.saveBtnOn();
${parent_module}${tabInfo.upperTabName}Index.deleteBtnOn();
${parent_module}${tabInfo.upperTabName}Index.selectBtnOn();