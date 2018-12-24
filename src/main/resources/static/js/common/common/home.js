//# sourceURL=/common/login/home.js
var commonLoginHome = {
	nav1Elem : 'common-login-home-nav-1',
	nav2Elem : 'common-login-home-nav-2',
	nav3Elem : 'common-login-home-nav-3',
	tabElem : 'common-login-home-tab-1',
	logoutUrl : '/logout',
	tabIdSet : new Set(),
	nav1On : function() {
		element.on('nav(' + commonLoginHome.nav1Elem + ')', function(elem) {
		});
	},
	nav2On : function() {
		element.on('nav(' + commonLoginHome.nav2Elem + ')', function(elem) {
			var title = $(elem).text();
			var url = $(elem).data("menu-url");
			if (url) {
				if (url == 'logout') {
					$.post(commonLoginHome.logoutUrl, function(data) {
						if (data.code > 0) {
							window.location.href = data.url;
						}
						layer.msg(data.message);
					}, 'json');
				} else {
					commonLoginHome.tabAdd(url, title, title);
				}
			}
		});
	},
	nav3On : function() {
		element.on('nav(' + commonLoginHome.nav3Elem + ')', function(elem) {
			var url = $(elem).data("menu-url");
			var target = $(elem).data("menu-target");
			var id = $(elem).data("menu-id");
			var title = $(elem).text();
			
			if (url) {
				if(target == '43691F6ADD38458CA7E765D0EBEC32A7'){
					commonLoginHome.tabAdd(url, id, title);
				}else if(target == '4C8DA09CB29048C48BFD151E53C11E20'){
					window.location.href = '/page?url=' + url;
				}else if(target == '7EC86E05ABD346ACA4A5C3204DD590B5'){
					window.open('/page?url=' + url);
				}
			}
		});
	},
	tabDeleteOn : function() {
		element.on('tabDelete(' + commonLoginHome.tabElem + ')', function(data) {
			var id = Array.from(commonLoginHome.tabIdSet)[data.index];
			commonLoginHome.tabIdSet.delete(id);
		});
	},
	tabDel : function(id) {
		element.tabDelete(commonLoginHome.tabElem, id);
		commonLoginHome.tabIdSet.delete(id);
	},
	tabAdd : function(url, id, title) {
		var content = "";
		$.ajaxSettings.async = false;
		$.post(url, function(data) {
			content = data;
		}, 'html');
		$.ajaxSettings.async = true;

		if (!commonLoginHome.tabIdSet.has(id)) {
			element.tabAdd(commonLoginHome.tabElem, {
				id : id,
				title : title,
				content : content
			});
			commonLoginHome.tabIdSet.add(id);
		}
		element.tabChange(commonLoginHome.tabElem, id);

		element.init();
		form.render();
	}
};
commonLoginHome.nav1On();
commonLoginHome.nav2On();
commonLoginHome.nav3On();
commonLoginHome.tabDeleteOn();