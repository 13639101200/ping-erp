//# sourceURL=/finance/voucherWord/index.js
var financeVoucherWordIndex = {
	tableUrl : '/finance/voucherWord/page',
	editUrl : '/finance/voucherWord/edit',
	saveUrl : '/finance/voucherWord/save',
	deleteUrl : '/finance/voucherWord/delete',
	tableElem : 'finance-voucherWord-index-table-1',
	addBtnElem : 'finance-voucherWord-index-btn-1',
	editBtnElem : 'finance-voucherWord-index-btn-2',
	deleteBtnElem : 'finance-voucherWord-index-btn-3',
	selectBtnElem : 'finance-voucherWord-index-btn-4',
	selectKeyElem : 'finance-voucherWord-index-key-1',
	saveBtnElem : 'finance-voucherWord-edit-btn-1',
	dataId : 'wordId',
	editWindowArea : [ '329px', '163px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'wordName',
		title : '凭证字名称',
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
			url : financeVoucherWordIndex.tableUrl,
			elem : '#' + financeVoucherWordIndex.tableElem,
			cols : financeVoucherWordIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(financeVoucherWordIndex.tableElem, {
			initSort : financeVoucherWordIndex.tableSort,
			where : {
				field : financeVoucherWordIndex.tableSort.field,
				order : financeVoucherWordIndex.tableSort.type,
				keyword : $('#' + financeVoucherWordIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeVoucherWordIndex.tableElem + ')', function(tableSort) {
			financeVoucherWordIndex.tableSort = tableSort;
			financeVoucherWordIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeVoucherWordIndex.addBtnElem).on('click', function() {
			financeVoucherWordIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeVoucherWordIndex.editBtnElem).on('click', function() {
			financeVoucherWordIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeVoucherWordIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(financeVoucherWordIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeVoucherWordIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		financeVoucherWordIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeVoucherWordIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeVoucherWordIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + financeVoucherWordIndex.saveBtnElem + ')', function(data) {
			var url = financeVoucherWordIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					financeVoucherWordIndex.tableReload();
					layer.close(financeVoucherWordIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeVoucherWordIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeVoucherWordIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeVoucherWordIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeVoucherWordIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeVoucherWordIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeVoucherWordIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeVoucherWordIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + financeVoucherWordIndex.selectBtnElem).on('click', function() {
			financeVoucherWordIndex.tableReload();
		});
	}
};
financeVoucherWordIndex.tableInit();
financeVoucherWordIndex.tableSortOn();
financeVoucherWordIndex.addBtnOn();
financeVoucherWordIndex.editBtnOn();
financeVoucherWordIndex.saveBtnOn();
financeVoucherWordIndex.deleteBtnOn();
financeVoucherWordIndex.selectBtnOn();