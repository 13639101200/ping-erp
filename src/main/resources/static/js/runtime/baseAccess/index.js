//# sourceURL=/runtime/baseAccess/index.js
var runtimeBaseAccessIndex = {
	tableUrl : '/runtime/baseAccess/page',
	editUrl : '/runtime/baseAccess/edit',
	saveUrl : '/runtime/baseAccess/save',
	deleteUrl : '/runtime/baseAccess/delete',
	tableElem : 'runtime-baseAccess-index-table-1',
	addBtnElem : 'runtime-baseAccess-index-btn-1',
	editBtnElem : 'runtime-baseAccess-index-btn-2',
	deleteBtnElem : 'runtime-baseAccess-index-btn-3',
	selectBtnElem : 'runtime-baseAccess-index-btn-4',
	selectKeyElem : 'runtime-baseAccess-index-key-1',
	saveBtnElem : 'runtime-baseAccess-edit-btn-1',
	dataId : 'accessId',
	editWindowArea : [ '329px', '375px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'user.userName',
		title : '访问用户',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatField(value, 'user', 'userName');
		}
	}, {
		field : 'clientIp',
		title : '客户端地址',
		align : 'center',
		sort : true,
	}, {
		field : 'serverUrl',
		title : '服务器资源',
		align : 'left',
		sort : true,
		width : 400
	}, {
		field : 'accessTotal',
		title : '访问次数',
		align : 'center',
		sort : true,
		width : 100
	}, {
		field : 'lastTime',
		title : '最后访问时间',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatDate(value, 'lastTime');
		}
	} ] ],
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			autoSort : false,
			url : runtimeBaseAccessIndex.tableUrl,
			elem : '#' + runtimeBaseAccessIndex.tableElem,
			cols : runtimeBaseAccessIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(runtimeBaseAccessIndex.tableElem, {
			initSort : runtimeBaseAccessIndex.tableSort,
			where : {
				field : runtimeBaseAccessIndex.tableSort.field,
				order : runtimeBaseAccessIndex.tableSort.type,
				keyword : $('#' + runtimeBaseAccessIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + runtimeBaseAccessIndex.tableElem + ')', function(tableSort) {
			runtimeBaseAccessIndex.tableSort = tableSort;
			runtimeBaseAccessIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + runtimeBaseAccessIndex.addBtnElem).on('click', function() {
			runtimeBaseAccessIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + runtimeBaseAccessIndex.editBtnElem).on('click', function() {
			runtimeBaseAccessIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = runtimeBaseAccessIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(runtimeBaseAccessIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + runtimeBaseAccessIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		runtimeBaseAccessIndex.editWindowShow(type, content);
		laydate.render({
			elem : '#runtime-baseAccess-edit-input-1',
			type : 'datetime'
		});
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		runtimeBaseAccessIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : runtimeBaseAccessIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + runtimeBaseAccessIndex.saveBtnElem + ')', function(data) {
			var url = runtimeBaseAccessIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					runtimeBaseAccessIndex.tableReload();
					layer.close(runtimeBaseAccessIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + runtimeBaseAccessIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(runtimeBaseAccessIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				runtimeBaseAccessIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					runtimeBaseAccessIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : runtimeBaseAccessIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					runtimeBaseAccessIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(runtimeBaseAccessIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + runtimeBaseAccessIndex.selectBtnElem).on('click', function() {
			runtimeBaseAccessIndex.tableReload();
		});
	}
};
runtimeBaseAccessIndex.tableInit();
runtimeBaseAccessIndex.tableSortOn();
runtimeBaseAccessIndex.addBtnOn();
runtimeBaseAccessIndex.editBtnOn();
runtimeBaseAccessIndex.saveBtnOn();
runtimeBaseAccessIndex.deleteBtnOn();
runtimeBaseAccessIndex.selectBtnOn();