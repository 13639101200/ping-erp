//# sourceURL=/finance/archivedSubject/initIndex.js
var financeArchivedSubjectInitIndex = {
	tableUrl : '/finance/archivedSubject/initPage',
	editUrl : '/finance/archivedSubject/initEdit',
	saveUrl : '/finance/archivedSubject/initSave',
	deleteUrl : '/finance/archivedSubject/initDelete',
	tableElem : 'finance-archivedSubject-initIndex-table-1',
	addBtnElem : 'finance-archivedSubject-initIndex-btn-1',
	editBtnElem : 'finance-archivedSubject-initIndex-btn-2',
	deleteBtnElem : 'finance-archivedSubject-initIndex-btn-3',
	selectBtnElem : 'finance-archivedSubject-initIndex-btn-4',
	selectKeyElem : 'finance-archivedSubject-initIndex-key-1',
	saveBtnElem : 'finance-archivedSubject-initEdit-btn-1',
	dataId : 'subjectId',
	editWindowArea : [ '638px', '459px' ],
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
		if (count > 0) {
			$('#finance-archivedSubject-initIndex-btn-5').attr('disabled', true);
			$('#finance-archivedSubject-initIndex-btn-5').attr('class', 'layui-btn layui-btn-disabled ping-erp-btn-group-first-btn-disabled');
		} else {
			$('#finance-archivedSubject-initIndex-btn-5').attr('disabled', false);
			$('#finance-archivedSubject-initIndex-btn-5').attr('class', 'layui-btn');
		}

		var url = '/finance/archivedSubject/isStart';
		$.post(url, function(data) {
			if (data.code >= 0) {
				$('#finance-archivedSubject-initIndex-btn-1').attr('disabled', true);
				$('#finance-archivedSubject-initIndex-btn-1').attr('class', 'layui-btn layui-btn-disabled ping-erp-btn-group-first-btn-disabled');
				$('#finance-archivedSubject-initIndex-btn-2').attr('disabled', true);
				$('#finance-archivedSubject-initIndex-btn-2').attr('class', 'layui-btn layui-btn-disabled');
				$('#finance-archivedSubject-initIndex-btn-3').attr('disabled', true);
				$('#finance-archivedSubject-initIndex-btn-3').attr('class', 'layui-btn layui-btn-disabled');
				$('#finance-archivedSubject-initIndex-btn-5').attr('disabled', true);
				$('#finance-archivedSubject-initIndex-btn-5').attr('class', 'layui-btn layui-btn-disabled ping-erp-btn-group-first-btn-disabled');
				$('#finance-archivedSubject-initIndex-btn-6').attr('disabled', true);
				$('#finance-archivedSubject-initIndex-btn-6').attr('class', 'layui-btn layui-btn-disabled');
			} else {
				$('#finance-archivedSubject-initIndex-btn-1').attr('disabled', false);
				$('#finance-archivedSubject-initIndex-btn-1').attr('class', 'layui-btn');
				$('#finance-archivedSubject-initIndex-btn-2').attr('disabled', false);
				$('#finance-archivedSubject-initIndex-btn-2').attr('class', 'layui-btn');
				$('#finance-archivedSubject-initIndex-btn-3').attr('disabled', false);
				$('#finance-archivedSubject-initIndex-btn-3').attr('class', 'layui-btn');
				$('#finance-archivedSubject-initIndex-btn-6').attr('disabled', false);
				$('#finance-archivedSubject-initIndex-btn-6').attr('class', 'layui-btn');
			}
		}, 'json');
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
			url : financeArchivedSubjectInitIndex.tableUrl,
			elem : '#' + financeArchivedSubjectInitIndex.tableElem,
			cols : financeArchivedSubjectInitIndex.tableCols,
			done : financeArchivedSubjectInitIndex.tableDone
		});
	},
	tableReload : function() {
		table.reload(financeArchivedSubjectInitIndex.tableElem, {
			initSort : financeArchivedSubjectInitIndex.tableSort,
			where : {
				field : financeArchivedSubjectInitIndex.tableSort.field,
				order : financeArchivedSubjectInitIndex.tableSort.type,
				keyword : $('#' + financeArchivedSubjectInitIndex.selectKeyElem).val()
			},
			done : financeArchivedSubjectInitIndex.tableDone
		});
	},
	tableSort : {
		field : 'subjectCode',
		type : 'asc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeArchivedSubjectInitIndex.tableElem + ')', function(tableSort) {
			financeArchivedSubjectInitIndex.tableSort = tableSort;
			financeArchivedSubjectInitIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeArchivedSubjectInitIndex.addBtnElem).on('click', function() {
			financeArchivedSubjectInitIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeArchivedSubjectInitIndex.editBtnElem).on('click', function() {
			financeArchivedSubjectInitIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeArchivedSubjectInitIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(financeArchivedSubjectInitIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeArchivedSubjectInitIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		financeArchivedSubjectInitIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeArchivedSubjectInitIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeArchivedSubjectInitIndex.editWindowArea
		});

		var assistValue = $('#finance-archivedSubject-initEdit-param-1').val();
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
			form.val("finance-archivedSubject-initEdit-form-1", assistAccount);
		}
	},
	saveBtnOn : function() {
		form.on('submit(' + financeArchivedSubjectInitIndex.saveBtnElem + ')', function(data) {
			var url = financeArchivedSubjectInitIndex.saveUrl;
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
					financeArchivedSubjectInitIndex.tableReload();
					layer.close(financeArchivedSubjectInitIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeArchivedSubjectInitIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeArchivedSubjectInitIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeArchivedSubjectInitIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeArchivedSubjectInitIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeArchivedSubjectInitIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeArchivedSubjectInitIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeArchivedSubjectInitIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + financeArchivedSubjectInitIndex.selectBtnElem).on('click', function() {
			financeArchivedSubjectInitIndex.tableReload();
		});
	},
	importWindowIndex : null,
	importWindowArea : [ '329px', '163px' ],
	importEditBtnOn : function() {
		$('#finance-archivedSubject-initIndex-btn-5').on('click', function() {
			var url = '/finance/archivedSubject/importEdit';
			var content = null;

			$.ajaxSettings.async = false;
			$.post(url, function(data) {
				content = data;
			}, 'html');
			$.ajaxSettings.async = true;

			var title = '导入科目模板';
			financeArchivedSubjectInitIndex.importWindowIndex = layer.open({
				type : 1,
				title : title,
				content : content,
				area : financeArchivedSubjectInitIndex.importWindowArea
			});
			form.render();
		});
	},
	importSaveBtnOn : function() {
		form.on('submit(finance-archivedSubject-importEdit-btn-1)', function(data) {
			var url = '/finance/archivedSubject/importSave';
			var data = data.field;
			var index = layer.load(2);
			$.post(url, data, function(data) {
				if (data.code > 0) {
					financeArchivedSubjectInitIndex.tableReload();
					layer.close(financeArchivedSubjectInitIndex.importWindowIndex);
				}
				layer.close(index);
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	startBtnOn : function() {
		$('#finance-archivedSubject-initIndex-btn-6').on('click', function() {
			var url = '/finance/archivedSubject/startSubject';
			var index = layer.load(2);
			$.post(url, function(data) {
				if (data.code > 0) {
					financeArchivedSubjectInitIndex.tableReload();
				}
				layer.close(index);
				layer.msg(data.message);
			}, 'json');
		});
	}
};
financeArchivedSubjectInitIndex.tableInit();
financeArchivedSubjectInitIndex.tableSortOn();
financeArchivedSubjectInitIndex.addBtnOn();
financeArchivedSubjectInitIndex.editBtnOn();
financeArchivedSubjectInitIndex.saveBtnOn();
financeArchivedSubjectInitIndex.deleteBtnOn();
financeArchivedSubjectInitIndex.selectBtnOn();
financeArchivedSubjectInitIndex.importEditBtnOn();
financeArchivedSubjectInitIndex.importSaveBtnOn();
financeArchivedSubjectInitIndex.startBtnOn();