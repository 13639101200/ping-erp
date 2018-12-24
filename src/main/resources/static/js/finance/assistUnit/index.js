//# sourceURL=/finance/assistUnit/index.js
var financeAssistUnitIndex = {
	tableUrl : '/finance/assistUnit/page',
	editUrl : '/finance/assistUnit/edit',
	saveUrl : '/finance/assistUnit/save',
	deleteUrl : '/finance/assistUnit/delete',
	tableElem : 'finance-assistUnit-index-table-1',
	addBtnElem : 'finance-assistUnit-index-btn-1',
	editBtnElem : 'finance-assistUnit-index-btn-2',
	deleteBtnElem : 'finance-assistUnit-index-btn-3',
	selectBtnElem : 'finance-assistUnit-index-btn-4',
	selectKeyElem : 'finance-assistUnit-index-key-1',
	saveBtnElem : 'finance-assistUnit-edit-btn-1',
	dataId : 'unitId',
	editWindowArea : [ '329px', '163px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'unitName',
		title : '辅计单位名称',
		align : 'center',
		sort : true,
	} ] ],
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			autoSort : false,
			url : financeAssistUnitIndex.tableUrl,
			elem : '#' + financeAssistUnitIndex.tableElem,
			cols : financeAssistUnitIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(financeAssistUnitIndex.tableElem, {
			initSort : financeAssistUnitIndex.tableSort,
			where : {
				field : financeAssistUnitIndex.tableSort.field,
				order : financeAssistUnitIndex.tableSort.type,
				keyword : $('#' + financeAssistUnitIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeAssistUnitIndex.tableElem + ')', function(tableSort) {
			financeAssistUnitIndex.tableSort = tableSort;
			financeAssistUnitIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeAssistUnitIndex.addBtnElem).on('click', function() {
			financeAssistUnitIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeAssistUnitIndex.editBtnElem).on('click', function() {
			financeAssistUnitIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeAssistUnitIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(financeAssistUnitIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeAssistUnitIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		financeAssistUnitIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeAssistUnitIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeAssistUnitIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + financeAssistUnitIndex.saveBtnElem + ')', function(data) {
			var url = financeAssistUnitIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					financeAssistUnitIndex.tableReload();
					layer.close(financeAssistUnitIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeAssistUnitIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeAssistUnitIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeAssistUnitIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeAssistUnitIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeAssistUnitIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeAssistUnitIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeAssistUnitIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + financeAssistUnitIndex.selectBtnElem).on('click', function() {
			financeAssistUnitIndex.tableReload();
		});
	}
};
financeAssistUnitIndex.tableInit();
financeAssistUnitIndex.tableSortOn();
financeAssistUnitIndex.addBtnOn();
financeAssistUnitIndex.editBtnOn();
financeAssistUnitIndex.saveBtnOn();
financeAssistUnitIndex.deleteBtnOn();
financeAssistUnitIndex.selectBtnOn();