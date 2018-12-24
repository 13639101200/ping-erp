//# sourceURL=/common/login/page.js
var commonLoginPage = {
	urlInputElem : 'common-login-page-url-1',
	windowDivElem : 'common-login-page-div-1',
	divReload : function() {
		var url = $('#' + commonLoginPage.urlInputElem).val();
		var data = null;
		var content = null;

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		$('#' + commonLoginPage.windowDivElem).html(content);
	}
};
commonLoginPage.divReload();