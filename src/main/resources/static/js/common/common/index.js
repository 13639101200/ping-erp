//# sourceURL=/common/login/index.js
var commonLoginIndex = {
	loginUrl : '/login',
	loginBtnElem : 'common-login-index-btn-1',
	loginBtnOn : function() {
		form.on('submit(' + commonLoginIndex.loginBtnElem + ')', function(data) {
			var url = commonLoginIndex.loginUrl;
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
	accountInputElem : 'common-login-index-input-1',
	accountInputFocus : function() {
		$('#' + commonLoginIndex.accountInputElem).focus();
	}
};
commonLoginIndex.loginBtnOn();
commonLoginIndex.accountInputFocus();