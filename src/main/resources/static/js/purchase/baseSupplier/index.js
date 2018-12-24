//# sourceURL=/purchase/baseSupplier/index.js
var purchaseBaseSupplierIndex = {
	tableUrl : '/purchase/baseSupplier/page',
	editUrl : '/purchase/baseSupplier/edit',
	saveUrl : '/purchase/baseSupplier/save',
	deleteUrl : '/purchase/baseSupplier/delete',
	tableElem : 'purchase-baseSupplier-index-table-1',
	addBtnElem : 'purchase-baseSupplier-index-btn-1',
	editBtnElem : 'purchase-baseSupplier-index-btn-2',
	deleteBtnElem : 'purchase-baseSupplier-index-btn-3',
	selectBtnElem : 'purchase-baseSupplier-index-btn-4',
	selectKeyElem : 'purchase-baseSupplier-index-key-1',
	saveBtnElem : 'purchase-baseSupplier-edit-btn-1',
	dataId : 'supplierId',
	editWindowArea : [ '329px', '163px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'supplierName',
		title : '供应商名称',
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
			url : purchaseBaseSupplierIndex.tableUrl,
			elem : '#' + purchaseBaseSupplierIndex.tableElem,
			cols : purchaseBaseSupplierIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(purchaseBaseSupplierIndex.tableElem, {
			initSort : purchaseBaseSupplierIndex.tableSort,
			where : {
				field : purchaseBaseSupplierIndex.tableSort.field,
				order : purchaseBaseSupplierIndex.tableSort.type,
				keyword : $('#' + purchaseBaseSupplierIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + purchaseBaseSupplierIndex.tableElem + ')', function(tableSort) {
			purchaseBaseSupplierIndex.tableSort = tableSort;
			purchaseBaseSupplierIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + purchaseBaseSupplierIndex.addBtnElem).on('click', function() {
			purchaseBaseSupplierIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + purchaseBaseSupplierIndex.editBtnElem).on('click', function() {
			purchaseBaseSupplierIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = purchaseBaseSupplierIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(purchaseBaseSupplierIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + purchaseBaseSupplierIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		purchaseBaseSupplierIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		purchaseBaseSupplierIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : purchaseBaseSupplierIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + purchaseBaseSupplierIndex.saveBtnElem + ')', function(data) {
			var url = purchaseBaseSupplierIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					purchaseBaseSupplierIndex.tableReload();
					layer.close(purchaseBaseSupplierIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + purchaseBaseSupplierIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(purchaseBaseSupplierIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				purchaseBaseSupplierIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					purchaseBaseSupplierIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : purchaseBaseSupplierIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					purchaseBaseSupplierIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(purchaseBaseSupplierIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + purchaseBaseSupplierIndex.selectBtnElem).on('click', function() {
			purchaseBaseSupplierIndex.tableReload();
		});
	}
};
purchaseBaseSupplierIndex.tableInit();
purchaseBaseSupplierIndex.tableSortOn();
purchaseBaseSupplierIndex.addBtnOn();
purchaseBaseSupplierIndex.editBtnOn();
purchaseBaseSupplierIndex.saveBtnOn();
purchaseBaseSupplierIndex.deleteBtnOn();
purchaseBaseSupplierIndex.selectBtnOn();