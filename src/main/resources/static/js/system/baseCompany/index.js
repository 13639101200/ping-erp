//# sourceURL=/system/baseCompany/index.js
var systemBaseCompanyIndex = {
	tableUrl : '/system/baseCompany/page',
	editUrl : '/system/baseCompany/edit',
	saveUrl : '/system/baseCompany/save',
	deleteUrl : '/system/baseCompany/delete',
	tableElem : 'system-baseCompany-index-table-1',
	addBtnElem : 'system-baseCompany-index-btn-1',
	editBtnElem : 'system-baseCompany-index-btn-2',
	deleteBtnElem : 'system-baseCompany-index-btn-3',
	selectBtnElem : 'system-baseCompany-index-btn-4',
	selectKeyElem : 'system-baseCompany-index-key-1',
	saveBtnElem : 'system-baseCompany-edit-btn-1',
	dataId : 'companyId',
	editWindowArea : [ '329px', '216px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'companyName',
		title : '公司名称',
		align : 'center',
		sort : true,
	}, {
		field : 'companyUrl',
		title : '公司链接',
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
			url : systemBaseCompanyIndex.tableUrl,
			elem : '#' + systemBaseCompanyIndex.tableElem,
			cols : systemBaseCompanyIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseCompanyIndex.tableElem, {
			initSort : systemBaseCompanyIndex.tableSort,
			where : {
				field : systemBaseCompanyIndex.tableSort.field,
				order : systemBaseCompanyIndex.tableSort.type,
				keyword : $('#' + systemBaseCompanyIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseCompanyIndex.tableElem + ')', function(tableSort) {
			systemBaseCompanyIndex.tableSort = tableSort;
			systemBaseCompanyIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseCompanyIndex.addBtnElem).on('click', function() {
			systemBaseCompanyIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseCompanyIndex.editBtnElem).on('click', function() {
			systemBaseCompanyIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseCompanyIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(systemBaseCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseCompanyIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseCompanyIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseCompanyIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseCompanyIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseCompanyIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseCompanyIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseCompanyIndex.tableReload();
					layer.close(systemBaseCompanyIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseCompanyIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseCompanyIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseCompanyIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseCompanyIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseCompanyIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseCompanyIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + systemBaseCompanyIndex.selectBtnElem).on('click', function() {
			systemBaseCompanyIndex.tableReload();
		});
	},
	authEditUrl : '/system/baseCompany/authEdit',
	authEditBtnElem : 'system-baseCompany-index-btn-5',
	authEditWindowArea : [ '330px', '460px' ],
	authEditWindowIndex : null,
	authEditBtnOn : function() {
		$('#' + systemBaseCompanyIndex.authEditBtnElem).on('click', function() {
			var url = systemBaseCompanyIndex.authEditUrl;
			var content = null;

			var selectData = table.checkStatus(systemBaseCompanyIndex.tableElem).data;
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

				systemBaseCompanyIndex.authEditWindowIndex = layer.open({
					type : 1,
					title : '授权',
					content : content,
					area : systemBaseCompanyIndex.authEditWindowArea
				});
				systemBaseCompanyIndex.authTreeInit();
			}
		});
	},
	authTreeObj : null,
	authTreeUlElem : 'system-baseCompany-authEdit-tree-1',
	authTreeSetting : {
		async : {
			enable : true,
			autoParam : [ 'menuId' ],
			url : '/system/baseCompany/authFind',
			otherParam : function() {
				var selectData = table.checkStatus(systemBaseCompanyIndex.tableElem).data;
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
				systemBaseCompanyIndex.authTreeObj.checkNode(treeNode, !treeNode.checked, true);
			}
		}
	},
	authTreeInit : function() {
		systemBaseCompanyIndex.authTreeObj = $.fn.zTree.init($('#' + systemBaseCompanyIndex.authTreeUlElem), systemBaseCompanyIndex.authTreeSetting);
	},
	authSaveUrl : '/system/baseCompany/authSave',
	authSaveBtnElem : 'system-baseCompany-authEdit-btn-1',
	authSaveBtnOn : function() {
		form.on('submit(' + systemBaseCompanyIndex.authSaveBtnElem + ')', function(data) {
			var url = systemBaseCompanyIndex.authSaveUrl;
			var data = null;

			var selectData = table.checkStatus(systemBaseCompanyIndex.tableElem).data;
			var treeData = systemBaseCompanyIndex.authTreeObj.getCheckedNodes(true);
			data = {
				companyId : selectData[0].companyId,
				menus : treeData
			};

			$.ajax({
				contentType : 'application/json;charset=UTF-8',
				type : 'post',
				url : url,
				data : JSON.stringify(data),
				success : function(data) {
					if (data.code > 0) {
						layer.close(systemBaseCompanyIndex.authEditWindowIndex);
					}
					layer.msg(data.message);
				},
				dataType : 'json'
			});

			return false;
		});
	}
};
systemBaseCompanyIndex.tableInit();
systemBaseCompanyIndex.tableSortOn();
systemBaseCompanyIndex.addBtnOn();
systemBaseCompanyIndex.editBtnOn();
systemBaseCompanyIndex.saveBtnOn();
systemBaseCompanyIndex.deleteBtnOn();
systemBaseCompanyIndex.selectBtnOn();
systemBaseCompanyIndex.authEditBtnOn();
systemBaseCompanyIndex.authSaveBtnOn();