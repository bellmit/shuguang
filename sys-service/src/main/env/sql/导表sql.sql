-- 插入子系统    OK 
-- insert into SYS_TEST.SYS_SUBSYSTEM
select * from SOFN.SYS_SUBSYSTEM ssub where SSUB.APP_ID = 'astcap'
and id not in (select id from SYS_TEST.SYS_SUBSYSTEM)
; --农业体系队伍及条件能力状况信息调查子系统

--插入组织机构   OK 
--insert into SYS_TEST.SYS_ORG
select * from SOFN.SYS_ORG a where a.id in (
select org_id from SOFN.SYS_SYSTEM_ORG a where a.APP_ID = 'astcap'
) and a.ORGANIZATION_NAME not in (
   select ORGANIZATION_NAME from SYS_TEST.SYS_ORG
) and a.CREATE_USER_ID in (
	select id from SOFN.SYS_USER where USERNAME = 'zqc'
);-- 20条



-- 插入子系统的组织机构绑定关系 OK 
--insert into SYS_TEST.SYS_SYSTEM_ORG
select * from SOFN.SYS_SYSTEM_ORG a where a.APP_ID = 'astcap' 
and APP_ID || org_id not in (select APP_ID || org_id from SYS_TEST.SYS_SYSTEM_ORG) 


-- 插入子系统的菜单
--insert into SYS_test.SYS_RESOURCE
select * from SOFN.SYS_RESOURCE a where a.APP_ID = 'astcap' and a.id not in (select id from SYS_TEST.SYS_RESOURCE); -- 105条条


-- 查询子系统的角色
--insert into SYS_TEST.sys_role
select * from sofn.SYS_ROLE a where a.SUBSYSTEM_ID = (select id from SOFN.SYS_SUBSYSTEM a where a.APP_ID = 'astcap')
and id not in (select id from SYS_TEST.SYS_ROLE)
; -- 13条

-- 查询子系统的角色资源
--insert into SYS_TEST.SYS_ROLE_RESOURCE
select * from SOFN.SYS_ROLE_RESOURCE a where a.ROLE_ID in (
	select sr.id from SOFN.SYS_ROLE sr where sr.SUBSYSTEM_ID = (select id from SOFN.SYS_SUBSYSTEM a where a.APP_ID = 'astcap')
) AND A.RESOURCE_ID || A.ROLE_ID NOT IN (SELECT RESOURCE_ID || ROLE_ID FROM SYS_TEST.SYS_ROLE_RESOURCE)

-- 查询创建的用户
--insert into sys_test.SYS_USER
select * from SOFN.SYS_USER where CREATE_USER_ID = '07' or CREATE_USER_ID = '06' and USERNAME like 'astcap%'
and id not in (select id from sys_test.SYS_USER)