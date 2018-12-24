//# sourceURL=/finance/voucherDetail/index.js
var financeVoucherDetailIndex = {
	tableUrl : '/finance/voucherDetail/page',
	editUrl : '/finance/voucherDetail/edit',
	saveUrl : '/finance/voucherDetail/save',
	deleteUrl : '/finance/voucherDetail/delete',
	tableElem : 'finance-voucherDetail-index-table-1',
	addBtnElem : 'finance-voucherDetail-index-btn-1',
	editBtnElem : 'finance-voucherDetail-index-btn-2',
	deleteBtnElem : 'finance-voucherDetail-index-btn-3',
	selectBtnElem : 'finance-voucherDetail-index-btn-4',
	selectKeyElem : 'finance-voucherDetail-index-key-1',
	saveBtnElem : 'finance-voucherDetail-edit-btn-1',
	dataId : 'detailId',
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
		{
			field : 'voucherId',
			title : '凭证ID',
			align : 'center',
			sort : true,
		},
		{
			field : 'subjectId',
			title : '科目ID',
			align : 'center',
			sort : true,
		},
		{
			field : 'detailAbstract',
			title : '摘要',
			align : 'center',
			sort : true,
		},
		{
			field : 'borrowerMoney',
			title : '借方金额',
			align : 'center',
			sort : true,
		},
		{
			field : 'lenderMoney',
			title : '贷方金额',
			align : 'center',
			sort : true,
		},
		{
			field : 'assistAccount',
			title : '辅助核算',
			align : 'center',
			sort : true,
		},
		{
			field : 'assistObject',
			title : '辅助对象',
			align : 'center',
			sort : true,
		},
		{
			field : 'createTime',
			title : '创建时间',
			align : 'center',
			sort : true,
			templet : function(value) {
				return common.formatDate(value, 'createTime');
			}
		},
	]],
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			autoSort : false,
			url : financeVoucherDetailIndex.tableUrl,
			elem : '#' + financeVoucherDetailIndex.tableElem,
			cols : financeVoucherDetailIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(financeVoucherDetailIndex.tableElem, {
			initSort : financeVoucherDetailIndex.tableSort,
			where : {
				field : financeVoucherDetailIndex.tableSort.field,
				order : financeVoucherDetailIndex.tableSort.type,
				keyword : $('#' + financeVoucherDetailIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeVoucherDetailIndex.tableElem + ')', function(tableSort) {
			financeVoucherDetailIndex.tableSort = tableSort;
			financeVoucherDetailIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeVoucherDetailIndex.addBtnElem).on('click', function() {
			financeVoucherDetailIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeVoucherDetailIndex.editBtnElem).on('click', function() {
			financeVoucherDetailIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeVoucherDetailIndex.editUrl;
		var data = null;
		var content = null;
		
		if (type == 2) {
			var selectData = table.checkStatus(financeVoucherDetailIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeVoucherDetailIndex.dataId + '})');
			}
		}
		
		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;
		
		financeVoucherDetailIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeVoucherDetailIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeVoucherDetailIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + financeVoucherDetailIndex.saveBtnElem + ')', function(data) {
			var url = financeVoucherDetailIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					financeVoucherDetailIndex.tableReload();
					layer.close(financeVoucherDetailIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeVoucherDetailIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeVoucherDetailIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeVoucherDetailIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeVoucherDetailIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeVoucherDetailIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeVoucherDetailIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeVoucherDetailIndex.deleteWindowIndex);
	},
	selectBtnOn : function(){
		$('#' + financeVoucherDetailIndex.selectBtnElem).on('click', function() {
			financeVoucherDetailIndex.tableReload();
		});
	}
};
financeVoucherDetailIndex.tableInit();
financeVoucherDetailIndex.tableSortOn();
financeVoucherDetailIndex.addBtnOn();
financeVoucherDetailIndex.editBtnOn();
financeVoucherDetailIndex.saveBtnOn();
financeVoucherDetailIndex.deleteBtnOn();
financeVoucherDetailIndex.selectBtnOn();