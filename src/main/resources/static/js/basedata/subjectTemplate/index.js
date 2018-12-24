//# sourceURL=/basedata/subjectTemplate/index.js
var basedataSubjectTemplateIndex = {
	tableUrl : '/basedata/subjectTemplate/page',
	editUrl : '/basedata/subjectTemplate/edit',
	saveUrl : '/basedata/subjectTemplate/save',
	deleteUrl : '/basedata/subjectTemplate/delete',
	tableElem : 'basedata-subjectTemplate-index-table-1',
	addBtnElem : 'basedata-subjectTemplate-index-btn-1',
	editBtnElem : 'basedata-subjectTemplate-index-btn-2',
	deleteBtnElem : 'basedata-subjectTemplate-index-btn-3',
	selectBtnElem : 'basedata-subjectTemplate-index-btn-4',
	selectKeyElem : 'basedata-subjectTemplate-index-key-1',
	saveBtnElem : 'basedata-subjectTemplate-edit-btn-1',
	dataId : 'templateId',
	editWindowArea : [ '329px', '163px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'templateName',
		title : '模板名称',
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
			url : basedataSubjectTemplateIndex.tableUrl,
			elem : '#' + basedataSubjectTemplateIndex.tableElem,
			cols : basedataSubjectTemplateIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(basedataSubjectTemplateIndex.tableElem, {
			initSort : basedataSubjectTemplateIndex.tableSort,
			where : {
				field : basedataSubjectTemplateIndex.tableSort.field,
				order : basedataSubjectTemplateIndex.tableSort.type,
				keyword : $('#' + basedataSubjectTemplateIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + basedataSubjectTemplateIndex.tableElem + ')', function(tableSort) {
			basedataSubjectTemplateIndex.tableSort = tableSort;
			basedataSubjectTemplateIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + basedataSubjectTemplateIndex.addBtnElem).on('click', function() {
			basedataSubjectTemplateIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + basedataSubjectTemplateIndex.editBtnElem).on('click', function() {
			basedataSubjectTemplateIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = basedataSubjectTemplateIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(basedataSubjectTemplateIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + basedataSubjectTemplateIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		basedataSubjectTemplateIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		basedataSubjectTemplateIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : basedataSubjectTemplateIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + basedataSubjectTemplateIndex.saveBtnElem + ')', function(data) {
			var url = basedataSubjectTemplateIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					basedataSubjectTemplateIndex.tableReload();
					layer.close(basedataSubjectTemplateIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + basedataSubjectTemplateIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(basedataSubjectTemplateIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				basedataSubjectTemplateIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					basedataSubjectTemplateIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : basedataSubjectTemplateIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					basedataSubjectTemplateIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(basedataSubjectTemplateIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + basedataSubjectTemplateIndex.selectBtnElem).on('click', function() {
			basedataSubjectTemplateIndex.tableReload();
		});
	},
	detailBtnOn : function() {
		$('#basedata-subjectTemplate-index-btn-5').on('click', function() {
			var url = '/basedata/templateSubject/index?templateId=';
			var id = '0B7083C906424CF39CBB3274A316BA00';
			var title = '科目模板-';

			var selectData = table.checkStatus(basedataSubjectTemplateIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要查看的模板');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持多模板同时查看');
				return;
			} else {
				url += selectData[0].templateId;
				title += selectData[0].templateName;
			}

			if (commonLoginHome.tabIdSet.has(id)) {
				commonLoginHome.tabDel(id);
			}
			commonLoginHome.tabAdd(url, id, title);
		});
	}
};
basedataSubjectTemplateIndex.tableInit();
basedataSubjectTemplateIndex.tableSortOn();
basedataSubjectTemplateIndex.addBtnOn();
basedataSubjectTemplateIndex.editBtnOn();
basedataSubjectTemplateIndex.saveBtnOn();
basedataSubjectTemplateIndex.deleteBtnOn();
basedataSubjectTemplateIndex.selectBtnOn();
basedataSubjectTemplateIndex.detailBtnOn();