//# sourceURL=/personnel/baseClerk/index.js
var personnelBaseClerkIndex = {
	tableUrl : '/personnel/baseClerk/page',
	editUrl : '/personnel/baseClerk/edit',
	saveUrl : '/personnel/baseClerk/save',
	deleteUrl : '/personnel/baseClerk/delete',
	tableElem : 'personnel-baseClerk-index-table-1',
	addBtnElem : 'personnel-baseClerk-index-btn-1',
	editBtnElem : 'personnel-baseClerk-index-btn-2',
	deleteBtnElem : 'personnel-baseClerk-index-btn-3',
	selectBtnElem : 'personnel-baseClerk-index-btn-4',
	selectKeyElem : 'personnel-baseClerk-index-key-1',
	saveBtnElem : 'personnel-baseClerk-edit-btn-1',
	dataId : 'clerkId',
	editWindowArea : [ '329px', '163px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'clerkName',
		title : '职员名称',
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
			url : personnelBaseClerkIndex.tableUrl,
			elem : '#' + personnelBaseClerkIndex.tableElem,
			cols : personnelBaseClerkIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(personnelBaseClerkIndex.tableElem, {
			initSort : personnelBaseClerkIndex.tableSort,
			where : {
				field : personnelBaseClerkIndex.tableSort.field,
				order : personnelBaseClerkIndex.tableSort.type,
				keyword : $('#' + personnelBaseClerkIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + personnelBaseClerkIndex.tableElem + ')', function(tableSort) {
			personnelBaseClerkIndex.tableSort = tableSort;
			personnelBaseClerkIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + personnelBaseClerkIndex.addBtnElem).on('click', function() {
			personnelBaseClerkIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + personnelBaseClerkIndex.editBtnElem).on('click', function() {
			personnelBaseClerkIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = personnelBaseClerkIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(personnelBaseClerkIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + personnelBaseClerkIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		personnelBaseClerkIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		personnelBaseClerkIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : personnelBaseClerkIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + personnelBaseClerkIndex.saveBtnElem + ')', function(data) {
			var url = personnelBaseClerkIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					personnelBaseClerkIndex.tableReload();
					layer.close(personnelBaseClerkIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + personnelBaseClerkIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(personnelBaseClerkIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				personnelBaseClerkIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					personnelBaseClerkIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : personnelBaseClerkIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					personnelBaseClerkIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(personnelBaseClerkIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + personnelBaseClerkIndex.selectBtnElem).on('click', function() {
			personnelBaseClerkIndex.tableReload();
		});
	}
};
personnelBaseClerkIndex.tableInit();
personnelBaseClerkIndex.tableSortOn();
personnelBaseClerkIndex.addBtnOn();
personnelBaseClerkIndex.editBtnOn();
personnelBaseClerkIndex.saveBtnOn();
personnelBaseClerkIndex.deleteBtnOn();
personnelBaseClerkIndex.selectBtnOn();