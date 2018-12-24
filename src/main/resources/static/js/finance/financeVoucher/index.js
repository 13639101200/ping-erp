//# sourceURL=/finance/financeVoucher/index.js
var financeFinanceVoucherIndex = {
	tableUrl : '/finance/financeVoucher/page',
	editUrl : '/finance/financeVoucher/edit',
	saveUrl : '/finance/financeVoucher/save',
	deleteUrl : '/finance/financeVoucher/delete',
	tableElem : 'finance-financeVoucher-index-table-1',
	addBtnElem : 'finance-financeVoucher-index-btn-1',
	editBtnElem : 'finance-financeVoucher-index-btn-2',
	deleteBtnElem : 'finance-financeVoucher-index-btn-3',
	selectBtnElem : 'finance-financeVoucher-index-btn-4',
	selectKeyElem : 'finance-financeVoucher-index-key-1',
	saveBtnElem : 'finance-financeVoucher-edit-btn-1',
	dataId : 'voucherId',
	editWindowArea : ['1273px', '368px'],
	tableCols : [ [ {
		fixed : 'left',
		type : 'radio'
	}, {
		fixed : 'left',
		title : '序号',
		type : 'numbers'
	}, {
		field : 'voucherWord.wordName',
		title : '凭证字',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatField(value, 'voucherWord', 'wordName');
		}
	}, {
		field : 'voucherNumber',
		title : '凭证号',
		align : 'center',
		sort : true,
	}, {
		field : 'voucherTime',
		title : '制单日期',
		align : 'center',
		sort : true,
		templet : function(value) {
			return common.formatDateC(value, 'voucherTime');
		}
	}, {
		field : 'voucherMoney',
		title : '凭证金额',
		align : 'right',
		templet : function(value) {
			var details = value.details;
			var total = 0;
			for (var i = 0; i < details.length; i++) {
				total += details[i].borrowerMoney;
			}
			return total;
		}
	}, {
		field : 'accessoryNumber',
		title : '附件数',
		align : 'center'
	}, {
		field : 'fillUser.userName',
		title : '制单人',
		align : 'center',
		templet : function(value) {
			return common.formatField(value, 'fillUser', 'userName');
		}
	}, {
		field : 'signatureUser.userName',
		title : '签字人',
		align : 'center',
		templet : function(value) {
			return common.formatField(value, 'signatureUser', 'userName');
		}
	}, {
		field : 'auditUser.userName',
		title : '审核人',
		align : 'center',
		templet : function(value) {
			return common.formatField(value, 'auditUser', 'userName');
		}
	}, {
		field : 'accountUser.userName',
		title : '记账人',
		align : 'center',
		templet : function(value) {
			return common.formatField(value, 'accountUser', 'userName');
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
			url : financeFinanceVoucherIndex.tableUrl,
			elem : '#' + financeFinanceVoucherIndex.tableElem,
			cols : financeFinanceVoucherIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(financeFinanceVoucherIndex.tableElem, {
			initSort : financeFinanceVoucherIndex.tableSort,
			where : {
				field : financeFinanceVoucherIndex.tableSort.field,
				order : financeFinanceVoucherIndex.tableSort.type,
				keyword : $('#' + financeFinanceVoucherIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'voucherNumber',
		type : 'asc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeFinanceVoucherIndex.tableElem + ')', function(tableSort) {
			financeFinanceVoucherIndex.tableSort = tableSort;
			financeFinanceVoucherIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeFinanceVoucherIndex.addBtnElem).on('click', function() {
			financeFinanceVoucherIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeFinanceVoucherIndex.editBtnElem).on('click', function() {
			financeFinanceVoucherIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeFinanceVoucherIndex.editUrl;
		var data = null;
		var content = null;

		if (type == 2) {
			var selectData = table.checkStatus(financeFinanceVoucherIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeFinanceVoucherIndex.dataId + '})');
			}
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		financeFinanceVoucherIndex.editWindowShow(type, content);
		laydate.render({
			elem : '#finance-financeVoucher-edit-input-1',
			type : 'date'
		});
		form.render('select', 'finance-financeVoucher-edit-input-2');
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeFinanceVoucherIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeFinanceVoucherIndex.editWindowArea
		});

		if (type == 1) {
			common.select('finance-financeVoucher-edit-subjectId-0', '/finance/archivedSubject/page', 'subjectId', [ 'subjectCode', 'subjectName' ], '', 'subjectCode', 'asc');
			common.select('finance-financeVoucher-edit-subjectId-1', '/finance/archivedSubject/page', 'subjectId', [ 'subjectCode', 'subjectName' ], '', 'subjectCode', 'asc');
			financeFinanceVoucherIndex.detailCount = [ 0, 1 ];
		} else if (type == 2) {
			var detailLength = $('#finance-financeVoucher-edit-param-1').val();
			var detailCount = new Array();
			for (var i = 0; i < detailLength; i++) {
				common.select('finance-financeVoucher-edit-subjectId-' + i, '/finance/archivedSubject/page', 'subjectId', [ 'subjectCode', 'subjectName' ], '', 'subjectCode', 'asc');
				detailCount.push(i);
			}
			financeFinanceVoucherIndex.detailCount = detailCount;
		}

		financeFinanceVoucherIndex.addDetailBtnOn();
	},
	saveBtnOn : function() {
		form.on('submit(' + financeFinanceVoucherIndex.saveBtnElem + ')', function(data) {
			var url = financeFinanceVoucherIndex.saveUrl;
			var field = data.field;
			var voucherId = Math.uuid().replace(/-/g, '');
			if (field.voucherId) {
				voucherId = field.voucherId;
			}

			var data = {};
			data.voucherId = voucherId;
			data.voucherWord = {};
			data.voucherWord.wordId = field['voucherWord.wordId'];
			data.voucherNumber = field.voucherNumber;
			data.voucherTime = field.voucherTime;
			data.accessoryNumber = field.accessoryNumber;
			data.details = new Array();

			var borrowTotal = 0;
			var lenderTotal = 0;
			for (var i = 0; i < financeFinanceVoucherIndex.detailCount.length; i++) {
				data.details[i] = {};
				data.details[i].detailId = Math.uuid().replace(/-/g, '');
				var detailId = eval('(field.detailId' + financeFinanceVoucherIndex.detailCount[i] + ')');
				if (detailId) {
					data.details[i].detailId = detailId;
				}
				data.details[i].voucher = {};
				data.details[i].voucher.voucherId = voucherId;
				data.details[i].detailAbstract = eval('(field.detailAbstract' + financeFinanceVoucherIndex.detailCount[i] + ')');
				data.details[i].subject = {};
				if (!$('#finance-financeVoucher-edit-subjectId-' + financeFinanceVoucherIndex.detailCount[i]).select2('data')[0]) {
					layer.msg('科目不能为空', {
						icon : 5
					});
					return;
				}
				data.details[i].subject.subjectId = $('#finance-financeVoucher-edit-subjectId-' + financeFinanceVoucherIndex.detailCount[i]).select2('data')[0].id;
				data.details[i].borrowerMoney = eval('(field.borrowerMoney' + financeFinanceVoucherIndex.detailCount[i] + '||0)');
				data.details[i].lenderMoney = eval('(field.lenderMoney' + financeFinanceVoucherIndex.detailCount[i] + '||0)');
				borrowTotal += Number(data.details[i].borrowerMoney == 0 ? 0 : data.details[i].borrowerMoney.replace(/,/g, ''));
				lenderTotal += Number(data.details[i].lenderMoney == 0 ? 0 : data.details[i].lenderMoney.replace(/,/g, ''));
				data.details[i].assistAmount = eval('(field.assistAmount' + financeFinanceVoucherIndex.detailCount[i] + '||0)');
			}

			if (borrowTotal != lenderTotal) {
				layer.msg('保存失败：借贷不平衡', {
					icon : 5
				});
				return;
			}

			$.ajax({
				contentType : 'application/json;charset=UTF-8',
				type : 'post',
				url : url,
				data : JSON.stringify(data),
				success : function(data) {
					if (data.code > 0) {
						financeFinanceVoucherIndex.tableReload();
						layer.close(financeFinanceVoucherIndex.editWindowIndex);
					}
					layer.msg(data.message);
				},
				dataType : 'json'
			});

			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeFinanceVoucherIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeFinanceVoucherIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeFinanceVoucherIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeFinanceVoucherIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeFinanceVoucherIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeFinanceVoucherIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeFinanceVoucherIndex.deleteWindowIndex);
	},
	selectBtnOn : function() {
		$('#' + financeFinanceVoucherIndex.selectBtnElem).on('click', function() {
			financeFinanceVoucherIndex.tableReload();
		});
	},
	detailCount : [ 0, 1 ],
	addDetailBtnOn : function() {
		$('#finance-financeVoucher-edit-btn-2').on('click', function() {
			var number = 0;
			var length = financeFinanceVoucherIndex.detailCount.length;
			var html = '';
			if (length > 0) {
				number = financeFinanceVoucherIndex.detailCount[length - 1] + 1;
			}

			html += '<div class="layui-form-item">';
			html += '<div class="layui-input-inline">';
			html += '<input name="detailAbstract' + number + '" type="text" class="layui-input" autocomplete="off" lay-verify="required|length20" />';
			html += '</div>';
			html += '<div class="layui-input-inline">';
			html += '<select id="finance-financeVoucher-edit-subjectId-' + number + '"></select>';
			html += '</div>';
			html += '<div class="layui-input-inline">';
			html += '<input name="borrowerMoney' + number + '" type="text" class="layui-input" autocomplete="off" lay-verify="doubleType" />';
			html += '</div>';
			html += '<div class="layui-input-inline">';
			html += '<input name="lenderMoney' + number + '" type="text" class="layui-input" autocomplete="off" lay-verify="doubleType" />';
			html += '</div>';
			html += '<div class="layui-input-inline">';
			html += '<input name="assistAmount' + number + '" type="text" class="layui-input" autocomplete="off" lay-verify="doubleType" />';
			html += '</div>';
			html += '<div class="layui-col-sm1">';
			html += '<button type="button" class="layui-btn layui-btn-danger" onclick="financeFinanceVoucherIndex.delDetailBtnOn(this,' + number + ');">';
			html += '<i class="layui-icon layui-icon-close"></i>';
			html += '</button>';
			html += '</div>';
			html += '</div>';

			$('#finance-financeVoucher-edit-div-1').append(html);
			common.select('finance-financeVoucher-edit-subjectId-' + number, '/finance/archivedSubject/page', 'subjectId', [ 'subjectCode', 'subjectName' ], '', 'subjectCode', 'asc');
			// $('#test').on('change', function(e) {});
			financeFinanceVoucherIndex.detailCount[length] = number;
		});
	},
	delDetailBtnOn : function(elem, number) {
		$(elem).parent().parent().remove();
		financeFinanceVoucherIndex.detailCount.forEach(function(currentValue, index, arr) {
			if (currentValue == number) {
				arr.splice(index, 1);
			}
		});
	},
	detailWindowArea : [ '964px', '466px' ],
	detailWindowIndex : null,
	detailBtnOn : function() {
		$('#finance-financeVoucher-index-btn-5').on('click', function() {
			var url = '/finance/financeVoucher/detail';
			var data = null;
			var content = null;

			var selectData = table.checkStatus(financeFinanceVoucherIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要查看的凭证');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持多凭证同时查看');
				return;
			} else {
				data = {
					id : selectData[0].voucherId
				};
				$.ajaxSettings.async = false;
				$.post(url, data, function(data) {
					content = data;
				}, 'html');
				$.ajaxSettings.async = true;

				financeFinanceVoucherIndex.detailWindowIndex = layer.open({
					type : 1,
					title : '凭证明细',
					content : content,
					area : financeFinanceVoucherIndex.detailWindowArea
				});
			}
		});
	},
	signatureBtnOn : function() {
		$('#finance-financeVoucher-index-btn-6').on('click', function() {
			var url = '/finance/financeVoucher/signature';
			var data = null;

			var selectData = table.checkStatus(financeFinanceVoucherIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要签字的凭证');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持多凭证同时签字');
				return;
			} else {
				data = {
					id : selectData[0].voucherId
				};
				$.post(url, data, function(res) {
					if (res.code > 0) {
						financeFinanceVoucherIndex.tableReload();
					}
					layer.msg(res.message);
				}, 'json');
			}
		});
	},
	auditBtnOn : function() {
		$('#finance-financeVoucher-index-btn-7').on('click', function() {
			var url = '/finance/financeVoucher/audit';
			var data = null;

			var selectData = table.checkStatus(financeFinanceVoucherIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要审核的凭证');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持多凭证同时审核');
				return;
			} else {
				data = {
					id : selectData[0].voucherId
				};
				$.post(url, data, function(res) {
					if (res.code > 0) {
						financeFinanceVoucherIndex.tableReload();
					}
					layer.msg(res.message);
				}, 'json');
			}
		});
	},
	accountBtnOn : function() {
		$('#finance-financeVoucher-index-btn-8').on('click', function() {
			var url = '/finance/financeVoucher/account';
			var data = null;

			var selectData = table.checkStatus(financeFinanceVoucherIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要记账的凭证');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持多凭证同时记账');
				return;
			} else {
				data = {
					id : selectData[0].voucherId
				};
				$.post(url, data, function(res) {
					if (res.code > 0) {
						financeFinanceVoucherIndex.tableReload();
					}
					layer.msg(res.message);
				}, 'json');
			}
		});
	}
};
financeFinanceVoucherIndex.tableInit();
financeFinanceVoucherIndex.tableSortOn();
financeFinanceVoucherIndex.addBtnOn();
financeFinanceVoucherIndex.editBtnOn();
financeFinanceVoucherIndex.saveBtnOn();
financeFinanceVoucherIndex.deleteBtnOn();
financeFinanceVoucherIndex.selectBtnOn();
financeFinanceVoucherIndex.detailBtnOn();
financeFinanceVoucherIndex.signatureBtnOn();
financeFinanceVoucherIndex.auditBtnOn();
financeFinanceVoucherIndex.accountBtnOn();