//# sourceURL=/system/baseRole/companyIndex.js
var systemBaseRoleCompanyIndex = {
	tableUrl : '/system/baseRole/companyPage',
	editUrl : '/system/baseRole/companyEdit',
	saveUrl : '/system/baseRole/companySave',
	deleteUrl : '/system/baseRole/companyDelete',
	tableElem : 'system-baseRole-companyIndex-table-1',
	addBtnElem : 'system-baseRole-companyIndex-btn-1',
	editBtnElem : 'system-baseRole-companyIndex-btn-2',
	deleteBtnElem : 'system-baseRole-companyIndex-btn-3',
	selectBtnElem : 'system-baseRole-companyIndex-btn-4',
	selectKeyElem : 'system-baseRole-companyIndex-key-1',
	saveBtnElem : 'system-baseRole-companyEdit-btn-1',
	dataId : 'roleId',
	editWindowArea : [ '329px', '163px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
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
			url : systemBaseRoleCompanyIndex.tableUrl,
			elem : '#' + systemBaseRoleCompanyIndex.tableElem,
			cols : systemBaseRoleCompanyIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseRoleCompanyIndex.tableElem, {
			initSort : systemBaseRoleCompanyIndex.tableSort,
			where : {
				field : systemBaseRoleCompanyIndex.tableSort.field,
				order : systemBaseRoleCompanyIndex.tableSort.type,
				keyword : $('#' + systemBaseRoleCompanyIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseRoleCompanyIndex.tableElem + ')', function(tableSort) {
			systemBaseRoleCompanyIndex.tableSort = tableSort;
			systemBaseRoleCompanyIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseRoleCompanyIndex.addBtnElem).on('click', function() {
			systemBaseRoleCompanyIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseRoleCompanyIndex.editBtnElem).on('click', function() {
			systemBaseRoleCompanyIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseRoleCompanyIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(systemBaseRoleCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseRoleCompanyIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseRoleCompanyIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseRoleCompanyIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseRoleCompanyIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseRoleCompanyIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseRoleCompanyIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseRoleCompanyIndex.tableReload();
					layer.close(systemBaseRoleCompanyIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseRoleCompanyIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseRoleCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseRoleCompanyIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseRoleCompanyIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseRoleCompanyIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseRoleCompanyIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseRoleCompanyIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + systemBaseRoleCompanyIndex.selectBtnElem).on('click', function() {
			systemBaseRoleCompanyIndex.tableReload();
		});
	},
	authEditUrl : '/system/baseRole/companyAuthEdit',
	authEditBtnElem : 'system-baseRole-companyIndex-btn-5',
	authEditWindowArea : [ '325px', '471px' ],
	authEditWindowIndex : null,
	authEditBtnOn : function() {
		$('#' + systemBaseRoleCompanyIndex.authEditBtnElem).on('click', function() {
			var url = systemBaseRoleCompanyIndex.authEditUrl;
			var content = null;

			var selectData = table.checkStatus(systemBaseRoleCompanyIndex.tableElem).data;
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

				systemBaseRoleCompanyIndex.authEditWindowIndex = layer.open({
					type : 1,
					title : '授权',
					content : content,
					area : systemBaseRoleCompanyIndex.authEditWindowArea
				});
				systemBaseRoleCompanyIndex.authTreeInit();
			}
		});
	},
	authTreeObj : null,
	authTreeUlElem : 'system-baseRole-companyAuthEdit-tree-1',
	authTreeSetting : {
		async : {
			enable : true,
			autoParam : [ 'menuId' ],
			url : '/system/baseRole/companyAuthFind',
			otherParam : function() {
				var selectData = table.checkStatus(systemBaseRoleCompanyIndex.tableElem).data;
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
				systemBaseRoleCompanyIndex.authTreeObj.checkNode(treeNode, !treeNode.checked, true);
			}
		}
	},
	authTreeInit : function() {
		systemBaseRoleCompanyIndex.authTreeObj = $.fn.zTree.init($('#' + systemBaseRoleCompanyIndex.authTreeUlElem), systemBaseRoleCompanyIndex.authTreeSetting);
	},
	authSaveUrl : '/system/baseRole/companyAuthSave',
	authSaveBtnElem : 'system-baseRole-companyAuthEdit-btn-1',
	authSaveBtnOn : function() {
		form.on('submit(' + systemBaseRoleCompanyIndex.authSaveBtnElem + ')', function(data) {
			var url = systemBaseRoleCompanyIndex.authSaveUrl;
			var data = null;

			var selectData = table.checkStatus(systemBaseRoleCompanyIndex.tableElem).data;
			var treeData = systemBaseRoleCompanyIndex.authTreeObj.getCheckedNodes(true);
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
						layer.close(systemBaseRoleCompanyIndex.authEditWindowIndex);
					}
					layer.msg(data.message);
				},
				dataType : 'json'
			});

			return false;
		});
	}
};
systemBaseRoleCompanyIndex.tableInit();
systemBaseRoleCompanyIndex.tableSortOn();
systemBaseRoleCompanyIndex.addBtnOn();
systemBaseRoleCompanyIndex.editBtnOn();
systemBaseRoleCompanyIndex.saveBtnOn();
systemBaseRoleCompanyIndex.deleteBtnOn();
systemBaseRoleCompanyIndex.selectBtnOn();
systemBaseRoleCompanyIndex.authEditBtnOn();
systemBaseRoleCompanyIndex.authSaveBtnOn();