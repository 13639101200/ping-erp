//# sourceURL=/finance/financePeriod/index.js
var financeFinancePeriodIndex = {
	tableUrl : '/finance/financePeriod/page',
	editUrl : '/finance/financePeriod/edit',
	saveUrl : '/finance/financePeriod/save',
	deleteUrl : '/finance/financePeriod/delete',
	tableElem : 'finance-financePeriod-index-table-1',
	addBtnElem : 'finance-financePeriod-index-btn-1',
	editBtnElem : 'finance-financePeriod-index-btn-2',
	deleteBtnElem : 'finance-financePeriod-index-btn-3',
	selectBtnElem : 'finance-financePeriod-index-btn-4',
	selectKeyElem : 'finance-financePeriod-index-key-1',
	saveBtnElem : 'finance-financePeriod-edit-btn-1',
	dataId : 'periodId',
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
			field : 'companyId',
			title : '公司ID',
			align : 'center',
			sort : true,
		},
		{
			field : 'initialTime',
			title : '初始时间',
			align : 'center',
			sort : true,
			templet : function(value) {
				return common.formatDate(value, 'initialTime');
			}
		},
		{
			field : 'archivedTime',
			title : '结账时间',
			align : 'center',
			sort : true,
			templet : function(value) {
				return common.formatDate(value, 'archivedTime');
			}
		},
		{
			field : 'isArchived',
			title : '是否结账',
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
			url : financeFinancePeriodIndex.tableUrl,
			elem : '#' + financeFinancePeriodIndex.tableElem,
			cols : financeFinancePeriodIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(financeFinancePeriodIndex.tableElem, {
			initSort : financeFinancePeriodIndex.tableSort,
			where : {
				field : financeFinancePeriodIndex.tableSort.field,
				order : financeFinancePeriodIndex.tableSort.type,
				keyword : $('#' + financeFinancePeriodIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeFinancePeriodIndex.tableElem + ')', function(tableSort) {
			financeFinancePeriodIndex.tableSort = tableSort;
			financeFinancePeriodIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeFinancePeriodIndex.addBtnElem).on('click', function() {
			financeFinancePeriodIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeFinancePeriodIndex.editBtnElem).on('click', function() {
			financeFinancePeriodIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeFinancePeriodIndex.editUrl;
		var data = null;
		var content = null;
		
		if (type == 2) {
			var selectData = table.checkStatus(financeFinancePeriodIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeFinancePeriodIndex.dataId + '})');
			}
		}
		
		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;
		
		financeFinancePeriodIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeFinancePeriodIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeFinancePeriodIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + financeFinancePeriodIndex.saveBtnElem + ')', function(data) {
			var url = financeFinancePeriodIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					financeFinancePeriodIndex.tableReload();
					layer.close(financeFinancePeriodIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeFinancePeriodIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeFinancePeriodIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeFinancePeriodIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeFinancePeriodIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeFinancePeriodIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeFinancePeriodIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeFinancePeriodIndex.deleteWindowIndex);
	},
	selectBtnOn : function(){
		$('#' + financeFinancePeriodIndex.selectBtnElem).on('click', function() {
			financeFinancePeriodIndex.tableReload();
		});
	}
};
financeFinancePeriodIndex.tableInit();
financeFinancePeriodIndex.tableSortOn();
financeFinancePeriodIndex.addBtnOn();
financeFinancePeriodIndex.editBtnOn();
financeFinancePeriodIndex.saveBtnOn();
financeFinancePeriodIndex.deleteBtnOn();
financeFinancePeriodIndex.selectBtnOn();