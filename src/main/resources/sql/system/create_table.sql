drop table if exists archived_subject;

drop table if exists assist_unit;

drop table if exists base_access;

drop table if exists base_auth;

drop table if exists base_clerk;

drop table if exists base_code;

drop table if exists base_company;

drop table if exists base_customer;

drop table if exists base_department;

drop table if exists base_menu;

drop table if exists base_project;

drop table if exists base_role;

drop table if exists base_supplier;

drop table if exists base_user;

drop table if exists company_menu;

drop table if exists finance_period;

drop table if exists finance_voucher;

drop table if exists initial_subject;

drop table if exists role_menu;

drop table if exists subject_template;

drop table if exists template_subject;

drop table if exists user_login;

drop table if exists user_role;

drop table if exists voucher_detail;

drop table if exists voucher_word;

/*==============================================================*/
/* Table: archived_subject                                      */
/*==============================================================*/
create table archived_subject
(
   subject_id           varchar(32) not null comment '科目ID',
   period_id            varchar(32) not null comment '期间ID',
   subject_code         varchar(20) not null comment '科目代码',
   subject_name         varchar(20) not null comment '科目名称',
   subject_type         varchar(32) not null comment '科目类别',
   subject_direction    varchar(32) not null comment '科目方向',
   subject_money        double not null default 0 comment '科目金额',
   assist_unit          varchar(32) comment '辅计单位',
   assist_amount        double not null default 0 comment '辅计数量',
   assist_account       varchar(10) comment '辅助核算',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (subject_id)
);

alter table archived_subject comment '结账科目';

/*==============================================================*/
/* Table: assist_unit                                           */
/*==============================================================*/
create table assist_unit
(
   unit_id              varchar(32) not null comment '单位ID',
   company_id           varchar(32) not null comment '公司ID',
   unit_name            varchar(20) not null comment '单位名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (unit_id)
);

alter table assist_unit comment '辅计单位';

/*==============================================================*/
/* Table: base_access                                           */
/*==============================================================*/
create table base_access
(
   access_id            varchar(32) not null comment '访问ID',
   user_id              varchar(32) comment '访问用户',
   client_ip            varchar(15) not null comment '客户端地址',
   server_url           varchar(100) not null comment '服务器资源',
   access_total         int not null default 1 comment '累计访问次数',
   last_time            datetime not null default CURRENT_TIMESTAMP comment '最后访问时间',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (access_id)
);

alter table base_access comment '访问统计';

/*==============================================================*/
/* Table: base_auth                                             */
/*==============================================================*/
create table base_auth
(
   auth_id              varchar(32) not null comment '权限ID',
   menu_id              varchar(32) not null comment '菜单ID',
   auth_href            varchar(100) not null comment '权限链接',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (auth_id)
);

alter table base_auth comment '基础权限';

/*==============================================================*/
/* Table: base_clerk                                            */
/*==============================================================*/
create table base_clerk
(
   clerk_id             varchar(32) not null comment '职员ID',
   company_id           varchar(32) not null comment '公司ID',
   clerk_name           varchar(20) not null comment '职员名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (clerk_id)
);

alter table base_clerk comment '基础职员';

/*==============================================================*/
/* Table: base_code                                             */
/*==============================================================*/
create table base_code
(
   code_id              varchar(32) not null comment '编码ID',
   type_code            varchar(50) not null comment '类型代码',
   code_type            varchar(20) not null comment '编码类型',
   code_value           varchar(20) not null comment '编码码值',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (code_id)
);

alter table base_code comment '基础编码';

/*==============================================================*/
/* Table: base_company                                          */
/*==============================================================*/
create table base_company
(
   company_id           varchar(32) not null comment '公司ID',
   company_name         varchar(20) not null comment '公司名称',
   company_url          varchar(20) not null comment '公司链接',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (company_id)
);

alter table base_company comment '基础公司';

/*==============================================================*/
/* Table: base_customer                                         */
/*==============================================================*/
create table base_customer
(
   customer_id          varchar(32) not null comment '客户ID',
   company_id           varchar(32) not null comment '公司ID',
   customer_name        varchar(20) not null comment '客户名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (customer_id)
);

alter table base_customer comment '基础客户';

/*==============================================================*/
/* Table: base_department                                       */
/*==============================================================*/
create table base_department
(
   department_id        varchar(32) not null comment '部门ID',
   company_id           varchar(32) not null comment '公司ID',
   department_pid       varchar(32) not null comment '部门PID',
   department_name      varchar(20) not null comment '部门名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (department_id)
);

alter table base_department comment '基础部门';

/*==============================================================*/
/* Table: base_menu                                             */
/*==============================================================*/
create table base_menu
(
   menu_id              varchar(32) not null comment '菜单ID',
   menu_pid             varchar(32) not null comment '菜单PID',
   menu_name            varchar(20) not null comment '菜单名称',
   menu_href            varchar(100) comment '菜单链接',
   menu_type            varchar(32) not null comment '菜单类型',
   menu_target          varchar(32) not null comment '打开方式',
   order_number         int not null comment '排序编号',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (menu_id)
);

