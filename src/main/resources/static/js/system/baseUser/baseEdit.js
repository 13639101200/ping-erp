//# sourceURL=/system/baseUser/baseEdit.js
var systemBaseUserBaseEdit = {
	saveBtnOn : function() {
		form.on('submit(system-baseUser-baseEdit-btn-1)', function(data) {
			var url = '/system/baseUser/baseSave';
			var data = data.field;
			$.post(url, data, function(data) {
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	}
};
systemBaseUserBaseEdit.saveBtnOn();