//# sourceURL=/common/login/default.js
var commonLoginDefault = {
	loginUrl : '/login',
	loginBtnElem : 'common-default-index-btn-1',
	loginBtnOn : function() {
		form.on('submit(' + commonLoginDefault.loginBtnElem + ')', function(data) {
			var url = commonLoginDefault.loginUrl;
			var data = data.field;
			$.post(url, data, function(data) {
				if (data.code > 0) {
					layer.msg(data.message);
					window.location.href = data.url;
				} else {
					layer.msg(data.message);
				}
			}, 'json');
			return false;
		});
	},
	accountInputElem : 'common-default-index-input-1',
	accountInputFocus : function() {
		$('#' + commonLoginDefault.accountInputElem).focus();
	}
};
commonLoginDefault.loginBtnOn();
commonLoginDefault.accountInputFocus();