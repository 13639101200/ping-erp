//# sourceURL=/finance/archivedSubject/index.js
var financeArchivedSubjectIndex = {
	tableUrl : '/finance/archivedSubject/page',
	editUrl : '/finance/archivedSubject/edit',
	saveUrl : '/finance/archivedSubject/save',
	deleteUrl : '/finance/archivedSubject/delete',
	tableElem : 'finance-archivedSubject-index-table-1',
	addBtnElem : 'finance-archivedSubject-index-btn-1',
	editBtnElem : 'finance-archivedSubject-index-btn-2',
	deleteBtnElem : 'finance-archivedSubject-index-btn-3',
	selectBtnElem : 'finance-archivedSubject-index-btn-4',
	selectKeyElem : 'finance-archivedSubject-index-key-1',
	saveBtnElem : 'finance-archivedSubject-edit-btn-1',
	dataId : 'subjectId',
	editWindowArea : [ '638px', '406px' ],
	tableCols : [ [ {
		fixed : 'left',
		type : 'radio'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'subjectCode',
		title : '科目代码',
		align : 'left',
		sort : true
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
	}, {
		field : 'subjectMoney',
		title : '科目金额',
		align : 'right',
		templet : function(value) {
			if (value.subjectMoney == 0) {
				return '';
			} else {
				return value.subjectMoney;
			}
		}
	}, {
		field : 'assistUnit.unitName',
		title : '辅计单位',
		align : 'center',
		templet : function(value) {
			return common.formatField(value, 'assistUnit', 'unitName');
		}
	}, {
		field : 'assistAmount',
		title : '辅计数量',
		align : 'right',
		templet : function(value) {
			if (value.assistAmount == 0) {
				return '';
			} else {
				return value.assistAmount;
			}
		}
	} ] ],
	tableDone : function(res, curr, count) {

	},
	tableInit : function() {
		table.render({
			request : {
				limitName : 'size'
			},
			height : 'full-212',
			page : true,
			limit : 50,
			limits : [ 50, 100, 150, 200, 250, 300, 350, 400, 450, 500 ],
			autoSort : false,
			url : financeArchivedSubjectIndex.tableUrl,
			elem : '#' + financeArchivedSubjectIndex.tableElem,
			cols : financeArchivedSubjectIndex.tableCols,
			done : financeArchivedSubjectIndex.tableDone
		});
	},
	tableReload : function() {
		table.reload(financeArchivedSubjectIndex.tableElem, {
			initSort : financeArchivedSubjectIndex.tableSort,
			where : {
				field : financeArchivedSubjectIndex.tableSort.field,
				order : financeArchivedSubjectIndex.tableSort.type,
				keyword : $('#' + financeArchivedSubjectIndex.selectKeyElem).val()
			},
			done : financeArchivedSubjectIndex.tableDone
		});
	},
	tableSort : {
		field : 'subjectCode',
		type : 'asc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeArchivedSubjectIndex.tableElem + ')', function(tableSort) {
			financeArchivedSubjectIndex.tableSort = tableSort;
			financeArchivedSubjectIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeArchivedSubjectIndex.addBtnElem).on('click', function() {
			financeArchivedSubjectIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeArchivedSubjectIndex.editBtnElem).on('click', function() {
			financeArchivedSubjectIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeArchivedSubjectIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(financeArchivedSubjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeArchivedSubjectIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		financeArchivedSubjectIndex.editWindowShow(type, content);
		if (type == 2) {
			$('#finance-archivedSubject-edit-input-1').attr('disabled', true);
			if ($('#finance-archivedSubject-edit-input-2').val() == "null") {
				$('#finance-archivedSubject-edit-input-2').attr('disabled', false);
			} else {
				$('#finance-archivedSubject-edit-input-2').attr('disabled', true);
			}
		} else {
			$('#finance-archivedSubject-edit-input-1').attr('disabled', false);
			$('#finance-archivedSubject-edit-input-2').attr('disabled', false);
		}
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeArchivedSubjectIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeArchivedSubjectIndex.editWindowArea
		});

		var assistValue = $('#finance-archivedSubject-edit-param-1').val();
		if (assistValue) {
			var assistAccount = {};
			if (assistValue.substring(0, 1) == '1') {
				assistAccount.assistDepartment = true;
			}
			if (assistValue.substring(1, 2) == '1') {
				assistAccount.assistClerk = true;
			}
			if (assistValue.substring(2, 3) == '1') {
				assistAccount.assistCustomer = true;
			}
			if (assistValue.substring(3, 4) == '1') {
				assistAccount.assistSupplier = true;
			}
			if (assistValue.substring(4, 5) == '1') {
				assistAccount.assistProject = true;
			}
			form.val("finance-archivedSubject-edit-form-1", assistAccount);
		}
	},
	saveBtnOn : function() {
		form.on('submit(' + financeArchivedSubjectIndex.saveBtnElem + ')', function(data) {
			var url = financeArchivedSubjectIndex.saveUrl;
			var data = data.field;

			if (data['assistUnit.unitId'] == "null") {
				delete data['assistUnit.unitId'];
			}
			data.assistAccount = '';
			if (data.assistDepartment == "on") {
				data.assistAccount += 1;
			} else {
				data.assistAccount += 0;
			}
			if (data.assistClerk == "on") {
				data.assistAccount += 1;
			} else {
				data.assistAccount += 0;
			}
			if (data.assistCustomer == "on") {
				data.assistAccount += 1;
			} else {
				data.assistAccount += 0;
			}
			if (data.assistSupplier == "on") {
				data.assistAccount += 1;
			} else {
				data.assistAccount += 0;
			}
			if (data.assistProject == "on") {
				data.assistAccount += 1;
			} else {
				data.assistAccount += 0;
			}
			delete data.assistDepartment;
			delete data.assistClerk;
			delete data.assistCustomer;
			delete data.assistSupplier;
			delete data.assistProject;

			$.post(url, data, function(data) {
				if (data.code > 0) {
					financeArchivedSubjectIndex.tableReload();
					layer.close(financeArchivedSubjectIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeArchivedSubjectIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeArchivedSubjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeArchivedSubjectIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeArchivedSubjectIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		if (data[0].subjectMoney) {
			layer.msg('删除失败：数据存在关联');
			return;
		}

		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeArchivedSubjectIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeArchivedSubjectIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeArchivedSubjectIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + financeArchivedSubjectIndex.selectBtnElem).on('click', function() {
			financeArchivedSubjectIndex.tableReload();
		});
	},
	archivedBtnOn : function() {
		$('#finance-archivedSubject-index-btn-5').on('click', function() {
			var url = '/finance/archivedSubject/archivedSubject';
			var index = layer.load(2);
			$.post(url, function(data) {
				if (data.code > 0) {
					var date = common.formatDateC(data, 'date');
					$('#finance-archivedSubject-index-btn-6').html('会计期间：' + date + '&nbsp;至&nbsp;结账时间');
					financeArchivedSubjectIndex.tableReload();
				}
				layer.close(index);
				layer.msg(data.message);
			}, 'json');
		});
	}
};
financeArchivedSubjectIndex.tableInit();
financeArchivedSubjectIndex.tableSortOn();
financeArchivedSubjectIndex.addBtnOn();
financeArchivedSubjectIndex.editBtnOn();
financeArchivedSubjectIndex.saveBtnOn();
financeArchivedSubjectIndex.deleteBtnOn();
financeArchivedSubjectIndex.selectBtnOn();
financeArchivedSubjectIndex.archivedBtnOn();