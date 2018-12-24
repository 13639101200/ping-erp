//# sourceURL=/common.js
var element = layui.element;
var table = layui.table;
var layer = layui.layer;
var form = layui.form;
var laydate = layui.laydate;
var upload = layui.upload;
var flow = layui.flow;

form.verify({
	password : function(value, item) {
		var password = $('#system-baseUser-passwordEdit-input-password').val();
		if (value != password) {
			return '新密码不一致';
		}
	},
	menu : function(value, item) {
		var url = '/system/baseMenu/verifyExist';
		var data = {
			id : value
		};
		var res = null;

		$.ajaxSettings.async = false;
		$.post(url, data, function(data) {
			res = data;
		}, 'json');
		$.ajaxSettings.async = true;

		if (res.code <= 0) {
			return res.message;
		}
	},
	identity : function(value, item) {
		if (value) {
			var reg = /(^\d{15}$)|(^\d{17}(\d|x|X)$)/;
			if (!reg.test(value)) {
				return '请输入正确的身份证号';
			}
		}
	},
	phone : function(value, item) {
		if (value) {
			var reg = /^0?1\d{10}$/;
			if (!reg.test(value)) {
				return '请输入正确的手机号码';
			}
		}
	},
	email : function(value, item) {
		if (value) {
			var reg = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+.[0-9a-zA-Z]+$/;
			if (!reg.test(value)) {
				return '请输入正确的电子邮箱';
			}
		}
	},
	intTypeZero : function(value, item) {
		if (value < 0 || value > 2147483647) {
			return '数字范围应为0-2147483647';
		}
	},
	intType : function(value, item) {
		if (value < 1 || value > 2147483647) {
			return '数字范围应为1-2147483647';
		}
	},
	doubleTypeZero : function(value, item) {

	},
	doubleType : function(value, item) {

	},
	length20 : function(value, item) {
		if (value.length > 20) {
			return '不能大于20个字符';
		}
	},
	length12 : function(value, item) {
		if (value.length > 12) {
			return '不能大于12个字符';
		}
	},
	length15 : function(value, item) {
		if (value.length > 15) {
			return '不能大于15个字符';
		}
	},
	length18 : function(value, item) {
		if (value.length > 18) {
			return '不能大于18个字符';
		}
	},
	length32 : function(value, item) {
		if (value.length > 32) {
			return '不能大于32个字符';
		}
	},
	length50 : function(value, item) {
		if (value.length > 50) {
			return '不能大于50个字符';
		}
	},
	length60 : function(value, item) {
		if (value.length > 60) {
			return '不能大于60个字符';
		}
	},
	length100 : function(value, item) {
		if (value.length > 100) {
			return '不能大于100个字符';
		}
	}
});

var common = {
	// 在小于10的数前添加'0'
	addZero : function(value) {
		return value < 10 ? '0' + value : value;
	},
	// 格式化日期为：YYYY-MM-DD hh:mm:ss
	formatDate : function(value, field) {
		value = eval('(value.' + field + ')');
		if (!value) {
			return '';
		}
		var date = new Date(value);
		return date.getFullYear() + '-' + this.addZero(date.getMonth() + 1) + '-' + this.addZero(date.getDate()) + ' ' + this.addZero(date.getHours()) + ':' + this.addZero(date.getMinutes()) + ':' + this.addZero(date.getSeconds());
	},
	// 格式化日期为：YYYY年MM月DD日
	formatDateA : function(value, field) {
		value = eval('(value.' + field + ')');
		if (!value) {
			return '';
		}
		var date = new Date(value);
		return date.getFullYear() + '年' + this.addZero(date.getMonth() + 1) + '月' + this.addZero(date.getDate()) + '日';
	},
	// 格式化日期为：hh:mm:ss
	formatDateB : function(value, field) {
		value = eval('(value.' + field + ')');
		if (!value) {
			return '';
		}
		var date = new Date(value);
		return this.addZero(date.getHours()) + ':' + this.addZero(date.getMinutes()) + ':' + this.addZero(date.getSeconds());
	},
	// 格式化日期为：YYYY-MM-DD
	formatDateC : function(value, field) {
		value = eval('(value.' + field + ')');
		if (!value) {
			return '';
		}
		var date = new Date(value);
		return date.getFullYear() + '-' + this.addZero(date.getMonth() + 1) + '-' + this.addZero(date.getDate());
	},
	// 格式化属性
	formatField : function(value, field1, field2) {
		if (value == null) {
			return '';
		}
		if (eval('(value.' + field1 + '==null)')) {
			return '';
		}
		return eval('(value.' + field1 + '.' + field2 + ')');
	},
	// 构建select2下拉框
	select : function(selectId, url, idName, textName, placeholder, field, order, size) {
		$('#' + selectId).select2({
			language : 'zh-CN',
			width : '100%',
			placeholder : placeholder || '请选择',
			ajax : {
				delay : 250,
				url : url,
				dataType : 'json',
				data : function(params) {
					return {
						keyword : params.term || '',
						field : field || 'createTime',
						order : order || 'desc',
						size : size || 10,
						page : params.page || 1
					};
				},
				processResults : function(res, params) {
					for (var i = 0; i < res.data.length; i++) {
						res.data[i].id = eval('(res.data[i].' + idName + ')');
						res.data[i].text = '';
						for (var j = 0; j < textName.length; j++) {
							res.data[i].text += eval('(res.data[i].' + textName[j] + ')') + '-';
						}
						res.data[i].text = res.data[i].text.substring(0, res.data[i].text.length - 1);
					}
					return {
						results : res.data,
						pagination : {
							more : (params.page || 1) * 10 < res.count
						}
					};
				}
			}
		});
	}
};