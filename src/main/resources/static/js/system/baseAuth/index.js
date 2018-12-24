//# sourceURL=/system/baseAuth/index.js
var systemBaseAuthIndex = {
	tableUrl : '/system/baseAuth/page',
	editUrl : '/system/baseAuth/edit',
	saveUrl : '/system/baseAuth/save',
	deleteUrl : '/system/baseAuth/delete',
	tableElem : 'system-baseAuth-index-table-1',
	addBtnElem : 'system-baseAuth-index-btn-1',
	editBtnElem : 'system-baseAuth-index-btn-2',
	deleteBtnElem : 'system-baseAuth-index-btn-3',
	selectBtnElem : 'system-baseAuth-index-btn-4',
	selectKeyElem : 'system-baseAuth-index-key-1',
	saveBtnElem : 'system-baseAuth-edit-btn-1',
	dataId : 'authId',
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
			field : 'menuId',
			title : '菜单ID',
			align : 'center',
			sort : true,
		},
		{
			field : 'authHref',
			title : '权限链接',
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
			url : systemBaseAuthIndex.tableUrl,
			elem : '#' + systemBaseAuthIndex.tableElem,
			cols : systemBaseAuthIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(systemBaseAuthIndex.tableElem, {
			initSort : systemBaseAuthIndex.tableSort,
			where : {
				field : systemBaseAuthIndex.tableSort.field,
				order : systemBaseAuthIndex.tableSort.type,
				keyword : $('#' + systemBaseAuthIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + systemBaseAuthIndex.tableElem + ')', function(tableSort) {
			systemBaseAuthIndex.tableSort = tableSort;
			systemBaseAuthIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + systemBaseAuthIndex.addBtnElem).on('click', function() {
			systemBaseAuthIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + systemBaseAuthIndex.editBtnElem).on('click', function() {
			systemBaseAuthIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = systemBaseAuthIndex.editUrl;
		var data = null;
		var content = null;
		
		if (type == 2) {
			var selectData = table.checkStatus(systemBaseAuthIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + systemBaseAuthIndex.dataId + '})');
			}
		}
		
		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;
		
		systemBaseAuthIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		systemBaseAuthIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseAuthIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + systemBaseAuthIndex.saveBtnElem + ')', function(data) {
			var url = systemBaseAuthIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseAuthIndex.tableReload();
					layer.close(systemBaseAuthIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + systemBaseAuthIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(systemBaseAuthIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				systemBaseAuthIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					systemBaseAuthIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : systemBaseAuthIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseAuthIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseAuthIndex.deleteWindowIndex);
	},
	selectBtnOn : function(){
		$('#' + systemBaseAuthIndex.selectBtnElem).on('click', function() {
			systemBaseAuthIndex.tableReload();
		});
	}
};
systemBaseAuthIndex.tableInit();
systemBaseAuthIndex.tableSortOn();
systemBaseAuthIndex.addBtnOn();
systemBaseAuthIndex.editBtnOn();
systemBaseAuthIndex.saveBtnOn();
systemBaseAuthIndex.deleteBtnOn();
systemBaseAuthIndex.selectBtnOn();