//# sourceURL=/system/baseUser/companyIndex.js
var systemBaseUserCompanyIndex = {
	tableUrl : '/system/baseUser/companyPage',
	editUrl : '/system/baseUser/companyEdit',
	saveUrl : '/system/baseUser/companySave',
	deleteUrl : '/system/baseUser/companyDelete',
	tableElem : 'system-baseUser-companyIndex-table-1',
	addBtnElem : 'system-baseUser-companyIndex-btn-1',
	editBtnElem : 'system-baseUser-companyIndex-btn-2',
	deleteBtnElem : 'system-baseUser-companyIndex-btn-3',
	selectBtnElem : 'system-baseUser-companyIndex-btn-4',
	selectKeyElem : 'system-baseUser-companyIndex-key-1',
	saveBtnElem : 'system-baseUser-companyEdit-btn-1',
	dataId : 'userId',
	editWindowArea : [ '329px', '322px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'userName',
		title : '用户名称',
		align : 'center',
		sort : true,
	}, {
		field : 'userCard',
		title : '身份证号',
		align : 'center',
	}, {
		field : 'userPhone',
		title : '手机号码',
		align : 'center',
	}, {
		field : 'userEmail',
		title : '电子邮箱',
		align : 'left',
	} ] ],
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			autoSort : false,
			url : systemBaseUserCompanyIndex.tableUrl,
			elem : '#' + systemBaseUserCompanyIndex.tableElem,
			cols : systemBaseUserCompanyIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseUserCompanyIndex.tableElem, {
			initSort : systemBaseUserCompanyIndex.tableSort,
			where : {
				field : systemBaseUserCompanyIndex.tableSort.field,
				order : systemBaseUserCompanyIndex.tableSort.type,
				keyword : $('#' + systemBaseUserCompanyIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseUserCompanyIndex.tableElem + ')', function(tableSort) {
			systemBaseUserCompanyIndex.tableSort = tableSort;
			systemBaseUserCompanyIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseUserCompanyIndex.addBtnElem).on('click', function() {
			systemBaseUserCompanyIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseUserCompanyIndex.editBtnElem).on('click', function() {
			systemBaseUserCompanyIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseUserCompanyIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(systemBaseUserCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseUserCompanyIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseUserCompanyIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseUserCompanyIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseUserCompanyIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseUserCompanyIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseUserCompanyIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseUserCompanyIndex.tableReload();
					layer.close(systemBaseUserCompanyIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseUserCompanyIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseUserCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseUserCompanyIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseUserCompanyIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseUserCompanyIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseUserCompanyIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseUserCompanyIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + systemBaseUserCompanyIndex.selectBtnElem).on('click', function() {
			systemBaseUserCompanyIndex.tableReload();
		});
	},
	authEditUrl : '/system/baseUser/companyAuthEdit',
	authEditBtnElem : 'system-baseUser-companyIndex-btn-5',
	authEditWindowArea : [ '330px', '460px' ],
	authEditWindowIndex : null,
	authEditBtnOn : function() {
		$('#' + systemBaseUserCompanyIndex.authEditBtnElem).on('click', function() {
			var url = systemBaseUserCompanyIndex.authEditUrl;
			var content = null;

			var selectData = table.checkStatus(systemBaseUserCompanyIndex.tableElem).data;
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

				systemBaseUserCompanyIndex.authEditWindowIndex = layer.open({
					type : 1,
					title : '授权',
					content : content,
					area : systemBaseUserCompanyIndex.authEditWindowArea
				});
				systemBaseUserCompanyIndex.authTreeInit();
			}
		});
	},
	authTreeObj : null,
	authTreeUlElem : 'system-baseUser-companyAuthEdit-tree-1',
	authTreeSetting : {
		async : {
			enable : true,
			autoParam : [ 'roleId' ],
			url : '/system/baseUser/companyAuthFind',
			otherParam : function() {
				var selectData = table.checkStatus(systemBaseUserCompanyIndex.tableElem).data;
				selectData[0].company = null;
				selectData[0].createTime = common.formatDate(selectData[0], 'createTime');
				return selectData[0];
			}
		},
		data : {
			key : {
				name : 'roleName'
			},
			simpleData : {
				enable : true,
				idKey : 'roleId'
			},
		},
		check : {
			enable : true
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				systemBaseUserCompanyIndex.authTreeObj.checkNode(treeNode, !treeNode.checked, true);
			}
		}
	},
	authTreeInit : function() {
		systemBaseUserCompanyIndex.authTreeObj = $.fn.zTree.init($('#' + systemBaseUserCompanyIndex.authTreeUlElem), systemBaseUserCompanyIndex.authTreeSetting);
	},
	authSaveUrl : '/system/baseUser/companyAuthSave',
	authSaveBtnElem : 'system-baseUser-companyAuthEdit-btn-1',
	authSaveBtnOn : function() {
		form.on('submit(' + systemBaseUserCompanyIndex.authSaveBtnElem + ')', function(data) {
			var url = systemBaseUserCompanyIndex.authSaveUrl;
			var data = null;

			var selectData = table.checkStatus(systemBaseUserCompanyIndex.tableElem).data;
			var treeData = systemBaseUserCompanyIndex.authTreeObj.getCheckedNodes(true);
			data = {
				userId : selectData[0].userId,
				roles : treeData
			};

			$.ajax({
				contentType : 'application/json;charset=UTF-8',
				type : 'post',
				url : url,
				data : JSON.stringify(data),
				success : function(data) {
					if (data.code > 0) {
						layer.close(systemBaseUserCompanyIndex.authEditWindowIndex);
					}
					layer.msg(data.message);
				},
				dataType : 'json'
			});

			return false;
		});
	},
	accountEditUrl : '/system/baseUser/companyAccountEdit',
	accountEditBtnElem : 'system-baseUser-companyIndex-btn-6',
	accountEditWindowArea : [ '329px', '216px' ],
	accountEditWindowIndex : null,
	accountEditBtnOn : function() {
		$('#' + systemBaseUserCompanyIndex.accountEditBtnElem).on('click', function() {
			var url = systemBaseUserCompanyIndex.accountEditUrl;
			var data = null;
			var content = null;

			var selectData = table.checkStatus(systemBaseUserCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要设置的用户');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持多用户同时授权');
				return;
			} else {
				data = {
					id : selectData[0].userId
				};
				$.ajaxSettings.async = false;
				$.post(url, data, function(data) {
					content = data;
				}, 'html');
				$.ajaxSettings.async = true;

				systemBaseUserCompanyIndex.accountEditWindowIndex = layer.open({
					type : 1,
					title : '账号',
					content : content,
					area : systemBaseUserCompanyIndex.accountEditWindowArea
				});
				form.render();
			}
		});
	},
	accountSaveUrl : '/system/baseUser/companyAccountSave',
	accountSaveBtnElem : 'system-baseUser-companyAccountEdit-btn-1',
	accountSaveBtnOn : function() {
		form.on('submit(' + systemBaseUserCompanyIndex.accountSaveBtnElem + ')', function(data) {
			var url = systemBaseUserCompanyIndex.accountSaveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					layer.close(systemBaseUserCompanyIndex.accountEditWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	disableEditUrl : '/system/baseUser/companyDisableEdit',
	disableEditBtnElem : 'system-baseUser-companyIndex-btn-7',
	disableEditWindowArea : [ '329px', '163px' ],
	disableEditWindowIndex : null,
	disableEditBtnOn : function() {
		$('#' + systemBaseUserCompanyIndex.disableEditBtnElem).on('click', function() {
			var url = systemBaseUserCompanyIndex.disableEditUrl;
			var data = null;
			var content = null;

			var selectData = table.checkStatus(systemBaseUserCompanyIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要设置的用户');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持多用户同时设置');
				return;
			} else {
				data = {
					id : selectData[0].userId
				};
				$.ajaxSettings.async = false;
				$.post(url, data, function(data) {
					content = data;
				}, 'html');
				$.ajaxSettings.async = true;

				systemBaseUserCompanyIndex.disableEditWindowIndex = layer.open({
					type : 1,
					title : '账号',
					content : content,
					area : systemBaseUserCompanyIndex.disableEditWindowArea
				});
				form.render();
			}
		});
	},
	disableEditWindowClose : function() {
		layer.close(systemBaseUserCompanyIndex.disableEditWindowIndex);
	},
	disableSaveBtnOn : function(userId) {
		var url = '/system/baseUser/companyDisableSave';
		var data = {
			userId : userId
		};
		$.post(url, data, function(data) {
			if (data.code > 0) {
				layer.close(systemBaseUserCompanyIndex.disableEditWindowIndex);
			}
			layer.msg(data.message);
		}, 'json');
	}
};
systemBaseUserCompanyIndex.tableInit();
systemBaseUserCompanyIndex.tableSortOn();
systemBaseUserCompanyIndex.addBtnOn();
systemBaseUserCompanyIndex.editBtnOn();
systemBaseUserCompanyIndex.saveBtnOn();
systemBaseUserCompanyIndex.deleteBtnOn();
systemBaseUserCompanyIndex.selectBtnOn();
systemBaseUserCompanyIndex.authEditBtnOn();
systemBaseUserCompanyIndex.authSaveBtnOn();
systemBaseUserCompanyIndex.accountEditBtnOn();
systemBaseUserCompanyIndex.accountSaveBtnOn();
systemBaseUserCompanyIndex.disableEditBtnOn();