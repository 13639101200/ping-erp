//# sourceURL=/basedata/templateSubject/index.js
var basedataTemplateSubjectIndex = {
	tableUrl : '/basedata/templateSubject/page?templateId=' + $('#basedata-templateSubject-index-param-1').val(),
	editUrl : '/basedata/templateSubject/edit',
	saveUrl : '/basedata/templateSubject/save',
	deleteUrl : '/basedata/templateSubject/delete',
	tableElem : 'basedata-templateSubject-index-table-1',
	addBtnElem : 'basedata-templateSubject-index-btn-1',
	editBtnElem : 'basedata-templateSubject-index-btn-2',
	deleteBtnElem : 'basedata-templateSubject-index-btn-3',
	selectBtnElem : 'basedata-templateSubject-index-btn-4',
	selectKeyElem : 'basedata-templateSubject-index-key-1',
	saveBtnElem : 'basedata-templateSubject-edit-btn-1',
	dataId : 'subjectId',
	editWindowArea : [ '329px', '322px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'checkbox'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'subjectCode',
		title : '科目代码',
		align : 'left',
		sort : true,
	}, {
		field : 'subjectName',
		title : '科目名称',
		align : 'center'
	}, {
		field : 'subjectType.codeValue',
		title : '科目类别',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatField(value, 'subjectType', 'codeValue');
		}
	}, {
		field : 'subjectDirection.codeValue',
		title : '科目方向',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatField(value, 'subjectDirection', 'codeValue');
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
			url : basedataTemplateSubjectIndex.tableUrl,
			elem : '#' + basedataTemplateSubjectIndex.tableElem,
			cols : basedataTemplateSubjectIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(basedataTemplateSubjectIndex.tableElem, {
			initSort : basedataTemplateSubjectIndex.tableSort,
			where : {
				field : basedataTemplateSubjectIndex.tableSort.field,
				order : basedataTemplateSubjectIndex.tableSort.type,
				keyword : $('#' + basedataTemplateSubjectIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'subjectCode',
		type : 'asc'
	},
	tableSortOn : function() {
		table.on('sort(' + basedataTemplateSubjectIndex.tableElem + ')', function(tableSort) {
			basedataTemplateSubjectIndex.tableSort = tableSort;
			basedataTemplateSubjectIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + basedataTemplateSubjectIndex.addBtnElem).on('click', function() {
			basedataTemplateSubjectIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + basedataTemplateSubjectIndex.editBtnElem).on('click', function() {
			basedataTemplateSubjectIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = basedataTemplateSubjectIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(basedataTemplateSubjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + basedataTemplateSubjectIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		basedataTemplateSubjectIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		basedataTemplateSubjectIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : basedataTemplateSubjectIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + basedataTemplateSubjectIndex.saveBtnElem + ')', function(data) {
			var url = basedataTemplateSubjectIndex.saveUrl;
			var data = data.field;
			data['template.templateId'] = $('#basedata-templateSubject-index-param-1').val();

			$.post(url, data, function(data) {
				if (data.code > 0) {
					basedataTemplateSubjectIndex.tableReload();
					layer.close(basedataTemplateSubjectIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + basedataTemplateSubjectIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(basedataTemplateSubjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				basedataTemplateSubjectIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					basedataTemplateSubjectIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : basedataTemplateSubjectIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					basedataTemplateSubjectIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(basedataTemplateSubjectIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + basedataTemplateSubjectIndex.selectBtnElem).on('click', function() {
			basedataTemplateSubjectIndex.tableReload();
		});
	}
};
basedataTemplateSubjectIndex.tableInit();
basedataTemplateSubjectIndex.tableSortOn();
basedataTemplateSubjectIndex.addBtnOn();
basedataTemplateSubjectIndex.editBtnOn();
basedataTemplateSubjectIndex.saveBtnOn();
basedataTemplateSubjectIndex.deleteBtnOn();
basedataTemplateSubjectIndex.selectBtnOn();