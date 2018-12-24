//# sourceURL=/project/baseProject/index.js
var projectBaseProjectIndex = {
	tableUrl : '/project/baseProject/page',
	editUrl : '/project/baseProject/edit',
	saveUrl : '/project/baseProject/save',
	deleteUrl : '/project/baseProject/delete',
	tableElem : 'project-baseProject-index-table-1',
	addBtnElem : 'project-baseProject-index-btn-1',
	editBtnElem : 'project-baseProject-index-btn-2',
	deleteBtnElem : 'project-baseProject-index-btn-3',
	selectBtnElem : 'project-baseProject-index-btn-4',
	selectKeyElem : 'project-baseProject-index-key-1',
	saveBtnElem : 'project-baseProject-edit-btn-1',
	dataId : 'projectId',
	editWindowArea : [ '329px', '216px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'projectPid',
		title : '项目PID',
		align : 'center',
		sort : true,
	}, {
		field : 'projectName',
		title : '项目名称',
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
			url : projectBaseProjectIndex.tableUrl,
			elem : '#' + projectBaseProjectIndex.tableElem,
			cols : projectBaseProjectIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(projectBaseProjectIndex.tableElem, {
			initSort : projectBaseProjectIndex.tableSort,
			where : {
				field : projectBaseProjectIndex.tableSort.field,
				order : projectBaseProjectIndex.tableSort.type,
				keyword : $('#' + projectBaseProjectIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + projectBaseProjectIndex.tableElem + ')', function(tableSort) {
			projectBaseProjectIndex.tableSort = tableSort;
			projectBaseProjectIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + projectBaseProjectIndex.addBtnElem).on('click', function() {
			projectBaseProjectIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + projectBaseProjectIndex.editBtnElem).on('click', function() {
			projectBaseProjectIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = projectBaseProjectIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(projectBaseProjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + projectBaseProjectIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		projectBaseProjectIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		projectBaseProjectIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : projectBaseProjectIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + projectBaseProjectIndex.saveBtnElem + ')', function(data) {
			var url = projectBaseProjectIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					projectBaseProjectIndex.tableReload();
					layer.close(projectBaseProjectIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + projectBaseProjectIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(projectBaseProjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				projectBaseProjectIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					projectBaseProjectIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : projectBaseProjectIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					projectBaseProjectIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(projectBaseProjectIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + projectBaseProjectIndex.selectBtnElem).on('click', function() {
			projectBaseProjectIndex.tableReload();
		});
	}
};
projectBaseProjectIndex.tableInit();
projectBaseProjectIndex.tableSortOn();
projectBaseProjectIndex.addBtnOn();
projectBaseProjectIndex.editBtnOn();
projectBaseProjectIndex.saveBtnOn();
projectBaseProjectIndex.deleteBtnOn();
projectBaseProjectIndex.selectBtnOn();