//# sourceURL=/system/baseMenu/treeIndex.js
var systemBaseMenuTreeIndex = {
	menuTreeObj : null,
	menuTreeElem : 'system-baseMenu-treeIndex-tree-1',
	menuTreeSetting : {
		async : {
			enable : true,
			autoParam : [ 'menuId' ],
			url : 'system/baseMenu/children',
			dataFilter : function(treeId, parentNode, responseData) {
				responseData.forEach(function(currentValue, index, arr) {
					currentValue.isParent = true;
				});
				return responseData;
			}
		},
		data : {
			key : {
				name : 'menuName'
			}
		},
		simpleData : {
			enable : true,
			idKey : 'menuId',
			pIdKey : 'menuPid'
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				systemBaseMenuTreeIndex.menuInfo = treeNode
				systemBaseMenuTreeIndex.menuInfoShow(treeNode);
			}
		}
	},
	menuTreeInit : function() {
		systemBaseMenuTreeIndex.menuTreeObj = $.fn.zTree.init($('#' + systemBaseMenuTreeIndex.menuTreeElem), systemBaseMenuTreeIndex.menuTreeSetting);
	},
	menuTreeRefresh : function() {
		systemBaseMenuTreeIndex.menuInfo = null;
		systemBaseMenuTreeIndex.menuTreeObj.reAsyncChildNodes(null, 'refresh');
	},
	menuInfo : null,
	menuInfoShow : function(menuInfo) {
		form.val('system-baseMenu-treeIndex-form-1', {
			menuId : menuInfo.menuId,
			menuPid : menuInfo.menuPid,
			menuName : menuInfo.menuName,
			menuHref : menuInfo.menuHref,
			menuType : menuInfo.menuType.codeValue,
			menuTarget : menuInfo.menuTarget.codeValue,
			orderNumber : menuInfo.orderNumber,
		});
	},
	menuBtn1On : function() {
		$('#system-baseMenu-treeIndex-btn-1').on('click', function() {
			systemBaseMenuTreeIndex.editData(1);
		});
	},
	menuBtn2On : function() {
		$('#system-baseMenu-treeIndex-btn-2').on('click', function() {
			systemBaseMenuTreeIndex.editData(2);
		});
	},
	menuBtn3On : function() {
		$('#system-baseMenu-treeIndex-btn-3').on('click', function() {
			systemBaseMenuTreeIndex.editData(3);
		});
	},
	editData : function(type) {
		if (!systemBaseMenuTreeIndex.menuInfo) {
			layer.msg('请先选择菜单');
			return;
		}

		var url = '/system/baseMenu/treeEdit';
		var data = {};
		var content = null;

		if (type == 1) {
			data.pid = systemBaseMenuTreeIndex.menuInfo.menuPid;
		} else if (type == 2) {
			data.pid = systemBaseMenuTreeIndex.menuInfo.menuId;
		} else if (type == 3) {
			data.id = systemBaseMenuTreeIndex.menuInfo.menuId;
		} else {
			return;
		}

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		systemBaseMenuTreeIndex.editWindowShow(type, content);
		form.render();
	},
	editWindowIndex : null,
	editWindowArea : [ '638px', '322px' ],
	editWindowShow : function(type, content) {
		var title = '增加';
		if (type == 3) {
			title = '编辑';
		}
		systemBaseMenuTreeIndex.editWindowIndex = layer.open({
			type : 1,
			title : title,
			content : content,
			area : systemBaseMenuTreeIndex.editWindowArea
		});
	},
	saveBtnOn : function() {
		form.on('submit(system-baseMenu-treeEdit-btn-1)', function(data) {
			var url = '/system/baseMenu/save';
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					systemBaseMenuTreeIndex.menuTreeRefresh();
					layer.close(systemBaseMenuTreeIndex.editWindowIndex);
				}
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	},
	deleteWindowIndex : null,
	deleteBtnOn : function() {
		$('#system-baseMenu-treeIndex-btn-4').on('click', function() {
			if (!systemBaseMenuTreeIndex.menuInfo) {
				layer.msg('请先选择菜单');
				return;
			}
			systemBaseMenuTreeIndex.deleteWindowIndex = layer.confirm('确定要删除菜单：' + systemBaseMenuTreeIndex.menuInfo.menuName + '？', {
				icon : 3,
				title : '提示'
			}, function() {
				systemBaseMenuTreeIndex.deleteData(systemBaseMenuTreeIndex.menuInfo);
			});
		});
	},
	deleteData : function(data) {
		var data = [ data ];
		$.ajax({
			contentType : 'application/json;charset=UTF-8',
			type : 'post',
			url : '/system/baseMenu/delete',
			data : JSON.stringify(data),
			success : function(data) {
				if (data.code > 0) {
					systemBaseMenuTreeIndex.menuTreeRefresh();
				}
				layer.msg(data.message);
			},
			dataType : 'json'
		});
		layer.close(systemBaseMenuTreeIndex.deleteWindowIndex);
	}
};
systemBaseMenuTreeIndex.menuTreeInit();
systemBaseMenuTreeIndex.menuBtn1On();
systemBaseMenuTreeIndex.menuBtn2On();
systemBaseMenuTreeIndex.menuBtn3On();
systemBaseMenuTreeIndex.saveBtnOn();
systemBaseMenuTreeIndex.deleteBtnOn();