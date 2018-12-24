-- 基础编码
insert into base_code(code_id, type_code, code_type, code_value) values('F74BCBB210A24C8AB79BA4C8B7F64511', 'base_menu_menu_type', '基础菜单-菜单类型', '后台菜单');
insert into base_code(code_id, type_code, code_type, code_value) values('65AC752B8C514A78BF762B6EB04408EC', 'base_menu_menu_type', '基础菜单-菜单类型', '前台菜单');
insert into base_code(code_id, type_code, code_type, code_value) values('6A685E3FA0144180895931254C6FF7DD', 'base_menu_menu_type', '基础菜单-菜单类型', '权限菜单');
insert into base_code(code_id, type_code, code_type, code_value) values('43691F6ADD38458CA7E765D0EBEC32A7', 'base_menu_menu_target', '基础菜单-打开方式', '子页打开');
insert into base_code(code_id, type_code, code_type, code_value) values('4C8DA09CB29048C48BFD151E53C11E20', 'base_menu_menu_target', '基础菜单-打开方式', '本页打开');
insert into base_code(code_id, type_code, code_type, code_value) values('7EC86E05ABD346ACA4A5C3204DD590B5', 'base_menu_menu_target', '基础菜单-打开方式', '新页打开');
-- 基础公司
insert into base_company(company_id, company_name, company_url) values('F896D6BA4D414AEEA54D3C32750CD77D', '金刚树有限公司', 'admin');
-- 基础用户
insert into base_user(user_id, company_id, user_name) values('AB0452E4800742C8A2C978FE26D72EBF', 'F896D6BA4D414AEEA54D3C32750CD77D', '吴兴平');
-- 用户登录
insert into user_login(user_id, account, password, enable) values('AB0452E4800742C8A2C978FE26D72EBF', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '1');
-- 基础角色
insert into base_role(role_id, company_id, role_name) values('3F8FD213436E4F16A19D2A8458B2EB5A', 'F896D6BA4D414AEEA54D3C32750CD77D', '超级管理员');
-- 基础菜单
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('A2312832B172447995DC923C51AB18B0', 'DFF4D882CFF2482DB547DA8B67350637', '部署设置', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 100);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('01C2DC6B6D064D2A8EE3D1EFDB3C6C19', 'A2312832B172447995DC923C51AB18B0', '公司管理', '/system/baseCompany/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('CCBFD6F3AA044CED9D1779CFF22310AC', 'A2312832B172447995DC923C51AB18B0', '用户管理', '/system/baseUser/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 2);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('C5126A52B4F84E509DC259A1BA141A3A', 'A2312832B172447995DC923C51AB18B0', '角色管理', '/system/baseRole/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 3);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('E919526D3A2849C09168FA264BB11667', 'A2312832B172447995DC923C51AB18B0', '菜单管理-表', '/system/baseMenu/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 4);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('9D8AEBC64B384D6BA1648AF8FFDD7F8D', 'A2312832B172447995DC923C51AB18B0', '菜单管理-树', '/system/baseMenu/treeIndex', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 5);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('24E2852CB90E4D3D8672299C522B459F', 'A2312832B172447995DC923C51AB18B0', '编码管理', '/system/baseCode/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 6);
-- 用户角色关联
insert into user_role(user_id, role_id) values('AB0452E4800742C8A2C978FE26D72EBF', '3F8FD213436E4F16A19D2A8458B2EB5A');
-- 角色菜单关联
insert into role_menu(role_id, menu_id) values('3F8FD213436E4F16A19D2A8458B2EB5A', 'A2312832B172447995DC923C51AB18B0');
insert into role_menu(role_id, menu_id) values('3F8FD213436E4F16A19D2A8458B2EB5A', '01C2DC6B6D064D2A8EE3D1EFDB3C6C19');
insert into role_menu(role_id, menu_id) values('3F8FD213436E4F16A19D2A8458B2EB5A', 'CCBFD6F3AA044CED9D1779CFF22310AC');
insert into role_menu(role_id, menu_id) values('3F8FD213436E4F16A19D2A8458B2EB5A', 'C5126A52B4F84E509DC259A1BA141A3A');
insert into role_menu(role_id, menu_id) values('3F8FD213436E4F16A19D2A8458B2EB5A', 'E919526D3A2849C09168FA264BB11667');
insert into role_menu(role_id, menu_id) values('3F8FD213436E4F16A19D2A8458B2EB5A', '9D8AEBC64B384D6BA1648AF8FFDD7F8D');
insert into role_menu(role_id, menu_id) values('3F8FD213436E4F16A19D2A8458B2EB5A', '24E2852CB90E4D3D8672299C522B459F');

-- 菜单
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('9025BC57E0B14239AF5EBC4C025146FD', 'DFF4D882CFF2482DB547DA8B67350637', '财务会计', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('7D5D85E0B2614E099B6ED46885D0AD16', '9025BC57E0B14239AF5EBC4C025146FD', '总账管理', '/finance/archivedSubject/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('2C67A45825CA44BC85497E034EC93633', '9025BC57E0B14239AF5EBC4C025146FD', '报表分析', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 2);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('DBDDD5F181FF441BBDBE7CA449C52690', '9025BC57E0B14239AF5EBC4C025146FD', '现金管理', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 3);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('0061FC91ADF441ACBFE193B87D281837', '9025BC57E0B14239AF5EBC4C025146FD', '应收账款', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 4);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('F5CB35583068413688D21E6EDCD9D871', '9025BC57E0B14239AF5EBC4C025146FD', '应付账款', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 5);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('AC1DEA158C274F2E8C211EFF60D5B982', '9025BC57E0B14239AF5EBC4C025146FD', '凭证管理', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 6);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('F7C52FB64FDA41AAAAFCC566CCC8DAB1', 'AC1DEA158C274F2E8C211EFF60D5B982', '凭证填制', '/finance/financeVoucher/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('35C27D644DFD4298ADE4E8D63E9B0532', 'F7C52FB64FDA41AAAAFCC566CCC8DAB1', '查看权限', '6A685E3FA0144180895931254C6FF7DD', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('04B9D7C7325741508C3ED1ACCB51AE19', 'F7C52FB64FDA41AAAAFCC566CCC8DAB1', '签字权限', '6A685E3FA0144180895931254C6FF7DD', '43691F6ADD38458CA7E765D0EBEC32A7', 2);
insert into base_auth(auth_id, menu_id, auth_href) values('C64D2C4C55444E0DAD5B5828F60C38F5', '04B9D7C7325741508C3ED1ACCB51AE19', '/finance/financeVoucher/signature');
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('687B3DAA9B5343EFA33B2A0691E3AA67', '9025BC57E0B14239AF5EBC4C025146FD', '固定资产', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 7);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('D882F61A8692455ABB0E40B4B02101E4', '9025BC57E0B14239AF5EBC4C025146FD', '初始化', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 8);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('359A1219070F4D6BB4051161D0DFB883', 'D882F61A8692455ABB0E40B4B02101E4', '会计科目', '/finance/archivedSubject/initIndex', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('FC9EE979F7B346C6A6B1EB3AFB4ED016', 'D882F61A8692455ABB0E40B4B02101E4', '部门设置', '/personnel/baseDepartment/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 2);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('2C51227E11A94C659EA45ECABAD6CDB8', 'D882F61A8692455ABB0E40B4B02101E4', '职员设置', '/personnel/baseClerk/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 3);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('DDE5BE4AD3CC4DA4A2B3247BD580FCC5', 'D882F61A8692455ABB0E40B4B02101E4', '客户设置', '/sale/baseCustomer/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 4);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('AB146E8620F14C91AC7CE12567A06780', 'D882F61A8692455ABB0E40B4B02101E4', '供应商设置', '/purchase/baseSupplier/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 5);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('3C2D823D63504F359D5CE00A7E90BEF1', 'D882F61A8692455ABB0E40B4B02101E4', '项目设置', '/project/baseProject/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 6);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('42108F7C09BB41CE85BA4D1AA7E320E2', 'D882F61A8692455ABB0E40B4B02101E4', '辅计单位设置', '/finance/assistUnit/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 7);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('74D25A2489D34266A548035C31CA167C', 'D882F61A8692455ABB0E40B4B02101E4', '凭证字设置', '/finance/voucherWord/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 8);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('952042F0CF444B258BAB9419B19896BF', 'DFF4D882CFF2482DB547DA8B67350637', '权限设置', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 41);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('4861CB11DD0249D48E9C4E3A66347DD6', '952042F0CF444B258BAB9419B19896BF', '用户管理', '/system/baseUser/companyIndex', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('1AF3C69FCA9A428EBEB8F4601D1BCD2D', '952042F0CF444B258BAB9419B19896BF', '角色管理', '/system/baseRole/companyIndex', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 2);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('33B23576AAAD4AE4BFF45EA1DA58CE49', 'DFF4D882CFF2482DB547DA8B67350637', '基础数据', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 51);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('A29E4BBD083141A19B7CC35359B8B079', '33B23576AAAD4AE4BFF45EA1DA58CE49', '科目模板', '/basedata/subjectTemplate/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);
insert into base_menu(menu_id, menu_pid, menu_name, menu_type, menu_target, order_number) values('D2E591733C8841248328CB007C7E7915', 'DFF4D882CFF2482DB547DA8B67350637', '运行状态', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 101);
insert into base_menu(menu_id, menu_pid, menu_name, menu_href, menu_type, menu_target, order_number) values('B09A7AF155104B41B81FAB025D1AF81B', 'D2E591733C8841248328CB007C7E7915', '访问统计', '/runtime/baseAccess/index', 'F74BCBB210A24C8AB79BA4C8B7F64511', '43691F6ADD38458CA7E765D0EBEC32A7', 1);

-- 编码
insert into base_code(code_id, type_code, code_type, code_value) values('F11F212B099644879E4360BF89AAD024', 'finance_subject_subject_type', '会计科目-科目类别', '现金');
insert into base_code(code_id, type_code, code_type, code_value) values('9FE16518B52745709DEAD89A5411117F', 'finance_subject_subject_type', '会计科目-科目类别', '银行存款');
insert into base_code(code_id, type_code, code_type, code_value) values('EF2CCD26990E4769A782A5DE7D7B244D', 'finance_subject_subject_type', '会计科目-科目类别', '流动资产');
insert into base_code(code_id, type_code, code_type, code_value) values('7C10C990812343C0B66FB27268E89D1E', 'finance_subject_subject_type', '会计科目-科目类别', '坏账准备');
insert into base_code(code_id, type_code, code_type, code_value) values('DBA50585B3BA4253BA223550118F645A', 'finance_subject_subject_type', '会计科目-科目类别', '存货');
insert into base_code(code_id, type_code, code_type, code_value) values('086F60A474B040DF91E5AFA932FD85E6', 'finance_subject_subject_type', '会计科目-科目类别', '非流动资产');
insert into base_code(code_id, type_code, code_type, code_value) values('5125BFCB4C0046BDAD306AFA76F5492C', 'finance_subject_subject_type', '会计科目-科目类别', '固定资产');
insert into base_code(code_id, type_code, code_type, code_value) values('3C48A6B4BC4C45EFAEE1E1D3B5D53C46', 'finance_subject_subject_type', '会计科目-科目类别', '累计折旧');
insert into base_code(code_id, type_code, code_type, code_value) values('8B7238C4B0A642399A3D147D689F145F', 'finance_subject_subject_type', '会计科目-科目类别', '流动负债');
insert into base_code(code_id, type_code, code_type, code_value) values('AC3A7CEAA65F420CBE05CF90C682227F', 'finance_subject_subject_type', '会计科目-科目类别', '非流动负债');
insert into base_code(code_id, type_code, code_type, code_value) values('CE5AD2D0C19241AEA2069EFDA0E43F39', 'finance_subject_subject_type', '会计科目-科目类别', '共同');
insert into base_code(code_id, type_code, code_type, code_value) values('7BF31FDBB4994CC1A8616D1CBE420FFB', 'finance_subject_subject_type', '会计科目-科目类别', '资本');
insert into base_code(code_id, type_code, code_type, code_value) values('7CCC1DF696D24899988242D2940421CF', 'finance_subject_subject_type', '会计科目-科目类别', '累计盈余');
insert into base_code(code_id, type_code, code_type, code_value) values('AFAA3231B03D4938B6E9D62F4D6D0975', 'finance_subject_subject_type', '会计科目-科目类别', '生产成本');
insert into base_code(code_id, type_code, code_type, code_value) values('6722B0EA56BF481898E8615BBC4DF65B', 'finance_subject_subject_type', '会计科目-科目类别', '收入');
insert into base_code(code_id, type_code, code_type, code_value) values('10152B7825A343A08AB92E0224B94165', 'finance_subject_subject_type', '会计科目-科目类别', '其他收入');
insert into base_code(code_id, type_code, code_type, code_value) values('919455A75B5345149F19961EDC66E278', 'finance_subject_subject_type', '会计科目-科目类别', '销售成本');
insert into base_code(code_id, type_code, code_type, code_value) values('5A3D66A1F3554CAF95CC01BF69291E86', 'finance_subject_subject_type', '会计科目-科目类别', '其他费用');
insert into base_code(code_id, type_code, code_type, code_value) values('B71589188C7848BDA3972A8DCC288053', 'finance_subject_subject_type', '会计科目-科目类别', '费用');

insert into base_code(code_id, type_code, code_type, code_value) values('D2E3828F2857477F8A94B824CF9CD730', 'finance_subject_subject_direction', '会计科目-科目方向', '借');
insert into base_code(code_id, type_code, code_type, code_value) values('50E61FFE609D4509906E4CE846CF68E2', 'finance_subject_subject_direction', '会计科目-科目方向', '贷');
