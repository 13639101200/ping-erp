//# sourceURL=/system/userLogin/index.js
var systemUserLoginIndex = {
	tableUrl : '/system/userLogin/page',
	editUrl : '/system/userLogin/edit',
	saveUrl : '/system/userLogin/save',
	deleteUrl : '/system/userLogin/delete',
	tableElem : 'system-userLogin-index-table-1',
	addBtnElem : 'system-userLogin-index-btn-1',
	editBtnElem : 'system-userLogin-index-btn-2',
	deleteBtnElem : 'system-userLogin-index-btn-3',
	selectBtnElem : 'system-userLogin-index-btn-4',
	selectKeyElem : 'system-userLogin-index-key-1',
	saveBtnElem : 'system-userLogin-edit-btn-1',
	dataId : 'userId',
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
			field : 'account',
			title : '账号',
			align : 'center',
			sort : true,
		},
		{
			field : 'password',
			title : '密码',
			align : 'center',
			sort : true,
		},
		{
			field : 'enable',
			title : '是否可用',
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
			url : systemUserLoginIndex.tableUrl,
			elem : '#' + systemUserLoginIndex.tableElem,
			cols : systemUserLoginIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemUserLoginIndex.tableElem, {
			initSort : systemUserLoginIndex.tableSort,
			where : {
				field : systemUserLoginIndex.tableSort.field,
				order : systemUserLoginIndex.tableSort.type,
				keyword : $('#' + systemUserLoginIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemUserLoginIndex.tableElem + ')', function(tableSort) {
			systemUserLoginIndex.tableSort = tableSort;
			systemUserLoginIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemUserLoginIndex.addBtnElem).on('click', function() {
			systemUserLoginIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemUserLoginIndex.editBtnElem).on('click', function() {
			systemUserLoginIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemUserLoginIndex.editUrl;
		var data = null;
		var content = null;
		
		if (type == 2) {
			var selectData = table.checkStatus(systemUserLoginIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemUserLoginIndex.dataId + '})');
			}
		}
		
		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;
		
		systemUserLoginIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemUserLoginIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemUserLoginIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemUserLoginIndex.saveBtnElem + ')', function(data) {
			var url = systemUserLoginIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemUserLoginIndex.tableReload();
					layer.close(systemUserLoginIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemUserLoginIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemUserLoginIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemUserLoginIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemUserLoginIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemUserLoginIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemUserLoginIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemUserLoginIndex.deleteWindowIndex);
	},
	selectBtnOn : function(){
		$('#' + systemUserLoginIndex.selectBtnElem).on('click', function() {
			systemUserLoginIndex.tableReload();
		});
	}
};
systemUserLoginIndex.tableInit();
systemUserLoginIndex.tableSortOn();
systemUserLoginIndex.addBtnOn();
systemUserLoginIndex.editBtnOn();
systemUserLoginIndex.saveBtnOn();
systemUserLoginIndex.deleteBtnOn();
systemUserLoginIndex.selectBtnOn();