alter table base_menu comment '基础菜单';

/*==============================================================*/
/* Table: base_project                                          */
/*==============================================================*/
create table base_project
(
   project_id           varchar(32) not null comment '项目ID',
   company_id           varchar(32) not null comment '公司ID',
   project_pid          varchar(32) not null comment '项目PID',
   project_name         varchar(20) not null comment '项目名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (project_id)
);

alter table base_project comment '基础项目';

/*==============================================================*/
/* Table: base_role                                             */
/*==============================================================*/
create table base_role
(
   role_id              varchar(32) not null comment '角色ID',
   company_id           varchar(32) not null comment '公司ID',
   role_name            varchar(20) not null comment '角色名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (role_id)
);

alter table base_role comment '基础角色';

/*==============================================================*/
/* Table: base_supplier                                         */
/*==============================================================*/
create table base_supplier
(
   supplier_id          varchar(32) not null comment '供应商ID',
   company_id           varchar(32) not null comment '公司ID',
   supplier_name        varchar(20) not null comment '供应商名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (supplier_id)
);

alter table base_supplier comment '基础供应商';

/*==============================================================*/
/* Table: base_user                                             */
/*==============================================================*/
create table base_user
(
   user_id              varchar(32) not null comment '用户ID',
   company_id           varchar(32) not null comment '公司ID',
   user_name            varchar(20) not null comment '用户名称',
   user_card            varchar(18) comment '身份证号',
   user_phone           varchar(12) comment '手机号码',
   user_email           varchar(50) comment '电子邮箱',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (user_id)
);

alter table base_user comment '基础用户';

/*==============================================================*/
/* Table: company_menu                                          */
/*==============================================================*/
create table company_menu
(
   company_id           varchar(32) not null comment '公司ID',
   menu_id              varchar(32) not null comment '菜单ID'
);

alter table company_menu comment '公司菜单关联';

/*==============================================================*/
/* Table: finance_period                                        */
/*==============================================================*/
create table finance_period
(
   period_id            varchar(32) not null comment '期间ID',
   company_id           varchar(32) not null comment '公司ID',
   initial_time         datetime comment '初始时间',
   archived_time        datetime comment '结账时间',
   is_archived          varchar(1) not null default '0' comment '是否结账',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (period_id)
);

alter table finance_period comment '会计期间';

/*==============================================================*/
/* Table: finance_voucher                                       */
/*==============================================================*/
create table finance_voucher
(
   voucher_id           varchar(32) not null comment '凭证ID',
   period_id            varchar(32) not null comment '期间ID',
   voucher_word         varchar(32) not null comment '凭证字',
   voucher_number       varchar(10) not null comment '凭证号',
   voucher_time         datetime not null comment '制单日期',
   accessory_number     int not null default 0 comment '附件数',
   fill_user            varchar(32) not null comment '制单人',
   signature_user       varchar(32) comment '签字人',
   audit_user           varchar(32) comment '审核人',
   account_user         varchar(32) comment '记账人',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (voucher_id)
);

alter table finance_voucher comment '记账凭证';

/*==============================================================*/
/* Table: initial_subject                                       */
/*==============================================================*/
create table initial_subject
(
   subject_id           varchar(32) not null comment '科目ID',
   period_id            varchar(32) not null comment '期间ID',
   subject_code         varchar(20) not null comment '科目代码',
   subject_name         varchar(20) not null comment '科目名称',
   subject_type         varchar(32) not null comment '科目类别',
   subject_direction    varchar(32) not null comment '科目方向',
   subject_money        double not null default 0 comment '科目金额',
   assist_unit          varchar(32) comment '辅计单位',
   assist_amount        double not null default 0 comment '辅计数量',
   assist_account       varchar(10) comment '辅助核算：1、部门辅助；2、人员辅助；3、客户辅助；4、供应商辅助；5、项目辅助；',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (subject_id)
);

alter table initial_subject comment '初始科目';

/*==============================================================*/
/* Table: role_menu                                             */
/*==============================================================*/
create table role_menu
(
   role_id              varchar(32) not null comment '角色ID',
   menu_id              varchar(32) not null comment '菜单ID'
);

alter table role_menu comment '角色菜单关联';

/*==============================================================*/
/* Table: subject_template                                      */
/*==============================================================*/
create table subject_template
(
   template_id          varchar(32) not null comment '模板ID',
   template_name        varchar(20) not null comment '模板名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (template_id)
);

alter table subject_template comment '科目模板';

