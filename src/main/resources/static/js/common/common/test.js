commonCommonTest = {
	tableInit : function() {
		$('#mb').menubutton({
			iconCls : 'icon-edit',
			menu : '#mm'
		});
		$.messager.alert('dd');
		setTimeout(function() {
			$('#dg').datagrid({
				columns : [ [ {
					field : 'code',
					title : 'Code',
					width : 100
				}, {
					field : 'name',
					title : 'Name',
					width : 100
				}, {
					field : 'price',
					title : 'Price',
					width : 100,
					align : 'right'
				} ] ]
			});
		});

	}
};
commonCommonTest.tableInit();