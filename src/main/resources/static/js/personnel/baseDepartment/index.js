//# sourceURL=/personnel/baseDepartment/index.js
var personnelBaseDepartmentIndex = {
	tableUrl : '/personnel/baseDepartment/page',
	editUrl : '/personnel/baseDepartment/edit',
	saveUrl : '/personnel/baseDepartment/save',
	deleteUrl : '/personnel/baseDepartment/delete',
	tableElem : 'personnel-baseDepartment-index-table-1',
	addBtnElem : 'personnel-baseDepartment-index-btn-1',
	editBtnElem : 'personnel-baseDepartment-index-btn-2',
	deleteBtnElem : 'personnel-baseDepartment-index-btn-3',
	selectBtnElem : 'personnel-baseDepartment-index-btn-4',
	selectKeyElem : 'personnel-baseDepartment-index-key-1',
	saveBtnElem : 'personnel-baseDepartment-edit-btn-1',
	dataId : 'departmentId',
	editWindowArea : [ '329px', '216px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'departmentPid',
		title : '部门PID',
		align : 'center',
		sort : true,
	}, {
		field : 'departmentName',
		title : '部门名称',
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
			url : personnelBaseDepartmentIndex.tableUrl,
			elem : '#' + personnelBaseDepartmentIndex.tableElem,
			cols : personnelBaseDepartmentIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(personnelBaseDepartmentIndex.tableElem, {
			initSort : personnelBaseDepartmentIndex.tableSort,
			where : {
				field : personnelBaseDepartmentIndex.tableSort.field,
				order : personnelBaseDepartmentIndex.tableSort.type,
				keyword : $('#' + personnelBaseDepartmentIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + personnelBaseDepartmentIndex.tableElem + ')', function(tableSort) {
			personnelBaseDepartmentIndex.tableSort = tableSort;
			personnelBaseDepartmentIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + personnelBaseDepartmentIndex.addBtnElem).on('click', function() {
			personnelBaseDepartmentIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + personnelBaseDepartmentIndex.editBtnElem).on('click', function() {
			personnelBaseDepartmentIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = personnelBaseDepartmentIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(personnelBaseDepartmentIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + personnelBaseDepartmentIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		personnelBaseDepartmentIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		personnelBaseDepartmentIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : personnelBaseDepartmentIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + personnelBaseDepartmentIndex.saveBtnElem + ')', function(data) {
			var url = personnelBaseDepartmentIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					personnelBaseDepartmentIndex.tableReload();
					layer.close(personnelBaseDepartmentIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + personnelBaseDepartmentIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(personnelBaseDepartmentIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				personnelBaseDepartmentIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					personnelBaseDepartmentIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : personnelBaseDepartmentIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					personnelBaseDepartmentIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(personnelBaseDepartmentIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + personnelBaseDepartmentIndex.selectBtnElem).on('click', function() {
			personnelBaseDepartmentIndex.tableReload();
		});
	}
};
personnelBaseDepartmentIndex.tableInit();
personnelBaseDepartmentIndex.tableSortOn();
personnelBaseDepartmentIndex.addBtnOn();
personnelBaseDepartmentIndex.editBtnOn();
personnelBaseDepartmentIndex.saveBtnOn();
personnelBaseDepartmentIndex.deleteBtnOn();
personnelBaseDepartmentIndex.selectBtnOn();