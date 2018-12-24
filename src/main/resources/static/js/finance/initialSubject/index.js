//# sourceURL=/finance/initialSubject/index.js
var financeInitialSubjectIndex = {
	tableUrl : '/finance/initialSubject/page',
	editUrl : '/finance/initialSubject/edit',
	saveUrl : '/finance/initialSubject/save',
	deleteUrl : '/finance/initialSubject/delete',
	tableElem : 'finance-initialSubject-index-table-1',
	addBtnElem : 'finance-initialSubject-index-btn-1',
	editBtnElem : 'finance-initialSubject-index-btn-2',
	deleteBtnElem : 'finance-initialSubject-index-btn-3',
	selectBtnElem : 'finance-initialSubject-index-btn-4',
	selectKeyElem : 'finance-initialSubject-index-key-1',
	saveBtnElem : 'finance-initialSubject-edit-btn-1',
	dataId : 'subjectId',
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
			field : 'periodId',
			title : '期间ID',
			align : 'center',
			sort : true,
		},
		{
			field : 'subjectCode',
			title : '科目代码',
			align : 'center',
			sort : true,
		},
		{
			field : 'subjectName',
			title : '科目名称',
			align : 'center',
			sort : true,
		},
		{
			field : 'subjectType',
			title : '科目类别',
			align : 'center',
			sort : true,
		},
		{
			field : 'subjectDirection',
			title : '科目方向',
			align : 'center',
			sort : true,
		},
		{
			field : 'subjectMoney',
			title : '科目金额',
			align : 'center',
			sort : true,
		},
		{
			field : 'assistUnit',
			title : '辅计单位',
			align : 'center',
			sort : true,
		},
		{
			field : 'assistAmount',
			title : '辅计数量',
			align : 'center',
			sort : true,
		},
		{
			field : 'assistAccount',
			title : '辅助核算：1、部门辅助；2、人员辅助；3、客户辅助；4、供应商辅助；5、项目辅助；',
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
			url : financeInitialSubjectIndex.tableUrl,
			elem : '#' + financeInitialSubjectIndex.tableElem,
			cols : financeInitialSubjectIndex.tableCols
		});
	},
	tableReload : function() {
		table.reload(financeInitialSubjectIndex.tableElem, {
			initSort : financeInitialSubjectIndex.tableSort,
			where : {
				field : financeInitialSubjectIndex.tableSort.field,
				order : financeInitialSubjectIndex.tableSort.type,
				keyword : $('#' + financeInitialSubjectIndex.selectKeyElem).val()
			}
		});
	},
	tableSort : {
		field : 'createTime',
		type : 'desc'
	},
	tableSortOn : function() {
		table.on('sort(' + financeInitialSubjectIndex.tableElem + ')', function(tableSort) {
			financeInitialSubjectIndex.tableSort = tableSort;
			financeInitialSubjectIndex.tableReload();
		});
	},
	addBtnOn : function() {
		$('#' + financeInitialSubjectIndex.addBtnElem).on('click', function() {
			financeInitialSubjectIndex.editData(1);
		});
	},
	editBtnOn : function() {
		$('#' + financeInitialSubjectIndex.editBtnElem).on('click', function() {
			financeInitialSubjectIndex.editData(2);
		});
	},
	editData : function(type) {
		var url = financeInitialSubjectIndex.editUrl;
		var data = null;
		var content = null;
		
		if (type == 2) {
			var selectData = table.checkStatus(financeInitialSubjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要编辑的一条数据');
				return;
			} else if (selectData.length > 1) {
				layer.msg('不支持对多条数据同时编辑');
				return;
			} else {
				data = eval('({id:selectData[0].' + financeInitialSubjectIndex.dataId + '})');
			}
		}
		
		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;
		
		financeInitialSubjectIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 2) {
			title = '编辑';
		}
		financeInitialSubjectIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : financeInitialSubjectIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(' + financeInitialSubjectIndex.saveBtnElem + ')', function(data) {
			var url = financeInitialSubjectIndex.saveUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					financeInitialSubjectIndex.tableReload();
					layer.close(financeInitialSubjectIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#' + financeInitialSubjectIndex.deleteBtnElem).on('click', function() {
			var selectData = table.checkStatus(financeInitialSubjectIndex.tableElem).data;
			if (selectData.length < 1) {
				layer.msg('请选择需要删除的数据');
			} else {
				financeInitialSubjectIndex.deleteWindowIndex = layer.confirm('确定要删除这些数据？', {
					icon : 3,
					title : '提示'
				}, function() {
					financeInitialSubjectIndex.deleteData(selectData);
				});
			}
		});
	},
	deleteData : function(data) {
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : financeInitialSubjectIndex.deleteUrl,
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					financeInitialSubjectIndex.tableReload();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(financeInitialSubjectIndex.deleteWindowIndex);
	},
	selectBtnOn : function(){
		$('#' + financeInitialSubjectIndex.selectBtnElem).on('click', function() {
			financeInitialSubjectIndex.tableReload();
		});
	}
};
financeInitialSubjectIndex.tableInit();
financeInitialSubjectIndex.tableSortOn();
financeInitialSubjectIndex.addBtnOn();
financeInitialSubjectIndex.editBtnOn();
financeInitialSubjectIndex.saveBtnOn();
financeInitialSubjectIndex.deleteBtnOn();
financeInitialSubjectIndex.selectBtnOn();