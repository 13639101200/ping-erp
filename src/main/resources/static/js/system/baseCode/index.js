//# sourceURL=/system/baseCode/index.js
var systemBaseCodeIndex = {
	tableUrl : '/system/baseCode/page',
	editUrl : '/system/baseCode/edit',
	saveUrl : '/system/baseCode/save',
	deleteUrl : '/system/baseCode/delete',
	tableElem : 'system-baseCode-index-table-1',
	addBtnElem : 'system-baseCode-index-btn-1',
	editBtnElem : 'system-baseCode-index-btn-2',
	deleteBtnElem : 'system-baseCode-index-btn-3',
	selectBtnElem : 'system-baseCode-index-btn-4',
	selectKeyElem : 'system-baseCode-index-key-1',
	saveBtnElem : 'system-baseCode-edit-btn-1',
	dataId : 'codeId',
	editWindowArea : [ '329px', '269px' ], // 三行为:280;每行加:53;下拉加86;额外加:10;
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'typeCode',
		title : '类型代码',
		align : 'center',
		sort : true,
	}, {
		field : 'codeType',
		title : '编码类型',
		align : 'center',
	}, {
		field : 'codeValue',
		title : '编码码值',
		align : 'center',
	}, ] ],
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			autoSort : false,
			url : systemBaseCodeIndex.tableUrl,
			elem : '#' + systemBaseCodeIndex.tableElem,
			cols : systemBaseCodeIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseCodeIndex.tableElem, {
			initSort : systemBaseCodeIndex.tableSort,
			where : {
				field : systemBaseCodeIndex.tableSort.field,
				order : systemBaseCodeIndex.tableSort.type,
				keyword : $('#' + systemBaseCodeIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseCodeIndex.tableElem + ')', function(tableSort) {
			systemBaseCodeIndex.tableSort = tableSort;
			systemBaseCodeIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseCodeIndex.addBtnElem).on('click', function() {
			systemBaseCodeIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseCodeIndex.editBtnElem).on('click', function() {
			systemBaseCodeIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseCodeIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(systemBaseCodeIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseCodeIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseCodeIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseCodeIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseCodeIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseCodeIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseCodeIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseCodeIndex.tableReload();
					layer.close(systemBaseCodeIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseCodeIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseCodeIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseCodeIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseCodeIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseCodeIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseCodeIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseCodeIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + systemBaseCodeIndex.selectBtnElem).on('click', function() {
			systemBaseCodeIndex.tableReload();
		});
	}
};
systemBaseCodeIndex.tableInit();
systemBaseCodeIndex.tableSortOn();
systemBaseCodeIndex.addBtnOn();
systemBaseCodeIndex.editBtnOn();
systemBaseCodeIndex.saveBtnOn();
systemBaseCodeIndex.deleteBtnOn();
systemBaseCodeIndex.selectBtnOn();