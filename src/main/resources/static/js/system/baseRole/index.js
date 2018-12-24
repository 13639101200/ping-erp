//# sourceURL=/system/baseRole/index.js
var systemBaseRoleIndex = {
	tableUrl : '/system/baseRole/page',
	editUrl : '/system/baseRole/edit',
	saveUrl : '/system/baseRole/save',
	deleteUrl : '/system/baseRole/delete',
	tableElem : 'system-baseRole-index-table-1',
	addBtnElem : 'system-baseRole-index-btn-1',
	editBtnElem : 'system-baseRole-index-btn-2',
	deleteBtnElem : 'system-baseRole-index-btn-3',
	selectBtnElem : 'system-baseRole-index-btn-4',
	selectKeyElem : 'system-baseRole-index-key-1',
	saveBtnElem : 'system-baseRole-edit-btn-1',
	dataId : 'roleId',
	editWindowArea : [ '329px', '216px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'company.companyName',
		title : '公司',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatField(value, 'company', 'companyName');
		}
	}, {
		field : 'roleName',
		title : '角色名称',
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
			url : systemBaseRoleIndex.tableUrl,
			elem : '#' + systemBaseRoleIndex.tableElem,
			cols : systemBaseRoleIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseRoleIndex.tableElem, {
			initSort : systemBaseRoleIndex.tableSort,
			where : {
				field : systemBaseRoleIndex.tableSort.field,
				order : systemBaseRoleIndex.tableSort.type,
				keyword : $('#' + systemBaseRoleIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseRoleIndex.tableElem + ')', function(tableSort) {
			systemBaseRoleIndex.tableSort = tableSort;
			systemBaseRoleIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseRoleIndex.addBtnElem).on('click', function() {
			systemBaseRoleIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseRoleIndex.editBtnElem).on('click', function() {
			systemBaseRoleIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseRoleIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(systemBaseRoleIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseRoleIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseRoleIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseRoleIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseRoleIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseRoleIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseRoleIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseRoleIndex.tableReload();
					layer.close(systemBaseRoleIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseRoleIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseRoleIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseRoleIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseRoleIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseRoleIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseRoleIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseRoleIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + systemBaseRoleIndex.selectBtnElem).on('click', function() {
			systemBaseRoleIndex.tableReload();
		});
	},
	authEditUrl : '/system/baseRole/authEdit',
	authEditBtnElem : 'system-baseRole-index-btn-5',
	authEditWindowArea : [ '330px', '460px' ],
	authEditWindowIndex : null,
	authEditBtnOn : function() {
		$('#' + systemBaseRoleIndex.authEditBtnElem).on('click', function() {
			var url = systemBaseRoleIndex.authEditUrl;
			var content = null;

			var selectData = table.checkStatus(systemBaseRoleIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要授权的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时授权');
				return;
			} else {
				$.ajaxSettings.async = false;
				$.post(url, function(data) {
					content = data;
				}, 'html');
				$.ajaxSettings.async = true;

				systemBaseRoleIndex.authEditWindowIndex = layer.open({
					type : 1,
					title : '授权',
					content : content,
					area : systemBaseRoleIndex.authEditWindowArea
				});
				systemBaseRoleIndex.authTreeInit();
			}
		});
	},
	authTreeObj : null,
	authTreeUlElem : 'system-baseRole-authEdit-tree-1',
	authTreeSetting : {
		async : {
			enable : true,
			autoParam : [ 'menuId' ],
			url : '/system/baseRole/authFind',
			otherParam : function() {
				var selectData = table.checkStatus(systemBaseRoleIndex.tableElem).data;
				selectData[0].company = null;
				selectData[0].createTime = common.formatDate(selectData[0], 'createTime');
				return selectData[0];
			}
		},
		data : {
			key : {
				name : 'menuName'
			},
			simpleData : {
				enable : true,
				idKey : 'menuId',
				pIdKey : 'menuPid'
			},
		},
		check : {
			enable : true
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				systemBaseRoleIndex.authTreeObj.checkNode(treeNode, !treeNode.checked, true);
			}
		}
	},
	authTreeInit : function() {
		systemBaseRoleIndex.authTreeObj = $.fn.zTree.init($('#' + systemBaseRoleIndex.authTreeUlElem), systemBaseRoleIndex.authTreeSetting);
	},
	authSaveUrl : '/system/baseRole/authSave',
	authSaveBtnElem : 'system-baseRole-authEdit-btn-1',
	authSaveBtnOn : function() {
		form.on('submit(' + systemBaseRoleIndex.authSaveBtnElem + ')', function(data) {
			var url = systemBaseRoleIndex.authSaveUrl;
			var data = null;

			var selectData = table.checkStatus(systemBaseRoleIndex.tableElem).data;
			var treeData = systemBaseRoleIndex.authTreeObj.getCheckedNodes(true);
			data = {
				roleId : selectData[0].roleId,
				menus : treeData
			};

			$.ajax({
				contentType : 'application/json;charset=UTF-8',
				type : 'post',
				url : url,
				data : JSON.stringify(data),
				success : function(data) {
					if (data.code > 0) {
						layer.close(systemBaseRoleIndex.authEditWindowIndex);
					}
					layer.msg(data.message);
				},
				dataType : 'json'
			});

			return false;
		});
	}
};
systemBaseRoleIndex.tableInit();
systemBaseRoleIndex.tableSortOn();
systemBaseRoleIndex.addBtnOn();
systemBaseRoleIndex.editBtnOn();
systemBaseRoleIndex.saveBtnOn();
systemBaseRoleIndex.deleteBtnOn();
systemBaseRoleIndex.selectBtnOn();
systemBaseRoleIndex.authEditBtnOn();
systemBaseRoleIndex.authSaveBtnOn();