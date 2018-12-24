//# sourceURL=/sale/baseCustomer/index.js
var saleBaseCustomerIndex = {
	tableUrl : '/sale/baseCustomer/page',
	editUrl : '/sale/baseCustomer/edit',
	saveUrl : '/sale/baseCustomer/save',
	deleteUrl : '/sale/baseCustomer/delete',
	tableElem : 'sale-baseCustomer-index-table-1',
	addBtnElem : 'sale-baseCustomer-index-btn-1',
	editBtnElem : 'sale-baseCustomer-index-btn-2',
	deleteBtnElem : 'sale-baseCustomer-index-btn-3',
	selectBtnElem : 'sale-baseCustomer-index-btn-4',
	selectKeyElem : 'sale-baseCustomer-index-key-1',
	saveBtnElem : 'sale-baseCustomer-edit-btn-1',
	dataId : 'customerId',
	editWindowArea : [ '329px', '163px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'customerName',
		title : '客户名称',
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
			url : saleBaseCustomerIndex.tableUrl,
			elem : '#' + saleBaseCustomerIndex.tableElem,
			cols : saleBaseCustomerIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(saleBaseCustomerIndex.tableElem, {
			initSort : saleBaseCustomerIndex.tableSort,
			where : {
				field : saleBaseCustomerIndex.tableSort.field,
				order : saleBaseCustomerIndex.tableSort.type,
				keyword : $('#' + saleBaseCustomerIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + saleBaseCustomerIndex.tableElem + ')', function(tableSort) {
			saleBaseCustomerIndex.tableSort = tableSort;
			saleBaseCustomerIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + saleBaseCustomerIndex.addBtnElem).on('click', function() {
			saleBaseCustomerIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + saleBaseCustomerIndex.editBtnElem).on('click', function() {
			saleBaseCustomerIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = saleBaseCustomerIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(saleBaseCustomerIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + saleBaseCustomerIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		saleBaseCustomerIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		saleBaseCustomerIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : saleBaseCustomerIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + saleBaseCustomerIndex.saveBtnElem + ')', function(data) {
			var url = saleBaseCustomerIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					saleBaseCustomerIndex.tableReload();
					layer.close(saleBaseCustomerIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + saleBaseCustomerIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(saleBaseCustomerIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				saleBaseCustomerIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					saleBaseCustomerIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : saleBaseCustomerIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					saleBaseCustomerIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(saleBaseCustomerIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + saleBaseCustomerIndex.selectBtnElem).on('click', function() {
			saleBaseCustomerIndex.tableReload();
		});
	}
};
saleBaseCustomerIndex.tableInit();
saleBaseCustomerIndex.tableSortOn();
saleBaseCustomerIndex.addBtnOn();
saleBaseCustomerIndex.editBtnOn();
saleBaseCustomerIndex.saveBtnOn();
saleBaseCustomerIndex.deleteBtnOn();
saleBaseCustomerIndex.selectBtnOn();