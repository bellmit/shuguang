declare
	test_Id VARCHAR2(64) :='';
	test_AppId VARCHAR2(64) :='';
	test_ParentId VARCHAR2(64) :='';
	test_ParentAppId VARCHAR2(64) :='';
	dev_ParentId VARCHAR2(64) :='';
	dev_ParentAppId VARCHAR2(64) :='';
	num int;

BEGIN
	for item in (select t.ID,t.APP_ID,t.PARENT_ID from SYS_TEST."SYS_SUBSYSTEM" t) loop
				test_Id := item.ID;
				test_AppId := item.APP_ID;

				-- 查询父app_ID
				select count(*) into num from SOFN.SYS_SUBSYSTEM dev WHERE dev.APP_ID=test_AppId;
				if num>0 then
					select dev.PARENT_ID into dev_ParentId from SOFN.SYS_SUBSYSTEM dev WHERE dev.APP_ID=test_AppId;
					select dev.APP_ID into dev_ParentAppId from SOFN.SYS_SUBSYSTEM dev WHERE dev.ID=dev_ParentId;
					-- 查询测试环境父id
					select t.ID into test_ParentId from SYS_TEST."SYS_SUBSYSTEM" t WHERE t.APP_ID=dev_ParentAppId;
					-- 更新父ID
					update SYS_TEST."SYS_SUBSYSTEM" set PARENT_ID=test_ParentId WHERE ID=test_Id;
				end if;
	end loop;
END;

