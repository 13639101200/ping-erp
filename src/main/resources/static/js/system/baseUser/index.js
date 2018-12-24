//# sourceURL=/system/baseUser/index.js
var systemBaseUserIndex = {
	tableUrl : '/system/baseUser/page',
	editUrl : '/system/baseUser/edit',
	saveUrl : '/system/baseUser/save',
	deleteUrl : '/system/baseUser/delete',
	tableElem : 'system-baseUser-index-table-1',
	addBtnElem : 'system-baseUser-index-btn-1',
	editBtnElem : 'system-baseUser-index-btn-2',
	deleteBtnElem : 'system-baseUser-index-btn-3',
	selectBtnElem : 'system-baseUser-index-btn-4',
	selectKeyElem : 'system-baseUser-index-key-1',
	saveBtnElem : 'system-baseUser-edit-btn-1',
	dataId : 'userId',
	editWindowArea : [ '329px', '375px' ],
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
			url : systemBaseUserIndex.tableUrl,
			elem : '#' + systemBaseUserIndex.tableElem,
			cols : systemBaseUserIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseUserIndex.tableElem, {
			initSort : systemBaseUserIndex.tableSort,
			where : {
				field : systemBaseUserIndex.tableSort.field,
				order : systemBaseUserIndex.tableSort.type,
				keyword : $('#' + systemBaseUserIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseUserIndex.tableElem + ')', function(tableSort) {
			systemBaseUserIndex.tableSort = tableSort;
			systemBaseUserIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseUserIndex.addBtnElem).on('click', function() {
			systemBaseUserIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseUserIndex.editBtnElem).on('click', function() {
			systemBaseUserIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseUserIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(systemBaseUserIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseUserIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseUserIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseUserIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseUserIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseUserIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseUserIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseUserIndex.tableReload();
					layer.close(systemBaseUserIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseUserIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseUserIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseUserIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseUserIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseUserIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseUserIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseUserIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + systemBaseUserIndex.selectBtnElem).on('click', function() {
			systemBaseUserIndex.tableReload();
		});
	},
	authEditUrl : '/system/baseUser/authEdit',
	authEditBtnElem : 'system-baseUser-index-btn-5',
	authEditWindowArea : [ '330px', '460px' ],
	authEditWindowIndex : null,
	authEditBtnOn : function() {
		$('#' + systemBaseUserIndex.authEditBtnElem).on('click', function() {
			var url = systemBaseUserIndex.authEditUrl;
			var content = null;

			var selectData = table.checkStatus(systemBaseUserIndex.tableElem).data;
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

				systemBaseUserIndex.authEditWindowIndex = layer.open({
					type : 1,
					title : '授权',
					content : content,
					area : systemBaseUserIndex.authEditWindowArea
				});
				systemBaseUserIndex.authTreeInit();
			}
		});
	},
	authTreeObj : null,
	authTreeUlElem : 'system-baseUser-authEdit-tree-1',
	authTreeSetting : {
		async : {
			enable : true,
			autoParam : [ 'roleId' ],
			url : '/system/baseUser/authFind',
			otherParam : function() {
				var selectData = table.checkStatus(systemBaseUserIndex.tableElem).data;
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
				systemBaseUserIndex.authTreeObj.checkNode(treeNode, !treeNode.checked, true);
			}
		}
	},
	authTreeInit : function() {
		systemBaseUserIndex.authTreeObj = $.fn.zTree.init($('#' + systemBaseUserIndex.authTreeUlElem), systemBaseUserIndex.authTreeSetting);
	},
	authSaveUrl : '/system/baseUser/authSave',
	authSaveBtnElem : 'system-baseUser-authEdit-btn-1',
	authSaveBtnOn : function() {
		form.on('submit(' + systemBaseUserIndex.authSaveBtnElem + ')', function(data) {
			var url = systemBaseUserIndex.authSaveUrl;
			var data = null;

			var selectData = table.checkStatus(systemBaseUserIndex.tableElem).data;
			var treeData = systemBaseUserIndex.authTreeObj.getCheckedNodes(true);
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
						layer.close(systemBaseUserIndex.authEditWindowIndex);
					}
					layer.msg(data.message);
				},
				dataType : 'json'
			});

			return false;
		});
	},
	accountEditUrl : '/system/baseUser/accountEdit',
	accountEditBtnElem : 'system-baseUser-index-btn-6',
	accountEditWindowArea : [ '329px', '216px' ],
	accountEditWindowIndex : null,
	accountEditBtnOn : function() {
		$('#' + systemBaseUserIndex.accountEditBtnElem).on('click', function() {
			var url = systemBaseUserIndex.accountEditUrl;
			var data = null;
			var content = null;

			var selectData = table.checkStatus(systemBaseUserIndex.tableElem).data;
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

				systemBaseUserIndex.accountEditWindowIndex = layer.open({
					type : 1,
					title : '账号',
					content : content,
					area : systemBaseUserIndex.accountEditWindowArea
				});
				form.render();
			}
		});
	},
	accountSaveUrl : '/system/baseUser/accountSave',
	accountSaveBtnElem : 'system-baseUser-accountEdit-btn-1',
	accountSaveBtnOn : function() {
		form.on('submit(' + systemBaseUserIndex.accountSaveBtnElem + ')', function(data) {
			var url = systemBaseUserIndex.accountSaveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					layer.close(systemBaseUserIndex.accountEditWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	disableEditUrl : '/system/baseUser/disableEdit',
	disableEditBtnElem : 'system-baseUser-index-btn-7',
	disableEditWindowArea : [ '329px', '163px' ],
	disableEditWindowIndex : null,
	disableEditBtnOn : function() {
		$('#' + systemBaseUserIndex.disableEditBtnElem).on('click', function() {
			var url = systemBaseUserIndex.disableEditUrl;
			var data = null;
			var content = null;

			var selectData = table.checkStatus(systemBaseUserIndex.tableElem).data;
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

				systemBaseUserIndex.disableEditWindowIndex = layer.open({
					type : 1,
					title : '账号',
					content : content,
					area : systemBaseUserIndex.disableEditWindowArea
				});
				form.render();
			}
		});
	},
	disableEditWindowClose : function() {
		layer.close(systemBaseUserIndex.disableEditWindowIndex);
	},
	disableSaveBtnOn : function(userId) {
		var url = '/system/baseUser/disableSave';
		var data = {
			userId : userId
		};
		$.post(url, data, function(data) {
			if (data.code > 0) {
				layer.close(systemBaseUserIndex.disableEditWindowIndex);
			}
			layer.msg(data.message);
		}, 'json');
	}
};
systemBaseUserIndex.tableInit();
systemBaseUserIndex.tableSortOn();
systemBaseUserIndex.addBtnOn();
systemBaseUserIndex.editBtnOn();
systemBaseUserIndex.saveBtnOn();
systemBaseUserIndex.deleteBtnOn();
systemBaseUserIndex.selectBtnOn();
systemBaseUserIndex.authEditBtnOn();
systemBaseUserIndex.authSaveBtnOn();
systemBaseUserIndex.accountEditBtnOn();
systemBaseUserIndex.accountSaveBtnOn();
systemBaseUserIndex.disableEditBtnOn();