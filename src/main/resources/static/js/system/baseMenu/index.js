//# sourceURL=/system/baseMenu/index.js
var systemBaseMenuIndex = {
	tableUrl : '/system/baseMenu/page',
	editUrl : '/system/baseMenu/edit',
	saveUrl : '/system/baseMenu/save',
	deleteUrl : '/system/baseMenu/delete',
	tableElem : 'system-baseMenu-index-table-1',
	addBtnElem : 'system-baseMenu-index-btn-1',
	editBtnElem : 'system-baseMenu-index-btn-2',
	deleteBtnElem : 'system-baseMenu-index-btn-3',
	selectBtnElem : 'system-baseMenu-index-btn-4',
	selectKeyElem : 'system-baseMenu-index-key-1',
	saveBtnElem : 'system-baseMenu-edit-btn-1',
	dataId : 'menuId',
	editWindowArea : [ '638px', '322px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'menuId',
		title : '菜单ID',
		align : 'center',
		sort : true,
		width : 320
	}, {
		field : 'menuName',
		title : '菜单名称',
		align : 'center',
		sort : true,
	}, {
		field : 'menuHref',
		title : '菜单链接',
		align : 'left',
		sort : true,
	}, {
		field : 'menuType.codeValue',
		title : '菜单类型',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatField(value, 'menuType', 'codeValue');
		}
	}, {
		field : 'menuTarget.codeValue',
		title : '打开方式',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatField(value, 'menuTarget', 'codeValue');
		}
	}, {
		field : 'orderNumber',
		title : '排序编号',
		align : 'center',
		sort : true,
		width : 80
	} ] ],
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			autoSort : false,
			url : systemBaseMenuIndex.tableUrl,
			elem : '#' + systemBaseMenuIndex.tableElem,
			cols : systemBaseMenuIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseMenuIndex.tableElem, {
			initSort : systemBaseMenuIndex.tableSort,
			where : {
				field : systemBaseMenuIndex.tableSort.field,
				order : systemBaseMenuIndex.tableSort.type,
				keyword : $('#' + systemBaseMenuIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseMenuIndex.tableElem + ')', function(tableSort) {
			systemBaseMenuIndex.tableSort = tableSort;
			systemBaseMenuIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseMenuIndex.addBtnElem).on('click', function() {
			systemBaseMenuIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseMenuIndex.editBtnElem).on('click', function() {
			systemBaseMenuIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseMenuIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(systemBaseMenuIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseMenuIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseMenuIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseMenuIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseMenuIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseMenuIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseMenuIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseMenuIndex.tableReload();
					layer.close(systemBaseMenuIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseMenuIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseMenuIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseMenuIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseMenuIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseMenuIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseMenuIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseMenuIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + systemBaseMenuIndex.selectBtnElem).on('click', function() {
			systemBaseMenuIndex.tableReload();
		});
	}
};
systemBaseMenuIndex.tableInit();
systemBaseMenuIndex.tableSortOn();
systemBaseMenuIndex.addBtnOn();
systemBaseMenuIndex.editBtnOn();
systemBaseMenuIndex.saveBtnOn();
systemBaseMenuIndex.deleteBtnOn();
systemBaseMenuIndex.selectBtnOn();