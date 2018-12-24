//# sourceURL=/system/baseUser/passwordEdit.js
var systemBaseUserPasswordEdit = {
	saveBtnOn : function() {
		form.on('submit(system-baseUser-passwordEdit-btn-1)', function(data) {
			var url = '/system/baseUser/passwordSave';
			var data = data.field;
			$.post(url, data, function(data) {
				layer.msg(data.message);
			}, 'json');
			return false;
		});
	}
};
systemBaseUserPasswordEdit.saveBtnOn();