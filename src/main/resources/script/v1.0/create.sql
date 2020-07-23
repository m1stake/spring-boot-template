
drop table if exists sys_user;
create table sys_user (

    id bigint primary key IDENTITY(1,1), -- comment 'id',
    username varchar(50), -- comment 'oa id',
    name varchar(50), -- comment '姓名',
    department varchar(50), -- comment '部门',
    password varchar(200) -- comment '密码'

); -- comment '用户';