/*==============================================================*/
/* Table: template_subject                                      */
/*==============================================================*/
create table template_subject
(
   subject_id           varchar(32) not null comment '科目ID',
   template_id          varchar(32) not null comment '模板ID',
   subject_code         varchar(20) not null comment '科目代码',
   subject_name         varchar(20) not null comment '科目名称',
   subject_type         varchar(32) not null comment '科目类别',
   subject_direction    varchar(32) not null comment '科目方向',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (subject_id)
);

alter table template_subject comment '模板科目';

/*==============================================================*/
/* Table: user_login                                            */
/*==============================================================*/
create table user_login
(
   user_id              varchar(32) not null comment '用户ID',
   account              varchar(20) not null comment '账号',
   password             varchar(32) not null comment '密码',
   enable               varchar(1) not null default '1' comment '是否可用',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (user_id)
);

alter table user_login comment '用户登录';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   user_id              varchar(32) not null comment '用户ID',
   role_id              varchar(32) not null comment '角色ID'
);

alter table user_role comment '用户角色关联';

/*==============================================================*/
/* Table: voucher_detail                                        */
/*==============================================================*/
create table voucher_detail
(
   detail_id            varchar(32) not null comment '明细ID',
   voucher_id           varchar(32) comment '凭证ID',
   subject_id           varchar(32) not null comment '科目ID',
   detail_abstract      varchar(20) not null comment '摘要',
   borrower_money       double not null default 0 comment '借方金额',
   lender_money         double not null default 0 comment '贷方金额',
   assist_amount        double not null default 0 comment '辅计数量',
   assist_account       varchar(10) comment '辅助核算',
   assist_object        varchar(32) comment '辅助对象',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (detail_id)
);

alter table voucher_detail comment '凭证明细';

/*==============================================================*/
/* Table: voucher_word                                          */
/*==============================================================*/
create table voucher_word
(
   word_id              varchar(32) not null comment '字ID',
   company_id           varchar(32) not null comment '公司ID',
   word_name            varchar(20) not null comment '字名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (word_id)
);

alter table voucher_word comment '凭证字';

alter table archived_subject add constraint FK_Reference_20 foreign key (period_id)
      references finance_period (period_id) on delete restrict on update restrict;

alter table archived_subject add constraint FK_Reference_28 foreign key (assist_unit)
      references assist_unit (unit_id) on delete restrict on update restrict;

alter table assist_unit add constraint FK_Reference_24 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table base_access add constraint FK_Reference_12 foreign key (user_id)
      references base_user (user_id) on delete restrict on update restrict;

alter table base_auth add constraint FK_Reference_31 foreign key (menu_id)
      references base_menu (menu_id) on delete restrict on update restrict;

alter table base_clerk add constraint FK_Reference_15 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table base_customer add constraint FK_Reference_16 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table base_department add constraint FK_Reference_14 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table base_project add constraint FK_Reference_18 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table base_role add constraint FK_Reference_1 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table base_supplier add constraint FK_Reference_17 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table base_user add constraint FK_Reference_2 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table company_menu add constraint FK_Reference_10 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table company_menu add constraint FK_Reference_11 foreign key (menu_id)
      references base_menu (menu_id) on delete restrict on update restrict;

alter table finance_period add constraint FK_Reference_25 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;

alter table finance_voucher add constraint FK_Reference_21 foreign key (period_id)
      references finance_period (period_id) on delete restrict on update restrict;

alter table finance_voucher add constraint FK_Reference_30 foreign key (voucher_word)
      references voucher_word (word_id) on delete restrict on update restrict;

alter table initial_subject add constraint FK_Reference_26 foreign key (period_id)
      references finance_period (period_id) on delete restrict on update restrict;

alter table initial_subject add constraint FK_Reference_29 foreign key (assist_unit)
      references assist_unit (unit_id) on delete restrict on update restrict;

alter table role_menu add constraint FK_Reference_6 foreign key (role_id)
      references base_role (role_id) on delete restrict on update restrict;

alter table role_menu add constraint FK_Reference_7 foreign key (menu_id)
      references base_menu (menu_id) on delete restrict on update restrict;

alter table template_subject add constraint FK_Reference_13 foreign key (template_id)
      references subject_template (template_id) on delete restrict on update restrict;

alter table user_login add constraint FK_Reference_3 foreign key (user_id)
      references base_user (user_id) on delete restrict on update restrict;

alter table user_role add constraint FK_Reference_4 foreign key (user_id)
      references base_user (user_id) on delete restrict on update restrict;

alter table user_role add constraint FK_Reference_5 foreign key (role_id)
      references base_role (role_id) on delete restrict on update restrict;

alter table voucher_detail add constraint FK_Reference_22 foreign key (voucher_id)
      references finance_voucher (voucher_id) on delete restrict on update restrict;

alter table voucher_detail add constraint FK_Reference_23 foreign key (subject_id)
      references archived_subject (subject_id) on delete restrict on update restrict;

alter table voucher_word add constraint FK_Reference_27 foreign key (company_id)
      references base_company (company_id) on delete restrict on update restrict;
