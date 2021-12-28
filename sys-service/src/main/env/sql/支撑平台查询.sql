-- 查询用户信息、角色、所属系统
select su.USERNAME,su.INIT_PASSWORD,su.NICKNAME,sr.ROLE_NAME,ss.SUBSYSTEM_NAME from SYS_USER su
left join SYS_USER_ROLE sur 		on su.ID=sur.user_id
left join SYS_ROLE sr 					on sr.ID=sur.ROLE_ID
left join SYS_SUBSYSTEM ss 			on ss.ID=sr.SUBSYSTEM_ID
where su.DEL_FLAG='N' and sr.ROLE_NAME is not null
order by ss.APP_ID asc
