<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="公司简称" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 地区||'省' as 地区,count(ID) as 个数
FROM S人员花名册 
where 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
group by 地区]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="年龄段" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 年龄段,count(ID) as 个数
FROM S人员花名册
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
group by 年龄段]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="性别" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 性别,count(ID) as 个数
FROM S人员花名册 
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
group by 性别]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="职业资格等级" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 职业资格等级,count(ID) as 个数
FROM S人员花名册
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
group by 职业资格等级]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="最高学历" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 最高学历,count(ID) as 个数
FROM S人员花名册
where 1=1 
${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
group by 最高学历]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="学历专业类别" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 学历专业类别,count(ID) as 个数
FROM S人员花名册
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
and 学历专业类别<>'其他'
group by 学历专业类别]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="人员类别名称" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 人员类别名称,count(ID) as 个数
FROM S人员花名册 
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
and(人员类别名称='合同制'
or 人员类别名称='派遣制')
group by 人员类别名称]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="岗位层级" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 岗位层级,count(ID) as 个数
FROM S人员花名册
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
and 岗位层级 is not null
group by 岗位层级]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="专业技术职务等级" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 专业技术职务等级,count(ID) as 个数
FROM S人员花名册
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
group by 专业技术职务等级]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="一级岗位目录" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[山西]]></O>
</Parameter>
<Parameter>
<Attributes name="twoposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 一级岗位目录,count(ID) as 个数
FROM S人员花名册 
where 1=1 ${if(len(company)==0,nofilter,"and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(twoposition)==0,nofilter,"and 二级岗位目录 = '" + twoposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
and 一级岗位目录 is not null
group by 一级岗位目录]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="二级岗位目录" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="expertise"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="sex"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="nofilter"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="vocational"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="positionlevel"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="age"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="company"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="education"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="oneposition"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="specialized"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 二级岗位目录,count(ID) as 个数
FROM S人员花名册
where 1=1 ${if(len(company)==0,"and 地区='云南'","and 地区 = '" + company + "'" )}
and 1=1 ${if(len(age)==0,nofilter,"and 年龄段 = '" + age + "'" )}
and 1=1 ${if(len(sex)==0,nofilter,"and 性别 = '" + sex + "'" )} 
and 1=1 ${if(len(officers)==0,nofilter,"and 人员类别名称 = '" + officers + "'" )}
and 1=1 ${if(len(education)==0,nofilter,"and 最高学历 = '" + education + "'" )}
and 1=1 ${if(len(specialized)==0,nofilter,"and 学历专业类别 = '" + specialized + "'" )}
and 1=1 ${if(len(oneposition)==0,nofilter,"and 一级岗位目录 = '" + oneposition + "'" )}
and 1=1 ${if(len(positionlevel)==0,nofilter,"and 岗位层级 = '" + positionlevel + "'" )}
and 1=1 ${if(len(vocational)==0,nofilter,"and 职业资格等级 = '" + vocational + "'" )}
and 1=1 ${if(len(expertise)==0,nofilter,"and 专业技术职务等级 = '" + expertise + "'" )}
group by 二级岗位目录
order by count(ID) desc limit 5]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Center class="com.fr.form.ui.container.WFitLayout">
<WidgetName name="body"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteBodyLayout">
<WidgetName name="body"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="JPEG">
<IM>
<![CDATA[SGWas'A(e>Npg4D!5h5GCVYHdDAcZ@8</G*"!.LM&9>&C.J7WkX<`0n#uQd\23Fp+HnjDS_&
./!#tTEA]Au1\IV8Xo#HO^Sg77F$e&>YOo'XY9VrU$7pcgCTos5+?ZrB)biRFuqmf"9d_XgS,
hl7XBt>9jae!/V*a$2t%A>R-aK.ud#r\[^)PF>l-[G6['C=L<nCZXg$Oiodr%hp7Y.@L%U@Z
<'1&aC9-IT[/:4Gs's+d)]A>g\sT]A-hE'go88`<_N("/PLBhm+!%VI5s6H:ag;%0!.\El?OB)
DQ@M36,l'G%N-(Lk@C#K:Zq&tkL<#6J/e]AiLS]A\okM+Hn2f16pkY6D_Q*qqu`b?0njBJ/_9g
.c!^(V*`bt.c,7V9R-$C10NLY5nVcR#Mn1->0cd1%1W^+TW<?f=MKD/)7A^iM1VO6Th)ong#
nt,dgI$RgKrU*X&.rXGOHt3g0:X-IJ(tG,Hne@=&p3r3*'=+gf.?nYr4G1DL1XI%r>lCr2A`
4h`eYsWP,(5lgj5!rdc^,Y?\b!o[W0XpoTCpgJ_J*hRtj,J,d@Z5<O%jMZbH(?[r(@\e`2+O
f;:eQ0qlnM-T,S?np1XJ4j)Z0;WSe/R>bZkDCNa]A_UCf\%i:&GK[9"qV7\MidDP(lWi/\b5V
EFfYRS`/,O0l^TVDY0IS4Aj\ZeGNSqE2;24:XKKH),]A.c!.;To;!9#qKV,!]As6#t]AfE$G\IO
,c!gr6C+%6V3i5"c1r/),nU/IR@OEkWL4pKgc_G)Nl&Bm2^`?FHnJXgaqpJS4Hq?7aSjg-_Q
F(lk".CPW7o)/9$JP\:5n?9(s:df_MA*.H.XKZkm^(3GPq4WJlNBDfT&lj%iknM*d"SM3E/?
Zo'Wq<5u'>.d+QVfp\erI(.9!9mE,"mMnI/o0tV8BKs.b,92H?-@Ui9oLo8D^aEk?U.>%sWm
%aDK]A0ruPU>-P!*<#T5DZ+D%:rQk?Ia[[CGVD!,(]Art!kD3'=nM1Z?YE</S'D/36`a@tP</!
)S.;)"(J)\Ce^m(VFHhP,/i7:_dhAVR6$rYgjDd\RkSsFi["m.Ac=q5Jo+92'[m15a@qnKu'
]AX"JNg@0ft&^RVXa-efJ=7,H`HuE<^3VB`9-3!"%J,cStq.b#jn690Mm";IGm?E:S>-C3I[.
%(V*ipT#;!Xsh12-$aXrc!qhD%Il*tbRG%UUc;)9S/pA<!jD`"TY[/rB4n&,RGC\V-)U-28g
Xh+fkDEe\\L`']A=PHO4C_?gcX;Onlc*qi/WL?RB$*->=3p>'3tSaYNQ"\tQs9n_E=97+$E08
O7n/>TYj\;OTr_\8t3m?bi8lefJ`lf`%V'aq3GAnU[!/$/4fO12Xh%-2LPpN5`juh72n7kq>
c<^035A8m.bame5fZI?V/.j&o61RucYd:TJ"t*N(O6Uoi&tWCBXWl8HLj`[$FTpUa-t#rnW`
;_A'lV6;A^0Mr>)Bh^;#Bn;3H_iptU(cBK8j,irm5iaaq1q>Dp$S="?jcG_*Ooqdq5/"TQGX
dpKC5qYL"5-k80L_@&`]A:uPO0-c2fl]A7Z^ql7%UO=n&,n\!^ISMCqs%aGo*Y3bT_A/S%*,t2
6[U*PT7Y&`3`>7F.$b&(kMP*\","J]AZZ6\DX8K+SB5a=h:\#5E?O&]Aqe_F5H-U/!p:TlkSFi
=h5V*2#pf>0^h*`(mbm?mQ;S@g6XXUtUG\M*:9b"g@`=2K/d\Vn<3r3G=(EBg#0eT<&B%/QV
X#Zq,81T[k@9@I:6N^$oGR>d(5)#[L[2qd<S>$%Ul,:p$%p`tL$"p#W]A)lH]AM7H9GE^L\VKH
MKL<_(?<j-@r]A3q37%_lIMSD#qFU2RG^Rpm,3?tZ(9>1^(i@\Eh4i3`,p65+B</*'gq;4PgT
N&hQ*%f^S@hQoIfAI*oYAOke:$SLd!$Fd)Q:>-p;j_lhmaq;kXjs`ePjYeJpt%Z<*L?qLtk*
oO^EU\\un*8m+%dUmsfVgG+cfCfJW%Ud1JNMbkb0L+:LCX*]AU;%$J4+aT<a3!b8o$n8+!d:`
S\kq?e3fIf$>[i]AotS1c!UCflQ[l!`;?d^P:3]AY)J$42otYU]AMb]A\[gs!(*0jZ(Q_8RM.K7#
Rea5n@b]AMBf2nakn,?>j!uUbD7s*AP,'TV(jZdT(Q)Ffmf[FiiYaU1oRgIWecAaG?dRFWnMA
qkd?eXLqM>0kf\+URP'GUTiMm[ek!X`G"Z.Gk(:*TH/c8TB'eqC&afoboV:;[6It6@#skRqi
&%ok+<gC#VIF:l1`Y(fu>sUfUY2^ibTr,)#J.t1trG]AW!<G*037>7PEXu1EZ4m51'r5*5;&/
Q8`IZ-?g[OF:M9pj#Mo_XC'NoPqA]A:0G;nA@^%mYTm=2fglC%/g;.Y$<afiDE:%Cq^[o<:S&
.#)PkR151/"JajA^[8^o'2Tk2]AhCY8_\"u252abIDfWgnJ@9+;J_3RoC6CADILQ0qb.i6Brm
[l?6,Y#$g9lVK=]A6lR3W@[(;%&<1n@>7Z//=Ok%-3K5..`7V!P0::K5;.0pI>J7B\Hp8.d<&
OZi@C-qFaVMhM@;G7;'nBMTHsl5o*8GdN1h_]A.S88n]ANaQ"agkpeC-%BI5B0-?O]As[D!@YF,
7)gh%)&8QtL*6p7X$M(jDRV4E9@<Ts9Y!Wt'HIdg;>u)&s9h=SP;fNOQKe^#VC?Fl<c.Igkr
+ENnI^muc;FdZiYFM+3E:?+0f5je)Kt]A47+N(':aWX^?GEK#cS19q;lGr!^sSJh=M!PKWVl+
+'U1/&^\.$P<'ED`cbgY5R(5dufUt#l/^u.4eM7f'L7)!&=B3/3LKTb9)6+\)7`$ip-Vb6oG
(N-:>-D>#*s/*c3:$g^t%T5VDsL)MQJp^VBtU:]AE>43]AqK!H2DPKR`(D?]AqKnR$`!oM7_0pf
cIIUP+3]A["f&JS]Ahf+Re=3Nh$\7>?&^MLbJo+/?`oKfUb+JF1hq?EO[9/KI[`lE-MH8ob'O0
$R@2#:?p@d%*L\iSfC`]A$*[QI&'K*O36L7&r%ZIQ[)Z-0qdANF:PeT.pB"HLllYD-;9%>-Mu
TCO;(FTM[(pMdmKtT!n+E!\7&9n7-DjRW41q)c43mL<<S08?:JB+pn9g(D,kABb*jcq14ek,
i!P%?%UWd<6_e]AH\ns9X^UJn",hMq&MUcagHTBsY,]Ao$G7r#Hk:-BgA'<>4)<+/2<q<]AdD;.
GNneGfGr7G?tp'WSO[EbYNWmdNP89lTi36l^B`i9\$LgDF1NKQk(l$p?kIR:'8:!ZXZ1*c+S
-F=2+2kgA73n4X>T3j,4FIW]ADp5lj,N7M@7BCZAoAKJ,>If79j)mb0#'t1(7dIn6JaI)Fj*S
Y_?Vd)5$RO>G'.]Aun,N7k"-c3a[MB6PscX.L*_qYHs6f>aB/$2<\h-jmCaI>S_5`BW@DYT'/
T)\<(jKI)A`:QIst/S=20bZ1$#n&iJH-L9=;?pLmOp%aMsV>[OA+G7:#3+Gki(,l4sRj(abN
UNOoTn541*nfpjl0qe(!ibpu.:Gh(E(j4Lk()j[BkmfR;Z*p`je/?=]Aco2"<T;)SXp48D]AZs
d;GrD#us,c4_W_\-p8DNndDW@%mL.P2neiMXQM/-3RE.3k]A_*Ua+-AK-RX9@W0OU-7cF;`;:
m67<Fm\nLPs2fmj<G.'8$YuDjc6b3_7LK>&(tE1n/9p-I8D=3BJG47o6;fiFig>2R<3VR5kS
Xk,=*Y52V'YX'o^?\c4O82#nkjF=29Sq7!toLRB#,ADF_dMN0*V*/kS-0l/\d:!7qMVQ_1*E
&g0i^=>meD:!EX):\&2>:U`BEqm`rXj/r=raO2,"Pq;j)s'2mQ9pq<"d[C-=D!R,jX#!'I%m
K"RUZL1i^X^oOo-L4/`295!X%Ojt2D;/h9<gj0nFg%(T"*]A]A>I;[W-Hqu^%"!o8AB=t%dG,R
KMl$%enoL*DYLn4k8@'44sCHF/[X"/,mlc9"VQp&F%,+rCmFeX`chAp<2Y0V2^9<HBlf[HVt
F[`4Gr;+Iro%7j:/Kt)bZsX<?$A6co5J0YSn^It`*\AKUrn*ju&P9K+meTaGBtS*'g?]A^7)r
mE,Bn#(c!*HR-F2U.6D3D[(32@]AkX+.2@lA`PbPM13#:=WG)_"H)l=o;B\h8R\rStJgb`)1p
fYMH\YT2?Uq>b>;4WuBWMoGJtf.8C7$Srn.NCY""2l*YqsO,e<(3"\CddYU\J3;ScqbI'EiM
*D2n4.6,QL_pNTWOe9E('`SpYXZ':4[*1UdCt0UhjlS<n'c7%&1&J+pbPM$\gg7QA83JWmbq
a;[#G`C0DG=:K`/kd4sfo!5dl2-Wa/pFGpc0-k&gG7h[tO6l_M9e:raH-]AH!,:->-==9niBG
T'E:38gq:$"^\iuK(0B.$`St_VT3nRYE:2":\uTShVgCF$$duSF_'7?1dB;!#t:-OEm^-H)0
]A@N19;DD;Tao!Sda;ab%RAR#^^'$",DNHif(UNpY%p#7n>h&G+K<9cptV%'H@`[UpULli1!I
*%S"(pJN2:=Ll5(6Wph*\7No^I;\NolZ",0-GjOLf;s&Lc4"qSHg_<V+W(r+ne5cOqLXUk,1
*>!j3B5q1F7@`m0XbhJ@_q`g"nQj,dtQ_i#^RMjV=%l'GfrnfLWG41Ys@5-c>[.dhcuiP=%?
I?r10TE#D;Un\h9LH$sjU]Ar1aZtk("&bAEH5%6EgVKY(0Hs>@?[A0lkM:OCgHuX)<JOqjm^6
4q9^frTD3Irp"*V$#_"QRB]AJV)7UNZ`U`ohN^9m7<Sd.fp_J&coKOGWT>^66%<<Q9$%Yjr+m
;#-@\e7K_bS(f7a"3]AJkj-E/Nif:Vh14#MZ#rqUlgX:2UB]AaS2UV'B_YC1C"RB'_)$[hjo_Y
+)D*D0couR`I^^HWX*O)]ARuu2&U\p[Y4RQ0`]A\ZCRhMRjuQWCJQZ0Z!rd-UMtc^+gMGhDKKW
c9ho4!;ULeP01_85GN8n9CA(ilVt4'5?L']Aa$qqO7FZ6^L+E/P".gHRk8,Rrd;WYR*V^3SA`
#RX^"eN+f)+h1U[T)E3IIt/qL:b,Q/+ipG46'o2QT7(_uG7e1/:G!pmI9SSP.pT[)^:g_b$l
YNY7tJ9XRWCEXqs0Dd.EfC2_fX$u]ADoCWh]A&Ep3Eqe_+r6(=MBlh<V[^6^s1Zn\eZ2Hn*;ZS
"9eLl]A1#%-#XmB+BVFVb;$k&+@!,d_9&N_U@j>[<G:>TaLM-YE`\f\=6.fKgC-p;<mE,9jau
,n_#F2(!g"iLMqU>(#Fskf;/,24LZM_M`U\*b.,6lX*3d#lHc1jq6Z.+Tc0C"J2Fu]A=%E'1o
9rL!+4_J#^;Vm/&UTu0)J+tBNX]A&j41*'JD/GIfF2'IZFr$OTH"HY2Z>ukJLsQqN'N8U!eZA
)\&:ZI>#Kgk/.5&(e,^Injg4o@VLf(95r;MKDpQ@93-[%/MjCj=m'L,\0"^!jF2pbkq__\#4
!NKiW^;Vg(6fTCKPDDqK#Dt6(<m)Yo['*U_9if&3`b#of#iN7S"+:gse`kV9P!UAI+`g0UdX
iX[gs7e1EqFNQ2tTEb_=mP'#gIOc7$FQ<o`tXgQ-kWLl'u';kR=3^houpl-\/:PhZN+U1d"0
\i";%&[VJqM`>0;N&=\=\q6r0E1Kmr\04JrFo_k9"5%2#NO?&tt$/eOc/S"Q.c/\TdN9K4r<
T6bV>kqS\L3ClBQ#q6!TPi?,$5_\Cqal5dj0A<2Z?="LJ,9?2"O91C'$cCj&tEe<(_$0\F[j
!`'3P*4=S\"$F3%PkE)2iV<7M=8QH'XnfKdWY?Il=0arJ,@$eZ[bW'K&DhJeJ3EXJs["B(fP
f&15-CiMB>7;O?@1b-_'':lkklb,6@!mQlId-!Y^CWt"iEb;gNWKE99)*>\LUuDqAIjV;fKB
8530;9WJ>c4MCnh2kIr6Y,CJpduhB)H(e-bCA1hf/G_Pc:lT:-s:<dK#h\.O4r8nDUA4;p!]A
2Dm]A*OV0:eID^g<=<Gaof_GX=4]AE=S?R:]A7d^!obYcT5Wh50I:j-$khsbQg-eBAo._+WCnZQ
/A^qbp32caCh--m`rd6YQS8mA**>.nUZ*/a4mjORNWF'Frfg#;.GN=b\u*&'j7tE,&3<S;:[
i2<n=K;P=!DT5<4#$S^L&`@-#J#.$mra@Lr?2*Rdo$,=M/=kK?&ml8NQ7kCb$r&[bqXD@+Q<
NB_]AAWe[^7O3$#`h!\&#@=iT_>q/UPIGp64EB3$j-e+Zan'7B;s&'5M`gM;^WKV>1POo7p:/
<ap2%br9r:sN(&R2-tM+H$bOsZ?YSjcgeDhG;</S">[:XonDqB1hU;Q`!L\jg<b[(a2B;O/K
cpK@SFB(<5^0s!Y=kVUCnP=3Z<!tP@)5Dgf;H:IR5Ba17cUSV%28OT[MSOD&WHGg^`p\`Cc:
+BiEd.iY7cl&m(aC"\08m[hg!lc4k)gqg`d!aooXO+J)?(Cc6oAcHe+#]AEE,TuM7Z(GBAot*
prHkRf)V[V3L-58gL\HZO_Z;!8096WI>^XB5on*.U/NeKCa5lG/ePZA4Pd5&N(jdj'?%3st%
WOS-Q=ckP>?'-mANJlsbbEN9Q54M?2oN)'^a?T%Zl.Q\F"'KaA`K.s[eu;Ec#H64h1CsnD0c
NU9-28NPY015V?\lqOW9/aQ(so:FK?&'j:5guF6f3VG"WXC<5]AV6'KYrWQ1?Q@HL[6sfNB0)
bDZZ@#cNG!>b,G7Tp$ASYM!m24-.Y2mbd[Wl[1J%^9%6G"SW4/ul>jR&qTit#9Y$gmO23&:5
g&^<?\WVe94Up/rcLoXOJ(m!#U)Se+^Q<_g>]Aa;+Va0tCOXIorQERd[OT@ca-qVsd-$d&6?\
]A8LYurcPORn#fj;VigA>!=T>J4\-pP9D@;LNN?40njS920&ILqL%q:d1E6Pl,jA)BphO-WF5
&:D@de#`nSZ$*<^>)3<<\lPehZA5mkRDCjld?!OH[>QuYf6/P?-'$'Z/RMY!.`r`E9-;[JV<
sLmK!(^S`/FZIrCDD0O^#rjWO_@W^&Bn-qT,ojbTkKpB;B.NpD8p*p7U_F?mf%:H,Da"pTVE
*pjk<L@!#,TUfZM]A0lrYc,/QBQ!_$=*7JU9^gWEQ`8:c$G8ICBas7l65M;dac1%MA]AI9,IST
:0jPrP4*VTKJ$1aLa1Ts$9:Q$kPp:LJ+FH[4%0T)EUUcq*@JBJ#0@sG3Z;t"epo:1oE_J>dH
)SfuVu91*dm]A!6QqVWcEj3<,hE`Ks/&Y">/@JhjIZrIt)IYnBIrPL5="^P]A0X6a4XuqQ%#PX
rmWHfhd2h<lkbArB>#('/GO;b/+)SN=?a>sfT@Nm(F.`f1Zk=9[I4S56gsOT2p,>'*qIS:pJ
'glHAD;,e_&jnQMca(q)9pOId`&@SXiSrrk_9inthpGmLl,'fB5@p[k4.C\lQ`>NjI]ARI>#h
0)=N=Z0p94*OcE@OSG3J%dqMMc<WB!WU%%kiokH#dS*FQCgonUS\U,I;fI9=M/"1kKThMTuV
4Ro7S;st9>=",+)uhrNPMaoYiIu9j:ZFM[V]AhJAUE@X!I(K,W)H9]A%[:E\khdW^+oJa@%'<+
u55!nrS'Pj'Yp8966De;Q@Unp)Q`>G0A^f"hCEAs#ngIf"F%u`<q[''Ll@8@`b[:#h%Ci^>2
/24_4dOG\PG1&(iksU%_&S[Z[11l:kNI$oj^_1P"#p_piiA+Ig<pJ$adCN$uP-plR-*"5V/_
5bV%/_9\jOhSnaV;_<nCru@*W'``\:f_p,&iXTc*95SjXNniM=4]A'B/Z"65WgD5/[ofK,SBb
W+-rc@:_&j.VBiI6i#,dthklQ0U$k60U$b/^eG6!!)14>a,,p)[Ce]AK7i"C/*J4!(h/`>.C4
;t\13hbQjo$G.G&aiU&3;VMVEuV]A`\83puf]A[KV)^?W\_A!Y!r7bNBS*=RW,f^Zu=7/gKEU*
1+cI_X^!KV0/]A2%gV:g3-6%7]AiP(s#9)ZF,',5mQA;c.gf=dc8%InIS@uWD^&H$!ddk<S6q1
H&#G)m)X8?B(j]Ag<<`A_OccnY%9UM6N,hgFZm4>83;rs[=L(N=$&;@jco,XmgUMV&n.sRrJl
_MZ4i2WjdU[6A=!p^8qERV`h</J[9&-&`9RJlHC0^c+@:/YAIH+_G)pSbY1l8O%dtrnL-B\f
9+o-5,PSVs$pkera_@%Y_dgD]ATbW@Z$.^IF)3,1S+kPdDuE??']A,_e`)'Hh"KKiP2G=IV;Ul
;puT<G@M3#N7p3,7qlJ]AYq7f++.JAeug\8**=f-gtU'Cae2NBK#q#j<\SHF_[rj7!%UisaaU
1fpCaP?<KbFGCh&OW\!U`'%iY&)Oc?F_bXW"YS7nUVKq>P=?HFX?ji8]A^PU#cM%rp70XZrN5
;pr%HLj&=c5d/4,G4-EQ?ZZNW>Z$BRs/8.$io'DUFKS453U;A,JHXjCW\9dL`2^$f]AX!&,j)
Dl+IW<B$6D:#@!a:N>Q?WXG&k.A3\*)DML70I3[hng\K]A9\^CuqCA:n?im'(8\sSXd]A0eF*"
=JJ3#0D2+@:@R@hHo:[@HThjIcVP(<2?=]A,P9dKT=,Ts.2Yd<a,%-1IQG;mgc<"'fGpn1]AE2
XmEQk#&%eYKWeDpZ>>$ps[+U7W60DP6$%$7M,phRRH^q.*kA:dt2sb-h!nK\]AHA72d_b;_e8
-.J_k"#[@;rWB=ld$Ede$rUq/,;$.!=(]A)hrXhCb),?#Ib9*R]AZl`pR$_g<]AM"*reQeX*-u*
]A1ltPb;rDj[m"*'gG4t_;!H-F_W1<5f>XHc2'1kM#!uI@3'X\fB[#m0<4q5D(BZ&_RZ>TrH!
n6mi&H[.Z-#J73^m]A>OLPgtqE9g@S)7O_$c1hu_qG*>065hdf5UkL&P;=g6D`Fj[cp1-IEdL
g>(qkiDfSBGI7YX16AB>mr"Ih.#&s-Rf(Q,mm8.X:D)+*obRR-<1YWoS4q8^*6pFhRKJ]A8JP
D!/.h8+m_hP#M-OBQ_VPNV4M3Ab<F_#6.4c%MmG$s<qM6?_8GeE;pI/6)TaLqPUg40)?+/"o
QGn>JELWB16$p)enHa^s]A961BNEs!?=uj7>KJhc0.8=ce?1MC1nVI-PuRkr9`J->-$cLg2,_
qmtOuEo++A:d='O3t7tbJW5'=5Gl%Jr5S<M'k[R4NpO<EL6d1BZMi8`j^#/1B(+%/IXq$"9$
`bCY6Ci-Xo"]A+=a[`3e2<fpH'*;59kRGTiI<u^Dj^;Ao'2a/.i%WeJReS^)S/_XFY0mS%m4'
8DQ9a#+@`M/"D]Ag'64jHURV:u0We:K.3H\s$;EYMoX1VXA:/$>*F4M>T8(#ou:e(r!9'kti5
<O/i%mIWnkGA!1=X+Yf5\).:gF+\KENELtl(X&gOil`>]AgVa\%l.YLd2q^u/!2h:Nm[+61;F
AB)`;@UjMgN9U.-P[(i$CJOpkqCeOXk+UD0Il:+$5)&ja$R"r@[1#p=`rL+kOAb+;?m\nJ(:
bli3(YkXdlX#!&FAlgKJ4(>,##0l,kWEG\c2rDB9K4k(KG>D_tG=5#@FqOPh"FWeaCk"6pDn
4)`]AOtfX*Rsp5I)B&@&M$s(Jp_dX2lT1&:ls;VF"2,8blNH:(mqI@pZZY_pep1]A!j@ElX,Y<
Fj'KRr:7p^3fL+Qo@naRb0.UhrV&'49c(!T0?WU"6gu6h'I"-=u6"04bY2N/6,4CZaZ,k=fN
N1KF#!T4B]AfDL+OQ&iug)]A6BauJu^A!O2rg-R7?gqWmO9AA*;HXlI%mfe]A=mGSj",PCD9l0P
&18COHIT0E!IAdsH:+G7h=N\SnF7nO6!Hf_BuVM3WrYAk"uGt4l&i?e%.]A+XA7(@#f5H_s#W
SBiA?PRFXDGW>,IF[Oqhbp4KAO87+m,9+os+qSk+Ac%a^8'CR.P8criA#:fN<atc@h1rGaG4
JC5YLPV@Q*u=CMjP/*"R3Qc_M,]AZ6aW[hYKq2fQ@$;7*&,-,Jh*`\7?P'SqeG/MD'E]A+%o5[
carnqRQ=]Atr-&7.r`CPdWZaf=#W<mAY_okK@XjWeWW#OV@m;EI^.m5(@e7Vd(nqOBV0/J'!j
PX8tARboUM71K;]A"r^+3Tk3\A-X#!+UqEm1X&"]A?9qqU^L.K[.d,)&1'pdM9W./'LS9-7=m@
[L8E/k>+=c`<S6@4BO;@7Z8%b$@TFCJjc,5MrPN[ku=#p@m+o;c&'Cp:GKIiUBin;KeWQ>N/
Z12VWir[)]Aj9LO2kY.)ZqQNUnK\2;`6b)N4;jr'T$Dt-0/A%AWefT6:4`GC)DJ7uXs!92krt
BFCPK\=sMqGcgNTU:(YQf*IZX9aDmTX:e18<^>nSk<+jKqnNA_AF?O:GDSH)#ar:HQNW/2<)
6??BC/K2G?G]AZI[o>i$lAF-)%`YULGMWHE@H?,Otj/QW;iX9OC7TCY-K"81W>HQBM,3et>>B
)#ZSNEdoQB=^[8N`tM5mdNLPn&i^Ib2q#BJO;J*B`uK>_Sa6f70+?c6N+E`(Rpa_`[M!sFM/
g9,AnN1fkHO\-("X<$2hQY6<1@\<XJ"dA=^9`Ir5P5d*F:uBL]A`@QTB5[5I2."-jf^]AR^'3e
aZM7nbZQqQ0)+Qd:R_.o.>\"/G#aLN7-/O]AN4ojY#a8MLd1jk_Q'ca35)IA&ccY8#9B[O!IT
*SFfJ3AbE7GEncUf/Q26?AII^6.V>Tk;6NT]A;HHW]A%G4Xp"0<f+KK+1VP9]A0Y-6o(l>6.h^9
sNQCo3GCG`/G%"1mLkE@8qO-Zb>FQuLqjK^n-k70%]A<<TS]A+K$e8b,BT10O?L8d02i@AfC'C
rQG0IC%YqE![:X)3"Cf7]A>NT!UjD/@f.iinWtg$B_-k2J5X=[pI&Ail4f(Z9-iTR>%PA-g=$
eGdoKPiD&o^&4fEN6Qt=<ML@g0amlhVCK7\'^,#21G`kf>.UmalP,>3\Q*:J\0+;B$(;qY<=
d^!;+Hjk5t"tHZJ8V:q,LMcOAG.<3#a*6qaJTp"Lq#>uq.lZOk"!-^UXb%tSiP>$Dq$(Tkbi
LLmr6uXM7_J9Hc-e+pKZAfC\@Egf0DP=T^Kf/EPoBGcFKMZpg2-e6[S..p@#%#'^"OdarD/s
1VO=es+03VrXW\TS5e>`Ye1Np%B3RFYd-nHO<UqdY?&0H@2$5lqDGK]A<ML6M\H^d&Sk6BM'`
n8BR\f/oT:%.-_p%dX.QL)(p(E+M>&JFN4X<eg6it"%KR/VJm3OL;PfWr17OAh3flri3S=B4
6.3RlJt%@$MC>89'PeK'7]A6a7lb)=#@crX^u:[r>ruh^3(RB:%^G5%!Tj+[=plW/"tT0H;0P
:mjuQ3]ANpSNHE6Del27@#VlZC2Jsmj5DsY4AkX2k=P1m,:A:A-0JCAnGiY&FC=[RaTjW\Q9@
>UaRC;JQ`t\qp`SCMCW]At>hg-h>220IWN%(8<A>)"kFa=mT7H]A1In]A?)RBY3]A0:pZ(g/*PB4
-ItkgS?i*h9N/Wigl&O=4EGdPV@<[;hMU7cXj>os*F?0nA1f$#5WI:ehR<=Wd_?3K,',.p5I
SdIMP_sX]A"gX/BDLl:TDYTN6]AkgZ[,"9:]A#KU=dNmhK40K[@[mO-Ut.i:q+:!6XZ@5![mU7+
8:(e-&t$gCi/19E2UOn)WP3&<IRiCr<+f8pf[V7-B4l33DuiCfbQGd)\CllZ\"G025$M;-:F
cn7=Z%:@pmqL1#u`m]AS?#DtV=hI-"+UZ#35ad%pse=Yi!9gD%OUKGQ#<,M@g7X,Dn)kMBq:\
2WpSUOq9(b$dDoFJV/:&,W\o@RE)G:-bbicbt@'XJ/(5<7U?)ll)CDQJ,+,jD_^8ZY)P^h^J
Ja3F%uATu^SEmU%m$hIi;[DeF?>\<B]Ar8Z>F;trGq,lTpWlIHE9LTp\EW?'I!Vog"Z\^D1iW
dK32g$TR`U9l>>pDp99BkLG51-a'PD'\f$a08Y0K_8!m\Fc/]AbJMO^J`d)pLm<m'QlN<\Yg4
T``^\ouJZU&Rp8'*!06=qM#ZjL;-<Uga0+YRn_m@5ePp*_g0*HSpWIP2A[&RjGa2^g(b=V>;
ls80@F.]Auc?t.":8T,*peJo$=K1[;(a!Pm1XEubFSpQrWqcakc\G9MSW^VOJ%E0RFA[:s37"
=V$]A93%]AN:5?f8klF>q=]A>?7_YJpD7M>i8&js*C0=o92`OckL(gFs='s\7ohb?@m4HF#ImQJ
IjeF]Ae7X"3DQ:WRFggAcX4R5m_WPKeA\!'BbX<Hk;,JG[o6TPe==DFS:`iQ7X*Rp.2cqC3YO
tM$$Bp\MVBBr!Aa5/(Tn$]AR7fFKk<L@F\AbKihdRZUAJp0E'$4'ZjShIi.U\8D@Ub[;AcqfX
uHgK98'fY)U>LI6B]An3*D/dcB`9.g4gnPKdl!M6Wb,Ub.e.6s71(ZH7>o95$sWo4qn?<m%Vd
_pd;%pee/kJr6TAl;\k'A_[<tfF7G[R(\-oIb8##.C=7\+(pn'3&;$h=Y2JB;`@0hp01JqdH
s/>)VM^rgUrj:WKc8iOUibs0BZX`Y)36OU=>#t6g6.QQ/jp).R`gN?<+,PqnU)MXXKCfHU\^
_a$NV$/D87$$h[MVJ0hlmN2,*J5'QMG=U-Ru83k_3cQbE<H?Z!]AC(nWT)ufm=p4skZ3ii8g4
T)&&^fI&[Ca\[hkD\2U84'V)oK5u?B(@e@VDiM6D3W\Jla9B6G]A+?fo#]A24aN%dXpg<TjP\0
%[/#X*3E_%7FGq-DO9H(2/Hg,,*^[ZV!m]ApFJLO6!1k@0>^R9"pVn)j9b%0jXaR.W!lC"R5N
XUq?<l@9C`FJ$Qj3IH*epgD5lilD>4?gk.Ve=>)Dn]Aakl^41!es/8:NN]Af=0b00b]A'7j6"2$
p1_0COCWFCAo2NP[dDf@mU)dW?q`8uGOUnYDXh+kBl_o_-=Z,HdDo-bqtpF3&;=/(`k5NOVn
S3:/,8U0J=;k<t0oeU`/tB*MRN9!q1?Iu44F.sIpsZ4GfH#IU'Vq\[%]AM6j!&J)'>QZd9%G;
-8*57L$n*GBSea+cN.0_8"ANW\`7S)#:WN$$aYuN\huUnJ&\'RqReOA<AC_O`=YF5PB(QaVl
KL9r2#*W-79_'4N.m!?'k9UTKC=bM?GU\!>E+X8hf47reR"Dp#/34`f/&qfs9`Uj8h((:!d(
:2Mu2k:)EKOQ?rS8hRhfm4Jjhmqid#:WA>2k3Rs_r<'-`Ia[(Q'to-N0:HCR4S4E\]A%L5.2F
Z-;;RHn&$"AApN`ofBn=WP>g!(C]AF5fU@IbQHQ0D64#[W9A^=Rd4\.HW?k;&tRgCn_c(q4m&
66cG?KG7AO]A`gK4_(<!2Q?79t[iK1g6qL:,23K*,0SSAcIWGT88Ss*u+S^QA3*QXDW`Mt[d\
@UDFWG<O5OUV?[OCneK:+#=P\jpU0MD8sLA"N!m3M.C+l^<q`B8Mlp@2roQMB@Um\N-5Ial,
f>jk><EWsgoR>q`8)7#`SB1/V?X)(b_,J[3!-*A*US3j2XG:sLppKVqMtO<7f=*3Zuc]ArFnS
I'fWd:=VD*^78tX=*<RoH-UtZo_HIMY_hCL++jLCYo%cNqr&R]A;!lQ"=>]A9QEB+;`]Am[M6V%
n"PP#tXu,X%@;X#U,SprDNJ7('SPB;5+u(-s/E\S5dFakJ(NTp>!ko(kmF%JtmBgWZ8Wjf-G
o]AGt*EI^3=F4diEt?t!'[bm:RAr*BMK^G_XaWf;C)0\2XXjc<7Cp%mJX`neFjd(nINH[)tQ-
Kc&/i8gDpoT=^S0qX=!:]Am=\q"D_H^mH*^NN+p),`D,aTj"5@&0Z&rUY=h0NZ0?M<L^GIMH+
BA0a-h\it^/(#pI/sUsC@i=T^>Tn@.0rXW*mDEp]A[&;lObZ7kZmbVtKHa8Sl]AYbUr'0C^RM1
%E@3d$H=FnA[""^nK[iGi0CR<RgT#[#l$OiK#69'GUKX.!K#P:\R[TI:\r'jbC@h$>-Thq$2
3r+\=E^b>[bdZertG.^?H@OGN[4t\YQl\OE2c8hkEa6S+<61+Hr9/]A\hrQ7UZ+KeFJpqZ!Ah
qYp@Jcs7t_TPR2<+7F0Fc/$6X<3GS37A7"R3jahh"CWq.lS0?]A'.-,[Eq\<\BZq_YZb4&Sr1
4;`>!;4Lmn.lG[<bSc/J5#[`c68n6jlu72\tH%bdnDCAD`3\i]A@U+C591B.Pb(2&]AY"KK\NH
nbdWi%`T^@]A!jgba`B3-2P>2u4>Pl]ACrf@*+[^';dCOimUcP(@IQp@.be<%WoF30Qu&@$=Fb
CW@5Z8uG)$JiL+Ad4Q]A..]A]A[H=Z.I\ZBOBEVujoKY`Wj13Usr[1(!()UUGK1Q'Jc?F;K:_j`
(!,"XC`0A"hOmql=9=FeA6[>^[T&$A*uMQ$?&n+j+aMZ=VGub_6@+`Y)NW!)F1<\/'Bu5-+V
U:)?dI6A:Et+71`WI,7^!f+F#]ANtC-?\SYQ)_itFYI@O]AanUW/>5=h@1o!FuD>]A(>^/JSa_d
3t";JZO=\]Ak!1cHmM@0%>IcIk>d:-`as?`:#[u:8M!X`=;'m2lKmH65FFin0'-C[G_(+#f-\
7a7gLgWjb'A[ot#&!A*ja9#&F,LK/l=d%e==gH:KV*4b:/FrD74/e$:/+2@3*aa3m[b*bRV8
[ibKI+p#a)&!=j:r6D0W;-<F2"LYu:Boe<rH&n/12Ih[,:+@"<^WbBZ[SK1US\6Q`Ptn"YR9
;i?'uE"sf#sQqc_Su<<UG?1^R'5?f.KQgkSP+\hdi]AT/W:,I_q\2>SM[I0\\B]AP;8/qi2"ia
37'`kMDC1n%6#KjKdMf:V[>(\9[hQMCDTn*f[0&=f08W1k9u%99R(OJMlYR2HEcq^/.?+js,
TI;k)Q1V*G[;S/oS5&-+S4WK157WN>g"_JlkC-m!\ZPDnu(n$rIJ6s9:t>EC%IM&Fkq@RI9C
s_[c1uNd,A'khOu17@nU?*]AE3A]A,ij@+QMUf[*)'=HOWS/HXckkc<UBdZ?+AdeMN<47j3\=@
;gUS`eFc:c8Z]AG(!-'nmW:kL*o0bpE(t;kIA^SibBd.48,;AP42)WmLaK!BhOY(D\pGRlkB'
ORU7$t<]AQolshjS+58m#D+9ChcRBhP/iYOS]A7DCcK&_mlcdulk7H^c#fSMlpm(h<Xfrb5NN2
XrG187fng4SbY.h>p!,3n.8!.i7UD*TFNN_2/$Joafl[P%=uheRdfpZ"[4]A;CV.COj$A-8]Ai
b@F7q'-AFY?sQ7a\lA_rOb$Cgm^sX:-We7RJ?Bo1lopEY`fi"]A8m&JbJRTAajZoS_Ag&\pSV
l=OZ/'=kIWd#Y2$J-?U/[q^[6:(.V#o18TiL<r8$riT,V*!:=-)QTCh.:j]Ac4>H^ct4ftKV+
nV3$r5NPO+3Y-AcrL$&12?Io2)S5`@<kdl>c^\:s6r<1X[ZMsQk2E693jS?1f(IS0kq!/!o?
R<=IWjEZ?R_"g;J.?$>(7<@C.&r=%ab3"<s54K6jA78L^WU6iAB!67*]A%)?`G&$/ADcuZ:<%
!YE+fg*`bbh=H#p3'<Q/-WbkII6hrpqZfD/G!i7%Ak&2PFFn._k`D19mYsS36%N@RYoLq]AOO
+hA5og.DUfP0B!'E\a0-VLc>&ZYI'lZ$WcfK'1W#Yj_TS2i<KeX_qbfI7Y;/To(h;orQtq&R
I[A":5RBC^uflj-o23iI0s5gtism9HQR=`Oq#dQf%@%P?2J(`Y&:AU<#3_G4j6ZdbUY6LGQ%
ckKt5ms3=_qcMoEm%HQ+M!&"AXW_<$6Ku5/_q@/:?!2Rk,3:gth??*A'QZQ%p.cq19ef&=<[
]AS_Dt6%L+Q)SDP-+CO$l8/5DC'?#=_C,&a&Pf>%1t6Y"jqa"#\%Rs$l#oE$36D#-)HS<eSj&
C9Yep$\7;nY'Q_/J(!,UZA?RLN>*nLKl/XZl>=SI.WF9)\7Or$bjI&;/&LU;)?R$-0OmhM;o
VV`^jnXWp.&ZePr[?JYW[6JXDr]A;J<E:>]A?I%?$0@\EY$4Bu`)g0^DY$*l7TJ\ae?>W=sO'E
lu7+sC8N3gDl^C3JHXs@HXdVk-Vci7L)%^VMbs2b7#2d.5tQf[3=m9FsYE#<F?_=^,BXk\W`
%_:IK>"qNehqW91q`liJ8r-3++7WZE>$HSs.\0/qLcG(\#s8UgT6$>+f'RMZ:sD(k06C3b)-
`n:>0(?b<1&2\Y+@Z0X5VZ[i\lL9aq%G$l,>iqSe8KW/R'2V,uD?2P=kX:o).Rt'j&*ojrPu
8OFr.`>%Jo4s6QbPHiK5PS=.AZ-4K5'-QS1TQ%,UnU\(MBcY#hN;6=b!@-)0O`F":,?bMNnJ
8^Z0UH1m"5Z+;#A:"TSTS,)`lLg&Eq\4]AF2)J*.Y-ZC19T/7nQBS%5%k@>i$b=C"V^J4nj(P
?B?&0?`cgY29D2jhYaVZn*=6o.6%=rb)JDX=Zm(H--g,RWLZ#;d(7(B>BbY&DOVl4O;k*@N9
1f7nj%TC3"m\*3DkO[3Us/t,;qjIF(T3gd3ZcUdfAsEpKPFW#Zo2E)_X0AKKP+Uc?*B+eQH9
AgIas=TEF9<%u7S0fTN:)dmSRp[jH+M#^j7ENnk()`W#uf(\I2ED.:/oIrB'jh.;!FmTjGE*
d(W4h+ArFebSt<fFMArL:RjRR-Q^[A':)LpJI!W#j72PIAU6'<cG*hSOYLF21nW]A!YB9`$YL
5&%+1M<*gd0%YJFYoOmh4p*#q22UOaRediXq)DrnnZC/gMpruCbV&lnR:CN6:a7YY[]A7?&lm
,K:mXRXMkRQe1l4+BrK(ONAS[XlS9?4k0,3#PQQh./Na)Ub`8KC04\[]AS9H4%"=Y(pg]AC\HZ
pY$$Z["M[q>$i.k;MfQ32c.".(VW0Yf,6Q+?;'l-@p2pt*MeAAq$(oTY1)hr,o1N,pL7i4<+
1EqjuFoNGNPpfgnCo>m8GLgakeRuB9M!%J*RI''Vn''#ed4u0XE9jkuAQ`cagl5WiJ=^D\r_
KYj"P&^RG\K`0h?k>^u'9V=AOU&?n,us.hWCk%m^u'+Q0r;_jVg(NQ)FJp-_`Bjp#TAdEDGp
f@;aj8P89TTjW*F70QtktK5blp^B\)(hKkZQ#u/M\p;JSCe!MiXeBGlY[G?Ij/7>b^scIIQA
(&ND&Q[12SBrG0%KH!aZD5`1HZP2=1\.@U9ccIfZ9!lPc^Q_ZWh74B4^kkY+&tr!@5=m"B`\
Ks/0H3?a"qb&b7K7m#aID;fRGD=b7gf8QIM2S2YhpEf)F$GC!g?*iYO6TH$r4@("rr.J!;I<
<p1oQqDcAE-l85d.[^lK!%N^[<Ld%#YNW]A,h3W:rEXeG_oaUR\5hgDcaLMFo"_;CH@_A">%L
$.9o/ZCG6L.-B?f1<`[VnEUJF2<4KZ-:Vj"LQkjIJ09=UWJM^YW7smDO6;XY&1F^0T%,=mX(
dIFrV3Wr?rLF++cn=Esa#TsTqUj7[S1u'E2hi<O-kjQ;NM+SPkuqFr1?LQYN+r[P7&9nf;Tq
`YCp-9o'nuNRLpn'U;#6d`T:J8!<G0<EIqh!PiRsnqMWn+Ift4&`"2,@+ZCF7alNWJ\YLG43
93nsaT(fKbWof5Am?'`(QBJ=pf\beR6L!#K<h9_,;GBiC^t7h[KFZ'n<Lk'L>(/WLa-/m5k?
Djp(q_c'JQ!Ie,\.3f8:H^tY@aQ5paSQog(I!MhADC/CM!2'9Y_%WQ70".:&0omLb!Z:^3*,
aT2ujH@OqL(-2f+Jg=tSe,DOJ&,k8u*kN5?>Q78P\qgJL\-#S10YP3"K^\l:e5M*FA?<<)EH
T@o"e[U07(T'p=G%h:I>SikHcIfSToUIHp4CTYf8pBJ-6cI9K_=COLN(`;o1,dh9R-m(PU@\
eQ$,h[B"Q]A-66O`-30?haM1p/"$RG$/d\R5Ba4_s4+k%;aU339;7-<te-M&4KYYA-o"3Tj*T
RA_qpM(0Fod?.9Vf"7oN\&Shu-DcGA/NoB(X7<:+;%>pFqVP'Z4f+#(h5S%FUs`]ATC%(0'Vl
9KT)qrEffI=9ET1JKgb,2CV8*,JQIV_r&c8<PFAl2OiP5\[gY#g<Vor3nPe(oQ-B"]AGKq`J`
85o2qLn*5<>GK1_YW&QnP4"lU**OXEe<#?;pYJ?,uqU!>:#Qc]Acqu!*<Om3tDT0!(i2aY<(p
@9-:EH\U^`#*9N1YlMg[OZ4q(nlY7<DHJ*3/IZ_U^oMeA=WrnO-tR:ja4'`rSFhr,Q=%E'dM
YVBUNI$F)XD+L\@#c<OJG-HtLRPbGm$iBtie]A;4B%$P?Lgs"$8JnOAO"i*9'$sR^_V[&h-UV
Z(rT:@(C'AheaF!N>:is@Ge'i[1%ndTu+@hcG?T]A4'$5H#uaN&bY@0_WIU/&Y&W_TBi<e$,%
Nd7K[aA5pMn+=!0DGOV)m`N,D.eY:kl%7;#&pI!)%a!A>++TKCYN"g*.7G'BF1h6UKXiUT3B
k=^PVtR1DlQKd:90e#X!(Tt%r2KQ,mSo"CL0)7%5Xm:jXGGh_K(e*.EXd6o*#j7tLQB[VIU!
QHW1l[F(AGtZQLV?01F?s@P"=FCC<Z13gOZZ>ugNbk4(l8Rf+?R<lSe\8N,)'HrM-HHr7j&^
1#UW.*id#'8aLU9.%h[=!PB;/?iMn+Khi7A,uLS4V"K7p8/l1F;MHe@?WG?$Do=1"N6.O:Rk
5-WfSBA.lQV@f6J;ssJ3%r+=!9!>nZYUZA'"4?bU%N$tLGt<'@@XJiRmAa?tBW8"16VA$s1+
+<KMLTHsIe)^t]A44ng!J.GF+";"eN\UL,-Rp?Yh9o/E2k3)JduOfiNJ7%QT%O.9mSLJ!Sm<:
G(NZhkhu&4S*di'(2mj0;G<nbD&s88]A=fZAG;O5>Y4C+j,;*c8K`?%&e[-jjt!oDMcNPJZ1(
hADZ3k_(Y3-R&+MgT_+F/CX8c5iu2P,<"*?[d1k?-EqfT3#q4WprZNCRE:k\MpfU1bU@Bo(A
G\p5F@:Hp^Vf+GfJ=VXrLDJ%XLG0uGSV%h(:Tl8,OC,OSOV1Bg?bbjn;MC%30/JR8a"bF)mE
Db&&]AY'YH)#4k2P75&VAC*PDT/&6a!D8%:(Ikn-p)60->#6CAh..CJ<M)WUS;M=\OF$UA*1b
rFQENNoLHV)"bq6dt/-q3!Jo#('aXm+*;8tERJ`3c_Io=B(8DGcc#^p_,-C)(CG`\99A3X_q
[OE&\[/^)Q9%@*-/bU3/e.s&'SQ#+*;5XQbW1Q<$QDas*X)9c[bfI&/d5di[#M^DQ8N^GSai
2SA%]AVLWQV;PVVcSm*MgabaWeKu*q7\=?#LSRZ_Y%m$C3GK<i[)l7`iTbk*Q!Sf&`4[B\&%H
7lk]ADTgI;_o7E6*ET^[0G$#hjs*AA5d#D(F:F.MPf(E)9ZRm?]A%bar)POSQ$"t9uhHq`Dc.L
LlV#W3sjaok<7jDep7HPA6hQ!FC5B1pmnP2H:09@*&SO2%)sIK(0%9t9DN_jlP=,^[`1laC?
gqbiXsPpNJu;R)`'li`u5f.rOsoF1,j9'30J:NfW/k=Bm+6R_^=JqOb%?7S&#AC:G5]APBXM/
l06(;Fo-\;.p[[D1'GH:c6(K/$+dG%0aF>UjDe9S\=.-M;*=':d)[F=n&HWnUTcVC3^5c^>_
$f"[9&Ts,GQoaelKn5,`87phBf+m%BNJ5%rb6FgVKD"0"V8?qfi>0<8L!=hdWlutmVI+l=2O
Fm^1DTr^*fS(\7-i/qVpZEQT&7jF7(:G"X4Gh]A56Ip8h)t/=%;bpEgS\,ZG?eM%bTd.MmV!u
%%Vl4PnBjZD9"pGA*_:SU7>HT8b#X"U6GSbPF`bc0,@BF\3nF#$i*^_TUcY3BanVlD2Xs]AEZ
IX4fn^iNqQ<L>.u:eEDrWc:jJpe81uK`NPD><H7`i4i:>N_hkFVWA1t'6W?_kH'lLqk?At*^
8>-"P#.^:^Il((NrY@]A0P/gWVU3>6%1]Ai*(20b<3:UDKEQaG@=_CL%D<55Y#\\T[DkWjEX[l
"ZY_N`IB_P\MH2V#"?"*EZLj!GhOuZ+$/dqF[K_8nWk(&NO]AD/ml%N2cMcf1aP@MHCLLOp<N
tn)W.J<2I10W@E:YGjKRhG>d-HSl?)Q#YKo--&BZ*&E8T<R@sYX?_Bq3MEk`?A>';%Un:XYg
.Sh`'K,.7;e(E$GhGeW]AQR/p"RpQ-$9oDnmd9?WS4GrE)KR%mJ[gX,)T9GtO:`T^Wm1,LUcU
M-(?moM`;BF)\cIl[pgoMgf=/p%Q::Vgo%N=G$"fZ@a)B,Z`g"Of2pl6aS@^ZY@Ztir6m5ds
+8H<5.=1&8-2.;/B"S9\AnY>pW%>EAGDtpt?fsPmqlJ)g1aaUduTVP8WOO%&cJ!b#qeR"c_4
<CK?U&4X+1q*\FWG)CbDC86B8+PD(oi\hXAoR.o2U,^i0irZr5Heq%\fD4hg,XB1_S"6j;.6
0oD(A$]AbHCb=(@.ZtTN@_*rbYH?R:P8O>?`<;T#3,G%\Ul7R1HA'WA8t0r"nj+Q+fIg"B&6D
;H>To=0!gJia^J_!grCE>[Kg.7@!r;Tn\AfNZ7#hZ-JZ1Nirr)q2lRZfJYZcF[LjQhb/+NpX
6'ch&7j%4_\10,@77P`_(c$QMg1`/slAJEfI(7^&$E0mod_O7eJP4i;.<F.01>#254iiR\m]A
+1-Kb:3G]A;f/unM2UOO734*o-^4mC`1]AJ^;VG7RnWRF;J;D4R@4ETQn+A/^Ff<e.K)/8XZDB
F3N`)US+H[`:"5O1dVP6^Q]Am:^iehK2pCQI=g1_X<Wn)[rZ="Z$V=PoBm2t;L?3(+6H-3Pk:
'$WB`*OltrY"kY(9<13"LndMeR`Iq$CRN_#s?fs._0WrMYZV2EH^5WVX&d8#s,,qD7QhUVMh
:(/(Sdk'R:d`^HGI6JJ-6*Dr6C$STFah*q//ZV$)+lauLEUGiE:D$"GBpZ:Q1>cpB"m'7%Zj
.RkU0"DI.\jOP<L,#d,54"H1s0?jdF<&WU&)5VOiJP9.%EbVULutR=D*j:PS]A+_9.>\%"\M5
?EU0V<a]Afj*8qruc659lTY`)su'??QLRtR/EP-]AP\ICp:U6hFp$eU?UMMH4KCGA=4SGY26!2
PNg?8Q1?nb:hDPA:;QV#@q/dB`dJtjl)'9>6m;=Y,g.NE]A]Adks0=a<K]AB/G3HO?.;B-4gS@_
,M(@]A]A."YP^q.MXBO[-j=a`eFX@71mq=k>)Dh+EGC(Jf<rC+\t<:9r+(?"I)Wrq/L"7/en'D
^9S0pVPfC6A4i$hOaB#fok&#46k6QhOi</fGHN&t!Di/L@4I-GXb0[e>6h34rGE:</oGfKRq
uA;2/@nQoN'a'>1sX:1n]AC?j3Y3?fnBu<%lufB,Nf?*Gk`f+6#g[Sbi,rP?u9^`fJa65XQbt
%)EFeamV5&]Ao>i5(Td#_pn90&L?lrS1&O'"O*$3B)oSpp.kq;KA_4lhJ(FQf9cNs$c5fsfTr
p7V7[aTGSN=ni<ml7MbRHdF4#lX/t'4Qj7Q5j^'P9CdRs(HP6WF3H0]AKEDU@!iT$*gf[64GC
2ff3A3&Y]AbXfh9!hB%n96hb"L"6%pmH1aLs1'3fM7b?]ALXlJ8]A2;\dLOo"2?SW95BWTiN44C
0q4F#]ATXCQGL!_)2\T>QPH6R->O=b0qm!.<8L37mVjCkN9WC>bndh-O5^]AoPo_^_dQ^\hPOD
6XN/8h,BE'nZ$Rod6fh7roCl<3S&)71K0YMf+n9j,o2JA".Nq<@AA[Ia<5i2>!C^FtN-)lcS
ROk1*LW,LI9#b:aokp^F!GK0[\q18LJrk,d9[9<Pqm:^d*g$Cq"HA6"GZ1PJV[%ceO=!ML82
*4df1ht%cj*O8hqRqY9G*r00=P439rsr?&*nSo0]Ao2@7+bkodgBO)haDGiW.Zn!GQfpmC8-4
HLCo>0IMdR]AJW[4Bq@C-=hg,+NaWF--S$OK@:35$.ZNm`M:TP]A5k37st%PY`d)8CaB`g<S@j
45Y+.T?lsjq%1G=hKQ*rH.MkoB1:T:S2[j*<q=[Us$;Lj/V*j'XFL!,o\`CY]Ae8/<Ap8&E]Ad
Yqm9,ArP0\WRoX%P*h7@IOCqQ'RtBOOS92)51\8Ok8cM2MjFa<Rs,-36b>nT'*:\Mg"L1`\0
XN99qJ2EPgfiOe;=5PW@1'A22Y1u!DnSZ4]A73p!&kK>;B7]A@^*^cp"'Sp`\:;q1D(n3#,4*b
Hos!AD<<[,(3T6j2EEpVn>_E\[G;(^Qjj>s+SiEcIWP45h,#F*kHMn*9C+<AN^r`d9_UBs4b
*FaXY2mr8#G8F6R31Yns'i@YBu&134.a,)]AX[LN&,dCNcB$SfnEEZhb.(X\g[;p?uY[9-+Dm
$UXLkbBpb!Uk1YpZOM^kTq-(s5ocXTP/5L<HF9D;eD4=PWunIE:@P@u;rK\#Eb<eLbLp=dZ$
7(h3Fgk;!D.6P'7QsF`p5^B-8.rXH,DEA*BbOLK&>AhNo?B,:(L6Y`/K_QSn91X+9tBrGX?+
(5703i%+;Y>2"gt_JS>[OA"KhF(=M2C<+QD:J/s^SAe^-@6#<';q.F[-T&hA;*J=;YPpA:LJ
L26%XY]AWr'R0F\]A4u@bPakVkmetNG68VP0-e@OhN^ta1G@YpekOH#4er+P+Kp[A6P4INfr/&
Mcjb*UR:HL3,JQh?ff&I%`hA2Q&+2)91/7"c;7^N^T)q>sgL5@t=I]Aeh:3!KMlQg>PV2I>!c
_kUPg#+h_f&,=g`lTKPT`tN'V+G!&Nl@D*[_dTIA:^=Ns$Z!chAL<:e5O\Zu%an!L7+OH!AT
;6<\a^%OS:26AaZ0tiF1W;tQ>M!")t*%LKJ;Y[5kAM!F#T2&cI!^tRt:WAL4hk)EWO:J:l)>
;K:PbF\]ACfG[iudT.gEXl#Y.'q>/[?IP#bA<#%*&3F<#>3@ee4:WbX3]Aq-7L`\PnN@3[I@?8
GNo@A)T(1j;&_>/p'Lb0C8;iOb3bWeH^orZ<V'/S,mfRVuN,ABu9oIiHs5dS#QWsM#qN9d,J
oSP!+OTN&khFe;Apd!EDbeYj0:$K<5-&ZJ#49>Ur!JVTA1YhaNUshF/Lq]AHoI>,q9f:,V<JP
If[efi:;NmncSo-o=M3u),#&MCAkJ\,O+"?*(:XB3Zu(hZdU^E_YIhl<f/3[ir>ZS$]AORY^u
8-(%6p(Z\Ir$)=e8'//=/.-6E5ZGh?A$r`fY!fZQq"SBfKt#&g/_TaNll@f;W0F-#EUO8Bf+
fK?-HQ"'M%K0!iBD$(:@G2t4"\M[?MMoD7iT99JN5rHXX?3%&-:MW,*"oUi9bP!0VuT>*<Ms
+tdnJN\$7r''D4]Afp#s4!?7%KSubWWY*3qRrC:_Yr$o']AB[G$\M;S3rbue2TL=1e<p>FB;Z$
C]Agg"GkpY:/)i&BB$ahRmsYY?AB-gcnM<?7UHKGfiMp`)drSu<Pk,Q9bU:LT#Skgb;bcg)[#
2(2:aWf9#pZ`VAHKqludGbN.;_si@qgJLG>^FTr'4/EO`)g8%$:5"uuX2*HB8fBd*%K;HR/g
:_O<[)R&Z<S=9kj*iq5JjYU3?eA4OLkbcHTl"10RI'qM`m6[^_tQ&]AD5,XjXt`/q$QM8n!_i
m1E0$?>?k-KNt@]A7bt>CYoTtBKPq4PZRk6KHUTDEN_C;8Q(WE[WI=Chb2r'62Xi@D/rUoHSH
YKaqZIrF`.!W;Tml1$F3hVSW>,BoIEKa;_dT(XFjO,FCg+coGMOb`Uj]A4S4L'XHZ')`@KO[f
r?_7UP>L8I)A%$*se*(lBL<M$9YJ[<nR+K-DCSXn$&]AI\>FmANURcNh]AiZPHSQZR<.2:=eI?
<sjaf0!`kR`?C,`Cj#ki1`2;0i/FW5ZU-YCfdWsJ7g&5sm0M*1\N@&;X<uj8$NdNi`(ltW/*
(U=^*TIm<>m19,PT7[!dnQN@26\T43H)uI`<ND]A0F0k@R28PJQ_Ca3L<A_a[V).bRqNW^RGl
"X$0ZDC'nq<O2p5l?"m!h\B-==\aWso,`5GJ0ddpm3!/Drngrn<qQ,skLN?Yn66po,D0DDXF
GJ#b71#8OIchE4IQ%?tL'!p5dI%&I[p\nPJea?7!g(F1Ji_]Ao(MK//1odJ)&/plECt+gAdXY
G1T>O)L`"Om"*643i,mi&O`lcY7:K=8U<Tqai=rXP)3Y2NXLIRbJoftWsSAH'bbOY_3Gc*7)
>+C+?k;,PES6:S5#SY:+@%V6@Z1hY5QEJlibhdT6'(@nf#:R!4Di2MZC7?(p?dUIZ+6&YA(@
4r,>5:<)rKNs<^'a,4o.)X5^E/mV24I7'$-C(^@`Yqe!bTn]An3I+lb!!PQOOsP+/aFWn1VSf
"%_D?O8H:l$$>82LD8&q#(,96EUI6GnU_FV>pP'KK9?Hk[Z,f^>V';h%9ZaYPaf8/U'HX%Ng
@jQ2cog):nhat!"acU>)UtYt3_[Bp"""_^6r,MdP(5(u]A=*$#*&4J#30m5n06uZ[pAGu$J%R
4ZX[+g%o7>_X6]ASNrnTs;e,e50t8hNkN*_ls;)[#+UA=5><Pk'31n#lZRcD-tKG5&Pfo(jhs
p:9qlHt(Y8V\Y;\GP;N1NbQ&3^=B,6>IKFA/U5K8'fAC6)duEX;?AfR!RL\=AV"2_=S<"4i,
X1!5\4ia"V1#L:L$!.qTpr&!Y`cNc#?Z9?-gUhmCV'!7XofdkN#6a!F$C<epMTu"tV[u>i:a
#(8O2+8=Z56G/kSolC\0JDYd:43?n5)i)!Ss7hgk\fc[L49?G]AP;0DmiTZ&Ws)7b%1_'FlN2
\63djh=aq1eBSeWuW?f2dG@Bk@>A4+(ipp,KhSn(u-41p-K;WAAu0-]A2r`g'Vb+R@Gf:WRT)
<J"co/1,6c^\-gfLA]ALRIbFmI$.r/"=9R6DO,b`JM%,et+uks,;F)Vkui3bg&bX1%t:@\qB$
H4nNGl-)Aeao6JT:C$`A<M]AI@Rq>c>H2"@65C[<.YU\8'o,k#l;]AS[Rn4u[Jej#>@,S=U3Xc
M9P's&;$UCdg"I+#50,9(15V\XkgNK)1D0d/7uqH9ll`+:MAg2]A,dWH7F@2If".@utPO5o<G
kaAY?;Ss,ahZfd519:]AT;,#cT"-]A,!N\\(srIWcL=T.(CNm;;ZQj2<J>U^Vpa,rEg*p?RoAi
4);gX+1P&JU,Wq+;B<uZ[/U#P^BQ9bE>13q0`@/%T]A(F1pD?'O\d/K=#Of^n45"Km?e&$<>:
t+bQ7*/)F.FJiAlD9U1.]A!Au:.5c/pdW)GbYL8b5,-V7bLZ=D#@:BchrKoPt/Wno"Ps&.iE2
(CDMs/U7lDjTShR\j&64;i60WeIu,k6FGLg8m+D>1*W+($r*&#:._bKf;8+lPag\[H5t.m!2
5>k%6`_&c?[nOWL&PDb;2VEg,R@f&s.MY^g[MJIPO%WMD7Z7LQKC.@^gm9\674(PErKP&F;?
T%3APaA3rPacakb>!(&B_p@o=A'5e.k_&Q]AkZC?6Wd0oR8?c?bjUB]AD\*PhLZJ9p^s1r`h&i
'7Z@'\<`gNbG1LLZ4-O41q*&Gr.dZD#$J!+Ur'S"knA$[[`.NB=]AL]Aq+GYH`gf4;NYeC7HW6
X3Z0/H_.l.5/<UlA#=k8`X(9uLS_P-"N)oe^(bYgBkZ5$a(&K)a2bUYFdUm-$M<n%\4;-OTZ
UF]As:`n(kjK59F^:gR.5fa[FrF4j*jaTCh._"X=_O6t#+$\SC38dBV7ctlN2$5Vn;YeutmV%
HJ/GI1->[Wnc;81q5FPE;+,m,l<\!S+W8R>Yo&EJgLkS1J;FS:T>n`0jN9Zgu+X]As2;&+%rj
o;.ZgM/CL]A4l%&Z`a:KL##$:RqT>l)r<MTBFednp5PB#7?-Ekb'+<*;)\jWFdl=/*4p@-)/2
';GS3r_FH?hJ8BkRg2Hf__l/rn#)+[S%Cb(sml%-iTj7QB%C2-"MoPc`JM,!<EuIHl`R&:_t
Jg30!VD>n.h'_rKHI,pgA>]AB*]A_q7-ld]AqBh$'/V5^!f$3h\:$cZLf?;ggDH4Y@#osK*tKW*
@4Ik9"sc?p_@Yp4Rk>;tb%!?77aO`u`W/"f3L9Bs%:eS:hkU'TKJ(f)nb$J,eiFdeeRu#6_S
XBUeq\Dk4TLqR^H%1,"p0Hqh-3'GhsU8:)/LnOkGcqj;#kSo3j*fYN(7d.c*eJ<6_>A-;e`)
a4"$m^*kBQgGUrPLn3"/4kpJ(aRqCgl#/dWBE^=8Lf]A(j8nq5e#]A@Ec9=Ba4Fh4:&C2jHU%0
S3"AEMH.!?`7li@i%Ob&=EQS#Q$I>>9u-_,PR6O;5bQZ>T?jDo)9Ckf:/35Z_';/huFq88>!
MZ.*B%berg9-=?Q`ja7g`\S/2HeZ:aam]A'aFq6,H)9TV'uA(84eK]A#Y<jJ8.n/0b<d+SK:=h
7!M!8%Qt4[?W,m.&+1<W7+gnfbBjk`YpK;"As7^/36<+&m/eE+lQc0;^/MiFrR8b:Ec%=7>r
,Z6+n#0M4q5^Ljr_O/GqtWFFbI7a*\Yf_&?K^!ZMc9Y?rE.+B.VI?S'>#l*>(l3I9;&eJo>\
1*"QIifsbEnTAWrrU]A%H^S3QOGTruP3qHAAIP2_-L-BF#R%t3";_1$KP#/-lYS<E;3YE55QY
J)Rg_CDseJg?$s<8AIi@[.?_f\P1T**/?4FKt3=E5Z673'I-+his7u>&k6a/4?YM6!P/.+3%
uT%]ANij3(Q_M02<6bo%YMhec`1/0+Ys?*ko6o*1*<XabnK/\rl,9F4^a0Q5pX:lMFp,3R73b
<SI;b&QN2I=%.`c&_=SiHS9kbeVriTZHAl"^@tAd[\LHQoGm>ne3g#J0g<Gg5@=-aAoPdqf"
;R@I5S+Zmtdt3+9u5$p\=,2_tkCj+=*2oDJhH"R'6Ro7tX7AAT^U=TtSjS?>QH#p$G2*9XOJ
2aNGR+\.s-6odC;`gnZdDKS2#jd,SJU)V$N_7XT6/IK$;B/3hkb?(EVrkF[+,L2jkF9f_q1<
(Gr)cFFXJoP]AmV.i;#a`9P_Ma,Lj%Kr6n"b=QYL@SU.'lLDm0n=?XJW]A!JTib`U-98rSTN`^
GdAL>]AnlVZ)p>N;\W&"laPWZ3b03sfOCSr7IrK9Pe'8IS67'%"sBel0EI\d$IZ<g#F^(h[bc
;R5[gEL\fL_]A)C9+Q+4]AYtoYf0XA+2P,05MojD"%c(76pM)PI10-ZrnE.>0k8e7$[`:@oS-Q
RAR+5?tG:S[W>n%7LNPPWnT&JK?m?e^hfcB5,*YZ7=UB;GP:I/gbkh>HET5g3/;oE"i.FA4+
<E%G!$P7-Z<V&n\K>!J>rn-!_(bCsYGj.8p;og96nLX2`\%@lDc3-J2]A5-*;%7_F+ajB"duA
-6l>Ks8%R]A=C.6'L]A(83H!K[ZJ1(G/3OD$-jXd+OP9rr4n>KQFn?p_,?Llj&RS0]A5YG;2"EE
1M==UE?-Mjs&<<Gj1WYG?I>T+_FheBk507''slKT:K-JMqL`Ej)k&DAT>Cmc?WLg8[;3kO,9
E:3mY9I3l8deWi;$`E-!K0fqIQ'H<6F!K$N5Ap$#Tmei&$\j4!AA[aBC&QrXr4V7jV;5cKi`
0PEVdqfq%km<1occ?m=l!aY<+)'8q1#NK&oki.0Wcu9N#t-pUiA^!+;`[EVl3n+ij/>sH[\T
5>[$eiK.JT:=C.%Q7<CpT[#g[k8'qc-i(3RYS7ml.#M\W.'bBJ.TXhJUjKD>fc/kY46n3rtB
<2_p6EePbimDd-#6dq4I@jUMCC?7TD_8E,3;?D.9-OSL3ZIpqE;-b%^PBW:0W>G!,S-_=m(b
-hDbi=S+IM-%ome(X9eEAZ.')ih-i,s&0I?Td9kDHW[j'[,<o0_T'OjP5kOb]Aa+:Ulgb,RoM
E=>`0igJ0ro"@f#oPaX4NXuOVJ?j3#HUt]A/XsB:DU6E+cX"#[m(t*6/l\.%#3"?/,mGLO#GX
T7spgo`#Gb!)[$#`M/G'a4Z^FTtN$r,="3$'/ta.3E"<9Ed1Gg9JB^Qn=OkJf(Yjb4q2+'c"
<W%;P?9`P*4"7JG;r>=N8(HqQph"2tfK2RE@feU/iC!>]AT^;k[eXB?L!UT>TBRVjOhTqbtT,
$ga"m"h$!rN_!dU<EWa0UM%'O/,b.I!<:Bli=nt$L^(QA%`ahL:VImW16L*Psg)IYWfub4-s
/0"oE:lEqp(aS\I?;/9*(ZjKXq[K4,3!"5srgI'K\A!:a9O%)X>Q*nW8QNWL./nf]A/FE/q.,
9Oh1rQIR/V-h=X\,=@+3HT8)Na31h[MfVcg<G&?Z5B"I))+JEY3oKb:5:sPg9>/05'3X.5[H
."cK,FQid7J@ILENZ\H'kqZkQI\8h#5l"/o'=c($Dpe_s_hmfhi87.B+qH?"k3'A6QIdnp9k
F0MtBYs.ZR)T'sH<G:8-@Uk+kEnH1BHO_%COU:QPcAX;^.[gtIpj%*Wck.Ng*_'<ukG%L6Kf
%rm%6,A8@mCpQH_1>tGkK[]ApJjg>nN`r&Vl^g3jUdiI(5]AtTs8lr]A+8Mk40Wd7jLHFt86aam
#)68@Je([3)@MPJ^scX2/&`/5Qi:b^2DCO6B\`QpNfT%:gDrmQl+9+BNK)-TV9mO"&;:GA0;
IkCK,jO!95/#MVM+ap$Z)Sbd)_`kS'L=5$<MQhmODhc1o?SSkT>/[KJB1><n-4St/5(YR/gH
#tC*s.4gh0Tdr?B3h'PX^&ZZ$j(n%_.#H))`QfC?eQ:Z0,BAC#_-%i_Sm>SC05J+(&?tp/51
G37tOJOV'ck-toQo0n]A'&-=JXnQRbfTGj5'1GrSi;2)jd+Uc`:fCMe1g9$Zod(EnD]An]A_Cum
Kgn]AEe+jN:[)&,3%od]AS>Zef;\HZHZ[o>MNi),$S$b@UApX8N9*@oI`)`,LF"TjJIl\2Y[43
!F.bSM4VB+MD\j>N_Q[C1/_!3W2OdV7&)DM_85WN'J<0l.MjKeI&C>JDIm;PNr"AuGoGi\3a
9,BFYW?-]AT!Ce/'Bf989O$-l0KS8@C<)3j.4sr[bYj:\0=Nd1CEFILSS5Gc*%sq&`KR8a:8L
(DAXTeEGk(ua9S<86$/2`kI]A/=20U(PJU]A+0R`hu!%eP@(%hWu:2j(&PK2e4-blhR2I8$gR.
qa8FFe<LLE:c1;=:N)Xn%bq;F2UG,t:R%#tpNpH"_[[0'(i5F3%I3.4$GoYX9)?P8nTc0>jI
2Cui>37N!A(I79LmVAak_5=uiI@3>B(+uS'2i=+F/Tp(M522+p#o:V)bKu.A^jI*O1fc*IS)
78@j"9h',B9iI\?V+RWQ)`r&r$G"&<D7[W<T97!A1g1Fmuo%t:V[]ABK6c"d/2VPL`6`g)2<k
-$o:(Uq]A<ClYZ/pXH`-``Rhoq\2h$f>5hIok"SoO?[cU?bt%2sIDl6OBXgq<Q2]A%s,U'l&'e
haj-<EeM<;-:M%KJb0R'Wn>*2D+tUs;QoKf!hSV<k=W?#OIg/mpgj5C`j_]A`!HU*f52,CR&#
H:R7/F?-2;f?CqUf"Ah><EWLtnWVDL!/OW@0J^OfM7X1*UVldB`We`(?dFc]A$%Np'tDQXg&b
%a_!]ACb)I*00Lo@PDS/h[^uH7M=DlYB)ZlN?gV7E/6t18N:afN$&WlS+HN=4[EWMA7N"`1l%
Kgn8MB,F6*.TMiIeT?*remAIE"[1,c!N3Kj"`G\g+l!(VXVE8#X+&'8SYRY.ZX\O3O$mu8L-
.O@_O;+k#ahTa]A<+#0*WBTqMKJqe7Yi3:OXbkuT-LOC=U(R9Ji7T:mc.i1]A:*^[+1X<)B_j@
WGJ;r:V*4QhT.^FOHK<?3H<SJYl81@+DeMYNc7h&q>:5!@b)BpMc``VJgF]AJ4gsTeoVXA/1M
]A<YKXlYVN`TU"uu8M;tnMNCenfYpHBZ<->jL\K4V0p8X,4k!1^YQO:/D<GdPVEg/UfcaUB<K
?hc@OtHIK9(LJ4M)#lO8c"SVC%2$Z[r?L]AKBE"ZZRJl)",pJt/p6+pm_]AtF(rE^b"08gU)'Q
aV/+F=OO_=)XYS4VE93ueb"n&p/-Iae?n5lnRd+L6;Z5e@qk,1s4<3efmc]Ad`%i5eWS.qps2
"Cdr,<Z94pJ@Rb`^i:$3ab3@#5S&.PL]AFmhPC?Dmr"_\`a2:NX\Y+NP7.nO<JD0iSD+NBlK/
dh6eRscdch,/G#Q/7m#%Df(ehXc&/@`):ql+iM%nE-f*4Q)^/]A:n\LQ8^es.,lacXkt'6Wo<
Mo-U+jdBqi:b"5@&\r)HGPMI*:)Yuq:66mgoBuIrMP>XTONAUT!I6;&^#HYi!f;!/oOsfXk\
2Y12Q$L]A^]Aq_L/R(n*50IU89_Z*4)klDjJm&'E^ef.3S!Mu>tH*r]AmT+?B6F$`>m8+L]A<[_E
?;8m40dS8j5[=U8XZmC_LfaNsk;\T"m@hHSi+E^fU-AlH9Rqnn&S]APg)SNJ$kS?f\auSq"%@
AE/O2l,jgBKE+NVV_;,u*ic7nc.&YMg?<ko6)6#OWQ#AZRThXom!V_-HAEeKL1=^0*`qS;&1
dN0NVe>9hb-*OljpV4Xm_-Zl\TDOB@aWX!`^J2+-<;\q]A<23MBUDLP9scX-!"@k)&k90I3rL
ZP:ntnH,85:j%VPEY>[J+hDqoX4*3WU.=m!s.RAE,$["*jianpCY8g0_Mh/"+fo4_b&Qjq[1
[n@:#I#gdnADmELJTF[T+^K!DH]A^_]A'Pb,O\NB:#-o7UL1U*?,rlPJG:K9\GZ`L5+muQ1_<s
PC<9;"_08u)t@&^Jl=D#G-r2IPjJE?iYabbXVGI!?VW4gt]ADlP\SO?Sr)c5u>0^hE\O\M1t9
1.f\(n;c&`nC(qH/od2H4I!"e!(H>\Hi]AJJ?G.1UI8c.2X%kKhcjU#1,-_JY!`M'H!"L6m#+
6c+8Aiqk@!AcgI/j^458?V>T8]A/.J3qlQL<+l?8iYtr[CjtrNYm'"G[)^b.nrAj&BlG:>%(F
*/;mA%h\?T/W?5hnZpVC\(<8Sp9$n(3kqIul)uu"K-;&65Znu\hN6^Gg.rB,s!/,IS=F/3P%
W=6VAI^.,LWnB$66Z'rD%S/@2uV<Vgb:!>[)H;NJ('qnL3n8IApaL[a)do=4JZWNQBh22_.H
B0*[Embra$Lr"1donEl@Nak<^5d%)C3@RnD)Bd]ABRqGMZ0r@`adENbVX/TfFh1\@Cq^3ig)l
el/?SN)>3ah35,/-p["sR<]A,Pc<?YGmKiKQ(5C9<XtgV;nk']AqrJ/7d'GWZqWD@P5e<u`aQE
E2Jl"H:.I(@,[#bTJmeA%n*l0)`d,l>nch_Y)SL7dK^cF&+Cf@Q=<.Bh0lE?SL^%:=ordD/6
ZO[9W3hedR'Nj2J%+cc/"d@q/1?#W^d&^e(3'4Q*Y#E^j"6\$K_()g8+\mjQ@XMYhrcSQZi!
;GBcKBAfef3^SPk&Pbl%WDN5/o9#X:Cfe9G=Ke[abY=Xb]AKrjbdX",j%1dC5B]A(l_L/8cH39
?PZ/fYPIkU#nAi$]AI\1/f'h&Xc7K[Tn)jV%\n-+OBU\!0Q7;Pn4doW79$Z5Q+#Md\*5'2TVk
4@K?r7YW4Ai"uHhpkQ,pc)1A"<]A=7`ipM[+@<I'1?o^^0c^D/9-04-tH'pE,%hF(O&#TtUin
\Bd,FF_I'n?Ou3s,VC%:NjiFhIrQ6<:n(\ip6&;^Dm^a.[pnJ?5K"-o.kQZYs`^f`gGM8n&G
L13+,c(j0`GA1:qLs+*5s7jY.HhR+t"SZGP"SXS-CUQ;UY*Gme*Kn23b[WV&Ce>Wp/s!_q5^
W:L557sCX.+p:2Wf8B<?PV)2`nCKemK$=5\@Nd7FC?aA?^H7'Mb@7"SE<TcA01.0+.N#4mI(
-?!TXuAdYn@eIohB-\+&('+:p#QP<<_J'jmRCX)0[['E#c57Xss%G0!E&L))$GTnVmSE%%V"
NTu<k!,]A^M@hHlE&GmQe:WO!$q%)2a/@.7H=j%PtAo*j!*?F]AXF*+WO$^"D&d=V",EbJVb4B
#ffj=JP&ctONoAmM&G`4V_XR-3Oh"'Cn?faaPla8q&nAe9!a"-5pP!gjckZX8DYbZ[GbX;Jf
fpg7nbNE+rQP`R1-;Jm[RZdXsF2)c)@/!5L:<&YkHq1UVne9'[u$Di;I5^LQ"$dDlg=sF+$D
$BUmg(c!CYT!11+mKMad[ZE%3lGqG8'^75+*Wa1V$ubn''3+_T@sJ3'f?1H=C>PJiPEBqDTH
7*IlA0qENl\onifNcXV1*1FTlbtH27T>I)@+pQhcE'SCOU@:`S5PL&SIlcifeg6Z>VW@edjI
U;@kM:;jD0"KII4iinQjK`6YL7=`YI<s5G%jW!'G=M-gReoHBu7b%"E;qT.UgA]A`1<:6gp?S
\o^16AHUfFD7eOSEZS.ZK;QIs+RJJA0?E)Eu+=S.Q^gp0KCX,&2IJb0,6Rq.?/IBS"DQ=6!8
)/u3L5iV8HQ,WHV&,=Y<l30mAXirEs0IcRt-B\^NgJIVTbU7oP]AiM<'0mN9;ab:d:KZ[1?i8
]A/C%h6R<_+Z6/\euFA9BQr5!QT<V5j-K:S:YK0$kJYt;nQC<j>YP'g=<4P=4Fm7\CYm8m!.i
#QmDgT/`HOK<BR3U&8Gc0GX#4l3_rR]A_I;R)E"Ai.n-3(^JQ[A2q7?1DXJYdL:f:W&2*9bn$
:q5K1igPU497,0K+NEQ_^\=qBSGJ:Vk8)qoq!.oiX"lmk%;'PP]Am&e-<>$$^#AB^a.rn'K]AY
.j`;B2GF.bPI]ARNdZio[1*ZErQ">XCCliRe@S<rR^q^_<qu8H:1_L8^_Q<h;kI@J7h=GScHB
1Ile10-WD>?.Mj(BZV"r]Ar(1b(dKO-$p4F9H0QMKrB't*?;Fnk2#Bf4RY[02h?$W]A5mI3.4p
@qanK@=-/O9(B<5$+5Y/*g8t*f+;3<6u]AL.1C0Z_LIhHf:Np7?(2i\N^^?PKd/K01f#_OnKb
e)]A#gtA7pXq&T_:!q!*sQ<gr%Ou>Ug$G'_"M:J-PtLS4Bb1fQh45KYgX8X3S@@9KIkAlOTj&
,fp]AqI(MR_<;X=!s&Rk?0:jqPV%V!E,XJuQTr<Be4))2_754%Dern3\U6pMY!pYgO9cN1Y5Y
!`?b*#@O-n5pLpM\Q%q"1SMo@]A/V-(@n7[p^@JT=31]A]A'SiK6K"?GGN2BqJdX$Tlt`_p[\M.
r,X29U2c9#_QuBQog'V0_a-H*dlXeZGTQZrRcdjaBB^'1PGjjs)%<DbDAUN,7/B`OQ+T/=,0
^SZS<mLA8oFc4&'TmHF4do684UAL[!GT1^p?NZ[olIGG`=ALQR:*c1qSh>[MXT4)Dh'q3pcA
f'QYtojC1VlOkZ=XuOYb75N8Zc.`/0DBclo[kal^Ah_^&Pl8@')p./-.SFP/Y4!fQ"BbHl?&
r]A_<"8,mO;qu7\>7S&nP+HD9AH5_:UfEh?@?H]Adh.%GsElq#<CZ['[EB.[7^,;XE*nMn!i11
I7[5"9%4a#]A#:C@\:dJTBTkcGJHDo6<U$'O;VZ(h:.7k:\r]A<h?D!nq]A>HrgJMig/1L$dJRR
C>\_5P;NSU5[IULS[PABcfBg%"NVIP#1af!)aD<EDcQ5u<8"(G=:)MUG6eagC3M^Ke&IRA*S
h6R1!Qc2bd+Kd[LJ)KuZ$\NV\9m4$CQXmae+p5M1FD`#r+5&Ubr=]AqWl'`(`iiSkm.liWhfO
;DS;Z-Y!M!."60%XAq:*G9Aj5mj3$VQ5Hr5aGUNpl%(Le3q!..^`l:i8;UIW4L?,%L^%]AFOC
[d;>'D+X:WG5SY\c'<8/N\l2Np2&4edd8tp4aFX9@G@CC_O@h)rpZ35<W/F2[$%X"f7m;=\:
6*l(36iB@or8eD=@Go=JON[P!'O`.V.^HoqGA.2boUN^I'p4aO%k`q7\4+hAgj[iFq2[abBR
rGanF[7KMTl7;su;R0Yl]AmQ%"&@a0khPAHA!#rP$-em_G8nIPMjO@cM2Z'5FmEj*acrkk0-]A
9dS'Mt*tZl:dSikKCFaM0JLcpM+`o[_><g\dt?%hgVG]AGC>Qo87g\*WBIaHiV2H"*C*n8l%g
PY[,`9U1acka_%]A<e52Mb_1SutB!ri(C?[1Pp2H0@"JC<D'k>DE-`.]A<#)h%1(Vp8dm27;5s
W#P.ep\!Cf.IL)&g@t&YWc-+m&jG1N<@@[(MBk!tO.NYnT!5\M)fl-9n!sI[I&mE>bgchZY7
Bg%0D.jLMM=f_`91[M)a%P(\Wf-V13raQS^Zkn53ns,;f,9;Y6rRpe"\>?#d$!j_`X+l8a22
O:;C-Y)ns+*8\U86@Wt+/2;R=2cGl!SD^^B(."Li0k*/cl+Nt;$'`E7f[Z\g;g7*qbL;Z./n
O"cLS"1\s+`_]Ag)l%sSL."@#Ld7L3g5A_XM!=6Ab(esaGDcXW5,-DY9\mtUis6pFb/3IEhWT
ZM9=;FdDf*c,Gut'#F;*PC+&`+@XP'jOedi&H(qEe-G%qktT8gage:-KH=As]A;!`o*=Zq9f`
cT/&E>;)mUGXu\;34+B,Yp;miVs;$Ydb4LV42:F/&(i>u307[ueo&FBTA<GAkFk.FW?ijGch
*]Ar70@kpoPL2Jn]AJO-Pf->Z\G>))AsoUPLG94gLWGV6@F!W86@XZ2n-'ge__\*D;jn/%)H<B
RIs1D^6m$F[CHP+^IYK$KW^_,LO.*-=")me49/KrFhR7(,7jDaV%<jW*_Rb2@bUiSXr'ePBl
P6O/e??d,eRs1@V@LGglY`9@!,q!_<b0*=<W*ACCMDo?n>Aj96mJO-%(TbVN!fR9`KHVL7f)
6e0qk_KL['65lFEBQ2@[Dqospg=cP(>b!o"G*;]AMEmJ8!nl5Dl$l#&E-Jq9&^2?A^GjraWP"
OpsV^Eo7705djF5.5dRl37a0tbpgI>-D$U5%fUiS+H7@E'(j/2??7iG2se>;5[[nH]AOf064A
<%bmu,2c`%Yt&W>KIQeOD?6,i<+V0C42sk"d<H5gT[BUH+:"JYudOk(pYQ0#tC9)P*Gt+lN?
#Z8>GKD]AIe2C00USh=lUW+NqtKe$9HFc1WRg9<HN<&/r>jfT./ML0U\1<(W`Iid>+*)aMtQ$
2^Q:g6TX+Tap+Ym_$qp/@Oko`p^]AWEQl)mBl#?^(V."jNU\.d5irJ;L32n:!c\pkkpAc2I%:
NGL2s(U\Z#e@,\sTuM[XDa,84CQZ+MWD;o-M<jR',&$V@r2cEu9Jg$eG!XcRJt=ET!iKf^ZM
RA(X;i7pj$;9Ye8>Lb,h/6,!gf'.p]Am5#bjG>JNa3t,mS4E<TMClf6G`M#l/Z,U@MH5buPQW
h>^8$5.<\ae.:o0cI@GN9\+4H+o)-I`EQQbK;Kr-[D#@B*'Yr:,HV_Xq#IXu;FPduJX:Z#=4
@HR:_<,G1u>a?m'gR-2Jk)Tqj0Ic2hYeYkA]Afjd";!rCSG+S++33]A\kR`kXm93sTbgiaUg#Y
*$2?B>m#FQ1j*t00)7`5AXW]Ah#gUoH.fP/l[>3$7babnXFh]ATk'uY?guJ5e^p1OnYWqBFlNQ
<]AA#PJ.a-/Y&<FG&SD>=R/G@Q/ih8UC]A6_P^$^;-iF.T`_H^b.A#=1WqABkGHND9G?[VF&_O
<SkUGBjCgS`a@K@aY2/E#\OEdXFmfFT,X.sUcTL"jTLAaEdZX-+d-1?fTjfmo2S;j@c,UuPt
rhc(*H6sc3@ECd_nJc3*m-jbo#!aD1Tu@i==Ah>\=D6pD?$`)*OX;4KL#,UT>Ha"5[/m:?!6
JE";YDTF*As-->npZ.O5->0G2fUXMqu4Sra<DG-;9VXhW<\[!9.,EM%/a!;5n9DS-=q+$M&F
F#2#[[rG:XSH*?g!b,/3ud`&mJ0M9Tmj;D@oKH2n60/s/AVM.gu3*U"MENBa9fc1V:kRPU+]A
R:_,E'hbs"3Yolbo`O\UjQ;Z`f(Y4b8OODK@6Y91R*9,K@A_na+rcO^R\,lJZWC7j8I]A[YCk
7*LTnVad$/#f5$Tb'qKSXm2-R@6gR\RD6!K,hU:X:\ji9+Z%2'_%5mhQG46.SF;LVm@Nf.el
8Z/7t)5trjB#lC[NaS3-*N[/:ZmS#9b6dIOMi,[EKM3<?#c'j:>lK.o$K?jh0I_0rabC0!e)
-"Aj2R<=%.0L3OQI'_CuH@:nFIbff6NFo;m,3,'dS&uJN,BN8N*$4<*X-l`93"\fffOSd>rP
Se6_-HFLC=-WlZ&OXD3Q<Z^R_"V_<8ci>OkT'kYZ.Bn5b!b;Ia?_P*8>RLn0+C]A>`d!E/OCe
B?4`9?lm>`,VG?t<1OB.SUk/O0I+ha=9D>IQRB&,[SW=6N$SGMYe3o"#1ja[/U:0=7In89e,
nl2Nb8A"/]AJ?J1-)#iOl6\CDXEGaWi\SG5*!!>TV.A2nq%)rajZL`U[&!Yo8!7m&SXYm^uD4
>[%W$TC=ZIoIBA\XS+(pM=@#[/f6ck\B>2-P40nOW4IL5#\dbrW^:Hg,E>0C+Z'+F\Cf8KD6
7h2OXHOd9.ulF#\Ni<PAZC>NtR-*".]A[X?rq?O8^s'C<j/M"3j:<:*jP@FN<*pu\>U#<RKo5
j+J$=gWHI*0$\^(SR,*9LBX?5,\/!4rW^V6,k%qK_94XJFUeBMJZQn>(V5bUW@[:?"Y/Io=o
kD[%q6d!n2D_fL?YNK'sMu4:eZu6R$n0=r\NC1a%f_q4+)^4EE?4s1j2^kdNV]Ao`<"eGC:60
Q]A>^_+[$9L%A/`oT??@qoJ09U(*d;5X^etNk=!9V/0u[%1ZLK$-]A4ala\>`rI_3m*q%!U^fO
qdiaj6DI\*o4`DZ"1Y2LTmJ%(%&F,2I/Eg:[O#k[ko.YHG-Y%DMD"aIU\oRG"]ACNrG^$?Z;r
LlTL"<^;K8X`-;NL,IZn6bp1$1?EcEb@1,%!pcl"/i*=#X)Jh/+*1.M(9[^Jg=4B+(&#7`Ao
1p*Sc7Ru&@)?`g.o@6=/6''AgD)XOb/6]A?\_[j-kdTHX_*Yk!P-1i<'lJO;d_^Wbp-I'YL_]A
9H>S=tQ>PQHhH3e-UK]AW?o\boNPC+.aFUe/NtqK9ni\Li\MA!$46)96'(.=&2[6MOf_YWs7e
UL%-r>4)o;\`1MC4-1jj5*eON[*e^@jl<KqTD+"482ggE"7L,fMb>&HBXkO*#oKP/T!6uLS-
mch5-d@l!]A6cNE(Du)GgJ/f$lH=34gQ<l[/o[[84JuWj.\j9p8;InXC:(pm[\^EO]A-Q&>!e=
\?Cm7ACaFpF$P/Dfh*DO,_Gt=3G=R%_B6MB$_<SNRZ&0=B$@T$qN#8-8YI^hO\L>H0<oVH05
j4j`bH(9NS%5_/fkK,UT)*9`kSrhLJ92NR%4@ms*0-M5ie-='.b'NpN<BFt;VSkF;\B;eSXL
1&[;8k&Gjd#>Y>nr,A:O0.@lo.4jUlrX,Fi4r14Wgq7]AYfHZBOPpoa6_"mZl@,J3A#&i+B=I
7-XkmlhYc2c3n@Hf2t3h/[BIXk2!M[GgnXV"f\lMZNt8FA-]Aa6o!8Z#/Rmh$IPU5%jdmf1_L
c_0(bZVL:)]AqeoBAK:$>G`)cK1ge06B4KN@Wui2-%MGZ$P@$"H=d.b/[DlY<0HVE/#lRf&72
.3e$[g/$rV2paQfFY/K9uks-.gouA?)V+K9l+BXU7ia^L\F+HkXB7'X057J.?!kh\CCX%as[
sFA.3(9$7P*^+-[`T'MQ.?;MQoD0ZqRMEEf.+Mmg^KdjN.Ii(iI%FlVgg=-MA>?Ue>;2>7um
1>?qV0Cha'h_J5Q3q99e8;*O2&\ZB]A(HhXh(dMn]A\r#X8s+1Vjgo^sC>7Esof0%q)+2o@/XD
RrRPAM"(8,5A1MHo8ea4.-Ju#U-eUY\WUT)in;BV:"C3;&SREl^4g[Z.co?Rh5SDp>52E41+
Po>2!HVc,KpC]Abq(e="'PIg2^pP2n%n/+b*/^#_ORa"+oKOI6_j`ua\5fr6V906)5oQsg>8@
>dlm%]A8&e86/8pn$+WCsG_'0nGPgCf!10ri]Ar\dOW6-1]AX/rjB[Q\f-QN?/I,V=ZIus0+p`2
l?+YS09%D;X8$N*qS2ce\jk@^;+o*p)F%BXOR-<JI17uUT]A,do93I"TBZBuk,>=b,:Fia$*>
;nlF3caagJm.f_Os"=abSA9NT;X+%C`B@o0,c)hW$gRUX]A&9V;S@Y\j!ba1`D7&Z9eYjbHiO
0-QPOlm2W&mr(*ca7"RPBG;P%00Soq3$2#Y`.FTkQos`TBeqQg@ruI^,mV8WbTp?YR!*c87Q
N2LDS4+j:H*D8<<5F4_RVDWEK0jWEX"'8*]A75m'8\T1-9!s/BdP1Yd(iRC`0?Y<_?*&_%R?&
rqGG^S9D>McA[@[KQWqD-9X/'6:A9c>%u]Af8jl7]A`bc!g"^=1OaNk.DQ0AU6WC6,RK"#'U?\
B6u%BR'88TuCJ*F1C/4m,g+-s&jeF-J8t!_d7hlSl/d^rUoHE\A/+7C'slLlPXm5-g?au!9s
L7?XhOoJa/Lj<=AVunb87=!II*#\a-i>G,h'kr;%;Ej2.(rM1Y$-@d5FW,^Ra#L*b9gU%C3T
%ug=(5M27\(NA@'nAM;=51ra)<Z)]A\W/Y.u.+!]A!pGU0sqp'n59WdH99YL2b359B,HKAhbV]A
A#l)FTE^7iL>/mX65n+f0!5?fFQ_1`gh($5U#T"A7*#btQd2Oe:lWWUWQIF3Vfuh2/jG\Z(`
4cH0!E/L\tH%#qri;T)81(K+a5lcd+i6@mWC\sX3=Lf6*.YX0Wo,UECS%b.WA@BY&b2gS(?e
^QPYGcjN+?k4pa1u7GiX9:V33$+6M/d+aq@TA_=VV_>CRCt$Gg7%J'cWk<i87>FdFo':LO3*
/=0a"C1@&*Z[V=OdQj[+45^m^U*PeV[U\[!Y6"UL_->P'Sm!d(gd"m=4m-;[%k1AHY^+t&8<
E:0dqCIW0M_^b:?QMUC@g[:eX^3q>R>@Ud1K.0DRfV]Am"6P\>SUp,tCN'6<@jcT@WH9b5ek2
f-/(u/_\e$pWYei2$e\Nj6^<pe`,mFnK`mQ:DPP+k"+l^lDg<rR;"OFo\@W/rNcTqq6*o0We
13o#@m.p!-pSV=LFNo%\t(D%%^HRh46TUAFi[>e_MbuY&JL6^?ho!%d^D`R!)F44XF7$nKuR
RL]AqT&jDu=KYHlgsrHSK7I)EAIkL6rERkr'a3G;%0%-8*g?+Y']ArYsiRPQWT:8J^D]AUoN'.p
WQJqoT,Z4pbUnc=]AZ6\MlIY8nTB:RInK,?1C_4HDd1JP#B?9aAudD:W8Qp4J'31g0+.fE<1_
(mPSi3o*c9:N=c$Qdt,Him`Tm*=\H+RiD=HS&S._Eioc:%q!`".B5^=_d-dnSesJAj*LKF/n
%$<A-qibf9o9Oa=3`<3a:7h<aoTU(1Fj$oB;Kp1TptM\HOP$MT.*g7#s1j7[p[`\R+9;?K9e
1MR7M&0IHN;SD=]A[4H2k_AFO4fZ4nDRpB]AWuh/bY7T>,I9#*OBYlr$IjX3CQQU6L$YTb$cbX
p@/"fQu0DTI&s*#?Y^NEbPt9S_)2d7)ONIYaHG0nR@?p:"@_eAd`]ArfX:JJOdfKqe`ZSC`E%
#-0>f=;OPdOjA7.RW<a9PDMA>@V>:>ucKgoEl`jZ`:RB'?apn!R=dqA(HOfV:,<K;2O]A:1WX
ZV)YECrb&a&1kS;c/,ps=lf,[3HI9W,<W=QMd+7TXIg:8$f=eaE.>Y1MGD`&NLK\N%A&QKe=
Hm;)S2qK$Q<-bC<;GipkX]Ai6NKtq6]AQ)35h>8KCVf;'EdL#VLXMG>?mlB).tM6.Gj/DW?Sm4
0T1[5X`=k$a5`5'8\Q-9#X6Yi&4K#j#T58!-det5o6(E6]AFT\[(bhRWJ1uI6jf=(,onFqCun
6r1crU$!:L=a$!XUDR50"`694]A5WY.lL\"Uepg7XWMtQ7olD2IU;ckcBJ0trq(%4UT=h*n7o
;3NW%2!*BRCE2n6W%F>&=ah@HB<U1+c[,;4TY?gp^To-5In@SR<NhRsh<[\2AI[b[4h2L\sj
D7OjAfs'l/<8Y%<oL$c_QZPGmGlF+,Vpo>!g>E[fXH9lE2c6"a?<,+25IXtR'%Zj(ao7hIB7
;AVS*9oZT<.AB3rAWJGh-Be^P4r/5`3L@J3aLg2j]A]A3-+BtB_On2-f9esW8IG#,WEPNmDWci
\p;Ua[F?AZ<rj<$Z*@FIQk1AIk(".n)gH5MM6o_(E6PG;pPH]A=\HKGl($ogYCB^-Q?5Mp84O
"lZsl2'>"b'E+P?V7kD?"rAZT,MX(rEgK=KBnmF.D?qWVL><>"V`Z7gnJnlD\TCS^\Rr*IoN
FB=`CKl^Y`ajhj'Cshf)*b>f`[m)#_t@ZY1FnAET3?UF.kUBd)XlHBagB:f8-Ii8&N_I84"S
IBqtS?nP9E;:u`,8fSUq[S?5*2;rbck6fM^nRL.=%Y[hd^oEDr/i7rnZ;PFs'ecP%Ii3FU-\
SZQ7#/=RJ+n4YRh)hq^<F6.%GFh'3\UQ]A1PG&;WitL\2NOqi-sb20q0Tj5pYA]ArRRTj&QTk7
l`IDO3&TcXRT+VhVj6;q$Nq`QSIism]A]AjYi30krAk<dSa3\`i\+NCR0fZV$)Y5iX/,asX4$k
6c,_0#WufMAcR`(opC*Pd)$6<5>=O@O<Bs<Dd]ArLUp_t^qKIUL&GWOSr#$hU^&i>q\h1-E^l
"OG?RWE[lkiDoGWQ0cca[(KN2j)o>kDPd<,_^j_i=K+Yhr,X'>^?'g]AD0;,`Bt2Ep-Y_Q\ng
J]A.K/r',"'S549b-^VEmE.jGg[>:3@IW,X@[Qf/bP%_sE$\h:6?"B7c[[()A#2l:#'[KU5YM
)`\0ItP^G]A*5"me/"f\cD:`17OH"I@4567nUOc?@&.eb=Y?1Y7X^ba-\k*3=]AO6\f#p^bbts
DMGSs;Wl+Aj*)8XqW+pnjb.SPjIh:VB,UG8$Yr&3;\1CQ8Wi*"Rljg6]A\"r[?^/9sbF7Enbg
A2cdX)<YJE_+kU\ND$^/>lHM'K>l>&C>k]AC*pA6#1c>_HOjG$SX=<+O^JtN?rU3LdKNOJ0Ro
S>M/i05bBkr/WnAs'T&[?lmu-=G4Tk>pY14>f!lDM9a2=W?`&cCD@>YT^;BaS,HQVfaH;H4:
'rWA_Hhq7MWP/TS$ReIBdNSePCPH8I43r@7gL7S3EFe<qSO\c_**QM7SR`>MP5%3q4]A[2&p!
>#*G9I..f<%rKRktNk*ni=e"h@lQ7/I"mALeh&IQ`f"Y))4ppH`18e#7@cn"Wt/qcuHae\%"
?VYI\5HV?T>3XCsRG(?LWrBSehX08Q5^0.FJ#1T6Z;HPRtS,-mV(^Xn:^htZrgpA?o_sWH&9
J3?M]A&=cPgM899]A#)"E"WFl2%\+qKYRC*,M74P5P@Z/6684kf@]AeGUAaR_!pFi,=OOrd^Rml
)@DW8>H-Lr?94g=[u!:*J2M234'2$57?TU+1?Q[Z0!cFAZep;\S4=M:M>LmZSP.n1s21R<8p
*g5;on.7`69+>lG-/kNLfETT>2`j$;MMddlVKAr\Z"bm9(KSkU$r)S,NZ^=>>IfN=Jn;hjpY
IdMn(*9M#qY7m1B5"p5tt58&4g_CG@MS)7E%m<^R"8JI-/gLl1BT\5`XqB>LeTMrl/%>F]A^@
h\,:3@roA\KD<+>e;e>Y(d>`g)bH"o=6[!\!UX_`<R8s$E!DN3$F278A;Js2ciYY'SOaqrVd
0GTmK^J"i>FOP3?=S\Y]AKa>iXj#FhjG&76e2K&+A_):gCY@Zr);FKtqFjMaIJDB/]A=*1"H-;
foa1_uA9O%@.e7]Au4h8`ND$_*%Br2+e$Sh<]A=FM)p2b59-/lIV.Ib*2u@8TlqLk?2-Fg'E'<
7L81c+jhB1+/*5+$k5l[1DC4/nXg\)KEI-+T/]A5d8!Bi&Gr$dLlV69;]A&`'&#Ps<fOi-,"Gr
1UOZ5;5BT9g7R_OPoHmU)k36s*nXRnY"c*?*"]AbAMn83[Rl49\Dmql17M;lfhTM[+lBheA_o
uioD8)fpG&X^D#@N"/@b5eQ15s>)*Dg!L#bIh#.4DfPgm%qtsC=W3tqLPechcm63a$<mqNXc
^\3G)X%DVHa$lO&M7s8aHNMXPu?$qSE\J;IBAb*_TeXrJ(30MnI-0a[lM]A,+usA0]A<EbdS6N
eQKog)70lul=n2^<9X)G4hVCY"/bH&d<&"CHH[X?3c6%D/Ti!&\o3aR+=XKLi$cckCJ\uQk&
G-s,HQZSf_]A7[@<2]AlWFj"_jDTQCJ[qW6j37YkJ%&9nY+<h@em__^@2n<0tt#S!NV)'KOWM#
#)C'dnu)-6VVSg=sbR_a1n(*XM:UU3Y\:`V5Q28rW:6p^S=>,G]A\7['F"[7K[I'L;X/!%#LB
cWLQaKB`CX-@"o#B#d`L3YM2uhJRV;*T[4lYMas5#UgGd%<a'[%NE#Ld5jMr\\rma=%6'c&L
GD;92_Q;B^M(YD963H:&023EYhM_e)B?Q8j[2A7$cM9j9A*?B%nar%IVX?MgE),i$I"&T":T
0+O/_<J&QYL$82!HsFXQLe,*#.a]Ab`R>rFYh5Y=mn3!Q^i`G<]ArnZ'0#SW*/k<`h"t6^o,dD
PZbj4CgJAfc7L1B+k_*6JB'DO:@_N*FLp/W>i-,'b2pC\V'O.j8#S5PlI0H41u!R\'7HqPjf
"*<l<A(9%A1*07-eIrB(0f9'WB(2=plJD)*Q9nQPn7PLJEN3F8uC$e7"76igV"m;a(mXP>Sp
YhdI7Wlb1UlT+TOnp25=h76JkeNZ4$Q.-P>r5ZMi/?(4hnG=B5`R(\JWs3dI)1;5k[o.kD@H
-8O)rG9F8=1J.]AP=sjol:'H=SJSmt/,[_b?<;:Oo7`T:c:74!<=T_-hTp]A0n>e8I#-QX(<YF
TQ]Ams-H?[>qj]AK<sG+6;XpQMq++`UdSN6FZoq`]ALm'(WIqc\^n@)(F&Hg$<6-Ebk`<_6Q1*i
'[&Fn*]A%Z';1E)O`HMf4gY[<]AS&"t.^32CX5E0mHQ:X&#HKr`C?.ms+%M#:G(cIpP!:@_=a3
2F^SB#YF<?G=pJptb%WMI1lfomfNN"98jAgmf4(bDXhInXKb5D:4Q1ZSM^'%#u<QMnap>PVQ
uP#Rl&+mu1joG_0c1S5aZ@:Rksj\n_c.<DSni@^]A2fMD!-A]AR8RA?O6u]ABnAL;53SZ(eug&Y
>bQs&iZ^uUVTYsa'V8N+XUu'lA6FF]Ag+6jae7J+q84j-a$_`gB./bP1_i(R7o*WSFIp!`0VR
r%1^NG8AA8PHQ%XS5eD7%a,f6C!D)@YRR"[Vf8D:JJH'u$OD0]AIn=UY4`TDat5Q"H"GmKR@j
?s%9>7^)EQ)m;?V\?'Tb9RA6-Vlo-:mTuePY*d[N/I=/@Nt@Sa%/au4k_:bp8a.A?gLfHZTN
Ro!P\:>.=$D*_rc*a9SB*S6ReO49L4`_'ir-Z=2HV-GckI&Z0d3Y2X`[?UK07@\]AjB,l[oC>
\oV4[jF%,;K;I6@oXFaU7m7DQ*^09.HL^jq@CrqMY&;\pOh,"`6Y9OrbgGDh!e*7;o/XeAp_
W)58[t:7B7r?f]Am7<q?]AbdCl/&=P]AjET6RkK?+qg;]A?LKN4@*W+:,K''tWZ%q'T(:ZoYae*E
,m3r!!)l]A;;3HXmlt^^`Y+7J&Z>=tXt!LC&c=]A2UirQeK@U<2q6r)R),/>._Woo,-8&GY/BD
P1%tm[OVD(S*lP#<s^;rCeX9Z"Yja\2C4C;gk:<"fLT'TGg'&@YT_j,7"EGcK%LRY<'<u%89
"]AB+#r0":\O]A.[ba-LWH2nq?'uikWQR2-5S0$q<[<j^KL(5+8-6^<G'GHY,IeoUX-S:1KWRs
6Y\(om+:?,EO\F,>3p]A2Pf@#6h^p[YWChh?A"U$7uV#j[Zm.3?Up3c&t4+F3MYZT"nmea`$H
b,HS@CD#;=s6fCLN,GX39%oP1$$%--qa2/2MULTX>G<uC1+".5%^<rc;P(1b]A7V!m=4Rob3u
p#Qr)@UYNt!"i2k(%'.M!Mg/.#I'oT+,C9"#I/!ZX^iYsQRI^<+b^O5C./%N(QooFU7Ir,Ra
j?$g(%3I;@9X/58._h@Wm"tOrVM;:5@ndj)@90TVgLYgChr*BTbulin,l="lle(_'431C6$l
ScoIYOE^UJQ<=91KW/)ZSJ3*;a=NY'&,M*eCH(p%Ye+8(;M0ALi2G=#>$h<;U9C;?>0(%llQ
JEVA2"r8"jM*iW,H#IX?3VD%p94>C/dV+7Dqb+aQpbkGEt(33X4n_9L^HJe9L7>u)R2?3kUb
(D_2U7US[mjs]A`DY(MtJ?>K3p+;Xje9WGVo([%joO<JO`$FuKn,D9EJ274P%7_i9XC[2Vaa)
3#ba(6rLf[`e.o,QoNTcW^<0n.P$gj&eGQ25'j/)(iBmue@KWrMT=]Aa,Ro:Qm\_W_q;TmO"Y
=d'mb1JPbAddTu<%*aq]A&0(if;l:E9>HHu5kSfn4>e@+@Is_p0QRiLgW^%g_+)et)=/c4)aA
R*+.i,HKl8a[Y'8$KtlS;5+8Y7'(K(_Y\7)m]A@qW=I5,#\OJi/\Ulh6[npP0^Ib0e7[HGHEj
bA0/0SF&k,EjgO-]AIOa-`m6PX=<gXGR+h8Z*Pt7l]A1B9rJIT]AYhGKFk=_l]A*tQU/[%h#RtRn
J*GHB:7_Cj-Z-Ko`:Hp;^*heZe@`V^nd4@>F@+]AIO*KTRoK!'U6Sf4jhmO4*Y7Dml!AI6N<i
#V?5;?@n+8Cg>-&4LOE8q>>n,%LOFB1,Fi_Fm/)3%4[Rs$qZ!*N$FPr&pb6+#IAN<DJ\IEdJ
@5:cOUI5RgkqIq28gQ-Q1<rkejJ=,DjUjj2biW@/:iU$7l&Z(\A?c@ZOCDaWr]AS&Sq*_48cH
l-?ap1'-B%Whl?5(--Wc;%NoiT]A`Ho3&Bi9iN!]A2_0lFYq5@K[fY4OF!Hn*8GWPNj_Ml*4XO
9o_L>'QQ<4Q#XE&MFZMkh.XFLlb'1[n3ONNfSnG5[hh):tf-qH<q02@lh5g'@Cl&_XXbrO>N
h]A0]A%tDP]A-:iNpMA%B%h"e9Cg[(4%:t#3H%mtk;8?DTL!@h9L)aE,4]AQ#GH2`0"?WICUoJtj
e"l(JP:0;d>6??lAoohfII5h*S!-)U*8_rO[-EtNK+<E"<ma;eIT]ApdFGrZ^YefuS/7@rQV(
F<dHPILRQ>EZ6XtC&aC5gZ\LI($"67O(VOCG(qC$oj?dt1DP--9hgNPG`9YP%l9pgq:4.W<]A
MeGNVDggC&M[34f!FVK"sV-BgOeT\=[<Hb\C>6SC#&qXmoP0^\Gp/GU3:EV[JklVYt_q5P&:
7p@8Yi*7;5sX;_PJmp9hi.ZnY`pD>hsYOBKSYC+b;s5V9nF`UQqCppKh-M>Ies7Z+X6H066A
mF-OVr@gK8$"NNQEb[+Q*INSb98mXDoNl9]AX89*s5=)N?Ff+3pHN;G<ZQ$^Z0M:SWBT[:<-#
;nO\;@s?YGM9jdV&+m;Z/D7E.q%9VImaqdDEk3,O'mH_qE@Y='!/Pa3cci%Vi4N1nq(\XN&N
C:(ZnlbiFko\+WYT[H!F$_s+FH9#p&Lk9t=$%K+P#W/QE+E4O9`r;[`,L)#&@>tA/S':n:'=
-m//035>X#Ss:1NXs_@JbpsNHg`OBN>R]A6F=7QfKRlmAarg1i\XqU_hp=PiNO_C`kBI(k)&.
PrD\-@RSU_^T1/PKFC@!kQhR>*jSX^GSjA0h1Qo)W7)PIBW#gJo_T!h1;agd^/RuSecUci]A-
ZF]AZ4bW'uLBqLg!/JM5W$#VqVEVWiFYSCR_(b92emLNE8-X$c^ta2Cn4&&e%//cj!&j>QW;n
JYp%&UVDE72,0PjXEgb"poB2oM/TZS9m+^U(?=>a'SUcOCGYeCIHhBf$!.rE.c;oQa3-fH?=
<gGL+3XZL=Q<6idh`i4cm!=qc2Tt?,\\u9:l5tINp\[r/+>@5%FV=2VBeHhd"0XR]Af#r8uf+
LroLl5UMlN73XJV]A0`&=8Ud>>5BB)h=ugh&5;S)H/s?SY5F[g\U_04u/q.8oFj2Nac2Z!`"l
9%&C-4BUE7n**Y1;!XIT2<`>tNF1d`TGm#aG@619HUd3kr0<-QQjDgQ(s4`;4+XOQ-lRDtF&
WUWnKt+2CnPH)<kFr`#,0>nLfZVrRpdmh.APLr12f@OX$WEo!e=Vff@L#;Y1G@-c@nu@iBYJ
pU\?Wq>S2X2CVEN<>Ns*mT@Kf?9=TLUcS9i7EXKL<CrX.nZDLZG(g"`r"1V"7$Hp42!CUTLo
M9\RYI+/d0gO<?`\i@r;V;6"d.7UU)]A:jH4K'$GOU[Kp9F/'E_?J)_t65m`Oh>WP.d,(1p`)
Q"]AhgaD#qY)Op'`YKRZW6a*kH:,lLE7=eheH`c\u.3)WnL1#XCBbD3Hd>nnBbZl6i1BJeH;L
KUYLFl>%%$A(_6J:=o!t+htOq[FQ(F8Yjn>Z*6JLgc_*#9lY?Yo[b9pZ*b@9MgT.q!VE#[)'
r(cqIq&)FllTip)D%l/0;c*8T(i'KA;j=T,7F5D-XS>_QV+9)kTB_LLWQHiDh>M5R@o!AnPS
/"3299+&:^/i7RaaRV]AG$<AV]AC0YHLUigbBj'fL>WinrYoBjbckh+kHu+qcHE8$MCc8a.9&e
WJcfWh!5V,hH=u:>-qlkp_`VBX!X0(*$W@;qNpHU^D3Z73!t<;5%Sam2b#?iX<g9WBM;sfNn
t@LKNb?*^GR/2h5^PtBG@UZW;\I+:q%Q0KSD"?-'5aVWN>sE-lK^;`gtZ_cD.6?O:&c7%<,o
K]AO\Gs_Gr2/BR^)6p5!:sLXtLD%e'3uJj_<c&.^UMX8Qro/0k1V#b1$#@M-JR&(s?YScJsN!
81jeab.]Ad6?#LPjfo!K!J"HgNK6&1/J+:WeKeOrZl).WB<q6@FdmD<N"M/JR-'2(e.g3(/Qc
kW6kUU`$."@&4QoVFa9c5[*5us!J&0@)4.YT,,m.6N!et9V!aM3A!!-,<$^'")6OM+5iK,kF
f0XG-L&M]Ai/#+bn"@UY9kF#eA)g-\?_L6Y+SUim5>e1$Q5QF.?mhe-gfZDBkm(#6+;g9uK0c
YmfCIBQQ@Yq,:olI:$JJUC!9#RnQdQLN-F/$bQ[sF24OXg$!NanU/'HYm<r"r$BeE_">(@$$
jQiq)jiL>WaDl+p;UccS1l@Va(?'NcK%]AC`W=N=@h.*^'rGiPd6/PObq9*g%ri8-J)6/iZTN
/"!E;'dR*&n.G[LQl`,/6GUC_"/LTgC+rP+]AaeB*-:^A1*=:M]Af:Q#QBT:Y[JoQ.GBZE6>0h
.1Z2S/gF7A8d]A#%Uq4*fMX2C^^MYp4/9B@&hEh8R]ANN@loI+oKN7SrINMPU4Mm.rc4kgR=Ci
&%qbC6,e!cU*pm=rf81)d6hZr*61/5S\V#L>Bo,!)U:uC.gL.C:4LE=(a3D6Bi0Y.O58Y*oc
Ybo?uTI"!9s:2^!t8SJ*=:A\(".UV/?5ZA'1E,S[L81)u&!LmE)ZhS=$n/7t\P&DiOR+AB>I
io=mg&o3^lNUo?1j!jOBXFkr@HVa@Jo7B:emFQuKrM'ds@pET=$qn@Ct)#8ZDc)Iae,mZsId
%=dts/[/]Aag;`5F)-!5PF$H^1)kre0YBpSeb,,Mn<K*>bhRP<f%i>nn*d.!&*mB9W/L:_(,>
*11WiLWlT[17U"n*AUrE_-Nj&(_:Z+^d'HAR]A2N#C9ae73"?KB9c)9ncsdtYLm_V8@)s"H#]A
VHT4M%A#N5-]AToE^8Y1PHmQ'POTK0<`Qpk*+n34V%3W@'HAr'lP]AkK$BG&-Y..I8BAQVY0VM
]AdKR$mg&BE:fI4m2eT37?`*#+FG9jl)]ALGloYdJHBBB:BfU;aTn9XWg.]A%F=M:QOJ+[J1#i>
381uGd7`EG"i;(-=OGs#m01,_`<sYIp:aspXFqAI9&ZbP1#3=H@OXsHgX:k]AF/_9bV"^@r1A
:&hpq`UK&Lm@bs)MCV1YrHD_K8:R\s'eF:3iVr'UhZ=MJD`r%r0\ErVkIYr&M3@-39;j8f=O
+LJH=dLlBbf#-J1Q2%l'!sIc6Lm!!bi(i)ss$nEd=IO]A"-dJ2n$N34ca)"n#ur9qG,]A!/2V?
CDpAM=Y@3Z'bTs92G]A%+=d=Q!kl?GE?9$_H+@`FEnS-tW/?$,h(%:rB0&ep.[ENV1_ais_&^
u=n]A;Q>C>T::(4@Eb397INFS9:.<h$\"8QtKBIfF?S7-V%pMi5^24Lq!in-`F)EqWFK3(B-L
T39tB<'03nY>F#GcU[$CUVWd>S#L0P:lK/3LnV)c5%0/Sc(9X/mMXa5g:[sCZBBqPFUNG2Dj
V/\P?$cLQ)E+788$)6\=dKW90?O0UTC)AlqURp2dQD&23JKD;ClF]AY:<bs;#>a=!c?sqb_6P
;sMRG;8n"*_/^hLRS".rru8l,>embB?p)1?Hj\4'5KSDLUN9P@q6S?LS#d2L!/ISns\d\ouF
f-g(t[s"qr/:)[Mc2=r!G.0=bT>]AWlSrM(I*45T^DpG/`]A*",BY;ZH]AIQWB*oso8)RoqZG=B
N6!_Y!BKm1rrakf]AYtg22@pNHMk[hb"b^#8h%.@i&.6-.@n)g2j]AX:6oM.2NAuJrd?Vlc`au
#hmdh$WCti(=#Yq_n8/hUDl'I(1%>$0kbVb29V&orN$U"Rl)KpGk)]A&!5ITq%N.)[%GkJBI_
GLOj@#Nj,/83<=45>a'h\_E$GN0G_C#"e=mnZMg!/DZLn>p-C5msN2NC35n-uKP'osk(GDA3
b8]ASk+QNRZ%SL,:/"h;.Jk5gJ3&7M3<^BmC,Ci!b,/I)3W>JX]A'EK?4ut>/Z/!R'T*"HNlps
_TUq:-Gkg+$c)QZl(3If3%M:Q*djr6;Qh5Og??\#:n(8V[lcXKMs=s0)InGM%MF,5@tDTUMi
nF):"Z`S'DTH,4Zj+2SZT[P&/fPG;<6A^PNm"2"PO;=-]AX$ka!-=c5C+RFBOf*ONGVSEbq;0
m&+G/J9M_rekn>T&eHi7+R139"-<sfARU2/RJgH"JXT"=#:#;5qqO[b9^^h%Yn42isCkk-n%
GIqg=UVf#fR8gOF9h$ker:U[hBW&-Bg:eoq`m,F#N.[EkF/JiK$3Dp6$B#H\"1;h.6uNqqk=
)&%cFi#VO9b1ZlN0Pm\Bp<,OcMZ":P!ZHZ+<s9ikY7oSiMaX+@Pqoi5!Q#C&<"DE6ke?+VTe
Y+l44&4H"!'>@edPFL)5Jj1,rj$S!d.c1B:jqBZs>K]ApE2+?qTrE6Y(n:h(m/Nrn%(42A+L]A
oT9H9ef/[!G+k3Lqc)5@!%G=:bp/:IN;mU?^D>r5$KF.n,s&=)OAedu`=7*g,@9=Hq,VC+sc
cN?H5:G2]ACJ?6ucr/oQdieIEe+YH)RVRF^0DaYX0F%Q_/hHUJ8mWRT/fUfk%tp\.%ed!NTDj
Es.o\hC+mMn1a7Q@60DDQl%+_MN'9rZ"`61&kfiDlVmK?(<LPfB7bN#!I(3?tROiZG!*1Lpk
BsMn?SQ7<`PHR%FrLo)t#?K_Acf<9U_Efd#%C#jkJ,6ArWc<*bOR%r@G8ir(;(4+>S6]A_#NK
bD,='nK)0U9L2?Ko@8n[OhU!FrFaSG?]A$+?N4L&m;8mnB54QFH7otUu3*^`uq>Jj-_cmD6^"
4*f&*IA.@"^_nb..]AHNip#rM_^M:OuefMCCe^fHMnY:3F-ea@YX3M4'kuD=Gs6mHUOee)@g?
EGU_nC5M"2BO#"d)p#VN@VFL-f=C"WPV>N)!oL%?T*GScQT;5phIQf"DY<h/K-^(GH5enV;N
@DQ^.gk%cc-15UA-$jcY,Z3PB3"3;[sCX2BJhcXU>`\eFr/hPg^Tc-"CoJ;J<b23-%1A%E9>
t#QHuAG0iDd'[S!s%MnRohXZpX=[;60t!SE7niq$l5c(AbQi5U@`[khu9mgc&f<gGNE$1\,;
7lB;'mjcbIRPn2n%5&P"AmtD*<3?;lmgqmnM,DUteW_1lrb]AKY6Q":e9TIllml#mK6<d.jlM
U?8NV\0!VJJ+A=IapOIemXb/2ig2<=?q5Z$deRDn[WbckWnfK$(CheNX/cIpune(c"XZ/X(+
[r`LI3H2TM4E-h\Ge0M3HSmZlkXJ*_HFMT(D8q<=SdhiMC!7JY[IPMp3=KK5ua5^dLOWtj4%
Qk@I:jQIUmL7b="AP8&o3jbp6?SG5it?@<WiHfO8FNh%E.94iW="TUe?fMgVoOYMo'mh6P%g
epV&gKV%S9,<i%eVU(OuhZHs]A(^Js.S!+?W]A$G9)?`:_kUtpq^HD$[30dm,.Fd.8PTnSe+[R
F!Zmr,u<L2pUnDg7p\Z*rf5g$Xit.+fTg#E&s-",&sjr"#6Qb(XAF5&!n.%!G&S]A9l,.3QWM
,'*5(Ogc\(7C?FeLM.a!W!#:<aGR2OkA=L#ag.I4)[M^i]A-MNR;D.K*ST1f_+ium`+c>=dW!
QiM1N?PEsgP-h7<*ep[=KAO!=HWgpBSk,%O(&Y3iH?IT,&KI:9^XS'R7bF,kmQM-,-2_@g#*
:QEan.ui>aBnZKlLH&,]Am''#31[cEKK-7S1k+&Lqs_[k/WKJhm@U:CRh5UkXjmN5%qJ`:Ke$
GgFrs\IY7T0p4'5;U0H-Ken9<*=\2r)(MI1ALh#opH(cT2q\[F><9\'$`^u^")Q`T(eK[*@r
&[ifb+OaZY5+\kR[(Ef,nl9DJJ!#`/Q]A[1JnV'%CnbWMS>O_)\W"4i'g%>I1ps.a*GAD(mI?
*dQbM0'P52H0dRB/?aV5bN[N@0Q./+7<arPq;lhH'09Rd@h7H3_Lj%V&VOVW(X>DqO:O5hcd
&0rX0`o#u/"kK=QtNgHsn&nBaLEY#"$`ZEZ:Z^f<FLg,aM9Dn?s?in;FbX1lGCiG_)2_Mde1
ZVK^g#18.<sb->miOj=fs=u&>4o_oT\=]AbK"IUCl+#sX@J%%q^eZp\fKfL:R1(I\gI`2,0`[
Dic.>?$kNbh35nar(1)XK/XV6G4#Thq++nlI[(k<("S(gilQW7%lXgGYKOL2Q0VFImqS<:bj
n`!U[X1&cQ.=Gap&6@UJ=f%7m@J$2@H$.:3Pkf6E"mO4!_GTAdLDcJeM.$<V$I#Aqgq[kV`.
m4nDjC2QaRQ7hds:]AtZD6hgTHQ/p3'&h>mu%?o;6r@1fP4i;nHi;s4QWTC;Ed;SBAW\$DS?-
#57UemM?(H^3!e6@)Li,d,:V@t:Z^BElp$\G5<qQ$9U0`'A?3>r&,/6/gka`+qYcbo`F&B__
;.uNNgOS6T4GC)m6[fL>EQ2=677#G_g9tk5GFJEqsSQr#Od3A&3A7@Uslki5P@[o:1D''9;<
JL0,#o3bkDA#^m3<[7#9hGT=13^nm"%(0/Jm#HEH$32GJH\_o_rIDMbIo5B4Wa'^dT^L#F%I
LBD$@@TNO7c..VTE20R`-Ga0-MC#0fI8M>25WT/>*jH9#C'+q7+U5sfM\!ThRRYhqUu5)d<N
b>*]Au[Oj`L57EZi\e[^#TC3fOJO@XTe!?2=Z"CWTcgF)Y(hIpiJpB0=EH`B4trCKaaA.lk8#
NHJW6*7dr%_r/ji.\Cis7H?374dG>bVlmBFe7)/_1O`o\JDs(kHYC7Y%gBos.m`\s^8+&O6@
U)o)^5G-Pc"@PDB.#Hg%-h)_DY3&!=NYE'fPtI(`6WXbK/J-J?#ZZY/YAW*9,eK2hmbPBio8
\5Yr?gr?7Ve63f`+2YGu:*RiaPAjBmto@or]A&",l\)grb53K/3g\NB[4=*&&I@f2L-7JG5]A4
,&dL0;:Lq05N]A)_*d@,D*-^-]A_U^p'QTZq?+s[r)]An%t`<?lD6A3f_CEh:Q,Cj>?YI(6n'BZ
:Y<m>?d'=,q5O(o&_:J'n8!IpP9jY>f3t5:ET#AcB+Lr'RLk\]AfqLD-ZJis!E?^>!pD]AGond
#Qd_Zh?F=\F'^h,5P'up#0)j]ADM`WqY]A2-MooYum@B44+6e]A<V/P>=!p7<Vqn^Ta?tVJ<c0/
Am>rh20.q@iV*cNY)+Xj!rqc(Tq5E1hP[<d@!2`R)Q>a2(ZPFFU'\8\QGUdW-LsJ0/Ms#Zsh
crFRCFKZOUZ93rZ/a*t>?"hS!*a/l,rGn><8pW['p\g-jMHUr%eD!+\;D*WmMde[IC91Y+<@
+Ar6qlTj3PdAqlfD`Qe1`@<M.@r!D8VGNTp9LK^BYFVe5Me&6!M"iO`0t8//QLHlZn7?TT_"
>GT0c`#pbpU")%PlG?<bS\+n<7LVeXIACDA!Lc?ZX>G[aX/@%K`YclurtMNjmdL#Ui.d@tf(
lhZhKG&^)BbVN.RBLMKPJE^Op]A/-q+\*m_]A_p0Dt9p\=/f8*ppl]A>QA'b'5b3[nNj=/XZVRr
=e:F%h;f\=FcUkZYJp?/U,hFRK4iZ'T3A*?#"86,"K7FIVmf;Gj/^:!oJdiFrt\n?gie?W\f
.V7u=auS%upaiO+:uYi,Vj!CQ$sUD3dHe%-5]AdY=b?-G\*)_>\)lWuIefX]AQm;e]AY<EoA-jh
W!N'FaK'":\1DiE%V?q;;>'cZ/U/(9EF6e#^%-h9n4mh^p.-V5r92(,0:2HX3fTjE!#O_dGF
9EN*^%&*$dLouN/@bf4rhW!rEc\m<=iTE!P;lG2[$UXMdV[>/o+M)mXeGOFo;k\!nWX\XL/,
R=_j1u,R1$sBS?dCdj1+!*5S"Q"mc`a:R3shP>Luj4=>6Bn(C91,c'3q,GNHdW[k">W&+QD\
b<MdNHOlaC\D+:k9QGT`7U)kHX=qiMN8cK@3D2$eH[[nce93H8*`9U-f#.orDbH6Rfl[GO_K
hcJsskn7'pg$qEG/%&r!BNe+F;e6qN6<'X-)-s,/E<m#'/-I)Wjq$U6,\CeIHtI/UQpoO+IX
CViU_N(-QJ?H8G$jTQr`P^og+r,Bp'*lb[Xh0C?^KS]AleeMF<(Ejb%n*\#)D[FqhDq0,BL"^
@#df^@e$Ct<^FqXY$,;k+RlWqpXfNo1udT1F&g'l8^k9#H$8N)M8ag^Ms3fU@5b:$sKH-XE-
3-*!hlEK,usqo,g'pL+<+R]AdnVcF$RPmpf^.d8Okb&.=<mMmeD9H^!M^kiP#`bT>\c2[*]Af-
4Mg4\9tf9-QMfQ\[=;.ZS7;lMNp:87?!aFp>gMgh]A-jFeq?Cp]A?gr7f86o,l3sqK;8tU[QOp
NCfM#]At_ti9pk+r4C9(e*%:;+Z4UJf"R&^7J!I&L1_&lTY&h94-Ce'gDJ7@#p#%*NNUr$bnY
3aG>ic\?m:eB!iLIO:Zgq(r@[^bk61D1;=3i)\-h4kg>Q4c>C[Qg!>Sm>dLRs-7/:@\7;94.
t1Afe@dqR`,>Q!kg9&&W[k2>>@qkU$W_l'K#p'lE'kqprK/u9r'+j]A5JQ,7$_?SORfq;?6Y"
A1[nDpq(=tP3IZ(B&GpuH6F#,mT=AXA+.oonC$Edq3*08Od/!+/(>=EQ3tBf#.\Qt`JHhhrB
r'>#&V@/\`3*!2'&DY<;N\S0Y7(Jf9j>rD<^8\I6?*gGW"]A`9Ma#r2_S"j9NrE7;Qq@&k=Vf
NX<#@s&YBf<31^"+mgdS=m^L#a>O3uB0dE+##W6-1:(Y-LK/P@QO]AB,5U%]A1.9'd]A68JA0'8
;u72i/N"ah51=5!N92\)`ljVLr3%Ijl.&LANY48R`A;5t[pI:2hRX&5pMqJI+<k4U(5uI9/5
'N83+#.go&G3?84-,K>]A+*g(Xuqkmf:pgf]A3ZT\<^jEqY.Qk?/X&tJb'eO8T\[>PTi_>l!Uq
Wn:1>>i[&grRe=p9@eaPaX;G22)\)]A+eBtqF0pTl_'8%f[$.]AA>63i/Np_*HR18aJ+"6FhQH
Hnp_7)0*Ik(>5@+m\hK,Z6mcT]A13N*E'gI[tuL/Y`gqaP!fim83YJd,XkJQSN^j=DlSd<LX@
H3UT<j%g%]Aurk2TjalT.&h.s'@Zl'*\5Z/i6dVnQrE/d0TQQ,arLYJ%s"mXDD%B]AK!mO7.DJ
jJ1RMEN9pho7bo:f<3IB$aYK.RY"n&3MUqC2=PhCoh59)2U26/s"*DQ%h:l(cfuB#;-nHTIT
Ik7![I1q=Ue]Ai8n5#MB,CDU8T6S6r$U'kasWt*)Oqa)RUn1.*C`hKg`B@m^Nj<o,;q#+dt>C
FO>=cS7@nG7;R\em6S5V@R=;)WXh(M<r4]AG<;l6N(;0>a_Dsi%t_Oe@#769#To5:J/SDB*L@
15]AZTr6nmY&4%/;S_6G]A1@ZhE>T5RS+0Q0*6D&&^.mf+M[;<NQc@J0$a]A-Eo6kue]Ah5l"X`5
Ac9edrCQQ!XcU,f884*JT0Ll[RGql$4+_9+;\G"2:A(M`>DeLKu!-7fG[Ee`V%RR[s3GgE%<
Ugr2kiJ;p]Aj&H7QkuSB;L>*+O#;+D0_CSc'SPI.Bdu/@MaXS=!;$T2eD#s&r*NIOP'aq9pni
C/8=OdtiCNBk/$KG1"T,]Ac/AH.M\';=tKc1Y4J^]A.GW%$Lan7nU-RT"L9I*nNO%"T*jN8NGK
4e'T/4#<`qVW`KCI]ATTaF?_,k]A[[MjT?(pD=k#-C+2)f0I31bL9e"%7B7QEV'8*\EuV!b1fW
Dhb<3$3ZYjXr`G'/4R!!=o11/=Nns1R#<dD3#`=`sR)(9Fh[UXT5J]AjmGV"<'n8JEOf>OiX)
Or5KQo^m$_s;)mP.O=)88$&ZO6;qEQ`(!2H9Lp>9._`ZYI77&lZ>YNE5"+f7c$cpR,9Gr^J5
Zo,So8X^=3CaaDb$e$<.o7^K9Q)i;i-A#k)@IIIn^p%Ni!o)VnnRO\Kq4KF$=V9<M0rY2VP?
q'0(?[*t)6qrF1#4fgWRX3qU#L<9S5F#]AoCtGG'+Rl\pDWfP-Hm"3bAnqGR:a1F^.NTD*SYL
c)FP?Q`TB9,/Bd.LH96X]A70"j,W8`RlIt&,/XCYVqW]A499i?;B;#f-Ff=D^ahaP]A'o:pZJse
P@f(OrHC.kS\]Ae.!`!F>:):>los[k.Hde69H&bR4J5@R\,K29F3pKb&6Ye(E]APRWcNU;3L9I
B.9$Z2%\g_U0BRD-qj4/pK]A6%5d<"56m_b&`6P<g&ADRZ_,?223cBWGJImHh#m?('CId,r,n
G]A.5c%$gOl(+f5ia+c1OOp*@Q#P!A01/&fnYr[i_D!ZrSD6r8]A,)B<QmC?t"AfE]A55RK)>C*
NSXXkH?U_O1ILcA)g;$YMm8GdGh\6NCZ1UW>A+E*/SfX)dq5mO<<HnjdQO&+':?g-FM?]Ast)
)207A!W3S<sU&qm]Amq:lL=P6kJ_oal"$GT.EFeN)rn=%NB2gcK.oDpe0Fno.GEBLs$;PoCmD
+u@2IR3S;HIB)>?u!W=NnI'hd<dir?m,b@jA,uSH]AF&oE,QQR@id9[n4\N4"]AG!N\o_IVjh#
)h8tgP\8`%B5^)N0=Ardu^]A@ZqRN$>Hp1b*m6?("@?o]AX'27\jFApUInNj6Tb#'(2_,e,"1.
NdQs\=t;+O@<?upZ)sWOF45<fTDep>Eo@q+^W^W1C^+k&G*i>;Xo\PKQ99Q'G(m39X!rWBh2
;B5`c\j!rDt-Vbd!mgS/T.FO`?ii<kB^Mh=;#!VCET`IW]Arl5G'fe.ebXF[Ti"?K<VfifRk*
!Q^S@<6;0/,jd%S(EBLtf6k'-p>]AH^VAqjO66puZg;MG0GfCTT+R!T[''O-e@+OmH9!#'I@`
rOhRQ/+@V;8%4e/W))3S2'Mi+@$#N^`hVDeU-r!`lC!i"KYlog8)s0,ar)+3_B`<!fDMlOs"
utUr6`"L/hb%"O5lr4JTCpes?fHXu;#I8eRp`E)=T[.G/)R$#)P#fLh>E'j*teEKS'XMg3G0
7h5U-*LidrImCuU*)0dr6Bm:+qT';NThXJSTCI-Zd(Z9\P!7YsY?/]A!Et'W`)37kkG;pEF?F
+0#=oI@f39JYa1h:t\@@KDhW4KPj:OYpdaTa#).S^uVodUT#d#?Sd0_?=;AR(O8MV6+GZ0dM
g#<PLU>Y?\5TK%lW-@L;4(pZJW3aFO-Q#soD.oBeK026p:rIT6()U<9;Sm<gAqm"f=Do$)k\
<@a*`[]Am#UgnAt-++9D3OU7*L_>p"ECo"L;)gAVjl-fGb.XRbMC#r06=:>KJ";1?YLmIFKEp
?moeC=fj4#jHK6_UfmCF,X;V3PjAf6l=P![I%$Qn\X=d_a=lQK3;+'m.OAkK2!lMX8L1]Ah7i
G5NUs_A\*]ASa[g!"&&Uijua,NRK$PoOBroWGYY8`a\8m1DHq^@S)^CG63Asn<HYCrUKCT3*;
o[Kk?:0Y)Mq9[EuZk,nD^GQ#EIsgO?6@=CTH,a5'?g>6c]AauS"XSm$/KeR0^mbOY9LJ1T0Jd
Jh28kq6"tNg]AhocXqF$B<otpV(Q+O^nnIKKi;Nga)T'^G(Hgi>0nmY]A;.g"=[<kNV:Z_0/S-
3O#6Nmh%SK/_r38c)hRj\6$N@>0)^Fm7uc'(Pi4"B%+DK[NNH$]A`!8frVk+Z1brRe[b^bS;@
W:=oV'qY3,_s1%IeD"in+2rRoP6jL6f7m8=V^aU*h^k;De4OmQeU"5+s^na)lu7<LRd;i^.-
KfR.,)A;dKk)C9&:)[`_h9$FmQ%9T6RnYof:[LN4?je=lpB9'mKAG9jgkne`4f(g;CKCo`9r
lb0Wh61mO:=]A!Y7Kqd\'4S<UmPBVN1l\InpQNFDS1o]A3G^cnZGcPN)%4Y]A5(-`!_[W/j]A3Z"
$3r;=&fcEsm6sP)1S@#-[a0!Ah:ibRPX'n"\ini!o/U+^'L13lf[rFaod4qV(jcP^jYu6!RV
[up_,'$F\h<.,[i.^367iSS4j(1<_5(,hPqWkr"9cOS*R]AAVK%/Ae6M50_?.u^NY1$#q4/cT
qX5"n_"`,VnY#oKYpL4>`iFX39a40kMX`UIE0]A=DI[YY;7!Dfs&>EbrHO?'Q4k<'5=n&DMt)
pq-+764jpGn??">Qt1kGQhM%8QBPam3h^mV6'9^j<OJFEhBTt@+V-Hns(i34:&Vh_-(#V^Vq
Sug`1>Mg3[:)tH7dCf`Ai9>8sr>J3H2$6Qkt8ln8%ViB01'eFG/\r18A=GOY3M464uE^(Pf&
=+r1BXLWl39%PjoZb(m$ioQ+'?1G@Nj?df^sqM?V?1E9;u=F5Q;*7i?Ge6<7h*)m?3=iKAaU
?k%QVoq`?S5igcmtPLClHV=F"8>/Xk6l!QX,V.[\IEp59BFs!Sk8NL-Up1M_ZKNGE_^S;Nj'
UpY2"?+p?3rT'@Q''*]A=UB')aDj&?3XZm+UDJhe&7[8DTt7:Be4@J?=J2<nfl"ZAI#>pJb6_
iGg,%U+LY@H"-s$:0eO_h#FH!)d>Z1g^Unh5OKLh`r[SY"h;\`Gj(!>Ai9Wh@@3qpIX6%G5`
47Fk0M7_lB0&R6.]AR6'9dg^Isl/oR:XoY:7n/cG4t_G1EU#.^0Ws#laN#rh&Z94nBqnLgqXk
4`.I[QBp6Y=WAj#8]AGUg6r@uQ$V6/>e-gD%/C=A9S:O5XOn%?`g4I$U-aYI15<E?$q]ARRN%.
>cZjAD0X[iCj!kRoFS-D=M[BC-_LX-a%tE[NOIn?ns/O*45CMXs.qKhR%4KN:_goY\k$!+.+
g]AQsUHcW9R<@4A1&HEhQpeGgNg2#3]AC$1oBU%NTf*P]AdcV$%N%Q3QCAj`Pg?C?"8K)S$h,i!
4["?L`PhKA@jJAAcAVA"V'-4+ik\MmH-2$)=oVEfHMadr+5/nY`po<o<nuB?/*#1?5M'[SOj
WN`h.NI7G=g;=4aotgr*g>]A1O8T2g*M)DcaKPVXiXD2h*%N*I2%tb)G!-e<-DZV09`N3'a6N
C%<)6_@o)]A[C]A78jNP+cS7]AUJ*Zp!tng.P:]AAA2fZ$a^I'Q37c:r*EKL:-#JG'7]A@M'lElp9
*M5sqTg&)jEN[;IUkn:U"b_"F\Oscn")N4l<`67H'SE<s1DpbAKPSdjs7ur3ih,jS"PeVO*[
Wp50i%s:D_@kI[;4u79Y*NSORmdHPtF'Fqe<;Q#-qE[FHGe)%O2MmYn$#VpsQo6lnCYq)t(4
U\*YhLcQ?fT0)ke?"aCX&T-)D'n=M@-g/$SNt&Eg/6=FW#0*uE<=UI_7j"S_<cJjQBHcP"dk
j,Eb,JeO:JXUWbr\QhWdXEUMNQ%;LK??upOM24n("Y\A!pWDYEIM6a,6#:UD)9ZKT9JAOUt]A
Ci;9R&dfk$#l(WY*r)X2J>CK.n6>5S6P2'p!"\Ea$$<R#)L:k"C-NS-F<T+6L(!Ss0c)4uKJ
"8Do)e08M7Ch\_j#e]AaHJ?:=gmpc?)-lH*K$hS/Fs3:5P@(uQMCimYrIP5JjSb4[VL8]A=J_c
2C1OQ0o?Jd*);;ScsM=gfsHAp_F97mBZQ.Dg;V5JWr79kF#E*BF,B831`As`k>4r\N3rqb(E
Q`.3>P<'lghY^B0bleGJdOA7WkTY5i#t*u2g`4f.-gm6iXo5>Ic/*7-]AmFSg_)P.2,$FGTj0
h2O':dV8S%!T'h1TQR?"gVt=Y/4j`8KmE@F[qn-e>kLl98)$Ft;-F6!(Q+!aN0A>r&b+PeN3
o,I1a--D3e.#80e]A09594/_IsA9:V)uJZ>os%2nXf7?2=dJlkanWrI=NR@H<g.<M<\+#q>pC
Y\^Q`gpE5\*fPl]Au34D4,&O4`mJql=]ARY>Vj5Bi&u0iC%o.29ltS!K0i]A&W>$n*\B%:Q"Okf
_-dd#..an-Se@iFW7X_5e<*bRHq0brU(pJn;>4?bLh5))=t"t@J@eKUOuWRi,u^V@p;Q(/S(
79a37&hpo[d=^#>\$b,:kXpA8po42r,b;aRT819F!6^?>k_4aBe0SG(DSSFA]Ab!8;,_EO9qN
QN2:0(nN;0>GOMtPV;nPMK_=[V>pFUMh+$=oCfTP!91BV-E,@`9F\iHTUfD+BY"SaT:nF+'(
s4*R6VgG]As.d.r&(E0dVE'PI"7]A@rE*I6AGU\EP"P,FO1^MBuV8`dM[64e#'6E(SRN#)9Q\k
-[FDV)`@[e9MT5+4*?>7qFO+hNV'-=GXR??HmO]AZ88rpN/JGZ?J-Is[cke2iL;glmXs[rUai
o9Fg@HM[^Y0q\"VL=+VUGmc5KJkE/68f$4F:jS^iOimN/2T*SJPU)3G^iZ,o^E<TWFQ..f)`
l(7-\;=q(Q$QMP$TU.=(T>#A;8+<^c;JD\+o`J+9d7;:c'&USpqI'=r&a-3h]A,TC7QHAaIMm
\=V`$\WpTA&ep4*tKTQ9GtoEGP9eR^T0:RU6D#ZTfo3^H-VIbi[Tbd;N[6]A9oBkcS6a=e@gW
CCl3(P2.%Ic.XUbP&e,NA%&JuZXe[kthr_io%.hWoWR'o&BKKp!)/ur;3MSg89/,S;Ifhs(a
J<u&f+:$K@+s?aV7M%bo"II<KIRZAUshL!pi;%n@,J3Nhd<!YYT:)glD.iN/LIqf",P5/(+4
MMp"JZu385.-.d:mg6>l"C1*@1:kt5+&G#8U5rnTQUML2L\Gp65dlC7m3_9HqtUFVOt:p7b[
o6#3PJcs6KGupu<fSNFBp?$3>Dda&Z9UFX)Ec`?/%iKMqrjpj>F%s(JDgVFKeBJLXOX[Qk8=
#"l\uMfgX\LFMDW-h$F8!n3:sir/8STu"A/KZUq&Le;n_TOU@a(Y9-a*+P10JrK>>6@]Af;ek
rQ5f=u/X5qTRFI=;BP1e3,<QaXqF8\JPY+!]A_Y!=-#E7DgcZI]Ac:3Cd7<@maUa>>kho/nh0$
>ri$ES]A^u]A'R`TMeKUZPk*IUC!U4[46uZFF_4md[Q2Cd6ZXHVb^6UOaTeFQ.uG1[s-1US7b8
ZWg\m`YrmD;sThFdhSe)\uE6g>H[EZk<7sm=i@uZZ"-n5/bFB[<E2q_544#hI9B6\YHbDDm(
['[i1I3,FarTT-G)ifY0:0Br_3E7Pqo(f_5PRMF\G-Sd4-^6C:_DGg$U":f/SI"i?i7IPlR(
GD;8IU/]ARJNp%U@L<%+ni_C@1p>;_)>Jp6<Ff2gf5ga_&Q3oVhH*f<;R]AX/If.Uh9aMA1$q*
:TpcV&R\0MC2q)G*p+SPo-L+!Zfm\c]A`kgGCn-m1OlhF&%B4Db?"Q'`s7E8!lWuY=-3_bb(n
n7CqG^rlHeJ.+HQu^f_oiI8Vd2*FtcHk)&WqU"gVucBZ'o+'-(TJ?*--\)eZ!R1Sh:VSa;4&
1jYF#U%aa&7WPjK.c8kM>-#`kc)k.9]A$D6+Wt^A/"Qq^-rn`G3:-NpOIW<'iD.I(W6J$O_id
RsuL.AG4tGQ9-M]ABd`^WY;PFPTnpV96YO*u5"Q=l*-$Nr+aKj_lg\DK#KsdIF+Z,\?H4DtUN
86hf+2gIoj6/79$YO;54!V[@OCb[02A'6ndca$_s+DarmB;ndLHc#2u_7>]Apu8#?S':=SSTW
bQcp<t>AsQ=[CU%6-HQ(p'tPgJfY<fAlS:;LJ+l5.'jLKRB\QSJF]AkWD-.L`Rb"IV0Y?-n]Af
[[U2!%q&]A*Q9'+Usl)!^>4eY&*[/Gqlq*ZU.!e*:HEoafF1S\$-V#1+Q?uR[^'N(c:_ehV[S
H8K'1usQk4h,Y\oOM'ljVjcj#D2SbgDm`!a9KI<;:'PuZ:"UZQ=Ub5NR?_!+#SAgO']A74ZaO
:cL0rM"20=kdEu$,]A4n]AAM,\SI?\h),='T[DWgj$R<$-mJJ-RZ4SXne1='sAb(7p[F9ZZ@#s
$MF7X\H^5EEWnZN=k?0<AYGcLb:,Y8s7PW=Pf`qlh.IB:\m(]APjI%54_a41$,G1]A6!qQj-OE
,*h0ID`fg-d,LJSPPrcp8!Kg(eWVuF,prK0BOhg3J?C)oT77N`bFYri1.R9TO]A_R%tOl*"&B
!HmtnC(e_O>ijDEcg122/)]A<>&Sb=N0+0a$G\)+7U[t>^'tb-kn1,:cG6fH2Yijfo[JXf$6c
0meB:La(p!-UI`I>9CAR@upmnlKFYPMkV34`PpYO1+#F_XPXgSO'_%]AJ#,dFs*VPguD<d&CL
fK_4"0-juU7J9HrAZ7BD2L,Aq9)H&N!>q]A]AObM=J"$T1e8=`OaS$L>rS-BHk(i51^).M$FBh
-tFD-_-K@j))^P!25*<ub)<TP,#>KC8.\[9LocYVoJIqD27XH^ifc"nho1Ipe<Q<\=@:F<4Z
l2q>?&$Km+3.ULr;5,Ya&+!a9:@GSkP35]AG]A;Z]A`[Fb,(Fs'fs#D\-L.C"VY_5[u#!4Vb!M7
"eZb<o[18J>$>J9KQYlLaREnQPCq?E%Ee:@%\\RR1T+s.m@!S9`IJ(KWA-V=!2:LXR!Gf4_W
#V6UFVkru/kn&@u)6o;U^16Y!$`U=PhA`Z*%)i[MC0Xh$*iY]A@6G7AP.sr6\\0Y/>o4,[OFc
p'*,(.3Qe48+gBYJD\ES4.<4H778ia&s;*+nRp70@"7=%AF84el]AnNO@erXp$arh=\Dte+T;
EfnG_4?#A\NM)I_`oOjj),kBNV5_3q.c?mgQqi-eILo;Xer4CGp#tTI:e[XdBR@WCAItfB27
N%GR>:dBa/AiA10P;[f)$8aGdcdQ?]Ak"Q"'2VcS:ZrjhVthZ2j4F)._p1V\efh#5ulP4#-%l
:P&,<Rj4li_73W^D]A]A$!<\-XA=RS18aAg7OWWgE-Ncu1Gd?JA=T.]Ao=A^P>I3]AXKI6)M43Nf
t/[n[Nll\\aL]Aq_!mZ.]AjuNl5L/CD:5_b2$gXF3cT/X\7"9k/oC(G#I7-(_87PbtFaRjpf$C
RJNb3&08E%b*_E>rns@6C6`sHZ`E#,-?:c_13VVLj5VH?JL<BncTR6O,O!8[]AQN<>_u$6J\C
7VjmCO%C=0q["[)HOEf_;/L&H/!:rHl$9gF(=3B'3OA%@]A#(5'iXL*]AL\-mFt4r:XTt.]A@j:
CA]AK"elMYBY[DT8#i;oZh"Km`pBSj%1lpHeFT1b!2<!.`#Sa(eGKj^/l`.WrG$]ASf6s+\&X<
13TT9LQ)!&hFsKh/J*&!IC=o-=h-!@g6Tc<998/#o@3LVU=e^L6Xa$9]Ad@9;F$X+8n<X[n9^
i@=j8Z-#rls&Z"5sfMP2QsV"_\sYHQBVW1u?t>$0gpFf01oH%J:!7>M5"%qERA!hR=STB#c'
O"nbtOVb%0ckHCgg-NM!>h\=]AbZp-h;)5,Y1\IlOBat@9;Q1=gMtVXman-V%H'&$a>-oJ(\Y
SlaeV4/7rXfN"U+$qmN6bHslAVVU8bbXg'\SgI0cNRKB"!OU=\Tpd]AH8V0qkE(,5DSZufN/a
(.9?(l&4a']AU3aa'kohQe#.&%QLnq2JPi;9'T&stU<!L]AfKi/Fa=:m"mHbJuqr3(j.9!O^pM
0Z`X8FIklOXUd6Q:R`_:MaB[ktr?fV)FVcYd6r,*tZ*+AX1DDGqp&,-]AN>r8.Qr`s7kT@rf^
b701]AF^p^?\KYHqB?>YV)?^8hPZfH-@UZfM1B65@4khkFYWKjDniG0igV3rN<pbd?t)-W:[$
O;1+pZKAUmVB,(/OAa`"AoS3(Q&&[;T7th"03c@Zg47\bLW9Fn6/YE_JlQHn%[mZoo]A@Zijg
CkrP\21J2"tI;%$>m$I6J+hC_F?FCq:ih]A,,Ut21e@$!=_-JH`LJ/3uu.Z]A2eS4));X9+p@`
`Kkk"cOkYJ(LcR<9Pnc@E^akno%5Eq=,cCgPq/6SPj?*LR2-6%uE02ThWREYsaqV"4Aha>+Q
7t@s7L0"JRBii-:Z0C*;Q+t4VscVB)r96h;gi8AdP)+n;EmfsE_D4fA,rn.U;JZ>?V83]A3&X
\Ce*P_3RU=#;Ab:e]AO0UI/8mmYC5:be(p5r0^[38_m]A8JRo'G]A!#h@-u=?9n(Rq"8hL,5TkC
=NG(1kr3-BapNFl,>8WZDbK#:'+$TF#X#69f%FedH5gI.G+>l([Kjl['7/M:Lb#o^rbGk36(
tk4^V03[J7iN-lt'r*]AFFnEh&7BdUq'tu+?ErJi"pnM(>6BQCt4W^N1<mWL5]Ai_bZ6*snZr7
KStZ&Yl*#k::L3_E>(pK5@ul.n20W1j\iXIfAr#B>ZF@dbVrIp*Va_L7#&dVWB+PXm@LT'@B
<'H<CN-/C@VNZ3-R"pGjtL;Q+\!k>9#*Vl^#?&1!0aYD10U"\`JN%Pr6cXtmE$Yb+kA$u-Sb
2BAG5b&ico%HXh%tNZM#Or2sud`%j<ebU(i[,0:"gUHZe(2/rEmq16_R//?J\eju.X76,%mC
FqL#1OAro,O8E!^d^`]A4RMSuM.gnsSXkk^W9X24Vj<*(!D`!NH8B,QhA"f:WqD\,qDr:@dY1
KX*:>U3+Y*Hk6P/14F@9uMnP1@bE>%+Voe[N8<Ict$oTi+;c;b1F]AG<-U>49-BVlIrW>inDO
=/0(Y07JM'9l2%V:;'Nm:G48/r($]AuWZ,3I#GUX8$^G;c1OEp5-?HE;c51;G$:1Et[q(e59o
'&(E73>9>DY'LMFa<AD1^GudjmQ$/^:kO7e(h[+q5Y(-&5R^?O*4MZQ%b*P_HujbDq'\"bGS
(7OgRn&kQS\OF;j.@k:5MFiTB'm*cR(S6c^trS$FBA<!d&,ZnD`2CiMo[&.h7T0f,:2#qntm
-1@CH:?4%T:c5Rcf>f;U$1QJdBkY2CatWPHETO3Eg_mp*g*<9f/)R>27N4,DgoSIX?^:rCEM
ZL/*oB2OdbR+3nbV9RrTV!eYsK]AJ?0S#8M()/1dqM@\g)E9_4E+ciR[iue8i$2iDjFI-nd?F
U#@&YD*KJK6d7cEQ5'hsC,(6'ooY&"1?"08kXo5&BC!=AQ=7g?TD/IP&W%/kt4Yc$Ib0C(bk
=\KBLmV%`>iN@BXIss]A^TJ-m@7;Nel-PLdJi5(GMEMT]ADeYNt3RB_2)q*='`KUai*%+).<a+
NZ)HO#h<p6NN.^%]As9o1B3`D'AH2>s'*NX4d"6frN(OD%TKDt#+nK:3,8h$8>b]AtKVaTdTHr
BaAJ$?IOa^2r_I(hu]A^ufg4]A/LhEA2S+Rsr$.%1KVAQ&d2B"XCSoa@GL;Q-".+$micN1ciOC
fEb/eS.ZpPu:DNSb:1X;c,'76?V`<>W$J(h&ZJ3YaIWNg[:*:@+SPlGI(/39D!0#,klL_gk;
F54%,A'&;&ShUYAtq5r6502-G<"0H9i7ESN9Qj%gQ"/Tt7cO;I<\<QEq$(BC2X;S!#DFO5n1
ea-U:$;]AWIPqlu6t5KMLd_LrfjS#))LT7*/Nm<B>'7Y(pe?QEFk[d"qV$1^mfjL5`?k+i(kk
A'F6jn(#Atu,$/PY9RLaWlT%+#gSNb\$@u:f/#8H*@*l*=630hOniqFB6c3bBS\C;O!15pif
bs]APaik-N_"5!O>48ag,<Y]A'se4]Aq=6fdTYjJbOS>5T=F0CbMOrJA@%e0WNap`<Z/gG,Dp<?
S[5*F";F0MhSH5_)XBqA++b&N2"Q@S@0NVr0WUG-\K*f=m9;O>;'F#-bBDLerO\C4=PB$RX9
Y6"@@Dp*]AGp-^A#@I3S1510flW""+cgIJ8o,T<mf+$fK'%oZe]A5KfZmlCC_f:9/TUmrn^RTn
UjUcW[FAsf<!1SW[",\ZCaab@9'r^ATY,MI-HCW.2nrm>F';p'KgPf^l[9*qsE+"G^>iG/FT
C`Ob[-_""oX\d7gcf&1"Y703WCNfR<+O]AG[j..gA*C3#@a`^AR@7Lm0"GYm3Q["a#dmBgThH
c,Z`Nr%1di[RN<60jT0Gj>-QKi,iMo#S^=KeT<N!1H^cWDf[)q,)^#me!6]Ab^Bl'`\5F!g-a
eJ++&6nS15l8-*cnd2N]A&k)e!G<`54kFrD!maWJGI@lj&E:NdQ2PX0UlZ@Jn3JT+p0$TadVt
'&V-pfS]AjS**7IQh_.\jq0q>V?.$C^#e1=a@s45QYDo-#W!AOtj5))D:g(B^,+p0sjG3ZdF9
,CNp3mX3lB8C]AG\@-@-1INneOZdRU[oHD6bkP'b3*`AL]Am6>%q1t4!fm1UdM$Z-mBKe&erL8
BM?7%C$/"Mgn3R7;A^,GtA%_jl'HaA-T6h[5N1)imT$s(+ee:'@$l#*_-/Jsh;o:$GEC,C!h
q)RH8huIC^MJ&=_gWp#NA&WlK*-K*]APha+Qm>?a>T>YiJX_e?>q'l'SHnCfoaKPU71$.]A/9u
PM."Mhq^To;hQkni#).#W)Edp[O,QSR\EAV\,2Y`TV0Q+1fBi&-@/9cQ)h;s0(R-h.Mu.V<\
tbY5%W-RiffVJq3#pW>48djC>Z]A6AT\e%R/j[]AT?0aVp:j(gJDqY"hcK@#>o;7_D+F9;43Fq
nJWM'bl;0\-@_srF4I*cbp"DBBiog:,Jal9)oIeD*e*%4Z!C_[$030I4K8K#2aM,LFBjh#mY
d2<O3[B*-gT_1?0hQ1$aRo>?FU^"Ct/"9-dch3N;?-XDii2$:0f,mQ&#t@T`rO6i;&Y03lTY
R!,pIho&MU+qt3U+#8/36?$qTBR?aV/->6jOcfkFJJu<!#Au9NYp%BL6ngiVgB:E/X.QnBPX
2cj0E,HH((Eb<aWMcg:pZVQ-NZNNR>1%'"Ueom@thpf<AU29Sr)[*I"V&-Uo5,oL5N%4;g4`
;.9a#ZX=.P\>BhbO4!I5!pW>?9U&h,T#D,IZAeiRoXD&=sYQ4t;f9!NqSMnMa#Y^s9ZN.3`3
WW+1g_Tg(+Li[iZfp9&"$S>2$kLDF]AiN&;#OgY,fjP1qF'o+4NDj,61hR)u_f;E."*\uN@E4
=54p"iqO5<cg_#;]ANLT9uhK6eee_+kZIh,G:k1&tWbVZ8D4GH$2XhBELZKq%h97HTnTgiE\Q
aMIr4e.j%!-9r#Ei21-#5YQR_>)O8?NNZ"4%su:\A9=WA$BG!^M_&hC1e5O$:mefe(1NOAFh
p4-I.rF#%E$krcW4[XJlB^lXGYSkg60Vf7p)Q`6REh7h7iEdX>B=IVTWE>_q!H8]A;KFH]Akth
c-b:!s[ln$Z5N/q'BnM?L[@\WJ1eT.scJKL26Us`4R4hRki(N>-p>V`=>[asD\>I0\i)1V#E
M4I/#;l$)UT]A$EG_Jm8=/DVf(4+FcBe5.`G]A>cq<(1FZK#pSb`/o.R_Ec$XIC@);g?=58b(+
$"G^cS`LLHb[Cuj:Yi*Q=5AeYR(6]A^JU.ENH;V2RLV5d7"%?4FL*#\Mrj#<hPj_YQ66S,Sn.
6I$"[0r`JrJr#@2F>9\8=2+Bj7<d0CVn#p[Y#i8rjUBW%jY(qm?nCPPY`PL]AUDP*d/IfUDCl
U3;J$M0p&ZEIb?KC]A4ZcNPbcgPso]AP=67\JdVE(GhT6<>tC@p0F#.&A]A3,0_g=G#P?*YK"5T
$3I!#`.eOjkl,CRT*hOkRbL/r8O[VOZ]AhR1q/W#%tLI]AJu;%h-)OOR*;le0iQ.'#8'&L,0#8
>8er$[#TrA$0oC]A5%<@eZF1^H=@pSp`i(#9KPgkKj2Tg+ZQJ=9%QNIVKBf`]AlC*0s%cqh*nS
o,Y)fbk8-$3"]A/?8_TdXI+;2Ik%!MVY:>S806`(kY-[0R*GheEHp`%^HdW,:!?>S:s0`fq_k
SY<0`=QLr0X9)iM#_A\,MA.IpLhHs/e[E6#YruaFGJ:gVdZ5(J4)=<X]A[-JXpO+*X\S36VlQ
IeJ)7ThojrN&l6T)!N+lrg;j@kBaY))lU3l=>TANhc6L*JjMep-7J2UY]AN4G>gN4(9^p&Je@
[%X.?Q7Wl&4`d*:II5,7Bcg5oIT(J:/$)I1?7B288e_0_DOkoI`c\^[kL2b8,'OR5j[8]AVSh
)Ua(?!%$3S>FehFifTmOQ-t5f!_>s&EGcUA!$"!,F*3P#c#GYjTRmnA!7G^#Rg3hBt5EUrWp
@JobjIAJL6LK9akKoiqYq]A+s/+qc[,;g4EMjI`aj;Hf8^7qL\.)E0#k:J96N/L]AbEfiA%UWg
qL&s1D=mUE(8J\rMf?2C:.F7O'#qf6&Ud4Y5-B6DCXgC5KdsT8C\;ICg5"=08j^"_)4gW_DA
M=RC\0Sm0p*<pCDqSf6?Ti6Q$/c3fc-fKNlR1KG\5<9[;]A#*MtjF8TO^G;c8Bas+$Q%(f:o9
i#\Q'*[po`c#DbCXZL5\!`s.R%q1L]A-Jhu)';%c`DqeO[Bl'fcl0[b:Y<C729O@Z4j&G&s9M
$!9A2:b':T/UQZj23oFM7K(+jo+:qZht:9d6t?7EN=@>#\+MgUP-Kld'U@R4oOuOA3F"aimW
SmSX_n;;c/4Z9*u*rYr1W76q\P*?A^eO^RT7PS"5]AdVGLhP)"aReOj!DG0$c=(oTd$*]AG?X!
/QZPLT9*a0b=!r@dsseXo'UC6:/83m"miJV5cgg)+5)9Cn=J&*_m\F*foM\g-ehq"LFt%2`^
up1d8W]AOiAJYl]Aj<Qcffbi<SB3XVP*41B6KSh#X6aRj>Jc7s\/g<Pg2C@c_7bi2bJ(/jC_od
B<aFO`KV2Qf\1'Eo=Mh`p,9L`uKBZEOcd'cVo#u(1&a`,>iX9sh(?9ZTn%0A<8jcS!Rl[+Ij
apmaCOslc*`ep"8m!i8hAZgo[_JB^=eEbS\*RusSN`,2@EIA9[IaM=f'tH7`;8YD+#U#Zm\K
l.J'q(KlDj#9i0U[aE+bAagfSYm:s2o9/*$2bSD:!7's$d8SM]Af#Vk0!L0&+S+N9MKe[OD]Af
hdar;R^Ae8W3-XA3RN9ojQf]A]Ab_t(c(g+Ns+M?*,CGLq0rn&i9N;%;iONqd[X@Z!BYPZF/-B
rB\6,YSOP"'9DX=2\j-ii^.fXVM/2@sro2F=21HA"IVm\-jhQS'U0$69s<oG#UE+Mt8/b&BA
3j@9]AO\bQV"'NBYrkr-YHq7AN'ALpG-jMFs242AO85Y?L'LHLS8%PQVPp;_\XaY,n;BO?,49
s_be:',H=?sjA5GBAt+UoVk>Bp,l,TeqL^_@&\!r@0m<'V)fulAV^lbg_++Qf*^*q1Ash.na
.-@a%&"1:mC8h92a<XY^\83/ICu"3dCe)H4FgA+P'Sd<'Fp<]AA5[J`;T2a]A?,,&ks"J3'Q@_
b0rUY*B!`!?)BX0^jH8@7(O[NrX0Vt,MD'3'%um[pi204>'nb^&j,2*St2&$)&StgMhoG7-D
^uFM2.h"o7kQ6I)+b'=(Klpf>dLSJ!b/q5cK^>UmWC$FCl1Lc8F/KGRo'jT,G`?<B;$@j<_!
^QmY0-eX!T*B3R6>:/\/KA6-Yp2-J*o,?n#?=(]AGt[_FU]ACd1hQNjNW`-;qu<]A8GGt*.4b/*
?Rf?$D%Q$>;+8ZPbG$:R/rL>H$SttNtBZ#N]AGhsP8!74ntaTd_s6bBUerA+je:n8,jAQ?e$J
T=+WFWdK&@998TMA-/XYfZqZEH,Q@/YKSGi!">(]A"c'edm\T9*M;X&!E6_P1G0$tqF&<O^-=
U_5c>Oc]A23C^AbCUIkZ/HBF!g1*i@sm5g3G[`ao.IWDaoq^Z57>#@T$OR:NKmo^AK.Jk"UGP
JRddtB\,kJtN[dH_`""93C,i+N*aIf$R[Ze?+TQGDk*boEb'([qTa+dHVSbh#M6Hd?#ud;pJ
j0Ut"4aKZ_tUSBe(m;rdbCq&^=0P_,J\M:`P<?N/W'"M(5p1@E7l=k,m#L#r_S$h&bRM5pGK
fS(b[g]AA+D%V'+,Go3US:".&*]A;8&2X2qmN5IL<b$A5t$%?I%28ANJ[Q?L`/s&ll:LOoS^/7
IJn%*9LX7rB4hMl12$KK-N3(KU;[S<6((6P(NMOj3^eSNq#U0GFI!;V`Dd]A/Z=*kXK4.`5cJ
Lge:h>oLO('X]A#jiJi)%aT]A_u#@\p*"CnnuK"tsMP\$=&,MBCjV9cdY3@Tc+\TXjA!@-XmSu
OsoW5YJ;$Q#S3*&h6%,]AQ8cE0O(GX`MURq1c-RbdVub(Y(c0.[e9AXdhgG(W5[2QP_?Or'YQ
'RJ#4K$Q9@bk[c%Ho!pu3:uAai<M(ntEkRY8q8oQ*4It>I`r<2LmfSZk"%:2rZnk=@Zp(M9f
Eh=4MHZu73_Zf#?+fWQb.h^oj]A_Y,",Qb:ot(O3U]A#sI4ag767V]An\i2mj7Vd's6jk8SuIjS
BLKdJDEd#'ZuRHV3gYpFj-!SD(NbD5a'+H^W=YOT43W/D7C8Y;;i"@d<Sf*6;(n-sat;'IB6
-_I"A.@k@d,C5iL:-3m91Bo0q0[8.ke3nHR/<M)0"Ej<1l&Qf:^^mY!<2.QRj:]A#/6<gDP6$
fb.DMeU<!A@"='fg6s(`!fFR6F'(&oM5q]An6Z'-Te-,@524$R%:NF%-&L9LE(B/<Yg76T>Zj
O('XFQ#opti]Ai?aB\'rlUQ:,o5i""A%A88Q.i]ACZ\c.]A87m.>gee/fDu3Km$?`N2)&pSBS23
VIBCMP#(LC05cV1I5m=+DlF5TC]AkR5CLs:BYeoBkA$BN)8_HM)s9u2!A<NLR\I9j"r)gr-iK
X-,aem/<)_DG$#usdUGH:_*S'u*"q0+dr*6g`(]A2WFjQ\R*\c+6M*O%On)s77D=r>rg`,o(%
4e%\[.sAdj%bO^S)LR*o&.S=*iGTf6EPtt&,+_%YRobtPN;i'>/$A+4Nqh(&=.4Bq'LVl#Mb
7l+ds]AJZ$7b`X/=FQCs"^hQmC\a<@E.2c#4m4$/K,DU2ilk#UfE[G6@3.\+_Yl8=M;:hah!g
$hW`RM[?ZL9-G.@=mTeR]AboM=naudhWd(*4%8rO2m+[:omI\mJOlZP@lgDZOB\_>FQ)c+<JX
;:MSMDuq$B5)]A&T,\62$0-dBO7>05jj]A(=#pFj=!q>4`kDY#I"UX%2n=GkEH7dRb"%Zod46-
n!$kZRCkPjfkM63Ib@-!*"MjMH[a(:(3rADVH=WWS90fRe;H3CVs,2!<5gVLeG`UFXcMle\n
'O/(IZON/?%L>LVc^:j/4m#R$N'"Z)DIS8L&O%CT8&E0^Lj>9BmXCZOAbEk67AK,/-l]A["A,
=DMX+r6m>B:)$_oo)+3F/f>3niS$n3F`O-Oj=[gJ9ZkJ5O-NqN+QgV]A\!3s,C(l?oQ:eN7FJ
Z*60ZoJscXJgh-oh%CcDEk*Xa?\o*'FCUEZ3bU5=83c?_6j?lKmErl^f(93mo+,FbFEYa*,,
!&RqWn"%_`3hKu5FrQoOM,u:mLoa1Mm9q,Yut(+Qs>a8M-UgE'fdmcFcmRT.4P<2MG.S_L6T
sc:$u*J($4QOcruXEXiH?`f>OZ`Fet.6B6X[6*Hgpaa]Abd%&9Qqim^1HO]ADUQR5;#8(Ec?Mb
]AG)\5[k%l)=Y);(<pl]AoidZKh.W+B'd]ABeXH+5l<dK[OCP__AkD@fmk$B:Cg25;uugf>^oJE
"2FoaQhs!i'tm2^Qm%AWaZLNO[k._O=\h\WKgtX0I7#mKU\^U3OBF#61fH7Nj_NgcXta>0aP
JIG'DDmRH)T;'32DAt7qm@^!>Bhpamq!kW[/'``99O^ao#S7NqE[QG94UdQ0%g']A1iqcV_jU
7BllDf!XK3+F/DOUn(EZr:84n=iM@3k5;cL^8jd/@j%/B`ZJZpogsOd;0Go,GbqR/)jN\kOh
*X-m"K)&H09fO_7Vp2j6a(eH8_*G+m&pV[Vd@-dn`NYDu*_*V"cS9[3o4dh-PPR$^haD7d`*
T+?S<?mS^ESE&I@*VNfZh;8[6>X!LSa,kpK-lUo=hEc'3l`lJ@#.tt!o>ePbUn*UD?es[U&)
6D''jZ8HI[*4.AB/6'=(o8m:TrW(VXl8pT*JQP0,58!#jX-5b`%U@-a:AlSL*ZS4F>,>oMmG
<,g!HmgR,^6%T+A<(+LPO$DOQK/jpeYiO/GR'"$;l1H)R2c/VkK<r7&/Cj46JIS"4r_9;JV=
Oh29a]Ak>5=\iUn,TpJ(leg6m-cDS]AQnK$-[bI$iWZ)6j3("[J\E)pd>g-jN)WZU4+e:9u$OI
EueQnS.;M^_-:O>V^FViBjT,h97/6pkJI82W,M%UDB<;=SXiNpg[QFXH^N"dR0OPhcS8Z*R?
//eqAj<X2qCKjUpOHT95^I@X(^mkC0/#/R4T!S.P7A&ZU]AGti(U<0H_5J[D<B8:F1*G2-Vop
>+.Ldu'%pj?"#>j9hM#9SB:'q\VugD,@RE_o%$'&E')?r`K:ga;eKDhVk'AP7$Fm*b%4m//n
GN3l&5kDWhCYF8#Lbpb/@P0io>"5FP&WSeSK!&^%t.58`IRHJb`8/B!`'/AmZSX>j9C+W[X[
j?B8fLbl502nb[,3Oj[`hjpW`TfgU&2]A.7n_KQ!A-cSq*(rbI/(&FG'U^<pAds`5Bc`t>Sd9
"U89&6aA!X*M+u8)6/OW/fCn.7*`/&gcJo_5e+!1_DCtDrLY$4\+]AQE\LFKdTB,'?LoKacHt
OS8HPA%>1T?[ueuH;Q3)Q<a+CLt.aH$@a`U3t[l2JaoPs;/OVac?:7JH32!u8>hb`F0+5-=.
%'3!7JHt2O,a7E:ItC8<IQA?JCJ8mi"%l)nK5iK4QMe/*E9o-*mD!Cl$2\gVpNugGO]AoMjtg
,&?n:%P;)hQ*O]Abf.4tDh\RILW0FK`:m"dtn?<Q2qG/B@/,C<63N$;ad65?_OKISTb5=(3^q
b=!QpVuFUNW&oH`>q^jc]A(*H4cCAr_`K0:=>ssEC#dj%SE8_baO,oDY7'\\Q_Q`f7j_aX[>T
F(gp/eH!L%/_qe';X#0KR"3G!kB9S,AkN"(tX9o;`V\]AG;BS^&[@^eW9-Id0\b[b%-@R7N:a
^>4MG.9b.P/=#BS>k)GCWVc4P;H'sYf_u2YQ/*'-o>l2iDn%fX,@8,p8S^r:A(n3YIf/dfCa
Usg%.DWOo78m3mg+oBJotW?Z`dlQm$A6$U7!+1!\WI<4,H)<NsfRd[/&]AlhH;4/nP<"K%t&#
i,s*]AFZp&5!Aq__3%kln,$VuN$eVi"^fL"FjJaf(d[1;FH^/&;&=!n'kWDePXEbtS_e(!]A1q
JtK:q.+g2[F?p`Il95/Q^;o(`9WI9A_8dm&u3I.!C)(3lMRUR`A2UmfpmW$ZK1UU`C-35<tY
fokLt:rF9ror7$.O_Ic"5!h`feF$CUq;`^]A_K^\>$j-*M9MOh=%.<Y$f^iA`ics4AXQ<MM?4
KUd2:R'AANV1"Fp)l%;dT:LBe?+W5*=>2jIpQ9q\=OeZ8T1E,bXa''^M9\IJ.63@A?fp[c,F
IiF"4]Adi753L^ks'&6eCr+h#a,;OP2^iU16q@YN2*D<*&:b:(Z'L4/6ii;SbNp[=$_&L^@WW
q*&C?^pV;4ETXRfU\b=KB/J/@J.DRt]A9"V_+`c*_Td?>638NG[c'26s!OFbD4;e<&7*aOoPY
kq+i.&Yj_'=5Sn,M-i#fc)%Y(H^sFQFhD`Qmgso7f*b23(R*$S/?%25(7MrD=&12B9p_4/c#
(b)?W[e1fAcea83<sCTq>*7h>3ONq)h&GTne(ofi;Q#As!m^D(lc-,lpKBO&N>:QR&RbF4>I
-(1SF;0/M"FX^CtbV-Zdj#u+O06lE",1G7FnN.#m.]ANt3&ZXKIHLQ*qnt0NaDY,\obs<I3$s
;Uk[WmKe8Q@/i<J9u\dt/6^"**Y]Ab5:[RAP/j6W*nruO:W=[A^uQX4U=%4T%11qb]A;j1dJGC
LYX(G1M!U`+O>k1ZP60F_h!m34$9:-7K@mjaSbGZ_NPR+mh1Q=LnTlXI6$+?.g4Zqd4Tb`u`
$Y.:(.]A9t3+!LR$OlKHI5#=rN]A$t7>cEo'+Q6K*M,A@R!a$A^CtXb0I3?q6!KhtH-H$,q)4Y
S!I&fr5`ChZZ=^INc2G#->N)9-Z.`(cpc3MJMe)ic?J^J;Zf1Re'=c&%c^Io+m+kNR_R8,E5
&[4kuk*n^a-lCC.TVMs^?+!Z/%nU^1n!5GT59DrKnK)pC_\Y_T)-(eiolEV1(3sJUY+(u=O\
V9o,dO2j3\NH:H:TP!g&/lcT)ZA%YDdGRKg<F#?0h,k[RN4[G'Z?U\<aA]A[KQ2>=>i&ActS5
P0c&gi:[P?mJ?0fsdm`6SkZ((mj(JY?n4Gmm&(342P?+nZgfC;i(!Qr1(X##PB$kg%`48U<*
G.5a:-`EkkM%HY'<,r%Oe"0f=bI#OirZksq'cLRR+qMV6S<7Fj?I,@"[KB"2U7dTFI4^hQ$Q
oG?[MgD&abP5C(9l;m$Z^GA!<7`CWb3@/]A9^s=o,7Y!II/eJUH+A+h`=Jaqaf*hbCfQ2[^!I
_$a-(*"/#>_Ab0%R;FEfYgaYs+oN<ts.4FFIYq1BL-m!Z-_AC(&NsJLg+EWL^@[ti=)S!%*>
pa3@4S`8q.5`rCZ-Dh?LRl%JXn\YC5j0M:@O[WH<8)DFKa!a>/tmaKfeMKYjpX%C'uVjmkF<
="7rghcL,@E/m1,WTLucu]A9#.qF@R5>D2(IN9SL0&IZ2nLg@^"-r9sN5>[/"V9*E@_8Yok%+
u!JTV=::5BV>nt8Lr7tIYj",p_[L.qFn]A4&FqPqhJU"FONOg1A,V2*BHE/tL).(/!$blNPYc
VqOkh[*icXu#D^9H(e@DB6[8:megB_HDk47*U=&s0#?L".G--`32l6oULffuY7RHh@D.e*?B
Y9bW([URF8O/(V/`cu#Dj!j;.pN;se4Z7:!`QLJUjYm;\T),MdBd.0A(FbkLc!;D&".o-%I2
C_SoUqaP]A5g=`^Fjnd2TWOL<Lq'`#h82Xlh7=N5QCDW7pi&t^>;3n-48U/7PQQW*q;D.H":H
tX7Q4oodmd0Zh+&8?N8^_FB:lRdq`g:lSFG'5OXC]AI4?23nN18-DNJ]A-OS[k%VBbP`RnIhl"
XcCrDeHerLJ8%o$Ha]AD`!MkGRG6AE2lir+XH/jY^>V.K::<ro<n#\Ea%m1loeL?N)*Zl>k06
FS&dOFL1;4kS^q]A.Arp`%VmA4J$T@ku1idoK'CN3_\QmLL0#;U`Fb_?O,:J`.@Gg$54jf%"_
6R_QDEu_E0RTtjh#*KeljJ?1X\Kr)4C-!-uVs[r96BKi&7rDM3CQ$g<W0C]A<$F/r5/5l:Wfc
0&9c8pG]A)o+4Wf8k^.NK%M)&`=WHl+Yk7/r;BeM9B4>2XHth.\ir^+<c)(:L/75M%#pg;iB<
,>[0(="k5N4!oDTa*9HEN"4haS?<2QL14NGBKd%Oh;2-nO+<*'t44u0OE<bn9_Co*O-7A'Zk
+d*l)PY9;O>m[;,n:S%(UJC[3LsU90p0*RI@ncJPrM?)DSKLdYi^`.?]A"[Wft2C/Pb?m+7eq
ZE',-bjJ3M7/,ELp:nb.7iH'NG%&la4Mc.M!&Xp5G>*K#HgB!ka8p!9LjeJ#g`k1O2Mb*<Mf
D8a7Y-H[[NOm)a'Y>?NX!d6XY5Iqb_Z-n5ucfa%tFI9h#PK7j/qM`J\\V[SI='hJ5GUd@uDs
SdqL<qhi44>2hlsb6`f<\$b+Y^AS7k8HGYF"rr6p,FtqZ?>*O3X?!6FH<GogIgH-T-Se.8aT
)-HO=kjun1%8mFdn.#Y4u1=6aGTPLlEkZ.GJJTtFK1PN)?JFq#.:ju]AiB)-)]AaJFr&20s'nU
@'8OEO.DSHD\h"D.;3:B:VB>c$GBRCh5hS1_XomnnB8k$o>J>H):]A^d!E15ghc+ppa@3515b
O$U$=g(i+[BBN+?)J7gZpSk-$Ok?I-&]A'%P*^N/n@uo?9EmV"n<>mHQYM]AIr<?dQ`SP/oS9R
?JYLNX++te]AjL;L4n<&_:jUQZ;0>a\9:CSe=Hk!$h#_BR[rW$Iq#?tgCG)30_6HFT@llF^pI
Y/p>R\u)X8XR@'fHN5^n65\'8eDg.mI7M,Vc(+U1Z`(3k#!)qnV+'m/GBRF2M5UHGkX;P=+l
OgKICK_3)O]Aq\sH28?R><'%*J80UTR9jc5<UC^WTdA*^1>:"N:c%1h$ni.-/*XZuTB^Ui%4N
eoqRXN+?S#;6*@3d7ZQg@!SDD:nCk^r=uB^31ncj#67RSa#8>C7;lY.lXKWo/\q&JU6f%AAJ
+e<LkWaUq?YgT!hOh-%FbOOBRd'_Iu3*RT7NoA:un+lC(CiTo=gb;6!rc!UoG55I,ko'WTHe
(W\I9b7eg),ZZb]A0BH[3Ohm>06)@?kV:K"u^RX)$oB]AH.c1ZakYs))6,^]A=c!hmd4dIGjee#
i/)QQFe1qPX550%[u=r(k'Z$;+3+Xc.$+o><a^I<4o8J8Zf:N&rM5YqjTnKlO>F+F>X_#Y)A
2,\)ZUCcl9kktE*C!bX\OVG*&//e<9SO`.L/m\2un0M/MJLgi)'pP?h*k4>@Z3R@[e\#Z]Aq:
_u:\-C!`elcQsuU>k-_&)QN+bX(Aue&ij@7Q\X/+Fe@-:,$,0$Lt1SpoJ-',A5tj^!iaB/#&
/]A=C;uDs0.F,*)ri-pr3K3G8#arj9<Q3j3J>>ZoqT.cfKsoU'jYjAW(O!Q6]AV]A9:m@0n+$*s
aG)h/rjeAJY4%g@r>=]A\n1r9-Ud]A+'bqOu8GM`U#jA0hj#oW^:!a!TQHg^oZ=3H;kUO%HJ*]A
^.2LE#0?^I'I=<JW$+T8nh@IXT:%=Q;QMDp$=DZR9\4i\[6'+>Q%n9scP8J#R<oj[)#Pi&0Z
j<3d)I'H!_]ANV%aE`)3R,OXfa6+4VOFBDtap4)-8`QW!E,J19G*HKF`m'(lR*oeo)WM?X.9Y
nNP;Y.)&lac`i58#GHQJ=Rl5iS3U*p4.PL__e*AMQB>"e%pif<X"YU8;>J)(j<M,!ZqQ2,kZ
4G[MkZiW`D?'78XNUdmWBN@_b4\%p4m<AC@u!/!:(5>nfSA[oH0Qoi><X$@h</PG_07%C&G<
.>a2aB3i8L/l@"+5O[AR]A(&?q$_^[$IAg>Y+*g&c#iq\c`o)VTEWG[3/JOb&<AQ[VIo]A(m`?
Wl-PE_$hHHid*XFpaDKo=3_;bf=cE[hNJq!2Cq8%gUaApFHGKEhuAX)XFAlp?4L8X0dBW4@a
E0R,!@/pQ4_%'"XG_2,eqCtHZFU*bJl_"T$Z\Vj<($]A13$nn,R)S/A49:4SZKi.K+obC&co/
4s5KhNbV]Ao@;,-8*.(ah2+fB9_sSH/l>#>S3A!K)6H?5d<tk8a<%/FZd\hCp7"q!CUKbd1"N
^qf@3D_/k'gd%TP]AS"=#lXcDK<b$!:]Ao9%N-6qr[8`M,`KuefT<dAt^WjfQ_TG?aSU%rcX6E
_`OaQ8W)ssbcDgGglMKWO7[Ou.Kp-(_fjf_;0kU#[e=cWJ=LY7aaE.`#aL8RN0LGY'A!3ZQu
kJ:#ZU7tG@ZCW?sg1>oBY!m4+":4;RqpbVku6kY-<PgZ0%uUKmP+L]AR"e<lOejn7<d0i,MLQ
^UGFcmINB#I2Wgt2CG0rmEM7@n'r8-kS7o=-=oAnmdLGt7lQodjk/6DGp?(5Q^jrI#0nW<EN
^u5;AS:+oo\9h\-h3kLi'H8HOMo7kl[g/<Z;,e,CG=pg-)ig/'sqThh+qA7mK2oC>ar#iHc,
ZspDpnsU>`GL]A:[0^BhSH#Dpajmh`/+kR+Lp:09lk;$T]AFGc)m'1q'*G9'7+E#lI+=0J&RPp
6#Dm48(u(PlV8G\gQo2g\O>t/(LM\hN4jA2)<^()-R;Y`9WpYG?;0;p99\pj/7KZ7Ye(E9.V
42)\[S#BD5G!bY7(45%$nkXaGOaRM%%DDT11CX5t#-dGK&MGp2qkWWE=To,W:`cW!!F]Aa4#=
ZOBm$*7/qC%mLS?D1poaLb2g'gElnic`=0fOY^1!rr<r`MU5>h&WoUj.bg.,mTgr$6[#(Y6^
Z96kTu!X:JQg>T>j^aNq'8VYhCES8rl:FPlfBT1&mNd4Ub;HP+RJ1+/6RA>X>oiA:)_KdZDW
E?=k=?G-S^#&5q$sDF$3GkZ$&1rgF9$(?U"@3n,.9RGq5B\d-)-)s"!.P]AuLG9`!IkC5Z-f(
^hk>/.EZ+D#?$q4J-XP92$R8ochV^oD)@K;a<+Ba$IJ&l57>;Ceg79s,Q&kSiUCPd*_Vs$m7
n\Br0#\EAYO[F'lr$mrdGDVaN9BR.Puj`5U-t]Ar8W.mSpK;l[k/>_8mdkS#pGUH;2A.kN2jZ
>elg6\_?\RT--"l(Te/@nZsc?I9s@15^/D;Jah=&sT9iX!DN4-UPPZJX,=\D95g7.E!tcD`e
sW48ml-:&0Sp]A4)3V((SHg-7&L\5o;e_pk8lD@RSZmOFi`b]A;&n?"ZWm)EFfHgbU1sSb(.Y6
82/)l_KOn"lTEt9^]A^c/T)DG10A#L\!?g`?8iC*J1BWU0u]ALF^'e+6/:f>n)I[/`C1a+2X0-
[C4)k')8)bb]AjZ2iK,$GfVn^O^[\>Am\YYZ?QX6ER)q9>X^!HQB,Yn<]A@Rg&lCDk!!)]AA.3g
>QMPYN9.B,druqFKbH]A-aaB(*<`KlbLInURkP9_=^L[jS?fB#CBG%3eN[40joBikIfD$IL@=
4!(!IJ\[_Am7M,^GkAq%_Gn0hJId8a8kusWT9$dEs2tKcqUg>VSR_ePq98-CJU]AU9`ql,HpY
kluPXqKJ<bmF"7>JQmZ@Z^siGkeU=$9@H9V`+]A)Z4P,*;b.S$(_A#Q/8!cdb[P<]A'<+3Aa;I
S9EO`GH0'6b>@5&kagSZ2N3T@&C`V,S;c_%uS*(2"bRi$[(iMHQAS'Ys@JgH]A:C[j%j:klWV
\mV[OJ+8l?>T3U84Z@4-D;E"Npo7T&UG\`+.<(P\]A+_9uli.mRJo%$(+m4RRgO:"(D^j`Ukc
3d-X,NmPFSZ=2g:_Ll^@U>8hqQbSZ1"X??'AA%_nk\kTjbIdfKPr1Mmi53ipps87p5C)PIN4
)C",_XY(p)&ibKbU(///'VSm>`dG\q*eO@T=]A;d)C3h!'>0F1Xomq/^3L7i,![q'aGOhs'nj
ZB<q#An6CJT!qbZ;B^X+E_8`nqk8BQGcYR73n0gI*UH@8QqZB,,6,o)OYS_.SG`r+\B>Xd5S
eHkFf=p)EBRtqj["8?H]AsYQ_>>1`0D-+s+0U#O^ePZs&W!1&Gjm)^9_..Z8WJUTHmY$+[X`U
GK=TLf>7`rR,&PU=[?5/6hiJt48a/Deb',Y-f8f!>+^&\iJlS**]A]A<k4%>F..:`")R,MQYjI
"%-SQ-oCWPm"h4TM7g*Z%IukYH:)`JEM3Zf7?%"&EfjWI)Yfq!ABKlEX2;$6_Q"SKY"='RE1
KOHDB2p1A't<L1ZK5_^7eV$`AAi4aB4Ml@O0>8Uq5Z?_N/plCDgFMUXf\28X(.s'&8Yc'mHn
M_EEA0Bsc`)VE2eCN9VlLlR]Ar:H*[$S?fT"4G_g__G6aNi$:Sm9g*kNT%8>-_hD/Ljtt/i3L
m9*cg,=7&/SM_bSc/_<`G<cBo`/oKTlYs+b?TJ/BE?8-ftr['q<4c!KBl&8i&T:B+@)BMO@p
iu;<-H3C(Jg5oB55[26P[F#orATt>:Ybl\QhdhM`Aa.V,'Ge@_KcDe-?lWV^R"D"^A2llHA*
tZj-1iUIT3*aLh3bANlOmp/":r&Tgq'$fbQ0`SLWZkef5/L)gdB@1HsJ]A)RcQRDd<d_SER4N
;()Zrj;+-!u71r/?@poFc2oapd_='gS93T6mFu\kETQKZ/D%si"#'J4KH#V947MRjRL$'">K
g\/WXA+tp6seju`,P!n8r0@*fnMO@5jmL,]A2n4'f-@$HlB[s$Kdoehas72X&EN'O%&8"41bZ
3Iio$b$/PeJ(g&!.SL)Ua[J%jt8ioLM1NEK3-/,qA\A$^J*@4"*E@=s5NYBe4]A]A?u_)nX*.`
@1spmJZF\`ABqB=="$@i*Nk`q;!/V[_2cKrIr>&aQO`8C[@f4RC!HXu=r;njd8kDI1!GCpi6
<j?cliLuA5%h4,Qsh9;>Uc1491\PSSeSn&miG!dN3Xi4?@QL;S^SOEA+iU</i\N."8>'WReB
&.^pio/gdld:`>$J#4mC9G#i;G`]A-;OPgdco?(g\BnG+.`.i2469#=u[nj/elm)?AcR\-=GP
D]A1DZ'gF'BEeqNgsg%ElW.'=c1)pK=<pjH.,BT%GFo;9p>#h'98#J&r<UQ!,]A5SN\&ploZGY
c.bF'$cJ#u>jp)JmB4:=R*>lp!U@sO'%:#FSbp<g+TroqBtfqdZk1(5D`-a&^$.4U:G%V(7s
BmW)>@Z>oqrbeTAkE'u$h="Q>'i":=m_Oih$Kg`elnI]A"f6^8'js&JC?8@Je_pZ(%Tl]A@I/d
bjUB=C7j$!JR!$O`cZ^2Id91/ZePD&V*R%2,ZZ+*MKn(UTIMp[C!VWP)a1npdm=IKTV'N$5:
p=2[j/&d)@5!T]APJ?%oO6,Um\hiCjXgeY>$=fV%XF.S8NH&f#Y/<-[\"mrmlhg)a.Tpdn7[%
V`kn2m24fitc?CV38JX/Dt4Tfkki)[R,R7hoMWo2i&8=,JH[tO$B"X:$`(K"u^P0g:8pT^8(
_gg:@&,/s_ue+(uJ7I+%L)m0F-.M37eE6B7NiB'a*jkQ['/C5fSJ-cLb`8SoD+s!4Kt<%aR9
8Hq^G8:\g/f_VheP%$Gi[Nt`3(4!tU)\3:j4XIY%>Hs1MHZJ#pd9V6u=Gt[C?/MZ3;?0E!@s
l_LXXl#KdW>?(E9[3'm]AD[JnsBt_hKY8iD7iHM<]A+dOb0no\.2Q10!VuY^_aG`2K\_YF]AmZt
a1^WnC/(Lu#">OQ:.2Ng,V4+9QWQf)[e;%Mm+SqOZfDjVL$8dL7Fi,V&&<8(Rj7:Ge@=?T9@
2`-E):U99Ua($na_OqAk30.<&n5E4LXd(m")3.7LR,:d4mJbf,'Z+[4f;#r+PDb_Q.2J`O3b
8d'rW8TY2iW2I3bs7EtqT/E=AM&3XNXK8TAcH6`bd7aOVjd=o=kN'@K+[b@n@G&i)"B!S[c:
2$gfWOmG59eVCo(C?#l3PR)9oU$X28B(b-3KYld<PYNAs;?E3Vq,d/1_cc_Coq>l_^X<.^qX
;EbUX_=IK1Pdi@S9jWA2d`n"X>6`V#j&[C=\#?'DODiR5Jc9(Ht":)[*-VHk31'@C*iI38.S
[8;BS-YD&pXQ)uqLW=%Mg+:e:]AVD\7)#J@7,=%LE%LN'-C;WWa\qeO4T>[KFQCM`X.aLI(C^
--+r2>U2+X%PXe]A<$E<*\OVW*dD<7Buq$qgekN"02^-RG`Y7+B$:Zd!ThK+$oa_9"LU?coAq
;NOhJMteCf6X8U3T_hs(_%qPt(VLT)u6NE;%C!2?If%jN7:iGF1'oGTj)4^j_*/2?Mbm.)]A0
h0=<PL#RB($&f8sic@:bKWL`8%*/R>3t!_kVcPC@W<F\.18^DW((-T#4ALu,^)i@]AV/&I<s&
!shWq`<'*4lo^(3^L(jd72DOQ%1GHt8ic6:i$L))3&oD=37a/^dN7=eTWW/ueN(_6eDK8P-D
,-Z9?m;sSO2g?b(/g%s5QFG4>0F&N0qH!hJYZ0*@J>)_9p2#es#b!AR/(U+rQWBlPG_\."Ir
Mfi;q/c)HQUsEH\sN1q5J998gmm/<Yk>Fqe]A-GW3ciQ^AO3d`8S%]A"eE&h//7S,gF8IFuS:S
TMg-D%Rp6A30rT07&5+USAC0TI)^1&`Gj!/44F=h'jUYbDF$NA-\EOdZ?eQ4AF=MMEZ*#53W
SL;]Ac2$Q(?Ps;H)U/FQDg\%Dc\#!sIUXmEpft=^ed$:$MSHr9"q$4Z-aH_6PB3qfZ@=n1"-b
l=r_?ZIC1mlg1RV6W"q&W"%R9G(=SE%#+]A5b'R?l=6K^*YY=Y_38JgCe[iMl82[hp-e'';C'
#+2fkPlN&qo9j`$4Rjl1>;o6:i#rY<DgS9j*DV'+W=;-=g>ZH%9EGNLe#<#I]A[-gc-DQP9Qr
n8.jek<&]A^?%Vo%J9blN$i+`a8-;c[ZC)!DmDqNeeGl)1t0HeoBmFC-'F8C2!T`cJneImg-9
A5JaDakV_+_>%n;F^8P>6E]A$;"+=ai/8i?]AB'aht+l%BCBM?Z%@MQLNMlc)N"3Z>"GuH0VMF
I%InHJoD]A0\!>*,D/nAQYCFrtUEG5:_aE;Kl;W+k-%\*d?(WFGa&?+T'F5lubr6JdAHL`AT6
I;p`sF2QhnTj285lI`:*.]AP+r]Am<+]AHPJ(+59;45/A/9E[mK)TC`"A<JdlT\""Vs"L#cA'&&
&Giq$*eW%1@CT[C`);n8c'U8siY9GV*><t(XP//8BD!:\4J)JW[5K741>*_,)OF`Bl&kNh-O
]A.iDSC\_)NVs1c(5NmY\th`9'Ftqg3WiG)5#%`\B.HOCZdkUnSF8#^Mo5OP3-flJ4P+@s\hg
GPqK:X0p0$D%)MG-mQU\"]Ao04*_7<CC#70^eH#U]A7_@T&(CC^aRm[$(sUajim(=4g32TmWBV
Xd,IsEj`KK%c4&'$,XUChd"$ilN*`Q0a$DMOjG[5+:^t/,9^IV0-eV7CtDXD9$2"C^qM_si'
+Ua;Gk9*>/_:pD";?c@nLV9UQ)]AOYGr0o8%]ABDW;Q!hP\kE6&h_c#*)@<So'd4$\$uVQ(c&9
'>g./N([Qs:/mB"0B%0r?a9B1U7i`?fB-Vol/+:@XIVj222Xd1SDmXjl`3F+#Yide2`15?G,
9cR*Fq]An=7+h=-&$ltolG(HnX\441Q+R^:&%hVqUIXGMAEVRVg9:s[Q8_s2WX6eaC<h\+Zuo
M?>".5$:Y0<n<L(YMF4Ob&f,I_bCCV..H]AJ[#p+m]Atq#"rEge!=]A,VJCSTd`Rc88oor]A`aZW
L5]A\s^/nm#9ne?Pa4F6RQ(lO4!SR+opi'#rC@1M'1jhK&2nHfYCON%biETJ]AS9Ja!Lo=hF7t
#4JQcJQ/"T=!_)qR"8ci_^=X&`O]A2#J;+=F3ZfraVKphBmW(b)f[[V3d;Y[[JsoRh+gRrka:
QOINTVlQ;9)kqU33>2s#fHoj;A3;W0i1C6,[UQpCg_QV[cDKe-:;tZT:K&BG4h$0YMaI!bKK
rWke2W(_d*Xl_nZoOg^(P5t4n[iK38]AMtA?F7`QRK"Gmi(j/'V9cM#3AWV,lb?n7a`KiGX%W
_=6CPU(Q<]AohZLWOTW^9q\f&''s@6lJ<@sBJ]AN0iQRI%ja-<U8PBHO8kr>Q/Y'_2nno6K*Pb
YiM%CZ7oE30.ITYeBa$ir;Mr+<c[cI_]AA+Ujl:IEkn-#@;7sL+VgVJ01no(1nAlY)mBF`GGi
8RqX1et.8+CJ]AcT%oQ83Z%VSV.;?5E8\n*QXfT);1qtYXi<,72r80'=JO6>5"1CH/XlY>@%Q
3F8AVkBn!,:?G>8Ue[_i)_r`&mB<LR2eR&fm-;0l<lGri"c=(r0TDYOK2eV(m$K_%9QVX2i?
(O%?NZe;-h>*ic&pO/SbFhfeQVZ@a-4QDShU*M<6C93fjD[n.l%FQb?'DF^i]AUl_Pq)1:VKB
E9A7MgR0d+`9KQNe^la6QA^&AstJLh:Kof4$MeRhkW;*0K`X?o@g9?SW2q<MGX\aa4+*u#(K
:ZQbHFCC"H.r%i)(beC(%t:`WSaqX4f0#Voop8Q#Wil(X@OtNmdG:.EUMHaXOj]AZbP')VDqr
-]A4UTB)"HVVcFK_Tf"M)T4UnY.'nn]A%6Ar_.,W@_O?;<IM\#l`2XVZ%mp)8X61_W>70*Y;-n
s`ul';+u7JFZ>gfDH4=o`(\;uJfRY3Us)R0^M;EI>)@>0?5m[Q"="=CsT@IP[I+F(\%jr[J*
Y6Prc<&=Q^jZ0:c0?CS5H7cN)M<h32*-D(NKoeNqnhNF'\=g@-p86<QNQfSQME+-(O]A^=i0:
i`[-Di>D7p0nIp^'PP\f"-D<i41kedMsaleui-#<\JoG996cWLGbYSH5e'kS>;P8'C9D*4uT
@_VF*IIP`N3tZO.[5-MLo.</rmijNE$<="h_,b`=YtPtPn"L;QV`Qt?E.==GQ*+c<e?H=ML^
U@>RpgK#)W[(qPM"ZLhZX@"L!?,?Vu'inY5`aC'(ROAD9`cAM%GgRkV1F\C5lNO@tf2a@:\?
#Vmuk1#6jWLAmQjSU(Y&8;SQ&;fI;/6:/YQd:YnYe--'-=Si`%VIUo?>)Qc1Cor_+;]AZnOZf
cRf#+`G#Ya[SbWEc0<2mKu$IC<Yk_8QZO0T;AuP>,1Oso;feHZYo>HbnNW[\9t*@C3:I6N&3
9++]A_I/;uH[ePC5ZffmS(AQI:VI7KPE1Bq+dFs)A_98FYLn;1=(m0Sc/[OIX,WC<P1]A!j8L8
F;64l$)X74m13Bef=QTAD>d+%iOtjignei!0Q'HOcVDn+^Fn3Z-5E0-ZM%7E%&GFj)#4WRHL
\t)U822cZ-GA*(-=D-A8?:7p?iYoGjdicBZ=K%W9>em>d1Cp'I+0h1(r<p#1)HclG83\.-$m
!!A)%^mN8Gdq`O8YEWN$*LgQR5Em%@klEW`:@=<Ool4\XsPr7r<4\,m:gJJcp[I>Z:c53cXV
+&i3FTc&3Yk=0EF;JL>d2f`"eTDf"CqW>DK>D'`3;cN8_bO?6(eD&*19(2K9kSFpRGI67o.a
Fq$B778Hh@N;A\^L8)f:'R$&"rFONC):4)!Dmk9LfW[TbWI//4]A!.-'*@li#CRC@]ALTr-+*[
If,J)14$r9:ZYr(N<@YM9'B+fYZ>8@#(9c(rXFPWNgI`,K=!E)UN(;=J<<bod46GS,\_^jRn
P0Q0@dp`[YH;')Mq9Zj5*,J%QBIcW7ung/QClVfaNQ2,6_T-lX>H"W\5]AU(-Q\-d1LbFKGD'
Q3T2,N7ufVoKp5HW-DA%"Y*]A8Kl";*Gd<m5m'ZKgdZ/Nr>NGMoL_3"TW`QP'oPGAYQ-'$G/P
,tQ:niY!Cc*\n8d!Tmnk"g5P:jL"'/:2Q%2s2jr67#e14Lb^.K[8)pjcZ`4;(g-(q8A>dT_4
,(/keJ!P!p%IMr-D1#)&O_I^&g"o!%d'.I&lC'V\<-"k8dQ_\(Ru5mP;m2j0:*\c\Zqefd]AE
nq(ue1Qop!re/W]A_.$g3e4@gSc$D<H*<[`F2s+(@##k/0isD]A,Op!)N#p"?k<%CrQUkL5>9u
SaU#kqGsfR]AQ(9OPS^@`LQ7_:$CZ94e`Z''<i$k8HCdKIOpP7+=4iXU#r)kHfYS6C'cRed!X
gbmTmj3?eDI$g9DD*J-C:Y=6FhjB0"/bnjV@9\3mPdHA2[d&$?O:anHkbY$eu*mn<_&("in\
G:l7C>F1TD*2!S0E)K]A\#ukVWUQB5asT=hE]Ab?J2V<Hc5922nBj#1T!U"e#/*Bs9R\t&"cib
S6GoicGDi]AaO'JGILBDPEM`3;AZM=8+.plWf]A7flQ6K8PCbnP8]Ao.:lW5WM7''ZG%TX>lPW&
Qr,kfJJMO`.6E(+(JIFW9O8ClYa&EX+`#fVB%]A.8^@8rQ.+3.Z6L+2Y^LE7`99!_VrC.;C"P
lma<uNk-?XEVZU\OlTF\"H,HYK(Djq@ns7KbRFYfB\j"`JuY8fKjf8Jpn>/\Q;anPdj376RA
G&@Zg_+ihYqT1FFCJ_B/8*T0#@RPH1GPCd+OodEE!LtSDQq(NgCl(<M`6a">heSJ_n,)Q`fr
(DEugjDX.JE`9t4=UDt^)*=-fW_:X'O=@9ii?7R<RXH_8+'ZPS<\(eaJR![8X%ReX+L?(<g_
/e/1g`.q%6FsBiB`kdQb.&+44H8(K;8VN9F%7lugbt(%ZC\IKoi8B(noF61j-,=%$>l\\e<g
)<$SPfH\-3pb3-s&)Hs)2Lj\3M=M[O6'#JAQ2pn"FDXFk)AtUW\L7^uf]AhA2@H_uHo547^hE
g[Cl`\60(a1@&:`.X5i6+.6>0GWM?[4e?hks(QDiP:[mYiRGCErDckH[uiY9EVfNlQ>.S24)
tl;m1[`n9N($D'"Pk!'It@I3Z`baibE-[UpTXo.6QD0I.mM2p[Eq6eUO+5E)JNr>AcO6]AP5)
6Am8<oGXn_tX"KjS#\8^RWdH=OG(mMp"`0c"37*7ZP4H6"`aj9/2V)QaX_g'Go(Rr2DRZs1I
f74#9JgcLG(^/Xd_Dkl3U42eOR&6=n6i_Dq?tM0dO[h+>#;oUn8=XY1-Z>;(K1kYE1(qmq>G
K!gY#Kt#gg`[7fapE+,@knpgOr)hfo2,MB+>#U>#]AVl)Vb]AQ@EHBSBtCJmX=>Jd#S7j]AIh:S
eT&#4lqfmcAQA1^otONYY%^@rO6ZE^>Zpk",Jg,pe:,KEGDo>9Al!h;J">n=iNbitA1sG>'g
OWDID<gRY/,6GVJEY$q)ujY1/@@&..o;b?:2j7FHG7sBNG(\[.G4d!^/3/PhTa3thXcS/,kY
.KIH09^Cih4M^QQDe3&`OD2Q4V/:G/NkfnSr1of*5'r\I`;rXkd]AI:1M#tfr>P74(#3ntrQc
QmfY_&EaUM?DU+.uk9(370,R\"l&T^2Q;%'967=E#d[[\_3HN>l!D:2iLeZA!DQ6=L8!cp^7
q%<4+VF+M5JE3,!2hIp[$(k?^+1$>b%3B-?VRr76dO*4[;&$rrJ2eBF?pK0<^M[<5)Vt'L[J
-5d9/(LC(G'4S^O"XI(!ir]AIrI0a:ZMrZKg'.Xpa+$M$)IH*#Fr+5gAe:$5AO3nmtur0"LB+
W5*/KeiJoNg,,$.&9:Q1L9[Yrc%!P#DahC=o.4(fPFp_(d_EeTKhZN=;]Aqjl%Hrh#/1<$_p.
gaX_@f[8jmunK"ad>M4M\Tu<eAseB/<r"*I^o4B:>6@e/:UV8LiuWH;/=dG.b\j%;.D$]A]AXm
UH4dRV&BqOThCC[4tVV7NM9E`]AF_c;l3%6XbSRi\iHTOcsao+k]AUfdR><5PG5qs8QFoY'lZK
+k\iGO0cqujq)%On_JWgHpmMtMk-@RG9dQ:a2B[!+]ACO?iGKqe^ibcICgdF4=+dgroF<3Wp?
AM[o#'W/lp+RnlcnFTLhb5F,l$Redd&RgA:`RZ#t`RJko/@_@O[72A=NVW8@UP?#c\=;`HSh
XXUM#%GaBo]AM!.XqH^A]A.OgO\1UQRmX2q_">@n]A]A\^D.KTSF`EXVNFBJK6nN+luZ-pGkG)"D
2m.H=0Q]ACRKc#@n^:rAfS)#dbaRT-%I/MopMoS!`AnHG7IV,u>54Ef@;>7^.mHSP+AU'#1RJ
t#"N,eJA"gZ>V8cF_;W]ALe\KZ+,^<37Ne**V"^M_n#Io,V.((PL+lOl,RGmL=4pJu=QL-ZSh
+sl>'^D2.WS.!5^`'eB-OBS8XYi4g]Ad5U[%i/5M9JBb1\jG1-5N'f*dF@I9t6f./!9MJU>$&
RWrk04FcHmla\cf@9Jb1b$W28"=59m-UAWK$7qSVII5G=gB!OIORGm8)F@)$cM8ZZ?&*/r]A-
fABbK3M!8",HG2FSVsTk7DI2nC(=IasNAjUK>$lN5nk,$dV6'r_'"m3Q<:*V+:itge=R-8"P
nc,=gAY8NEq:cs+CHRM;V=(s)sRg=EY$9h_<RdZr-#+]A0ASahi(RVP1kRoc%4u+S`,l@(fq,
;YGHO-hU;T1a!9D8qg-JZ<fG_jGmL!mFn^,:bdg!M)Ff4Y@3bi6e_19d?X3E"7BpsRo?WT_3
M5;)/Idl'JT^/d?eZRUskY'Ia>g35-;]AQN\9->YUP"K:7_\@cUn#AB7O,5bYo?N<KU2lZU5s
Bhjjb#?d7SUIPTO9Mi^SQ?<#r9KLF%AM7FED91`l<s?Vc\Pg&QZMH:O87KH:EdB:UE9EL#%L
5MA7&Bk^bA2`u,Q.gMB!$)ZfLq^!,q'=)>8n6&[KPU;e22!NLB2@@rWL5\:Psb`/sr<R7-=)
I[qlcP=be>Kpn<bg?YA12Cu\`7/5X+'U2.Ro&m*DUcW;[;R6=pnC?b5*HgV0*mLP1F=8Pb9N
G^r[3+<'O%$&s#)D)\!NMU<@ttDg&L@k5%eF+9%BNV$2G6l??Vt2,SX7f!p6S:F`E7*TCe:+
9Z=u&@eB9nVmD9t?n.[iK1_EsF&ZYkk+@aJE#IoF54(8LW"Ulnq[UYjY0"WF-,jftn3ifTUt
)s3Cjh"[]AW#beT#NC#J\h1^0)-d/+Hs3or.6PW:L5G(PD:"a9#I^8O"X!?4H[)j#:'2a>2T0
a?IF]AG+ZFU*5PU:<f&]A'>.2s[rmpl*V'H1MY.K=U_mXNdRAkgHW>2Ne=!5CY!Jh'1`YD;FE$
/4<S#`.%rf;es\VCjr3#P1pJ%HEne^\i5OUILjrSJ\6*WV"^L&Fqf-Gd"uV6[LVnTH4#'N)e
*\e'Zt>]AJ)"B3?2TE^r9;6l3J=4EuB?h62YgFl!Z3n%9XN`/DP%`KI`'\5fFJ.Eu"[19Gq>j
\V6T33!10/59bPk^1Xnc)mG&+:Z++.SD(jXk2c391TrRlo`;tLducZX0s$>P'n;6!r+^]A,"T
PCq??-/<Be!VHiqe?"+cK;9s$VU9=C3+eG^-bgT,[39`&Ee0AA2!U/MFu=j#4R)mm#*Wf]Ag'
5+i3W"ZtI'Ba!T`B<h,k5>$C2#0\qB5:2+ErO2Cm^lc3p+OmgV9%fZNq!V__[8'VoH4M+mOL
BJ>;XqgI3GJZR15-H+$88)X2_o0Y7RA?fSQG_'=C)=B)<hCae0[DSn]A*):TnF$NnDWD@mOB%
0&h5`Z4q`.U$-I8<kCdmE\FWnI>(FG`CAK`XnnN5Zia*D=2YMjY-MC:g8Zb)#)`$7[\B2u$M
NJ+[\lrM;#H/;m&Hufn+Dme[+5"Q:`?_#kBRMAW_L4:RWr`o*rL@WRG9;T^Q6G)#8q\K4Oeo
Ui8)<1CWj:AFFjT#1:s8);+e".K`rU(=+YAj$;h^^r+?H0(G5U'>$3PpLM(<!4B.^CfB3Hui
rq82D!qqe;!<_^COYNW;.n%8M`DKnNr.[S,_a*bmb5Ml6&$nju4r7eXZ%f~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="JPEG">
<IM>
<![CDATA[SGWas'A(e>Npg4D!5h5GCVYHdDAcZ@8</G*"!.LM&9>&C.J7WkX<`0n#uQd\23Fp+HnjDS_&
./!#tTEA]Au1\IV8Xo#HO^Sg77F$e&>YOo'XY9VrU$7pcgCTos5+?ZrB)biRFuqmf"9d_XgS,
hl7XBt>9jae!/V*a$2t%A>R-aK.ud#r\[^)PF>l-[G6['C=L<nCZXg$Oiodr%hp7Y.@L%U@Z
<'1&aC9-IT[/:4Gs's+d)]A>g\sT]A-hE'go88`<_N("/PLBhm+!%VI5s6H:ag;%0!.\El?OB)
DQ@M36,l'G%N-(Lk@C#K:Zq&tkL<#6J/e]AiLS]A\okM+Hn2f16pkY6D_Q*qqu`b?0njBJ/_9g
.c!^(V*`bt.c,7V9R-$C10NLY5nVcR#Mn1->0cd1%1W^+TW<?f=MKD/)7A^iM1VO6Th)ong#
nt,dgI$RgKrU*X&.rXGOHt3g0:X-IJ(tG,Hne@=&p3r3*'=+gf.?nYr4G1DL1XI%r>lCr2A`
4h`eYsWP,(5lgj5!rdc^,Y?\b!o[W0XpoTCpgJ_J*hRtj,J,d@Z5<O%jMZbH(?[r(@\e`2+O
f;:eQ0qlnM-T,S?np1XJ4j)Z0;WSe/R>bZkDCNa]A_UCf\%i:&GK[9"qV7\MidDP(lWi/\b5V
EFfYRS`/,O0l^TVDY0IS4Aj\ZeGNSqE2;24:XKKH),]A.c!.;To;!9#qKV,!]As6#t]AfE$G\IO
,c!gr6C+%6V3i5"c1r/),nU/IR@OEkWL4pKgc_G)Nl&Bm2^`?FHnJXgaqpJS4Hq?7aSjg-_Q
F(lk".CPW7o)/9$JP\:5n?9(s:df_MA*.H.XKZkm^(3GPq4WJlNBDfT&lj%iknM*d"SM3E/?
Zo'Wq<5u'>.d+QVfp\erI(.9!9mE,"mMnI/o0tV8BKs.b,92H?-@Ui9oLo8D^aEk?U.>%sWm
%aDK]A0ruPU>-P!*<#T5DZ+D%:rQk?Ia[[CGVD!,(]Art!kD3'=nM1Z?YE</S'D/36`a@tP</!
)S.;)"(J)\Ce^m(VFHhP,/i7:_dhAVR6$rYgjDd\RkSsFi["m.Ac=q5Jo+92'[m15a@qnKu'
]AX"JNg@0ft&^RVXa-efJ=7,H`HuE<^3VB`9-3!"%J,cStq.b#jn690Mm";IGm?E:S>-C3I[.
%(V*ipT#;!Xsh12-$aXrc!qhD%Il*tbRG%UUc;)9S/pA<!jD`"TY[/rB4n&,RGC\V-)U-28g
Xh+fkDEe\\L`']A=PHO4C_?gcX;Onlc*qi/WL?RB$*->=3p>'3tSaYNQ"\tQs9n_E=97+$E08
O7n/>TYj\;OTr_\8t3m?bi8lefJ`lf`%V'aq3GAnU[!/$/4fO12Xh%-2LPpN5`juh72n7kq>
c<^035A8m.bame5fZI?V/.j&o61RucYd:TJ"t*N(O6Uoi&tWCBXWl8HLj`[$FTpUa-t#rnW`
;_A'lV6;A^0Mr>)Bh^;#Bn;3H_iptU(cBK8j,irm5iaaq1q>Dp$S="?jcG_*Ooqdq5/"TQGX
dpKC5qYL"5-k80L_@&`]A:uPO0-c2fl]A7Z^ql7%UO=n&,n\!^ISMCqs%aGo*Y3bT_A/S%*,t2
6[U*PT7Y&`3`>7F.$b&(kMP*\","J]AZZ6\DX8K+SB5a=h:\#5E?O&]Aqe_F5H-U/!p:TlkSFi
=h5V*2#pf>0^h*`(mbm?mQ;S@g6XXUtUG\M*:9b"g@`=2K/d\Vn<3r3G=(EBg#0eT<&B%/QV
X#Zq,81T[k@9@I:6N^$oGR>d(5)#[L[2qd<S>$%Ul,:p$%p`tL$"p#W]A)lH]AM7H9GE^L\VKH
MKL<_(?<j-@r]A3q37%_lIMSD#qFU2RG^Rpm,3?tZ(9>1^(i@\Eh4i3`,p65+B</*'gq;4PgT
N&hQ*%f^S@hQoIfAI*oYAOke:$SLd!$Fd)Q:>-p;j_lhmaq;kXjs`ePjYeJpt%Z<*L?qLtk*
oO^EU\\un*8m+%dUmsfVgG+cfCfJW%Ud1JNMbkb0L+:LCX*]AU;%$J4+aT<a3!b8o$n8+!d:`
S\kq?e3fIf$>[i]AotS1c!UCflQ[l!`;?d^P:3]AY)J$42otYU]AMb]A\[gs!(*0jZ(Q_8RM.K7#
Rea5n@b]AMBf2nakn,?>j!uUbD7s*AP,'TV(jZdT(Q)Ffmf[FiiYaU1oRgIWecAaG?dRFWnMA
qkd?eXLqM>0kf\+URP'GUTiMm[ek!X`G"Z.Gk(:*TH/c8TB'eqC&afoboV:;[6It6@#skRqi
&%ok+<gC#VIF:l1`Y(fu>sUfUY2^ibTr,)#J.t1trG]AW!<G*037>7PEXu1EZ4m51'r5*5;&/
Q8`IZ-?g[OF:M9pj#Mo_XC'NoPqA]A:0G;nA@^%mYTm=2fglC%/g;.Y$<afiDE:%Cq^[o<:S&
.#)PkR151/"JajA^[8^o'2Tk2]AhCY8_\"u252abIDfWgnJ@9+;J_3RoC6CADILQ0qb.i6Brm
[l?6,Y#$g9lVK=]A6lR3W@[(;%&<1n@>7Z//=Ok%-3K5..`7V!P0::K5;.0pI>J7B\Hp8.d<&
OZi@C-qFaVMhM@;G7;'nBMTHsl5o*8GdN1h_]A.S88n]ANaQ"agkpeC-%BI5B0-?O]As[D!@YF,
7)gh%)&8QtL*6p7X$M(jDRV4E9@<Ts9Y!Wt'HIdg;>u)&s9h=SP;fNOQKe^#VC?Fl<c.Igkr
+ENnI^muc;FdZiYFM+3E:?+0f5je)Kt]A47+N(':aWX^?GEK#cS19q;lGr!^sSJh=M!PKWVl+
+'U1/&^\.$P<'ED`cbgY5R(5dufUt#l/^u.4eM7f'L7)!&=B3/3LKTb9)6+\)7`$ip-Vb6oG
(N-:>-D>#*s/*c3:$g^t%T5VDsL)MQJp^VBtU:]AE>43]AqK!H2DPKR`(D?]AqKnR$`!oM7_0pf
cIIUP+3]A["f&JS]Ahf+Re=3Nh$\7>?&^MLbJo+/?`oKfUb+JF1hq?EO[9/KI[`lE-MH8ob'O0
$R@2#:?p@d%*L\iSfC`]A$*[QI&'K*O36L7&r%ZIQ[)Z-0qdANF:PeT.pB"HLllYD-;9%>-Mu
TCO;(FTM[(pMdmKtT!n+E!\7&9n7-DjRW41q)c43mL<<S08?:JB+pn9g(D,kABb*jcq14ek,
i!P%?%UWd<6_e]AH\ns9X^UJn",hMq&MUcagHTBsY,]Ao$G7r#Hk:-BgA'<>4)<+/2<q<]AdD;.
GNneGfGr7G?tp'WSO[EbYNWmdNP89lTi36l^B`i9\$LgDF1NKQk(l$p?kIR:'8:!ZXZ1*c+S
-F=2+2kgA73n4X>T3j,4FIW]ADp5lj,N7M@7BCZAoAKJ,>If79j)mb0#'t1(7dIn6JaI)Fj*S
Y_?Vd)5$RO>G'.]Aun,N7k"-c3a[MB6PscX.L*_qYHs6f>aB/$2<\h-jmCaI>S_5`BW@DYT'/
T)\<(jKI)A`:QIst/S=20bZ1$#n&iJH-L9=;?pLmOp%aMsV>[OA+G7:#3+Gki(,l4sRj(abN
UNOoTn541*nfpjl0qe(!ibpu.:Gh(E(j4Lk()j[BkmfR;Z*p`je/?=]Aco2"<T;)SXp48D]AZs
d;GrD#us,c4_W_\-p8DNndDW@%mL.P2neiMXQM/-3RE.3k]A_*Ua+-AK-RX9@W0OU-7cF;`;:
m67<Fm\nLPs2fmj<G.'8$YuDjc6b3_7LK>&(tE1n/9p-I8D=3BJG47o6;fiFig>2R<3VR5kS
Xk,=*Y52V'YX'o^?\c4O82#nkjF=29Sq7!toLRB#,ADF_dMN0*V*/kS-0l/\d:!7qMVQ_1*E
&g0i^=>meD:!EX):\&2>:U`BEqm`rXj/r=raO2,"Pq;j)s'2mQ9pq<"d[C-=D!R,jX#!'I%m
K"RUZL1i^X^oOo-L4/`295!X%Ojt2D;/h9<gj0nFg%(T"*]A]A>I;[W-Hqu^%"!o8AB=t%dG,R
KMl$%enoL*DYLn4k8@'44sCHF/[X"/,mlc9"VQp&F%,+rCmFeX`chAp<2Y0V2^9<HBlf[HVt
F[`4Gr;+Iro%7j:/Kt)bZsX<?$A6co5J0YSn^It`*\AKUrn*ju&P9K+meTaGBtS*'g?]A^7)r
mE,Bn#(c!*HR-F2U.6D3D[(32@]AkX+.2@lA`PbPM13#:=WG)_"H)l=o;B\h8R\rStJgb`)1p
fYMH\YT2?Uq>b>;4WuBWMoGJtf.8C7$Srn.NCY""2l*YqsO,e<(3"\CddYU\J3;ScqbI'EiM
*D2n4.6,QL_pNTWOe9E('`SpYXZ':4[*1UdCt0UhjlS<n'c7%&1&J+pbPM$\gg7QA83JWmbq
a;[#G`C0DG=:K`/kd4sfo!5dl2-Wa/pFGpc0-k&gG7h[tO6l_M9e:raH-]AH!,:->-==9niBG
T'E:38gq:$"^\iuK(0B.$`St_VT3nRYE:2":\uTShVgCF$$duSF_'7?1dB;!#t:-OEm^-H)0
]A@N19;DD;Tao!Sda;ab%RAR#^^'$",DNHif(UNpY%p#7n>h&G+K<9cptV%'H@`[UpULli1!I
*%S"(pJN2:=Ll5(6Wph*\7No^I;\NolZ",0-GjOLf;s&Lc4"qSHg_<V+W(r+ne5cOqLXUk,1
*>!j3B5q1F7@`m0XbhJ@_q`g"nQj,dtQ_i#^RMjV=%l'GfrnfLWG41Ys@5-c>[.dhcuiP=%?
I?r10TE#D;Un\h9LH$sjU]Ar1aZtk("&bAEH5%6EgVKY(0Hs>@?[A0lkM:OCgHuX)<JOqjm^6
4q9^frTD3Irp"*V$#_"QRB]AJV)7UNZ`U`ohN^9m7<Sd.fp_J&coKOGWT>^66%<<Q9$%Yjr+m
;#-@\e7K_bS(f7a"3]AJkj-E/Nif:Vh14#MZ#rqUlgX:2UB]AaS2UV'B_YC1C"RB'_)$[hjo_Y
+)D*D0couR`I^^HWX*O)]ARuu2&U\p[Y4RQ0`]A\ZCRhMRjuQWCJQZ0Z!rd-UMtc^+gMGhDKKW
c9ho4!;ULeP01_85GN8n9CA(ilVt4'5?L']Aa$qqO7FZ6^L+E/P".gHRk8,Rrd;WYR*V^3SA`
#RX^"eN+f)+h1U[T)E3IIt/qL:b,Q/+ipG46'o2QT7(_uG7e1/:G!pmI9SSP.pT[)^:g_b$l
YNY7tJ9XRWCEXqs0Dd.EfC2_fX$u]ADoCWh]A&Ep3Eqe_+r6(=MBlh<V[^6^s1Zn\eZ2Hn*;ZS
"9eLl]A1#%-#XmB+BVFVb;$k&+@!,d_9&N_U@j>[<G:>TaLM-YE`\f\=6.fKgC-p;<mE,9jau
,n_#F2(!g"iLMqU>(#Fskf;/,24LZM_M`U\*b.,6lX*3d#lHc1jq6Z.+Tc0C"J2Fu]A=%E'1o
9rL!+4_J#^;Vm/&UTu0)J+tBNX]A&j41*'JD/GIfF2'IZFr$OTH"HY2Z>ukJLsQqN'N8U!eZA
)\&:ZI>#Kgk/.5&(e,^Injg4o@VLf(95r;MKDpQ@93-[%/MjCj=m'L,\0"^!jF2pbkq__\#4
!NKiW^;Vg(6fTCKPDDqK#Dt6(<m)Yo['*U_9if&3`b#of#iN7S"+:gse`kV9P!UAI+`g0UdX
iX[gs7e1EqFNQ2tTEb_=mP'#gIOc7$FQ<o`tXgQ-kWLl'u';kR=3^houpl-\/:PhZN+U1d"0
\i";%&[VJqM`>0;N&=\=\q6r0E1Kmr\04JrFo_k9"5%2#NO?&tt$/eOc/S"Q.c/\TdN9K4r<
T6bV>kqS\L3ClBQ#q6!TPi?,$5_\Cqal5dj0A<2Z?="LJ,9?2"O91C'$cCj&tEe<(_$0\F[j
!`'3P*4=S\"$F3%PkE)2iV<7M=8QH'XnfKdWY?Il=0arJ,@$eZ[bW'K&DhJeJ3EXJs["B(fP
f&15-CiMB>7;O?@1b-_'':lkklb,6@!mQlId-!Y^CWt"iEb;gNWKE99)*>\LUuDqAIjV;fKB
8530;9WJ>c4MCnh2kIr6Y,CJpduhB)H(e-bCA1hf/G_Pc:lT:-s:<dK#h\.O4r8nDUA4;p!]A
2Dm]A*OV0:eID^g<=<Gaof_GX=4]AE=S?R:]A7d^!obYcT5Wh50I:j-$khsbQg-eBAo._+WCnZQ
/A^qbp32caCh--m`rd6YQS8mA**>.nUZ*/a4mjORNWF'Frfg#;.GN=b\u*&'j7tE,&3<S;:[
i2<n=K;P=!DT5<4#$S^L&`@-#J#.$mra@Lr?2*Rdo$,=M/=kK?&ml8NQ7kCb$r&[bqXD@+Q<
NB_]AAWe[^7O3$#`h!\&#@=iT_>q/UPIGp64EB3$j-e+Zan'7B;s&'5M`gM;^WKV>1POo7p:/
<ap2%br9r:sN(&R2-tM+H$bOsZ?YSjcgeDhG;</S">[:XonDqB1hU;Q`!L\jg<b[(a2B;O/K
cpK@SFB(<5^0s!Y=kVUCnP=3Z<!tP@)5Dgf;H:IR5Ba17cUSV%28OT[MSOD&WHGg^`p\`Cc:
+BiEd.iY7cl&m(aC"\08m[hg!lc4k)gqg`d!aooXO+J)?(Cc6oAcHe+#]AEE,TuM7Z(GBAot*
prHkRf)V[V3L-58gL\HZO_Z;!8096WI>^XB5on*.U/NeKCa5lG/ePZA4Pd5&N(jdj'?%3st%
WOS-Q=ckP>?'-mANJlsbbEN9Q54M?2oN)'^a?T%Zl.Q\F"'KaA`K.s[eu;Ec#H64h1CsnD0c
NU9-28NPY015V?\lqOW9/aQ(so:FK?&'j:5guF6f3VG"WXC<5]AV6'KYrWQ1?Q@HL[6sfNB0)
bDZZ@#cNG!>b,G7Tp$ASYM!m24-.Y2mbd[Wl[1J%^9%6G"SW4/ul>jR&qTit#9Y$gmO23&:5
g&^<?\WVe94Up/rcLoXOJ(m!#U)Se+^Q<_g>]Aa;+Va0tCOXIorQERd[OT@ca-qVsd-$d&6?\
]A8LYurcPORn#fj;VigA>!=T>J4\-pP9D@;LNN?40njS920&ILqL%q:d1E6Pl,jA)BphO-WF5
&:D@de#`nSZ$*<^>)3<<\lPehZA5mkRDCjld?!OH[>QuYf6/P?-'$'Z/RMY!.`r`E9-;[JV<
sLmK!(^S`/FZIrCDD0O^#rjWO_@W^&Bn-qT,ojbTkKpB;B.NpD8p*p7U_F?mf%:H,Da"pTVE
*pjk<L@!#,TUfZM]A0lrYc,/QBQ!_$=*7JU9^gWEQ`8:c$G8ICBas7l65M;dac1%MA]AI9,IST
:0jPrP4*VTKJ$1aLa1Ts$9:Q$kPp:LJ+FH[4%0T)EUUcq*@JBJ#0@sG3Z;t"epo:1oE_J>dH
)SfuVu91*dm]A!6QqVWcEj3<,hE`Ks/&Y">/@JhjIZrIt)IYnBIrPL5="^P]A0X6a4XuqQ%#PX
rmWHfhd2h<lkbArB>#('/GO;b/+)SN=?a>sfT@Nm(F.`f1Zk=9[I4S56gsOT2p,>'*qIS:pJ
'glHAD;,e_&jnQMca(q)9pOId`&@SXiSrrk_9inthpGmLl,'fB5@p[k4.C\lQ`>NjI]ARI>#h
0)=N=Z0p94*OcE@OSG3J%dqMMc<WB!WU%%kiokH#dS*FQCgonUS\U,I;fI9=M/"1kKThMTuV
4Ro7S;st9>=",+)uhrNPMaoYiIu9j:ZFM[V]AhJAUE@X!I(K,W)H9]A%[:E\khdW^+oJa@%'<+
u55!nrS'Pj'Yp8966De;Q@Unp)Q`>G0A^f"hCEAs#ngIf"F%u`<q[''Ll@8@`b[:#h%Ci^>2
/24_4dOG\PG1&(iksU%_&S[Z[11l:kNI$oj^_1P"#p_piiA+Ig<pJ$adCN$uP-plR-*"5V/_
5bV%/_9\jOhSnaV;_<nCru@*W'``\:f_p,&iXTc*95SjXNniM=4]A'B/Z"65WgD5/[ofK,SBb
W+-rc@:_&j.VBiI6i#,dthklQ0U$k60U$b/^eG6!!)14>a,,p)[Ce]AK7i"C/*J4!(h/`>.C4
;t\13hbQjo$G.G&aiU&3;VMVEuV]A`\83puf]A[KV)^?W\_A!Y!r7bNBS*=RW,f^Zu=7/gKEU*
1+cI_X^!KV0/]A2%gV:g3-6%7]AiP(s#9)ZF,',5mQA;c.gf=dc8%InIS@uWD^&H$!ddk<S6q1
H&#G)m)X8?B(j]Ag<<`A_OccnY%9UM6N,hgFZm4>83;rs[=L(N=$&;@jco,XmgUMV&n.sRrJl
_MZ4i2WjdU[6A=!p^8qERV`h</J[9&-&`9RJlHC0^c+@:/YAIH+_G)pSbY1l8O%dtrnL-B\f
9+o-5,PSVs$pkera_@%Y_dgD]ATbW@Z$.^IF)3,1S+kPdDuE??']A,_e`)'Hh"KKiP2G=IV;Ul
;puT<G@M3#N7p3,7qlJ]AYq7f++.JAeug\8**=f-gtU'Cae2NBK#q#j<\SHF_[rj7!%UisaaU
1fpCaP?<KbFGCh&OW\!U`'%iY&)Oc?F_bXW"YS7nUVKq>P=?HFX?ji8]A^PU#cM%rp70XZrN5
;pr%HLj&=c5d/4,G4-EQ?ZZNW>Z$BRs/8.$io'DUFKS453U;A,JHXjCW\9dL`2^$f]AX!&,j)
Dl+IW<B$6D:#@!a:N>Q?WXG&k.A3\*)DML70I3[hng\K]A9\^CuqCA:n?im'(8\sSXd]A0eF*"
=JJ3#0D2+@:@R@hHo:[@HThjIcVP(<2?=]A,P9dKT=,Ts.2Yd<a,%-1IQG;mgc<"'fGpn1]AE2
XmEQk#&%eYKWeDpZ>>$ps[+U7W60DP6$%$7M,phRRH^q.*kA:dt2sb-h!nK\]AHA72d_b;_e8
-.J_k"#[@;rWB=ld$Ede$rUq/,;$.!=(]A)hrXhCb),?#Ib9*R]AZl`pR$_g<]AM"*reQeX*-u*
]A1ltPb;rDj[m"*'gG4t_;!H-F_W1<5f>XHc2'1kM#!uI@3'X\fB[#m0<4q5D(BZ&_RZ>TrH!
n6mi&H[.Z-#J73^m]A>OLPgtqE9g@S)7O_$c1hu_qG*>065hdf5UkL&P;=g6D`Fj[cp1-IEdL
g>(qkiDfSBGI7YX16AB>mr"Ih.#&s-Rf(Q,mm8.X:D)+*obRR-<1YWoS4q8^*6pFhRKJ]A8JP
D!/.h8+m_hP#M-OBQ_VPNV4M3Ab<F_#6.4c%MmG$s<qM6?_8GeE;pI/6)TaLqPUg40)?+/"o
QGn>JELWB16$p)enHa^s]A961BNEs!?=uj7>KJhc0.8=ce?1MC1nVI-PuRkr9`J->-$cLg2,_
qmtOuEo++A:d='O3t7tbJW5'=5Gl%Jr5S<M'k[R4NpO<EL6d1BZMi8`j^#/1B(+%/IXq$"9$
`bCY6Ci-Xo"]A+=a[`3e2<fpH'*;59kRGTiI<u^Dj^;Ao'2a/.i%WeJReS^)S/_XFY0mS%m4'
8DQ9a#+@`M/"D]Ag'64jHURV:u0We:K.3H\s$;EYMoX1VXA:/$>*F4M>T8(#ou:e(r!9'kti5
<O/i%mIWnkGA!1=X+Yf5\).:gF+\KENELtl(X&gOil`>]AgVa\%l.YLd2q^u/!2h:Nm[+61;F
AB)`;@UjMgN9U.-P[(i$CJOpkqCeOXk+UD0Il:+$5)&ja$R"r@[1#p=`rL+kOAb+;?m\nJ(:
bli3(YkXdlX#!&FAlgKJ4(>,##0l,kWEG\c2rDB9K4k(KG>D_tG=5#@FqOPh"FWeaCk"6pDn
4)`]AOtfX*Rsp5I)B&@&M$s(Jp_dX2lT1&:ls;VF"2,8blNH:(mqI@pZZY_pep1]A!j@ElX,Y<
Fj'KRr:7p^3fL+Qo@naRb0.UhrV&'49c(!T0?WU"6gu6h'I"-=u6"04bY2N/6,4CZaZ,k=fN
N1KF#!T4B]AfDL+OQ&iug)]A6BauJu^A!O2rg-R7?gqWmO9AA*;HXlI%mfe]A=mGSj",PCD9l0P
&18COHIT0E!IAdsH:+G7h=N\SnF7nO6!Hf_BuVM3WrYAk"uGt4l&i?e%.]A+XA7(@#f5H_s#W
SBiA?PRFXDGW>,IF[Oqhbp4KAO87+m,9+os+qSk+Ac%a^8'CR.P8criA#:fN<atc@h1rGaG4
JC5YLPV@Q*u=CMjP/*"R3Qc_M,]AZ6aW[hYKq2fQ@$;7*&,-,Jh*`\7?P'SqeG/MD'E]A+%o5[
carnqRQ=]Atr-&7.r`CPdWZaf=#W<mAY_okK@XjWeWW#OV@m;EI^.m5(@e7Vd(nqOBV0/J'!j
PX8tARboUM71K;]A"r^+3Tk3\A-X#!+UqEm1X&"]A?9qqU^L.K[.d,)&1'pdM9W./'LS9-7=m@
[L8E/k>+=c`<S6@4BO;@7Z8%b$@TFCJjc,5MrPN[ku=#p@m+o;c&'Cp:GKIiUBin;KeWQ>N/
Z12VWir[)]Aj9LO2kY.)ZqQNUnK\2;`6b)N4;jr'T$Dt-0/A%AWefT6:4`GC)DJ7uXs!92krt
BFCPK\=sMqGcgNTU:(YQf*IZX9aDmTX:e18<^>nSk<+jKqnNA_AF?O:GDSH)#ar:HQNW/2<)
6??BC/K2G?G]AZI[o>i$lAF-)%`YULGMWHE@H?,Otj/QW;iX9OC7TCY-K"81W>HQBM,3et>>B
)#ZSNEdoQB=^[8N`tM5mdNLPn&i^Ib2q#BJO;J*B`uK>_Sa6f70+?c6N+E`(Rpa_`[M!sFM/
g9,AnN1fkHO\-("X<$2hQY6<1@\<XJ"dA=^9`Ir5P5d*F:uBL]A`@QTB5[5I2."-jf^]AR^'3e
aZM7nbZQqQ0)+Qd:R_.o.>\"/G#aLN7-/O]AN4ojY#a8MLd1jk_Q'ca35)IA&ccY8#9B[O!IT
*SFfJ3AbE7GEncUf/Q26?AII^6.V>Tk;6NT]A;HHW]A%G4Xp"0<f+KK+1VP9]A0Y-6o(l>6.h^9
sNQCo3GCG`/G%"1mLkE@8qO-Zb>FQuLqjK^n-k70%]A<<TS]A+K$e8b,BT10O?L8d02i@AfC'C
rQG0IC%YqE![:X)3"Cf7]A>NT!UjD/@f.iinWtg$B_-k2J5X=[pI&Ail4f(Z9-iTR>%PA-g=$
eGdoKPiD&o^&4fEN6Qt=<ML@g0amlhVCK7\'^,#21G`kf>.UmalP,>3\Q*:J\0+;B$(;qY<=
d^!;+Hjk5t"tHZJ8V:q,LMcOAG.<3#a*6qaJTp"Lq#>uq.lZOk"!-^UXb%tSiP>$Dq$(Tkbi
LLmr6uXM7_J9Hc-e+pKZAfC\@Egf0DP=T^Kf/EPoBGcFKMZpg2-e6[S..p@#%#'^"OdarD/s
1VO=es+03VrXW\TS5e>`Ye1Np%B3RFYd-nHO<UqdY?&0H@2$5lqDGK]A<ML6M\H^d&Sk6BM'`
n8BR\f/oT:%.-_p%dX.QL)(p(E+M>&JFN4X<eg6it"%KR/VJm3OL;PfWr17OAh3flri3S=B4
6.3RlJt%@$MC>89'PeK'7]A6a7lb)=#@crX^u:[r>ruh^3(RB:%^G5%!Tj+[=plW/"tT0H;0P
:mjuQ3]ANpSNHE6Del27@#VlZC2Jsmj5DsY4AkX2k=P1m,:A:A-0JCAnGiY&FC=[RaTjW\Q9@
>UaRC;JQ`t\qp`SCMCW]At>hg-h>220IWN%(8<A>)"kFa=mT7H]A1In]A?)RBY3]A0:pZ(g/*PB4
-ItkgS?i*h9N/Wigl&O=4EGdPV@<[;hMU7cXj>os*F?0nA1f$#5WI:ehR<=Wd_?3K,',.p5I
SdIMP_sX]A"gX/BDLl:TDYTN6]AkgZ[,"9:]A#KU=dNmhK40K[@[mO-Ut.i:q+:!6XZ@5![mU7+
8:(e-&t$gCi/19E2UOn)WP3&<IRiCr<+f8pf[V7-B4l33DuiCfbQGd)\CllZ\"G025$M;-:F
cn7=Z%:@pmqL1#u`m]AS?#DtV=hI-"+UZ#35ad%pse=Yi!9gD%OUKGQ#<,M@g7X,Dn)kMBq:\
2WpSUOq9(b$dDoFJV/:&,W\o@RE)G:-bbicbt@'XJ/(5<7U?)ll)CDQJ,+,jD_^8ZY)P^h^J
Ja3F%uATu^SEmU%m$hIi;[DeF?>\<B]Ar8Z>F;trGq,lTpWlIHE9LTp\EW?'I!Vog"Z\^D1iW
dK32g$TR`U9l>>pDp99BkLG51-a'PD'\f$a08Y0K_8!m\Fc/]AbJMO^J`d)pLm<m'QlN<\Yg4
T``^\ouJZU&Rp8'*!06=qM#ZjL;-<Uga0+YRn_m@5ePp*_g0*HSpWIP2A[&RjGa2^g(b=V>;
ls80@F.]Auc?t.":8T,*peJo$=K1[;(a!Pm1XEubFSpQrWqcakc\G9MSW^VOJ%E0RFA[:s37"
=V$]A93%]AN:5?f8klF>q=]A>?7_YJpD7M>i8&js*C0=o92`OckL(gFs='s\7ohb?@m4HF#ImQJ
IjeF]Ae7X"3DQ:WRFggAcX4R5m_WPKeA\!'BbX<Hk;,JG[o6TPe==DFS:`iQ7X*Rp.2cqC3YO
tM$$Bp\MVBBr!Aa5/(Tn$]AR7fFKk<L@F\AbKihdRZUAJp0E'$4'ZjShIi.U\8D@Ub[;AcqfX
uHgK98'fY)U>LI6B]An3*D/dcB`9.g4gnPKdl!M6Wb,Ub.e.6s71(ZH7>o95$sWo4qn?<m%Vd
_pd;%pee/kJr6TAl;\k'A_[<tfF7G[R(\-oIb8##.C=7\+(pn'3&;$h=Y2JB;`@0hp01JqdH
s/>)VM^rgUrj:WKc8iOUibs0BZX`Y)36OU=>#t6g6.QQ/jp).R`gN?<+,PqnU)MXXKCfHU\^
_a$NV$/D87$$h[MVJ0hlmN2,*J5'QMG=U-Ru83k_3cQbE<H?Z!]AC(nWT)ufm=p4skZ3ii8g4
T)&&^fI&[Ca\[hkD\2U84'V)oK5u?B(@e@VDiM6D3W\Jla9B6G]A+?fo#]A24aN%dXpg<TjP\0
%[/#X*3E_%7FGq-DO9H(2/Hg,,*^[ZV!m]ApFJLO6!1k@0>^R9"pVn)j9b%0jXaR.W!lC"R5N
XUq?<l@9C`FJ$Qj3IH*epgD5lilD>4?gk.Ve=>)Dn]Aakl^41!es/8:NN]Af=0b00b]A'7j6"2$
p1_0COCWFCAo2NP[dDf@mU)dW?q`8uGOUnYDXh+kBl_o_-=Z,HdDo-bqtpF3&;=/(`k5NOVn
S3:/,8U0J=;k<t0oeU`/tB*MRN9!q1?Iu44F.sIpsZ4GfH#IU'Vq\[%]AM6j!&J)'>QZd9%G;
-8*57L$n*GBSea+cN.0_8"ANW\`7S)#:WN$$aYuN\huUnJ&\'RqReOA<AC_O`=YF5PB(QaVl
KL9r2#*W-79_'4N.m!?'k9UTKC=bM?GU\!>E+X8hf47reR"Dp#/34`f/&qfs9`Uj8h((:!d(
:2Mu2k:)EKOQ?rS8hRhfm4Jjhmqid#:WA>2k3Rs_r<'-`Ia[(Q'to-N0:HCR4S4E\]A%L5.2F
Z-;;RHn&$"AApN`ofBn=WP>g!(C]AF5fU@IbQHQ0D64#[W9A^=Rd4\.HW?k;&tRgCn_c(q4m&
66cG?KG7AO]A`gK4_(<!2Q?79t[iK1g6qL:,23K*,0SSAcIWGT88Ss*u+S^QA3*QXDW`Mt[d\
@UDFWG<O5OUV?[OCneK:+#=P\jpU0MD8sLA"N!m3M.C+l^<q`B8Mlp@2roQMB@Um\N-5Ial,
f>jk><EWsgoR>q`8)7#`SB1/V?X)(b_,J[3!-*A*US3j2XG:sLppKVqMtO<7f=*3Zuc]ArFnS
I'fWd:=VD*^78tX=*<RoH-UtZo_HIMY_hCL++jLCYo%cNqr&R]A;!lQ"=>]A9QEB+;`]Am[M6V%
n"PP#tXu,X%@;X#U,SprDNJ7('SPB;5+u(-s/E\S5dFakJ(NTp>!ko(kmF%JtmBgWZ8Wjf-G
o]AGt*EI^3=F4diEt?t!'[bm:RAr*BMK^G_XaWf;C)0\2XXjc<7Cp%mJX`neFjd(nINH[)tQ-
Kc&/i8gDpoT=^S0qX=!:]Am=\q"D_H^mH*^NN+p),`D,aTj"5@&0Z&rUY=h0NZ0?M<L^GIMH+
BA0a-h\it^/(#pI/sUsC@i=T^>Tn@.0rXW*mDEp]A[&;lObZ7kZmbVtKHa8Sl]AYbUr'0C^RM1
%E@3d$H=FnA[""^nK[iGi0CR<RgT#[#l$OiK#69'GUKX.!K#P:\R[TI:\r'jbC@h$>-Thq$2
3r+\=E^b>[bdZertG.^?H@OGN[4t\YQl\OE2c8hkEa6S+<61+Hr9/]A\hrQ7UZ+KeFJpqZ!Ah
qYp@Jcs7t_TPR2<+7F0Fc/$6X<3GS37A7"R3jahh"CWq.lS0?]A'.-,[Eq\<\BZq_YZb4&Sr1
4;`>!;4Lmn.lG[<bSc/J5#[`c68n6jlu72\tH%bdnDCAD`3\i]A@U+C591B.Pb(2&]AY"KK\NH
nbdWi%`T^@]A!jgba`B3-2P>2u4>Pl]ACrf@*+[^';dCOimUcP(@IQp@.be<%WoF30Qu&@$=Fb
CW@5Z8uG)$JiL+Ad4Q]A..]A]A[H=Z.I\ZBOBEVujoKY`Wj13Usr[1(!()UUGK1Q'Jc?F;K:_j`
(!,"XC`0A"hOmql=9=FeA6[>^[T&$A*uMQ$?&n+j+aMZ=VGub_6@+`Y)NW!)F1<\/'Bu5-+V
U:)?dI6A:Et+71`WI,7^!f+F#]ANtC-?\SYQ)_itFYI@O]AanUW/>5=h@1o!FuD>]A(>^/JSa_d
3t";JZO=\]Ak!1cHmM@0%>IcIk>d:-`as?`:#[u:8M!X`=;'m2lKmH65FFin0'-C[G_(+#f-\
7a7gLgWjb'A[ot#&!A*ja9#&F,LK/l=d%e==gH:KV*4b:/FrD74/e$:/+2@3*aa3m[b*bRV8
[ibKI+p#a)&!=j:r6D0W;-<F2"LYu:Boe<rH&n/12Ih[,:+@"<^WbBZ[SK1US\6Q`Ptn"YR9
;i?'uE"sf#sQqc_Su<<UG?1^R'5?f.KQgkSP+\hdi]AT/W:,I_q\2>SM[I0\\B]AP;8/qi2"ia
37'`kMDC1n%6#KjKdMf:V[>(\9[hQMCDTn*f[0&=f08W1k9u%99R(OJMlYR2HEcq^/.?+js,
TI;k)Q1V*G[;S/oS5&-+S4WK157WN>g"_JlkC-m!\ZPDnu(n$rIJ6s9:t>EC%IM&Fkq@RI9C
s_[c1uNd,A'khOu17@nU?*]AE3A]A,ij@+QMUf[*)'=HOWS/HXckkc<UBdZ?+AdeMN<47j3\=@
;gUS`eFc:c8Z]AG(!-'nmW:kL*o0bpE(t;kIA^SibBd.48,;AP42)WmLaK!BhOY(D\pGRlkB'
ORU7$t<]AQolshjS+58m#D+9ChcRBhP/iYOS]A7DCcK&_mlcdulk7H^c#fSMlpm(h<Xfrb5NN2
XrG187fng4SbY.h>p!,3n.8!.i7UD*TFNN_2/$Joafl[P%=uheRdfpZ"[4]A;CV.COj$A-8]Ai
b@F7q'-AFY?sQ7a\lA_rOb$Cgm^sX:-We7RJ?Bo1lopEY`fi"]A8m&JbJRTAajZoS_Ag&\pSV
l=OZ/'=kIWd#Y2$J-?U/[q^[6:(.V#o18TiL<r8$riT,V*!:=-)QTCh.:j]Ac4>H^ct4ftKV+
nV3$r5NPO+3Y-AcrL$&12?Io2)S5`@<kdl>c^\:s6r<1X[ZMsQk2E693jS?1f(IS0kq!/!o?
R<=IWjEZ?R_"g;J.?$>(7<@C.&r=%ab3"<s54K6jA78L^WU6iAB!67*]A%)?`G&$/ADcuZ:<%
!YE+fg*`bbh=H#p3'<Q/-WbkII6hrpqZfD/G!i7%Ak&2PFFn._k`D19mYsS36%N@RYoLq]AOO
+hA5og.DUfP0B!'E\a0-VLc>&ZYI'lZ$WcfK'1W#Yj_TS2i<KeX_qbfI7Y;/To(h;orQtq&R
I[A":5RBC^uflj-o23iI0s5gtism9HQR=`Oq#dQf%@%P?2J(`Y&:AU<#3_G4j6ZdbUY6LGQ%
ckKt5ms3=_qcMoEm%HQ+M!&"AXW_<$6Ku5/_q@/:?!2Rk,3:gth??*A'QZQ%p.cq19ef&=<[
]AS_Dt6%L+Q)SDP-+CO$l8/5DC'?#=_C,&a&Pf>%1t6Y"jqa"#\%Rs$l#oE$36D#-)HS<eSj&
C9Yep$\7;nY'Q_/J(!,UZA?RLN>*nLKl/XZl>=SI.WF9)\7Or$bjI&;/&LU;)?R$-0OmhM;o
VV`^jnXWp.&ZePr[?JYW[6JXDr]A;J<E:>]A?I%?$0@\EY$4Bu`)g0^DY$*l7TJ\ae?>W=sO'E
lu7+sC8N3gDl^C3JHXs@HXdVk-Vci7L)%^VMbs2b7#2d.5tQf[3=m9FsYE#<F?_=^,BXk\W`
%_:IK>"qNehqW91q`liJ8r-3++7WZE>$HSs.\0/qLcG(\#s8UgT6$>+f'RMZ:sD(k06C3b)-
`n:>0(?b<1&2\Y+@Z0X5VZ[i\lL9aq%G$l,>iqSe8KW/R'2V,uD?2P=kX:o).Rt'j&*ojrPu
8OFr.`>%Jo4s6QbPHiK5PS=.AZ-4K5'-QS1TQ%,UnU\(MBcY#hN;6=b!@-)0O`F":,?bMNnJ
8^Z0UH1m"5Z+;#A:"TSTS,)`lLg&Eq\4]AF2)J*.Y-ZC19T/7nQBS%5%k@>i$b=C"V^J4nj(P
?B?&0?`cgY29D2jhYaVZn*=6o.6%=rb)JDX=Zm(H--g,RWLZ#;d(7(B>BbY&DOVl4O;k*@N9
1f7nj%TC3"m\*3DkO[3Us/t,;qjIF(T3gd3ZcUdfAsEpKPFW#Zo2E)_X0AKKP+Uc?*B+eQH9
AgIas=TEF9<%u7S0fTN:)dmSRp[jH+M#^j7ENnk()`W#uf(\I2ED.:/oIrB'jh.;!FmTjGE*
d(W4h+ArFebSt<fFMArL:RjRR-Q^[A':)LpJI!W#j72PIAU6'<cG*hSOYLF21nW]A!YB9`$YL
5&%+1M<*gd0%YJFYoOmh4p*#q22UOaRediXq)DrnnZC/gMpruCbV&lnR:CN6:a7YY[]A7?&lm
,K:mXRXMkRQe1l4+BrK(ONAS[XlS9?4k0,3#PQQh./Na)Ub`8KC04\[]AS9H4%"=Y(pg]AC\HZ
pY$$Z["M[q>$i.k;MfQ32c.".(VW0Yf,6Q+?;'l-@p2pt*MeAAq$(oTY1)hr,o1N,pL7i4<+
1EqjuFoNGNPpfgnCo>m8GLgakeRuB9M!%J*RI''Vn''#ed4u0XE9jkuAQ`cagl5WiJ=^D\r_
KYj"P&^RG\K`0h?k>^u'9V=AOU&?n,us.hWCk%m^u'+Q0r;_jVg(NQ)FJp-_`Bjp#TAdEDGp
f@;aj8P89TTjW*F70QtktK5blp^B\)(hKkZQ#u/M\p;JSCe!MiXeBGlY[G?Ij/7>b^scIIQA
(&ND&Q[12SBrG0%KH!aZD5`1HZP2=1\.@U9ccIfZ9!lPc^Q_ZWh74B4^kkY+&tr!@5=m"B`\
Ks/0H3?a"qb&b7K7m#aID;fRGD=b7gf8QIM2S2YhpEf)F$GC!g?*iYO6TH$r4@("rr.J!;I<
<p1oQqDcAE-l85d.[^lK!%N^[<Ld%#YNW]A,h3W:rEXeG_oaUR\5hgDcaLMFo"_;CH@_A">%L
$.9o/ZCG6L.-B?f1<`[VnEUJF2<4KZ-:Vj"LQkjIJ09=UWJM^YW7smDO6;XY&1F^0T%,=mX(
dIFrV3Wr?rLF++cn=Esa#TsTqUj7[S1u'E2hi<O-kjQ;NM+SPkuqFr1?LQYN+r[P7&9nf;Tq
`YCp-9o'nuNRLpn'U;#6d`T:J8!<G0<EIqh!PiRsnqMWn+Ift4&`"2,@+ZCF7alNWJ\YLG43
93nsaT(fKbWof5Am?'`(QBJ=pf\beR6L!#K<h9_,;GBiC^t7h[KFZ'n<Lk'L>(/WLa-/m5k?
Djp(q_c'JQ!Ie,\.3f8:H^tY@aQ5paSQog(I!MhADC/CM!2'9Y_%WQ70".:&0omLb!Z:^3*,
aT2ujH@OqL(-2f+Jg=tSe,DOJ&,k8u*kN5?>Q78P\qgJL\-#S10YP3"K^\l:e5M*FA?<<)EH
T@o"e[U07(T'p=G%h:I>SikHcIfSToUIHp4CTYf8pBJ-6cI9K_=COLN(`;o1,dh9R-m(PU@\
eQ$,h[B"Q]A-66O`-30?haM1p/"$RG$/d\R5Ba4_s4+k%;aU339;7-<te-M&4KYYA-o"3Tj*T
RA_qpM(0Fod?.9Vf"7oN\&Shu-DcGA/NoB(X7<:+;%>pFqVP'Z4f+#(h5S%FUs`]ATC%(0'Vl
9KT)qrEffI=9ET1JKgb,2CV8*,JQIV_r&c8<PFAl2OiP5\[gY#g<Vor3nPe(oQ-B"]AGKq`J`
85o2qLn*5<>GK1_YW&QnP4"lU**OXEe<#?;pYJ?,uqU!>:#Qc]Acqu!*<Om3tDT0!(i2aY<(p
@9-:EH\U^`#*9N1YlMg[OZ4q(nlY7<DHJ*3/IZ_U^oMeA=WrnO-tR:ja4'`rSFhr,Q=%E'dM
YVBUNI$F)XD+L\@#c<OJG-HtLRPbGm$iBtie]A;4B%$P?Lgs"$8JnOAO"i*9'$sR^_V[&h-UV
Z(rT:@(C'AheaF!N>:is@Ge'i[1%ndTu+@hcG?T]A4'$5H#uaN&bY@0_WIU/&Y&W_TBi<e$,%
Nd7K[aA5pMn+=!0DGOV)m`N,D.eY:kl%7;#&pI!)%a!A>++TKCYN"g*.7G'BF1h6UKXiUT3B
k=^PVtR1DlQKd:90e#X!(Tt%r2KQ,mSo"CL0)7%5Xm:jXGGh_K(e*.EXd6o*#j7tLQB[VIU!
QHW1l[F(AGtZQLV?01F?s@P"=FCC<Z13gOZZ>ugNbk4(l8Rf+?R<lSe\8N,)'HrM-HHr7j&^
1#UW.*id#'8aLU9.%h[=!PB;/?iMn+Khi7A,uLS4V"K7p8/l1F;MHe@?WG?$Do=1"N6.O:Rk
5-WfSBA.lQV@f6J;ssJ3%r+=!9!>nZYUZA'"4?bU%N$tLGt<'@@XJiRmAa?tBW8"16VA$s1+
+<KMLTHsIe)^t]A44ng!J.GF+";"eN\UL,-Rp?Yh9o/E2k3)JduOfiNJ7%QT%O.9mSLJ!Sm<:
G(NZhkhu&4S*di'(2mj0;G<nbD&s88]A=fZAG;O5>Y4C+j,;*c8K`?%&e[-jjt!oDMcNPJZ1(
hADZ3k_(Y3-R&+MgT_+F/CX8c5iu2P,<"*?[d1k?-EqfT3#q4WprZNCRE:k\MpfU1bU@Bo(A
G\p5F@:Hp^Vf+GfJ=VXrLDJ%XLG0uGSV%h(:Tl8,OC,OSOV1Bg?bbjn;MC%30/JR8a"bF)mE
Db&&]AY'YH)#4k2P75&VAC*PDT/&6a!D8%:(Ikn-p)60->#6CAh..CJ<M)WUS;M=\OF$UA*1b
rFQENNoLHV)"bq6dt/-q3!Jo#('aXm+*;8tERJ`3c_Io=B(8DGcc#^p_,-C)(CG`\99A3X_q
[OE&\[/^)Q9%@*-/bU3/e.s&'SQ#+*;5XQbW1Q<$QDas*X)9c[bfI&/d5di[#M^DQ8N^GSai
2SA%]AVLWQV;PVVcSm*MgabaWeKu*q7\=?#LSRZ_Y%m$C3GK<i[)l7`iTbk*Q!Sf&`4[B\&%H
7lk]ADTgI;_o7E6*ET^[0G$#hjs*AA5d#D(F:F.MPf(E)9ZRm?]A%bar)POSQ$"t9uhHq`Dc.L
LlV#W3sjaok<7jDep7HPA6hQ!FC5B1pmnP2H:09@*&SO2%)sIK(0%9t9DN_jlP=,^[`1laC?
gqbiXsPpNJu;R)`'li`u5f.rOsoF1,j9'30J:NfW/k=Bm+6R_^=JqOb%?7S&#AC:G5]APBXM/
l06(;Fo-\;.p[[D1'GH:c6(K/$+dG%0aF>UjDe9S\=.-M;*=':d)[F=n&HWnUTcVC3^5c^>_
$f"[9&Ts,GQoaelKn5,`87phBf+m%BNJ5%rb6FgVKD"0"V8?qfi>0<8L!=hdWlutmVI+l=2O
Fm^1DTr^*fS(\7-i/qVpZEQT&7jF7(:G"X4Gh]A56Ip8h)t/=%;bpEgS\,ZG?eM%bTd.MmV!u
%%Vl4PnBjZD9"pGA*_:SU7>HT8b#X"U6GSbPF`bc0,@BF\3nF#$i*^_TUcY3BanVlD2Xs]AEZ
IX4fn^iNqQ<L>.u:eEDrWc:jJpe81uK`NPD><H7`i4i:>N_hkFVWA1t'6W?_kH'lLqk?At*^
8>-"P#.^:^Il((NrY@]A0P/gWVU3>6%1]Ai*(20b<3:UDKEQaG@=_CL%D<55Y#\\T[DkWjEX[l
"ZY_N`IB_P\MH2V#"?"*EZLj!GhOuZ+$/dqF[K_8nWk(&NO]AD/ml%N2cMcf1aP@MHCLLOp<N
tn)W.J<2I10W@E:YGjKRhG>d-HSl?)Q#YKo--&BZ*&E8T<R@sYX?_Bq3MEk`?A>';%Un:XYg
.Sh`'K,.7;e(E$GhGeW]AQR/p"RpQ-$9oDnmd9?WS4GrE)KR%mJ[gX,)T9GtO:`T^Wm1,LUcU
M-(?moM`;BF)\cIl[pgoMgf=/p%Q::Vgo%N=G$"fZ@a)B,Z`g"Of2pl6aS@^ZY@Ztir6m5ds
+8H<5.=1&8-2.;/B"S9\AnY>pW%>EAGDtpt?fsPmqlJ)g1aaUduTVP8WOO%&cJ!b#qeR"c_4
<CK?U&4X+1q*\FWG)CbDC86B8+PD(oi\hXAoR.o2U,^i0irZr5Heq%\fD4hg,XB1_S"6j;.6
0oD(A$]AbHCb=(@.ZtTN@_*rbYH?R:P8O>?`<;T#3,G%\Ul7R1HA'WA8t0r"nj+Q+fIg"B&6D
;H>To=0!gJia^J_!grCE>[Kg.7@!r;Tn\AfNZ7#hZ-JZ1Nirr)q2lRZfJYZcF[LjQhb/+NpX
6'ch&7j%4_\10,@77P`_(c$QMg1`/slAJEfI(7^&$E0mod_O7eJP4i;.<F.01>#254iiR\m]A
+1-Kb:3G]A;f/unM2UOO734*o-^4mC`1]AJ^;VG7RnWRF;J;D4R@4ETQn+A/^Ff<e.K)/8XZDB
F3N`)US+H[`:"5O1dVP6^Q]Am:^iehK2pCQI=g1_X<Wn)[rZ="Z$V=PoBm2t;L?3(+6H-3Pk:
'$WB`*OltrY"kY(9<13"LndMeR`Iq$CRN_#s?fs._0WrMYZV2EH^5WVX&d8#s,,qD7QhUVMh
:(/(Sdk'R:d`^HGI6JJ-6*Dr6C$STFah*q//ZV$)+lauLEUGiE:D$"GBpZ:Q1>cpB"m'7%Zj
.RkU0"DI.\jOP<L,#d,54"H1s0?jdF<&WU&)5VOiJP9.%EbVULutR=D*j:PS]A+_9.>\%"\M5
?EU0V<a]Afj*8qruc659lTY`)su'??QLRtR/EP-]AP\ICp:U6hFp$eU?UMMH4KCGA=4SGY26!2
PNg?8Q1?nb:hDPA:;QV#@q/dB`dJtjl)'9>6m;=Y,g.NE]A]Adks0=a<K]AB/G3HO?.;B-4gS@_
,M(@]A]A."YP^q.MXBO[-j=a`eFX@71mq=k>)Dh+EGC(Jf<rC+\t<:9r+(?"I)Wrq/L"7/en'D
^9S0pVPfC6A4i$hOaB#fok&#46k6QhOi</fGHN&t!Di/L@4I-GXb0[e>6h34rGE:</oGfKRq
uA;2/@nQoN'a'>1sX:1n]AC?j3Y3?fnBu<%lufB,Nf?*Gk`f+6#g[Sbi,rP?u9^`fJa65XQbt
%)EFeamV5&]Ao>i5(Td#_pn90&L?lrS1&O'"O*$3B)oSpp.kq;KA_4lhJ(FQf9cNs$c5fsfTr
p7V7[aTGSN=ni<ml7MbRHdF4#lX/t'4Qj7Q5j^'P9CdRs(HP6WF3H0]AKEDU@!iT$*gf[64GC
2ff3A3&Y]AbXfh9!hB%n96hb"L"6%pmH1aLs1'3fM7b?]ALXlJ8]A2;\dLOo"2?SW95BWTiN44C
0q4F#]ATXCQGL!_)2\T>QPH6R->O=b0qm!.<8L37mVjCkN9WC>bndh-O5^]AoPo_^_dQ^\hPOD
6XN/8h,BE'nZ$Rod6fh7roCl<3S&)71K0YMf+n9j,o2JA".Nq<@AA[Ia<5i2>!C^FtN-)lcS
ROk1*LW,LI9#b:aokp^F!GK0[\q18LJrk,d9[9<Pqm:^d*g$Cq"HA6"GZ1PJV[%ceO=!ML82
*4df1ht%cj*O8hqRqY9G*r00=P439rsr?&*nSo0]Ao2@7+bkodgBO)haDGiW.Zn!GQfpmC8-4
HLCo>0IMdR]AJW[4Bq@C-=hg,+NaWF--S$OK@:35$.ZNm`M:TP]A5k37st%PY`d)8CaB`g<S@j
45Y+.T?lsjq%1G=hKQ*rH.MkoB1:T:S2[j*<q=[Us$;Lj/V*j'XFL!,o\`CY]Ae8/<Ap8&E]Ad
Yqm9,ArP0\WRoX%P*h7@IOCqQ'RtBOOS92)51\8Ok8cM2MjFa<Rs,-36b>nT'*:\Mg"L1`\0
XN99qJ2EPgfiOe;=5PW@1'A22Y1u!DnSZ4]A73p!&kK>;B7]A@^*^cp"'Sp`\:;q1D(n3#,4*b
Hos!AD<<[,(3T6j2EEpVn>_E\[G;(^Qjj>s+SiEcIWP45h,#F*kHMn*9C+<AN^r`d9_UBs4b
*FaXY2mr8#G8F6R31Yns'i@YBu&134.a,)]AX[LN&,dCNcB$SfnEEZhb.(X\g[;p?uY[9-+Dm
$UXLkbBpb!Uk1YpZOM^kTq-(s5ocXTP/5L<HF9D;eD4=PWunIE:@P@u;rK\#Eb<eLbLp=dZ$
7(h3Fgk;!D.6P'7QsF`p5^B-8.rXH,DEA*BbOLK&>AhNo?B,:(L6Y`/K_QSn91X+9tBrGX?+
(5703i%+;Y>2"gt_JS>[OA"KhF(=M2C<+QD:J/s^SAe^-@6#<';q.F[-T&hA;*J=;YPpA:LJ
L26%XY]AWr'R0F\]A4u@bPakVkmetNG68VP0-e@OhN^ta1G@YpekOH#4er+P+Kp[A6P4INfr/&
Mcjb*UR:HL3,JQh?ff&I%`hA2Q&+2)91/7"c;7^N^T)q>sgL5@t=I]Aeh:3!KMlQg>PV2I>!c
_kUPg#+h_f&,=g`lTKPT`tN'V+G!&Nl@D*[_dTIA:^=Ns$Z!chAL<:e5O\Zu%an!L7+OH!AT
;6<\a^%OS:26AaZ0tiF1W;tQ>M!")t*%LKJ;Y[5kAM!F#T2&cI!^tRt:WAL4hk)EWO:J:l)>
;K:PbF\]ACfG[iudT.gEXl#Y.'q>/[?IP#bA<#%*&3F<#>3@ee4:WbX3]Aq-7L`\PnN@3[I@?8
GNo@A)T(1j;&_>/p'Lb0C8;iOb3bWeH^orZ<V'/S,mfRVuN,ABu9oIiHs5dS#QWsM#qN9d,J
oSP!+OTN&khFe;Apd!EDbeYj0:$K<5-&ZJ#49>Ur!JVTA1YhaNUshF/Lq]AHoI>,q9f:,V<JP
If[efi:;NmncSo-o=M3u),#&MCAkJ\,O+"?*(:XB3Zu(hZdU^E_YIhl<f/3[ir>ZS$]AORY^u
8-(%6p(Z\Ir$)=e8'//=/.-6E5ZGh?A$r`fY!fZQq"SBfKt#&g/_TaNll@f;W0F-#EUO8Bf+
fK?-HQ"'M%K0!iBD$(:@G2t4"\M[?MMoD7iT99JN5rHXX?3%&-:MW,*"oUi9bP!0VuT>*<Ms
+tdnJN\$7r''D4]Afp#s4!?7%KSubWWY*3qRrC:_Yr$o']AB[G$\M;S3rbue2TL=1e<p>FB;Z$
C]Agg"GkpY:/)i&BB$ahRmsYY?AB-gcnM<?7UHKGfiMp`)drSu<Pk,Q9bU:LT#Skgb;bcg)[#
2(2:aWf9#pZ`VAHKqludGbN.;_si@qgJLG>^FTr'4/EO`)g8%$:5"uuX2*HB8fBd*%K;HR/g
:_O<[)R&Z<S=9kj*iq5JjYU3?eA4OLkbcHTl"10RI'qM`m6[^_tQ&]AD5,XjXt`/q$QM8n!_i
m1E0$?>?k-KNt@]A7bt>CYoTtBKPq4PZRk6KHUTDEN_C;8Q(WE[WI=Chb2r'62Xi@D/rUoHSH
YKaqZIrF`.!W;Tml1$F3hVSW>,BoIEKa;_dT(XFjO,FCg+coGMOb`Uj]A4S4L'XHZ')`@KO[f
r?_7UP>L8I)A%$*se*(lBL<M$9YJ[<nR+K-DCSXn$&]AI\>FmANURcNh]AiZPHSQZR<.2:=eI?
<sjaf0!`kR`?C,`Cj#ki1`2;0i/FW5ZU-YCfdWsJ7g&5sm0M*1\N@&;X<uj8$NdNi`(ltW/*
(U=^*TIm<>m19,PT7[!dnQN@26\T43H)uI`<ND]A0F0k@R28PJQ_Ca3L<A_a[V).bRqNW^RGl
"X$0ZDC'nq<O2p5l?"m!h\B-==\aWso,`5GJ0ddpm3!/Drngrn<qQ,skLN?Yn66po,D0DDXF
GJ#b71#8OIchE4IQ%?tL'!p5dI%&I[p\nPJea?7!g(F1Ji_]Ao(MK//1odJ)&/plECt+gAdXY
G1T>O)L`"Om"*643i,mi&O`lcY7:K=8U<Tqai=rXP)3Y2NXLIRbJoftWsSAH'bbOY_3Gc*7)
>+C+?k;,PES6:S5#SY:+@%V6@Z1hY5QEJlibhdT6'(@nf#:R!4Di2MZC7?(p?dUIZ+6&YA(@
4r,>5:<)rKNs<^'a,4o.)X5^E/mV24I7'$-C(^@`Yqe!bTn]An3I+lb!!PQOOsP+/aFWn1VSf
"%_D?O8H:l$$>82LD8&q#(,96EUI6GnU_FV>pP'KK9?Hk[Z,f^>V';h%9ZaYPaf8/U'HX%Ng
@jQ2cog):nhat!"acU>)UtYt3_[Bp"""_^6r,MdP(5(u]A=*$#*&4J#30m5n06uZ[pAGu$J%R
4ZX[+g%o7>_X6]ASNrnTs;e,e50t8hNkN*_ls;)[#+UA=5><Pk'31n#lZRcD-tKG5&Pfo(jhs
p:9qlHt(Y8V\Y;\GP;N1NbQ&3^=B,6>IKFA/U5K8'fAC6)duEX;?AfR!RL\=AV"2_=S<"4i,
X1!5\4ia"V1#L:L$!.qTpr&!Y`cNc#?Z9?-gUhmCV'!7XofdkN#6a!F$C<epMTu"tV[u>i:a
#(8O2+8=Z56G/kSolC\0JDYd:43?n5)i)!Ss7hgk\fc[L49?G]AP;0DmiTZ&Ws)7b%1_'FlN2
\63djh=aq1eBSeWuW?f2dG@Bk@>A4+(ipp,KhSn(u-41p-K;WAAu0-]A2r`g'Vb+R@Gf:WRT)
<J"co/1,6c^\-gfLA]ALRIbFmI$.r/"=9R6DO,b`JM%,et+uks,;F)Vkui3bg&bX1%t:@\qB$
H4nNGl-)Aeao6JT:C$`A<M]AI@Rq>c>H2"@65C[<.YU\8'o,k#l;]AS[Rn4u[Jej#>@,S=U3Xc
M9P's&;$UCdg"I+#50,9(15V\XkgNK)1D0d/7uqH9ll`+:MAg2]A,dWH7F@2If".@utPO5o<G
kaAY?;Ss,ahZfd519:]AT;,#cT"-]A,!N\\(srIWcL=T.(CNm;;ZQj2<J>U^Vpa,rEg*p?RoAi
4);gX+1P&JU,Wq+;B<uZ[/U#P^BQ9bE>13q0`@/%T]A(F1pD?'O\d/K=#Of^n45"Km?e&$<>:
t+bQ7*/)F.FJiAlD9U1.]A!Au:.5c/pdW)GbYL8b5,-V7bLZ=D#@:BchrKoPt/Wno"Ps&.iE2
(CDMs/U7lDjTShR\j&64;i60WeIu,k6FGLg8m+D>1*W+($r*&#:._bKf;8+lPag\[H5t.m!2
5>k%6`_&c?[nOWL&PDb;2VEg,R@f&s.MY^g[MJIPO%WMD7Z7LQKC.@^gm9\674(PErKP&F;?
T%3APaA3rPacakb>!(&B_p@o=A'5e.k_&Q]AkZC?6Wd0oR8?c?bjUB]AD\*PhLZJ9p^s1r`h&i
'7Z@'\<`gNbG1LLZ4-O41q*&Gr.dZD#$J!+Ur'S"knA$[[`.NB=]AL]Aq+GYH`gf4;NYeC7HW6
X3Z0/H_.l.5/<UlA#=k8`X(9uLS_P-"N)oe^(bYgBkZ5$a(&K)a2bUYFdUm-$M<n%\4;-OTZ
UF]As:`n(kjK59F^:gR.5fa[FrF4j*jaTCh._"X=_O6t#+$\SC38dBV7ctlN2$5Vn;YeutmV%
HJ/GI1->[Wnc;81q5FPE;+,m,l<\!S+W8R>Yo&EJgLkS1J;FS:T>n`0jN9Zgu+X]As2;&+%rj
o;.ZgM/CL]A4l%&Z`a:KL##$:RqT>l)r<MTBFednp5PB#7?-Ekb'+<*;)\jWFdl=/*4p@-)/2
';GS3r_FH?hJ8BkRg2Hf__l/rn#)+[S%Cb(sml%-iTj7QB%C2-"MoPc`JM,!<EuIHl`R&:_t
Jg30!VD>n.h'_rKHI,pgA>]AB*]A_q7-ld]AqBh$'/V5^!f$3h\:$cZLf?;ggDH4Y@#osK*tKW*
@4Ik9"sc?p_@Yp4Rk>;tb%!?77aO`u`W/"f3L9Bs%:eS:hkU'TKJ(f)nb$J,eiFdeeRu#6_S
XBUeq\Dk4TLqR^H%1,"p0Hqh-3'GhsU8:)/LnOkGcqj;#kSo3j*fYN(7d.c*eJ<6_>A-;e`)
a4"$m^*kBQgGUrPLn3"/4kpJ(aRqCgl#/dWBE^=8Lf]A(j8nq5e#]A@Ec9=Ba4Fh4:&C2jHU%0
S3"AEMH.!?`7li@i%Ob&=EQS#Q$I>>9u-_,PR6O;5bQZ>T?jDo)9Ckf:/35Z_';/huFq88>!
MZ.*B%berg9-=?Q`ja7g`\S/2HeZ:aam]A'aFq6,H)9TV'uA(84eK]A#Y<jJ8.n/0b<d+SK:=h
7!M!8%Qt4[?W,m.&+1<W7+gnfbBjk`YpK;"As7^/36<+&m/eE+lQc0;^/MiFrR8b:Ec%=7>r
,Z6+n#0M4q5^Ljr_O/GqtWFFbI7a*\Yf_&?K^!ZMc9Y?rE.+B.VI?S'>#l*>(l3I9;&eJo>\
1*"QIifsbEnTAWrrU]A%H^S3QOGTruP3qHAAIP2_-L-BF#R%t3";_1$KP#/-lYS<E;3YE55QY
J)Rg_CDseJg?$s<8AIi@[.?_f\P1T**/?4FKt3=E5Z673'I-+his7u>&k6a/4?YM6!P/.+3%
uT%]ANij3(Q_M02<6bo%YMhec`1/0+Ys?*ko6o*1*<XabnK/\rl,9F4^a0Q5pX:lMFp,3R73b
<SI;b&QN2I=%.`c&_=SiHS9kbeVriTZHAl"^@tAd[\LHQoGm>ne3g#J0g<Gg5@=-aAoPdqf"
;R@I5S+Zmtdt3+9u5$p\=,2_tkCj+=*2oDJhH"R'6Ro7tX7AAT^U=TtSjS?>QH#p$G2*9XOJ
2aNGR+\.s-6odC;`gnZdDKS2#jd,SJU)V$N_7XT6/IK$;B/3hkb?(EVrkF[+,L2jkF9f_q1<
(Gr)cFFXJoP]AmV.i;#a`9P_Ma,Lj%Kr6n"b=QYL@SU.'lLDm0n=?XJW]A!JTib`U-98rSTN`^
GdAL>]AnlVZ)p>N;\W&"laPWZ3b03sfOCSr7IrK9Pe'8IS67'%"sBel0EI\d$IZ<g#F^(h[bc
;R5[gEL\fL_]A)C9+Q+4]AYtoYf0XA+2P,05MojD"%c(76pM)PI10-ZrnE.>0k8e7$[`:@oS-Q
RAR+5?tG:S[W>n%7LNPPWnT&JK?m?e^hfcB5,*YZ7=UB;GP:I/gbkh>HET5g3/;oE"i.FA4+
<E%G!$P7-Z<V&n\K>!J>rn-!_(bCsYGj.8p;og96nLX2`\%@lDc3-J2]A5-*;%7_F+ajB"duA
-6l>Ks8%R]A=C.6'L]A(83H!K[ZJ1(G/3OD$-jXd+OP9rr4n>KQFn?p_,?Llj&RS0]A5YG;2"EE
1M==UE?-Mjs&<<Gj1WYG?I>T+_FheBk507''slKT:K-JMqL`Ej)k&DAT>Cmc?WLg8[;3kO,9
E:3mY9I3l8deWi;$`E-!K0fqIQ'H<6F!K$N5Ap$#Tmei&$\j4!AA[aBC&QrXr4V7jV;5cKi`
0PEVdqfq%km<1occ?m=l!aY<+)'8q1#NK&oki.0Wcu9N#t-pUiA^!+;`[EVl3n+ij/>sH[\T
5>[$eiK.JT:=C.%Q7<CpT[#g[k8'qc-i(3RYS7ml.#M\W.'bBJ.TXhJUjKD>fc/kY46n3rtB
<2_p6EePbimDd-#6dq4I@jUMCC?7TD_8E,3;?D.9-OSL3ZIpqE;-b%^PBW:0W>G!,S-_=m(b
-hDbi=S+IM-%ome(X9eEAZ.')ih-i,s&0I?Td9kDHW[j'[,<o0_T'OjP5kOb]Aa+:Ulgb,RoM
E=>`0igJ0ro"@f#oPaX4NXuOVJ?j3#HUt]A/XsB:DU6E+cX"#[m(t*6/l\.%#3"?/,mGLO#GX
T7spgo`#Gb!)[$#`M/G'a4Z^FTtN$r,="3$'/ta.3E"<9Ed1Gg9JB^Qn=OkJf(Yjb4q2+'c"
<W%;P?9`P*4"7JG;r>=N8(HqQph"2tfK2RE@feU/iC!>]AT^;k[eXB?L!UT>TBRVjOhTqbtT,
$ga"m"h$!rN_!dU<EWa0UM%'O/,b.I!<:Bli=nt$L^(QA%`ahL:VImW16L*Psg)IYWfub4-s
/0"oE:lEqp(aS\I?;/9*(ZjKXq[K4,3!"5srgI'K\A!:a9O%)X>Q*nW8QNWL./nf]A/FE/q.,
9Oh1rQIR/V-h=X\,=@+3HT8)Na31h[MfVcg<G&?Z5B"I))+JEY3oKb:5:sPg9>/05'3X.5[H
."cK,FQid7J@ILENZ\H'kqZkQI\8h#5l"/o'=c($Dpe_s_hmfhi87.B+qH?"k3'A6QIdnp9k
F0MtBYs.ZR)T'sH<G:8-@Uk+kEnH1BHO_%COU:QPcAX;^.[gtIpj%*Wck.Ng*_'<ukG%L6Kf
%rm%6,A8@mCpQH_1>tGkK[]ApJjg>nN`r&Vl^g3jUdiI(5]AtTs8lr]A+8Mk40Wd7jLHFt86aam
#)68@Je([3)@MPJ^scX2/&`/5Qi:b^2DCO6B\`QpNfT%:gDrmQl+9+BNK)-TV9mO"&;:GA0;
IkCK,jO!95/#MVM+ap$Z)Sbd)_`kS'L=5$<MQhmODhc1o?SSkT>/[KJB1><n-4St/5(YR/gH
#tC*s.4gh0Tdr?B3h'PX^&ZZ$j(n%_.#H))`QfC?eQ:Z0,BAC#_-%i_Sm>SC05J+(&?tp/51
G37tOJOV'ck-toQo0n]A'&-=JXnQRbfTGj5'1GrSi;2)jd+Uc`:fCMe1g9$Zod(EnD]An]A_Cum
Kgn]AEe+jN:[)&,3%od]AS>Zef;\HZHZ[o>MNi),$S$b@UApX8N9*@oI`)`,LF"TjJIl\2Y[43
!F.bSM4VB+MD\j>N_Q[C1/_!3W2OdV7&)DM_85WN'J<0l.MjKeI&C>JDIm;PNr"AuGoGi\3a
9,BFYW?-]AT!Ce/'Bf989O$-l0KS8@C<)3j.4sr[bYj:\0=Nd1CEFILSS5Gc*%sq&`KR8a:8L
(DAXTeEGk(ua9S<86$/2`kI]A/=20U(PJU]A+0R`hu!%eP@(%hWu:2j(&PK2e4-blhR2I8$gR.
qa8FFe<LLE:c1;=:N)Xn%bq;F2UG,t:R%#tpNpH"_[[0'(i5F3%I3.4$GoYX9)?P8nTc0>jI
2Cui>37N!A(I79LmVAak_5=uiI@3>B(+uS'2i=+F/Tp(M522+p#o:V)bKu.A^jI*O1fc*IS)
78@j"9h',B9iI\?V+RWQ)`r&r$G"&<D7[W<T97!A1g1Fmuo%t:V[]ABK6c"d/2VPL`6`g)2<k
-$o:(Uq]A<ClYZ/pXH`-``Rhoq\2h$f>5hIok"SoO?[cU?bt%2sIDl6OBXgq<Q2]A%s,U'l&'e
haj-<EeM<;-:M%KJb0R'Wn>*2D+tUs;QoKf!hSV<k=W?#OIg/mpgj5C`j_]A`!HU*f52,CR&#
H:R7/F?-2;f?CqUf"Ah><EWLtnWVDL!/OW@0J^OfM7X1*UVldB`We`(?dFc]A$%Np'tDQXg&b
%a_!]ACb)I*00Lo@PDS/h[^uH7M=DlYB)ZlN?gV7E/6t18N:afN$&WlS+HN=4[EWMA7N"`1l%
Kgn8MB,F6*.TMiIeT?*remAIE"[1,c!N3Kj"`G\g+l!(VXVE8#X+&'8SYRY.ZX\O3O$mu8L-
.O@_O;+k#ahTa]A<+#0*WBTqMKJqe7Yi3:OXbkuT-LOC=U(R9Ji7T:mc.i1]A:*^[+1X<)B_j@
WGJ;r:V*4QhT.^FOHK<?3H<SJYl81@+DeMYNc7h&q>:5!@b)BpMc``VJgF]AJ4gsTeoVXA/1M
]A<YKXlYVN`TU"uu8M;tnMNCenfYpHBZ<->jL\K4V0p8X,4k!1^YQO:/D<GdPVEg/UfcaUB<K
?hc@OtHIK9(LJ4M)#lO8c"SVC%2$Z[r?L]AKBE"ZZRJl)",pJt/p6+pm_]AtF(rE^b"08gU)'Q
aV/+F=OO_=)XYS4VE93ueb"n&p/-Iae?n5lnRd+L6;Z5e@qk,1s4<3efmc]Ad`%i5eWS.qps2
"Cdr,<Z94pJ@Rb`^i:$3ab3@#5S&.PL]AFmhPC?Dmr"_\`a2:NX\Y+NP7.nO<JD0iSD+NBlK/
dh6eRscdch,/G#Q/7m#%Df(ehXc&/@`):ql+iM%nE-f*4Q)^/]A:n\LQ8^es.,lacXkt'6Wo<
Mo-U+jdBqi:b"5@&\r)HGPMI*:)Yuq:66mgoBuIrMP>XTONAUT!I6;&^#HYi!f;!/oOsfXk\
2Y12Q$L]A^]Aq_L/R(n*50IU89_Z*4)klDjJm&'E^ef.3S!Mu>tH*r]AmT+?B6F$`>m8+L]A<[_E
?;8m40dS8j5[=U8XZmC_LfaNsk;\T"m@hHSi+E^fU-AlH9Rqnn&S]APg)SNJ$kS?f\auSq"%@
AE/O2l,jgBKE+NVV_;,u*ic7nc.&YMg?<ko6)6#OWQ#AZRThXom!V_-HAEeKL1=^0*`qS;&1
dN0NVe>9hb-*OljpV4Xm_-Zl\TDOB@aWX!`^J2+-<;\q]A<23MBUDLP9scX-!"@k)&k90I3rL
ZP:ntnH,85:j%VPEY>[J+hDqoX4*3WU.=m!s.RAE,$["*jianpCY8g0_Mh/"+fo4_b&Qjq[1
[n@:#I#gdnADmELJTF[T+^K!DH]A^_]A'Pb,O\NB:#-o7UL1U*?,rlPJG:K9\GZ`L5+muQ1_<s
PC<9;"_08u)t@&^Jl=D#G-r2IPjJE?iYabbXVGI!?VW4gt]ADlP\SO?Sr)c5u>0^hE\O\M1t9
1.f\(n;c&`nC(qH/od2H4I!"e!(H>\Hi]AJJ?G.1UI8c.2X%kKhcjU#1,-_JY!`M'H!"L6m#+
6c+8Aiqk@!AcgI/j^458?V>T8]A/.J3qlQL<+l?8iYtr[CjtrNYm'"G[)^b.nrAj&BlG:>%(F
*/;mA%h\?T/W?5hnZpVC\(<8Sp9$n(3kqIul)uu"K-;&65Znu\hN6^Gg.rB,s!/,IS=F/3P%
W=6VAI^.,LWnB$66Z'rD%S/@2uV<Vgb:!>[)H;NJ('qnL3n8IApaL[a)do=4JZWNQBh22_.H
B0*[Embra$Lr"1donEl@Nak<^5d%)C3@RnD)Bd]ABRqGMZ0r@`adENbVX/TfFh1\@Cq^3ig)l
el/?SN)>3ah35,/-p["sR<]A,Pc<?YGmKiKQ(5C9<XtgV;nk']AqrJ/7d'GWZqWD@P5e<u`aQE
E2Jl"H:.I(@,[#bTJmeA%n*l0)`d,l>nch_Y)SL7dK^cF&+Cf@Q=<.Bh0lE?SL^%:=ordD/6
ZO[9W3hedR'Nj2J%+cc/"d@q/1?#W^d&^e(3'4Q*Y#E^j"6\$K_()g8+\mjQ@XMYhrcSQZi!
;GBcKBAfef3^SPk&Pbl%WDN5/o9#X:Cfe9G=Ke[abY=Xb]AKrjbdX",j%1dC5B]A(l_L/8cH39
?PZ/fYPIkU#nAi$]AI\1/f'h&Xc7K[Tn)jV%\n-+OBU\!0Q7;Pn4doW79$Z5Q+#Md\*5'2TVk
4@K?r7YW4Ai"uHhpkQ,pc)1A"<]A=7`ipM[+@<I'1?o^^0c^D/9-04-tH'pE,%hF(O&#TtUin
\Bd,FF_I'n?Ou3s,VC%:NjiFhIrQ6<:n(\ip6&;^Dm^a.[pnJ?5K"-o.kQZYs`^f`gGM8n&G
L13+,c(j0`GA1:qLs+*5s7jY.HhR+t"SZGP"SXS-CUQ;UY*Gme*Kn23b[WV&Ce>Wp/s!_q5^
W:L557sCX.+p:2Wf8B<?PV)2`nCKemK$=5\@Nd7FC?aA?^H7'Mb@7"SE<TcA01.0+.N#4mI(
-?!TXuAdYn@eIohB-\+&('+:p#QP<<_J'jmRCX)0[['E#c57Xss%G0!E&L))$GTnVmSE%%V"
NTu<k!,]A^M@hHlE&GmQe:WO!$q%)2a/@.7H=j%PtAo*j!*?F]AXF*+WO$^"D&d=V",EbJVb4B
#ffj=JP&ctONoAmM&G`4V_XR-3Oh"'Cn?faaPla8q&nAe9!a"-5pP!gjckZX8DYbZ[GbX;Jf
fpg7nbNE+rQP`R1-;Jm[RZdXsF2)c)@/!5L:<&YkHq1UVne9'[u$Di;I5^LQ"$dDlg=sF+$D
$BUmg(c!CYT!11+mKMad[ZE%3lGqG8'^75+*Wa1V$ubn''3+_T@sJ3'f?1H=C>PJiPEBqDTH
7*IlA0qENl\onifNcXV1*1FTlbtH27T>I)@+pQhcE'SCOU@:`S5PL&SIlcifeg6Z>VW@edjI
U;@kM:;jD0"KII4iinQjK`6YL7=`YI<s5G%jW!'G=M-gReoHBu7b%"E;qT.UgA]A`1<:6gp?S
\o^16AHUfFD7eOSEZS.ZK;QIs+RJJA0?E)Eu+=S.Q^gp0KCX,&2IJb0,6Rq.?/IBS"DQ=6!8
)/u3L5iV8HQ,WHV&,=Y<l30mAXirEs0IcRt-B\^NgJIVTbU7oP]AiM<'0mN9;ab:d:KZ[1?i8
]A/C%h6R<_+Z6/\euFA9BQr5!QT<V5j-K:S:YK0$kJYt;nQC<j>YP'g=<4P=4Fm7\CYm8m!.i
#QmDgT/`HOK<BR3U&8Gc0GX#4l3_rR]A_I;R)E"Ai.n-3(^JQ[A2q7?1DXJYdL:f:W&2*9bn$
:q5K1igPU497,0K+NEQ_^\=qBSGJ:Vk8)qoq!.oiX"lmk%;'PP]Am&e-<>$$^#AB^a.rn'K]AY
.j`;B2GF.bPI]ARNdZio[1*ZErQ">XCCliRe@S<rR^q^_<qu8H:1_L8^_Q<h;kI@J7h=GScHB
1Ile10-WD>?.Mj(BZV"r]Ar(1b(dKO-$p4F9H0QMKrB't*?;Fnk2#Bf4RY[02h?$W]A5mI3.4p
@qanK@=-/O9(B<5$+5Y/*g8t*f+;3<6u]AL.1C0Z_LIhHf:Np7?(2i\N^^?PKd/K01f#_OnKb
e)]A#gtA7pXq&T_:!q!*sQ<gr%Ou>Ug$G'_"M:J-PtLS4Bb1fQh45KYgX8X3S@@9KIkAlOTj&
,fp]AqI(MR_<;X=!s&Rk?0:jqPV%V!E,XJuQTr<Be4))2_754%Dern3\U6pMY!pYgO9cN1Y5Y
!`?b*#@O-n5pLpM\Q%q"1SMo@]A/V-(@n7[p^@JT=31]A]A'SiK6K"?GGN2BqJdX$Tlt`_p[\M.
r,X29U2c9#_QuBQog'V0_a-H*dlXeZGTQZrRcdjaBB^'1PGjjs)%<DbDAUN,7/B`OQ+T/=,0
^SZS<mLA8oFc4&'TmHF4do684UAL[!GT1^p?NZ[olIGG`=ALQR:*c1qSh>[MXT4)Dh'q3pcA
f'QYtojC1VlOkZ=XuOYb75N8Zc.`/0DBclo[kal^Ah_^&Pl8@')p./-.SFP/Y4!fQ"BbHl?&
r]A_<"8,mO;qu7\>7S&nP+HD9AH5_:UfEh?@?H]Adh.%GsElq#<CZ['[EB.[7^,;XE*nMn!i11
I7[5"9%4a#]A#:C@\:dJTBTkcGJHDo6<U$'O;VZ(h:.7k:\r]A<h?D!nq]A>HrgJMig/1L$dJRR
C>\_5P;NSU5[IULS[PABcfBg%"NVIP#1af!)aD<EDcQ5u<8"(G=:)MUG6eagC3M^Ke&IRA*S
h6R1!Qc2bd+Kd[LJ)KuZ$\NV\9m4$CQXmae+p5M1FD`#r+5&Ubr=]AqWl'`(`iiSkm.liWhfO
;DS;Z-Y!M!."60%XAq:*G9Aj5mj3$VQ5Hr5aGUNpl%(Le3q!..^`l:i8;UIW4L?,%L^%]AFOC
[d;>'D+X:WG5SY\c'<8/N\l2Np2&4edd8tp4aFX9@G@CC_O@h)rpZ35<W/F2[$%X"f7m;=\:
6*l(36iB@or8eD=@Go=JON[P!'O`.V.^HoqGA.2boUN^I'p4aO%k`q7\4+hAgj[iFq2[abBR
rGanF[7KMTl7;su;R0Yl]AmQ%"&@a0khPAHA!#rP$-em_G8nIPMjO@cM2Z'5FmEj*acrkk0-]A
9dS'Mt*tZl:dSikKCFaM0JLcpM+`o[_><g\dt?%hgVG]AGC>Qo87g\*WBIaHiV2H"*C*n8l%g
PY[,`9U1acka_%]A<e52Mb_1SutB!ri(C?[1Pp2H0@"JC<D'k>DE-`.]A<#)h%1(Vp8dm27;5s
W#P.ep\!Cf.IL)&g@t&YWc-+m&jG1N<@@[(MBk!tO.NYnT!5\M)fl-9n!sI[I&mE>bgchZY7
Bg%0D.jLMM=f_`91[M)a%P(\Wf-V13raQS^Zkn53ns,;f,9;Y6rRpe"\>?#d$!j_`X+l8a22
O:;C-Y)ns+*8\U86@Wt+/2;R=2cGl!SD^^B(."Li0k*/cl+Nt;$'`E7f[Z\g;g7*qbL;Z./n
O"cLS"1\s+`_]Ag)l%sSL."@#Ld7L3g5A_XM!=6Ab(esaGDcXW5,-DY9\mtUis6pFb/3IEhWT
ZM9=;FdDf*c,Gut'#F;*PC+&`+@XP'jOedi&H(qEe-G%qktT8gage:-KH=As]A;!`o*=Zq9f`
cT/&E>;)mUGXu\;34+B,Yp;miVs;$Ydb4LV42:F/&(i>u307[ueo&FBTA<GAkFk.FW?ijGch
*]Ar70@kpoPL2Jn]AJO-Pf->Z\G>))AsoUPLG94gLWGV6@F!W86@XZ2n-'ge__\*D;jn/%)H<B
RIs1D^6m$F[CHP+^IYK$KW^_,LO.*-=")me49/KrFhR7(,7jDaV%<jW*_Rb2@bUiSXr'ePBl
P6O/e??d,eRs1@V@LGglY`9@!,q!_<b0*=<W*ACCMDo?n>Aj96mJO-%(TbVN!fR9`KHVL7f)
6e0qk_KL['65lFEBQ2@[Dqospg=cP(>b!o"G*;]AMEmJ8!nl5Dl$l#&E-Jq9&^2?A^GjraWP"
OpsV^Eo7705djF5.5dRl37a0tbpgI>-D$U5%fUiS+H7@E'(j/2??7iG2se>;5[[nH]AOf064A
<%bmu,2c`%Yt&W>KIQeOD?6,i<+V0C42sk"d<H5gT[BUH+:"JYudOk(pYQ0#tC9)P*Gt+lN?
#Z8>GKD]AIe2C00USh=lUW+NqtKe$9HFc1WRg9<HN<&/r>jfT./ML0U\1<(W`Iid>+*)aMtQ$
2^Q:g6TX+Tap+Ym_$qp/@Oko`p^]AWEQl)mBl#?^(V."jNU\.d5irJ;L32n:!c\pkkpAc2I%:
NGL2s(U\Z#e@,\sTuM[XDa,84CQZ+MWD;o-M<jR',&$V@r2cEu9Jg$eG!XcRJt=ET!iKf^ZM
RA(X;i7pj$;9Ye8>Lb,h/6,!gf'.p]Am5#bjG>JNa3t,mS4E<TMClf6G`M#l/Z,U@MH5buPQW
h>^8$5.<\ae.:o0cI@GN9\+4H+o)-I`EQQbK;Kr-[D#@B*'Yr:,HV_Xq#IXu;FPduJX:Z#=4
@HR:_<,G1u>a?m'gR-2Jk)Tqj0Ic2hYeYkA]Afjd";!rCSG+S++33]A\kR`kXm93sTbgiaUg#Y
*$2?B>m#FQ1j*t00)7`5AXW]Ah#gUoH.fP/l[>3$7babnXFh]ATk'uY?guJ5e^p1OnYWqBFlNQ
<]AA#PJ.a-/Y&<FG&SD>=R/G@Q/ih8UC]A6_P^$^;-iF.T`_H^b.A#=1WqABkGHND9G?[VF&_O
<SkUGBjCgS`a@K@aY2/E#\OEdXFmfFT,X.sUcTL"jTLAaEdZX-+d-1?fTjfmo2S;j@c,UuPt
rhc(*H6sc3@ECd_nJc3*m-jbo#!aD1Tu@i==Ah>\=D6pD?$`)*OX;4KL#,UT>Ha"5[/m:?!6
JE";YDTF*As-->npZ.O5->0G2fUXMqu4Sra<DG-;9VXhW<\[!9.,EM%/a!;5n9DS-=q+$M&F
F#2#[[rG:XSH*?g!b,/3ud`&mJ0M9Tmj;D@oKH2n60/s/AVM.gu3*U"MENBa9fc1V:kRPU+]A
R:_,E'hbs"3Yolbo`O\UjQ;Z`f(Y4b8OODK@6Y91R*9,K@A_na+rcO^R\,lJZWC7j8I]A[YCk
7*LTnVad$/#f5$Tb'qKSXm2-R@6gR\RD6!K,hU:X:\ji9+Z%2'_%5mhQG46.SF;LVm@Nf.el
8Z/7t)5trjB#lC[NaS3-*N[/:ZmS#9b6dIOMi,[EKM3<?#c'j:>lK.o$K?jh0I_0rabC0!e)
-"Aj2R<=%.0L3OQI'_CuH@:nFIbff6NFo;m,3,'dS&uJN,BN8N*$4<*X-l`93"\fffOSd>rP
Se6_-HFLC=-WlZ&OXD3Q<Z^R_"V_<8ci>OkT'kYZ.Bn5b!b;Ia?_P*8>RLn0+C]A>`d!E/OCe
B?4`9?lm>`,VG?t<1OB.SUk/O0I+ha=9D>IQRB&,[SW=6N$SGMYe3o"#1ja[/U:0=7In89e,
nl2Nb8A"/]AJ?J1-)#iOl6\CDXEGaWi\SG5*!!>TV.A2nq%)rajZL`U[&!Yo8!7m&SXYm^uD4
>[%W$TC=ZIoIBA\XS+(pM=@#[/f6ck\B>2-P40nOW4IL5#\dbrW^:Hg,E>0C+Z'+F\Cf8KD6
7h2OXHOd9.ulF#\Ni<PAZC>NtR-*".]A[X?rq?O8^s'C<j/M"3j:<:*jP@FN<*pu\>U#<RKo5
j+J$=gWHI*0$\^(SR,*9LBX?5,\/!4rW^V6,k%qK_94XJFUeBMJZQn>(V5bUW@[:?"Y/Io=o
kD[%q6d!n2D_fL?YNK'sMu4:eZu6R$n0=r\NC1a%f_q4+)^4EE?4s1j2^kdNV]Ao`<"eGC:60
Q]A>^_+[$9L%A/`oT??@qoJ09U(*d;5X^etNk=!9V/0u[%1ZLK$-]A4ala\>`rI_3m*q%!U^fO
qdiaj6DI\*o4`DZ"1Y2LTmJ%(%&F,2I/Eg:[O#k[ko.YHG-Y%DMD"aIU\oRG"]ACNrG^$?Z;r
LlTL"<^;K8X`-;NL,IZn6bp1$1?EcEb@1,%!pcl"/i*=#X)Jh/+*1.M(9[^Jg=4B+(&#7`Ao
1p*Sc7Ru&@)?`g.o@6=/6''AgD)XOb/6]A?\_[j-kdTHX_*Yk!P-1i<'lJO;d_^Wbp-I'YL_]A
9H>S=tQ>PQHhH3e-UK]AW?o\boNPC+.aFUe/NtqK9ni\Li\MA!$46)96'(.=&2[6MOf_YWs7e
UL%-r>4)o;\`1MC4-1jj5*eON[*e^@jl<KqTD+"482ggE"7L,fMb>&HBXkO*#oKP/T!6uLS-
mch5-d@l!]A6cNE(Du)GgJ/f$lH=34gQ<l[/o[[84JuWj.\j9p8;InXC:(pm[\^EO]A-Q&>!e=
\?Cm7ACaFpF$P/Dfh*DO,_Gt=3G=R%_B6MB$_<SNRZ&0=B$@T$qN#8-8YI^hO\L>H0<oVH05
j4j`bH(9NS%5_/fkK,UT)*9`kSrhLJ92NR%4@ms*0-M5ie-='.b'NpN<BFt;VSkF;\B;eSXL
1&[;8k&Gjd#>Y>nr,A:O0.@lo.4jUlrX,Fi4r14Wgq7]AYfHZBOPpoa6_"mZl@,J3A#&i+B=I
7-XkmlhYc2c3n@Hf2t3h/[BIXk2!M[GgnXV"f\lMZNt8FA-]Aa6o!8Z#/Rmh$IPU5%jdmf1_L
c_0(bZVL:)]AqeoBAK:$>G`)cK1ge06B4KN@Wui2-%MGZ$P@$"H=d.b/[DlY<0HVE/#lRf&72
.3e$[g/$rV2paQfFY/K9uks-.gouA?)V+K9l+BXU7ia^L\F+HkXB7'X057J.?!kh\CCX%as[
sFA.3(9$7P*^+-[`T'MQ.?;MQoD0ZqRMEEf.+Mmg^KdjN.Ii(iI%FlVgg=-MA>?Ue>;2>7um
1>?qV0Cha'h_J5Q3q99e8;*O2&\ZB]A(HhXh(dMn]A\r#X8s+1Vjgo^sC>7Esof0%q)+2o@/XD
RrRPAM"(8,5A1MHo8ea4.-Ju#U-eUY\WUT)in;BV:"C3;&SREl^4g[Z.co?Rh5SDp>52E41+
Po>2!HVc,KpC]Abq(e="'PIg2^pP2n%n/+b*/^#_ORa"+oKOI6_j`ua\5fr6V906)5oQsg>8@
>dlm%]A8&e86/8pn$+WCsG_'0nGPgCf!10ri]Ar\dOW6-1]AX/rjB[Q\f-QN?/I,V=ZIus0+p`2
l?+YS09%D;X8$N*qS2ce\jk@^;+o*p)F%BXOR-<JI17uUT]A,do93I"TBZBuk,>=b,:Fia$*>
;nlF3caagJm.f_Os"=abSA9NT;X+%C`B@o0,c)hW$gRUX]A&9V;S@Y\j!ba1`D7&Z9eYjbHiO
0-QPOlm2W&mr(*ca7"RPBG;P%00Soq3$2#Y`.FTkQos`TBeqQg@ruI^,mV8WbTp?YR!*c87Q
N2LDS4+j:H*D8<<5F4_RVDWEK0jWEX"'8*]A75m'8\T1-9!s/BdP1Yd(iRC`0?Y<_?*&_%R?&
rqGG^S9D>McA[@[KQWqD-9X/'6:A9c>%u]Af8jl7]A`bc!g"^=1OaNk.DQ0AU6WC6,RK"#'U?\
B6u%BR'88TuCJ*F1C/4m,g+-s&jeF-J8t!_d7hlSl/d^rUoHE\A/+7C'slLlPXm5-g?au!9s
L7?XhOoJa/Lj<=AVunb87=!II*#\a-i>G,h'kr;%;Ej2.(rM1Y$-@d5FW,^Ra#L*b9gU%C3T
%ug=(5M27\(NA@'nAM;=51ra)<Z)]A\W/Y.u.+!]A!pGU0sqp'n59WdH99YL2b359B,HKAhbV]A
A#l)FTE^7iL>/mX65n+f0!5?fFQ_1`gh($5U#T"A7*#btQd2Oe:lWWUWQIF3Vfuh2/jG\Z(`
4cH0!E/L\tH%#qri;T)81(K+a5lcd+i6@mWC\sX3=Lf6*.YX0Wo,UECS%b.WA@BY&b2gS(?e
^QPYGcjN+?k4pa1u7GiX9:V33$+6M/d+aq@TA_=VV_>CRCt$Gg7%J'cWk<i87>FdFo':LO3*
/=0a"C1@&*Z[V=OdQj[+45^m^U*PeV[U\[!Y6"UL_->P'Sm!d(gd"m=4m-;[%k1AHY^+t&8<
E:0dqCIW0M_^b:?QMUC@g[:eX^3q>R>@Ud1K.0DRfV]Am"6P\>SUp,tCN'6<@jcT@WH9b5ek2
f-/(u/_\e$pWYei2$e\Nj6^<pe`,mFnK`mQ:DPP+k"+l^lDg<rR;"OFo\@W/rNcTqq6*o0We
13o#@m.p!-pSV=LFNo%\t(D%%^HRh46TUAFi[>e_MbuY&JL6^?ho!%d^D`R!)F44XF7$nKuR
RL]AqT&jDu=KYHlgsrHSK7I)EAIkL6rERkr'a3G;%0%-8*g?+Y']ArYsiRPQWT:8J^D]AUoN'.p
WQJqoT,Z4pbUnc=]AZ6\MlIY8nTB:RInK,?1C_4HDd1JP#B?9aAudD:W8Qp4J'31g0+.fE<1_
(mPSi3o*c9:N=c$Qdt,Him`Tm*=\H+RiD=HS&S._Eioc:%q!`".B5^=_d-dnSesJAj*LKF/n
%$<A-qibf9o9Oa=3`<3a:7h<aoTU(1Fj$oB;Kp1TptM\HOP$MT.*g7#s1j7[p[`\R+9;?K9e
1MR7M&0IHN;SD=]A[4H2k_AFO4fZ4nDRpB]AWuh/bY7T>,I9#*OBYlr$IjX3CQQU6L$YTb$cbX
p@/"fQu0DTI&s*#?Y^NEbPt9S_)2d7)ONIYaHG0nR@?p:"@_eAd`]ArfX:JJOdfKqe`ZSC`E%
#-0>f=;OPdOjA7.RW<a9PDMA>@V>:>ucKgoEl`jZ`:RB'?apn!R=dqA(HOfV:,<K;2O]A:1WX
ZV)YECrb&a&1kS;c/,ps=lf,[3HI9W,<W=QMd+7TXIg:8$f=eaE.>Y1MGD`&NLK\N%A&QKe=
Hm;)S2qK$Q<-bC<;GipkX]Ai6NKtq6]AQ)35h>8KCVf;'EdL#VLXMG>?mlB).tM6.Gj/DW?Sm4
0T1[5X`=k$a5`5'8\Q-9#X6Yi&4K#j#T58!-det5o6(E6]AFT\[(bhRWJ1uI6jf=(,onFqCun
6r1crU$!:L=a$!XUDR50"`694]A5WY.lL\"Uepg7XWMtQ7olD2IU;ckcBJ0trq(%4UT=h*n7o
;3NW%2!*BRCE2n6W%F>&=ah@HB<U1+c[,;4TY?gp^To-5In@SR<NhRsh<[\2AI[b[4h2L\sj
D7OjAfs'l/<8Y%<oL$c_QZPGmGlF+,Vpo>!g>E[fXH9lE2c6"a?<,+25IXtR'%Zj(ao7hIB7
;AVS*9oZT<.AB3rAWJGh-Be^P4r/5`3L@J3aLg2j]A]A3-+BtB_On2-f9esW8IG#,WEPNmDWci
\p;Ua[F?AZ<rj<$Z*@FIQk1AIk(".n)gH5MM6o_(E6PG;pPH]A=\HKGl($ogYCB^-Q?5Mp84O
"lZsl2'>"b'E+P?V7kD?"rAZT,MX(rEgK=KBnmF.D?qWVL><>"V`Z7gnJnlD\TCS^\Rr*IoN
FB=`CKl^Y`ajhj'Cshf)*b>f`[m)#_t@ZY1FnAET3?UF.kUBd)XlHBagB:f8-Ii8&N_I84"S
IBqtS?nP9E;:u`,8fSUq[S?5*2;rbck6fM^nRL.=%Y[hd^oEDr/i7rnZ;PFs'ecP%Ii3FU-\
SZQ7#/=RJ+n4YRh)hq^<F6.%GFh'3\UQ]A1PG&;WitL\2NOqi-sb20q0Tj5pYA]ArRRTj&QTk7
l`IDO3&TcXRT+VhVj6;q$Nq`QSIism]A]AjYi30krAk<dSa3\`i\+NCR0fZV$)Y5iX/,asX4$k
6c,_0#WufMAcR`(opC*Pd)$6<5>=O@O<Bs<Dd]ArLUp_t^qKIUL&GWOSr#$hU^&i>q\h1-E^l
"OG?RWE[lkiDoGWQ0cca[(KN2j)o>kDPd<,_^j_i=K+Yhr,X'>^?'g]AD0;,`Bt2Ep-Y_Q\ng
J]A.K/r',"'S549b-^VEmE.jGg[>:3@IW,X@[Qf/bP%_sE$\h:6?"B7c[[()A#2l:#'[KU5YM
)`\0ItP^G]A*5"me/"f\cD:`17OH"I@4567nUOc?@&.eb=Y?1Y7X^ba-\k*3=]AO6\f#p^bbts
DMGSs;Wl+Aj*)8XqW+pnjb.SPjIh:VB,UG8$Yr&3;\1CQ8Wi*"Rljg6]A\"r[?^/9sbF7Enbg
A2cdX)<YJE_+kU\ND$^/>lHM'K>l>&C>k]AC*pA6#1c>_HOjG$SX=<+O^JtN?rU3LdKNOJ0Ro
S>M/i05bBkr/WnAs'T&[?lmu-=G4Tk>pY14>f!lDM9a2=W?`&cCD@>YT^;BaS,HQVfaH;H4:
'rWA_Hhq7MWP/TS$ReIBdNSePCPH8I43r@7gL7S3EFe<qSO\c_**QM7SR`>MP5%3q4]A[2&p!
>#*G9I..f<%rKRktNk*ni=e"h@lQ7/I"mALeh&IQ`f"Y))4ppH`18e#7@cn"Wt/qcuHae\%"
?VYI\5HV?T>3XCsRG(?LWrBSehX08Q5^0.FJ#1T6Z;HPRtS,-mV(^Xn:^htZrgpA?o_sWH&9
J3?M]A&=cPgM899]A#)"E"WFl2%\+qKYRC*,M74P5P@Z/6684kf@]AeGUAaR_!pFi,=OOrd^Rml
)@DW8>H-Lr?94g=[u!:*J2M234'2$57?TU+1?Q[Z0!cFAZep;\S4=M:M>LmZSP.n1s21R<8p
*g5;on.7`69+>lG-/kNLfETT>2`j$;MMddlVKAr\Z"bm9(KSkU$r)S,NZ^=>>IfN=Jn;hjpY
IdMn(*9M#qY7m1B5"p5tt58&4g_CG@MS)7E%m<^R"8JI-/gLl1BT\5`XqB>LeTMrl/%>F]A^@
h\,:3@roA\KD<+>e;e>Y(d>`g)bH"o=6[!\!UX_`<R8s$E!DN3$F278A;Js2ciYY'SOaqrVd
0GTmK^J"i>FOP3?=S\Y]AKa>iXj#FhjG&76e2K&+A_):gCY@Zr);FKtqFjMaIJDB/]A=*1"H-;
foa1_uA9O%@.e7]Au4h8`ND$_*%Br2+e$Sh<]A=FM)p2b59-/lIV.Ib*2u@8TlqLk?2-Fg'E'<
7L81c+jhB1+/*5+$k5l[1DC4/nXg\)KEI-+T/]A5d8!Bi&Gr$dLlV69;]A&`'&#Ps<fOi-,"Gr
1UOZ5;5BT9g7R_OPoHmU)k36s*nXRnY"c*?*"]AbAMn83[Rl49\Dmql17M;lfhTM[+lBheA_o
uioD8)fpG&X^D#@N"/@b5eQ15s>)*Dg!L#bIh#.4DfPgm%qtsC=W3tqLPechcm63a$<mqNXc
^\3G)X%DVHa$lO&M7s8aHNMXPu?$qSE\J;IBAb*_TeXrJ(30MnI-0a[lM]A,+usA0]A<EbdS6N
eQKog)70lul=n2^<9X)G4hVCY"/bH&d<&"CHH[X?3c6%D/Ti!&\o3aR+=XKLi$cckCJ\uQk&
G-s,HQZSf_]A7[@<2]AlWFj"_jDTQCJ[qW6j37YkJ%&9nY+<h@em__^@2n<0tt#S!NV)'KOWM#
#)C'dnu)-6VVSg=sbR_a1n(*XM:UU3Y\:`V5Q28rW:6p^S=>,G]A\7['F"[7K[I'L;X/!%#LB
cWLQaKB`CX-@"o#B#d`L3YM2uhJRV;*T[4lYMas5#UgGd%<a'[%NE#Ld5jMr\\rma=%6'c&L
GD;92_Q;B^M(YD963H:&023EYhM_e)B?Q8j[2A7$cM9j9A*?B%nar%IVX?MgE),i$I"&T":T
0+O/_<J&QYL$82!HsFXQLe,*#.a]Ab`R>rFYh5Y=mn3!Q^i`G<]ArnZ'0#SW*/k<`h"t6^o,dD
PZbj4CgJAfc7L1B+k_*6JB'DO:@_N*FLp/W>i-,'b2pC\V'O.j8#S5PlI0H41u!R\'7HqPjf
"*<l<A(9%A1*07-eIrB(0f9'WB(2=plJD)*Q9nQPn7PLJEN3F8uC$e7"76igV"m;a(mXP>Sp
YhdI7Wlb1UlT+TOnp25=h76JkeNZ4$Q.-P>r5ZMi/?(4hnG=B5`R(\JWs3dI)1;5k[o.kD@H
-8O)rG9F8=1J.]AP=sjol:'H=SJSmt/,[_b?<;:Oo7`T:c:74!<=T_-hTp]A0n>e8I#-QX(<YF
TQ]Ams-H?[>qj]AK<sG+6;XpQMq++`UdSN6FZoq`]ALm'(WIqc\^n@)(F&Hg$<6-Ebk`<_6Q1*i
'[&Fn*]A%Z';1E)O`HMf4gY[<]AS&"t.^32CX5E0mHQ:X&#HKr`C?.ms+%M#:G(cIpP!:@_=a3
2F^SB#YF<?G=pJptb%WMI1lfomfNN"98jAgmf4(bDXhInXKb5D:4Q1ZSM^'%#u<QMnap>PVQ
uP#Rl&+mu1joG_0c1S5aZ@:Rksj\n_c.<DSni@^]A2fMD!-A]AR8RA?O6u]ABnAL;53SZ(eug&Y
>bQs&iZ^uUVTYsa'V8N+XUu'lA6FF]Ag+6jae7J+q84j-a$_`gB./bP1_i(R7o*WSFIp!`0VR
r%1^NG8AA8PHQ%XS5eD7%a,f6C!D)@YRR"[Vf8D:JJH'u$OD0]AIn=UY4`TDat5Q"H"GmKR@j
?s%9>7^)EQ)m;?V\?'Tb9RA6-Vlo-:mTuePY*d[N/I=/@Nt@Sa%/au4k_:bp8a.A?gLfHZTN
Ro!P\:>.=$D*_rc*a9SB*S6ReO49L4`_'ir-Z=2HV-GckI&Z0d3Y2X`[?UK07@\]AjB,l[oC>
\oV4[jF%,;K;I6@oXFaU7m7DQ*^09.HL^jq@CrqMY&;\pOh,"`6Y9OrbgGDh!e*7;o/XeAp_
W)58[t:7B7r?f]Am7<q?]AbdCl/&=P]AjET6RkK?+qg;]A?LKN4@*W+:,K''tWZ%q'T(:ZoYae*E
,m3r!!)l]A;;3HXmlt^^`Y+7J&Z>=tXt!LC&c=]A2UirQeK@U<2q6r)R),/>._Woo,-8&GY/BD
P1%tm[OVD(S*lP#<s^;rCeX9Z"Yja\2C4C;gk:<"fLT'TGg'&@YT_j,7"EGcK%LRY<'<u%89
"]AB+#r0":\O]A.[ba-LWH2nq?'uikWQR2-5S0$q<[<j^KL(5+8-6^<G'GHY,IeoUX-S:1KWRs
6Y\(om+:?,EO\F,>3p]A2Pf@#6h^p[YWChh?A"U$7uV#j[Zm.3?Up3c&t4+F3MYZT"nmea`$H
b,HS@CD#;=s6fCLN,GX39%oP1$$%--qa2/2MULTX>G<uC1+".5%^<rc;P(1b]A7V!m=4Rob3u
p#Qr)@UYNt!"i2k(%'.M!Mg/.#I'oT+,C9"#I/!ZX^iYsQRI^<+b^O5C./%N(QooFU7Ir,Ra
j?$g(%3I;@9X/58._h@Wm"tOrVM;:5@ndj)@90TVgLYgChr*BTbulin,l="lle(_'431C6$l
ScoIYOE^UJQ<=91KW/)ZSJ3*;a=NY'&,M*eCH(p%Ye+8(;M0ALi2G=#>$h<;U9C;?>0(%llQ
JEVA2"r8"jM*iW,H#IX?3VD%p94>C/dV+7Dqb+aQpbkGEt(33X4n_9L^HJe9L7>u)R2?3kUb
(D_2U7US[mjs]A`DY(MtJ?>K3p+;Xje9WGVo([%joO<JO`$FuKn,D9EJ274P%7_i9XC[2Vaa)
3#ba(6rLf[`e.o,QoNTcW^<0n.P$gj&eGQ25'j/)(iBmue@KWrMT=]Aa,Ro:Qm\_W_q;TmO"Y
=d'mb1JPbAddTu<%*aq]A&0(if;l:E9>HHu5kSfn4>e@+@Is_p0QRiLgW^%g_+)et)=/c4)aA
R*+.i,HKl8a[Y'8$KtlS;5+8Y7'(K(_Y\7)m]A@qW=I5,#\OJi/\Ulh6[npP0^Ib0e7[HGHEj
bA0/0SF&k,EjgO-]AIOa-`m6PX=<gXGR+h8Z*Pt7l]A1B9rJIT]AYhGKFk=_l]A*tQU/[%h#RtRn
J*GHB:7_Cj-Z-Ko`:Hp;^*heZe@`V^nd4@>F@+]AIO*KTRoK!'U6Sf4jhmO4*Y7Dml!AI6N<i
#V?5;?@n+8Cg>-&4LOE8q>>n,%LOFB1,Fi_Fm/)3%4[Rs$qZ!*N$FPr&pb6+#IAN<DJ\IEdJ
@5:cOUI5RgkqIq28gQ-Q1<rkejJ=,DjUjj2biW@/:iU$7l&Z(\A?c@ZOCDaWr]AS&Sq*_48cH
l-?ap1'-B%Whl?5(--Wc;%NoiT]A`Ho3&Bi9iN!]A2_0lFYq5@K[fY4OF!Hn*8GWPNj_Ml*4XO
9o_L>'QQ<4Q#XE&MFZMkh.XFLlb'1[n3ONNfSnG5[hh):tf-qH<q02@lh5g'@Cl&_XXbrO>N
h]A0]A%tDP]A-:iNpMA%B%h"e9Cg[(4%:t#3H%mtk;8?DTL!@h9L)aE,4]AQ#GH2`0"?WICUoJtj
e"l(JP:0;d>6??lAoohfII5h*S!-)U*8_rO[-EtNK+<E"<ma;eIT]ApdFGrZ^YefuS/7@rQV(
F<dHPILRQ>EZ6XtC&aC5gZ\LI($"67O(VOCG(qC$oj?dt1DP--9hgNPG`9YP%l9pgq:4.W<]A
MeGNVDggC&M[34f!FVK"sV-BgOeT\=[<Hb\C>6SC#&qXmoP0^\Gp/GU3:EV[JklVYt_q5P&:
7p@8Yi*7;5sX;_PJmp9hi.ZnY`pD>hsYOBKSYC+b;s5V9nF`UQqCppKh-M>Ies7Z+X6H066A
mF-OVr@gK8$"NNQEb[+Q*INSb98mXDoNl9]AX89*s5=)N?Ff+3pHN;G<ZQ$^Z0M:SWBT[:<-#
;nO\;@s?YGM9jdV&+m;Z/D7E.q%9VImaqdDEk3,O'mH_qE@Y='!/Pa3cci%Vi4N1nq(\XN&N
C:(ZnlbiFko\+WYT[H!F$_s+FH9#p&Lk9t=$%K+P#W/QE+E4O9`r;[`,L)#&@>tA/S':n:'=
-m//035>X#Ss:1NXs_@JbpsNHg`OBN>R]A6F=7QfKRlmAarg1i\XqU_hp=PiNO_C`kBI(k)&.
PrD\-@RSU_^T1/PKFC@!kQhR>*jSX^GSjA0h1Qo)W7)PIBW#gJo_T!h1;agd^/RuSecUci]A-
ZF]AZ4bW'uLBqLg!/JM5W$#VqVEVWiFYSCR_(b92emLNE8-X$c^ta2Cn4&&e%//cj!&j>QW;n
JYp%&UVDE72,0PjXEgb"poB2oM/TZS9m+^U(?=>a'SUcOCGYeCIHhBf$!.rE.c;oQa3-fH?=
<gGL+3XZL=Q<6idh`i4cm!=qc2Tt?,\\u9:l5tINp\[r/+>@5%FV=2VBeHhd"0XR]Af#r8uf+
LroLl5UMlN73XJV]A0`&=8Ud>>5BB)h=ugh&5;S)H/s?SY5F[g\U_04u/q.8oFj2Nac2Z!`"l
9%&C-4BUE7n**Y1;!XIT2<`>tNF1d`TGm#aG@619HUd3kr0<-QQjDgQ(s4`;4+XOQ-lRDtF&
WUWnKt+2CnPH)<kFr`#,0>nLfZVrRpdmh.APLr12f@OX$WEo!e=Vff@L#;Y1G@-c@nu@iBYJ
pU\?Wq>S2X2CVEN<>Ns*mT@Kf?9=TLUcS9i7EXKL<CrX.nZDLZG(g"`r"1V"7$Hp42!CUTLo
M9\RYI+/d0gO<?`\i@r;V;6"d.7UU)]A:jH4K'$GOU[Kp9F/'E_?J)_t65m`Oh>WP.d,(1p`)
Q"]AhgaD#qY)Op'`YKRZW6a*kH:,lLE7=eheH`c\u.3)WnL1#XCBbD3Hd>nnBbZl6i1BJeH;L
KUYLFl>%%$A(_6J:=o!t+htOq[FQ(F8Yjn>Z*6JLgc_*#9lY?Yo[b9pZ*b@9MgT.q!VE#[)'
r(cqIq&)FllTip)D%l/0;c*8T(i'KA;j=T,7F5D-XS>_QV+9)kTB_LLWQHiDh>M5R@o!AnPS
/"3299+&:^/i7RaaRV]AG$<AV]AC0YHLUigbBj'fL>WinrYoBjbckh+kHu+qcHE8$MCc8a.9&e
WJcfWh!5V,hH=u:>-qlkp_`VBX!X0(*$W@;qNpHU^D3Z73!t<;5%Sam2b#?iX<g9WBM;sfNn
t@LKNb?*^GR/2h5^PtBG@UZW;\I+:q%Q0KSD"?-'5aVWN>sE-lK^;`gtZ_cD.6?O:&c7%<,o
K]AO\Gs_Gr2/BR^)6p5!:sLXtLD%e'3uJj_<c&.^UMX8Qro/0k1V#b1$#@M-JR&(s?YScJsN!
81jeab.]Ad6?#LPjfo!K!J"HgNK6&1/J+:WeKeOrZl).WB<q6@FdmD<N"M/JR-'2(e.g3(/Qc
kW6kUU`$."@&4QoVFa9c5[*5us!J&0@)4.YT,,m.6N!et9V!aM3A!!-,<$^'")6OM+5iK,kF
f0XG-L&M]Ai/#+bn"@UY9kF#eA)g-\?_L6Y+SUim5>e1$Q5QF.?mhe-gfZDBkm(#6+;g9uK0c
YmfCIBQQ@Yq,:olI:$JJUC!9#RnQdQLN-F/$bQ[sF24OXg$!NanU/'HYm<r"r$BeE_">(@$$
jQiq)jiL>WaDl+p;UccS1l@Va(?'NcK%]AC`W=N=@h.*^'rGiPd6/PObq9*g%ri8-J)6/iZTN
/"!E;'dR*&n.G[LQl`,/6GUC_"/LTgC+rP+]AaeB*-:^A1*=:M]Af:Q#QBT:Y[JoQ.GBZE6>0h
.1Z2S/gF7A8d]A#%Uq4*fMX2C^^MYp4/9B@&hEh8R]ANN@loI+oKN7SrINMPU4Mm.rc4kgR=Ci
&%qbC6,e!cU*pm=rf81)d6hZr*61/5S\V#L>Bo,!)U:uC.gL.C:4LE=(a3D6Bi0Y.O58Y*oc
Ybo?uTI"!9s:2^!t8SJ*=:A\(".UV/?5ZA'1E,S[L81)u&!LmE)ZhS=$n/7t\P&DiOR+AB>I
io=mg&o3^lNUo?1j!jOBXFkr@HVa@Jo7B:emFQuKrM'ds@pET=$qn@Ct)#8ZDc)Iae,mZsId
%=dts/[/]Aag;`5F)-!5PF$H^1)kre0YBpSeb,,Mn<K*>bhRP<f%i>nn*d.!&*mB9W/L:_(,>
*11WiLWlT[17U"n*AUrE_-Nj&(_:Z+^d'HAR]A2N#C9ae73"?KB9c)9ncsdtYLm_V8@)s"H#]A
VHT4M%A#N5-]AToE^8Y1PHmQ'POTK0<`Qpk*+n34V%3W@'HAr'lP]AkK$BG&-Y..I8BAQVY0VM
]AdKR$mg&BE:fI4m2eT37?`*#+FG9jl)]ALGloYdJHBBB:BfU;aTn9XWg.]A%F=M:QOJ+[J1#i>
381uGd7`EG"i;(-=OGs#m01,_`<sYIp:aspXFqAI9&ZbP1#3=H@OXsHgX:k]AF/_9bV"^@r1A
:&hpq`UK&Lm@bs)MCV1YrHD_K8:R\s'eF:3iVr'UhZ=MJD`r%r0\ErVkIYr&M3@-39;j8f=O
+LJH=dLlBbf#-J1Q2%l'!sIc6Lm!!bi(i)ss$nEd=IO]A"-dJ2n$N34ca)"n#ur9qG,]A!/2V?
CDpAM=Y@3Z'bTs92G]A%+=d=Q!kl?GE?9$_H+@`FEnS-tW/?$,h(%:rB0&ep.[ENV1_ais_&^
u=n]A;Q>C>T::(4@Eb397INFS9:.<h$\"8QtKBIfF?S7-V%pMi5^24Lq!in-`F)EqWFK3(B-L
T39tB<'03nY>F#GcU[$CUVWd>S#L0P:lK/3LnV)c5%0/Sc(9X/mMXa5g:[sCZBBqPFUNG2Dj
V/\P?$cLQ)E+788$)6\=dKW90?O0UTC)AlqURp2dQD&23JKD;ClF]AY:<bs;#>a=!c?sqb_6P
;sMRG;8n"*_/^hLRS".rru8l,>embB?p)1?Hj\4'5KSDLUN9P@q6S?LS#d2L!/ISns\d\ouF
f-g(t[s"qr/:)[Mc2=r!G.0=bT>]AWlSrM(I*45T^DpG/`]A*",BY;ZH]AIQWB*oso8)RoqZG=B
N6!_Y!BKm1rrakf]AYtg22@pNHMk[hb"b^#8h%.@i&.6-.@n)g2j]AX:6oM.2NAuJrd?Vlc`au
#hmdh$WCti(=#Yq_n8/hUDl'I(1%>$0kbVb29V&orN$U"Rl)KpGk)]A&!5ITq%N.)[%GkJBI_
GLOj@#Nj,/83<=45>a'h\_E$GN0G_C#"e=mnZMg!/DZLn>p-C5msN2NC35n-uKP'osk(GDA3
b8]ASk+QNRZ%SL,:/"h;.Jk5gJ3&7M3<^BmC,Ci!b,/I)3W>JX]A'EK?4ut>/Z/!R'T*"HNlps
_TUq:-Gkg+$c)QZl(3If3%M:Q*djr6;Qh5Og??\#:n(8V[lcXKMs=s0)InGM%MF,5@tDTUMi
nF):"Z`S'DTH,4Zj+2SZT[P&/fPG;<6A^PNm"2"PO;=-]AX$ka!-=c5C+RFBOf*ONGVSEbq;0
m&+G/J9M_rekn>T&eHi7+R139"-<sfARU2/RJgH"JXT"=#:#;5qqO[b9^^h%Yn42isCkk-n%
GIqg=UVf#fR8gOF9h$ker:U[hBW&-Bg:eoq`m,F#N.[EkF/JiK$3Dp6$B#H\"1;h.6uNqqk=
)&%cFi#VO9b1ZlN0Pm\Bp<,OcMZ":P!ZHZ+<s9ikY7oSiMaX+@Pqoi5!Q#C&<"DE6ke?+VTe
Y+l44&4H"!'>@edPFL)5Jj1,rj$S!d.c1B:jqBZs>K]ApE2+?qTrE6Y(n:h(m/Nrn%(42A+L]A
oT9H9ef/[!G+k3Lqc)5@!%G=:bp/:IN;mU?^D>r5$KF.n,s&=)OAedu`=7*g,@9=Hq,VC+sc
cN?H5:G2]ACJ?6ucr/oQdieIEe+YH)RVRF^0DaYX0F%Q_/hHUJ8mWRT/fUfk%tp\.%ed!NTDj
Es.o\hC+mMn1a7Q@60DDQl%+_MN'9rZ"`61&kfiDlVmK?(<LPfB7bN#!I(3?tROiZG!*1Lpk
BsMn?SQ7<`PHR%FrLo)t#?K_Acf<9U_Efd#%C#jkJ,6ArWc<*bOR%r@G8ir(;(4+>S6]A_#NK
bD,='nK)0U9L2?Ko@8n[OhU!FrFaSG?]A$+?N4L&m;8mnB54QFH7otUu3*^`uq>Jj-_cmD6^"
4*f&*IA.@"^_nb..]AHNip#rM_^M:OuefMCCe^fHMnY:3F-ea@YX3M4'kuD=Gs6mHUOee)@g?
EGU_nC5M"2BO#"d)p#VN@VFL-f=C"WPV>N)!oL%?T*GScQT;5phIQf"DY<h/K-^(GH5enV;N
@DQ^.gk%cc-15UA-$jcY,Z3PB3"3;[sCX2BJhcXU>`\eFr/hPg^Tc-"CoJ;J<b23-%1A%E9>
t#QHuAG0iDd'[S!s%MnRohXZpX=[;60t!SE7niq$l5c(AbQi5U@`[khu9mgc&f<gGNE$1\,;
7lB;'mjcbIRPn2n%5&P"AmtD*<3?;lmgqmnM,DUteW_1lrb]AKY6Q":e9TIllml#mK6<d.jlM
U?8NV\0!VJJ+A=IapOIemXb/2ig2<=?q5Z$deRDn[WbckWnfK$(CheNX/cIpune(c"XZ/X(+
[r`LI3H2TM4E-h\Ge0M3HSmZlkXJ*_HFMT(D8q<=SdhiMC!7JY[IPMp3=KK5ua5^dLOWtj4%
Qk@I:jQIUmL7b="AP8&o3jbp6?SG5it?@<WiHfO8FNh%E.94iW="TUe?fMgVoOYMo'mh6P%g
epV&gKV%S9,<i%eVU(OuhZHs]A(^Js.S!+?W]A$G9)?`:_kUtpq^HD$[30dm,.Fd.8PTnSe+[R
F!Zmr,u<L2pUnDg7p\Z*rf5g$Xit.+fTg#E&s-",&sjr"#6Qb(XAF5&!n.%!G&S]A9l,.3QWM
,'*5(Ogc\(7C?FeLM.a!W!#:<aGR2OkA=L#ag.I4)[M^i]A-MNR;D.K*ST1f_+ium`+c>=dW!
QiM1N?PEsgP-h7<*ep[=KAO!=HWgpBSk,%O(&Y3iH?IT,&KI:9^XS'R7bF,kmQM-,-2_@g#*
:QEan.ui>aBnZKlLH&,]Am''#31[cEKK-7S1k+&Lqs_[k/WKJhm@U:CRh5UkXjmN5%qJ`:Ke$
GgFrs\IY7T0p4'5;U0H-Ken9<*=\2r)(MI1ALh#opH(cT2q\[F><9\'$`^u^")Q`T(eK[*@r
&[ifb+OaZY5+\kR[(Ef,nl9DJJ!#`/Q]A[1JnV'%CnbWMS>O_)\W"4i'g%>I1ps.a*GAD(mI?
*dQbM0'P52H0dRB/?aV5bN[N@0Q./+7<arPq;lhH'09Rd@h7H3_Lj%V&VOVW(X>DqO:O5hcd
&0rX0`o#u/"kK=QtNgHsn&nBaLEY#"$`ZEZ:Z^f<FLg,aM9Dn?s?in;FbX1lGCiG_)2_Mde1
ZVK^g#18.<sb->miOj=fs=u&>4o_oT\=]AbK"IUCl+#sX@J%%q^eZp\fKfL:R1(I\gI`2,0`[
Dic.>?$kNbh35nar(1)XK/XV6G4#Thq++nlI[(k<("S(gilQW7%lXgGYKOL2Q0VFImqS<:bj
n`!U[X1&cQ.=Gap&6@UJ=f%7m@J$2@H$.:3Pkf6E"mO4!_GTAdLDcJeM.$<V$I#Aqgq[kV`.
m4nDjC2QaRQ7hds:]AtZD6hgTHQ/p3'&h>mu%?o;6r@1fP4i;nHi;s4QWTC;Ed;SBAW\$DS?-
#57UemM?(H^3!e6@)Li,d,:V@t:Z^BElp$\G5<qQ$9U0`'A?3>r&,/6/gka`+qYcbo`F&B__
;.uNNgOS6T4GC)m6[fL>EQ2=677#G_g9tk5GFJEqsSQr#Od3A&3A7@Uslki5P@[o:1D''9;<
JL0,#o3bkDA#^m3<[7#9hGT=13^nm"%(0/Jm#HEH$32GJH\_o_rIDMbIo5B4Wa'^dT^L#F%I
LBD$@@TNO7c..VTE20R`-Ga0-MC#0fI8M>25WT/>*jH9#C'+q7+U5sfM\!ThRRYhqUu5)d<N
b>*]Au[Oj`L57EZi\e[^#TC3fOJO@XTe!?2=Z"CWTcgF)Y(hIpiJpB0=EH`B4trCKaaA.lk8#
NHJW6*7dr%_r/ji.\Cis7H?374dG>bVlmBFe7)/_1O`o\JDs(kHYC7Y%gBos.m`\s^8+&O6@
U)o)^5G-Pc"@PDB.#Hg%-h)_DY3&!=NYE'fPtI(`6WXbK/J-J?#ZZY/YAW*9,eK2hmbPBio8
\5Yr?gr?7Ve63f`+2YGu:*RiaPAjBmto@or]A&",l\)grb53K/3g\NB[4=*&&I@f2L-7JG5]A4
,&dL0;:Lq05N]A)_*d@,D*-^-]A_U^p'QTZq?+s[r)]An%t`<?lD6A3f_CEh:Q,Cj>?YI(6n'BZ
:Y<m>?d'=,q5O(o&_:J'n8!IpP9jY>f3t5:ET#AcB+Lr'RLk\]AfqLD-ZJis!E?^>!pD]AGond
#Qd_Zh?F=\F'^h,5P'up#0)j]ADM`WqY]A2-MooYum@B44+6e]A<V/P>=!p7<Vqn^Ta?tVJ<c0/
Am>rh20.q@iV*cNY)+Xj!rqc(Tq5E1hP[<d@!2`R)Q>a2(ZPFFU'\8\QGUdW-LsJ0/Ms#Zsh
crFRCFKZOUZ93rZ/a*t>?"hS!*a/l,rGn><8pW['p\g-jMHUr%eD!+\;D*WmMde[IC91Y+<@
+Ar6qlTj3PdAqlfD`Qe1`@<M.@r!D8VGNTp9LK^BYFVe5Me&6!M"iO`0t8//QLHlZn7?TT_"
>GT0c`#pbpU")%PlG?<bS\+n<7LVeXIACDA!Lc?ZX>G[aX/@%K`YclurtMNjmdL#Ui.d@tf(
lhZhKG&^)BbVN.RBLMKPJE^Op]A/-q+\*m_]A_p0Dt9p\=/f8*ppl]A>QA'b'5b3[nNj=/XZVRr
=e:F%h;f\=FcUkZYJp?/U,hFRK4iZ'T3A*?#"86,"K7FIVmf;Gj/^:!oJdiFrt\n?gie?W\f
.V7u=auS%upaiO+:uYi,Vj!CQ$sUD3dHe%-5]AdY=b?-G\*)_>\)lWuIefX]AQm;e]AY<EoA-jh
W!N'FaK'":\1DiE%V?q;;>'cZ/U/(9EF6e#^%-h9n4mh^p.-V5r92(,0:2HX3fTjE!#O_dGF
9EN*^%&*$dLouN/@bf4rhW!rEc\m<=iTE!P;lG2[$UXMdV[>/o+M)mXeGOFo;k\!nWX\XL/,
R=_j1u,R1$sBS?dCdj1+!*5S"Q"mc`a:R3shP>Luj4=>6Bn(C91,c'3q,GNHdW[k">W&+QD\
b<MdNHOlaC\D+:k9QGT`7U)kHX=qiMN8cK@3D2$eH[[nce93H8*`9U-f#.orDbH6Rfl[GO_K
hcJsskn7'pg$qEG/%&r!BNe+F;e6qN6<'X-)-s,/E<m#'/-I)Wjq$U6,\CeIHtI/UQpoO+IX
CViU_N(-QJ?H8G$jTQr`P^og+r,Bp'*lb[Xh0C?^KS]AleeMF<(Ejb%n*\#)D[FqhDq0,BL"^
@#df^@e$Ct<^FqXY$,;k+RlWqpXfNo1udT1F&g'l8^k9#H$8N)M8ag^Ms3fU@5b:$sKH-XE-
3-*!hlEK,usqo,g'pL+<+R]AdnVcF$RPmpf^.d8Okb&.=<mMmeD9H^!M^kiP#`bT>\c2[*]Af-
4Mg4\9tf9-QMfQ\[=;.ZS7;lMNp:87?!aFp>gMgh]A-jFeq?Cp]A?gr7f86o,l3sqK;8tU[QOp
NCfM#]At_ti9pk+r4C9(e*%:;+Z4UJf"R&^7J!I&L1_&lTY&h94-Ce'gDJ7@#p#%*NNUr$bnY
3aG>ic\?m:eB!iLIO:Zgq(r@[^bk61D1;=3i)\-h4kg>Q4c>C[Qg!>Sm>dLRs-7/:@\7;94.
t1Afe@dqR`,>Q!kg9&&W[k2>>@qkU$W_l'K#p'lE'kqprK/u9r'+j]A5JQ,7$_?SORfq;?6Y"
A1[nDpq(=tP3IZ(B&GpuH6F#,mT=AXA+.oonC$Edq3*08Od/!+/(>=EQ3tBf#.\Qt`JHhhrB
r'>#&V@/\`3*!2'&DY<;N\S0Y7(Jf9j>rD<^8\I6?*gGW"]A`9Ma#r2_S"j9NrE7;Qq@&k=Vf
NX<#@s&YBf<31^"+mgdS=m^L#a>O3uB0dE+##W6-1:(Y-LK/P@QO]AB,5U%]A1.9'd]A68JA0'8
;u72i/N"ah51=5!N92\)`ljVLr3%Ijl.&LANY48R`A;5t[pI:2hRX&5pMqJI+<k4U(5uI9/5
'N83+#.go&G3?84-,K>]A+*g(Xuqkmf:pgf]A3ZT\<^jEqY.Qk?/X&tJb'eO8T\[>PTi_>l!Uq
Wn:1>>i[&grRe=p9@eaPaX;G22)\)]A+eBtqF0pTl_'8%f[$.]AA>63i/Np_*HR18aJ+"6FhQH
Hnp_7)0*Ik(>5@+m\hK,Z6mcT]A13N*E'gI[tuL/Y`gqaP!fim83YJd,XkJQSN^j=DlSd<LX@
H3UT<j%g%]Aurk2TjalT.&h.s'@Zl'*\5Z/i6dVnQrE/d0TQQ,arLYJ%s"mXDD%B]AK!mO7.DJ
jJ1RMEN9pho7bo:f<3IB$aYK.RY"n&3MUqC2=PhCoh59)2U26/s"*DQ%h:l(cfuB#;-nHTIT
Ik7![I1q=Ue]Ai8n5#MB,CDU8T6S6r$U'kasWt*)Oqa)RUn1.*C`hKg`B@m^Nj<o,;q#+dt>C
FO>=cS7@nG7;R\em6S5V@R=;)WXh(M<r4]AG<;l6N(;0>a_Dsi%t_Oe@#769#To5:J/SDB*L@
15]AZTr6nmY&4%/;S_6G]A1@ZhE>T5RS+0Q0*6D&&^.mf+M[;<NQc@J0$a]A-Eo6kue]Ah5l"X`5
Ac9edrCQQ!XcU,f884*JT0Ll[RGql$4+_9+;\G"2:A(M`>DeLKu!-7fG[Ee`V%RR[s3GgE%<
Ugr2kiJ;p]Aj&H7QkuSB;L>*+O#;+D0_CSc'SPI.Bdu/@MaXS=!;$T2eD#s&r*NIOP'aq9pni
C/8=OdtiCNBk/$KG1"T,]Ac/AH.M\';=tKc1Y4J^]A.GW%$Lan7nU-RT"L9I*nNO%"T*jN8NGK
4e'T/4#<`qVW`KCI]ATTaF?_,k]A[[MjT?(pD=k#-C+2)f0I31bL9e"%7B7QEV'8*\EuV!b1fW
Dhb<3$3ZYjXr`G'/4R!!=o11/=Nns1R#<dD3#`=`sR)(9Fh[UXT5J]AjmGV"<'n8JEOf>OiX)
Or5KQo^m$_s;)mP.O=)88$&ZO6;qEQ`(!2H9Lp>9._`ZYI77&lZ>YNE5"+f7c$cpR,9Gr^J5
Zo,So8X^=3CaaDb$e$<.o7^K9Q)i;i-A#k)@IIIn^p%Ni!o)VnnRO\Kq4KF$=V9<M0rY2VP?
q'0(?[*t)6qrF1#4fgWRX3qU#L<9S5F#]AoCtGG'+Rl\pDWfP-Hm"3bAnqGR:a1F^.NTD*SYL
c)FP?Q`TB9,/Bd.LH96X]A70"j,W8`RlIt&,/XCYVqW]A499i?;B;#f-Ff=D^ahaP]A'o:pZJse
P@f(OrHC.kS\]Ae.!`!F>:):>los[k.Hde69H&bR4J5@R\,K29F3pKb&6Ye(E]APRWcNU;3L9I
B.9$Z2%\g_U0BRD-qj4/pK]A6%5d<"56m_b&`6P<g&ADRZ_,?223cBWGJImHh#m?('CId,r,n
G]A.5c%$gOl(+f5ia+c1OOp*@Q#P!A01/&fnYr[i_D!ZrSD6r8]A,)B<QmC?t"AfE]A55RK)>C*
NSXXkH?U_O1ILcA)g;$YMm8GdGh\6NCZ1UW>A+E*/SfX)dq5mO<<HnjdQO&+':?g-FM?]Ast)
)207A!W3S<sU&qm]Amq:lL=P6kJ_oal"$GT.EFeN)rn=%NB2gcK.oDpe0Fno.GEBLs$;PoCmD
+u@2IR3S;HIB)>?u!W=NnI'hd<dir?m,b@jA,uSH]AF&oE,QQR@id9[n4\N4"]AG!N\o_IVjh#
)h8tgP\8`%B5^)N0=Ardu^]A@ZqRN$>Hp1b*m6?("@?o]AX'27\jFApUInNj6Tb#'(2_,e,"1.
NdQs\=t;+O@<?upZ)sWOF45<fTDep>Eo@q+^W^W1C^+k&G*i>;Xo\PKQ99Q'G(m39X!rWBh2
;B5`c\j!rDt-Vbd!mgS/T.FO`?ii<kB^Mh=;#!VCET`IW]Arl5G'fe.ebXF[Ti"?K<VfifRk*
!Q^S@<6;0/,jd%S(EBLtf6k'-p>]AH^VAqjO66puZg;MG0GfCTT+R!T[''O-e@+OmH9!#'I@`
rOhRQ/+@V;8%4e/W))3S2'Mi+@$#N^`hVDeU-r!`lC!i"KYlog8)s0,ar)+3_B`<!fDMlOs"
utUr6`"L/hb%"O5lr4JTCpes?fHXu;#I8eRp`E)=T[.G/)R$#)P#fLh>E'j*teEKS'XMg3G0
7h5U-*LidrImCuU*)0dr6Bm:+qT';NThXJSTCI-Zd(Z9\P!7YsY?/]A!Et'W`)37kkG;pEF?F
+0#=oI@f39JYa1h:t\@@KDhW4KPj:OYpdaTa#).S^uVodUT#d#?Sd0_?=;AR(O8MV6+GZ0dM
g#<PLU>Y?\5TK%lW-@L;4(pZJW3aFO-Q#soD.oBeK026p:rIT6()U<9;Sm<gAqm"f=Do$)k\
<@a*`[]Am#UgnAt-++9D3OU7*L_>p"ECo"L;)gAVjl-fGb.XRbMC#r06=:>KJ";1?YLmIFKEp
?moeC=fj4#jHK6_UfmCF,X;V3PjAf6l=P![I%$Qn\X=d_a=lQK3;+'m.OAkK2!lMX8L1]Ah7i
G5NUs_A\*]ASa[g!"&&Uijua,NRK$PoOBroWGYY8`a\8m1DHq^@S)^CG63Asn<HYCrUKCT3*;
o[Kk?:0Y)Mq9[EuZk,nD^GQ#EIsgO?6@=CTH,a5'?g>6c]AauS"XSm$/KeR0^mbOY9LJ1T0Jd
Jh28kq6"tNg]AhocXqF$B<otpV(Q+O^nnIKKi;Nga)T'^G(Hgi>0nmY]A;.g"=[<kNV:Z_0/S-
3O#6Nmh%SK/_r38c)hRj\6$N@>0)^Fm7uc'(Pi4"B%+DK[NNH$]A`!8frVk+Z1brRe[b^bS;@
W:=oV'qY3,_s1%IeD"in+2rRoP6jL6f7m8=V^aU*h^k;De4OmQeU"5+s^na)lu7<LRd;i^.-
KfR.,)A;dKk)C9&:)[`_h9$FmQ%9T6RnYof:[LN4?je=lpB9'mKAG9jgkne`4f(g;CKCo`9r
lb0Wh61mO:=]A!Y7Kqd\'4S<UmPBVN1l\InpQNFDS1o]A3G^cnZGcPN)%4Y]A5(-`!_[W/j]A3Z"
$3r;=&fcEsm6sP)1S@#-[a0!Ah:ibRPX'n"\ini!o/U+^'L13lf[rFaod4qV(jcP^jYu6!RV
[up_,'$F\h<.,[i.^367iSS4j(1<_5(,hPqWkr"9cOS*R]AAVK%/Ae6M50_?.u^NY1$#q4/cT
qX5"n_"`,VnY#oKYpL4>`iFX39a40kMX`UIE0]A=DI[YY;7!Dfs&>EbrHO?'Q4k<'5=n&DMt)
pq-+764jpGn??">Qt1kGQhM%8QBPam3h^mV6'9^j<OJFEhBTt@+V-Hns(i34:&Vh_-(#V^Vq
Sug`1>Mg3[:)tH7dCf`Ai9>8sr>J3H2$6Qkt8ln8%ViB01'eFG/\r18A=GOY3M464uE^(Pf&
=+r1BXLWl39%PjoZb(m$ioQ+'?1G@Nj?df^sqM?V?1E9;u=F5Q;*7i?Ge6<7h*)m?3=iKAaU
?k%QVoq`?S5igcmtPLClHV=F"8>/Xk6l!QX,V.[\IEp59BFs!Sk8NL-Up1M_ZKNGE_^S;Nj'
UpY2"?+p?3rT'@Q''*]A=UB')aDj&?3XZm+UDJhe&7[8DTt7:Be4@J?=J2<nfl"ZAI#>pJb6_
iGg,%U+LY@H"-s$:0eO_h#FH!)d>Z1g^Unh5OKLh`r[SY"h;\`Gj(!>Ai9Wh@@3qpIX6%G5`
47Fk0M7_lB0&R6.]AR6'9dg^Isl/oR:XoY:7n/cG4t_G1EU#.^0Ws#laN#rh&Z94nBqnLgqXk
4`.I[QBp6Y=WAj#8]AGUg6r@uQ$V6/>e-gD%/C=A9S:O5XOn%?`g4I$U-aYI15<E?$q]ARRN%.
>cZjAD0X[iCj!kRoFS-D=M[BC-_LX-a%tE[NOIn?ns/O*45CMXs.qKhR%4KN:_goY\k$!+.+
g]AQsUHcW9R<@4A1&HEhQpeGgNg2#3]AC$1oBU%NTf*P]AdcV$%N%Q3QCAj`Pg?C?"8K)S$h,i!
4["?L`PhKA@jJAAcAVA"V'-4+ik\MmH-2$)=oVEfHMadr+5/nY`po<o<nuB?/*#1?5M'[SOj
WN`h.NI7G=g;=4aotgr*g>]A1O8T2g*M)DcaKPVXiXD2h*%N*I2%tb)G!-e<-DZV09`N3'a6N
C%<)6_@o)]A[C]A78jNP+cS7]AUJ*Zp!tng.P:]AAA2fZ$a^I'Q37c:r*EKL:-#JG'7]A@M'lElp9
*M5sqTg&)jEN[;IUkn:U"b_"F\Oscn")N4l<`67H'SE<s1DpbAKPSdjs7ur3ih,jS"PeVO*[
Wp50i%s:D_@kI[;4u79Y*NSORmdHPtF'Fqe<;Q#-qE[FHGe)%O2MmYn$#VpsQo6lnCYq)t(4
U\*YhLcQ?fT0)ke?"aCX&T-)D'n=M@-g/$SNt&Eg/6=FW#0*uE<=UI_7j"S_<cJjQBHcP"dk
j,Eb,JeO:JXUWbr\QhWdXEUMNQ%;LK??upOM24n("Y\A!pWDYEIM6a,6#:UD)9ZKT9JAOUt]A
Ci;9R&dfk$#l(WY*r)X2J>CK.n6>5S6P2'p!"\Ea$$<R#)L:k"C-NS-F<T+6L(!Ss0c)4uKJ
"8Do)e08M7Ch\_j#e]AaHJ?:=gmpc?)-lH*K$hS/Fs3:5P@(uQMCimYrIP5JjSb4[VL8]A=J_c
2C1OQ0o?Jd*);;ScsM=gfsHAp_F97mBZQ.Dg;V5JWr79kF#E*BF,B831`As`k>4r\N3rqb(E
Q`.3>P<'lghY^B0bleGJdOA7WkTY5i#t*u2g`4f.-gm6iXo5>Ic/*7-]AmFSg_)P.2,$FGTj0
h2O':dV8S%!T'h1TQR?"gVt=Y/4j`8KmE@F[qn-e>kLl98)$Ft;-F6!(Q+!aN0A>r&b+PeN3
o,I1a--D3e.#80e]A09594/_IsA9:V)uJZ>os%2nXf7?2=dJlkanWrI=NR@H<g.<M<\+#q>pC
Y\^Q`gpE5\*fPl]Au34D4,&O4`mJql=]ARY>Vj5Bi&u0iC%o.29ltS!K0i]A&W>$n*\B%:Q"Okf
_-dd#..an-Se@iFW7X_5e<*bRHq0brU(pJn;>4?bLh5))=t"t@J@eKUOuWRi,u^V@p;Q(/S(
79a37&hpo[d=^#>\$b,:kXpA8po42r,b;aRT819F!6^?>k_4aBe0SG(DSSFA]Ab!8;,_EO9qN
QN2:0(nN;0>GOMtPV;nPMK_=[V>pFUMh+$=oCfTP!91BV-E,@`9F\iHTUfD+BY"SaT:nF+'(
s4*R6VgG]As.d.r&(E0dVE'PI"7]A@rE*I6AGU\EP"P,FO1^MBuV8`dM[64e#'6E(SRN#)9Q\k
-[FDV)`@[e9MT5+4*?>7qFO+hNV'-=GXR??HmO]AZ88rpN/JGZ?J-Is[cke2iL;glmXs[rUai
o9Fg@HM[^Y0q\"VL=+VUGmc5KJkE/68f$4F:jS^iOimN/2T*SJPU)3G^iZ,o^E<TWFQ..f)`
l(7-\;=q(Q$QMP$TU.=(T>#A;8+<^c;JD\+o`J+9d7;:c'&USpqI'=r&a-3h]A,TC7QHAaIMm
\=V`$\WpTA&ep4*tKTQ9GtoEGP9eR^T0:RU6D#ZTfo3^H-VIbi[Tbd;N[6]A9oBkcS6a=e@gW
CCl3(P2.%Ic.XUbP&e,NA%&JuZXe[kthr_io%.hWoWR'o&BKKp!)/ur;3MSg89/,S;Ifhs(a
J<u&f+:$K@+s?aV7M%bo"II<KIRZAUshL!pi;%n@,J3Nhd<!YYT:)glD.iN/LIqf",P5/(+4
MMp"JZu385.-.d:mg6>l"C1*@1:kt5+&G#8U5rnTQUML2L\Gp65dlC7m3_9HqtUFVOt:p7b[
o6#3PJcs6KGupu<fSNFBp?$3>Dda&Z9UFX)Ec`?/%iKMqrjpj>F%s(JDgVFKeBJLXOX[Qk8=
#"l\uMfgX\LFMDW-h$F8!n3:sir/8STu"A/KZUq&Le;n_TOU@a(Y9-a*+P10JrK>>6@]Af;ek
rQ5f=u/X5qTRFI=;BP1e3,<QaXqF8\JPY+!]A_Y!=-#E7DgcZI]Ac:3Cd7<@maUa>>kho/nh0$
>ri$ES]A^u]A'R`TMeKUZPk*IUC!U4[46uZFF_4md[Q2Cd6ZXHVb^6UOaTeFQ.uG1[s-1US7b8
ZWg\m`YrmD;sThFdhSe)\uE6g>H[EZk<7sm=i@uZZ"-n5/bFB[<E2q_544#hI9B6\YHbDDm(
['[i1I3,FarTT-G)ifY0:0Br_3E7Pqo(f_5PRMF\G-Sd4-^6C:_DGg$U":f/SI"i?i7IPlR(
GD;8IU/]ARJNp%U@L<%+ni_C@1p>;_)>Jp6<Ff2gf5ga_&Q3oVhH*f<;R]AX/If.Uh9aMA1$q*
:TpcV&R\0MC2q)G*p+SPo-L+!Zfm\c]A`kgGCn-m1OlhF&%B4Db?"Q'`s7E8!lWuY=-3_bb(n
n7CqG^rlHeJ.+HQu^f_oiI8Vd2*FtcHk)&WqU"gVucBZ'o+'-(TJ?*--\)eZ!R1Sh:VSa;4&
1jYF#U%aa&7WPjK.c8kM>-#`kc)k.9]A$D6+Wt^A/"Qq^-rn`G3:-NpOIW<'iD.I(W6J$O_id
RsuL.AG4tGQ9-M]ABd`^WY;PFPTnpV96YO*u5"Q=l*-$Nr+aKj_lg\DK#KsdIF+Z,\?H4DtUN
86hf+2gIoj6/79$YO;54!V[@OCb[02A'6ndca$_s+DarmB;ndLHc#2u_7>]Apu8#?S':=SSTW
bQcp<t>AsQ=[CU%6-HQ(p'tPgJfY<fAlS:;LJ+l5.'jLKRB\QSJF]AkWD-.L`Rb"IV0Y?-n]Af
[[U2!%q&]A*Q9'+Usl)!^>4eY&*[/Gqlq*ZU.!e*:HEoafF1S\$-V#1+Q?uR[^'N(c:_ehV[S
H8K'1usQk4h,Y\oOM'ljVjcj#D2SbgDm`!a9KI<;:'PuZ:"UZQ=Ub5NR?_!+#SAgO']A74ZaO
:cL0rM"20=kdEu$,]A4n]AAM,\SI?\h),='T[DWgj$R<$-mJJ-RZ4SXne1='sAb(7p[F9ZZ@#s
$MF7X\H^5EEWnZN=k?0<AYGcLb:,Y8s7PW=Pf`qlh.IB:\m(]APjI%54_a41$,G1]A6!qQj-OE
,*h0ID`fg-d,LJSPPrcp8!Kg(eWVuF,prK0BOhg3J?C)oT77N`bFYri1.R9TO]A_R%tOl*"&B
!HmtnC(e_O>ijDEcg122/)]A<>&Sb=N0+0a$G\)+7U[t>^'tb-kn1,:cG6fH2Yijfo[JXf$6c
0meB:La(p!-UI`I>9CAR@upmnlKFYPMkV34`PpYO1+#F_XPXgSO'_%]AJ#,dFs*VPguD<d&CL
fK_4"0-juU7J9HrAZ7BD2L,Aq9)H&N!>q]A]AObM=J"$T1e8=`OaS$L>rS-BHk(i51^).M$FBh
-tFD-_-K@j))^P!25*<ub)<TP,#>KC8.\[9LocYVoJIqD27XH^ifc"nho1Ipe<Q<\=@:F<4Z
l2q>?&$Km+3.ULr;5,Ya&+!a9:@GSkP35]AG]A;Z]A`[Fb,(Fs'fs#D\-L.C"VY_5[u#!4Vb!M7
"eZb<o[18J>$>J9KQYlLaREnQPCq?E%Ee:@%\\RR1T+s.m@!S9`IJ(KWA-V=!2:LXR!Gf4_W
#V6UFVkru/kn&@u)6o;U^16Y!$`U=PhA`Z*%)i[MC0Xh$*iY]A@6G7AP.sr6\\0Y/>o4,[OFc
p'*,(.3Qe48+gBYJD\ES4.<4H778ia&s;*+nRp70@"7=%AF84el]AnNO@erXp$arh=\Dte+T;
EfnG_4?#A\NM)I_`oOjj),kBNV5_3q.c?mgQqi-eILo;Xer4CGp#tTI:e[XdBR@WCAItfB27
N%GR>:dBa/AiA10P;[f)$8aGdcdQ?]Ak"Q"'2VcS:ZrjhVthZ2j4F)._p1V\efh#5ulP4#-%l
:P&,<Rj4li_73W^D]A]A$!<\-XA=RS18aAg7OWWgE-Ncu1Gd?JA=T.]Ao=A^P>I3]AXKI6)M43Nf
t/[n[Nll\\aL]Aq_!mZ.]AjuNl5L/CD:5_b2$gXF3cT/X\7"9k/oC(G#I7-(_87PbtFaRjpf$C
RJNb3&08E%b*_E>rns@6C6`sHZ`E#,-?:c_13VVLj5VH?JL<BncTR6O,O!8[]AQN<>_u$6J\C
7VjmCO%C=0q["[)HOEf_;/L&H/!:rHl$9gF(=3B'3OA%@]A#(5'iXL*]AL\-mFt4r:XTt.]A@j:
CA]AK"elMYBY[DT8#i;oZh"Km`pBSj%1lpHeFT1b!2<!.`#Sa(eGKj^/l`.WrG$]ASf6s+\&X<
13TT9LQ)!&hFsKh/J*&!IC=o-=h-!@g6Tc<998/#o@3LVU=e^L6Xa$9]Ad@9;F$X+8n<X[n9^
i@=j8Z-#rls&Z"5sfMP2QsV"_\sYHQBVW1u?t>$0gpFf01oH%J:!7>M5"%qERA!hR=STB#c'
O"nbtOVb%0ckHCgg-NM!>h\=]AbZp-h;)5,Y1\IlOBat@9;Q1=gMtVXman-V%H'&$a>-oJ(\Y
SlaeV4/7rXfN"U+$qmN6bHslAVVU8bbXg'\SgI0cNRKB"!OU=\Tpd]AH8V0qkE(,5DSZufN/a
(.9?(l&4a']AU3aa'kohQe#.&%QLnq2JPi;9'T&stU<!L]AfKi/Fa=:m"mHbJuqr3(j.9!O^pM
0Z`X8FIklOXUd6Q:R`_:MaB[ktr?fV)FVcYd6r,*tZ*+AX1DDGqp&,-]AN>r8.Qr`s7kT@rf^
b701]AF^p^?\KYHqB?>YV)?^8hPZfH-@UZfM1B65@4khkFYWKjDniG0igV3rN<pbd?t)-W:[$
O;1+pZKAUmVB,(/OAa`"AoS3(Q&&[;T7th"03c@Zg47\bLW9Fn6/YE_JlQHn%[mZoo]A@Zijg
CkrP\21J2"tI;%$>m$I6J+hC_F?FCq:ih]A,,Ut21e@$!=_-JH`LJ/3uu.Z]A2eS4));X9+p@`
`Kkk"cOkYJ(LcR<9Pnc@E^akno%5Eq=,cCgPq/6SPj?*LR2-6%uE02ThWREYsaqV"4Aha>+Q
7t@s7L0"JRBii-:Z0C*;Q+t4VscVB)r96h;gi8AdP)+n;EmfsE_D4fA,rn.U;JZ>?V83]A3&X
\Ce*P_3RU=#;Ab:e]AO0UI/8mmYC5:be(p5r0^[38_m]A8JRo'G]A!#h@-u=?9n(Rq"8hL,5TkC
=NG(1kr3-BapNFl,>8WZDbK#:'+$TF#X#69f%FedH5gI.G+>l([Kjl['7/M:Lb#o^rbGk36(
tk4^V03[J7iN-lt'r*]AFFnEh&7BdUq'tu+?ErJi"pnM(>6BQCt4W^N1<mWL5]Ai_bZ6*snZr7
KStZ&Yl*#k::L3_E>(pK5@ul.n20W1j\iXIfAr#B>ZF@dbVrIp*Va_L7#&dVWB+PXm@LT'@B
<'H<CN-/C@VNZ3-R"pGjtL;Q+\!k>9#*Vl^#?&1!0aYD10U"\`JN%Pr6cXtmE$Yb+kA$u-Sb
2BAG5b&ico%HXh%tNZM#Or2sud`%j<ebU(i[,0:"gUHZe(2/rEmq16_R//?J\eju.X76,%mC
FqL#1OAro,O8E!^d^`]A4RMSuM.gnsSXkk^W9X24Vj<*(!D`!NH8B,QhA"f:WqD\,qDr:@dY1
KX*:>U3+Y*Hk6P/14F@9uMnP1@bE>%+Voe[N8<Ict$oTi+;c;b1F]AG<-U>49-BVlIrW>inDO
=/0(Y07JM'9l2%V:;'Nm:G48/r($]AuWZ,3I#GUX8$^G;c1OEp5-?HE;c51;G$:1Et[q(e59o
'&(E73>9>DY'LMFa<AD1^GudjmQ$/^:kO7e(h[+q5Y(-&5R^?O*4MZQ%b*P_HujbDq'\"bGS
(7OgRn&kQS\OF;j.@k:5MFiTB'm*cR(S6c^trS$FBA<!d&,ZnD`2CiMo[&.h7T0f,:2#qntm
-1@CH:?4%T:c5Rcf>f;U$1QJdBkY2CatWPHETO3Eg_mp*g*<9f/)R>27N4,DgoSIX?^:rCEM
ZL/*oB2OdbR+3nbV9RrTV!eYsK]AJ?0S#8M()/1dqM@\g)E9_4E+ciR[iue8i$2iDjFI-nd?F
U#@&YD*KJK6d7cEQ5'hsC,(6'ooY&"1?"08kXo5&BC!=AQ=7g?TD/IP&W%/kt4Yc$Ib0C(bk
=\KBLmV%`>iN@BXIss]A^TJ-m@7;Nel-PLdJi5(GMEMT]ADeYNt3RB_2)q*='`KUai*%+).<a+
NZ)HO#h<p6NN.^%]As9o1B3`D'AH2>s'*NX4d"6frN(OD%TKDt#+nK:3,8h$8>b]AtKVaTdTHr
BaAJ$?IOa^2r_I(hu]A^ufg4]A/LhEA2S+Rsr$.%1KVAQ&d2B"XCSoa@GL;Q-".+$micN1ciOC
fEb/eS.ZpPu:DNSb:1X;c,'76?V`<>W$J(h&ZJ3YaIWNg[:*:@+SPlGI(/39D!0#,klL_gk;
F54%,A'&;&ShUYAtq5r6502-G<"0H9i7ESN9Qj%gQ"/Tt7cO;I<\<QEq$(BC2X;S!#DFO5n1
ea-U:$;]AWIPqlu6t5KMLd_LrfjS#))LT7*/Nm<B>'7Y(pe?QEFk[d"qV$1^mfjL5`?k+i(kk
A'F6jn(#Atu,$/PY9RLaWlT%+#gSNb\$@u:f/#8H*@*l*=630hOniqFB6c3bBS\C;O!15pif
bs]APaik-N_"5!O>48ag,<Y]A'se4]Aq=6fdTYjJbOS>5T=F0CbMOrJA@%e0WNap`<Z/gG,Dp<?
S[5*F";F0MhSH5_)XBqA++b&N2"Q@S@0NVr0WUG-\K*f=m9;O>;'F#-bBDLerO\C4=PB$RX9
Y6"@@Dp*]AGp-^A#@I3S1510flW""+cgIJ8o,T<mf+$fK'%oZe]A5KfZmlCC_f:9/TUmrn^RTn
UjUcW[FAsf<!1SW[",\ZCaab@9'r^ATY,MI-HCW.2nrm>F';p'KgPf^l[9*qsE+"G^>iG/FT
C`Ob[-_""oX\d7gcf&1"Y703WCNfR<+O]AG[j..gA*C3#@a`^AR@7Lm0"GYm3Q["a#dmBgThH
c,Z`Nr%1di[RN<60jT0Gj>-QKi,iMo#S^=KeT<N!1H^cWDf[)q,)^#me!6]Ab^Bl'`\5F!g-a
eJ++&6nS15l8-*cnd2N]A&k)e!G<`54kFrD!maWJGI@lj&E:NdQ2PX0UlZ@Jn3JT+p0$TadVt
'&V-pfS]AjS**7IQh_.\jq0q>V?.$C^#e1=a@s45QYDo-#W!AOtj5))D:g(B^,+p0sjG3ZdF9
,CNp3mX3lB8C]AG\@-@-1INneOZdRU[oHD6bkP'b3*`AL]Am6>%q1t4!fm1UdM$Z-mBKe&erL8
BM?7%C$/"Mgn3R7;A^,GtA%_jl'HaA-T6h[5N1)imT$s(+ee:'@$l#*_-/Jsh;o:$GEC,C!h
q)RH8huIC^MJ&=_gWp#NA&WlK*-K*]APha+Qm>?a>T>YiJX_e?>q'l'SHnCfoaKPU71$.]A/9u
PM."Mhq^To;hQkni#).#W)Edp[O,QSR\EAV\,2Y`TV0Q+1fBi&-@/9cQ)h;s0(R-h.Mu.V<\
tbY5%W-RiffVJq3#pW>48djC>Z]A6AT\e%R/j[]AT?0aVp:j(gJDqY"hcK@#>o;7_D+F9;43Fq
nJWM'bl;0\-@_srF4I*cbp"DBBiog:,Jal9)oIeD*e*%4Z!C_[$030I4K8K#2aM,LFBjh#mY
d2<O3[B*-gT_1?0hQ1$aRo>?FU^"Ct/"9-dch3N;?-XDii2$:0f,mQ&#t@T`rO6i;&Y03lTY
R!,pIho&MU+qt3U+#8/36?$qTBR?aV/->6jOcfkFJJu<!#Au9NYp%BL6ngiVgB:E/X.QnBPX
2cj0E,HH((Eb<aWMcg:pZVQ-NZNNR>1%'"Ueom@thpf<AU29Sr)[*I"V&-Uo5,oL5N%4;g4`
;.9a#ZX=.P\>BhbO4!I5!pW>?9U&h,T#D,IZAeiRoXD&=sYQ4t;f9!NqSMnMa#Y^s9ZN.3`3
WW+1g_Tg(+Li[iZfp9&"$S>2$kLDF]AiN&;#OgY,fjP1qF'o+4NDj,61hR)u_f;E."*\uN@E4
=54p"iqO5<cg_#;]ANLT9uhK6eee_+kZIh,G:k1&tWbVZ8D4GH$2XhBELZKq%h97HTnTgiE\Q
aMIr4e.j%!-9r#Ei21-#5YQR_>)O8?NNZ"4%su:\A9=WA$BG!^M_&hC1e5O$:mefe(1NOAFh
p4-I.rF#%E$krcW4[XJlB^lXGYSkg60Vf7p)Q`6REh7h7iEdX>B=IVTWE>_q!H8]A;KFH]Akth
c-b:!s[ln$Z5N/q'BnM?L[@\WJ1eT.scJKL26Us`4R4hRki(N>-p>V`=>[asD\>I0\i)1V#E
M4I/#;l$)UT]A$EG_Jm8=/DVf(4+FcBe5.`G]A>cq<(1FZK#pSb`/o.R_Ec$XIC@);g?=58b(+
$"G^cS`LLHb[Cuj:Yi*Q=5AeYR(6]A^JU.ENH;V2RLV5d7"%?4FL*#\Mrj#<hPj_YQ66S,Sn.
6I$"[0r`JrJr#@2F>9\8=2+Bj7<d0CVn#p[Y#i8rjUBW%jY(qm?nCPPY`PL]AUDP*d/IfUDCl
U3;J$M0p&ZEIb?KC]A4ZcNPbcgPso]AP=67\JdVE(GhT6<>tC@p0F#.&A]A3,0_g=G#P?*YK"5T
$3I!#`.eOjkl,CRT*hOkRbL/r8O[VOZ]AhR1q/W#%tLI]AJu;%h-)OOR*;le0iQ.'#8'&L,0#8
>8er$[#TrA$0oC]A5%<@eZF1^H=@pSp`i(#9KPgkKj2Tg+ZQJ=9%QNIVKBf`]AlC*0s%cqh*nS
o,Y)fbk8-$3"]A/?8_TdXI+;2Ik%!MVY:>S806`(kY-[0R*GheEHp`%^HdW,:!?>S:s0`fq_k
SY<0`=QLr0X9)iM#_A\,MA.IpLhHs/e[E6#YruaFGJ:gVdZ5(J4)=<X]A[-JXpO+*X\S36VlQ
IeJ)7ThojrN&l6T)!N+lrg;j@kBaY))lU3l=>TANhc6L*JjMep-7J2UY]AN4G>gN4(9^p&Je@
[%X.?Q7Wl&4`d*:II5,7Bcg5oIT(J:/$)I1?7B288e_0_DOkoI`c\^[kL2b8,'OR5j[8]AVSh
)Ua(?!%$3S>FehFifTmOQ-t5f!_>s&EGcUA!$"!,F*3P#c#GYjTRmnA!7G^#Rg3hBt5EUrWp
@JobjIAJL6LK9akKoiqYq]A+s/+qc[,;g4EMjI`aj;Hf8^7qL\.)E0#k:J96N/L]AbEfiA%UWg
qL&s1D=mUE(8J\rMf?2C:.F7O'#qf6&Ud4Y5-B6DCXgC5KdsT8C\;ICg5"=08j^"_)4gW_DA
M=RC\0Sm0p*<pCDqSf6?Ti6Q$/c3fc-fKNlR1KG\5<9[;]A#*MtjF8TO^G;c8Bas+$Q%(f:o9
i#\Q'*[po`c#DbCXZL5\!`s.R%q1L]A-Jhu)';%c`DqeO[Bl'fcl0[b:Y<C729O@Z4j&G&s9M
$!9A2:b':T/UQZj23oFM7K(+jo+:qZht:9d6t?7EN=@>#\+MgUP-Kld'U@R4oOuOA3F"aimW
SmSX_n;;c/4Z9*u*rYr1W76q\P*?A^eO^RT7PS"5]AdVGLhP)"aReOj!DG0$c=(oTd$*]AG?X!
/QZPLT9*a0b=!r@dsseXo'UC6:/83m"miJV5cgg)+5)9Cn=J&*_m\F*foM\g-ehq"LFt%2`^
up1d8W]AOiAJYl]Aj<Qcffbi<SB3XVP*41B6KSh#X6aRj>Jc7s\/g<Pg2C@c_7bi2bJ(/jC_od
B<aFO`KV2Qf\1'Eo=Mh`p,9L`uKBZEOcd'cVo#u(1&a`,>iX9sh(?9ZTn%0A<8jcS!Rl[+Ij
apmaCOslc*`ep"8m!i8hAZgo[_JB^=eEbS\*RusSN`,2@EIA9[IaM=f'tH7`;8YD+#U#Zm\K
l.J'q(KlDj#9i0U[aE+bAagfSYm:s2o9/*$2bSD:!7's$d8SM]Af#Vk0!L0&+S+N9MKe[OD]Af
hdar;R^Ae8W3-XA3RN9ojQf]A]Ab_t(c(g+Ns+M?*,CGLq0rn&i9N;%;iONqd[X@Z!BYPZF/-B
rB\6,YSOP"'9DX=2\j-ii^.fXVM/2@sro2F=21HA"IVm\-jhQS'U0$69s<oG#UE+Mt8/b&BA
3j@9]AO\bQV"'NBYrkr-YHq7AN'ALpG-jMFs242AO85Y?L'LHLS8%PQVPp;_\XaY,n;BO?,49
s_be:',H=?sjA5GBAt+UoVk>Bp,l,TeqL^_@&\!r@0m<'V)fulAV^lbg_++Qf*^*q1Ash.na
.-@a%&"1:mC8h92a<XY^\83/ICu"3dCe)H4FgA+P'Sd<'Fp<]AA5[J`;T2a]A?,,&ks"J3'Q@_
b0rUY*B!`!?)BX0^jH8@7(O[NrX0Vt,MD'3'%um[pi204>'nb^&j,2*St2&$)&StgMhoG7-D
^uFM2.h"o7kQ6I)+b'=(Klpf>dLSJ!b/q5cK^>UmWC$FCl1Lc8F/KGRo'jT,G`?<B;$@j<_!
^QmY0-eX!T*B3R6>:/\/KA6-Yp2-J*o,?n#?=(]AGt[_FU]ACd1hQNjNW`-;qu<]A8GGt*.4b/*
?Rf?$D%Q$>;+8ZPbG$:R/rL>H$SttNtBZ#N]AGhsP8!74ntaTd_s6bBUerA+je:n8,jAQ?e$J
T=+WFWdK&@998TMA-/XYfZqZEH,Q@/YKSGi!">(]A"c'edm\T9*M;X&!E6_P1G0$tqF&<O^-=
U_5c>Oc]A23C^AbCUIkZ/HBF!g1*i@sm5g3G[`ao.IWDaoq^Z57>#@T$OR:NKmo^AK.Jk"UGP
JRddtB\,kJtN[dH_`""93C,i+N*aIf$R[Ze?+TQGDk*boEb'([qTa+dHVSbh#M6Hd?#ud;pJ
j0Ut"4aKZ_tUSBe(m;rdbCq&^=0P_,J\M:`P<?N/W'"M(5p1@E7l=k,m#L#r_S$h&bRM5pGK
fS(b[g]AA+D%V'+,Go3US:".&*]A;8&2X2qmN5IL<b$A5t$%?I%28ANJ[Q?L`/s&ll:LOoS^/7
IJn%*9LX7rB4hMl12$KK-N3(KU;[S<6((6P(NMOj3^eSNq#U0GFI!;V`Dd]A/Z=*kXK4.`5cJ
Lge:h>oLO('X]A#jiJi)%aT]A_u#@\p*"CnnuK"tsMP\$=&,MBCjV9cdY3@Tc+\TXjA!@-XmSu
OsoW5YJ;$Q#S3*&h6%,]AQ8cE0O(GX`MURq1c-RbdVub(Y(c0.[e9AXdhgG(W5[2QP_?Or'YQ
'RJ#4K$Q9@bk[c%Ho!pu3:uAai<M(ntEkRY8q8oQ*4It>I`r<2LmfSZk"%:2rZnk=@Zp(M9f
Eh=4MHZu73_Zf#?+fWQb.h^oj]A_Y,",Qb:ot(O3U]A#sI4ag767V]An\i2mj7Vd's6jk8SuIjS
BLKdJDEd#'ZuRHV3gYpFj-!SD(NbD5a'+H^W=YOT43W/D7C8Y;;i"@d<Sf*6;(n-sat;'IB6
-_I"A.@k@d,C5iL:-3m91Bo0q0[8.ke3nHR/<M)0"Ej<1l&Qf:^^mY!<2.QRj:]A#/6<gDP6$
fb.DMeU<!A@"='fg6s(`!fFR6F'(&oM5q]An6Z'-Te-,@524$R%:NF%-&L9LE(B/<Yg76T>Zj
O('XFQ#opti]Ai?aB\'rlUQ:,o5i""A%A88Q.i]ACZ\c.]A87m.>gee/fDu3Km$?`N2)&pSBS23
VIBCMP#(LC05cV1I5m=+DlF5TC]AkR5CLs:BYeoBkA$BN)8_HM)s9u2!A<NLR\I9j"r)gr-iK
X-,aem/<)_DG$#usdUGH:_*S'u*"q0+dr*6g`(]A2WFjQ\R*\c+6M*O%On)s77D=r>rg`,o(%
4e%\[.sAdj%bO^S)LR*o&.S=*iGTf6EPtt&,+_%YRobtPN;i'>/$A+4Nqh(&=.4Bq'LVl#Mb
7l+ds]AJZ$7b`X/=FQCs"^hQmC\a<@E.2c#4m4$/K,DU2ilk#UfE[G6@3.\+_Yl8=M;:hah!g
$hW`RM[?ZL9-G.@=mTeR]AboM=naudhWd(*4%8rO2m+[:omI\mJOlZP@lgDZOB\_>FQ)c+<JX
;:MSMDuq$B5)]A&T,\62$0-dBO7>05jj]A(=#pFj=!q>4`kDY#I"UX%2n=GkEH7dRb"%Zod46-
n!$kZRCkPjfkM63Ib@-!*"MjMH[a(:(3rADVH=WWS90fRe;H3CVs,2!<5gVLeG`UFXcMle\n
'O/(IZON/?%L>LVc^:j/4m#R$N'"Z)DIS8L&O%CT8&E0^Lj>9BmXCZOAbEk67AK,/-l]A["A,
=DMX+r6m>B:)$_oo)+3F/f>3niS$n3F`O-Oj=[gJ9ZkJ5O-NqN+QgV]A\!3s,C(l?oQ:eN7FJ
Z*60ZoJscXJgh-oh%CcDEk*Xa?\o*'FCUEZ3bU5=83c?_6j?lKmErl^f(93mo+,FbFEYa*,,
!&RqWn"%_`3hKu5FrQoOM,u:mLoa1Mm9q,Yut(+Qs>a8M-UgE'fdmcFcmRT.4P<2MG.S_L6T
sc:$u*J($4QOcruXEXiH?`f>OZ`Fet.6B6X[6*Hgpaa]Abd%&9Qqim^1HO]ADUQR5;#8(Ec?Mb
]AG)\5[k%l)=Y);(<pl]AoidZKh.W+B'd]ABeXH+5l<dK[OCP__AkD@fmk$B:Cg25;uugf>^oJE
"2FoaQhs!i'tm2^Qm%AWaZLNO[k._O=\h\WKgtX0I7#mKU\^U3OBF#61fH7Nj_NgcXta>0aP
JIG'DDmRH)T;'32DAt7qm@^!>Bhpamq!kW[/'``99O^ao#S7NqE[QG94UdQ0%g']A1iqcV_jU
7BllDf!XK3+F/DOUn(EZr:84n=iM@3k5;cL^8jd/@j%/B`ZJZpogsOd;0Go,GbqR/)jN\kOh
*X-m"K)&H09fO_7Vp2j6a(eH8_*G+m&pV[Vd@-dn`NYDu*_*V"cS9[3o4dh-PPR$^haD7d`*
T+?S<?mS^ESE&I@*VNfZh;8[6>X!LSa,kpK-lUo=hEc'3l`lJ@#.tt!o>ePbUn*UD?es[U&)
6D''jZ8HI[*4.AB/6'=(o8m:TrW(VXl8pT*JQP0,58!#jX-5b`%U@-a:AlSL*ZS4F>,>oMmG
<,g!HmgR,^6%T+A<(+LPO$DOQK/jpeYiO/GR'"$;l1H)R2c/VkK<r7&/Cj46JIS"4r_9;JV=
Oh29a]Ak>5=\iUn,TpJ(leg6m-cDS]AQnK$-[bI$iWZ)6j3("[J\E)pd>g-jN)WZU4+e:9u$OI
EueQnS.;M^_-:O>V^FViBjT,h97/6pkJI82W,M%UDB<;=SXiNpg[QFXH^N"dR0OPhcS8Z*R?
//eqAj<X2qCKjUpOHT95^I@X(^mkC0/#/R4T!S.P7A&ZU]AGti(U<0H_5J[D<B8:F1*G2-Vop
>+.Ldu'%pj?"#>j9hM#9SB:'q\VugD,@RE_o%$'&E')?r`K:ga;eKDhVk'AP7$Fm*b%4m//n
GN3l&5kDWhCYF8#Lbpb/@P0io>"5FP&WSeSK!&^%t.58`IRHJb`8/B!`'/AmZSX>j9C+W[X[
j?B8fLbl502nb[,3Oj[`hjpW`TfgU&2]A.7n_KQ!A-cSq*(rbI/(&FG'U^<pAds`5Bc`t>Sd9
"U89&6aA!X*M+u8)6/OW/fCn.7*`/&gcJo_5e+!1_DCtDrLY$4\+]AQE\LFKdTB,'?LoKacHt
OS8HPA%>1T?[ueuH;Q3)Q<a+CLt.aH$@a`U3t[l2JaoPs;/OVac?:7JH32!u8>hb`F0+5-=.
%'3!7JHt2O,a7E:ItC8<IQA?JCJ8mi"%l)nK5iK4QMe/*E9o-*mD!Cl$2\gVpNugGO]AoMjtg
,&?n:%P;)hQ*O]Abf.4tDh\RILW0FK`:m"dtn?<Q2qG/B@/,C<63N$;ad65?_OKISTb5=(3^q
b=!QpVuFUNW&oH`>q^jc]A(*H4cCAr_`K0:=>ssEC#dj%SE8_baO,oDY7'\\Q_Q`f7j_aX[>T
F(gp/eH!L%/_qe';X#0KR"3G!kB9S,AkN"(tX9o;`V\]AG;BS^&[@^eW9-Id0\b[b%-@R7N:a
^>4MG.9b.P/=#BS>k)GCWVc4P;H'sYf_u2YQ/*'-o>l2iDn%fX,@8,p8S^r:A(n3YIf/dfCa
Usg%.DWOo78m3mg+oBJotW?Z`dlQm$A6$U7!+1!\WI<4,H)<NsfRd[/&]AlhH;4/nP<"K%t&#
i,s*]AFZp&5!Aq__3%kln,$VuN$eVi"^fL"FjJaf(d[1;FH^/&;&=!n'kWDePXEbtS_e(!]A1q
JtK:q.+g2[F?p`Il95/Q^;o(`9WI9A_8dm&u3I.!C)(3lMRUR`A2UmfpmW$ZK1UU`C-35<tY
fokLt:rF9ror7$.O_Ic"5!h`feF$CUq;`^]A_K^\>$j-*M9MOh=%.<Y$f^iA`ics4AXQ<MM?4
KUd2:R'AANV1"Fp)l%;dT:LBe?+W5*=>2jIpQ9q\=OeZ8T1E,bXa''^M9\IJ.63@A?fp[c,F
IiF"4]Adi753L^ks'&6eCr+h#a,;OP2^iU16q@YN2*D<*&:b:(Z'L4/6ii;SbNp[=$_&L^@WW
q*&C?^pV;4ETXRfU\b=KB/J/@J.DRt]A9"V_+`c*_Td?>638NG[c'26s!OFbD4;e<&7*aOoPY
kq+i.&Yj_'=5Sn,M-i#fc)%Y(H^sFQFhD`Qmgso7f*b23(R*$S/?%25(7MrD=&12B9p_4/c#
(b)?W[e1fAcea83<sCTq>*7h>3ONq)h&GTne(ofi;Q#As!m^D(lc-,lpKBO&N>:QR&RbF4>I
-(1SF;0/M"FX^CtbV-Zdj#u+O06lE",1G7FnN.#m.]ANt3&ZXKIHLQ*qnt0NaDY,\obs<I3$s
;Uk[WmKe8Q@/i<J9u\dt/6^"**Y]Ab5:[RAP/j6W*nruO:W=[A^uQX4U=%4T%11qb]A;j1dJGC
LYX(G1M!U`+O>k1ZP60F_h!m34$9:-7K@mjaSbGZ_NPR+mh1Q=LnTlXI6$+?.g4Zqd4Tb`u`
$Y.:(.]A9t3+!LR$OlKHI5#=rN]A$t7>cEo'+Q6K*M,A@R!a$A^CtXb0I3?q6!KhtH-H$,q)4Y
S!I&fr5`ChZZ=^INc2G#->N)9-Z.`(cpc3MJMe)ic?J^J;Zf1Re'=c&%c^Io+m+kNR_R8,E5
&[4kuk*n^a-lCC.TVMs^?+!Z/%nU^1n!5GT59DrKnK)pC_\Y_T)-(eiolEV1(3sJUY+(u=O\
V9o,dO2j3\NH:H:TP!g&/lcT)ZA%YDdGRKg<F#?0h,k[RN4[G'Z?U\<aA]A[KQ2>=>i&ActS5
P0c&gi:[P?mJ?0fsdm`6SkZ((mj(JY?n4Gmm&(342P?+nZgfC;i(!Qr1(X##PB$kg%`48U<*
G.5a:-`EkkM%HY'<,r%Oe"0f=bI#OirZksq'cLRR+qMV6S<7Fj?I,@"[KB"2U7dTFI4^hQ$Q
oG?[MgD&abP5C(9l;m$Z^GA!<7`CWb3@/]A9^s=o,7Y!II/eJUH+A+h`=Jaqaf*hbCfQ2[^!I
_$a-(*"/#>_Ab0%R;FEfYgaYs+oN<ts.4FFIYq1BL-m!Z-_AC(&NsJLg+EWL^@[ti=)S!%*>
pa3@4S`8q.5`rCZ-Dh?LRl%JXn\YC5j0M:@O[WH<8)DFKa!a>/tmaKfeMKYjpX%C'uVjmkF<
="7rghcL,@E/m1,WTLucu]A9#.qF@R5>D2(IN9SL0&IZ2nLg@^"-r9sN5>[/"V9*E@_8Yok%+
u!JTV=::5BV>nt8Lr7tIYj",p_[L.qFn]A4&FqPqhJU"FONOg1A,V2*BHE/tL).(/!$blNPYc
VqOkh[*icXu#D^9H(e@DB6[8:megB_HDk47*U=&s0#?L".G--`32l6oULffuY7RHh@D.e*?B
Y9bW([URF8O/(V/`cu#Dj!j;.pN;se4Z7:!`QLJUjYm;\T),MdBd.0A(FbkLc!;D&".o-%I2
C_SoUqaP]A5g=`^Fjnd2TWOL<Lq'`#h82Xlh7=N5QCDW7pi&t^>;3n-48U/7PQQW*q;D.H":H
tX7Q4oodmd0Zh+&8?N8^_FB:lRdq`g:lSFG'5OXC]AI4?23nN18-DNJ]A-OS[k%VBbP`RnIhl"
XcCrDeHerLJ8%o$Ha]AD`!MkGRG6AE2lir+XH/jY^>V.K::<ro<n#\Ea%m1loeL?N)*Zl>k06
FS&dOFL1;4kS^q]A.Arp`%VmA4J$T@ku1idoK'CN3_\QmLL0#;U`Fb_?O,:J`.@Gg$54jf%"_
6R_QDEu_E0RTtjh#*KeljJ?1X\Kr)4C-!-uVs[r96BKi&7rDM3CQ$g<W0C]A<$F/r5/5l:Wfc
0&9c8pG]A)o+4Wf8k^.NK%M)&`=WHl+Yk7/r;BeM9B4>2XHth.\ir^+<c)(:L/75M%#pg;iB<
,>[0(="k5N4!oDTa*9HEN"4haS?<2QL14NGBKd%Oh;2-nO+<*'t44u0OE<bn9_Co*O-7A'Zk
+d*l)PY9;O>m[;,n:S%(UJC[3LsU90p0*RI@ncJPrM?)DSKLdYi^`.?]A"[Wft2C/Pb?m+7eq
ZE',-bjJ3M7/,ELp:nb.7iH'NG%&la4Mc.M!&Xp5G>*K#HgB!ka8p!9LjeJ#g`k1O2Mb*<Mf
D8a7Y-H[[NOm)a'Y>?NX!d6XY5Iqb_Z-n5ucfa%tFI9h#PK7j/qM`J\\V[SI='hJ5GUd@uDs
SdqL<qhi44>2hlsb6`f<\$b+Y^AS7k8HGYF"rr6p,FtqZ?>*O3X?!6FH<GogIgH-T-Se.8aT
)-HO=kjun1%8mFdn.#Y4u1=6aGTPLlEkZ.GJJTtFK1PN)?JFq#.:ju]AiB)-)]AaJFr&20s'nU
@'8OEO.DSHD\h"D.;3:B:VB>c$GBRCh5hS1_XomnnB8k$o>J>H):]A^d!E15ghc+ppa@3515b
O$U$=g(i+[BBN+?)J7gZpSk-$Ok?I-&]A'%P*^N/n@uo?9EmV"n<>mHQYM]AIr<?dQ`SP/oS9R
?JYLNX++te]AjL;L4n<&_:jUQZ;0>a\9:CSe=Hk!$h#_BR[rW$Iq#?tgCG)30_6HFT@llF^pI
Y/p>R\u)X8XR@'fHN5^n65\'8eDg.mI7M,Vc(+U1Z`(3k#!)qnV+'m/GBRF2M5UHGkX;P=+l
OgKICK_3)O]Aq\sH28?R><'%*J80UTR9jc5<UC^WTdA*^1>:"N:c%1h$ni.-/*XZuTB^Ui%4N
eoqRXN+?S#;6*@3d7ZQg@!SDD:nCk^r=uB^31ncj#67RSa#8>C7;lY.lXKWo/\q&JU6f%AAJ
+e<LkWaUq?YgT!hOh-%FbOOBRd'_Iu3*RT7NoA:un+lC(CiTo=gb;6!rc!UoG55I,ko'WTHe
(W\I9b7eg),ZZb]A0BH[3Ohm>06)@?kV:K"u^RX)$oB]AH.c1ZakYs))6,^]A=c!hmd4dIGjee#
i/)QQFe1qPX550%[u=r(k'Z$;+3+Xc.$+o><a^I<4o8J8Zf:N&rM5YqjTnKlO>F+F>X_#Y)A
2,\)ZUCcl9kktE*C!bX\OVG*&//e<9SO`.L/m\2un0M/MJLgi)'pP?h*k4>@Z3R@[e\#Z]Aq:
_u:\-C!`elcQsuU>k-_&)QN+bX(Aue&ij@7Q\X/+Fe@-:,$,0$Lt1SpoJ-',A5tj^!iaB/#&
/]A=C;uDs0.F,*)ri-pr3K3G8#arj9<Q3j3J>>ZoqT.cfKsoU'jYjAW(O!Q6]AV]A9:m@0n+$*s
aG)h/rjeAJY4%g@r>=]A\n1r9-Ud]A+'bqOu8GM`U#jA0hj#oW^:!a!TQHg^oZ=3H;kUO%HJ*]A
^.2LE#0?^I'I=<JW$+T8nh@IXT:%=Q;QMDp$=DZR9\4i\[6'+>Q%n9scP8J#R<oj[)#Pi&0Z
j<3d)I'H!_]ANV%aE`)3R,OXfa6+4VOFBDtap4)-8`QW!E,J19G*HKF`m'(lR*oeo)WM?X.9Y
nNP;Y.)&lac`i58#GHQJ=Rl5iS3U*p4.PL__e*AMQB>"e%pif<X"YU8;>J)(j<M,!ZqQ2,kZ
4G[MkZiW`D?'78XNUdmWBN@_b4\%p4m<AC@u!/!:(5>nfSA[oH0Qoi><X$@h</PG_07%C&G<
.>a2aB3i8L/l@"+5O[AR]A(&?q$_^[$IAg>Y+*g&c#iq\c`o)VTEWG[3/JOb&<AQ[VIo]A(m`?
Wl-PE_$hHHid*XFpaDKo=3_;bf=cE[hNJq!2Cq8%gUaApFHGKEhuAX)XFAlp?4L8X0dBW4@a
E0R,!@/pQ4_%'"XG_2,eqCtHZFU*bJl_"T$Z\Vj<($]A13$nn,R)S/A49:4SZKi.K+obC&co/
4s5KhNbV]Ao@;,-8*.(ah2+fB9_sSH/l>#>S3A!K)6H?5d<tk8a<%/FZd\hCp7"q!CUKbd1"N
^qf@3D_/k'gd%TP]AS"=#lXcDK<b$!:]Ao9%N-6qr[8`M,`KuefT<dAt^WjfQ_TG?aSU%rcX6E
_`OaQ8W)ssbcDgGglMKWO7[Ou.Kp-(_fjf_;0kU#[e=cWJ=LY7aaE.`#aL8RN0LGY'A!3ZQu
kJ:#ZU7tG@ZCW?sg1>oBY!m4+":4;RqpbVku6kY-<PgZ0%uUKmP+L]AR"e<lOejn7<d0i,MLQ
^UGFcmINB#I2Wgt2CG0rmEM7@n'r8-kS7o=-=oAnmdLGt7lQodjk/6DGp?(5Q^jrI#0nW<EN
^u5;AS:+oo\9h\-h3kLi'H8HOMo7kl[g/<Z;,e,CG=pg-)ig/'sqThh+qA7mK2oC>ar#iHc,
ZspDpnsU>`GL]A:[0^BhSH#Dpajmh`/+kR+Lp:09lk;$T]AFGc)m'1q'*G9'7+E#lI+=0J&RPp
6#Dm48(u(PlV8G\gQo2g\O>t/(LM\hN4jA2)<^()-R;Y`9WpYG?;0;p99\pj/7KZ7Ye(E9.V
42)\[S#BD5G!bY7(45%$nkXaGOaRM%%DDT11CX5t#-dGK&MGp2qkWWE=To,W:`cW!!F]Aa4#=
ZOBm$*7/qC%mLS?D1poaLb2g'gElnic`=0fOY^1!rr<r`MU5>h&WoUj.bg.,mTgr$6[#(Y6^
Z96kTu!X:JQg>T>j^aNq'8VYhCES8rl:FPlfBT1&mNd4Ub;HP+RJ1+/6RA>X>oiA:)_KdZDW
E?=k=?G-S^#&5q$sDF$3GkZ$&1rgF9$(?U"@3n,.9RGq5B\d-)-)s"!.P]AuLG9`!IkC5Z-f(
^hk>/.EZ+D#?$q4J-XP92$R8ochV^oD)@K;a<+Ba$IJ&l57>;Ceg79s,Q&kSiUCPd*_Vs$m7
n\Br0#\EAYO[F'lr$mrdGDVaN9BR.Puj`5U-t]Ar8W.mSpK;l[k/>_8mdkS#pGUH;2A.kN2jZ
>elg6\_?\RT--"l(Te/@nZsc?I9s@15^/D;Jah=&sT9iX!DN4-UPPZJX,=\D95g7.E!tcD`e
sW48ml-:&0Sp]A4)3V((SHg-7&L\5o;e_pk8lD@RSZmOFi`b]A;&n?"ZWm)EFfHgbU1sSb(.Y6
82/)l_KOn"lTEt9^]A^c/T)DG10A#L\!?g`?8iC*J1BWU0u]ALF^'e+6/:f>n)I[/`C1a+2X0-
[C4)k')8)bb]AjZ2iK,$GfVn^O^[\>Am\YYZ?QX6ER)q9>X^!HQB,Yn<]A@Rg&lCDk!!)]AA.3g
>QMPYN9.B,druqFKbH]A-aaB(*<`KlbLInURkP9_=^L[jS?fB#CBG%3eN[40joBikIfD$IL@=
4!(!IJ\[_Am7M,^GkAq%_Gn0hJId8a8kusWT9$dEs2tKcqUg>VSR_ePq98-CJU]AU9`ql,HpY
kluPXqKJ<bmF"7>JQmZ@Z^siGkeU=$9@H9V`+]A)Z4P,*;b.S$(_A#Q/8!cdb[P<]A'<+3Aa;I
S9EO`GH0'6b>@5&kagSZ2N3T@&C`V,S;c_%uS*(2"bRi$[(iMHQAS'Ys@JgH]A:C[j%j:klWV
\mV[OJ+8l?>T3U84Z@4-D;E"Npo7T&UG\`+.<(P\]A+_9uli.mRJo%$(+m4RRgO:"(D^j`Ukc
3d-X,NmPFSZ=2g:_Ll^@U>8hqQbSZ1"X??'AA%_nk\kTjbIdfKPr1Mmi53ipps87p5C)PIN4
)C",_XY(p)&ibKbU(///'VSm>`dG\q*eO@T=]A;d)C3h!'>0F1Xomq/^3L7i,![q'aGOhs'nj
ZB<q#An6CJT!qbZ;B^X+E_8`nqk8BQGcYR73n0gI*UH@8QqZB,,6,o)OYS_.SG`r+\B>Xd5S
eHkFf=p)EBRtqj["8?H]AsYQ_>>1`0D-+s+0U#O^ePZs&W!1&Gjm)^9_..Z8WJUTHmY$+[X`U
GK=TLf>7`rR,&PU=[?5/6hiJt48a/Deb',Y-f8f!>+^&\iJlS**]A]A<k4%>F..:`")R,MQYjI
"%-SQ-oCWPm"h4TM7g*Z%IukYH:)`JEM3Zf7?%"&EfjWI)Yfq!ABKlEX2;$6_Q"SKY"='RE1
KOHDB2p1A't<L1ZK5_^7eV$`AAi4aB4Ml@O0>8Uq5Z?_N/plCDgFMUXf\28X(.s'&8Yc'mHn
M_EEA0Bsc`)VE2eCN9VlLlR]Ar:H*[$S?fT"4G_g__G6aNi$:Sm9g*kNT%8>-_hD/Ljtt/i3L
m9*cg,=7&/SM_bSc/_<`G<cBo`/oKTlYs+b?TJ/BE?8-ftr['q<4c!KBl&8i&T:B+@)BMO@p
iu;<-H3C(Jg5oB55[26P[F#orATt>:Ybl\QhdhM`Aa.V,'Ge@_KcDe-?lWV^R"D"^A2llHA*
tZj-1iUIT3*aLh3bANlOmp/":r&Tgq'$fbQ0`SLWZkef5/L)gdB@1HsJ]A)RcQRDd<d_SER4N
;()Zrj;+-!u71r/?@poFc2oapd_='gS93T6mFu\kETQKZ/D%si"#'J4KH#V947MRjRL$'">K
g\/WXA+tp6seju`,P!n8r0@*fnMO@5jmL,]A2n4'f-@$HlB[s$Kdoehas72X&EN'O%&8"41bZ
3Iio$b$/PeJ(g&!.SL)Ua[J%jt8ioLM1NEK3-/,qA\A$^J*@4"*E@=s5NYBe4]A]A?u_)nX*.`
@1spmJZF\`ABqB=="$@i*Nk`q;!/V[_2cKrIr>&aQO`8C[@f4RC!HXu=r;njd8kDI1!GCpi6
<j?cliLuA5%h4,Qsh9;>Uc1491\PSSeSn&miG!dN3Xi4?@QL;S^SOEA+iU</i\N."8>'WReB
&.^pio/gdld:`>$J#4mC9G#i;G`]A-;OPgdco?(g\BnG+.`.i2469#=u[nj/elm)?AcR\-=GP
D]A1DZ'gF'BEeqNgsg%ElW.'=c1)pK=<pjH.,BT%GFo;9p>#h'98#J&r<UQ!,]A5SN\&ploZGY
c.bF'$cJ#u>jp)JmB4:=R*>lp!U@sO'%:#FSbp<g+TroqBtfqdZk1(5D`-a&^$.4U:G%V(7s
BmW)>@Z>oqrbeTAkE'u$h="Q>'i":=m_Oih$Kg`elnI]A"f6^8'js&JC?8@Je_pZ(%Tl]A@I/d
bjUB=C7j$!JR!$O`cZ^2Id91/ZePD&V*R%2,ZZ+*MKn(UTIMp[C!VWP)a1npdm=IKTV'N$5:
p=2[j/&d)@5!T]APJ?%oO6,Um\hiCjXgeY>$=fV%XF.S8NH&f#Y/<-[\"mrmlhg)a.Tpdn7[%
V`kn2m24fitc?CV38JX/Dt4Tfkki)[R,R7hoMWo2i&8=,JH[tO$B"X:$`(K"u^P0g:8pT^8(
_gg:@&,/s_ue+(uJ7I+%L)m0F-.M37eE6B7NiB'a*jkQ['/C5fSJ-cLb`8SoD+s!4Kt<%aR9
8Hq^G8:\g/f_VheP%$Gi[Nt`3(4!tU)\3:j4XIY%>Hs1MHZJ#pd9V6u=Gt[C?/MZ3;?0E!@s
l_LXXl#KdW>?(E9[3'm]AD[JnsBt_hKY8iD7iHM<]A+dOb0no\.2Q10!VuY^_aG`2K\_YF]AmZt
a1^WnC/(Lu#">OQ:.2Ng,V4+9QWQf)[e;%Mm+SqOZfDjVL$8dL7Fi,V&&<8(Rj7:Ge@=?T9@
2`-E):U99Ua($na_OqAk30.<&n5E4LXd(m")3.7LR,:d4mJbf,'Z+[4f;#r+PDb_Q.2J`O3b
8d'rW8TY2iW2I3bs7EtqT/E=AM&3XNXK8TAcH6`bd7aOVjd=o=kN'@K+[b@n@G&i)"B!S[c:
2$gfWOmG59eVCo(C?#l3PR)9oU$X28B(b-3KYld<PYNAs;?E3Vq,d/1_cc_Coq>l_^X<.^qX
;EbUX_=IK1Pdi@S9jWA2d`n"X>6`V#j&[C=\#?'DODiR5Jc9(Ht":)[*-VHk31'@C*iI38.S
[8;BS-YD&pXQ)uqLW=%Mg+:e:]AVD\7)#J@7,=%LE%LN'-C;WWa\qeO4T>[KFQCM`X.aLI(C^
--+r2>U2+X%PXe]A<$E<*\OVW*dD<7Buq$qgekN"02^-RG`Y7+B$:Zd!ThK+$oa_9"LU?coAq
;NOhJMteCf6X8U3T_hs(_%qPt(VLT)u6NE;%C!2?If%jN7:iGF1'oGTj)4^j_*/2?Mbm.)]A0
h0=<PL#RB($&f8sic@:bKWL`8%*/R>3t!_kVcPC@W<F\.18^DW((-T#4ALu,^)i@]AV/&I<s&
!shWq`<'*4lo^(3^L(jd72DOQ%1GHt8ic6:i$L))3&oD=37a/^dN7=eTWW/ueN(_6eDK8P-D
,-Z9?m;sSO2g?b(/g%s5QFG4>0F&N0qH!hJYZ0*@J>)_9p2#es#b!AR/(U+rQWBlPG_\."Ir
Mfi;q/c)HQUsEH\sN1q5J998gmm/<Yk>Fqe]A-GW3ciQ^AO3d`8S%]A"eE&h//7S,gF8IFuS:S
TMg-D%Rp6A30rT07&5+USAC0TI)^1&`Gj!/44F=h'jUYbDF$NA-\EOdZ?eQ4AF=MMEZ*#53W
SL;]Ac2$Q(?Ps;H)U/FQDg\%Dc\#!sIUXmEpft=^ed$:$MSHr9"q$4Z-aH_6PB3qfZ@=n1"-b
l=r_?ZIC1mlg1RV6W"q&W"%R9G(=SE%#+]A5b'R?l=6K^*YY=Y_38JgCe[iMl82[hp-e'';C'
#+2fkPlN&qo9j`$4Rjl1>;o6:i#rY<DgS9j*DV'+W=;-=g>ZH%9EGNLe#<#I]A[-gc-DQP9Qr
n8.jek<&]A^?%Vo%J9blN$i+`a8-;c[ZC)!DmDqNeeGl)1t0HeoBmFC-'F8C2!T`cJneImg-9
A5JaDakV_+_>%n;F^8P>6E]A$;"+=ai/8i?]AB'aht+l%BCBM?Z%@MQLNMlc)N"3Z>"GuH0VMF
I%InHJoD]A0\!>*,D/nAQYCFrtUEG5:_aE;Kl;W+k-%\*d?(WFGa&?+T'F5lubr6JdAHL`AT6
I;p`sF2QhnTj285lI`:*.]AP+r]Am<+]AHPJ(+59;45/A/9E[mK)TC`"A<JdlT\""Vs"L#cA'&&
&Giq$*eW%1@CT[C`);n8c'U8siY9GV*><t(XP//8BD!:\4J)JW[5K741>*_,)OF`Bl&kNh-O
]A.iDSC\_)NVs1c(5NmY\th`9'Ftqg3WiG)5#%`\B.HOCZdkUnSF8#^Mo5OP3-flJ4P+@s\hg
GPqK:X0p0$D%)MG-mQU\"]Ao04*_7<CC#70^eH#U]A7_@T&(CC^aRm[$(sUajim(=4g32TmWBV
Xd,IsEj`KK%c4&'$,XUChd"$ilN*`Q0a$DMOjG[5+:^t/,9^IV0-eV7CtDXD9$2"C^qM_si'
+Ua;Gk9*>/_:pD";?c@nLV9UQ)]AOYGr0o8%]ABDW;Q!hP\kE6&h_c#*)@<So'd4$\$uVQ(c&9
'>g./N([Qs:/mB"0B%0r?a9B1U7i`?fB-Vol/+:@XIVj222Xd1SDmXjl`3F+#Yide2`15?G,
9cR*Fq]An=7+h=-&$ltolG(HnX\441Q+R^:&%hVqUIXGMAEVRVg9:s[Q8_s2WX6eaC<h\+Zuo
M?>".5$:Y0<n<L(YMF4Ob&f,I_bCCV..H]AJ[#p+m]Atq#"rEge!=]A,VJCSTd`Rc88oor]A`aZW
L5]A\s^/nm#9ne?Pa4F6RQ(lO4!SR+opi'#rC@1M'1jhK&2nHfYCON%biETJ]AS9Ja!Lo=hF7t
#4JQcJQ/"T=!_)qR"8ci_^=X&`O]A2#J;+=F3ZfraVKphBmW(b)f[[V3d;Y[[JsoRh+gRrka:
QOINTVlQ;9)kqU33>2s#fHoj;A3;W0i1C6,[UQpCg_QV[cDKe-:;tZT:K&BG4h$0YMaI!bKK
rWke2W(_d*Xl_nZoOg^(P5t4n[iK38]AMtA?F7`QRK"Gmi(j/'V9cM#3AWV,lb?n7a`KiGX%W
_=6CPU(Q<]AohZLWOTW^9q\f&''s@6lJ<@sBJ]AN0iQRI%ja-<U8PBHO8kr>Q/Y'_2nno6K*Pb
YiM%CZ7oE30.ITYeBa$ir;Mr+<c[cI_]AA+Ujl:IEkn-#@;7sL+VgVJ01no(1nAlY)mBF`GGi
8RqX1et.8+CJ]AcT%oQ83Z%VSV.;?5E8\n*QXfT);1qtYXi<,72r80'=JO6>5"1CH/XlY>@%Q
3F8AVkBn!,:?G>8Ue[_i)_r`&mB<LR2eR&fm-;0l<lGri"c=(r0TDYOK2eV(m$K_%9QVX2i?
(O%?NZe;-h>*ic&pO/SbFhfeQVZ@a-4QDShU*M<6C93fjD[n.l%FQb?'DF^i]AUl_Pq)1:VKB
E9A7MgR0d+`9KQNe^la6QA^&AstJLh:Kof4$MeRhkW;*0K`X?o@g9?SW2q<MGX\aa4+*u#(K
:ZQbHFCC"H.r%i)(beC(%t:`WSaqX4f0#Voop8Q#Wil(X@OtNmdG:.EUMHaXOj]AZbP')VDqr
-]A4UTB)"HVVcFK_Tf"M)T4UnY.'nn]A%6Ar_.,W@_O?;<IM\#l`2XVZ%mp)8X61_W>70*Y;-n
s`ul';+u7JFZ>gfDH4=o`(\;uJfRY3Us)R0^M;EI>)@>0?5m[Q"="=CsT@IP[I+F(\%jr[J*
Y6Prc<&=Q^jZ0:c0?CS5H7cN)M<h32*-D(NKoeNqnhNF'\=g@-p86<QNQfSQME+-(O]A^=i0:
i`[-Di>D7p0nIp^'PP\f"-D<i41kedMsaleui-#<\JoG996cWLGbYSH5e'kS>;P8'C9D*4uT
@_VF*IIP`N3tZO.[5-MLo.</rmijNE$<="h_,b`=YtPtPn"L;QV`Qt?E.==GQ*+c<e?H=ML^
U@>RpgK#)W[(qPM"ZLhZX@"L!?,?Vu'inY5`aC'(ROAD9`cAM%GgRkV1F\C5lNO@tf2a@:\?
#Vmuk1#6jWLAmQjSU(Y&8;SQ&;fI;/6:/YQd:YnYe--'-=Si`%VIUo?>)Qc1Cor_+;]AZnOZf
cRf#+`G#Ya[SbWEc0<2mKu$IC<Yk_8QZO0T;AuP>,1Oso;feHZYo>HbnNW[\9t*@C3:I6N&3
9++]A_I/;uH[ePC5ZffmS(AQI:VI7KPE1Bq+dFs)A_98FYLn;1=(m0Sc/[OIX,WC<P1]A!j8L8
F;64l$)X74m13Bef=QTAD>d+%iOtjignei!0Q'HOcVDn+^Fn3Z-5E0-ZM%7E%&GFj)#4WRHL
\t)U822cZ-GA*(-=D-A8?:7p?iYoGjdicBZ=K%W9>em>d1Cp'I+0h1(r<p#1)HclG83\.-$m
!!A)%^mN8Gdq`O8YEWN$*LgQR5Em%@klEW`:@=<Ool4\XsPr7r<4\,m:gJJcp[I>Z:c53cXV
+&i3FTc&3Yk=0EF;JL>d2f`"eTDf"CqW>DK>D'`3;cN8_bO?6(eD&*19(2K9kSFpRGI67o.a
Fq$B778Hh@N;A\^L8)f:'R$&"rFONC):4)!Dmk9LfW[TbWI//4]A!.-'*@li#CRC@]ALTr-+*[
If,J)14$r9:ZYr(N<@YM9'B+fYZ>8@#(9c(rXFPWNgI`,K=!E)UN(;=J<<bod46GS,\_^jRn
P0Q0@dp`[YH;')Mq9Zj5*,J%QBIcW7ung/QClVfaNQ2,6_T-lX>H"W\5]AU(-Q\-d1LbFKGD'
Q3T2,N7ufVoKp5HW-DA%"Y*]A8Kl";*Gd<m5m'ZKgdZ/Nr>NGMoL_3"TW`QP'oPGAYQ-'$G/P
,tQ:niY!Cc*\n8d!Tmnk"g5P:jL"'/:2Q%2s2jr67#e14Lb^.K[8)pjcZ`4;(g-(q8A>dT_4
,(/keJ!P!p%IMr-D1#)&O_I^&g"o!%d'.I&lC'V\<-"k8dQ_\(Ru5mP;m2j0:*\c\Zqefd]AE
nq(ue1Qop!re/W]A_.$g3e4@gSc$D<H*<[`F2s+(@##k/0isD]A,Op!)N#p"?k<%CrQUkL5>9u
SaU#kqGsfR]AQ(9OPS^@`LQ7_:$CZ94e`Z''<i$k8HCdKIOpP7+=4iXU#r)kHfYS6C'cRed!X
gbmTmj3?eDI$g9DD*J-C:Y=6FhjB0"/bnjV@9\3mPdHA2[d&$?O:anHkbY$eu*mn<_&("in\
G:l7C>F1TD*2!S0E)K]A\#ukVWUQB5asT=hE]Ab?J2V<Hc5922nBj#1T!U"e#/*Bs9R\t&"cib
S6GoicGDi]AaO'JGILBDPEM`3;AZM=8+.plWf]A7flQ6K8PCbnP8]Ao.:lW5WM7''ZG%TX>lPW&
Qr,kfJJMO`.6E(+(JIFW9O8ClYa&EX+`#fVB%]A.8^@8rQ.+3.Z6L+2Y^LE7`99!_VrC.;C"P
lma<uNk-?XEVZU\OlTF\"H,HYK(Djq@ns7KbRFYfB\j"`JuY8fKjf8Jpn>/\Q;anPdj376RA
G&@Zg_+ihYqT1FFCJ_B/8*T0#@RPH1GPCd+OodEE!LtSDQq(NgCl(<M`6a">heSJ_n,)Q`fr
(DEugjDX.JE`9t4=UDt^)*=-fW_:X'O=@9ii?7R<RXH_8+'ZPS<\(eaJR![8X%ReX+L?(<g_
/e/1g`.q%6FsBiB`kdQb.&+44H8(K;8VN9F%7lugbt(%ZC\IKoi8B(noF61j-,=%$>l\\e<g
)<$SPfH\-3pb3-s&)Hs)2Lj\3M=M[O6'#JAQ2pn"FDXFk)AtUW\L7^uf]AhA2@H_uHo547^hE
g[Cl`\60(a1@&:`.X5i6+.6>0GWM?[4e?hks(QDiP:[mYiRGCErDckH[uiY9EVfNlQ>.S24)
tl;m1[`n9N($D'"Pk!'It@I3Z`baibE-[UpTXo.6QD0I.mM2p[Eq6eUO+5E)JNr>AcO6]AP5)
6Am8<oGXn_tX"KjS#\8^RWdH=OG(mMp"`0c"37*7ZP4H6"`aj9/2V)QaX_g'Go(Rr2DRZs1I
f74#9JgcLG(^/Xd_Dkl3U42eOR&6=n6i_Dq?tM0dO[h+>#;oUn8=XY1-Z>;(K1kYE1(qmq>G
K!gY#Kt#gg`[7fapE+,@knpgOr)hfo2,MB+>#U>#]AVl)Vb]AQ@EHBSBtCJmX=>Jd#S7j]AIh:S
eT&#4lqfmcAQA1^otONYY%^@rO6ZE^>Zpk",Jg,pe:,KEGDo>9Al!h;J">n=iNbitA1sG>'g
OWDID<gRY/,6GVJEY$q)ujY1/@@&..o;b?:2j7FHG7sBNG(\[.G4d!^/3/PhTa3thXcS/,kY
.KIH09^Cih4M^QQDe3&`OD2Q4V/:G/NkfnSr1of*5'r\I`;rXkd]AI:1M#tfr>P74(#3ntrQc
QmfY_&EaUM?DU+.uk9(370,R\"l&T^2Q;%'967=E#d[[\_3HN>l!D:2iLeZA!DQ6=L8!cp^7
q%<4+VF+M5JE3,!2hIp[$(k?^+1$>b%3B-?VRr76dO*4[;&$rrJ2eBF?pK0<^M[<5)Vt'L[J
-5d9/(LC(G'4S^O"XI(!ir]AIrI0a:ZMrZKg'.Xpa+$M$)IH*#Fr+5gAe:$5AO3nmtur0"LB+
W5*/KeiJoNg,,$.&9:Q1L9[Yrc%!P#DahC=o.4(fPFp_(d_EeTKhZN=;]Aqjl%Hrh#/1<$_p.
gaX_@f[8jmunK"ad>M4M\Tu<eAseB/<r"*I^o4B:>6@e/:UV8LiuWH;/=dG.b\j%;.D$]A]AXm
UH4dRV&BqOThCC[4tVV7NM9E`]AF_c;l3%6XbSRi\iHTOcsao+k]AUfdR><5PG5qs8QFoY'lZK
+k\iGO0cqujq)%On_JWgHpmMtMk-@RG9dQ:a2B[!+]ACO?iGKqe^ibcICgdF4=+dgroF<3Wp?
AM[o#'W/lp+RnlcnFTLhb5F,l$Redd&RgA:`RZ#t`RJko/@_@O[72A=NVW8@UP?#c\=;`HSh
XXUM#%GaBo]AM!.XqH^A]A.OgO\1UQRmX2q_">@n]A]A\^D.KTSF`EXVNFBJK6nN+luZ-pGkG)"D
2m.H=0Q]ACRKc#@n^:rAfS)#dbaRT-%I/MopMoS!`AnHG7IV,u>54Ef@;>7^.mHSP+AU'#1RJ
t#"N,eJA"gZ>V8cF_;W]ALe\KZ+,^<37Ne**V"^M_n#Io,V.((PL+lOl,RGmL=4pJu=QL-ZSh
+sl>'^D2.WS.!5^`'eB-OBS8XYi4g]Ad5U[%i/5M9JBb1\jG1-5N'f*dF@I9t6f./!9MJU>$&
RWrk04FcHmla\cf@9Jb1b$W28"=59m-UAWK$7qSVII5G=gB!OIORGm8)F@)$cM8ZZ?&*/r]A-
fABbK3M!8",HG2FSVsTk7DI2nC(=IasNAjUK>$lN5nk,$dV6'r_'"m3Q<:*V+:itge=R-8"P
nc,=gAY8NEq:cs+CHRM;V=(s)sRg=EY$9h_<RdZr-#+]A0ASahi(RVP1kRoc%4u+S`,l@(fq,
;YGHO-hU;T1a!9D8qg-JZ<fG_jGmL!mFn^,:bdg!M)Ff4Y@3bi6e_19d?X3E"7BpsRo?WT_3
M5;)/Idl'JT^/d?eZRUskY'Ia>g35-;]AQN\9->YUP"K:7_\@cUn#AB7O,5bYo?N<KU2lZU5s
Bhjjb#?d7SUIPTO9Mi^SQ?<#r9KLF%AM7FED91`l<s?Vc\Pg&QZMH:O87KH:EdB:UE9EL#%L
5MA7&Bk^bA2`u,Q.gMB!$)ZfLq^!,q'=)>8n6&[KPU;e22!NLB2@@rWL5\:Psb`/sr<R7-=)
I[qlcP=be>Kpn<bg?YA12Cu\`7/5X+'U2.Ro&m*DUcW;[;R6=pnC?b5*HgV0*mLP1F=8Pb9N
G^r[3+<'O%$&s#)D)\!NMU<@ttDg&L@k5%eF+9%BNV$2G6l??Vt2,SX7f!p6S:F`E7*TCe:+
9Z=u&@eB9nVmD9t?n.[iK1_EsF&ZYkk+@aJE#IoF54(8LW"Ulnq[UYjY0"WF-,jftn3ifTUt
)s3Cjh"[]AW#beT#NC#J\h1^0)-d/+Hs3or.6PW:L5G(PD:"a9#I^8O"X!?4H[)j#:'2a>2T0
a?IF]AG+ZFU*5PU:<f&]A'>.2s[rmpl*V'H1MY.K=U_mXNdRAkgHW>2Ne=!5CY!Jh'1`YD;FE$
/4<S#`.%rf;es\VCjr3#P1pJ%HEne^\i5OUILjrSJ\6*WV"^L&Fqf-Gd"uV6[LVnTH4#'N)e
*\e'Zt>]AJ)"B3?2TE^r9;6l3J=4EuB?h62YgFl!Z3n%9XN`/DP%`KI`'\5fFJ.Eu"[19Gq>j
\V6T33!10/59bPk^1Xnc)mG&+:Z++.SD(jXk2c391TrRlo`;tLducZX0s$>P'n;6!r+^]A,"T
PCq??-/<Be!VHiqe?"+cK;9s$VU9=C3+eG^-bgT,[39`&Ee0AA2!U/MFu=j#4R)mm#*Wf]Ag'
5+i3W"ZtI'Ba!T`B<h,k5>$C2#0\qB5:2+ErO2Cm^lc3p+OmgV9%fZNq!V__[8'VoH4M+mOL
BJ>;XqgI3GJZR15-H+$88)X2_o0Y7RA?fSQG_'=C)=B)<hCae0[DSn]A*):TnF$NnDWD@mOB%
0&h5`Z4q`.U$-I8<kCdmE\FWnI>(FG`CAK`XnnN5Zia*D=2YMjY-MC:g8Zb)#)`$7[\B2u$M
NJ+[\lrM;#H/;m&Hufn+Dme[+5"Q:`?_#kBRMAW_L4:RWr`o*rL@WRG9;T^Q6G)#8q\K4Oeo
Ui8)<1CWj:AFFjT#1:s8);+e".K`rU(=+YAj$;h^^r+?H0(G5U'>$3PpLM(<!4B.^CfB3Hui
rq82D!qqe;!<_^COYNW;.n%8M`DKnNr.[S,_a*bmb5Ml6&$nju4r7eXZ%f~
]]></IM>
</FineImage>
</Background>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[914400,762000,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[性别]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[年龄段]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[学历]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[合同类别]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[职业资格]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="5" s="1">
<O t="Image">
<IM>
<![CDATA[I@=eJ;qnb$BaRIj'PUI1,#SQ>"UJC5U^>Ir&YsV;Qen3&&OK"PKH_Fge:;Y:82GqU!sAl4!m
brVHXkhchE>M$fOKE`C[V"jF3VD\)S[#^WNJR:Qgl[L*Zk.#(J8W8n-HN&4?k%#rTDI>!GKE
hH[,ReP.V8AZCc3YoBgW-r01TTMfU*B8H-^AHF^5(TZ\dWAm^DM@fT#t>^(X*IN\$kbP=lC?
$g.h2Vj*W%025"rO628<BG+D/0-W1%qJbo_!b$)b<;T(m6iPu892I@S8R+>C#<cK^s.CG;1X
jk[a'oYS*RDHQ4H==pdZ1.YO&t%f(::s#4+K,&CCcGmqg4Qpg2\8Pb,7D).h:]A"]A;BbfTZ[p
cQe`?B>7u82BMp]AE7!36L6=sm4d:5;6!d6RGO!QqYqB*7-M>0s$:O,fi(E]A.osR3&]At\hWGT
Y1>[1qt,gK!*aHLHYGS4GCmo^k->=>bFIjSJ)[LpT9BNG%D1,4+-5pI"mMYF92K`E:2.#9c1
q7<0M9l)?Njd64cq@X00:Z]As8)X4rgM)Xg>`Y".=G=D*j)D[$Se@(?3H,Y'1XXT[!,i8,cPq
,6oA^%D9.,9VY]A/7t+3O"$7,0f9=7'9_B/m,Op+1V)*TDO;Q=opRsjOnRskSO,?7mI-k*^]AX
FGlQP81EEH^qQdbH,kW8iZdEZJYQpNjOd$s;1=SC/7=;]AfChgHYs20kgq0CgTc:MR<6EV#10
Y5p&qK4<H-8&q#'q5!WKoCl:)4HO,*U>E[sIWVX22;n`5o8bu&npmD5Lh@Q/./W*6NAj.VgB
pgV^'.XkL95ok-Rl#6U)#neD,+>'/=o5;@e#;uIlr'I,L)e&"?hWI-aRD[cS!66!lA+?_p6"
p24=:I+kO;Qe,E[JA,;AM+kNY[p[+fHY6noL@'_d:r<;(A:-)UBa/frk4Xr,mLKW2E]Abbnm/
P[A6otPg^?iG"Q1XTaHMGQDUKkH]A[3ee7Q),CeRoZUS^.kO8M4#qg1k``4Mi'KWK`FtJf5b'
P'2*R5imjaH8FqMEX&SpI"d9ursY%poZ@DGK#pjW_M.kF7>O4M:6"dV)Q8VaZP<V)?nAu\oB
VuY/PJn2&MGM:m5q3r:RW/=e:$,67Kn)<^`>7GQ-Kd!`i>rR)rMhDKaDD:nd;"@]AM<;\p$1V
KGC/.0Nfp.H_mp-ngRPA>q/JNK:lB@e0r1CuRt-AdhT#<Y.7IISn'4VPXSPG>=8.ZI!5)&Ho
Q,dM@q"P\DZKg.B<P&P."Qhu6V*G$hCD]Aq&(n.Mh8P\Vg4nTJh,gbC+.M?[1od6J@!+#*`?5
K@,;^%b5[6-N\BZJDQSqXuSmTDk)Fb2.6:Xf5_g\bGi*gEH7T!?4*EN):(G,Z8>^61+EMb:W
`D[Zf:iri6nq!OYi_)i<p29[J8<0BR`=)<-K`m`-BOF"/OtG4j0C%#:l>M83_ab(a5RCg(/*
9<lY_%Da:BE>adb%".F[3jP!DLPgQp3?4k?jKHuY@bU3Jm.U_[8&u/[qBdVN=E`6WjA82gB&
!?cFLu"d?s+$K&MiYOAT]ASHS$dF0\Ui)uo'u[[C\VY\HOA2S.Z8oF`4Br.6g=`PUof6D)O&T
q!?]A^kV7RjkjC?d8aNnSci#`X/c%ig$aNk<JLMQKRc-'V.i;OsLnF7f3q_FPbb1TGA"/KZ/h
KJ\*GM(QUH^(Eheop\k7aqOT(mA/K**\R%R8S7`J@$N7ZHl0lAn=L49JI]A&0</K(%T0@uNk[
]Am7RkG@7RiS\?.Ac<j)(d?UK$p6O'EFha[lA-/%&OdB0m"0cM4)?@WGIai6kT:%Z\s#'/98G
m%+HX*=?'!OiEX!j2MLV"WsO\>LhdCQim^o>RW6jk.WU)XZ4mK`n%1X2b/5nAUMGY*;UpRB$
pOAZh62c]AkoHfes<nBk,O)a<6s(AWu<OrF)ijXBZX4YV2g`7lER"RR_UDarYt''.?=g"r)4%
uj?uT:H]AT89A1_Xt`I\2RHJ,[?C'mk4qqN=_D<isZ\`hYh')D)8ca7,e>8J%81U[T5?4[si5
T%kaF,"R'aJGREJOpM*5qs"4ZO-3:Z*$^k;Sq;HO>eG`N2[2*GW+U!?gQ^)f/R""<6=EZXdW
)#!#iX$GU>D6bmJ6i3H'tPP'hJGQ/)0-Cmu#QhcANbraaWb`Q]AmiX#8p&Mp%Ls0R[p<:4o\U
@TluQGrDl5="M+2$GOM!L'*i3CHJoOS]A#h-`!0;^]A!)!(79icRADod?nXW"P-LpgM_=`Foic
Kmd2U7s)VuD3+eni$?.:8itoeHh.QT4[\a=KYXEc0%<_(^p-VgG>^q0\E?[ld]AHkWnG@p)V1
fLqF<]A5OC@5WONtJUZ.HLVCg_WP[4"K-@uE/'MF2?Ybpe&#u_T(Ff8^X[(]A1`Ms75]A?`oZA1
+XG/K"M&9?KUkuX"t"<$B7"k<jOZ"=arSQ+eeX,MGjja/B'7Y'kjn8/WB1X<;8VjP^F#lD9f
IW)%f[_[lnUA3NuoAe$uQJNYIsc+Q%>pX]AKM<,[=_p1n%le,X0+6#;5`=`jf)js+j(nZ\r!C
)o1Ztc`HIZH7]A!?nOoVm/migjVGJhr`WRh:"OS&2WYBH)Ua6h6LM$u7W"8dq4CJ9$-&O:Eb?
k+DLljBHGb4b9QXmO?6&Uj+G/RGm5,N#iXN<7]A[>C/Y8[gCTI\U)JDu-UpKskuo6E\r-6;tO
<n_F@=hYO85OO,T2S)j6"en=+QpXe$-f&io["YZ_<]AB=$'E_u2fAr`0,2N$:,<3\.EI93ca.
5Fn3P9kGrgt2>=@u*o2ELY&kiVm*P_X;PJg#I&Da9-;2@)_\"X5Rt/jK:*ZLq4R[G!XIGhjV
Ai-r\ElCYoo@-s>-MVW0oE3r46Ui7e.:ZHjcc]ATY<+U&;gjE2Td75a)(Wrfsf/;l=B86BD__
g6%>ZiTNUm*?DCbSW(OaOKt*4`l@LR_r_3tah-"'qe,fu$T4VPrpL/!+h"J3GYOPuV_9&GjD
Z"b!.*Wbk-=rqQ4?flR<.7Ve@,1S*Tj#W+8\@$OC\M7A-Le9E]A/._JROPnPNA:T0Y[q3%C6K
1"tr$^\]AC7s3L9%1XJ&>\/\KJ[I-/i1#0";k)I!aMb'p=kH;Z&;MWq*-7%guJbV9]A@/@*Hc!
Zt6)/)Kn_9gp11R4AnI<\3cL@8_q*m19'PqopbaP)0?F&$4@.>La@Z5\d@"atTkg`ioi-7!R
CI@3XK?m8oJ$jT`\=@+iJ:DRj0HeBtPN-3J"P03bH"m>ssZ:oEFGrO@eJ2bJc)8L'FFO416:
^6[cPM`X?-Ja<Wk0+fH1*(Zrn_(RH`Js!KoJLAe^Uj)O"?CW*RhX>!iYY\^Y_e,'V&$ESoT)
$5MLC1S<"QZ)+)jcWj;dYMj#B`q>opTX!(\JDMgDdL\=\3$:)rPMGo3R2/ZcW[CP@1A2G^&$
CTZ30R4^V%GAlKTnCu,b(;Il(!n)+KDX[+Q6)!tn$c*35O`^a<=0+&WZ4OJHlU:Z&a,i'nu2
#C0I3TnN552fiR30@S"gN1&SIstN87CT,'7=T_dK!L,.pg^F-gbn"5?_o`qN'2iB,TUGD*#3
N$>8'^L/(nR:[U5&MJ,acjM6-fdS`4n$.T<?XdPT`ap+r.MCVY[ONq5_!]AD9+4'g/6^*`K&.
`jMY7l7a)eD_P,2.(+tlHo,%I=0Yi[dP*Y./+"aVA#]AQY143ju-@TP!h8WIB`3?ul>'Ks%g7
IT98(&B0cR`<D.mTOhi(B"BE@OAViJ/OAGS#6aXp'H%o#AODA[)gn1[q!5ksoTtYY0l_L-+1
^g<??c"Bh'I*pQt'AMlg/1A>!g5=TU7>"*F:f)e)g>mqI;g?7[FV-,45Yhg^2c@EU#+H*)"X
:A[g>\t^^[G=$J5J'@/7h4R)V#l$\KV`ZWG2Qj^lL;5dO+]AIk/8CYQO$kn<7n+f^=Uu1gK1^
gG,tTnP(ksgAcU86m?@&%Xk7./!'3Ik6?Ltm=>5>N"SaIpKf%^e$,IYZV2,?q`pbm+25<#*W
LohhP`s#<7!1b:aeItB!@onnP5'aeD=#0K?%P4,jq'[#\D0`ZDniEF4XX9,AVKr.f:M0:<(d
Gi73Ne-oJ8Vb_/!E%Z6r$U"p$heI@Z(k`DF#A0Og!;df6p(hV3$1*37:./\AA)+8!08F*UMd
cQ+%,8#@*,C%^$*5rpL'NH-GXH&.69Q+oklOKC!dY)sZTdiKDL5:QZ[ZC1#63T,=gp]Ak%]A0d
igN,o&2$I\BGpeZX3T0Hlc:m5L%Y\U]A=8N$I:Gg9dpBe!N[86^lk'D>"`KS$[FFGB2if+M'T
e#K'pHs]AuJ6pF]AV7;RAEa?Z`73"nu3""J@43VGL28j0R<R:nmqM-%<8OP*FuLuO21N@KUeC7
WT^f.KmiYNQi*HRO^n4mJ>Fc%Te5HLFn;mUq=KG::$+N2T9aS"#pf^Q0+XA.mD33R]A$4:P]Ar
"jSVS!H/L!H1Ui5:ijNuMGli1VgB@Qd[:=J5nt";GkDYBiKp%c%?a.Xo5Iin@7E4C[#&XC*F
-lE;Ak]AUIXa?3FS[g,r(n?sgkg&c:]A*,DkdTMm`49cS:'Ka1ICn?q.qh[OL+&>]AuqZW`fA]AF
5=bZJKF).SlX'@$W'+:BLEMAqb8A/H_Ji0ga$8A(XrLL5/ZgR)bZM)$NRshDrDY)!)&$XEO[
scB)Ps@@P&/A:?#HUn]A[j3HnF24,,pO"M0h>oTL[B!qMkgX,hf';`iUc9N;^fe:s8EXY9.,G
/!@O!\s!8j`j'Be>'>R8,V![84L`noorLS[$I>>m)?89]A'6>QCTQ!6SATs'8?MF$>9n"9nnB
a6/oAFiSqh-A.c2KD!nM_uSG'XAdk5GeuC\1gY(qLaJItRaA&bmsDS#c8CKB[\#K%rYi;s'j
'/R!AA5.*Yo/pi"0C(?X]A9)D6iglcf&1f$R2rMkl%<PI/1fq2H1(TO.RP@i`hZk82=GJ#*el
hsC14bHW)T<lKLq:_E'gA0=l;TOj@ERGLF8^'*t_Kg@L]A'Kd-#=_Rm2"#WoiF9.=Ik&bNE;$
=uPNMU:7BbD=es1(La]AoP89<s^VYj+DOg?t^".*]AQ5X@&Q.U,nW3PTR)Tp"+JNB"m89AiEsU
Q/),9XeL(e'?;\@^7LZ!R1^-u'B]AHWDPa6f'0R_[Qm3l,o_fG/$WH"Ym)i4_IHgAVW`&An7K
KP!=dk.k)g=s(KSr:ejO!TLEJmSE$OT71KYgD)E"On/lmmcd63h&$BoTYGjbZe2agOcJb2-i
)PPlisJ`Akc.q$8")SE?H&40e7(bt^92H]A[pZrjYd0p-1VCui!<`IJgGpO%p"VsD1ZK-e'*_
^.@<*I9te.qm,m]AUH17-t"sS@81PB)8^G-..i'(B25eBB^;0\?ue!cUu"ZX8*Y@$N;u@PV)e
Js[f&LKC+H2&Qc[Do7Ke6\T[X,(Ce1Ed/qe`m>9IK8o.*P^ifGWp;T"=E34\%df1?G)W0<2$
Rlp\`-dmP@:eU$\3lX>)EUtPo\>.AXNTqfa\7f<Fl:0a2s-k$C^j_%)X\^"B/S+B@iq'Cr>s
glejGqZ5oUQ[N:A=C`ZcUrqc\d#S@7ap_1A@^0js[.EjIio$@9i@eN!>M&rUk9gf.0u%R;PJ
3IlAn:h;=0.r^V1._KH@hC\=,`/5=!$nu,DWI(q#J?Ji5>od%S_TF'QZCbM32,bsr0K3SLn;
N2WA_F;62K@S\nGXH@_>Zg%F<0AQ5`G<>u9;S>tLpAJ(+82"WpF7scTK.X'i6"jmD/&ti,qp
M,;SX""daSBA&\f:FXiC29nRC2*EN]AMQ!m(QmPNu.@N,ra^4H]A_/)SZ$K^W/jJp2CJiE*2I*
K\V<C*'tjcc-'-D<QcC,F<fFM;(IsrP<s$9U?$2mNjC,1nJm'rFAhg4dV$_\?JIAA[mTGQ*D
W,aTRFJT@!NcUh1BXjd$U3+N!l8@2nbt(R*tM!dVWsOY6&$PR%GPu6/RBN1>m0EWQGgP*'PQ
fl\K3=_E%+cNgm:g.+f7o=L=oeo4e@a@D)\FJh2!(W6?AaK<RC<a0nGA)DisSni_$[M:)Zih
@#D<O@R"SUYFsXVKSu:7g,S4mj/Z4mGTTrlS,389=<JSX0jCB<ba?4Y9>2\.>>1[;\,IgDO0
V1=[SI3_u`>s>G!pU%JR<,GfJoNQ$fP)b6%#kXM&43RBs."o0LgmeSu:H$%/_=38KJBB2F_U
pXtYDW=FeRT6DDgL[MD9YnN`ApVR)Z;6qVehi]AbQ+A^iXNMS?JBI6l3G@<I892o&j"2ntjL'
okNNe9:crfJun*FGpG%+Q$UEVKH$]A4"lL\.t[I,1;Z$5%IoIqcO%2MCAucDU#g^NVm8CBLIu
Jh=k?n[TC*mjO[i!VGTVWL/G4)7Tb<<J<B@;-*M"&Q>[Y(r5s9>b5),p$/Q/KXTIu5c^@h=M
dc_+`<-cFp27u)h#<dje>r[7:pR/Tab"i&@GNoX^23*BpZ#Adhs9h%aI1VYGKL"5+0URI\f:
B>0-u'5^MajE3\Ou*f*?QZ4rNmaW&(D*&0.4I0%hmpQBC_pc-TG1&K73U`K4'*J#`G4;phQ;
qVHfQb?^9`6RHFaPA%T_PqHXBnW*g1dF9W:\VHXJHA!/h"LZiAFsPp\4Md@IrXess\'+Rakc
CS^/8g'HD@Mjc;kA4ARMpdm!7Dn$b&k6C87^6@.T"<@<dF:VXE^+)hbg7O1AkPooWk]A`LTq$
<b6+P5je0;"ZU(.u2I>KNpaJSl'0rqd47a8K8[`V<UrV]AbL;4=r\KMga(F5"tP==dXa-XL:d
I"b9W.T+QY(rLa#p9,d_HJFF%t0_pMH,o/\7eCE"um]AS7`S>2JLE-m7<jKc3S)cr@WL8W"Zd
If1@SpQ93d]A;a.uQKmX%";Y.sMNn5#<fB23)i]Ak(n_4DV6#XX6XPbL]AJ`FJ73K2%4#B\8B.\
nY!ULE:SN'HLR?/bGEo0QOmZ7DU\F\XC+80J5*\lBTpFBri>7ke<"j?Vr:GE%^2._`dABUL1
Z;:hrq\aCT.]ANij-"lJ(GuY`V4[JblrAe#ZN4@nb)uB)N6##3O\?g)h+pXela,mgZ$@&>O`g
)If-l.>M-6MX%[UP?9<V>d.mE9'=Ml?RKT\7+MSdD_lMS2=gXbH/jEOH/fY77kQ.1>0VP>I"
9EY[`=%hY%`D=hA7hNcMUQY81-.!JH(q=!cGJZj2e6F:o5'(ZdXo,(;OE,K0[4>jR@l]AZJkj
Al=",K[2i@L`3#^H-jcmj)?mZ`eF:m&E9JqF9,JGHT(q[TW_l`CTH(omcRlpQlg`2DT=^`\7
;'^)58"8]AOdH5F$NmV_gj`q[[i1Gh!`PLptRt;]A;f!e&,;rp'I1l>JeTr.]ATr_r$k(-uWo(V
qBDNH7i;>'c^(qM]ADI0s?03gHn;`j=S&i'DH-srf51J$_$nD]AY_AXd7f!<!c&lDZPc/[#+77
0=jD(^%)GkN[LI4'2">iYA<pjEJ)ok[!SP-U&+c7]AdGU1;f^CIVBoW4X2-:%u_Wt*0+Ir87)
.K*Wkqcb:ZF:6XqZdOgZNj0`Bnq:TAqj$>*Gf<V\c]A=P3-LELWSO^JYZURiWNhYh@-&n71V=
HP0tZ+*l?=0MisgG4(7VL$UeoQkS[W(7*IkYG#4::X*Hpo"BQM;Yc]A)',[/<3t1$OSC]A*Req
f"bQX.H<d.eamO8GZU^@TX.1t6,l6c;#ujb>VQ<Q^>P$<W,]AOS1AWJRPSe..QWRft:og=/\!
Uf9\&Cs>YeYTH*j22TLXg_73J,c<ni!?NL3S,1/lA(&]A$]A7I4&qN-Y*_`1j8Vg6mf&VfO*m!
t5]A1Y`49'\+F_K#ELjb0Zo(hZ\r//1Ha+f\38!&pRIC!m=L^9Z&7t5#'bIW0X8bAOW8oP2"e
V__#mi>*=10KF0O*6#_HX.+FCLYGc,VH:S,Ha#.N&Ws?\)384r!@(Edt/;jL\iEoTd?M\iGO
8TK[4;4,a1X,>O9SHH084h;NFfK*+)4"!!T+`?EFOTN\m5D%^A7s87=;"s%W27jI@1[`P;r8
AaAp:`(fUmm[Qf=M8JHtKR-j4N2mVmTR)!PB'8Eo@dCmhh_@LHDeaWUTJ8f'h?J>[:YCmFh6
,"meFV+q?rOT+N:ff?&iC:4;:;=#c[Y1BQ`5\PH9S,uAA[5GP`@Tp$m[ciI0cX/LK1EK=%j]A
_c`p]A)bc&O=FqqlP1<m>*^,K^kdAuTjT4gMRN-A9%>q]ABD,MpuF*i"[,)%*bT;MMKA\?"%^<
GSXhKQ^jH)S>hu5>XYF=nCP8M214<bl*9^Q5E:@57Fo&C8P*"8?o"Z1>]AG]A^ZZopH80XR=/r
%4D.2+ZgA<(XOjM]A9X7[uM98d[,MImob4QEgijbGk%r+rQO'pkt/nFX806GB/XPPnnbK!0ei
IBeaI+83cTOt+X/&\c?8C7*a46C$3h:!4(gWbD3mDj,cH6B&MAI89,?d@X-"Zh3dC^9>a;Wf
'aMeE9V6X-o75oDDWV*.-.LM$I@!l0#K'8eeuX-7,P2n6hbKRL6N=rTT7Zk)IehrhT/LQ1F?
3h4$*t^&Z9q^_)'$MO+?;L$sq\Ei\.oFlCWBUYmD6[cu8Jac<\4G@33A^@]A^*&[2^a_XF+*B
r\iFWH=Q'kH#66,LKapF!5?$S+=[rqi"+]AR27L=koG'>=D.0I@IpBjdRQH?YL<`koGR7+'P?
*IV85%;I?n.sWnr*::+e5(9Q0TCBO1C<\E/BHR8m'orRak,<i3S5:V&MkpQC>"?jQDjV/OQV
SH,#'mt5`3p^3*ZH3$,RO>,^9aUaK6YKR_A]Ao3:3;K^3-M@m8SqYY2BioM]A$>BJ-eB(n,B>.
QF*mk\\7[3I*R(0Pr22'm#&UZY(dK4fb8e)W(fh8pDeCD`do70*cWKO?#FCnLR%o4NDZh):q
2H,edD3!I"b(pVbJ7J$Kmm;+d=g=sa,WAB<"cO&RI)QURgU[jt*[X,[1>&3+'-bPWCX]A`AM_
)JELNLd&n>aVC&Q2.CBT12p?VZpMAbr2iq#%/rBY(M2U,?+m94P's2*HGs2U4,RnA3>]AQS.5
1P"_j11WB$]Am(TMSV(;=[?]AaZ;UbW^Uqb>@m[&_KrZ:D8/7NV0bc@VDCB%d<V5U2EI^!ICGO
IgI4h?<R1o^%2&q2u<qH/0GL#*P]AAp?Zgt>p]AMQ,lHNShC5!(763j')QGjlnK+uL"84ZV]A,0
lf6OjY>#g#KJaIML);o6ce4FR,I_EV$QA%uZASD.m=E+/^I>PZI$^Bf,'K:qHY*KO!jr4qee
9/LI+khnI&oVHV*n5M2g-.maYQ[]Am)COb0&<5+A-'RrG9nh>K[7JoFL>/hl4.\5mG^MuSTa?
SLRJ8?XIA?Vbgd7d_l=0qC`\YL;\,2ND[jE,GnBDJrJ!"OMUldm]AVN,rDs=DASQBrKu_BbEj
7_Le-lIq6Wd$iPFRCICR,ngYobdnf2R]AQ>@O;n&*VD*OMr=Ppi=XkGCFbdu@;eq%%b3JhsKo
7m"2.h7>1Kk[mhM04VIoqi8Ir(*'e'5NOetIXoP;AfJ2$#_EP(+48hcELo(?pFe_,U@eU1S.
qdM(\]A$$^>9,H&b6uBCl!pXXfed9+mN_irV`K+8l`7$5-r\;5=:gXQrGQ9abgEl\5cg@;/^!
tl>__u9\%/?@V,C@n$"`%RHCt%o;CnGRm:>*H1ugK4o=fA23Vprq*cP9oJMM*]Asl$0YV^r2N
cM5n=gprtWH7?IA]A3^>GB[!R$dTgE:L5X):J$mpq\6uPF!I3-o-04VUWe<)8AADLSpSW??4^
FY.e!WYj.eqmMF7@a=f%_d/n&!5r2t[UZbmdZ4U>9!Y-SH7)$0[ib#,9`)thsA;#[:e*1uj"
^"#Tcrm5Jsp"U2eY-!5hOIdm7$6R7_:Y_2Qb,!VU^tH!3HgA=`X/(B8lG/0o\$^@TSr%]AlcO
)(ciHJe>'jeskRQY9>eaY<2R7Hh7$*(aRP2-)2Z\SJY;KApo$<`VG9W@l"#Apqp[9Q9T:'DN
RITu!`m'j)DLaR#L>MYR,nQg85P4`b8eGo)/b'9,sKG-<s.\6qPMnWm5gQNF$Af=?IaT5Z@O
Iu!]ASdPT"^\Z^VI6]AVsB9<.q=mT"A:Hu/DLh/h()GU3,'\'Cpl1a<8cn@*,3N!6CKu%L`D$?
,TeYU5Z=..F:_$n\X=^=_D5LA@cX:kL"Fl\#L]ATte5Tr;K6Er`HANcWK,UD-LY\Z0"\3gBQ^
#ahX//gk\\_Bi=aL>6-sAOl`1@bO3LV,PI[R9IhgEVq0[1S6KYir_c:>U+^s/dEqbrL]A6DOa
uj09,@W]Aetok-m8kO3A$`)g;`i=T#>.>@/!?$Q6J5'%LD>fUKl5'Crs5"$c=<HkSFXR%$qIC
]ADj&e%L+RR\BaJ]Aq.<(cG>W!pT?.hfrjegneEu5QWViS(^PX3X%(qH6I+pQD.#Yb!HQ12dd)
%sd\e/PnJq/>-Y9LO*YOr3oETa713kL!RqF,(T#B'5a[^aA$m`GLF\A;j`5#[ik\^XH0U/OC
DfPtM>8/.k?UM!h11%C)tHO<-"mA`'(SmGV--T5#(0ZHV72HRb"*Q*cL4I[abPqRR$C'd1l<
$"W5P+8ImFpg?mI6@W90_0V]AT%(_qGmbFc@FiV`loLj;FGP[L1']A5]AYpR16kq^`Q94d9I"As
BCs@7,OU_aQ]ATLRTB%/!g]A;_LRn2]A`0&6o?'0.X,M3@8`,0P2#3*$_$cfDLsll8LVLk,ErN'
!5q)/=rE4f,H;aAXepNam3q4$DN>#XIcidR%r9PHn=;Y39H(j$n)giWA[1p6SC.6O0pZHk%X
KGO)?lo//~
]]></IM>
</O>
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="2">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>;cgCt\;6CWG1qMu$FHA-@r6cjlO/;tWRas?7lo18+QQt1VXb'"0t]Aispn/WW<-neNK[
_J=Plq00Ruea"T43uk&ql?7U_lE@!C@Di89.K?XehW6GIb1]AEB&I;I:VfQp?T3=qQ71!q;T:
DJP-b3>AReZZXiZSmsJYUo3WIV)dC.QL!VL'SHJ?5fS#6#Icp*8M7J8%\$Ib+8LJ\M$d9ns:
R*sm4[\E#TeBZTglLjtS:05fG)8_+Z.5`V6PDhY@dNN^W%&^#b'6^.Q@ZY?.11IJGGDcsrTs
oV`l=e;n8cL'l>,coad2DO8!9Ut99[hHVeE-_\MM<PX-(QtHZY3]AJ&[DfFU9f$h*4r4No)7'
3[^j`rD>.(l#ueRpdf=uLmK,/?0l3<c<d\T?5ld9FZV-V@XZRdF#ZgC$smP2Vs7XfZ^AAl0'
L;?,%JS(CCa%SZA43dg)X<ZB?C?a^ME5qLKsDnBcOPt%:9X.Dmo@QEaqcPGJCi=2KJ``Pb9)
2R9L_+k?`MjF^&F7bU?3[eIQM?A4^8&SYX8+L\I1U[CKcF7&iklWa$dcORb9^09W=mU/4`pV
P$>-.6T9h:8hea<a[,^`s!o(a_+HaZ@6ru.]AQ;Y*AnHo?L=#.dT4u]Ahf1roY5ckq?+EmBG?.
7_0mg;b<"8L>4<NGr8n8'gV>k@RR)FAbh9Ki43h!2MRSB**5>c`hFm=1g,GU[dpZ5_;E;fW#
_uF]A.N(JjH)nW`06-DBL>*n&`0IfNP6A@:N`&>4%Ok:m_jm9pm5[n7mCJr1.Qi3K4n\Zc7T1
1U.'fV>/(f[uCW"Nd."S963SGI:p6F:GmX1WmQ:-up8Fjs`2NqH3(P:&\[-=fX!2fGn4'Dt*
8Q]A7.rOFeT`,5i5g1uf3c2Nfm@b?0<mepWKS7+$5+aW4rZ"dg]AOB/%JsG!S1$;FJc[-9?TNZ
[H)O)0/Bdrl[qbL#tiMO<9cu7,GTPM/3>bOZHfS=+2N86fK,uZg>%==sP?9PjDNGYeWRpj]AY
@NRBjtU^NIEWI*KrCp)4eodZGge),QltEirJtoHU(VAp&eu=$<rr"gm'IL1:911P-A!0m*J4
8Tdo>,Yf'Ia>7Ll?0cm1#XQh"eQ>jOZ-PiAD%'W#6@)Uu]A.8(o&=GjsJJB)\9S"TfT^1Hp%-
WXH=qA0P>`)D.aig/?0p-UD`s(_OArJHGqMF"N/1rqnrAQkeRU35JalC+$<Q#(SD=?F>$3Hc
;E>Q"Flo%rg6fKbcD9E$*\rZQUoJNTSU81h*O1;uWLQp9ASessF[nJ3%Bb=^dg%9[eh*9M/G
>^p`,MYl)`WNIpe$SZh2*,)V03S_;%#p`,R'*jkIm=[5'MO#8@C)VOPf,#aO.A'oZ:apu6i1
-8PkSU@7BS/X&Vb=L\=iL\Y[)8k[oM\$/j`r1RBV6hqfI5\nPu+G0:cT3_Cc19$uI[3]A5.4N
771JWJXKVi2i#.abMtE)8o1P=VAe&hQ"jT.Po&Pi?i&2$p7bKNc<6FC6IYo<8GfF94)$@O]Ar
-XWV^0,9:"I4A4V^"M7+Eq1Epko3'O=@q>'RH#M#(A7f%Hc>BsoFG=dIH#b=LY9NQ[6S8W)'
-eo#d4T"UQY65.GrVk^+T"`Y!fbN0VL,S-K/PVtQdj=rZDVA!=Q;e8dmQ@1L;.3mgk:2c1d0
:dha`X.$mp/\q1Af9k+TZW2K/W'O<9@duaVjZDA[hoH7V##>(Pb!)\%A5,@*2Ml;[F<FZfeT
#T$?)GDko%V&)2eT[@`MLgPre)<oi278[I$m&?_1uH^"Mf<1rPPAc--!]APVsLGcu?[8nd9iO
^X6^9Fc+S)hS$ran8Gk1h56_CU]Aj7MC!C@X>Q)j56&/q!?b;!jZlD_F+RkqVaT9kH>")?sBk
-05Q9JL+#ue+8a%JcV@Bo*`NjNS:YOWd?=(,@7;8<PMg5F1(>+)?;(mVc*(<JD_f]At7p8K7\
'QfF.gd:B+^DG@H`<2Q0@m6T"*KtU6nIK@0_#WfN]A6HuE8OG/dg;QDoV50;\me[JXS*\qS5'
X)Ola)W67@tP.moe_Sd!^kEjK;#.%p"/TVqGiq;0AJoeWo=U]A.HE)qKa,:Sfm\%;Dhk3f6_1
)U>"gEZ7Oo7#>:u7Fo"IEN=UZXbe3$Ttjm`s@&UXFYkHAtJr\VW7Aq8"4i/XUak"IRjkoshn
OoYHb&4L2.*-`n1>5V\k*:hC8S7:a$NM3okI6fuDIO1`bD]ARYh*<Q@Q$knh1ZB9))*/%4:7j
e^KNZP3ZDAO"S]Apm8/A:`j0Y\gFPqf4u5V6O6p4D9Ti;)abr/JgJYaYf:)QF;fP=nOCPMkm+
5N\ASN%3_'"Z82CXH^a'%(Zi+?k.%(0BscA8ZB*AmKB:r+E*++31>ZuXmD'T<T;>W;*;oPf!
u?O'!*W`m7&(g#RJo6*9G^2[ru%GL9@s$k5e/Z)Hfl0Je$tWH'3tk>MS1STjUC+$0eY`0)iK
W3.T0!L13r?>7[h*)*Nnk:Gi5QhD!M-mo%`jgg('Xh<+'<\R(GF8is54![([1ZPCH)PlJUAW
MlG`EE:sc!du,HI:+Dh)#G_Bf:UpKQm8[AeB(%RhYsr(tj`hSgLCjBoe1l8:m@Rq_2#R^A&i
7>C=742A/F*U5h*9K`;;VgE;)h@B^[$VmbP<9A'CJHh.G%dBI4;lA.dq?6c`Eg,4.S0A&stL
9md_=@U]Ar\WX-kpo2Km1]A>'WSE2._h(*gM`#RUZg6j>pXW84&7h#DL2;.f8hVHPOpl>1-1m6
L>[URT:cUgS$rQ>X('6S"a,-9^dO?kLCXpmCGI-+`.!4da?c+*"eW)<HSLnIr]AYa)uB5.ifk
RSBJ&0R<,LulmSr!F([OS[!r<+k@P$:&)Z\s&Q+8=FE>ta!R\cSq?BIYqA+0mRb(N48dfsN1
;J2KMW0^WW;H!39#cD,DK\N[rdqGd-k_fBu.\iEM_3"Aq""`'S3)X*VVo:>pCGa0jb*fWZ"W
_hXZ0-8`3&+gRTDml]AlK3\MW1?k!jOHbh>doooi%+tPS:(ERBqqg3L1,oF05VPhG29VEO4Zr
4+8Md2IFFpa):rHI-7(M>BZ"t69n:@0j0bgc%kLd>S<\lV8"!cU#fCM3#\+gJf\"%s/(4*fm
Rg+1r@N?S;8Jdg*Q>S@n!^l]AcPpt;G<H=o=3JdZZ;JS*BZc3R&!9ef\V29FHN%-19<;J,9dt
PFP]AFYIga(p!@`q0Ko.,pO4HZ__-NmphmZH2fe:!V1>cPs*)<egGr!Vf7EILBl2;NPmUp6@1
>gXu!rLZtB/nco8E:/l!/#D$&>>..g=69rBiIU1UZ\)hFJg2?n2ge(Bdf;MV5.b4%'EL9hm)
%G]Abf"/dn=.lgY_nX"ZWrJ8863NdNRk6p(qKPIH-X>@EI6s(3tMkj,16bU5DNb%RB&<&iQl0
f_[FgRVmnQERU.Vdl7f47?\I72P,l$R]AXh!i:%7M0%\d=PTj]A)ei'=lI:#3.6:1aP-BJ8WW9
-K,^@:>8+\-N#39*R/Or9T[1IQ3uW71]AcA1._CLMN\#"OUXUV>Wd[#)<lON"ph4]ALY^TcL?4
0Q0!90qL_"E2`Mm&i10oYopU%o;m/\5n&4,O4_5-h-iM]AMrIYMpdc#3(68F*42Fa\nf;%Md6
>2pA"P%%3bcE,BVZ`dDm*n&5E"bFaN5<]AK[H>.Ie%(rkBfT?moX?jupP.$,WOr!eZ>%,f_k_
d[?O=14h^L1b(6/jW2j5)u)nfZ4QR0L:H:$mtBEp%^i59]A7IJZtk10Reg!OooXMOr%__%-E[
q(Nb[M0b4!qAa?E-n:!FXhetB5[,Wc9fq8mc*jUhQA37r&5`cF*k9Il2B/%EB$21G_#f!aDf
,\#Q4BsXt\`RbuRH4cqFs)g19J>"KQ9LXm:-Y'L9_9P?_#.aL;SZYJ!F>rN'qj^"<-5C.iL!
5IZH*8Y4-()eT[SN#K<cTAhu%Ek3ei/BXPVlTlS,!i[1")"X4IC*M.u';(UQ@;^&rLtcS?'B
0,`ERVusQ^O-preRcXYMKE+,^`&=pL3/5uFq#A\Jo!@NfHt:4LjIr8l&A&?(?cDQrW4m_N^_
*I"\gYa[SV^(Q!eRO%b"fKqeoC0=#5T(;kdsoqp?EXK5(N<q]A+mMQOc*VnfVcF3(=;uh;(Wo
1]A^3_&W5`UoCF6Z+J9;[f>c--K/Mk2[a6k\*jU;hSFn[NDnt_uU5qSq^j5pI!2P%qXI@Bga]A
URbIF9FGHWL2;Vb+F;t]A4daPT)Kj&]AF!T-60C>*-p<Y)pt<(-.T,[Vn9j`KlfBuE^mptH?K=
18r"lMYNT1&]A8IS!pJq090o?7[(WI)"9V;VIN_dKlH>ps1.-h3dmQncJn>q7@#Qgt@TA@hqY
APM(8Mb7;mO!HH27?>;J3r+XK7)mVRHJcktqG"`@Fgj[j:>P)&.Nhnc>MN/?J%UD@K7KlYj%
m!8DKDak3,d8Y[-;Gmd8k:mgC3RHH^c"[=kcQ7K5/D#"%5H]A6/i64D_bUfE7/''q@Sr+'Zqp
#2'.9McrM$R%BNhk5tMb@nIF\BAZt*ldcY?=Ih(k+d9dK`Qi.'H!\RfL9X!o)(c5_L6XC8H2
09A34:('`]A8;3:,qS)c+FEM#Pr_m8L@$,ahK(EQ4G_oVM-?N"]A&HZ5:Y+`U7^C2=3'X<0h@+
.J!PK)ZJ9F]AG!be0(0$D^iIAn)ni=kC=!oFl/psu,0nf2NN\@NMC,V\4HmCpC&S&)OElQUHU
HkNZ<5Pj/(iu-M*=I3JKSl99G?bo@M'*n6dXY@UFjRt&ubl"#n-g%j9*PZ@q7EmRS&e`$KKM
V`Fm+YEeNAH)=]ARZ<'l+:Pu*A0a!"<faB_Jb%/\99:KX^IF>oBuEZb&<J:3F&f<#mbERNB%f
n'8E-kpjYj?@R@1rk<&Gm>KF!^T.f$EQYpD8]A'93Y:):2gU&qL:`dQ[Z8!6SSNWV_8a-8h<A
Hb`?Ja.P?L%=NQ3Q[C"d$:rOp%!HoRt%_DrZFsS`X'fdT36B%V]A1c&"?o!u5[m"'F,>pBHtL
'c>+ZW"blbtHhDg*+-BsuRO[&0]A6'ug5>*uY@"g-YjnfL\e)FBNF^an$t/#m(FDE?GQE/DB1
("FF1DKS4(^AB0@4g#^Ue4kgiBAQDko=f24'?h)#&MV#EE(-ZdSsPY.Tb5qj^heid-^.2'!:
O2ZT[2ASBE:+.jc/`3g+)Ch!MF3JI]AilfjQ!V0"Dk@m''KTbUQ^+pM_MqN>!'6d)nkAA4\nb
F`s#a!;(6qTUHtP?mCd?i.`lT>b/#IOf%`-2>YHM8CpmAi2M1:Bd7j(B#6'eM=:52/U4mhRA
U+YmV@]ALW8MN7n"k5DiGBodR5:I$>3lKCa<.B01pl\*K*CS7-,gQINn8`kQUme$HahB]AHDhC
_G*baQV?Tl,\UH/CIlq)Onhgl"%SgqVrc5(4)M"a0X=2ni]A/'!14AgBU9a39#T3<siQ3Fr]A)
<dtuBG^VMC._S+/5O_ZnmR#=3?.m"K-8\>Ti)2#.N]AFhQ3t&[H#OHIROre@'!A=mei$U$=G_
68O]A`(-9FH)!McMZnFk3lXRB7tQ\#;:dLr]A86e:rt*)c-jpB6=[dD)RFN^Qk:U3ISgQ]AUJ@J
um6K:jpB)+Hd%,eUn`j]A99SZOoV]AN8H!R_4U^^3siRe9X/LXga_E@R]AB'!<pCQdXKI$ZG<L.
"]AD9?Y55db^oICk<!+Y)fBr,K*ZBU(omfLQ0jN*iEb_()M-q59`@="\=?Li2QRlGSmA-QhLQ
CJJ!0VN,,qiGc;'c9Y?O3qFj!5[K)C,iao"&)-qWA:`[;40nhg*>h,@)QOp<0_3i!iG6SkW@
T>9oq(o!hIfVfJRnA`5^JL0`t_5#,2RQ!JCN9aZ?4Gh,TCI%)SQdWujf,a"OHHP3Ada84j.A
CADfo&Jt&)mA*WWEU\(+>Nc6Zq%BUI.oR2%/C!!0SI%8L]AA/AbEg<;CDH4%\J#g!4gtj!;@E
?o(^Bo*e2a.hXi0l;q%\Xp]A0KbX/6pH!tU!*$H!=kGNR?=U%=9[lY<ss&hc[4PC<05p".:_N
?C'AHjTdCS4PdEil_eR6D"(^C3Y%fZ(LqmU:0*40;*"%]A0u%e-%a';&W\`9Y62bREFL=cdnt
mFq!=g&fH9aMo`1I8DoWqe4"a2a?Nm!J>q3UDEl"Eum-k!_#cKsO@nrV]AK>Rm9OO`f6e\j:*
GmTk>0RX70po&=\RY+-\G=$Mhht@D/r0"u8"!gZ;ge0!'=nAuWJUmlXXE@o^$&d=JQ+Z'>b'
+Y:5UHF!i\ES\_Jr>Q*]Aib'ELQlq]AQjA`F<r6;OGBP2JH><p7lICC#5rmZ,8&"_qs6^U]A67N
W)rP$6:V".\TF>i#DJ21*:OBXt$-Z=i[==Y..C8.c:mc!#^qD%hI?jhdg'2j]ADqabWd0@HPr
W`~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[914400,762000,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[性别]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[年龄段]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[学历]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[合同类别]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[职业资格]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="5" s="1">
<O t="Image">
<IM>
<![CDATA[I@=eJ;qnb$BaRIj'PUI1,#SQ>"UJC5U^>Ir&YsV;Qen3&&OK"PKH_Fge:;Y:82GqU!sAl4!m
brVHXkhchE>M$fOKE`C[V"jF3VD\)S[#^WNJR:Qgl[L*Zk.#(J8W8n-HN&4?k%#rTDI>!GKE
hH[,ReP.V8AZCc3YoBgW-r01TTMfU*B8H-^AHF^5(TZ\dWAm^DM@fT#t>^(X*IN\$kbP=lC?
$g.h2Vj*W%025"rO628<BG+D/0-W1%qJbo_!b$)b<;T(m6iPu892I@S8R+>C#<cK^s.CG;1X
jk[a'oYS*RDHQ4H==pdZ1.YO&t%f(::s#4+K,&CCcGmqg4Qpg2\8Pb,7D).h:]A"]A;BbfTZ[p
cQe`?B>7u82BMp]AE7!36L6=sm4d:5;6!d6RGO!QqYqB*7-M>0s$:O,fi(E]A.osR3&]At\hWGT
Y1>[1qt,gK!*aHLHYGS4GCmo^k->=>bFIjSJ)[LpT9BNG%D1,4+-5pI"mMYF92K`E:2.#9c1
q7<0M9l)?Njd64cq@X00:Z]As8)X4rgM)Xg>`Y".=G=D*j)D[$Se@(?3H,Y'1XXT[!,i8,cPq
,6oA^%D9.,9VY]A/7t+3O"$7,0f9=7'9_B/m,Op+1V)*TDO;Q=opRsjOnRskSO,?7mI-k*^]AX
FGlQP81EEH^qQdbH,kW8iZdEZJYQpNjOd$s;1=SC/7=;]AfChgHYs20kgq0CgTc:MR<6EV#10
Y5p&qK4<H-8&q#'q5!WKoCl:)4HO,*U>E[sIWVX22;n`5o8bu&npmD5Lh@Q/./W*6NAj.VgB
pgV^'.XkL95ok-Rl#6U)#neD,+>'/=o5;@e#;uIlr'I,L)e&"?hWI-aRD[cS!66!lA+?_p6"
p24=:I+kO;Qe,E[JA,;AM+kNY[p[+fHY6noL@'_d:r<;(A:-)UBa/frk4Xr,mLKW2E]Abbnm/
P[A6otPg^?iG"Q1XTaHMGQDUKkH]A[3ee7Q),CeRoZUS^.kO8M4#qg1k``4Mi'KWK`FtJf5b'
P'2*R5imjaH8FqMEX&SpI"d9ursY%poZ@DGK#pjW_M.kF7>O4M:6"dV)Q8VaZP<V)?nAu\oB
VuY/PJn2&MGM:m5q3r:RW/=e:$,67Kn)<^`>7GQ-Kd!`i>rR)rMhDKaDD:nd;"@]AM<;\p$1V
KGC/.0Nfp.H_mp-ngRPA>q/JNK:lB@e0r1CuRt-AdhT#<Y.7IISn'4VPXSPG>=8.ZI!5)&Ho
Q,dM@q"P\DZKg.B<P&P."Qhu6V*G$hCD]Aq&(n.Mh8P\Vg4nTJh,gbC+.M?[1od6J@!+#*`?5
K@,;^%b5[6-N\BZJDQSqXuSmTDk)Fb2.6:Xf5_g\bGi*gEH7T!?4*EN):(G,Z8>^61+EMb:W
`D[Zf:iri6nq!OYi_)i<p29[J8<0BR`=)<-K`m`-BOF"/OtG4j0C%#:l>M83_ab(a5RCg(/*
9<lY_%Da:BE>adb%".F[3jP!DLPgQp3?4k?jKHuY@bU3Jm.U_[8&u/[qBdVN=E`6WjA82gB&
!?cFLu"d?s+$K&MiYOAT]ASHS$dF0\Ui)uo'u[[C\VY\HOA2S.Z8oF`4Br.6g=`PUof6D)O&T
q!?]A^kV7RjkjC?d8aNnSci#`X/c%ig$aNk<JLMQKRc-'V.i;OsLnF7f3q_FPbb1TGA"/KZ/h
KJ\*GM(QUH^(Eheop\k7aqOT(mA/K**\R%R8S7`J@$N7ZHl0lAn=L49JI]A&0</K(%T0@uNk[
]Am7RkG@7RiS\?.Ac<j)(d?UK$p6O'EFha[lA-/%&OdB0m"0cM4)?@WGIai6kT:%Z\s#'/98G
m%+HX*=?'!OiEX!j2MLV"WsO\>LhdCQim^o>RW6jk.WU)XZ4mK`n%1X2b/5nAUMGY*;UpRB$
pOAZh62c]AkoHfes<nBk,O)a<6s(AWu<OrF)ijXBZX4YV2g`7lER"RR_UDarYt''.?=g"r)4%
uj?uT:H]AT89A1_Xt`I\2RHJ,[?C'mk4qqN=_D<isZ\`hYh')D)8ca7,e>8J%81U[T5?4[si5
T%kaF,"R'aJGREJOpM*5qs"4ZO-3:Z*$^k;Sq;HO>eG`N2[2*GW+U!?gQ^)f/R""<6=EZXdW
)#!#iX$GU>D6bmJ6i3H'tPP'hJGQ/)0-Cmu#QhcANbraaWb`Q]AmiX#8p&Mp%Ls0R[p<:4o\U
@TluQGrDl5="M+2$GOM!L'*i3CHJoOS]A#h-`!0;^]A!)!(79icRADod?nXW"P-LpgM_=`Foic
Kmd2U7s)VuD3+eni$?.:8itoeHh.QT4[\a=KYXEc0%<_(^p-VgG>^q0\E?[ld]AHkWnG@p)V1
fLqF<]A5OC@5WONtJUZ.HLVCg_WP[4"K-@uE/'MF2?Ybpe&#u_T(Ff8^X[(]A1`Ms75]A?`oZA1
+XG/K"M&9?KUkuX"t"<$B7"k<jOZ"=arSQ+eeX,MGjja/B'7Y'kjn8/WB1X<;8VjP^F#lD9f
IW)%f[_[lnUA3NuoAe$uQJNYIsc+Q%>pX]AKM<,[=_p1n%le,X0+6#;5`=`jf)js+j(nZ\r!C
)o1Ztc`HIZH7]A!?nOoVm/migjVGJhr`WRh:"OS&2WYBH)Ua6h6LM$u7W"8dq4CJ9$-&O:Eb?
k+DLljBHGb4b9QXmO?6&Uj+G/RGm5,N#iXN<7]A[>C/Y8[gCTI\U)JDu-UpKskuo6E\r-6;tO
<n_F@=hYO85OO,T2S)j6"en=+QpXe$-f&io["YZ_<]AB=$'E_u2fAr`0,2N$:,<3\.EI93ca.
5Fn3P9kGrgt2>=@u*o2ELY&kiVm*P_X;PJg#I&Da9-;2@)_\"X5Rt/jK:*ZLq4R[G!XIGhjV
Ai-r\ElCYoo@-s>-MVW0oE3r46Ui7e.:ZHjcc]ATY<+U&;gjE2Td75a)(Wrfsf/;l=B86BD__
g6%>ZiTNUm*?DCbSW(OaOKt*4`l@LR_r_3tah-"'qe,fu$T4VPrpL/!+h"J3GYOPuV_9&GjD
Z"b!.*Wbk-=rqQ4?flR<.7Ve@,1S*Tj#W+8\@$OC\M7A-Le9E]A/._JROPnPNA:T0Y[q3%C6K
1"tr$^\]AC7s3L9%1XJ&>\/\KJ[I-/i1#0";k)I!aMb'p=kH;Z&;MWq*-7%guJbV9]A@/@*Hc!
Zt6)/)Kn_9gp11R4AnI<\3cL@8_q*m19'PqopbaP)0?F&$4@.>La@Z5\d@"atTkg`ioi-7!R
CI@3XK?m8oJ$jT`\=@+iJ:DRj0HeBtPN-3J"P03bH"m>ssZ:oEFGrO@eJ2bJc)8L'FFO416:
^6[cPM`X?-Ja<Wk0+fH1*(Zrn_(RH`Js!KoJLAe^Uj)O"?CW*RhX>!iYY\^Y_e,'V&$ESoT)
$5MLC1S<"QZ)+)jcWj;dYMj#B`q>opTX!(\JDMgDdL\=\3$:)rPMGo3R2/ZcW[CP@1A2G^&$
CTZ30R4^V%GAlKTnCu,b(;Il(!n)+KDX[+Q6)!tn$c*35O`^a<=0+&WZ4OJHlU:Z&a,i'nu2
#C0I3TnN552fiR30@S"gN1&SIstN87CT,'7=T_dK!L,.pg^F-gbn"5?_o`qN'2iB,TUGD*#3
N$>8'^L/(nR:[U5&MJ,acjM6-fdS`4n$.T<?XdPT`ap+r.MCVY[ONq5_!]AD9+4'g/6^*`K&.
`jMY7l7a)eD_P,2.(+tlHo,%I=0Yi[dP*Y./+"aVA#]AQY143ju-@TP!h8WIB`3?ul>'Ks%g7
IT98(&B0cR`<D.mTOhi(B"BE@OAViJ/OAGS#6aXp'H%o#AODA[)gn1[q!5ksoTtYY0l_L-+1
^g<??c"Bh'I*pQt'AMlg/1A>!g5=TU7>"*F:f)e)g>mqI;g?7[FV-,45Yhg^2c@EU#+H*)"X
:A[g>\t^^[G=$J5J'@/7h4R)V#l$\KV`ZWG2Qj^lL;5dO+]AIk/8CYQO$kn<7n+f^=Uu1gK1^
gG,tTnP(ksgAcU86m?@&%Xk7./!'3Ik6?Ltm=>5>N"SaIpKf%^e$,IYZV2,?q`pbm+25<#*W
LohhP`s#<7!1b:aeItB!@onnP5'aeD=#0K?%P4,jq'[#\D0`ZDniEF4XX9,AVKr.f:M0:<(d
Gi73Ne-oJ8Vb_/!E%Z6r$U"p$heI@Z(k`DF#A0Og!;df6p(hV3$1*37:./\AA)+8!08F*UMd
cQ+%,8#@*,C%^$*5rpL'NH-GXH&.69Q+oklOKC!dY)sZTdiKDL5:QZ[ZC1#63T,=gp]Ak%]A0d
igN,o&2$I\BGpeZX3T0Hlc:m5L%Y\U]A=8N$I:Gg9dpBe!N[86^lk'D>"`KS$[FFGB2if+M'T
e#K'pHs]AuJ6pF]AV7;RAEa?Z`73"nu3""J@43VGL28j0R<R:nmqM-%<8OP*FuLuO21N@KUeC7
WT^f.KmiYNQi*HRO^n4mJ>Fc%Te5HLFn;mUq=KG::$+N2T9aS"#pf^Q0+XA.mD33R]A$4:P]Ar
"jSVS!H/L!H1Ui5:ijNuMGli1VgB@Qd[:=J5nt";GkDYBiKp%c%?a.Xo5Iin@7E4C[#&XC*F
-lE;Ak]AUIXa?3FS[g,r(n?sgkg&c:]A*,DkdTMm`49cS:'Ka1ICn?q.qh[OL+&>]AuqZW`fA]AF
5=bZJKF).SlX'@$W'+:BLEMAqb8A/H_Ji0ga$8A(XrLL5/ZgR)bZM)$NRshDrDY)!)&$XEO[
scB)Ps@@P&/A:?#HUn]A[j3HnF24,,pO"M0h>oTL[B!qMkgX,hf';`iUc9N;^fe:s8EXY9.,G
/!@O!\s!8j`j'Be>'>R8,V![84L`noorLS[$I>>m)?89]A'6>QCTQ!6SATs'8?MF$>9n"9nnB
a6/oAFiSqh-A.c2KD!nM_uSG'XAdk5GeuC\1gY(qLaJItRaA&bmsDS#c8CKB[\#K%rYi;s'j
'/R!AA5.*Yo/pi"0C(?X]A9)D6iglcf&1f$R2rMkl%<PI/1fq2H1(TO.RP@i`hZk82=GJ#*el
hsC14bHW)T<lKLq:_E'gA0=l;TOj@ERGLF8^'*t_Kg@L]A'Kd-#=_Rm2"#WoiF9.=Ik&bNE;$
=uPNMU:7BbD=es1(La]AoP89<s^VYj+DOg?t^".*]AQ5X@&Q.U,nW3PTR)Tp"+JNB"m89AiEsU
Q/),9XeL(e'?;\@^7LZ!R1^-u'B]AHWDPa6f'0R_[Qm3l,o_fG/$WH"Ym)i4_IHgAVW`&An7K
KP!=dk.k)g=s(KSr:ejO!TLEJmSE$OT71KYgD)E"On/lmmcd63h&$BoTYGjbZe2agOcJb2-i
)PPlisJ`Akc.q$8")SE?H&40e7(bt^92H]A[pZrjYd0p-1VCui!<`IJgGpO%p"VsD1ZK-e'*_
^.@<*I9te.qm,m]AUH17-t"sS@81PB)8^G-..i'(B25eBB^;0\?ue!cUu"ZX8*Y@$N;u@PV)e
Js[f&LKC+H2&Qc[Do7Ke6\T[X,(Ce1Ed/qe`m>9IK8o.*P^ifGWp;T"=E34\%df1?G)W0<2$
Rlp\`-dmP@:eU$\3lX>)EUtPo\>.AXNTqfa\7f<Fl:0a2s-k$C^j_%)X\^"B/S+B@iq'Cr>s
glejGqZ5oUQ[N:A=C`ZcUrqc\d#S@7ap_1A@^0js[.EjIio$@9i@eN!>M&rUk9gf.0u%R;PJ
3IlAn:h;=0.r^V1._KH@hC\=,`/5=!$nu,DWI(q#J?Ji5>od%S_TF'QZCbM32,bsr0K3SLn;
N2WA_F;62K@S\nGXH@_>Zg%F<0AQ5`G<>u9;S>tLpAJ(+82"WpF7scTK.X'i6"jmD/&ti,qp
M,;SX""daSBA&\f:FXiC29nRC2*EN]AMQ!m(QmPNu.@N,ra^4H]A_/)SZ$K^W/jJp2CJiE*2I*
K\V<C*'tjcc-'-D<QcC,F<fFM;(IsrP<s$9U?$2mNjC,1nJm'rFAhg4dV$_\?JIAA[mTGQ*D
W,aTRFJT@!NcUh1BXjd$U3+N!l8@2nbt(R*tM!dVWsOY6&$PR%GPu6/RBN1>m0EWQGgP*'PQ
fl\K3=_E%+cNgm:g.+f7o=L=oeo4e@a@D)\FJh2!(W6?AaK<RC<a0nGA)DisSni_$[M:)Zih
@#D<O@R"SUYFsXVKSu:7g,S4mj/Z4mGTTrlS,389=<JSX0jCB<ba?4Y9>2\.>>1[;\,IgDO0
V1=[SI3_u`>s>G!pU%JR<,GfJoNQ$fP)b6%#kXM&43RBs."o0LgmeSu:H$%/_=38KJBB2F_U
pXtYDW=FeRT6DDgL[MD9YnN`ApVR)Z;6qVehi]AbQ+A^iXNMS?JBI6l3G@<I892o&j"2ntjL'
okNNe9:crfJun*FGpG%+Q$UEVKH$]A4"lL\.t[I,1;Z$5%IoIqcO%2MCAucDU#g^NVm8CBLIu
Jh=k?n[TC*mjO[i!VGTVWL/G4)7Tb<<J<B@;-*M"&Q>[Y(r5s9>b5),p$/Q/KXTIu5c^@h=M
dc_+`<-cFp27u)h#<dje>r[7:pR/Tab"i&@GNoX^23*BpZ#Adhs9h%aI1VYGKL"5+0URI\f:
B>0-u'5^MajE3\Ou*f*?QZ4rNmaW&(D*&0.4I0%hmpQBC_pc-TG1&K73U`K4'*J#`G4;phQ;
qVHfQb?^9`6RHFaPA%T_PqHXBnW*g1dF9W:\VHXJHA!/h"LZiAFsPp\4Md@IrXess\'+Rakc
CS^/8g'HD@Mjc;kA4ARMpdm!7Dn$b&k6C87^6@.T"<@<dF:VXE^+)hbg7O1AkPooWk]A`LTq$
<b6+P5je0;"ZU(.u2I>KNpaJSl'0rqd47a8K8[`V<UrV]AbL;4=r\KMga(F5"tP==dXa-XL:d
I"b9W.T+QY(rLa#p9,d_HJFF%t0_pMH,o/\7eCE"um]AS7`S>2JLE-m7<jKc3S)cr@WL8W"Zd
If1@SpQ93d]A;a.uQKmX%";Y.sMNn5#<fB23)i]Ak(n_4DV6#XX6XPbL]AJ`FJ73K2%4#B\8B.\
nY!ULE:SN'HLR?/bGEo0QOmZ7DU\F\XC+80J5*\lBTpFBri>7ke<"j?Vr:GE%^2._`dABUL1
Z;:hrq\aCT.]ANij-"lJ(GuY`V4[JblrAe#ZN4@nb)uB)N6##3O\?g)h+pXela,mgZ$@&>O`g
)If-l.>M-6MX%[UP?9<V>d.mE9'=Ml?RKT\7+MSdD_lMS2=gXbH/jEOH/fY77kQ.1>0VP>I"
9EY[`=%hY%`D=hA7hNcMUQY81-.!JH(q=!cGJZj2e6F:o5'(ZdXo,(;OE,K0[4>jR@l]AZJkj
Al=",K[2i@L`3#^H-jcmj)?mZ`eF:m&E9JqF9,JGHT(q[TW_l`CTH(omcRlpQlg`2DT=^`\7
;'^)58"8]AOdH5F$NmV_gj`q[[i1Gh!`PLptRt;]A;f!e&,;rp'I1l>JeTr.]ATr_r$k(-uWo(V
qBDNH7i;>'c^(qM]ADI0s?03gHn;`j=S&i'DH-srf51J$_$nD]AY_AXd7f!<!c&lDZPc/[#+77
0=jD(^%)GkN[LI4'2">iYA<pjEJ)ok[!SP-U&+c7]AdGU1;f^CIVBoW4X2-:%u_Wt*0+Ir87)
.K*Wkqcb:ZF:6XqZdOgZNj0`Bnq:TAqj$>*Gf<V\c]A=P3-LELWSO^JYZURiWNhYh@-&n71V=
HP0tZ+*l?=0MisgG4(7VL$UeoQkS[W(7*IkYG#4::X*Hpo"BQM;Yc]A)',[/<3t1$OSC]A*Req
f"bQX.H<d.eamO8GZU^@TX.1t6,l6c;#ujb>VQ<Q^>P$<W,]AOS1AWJRPSe..QWRft:og=/\!
Uf9\&Cs>YeYTH*j22TLXg_73J,c<ni!?NL3S,1/lA(&]A$]A7I4&qN-Y*_`1j8Vg6mf&VfO*m!
t5]A1Y`49'\+F_K#ELjb0Zo(hZ\r//1Ha+f\38!&pRIC!m=L^9Z&7t5#'bIW0X8bAOW8oP2"e
V__#mi>*=10KF0O*6#_HX.+FCLYGc,VH:S,Ha#.N&Ws?\)384r!@(Edt/;jL\iEoTd?M\iGO
8TK[4;4,a1X,>O9SHH084h;NFfK*+)4"!!T+`?EFOTN\m5D%^A7s87=;"s%W27jI@1[`P;r8
AaAp:`(fUmm[Qf=M8JHtKR-j4N2mVmTR)!PB'8Eo@dCmhh_@LHDeaWUTJ8f'h?J>[:YCmFh6
,"meFV+q?rOT+N:ff?&iC:4;:;=#c[Y1BQ`5\PH9S,uAA[5GP`@Tp$m[ciI0cX/LK1EK=%j]A
_c`p]A)bc&O=FqqlP1<m>*^,K^kdAuTjT4gMRN-A9%>q]ABD,MpuF*i"[,)%*bT;MMKA\?"%^<
GSXhKQ^jH)S>hu5>XYF=nCP8M214<bl*9^Q5E:@57Fo&C8P*"8?o"Z1>]AG]A^ZZopH80XR=/r
%4D.2+ZgA<(XOjM]A9X7[uM98d[,MImob4QEgijbGk%r+rQO'pkt/nFX806GB/XPPnnbK!0ei
IBeaI+83cTOt+X/&\c?8C7*a46C$3h:!4(gWbD3mDj,cH6B&MAI89,?d@X-"Zh3dC^9>a;Wf
'aMeE9V6X-o75oDDWV*.-.LM$I@!l0#K'8eeuX-7,P2n6hbKRL6N=rTT7Zk)IehrhT/LQ1F?
3h4$*t^&Z9q^_)'$MO+?;L$sq\Ei\.oFlCWBUYmD6[cu8Jac<\4G@33A^@]A^*&[2^a_XF+*B
r\iFWH=Q'kH#66,LKapF!5?$S+=[rqi"+]AR27L=koG'>=D.0I@IpBjdRQH?YL<`koGR7+'P?
*IV85%;I?n.sWnr*::+e5(9Q0TCBO1C<\E/BHR8m'orRak,<i3S5:V&MkpQC>"?jQDjV/OQV
SH,#'mt5`3p^3*ZH3$,RO>,^9aUaK6YKR_A]Ao3:3;K^3-M@m8SqYY2BioM]A$>BJ-eB(n,B>.
QF*mk\\7[3I*R(0Pr22'm#&UZY(dK4fb8e)W(fh8pDeCD`do70*cWKO?#FCnLR%o4NDZh):q
2H,edD3!I"b(pVbJ7J$Kmm;+d=g=sa,WAB<"cO&RI)QURgU[jt*[X,[1>&3+'-bPWCX]A`AM_
)JELNLd&n>aVC&Q2.CBT12p?VZpMAbr2iq#%/rBY(M2U,?+m94P's2*HGs2U4,RnA3>]AQS.5
1P"_j11WB$]Am(TMSV(;=[?]AaZ;UbW^Uqb>@m[&_KrZ:D8/7NV0bc@VDCB%d<V5U2EI^!ICGO
IgI4h?<R1o^%2&q2u<qH/0GL#*P]AAp?Zgt>p]AMQ,lHNShC5!(763j')QGjlnK+uL"84ZV]A,0
lf6OjY>#g#KJaIML);o6ce4FR,I_EV$QA%uZASD.m=E+/^I>PZI$^Bf,'K:qHY*KO!jr4qee
9/LI+khnI&oVHV*n5M2g-.maYQ[]Am)COb0&<5+A-'RrG9nh>K[7JoFL>/hl4.\5mG^MuSTa?
SLRJ8?XIA?Vbgd7d_l=0qC`\YL;\,2ND[jE,GnBDJrJ!"OMUldm]AVN,rDs=DASQBrKu_BbEj
7_Le-lIq6Wd$iPFRCICR,ngYobdnf2R]AQ>@O;n&*VD*OMr=Ppi=XkGCFbdu@;eq%%b3JhsKo
7m"2.h7>1Kk[mhM04VIoqi8Ir(*'e'5NOetIXoP;AfJ2$#_EP(+48hcELo(?pFe_,U@eU1S.
qdM(\]A$$^>9,H&b6uBCl!pXXfed9+mN_irV`K+8l`7$5-r\;5=:gXQrGQ9abgEl\5cg@;/^!
tl>__u9\%/?@V,C@n$"`%RHCt%o;CnGRm:>*H1ugK4o=fA23Vprq*cP9oJMM*]Asl$0YV^r2N
cM5n=gprtWH7?IA]A3^>GB[!R$dTgE:L5X):J$mpq\6uPF!I3-o-04VUWe<)8AADLSpSW??4^
FY.e!WYj.eqmMF7@a=f%_d/n&!5r2t[UZbmdZ4U>9!Y-SH7)$0[ib#,9`)thsA;#[:e*1uj"
^"#Tcrm5Jsp"U2eY-!5hOIdm7$6R7_:Y_2Qb,!VU^tH!3HgA=`X/(B8lG/0o\$^@TSr%]AlcO
)(ciHJe>'jeskRQY9>eaY<2R7Hh7$*(aRP2-)2Z\SJY;KApo$<`VG9W@l"#Apqp[9Q9T:'DN
RITu!`m'j)DLaR#L>MYR,nQg85P4`b8eGo)/b'9,sKG-<s.\6qPMnWm5gQNF$Af=?IaT5Z@O
Iu!]ASdPT"^\Z^VI6]AVsB9<.q=mT"A:Hu/DLh/h()GU3,'\'Cpl1a<8cn@*,3N!6CKu%L`D$?
,TeYU5Z=..F:_$n\X=^=_D5LA@cX:kL"Fl\#L]ATte5Tr;K6Er`HANcWK,UD-LY\Z0"\3gBQ^
#ahX//gk\\_Bi=aL>6-sAOl`1@bO3LV,PI[R9IhgEVq0[1S6KYir_c:>U+^s/dEqbrL]A6DOa
uj09,@W]Aetok-m8kO3A$`)g;`i=T#>.>@/!?$Q6J5'%LD>fUKl5'Crs5"$c=<HkSFXR%$qIC
]ADj&e%L+RR\BaJ]Aq.<(cG>W!pT?.hfrjegneEu5QWViS(^PX3X%(qH6I+pQD.#Yb!HQ12dd)
%sd\e/PnJq/>-Y9LO*YOr3oETa713kL!RqF,(T#B'5a[^aA$m`GLF\A;j`5#[ik\^XH0U/OC
DfPtM>8/.k?UM!h11%C)tHO<-"mA`'(SmGV--T5#(0ZHV72HRb"*Q*cL4I[abPqRR$C'd1l<
$"W5P+8ImFpg?mI6@W90_0V]AT%(_qGmbFc@FiV`loLj;FGP[L1']A5]AYpR16kq^`Q94d9I"As
BCs@7,OU_aQ]ATLRTB%/!g]A;_LRn2]A`0&6o?'0.X,M3@8`,0P2#3*$_$cfDLsll8LVLk,ErN'
!5q)/=rE4f,H;aAXepNam3q4$DN>#XIcidR%r9PHn=;Y39H(j$n)giWA[1p6SC.6O0pZHk%X
KGO)?lo//~
]]></IM>
</O>
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="2">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>;cgCt\;6CWG1qMu$FHA-@r6cjlO/;tWRas?7lo18+QQt1VXb'"0t]Aispn/WW<-neNK[
_J=Plq00Ruea"T43uk&ql?7U_lE@!C@Di89.K?XehW6GIb1]AEB&I;I:VfQp?T3=qQ71!q;T:
DJP-b3>AReZZXiZSmsJYUo3WIV)dC.QL!VL'SHJ?5fS#6#Icp*8M7J8%\$Ib+8LJ\M$d9ns:
R*sm4[\E#TeBZTglLjtS:05fG)8_+Z.5`V6PDhY@dNN^W%&^#b'6^.Q@ZY?.11IJGGDcsrTs
oV`l=e;n8cL'l>,coad2DO8!9Ut99[hHVeE-_\MM<PX-(QtHZY3]AJ&[DfFU9f$h*4r4No)7'
3[^j`rD>.(l#ueRpdf=uLmK,/?0l3<c<d\T?5ld9FZV-V@XZRdF#ZgC$smP2Vs7XfZ^AAl0'
L;?,%JS(CCa%SZA43dg)X<ZB?C?a^ME5qLKsDnBcOPt%:9X.Dmo@QEaqcPGJCi=2KJ``Pb9)
2R9L_+k?`MjF^&F7bU?3[eIQM?A4^8&SYX8+L\I1U[CKcF7&iklWa$dcORb9^09W=mU/4`pV
P$>-.6T9h:8hea<a[,^`s!o(a_+HaZ@6ru.]AQ;Y*AnHo?L=#.dT4u]Ahf1roY5ckq?+EmBG?.
7_0mg;b<"8L>4<NGr8n8'gV>k@RR)FAbh9Ki43h!2MRSB**5>c`hFm=1g,GU[dpZ5_;E;fW#
_uF]A.N(JjH)nW`06-DBL>*n&`0IfNP6A@:N`&>4%Ok:m_jm9pm5[n7mCJr1.Qi3K4n\Zc7T1
1U.'fV>/(f[uCW"Nd."S963SGI:p6F:GmX1WmQ:-up8Fjs`2NqH3(P:&\[-=fX!2fGn4'Dt*
8Q]A7.rOFeT`,5i5g1uf3c2Nfm@b?0<mepWKS7+$5+aW4rZ"dg]AOB/%JsG!S1$;FJc[-9?TNZ
[H)O)0/Bdrl[qbL#tiMO<9cu7,GTPM/3>bOZHfS=+2N86fK,uZg>%==sP?9PjDNGYeWRpj]AY
@NRBjtU^NIEWI*KrCp)4eodZGge),QltEirJtoHU(VAp&eu=$<rr"gm'IL1:911P-A!0m*J4
8Tdo>,Yf'Ia>7Ll?0cm1#XQh"eQ>jOZ-PiAD%'W#6@)Uu]A.8(o&=GjsJJB)\9S"TfT^1Hp%-
WXH=qA0P>`)D.aig/?0p-UD`s(_OArJHGqMF"N/1rqnrAQkeRU35JalC+$<Q#(SD=?F>$3Hc
;E>Q"Flo%rg6fKbcD9E$*\rZQUoJNTSU81h*O1;uWLQp9ASessF[nJ3%Bb=^dg%9[eh*9M/G
>^p`,MYl)`WNIpe$SZh2*,)V03S_;%#p`,R'*jkIm=[5'MO#8@C)VOPf,#aO.A'oZ:apu6i1
-8PkSU@7BS/X&Vb=L\=iL\Y[)8k[oM\$/j`r1RBV6hqfI5\nPu+G0:cT3_Cc19$uI[3]A5.4N
771JWJXKVi2i#.abMtE)8o1P=VAe&hQ"jT.Po&Pi?i&2$p7bKNc<6FC6IYo<8GfF94)$@O]Ar
-XWV^0,9:"I4A4V^"M7+Eq1Epko3'O=@q>'RH#M#(A7f%Hc>BsoFG=dIH#b=LY9NQ[6S8W)'
-eo#d4T"UQY65.GrVk^+T"`Y!fbN0VL,S-K/PVtQdj=rZDVA!=Q;e8dmQ@1L;.3mgk:2c1d0
:dha`X.$mp/\q1Af9k+TZW2K/W'O<9@duaVjZDA[hoH7V##>(Pb!)\%A5,@*2Ml;[F<FZfeT
#T$?)GDko%V&)2eT[@`MLgPre)<oi278[I$m&?_1uH^"Mf<1rPPAc--!]APVsLGcu?[8nd9iO
^X6^9Fc+S)hS$ran8Gk1h56_CU]Aj7MC!C@X>Q)j56&/q!?b;!jZlD_F+RkqVaT9kH>")?sBk
-05Q9JL+#ue+8a%JcV@Bo*`NjNS:YOWd?=(,@7;8<PMg5F1(>+)?;(mVc*(<JD_f]At7p8K7\
'QfF.gd:B+^DG@H`<2Q0@m6T"*KtU6nIK@0_#WfN]A6HuE8OG/dg;QDoV50;\me[JXS*\qS5'
X)Ola)W67@tP.moe_Sd!^kEjK;#.%p"/TVqGiq;0AJoeWo=U]A.HE)qKa,:Sfm\%;Dhk3f6_1
)U>"gEZ7Oo7#>:u7Fo"IEN=UZXbe3$Ttjm`s@&UXFYkHAtJr\VW7Aq8"4i/XUak"IRjkoshn
OoYHb&4L2.*-`n1>5V\k*:hC8S7:a$NM3okI6fuDIO1`bD]ARYh*<Q@Q$knh1ZB9))*/%4:7j
e^KNZP3ZDAO"S]Apm8/A:`j0Y\gFPqf4u5V6O6p4D9Ti;)abr/JgJYaYf:)QF;fP=nOCPMkm+
5N\ASN%3_'"Z82CXH^a'%(Zi+?k.%(0BscA8ZB*AmKB:r+E*++31>ZuXmD'T<T;>W;*;oPf!
u?O'!*W`m7&(g#RJo6*9G^2[ru%GL9@s$k5e/Z)Hfl0Je$tWH'3tk>MS1STjUC+$0eY`0)iK
W3.T0!L13r?>7[h*)*Nnk:Gi5QhD!M-mo%`jgg('Xh<+'<\R(GF8is54![([1ZPCH)PlJUAW
MlG`EE:sc!du,HI:+Dh)#G_Bf:UpKQm8[AeB(%RhYsr(tj`hSgLCjBoe1l8:m@Rq_2#R^A&i
7>C=742A/F*U5h*9K`;;VgE;)h@B^[$VmbP<9A'CJHh.G%dBI4;lA.dq?6c`Eg,4.S0A&stL
9md_=@U]Ar\WX-kpo2Km1]A>'WSE2._h(*gM`#RUZg6j>pXW84&7h#DL2;.f8hVHPOpl>1-1m6
L>[URT:cUgS$rQ>X('6S"a,-9^dO?kLCXpmCGI-+`.!4da?c+*"eW)<HSLnIr]AYa)uB5.ifk
RSBJ&0R<,LulmSr!F([OS[!r<+k@P$:&)Z\s&Q+8=FE>ta!R\cSq?BIYqA+0mRb(N48dfsN1
;J2KMW0^WW;H!39#cD,DK\N[rdqGd-k_fBu.\iEM_3"Aq""`'S3)X*VVo:>pCGa0jb*fWZ"W
_hXZ0-8`3&+gRTDml]AlK3\MW1?k!jOHbh>doooi%+tPS:(ERBqqg3L1,oF05VPhG29VEO4Zr
4+8Md2IFFpa):rHI-7(M>BZ"t69n:@0j0bgc%kLd>S<\lV8"!cU#fCM3#\+gJf\"%s/(4*fm
Rg+1r@N?S;8Jdg*Q>S@n!^l]AcPpt;G<H=o=3JdZZ;JS*BZc3R&!9ef\V29FHN%-19<;J,9dt
PFP]AFYIga(p!@`q0Ko.,pO4HZ__-NmphmZH2fe:!V1>cPs*)<egGr!Vf7EILBl2;NPmUp6@1
>gXu!rLZtB/nco8E:/l!/#D$&>>..g=69rBiIU1UZ\)hFJg2?n2ge(Bdf;MV5.b4%'EL9hm)
%G]Abf"/dn=.lgY_nX"ZWrJ8863NdNRk6p(qKPIH-X>@EI6s(3tMkj,16bU5DNb%RB&<&iQl0
f_[FgRVmnQERU.Vdl7f47?\I72P,l$R]AXh!i:%7M0%\d=PTj]A)ei'=lI:#3.6:1aP-BJ8WW9
-K,^@:>8+\-N#39*R/Or9T[1IQ3uW71]AcA1._CLMN\#"OUXUV>Wd[#)<lON"ph4]ALY^TcL?4
0Q0!90qL_"E2`Mm&i10oYopU%o;m/\5n&4,O4_5-h-iM]AMrIYMpdc#3(68F*42Fa\nf;%Md6
>2pA"P%%3bcE,BVZ`dDm*n&5E"bFaN5<]AK[H>.Ie%(rkBfT?moX?jupP.$,WOr!eZ>%,f_k_
d[?O=14h^L1b(6/jW2j5)u)nfZ4QR0L:H:$mtBEp%^i59]A7IJZtk10Reg!OooXMOr%__%-E[
q(Nb[M0b4!qAa?E-n:!FXhetB5[,Wc9fq8mc*jUhQA37r&5`cF*k9Il2B/%EB$21G_#f!aDf
,\#Q4BsXt\`RbuRH4cqFs)g19J>"KQ9LXm:-Y'L9_9P?_#.aL;SZYJ!F>rN'qj^"<-5C.iL!
5IZH*8Y4-()eT[SN#K<cTAhu%Ek3ei/BXPVlTlS,!i[1")"X4IC*M.u';(UQ@;^&rLtcS?'B
0,`ERVusQ^O-preRcXYMKE+,^`&=pL3/5uFq#A\Jo!@NfHt:4LjIr8l&A&?(?cDQrW4m_N^_
*I"\gYa[SV^(Q!eRO%b"fKqeoC0=#5T(;kdsoqp?EXK5(N<q]A+mMQOc*VnfVcF3(=;uh;(Wo
1]A^3_&W5`UoCF6Z+J9;[f>c--K/Mk2[a6k\*jU;hSFn[NDnt_uU5qSq^j5pI!2P%qXI@Bga]A
URbIF9FGHWL2;Vb+F;t]A4daPT)Kj&]AF!T-60C>*-p<Y)pt<(-.T,[Vn9j`KlfBuE^mptH?K=
18r"lMYNT1&]A8IS!pJq090o?7[(WI)"9V;VIN_dKlH>ps1.-h3dmQncJn>q7@#Qgt@TA@hqY
APM(8Mb7;mO!HH27?>;J3r+XK7)mVRHJcktqG"`@Fgj[j:>P)&.Nhnc>MN/?J%UD@K7KlYj%
m!8DKDak3,d8Y[-;Gmd8k:mgC3RHH^c"[=kcQ7K5/D#"%5H]A6/i64D_bUfE7/''q@Sr+'Zqp
#2'.9McrM$R%BNhk5tMb@nIF\BAZt*ldcY?=Ih(k+d9dK`Qi.'H!\RfL9X!o)(c5_L6XC8H2
09A34:('`]A8;3:,qS)c+FEM#Pr_m8L@$,ahK(EQ4G_oVM-?N"]A&HZ5:Y+`U7^C2=3'X<0h@+
.J!PK)ZJ9F]AG!be0(0$D^iIAn)ni=kC=!oFl/psu,0nf2NN\@NMC,V\4HmCpC&S&)OElQUHU
HkNZ<5Pj/(iu-M*=I3JKSl99G?bo@M'*n6dXY@UFjRt&ubl"#n-g%j9*PZ@q7EmRS&e`$KKM
V`Fm+YEeNAH)=]ARZ<'l+:Pu*A0a!"<faB_Jb%/\99:KX^IF>oBuEZb&<J:3F&f<#mbERNB%f
n'8E-kpjYj?@R@1rk<&Gm>KF!^T.f$EQYpD8]A'93Y:):2gU&qL:`dQ[Z8!6SSNWV_8a-8h<A
Hb`?Ja.P?L%=NQ3Q[C"d$:rOp%!HoRt%_DrZFsS`X'fdT36B%V]A1c&"?o!u5[m"'F,>pBHtL
'c>+ZW"blbtHhDg*+-BsuRO[&0]A6'ug5>*uY@"g-YjnfL\e)FBNF^an$t/#m(FDE?GQE/DB1
("FF1DKS4(^AB0@4g#^Ue4kgiBAQDko=f24'?h)#&MV#EE(-ZdSsPY.Tb5qj^heid-^.2'!:
O2ZT[2ASBE:+.jc/`3g+)Ch!MF3JI]AilfjQ!V0"Dk@m''KTbUQ^+pM_MqN>!'6d)nkAA4\nb
F`s#a!;(6qTUHtP?mCd?i.`lT>b/#IOf%`-2>YHM8CpmAi2M1:Bd7j(B#6'eM=:52/U4mhRA
U+YmV@]ALW8MN7n"k5DiGBodR5:I$>3lKCa<.B01pl\*K*CS7-,gQINn8`kQUme$HahB]AHDhC
_G*baQV?Tl,\UH/CIlq)Onhgl"%SgqVrc5(4)M"a0X=2ni]A/'!14AgBU9a39#T3<siQ3Fr]A)
<dtuBG^VMC._S+/5O_ZnmR#=3?.m"K-8\>Ti)2#.N]AFhQ3t&[H#OHIROre@'!A=mei$U$=G_
68O]A`(-9FH)!McMZnFk3lXRB7tQ\#;:dLr]A86e:rt*)c-jpB6=[dD)RFN^Qk:U3ISgQ]AUJ@J
um6K:jpB)+Hd%,eUn`j]A99SZOoV]AN8H!R_4U^^3siRe9X/LXga_E@R]AB'!<pCQdXKI$ZG<L.
"]AD9?Y55db^oICk<!+Y)fBr,K*ZBU(omfLQ0jN*iEb_()M-q59`@="\=?Li2QRlGSmA-QhLQ
CJJ!0VN,,qiGc;'c9Y?O3qFj!5[K)C,iao"&)-qWA:`[;40nhg*>h,@)QOp<0_3i!iG6SkW@
T>9oq(o!hIfVfJRnA`5^JL0`t_5#,2RQ!JCN9aZ?4Gh,TCI%)SQdWujf,a"OHHP3Ada84j.A
CADfo&Jt&)mA*WWEU\(+>Nc6Zq%BUI.oR2%/C!!0SI%8L]AA/AbEg<;CDH4%\J#g!4gtj!;@E
?o(^Bo*e2a.hXi0l;q%\Xp]A0KbX/6pH!tU!*$H!=kGNR?=U%=9[lY<ss&hc[4PC<05p".:_N
?C'AHjTdCS4PdEil_eR6D"(^C3Y%fZ(LqmU:0*40;*"%]A0u%e-%a';&W\`9Y62bREFL=cdnt
mFq!=g&fH9aMo`1I8DoWqe4"a2a?Nm!J>q3UDEl"Eum-k!_#cKsO@nrV]AK>Rm9OO`f6e\j:*
GmTk>0RX70po&=\RY+-\G=$Mhht@D/r0"u8"!gZ;ge0!'=nAuWJUmlXXE@o^$&d=JQ+Z'>b'
+Y:5UHF!i\ES\_Jr>Q*]Aib'ELQlq]AQjA`F<r6;OGBP2JH><p7lICC#5rmZ,8&"_qs6^U]A67N
W)rP$6:V".\TF>i#DJ21*:OBXt$-Z=i[==Y..C8.c:mc!#^qD%hI?jhdg'2j]ADqabWd0@HPr
W`~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="310" y="645" width="580" height="57"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[1143000,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4572000,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[分公司]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buoBm'3?)b[G%c)Ufpe4H#9V5j0g7o&HNQ.g8`4+AZ_$AUCq]AOW8\^?Wt7Y>nPNQW!2<I)JT
S;/O&dLkWiuq(/M\ai'>YoM/gfihJV&RE$7$eJRE=4ie@'np.K"DQ:@?p+qgOUp?4QsWH+<%
IWT-*'4?OjS/H!<$it&ALa!gU2A+:oq?g`aBnF4+,Z#IOZjLhB$U:)aEWTB2(U@)le=7p^rN
7]A_/N;4Paj[P9a'"]A1@moWSp>$>cWA%.I75CGI]AALpsVrUC@BefiPtll*%U+IEt2*E0lc%1W
ehQImu>]AhQdX/6$g(UE*%Oo0IM@&[LnS[3sagKIoC\\hOZJL&+TG3h0;AA&4R9++I@t&]Al\(
/XcL3[[;b@D#Z:>eCB_-W0/ZNfL,&KZ7hSpRi]AZ$9&ju*L%6G<pM%c"#]A3K>-`aaU3]A#qiqp
(,'-$JB4?Db.jK=FsYl8.FJ`9\nRgO1U0Hns#)E?9F)o3#+6Nn$.^(Khp:FHoM>Jj[rHh@jr
W9kcmE_=)%Nr)_#k8fZr(<DN(RH*a@S2S's=+GM7eh[Qg&3Kei14=jP`Ri/>a+R,ULm=?@3F
`d/_9FU=0$S1#H\0,6!FQ2Hb`ji8.fHYGuc=5&1*"ImiV<0qLhm6%UDif@2YO'uabKefaBr%
#r5m/Z@0EOtD@i<WO)SC;)l3Gqd7Zgf+KbeSpkS:HM6r6?_*e@L.=;hl4*Nk)m:UkEO=puW>
BMR-O(4\J\b-lY$*G78WOM<l<G2Muh^4YCTQ:\a'4arLYRB(T`^[d;*<aQ1XpVF(O(uC"%&.
ps9P%Q1,`pE9*C+f/,0iUf0jDd#NK!in4&G98I%s0"%;I6+q_f_?3`\:,PEth91T-uBN2.L\
S2/?m,e*?L%fjuhHFpY2?3r0=,4"cZR?Rk>#8(9*XmF!Qh2!IYE;g/cpX?KFQfenb\K,j8JN
?eV+s*4:o_hkUc`q*ri?-lRelU?d&5`Wkf4fM.n4AhpFQB*AY.b]Akq-PSjd%.XXoN7JpA#ci
kRd@A[FX5@!VYD,uo#S^$_/Z.QSFPl3LI%C(p$l^PM`RFG6Ba*3bYd8a`i8]An-N6RK4>(5DH
ci)c<em">Uec[)XM'hM.M9""[<'+8)GIVkj]AmXFQ4jYQ!&"POSjRo]Ap':WnXOLiq).bEt6Wt
;ghlRB#d8c=@5K5#B<)K8a+?=;PC;'k]AhRKYZELCk4XSXti+:>TfZ5Z[O_W%0&W5/-J4^7'o
kqE^B#)3IV&"P*B,J4e@nHg,8gZ(*sj%(2[;O&>;E,sd4#6j0Acd`nf3G.B?WMTPcqJiA+A:
umPNLGEfeY7mgR?d`YgK`&V_C[^o7nBK0[2)3VS@hd.i8d^.&cJ3>PM0"NE4]A2BpZX=4dS4P
>_LdN+T1]Ao0/pj$qCY=anS;WU9p?3HI[7D[Zsoi*+$M>P9:PJYH2%]APV`5%s=gZlrj#,1!#F
Gb"ck!6i%'eZ8?qTXP/hYtPbU*Y+QJO<[.u?t![i_MK(lRE:$s%g1d=YUAN;RZ;DTEa=#ml_
qC^X(aKJ%/lds)):#uh4]A()la!-cqU)$fT`bLqYlo`l>`,Rqh&CTDC(+&(A.9b+0T@#G)qnn
[IgV)*'CshFZaY`7VP7"X%CjD1lqa\C3%B_-,*N8d"]Ai''R,R7(^`iNo(PQCGObHr1:I;?,D
Ec37%drK`Rh778>UpO5Fs\7f3/+i?$t+5#_V(k@=ZX)VB@BfRF-UqNK-K`eA-]AUu7gts'@(P
Q3KO=R/]A>`qK6[2bTNU6M3pdRQ(q8.8oQf]A1t)9)ci&)88"5:'6OXnT6.3pBY\oT=dQ6YNsc
k<"D0mP,aYVM<%I*CsVlm(\chLY:Oo'\$2)InBGW.gq:6ocft_M/pt*iI$RAPU=^(V'TrEDF
UV:ok.4U<o_m_HS2geTERoB9^`$V<`i7N?).[u*5>aA/Esh(;1&@Rph2e$'L:T<1\\0%_eHl
8KZe9=bl3#>3]AZ#$)J+nGMa7dpaKohX_KPJI\Q#(Jna_!1C/4@%@V"AJKG?[mjZ%_ihTeAlj
4Q&8;[@!P)5LK[)tj_2m=G/$XLfYF<=)T=l-(A>Y:>ooX(^;EUP8V2bmFfkW?%K,nU?!<)F7
0NG%:G)PDfuaS0A3JjZ'^nTq@L*,t):4b7ss)Pa(GKFj"C)KE^h%Y?Wf&XRbbXE5:'a=Oe+I
CNP+j?_1s5e@h9'\jc6:]A.eO9`C&Gp2,Ud-WQ;7[/-)PH[F<;FbKLDAjs(uT,:$Ab`;d1.<a
@*[OQ?59AZWkL9u/oagQ_Ce8ph>G($L9ZX:FO<g$<DEQMI_[;6_@lU(s%rHkMM:/g>_OlK)G
GE)hGLBh/I";)'kEqCo8maQ=pVE#W^K2;H#DB426%Y0(7`VF+=I#!/MLY5smh)L3B]AR<&J7q
fuLXEQJ1:8En[Gk[b'#$9kW"'XkX@PJHZTNpXA\S/gd."0`;EH\5$#6Hu%N586G5CHP")Q!#
>iVG"XIe+BR:`kED6Aj:'sZl6k.G$6?P27LtD4c4bK7CNL7g82a)MH;)db,Ij`(h8O^Xsd-q
mC;Qsj"_V-Ju+m*Zpm1YU)Tbjh,O[i:Sil(,NCQIqS&aE]Akndb^J$%53OL2mWNb`B.jor2KQ
Bk-m0VuML=Sc'WuNas?bs@Pr@cediMEZ^9kFYKR(I\p_u8bks&)4k_tL0h#-0Hu'Ydm?W76G
(=(``GfG>.(SuUc1hGL.[VY-UXGiUTf?>1J`(N0uY.n15LnU@u)36P4Wk%a/6^=_/ep4fHgJ
S4Jmn[NaXiGgLE`QQM%lNRYFC7(0STL^qki=@<[QY"tP;QA%9_j]A<VX>hC6#NgLYVU@X$lJU
\ti''@4SUk;)=h;%;h>*WY<SkG%PdSL!CJs=hF5]AC[N<l@,^');+Q2+8?a7J%gdTJU5Lu/b^
=pDF%JQ$NR$iC*5m(rob@Z>i1roD<lD_`3b&ZJl]A,g,t&aZtaLBD]AL4P+Q@*_QI2P4iMdC^j
<aP.U<(8$IPE0h05E=IEkun=W<N#=13QjK>p?XZ_3hu@j:[0T4-l'p3-M%G&)PAS@nqQ#;$W
ulU_=Z9N$=HHIIG,S\kc-fS5(I"eAA=(N*Y]A,KIZ?hM(RYGh.7rTUD=;7_Q`G1&RQ6?0fT7C
,MF&<:$..%#kPu#Kn%#J)bKTp=i49DfdTY9`*mp#18t_&KpQD>cb2?#h>1hrJ=!k"V<V\r_I
D^ris&V;G>Y*l?;(GPWr*UE65S/Lb$Rlj1KaTDn"0\pu5Ufg=RX=_%rMc7G@_(5_X^&99O2`
hVSQCR!?&QA0oIr='R+/7VdSMeo)^I^[_O`5sP<*SW-BM3c7HDF)kg+-<!Mqf]AG*9aS^seqJ
SXk>h$W9h5\e>]AAR9`mpR&,;4>k<)JsE>R.)R4:Y'Y$ra>VC5t-iC[6aZ8QX>+4Qj<U,Z"Ws
MMskN8qgS~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[1143000,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4572000,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[分公司（联动）]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9FF+;ca_=;h=d9YRe,iZ<U/,SRO%U(^ZROYZ%odg(l!'6E\:AC(X>Q0t]AiG[A=lHWa.*10t
Mb*jcLqu<?@"iOZjn%9h@U'9Vi@nJ/DdJH/?)_[alo%gb'JFA;)-'#j:PqcJII8q`B.snEJ*
Wd1aP(N:ZW2R^cnu.rTZX4X0If)C+kF5s@<bVL?G,M`,k&/,tE"gY:HpP>\!e]Am*i_Z.e3)1
h&f$OiR0n%obYiY\_ORrSI'ccKr'cC>-?NBV4a`rC+#ZX882Z;g(C+]Alhe=G$_YV@0a!O;Cb
BVVFC'RObftcf<9ROq>b;nk%!(4f*=guA3N-,+c#H:]AE6lko^g7BYEJmF13AH8`TJ))M3hQ;
\be$I6UNB)EP'eT.iPl5^396XL5$I[i!5..^+D&UUrElh4uHo>XuGNNX(<ZHLCRYeU\Fqm`3
g$2i9JQW\?c6\r_YfKJV(h`PZXarVa5R]A/B:\7j>'/"%id:<)3HB#$c==O`p1I]A[VdCN`(\:
Q3G#9hP)(upcMbaNDK9$ZBpXd@5R]A';+081Z?='ar[XNKs&;0=!D#JHQ9'HQQ@^3m%?C>inY
YeChU(hWt>@(@Z(f\C"\$ot^aUh2t85?%H.bfjYnKgWc^""f`@u5!(JN9F&[Sdo3,73'\(H(
^[e7nhpaA+YIg0>!n,200PX\-dV6sU5Y@=o1+eH?%)doVrI=n#HO'Mh"ORpBl&55<kH-glt8
\lT\^V!Y;HlFD:fNUt>Z[NdQIn)P$!'=(f,l/aPRqeX/!qr[QGlQJ</E*'?jg4IC"TDhDE.J
^P<:!J)mEMO9$><96CUX$<.VPu2H,dP\k%gr`S'Z;oWI_JN+pgS.\S2%bb;5j=LI81?;V@qI
UP,m]AI1Fl3B5?oe4e!kNiKJPj".a?K_p^O_Y8JZS'EYP86-sc,;1ZZ38[B2&t+LBfUVpecGB
r*(@o%fMsJlOGiA_bMhWciF6%F%(F1!Yn).k<0*?<1-j-Fo;LV=WN>)?Po<m'322f*iZG,rm
ET"u4`Hc5O[/#\I+EZ-+bHG.Q1BoRGeOkVYHiHUP.+4]Ad_RhVV?"7EcNqphh@c?:s[dM^>i>
8rmp7/cmL?<nPc,F>6-M0:nEE,\Z.KB,Alk)bMO?3K8WK;\QWo\pf@jhE/W[]AS$m(T]Ap]AmPU
'R_,AgPlPi8T#oN-*;9$AB]A@o46?m6KP9fuYZX_g.QkE@:&f5jT^)cu>R6kdXTkGPSncZ+?\
YNdH>1Vt)d":kq-qT]A7L\8[QYrcOLn!>\ZB-Lg/-64C1$QE,hj3T06IJ;C%'Q$SI5Rn^NH!L
KXkM;EV?\nVqaA)%35G3`eWT&l'5X97Vp(55`I_NW5t53]API+f`<oLYlUcW\*;`KW1,pMHf.
)r'9laaq;5pHoX7A+.B40)%J>\iPg,1"$`:5jHc>7&eha#7#.27'1>"471O),Sg9.V0SB%:A
q9fGF@1?Wa8_q'k6K;$eUC\hM9I@-#-$dhr9YQO1W8:O4:^II$RtNKFd!_O`$HWj/H("H%G<
Vc_DB]AG@C=MC59^f:%S!8"4a-pE8iW)UM5]AF8?(k=rOZA]A451+?tH#^r,4*4F/^X`o6dEPRr
1=-s@/_BHf:oR1\g5.bctjmSl2`T4-g4M\00>m?Gc8KA$Cc#%^o3Z>o+r*3J%a63NZnW6I&p
09EH3OZ9"TjNtolW6;XEA1q:#*)0)hHu>X#E&?FabgtTM#]AMM"9'#"3=`e+@'hAq^FSl)nKW
.NEphcE2Lj$;'Bj)55%ZQPilJY-8(%"'[ncdW[O`(A4R50:YRKAL^CI/tIY7L*d"+O/_rm?[
D5oWi6K4oq-nk;ULAPMiHp@Nl<`<2p\u'6jDXuhipD?q7<(DklJHaER"kKc=RjE!66/P0mJP
+Oq"hURuk-kP&o.%VS!IXB#oK[$Qk!0sUN?F'$`94PCIVMnQ3]AE8soA6.khk5A%3)^_Mg&g_
6@Q%pSAiH>CZLA#H"AJ.g?jn4>ph=gaen&d5JX%$nJQ*nfK(tLhJI<0gbsu&oTa,J04QLWqJ
L,i&%@]AP;gLHOCs3TTd8K^i.a?9Wer3hQmW@e6Gi]AG%mMh.aJ79k+E3N\C#7:70HlftLkV-n
!l^;6&4YFe(@9lWHC]AALiYdDNTM1i"V5)HhBiM<Yl$O6\Ut9u$h7DQN=R?lg80:s^W)e$[qB
C$_!E"QeX.LsFc\O^T;-?:1H0.`oM:<Gip9KnAfJ&,$1iFe!KT[(I;."rr)$<(CXkEJtJTO-
+sd4ld'H-_S(W5dq4-oK=&#/'A`#5H54%b`/bB!J%5'B!)@=X;"H1464".Y'EJ8-gpdS3WZ1
9^&W?585A?!Ykpf?f^)g.j)',bHpK"JI$@Mg$SuGBr.#dbcVaXS5@u>=rqn.^N&ma6;EC-V2
Vs"5;aXWZ>Hn_.e0tN8R6h@$2lasT42=[F1NKVrMqL76H48Fr\iEU^_cK_oXqH5+U[DLtLCV
L&qK>Y'^NP@YpH-+p%iKMDKbYatVS6`I&NB)PLhGu$I,1e:#e'a:S?h=DK5sna&q_X3;&FN,
\6W=JW+?,Z<Z5of@1gUBG:U@?'ntt0^khmdag!sAeC=lA;p0?bC'UJEQJ@'R-1THSHCS3i$,
K>eX2o_<C85Tg?C&<Z;&6'bXAfEGHer0[a5@hYfA1O,QpV-2FT`,kflG`i#44m=<YOp8)b("
\/f!HQ^B+5/b7=_EWs)sR#P2([7QnDmJZm%R3e$+gY3</"#]Ak2LK#2e]AM9hMr"SO97onBZ_S
b<gQEqC6`b.0VA"sYHkZaYD'5e!7$J+%P4,uD_Hq2^-DXU_GVnsM$6kf,BtPQuB-d:[AnQRH
C!BR-!GhQD%qT*#1`fC1Pf&5*<lQ>N[dA+Yui,"0mKBsRhja&@WS/BQF"kj'tF&D)c#2#5ni
l_qI=3r;"sBUh57-@k:,O\7@R3^$RUge3Iu,:t(Ye*hLeIQou9YXRi]A@"Lq>+D?^9),HuI__
<9[B.c7fj76t6nk['Z;Nu"T?#n';Vh"3_@3*-srWBJrUUJb2Bb%%M<&cHeoOlWB?Qg%E<KN<
8mL0HSps-X,rU*D_hQ7-I?s8Z\>G:8cnU@p9"O@!]Am!;CP@Zr*P2eE<F&kH$Qla.K1eQg5@=
^*+88*<tDK0@fN2P;"O+N$?,Y&%Ol[.d*sD,a>dBbrs1Ir<aNE'1CR.(8a*BGoVA.E:B<Wj3
mt/=mad(_a*>SE\Ug8+Pk>:PRk3oq^%5e[-;G9.')F!I9(5P?NS$l(T-7]Am(tq]AgF_Z>;rVE
0SP:uAP1L`f5`K+!he+K56JgZ@3,uugpd9^Mn=lleljC'mfQtXk#`.:pYc#MJp1NNOoqM/A`
2[E"h>t6RPu>KU4B/mg)XR5!T/q2$c0niK/%kA1(<b+&"W<S($Fhm\m*cdlmgpAkcAB]AGTI]A
A6*C'3A[]Ajp@O,Nt33J<?1CN75H6e/KQ,A16Hj\uJj-"/g-k3E@EXGQC-B%rI6-e@9j7#$q"
ap=N9'E4#&*X,iY5gM)W8eQ-72ee8J]A8QtafqW.TTXq)Ti?='l6u.JYe[dD"*VGI$38H=UE$
7'2bU<>!,@;BT@aqM#21d#l'tR9K:Q+%fS'm'YBT\M4KkCgbl<:cK'2[-S$f0\]A"uG%@B(l[
DS4FP`mjO2[T83<E/YD9"*11YEYW?[!H0.$s!X6eP[X?V@F5ge`JWcB-3b0&`QCt/2<aC=P5
qY$=o2gO3mW]AeGC,IF_C9h0X[uN[BX2h*]A`2@IP7D)A"""mOSYd"Z--n&oeots1[`JIqi&f:
a6<<M,N(&?H4@""sq[_c*;\hV&^f;V>kG)9Jk5\]AdIl875B(kiQp,IYn3^U:*[M7I.FITX['
SK$_O!kf$D$II@'D?'O5-kXPZUHX#fQLD/BuVJfR;`I5?UA5hj0*Y%>FW7`s+*=.."G<gJVW
D)NqIjq`Q9S.\DlaAjOoS>Y5.gd.c%#(rQsZ(!`G'c^Ns0Elu@4Lf*@UgQ3+&E&p-8L#h8Pd
DKCFmKssL2^a]A>4%ijPcg:UoLS<YQb'@rLs)T\"a!N`%*0O9b2.mc3d'nCql(isF7VP8X97Z
tr!JE6:MdOPHFN,NZe"An`K&=LX27X,REF?q]ANk"a405\g6=oX3c*-"ihNmi);Fe%i9FhsO?
YpC/79S^%G2rE:!4pE`[^+nHl4.N$?RC@F1VBGX8dPh^-dbG7P=#Xl+uA,MC9S$Y)iJCCq;$
*[ig!.-e_h+`f+a+.UtA!j]Ag=CiR5;[Dl[[+mG>0u;kJYKP=S&Cbs]AEIAnf#A6Ql#:Z*:Gp.
od1>kP"GoXqs.`dTG^gt<Q'^'*!?$V&G,sHud]Al/H)Z%#KRD,5eSakPhO?SiGD[pQ$_bD\'G
#HK@04@,IS:9D">"SSoUoo]A`)H^k'E.`<('bm(9OS>lkeX3N&;@jsBYoPbqn/IdXnl5WRPGK
":R#!SfT0$<]A!GZ00g%7Zf#5.+*WQYCpKFVSETD'/L=4O>]A5o@G8:=l%(k0JMfKF3ef#B&'\
"U]An!B&LY*agt$%n^f8*0A9F1l+i?9]A4m.N8U0*ntoAe1*?"=2SU-ThK8fN]A&SNPeep<s,oZ
P53QP5pb>Nt&%GJR7Dg[g1bWn?upN3oaV"C!)DT[f0j%j8EaR(S5CG&tU$cmn9gD"ao9(N72
oaS52U#>X2Za-Z@fVjSEp0*5:P)#I;s4'B4F-.dTT77.#RQ7>ea>T-aMBr^p0)oYH"X~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="475" y="97" width="250" height="56"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart11"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart11"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按岗位层级统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8o3IP'8!\Y@mI_>'"I0ed_@kDm!8YN]AARTC9:`TRX*Z#;7UCp8sTfjJBA>QQ'EpO0<@i[?D
'V26sqU33>GFZV?j']A>p)IO5!1sr"18";ml<PoE6cAiHiJIX^[H6XVtYoM/Uh3K)[HRUnZK(
0gkS/-_M!E&s*bD@fo#*@KKq*1hY$!=84ojnM#,RsecA@^`)T:c[GF4DJ9ndQW5SXm%o+>J'
DC>P"i3Ae]At`^1F,/J8<PBndo#JY$@iaO#7C@R]Aq<Eu;\%T28aA3=YAa[7a]A</3QNDUAbX7S
u+7/2p,9hJ6WrLjnTWVs?%qj::/h85Q.R?_//EWYaqHLd(MTR:#\hNW;qB7&:WdY/QNn0679
YkK&/R;e^uirpNC_SokH/h*ac)`IW@3us)(Crr+YorabnN4kfk/?>9n(5an<gN]A'cKJ_]AIR@
NZ0lT3jG(J,8bY&DaZl##Mu]A04nmpW6+tjiZ97M!3@]AATqb?HQ+[(aQp3n[*%JbZoa]A%Z]Ar)
LmeH9'7La-=)6M67]A3GU-4tJ9)?J>om`'7$_I7]A^-.a=<HdAB!ODfWWtO23:&h)it"er%`mm
/lGbFE;#>H_D8Z,jaZUa%8""BhoR'EY@[W^O2_<9?8e#=c-jO<5>="<.d3&OLB4lgAcH:B,+
(,>Yj\$r?!>^At8+2;D\\M'cVrb"@WCKFGa<=>tnu+(`=kjo*)&^"Bb<ZA@t/cR!^5@E\uPe
IS2O<nk$,hQcIc9EN22+O]AfTRJN^)(TIt)UmML8`I]AYaV=s1Nph8M=aF0H=;(o`BL*\\1OY,
2.le@<&,KjL"fML\kMm[65F%&q.OlHmg6W0Hp_FPph%-dut&qACONr%=uXR6/g)C(?V0njq+
s*R^40["TR`NFJmcHq#pG+-hmA"6lAS.Uq9*Y$4O$:N>0PW9OkSTd.k3i"/N,&eN=:DAK3Ld
eYAY9>XItL@W:^5,3RPS`VWmK'gR<MO*%1>E'!,'[AY$rn($Y<DRZN5AHf8k<lB)XN']A&T]AJ
K=-m]A4Se6doS@HGPlCDkC#ClpA(#V5;mEgpr.9#!t%$H>7tEClH$dm<Zqcb):RBkXdmNJ<Y"
9$-!Es-_<C!4]AN,^eTumAq!ST?UK]A@pU31`'RP)^G2eN]AhB<>d5eeIL.Gk`F.p#eWUYhlp"l
eKoa^Z-s(Lfha<@:-38Oq%?2mF:qPg]ACn3Mgi@r9]AGeQoUh4R2Hrg5@5CoQ5G#d)Vj)\hefV
6"-eP02)'^U<7BO\C`!HM%S*ssO31iO$lK-IY73!lr=05u3>qeXl9g[&`_q"3IYLlXgM$9cY
RV67>sR.VKo+Q+3atCqDQg:k,?)jS^.VU&Dq:KW.K=UqU]AkcK>+ZAXS&@M..s&XcTV7k"b[k
Q!RX]AX]APRVq!=QB_d);kLgc5LPB(KtYal0Y"Q.<"UTAJ'\d<1r`09^-Ok+L1!2Gkt0b<:S>u
NnQ?*aH0-WYsY%MS/ZaO$c#cfZpK;-\t#[r*([`o?/:r+\-'W29Bg2_(g6M$L>dk3^Y_-!ak
PAn]AK!gFj:Nkll,g\bUga-YXc.20\4Gkq&hB@?3(X[I9sNJ9G(Y<0OOMIh&[#%j1tTer9B(5
WNbna-,Gfb9@AZN4W&KHkl6"!m;?ki0QTq1(I+?em;,JN2YNdjl-TXs/<%gp3&iN7OQX'O8%
Aq.K$)Y^Mk+$J+SKT#X'JHRiPNMJIZFeDb&$iL\0g9=6Dj4Q(E<f6do0UG0-7;"?C%$?.C4>
<O4mqXhk9K6.T%RXWV9Y?o'!HT+8aM!gGca?3L`5m.(m0l-KIWj%#<s]A0LSFSB;,fIe]A:,=A
NQ=]Ak)Dli;1L`b/O<&GZS\uheJ@jd^oiUQK@N^%'42c9OH_dpT+HoZbg1'kPdG;$g?SORY;*
*VS;;jD**d'k1du^/BqP?klI`=%Z_bcj`DBCen8T%THc%Z.Bltl1t0^Al>J=;auG6?eX!Bqp
=Y?JlJjZoV^hVD$)g"sg`Wp@BaK73Gui!KnHC*^-!fZ**:bKr`i.lJ4=]A-Z##30flel->Ebq
2LQPOH;^ZnX5qN=j<MOk6?-4'iHb(-VS`Kjj(a_DTZ.m0\[mj8aRgTeDlGC`A+"Xe%_)2:ku
<c%e(MH?mS(o#D6Y+eMG(lK6#g>%HE>&OJ"fd41E5OYu]AO=B-YT]AbXFR3X5_PUk0QKqV/73h
>aB?,\O=N$j'7ILK_hW@j!`=l<N?dQi>U/XW3aEriTqquaX_<53k3cV\3T<U[nd0M^1&)>T<
RP,m4+.pO!&[BB?+M_#Y,W>EP/l3*HV?pE,_`NNSsf'`<8sT/g$Z^r^OjXMaNiJKpk+4p5[k
BHkJPQhE!#5%/e,:UbX.:N\"9q(VYS60:\<oq?1^S?rsMtch9P-<9Xo@bJWcWNQhK%Pg3s9=
<2'-L+KuZZ24;80^:3dN`.M>[,k?h!'u079Sm$'g4"?G7AIJB^A^\bGgKXHo#`Tt9:^,A5&4
9CbHIunDhec"o$g%c'pF%uC>U%NPmb(oFskD'X17fWj*i1Cg'JZf'P@I@od`u]A\n\DD`@#<*
e3U2FrMG]A#14H0ao!!O?:.*(MW7A1G<g'5i"$fjH^a:qT4Le/nd/\b@6*>er1S`pImR,`tO-
+>9D,,;[)dfd!A%E@ImlYI=?(l=H1XFY0VQ6S7(ZKL9:'`YoRjMJBn&%@T[PG0]AK"slhM'$^
k\Z.(e\&Vn+ec#[Fj0lI6Sh'I*J??5*pb0*8+#i8<HOS_]A&r2Jm)7$WgPok$IEHMP(H"t,Z]A
=>X<Q^Oj`.)!\Ig[s07Om&3e!Oo;01B^B(X`\Z`$nG+R^t''-l86u^pXCS>jQN$RCD7LB5'J
'?l>h'O)T'g<pY2ug(-:s_k2NVNBA,&o2M%67i]ArV_(@!Hr16[OI$qEt/MWFI6XI&D-5\,h6
E?h"DYX^\$Zn%soY[1Y8"!2('3=)CH:Fo=(euVDLR?K79,o+O2J\Gq]ACC`fs-_P9hd3@\Do3
:s&$:Wk?YI;A;'eNp"3S+VZ:q/\hG<q$>CBnAK79TA6TZ#$cKr3&/c"^;s4W]AP(YM4<s5o-'
Q8t%N//8+FQJ/DqQU*SD3ES@@N8>eX9:UXp"LG#\E-Osk0iJ8@&AopD3p-,rm*JNfA!c<l,O
Em,QBK30?G]AaC7LS&ioX@MTh$cd!/6O2d-(*dB0Ei10&b3QK!s4Y!;//9q&PE5$sC&%>JQ<H
@X?<2!Ppo.lS2coP7/@.:AGjkUUeQ.-X/^0YbcQAtsB`Vr+CAOj:&-4V=T_PJfbgcT\4t)jB
(r*amoCrb%J"NTiB>iHd.pISRmKc$?iWChJU:&P=`)R0(m<:]A)#@U10(@gEXiCi-&iCi-&iW
#%ES1-HugZE\=8k;&jb9'`Ka$G"o@R&tDRu^sX2QV9Frj0qU302%k"^?7ZCOXPgJdqZ^(kkB
ul=b,hIb?m?Yi"BcHp]Aj*NQq2J>93.M:RdAPPN/e"oS?l*T\1n3bF&9>Vi/1/EW.An]ACm8tp
T`P9b-AfpZ7(N!QQcb=UWuN0j7'c27)QM4H^(r7X2]A1(LVM8rg#?2uP3Idf4KRH/O#l,cNI]A
LADCU;cA0&unL3"$6;Zg5'IXbbB3@l.sZTi2eGlF!RcqSa*h68RMp-Pg.N'N07BQuQS7#O5N
A53I^p6#ba+g0-nV1!C,r7hjV/5H7>VR[jkd;oe5/J'CAVhfZ/H,KDd%[86&VJ,!o$j.fdA/
tG#Q`)\I"Y=Dm:.jXC647h-TNj:DA&J55Xio*q.Kq0X4l$CePPjP0a'%<s&k)QHglD;Mg6>"
R2/MVgq5Et-KFN)^WQM;B#Q1$bi!(Frp)tUm0@JZ-=64G!<K.tQfdu-FF[P;Tg9Nhg^L1jXp
i@'7G'[))OXJP.5B2p7[Y<^\Z+\9-"hA?'!Tb+^b^pDqZYN(pQg?C!TDBW~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8o3IP'8!\Y@mI_>'"I0ed_@kDm!8YN]AARTC9:`TRX*Z#;7UCp8sTfjJBA>QQ'EpO0<@i[?D
'V26sqU33>GFZV?j']A>p)IO5!1sr"18";ml<PoE6cAiHiJIX^[H6XVtYoM/Uh3K)[HRUnZK(
0gkS/-_M!E&s*bD@fo#*@KKq*1hY$!=84ojnM#,RsecA@^`)T:c[GF4DJ9ndQW5SXm%o+>J'
DC>P"i3Ae]At`^1F,/J8<PBndo#JY$@iaO#7C@R]Aq<Eu;\%T28aA3=YAa[7a]A</3QNDUAbX7S
u+7/2p,9hJ6WrLjnTWVs?%qj::/h85Q.R?_//EWYaqHLd(MTR:#\hNW;qB7&:WdY/QNn0679
YkK&/R;e^uirpNC_SokH/h*ac)`IW@3us)(Crr+YorabnN4kfk/?>9n(5an<gN]A'cKJ_]AIR@
NZ0lT3jG(J,8bY&DaZl##Mu]A04nmpW6+tjiZ97M!3@]AATqb?HQ+[(aQp3n[*%JbZoa]A%Z]Ar)
LmeH9'7La-=)6M67]A3GU-4tJ9)?J>om`'7$_I7]A^-.a=<HdAB!ODfWWtO23:&h)it"er%`mm
/lGbFE;#>H_D8Z,jaZUa%8""BhoR'EY@[W^O2_<9?8e#=c-jO<5>="<.d3&OLB4lgAcH:B,+
(,>Yj\$r?!>^At8+2;D\\M'cVrb"@WCKFGa<=>tnu+(`=kjo*)&^"Bb<ZA@t/cR!^5@E\uPe
IS2O<nk$,hQcIc9EN22+O]AfTRJN^)(TIt)UmML8`I]AYaV=s1Nph8M=aF0H=;(o`BL*\\1OY,
2.le@<&,KjL"fML\kMm[65F%&q.OlHmg6W0Hp_FPph%-dut&qACONr%=uXR6/g)C(?V0njq+
s*R^40["TR`NFJmcHq#pG+-hmA"6lAS.Uq9*Y$4O$:N>0PW9OkSTd.k3i"/N,&eN=:DAK3Ld
eYAY9>XItL@W:^5,3RPS`VWmK'gR<MO*%1>E'!,'[AY$rn($Y<DRZN5AHf8k<lB)XN']A&T]AJ
K=-m]A4Se6doS@HGPlCDkC#ClpA(#V5;mEgpr.9#!t%$H>7tEClH$dm<Zqcb):RBkXdmNJ<Y"
9$-!Es-_<C!4]AN,^eTumAq!ST?UK]A@pU31`'RP)^G2eN]AhB<>d5eeIL.Gk`F.p#eWUYhlp"l
eKoa^Z-s(Lfha<@:-38Oq%?2mF:qPg]ACn3Mgi@r9]AGeQoUh4R2Hrg5@5CoQ5G#d)Vj)\hefV
6"-eP02)'^U<7BO\C`!HM%S*ssO31iO$lK-IY73!lr=05u3>qeXl9g[&`_q"3IYLlXgM$9cY
RV67>sR.VKo+Q+3atCqDQg:k,?)jS^.VU&Dq:KW.K=UqU]AkcK>+ZAXS&@M..s&XcTV7k"b[k
Q!RX]AX]APRVq!=QB_d);kLgc5LPB(KtYal0Y"Q.<"UTAJ'\d<1r`09^-Ok+L1!2Gkt0b<:S>u
NnQ?*aH0-WYsY%MS/ZaO$c#cfZpK;-\t#[r*([`o?/:r+\-'W29Bg2_(g6M$L>dk3^Y_-!ak
PAn]AK!gFj:Nkll,g\bUga-YXc.20\4Gkq&hB@?3(X[I9sNJ9G(Y<0OOMIh&[#%j1tTer9B(5
WNbna-,Gfb9@AZN4W&KHkl6"!m;?ki0QTq1(I+?em;,JN2YNdjl-TXs/<%gp3&iN7OQX'O8%
Aq.K$)Y^Mk+$J+SKT#X'JHRiPNMJIZFeDb&$iL\0g9=6Dj4Q(E<f6do0UG0-7;"?C%$?.C4>
<O4mqXhk9K6.T%RXWV9Y?o'!HT+8aM!gGca?3L`5m.(m0l-KIWj%#<s]A0LSFSB;,fIe]A:,=A
NQ=]Ak)Dli;1L`b/O<&GZS\uheJ@jd^oiUQK@N^%'42c9OH_dpT+HoZbg1'kPdG;$g?SORY;*
*VS;;jD**d'k1du^/BqP?klI`=%Z_bcj`DBCen8T%THc%Z.Bltl1t0^Al>J=;auG6?eX!Bqp
=Y?JlJjZoV^hVD$)g"sg`Wp@BaK73Gui!KnHC*^-!fZ**:bKr`i.lJ4=]A-Z##30flel->Ebq
2LQPOH;^ZnX5qN=j<MOk6?-4'iHb(-VS`Kjj(a_DTZ.m0\[mj8aRgTeDlGC`A+"Xe%_)2:ku
<c%e(MH?mS(o#D6Y+eMG(lK6#g>%HE>&OJ"fd41E5OYu]AO=B-YT]AbXFR3X5_PUk0QKqV/73h
>aB?,\O=N$j'7ILK_hW@j!`=l<N?dQi>U/XW3aEriTqquaX_<53k3cV\3T<U[nd0M^1&)>T<
RP,m4+.pO!&[BB?+M_#Y,W>EP/l3*HV?pE,_`NNSsf'`<8sT/g$Z^r^OjXMaNiJKpk+4p5[k
BHkJPQhE!#5%/e,:UbX.:N\"9q(VYS60:\<oq?1^S?rsMtch9P-<9Xo@bJWcWNQhK%Pg3s9=
<2'-L+KuZZ24;80^:3dN`.M>[,k?h!'u079Sm$'g4"?G7AIJB^A^\bGgKXHo#`Tt9:^,A5&4
9CbHIunDhec"o$g%c'pF%uC>U%NPmb(oFskD'X17fWj*i1Cg'JZf'P@I@od`u]A\n\DD`@#<*
e3U2FrMG]A#14H0ao!!O?:.*(MW7A1G<g'5i"$fjH^a:qT4Le/nd/\b@6*>er1S`pImR,`tO-
+>9D,,;[)dfd!A%E@ImlYI=?(l=H1XFY0VQ6S7(ZKL9:'`YoRjMJBn&%@T[PG0]AK"slhM'$^
k\Z.(e\&Vn+ec#[Fj0lI6Sh'I*J??5*pb0*8+#i8<HOS_]A&r2Jm)7$WgPok$IEHMP(H"t,Z]A
=>X<Q^Oj`.)!\Ig[s07Om&3e!Oo;01B^B(X`\Z`$nG+R^t''-l86u^pXCS>jQN$RCD7LB5'J
'?l>h'O)T'g<pY2ug(-:s_k2NVNBA,&o2M%67i]ArV_(@!Hr16[OI$qEt/MWFI6XI&D-5\,h6
E?h"DYX^\$Zn%soY[1Y8"!2('3=)CH:Fo=(euVDLR?K79,o+O2J\Gq]ACC`fs-_P9hd3@\Do3
:s&$:Wk?YI;A;'eNp"3S+VZ:q/\hG<q$>CBnAK79TA6TZ#$cKr3&/c"^;s4W]AP(YM4<s5o-'
Q8t%N//8+FQJ/DqQU*SD3ES@@N8>eX9:UXp"LG#\E-Osk0iJ8@&AopD3p-,rm*JNfA!c<l,O
Em,QBK30?G]AaC7LS&ioX@MTh$cd!/6O2d-(*dB0Ei10&b3QK!s4Y!;//9q&PE5$sC&%>JQ<H
@X?<2!Ppo.lS2coP7/@.:AGjkUUeQ.-X/^0YbcQAtsB`Vr+CAOj:&-4V=T_PJfbgcT\4t)jB
(r*amoCrb%J"NTiB>iHd.pISRmKc$?iWChJU:&P=`)R0(m<:]A)#@U10(@gEXiCi-&iCi-&iW
#%ES1-HugZE\=8k;&jb9'`Ka$G"o@R&tDRu^sX2QV9Frj0qU302%k"^?7ZCOXPgJdqZ^(kkB
ul=b,hIb?m?Yi"BcHp]Aj*NQq2J>93.M:RdAPPN/e"oS?l*T\1n3bF&9>Vi/1/EW.An]ACm8tp
T`P9b-AfpZ7(N!QQcb=UWuN0j7'c27)QM4H^(r7X2]A1(LVM8rg#?2uP3Idf4KRH/O#l,cNI]A
LADCU;cA0&unL3"$6;Zg5'IXbbB3@l.sZTi2eGlF!RcqSa*h68RMp-Pg.N'N07BQuQS7#O5N
A53I^p6#ba+g0-nV1!C,r7hjV/5H7>VR[jkd;oe5/J'CAVhfZ/H,KDd%[86&VJ,!o$j.fdA/
tG#Q`)\I"Y=Dm:.jXC647h-TNj:DA&J55Xio*q.Kq0X4l$CePPjP0a'%<s&k)QHglD;Mg6>"
R2/MVgq5Et-KFN)^WQM;B#Q1$bi!(Frp)tUm0@JZ-=64G!<K.tQfdu-FF[P;Tg9Nhg^L1jXp
i@'7G'[))OXJP.5B2p7[Y<^\Z+\9-"hA?'!Tb+^b^pDqZYN(pQg?C!TDBW~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[按岗位层级统计]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrSeriesImageBackground">
<AttrBackground>
<Background name="ImageBackground" layout="0">
<FineImage fm="png">
<IM>
<![CDATA[-!(C8<9sRU`QVF>dj<+B[u;5@ABg:<84&/d1"JtM#%;=XM#7Sl8R5Pia>VV.$kBEl+^$//SB
4grk1DF+q2bD;hfD!Mp3k84chkH6pN`@B(MkWjKr'K30USg#g!b-B<#u6rWsGHq\`#DV,n+t
T^#`s,HV3_ea,Sa!dpIVOb[m#h^:jKh5)Fs[gB+h%;3;?aWW#+d#`hB/IBN4M;tbS<pqub+F
F(-S`,UHFmB[YmO,r5^c8$Fm?I,k*I%h=[\aSA&k&2XL5N&ZSBN_Rsr8-B9o6W-2b^\o="'9
;E)Vu=$#eH9X*c(:GcH/rQ6I;ius)++e]AsZdO=`cQ3]Ah5+/lX)laq09'c53^1H6*9mU=.D_=
K>r`@8W38&a%&gU=/;n*c4]A<-mNbqNiDI,p"Z`!NXfjh*Urs"_g&e+c,Y>Z)U,Sqe6rZVAHK
CXOO:?J=Smh_AYlK,_A6cc'Q#+um4]ALQ)k(4LP^A->\rqtY>Ke#Xu&B90!TC?RfiB-(H-JFO
tnbM/9fs?aWB]A*2,o>"IM2<WBc";c[OTg"pFj`b4u+u2F@Di&'hotP:WQ#ei;Q:AFK<GMsC[
R4Dt77Q$tB0sHdgmR:k8Bf2_,M*47&]A(KpdU$o1d<aMgf#oTV?%ol1[DueZ$o4d&np;K^<F^
g:[p5$CU+tD?mAqQLJD_@I=Ypp]Aood4uKU6?EWeD:UD:'7CQ]A0,MIBm+`8a>-bdq:D-NdG.t
_:@gXUYSB\%T=J0n:'hhnp]A4kL?Q>MPB!k8nNOoh5KPdmR$'>#GLQ*Bs,Ltq/*G!ln)P.fl%
9>faa_rtc*\TVnT(q@$8;BL@A7dYfW30;#.i_=k$*0s5mh[OIVl4;PbuM5@4X]A?d(0Kg6uS5
^Xc;rUV>Jt]A`D'/fJ:HnHh?h"]AbAtl:TY04Y.jlUmQk&l/ajBfP8@[Jra/Rsuka<u!5RelnM
e\==OcftIm;WBJO1=%m\k1`\ghC\m<d!-UCq_0$BlX&\f?lOt&Y2;BP`HIV[6EU!1Oc5\m)$
j-he)E+c!"IDEl^`2m3Q-U1=TgL.SMlG1('@BQJ4:P97u6*j+2')^9iT"K>&Fi-,Nc00VW0\
-CC-a\Z-[TiZWKZ]Aff''\f2a4m5Itm?flSm[ti/)aBVXne2Va60sSf;*OYF4O4'S]Aa/M1<Y9
`TTmt^fLkZ!H9_:ihY]AoO'o[#C7an53:_)Nbj-mciWPfZuf=7KJ)Oit8\ga?=1kNQ2LOTaf3
5,V=Mq%Ic7#(HGKgDu-DKRiFj.@3&q'*<C`lmAln#\86+[6Z?,-VhMR:+JnC#_.('7'E!>(T
NmWO'UC1<M+WH<b/NC6)iBW'1:s^fbk`<S<7g&-GId`l&*KE?l#_!IYWKYAY+_JV7BG)RDV#
,!6kMdN1d:s=2!Rcc,*<RC=kr+9Hf??Fk[V6_o#O\;g:nKnE$tV+(Sil]A9W8rndY&Y><`H,H
)M<)H1G0jK*Di-"&;/#kDVfrA`p0+Z%r\(9C:H0_fTleJS!7lKX%*;a,Z1Y=o^j1;E)XVI#t
enu]A1@rj^tY9D3naLbRZ%.T?h^Tr;tgE@9XH*q]ATH#$$\V;!GY6>3:_c->%IH+5pf%MD#uHV
,f52c_mW)!sG5g1+eAb(e5/^AD:Z4158gb$H;::_`mNS87DeS7$Zj:ugfbNI)k:&:??%45-c
a6Nt[qG8N-HC(+j\*!'I4Mfu#`%]A1XQueJ'g6X#BO1NOH<d;I&'-b)d=SNPr(NXK9');SR-8
(`rUP5cS&T(0_u)8a]AqTqf`lj>]A[KNe=lCs8@>qh%nlP:o@f\Ife7%;.YPQ)brbsL8V&&g8p
Fdcpd1a1n)cW[dVMEitO.$lXTb>WZ4)UH'cC^oIP@ZeE*Oh\#`EW,_I='Ob6KpIo(&6L0=NP
4>BWOp;>Wp(0CCUC;VlIA]AuoOc)R`rX5rJ0*ZJqo=GD.c9/]A.s'Uof^A&iGoFk)-@^/1m%l>
6n/(7X2SZ0'm,*i2)sS]Ac`d.GU&sR_sp8^6XP>_@[4\-&(k_K'VBT@R<[(crj+p!Y[ZJ$(B+
iEYhWh$p'CgZm(AlJjsLdXf&1*"Ylmu,FMWIR[4mb;ZZeBrIEVoNC?]A1#+c^;GN`'tA2V5_Z
Un_p!hb;kMFu"V1!@KD4O$-haR_aWo)lVMe<5&DCEMX,hOKaLQl'VqsSo.+f5-7YqVH_d3F)
b_*1VBMap;ZQ"OJBA:\0kMDg)4\LqE(1%\XDZ=4Ok^LAU2I2!N$V]AJ?!^(J.,&JmZiRdd0dJ
uWu0F"tARDL]A31/)G@'ineB_-b5ENrZ73-"7DeUb;(D-r#YRA'.`/+9O;3=Km?bTl&ru-k0:
OZ)#HoqE@!#JKq+TplCNK0m``_q!H]AOh;:LM]Aq.eqH<73#`N'fh]A:42_0p(HHYRrJOh6Y=&a
I$*`DLIZ_f<,G2!PWP)"<BoDiL*:3diB.eY,YfR!\=CA'lk)DWFSZ*DGtT\]Au76u]Akbo57_]A
pZ[eT!C7]AebIl4j(Zf1nXlJ5Y2-/r"n7lWju%]AqHai2=00JJ;oN,V'me]AfrQ%d1C%D,r8^nk
BX=o8VXY*N6'Q.P+$#rW?t<#9D&a1K(#c,Yi;af<Mojd%>F),.XHo&tmGn^b'Mu::(D,e>[/
]AO\(B;`!cfQk-GOGsPFnHCa:`D>8WgC)u@Bi@p-rc6[o08r=cL>W_?iX>>F"oi&UVcU()m'A
!h%jeQe0^JD2mdZcP[q=?c!dBq\j"[_"tpK&7!Sj8njb/:U`/oEVa7U_\d;LROp8>584Y(rn
m</7NZ6Zo(q$jE,mn/$LXAe08uQY>bZu!^oWO'DSm!OZ`:A9H_,tnEkN[<K!=$;A8rkLkE"D
hSa.QBlYm'Z%Qs6Qs3L2aFC!%E'rI&Q]AEX'"n_Wp[mW0$hs="D_fLZZ($I`ukaoZN[M(Mlm=
-1=q:lF*XsXSDM*N'`PN>`/UHWi)"&*C3(Jce[G]A)2oRZ2=;#@LT%_*KA`H\cH+0n7D(C*7k
1]Ad(1M'[O-o@PNAO4_.<agZ`i%@RJ9%/)JptH4gTQVpgHpT$5:;8?>$OGd0O,9!a.qqjcI$A
!6+=iXZe$-7s)j@Fi<Q@mnJ-+^"-NfB@'N_5_qD>K2gK%G'V8nW=7:<[Tr:Z?g`Rp?SSKmgc
<:YP6h3ml^l&mP3[@=*A%T^NkF&2\eN/mKA;a:0f^p`uhl!h_>_%Jn#kk3Ur(S#m!0h2X0KS
P4E8C*;j4*7]AfAEgDGAGX#AY[YiJq'hI%VOs&$`YTe)*I^,'=A[jQrL%5iFV\!5<@hj^A?UJ
`FF+tF.co[(?gb$7]APdcQ[?oVA/u\R2b!=E-RAD6?/)rUAnBG#lVpn!kW.b*/GK%8DY@%,'H
t:n&%&aA84iO'ZJk(>&t124I'WSucqBQX__Ke9;(;V,.Io-njKNNkGPBCa]AAR>[A^hpWCO;"
ne7Uq*6$?WZ?ko$A$nP\"HggfS+6AlEf2oK:nrio;7bW?!^='$cEXLosOVO!R/jHl+g%h=`n
#5[#/!R[9feE(?>5"*3^94LuTmor-@Uj1<s-[B<LIps+T:TLP0b9&g)NKiDe7uJ0LMITe"u)
\eQkh#(RjXkQ[SQi=7X`SR391Bh!6K4-RQ@T_KkqRY6FQ;1[/D;1AtmC-6W?@5"IOe*#=:e-
Q?P;9CO6!R<*aRqf]A:)thamEhN7qs%+J4"'$G2)Ci%fpF5,f-?)t/u($A]As$HG0K6gV/0rb8
F<N(L;j`>jrW6cM5:hjV_X-O<.(%T@.uf>B=ir(+fFk*L5$Oq?>)7bPd08O\FOB=jj49THQb
#.m0hW.pgad!]A)s-0HdG'-5)_+hX>tO@W5P,l4_cL;nK$DO+`+AJ4LY!o4Y-uO<hp&90!-_l
+!(pR9*-o,H3Y?PB^?X$7F5O7nOh*Dq_u!I,13Y4QRZ\mel#KZ'H^tjZ@ce.&o_@5mNgP[0W
s2;n)PQ1QL_Umo)d#>r,m*-u]AG8Zn`KD:QV@_AKuAq='6_g6Y&,T-;0N,f[tuPqITYTI:Sd'
HtLd=OB!9/C)eNtm>VGYL@ZnQ/NH`Z"B:1<6'>F4KNF*0SA9kX0jH^mSAn#sQ)LTM`)sS\+h
>jah(n#eH86a(Rl`Z9qK*41CZ<B`r0r%"-/A@:o0=\+5K^7aA'80X:4>M>_l()dX%b>5?I0Z
U:sMpSFlYfWjR:*[5BE4:3s!/\cb:Z2%+JU(\(:tD*&mA*VN\M>m[41%nLA$38I\+dOOT`(?
/In8M\$CPF!_K#nqh/<jD?JB]AgT?Js*nj*d?"bJGTobcH#P+JXFh*8e+@$%a1RLp"?1,0rS.
8Q,BKO^,-sLq(V*0pT&a;OoiM&m%DW-tF4:J+0drc$D3.2-6W2*7;bVsX1mKU2_```c"q=%L
'nqsEPTMB#,`,nmp"[)0>J>ltY@K:dK'Ue6#`diLF#,LfTs]AB";&au]A1cdgH<"VIa"S2ZnjG
r!nhQOJ@J*hUld6'qn.+#"Nk@6e*hC;3fj%+5Y;c-d+d#loF(YPhD==`\[V+3Xq<4$&2g?X<
t4GMY_+F,PQ+SO$rXK/\P8*."UK(J5KXF!`#7oLa6ekgp,$q5dfJ3&'#r+"$QO7!HlK^1A0=
Gr.^7*Df1hMrF`eS'c+Ran?co'"@;r9fo+Qc!gh^YBSKFjB19m9DM"fe`IWE@k/gN7@9R-nE
!5qJ.-0`V[gA;9&tIq]AICtXJ+"VoTqA,A9^KNBk!Am[hGQump*6L/[s>Fi>XKV[V3E^*uNNO
6kucd77RNh-3G\OOS9nc*+_a%^<Uh#dr1\AqR#7p'`I%NYK`>Xe6S1spqVu<W5naScFhACA<
#h@j+@'jM!:hR582<A4-**A5K/^l<&bZtc7:8U.3hOk\;!p4@T[cfr=OKCm(1m]Aq#EK4?OD<
(^X[6$er":DbD<rGPZ/7TU\0,EA`ak;Tk\hRB0\kh!=0RT;At!%TA+lLEtmDWJ!Ui^2h-ic&
O3H4fee3<mEC:E=j9^:'1B%$W`P44"@d/2MG/L%C?E/@a`#eK;!o^T_Zo&p8=h@H!XpWN,%\
m1.s?Rh9X<[t-l$\jlbZgD3]AhP#a6+jWqc-V7+i!mjV[hNhj+S>T^_N_nXOZD?Oud_F[:Agn
qgKSIcjS@A>t5CgJ7Hkd`N4:'m^D6FoJ*".L.=9od?3KK.W_.G-dhDPOg:>L2%.lV^<cII%i
emHUH?k<B_o*tqoF-iqT)u7q@gF7MZsjSO7c/L_Zg\J.)TBF1nRIVplof(9k8A6q8Ru$hYe<
)OiB#]Ah/g]A>Pqi7toC3`S9+L's0pa7-e+[?CJi."PM!9iDk0L)66^[WM>2Q^r9_ed@c:Acr.
<t&=p]ASqf,h,7?D<0G%8=&?C)2_A^AF=MHi$cSB%u)4s(87LQkac]AJ5p.Lc%p'+&!jlbT.]Al
SXjJK(@.kIa]A-2PU)<5_6'C5N_("`Sj8KT-<glFZTYY+`7q&[nd)&,gDB4WhHjUsuei(1dur
[!jSIba<6k<g7kRLPqb8"PA`^:*HGKd0^H7HQCC$I*#t]AB55Bu=lubViH_1>2Qu:(+,21Q>Y
@o8.JFIXS2+Z#p04>l0cT4Rr>3bm3\"3Min(%;W'i.d;<*k?2*&n-_?D`ucu)RhL4XUrCO.F
mO8@IWSUS0pDgTs6l!T)Ai:M#$Q]A8;.\ib]Asb<6>Yd1goI^+R)>?=/i0'K#'S9JLEWiqpht9
L&"/s3HU"216_F&UA2L87n^g:@Z#OT?]A\#kgZ&#^kp<L.<T8o$n#6U[oZcb=S:O"^#`fD:Ir
!k.'lBO(gFQ[\LK[tD#0ql4Ya5?^`_<K3RSJT'-o6'\q$.((Ebr]A_B(1771!on"ssT!k^1Gt
O:daDFA8nCb%)$DXHWMm(QJTTrO<M4$`A+VSXs\d!B@TGrHuu=eHb(`e>"[aaK2\nY9h>E3(
^J'mi1%L?kP%K[6Kq,bV(!1;@K4&i4#aJ,<6JAVj^9$%/$BiiAo.M#2u%03Qr%gM/O8DIZJC
N4Ii(Wh7jN9W]AcTHc.<(%B#<*Ts5$5:=3%8M*^5IWmcBl]A8+485##CHo)u6C,e`LS7;V*WRi
!j(M<[<r?4nNrT7,JXbIs%3T6b`4!:VMG8,4hu=&QeZ>e`>lh$C-iojTlet:^E.Gp_pq`SC&
AqI<RG0Y>-Z8lG(?INe_m@Kq)Y=PHR/q>ZqNQUDA<k+0OQE]AgB&'cp$q9E/BZQFJMYTs*]AY]A
fE5[(.3WR*j";?2d=;*B(CkmZ;JF6Wrf*P4Dtm`0cS_]A3d(J.^A]Aa[Ks.0f$M'Q>_`77ID!g
/dh(cF##"PO?D*+EUPQ!@Y.Jd07n\64Al=8pn1:6_h0;p)mbT78LC*4W-_+$,?iPU7s,_`m5
8A,2Xihm-\ldJXo"Mo`XZ`j#Y%BhBTUQ?2_+XLfk'/&_C-QkeVpPPdqZe!!?"XT*UIebNahR
Wt>8_H%ddcu=gC%_ip3.j!:J$]A"c+)#s)>e[T>+1>)?>its6&nd9WKK&ldLpe9n8Vk?i_epH
J5KTr*4)ZSqq,R\FuriD4hGHQDT;1lQa"LEgD56Z`@VjqR7@.f30q/l=1"=2o#\CFD?b(nT+
p#Ua$oc<&gEr'?=Idm9A[O/_7e!m4O4<X4DV)f:1Fu_+&.ipGqM-:d+Kf9^6j7tqT&tU#qDc
Fd?:"J".j<4K,E)sj:-1N>Ja3Ea<m;)5S&4WXj:-*2nnRGL`)^)NI(8D73!j,hLF8&*a>&Wi
"AL0X+#18SF"`1hc"Mq7?"@$J50'7jU$S@EB-)f^;$\u&cd>n4`>Ht[5!oX>D[HhUq'[BOH&
oC?:C7U[UZEe>:%OY>ccR"A@kOCUN&aURbVPV@t_4%IBN2HcRGa0Z?76Mp:ro?'kcH#`*O7*
K6LErG<M7Cj6C0)st[;lcS+BIs_^6sQa$mZ(2k?d:[_3;5p4aBM>_;RI]An2-rR;XAK:YB-/%
qHjP=GTS_JL@=pcV=)C/DNh20m^:nl8B`X@?`$td)[:bOGhR?tV)Be2B^PS]Af3+/FUSDBCJ&
Cn%-iLnZWMD\mkIA=.9[Ug<6c)EX3h`j.07aeaO5d")C'#_BRKb2]A]AJj,AmH_`ld;Ne%-e83
k30dC)5^iW+mXEN:(3gg@=WWn?a0K%`iWD?2..2O-[OgrIML81:N.;=`,(CJ3,W8SBq5eGc*
C(F_hD:j;htm3)kZJ@KhnACQ`gI3-.Rl/P/#944]A-jQ%iV-VfZ'aEG;N/>Q1e\(a!m@PE]A]A-
L(e:1iu$PgR%V2btDqQ*q/1-4%-k<_f>RkAi@b2$bh?&1=;A=@]A-&:DNEiA@Hd=$DZ-F5Gos
2XR&!hc:Er?KR0lBSE44]AT%Q'+[UVY\aZ>V;9b_s!n$"h24t=i+7V&Q`i$qWkm%H!=,e),DH
&t.^uKq]AXUCH'&IW4fB49GEd>+FFjT:Q,N;p1Lg?Yu7D^o`^REJN?235!#ns;*A(,Q=Wje[h
+_oHaIXe%Pr*_eC-j*++^3W6O<2$WCT=#7H$XR=t9:L5GkaEnc@kh!Lq"1>:&_7/[GfW_YHW
gV?OjYcR`'p:AL]A..orPSedi)dII@I)YZig>=b1KW#H/<aWRr\4M_-dm>t9F!I(\em(CeUsY
HiolhDZ_nWi$>'&&IaK$]AulZqVQDsq)Efsqe;)5B$TZ7F3L?-)>?"OOX'5bQf-blMdfWT9(L
<>/snW`+%/&m."0h.Qr>-5(+jIbk]Akl@^6*dsEY!Q-1+#23%Q),3JqAbRKu0oEb7fWEWY<58
8$tMO#&9N0Jm8Z3S.8pq%cf!KgC@#ee?5AOg#f!Vq7u!6+RAd6TuUr-qE-(LM*4(\*6sP^Q@
_+jD:8pZ3\TDSl=+6a\rNrRVdr^U=djM%4.K\+\I89';NQ)GBB2;u;W</h-4H9YV+)Ijt=A6
0;]AmaV;G=ii\-'6^VAVQ#tkZEH+RTQgc6eGJ\eFbcGl84Eg]AhYT:>+DUG0-@^+\a:&F4GL&Z
70`+sC]Acke>q?/;M"809(+<IpGa44D1;0:COJ.h3p;a:'lS^><V\_9AL]A'B)i>QA=u`b;2&S
/,jRMs$$~
]]></IM>
</FineImage>
</Background>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="positionlevel"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName="新特性"/>
<isCustomFillStyle isCustomFillStyle="false"/>
<ColorList>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-14732976"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr rotation="-37" alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-14732976" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="10" filledWithImage="true" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[岗位层级]]></Name>
</TableData>
<CategoryName value="岗位层级"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="656" y="36" width="295" height="422"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart11"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="5"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="linear" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="positionlevel"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10234204"/>
<OColor colvalue="-11184811"/>
<OColor colvalue="-4363512"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-1052689" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-9997189"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[岗位层级]]></Name>
</TableData>
<CategoryName value="岗位层级"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="890" y="389" width="310" height="329"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart8"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart8"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按职业资格等级统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[职业资格]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="3" visible="false"/>
<FRFont name="微软雅黑" style="0" size="80" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="vocational"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16715266"/>
<OColor colvalue="-14586881"/>
<OColor colvalue="-11927297"/>
<OColor colvalue="-16730625"/>
<OColor colvalue="-13963597"/>
<OColor colvalue="-16723559"/>
<OColor colvalue="-274674"/>
<OColor colvalue="-292082"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="75.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="职业资格等级" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[职业资格等级]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="331" y="36" width="409" height="95"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart8"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按职业资格等级统计\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="3" visible="true"/>
<FRFont name="Microsoft YaHei" style="0" size="80" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="vocational"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10764311"/>
<OColor colvalue="-10233949"/>
<OColor colvalue="-79765"/>
<OColor colvalue="-487545"/>
<OColor colvalue="-6887937"/>
<OColor colvalue="-10764311"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="0.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="职业资格等级" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[职业资格等级]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="774" y="523" width="116" height="116"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart6"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart6"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按职能统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m?['BdK^K,D-\\EX]A;]A=VQ6V2XeF#K_[ESE;_qY3Lk/sBFR%4u6+h`_[@U8@EB]AYVOtP!1'A
*p$;0:'?-427jjorCDC'm1D5AkKZoiLKKYKhDWk;hGkSF_/M$;R3#g0h8C`lcG=6h#rmB.Qn
fA[Pu]Am2AVu5X1cJAS%&II/N<O9Y,6uo_PZr-c,`Cnk:l%R!%ehA34p4*VP$\\4b]AmDf!>*^
l=;g$gaa[heq.OB'X?t@e6_eYOuP:G^\#IAS&qt!rjU+kOJ,T)_dk1(f0fe8kJc9VUopGcJc
qNj6"a+@$YID3*PR4G<Ro1!a$3A73I>5YoMTTP6&a.'D>]AFfO#c&bYA&:>!SgMs"5I>_&HqQ
Z0I%U+SYu-$rsXIj@<k):WXS"/Uj^T,)DJ;K106=2GMd@A"(!?X=*lQ-X4YciFFHA,b3qZ<P
lk<,iRtK#__!bQT3c8r`fM:Nk`)<9W#1e_"6T5?h^jV#L""uD]AgJ`]A0=iFK`mi7I1VO(h6&i
*D0k5W;F<sn.4CNd?;D,M5D$m1Ad45jVb(k9_0&VFfE=$!Cm*g\39f@]A`B8]A[5XZ7P>?EO)A
3nW5$i3cV!<b!"kV5@DT8#Ba1HNm1FXM%9.d\M">;ET7HhZk!rel'J$rlD[SB0\LY.D,B;nA
;Ef8s@:O;AXUd_bhSq=?jsDaltl*X(PfA]AP(0PQcTD]A>SdbX>Sq&@G#`tJ+^=[YL!S^KkOJ0
?$eCFGP9le[E?$I7B"]AV9/Y7#%%EH<j"b*K(W@/BbQY.KgA#f]A'8`<,G#72=BY-K0Y"fV6OF
,]As#SnlsGh\ssYL>KS[Ap<Yb0b@*RTW`9L.dAMR6o4<(L=$+:9)+Rf5-]Ab'>Y)EDR)L*J"*:
=J#%u>0W4&:"rZ2-L_)5"097jNSMZ$HGKk#2r.=a>1;go^8"hUgB&uJgX5#7M!mH(HQk1o"X
$8@fa6lOI+X04!?egur1>@'/'A+\4@_Z@(;LLX?i;RS;%k?j;TIW9B2i-#7U)BI0%!muY$rH
#?(&#sq:`/]A[o7ck6*E:g;8q48A]A,e3^V<J,"L,V-oo_YZaKdYI4EF\U\K%j0i'J_"9./*+H
kjuoaR[N+j5f0)dL?I2(3poPT[U/=F^Wiu'rVguOk/mP_?*rUu6Nh%_7U;09HLI4#_G?,11H
ItKU:+]A=)p?(^o<Gu=rWP)HIN5?L2Nk3k_Z*C-or5uBZ"m`k645:j0S5K@X'iEC`#c_)DWd%
BkpMr'kXI#L1F@)2N()0V/frF>@VC"n;r<d'h%Sppn;nH$$-LNTF!(gDVE:)O$K.$4B]A^qFX
GHr_?:**4h*44KA%G?+S>I_$WO`Jq&:+\t,(.I3cu"c3Xj7=3PjB3b_NSuLDtl#S=c6[<(Z4
]A&og.4IkDM8\#d.CdV%^@tQp>*3d3Wqio*]Al9h!g_5LI07q@N$+9TO,tu*8>n6dnG&+EQ$d`
l;D_aErgA#fC4F&;<$*CX\/TBc[1UTXh2OTm$gj"PI!`5KGDD/lnt>3%1KH>>)/re.U.n;2.
!Ajb[^*M&IFk<qcbsl3\\a\6h0\WYZ'"C?A3mRY&V19!$<k`,t<Z^&tf,LV8CgIe\Cnn_;S:
Z?LZrh%N_UkURH/f``^Te.1ge$C.XcXMq>)nre]ATl%e?KBT?Fk5[`q!AEQQsHU\1XlT9&?kR
(r3ePTqf(CHoTFn,rRgs48rFbStg+.W7=[\,/BlpVqGEI.r_KK9<o_gCGu!7usZ%a6`0]A)g8
l9.<^O*aLV+OhU5BP!WQhE%sN.._6"9p:*]Ak^X4EN]A;b>nrA+XkSSc5228Z[7&7W#BXD,prY
rcuF<?IBl2g,*;P@(pCd;sH"hLGV$F4&*Dh7W>5C3J9_OAo5V9/\Mh@g`C1\@T\^=0`&86_b
_@''P]A5Rq9\\%SX(]AcQX*`XF',EZ<-T,SHSKR^M<KF[eK!_=W*l:WZ&6Sb)E6K*br-MCR@IP
)/$C<q/Gb>]AC!)9$SA-BX%j4`LOYIFfcSS!BGFMZ,njEH>+DDJ3CMFS(E9]AFSB_3tX!<~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m?['BdK^K,D-\\EX]A;]A=VQ6V2XeF#K_[ESE;_qY3Lk/sBFR%4u6+h`_[@U8@EB]AYVOtP!1'A
*p$;0:'?-427jjorCDC'm1D5AkKZoiLKKYKhDWk;hGkSF_/M$;R3#g0h8C`lcG=6h#rmB.Qn
fA[Pu]Am2AVu5X1cJAS%&II/N<O9Y,6uo_PZr-c,`Cnk:l%R!%ehA34p4*VP$\\4b]AmDf!>*^
l=;g$gaa[heq.OB'X?t@e6_eYOuP:G^\#IAS&qt!rjU+kOJ,T)_dk1(f0fe8kJc9VUopGcJc
qNj6"a+@$YID3*PR4G<Ro1!a$3A73I>5YoMTTP6&a.'D>]AFfO#c&bYA&:>!SgMs"5I>_&HqQ
Z0I%U+SYu-$rsXIj@<k):WXS"/Uj^T,)DJ;K106=2GMd@A"(!?X=*lQ-X4YciFFHA,b3qZ<P
lk<,iRtK#__!bQT3c8r`fM:Nk`)<9W#1e_"6T5?h^jV#L""uD]AgJ`]A0=iFK`mi7I1VO(h6&i
*D0k5W;F<sn.4CNd?;D,M5D$m1Ad45jVb(k9_0&VFfE=$!Cm*g\39f@]A`B8]A[5XZ7P>?EO)A
3nW5$i3cV!<b!"kV5@DT8#Ba1HNm1FXM%9.d\M">;ET7HhZk!rel'J$rlD[SB0\LY.D,B;nA
;Ef8s@:O;AXUd_bhSq=?jsDaltl*X(PfA]AP(0PQcTD]A>SdbX>Sq&@G#`tJ+^=[YL!S^KkOJ0
?$eCFGP9le[E?$I7B"]AV9/Y7#%%EH<j"b*K(W@/BbQY.KgA#f]A'8`<,G#72=BY-K0Y"fV6OF
,]As#SnlsGh\ssYL>KS[Ap<Yb0b@*RTW`9L.dAMR6o4<(L=$+:9)+Rf5-]Ab'>Y)EDR)L*J"*:
=J#%u>0W4&:"rZ2-L_)5"097jNSMZ$HGKk#2r.=a>1;go^8"hUgB&uJgX5#7M!mH(HQk1o"X
$8@fa6lOI+X04!?egur1>@'/'A+\4@_Z@(;LLX?i;RS;%k?j;TIW9B2i-#7U)BI0%!muY$rH
#?(&#sq:`/]A[o7ck6*E:g;8q48A]A,e3^V<J,"L,V-oo_YZaKdYI4EF\U\K%j0i'J_"9./*+H
kjuoaR[N+j5f0)dL?I2(3poPT[U/=F^Wiu'rVguOk/mP_?*rUu6Nh%_7U;09HLI4#_G?,11H
ItKU:+]A=)p?(^o<Gu=rWP)HIN5?L2Nk3k_Z*C-or5uBZ"m`k645:j0S5K@X'iEC`#c_)DWd%
BkpMr'kXI#L1F@)2N()0V/frF>@VC"n;r<d'h%Sppn;nH$$-LNTF!(gDVE:)O$K.$4B]A^qFX
GHr_?:**4h*44KA%G?+S>I_$WO`Jq&:+\t,(.I3cu"c3Xj7=3PjB3b_NSuLDtl#S=c6[<(Z4
]A&og.4IkDM8\#d.CdV%^@tQp>*3d3Wqio*]Al9h!g_5LI07q@N$+9TO,tu*8>n6dnG&+EQ$d`
l;D_aErgA#fC4F&;<$*CX\/TBc[1UTXh2OTm$gj"PI!`5KGDD/lnt>3%1KH>>)/re.U.n;2.
!Ajb[^*M&IFk<qcbsl3\\a\6h0\WYZ'"C?A3mRY&V19!$<k`,t<Z^&tf,LV8CgIe\Cnn_;S:
Z?LZrh%N_UkURH/f``^Te.1ge$C.XcXMq>)nre]ATl%e?KBT?Fk5[`q!AEQQsHU\1XlT9&?kR
(r3ePTqf(CHoTFn,rRgs48rFbStg+.W7=[\,/BlpVqGEI.r_KK9<o_gCGu!7usZ%a6`0]A)g8
l9.<^O*aLV+OhU5BP!WQhE%sN.._6"9p:*]Ak^X4EN]A;b>nrA+XkSSc5228Z[7&7W#BXD,prY
rcuF<?IBl2g,*;P@(pCd;sH"hLGV$F4&*Dh7W>5C3J9_OAo5V9/\Mh@g`C1\@T\^=0`&86_b
_@''P]A5Rq9\\%SX(]AcQX*`XF',EZ<-T,SHSKR^M<KF[eK!_=W*l:WZ&6Sb)E6K*br-MCR@IP
)/$C<q/Gb>]AC!)9$SA-BX%j4`LOYIFfcSS!BGFMZ,njEH>+DDJ3CMFS(E9]AFSB_3tX!<~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[按职能统计]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="SimSun" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrSeriesImageBackground">
<AttrBackground>
<Background name="ImageBackground" layout="0">
<FineImage fm="png">
<IM>
<![CDATA['j#ZCe,LM6'K2A"XE'R]AfB,i=b9Sh'S<(3=V.>go4!N:R:b&no<3D;l+#5SFQB"P^%HT2KjX
LtDhgV1^hN-"CnTfN=n+Z/@I(l2ufB\`dUQ#CU%OrSD+CTWj[#9Hb+H$3%,UUB8WJsD$#m_p
igRbiqM^c17..6A1DJe"'Qg*\rMI4)A[k5[&Gl@N(R/5ZF3D+]A<+bJ!@MNXq@Q'XA0EsBeR0
Tq2!8V8;kpD]A87Fq(!?h`&=IaJ;S^El6*lXk(ODCE!okc\LCPo]AH2$MtPmAr_n<O-Z>6(opP
HJ1TTbu%c_u/6fWOJ,>V.f8HA.XB);m49hi@>pjbI"\+uB55Oh.Q+n1(_fj<2;KZ?MC0/)+#
,.7LYm3Ac$PF`*(hc:U6Qtg4;iu_?K6+aG5Hg>FSYNFD25\1qhnG:TP^^J?ZkaqFqCA1q#ng
N;?X1qlF((r`QX?R"-nX>c!Mj,CoSW6hmF#mq$p,A]A8O+gQ[:.\=>SHnI*ptEKjCKEH=7(Aj
e]A#_)(d,rWd'369+i7r.X8L;XAk3`QCdV\esVV]A.=Y5DaXpsZh:`VUi"&H?9,oOujCo'U)2n
S"3R2dRE^K!.V.defS]A.I6.IkA2of4:/\QgS9t=5$2#OHYIZpdWG*WkEsur\_56$(qO'aSpJ
<3Yi:e)mT':J)7.M8PkKTA6W"mc%e,O.g5D3)ri_I5br]A/X;r;O]ApMVeHg^CMSL0_FJ:HGmE
oC<.@U3:Xgs)PR_\R#:u$-\P#$^0&oZH\TeM\2O>>26hd00f!pObrs^rRluCn+"M+RCGhJ`U
UOS1CIX+riV%LDk3i^o^793?.)K+r>/MHSDrdSj,)@,'+)\L2t,IMm'TQWn2=B7GiVeuQ2c=
%UsY8m(/6CgAs2K+BYS2W84^5CjW![_Vg7aXkVnrM@[$ggWEYbuiKY8]AOkMQ6O"t=BSJ,<t3
_/JNe!erCWmld#-gJ.u),XU199NP<WM!mTLIrq\9H2e%%Vku)EI)YOc*YG/*cFEJTN^.u08(
780tbDCM6KLHja"%!%Ln3lEbrV/-KciW'Hs'd[+FX;K5O>O[mb6`oGX.^bl"7/$YC-H8VSAs
GV)m>"D"g)58FS+7"opj:^g!%<XGUE<7V?F`l2U).(Ts<jotoqDLNlh_ULDYHQ)T?"=72&b*
d)+[-n21R:bBCPockl>3jN?Ma+9[,q\!='L7OV8R^]AaYjtT<)=s_C]ABDDad6BNLPA3GBnS<Z
]A\HEbXDD/eXMcO*r`:]A:F[$/#W'%d-`;#6A;iZKl4rU(./pLHhBH84eO+l7o@\3L8g29f@PX
\FAlq(PMsFQgf0_LIX1fF!bVn#JB_1]Aob]Ao9YYc9./Wc_Wt2Me5&=j4rit=9NiO`/`t1_&?g
CtgB$3l-3]A6.9B&N/ioK0j&84r1KCSDD19\e+#&KpE".Flm[66MOIb@l46I`o;_<V,YZNS:!
$t\b=R7S\@+$h#$Xs1;fbF`2]A.'^reEJ*eLd+.*,kR[fu3GFUtP1&*=["Ps7aZO3-/KreXU.
HPg/\Gap"YMVQE7q!T"5<qom+eh,)DBYq%4On6Y5r)SJ5S,R!kS<<DKeX6XChjpn*\j'@PH@
gTU*--X"S9I7s;Wo4\c&`>0c>`FpiCVUD8A)6=;,faMd'h[(Bg6h?lOrS(/mmTR@nW6&EIq)
)>0"mMTGthkWA6F7HWK1U'ABOuD]AgJY;pe:%3K%#CrSg?j"K-<A"M`APf_*1,TBn0MNEZ<)'
b;i2'Nd"LRU^5R6]AZ:Bs^h`48<&XHN<Xh>0e\7kb%kfWH\,UF>'SqXFo=h*qge-O.qu^YW/R
N^[+qSNVB-4gBRE1=Q[1DT/\7cpST?a2dMl&S@<?r3T`05sS]A%mGALR(V"VUpZA:o\,[Tc^a
J,ZqG\JW1%[j;Z6IEppq@tRolNWB&'&RD,*7rF'mL[?Me/X?TE:SG.m]A*'HF6)4k.ciBUo,u
\8=U37HuBU+JYS54(1/Umr0A<+,!.8)'dKTC&quHf09TiZdm$r,dQ(:r7#6)2Pcc@(;,J]AqQ
.RAX#9!h$Zp2F(.dS@i5=:tA]A)Y:A%\-:dS,hF!>d"3aXo[dWXlBI"]AO=Q`WZ\MN.DV-@bGL
"4e<>44&.1oPXeCZKro2Vejjb(QT]A?!^idhTM:!IQA%,RF-0"<kKO[-I&<%F]A+B(:g%_dcU=
RJ]AFC>!7Rjp;$o--g(Y)J2RJ([dV3Re083Wgr2'-YIZ=S`da&mi_i<PmmN$]AS<7ij%-/4-?W
a#DiU;QP;-+m$3<#'.>Z`_YR&W4V;c4oa<T3$ikn_#lLa9.sJgPe@lkD$XbR8i1pOhdoNAVG
-KVX>JZD:6HNlE&Xa,2WMQ$aSS*-AJhFakk.Sdf<=[_s$I(M'.dhG\m4l:g+=$;n/[/KdV#c
i*JJ]Ac=ghLAgmi(U"G1Fk!'`!lE1gL_o%q5!rGuaQi$r.pQjSoM+n'iO0'Lr)fX1^!*?!CGo
$jL6m-R]ALd*E+@:o'!bn"a0N.Ic@BEk@j[:AN7eJ2t;HM_Udk@kTBT1U<64]A`b\BYM)]AX(>_
X\YMq0IG%jZ).f9R$q@Fo'."9>">/:1aC+*q,SYk*<S.-U_uY);cf\`@/cNPh[>-L'?,_Tod
O7o6U4ED^Z3Ch8dZ&&G,P:#KE'5oY#kjbp);s%8p2Rr?A8a;H^imibI(%*h%7K[@NNA_%##6
4!uS?d=Ddt>]ASsUl2CljnW*U3]A7=:=O7p6t5$^-SUDEhdm"A&W!G:.<4:7XU'/tbU<YIQDf=
GaCqj<[6(b1!<SW$"Y/pkN*eTX=Pp!7qtW'i/hg[E5EK)AE<Vf<Ltl-O*,o&Ld[T-,i)Pm_Z
EaV_$8!7fnYN6h<J,2bC=,J1U"W6s6k#qeknJ@b+I\3/ogbJ:NX]A@"NnAc_.L'LF'E*1*_WA
1.ML7>k,:[K0)G[BbVcP"ch*/<KonN:\JFu;+"^BN4r&B')/`]AgQ[6^.KQ,B+>]AK%S,^l6#l
\eBU3`TRlD!OZV2<f>i':H#$97arNj(U.+XocAY^EDDnu&&EDLa-m?Es4USMZtCO!?446Jk-
p9j%Fc`79<Jb-j[32PV3&.\Vt`3Cc7^\ku7Nb\l(&)-oVM*bd0PL7fJ6@Gua16aVajbe"LM)
5;Kgnl3JCU!hF.+C7;<-?aCWj&)d\T-:-[8[_?P?;]A=Q37J`IFpJ6i-4dU;/1?#B1Gm]AJi5T
!W6SGss<OOJr"i\VG&`Vmcf5oMY$5$m9Rb`AeWZrJ65?;mW+rL7On7Va4J:]ARTHep^=k>R55
oos0':,bN;X?(_=icg(He:(=@RHKmY>9p^8Ag^`rg9*U_L:qmF:Kaf:7-s`mXa_m#2m3Zr]AY
=YgXJE#_*5k(ah(=J*6TU+HJercS5W=b7@#WM4+g0OTeGI4>Ppa!=?8js+87nfs"u,]A5g=eK
`'@]Af#kX+bK9m9CW)1n1:U$`Vhd4Z$LUUl?'^;RkDqV]AS1Kq_fe6)$pPdNSI"D6R`./DOOrE
hP^Eg`GIKVF5P2U&dB5$9jcl[]AY7Uf":e.4Z%37<[$HA2]AJk`f#<D]A62I4N-9V[]A4=LKGn8H
ZjM)7JV*Hl!cq'O`3WOnm]AbcB>UE_B1(#R-pCY.q7_R^l=AOJ8(^a$>1%V*PB?GY+#+@FF@]A
_jE[oZ9KX#L+:=jWd^>Pc`3/B&St1$,(#QQXrbh>XL\lu.:>rYF@]Adbg%68+18AZtc+r.e(L
43=nY6L=\34@YK6?5sLE31==`e<Oi6qTX5G/l_l_EZL]AM%5dYQe=UftIXFfajCX$nnQV*['>
+i7"hh:Q"fFa1??\/)f48gY0uJ<c6bP]AR,9\]A"[1g84/OQSFR%l=Q_$ZS[YDq&^aJO#tW1%M
L)/8bC`!$cW]A82dT&kKYk.s.G=.8S+A54h15k=M9#^m@8/I?3N>*-9"a>L*41_.HptpS2<X/
`Oo:1B5bP8(Be@g!T(k&K-0+PYrm_[Wh/E)Klf4m<CTa!h?2p\)P;&!8QGn4c"q"KJJe6YJh
]AVB(.H)d4'6CItZoG><$aO6NCWD@Z++ZL#8p#s5Z^`sIJkPrH4!N#c+9^B!n'QB&=Y5N<r%n
DIkWl!l5*X>4hH)ADLE:LRPgemEK&djY*A=5H60sY>$H0AP`o]A'<pj6Wg+2btTC[5Fd:kd_?
"Ucn?I-&^Ji,6r_T0-[Cm%W<=p==<^XULXX2-`@\6SXq5%$?s[\56!L5b28&++)4>$*c*E<c
,?jZ![1^/?ckqbE`-<$6FmuVeA.@"**6j78u<S10!#PjCOH-c>fmC&JnC]A2,B$i76J.G<+N9
[d9lfdU.3[cG97%2sgcSu[Un!BSpFEf'!C^_RYF>.)h!"rL(+`ioriCCe:0sp@Q\d"k)l*,M
+U2-__Xsj6[#c_'LmfT0$]A)*h87H+VTk+r[XXs\#EH,[JnRr3A,=:co2\#!Q`3ln<14PC[W2
hM!A/&t5NK>*e\!->-Bq<%I62O+%C+]A:Al,"Wl)nla6aU(sR#;,kD3r>!$b'Z3&Z%q`Vn*[:
L3Ok%D&6%Tj9LP\EnZB-jbJb;u+E[h2=hRbBXY8'";GQsc?4XkXAR]AYTI0NSk@&[2S]A-NO)6
f7YhS&EtaI.WGq>JLE!>.`,K7''e=&31X3CO5/pA`>SZ?gc%A%ZliRhe@Zok,\IChhPV]AY%T
1JJbWiDN?XoP%)%;52!TLR&inI4']Ac9T.$Y]AMN2PQM%ti:?VNf0CKfF;M5O9A/78";D4jB7l
<.H&mY$%.g)YI40&eML62bK!mE()hj%uOG:Kt=I5`W]Ak$c,nM!UG!XZ[h,\gHQ")[GGaZVpm
(^q@!d>M)#r&a)'<GUK.[C+B:HU(Sg=&W&Qc;UQ0ZOUca)=:.Kq@jbu'4O@oYr.Qb$0qSsH;
2mp)?HIH+[WW\b#pF_iTJ8@a.r$u^k]A52P._gIo%X=*Yi(&)OX<*ffWORZJbU4R$'qSc*pXc
AIQEf+mV]AkOocb$p2-;q3E^04>1\:9pRFWS#SlR1d*L5^A>Kq<AE]AsC##TsaP.4RVNf/#jt1
*P!&Zl"YWS0\WdM=g"aK:`MS-neXaGPt#JEL%p:ZXDKm!"[fn$#b8678NH-ue_Ma309Ge$sG
e4cn1$,l"]A(!4;8JN;m>/hjs?XmnS/CYR*7)**QUeR5J&DeX17cXd.2o=`(L?gZ\]A!YQaHB&
lc<;"+?t=9%OkULjh`GQsjJn>#*<PS.,Oi2&K<A6!Xfo-V)U&;2i!G.]AM7,<6b0kU9Lu4$RO
`)U4Q<7r5)p.-nZKklZKrk6G1er?ZKDf_c6SapG1a`Ec$LIFnW/%el<ajd.ht^mGA<6K3?dh
)rEBT1prsYSY^fX*+uhOFRr/F?W)TRp;*ieZ2GhaJCqcdA>O2I9>TFdeJ(khT[P^`VPUNf2L
rf;AG"9h[]AsB7NXZrd:6q'#30R-B:&7A)A9i/S0^..OCh9CSpq&ms)^Zo8H%EJZIDBB93j`_
BH=/##dPrEDV0D15[!BGQ]ANCgY%OE+`k^Q=Ailk!W@Jo./2$1^lcQfd3t/_7)qbCO^*5W<6>
u3N^S0ZT02FGsZ``AND$&(DoHEr1)=;(5VFW:2fI"j&VfP<.*cG6EEHiNo2U27CAG>R`btoj
F.3C>gDp+!n4VJBg>V\aPV$jS$LrMpKMoN6S./A3KIKf+ujO-0->AeEA.GWJb&T`inhl8NnS
bX6Z(io#QEB.]As6)k[)DcY=27(Kt8h,IES@[&abof!(;q3cdYl%(EW^g2I=D_9jCa4ZX+5+0
Y-_V9gV?)MIs+u7",mU"A2JDZst'%O9T[[ZF@i>qoQp`;+7jUtKF.tc)'1a5A7ik(G"$+mFV
[fsppG>dlB%+WM[r#<8#*&$$ZZ&R$k?HB^m#;0J,4,`*@^&%b-^jcIV@cAg_TU:"1WGlQ[S^
D8j"g_aa*eK\n4i-oPJddGOAA5MhoC/!_#h$YriOZ@VHi4O"b/ni,:IHdi?hJ!0@rOMUB-m,
tH-8I^/ie7jN!&.ff>6_N)PSKo[Bfq!41(6L0n&?>\:H1,8QW!F/2b=ac[l5,=t<U(GB'CT0
;`>';+umEYZGsL0#?mJF/fLh"?$o$CImu.RQB9%c!F$%@Z1QPU%Xta\PfF8Prrnp8*E8<W.(
'2E?DVLgP:Kn+#0/bc1%'Ul]A7F^;#W)tOJ&'b$P_3(XsRh+BZ_o4I'@WSEJSO$m]Ah</?gaF1
@ChBP0G`uQ7='hDBs@_+Qo(8Md9!N8qnDJKJDHWkY,LYgSpPT9V2_rC*Yi&4R;2?+3E;`@>D
DnmBQ\.N^F.st"d%u0&Y#feAYap'Y*-5_]A7@@rj9#`4M*<I[=B3a/Xcp-?mJ*e!qt%!8Y3%D
PN_aJ87[0C;:%SX2m%OIX2L`.]Ah[WMX[Z3<ZQrc:7h%`K#A+o$J8*hAhll:BToY%4,:UA/,S
$E74qGb&XZ+E>4R`<*Ql*_glnGQEa]Ao+6@%'(\tnOX=(/h0M2*BHVGXo^=bCCE9Wk8GeYlku
,d&r>[kDH)<+H?aq\<.4?d@aB8r(S$a+hC#YA2so?u&J99e&tb'r;Gk1l%!)nNoF(X*&rl?L
S?rZlG/eiQ]A,K]A]A@787L;<1T2..l2SU'akKfNd[LhW"?VF0n)O3'W.W1X^<W\jL6Cdjf+l$#
".4m"-J%9;X'^q4@R[C'-aX*e:k17W,fC5$bAWC`jH\]AN2g/H.5"&IZ#<HTq?h%#AT(DAT.u
",=;?>?2iJ:B`<iBNH;2PqQjBK_]A0C"&]AB.*([:o(#9;)-,K9V87>R6]Am@sNl^38Y;E8QT$=
6qCp`%J%Y%eiPW\iMg]A9=_BqRaMW=7mhZc2_B:9+(?:-oR2p$Wp_<JZ<0B7>$WF44A6Z!+Cd
`(Ck=:TBPH7n45-m9^/h=9mfdM4*dqF1G[H5GocB):gp0"uM:CSG#)gL3[RG%#I8AaEj6=^]A
7pDR3@D`m&V3*fDZj>uu(>]A?,_f@tomMm>3#/Sq"SI&"8I;]AmorXqi=!$'Q+"T.S+%,id@e1
\'=o0dNp`5VUHZ!"Y63h_:O8lWKD#gfnto>tY7!"P#PYK9GEh$htMH.VFaA$8(ppu"^D>(%(
62$t7BkMA8Pa9+4DIm*\&Y<%$l_%TJYl<6/FSK:Q3hjL5de37YA>O8ol35t<6pWHA&U-h5ND
u9Ng><JcDn!DWZ&'3_fqT,Q#oP-aY\UYD3Hj_?Y3g?'VTfNab,-$@9QI]A?_lDm\eCrrX!;fN
^E"O7Y8IH:/YbjU'uVgC&-W'b:FM\2oqe0^s5bft(["Rk[,05/@odhAl/6j\^pT;jQ[TsYuE
NA`!lWkXo-PaR\N+ut^?IA1@;I16!o6ER&>2l0Zn*6%[.,LQelo1IY'hG)nnj>^NX]Ab!<4hW
6;je<SL!U*FIn*@\NYF?8d'Tkto!A1.X9%Bc<]A*'9Vbs,k<bi8EW_+OI#F#G$9HDhF=9V3\!
Ve2:)m'5ID]A0!?1OU26;`rm3lo`2\/!(ZtSuWcdn.Hii;dr3)LqHcTHQi5rtq'$^36*5H:O+
ND;TH<h#6!;9a#IBF=8Aot%9SR@t@r<ZAYC?(5!EPp0D7_d&K/N09A0U8XWGN<.@^dlRuQ64
RmQ!B$aB1\-P</0LDHZ5"-JO>G//$BOq#U./CiDn13M&RR?PSN,TEp1FlIU(RG';@>D+M4,l
cGhmL-n$DP3%>0'P6M[uqoR-@$=/"'5ACp/PITKJ;@Ng/lphoq9DZd7]A+/E1=;U>B3r=\"6B
<!,>4UhOn=NpT$FFH'!`)8bBX>)Q7QStOed#'t^iOlrn%A9s+o8,i,)7R$*%!*e_qTUR&LiS
'+coO5,PKJ+Xt4a[7aX3qHO7]A<afJ8;UTmsu%(lXUFH:m9?qlNC)%J;MT"`&oGoLiq+p312M
=[1b;>L45);^3iC#E.3>q_N;o=R@Q:`TBbeq.BorIU`sP17Hl.\@\WPd%6h_nP\*U/6fDMS(
4^*(g/UXET;m<Fhh:n5*Ns'*_.aQDa<$oaLsks6.c.%5pKTPCMfjK_h]A<G?gs^ha;P9h'7FC
B:hr?l6Dp@%up?l%k4^[B\E=HSTs$Z[Y^^rSVTqss'crDF6=^9j.e;Wa`ZiVf6mh:4";(QrJ
5p@k2\+R9#'I498D>[+s'(r&`,\t-TD-TE2D8igU]A'"EmgO++_o-f0'C5&NG\d-J<f+E3IQl
]A4%\rQQH.%u6H>6KhS_@r;Gm<MbedON7#CT9:g=[9Up."$nRhh3/ZrYIn_01U/<0N?ca5_%&
Nul^Z;7$RU7=\e15bIm)=P7dGH,*m,h$K:8#@b*&]A(jcjML>ALsfS*q4ZY)i`VRN@t.pDi%Q
76DT;Su4*r[(;ATrV#n]An7,p(I0@kMM]AZX0,S2gm\9Zi);Sb5sjFrh*]A=c8u'MDpUZ0P^/r*
hdu%Ei<00BoH37*D<:Y42b1r,/1/pOcpPkW+J9T`.DNg&ob$mhZX+Z]AA;q"NLX.e#kd5c).r
s<:aM?5\kd&CZ,D^Y[oV8Pma0O4j21t0I5@=*fc5r6R[7cXpmI/"1nX=U%4bPPnK^3Ga9s=j
DaDFM!1UfPT0Uu@,nU6\.E!qPIpK!@ZI,#$(2o4NF8`EB!FAY#:>nSq2ETh']A.AQX$5%81X@
"bZ>+VjdZbS3WM]A[+m`c/#-@mdBE`D\S"oqgc,bkA?$Rmh]A+-2jf]Ak\6u0U[Bu?I/+U1M12j
.rrnV<nb.i_7A^-60q5&(9@@8ipJq[S!T)sJQ,s$WaAFtU*)2k$DR\M'T<?KsVl2qDHm'+Pr
\(rt*0dFsK'jB?[kZ@'4mPPM\4(tcZ'Kr$.[ralL[UJ9Xp.g^EQK2NJ&etAq(cA>e\a.[]AXn
ZNtdqoW5k'lN`UiC0L;`A;<@!>u`8;=b![dfd++)4K5e^?:&hru$-V42)[hJ"Lh:aKM@Slt.
fiHCf3_PcD))/_Zl3G5&`QdTVJ)$\UWXoC:hnCXU3USt$I7p-JuG3S]A8Z)h#5^m9D>"*6K%[
$fg''JV_if`Q5n<Oj10r\,Nsqp*VM*%OL)Z-\snVgjYc`SMn;>14nE7X<VXb$SHO4D$kbCXE
IWORrh)i?WWlh<CR1aXs0lE;(9@m%_\T]A"E"R0t'9nfTr[f6K9BCaT3$gho7IF8OL$3Fe1n]A
1W[ihi7trmmb[i39XP,lL%YE8hq#Y;K#@lpX%&ip3h?4^_E4pgX<tX@3klj!7RKN+QAPO,9M
2'&-UrTufuU;+1f^/nm/`T$Q&rokWc_lapVC!<r?2E%D[OcZ/HgLg/da[a]Anhk9(Jr]A)k1$;
,P^!I=_W0o]A0iEV^,os;95Q-`qbSR:fRk`6R6Qk\!IPX0Y<\eIZ*_>)cOAYSK4l$iI"&U2u5
r(AV-##/IqL6@A92YjJcgA\7$'`<"[!DSS8HLNkn"&PN`PM#r(UZZS;ggfW*]ASN9fTh#L_5P
(H8BC0HG.:m(F\qdnZU8?3M,)0>Q$a$-*E<q+5(TqNQfnnN(#IVK^D;X0NGdhM\J9[fF*3/7
(oBg[;N1XB[m`OUV8#r,IP#c6haKL:,Sk#GRH^6^L?Iq+:+H;(GR"Jf2(oDjrL12m=8`dEg>
%qdF@&FX;"luTClKXbSnF;5jIC((Z\\qD$E[M:IW]Ap`))]A_=PH[;#@a3Q(DlD?,,0LPjErj*
2^-diC#^<$O]A"pJprZF"XGHA64FnMg)F:0*I5JPE!akEM5^I[\Ffbk)A'\a_r7h$0X?_UVk<
)_J[h=o!#1h.H+=Q2bY<b02pQuDn@A%clr/eRtfhp5Y=q"DonCD*\2-p7^NZ'ob&.a$L4Zh@
.o*m8Rb2B!#ZaE"$0!2F:GhtFHZ7RP&#VBsR4R%k'H`f`3kXlU5?E:LYT&e1o?,rf]At^@_q,
oT*_oPA)BgI[.J1<(h[0oDi?J<O^S/AGuYe#/R`u)c\M@)#Yh3E-XhGDP2%`M3%C4Ea:&^R\
<fTnM>ejW'8CP55i!&.s!e;:'\2@@FS3T^;uML\,gU--pIHD-\LOW6sF38HVpj7@B,%DoN(I
WIC%fl0'-9)Y?ruu]AeFh2HL<g\k=a]A9q"==-e+-00SI35K>4[D/S`>+>M,.ECqI&Qgg-jurg
e[IWV-jF33WAchqb&ZiVWFr:C(n+[lgqS<!8Z+&?T6FX@,crH*3'H0nl*<]AKLKaj<g7jlDGO
W-7&6QUG"nT\m\HFOKC7NroLudiaZQ';/=W2W;9XW<U>cE+N^t_F<$ZIQ%Gk8&\H%ugCIMX?
docd9cW#gmZQh*]AVfPo8aurA)bg^Jg8bOkNQ7#I(?Y05X4ntT;[Q2ld^WgkYrUp(bNK6tEMY
O_6XtpY>^W/K@=U_$+c(d'(=$B3Z"Z`a]A42QNFL:qsicUdhY@+uuJ+0!_t55%JtF3['`I9>_
ESn.:#O1damkhS$j;5!J_QW6T)q=98+[P1c8[8E"\m3tCKMU$5MdP[9&'tOVB4n3o3PNPa-^
Qp+Z4"@Rpc<jV_"oU3dhY^8MX2R_ci9,O8m3!81N!#sIdGrA@Wbmq/hEghQq"h*#B:453@7]A
F*f1iu0/%9U;H.d1M%0b^m[L\jPi0/+&\1sU[+M@47SbI=]Ai(7''3KU4=g/1p%P%elDWJQoS
a0eb;-(b(^q<lo>+u>ba`g%jZ/7fX]AG/\qiM;,!tF^T[j@Ca!;]A2cj"/1+p-P<j9@B84:r7+
Od$Mf[Ymqj%Mo3*'Vd69Dk^jTH->E)88/R-9PnQ+s]A[@c)'tF#4gF]A47Nf:eTrOjSDZ]AS_2a
uQLY"RZlOq$MZ*q13[kP.@,?EL;mGfeWd3iX^-S[g%AfqiI8D[Fiu"BER=b@Ap*8j;j#PWnN
p:kr:I\O''&e31aO.7_f@E(m8_q([]AYfZIQY,5T3AD&=meXYuI:^R\4Nd%bF#2C,9i*;r>TO
B1q7Q5@T),8XO_(XV;)8"#*dW0ZJsYrb\]ARok"(_/LIkHZ&4<@@^+`TI#>\<V'&?Hm.U-'ts
S_`H]A>5_5r<7j6KY/t`XJH+8*4.`TEj-0KJ~
]]></IM>
</FineImage>
</Background>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="linear" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="oneposition"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10698774"/>
<OColor colvalue="-11184811"/>
<OColor colvalue="-4363512"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-14732976" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-14732976"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="10" filledWithImage="true" isBar="true"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[一级岗位目录]]></Name>
</TableData>
<CategoryName value="一级岗位目录"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="331" y="36" width="409" height="281"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart6"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按职能统计\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="5"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="linear" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="oneposition"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10698774"/>
<OColor colvalue="-11184811"/>
<OColor colvalue="-4363512"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-1052689" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-9997189"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-9997189"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[一级岗位目录]]></Name>
</TableData>
<CategoryName value="一级岗位目录"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="61" width="310" height="657"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart5"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart5"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按专业类别统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8o3IP'8!\Y@mI_>'"I0ed_@kDm!8YN]AARTC9:`TRX*Z#;7UCp8sTfjJBA>QQ'EpO0<@i[?D
'V26sqU33>GFZV?j']A>p)IO5!1sr"18";ml<PoE6cAiHiJIX^[H6XVtYoM/Uh3K)[HRUnZK(
0gkS/-_M!E&s*bD@fo#*@KKq*1hY$!=84ojnM#,RsecA@^`)T:c[GF4DJ9ndQW5SXm%o+>J'
DC>P"i3Ae]At`^1F,/J8<PBndo#JY$@iaO#7C@R]Aq<Eu;\%T28aA3=YAa[7a]A</3QNDUAbX7S
u+7/2p,9hJ6WrLjnTWVs?%qj::/h85Q.R?_//EWYaqHLd(MTR:#\hNW;qB7&:WdY/QNn0679
YkK&/R;e^uirpNC_SokH/h*ac)`IW@3us)(Crr+YorabnN4kfk/?>9n(5an<gN]A'cKJ_]AIR@
NZ0lT3jG(J,8bY&DaZl##Mu]A04nmpW6+tjiZ97M!3@]AATqb?HQ+[(aQp3n[*%JbZoa]A%Z]Ar)
LmeH9'7La-=)6M67]A3GU-4tJ9)?J>om`'7$_I7]A^-.a=<HdAB!ODfWWtO23:&h)it"er%`mm
/lGbFE;#>H_D8Z,jaZUa%8""BhoR'EY@[W^O2_<9?8e#=c-jO<5>="<.d3&OLB4lgAcH:B,+
(,>Yj\$r?!>^At8+2;D\\M'cVrb"@WCKFGa<=>tnu+(`=kjo*)&^"Bb<ZA@t/cR!^5@E\uPe
IS2O<nk$,hQcIc9EN22+O]AfTRJN^)(TIt)UmML8`I]AYaV=s1Nph8M=aF0H=;(o`BL*\\1OY,
2.le@<&,KjL"fML\kMm[65F%&q.OlHmg6W0Hp_FPph%-dut&qACONr%=uXR6/g)C(?V0njq+
s*R^40["TR`NFJmcHq#pG+-hmA"6lAS.Uq9*Y$4O$:N>0PW9OkSTd.k3i"/N,&eN=:DAK3Ld
eYAY9>XItL@W:^5,3RPS`VWmK'gR<MO*%1>E'!,'[AY$rn($Y<DRZN5AHf8k<lB)XN']A&T]AJ
K=-m]A4Se6doS@HGPlCDkC#ClpA(#V5;mEgpr.9#!t%$H>7tEClH$dm<Zqcb):RBkXdmNJ<Y"
9$-!Es-_<C!4]AN,^eTumAq!ST?UK]A@pU31`'RP)^G2eN]AhB<>d5eeIL.Gk`F.p#eWUYhlp"l
eKoa^Z-s(Lfha<@:-38Oq%?2mF:qPg]ACn3Mgi@r9]AGeQoUh4R2Hrg5@5CoQ5G#d)Vj)\hefV
6"-eP02)'^U<7BO\C`!HM%S*ssO31iO$lK-IY73!lr=05u3>qeXl9g[&`_q"3IYLlXgM$9cY
RV67>sR.VKo+Q+3atCqDQg:k,?)jS^.VU&Dq:KW.K=UqU]AkcK>+ZAXS&@M..s&XcTV7k"b[k
Q!RX]AX]APRVq!=QB_d);kLgc5LPB(KtYal0Y"Q.<"UTAJ'\d<1r`09^-Ok+L1!2Gkt0b<:S>u
NnQ?*aH0-WYsY%MS/ZaO$c#cfZpK;-\t#[r*([`o?/:r+\-'W29Bg2_(g6M$L>dk3^Y_-!ak
PAn]AK!gFj:Nkll,g\bUga-YXc.20\4Gkq&hB@?3(X[I9sNJ9G(Y<0OOMIh&[#%j1tTer9B(5
WNbna-,Gfb9@AZN4W&KHkl6"!m;?ki0QTq1(I+?em;,JN2YNdjl-TXs/<%gp3&iN7OQX'O8%
Aq.K$)Y^Mk+$J+SKT#X'JHRiPNMJIZFeDb&$iL\0g9=6Dj4Q(E<f6do0UG0-7;"?C%$?.C4>
<O4mqXhk9K6.T%RXWV9Y?o'!HT+8aM!gGca?3L`5m.(m0l-KIWj%#<s]A0LSFSB;,fIe]A:,=A
NQ=]Ak)Dli;1L`b/O<&GZS\uheJ@jd^oiUQK@N^%'42c9OH_dpT+HoZbg1'kPdG;$g?SORY;*
*VS;;jD**d'k1du^/BqP?klI`=%Z_bcj`DBCen8T%THc%Z.Bltl1t0^Al>J=;auG6?eX!Bqp
=Y?JlJjZoV^hVD$)g"sg`Wp@BaK73Gui!KnHC*^-!fZ**:bKr`i.lJ4=]A-Z##30flel->Ebq
2LQPOH;^ZnX5qN=j<MOk6?-4'iHb(-VS`Kjj(a_DTZ.m0\[mj8aRgTeDlGC`A+"Xe%_)2:ku
<c%e(MH?mS(o#D6Y+eMG(lK6#g>%HE>&OJ"fd41E5OYu]AO=B-YT]AbXFR3X5_PUk0QKqV/73h
>aB?,\O=N$j'7ILK_hW@j!`=l<N?dQi>U/XW3aEriTqquaX_<53k3cV\3T<U[nd0M^1&)>T<
RP,m4+.pO!&[BB?+M_#Y,W>EP/l3*HV?pE,_`NNSsf'`<8sT/g$Z^r^OjXMaNiJKpk+4p5[k
BHkJPQhE!#5%/e,:UbX.:N\"9q(VYS60:\<oq?1^S?rsMtch9P-<9Xo@bJWcWNQhK%Pg3s9=
<2'-L+KuZZ24;80^:3dN`.M>[,k?h!'u079Sm$'g4"?G7AIJB^A^\bGgKXHo#`Tt9:^,A5&4
9CbHIunDhec"o$g%c'pF%uC>U%NPmb(oFskD'X17fWj*i1Cg'JZf'P@I@od`u]A\n\DD`@#<*
e3U2FrMG]A#14H0ao!!O?:.*(MW7A1G<g'5i"$fjH^a:qT4Le/nd/\b@6*>er1S`pImR,`tO-
+>9D,,;[)dfd!A%E@ImlYI=?(l=H1XFY0VQ6S7(ZKL9:'`YoRjMJBn&%@T[PG0]AK"slhM'$^
k\Z.(e\&Vn+ec#[Fj0lI6Sh'I*J??5*pb0*8+#i8<HOS_]A&r2Jm)7$WgPok$IEHMP(H"t,Z]A
=>X<Q^Oj`.)!\Ig[s07Om&3e!Oo;01B^B(X`\Z`$nG+R^t''-l86u^pXCS>jQN$RCD7LB5'J
'?l>h'O)T'g<pY2ug(-:s_k2NVNBA,&o2M%67i]ArV_(@!Hr16[OI$qEt/MWFI6XI&D-5\,h6
E?h"DYX^\$Zn%soY[1Y8"!2('3=)CH:Fo=(euVDLR?K79,o+O2J\Gq]ACC`fs-_P9hd3@\Do3
:s&$:Wk?YI;A;'eNp"3S+VZ:q/\hG<q$>CBnAK79TA6TZ#$cKr3&/c"^;s4W]AP(YM4<s5o-'
Q8t%N//8+FQJ/DqQU*SD3ES@@N8>eX9:UXp"LG#\E-Osk0iJ8@&AopD3p-,rm*JNfA!c<l,O
Em,QBK30?G]AaC7LS&ioX@MTh$cd!/6O2d-(*dB0Ei10&b3QK!s4Y!;//9q&PE5$sC&%>JQ<H
@X?<2!Ppo.lS2coP7/@.:AGjkUUeQ.-X/^0YbcQAtsB`Vr+CAOj:&-4V=T_PJfbgcT\4t)jB
(r*amoCrb%J"NTiB>iHd.pISRmKc$?iWChJU:&P=`)R0(m<:]A)#@U10(@gEXiCi-&iCi-&iW
#%ES1-HugZE\=8k;&jb9'`Ka$G"o@R&tDRu^sX2QV9Frj0qU302%k"^?7ZCOXPgJdqZ^(kkB
ul=b,hIb?m?Yi"BcHp]Aj*NQq2J>93.M:RdAPPN/e"oS?l*T\1n3bF&9>Vi/1/EW.An]ACm8tp
T`P9b-AfpZ7(N!QQcb=UWuN0j7'c27)QM4H^(r7X2]A1(LVM8rg#?2uP3Idf4KRH/O#l,cNI]A
LADCU;cA0&unL3"$6;Zg5'IXbbB3@l.sZTi2eGlF!RcqSa*h68RMp-Pg.N'N07BQuQS7#O5N
A53I^p6#ba+g0-nV1!C,r7hjV/5H7>VR[jkd;oe5/J'CAVhfZ/H,KDd%[86&VJ,!o$j.fdA/
tG#Q`)\I"Y=Dm:.jXC647h-TNj:DA&J55Xio*q.Kq0X4l$CePPjP0a'%<s&k)QHglD;Mg6>"
R2/MVgq5Et-KFN)^WQM;B#Q1$bi!(Frp)tUm0@JZ-=64G!<K.tQfdu-FF[P;Tg9Nhg^L1jXp
i@'7G'[))OXJP.5B2p7[Y<^\Z+\9-"hA?'!Tb+^b^pDqZYN(pQg?C!TDBW~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8o3IP'8!\Y@mI_>'"I0ed_@kDm!8YN]AARTC9:`TRX*Z#;7UCp8sTfjJBA>QQ'EpO0<@i[?D
'V26sqU33>GFZV?j']A>p)IO5!1sr"18";ml<PoE6cAiHiJIX^[H6XVtYoM/Uh3K)[HRUnZK(
0gkS/-_M!E&s*bD@fo#*@KKq*1hY$!=84ojnM#,RsecA@^`)T:c[GF4DJ9ndQW5SXm%o+>J'
DC>P"i3Ae]At`^1F,/J8<PBndo#JY$@iaO#7C@R]Aq<Eu;\%T28aA3=YAa[7a]A</3QNDUAbX7S
u+7/2p,9hJ6WrLjnTWVs?%qj::/h85Q.R?_//EWYaqHLd(MTR:#\hNW;qB7&:WdY/QNn0679
YkK&/R;e^uirpNC_SokH/h*ac)`IW@3us)(Crr+YorabnN4kfk/?>9n(5an<gN]A'cKJ_]AIR@
NZ0lT3jG(J,8bY&DaZl##Mu]A04nmpW6+tjiZ97M!3@]AATqb?HQ+[(aQp3n[*%JbZoa]A%Z]Ar)
LmeH9'7La-=)6M67]A3GU-4tJ9)?J>om`'7$_I7]A^-.a=<HdAB!ODfWWtO23:&h)it"er%`mm
/lGbFE;#>H_D8Z,jaZUa%8""BhoR'EY@[W^O2_<9?8e#=c-jO<5>="<.d3&OLB4lgAcH:B,+
(,>Yj\$r?!>^At8+2;D\\M'cVrb"@WCKFGa<=>tnu+(`=kjo*)&^"Bb<ZA@t/cR!^5@E\uPe
IS2O<nk$,hQcIc9EN22+O]AfTRJN^)(TIt)UmML8`I]AYaV=s1Nph8M=aF0H=;(o`BL*\\1OY,
2.le@<&,KjL"fML\kMm[65F%&q.OlHmg6W0Hp_FPph%-dut&qACONr%=uXR6/g)C(?V0njq+
s*R^40["TR`NFJmcHq#pG+-hmA"6lAS.Uq9*Y$4O$:N>0PW9OkSTd.k3i"/N,&eN=:DAK3Ld
eYAY9>XItL@W:^5,3RPS`VWmK'gR<MO*%1>E'!,'[AY$rn($Y<DRZN5AHf8k<lB)XN']A&T]AJ
K=-m]A4Se6doS@HGPlCDkC#ClpA(#V5;mEgpr.9#!t%$H>7tEClH$dm<Zqcb):RBkXdmNJ<Y"
9$-!Es-_<C!4]AN,^eTumAq!ST?UK]A@pU31`'RP)^G2eN]AhB<>d5eeIL.Gk`F.p#eWUYhlp"l
eKoa^Z-s(Lfha<@:-38Oq%?2mF:qPg]ACn3Mgi@r9]AGeQoUh4R2Hrg5@5CoQ5G#d)Vj)\hefV
6"-eP02)'^U<7BO\C`!HM%S*ssO31iO$lK-IY73!lr=05u3>qeXl9g[&`_q"3IYLlXgM$9cY
RV67>sR.VKo+Q+3atCqDQg:k,?)jS^.VU&Dq:KW.K=UqU]AkcK>+ZAXS&@M..s&XcTV7k"b[k
Q!RX]AX]APRVq!=QB_d);kLgc5LPB(KtYal0Y"Q.<"UTAJ'\d<1r`09^-Ok+L1!2Gkt0b<:S>u
NnQ?*aH0-WYsY%MS/ZaO$c#cfZpK;-\t#[r*([`o?/:r+\-'W29Bg2_(g6M$L>dk3^Y_-!ak
PAn]AK!gFj:Nkll,g\bUga-YXc.20\4Gkq&hB@?3(X[I9sNJ9G(Y<0OOMIh&[#%j1tTer9B(5
WNbna-,Gfb9@AZN4W&KHkl6"!m;?ki0QTq1(I+?em;,JN2YNdjl-TXs/<%gp3&iN7OQX'O8%
Aq.K$)Y^Mk+$J+SKT#X'JHRiPNMJIZFeDb&$iL\0g9=6Dj4Q(E<f6do0UG0-7;"?C%$?.C4>
<O4mqXhk9K6.T%RXWV9Y?o'!HT+8aM!gGca?3L`5m.(m0l-KIWj%#<s]A0LSFSB;,fIe]A:,=A
NQ=]Ak)Dli;1L`b/O<&GZS\uheJ@jd^oiUQK@N^%'42c9OH_dpT+HoZbg1'kPdG;$g?SORY;*
*VS;;jD**d'k1du^/BqP?klI`=%Z_bcj`DBCen8T%THc%Z.Bltl1t0^Al>J=;auG6?eX!Bqp
=Y?JlJjZoV^hVD$)g"sg`Wp@BaK73Gui!KnHC*^-!fZ**:bKr`i.lJ4=]A-Z##30flel->Ebq
2LQPOH;^ZnX5qN=j<MOk6?-4'iHb(-VS`Kjj(a_DTZ.m0\[mj8aRgTeDlGC`A+"Xe%_)2:ku
<c%e(MH?mS(o#D6Y+eMG(lK6#g>%HE>&OJ"fd41E5OYu]AO=B-YT]AbXFR3X5_PUk0QKqV/73h
>aB?,\O=N$j'7ILK_hW@j!`=l<N?dQi>U/XW3aEriTqquaX_<53k3cV\3T<U[nd0M^1&)>T<
RP,m4+.pO!&[BB?+M_#Y,W>EP/l3*HV?pE,_`NNSsf'`<8sT/g$Z^r^OjXMaNiJKpk+4p5[k
BHkJPQhE!#5%/e,:UbX.:N\"9q(VYS60:\<oq?1^S?rsMtch9P-<9Xo@bJWcWNQhK%Pg3s9=
<2'-L+KuZZ24;80^:3dN`.M>[,k?h!'u079Sm$'g4"?G7AIJB^A^\bGgKXHo#`Tt9:^,A5&4
9CbHIunDhec"o$g%c'pF%uC>U%NPmb(oFskD'X17fWj*i1Cg'JZf'P@I@od`u]A\n\DD`@#<*
e3U2FrMG]A#14H0ao!!O?:.*(MW7A1G<g'5i"$fjH^a:qT4Le/nd/\b@6*>er1S`pImR,`tO-
+>9D,,;[)dfd!A%E@ImlYI=?(l=H1XFY0VQ6S7(ZKL9:'`YoRjMJBn&%@T[PG0]AK"slhM'$^
k\Z.(e\&Vn+ec#[Fj0lI6Sh'I*J??5*pb0*8+#i8<HOS_]A&r2Jm)7$WgPok$IEHMP(H"t,Z]A
=>X<Q^Oj`.)!\Ig[s07Om&3e!Oo;01B^B(X`\Z`$nG+R^t''-l86u^pXCS>jQN$RCD7LB5'J
'?l>h'O)T'g<pY2ug(-:s_k2NVNBA,&o2M%67i]ArV_(@!Hr16[OI$qEt/MWFI6XI&D-5\,h6
E?h"DYX^\$Zn%soY[1Y8"!2('3=)CH:Fo=(euVDLR?K79,o+O2J\Gq]ACC`fs-_P9hd3@\Do3
:s&$:Wk?YI;A;'eNp"3S+VZ:q/\hG<q$>CBnAK79TA6TZ#$cKr3&/c"^;s4W]AP(YM4<s5o-'
Q8t%N//8+FQJ/DqQU*SD3ES@@N8>eX9:UXp"LG#\E-Osk0iJ8@&AopD3p-,rm*JNfA!c<l,O
Em,QBK30?G]AaC7LS&ioX@MTh$cd!/6O2d-(*dB0Ei10&b3QK!s4Y!;//9q&PE5$sC&%>JQ<H
@X?<2!Ppo.lS2coP7/@.:AGjkUUeQ.-X/^0YbcQAtsB`Vr+CAOj:&-4V=T_PJfbgcT\4t)jB
(r*amoCrb%J"NTiB>iHd.pISRmKc$?iWChJU:&P=`)R0(m<:]A)#@U10(@gEXiCi-&iCi-&iW
#%ES1-HugZE\=8k;&jb9'`Ka$G"o@R&tDRu^sX2QV9Frj0qU302%k"^?7ZCOXPgJdqZ^(kkB
ul=b,hIb?m?Yi"BcHp]Aj*NQq2J>93.M:RdAPPN/e"oS?l*T\1n3bF&9>Vi/1/EW.An]ACm8tp
T`P9b-AfpZ7(N!QQcb=UWuN0j7'c27)QM4H^(r7X2]A1(LVM8rg#?2uP3Idf4KRH/O#l,cNI]A
LADCU;cA0&unL3"$6;Zg5'IXbbB3@l.sZTi2eGlF!RcqSa*h68RMp-Pg.N'N07BQuQS7#O5N
A53I^p6#ba+g0-nV1!C,r7hjV/5H7>VR[jkd;oe5/J'CAVhfZ/H,KDd%[86&VJ,!o$j.fdA/
tG#Q`)\I"Y=Dm:.jXC647h-TNj:DA&J55Xio*q.Kq0X4l$CePPjP0a'%<s&k)QHglD;Mg6>"
R2/MVgq5Et-KFN)^WQM;B#Q1$bi!(Frp)tUm0@JZ-=64G!<K.tQfdu-FF[P;Tg9Nhg^L1jXp
i@'7G'[))OXJP.5B2p7[Y<^\Z+\9-"hA?'!Tb+^b^pDqZYN(pQg?C!TDBW~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[按专业类别统计]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrSeriesImageBackground">
<AttrBackground>
<Background name="ImageBackground" layout="0">
<FineImage fm="png">
<IM>
<![CDATA[*ENP(<:Bjq0>eoJ:$>5EQC1%5N)6Asq`<aUA4A"Aar8qW1D0#5/SembRCIo,p6h^)qi"F]AKT
N$`D"XFXs.8i=F3f3Y^#sH$n+/Z6kIKVdD[D-5+FJQa:oeYMSGDsTOh>=,0bN8*UZ(-B5!HA
kX#&n@Oh#a_dF[qKeSh<_PtqN-40-h\qh"L*$aYG"&B`J9@ub]AX::Y,V$X)[sL"\1Hc\.#Ua
pMpL$d2E<10VQKKp9FsJ+FHEqPqlOYC@%S(i[gFY05.o)dE6SF!U&4/q7-U9_RR@JL?m1MrX
sQ82#_1a,hc3hCIVF<qD@OHSVFIZhGqj5'6^oo=qo,JY]A5sEIi;LJJh@/=3:&jj%5$Lhq6b3
rhQQg+)%fA)rl)-8q:#:U8)*Zf5iNZgsk(-p=7C(8_8IbFn5tR/d:#$J)>=L.qT$cc5U=N)u
EZ1:QITUK]AeV<IH6.,3Nk@@5.J2)`&Ut/VRJIo3<3E0ffdT:/fjQ3<\YFe>i38&dAfhs%W%0
-*83OC0sa^h=t/!4Z$(Bedm1cigZH$BU%YnQ%d-SblEg0BSr=8"__ia$RH9Vb]AC1IcN]A0+`M
`3l>fIbs-kI-pBZe[*.[,1/JZae8o<Ag@^'CCl[<RIo<@<m%I-G&.!q!<r@h6+YnoM%-2[J)
Bj*:BJ8CNaAs&oC?Y[;PBd(&qT[R`6WmPf*B?I%9H<6La9KgX0Z/or??4?G!<\*.0XorFD>0
I_mTXmd3h[WN+fY\!G3F9,o\ST23^.9^CinEGRnbg8^F]Abk;FFNTaf+MPXrif4b2)OG76]A>"
W5HV-EE6<;`i11LOR8,'FP<T:1UsBr[_e>2B/1;"Zj<*YfH7Z/t'prRPc1[O-P4ZQ[)@^KI%
9qRDZtmddA*G,#jFYA`8CO@ib&MDrTR>/lW,R?fL!4o),$J^ss'*V]A939X;o\D%3$038N",5
/)!jg(:KqofD&(CUr;WKuSGX-I1fD]A?E[HSLUPbGK6uFA^$BaCTR8Zc+p;iKbiD:`l/L+$!J
=:.QhfG'\b+9f\V<*l!W`,Y.C.gk1g\P&gRpT6]A.gZ!Na&VrlY<d^j21ShHHJ;=.uQ+etajJ
2/QaG#hZf@lfK[N>>THriOp*f*;B8_HhbS*FL)&X=^A1JMEiVR@A`J%cS9eXW3b04ks"bmIc
9fdVJ1mSVl1DH+2JsW!qr-,]AScQU:/TrEY<I^LE'NkIUkH87'hAnX3:HOP:)McE<s;!^`BeX
dqX,2Yp7k921qr6*auCa<I/Hp'6\=bUc?a?Ei3bhXg.MYg%JK7/&9`-Sf8Ki(gsZX$,`mUhQ
9O^:RXbV//-r,!(4A4."?SHV:n4bFJZOhm>f8YS(3#([FrWY+8R.ICeg>I76lEgmN*l[(,Te
Y$6EO&r!MD*:7U\a"7"r)=.mo_VgakbHY+:Z7+Pg-2+o6T$BruE.!-fGhhJs<?pnR^#D-^[f
,QuUEkYHkj?]A,+J0eNNL`VA[riJ0+C>GV&hN]AnNdeG("aN^9]AY+F$$o#ZLRhDYM7>8<9t^K&
&8pSUdFM!f)7+m,nU!l#EF++3J[a?NA9O$L9$mF"L_76KjgWC]A[7Q?MbF'&E2b>6j)k+q^<H
#[eZi9g@4E^`ZN!bY(2N>`,4?I:QmsgbWA%i;$J&SSAJi'j$7g'GZTih=*TYmHrlgt^lK;J8
`?_E.%DJ*Fq5^hMV$DSir`sl3ncpsU@M5f1YdLp4pK:M#fM#!"2!1PBh1_ojj6B6gB6EKD&l
pR_:$Y",nVc.6I=1\\S.EsBoDg`J1f-`M?!^fI2%CP[Bt,[p25TB3T!7MZcRl>d@iuM9]A@ZO
(s]AKR?hsm!c;H7JHmmX:X')0S_p[Yfo"3,L.1h?c;\[;>Pi.$DiV\4=+erUU&U]AE"!]A[ej>$
DZoeGXXT)t$p@\9A4jL?5I(=CM/\]AU\anARM4K`UN@$8SuoO3m(.7H"(`#ElNtFp-:WicFA+
n5rHVd_,[`[Z(W@,J>t=4^q3s]A"2srmKTHok/nR.NTs.;f1WC=%U;<</Bei_[I.o<W&RoVKn
gLmQKJO`S%f[,^<#F5tJ(CEk&H3n0]A`5?l#OA1OhsR)=HAWP+7+%NHPE.4jJHug23(p2#@UZ
4a,Nd9h-/c@@XYL"JrU;9=S/)VHcKk<ah^(O[Y'-.$oZQru!DH/UQm+6(j($a:+nP,gM2clP
6B&g2iqp]A7Wdn=^4:FQ&/C2#n$:I#EnfarFK_pd;,S3s5D[nP*VY/%8WjU=H[Hkrb.UgoaR`
Ks23,_*G&O.m%?EoN"C$##ElW?[1"!e4.g4<&O+1`rWK\"R$J,ZYKbn`2HFqos"g%4CM6"JG
!099YJ-9fCP!e4B%K-1!ZY,hFjVt$JU%M0^:Ibk[?#kW4_704QB+V]Ai$L`+gH`EAX?(^1>iY
4FBB5-g/T4H9P`f^P@fas/GV0:T&8TUA)<QPS!eLF4u1Fk;iKQ?+;)+FQH4CWCG_rN.MslBF
/g-iiG"^g^l*Vhg7'W(HYcEiLT3Kc?3OgS,Z`eO0bmKn*K";cDKao.(Fpr4'#_*8uArl.cXu
0fI.)PI=O<KY^PA/)]A[b4r/"AjkY-!2))]AO18!">dagfMX&Q6M'3fmk6^>8C<NRIu#1HiYhi
F+V$mlW^_5V*&Qjb;M#9fY30buC7.DE1d*9st3iW/>Jp4A\-!ZpNB*E[`2m9VD<i#bZ%n"!&
OBhV3P:!V`hAEs@1]AACP2#HRXh7AZV;[0&g(_1hfe*uHJRYb/sW:1u-8ic4Kf!H.3Q"AqfEG
abGT4tY+R?NEB7DY_*X^Jq.bbsP0e3++cDH68+YnLjC/R:?'kJ+_m.Ek"[]A%i`Kp[p]Al1eB\
sq*\fe\&So$\nKf%`j"2CQ<2rd7n^6>AXA5>>Ic@^@neO0VO5YPUq9j)Oh+!,g3if,PrEkmL
j.i]A(FSI\$qD2oO-SmUZ3TB8E\^.AEE#YQd,#qGOZ_0qQT$\M3Y5hm)c3R<$7iWHW[s?^D5W
H!PQVO_:"R#T3n8I07)M=f-A;en'LpjO;;L=`]A?_)8!ND;)_<&S;=R",1!!s:a7bZGAZ<_YF
hm:$KMn`GusQQu8>rdGOU*+fj5]AQ:i0qf"Qi]AS=ubCJF>9k@bR\mI=RaI(p5/"2*stH@85ZK
(1n#WZ4,(HCcfO<Oel2%+Zc-I=g.0pP4Uj/f<q\5+q4^U>\Tu6PB=&&M&i]A"a\$0>VHAW'e1
"+]A200tIad*&QDt"QdM)ME;e5ufdRFSA>Cmr8!rl/1(I;lc'q-&S_'A+KUJ$+mD9>+*"/.8h
J;*X4lRTO:ZJ`;&G^V7dS3CBhc]Aq,?oIHAeBhNhk(W-,f9VknBOkYL.9T]A+G7-B;W[ZF6*EG
e9.\mNL*[uI<dkHQDKQ]AaGL!!]AD%(TYtZ]A,tCh)iaU'GA*R,/mR58e>KQ<:m40qTF'Xqd)fL
2%ouF3l6>1cL82Fa>/ip=30oN"#)Gp>,10T/*uZZ16cG-H2l0`B9.c=\Y(.T-iTMoMrK=IZc
TUBA+Yl2e`#>jn9'Xc8IQT()poQ"u90Q`5pAYlrmP,lbn9iA2h;OlRFV88jn5`#YTocX7h$1
Nn7_RC!qN"T$E38`CN!JZ1EDqeJ\_qDt;)R4I9Mob[W_TO_k[Mq5,Cu84XB+`,,jOpI`=fj?
qt&&]AL'9-o8>)b*5Ii@rT27[pQ(rk;Pe^e7b/cFiG9NpghtFHSNoF""Q<$#_'RkkBpruqVk9
h>)(X`KqXVT$Dk8p_<DrGnmXeMK`)YUViB<&MGp$6i=]A;Dr4PRD(pV>(?d!l<H7M&h/c]A(MN
7KVr!g%M8L*P*N2@&eMpRMC;a3\))g1s1GK6#:G@Ue>_m%b<LEBZG/lJeeM8$)"s;;ee'n1(
&6@PGa@R]A.(gJQG+.#_$!OeD2l2K2K,C)^&#hM1P<i=0ZXP=ABR.As'@1q._24ToKu8UA`7a
\;I?\A+FAJn(9t+Nn[h/CR?,m)QcC:?"J5tC>>cMc5^]AK36L(\'N(jqVo:jo/UZ@V1c8HdiQ
Xgc6]AA6#et1i`&F<g!B[gqWY/XVJ(i[q&CYV*CnY=3eR.Jk3/8*,U8A[hj,[3Wblb8I_mAN-
2O\F:*QiE`?E;ku\/]A8,C,JWi`eGo@q$MI^O"c(/>"a*d$?1V+*tH(nYqu,r!u1hf]ABHf(=O
1E1$tuLR"<iAUdg59:P8mO^+:dJ:6#!4me+'T\&&^WVo$3O^\V;g>/i\MdESq;+KM346V[$h
C>DkUX[OR,Et`sUa9h%.Ne;h]A3^Zrn]AN1Be>!8cPPl(VW=s`)%?WT"lE2NOc@.-JPuHXW0FO
8n!#sACYGC50L#A`\^57$::Xn=C?$6/G\\5Q*g@#;,U,aD2K7$H'/lDKJr#iRLSeXth,1gUX
r6c<8Eb4cnc$*WY[rF']AN?8&!:ett&^Zerl2E#$A,o56^Bi-rk1B_[A+rMhakLd%pp+)3(l3
"oR,([r?pB$1$QbZQ!S5UJ3p>S[),[S*A<73.b[[\4(H:_)!3*);>P6$'_X8VNQ8`a:VOHab
G*frgV.AZqDjHu$R9(hRN*YZAS&:jb(>PR^OYSa&"%m3eQOTu-4^o/k:Ke-Bi=@-/"2-_oQ#
mYEZ+s8c^UM;#jD-c$aj0ABEneH,!$r3eBf,[CaF+uAXq#Lj[d^nsUO6(i(JNh]Al9+:>^[V(
MR/t@977n?jKA.6B(a!5@/<1TuD2B\W/T'MfB]Au!<_[p)?)&XJYoVMkh"-_#k@a<%d2)`-9X
Na1?]AG-]AU&MBUhl=Rf>C"RLOc5[o95Ba4*h'4C5I/<]A:UK.8j:P,_R?6jG%a.G.'??@Xp6M%
34gI$q$k/:!b(.0Tc8;+oq,oGgd@giABJk>6a/lk+LWdFEXGJmUJmX]Ae?Rmln7^^Jb#a*2G%
.:t6X\<PVlPDhKm5mHjWOAXjAtI6eLS,fuF>;-b/THcf42]A^@J*fam\>->C0bnK0Ud/5*&"j
qgKl'i/'(gB02PFBCsO)m^_`P4`W7"eBGOL@DF]ASDq9O\Lp.?"e&;S51r:n/,Mi*fSbH1I!j
ffj`gUlP4kUaW[O!%iPlADDDA9T\]Als1P=@_2LUS#K:s&r?<HC="UsTW2iFeNWK`/q$VR?l6
k*[@dOX;=gB>HM"<a5]AIX&;3F@_%#o1!8$9;T!bY@ZQ"+kCdG6.WJW4.VS6=.62rF9G-59XJ
a4NP@n9-.Lf)S`l\BVql#`r1*7add4Iou^K5]AC4nmhl+@L_#B%8>;'t6kL6;WlM7@D;oCWu]A
cI=P2J![s]AZ=0IJtU!ejhc8?B`rS:CFT"&`lJC6.3_Tdp_%rOo'NV67Z=b?<Upq2`nRkEAe<
P]A_c<Z;SUl)$!<O>#nVjTkFgT^DmOK*J-`!dX4ab@u6n4Kb.naXj@5OT)"Q+E[?p5T6upS?k
VjN]A\aUZMjsYNJ&bZ`S*'dO^'P'j;?[JdV_F"V<Z<&X,UM"TGYm5Wd#*OZ<XG$;"4t\5;$V1
lg;kF<\oUg^`XKO^=R7XB<bn9<Y:8(RjHg:5NG1`%($lQ!TNau1lO6IGU/ka9AW;s;O6KP1)
nRj,:_'RCWWce-DQH]AVA6UR>Ms$emb!R#.C*OK?VVsH@@6eEb*e!qgt.\Fcu$'i<5Cp8A3l$
\'8XE\[Ufb2QIYW4UI7W+?QLq0VHYoqBBTY)@Vn<_UD`g.;3Qh8+Pe^?fBL[5TOQ-$$6GBK-
9A(<$9T\>n,83eQXqXfn+\b_:OKYEN&qu]A:]AncDp9jXQQDJ:-jk=Ei/R!H_E^"T+S#Hq@9JM
L%pnfW'&JlDIb/dND7)L[X_kGZg*b'Vu;@g56-\:/_==q8uo^e3Ur4P`.mj)>:"_ibmi=;F#
TJ,eQ-5jRCWbeGG`iJ$(`"mFqNMb2W>5L6lV"Pia@dZn)hHri?9aXtO1nZ*1-Z2T6]A=#2&<R
daYq)2I`P/i:!Z=@0Co/.@O7D31Y%!&Cn%PK+V;+cT%Lt6K;[irDk@0"%jh7Y%-'HW"ngc3T
2fPMo!_(Ye_\^Vp_C)Z.=F1qSppn,9,-rG&HHW-j/M!&7f'>*-Jm-!`TLBt&M["9JbisrCi0
//G'-`tK!$22JM(e*k?#TDOBR-Q/T'SHuIg6]AN6TmJ8K20'q`boLP8e:hL^r^mC>Ke2&.n"o
e*$?;7gWZ!#S2SPCYC$M'+i.+#k!ZZ^2Fs/[0+$2jJPQRgDL4\f/8DkaOdFh.nH-/hjr1+<5
-l"-N^L./Nn>5j7(sG'I*96XfN]A[U8fL!/2p0AhU&u]AKn;5kb;@q4pp[9CX[E]A'jB=ceACYP
MI^jl3?cQ7l'4Tk-Zk0G+8JB8.9_cVbHD!,H&JPg)WNaO&@UInd>,e:RaESd4C0+]ArKqJHh1
Wk(XdKs)n&55l:kD&E!8O^9[R*=D)qb\h-@?NeNO_ei%q[R\6;kErJrL$FmQ5;e+=gGf2p"j
q0[FYq9Je/VT\C;0+h_8NN28Yak/!=S/2h\Z-`f=N3Q/(%1L<G9^<]A)/HVloXm_9c7J^U[G\
pQ,e2m&e@(W^%8,&=,:>-hSWJ?6jSmL%cmX4g%-W`lmdC%\Wk[RKcCUXciG"AuEGk2/5.YhR
KrJPYG\NjBjTf"JQRH($3$(B6FLOu[T94*B?$f>e<RG.>=V<+N&C8U"d)`LbZh$=efZ),#@_
9BASYU'i/U<^<d2LHeGZoHg+>\7^K%o.sS(f!LmCZCrZS"GoN-&B`5$EBHR3s+H,MF"=7jjk
A^a7`UMGg-4]A_fr#MiYU$+kH#H4:Dc1%s%UlB7C5GRqhA#bG(,^pZufQ;o"J$<]A"c9-C/3nK
j`-[m97nb50Jl3U=H_fR!V:Vbr3[E&PuHA@d.Df$m35jICoGS.U;_8UeGXPcPuEC2\JBi]AJf
'c==r"5dSPBiG5X7mI8S!Eh>h'G7ERAMOF#F^0"UYd:':eQJ2ZS(&?.l$U[37?+3nDkZiBa$
5##m)J4-.Tk^)6*)U%)DM9KoE;ZQ1KoO:D`"3\YBlf<u&G79`_+Ubj&L(iX'&`TG8`eBJ?<;
%g1G&pZ'd?UZ^X86%XMU=\-#J`OLOkm$9Y,_D^m[1C]A"AcM\:m?@s3/Jj"b4#fp5YkJthQe?
&3<B;bZF:dbM!kK,jG9;hkh"!Iat)4:^:[JgCf^GMIf)aT<9q_Q"1[DGm&\P5*)+-f3j\da(
CQi]AH^7!H7s$48Y>5j]AdqCS#ZiOoc_WhfG7S;c^$9=#F2Ia#,5!`Ut2*de8p&Q+.;VM]AT/Fd
##?KLhJ?dD,dL_Uok]A*9'GOcDD4ji,>@W"Ifc5/Tci%aT1*JItPmXAg&H`0WqDQ&X)J:Ue,&
Dl#d;k4&+^R.O?kXj(1aN>Gs:qE8Gs,e4[N98cN1J"@R>-)iA+[W#;kRY2A:?mGL#&fVsV0b
S^&J5QE%orDRE_fU@GD_.u6EK>O%Eea+;`Y-[V:c(15I[q:Z*4R/)ClJ'Q,P#DEXp95a/b,]A
Gd.tQ\UbfJ3gKSCYH:J3[NXkTifR9aE=H':b"(,l9Z[9Iia)+'nM3<-"TuF6qFM/+^U8'"bo
ogper$OW4pR#OE1,kB'e3$_p/BR#'l]Ai^cAI%X.e1:[/Ke5`C2VDD'=k8`Fe:db:2$/]A)LAE
;N//eJQM3&1N/aRMf_Gq]AE>@J2.-23G_bhm!50MEg<Iu-FJi0KP>f?aB+jkZKs">Jkf@VEdY
Y:gfWGlFp"5OI[u`jZLela<kIrR1,j&)'KHO4UTB'E&l#c=5+q;ttVM%p!DO$WY"Gl0*R,#"
7r7E.p:eAM4l(FG2l^7[stpJh/<NJ:D>&$M)Wbl]A1s7hn=CNMec.5LLD?cjBl9mm(GAd&p?$
U*!_Lg$Bus7/R;@p-ncm]AZU<`oD^NU/,hQN\"%u4^-!f(_L4+7u%ZAu3H-r_6jeJSr[URSEa
%)*X[5MJdqqlSB^JX.]AqkU`5b/2F9J*ISr&+31FQklHe,@fc5rYl&2g<bg#(ZT4V"M31Hj3K
<!\GD3M5L-/UND#2@$$1SNmsX>in=XYsZ%L,-b:6Z?4t_[]A#U%p_ma"'X4g=7:.pJ3je`-Rp
[\T4b&.dBc`&q+B!eR4H%miW;7]AQ#=P=N/VE]AsTMq3_ER6.a&0@7,2W:Fr-DR!52B^O8l;]A]A
Pr>q:E(DM,8B,UOu\=:$hILnl$9D4_Xd?r.$nu!Bt)8GNEBJ/_94^pB?G>4]A9]A=(?lu=OBga
7*V5+YgtcIA%L#V-;8\.G1u:1h1Hc,:,^P^cLShE?S_EtQ%D]A`t&=m<>(MpBn+`9g)S\k?S%
o^-JkBbjL#&3E),tjT@?-et0[&g)q4<'97a$=e8s)n'6H/?SOU[NgEe8:4d2L@\(B2>>#EH8
U$4>$GD,\S^Rp53ON*W&;*:,JbZ"Dq7g"*;uS<&krM/)jZl/@+C/)WkUD:M$,=>2qgR\^0R)
=38b7]A9/&O,6qElWB7b"q8EFO[QW'@]Amo4s.`dCW,$N$5m",ghK@8tr=V3CBRdVo"\h9SC;6
V'W.*B@a:nFqL?SK99plOpFVR2!]AbA<5hgJi"Mq+DO89,^m3?f/Ehf%\e/,+B$m/;2`lRL5.
V^&>?11,"RcT=,JUlHVgqKYVPKbk]A.:90Oa59dI-a)+Cp&_$egmY2]AK*^?FM.V4cc(^>d4K#
;K4`:0<(Sej_Gk7<jS\IdZ^ap@RJ!f@Qh5Y[skRQ_9![*QLZ3dY!MI(f&_#GYrIS+eh&EMfG
.pn@K:-.)pDRM3%\YB/&dNZ^-K0E80(N1`j!n;S3Tu$Ou\=Rnp<F0N(ipE5<ASns!g_To:$9
%hq1VGb!E?TjT5m^#QX_3]A]Aj3U^PU(@WLp#C9kqrfQ"VYUkgjK#eYg>c4/7t#?bMR%-SD5bi
t(Rs*R$>bKY-)/_rU5FjlmNh[-f2nc63fs46g+UhR9ciQt<57P^F<[cH4O1ic*#s-T5&LA6n
L2+7P&&PTt+02olm-VMr]A6h9lNB2C1C<P1K2ait/C8@$a8fh%OP/@`e;<jE/$NVR;"_1Mthp
O,AlN\D=Iq,K481O'c!kdsD9T$4?pSkShn-7fY+Apce0.n2k3=pJ"_bU+N)^FM$)^D3O+&^f
l&%b2m=0j2ame=<B/`i]AeR?Vln$0i42.n0J?3>H1*EC/?HPkc]A31e-A\mSgX/ROfbgFeZi")
?eW(2K[4Z@/@6_Ga@U<1>mta2[lNrAm/`Rm)s$KD.`8nWZ/E1*lH4_Wj/)0'HkmJg&!PRhS8
#f9CW\^*_Wpr;kM':.lo(8%<tifLcg=WT3GI<fG#&'`VUCC&R5<958JfTtieO<W97`Zcp/ce
3H"lliYo3&?JAG?tFOc=eFAR=TaNji8SPukK4?)0F)hZ_Lq$%mhD9=hb7-e_0A?oPJR(Ms,b
G19DH9)ea/&4BDg#h)iC8PZ,%R6XI#(K@!Ah,Co//Fq@/R1f^B_#2E5n\sgggPj90L39OQ9a
D;]A@n756kS5]AGu12:Ri$LW@ELf=L=G@0H7%KQf9>itOo]A=1bZUKsMSc!68L`:eL&)#l"H$BA
jRHo$($$j#G`f6\h)#gfhILaiZQR_81L%+l]AV[VG:BBfC=b:RQm,_"h;B,($q<1?[<gknN6L
J-/=@%0!\bp)HXKMTfV7(-d"?H>6X'SS'L0AJl;d\/0r@>&ta$O!+7.fX3E^9:o\mG@r>U+N
p69"W9N8b3>0f!Hh<X`]A@G)"q%TP3dG/YWNb&u@.YML2N#q&,AS)%HLhW*Pl@8a"Rbj49uQT
Yd_adY=G<,KBBAK5jJ=0C8L&q"MYJ9Ab5V2]A:*2%kUmu8uXQ%*;c$C,d1<hAAjThYLg`Q;*D
&@T&+nIecMZ2LeioR6,*l]AOSm?TbuGY?05QI49N,jKhYI9fF@ZU7g7;[XGH[N`eK#*pqJ<l,
NF5"`I7K;7Ud,]Ak-/Jr]AKnQA9Kau4M:Ze8t)jPA%F:c)q!igDnF0Eg;^:4'/rpUird=VqVSN
jNj94pRrg4-el,Pu.B!Zc0E#8sbH]A#f]At9fWHZ$qkj84)eY/IirQPZ@@\$iXhk2bqkkE9tIc
p#:l[1hZJ=WRj:k.7r*UPU-1>q_/b?k?_Ls'HtrJ&C%JJ8k'?>9]A\p)3^k#H`A8p9\;t9b"<
/ZnK^of:oZ*]A\]AGhN\W#dYa/ZuqFpHXSg+Mt*mD?*2,mY;+3gG?E\b=(V!iW1M&TDXo&-<CQ
K36rlp(mp()*:Zp19$ZSZp_"7?^CN<dSOEH]AIQWjaTYZo(tXb$d'FRRgTg+T+oaZn$,V#T5<
T%td>i'Z>abq"C$!0P+ghQce;j$X0eV<!!Vd=Bq[IPadLU&-&I2^$;Lg[H%Zc>WnIh_mNjO_
,8t\gO[a*^JL'Wsb["7(pOlYs?T4A?ST6;?`p?]A&P9'l>S0%"0ca\OKiH3d#i)[r8\A&&V<2
gqQQG0D?*CsRe[[i4=L,o81b]ARADND19$Q7H3Gj9gfs'(,Z)#WX_#'9$dd2N?*YXkhA)A:O]A
-0@4ITQ^M_ctf284OS5]AR`YRK6iB=j.kYE1mX'EOecX-TjiPN/#+LgWR-_-nc%HaHiCo(3D>
VKX^DOb\_ZqBPKV!k'reo-pWA$@f#nr,UJVr4;@CAcR<m8u?<KoX\?#&alNhJH_Ail.O&F]Aa
b'cfE]ADRWkSR8uK7l,5dYV3=A3nQg$]A6ES$mbMA?F/#,/de"*3^#On)^J+:o97JEOTBshH3R
."4R0FQ;dK>pibDW;E;-d$#`R:#bW0+KT4fJC'7.[Sln6sTH;uFi7oJ3t431X.k,=-P>KVe<
_a10_^1h?)HU)6uHQ2FY-[u$\[b\I(pbE@&AaAQ=VSD%8?*IP.l(=MWX!fjr9A`HE]AMgosVn
tW:SbJSmGSg517>%<YXHRs\[q]AX!M$a;%er(J<mCP$rPNqtLP<qV)Y<sCtGaOH\*g*rD[lK>
-[R51qb;G?-@e7Z',%@)D<n\Q1bm?oPoZ$j5Zg#t`G"XannOcf>I]ApO4\14KM@rL]A4Wd,K^'
9N;,<8-fQH't"Hui0`;>GrJm\I,Q1)Pr+tqX\4$!I([YF_Z!4TL2cJY!!~
]]></IM>
</FineImage>
</Background>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="specialized"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName="新特性"/>
<isCustomFillStyle isCustomFillStyle="false"/>
<ColorList>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-14732976" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="true"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-14732976"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="10" filledWithImage="true" isBar="true"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[学历专业类别]]></Name>
</TableData>
<CategoryName value="学历专业类别"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="522" height="281"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart5"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按专业类别统计\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="5"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="linear" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="specialized"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-422264"/>
<OColor colvalue="-11184811"/>
<OColor colvalue="-4363512"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-1052689" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-9997189"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-9997189"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[学历专业类别]]></Name>
</TableData>
<CategoryName value="学历专业类别"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="890" y="61" width="310" height="328"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart4"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart4"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按学历统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[学历]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="true" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="education"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16715266"/>
<OColor colvalue="-14586881"/>
<OColor colvalue="-11927297"/>
<OColor colvalue="-16730625"/>
<OColor colvalue="-13963597"/>
<OColor colvalue="-16723559"/>
<OColor colvalue="-274674"/>
<OColor colvalue="-292082"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="75.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="最高学历" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[最高学历]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="740" y="36" width="258" height="427"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart4"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按学历统计\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="education"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10764311"/>
<OColor colvalue="-10233949"/>
<OColor colvalue="-79765"/>
<OColor colvalue="-27427"/>
<OColor colvalue="-6887937"/>
<OColor colvalue="-6951467"/>
<OColor colvalue="-487545"/>
<OColor colvalue="-14374913"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="最高学历" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[最高学历]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="542" y="523" width="116" height="116"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按性别统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[性别]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="sex"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16715266"/>
<OColor colvalue="-14586881"/>
<OColor colvalue="-11927297"/>
<OColor colvalue="-16730625"/>
<OColor colvalue="-13963597"/>
<OColor colvalue="-16723559"/>
<OColor colvalue="-274674"/>
<OColor colvalue="-292082"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="75.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="性别" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[性别]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="251" y="36" width="271" height="427"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按性别统计\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="sex"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10764311"/>
<OColor colvalue="-10233949"/>
<OColor colvalue="-4363512"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="性别" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[性别]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="310" y="523" width="116" height="116"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按合同类别统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[合同类别]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16715266"/>
<OColor colvalue="-14586881"/>
<OColor colvalue="-11927297"/>
<OColor colvalue="-16730625"/>
<OColor colvalue="-13963597"/>
<OColor colvalue="-16723559"/>
<OColor colvalue="-274674"/>
<OColor colvalue="-292082"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="270.0" endAngle="270.0" innerRadius="75.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="人员类别名称" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[人员类别名称]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="522" y="36" width="476" height="427"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按合同类别统计\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="officers"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10764311"/>
<OColor colvalue="-10233949"/>
<OColor colvalue="-4363512"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="270.0" endAngle="270.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="人员类别名称" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[人员类别名称]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="658" y="523" width="116" height="116"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按年龄段统计\"]]></O>
<FRFont name="微软雅黑" style="0" size="80" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!IPE1n;0t:7h#eD$31&+%7s)Y;?-[s:]ALIq;@!I,!!'pi;l0Np%mroV5u`*!h-Ki;*5rX>KT
JGYNCb+"Yjm8HKYoNPir_C$S`ua!)=JIZ,/A_jAI)AIPAh?d`,YblrtG;G.?YDIlTSonf,KB
r,#A?nhEGNq<U+qeYICC,H]AE5mcf-=;g9]A70IIla[YC+_d,GLr">[0?>0!Ba#c*_lhm.KnBR
9l_+3,`K=pPNS[:R3PM]AOJG_eE5PU7eP%OVlA"]AA[O20as=d^A)-]A7l%]A(C`UG^7=14X>bI7
/XB)0L/nZP'^;pO]A/e,RCckE[(#g_;sm"L)2WmG?c1h/`7S:%qEqTXIoTEdkPrr@;mLq#]A.Y
^c5&:.B\c&qGP8*L4=QAF3K?0BSrjr!\Oqt\h1(T1;:CfEo86XmigfS>$gWnBkKrMZd+)9-b
m/RCZ*V@`8BjX`D>#=AV_(V:Pp74c^gL<=-lVT6NC7Rm;4H[N?O,=:_'.4NV5!,iU5-qpAOY
eDrZ(0g]A5V56@:Er?@5FMijodN`:qgV`8>62S*lo4J0#";r3uH@(1:,ns,M,pF^Os/F._=]AD
^,5kpE>L$m6=c0S8DH9]AiT,u26H,n9;_`)^"G#<CiPNm'Pi#G#plI_1Gh0B)p9am$9+\Nlca
KE"mj$=GAUog[;Bqbr$:YL!%4&IULeN*&-,["H)A99LLrOO["\_5DXT.&Gl,p>4=h8Y%]A`(*
guD:%IP;E(QHj?Lm?1rp5F]Asle0Q)T@*/*aklkJ8#dpk#H1+d/Mm[O9j76/.h5>5+Pb2"k!Z
^:In=YX(-(!DjJlZ[^5Ng>mU_eR0/9s2b?qGAlA>F+5I?:s8EaVHKagM#N]AH]A4%g4s1H=<.V
ZU5.o2Z+O:[/nAe$i%7r35e[gQ;Aga.?aUZXb%:FU#TW)/P\/7aV;9uAK",U?G':a1KDYgB]A
&f2<!1e7UPT0#G!#+8+CQ\&B<6=_2&oaZ<=tacH#02"0Kksm-i71VK)^>?BF/sVX4#&CkOV>
6Hd!('>=0p_P"3tb#Q^g"^ae>hR]A'V]A&b?BAp(ipr:<#eH1.bkgq;U0@ApDAdB!KWN^P^rB-
;?<*G8E]AsMgSDU]AZCL^%P(RN=D<3dYT:$K[P.pPL[DnW"#U2elhCH?*$r[:4'H.bEFY3-&<,
MA2EVZP9Xln[dp+j^q"n*+;%55iAjZ4dW:dL$N<>1OdB=WMB4C%0`iI9.jA]AoG"Cj`i]ACA,:
"+@>4j^2g/_53r@@UDuOPnJQ>+HjB_Hd068SI_=XpY^!=[=oD[7`SdfZiEG1Xpd*%ss*!%Z$
m=W/$D';!#>t/0,OiR$AO]A<m.Y,=+'5KXp.GUu7s'EtE.[f.3^`f,[?iiWkPth;GlAW2Ye_5
j&>5mQ%&5S*W&*CM22iNC06Ya1o6^bBO5aF&qRWV*$B=XbihI\3\T$Hu)"s>=lZK8fqEh`d2
rCEX7X_13`8d:*(R-BN:)+/r+jO35nEF.F[([+uH<dP?a/&-EC+f7q?BLn</k.-B*\q`S9T3
iZrrCPN>NroYD.[,X?qZsiR?s,ljGdb:QD`VZ5GH>uYbY#TMrq]A,?H&2q8d;@-5W2+\qYW'1
g4!ti`c\1D_>LJ.(nC.K6[ef5Ilg\lt&"JMm+G3:Po4BmZc]A5#tM/23n5*uO`P/dY$(Y;:@Z
qcnL"4>'-?XL^$*KYa3Gb]AC_D,/l>jT'@VjW>#I:B&ZpA3BV1#W\;F$q,ckH:;mo*EM1H>c;
Z_^uHT)mk&k7gOO@t2s+m`#\Q0V4G_cN_k@VZ`s%SG9EPiW@[r&V2\a_+%uD!U*"u&OJ\eD4
[^K4q+:K""7^Dj;/16l6_2a5+`h1?uPKL'F(J^M<Jd^&;:Wed2P+6LtkfiFlS-]Ar4NehI+_j
%6VXX\:/-.Qc\Vqnb'ql;62mhh3ELp(:W*gZMi$D8k^fa5\d+:iO_BoXHuqmS7TX=,&s1J6,
4'qBlOo.ek2>aWHX;+/<<*<^Vc-K+LSB4a6-iX!?9k!L\]Ak:GH5)ZaF%2$!.0d6Kd;&X?QG!
1;h7idAh1gd470&Ydl2EM+G!hH4()js?TP!O#:k9D2E-(ljPes&K3*g"mYQOO'8Nj7bRYX"]A
,PVPpllm0]AFH!(on8p/EAqgh40Im&3@Xr@+6eJ$F.6"@AG2Br:bKH!,1Po)*j*hZ]AYgraCoM
bL]A9mO6&'^OUu=rfRg+/K>M#C2bp2Pk=3/\T>1lP76hP^Ifu>l2NcBLj$&P3TXjR8K'$,?To
9IjM_Q'T(KSG/:-'2gbW[94;Mt9Af*kNAaOc\h$#*c2dfp[?abf.H<O\Ni\>F(gq[/it+]AP_
:a/So$b!0@03KXRg#[02k`W/fBrSl!:MLn(a%?Qh"InIZmZnO$<GU:-*(5DZKP$-_ca?sG;n
W.qIWY7Y\!4d*uB5cn$+]Ahn7oSOe;ZuCu.OE(pJ%nR?^i'u#16luT,s/WO2s5#C4-dG)T)(L
,ho?<2%ET"nt+P0P-[!/>I8?Q9h"/4!@R+>q8`:=Fk,8&,)6nBQL\M0%?&>T[PXa<ngQ38>k
-#$gX19Q0W?)RbuEjJ?*2:O.;G+/5(A]A>[aj8:pC#HJ;X2p9/7TO\smaDhljDL7iT!*`7"!%
LR7=%59rr.n''Ua?pDpr5l*_PI@]A"<^f$m_YYq5R!7e<P%StDR@m:O&45X,Js3&MYsAn6P=n
t=W.+W=/uE5<%`uS@"9ZY=5cE%5d6op&.)nl3'O%'FfO]AS!MJL;MV8%q<7E8s3%H0Q3W=f>L
s\sR5JhH[On1m3=g:gb'0D?X'W]A$'"<d!ki\D(!3@Nfo!',NOQh@DSSS!>(W!n^:cb?rU[;Z
b'KG+F1#\sH+WcF!(YdZ'g!Au_Q4nZ;10>l%WZh;g]AVjSXCBZ@=La]AXKtF)+q,5]AiSk41;)_
05^GqikS$1[gF81)E4"#gtn1=1.Tc)>0l,_BF=i#dnU&Xq%:d%FTFJRR7Pb=gH3NYq)IkGi.
\C%\^6."FHJbSG'4uYRHB>K\4S,IeRnr6W80fU!c)=qd"U3/Vd$:@3(C?Je_1@70F^:YB_(V
)Y,e0)69)3NTe&1pL1IS>$i**Tnm#j]A.L"A)"Wg586<cq[6@m'Be?F]AVpM2hEK>JV3,*g2lk
.J"Y4^\!NEi+^+e1Ms*8/sPVXp_Z)js1V;"1't;n;XEkb@kb1OZALJ18mZJ2XOB[&aHMr4$u
0U#$eQHqi:d=@O`SEFiMR.eI9,pR=H,WpqT6#cS8Lq;IH=aQ?"/uYs<]AoOW-dF.pc2ri5*74
T+W(\6u=u`69lK=6$(5?cs))HHWqW6\Kh1.>NqfU++86&Hl9i8M_ASY!`bo.'7I@m&?8jEoK
^RijV?E:[#AMg?.>f#01+_CMM3ni<_%L&?BZjr;"'2b#809fb=&WJSU)O9!0INZDqpoamAoA
Ce]AKFBG@j%>!i]A_?cf0bP'6ms\G7[aDep"`ESbR;(^WIcY2TAlMfSS9TV0LGU!'HOdkIaN.A
!=F+D[@N`]A<kaLfRsJo=cD&W664#ND?'Q.AlKlVrQa7(c1^!\LlN_JBsKBaV"^TLC4XdD8k7
XNP?SlC*>7i(5HWoN[G3H?3\)l]A\'k<s\.0pc"oc)G/ML-a5Et76@VJE1^WV`r_`\?DRaf$(
)XoB.AN%AH8Y^aB^o1f/CmT1'E68hsXhqZaQUiFGH]AqX_'#;tuG%jOR02?2R;)Kp=^ssC+Vr
A5A*Q8%\FH<?R8gC4=l2;B@Cjc,YYe0j1&pS@N149ID:5RL0#.k!uO)c<r/GiabZD4(uiP%Z
`%"ruK`5$^A?3^XoVnJ,c?fj!CD+,SefcT&1o'aqC3"O+[Zqt?5!lMeA`=P+G;\0!8Ma+k$X
C?Kc<Q!XPJ[U&qBOki<d6e<Lg0e.>UF"]A7GBJ"bLLIAO41c^nEN$(onnR^7DhR<ijdoO_n9@
2[f,A+0s!O<A_/hooH.[=f,cTmS.MTTF#:VEpJfbJL_P"agBbQHpSWAb7mCe=e_ju\cZs$[Z
]A7Sg:(T:lBI0mbBQ'+C36UYTFc*O\j'1VEq2hQ;SB:N1+[G#2<f7nccm$+gii:4Uc8a(A!]AC
F?"oeRn=-SV^`Z7nJPC]AYlmnJUtjAC]ALhVJc%,^_TZ"cTs#bY-ukSmfgl..cA^9p+17Dna=j
mLjjULA\#k!I9I[bgIWV\d[pr:r4W^m;65GA.64@j#[o(km"#>"$:RCU'7`G\RCLF\O3r;34
M;+^$tHPZ2r8tO3VAPHfY.LCMM.hB+/FH)mASE;fV0-sP*n&lm#]Ag_:&Cj*BU?#^>5&KpH/8
09_1_?2_2V.C4_4T"\<1r)1="ar8+cC!JZ!COOZRf4EE]A6:Rp)A51(i5/aRieujIkmSdTkFq
"QGfU^T_tnD;-=I&=q]Alh,hR57.=1cZ%C\)6X`c#?JHRRFGmF7DBuN&20fWn,!$g2"\B[MN_
.qJW=qBO.\.O+Q:dJ;7^$n&b=2J(5RAuU1=5`;b^l(l-]AL3m2">u4QN8dR9unniVDI'm$A+#
jTWTXO3-Al169$WT@,JdHlDFn!,%E5Vc*YM,\PdI<<$]A-WlidG$b$-Q5;E;OqlZI[4?<B@2n
<ee:&uki1Dn@OZN*)O"`qK-_=7udDrniq'8^WM3i@bFHAj=XUb]APECnk)AW0FnU0Qj<&+K?=
^>1R/.p!G-V#/8Maoh#MbDi[jm-d12KU9\q%h7c$HRcpI=L9aku&s0<cH/Ka,hP$tO_#`R18
o@R,`@7qjJS*g3FQ+"&04"6.W=U[s!V59GQJOqYrG[CgJAet8@i&qKf$2.U_f.G<F>6c=P%j
e!Z`stImJmbkim&#=k0eG9d>UFau<iB5g2$MAEn&8tp\H*B6=_o2+K*t&]Ao7Thb&H5bJ8;B3
t&h!J+k6i9t;=kh9,MMGoH_Q8/Zasjean/mA\=2A@8R!1(cK(Xk+X2B/<mUIc9Uoi0)[lC0s
8@Y^E!VSq??5F"fleK:RM+UXE%."`N[VMAXrI99+'f9gAhGQGm/R+d!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[年龄段]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="80" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="age"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16715266"/>
<OColor colvalue="-14586881"/>
<OColor colvalue="-11927297"/>
<OColor colvalue="-16730625"/>
<OColor colvalue="-13963597"/>
<OColor colvalue="-16723559"/>
<OColor colvalue="-274674"/>
<OColor colvalue="-292082"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="75.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="年龄段" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[年龄段]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="998" height="427"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  按年龄段统计\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="80" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="age"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SERIES]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-14374913"/>
<OColor colvalue="-10764311"/>
<OColor colvalue="-10233949"/>
<OColor colvalue="-79765"/>
<OColor colvalue="-27427"/>
<OColor colvalue="-6887937"/>
<OColor colvalue="-6951467"/>
<OColor colvalue="-487545"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="年龄段" valueName="个数" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[年龄段]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="426" y="523" width="116" height="116"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  分公司\"]]></O>
<FRFont name="微软雅黑" style="0" size="96" foreground="-13151882"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[mCI"6P%bjohE4O.P`nk#3_cU&@[<ldR@5<r;iRcUQ+8DGVIhG_MJOc+536;jjdI-QVIhIAMB
Y[4.(N6EiX4\"MLp]Ae!l@V'b^\dNIsJfDa4qcHO5iFmlb(O)GQ,nZ\doieFL9`0ouF0R2Jh#
XHiEBn%3%4r2f/7jb.fkcTA3\U1u,=oP_JMXdoR)@o:me]AY,jO5jU1@j_@a.22!dtp/33SYM
XQVO*o1r+;7!.;a&a&Lrt95+s1C\:6uUeceSb6tju'..rc?uCL0df\?,K`$r:Jd6+Zg9u:1h
oXW.=..cKKA"+b8%o/#VIHBeQ<'&fE2on2V!_rqEVJ60hdp*FAs`mZ`VkL8Ludp/J%19T2=4
_Lq[J_D%S>rB*EbV6r.Y`S<cr$$>J(-QEDt'095!r`]AeZs48d[7TL+M[+Kl>Sl'?I9:Y+ROo
G]Ai^#i%$pnUTj3LMRJ36`Lt)d;jJ1NJPMg&6_uB:4W\67iX.5JVVXW,o"8e2,h?]AU>(f^Fb:
e?KX_59@*3o<93a+f2Hub<9?ZoW&=.`'5ED^jumQ<QC>,43&G+DrLO570rt;1eRdRM"8"(Fc
k7#+LVQ#!O,V2\7:rVL@*<$p;RIWZi!70*A/3puA,*ZCrB>_8a#J;UlZl&"a1]A)c/Ys=8Vtn
l6-TV.f2Ep@gcd\D"qT3$D;nr[trEn#A:;-P$E@_jUgZnI60g0H9FZF[;1</f$?Zj'.8?\S\
.VemJh`Ae*K5S"88+m1IohPerJd2c7H=R_!0]A-hu*k'HQO=Z)u.(*3!ikVgE+&is#1lLD!U/
^bd:P8"G;6>hl_3lZ_l876Rimf=_$+fWRdkqG#\Fg6_,6Frg5@F0Pn[NImkg<&$=K/;4+(#;
;DkcKR;l,qkJOVr8j#=0iATq:;Mi)jgXcoRq`@SL&JoQ!f]A%QrF.&8%)Zq)KoZ=DP^+m+VAW
G`J?IMRcIN]AMg2f.GaHnF<)+;P0K,rN#NJEOoep5iC-EUZB[pT+8]A?EkYOM*di*Vhm;t2nQr
WdFeWe=S@g@K6Ym@$PAGOJh;J.d/sBCqmD--dQO_l=I$qYZ[,J`up"jGap",X%4nM3!EqsS3
3pR%7)#&-Wi_6HO/O<aFn2U=,auYefUuiMOZ^sa<mTe&uH]A5AO]A@NJWEeOBG-=+'B.UcD?Qr
&h\Uf0tcdGn0k=$<I0Q\RGN!YYhdii@<Y<_02(HX:@'[$eGcCKPFLp%0S=o&Zo.J\[Y^<MYH
SIJ#/2E<5k=gZF2R8Qd"qA*V01j<F06*G`jhTHQG7V'LIJ#8/m034Wb(f-,;-X3fr_]AY6Aji
)d_kT!E[A0(TWJ2TKIpX66-N.F8c>qj<l9%:@&W>^QH2rN,4H8HG7=hK+g92[i:WCo0,[7,t
hO0gN1g13c-X\HML'J8Y__?-3&2`V\rsjDWrSnFN/Yg#^JKg@INuH@925c7:Nn4_NU"V=]A]A:
nop$r.R%2<<NA>OoWh"\qm',cd%l`qo5j;O:7Dqj/lE%#rAd6%Bo00@GKcAMZG\Dqg5C8B;`
KcbmTW(L+s*UNTM@K65#I0ThgMGXfX7<ckXY6UOb:+(:*dVl,864Q-]A9h+;aiO'_DtVQ<P\b
rMKWg!cbo#tr&/LXPdSQJI1DHf>eK(]AOWh=d?=\6d)_:mq$JH80cQ[s'\6-C3:cnSG[t"8Qe
'Rk1Z!e.jS"8U1j<*&ln`.mGY(k+8X/f2_"*.id#ZBQ]AO+IW0XK^NC<PTWMgCG9lR>6>@60_
a]A+sXgD8@5p/hR8mg%%DXMe*@35H6rJS8L`b8?Z9VA;!.&ZnJP5FLaRX5())#:>+Wa2:3>lb
21/-JEe45;R;;)NM0?--8ZbS&$%9H&(!n\=l5T<d2?`_m-"=Nf\,3Fa\luadc"hgc4V?'sD"
9Iqnk9`^=`KJ@[0gB\GDo/#nj)L4f-^Db55k^'F!!p9?B=)!<Dcl&\K2/9VT1"&CYB^.(F0;
l)5Ds]A]A?>M]A:WPB8Y%s$j$O=,lqe3B8dWCl&[#ARf,PeBI9^DLM1'ff$VU>b_FNHD+0_WSW'
(o[9^nbi#*L#.@F2CZ,NCt'L3X,%Q!O8N.EM_NWdQ$)$-$#6fkf6lBG.?q=/!j.3=%k7;V+$
=O&O]A$96!!'\U[0ROne(J+SaA-V94X;%0+gX=W+i6]A'tg%3]AW)od1%GMgOpQ((:VRA4LQSFp
5gN$ukjlSL_;hWcF=Wjsq(KMR`m-s!^E&e(OKe-A,=QQROA+pA`=KZMC1H0,8ZWFkEQLr:rk
*;FY'8>F:@ZX"M=^4>DOD_p4?4qp480El)l-+f;-`TPS"?1G1qCX6.I^@HP)kiJgSJ(DUL/V
5XrchM>7Vtn)_+IY`>6#_]AU<R2Sup"bSg)_RK>miY.5r5=nr5B-W-B`KQo4o=I;KCtHiSM=r
nNiLiB)n"?7YtWDR,j9M]Ak?<A2KqUT_(P,A8hS^KG&e"\+pM*EFIO!6'hoU!l3/h^`DluhQY
"dgbb4IJ]A=r=/R)/kfUgF6iulHZqb"p#.`QdsYRX62QE,L%8'd'kH+81=YR/Eq.3rZ\\\,2q
7<,FZc527akPH5Y[+DDOA_1e.d38P/1H$aI]A+muI:`9L$frtUm7lY+mB_Bi@4E!G;YOqPmUS
_f%pLEp5L0CKN_b\i:Cgf#Q--s"9PY'CInitFuraO")7bs!EHWd)d[@V)13,6&[5n,cPKK3%
hod8TVML#+g',aOO.p^a%L7Q'tF,7K/Rp!^85M$fT@g)QZ7R@M`KMQ9J4l-Y$+g7sJ/QX,g2
O@j[ledXBYo*:eSEX=GVYA4+$XI=O$%6=fMG#f%Cu:eOK@)*SGQoV#'WVVqKB7hd%lW*UDe<
HR&$pWtQa9gd"!`sHp(Aj-k+rM,VjcrK!%I6Q`0$1]AFq3(0?dn'\N0D3MNpn%$Y[!W[SAO&&
?"+j'n,9'&lH3gG.EYVtOBKOZ9;6H$d*!VMa2Xsfk-W0!Tc15L=IK,Z+2Z@[<[O]AXACs-(l$
Z6^E%ra12?&=:(;i_T[m\S1/:<uj@P7P$1DKQBX59)R+lATfE$@5e8l/Khkg1[-!7j]A,A_;b
-po=a`cFc[oNr"`>8?d]Acp#S[6ii-TF#[fF&\aB$'-Tl+2`f@h2J7fR58b*$#.Z7nIdo>?C<
CNWo;pa4cj@<Lh*9d<u$"uMW-0uW6LVh)I>0sNk_tPT9D"\XR`_gpP0Q&eSGp5GKYaj<@\1&
^pdWq"P,PRKX[YlEE04B99^jF"KB5u8%6lBk$GQKJ%(;=a7IA#1a*Apk=KrMZg);[qM;APSR
>!uEXf@V8%SMSlT-qsG2d8Q7tlnYWcO8(F1g/OOPeA0a),kR9B7a%PkA"Z"HFZK/>3uu*>H8
^A_k4't60\u:C&^HAI<DQ`U6t=t<'p4*5>+1LJYHX$"6b\q?J;#n3R4$0)lIES'-[9L8'dHa
$./K7).rn6FDA23X[fl=>Zp(mAmhTGeY!pG_kO;F7]AT?s%\pQF\Q^0]AmS!1Wl#Tkg"*Ql&_P
qG$S))Ht"K=TN_fcf]AY/h6XZ1dVF5VQK]A;G@f;D*2pR8bXd%]A@q6KK=:8SC!ib6Ac(W^g[S=
`boX7E/-(,\02?Q@p\[ngp*>!`FW<OBk,:C9r4pXRmV*X<bZfZa+FQ(Lc@`h+TjU5NO]AQ%Jq
ne1WdY=>i9DWHs:mQ;sWr<F>o`iPS4pnde"iCGl_))OsCO/G5SY-81rf2b7p:-T4^jjA25&C
\0#LY(45U>^7jI5*<Ip1A_3;$1,h`n!Yb:@R7,eaL%4qlk7?4fb9l@BQ%ILPdbXDW,Ln@<jG
dcu;%G?G'i!fBGDh[)2CG2iPi]A.<6F6$e!aFZ1KmqcF&&`N#4+W&>i">Y,qn'h+&k""&"kV\
&#.VCVm(+c+oMgWJcU/%aZ:5()u>O3FI(^2UdR@+_@6hqTNStcOMA8KLV0M'oE0bfPQQo\4o
cMfEfSRmoiK@TZ@dbdAfS/.'C^E;kkC4r[E2'6,&7679*8S38%orlXEDTg_=8=gkH/LCN>j1
TIPTXKNmKadit6po.c8*c8T\Rs4UF)h'hF:/=(%W@&droqV>frf?8U-JXJYbT8=W3L!asp(f
J&4pR(I6*VM@Z.F5VU8S3V8hHhn33k%!k6Xi2dKRo`BZ?M6>ok095fioT;cZD;F]AtNK*)XH:
p_)NNW'b%ApD=i0gJA"4b9Q0Jn!%YoNgms1&$bG)H74]AH[rY^AE*A6%q/BTKZ']A"(sMebCQV
&9KCC-[$;>05O+WH=5+\*\[V3FHc4-6RO2\)hKZj63e9&1*5CDG"]AN[#\C1LlE+/Kt2aKV\0
+n=ekO55ZBUc^&R@rZ4W9p-h\LUCF`5?_JUskj#&YLPL9$aQ+UFSiN;,P)3#%f=*HsNr0/!D
AZWoGF8Yd1RVF>,ZXV\p4Qf1OJAnrfD:bXiC^u#3`+0higIMm_3*kI7S8!;F<!t'Lq!oKD`>
uK^C5SBDK_TYO5hCO54SmMo+>58!i?D(f`7_QE,-\3LA^VK@kWZi2Ba&T&3ii.b&l!2P%,[-
NQS/N,bAI,H@%ijB#&)^k"75j9MNoHY`M80>%Qu2[)+6\CfI@+TA_sK\)nh8DTQ@`-Dr,FL3
Of5<I:^#\0*D6*OE]AmgHkrhYAc/o-(g)?,Bi=uJQ6ni$m>V9QI+^C"G]A&ok[#q1\aK*;(4\K
U/%*K-)'=%0%i?/:CDRGI?Tegn5&U/!gRdu-p1$CjtC5_Wt"M0"hq/40`"\P*dg!UGfgtL#u
T*Et3.Q!Nt=e0<'B#5#PB-#`.r5"rlDef6iO)lo!!@.^%6fE35PlU96X,1$TL$4R2p/Yp=ML
g`?$OPV\;#";lRS&)f8.#E^F`K$oURD'0fDTJ9li$X`=YEjm_aQej*RVTn#OQ*#m):U.440I
>ULKn(X66Eq;/%Vh4#n!r+Z??;&P$r!/fjq^',X<0q,jDNbYJe!1PtrfSr\Ra/UiA4g41^mV
:m@ZG!A$_Cr=iH;m)_p/=S,?F5FBPBotDa:VkAY]A88$_`I,I+#?q%>l7Bqs;=nd-KkW70^=o
6nGl^F!B5R&aC^<-/4<]AuC0utls3VKu#Z]AW+a27]A2V<&(p]ADBqdF1tO,6Olsdgaj!9cBJ,b>
o\_dX9aJO,?R-&pBI7'OLm!N<XI2SJ7A6>Vf"karA":LO"Y:5QA2H"N&5-ee+il9AB3u/`Ot
J<h%P2/$'h1+,B;Xe=0e63LKQjBl;PY$c9`FSX5pFEPe340N?&8;j2(e?Pa1\jS.?/V@6g7=
K>N-##G\B%GiJhlFafCP%+121`8io32o)00e6K>n'_c;/3!6=go9i5!^2?>$i$hrI\UU3=j&
(YS;m$f"e(k3.85>Uj99[8?#d+:BK%>EB4eqGWt,KX)i"H7N5;).O=cP:09U;2(f[-j&01H+
DZI"Ja@XE,"8M]AVOif$(T[`=ifgjTu(E73P46FrlOh?5r@D8dU*-_ZnJM9s<4/2h;BGSZlM+
Od@T/csqQdiNG7kda48tKj/F5p8c8=n8[TT.11q^WI`2E^5&c5cY_PEnpm@(^9_3h\<C:pQ%
hd36o>?"NiPXkiQLroDF1Ym&ZrkS&F_41)SLMVqP0\NVX[oT&R(+AVoXVS[7VJ!"[j4Q5D!O
/Irjq'=ATh5\t4i)FIJ[iXZ]A\rHZ"\D-O.+1\_m\R4"QB^A!8BG>X,Vs@$ZX#khE%(.E3-14
kHi!+'F)VQ<P>4WOipTbNQ0MM:sPl6>#qo-=hW`pf?!JVKFH2PqPRc]Ab&5OnsROhpTdN%l5J
'0?X"HPgA51UPs(?AK]A#;hBDE4c0ChTe(`'*F$,U7#g[lW>8-52XNZb2:+gWWCmXsGPkKBL[
OaE&5UB9.sPh[InK+4[uh-qaM29rGWi?*OuIpCS'7%Xf/WKS#(/OVEo2Ui%2mm^Ks6<[DQ=o
^$(G-@0@<L#RpGf,Lff5LcFidXq_MW8n/M>QCe6R165X!5L`9ku<if:1kR[e1OrnYV$$R*^$
*9j#)+E5Z=(2D$.O3rI?_ZqXVS@!_bYRCi66aZ'C8L]A?C.<8Y#PUX6*#T;52f4-T2(Lr7'XU
o2@`2rCAt3do)8JEZjqb2*,A>`%8gWsV2.F&A+@.[4n+a]A@6FoFW:kJU;An>mi$(\`opb>X>
*YY"`bJRkP,;?%&?_5r$Wf3JpEOStiLj;r,bGJ]AM+rMf;0#m2gNq?YFErKd+ZGd:7J5'nU09
5H_@ZI8eoR!=fdaP!<uQI4AbjYIiCI?=VD48*-f/ndg-D?B__Z_h^!k*P6s'i1r*)VpkN`Fr
1iW@)(7sMaHu'i>"\roS$H0;+5DfJG-I%UV<9@4/KM&p;6;>PnQsT9E<7Gr$+VILXLlI`:RR
ik99ge?J)s9nm5KhX'J<kHfK[bm.n/s[b3-2BGq.79@67\[u)Ctj16DP_ptE1\n*b<<?E/)R
:F$qco9<^kbFiP47YkN^4bO,S8-/fB%?>XMYoH]AaMSB_I-!PS%0<$K@0_7X2.32=R7=eX[_j
'UjP=t,5!K/ALhn[_aN&plV?Z_aHhGSIJ?Ia@#@1VDSrb'2"aO7(k%HL1<bQY@ruco97LAQD
fkl5l*h2eNRC0(8_AGia,*N[1of6_fnC]AZXUbtFeY+TFJ8N/<p*/Fk7P0nI97+o;l4VL1#'B
mLAe-,uuQf%'t'Ts^-cJ`V"NqP1\DHr=*Qk;?X=!#%6*^mT*?Bm.JZe+X*]ApOAc]AWaZ?T/jm
`qt3mB."2'.!WW%4Cu9GcWYWNrK+Uo,c.E$NWS,+/J:rGJ:UjbY7fcYD]AHR:W!d?UZJ%IY,9
CcBW8JibHN^5XG)5*jSb4LoZC?0W@&&"Z-;ouOG]AB+eOoDnG[GAdAD)>,#F:5O6i[MpIqk=)
BX*<kr^Fa;[b-/LomBW5n?kIsU%IWir[dSbo]AVfG>UAlQ&to2l,13(ksRbrnA(H)E%)8ejJr
oQS)s*P*GpA+q/1H[Hn]A,QP`2E%=7"&A>mhBVo$$Jr&WGH%Jh;(<O;1BTSis8:&</b%Km/$U
Q]A1_;I4!0E.Cc;-+:IqGp;%"oI>trFHuH_6R7]AmuAa7CLM;+AmJAoLCZ4HFE4cHf>hsYgt>l
sRgjtODR?7<O]A,?",FLe\1-^h0[*p!iiMBsS^)#j*N7Dcqm0r>+&NOqq*?oMl=4'qLS<o:s@
.`"lor/12>[]Ag9]A7`?g)q7km?dO=?5>pi-k62a[0C/!)Q4JG;4PYS^U/M8cVUCFk(q`V#PEP
"L\.s!gkF7Ktgn\ZQmU]ALu=k-i>?ClFt[)I38oLj0>jruQsg]Ae&/rE`ch]A2E9e1:BQ/\C`S<
jQDU8*$-b)`d,<Z3-?m8d<hhiLGUf##I*2n44g[4_>(_6aHCYm[(RqPKSKX5NahWWMJmsI<O
O<N.n5dN(R]A+;(%JKT]A2e`D8%aMT;7NOLhT-h[[dlSY!MqN+@3[oQa`tnsHkEd/?C;L0bXF7
EHWqV!g`D59r4m[T.JnHuFnDj4dLassL-0e)6d:4[.mh`*f+?Pr8Y!K[m]Acbmka<)PF>0OGV
:>cX=%JsOcui?^rA2%.@OIR_B`J'bMjmXLeH+9SA4:8;r4^0XXXVjjr9O?1SB3Thhcq6Y\%-
R!c`]AcuI4[``"j;O8q-353f&fd6^.Q3=%#cotrRVYbI_Y@_mc+H,Zb:`3D,OYKrNi,;qbBG'
6'=MLhFcY4P\Jh^c?1HAG[mD#fCR*JJXj+CPK2%pe+<2*]AS=fmp58HVhHNjGa;.MN2l+TW7P
Sa*`E$rd)<%Si=QrNus%XbcgLC[OT1inD(j'OB*.L0Lc$\6@ejJPP;Y-n`FJc)<o7n*2qXC0
omO/"CI1+r7OsX3AoO2+.LkKY9a6;U6'[nF/do?C72mrH!'G`]ARQl5ek=p+6G"nRt4:k,!;.
[_[YA>N*?.`*qD_+T`g.OrCem,LLgc^gW6[hHe#XjZB>gD6Q:gcQ@"j!P>T9u*ZMbp1GBVFb
[NqW6$YKm/WVHVEhUomn6E?b#Rd@B"YKddn_&H5'c*P\s=Gg0-&b-33<BHfsF.)dPh+.&lQ9
Y5-SaQGEiba6gn.W1R?G\^U0=PSK;tL!+ReZg]ALn\c(X4\NT4%bK3\.2<Vfo(Z_=Z>a]Ae1>j
rf]AFW5>$-%!t^jK;>8mgY1$_52;'bKj6lR8l1Z#D,WP85ROB7,'V`Zn?]A)mIP)(T>4G@I<E,
%3CHH2`tiY:+UI;B"lb(]A<g#4T<cG5`B2pm=Yj[j!.pa$t3/&(ToFp*-W(`-Ol519q>.V;6*
i,%GR=$Wj%PQ.m1b[Ip!iSVUM8Uu66N!.ASbW*=j>Nc')":DbRU2U<bbW+K6kU*__L$N5UP@
a"Yn6f=CY_Cp4OWQBN=0&X;nqkrHUe?PO7_Q"D6I:NAq\g1m[SIZ-NqlE?u(1frlY*X"G0$+
;`3FAOaZU+O)Y``GgiiD6I2kPEl?<W]AC(\pCD(HX8e[(:/uk4:jG`t<9W$+!I[7N#gi#u*D^
OKK<R#)u>?%n7IkJAm>q)6PpnnVY"*h-%g]AEqBFkFML,8/tX7Q:ft/6.]AjmRuC"46c@a#/T,
t)8/I55X#.0T!^J?oMdtm"2Y(7j+MFVMkqU3ph*NrXcF>-.t%e7TJo<t7Pki5k$XBmcR=NHR
<j3XE$cWobBE&2l4.?V6G$8jla(;V)Y;ARLt-S5OWGU&eonQA:;]A[KR%rW;3)i:0A2LMDFI7
Yf@,GCZC_pS3Q"U!TD[Mda^cTUiHA$\\XOF,XmCc@7aI)a#=R$sW2-'*P".r9SZd/8i'Ce^G
ftfMJP5J7so'j146K>(]AH\[*T1X:ZAAc;K$Tu3rXPU]Af1LCDQdnRBrs%Ia)sYa3rtSDnlTem
`c:H)c*56`\;E`t[l!/L_`?,sHdFjpKZpI#U2>E.\1t4I='%[=bY%]A97aAkiSRV>>mK"_2fd
q%>pY,!XW2:dbnUOdlr7?HrhSjV2*D>BL".8-/fj$.f1X%7iDbGbE6;*7trRs!^HCjR0F5l=
lsK-a<6(sU"sU!U$K#B>]A:&,BL$Y<\,J`[Esi6fZL/Q#FhERg$j.2))54jeQ/p^F-G,pog<L
>OE*u3)TsCG!3M_2[rM?`S9!+31A/B8SFD5ksA1;f8Br#c%31ftCclPmL//\He6k"'n$6)D1
>eao@l%JQsf?[2mohtM,TksV`hZ`FaBoe_Z%mL]AijXFcGWug*]AJ<0[PB-/?t9;Z`UI%WDlQl
po$YOGeo.75WTJlfjHoi2gGhH!7.q*L^-78_NFfA38e%IrK4l30c!+lDJ$RTCTLaY(LbMBb#
:V"buO`Q@Jt,Zi1%dQhRUbFP&Kj6iFlgAq?"Nu8OQ_6'17'Q*9ZeZt(nf.R>4!/b<Z2cFkem
-2gDF*juhE9*:Y#>NPS2fGP0J,AndB%%aP:UQf3/6XTI[_`sK$jc\\9^K+,F+oga>TLH3.e^
R@26TA9?,J%?U!ogF+=Pq=ac9>/l;@l1:59Ym]AB[jTn*cMM3BWO?9tC]A#0mu#igT20>\Se28
/U[!]Aa%\6Q3t0K@%6uR)dH!CWG&B'hc1c;`m::NTK7`r+-QRc,f]A+L)Ns5trhp$)^ed=)L!p
Q',A5lpdO1?<d>^3)-2bEtFC)@qW:Z$;[]A[BPTSoLSOM5*!'FK^**Fc!2#%:?<NLa2f3ag^t
)2;-CQPi#))F2##Mo";-6gSBM#d<++Vij$(kU?ArKon"(la7WA/W?K[H%/XmmZsZQiR?KbBa
mpK<1XLBf25gFg0QNW;"V'\fVX4$r!q:,5n>J>_dutVP9JBqP.EQD5fHc+39M;3B\#-T1-X[
7);`!dU+Sd\!N>_+WVjN&uX+`b;@:u#8lhas3ZQNq\#O[tO%s$L4dQ]AXiq/PP)6(`40@S(@N
/j:bG2qk/E]A'_F7ZAkVLGE9:gF^nR:7XJ-^0R$RhEttaTfj5$R&I)+$OTM'iQ_b8'b@N-a4;
**Uq1<B)J(CitY9=8N!&FIHerD3HYmBmlDo(XN_+PKc8q*E,JeYd."Bo_JE.b2Ee;oFH.-Rs
ngq;"#gJES'0\[@U9!WVu5##.268E,8/?b:)6Hi$RoR3jbS<-k25S,RF(e0TgS[iC6F!0-DB
B?n>r/&7AjBCAWBfIl1!PO#8%JH]A#fGg^+CG/I)S)Wa2gik`W7J0KVUg2;OSG$-r$H<kd&/)
/DiCn<PIV'>W&aI*@8&'Eo*Ak1N2%(^3Jc(6@pbt5s'iFoiNssntIm6/*dcFW`GdQN]AqN<A@
>Apa]A7Ff`!Tl<q]AT6KN/7P>2AoAPkP,K/oBpPXLXqJ"Geg596?.Q[tg&ea9`b3GD4XVChj-V
YX36Ja>QiB9W3PcQTq?39+MD5(hAo>b!X*;4>C.*gGNcOq/WE(>:?UOK5(enTD%rl"-n._+?
M:MW"^On@Ldd!2XjFIG.#^5d/#/dRn5iP1%kFHtufcF'0k;>/lA[:agG%9d4Tb:p!Oo8ULP5
M:T0co"q=:CJ5eYnW.OmY7#C?U4M)>_XS2)L<R@CSusecrd=)dK)7E@P?:Ds4&ju&k`435d,
@EX[LqOIjQ&5<55M1XoY&G\dWROU0]Al'dIr`<G^iQl-)JbYoR0KPCff%J0DhdA/"-KB$gp$:
2h%^V\59rsl&aQXrXr*rMLJJt-oQ^c[q@9KmV\t@elmYYCN8be(aSsF[#n5:aR/eXbJ<eNG]A
DfY%*D&"50?1QVVPlWQs3]Aek/RhI_<>rgE&q-O2)#-V&1r4mCGVjV%MhJV:eT-P9QmD]A>)!L
[2<pNjJ26DhjWmsBo5mO1;.t$c8*315LB#XE8BrrK4VDo-6SBh*1CY&25$'njm4[8U^Mie-=
a!SLl!Tei9u9laP,L]A;:QO;kf?T)YMl6>=8Jj)Qc$s'nHdlLUK:[U2qu:d5kA>:#:(m<PQS(
qW-_m;br)]A`,S_Y\3nlA$#mTsM1,JGAED7UDcfU-j9@h<8ZTBLbdh75!3bathKkhqYnRo5D)
6-QJV,8K>Mc^/]Aoo%M?QLF'FFZn#^Y7uBGed`\5[)Jr%c(jT=KdPH*C^;+R%YFs2eJWRXkDU
A4O&bJq0hR)(oFu@lLYE/HKqeJNToLf%%ehn:/oV,t,jPijW]A4%+mkt)Zi\qV*!_luhtq]A)3
1QY3b0r6g&XXL,*TFnIicqG4C7Wj2rl/fR3pIns8SbFjO(ZTipJSmrgUGUZmVL%`\!U/;M"6
!\RuL5Vc?F4!6Fpg%kViuq&l1(NHgkO76n%_['TVeK?_'Z[C<aKjJUT1sV^-.fSd3hN>M:8;
KK:NP4b,^+-!<<eGg'9k9O^*$.De"lcXh,U+OK$eDgkI1^r*7)`LF!%LU?0^j]Amt`0*UJOL4
=&UOV1W%>eBF#6jcQQHX.T&rg"C"mdPY<a^ls."l6OVRmg),FI=B-t-Lr5liGU`0-:6;=Pl.
Knj:k;chFLoYjn%'`1(BmNZ7k#Kql'ZJ+/9fhP<OQsGr-msAFe($CbB%IDoX^[\3d\jJS+_d
+)u&]A1mHS6EpitESSK(4DXq$Io<7L.iKQI"Qa*kV[WCDO6#/gMDq13YNMtOmDV8q<7MY5#dS
%RY[>qGHOR]A@#S-"WQW7[WoTF>F^VA*Z^S>Yo'."Y[W32o!o8QX3/]A(+0i+KiR`]Ad5SK(n@:
*g75@t$`t;+Q_B&6'=T`m8<OZ1*R!!#&1QJU#Ah=@6HUI`b%M;]AsoWI!^k$af@PTZT4=]Aqu.
<gZZT7CY0/8T-RO:i"ol1Rb&ibTFp*Y2?!]AfK$<`*C%`V:-X;7okDo/XXfal:JmAs)sJcYRD
sZ>$#T@eQX<gqA&<qKR1CDunrLofUCp*=,O2bT?]AZ`r?\73>Egh\\>p14T#n;C)M&`!_Z]A5o
CZ%[a\*TK,:X(1sX$FokrSC/,DDG)B2qt;Tj9e3,[C/@\VC6,\9R8X1[@0QBN!i-VTlf$#Y2
>"a<Lr+%c>ipE=1F+cg*eD%IYqEq$o859Yi0ALi"[Y9%H#K)?D2\BjE,_(SQl"Tl,A9e<At'
U`=_,_RE\_`--hX>0mKe_$!SrCYVD5sb5-n8O=&f+@%Oid<dG:\uN>\%uldFH9k5%kDTkO>^
:7*lM_H=kUcl8'A.Y1"$W2+Uh4$oS5rTVSEdp\)a>uLET]A-^8XQ)8d;6R/\[c090D&[(*=qS
KA^lrlgW]A>^E"h"5@2)kQPD(f+udk'kbPXZW8_Wu'aiS;6m=-p02RF<'K(mK%Mb6F^/?Y$H?
;^]AQp]AmbTgcSiK4'Bucl1joh-_!70Hq=>tV5g;O[)N<Vi8;$^cAO[$p"M2j"F0c1K!g7giY=
M,EUCY,;l'$CU+[H_a2;1.h<,JLq_5U@OThET^V4#D]A''fULM2B>l]AEZ3%lX1@KV+)l3^5'*
*;>ustUWL&ofWYuW'C%-H0qd#*0AeE",TU&i3b%.19VpOSuTHaMr`cS8;Fk&Au=aG^MUdTFH
oNGTMOOa^/_.qXN^ib#K0@>Y6eO%W?GuHTCqJ$B"p1ah,NgNCpTqGU/G@-N8"0P>8s4uRVQE
[U0iSLloL\$LQLnkh:'>@5<F0dfkgT$Z18Qf_g!%c@]A%I91]Al`mMVD'+WVbL\T7$DEpm3GP(
$iFH4JV=!WZXi!Mp&:j4Z=4@Jl;5gq2>auE%-!.L)9#;AD0Xs<3(lV)X6MY%-9QD$25ABUe>
i#22kEE^B';8\\D8#n@D!TGje^J;\ZH^c1s/AKU3E/CZSH-[Ndk,34CHL'HY=m_p.?gZRpYB
n/bs8$if<"ffBTJ'!%HDc.rFp+gm:gBBUWT_.VHfHHI\@RMZ+>n/CCdr2G<]A_fc@MPDIJPSf
WK4OZUeWBoBR!*(3':$4Uu.WiqcMR!$#N1i?RKTu/UU(G\Z7BjH&^G]A]A^A,sG'<gd^27gf)E
46\5eI$Tb'm$[AnGo^UoX9c4f69S$SpD0Wc/p3l$&gQ]A2a^-[_?mggT>a4W`4q:KPV@M1ED?
G"$q9t8>=kbLe,.gq/$6LLdPcQfS,o336iT,f9O5(%?Oia6pe._%)]ASe@=sV1I>o[$JO&Zh6
^#:BT%W*t>#C)Hq,jbFV^.Xf%_tL^k;K4q!k1$g>d^-*/^:cFSK*t#!`PqK,%g!)=%mle<$l
rPg_ajUb3oUc_kU"t`u5Y3lYfRO2FJX6#;c5o$omAHK"UWlAc/d56F(7arP7'BB:CjZ-jr#h
Wjp#T,J8Vh1"==TGTT+e@6,@]A[$L2f[K9k'`QcnG5;0alr8u%/L'G"chKk#^"eJHDN#1'l,F
VdZfJVdFne]A?^X$[Y/qP[5URg*6>!9HLD9$fHOcf*;_A^$3QIgDT625_.XdG72fAGenX`JqG
5NbJ3/[C=o9`nHD"1thu.%MZuN>qu[0b'q'4&+b(cn=I>,A,NF$-KVkNlYB!E?N#2"q85.5>
7jL\\ZKZdHhc*:\I8.CP2/0qrVYpA?^oJE<Y#a:NcT%YSZHp$7'h2>L7&.WGZY@q#5mJW6V)
$!q4U$tDZ.s?q=_VQ"-<KT=W.#\OKI;NkEgU_5?,p,+9-Sto@eMBU<jK<4pL#MJ[rsR`[]Aca
@:`!s_j/-^kei"(jO\4\jhm(=DnY,2..rj*nW@jd^d8m?dJsl$=g2Fl5iC>V]AV[9p8o+I/['
mjHa?4(mBnj(;2T%jd9o-Ulb_2oi$WaW"rR%'N7]AOg,XOMann<`L]A[Es#ircg5m%_.5K/lea
t&qCtd+$r"l^9RC<6.<FB"k:;`]At`[fA+9EnC\mUYUmm:pS1h]AX@'fBcc$9saS!9&mr]A0PMe
o4r"_CB,GP5=qY@>!+AW,B>9a9R_XY&5LoQic**a.F"\]A/,emOfdmJ/_u^OH2+88aZ%Ka`7P
%,^%GGg_Slc<QdT?Bo#3HV`YH;D]A>No2P,`C3dc@a&frRE8iTbo*UeuHh&qdE_/_&\)oE1SI
ob[P%*mQ7&!nNiIcp$ZP>\ukMPd+7`^l6I^E;tkrp4*PfkccGtDi9#9nVT0VM^"*$`]A[XRPK
_%MQ$-ieUZBXZbg.WgTOUDjL[b)RXhcuZl9&0%!b4S%^A5%"@s2m#2BJdkD*\=jq1@4Ha!oH
iS]A8,<?c#%0-U#O)ld1o-"KYIMr,+Y&`1KqcMZ&_%1OagDfWT?E8]AcObeNIqB^q)8Ym376SB
k0^hrE%N[''=d<`7Sf5o4n@fpr/-+'W^8D@@cHFgI1UoqZk-B)+,4Fchp!t&7;p?5@G0=RkR
'knt2FQ<^')=>JU3<Y[EWNLDCXG\p)@Wpa5q[Yb,N+3P:j[nFuT]A&iF&/"C_6sCG1Ts,Np=J
oJLdMC1EBP<25=01Ks#UjT1o*Oa_u`c)8Wo;iu>1AVKLCj3ZFs$g9!?F_d:lXmRH,%0cSa$5
q.!-Bl37.8%FM??sVa#ep-7+i[A$,Y(fkSM</#MJOqtO"!r^6lud5MA?]AWHM.AVa'UH%bF]AP
]A:\g_;<QXrKnOuDeq*M[CkWjpBT9,G+FmE8T0)VRG0b#]A-Xs3>`in";K7C#67=?6a)+#Eb#h
p`Nn6_eh+o^/sbA-SjmB[gs2i;J*i\?sp$_d(!p^A;unS3hr#pS@^%s7H^r;K+[)o!/nC$eR
qIpKp#6g9?T;G_Ea0&5Zf-n4=A<7n]AjW#\>Icf%o;f0?+?16:u[EotQe\fs&\9_DnEGAmFcE
ND@'UJ[p/WUX%K)Xo=)6CM#;Qc@Ga"8H2Q^b\;"FDp"71T2H?*da>ip!VLX^hEq8$&<T,P[X
7UMXTSTuW/VF5WJ^V*Rb`!+dY&,QDAi_#=7isfh^I(Lr2D2%[R%p`dec[A2VdEBW$@?-aJXm
q0lIgK!S9IO+qKFcJ'0t5ml<9r\trVQBHJ>R#q066>%R=Tj,2%I!u@ks491+/R9XH(`tof'=
BZ*GA!?m.e(dPO]Afm8@-s#;,ldYSc^m3r;]AG9uPQpKQT1r+6p^U?_CUq2t:BNm'dW/]AFZD^p
BPn2E.S+VW,N2]A!PeG[uL,>8n`rb$f98mfXeC<^>7!4Y_S@G\S,+ZSCN>':5i"%BJ=]AGD7S/
Skon25G"RfVOH<HkpYB.h6F]AnYCjSPm+aP$0La=#Cb<p,>`jE9s!#=0W+F:[e4ID2jl.f8N7
`7);Q6N`7_.!*B^:lR7`S4-,nEZ92&D56<'2_E!k^&XI:O5p[n&P\</K`JIA5"eOZ0&oVbB\
cJkjj>cN*Dp.r/gJhDXcerarWh_\a*[TbWC"r=aH172k`lQaAkD-!d>"r-)I4c6:ZJmNb'_i
VFq'R:C-R*(mF-Wf.()kg!n40&"1R]AD6hh:KF6nU>L9&k"ft"@*V`^f1#50@a/gT7EN1AML\
PZ]Asr<3D8^^5:XlT5:;B-%TK48>JM[2b^Ud,Qh`\Mb=^^A>.a=gB!<[W6,93:+?p8kG.<G,A
)t?-0`1<9h?7cQ\GpXW@&KLAk>^R('Z!C3_4`r"B$B#l@)Nkum^!Z%a2al[AmZ)8]A"gI2dS8
Qu`iG?fFRe!o#LqI`FL/WRM#G2GE^;'W-kTA2:U45T2M#J"b,Pjq&O6HULOTHb]A>u3_ZrZ!,
o,gqP,qtS2*he^s7;tlXQEf_ai2Z._rn9O9cBl2u#Gir^VIq6E]A^V?7ed1XOuB>He?r`Sa:K
)aSW`m95G=cUT/D3GL5mW"K1;]Akc1gFAh4YW_/o[euJmEW67~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[mCI"6P%bjohE4O.P`nk#3_cU&@[<ldR@5<r;iRcUQ+8DGVIhG_MJOc+536;jjdI-QVIhIAMB
Y[4.(N6EiX4\"MLp]Ae!l@V'b^\dNIsJfDa4qcHO5iFmlb(O)GQ,nZ\doieFL9`0ouF0R2Jh#
XHiEBn%3%4r2f/7jb.fkcTA3\U1u,=oP_JMXdoR)@o:me]AY,jO5jU1@j_@a.22!dtp/33SYM
XQVO*o1r+;7!.;a&a&Lrt95+s1C\:6uUeceSb6tju'..rc?uCL0df\?,K`$r:Jd6+Zg9u:1h
oXW.=..cKKA"+b8%o/#VIHBeQ<'&fE2on2V!_rqEVJ60hdp*FAs`mZ`VkL8Ludp/J%19T2=4
_Lq[J_D%S>rB*EbV6r.Y`S<cr$$>J(-QEDt'095!r`]AeZs48d[7TL+M[+Kl>Sl'?I9:Y+ROo
G]Ai^#i%$pnUTj3LMRJ36`Lt)d;jJ1NJPMg&6_uB:4W\67iX.5JVVXW,o"8e2,h?]AU>(f^Fb:
e?KX_59@*3o<93a+f2Hub<9?ZoW&=.`'5ED^jumQ<QC>,43&G+DrLO570rt;1eRdRM"8"(Fc
k7#+LVQ#!O,V2\7:rVL@*<$p;RIWZi!70*A/3puA,*ZCrB>_8a#J;UlZl&"a1]A)c/Ys=8Vtn
l6-TV.f2Ep@gcd\D"qT3$D;nr[trEn#A:;-P$E@_jUgZnI60g0H9FZF[;1</f$?Zj'.8?\S\
.VemJh`Ae*K5S"88+m1IohPerJd2c7H=R_!0]A-hu*k'HQO=Z)u.(*3!ikVgE+&is#1lLD!U/
^bd:P8"G;6>hl_3lZ_l876Rimf=_$+fWRdkqG#\Fg6_,6Frg5@F0Pn[NImkg<&$=K/;4+(#;
;DkcKR;l,qkJOVr8j#=0iATq:;Mi)jgXcoRq`@SL&JoQ!f]A%QrF.&8%)Zq)KoZ=DP^+m+VAW
G`J?IMRcIN]AMg2f.GaHnF<)+;P0K,rN#NJEOoep5iC-EUZB[pT+8]A?EkYOM*di*Vhm;t2nQr
WdFeWe=S@g@K6Ym@$PAGOJh;J.d/sBCqmD--dQO_l=I$qYZ[,J`up"jGap",X%4nM3!EqsS3
3pR%7)#&-Wi_6HO/O<aFn2U=,auYefUuiMOZ^sa<mTe&uH]A5AO]A@NJWEeOBG-=+'B.UcD?Qr
&h\Uf0tcdGn0k=$<I0Q\RGN!YYhdii@<Y<_02(HX:@'[$eGcCKPFLp%0S=o&Zo.J\[Y^<MYH
SIJ#/2E<5k=gZF2R8Qd"qA*V01j<F06*G`jhTHQG7V'LIJ#8/m034Wb(f-,;-X3fr_]AY6Aji
)d_kT!E[A0(TWJ2TKIpX66-N.F8c>qj<l9%:@&W>^QH2rN,4H8HG7=hK+g92[i:WCo0,[7,t
hO0gN1g13c-X\HML'J8Y__?-3&2`V\rsjDWrSnFN/Yg#^JKg@INuH@925c7:Nn4_NU"V=]A]A:
nop$r.R%2<<NA>OoWh"\qm',cd%l`qo5j;O:7Dqj/lE%#rAd6%Bo00@GKcAMZG\Dqg5C8B;`
KcbmTW(L+s*UNTM@K65#I0ThgMGXfX7<ckXY6UOb:+(:*dVl,864Q-]A9h+;aiO'_DtVQ<P\b
rMKWg!cbo#tr&/LXPdSQJI1DHf>eK(]AOWh=d?=\6d)_:mq$JH80cQ[s'\6-C3:cnSG[t"8Qe
'Rk1Z!e.jS"8U1j<*&ln`.mGY(k+8X/f2_"*.id#ZBQ]AO+IW0XK^NC<PTWMgCG9lR>6>@60_
a]A+sXgD8@5p/hR8mg%%DXMe*@35H6rJS8L`b8?Z9VA;!.&ZnJP5FLaRX5())#:>+Wa2:3>lb
21/-JEe45;R;;)NM0?--8ZbS&$%9H&(!n\=l5T<d2?`_m-"=Nf\,3Fa\luadc"hgc4V?'sD"
9Iqnk9`^=`KJ@[0gB\GDo/#nj)L4f-^Db55k^'F!!p9?B=)!<Dcl&\K2/9VT1"&CYB^.(F0;
l)5Ds]A]A?>M]A:WPB8Y%s$j$O=,lqe3B8dWCl&[#ARf,PeBI9^DLM1'ff$VU>b_FNHD+0_WSW'
(o[9^nbi#*L#.@F2CZ,NCt'L3X,%Q!O8N.EM_NWdQ$)$-$#6fkf6lBG.?q=/!j.3=%k7;V+$
=O&O]A$96!!'\U[0ROne(J+SaA-V94X;%0+gX=W+i6]A'tg%3]AW)od1%GMgOpQ((:VRA4LQSFp
5gN$ukjlSL_;hWcF=Wjsq(KMR`m-s!^E&e(OKe-A,=QQROA+pA`=KZMC1H0,8ZWFkEQLr:rk
*;FY'8>F:@ZX"M=^4>DOD_p4?4qp480El)l-+f;-`TPS"?1G1qCX6.I^@HP)kiJgSJ(DUL/V
5XrchM>7Vtn)_+IY`>6#_]AU<R2Sup"bSg)_RK>miY.5r5=nr5B-W-B`KQo4o=I;KCtHiSM=r
nNiLiB)n"?7YtWDR,j9M]Ak?<A2KqUT_(P,A8hS^KG&e"\+pM*EFIO!6'hoU!l3/h^`DluhQY
"dgbb4IJ]A=r=/R)/kfUgF6iulHZqb"p#.`QdsYRX62QE,L%8'd'kH+81=YR/Eq.3rZ\\\,2q
7<,FZc527akPH5Y[+DDOA_1e.d38P/1H$aI]A+muI:`9L$frtUm7lY+mB_Bi@4E!G;YOqPmUS
_f%pLEp5L0CKN_b\i:Cgf#Q--s"9PY'CInitFuraO")7bs!EHWd)d[@V)13,6&[5n,cPKK3%
hod8TVML#+g',aOO.p^a%L7Q'tF,7K/Rp!^85M$fT@g)QZ7R@M`KMQ9J4l-Y$+g7sJ/QX,g2
O@j[ledXBYo*:eSEX=GVYA4+$XI=O$%6=fMG#f%Cu:eOK@)*SGQoV#'WVVqKB7hd%lW*UDe<
HR&$pWtQa9gd"!`sHp(Aj-k+rM,VjcrK!%I6Q`0$1]AFq3(0?dn'\N0D3MNpn%$Y[!W[SAO&&
?"+j'n,9'&lH3gG.EYVtOBKOZ9;6H$d*!VMa2Xsfk-W0!Tc15L=IK,Z+2Z@[<[O]AXACs-(l$
Z6^E%ra12?&=:(;i_T[m\S1/:<uj@P7P$1DKQBX59)R+lATfE$@5e8l/Khkg1[-!7j]A,A_;b
-po=a`cFc[oNr"`>8?d]Acp#S[6ii-TF#[fF&\aB$'-Tl+2`f@h2J7fR58b*$#.Z7nIdo>?C<
CNWo;pa4cj@<Lh*9d<u$"uMW-0uW6LVh)I>0sNk_tPT9D"\XR`_gpP0Q&eSGp5GKYaj<@\1&
^pdWq"P,PRKX[YlEE04B99^jF"KB5u8%6lBk$GQKJ%(;=a7IA#1a*Apk=KrMZg);[qM;APSR
>!uEXf@V8%SMSlT-qsG2d8Q7tlnYWcO8(F1g/OOPeA0a),kR9B7a%PkA"Z"HFZK/>3uu*>H8
^A_k4't60\u:C&^HAI<DQ`U6t=t<'p4*5>+1LJYHX$"6b\q?J;#n3R4$0)lIES'-[9L8'dHa
$./K7).rn6FDA23X[fl=>Zp(mAmhTGeY!pG_kO;F7]AT?s%\pQF\Q^0]AmS!1Wl#Tkg"*Ql&_P
qG$S))Ht"K=TN_fcf]AY/h6XZ1dVF5VQK]A;G@f;D*2pR8bXd%]A@q6KK=:8SC!ib6Ac(W^g[S=
`boX7E/-(,\02?Q@p\[ngp*>!`FW<OBk,:C9r4pXRmV*X<bZfZa+FQ(Lc@`h+TjU5NO]AQ%Jq
ne1WdY=>i9DWHs:mQ;sWr<F>o`iPS4pnde"iCGl_))OsCO/G5SY-81rf2b7p:-T4^jjA25&C
\0#LY(45U>^7jI5*<Ip1A_3;$1,h`n!Yb:@R7,eaL%4qlk7?4fb9l@BQ%ILPdbXDW,Ln@<jG
dcu;%G?G'i!fBGDh[)2CG2iPi]A.<6F6$e!aFZ1KmqcF&&`N#4+W&>i">Y,qn'h+&k""&"kV\
&#.VCVm(+c+oMgWJcU/%aZ:5()u>O3FI(^2UdR@+_@6hqTNStcOMA8KLV0M'oE0bfPQQo\4o
cMfEfSRmoiK@TZ@dbdAfS/.'C^E;kkC4r[E2'6,&7679*8S38%orlXEDTg_=8=gkH/LCN>j1
TIPTXKNmKadit6po.c8*c8T\Rs4UF)h'hF:/=(%W@&droqV>frf?8U-JXJYbT8=W3L!asp(f
J&4pR(I6*VM@Z.F5VU8S3V8hHhn33k%!k6Xi2dKRo`BZ?M6>ok095fioT;cZD;F]AtNK*)XH:
p_)NNW'b%ApD=i0gJA"4b9Q0Jn!%YoNgms1&$bG)H74]AH[rY^AE*A6%q/BTKZ']A"(sMebCQV
&9KCC-[$;>05O+WH=5+\*\[V3FHc4-6RO2\)hKZj63e9&1*5CDG"]AN[#\C1LlE+/Kt2aKV\0
+n=ekO55ZBUc^&R@rZ4W9p-h\LUCF`5?_JUskj#&YLPL9$aQ+UFSiN;,P)3#%f=*HsNr0/!D
AZWoGF8Yd1RVF>,ZXV\p4Qf1OJAnrfD:bXiC^u#3`+0higIMm_3*kI7S8!;F<!t'Lq!oKD`>
uK^C5SBDK_TYO5hCO54SmMo+>58!i?D(f`7_QE,-\3LA^VK@kWZi2Ba&T&3ii.b&l!2P%,[-
NQS/N,bAI,H@%ijB#&)^k"75j9MNoHY`M80>%Qu2[)+6\CfI@+TA_sK\)nh8DTQ@`-Dr,FL3
Of5<I:^#\0*D6*OE]AmgHkrhYAc/o-(g)?,Bi=uJQ6ni$m>V9QI+^C"G]A&ok[#q1\aK*;(4\K
U/%*K-)'=%0%i?/:CDRGI?Tegn5&U/!gRdu-p1$CjtC5_Wt"M0"hq/40`"\P*dg!UGfgtL#u
T*Et3.Q!Nt=e0<'B#5#PB-#`.r5"rlDef6iO)lo!!@.^%6fE35PlU96X,1$TL$4R2p/Yp=ML
g`?$OPV\;#";lRS&)f8.#E^F`K$oURD'0fDTJ9li$X`=YEjm_aQej*RVTn#OQ*#m):U.440I
>ULKn(X66Eq;/%Vh4#n!r+Z??;&P$r!/fjq^',X<0q,jDNbYJe!1PtrfSr\Ra/UiA4g41^mV
:m@ZG!A$_Cr=iH;m)_p/=S,?F5FBPBotDa:VkAY]A88$_`I,I+#?q%>l7Bqs;=nd-KkW70^=o
6nGl^F!B5R&aC^<-/4<]AuC0utls3VKu#Z]AW+a27]A2V<&(p]ADBqdF1tO,6Olsdgaj!9cBJ,b>
o\_dX9aJO,?R-&pBI7'OLm!N<XI2SJ7A6>Vf"karA":LO"Y:5QA2H"N&5-ee+il9AB3u/`Ot
J<h%P2/$'h1+,B;Xe=0e63LKQjBl;PY$c9`FSX5pFEPe340N?&8;j2(e?Pa1\jS.?/V@6g7=
K>N-##G\B%GiJhlFafCP%+121`8io32o)00e6K>n'_c;/3!6=go9i5!^2?>$i$hrI\UU3=j&
(YS;m$f"e(k3.85>Uj99[8?#d+:BK%>EB4eqGWt,KX)i"H7N5;).O=cP:09U;2(f[-j&01H+
DZI"Ja@XE,"8M]AVOif$(T[`=ifgjTu(E73P46FrlOh?5r@D8dU*-_ZnJM9s<4/2h;BGSZlM+
Od@T/csqQdiNG7kda48tKj/F5p8c8=n8[TT.11q^WI`2E^5&c5cY_PEnpm@(^9_3h\<C:pQ%
hd36o>?"NiPXkiQLroDF1Ym&ZrkS&F_41)SLMVqP0\NVX[oT&R(+AVoXVS[7VJ!"[j4Q5D!O
/Irjq'=ATh5\t4i)FIJ[iXZ]A\rHZ"\D-O.+1\_m\R4"QB^A!8BG>X,Vs@$ZX#khE%(.E3-14
kHi!+'F)VQ<P>4WOipTbNQ0MM:sPl6>#qo-=hW`pf?!JVKFH2PqPRc]Ab&5OnsROhpTdN%l5J
'0?X"HPgA51UPs(?AK]A#;hBDE4c0ChTe(`'*F$,U7#g[lW>8-52XNZb2:+gWWCmXsGPkKBL[
OaE&5UB9.sPh[InK+4[uh-qaM29rGWi?*OuIpCS'7%Xf/WKS#(/OVEo2Ui%2mm^Ks6<[DQ=o
^$(G-@0@<L#RpGf,Lff5LcFidXq_MW8n/M>QCe6R165X!5L`9ku<if:1kR[e1OrnYV$$R*^$
*9j#)+E5Z=(2D$.O3rI?_ZqXVS@!_bYRCi66aZ'C8L]A?C.<8Y#PUX6*#T;52f4-T2(Lr7'XU
o2@`2rCAt3do)8JEZjqb2*,A>`%8gWsV2.F&A+@.[4n+a]A@6FoFW:kJU;An>mi$(\`opb>X>
*YY"`bJRkP,;?%&?_5r$Wf3JpEOStiLj;r,bGJ]AM+rMf;0#m2gNq?YFErKd+ZGd:7J5'nU09
5H_@ZI8eoR!=fdaP!<uQI4AbjYIiCI?=VD48*-f/ndg-D?B__Z_h^!k*P6s'i1r*)VpkN`Fr
1iW@)(7sMaHu'i>"\roS$H0;+5DfJG-I%UV<9@4/KM&p;6;>PnQsT9E<7Gr$+VILXLlI`:RR
ik99ge?J)s9nm5KhX'J<kHfK[bm.n/s[b3-2BGq.79@67\[u)Ctj16DP_ptE1\n*b<<?E/)R
:F$qco9<^kbFiP47YkN^4bO,S8-/fB%?>XMYoH]AaMSB_I-!PS%0<$K@0_7X2.32=R7=eX[_j
'UjP=t,5!K/ALhn[_aN&plV?Z_aHhGSIJ?Ia@#@1VDSrb'2"aO7(k%HL1<bQY@ruco97LAQD
fkl5l*h2eNRC0(8_AGia,*N[1of6_fnC]AZXUbtFeY+TFJ8N/<p*/Fk7P0nI97+o;l4VL1#'B
mLAe-,uuQf%'t'Ts^-cJ`V"NqP1\DHr=*Qk;?X=!#%6*^mT*?Bm.JZe+X*]ApOAc]AWaZ?T/jm
`qt3mB."2'.!WW%4Cu9GcWYWNrK+Uo,c.E$NWS,+/J:rGJ:UjbY7fcYD]AHR:W!d?UZJ%IY,9
CcBW8JibHN^5XG)5*jSb4LoZC?0W@&&"Z-;ouOG]AB+eOoDnG[GAdAD)>,#F:5O6i[MpIqk=)
BX*<kr^Fa;[b-/LomBW5n?kIsU%IWir[dSbo]AVfG>UAlQ&to2l,13(ksRbrnA(H)E%)8ejJr
oQS)s*P*GpA+q/1H[Hn]A,QP`2E%=7"&A>mhBVo$$Jr&WGH%Jh;(<O;1BTSis8:&</b%Km/$U
Q]A1_;I4!0E.Cc;-+:IqGp;%"oI>trFHuH_6R7]AmuAa7CLM;+AmJAoLCZ4HFE4cHf>hsYgt>l
sRgjtODR?7<O]A,?",FLe\1-^h0[*p!iiMBsS^)#j*N7Dcqm0r>+&NOqq*?oMl=4'qLS<o:s@
.`"lor/12>[]Ag9]A7`?g)q7km?dO=?5>pi-k62a[0C/!)Q4JG;4PYS^U/M8cVUCFk(q`V#PEP
"L\.s!gkF7Ktgn\ZQmU]ALu=k-i>?ClFt[)I38oLj0>jruQsg]Ae&/rE`ch]A2E9e1:BQ/\C`S<
jQDU8*$-b)`d,<Z3-?m8d<hhiLGUf##I*2n44g[4_>(_6aHCYm[(RqPKSKX5NahWWMJmsI<O
O<N.n5dN(R]A+;(%JKT]A2e`D8%aMT;7NOLhT-h[[dlSY!MqN+@3[oQa`tnsHkEd/?C;L0bXF7
EHWqV!g`D59r4m[T.JnHuFnDj4dLassL-0e)6d:4[.mh`*f+?Pr8Y!K[m]Acbmka<)PF>0OGV
:>cX=%JsOcui?^rA2%.@OIR_B`J'bMjmXLeH+9SA4:8;r4^0XXXVjjr9O?1SB3Thhcq6Y\%-
R!c`]AcuI4[``"j;O8q-353f&fd6^.Q3=%#cotrRVYbI_Y@_mc+H,Zb:`3D,OYKrNi,;qbBG'
6'=MLhFcY4P\Jh^c?1HAG[mD#fCR*JJXj+CPK2%pe+<2*]AS=fmp58HVhHNjGa;.MN2l+TW7P
Sa*`E$rd)<%Si=QrNus%XbcgLC[OT1inD(j'OB*.L0Lc$\6@ejJPP;Y-n`FJc)<o7n*2qXC0
omO/"CI1+r7OsX3AoO2+.LkKY9a6;U6'[nF/do?C72mrH!'G`]ARQl5ek=p+6G"nRt4:k,!;.
[_[YA>N*?.`*qD_+T`g.OrCem,LLgc^gW6[hHe#XjZB>gD6Q:gcQ@"j!P>T9u*ZMbp1GBVFb
[NqW6$YKm/WVHVEhUomn6E?b#Rd@B"YKddn_&H5'c*P\s=Gg0-&b-33<BHfsF.)dPh+.&lQ9
Y5-SaQGEiba6gn.W1R?G\^U0=PSK;tL!+ReZg]ALn\c(X4\NT4%bK3\.2<Vfo(Z_=Z>a]Ae1>j
rf]AFW5>$-%!t^jK;>8mgY1$_52;'bKj6lR8l1Z#D,WP85ROB7,'V`Zn?]A)mIP)(T>4G@I<E,
%3CHH2`tiY:+UI;B"lb(]A<g#4T<cG5`B2pm=Yj[j!.pa$t3/&(ToFp*-W(`-Ol519q>.V;6*
i,%GR=$Wj%PQ.m1b[Ip!iSVUM8Uu66N!.ASbW*=j>Nc')":DbRU2U<bbW+K6kU*__L$N5UP@
a"Yn6f=CY_Cp4OWQBN=0&X;nqkrHUe?PO7_Q"D6I:NAq\g1m[SIZ-NqlE?u(1frlY*X"G0$+
;`3FAOaZU+O)Y``GgiiD6I2kPEl?<W]AC(\pCD(HX8e[(:/uk4:jG`t<9W$+!I[7N#gi#u*D^
OKK<R#)u>?%n7IkJAm>q)6PpnnVY"*h-%g]AEqBFkFML,8/tX7Q:ft/6.]AjmRuC"46c@a#/T,
t)8/I55X#.0T!^J?oMdtm"2Y(7j+MFVMkqU3ph*NrXcF>-.t%e7TJo<t7Pki5k$XBmcR=NHR
<j3XE$cWobBE&2l4.?V6G$8jla(;V)Y;ARLt-S5OWGU&eonQA:;]A[KR%rW;3)i:0A2LMDFI7
Yf@,GCZC_pS3Q"U!TD[Mda^cTUiHA$\\XOF,XmCc@7aI)a#=R$sW2-'*P".r9SZd/8i'Ce^G
ftfMJP5J7so'j146K>(]AH\[*T1X:ZAAc;K$Tu3rXPU]Af1LCDQdnRBrs%Ia)sYa3rtSDnlTem
`c:H)c*56`\;E`t[l!/L_`?,sHdFjpKZpI#U2>E.\1t4I='%[=bY%]A97aAkiSRV>>mK"_2fd
q%>pY,!XW2:dbnUOdlr7?HrhSjV2*D>BL".8-/fj$.f1X%7iDbGbE6;*7trRs!^HCjR0F5l=
lsK-a<6(sU"sU!U$K#B>]A:&,BL$Y<\,J`[Esi6fZL/Q#FhERg$j.2))54jeQ/p^F-G,pog<L
>OE*u3)TsCG!3M_2[rM?`S9!+31A/B8SFD5ksA1;f8Br#c%31ftCclPmL//\He6k"'n$6)D1
>eao@l%JQsf?[2mohtM,TksV`hZ`FaBoe_Z%mL]AijXFcGWug*]AJ<0[PB-/?t9;Z`UI%WDlQl
po$YOGeo.75WTJlfjHoi2gGhH!7.q*L^-78_NFfA38e%IrK4l30c!+lDJ$RTCTLaY(LbMBb#
:V"buO`Q@Jt,Zi1%dQhRUbFP&Kj6iFlgAq?"Nu8OQ_6'17'Q*9ZeZt(nf.R>4!/b<Z2cFkem
-2gDF*juhE9*:Y#>NPS2fGP0J,AndB%%aP:UQf3/6XTI[_`sK$jc\\9^K+,F+oga>TLH3.e^
R@26TA9?,J%?U!ogF+=Pq=ac9>/l;@l1:59Ym]AB[jTn*cMM3BWO?9tC]A#0mu#igT20>\Se28
/U[!]Aa%\6Q3t0K@%6uR)dH!CWG&B'hc1c;`m::NTK7`r+-QRc,f]A+L)Ns5trhp$)^ed=)L!p
Q',A5lpdO1?<d>^3)-2bEtFC)@qW:Z$;[]A[BPTSoLSOM5*!'FK^**Fc!2#%:?<NLa2f3ag^t
)2;-CQPi#))F2##Mo";-6gSBM#d<++Vij$(kU?ArKon"(la7WA/W?K[H%/XmmZsZQiR?KbBa
mpK<1XLBf25gFg0QNW;"V'\fVX4$r!q:,5n>J>_dutVP9JBqP.EQD5fHc+39M;3B\#-T1-X[
7);`!dU+Sd\!N>_+WVjN&uX+`b;@:u#8lhas3ZQNq\#O[tO%s$L4dQ]AXiq/PP)6(`40@S(@N
/j:bG2qk/E]A'_F7ZAkVLGE9:gF^nR:7XJ-^0R$RhEttaTfj5$R&I)+$OTM'iQ_b8'b@N-a4;
**Uq1<B)J(CitY9=8N!&FIHerD3HYmBmlDo(XN_+PKc8q*E,JeYd."Bo_JE.b2Ee;oFH.-Rs
ngq;"#gJES'0\[@U9!WVu5##.268E,8/?b:)6Hi$RoR3jbS<-k25S,RF(e0TgS[iC6F!0-DB
B?n>r/&7AjBCAWBfIl1!PO#8%JH]A#fGg^+CG/I)S)Wa2gik`W7J0KVUg2;OSG$-r$H<kd&/)
/DiCn<PIV'>W&aI*@8&'Eo*Ak1N2%(^3Jc(6@pbt5s'iFoiNssntIm6/*dcFW`GdQN]AqN<A@
>Apa]A7Ff`!Tl<q]AT6KN/7P>2AoAPkP,K/oBpPXLXqJ"Geg596?.Q[tg&ea9`b3GD4XVChj-V
YX36Ja>QiB9W3PcQTq?39+MD5(hAo>b!X*;4>C.*gGNcOq/WE(>:?UOK5(enTD%rl"-n._+?
M:MW"^On@Ldd!2XjFIG.#^5d/#/dRn5iP1%kFHtufcF'0k;>/lA[:agG%9d4Tb:p!Oo8ULP5
M:T0co"q=:CJ5eYnW.OmY7#C?U4M)>_XS2)L<R@CSusecrd=)dK)7E@P?:Ds4&ju&k`435d,
@EX[LqOIjQ&5<55M1XoY&G\dWROU0]Al'dIr`<G^iQl-)JbYoR0KPCff%J0DhdA/"-KB$gp$:
2h%^V\59rsl&aQXrXr*rMLJJt-oQ^c[q@9KmV\t@elmYYCN8be(aSsF[#n5:aR/eXbJ<eNG]A
DfY%*D&"50?1QVVPlWQs3]Aek/RhI_<>rgE&q-O2)#-V&1r4mCGVjV%MhJV:eT-P9QmD]A>)!L
[2<pNjJ26DhjWmsBo5mO1;.t$c8*315LB#XE8BrrK4VDo-6SBh*1CY&25$'njm4[8U^Mie-=
a!SLl!Tei9u9laP,L]A;:QO;kf?T)YMl6>=8Jj)Qc$s'nHdlLUK:[U2qu:d5kA>:#:(m<PQS(
qW-_m;br)]A`,S_Y\3nlA$#mTsM1,JGAED7UDcfU-j9@h<8ZTBLbdh75!3bathKkhqYnRo5D)
6-QJV,8K>Mc^/]Aoo%M?QLF'FFZn#^Y7uBGed`\5[)Jr%c(jT=KdPH*C^;+R%YFs2eJWRXkDU
A4O&bJq0hR)(oFu@lLYE/HKqeJNToLf%%ehn:/oV,t,jPijW]A4%+mkt)Zi\qV*!_luhtq]A)3
1QY3b0r6g&XXL,*TFnIicqG4C7Wj2rl/fR3pIns8SbFjO(ZTipJSmrgUGUZmVL%`\!U/;M"6
!\RuL5Vc?F4!6Fpg%kViuq&l1(NHgkO76n%_['TVeK?_'Z[C<aKjJUT1sV^-.fSd3hN>M:8;
KK:NP4b,^+-!<<eGg'9k9O^*$.De"lcXh,U+OK$eDgkI1^r*7)`LF!%LU?0^j]Amt`0*UJOL4
=&UOV1W%>eBF#6jcQQHX.T&rg"C"mdPY<a^ls."l6OVRmg),FI=B-t-Lr5liGU`0-:6;=Pl.
Knj:k;chFLoYjn%'`1(BmNZ7k#Kql'ZJ+/9fhP<OQsGr-msAFe($CbB%IDoX^[\3d\jJS+_d
+)u&]A1mHS6EpitESSK(4DXq$Io<7L.iKQI"Qa*kV[WCDO6#/gMDq13YNMtOmDV8q<7MY5#dS
%RY[>qGHOR]A@#S-"WQW7[WoTF>F^VA*Z^S>Yo'."Y[W32o!o8QX3/]A(+0i+KiR`]Ad5SK(n@:
*g75@t$`t;+Q_B&6'=T`m8<OZ1*R!!#&1QJU#Ah=@6HUI`b%M;]AsoWI!^k$af@PTZT4=]Aqu.
<gZZT7CY0/8T-RO:i"ol1Rb&ibTFp*Y2?!]AfK$<`*C%`V:-X;7okDo/XXfal:JmAs)sJcYRD
sZ>$#T@eQX<gqA&<qKR1CDunrLofUCp*=,O2bT?]AZ`r?\73>Egh\\>p14T#n;C)M&`!_Z]A5o
CZ%[a\*TK,:X(1sX$FokrSC/,DDG)B2qt;Tj9e3,[C/@\VC6,\9R8X1[@0QBN!i-VTlf$#Y2
>"a<Lr+%c>ipE=1F+cg*eD%IYqEq$o859Yi0ALi"[Y9%H#K)?D2\BjE,_(SQl"Tl,A9e<At'
U`=_,_RE\_`--hX>0mKe_$!SrCYVD5sb5-n8O=&f+@%Oid<dG:\uN>\%uldFH9k5%kDTkO>^
:7*lM_H=kUcl8'A.Y1"$W2+Uh4$oS5rTVSEdp\)a>uLET]A-^8XQ)8d;6R/\[c090D&[(*=qS
KA^lrlgW]A>^E"h"5@2)kQPD(f+udk'kbPXZW8_Wu'aiS;6m=-p02RF<'K(mK%Mb6F^/?Y$H?
;^]AQp]AmbTgcSiK4'Bucl1joh-_!70Hq=>tV5g;O[)N<Vi8;$^cAO[$p"M2j"F0c1K!g7giY=
M,EUCY,;l'$CU+[H_a2;1.h<,JLq_5U@OThET^V4#D]A''fULM2B>l]AEZ3%lX1@KV+)l3^5'*
*;>ustUWL&ofWYuW'C%-H0qd#*0AeE",TU&i3b%.19VpOSuTHaMr`cS8;Fk&Au=aG^MUdTFH
oNGTMOOa^/_.qXN^ib#K0@>Y6eO%W?GuHTCqJ$B"p1ah,NgNCpTqGU/G@-N8"0P>8s4uRVQE
[U0iSLloL\$LQLnkh:'>@5<F0dfkgT$Z18Qf_g!%c@]A%I91]Al`mMVD'+WVbL\T7$DEpm3GP(
$iFH4JV=!WZXi!Mp&:j4Z=4@Jl;5gq2>auE%-!.L)9#;AD0Xs<3(lV)X6MY%-9QD$25ABUe>
i#22kEE^B';8\\D8#n@D!TGje^J;\ZH^c1s/AKU3E/CZSH-[Ndk,34CHL'HY=m_p.?gZRpYB
n/bs8$if<"ffBTJ'!%HDc.rFp+gm:gBBUWT_.VHfHHI\@RMZ+>n/CCdr2G<]A_fc@MPDIJPSf
WK4OZUeWBoBR!*(3':$4Uu.WiqcMR!$#N1i?RKTu/UU(G\Z7BjH&^G]A]A^A,sG'<gd^27gf)E
46\5eI$Tb'm$[AnGo^UoX9c4f69S$SpD0Wc/p3l$&gQ]A2a^-[_?mggT>a4W`4q:KPV@M1ED?
G"$q9t8>=kbLe,.gq/$6LLdPcQfS,o336iT,f9O5(%?Oia6pe._%)]ASe@=sV1I>o[$JO&Zh6
^#:BT%W*t>#C)Hq,jbFV^.Xf%_tL^k;K4q!k1$g>d^-*/^:cFSK*t#!`PqK,%g!)=%mle<$l
rPg_ajUb3oUc_kU"t`u5Y3lYfRO2FJX6#;c5o$omAHK"UWlAc/d56F(7arP7'BB:CjZ-jr#h
Wjp#T,J8Vh1"==TGTT+e@6,@]A[$L2f[K9k'`QcnG5;0alr8u%/L'G"chKk#^"eJHDN#1'l,F
VdZfJVdFne]A?^X$[Y/qP[5URg*6>!9HLD9$fHOcf*;_A^$3QIgDT625_.XdG72fAGenX`JqG
5NbJ3/[C=o9`nHD"1thu.%MZuN>qu[0b'q'4&+b(cn=I>,A,NF$-KVkNlYB!E?N#2"q85.5>
7jL\\ZKZdHhc*:\I8.CP2/0qrVYpA?^oJE<Y#a:NcT%YSZHp$7'h2>L7&.WGZY@q#5mJW6V)
$!q4U$tDZ.s?q=_VQ"-<KT=W.#\OKI;NkEgU_5?,p,+9-Sto@eMBU<jK<4pL#MJ[rsR`[]Aca
@:`!s_j/-^kei"(jO\4\jhm(=DnY,2..rj*nW@jd^d8m?dJsl$=g2Fl5iC>V]AV[9p8o+I/['
mjHa?4(mBnj(;2T%jd9o-Ulb_2oi$WaW"rR%'N7]AOg,XOMann<`L]A[Es#ircg5m%_.5K/lea
t&qCtd+$r"l^9RC<6.<FB"k:;`]At`[fA+9EnC\mUYUmm:pS1h]AX@'fBcc$9saS!9&mr]A0PMe
o4r"_CB,GP5=qY@>!+AW,B>9a9R_XY&5LoQic**a.F"\]A/,emOfdmJ/_u^OH2+88aZ%Ka`7P
%,^%GGg_Slc<QdT?Bo#3HV`YH;D]A>No2P,`C3dc@a&frRE8iTbo*UeuHh&qdE_/_&\)oE1SI
ob[P%*mQ7&!nNiIcp$ZP>\ukMPd+7`^l6I^E;tkrp4*PfkccGtDi9#9nVT0VM^"*$`]A[XRPK
_%MQ$-ieUZBXZbg.WgTOUDjL[b)RXhcuZl9&0%!b4S%^A5%"@s2m#2BJdkD*\=jq1@4Ha!oH
iS]A8,<?c#%0-U#O)ld1o-"KYIMr,+Y&`1KqcMZ&_%1OagDfWT?E8]AcObeNIqB^q)8Ym376SB
k0^hrE%N[''=d<`7Sf5o4n@fpr/-+'W^8D@@cHFgI1UoqZk-B)+,4Fchp!t&7;p?5@G0=RkR
'knt2FQ<^')=>JU3<Y[EWNLDCXG\p)@Wpa5q[Yb,N+3P:j[nFuT]A&iF&/"C_6sCG1Ts,Np=J
oJLdMC1EBP<25=01Ks#UjT1o*Oa_u`c)8Wo;iu>1AVKLCj3ZFs$g9!?F_d:lXmRH,%0cSa$5
q.!-Bl37.8%FM??sVa#ep-7+i[A$,Y(fkSM</#MJOqtO"!r^6lud5MA?]AWHM.AVa'UH%bF]AP
]A:\g_;<QXrKnOuDeq*M[CkWjpBT9,G+FmE8T0)VRG0b#]A-Xs3>`in";K7C#67=?6a)+#Eb#h
p`Nn6_eh+o^/sbA-SjmB[gs2i;J*i\?sp$_d(!p^A;unS3hr#pS@^%s7H^r;K+[)o!/nC$eR
qIpKp#6g9?T;G_Ea0&5Zf-n4=A<7n]AjW#\>Icf%o;f0?+?16:u[EotQe\fs&\9_DnEGAmFcE
ND@'UJ[p/WUX%K)Xo=)6CM#;Qc@Ga"8H2Q^b\;"FDp"71T2H?*da>ip!VLX^hEq8$&<T,P[X
7UMXTSTuW/VF5WJ^V*Rb`!+dY&,QDAi_#=7isfh^I(Lr2D2%[R%p`dec[A2VdEBW$@?-aJXm
q0lIgK!S9IO+qKFcJ'0t5ml<9r\trVQBHJ>R#q066>%R=Tj,2%I!u@ks491+/R9XH(`tof'=
BZ*GA!?m.e(dPO]Afm8@-s#;,ldYSc^m3r;]AG9uPQpKQT1r+6p^U?_CUq2t:BNm'dW/]AFZD^p
BPn2E.S+VW,N2]A!PeG[uL,>8n`rb$f98mfXeC<^>7!4Y_S@G\S,+ZSCN>':5i"%BJ=]AGD7S/
Skon25G"RfVOH<HkpYB.h6F]AnYCjSPm+aP$0La=#Cb<p,>`jE9s!#=0W+F:[e4ID2jl.f8N7
`7);Q6N`7_.!*B^:lR7`S4-,nEZ92&D56<'2_E!k^&XI:O5p[n&P\</K`JIA5"eOZ0&oVbB\
cJkjj>cN*Dp.r/gJhDXcerarWh_\a*[TbWC"r=aH172k`lQaAkD-!d>"r-)I4c6:ZJmNb'_i
VFq'R:C-R*(mF-Wf.()kg!n40&"1R]AD6hh:KF6nU>L9&k"ft"@*V`^f1#50@a/gT7EN1AML\
PZ]Asr<3D8^^5:XlT5:;B-%TK48>JM[2b^Ud,Qh`\Mb=^^A>.a=gB!<[W6,93:+?p8kG.<G,A
)t?-0`1<9h?7cQ\GpXW@&KLAk>^R('Z!C3_4`r"B$B#l@)Nkum^!Z%a2al[AmZ)8]A"gI2dS8
Qu`iG?fFRe!o#LqI`FL/WRM#G2GE^;'W-kTA2:U45T2M#J"b,Pjq&O6HULOTHb]A>u3_ZrZ!,
o,gqP,qtS2*he^s7;tlXQEf_ai2Z._rn9O9cBl2u#Gir^VIq6E]A^V?7ed1XOuB>He?r`Sa:K
)aSW`m95G=cUT/D3GL5mW"K1;]Akc1gFAh4YW_/o[euJmEW67~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[分公司（联动）]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.map.VanChartMapPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="0.84"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrEffect">
<AttrEffect>
<attr enabled="false" period="3.2"/>
</AttrEffect>
</Attr>
<Attr class="com.fr.plugin.chart.map.line.condition.AttrCurve">
<AttrCurve>
<attr lineWidth="0.5" bending="30.0" alpha="100.0"/>
</AttrCurve>
</Attr>
<Attr class="com.fr.plugin.chart.map.line.condition.AttrLineEffect">
<AttrEffect>
<attr enabled="true" period="30.0"/>
<lineEffectAttr animationType="default"/>
<marker>
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</marker>
</AttrEffect>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapLabel">
<AttrMapLabel>
<areaLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</areaLabel>
<pointLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</pointLabel>
</AttrMapLabel>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapTooltip">
<AttrMapTooltip>
<areaTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</areaTooltip>
<pointTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</pointTooltip>
<lineTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipStartAndEndNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</lineTooltip>
</AttrMapTooltip>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrBorderWithAlpha">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-7355649"/>
</AttrBorder>
<AlphaAttr alpha="0.64"/>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
<Attr4VanChartScatter>
<Type rangeLegendType="1"/>
<GradualLegend>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<LegendLabelFormat>
<IsCommon commonValueFormat="true"/>
</LegendLabelFormat>
</GradualLegend>
</Attr4VanChartScatter>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="2"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-15685193"/>
<OColor colvalue="-15548208"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartMapPlotAttr mapType="area" geourl="assets/map/geographic/world/中国.json" zoomlevel="4" mapmarkertype="0" nullvaluecolor="-7355649"/>
<areaHotHyperLink>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="company"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
</areaHotHyperLink>
<lineMapDataProcessor>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
</lineMapDataProcessor>
<GisLayer>
<Attr gislayer="null" layerName="无"/>
</GisLayer>
<ViewCenter auto="false" longitude="103.5" latitude="40.0"/>
<pointConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</pointConditionCollection>
<lineConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</lineConditionCollection>
</Plot>
<ChartDefinition>
<VanMapDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<areaDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[公司简称]]></Name>
</TableData>
<CategoryName value="地区"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
</areaDefinition>
</VanMapDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="998" height="563"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  分公司\"]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="5"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="linear" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<ConditionAttr name="条件属性1">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-10698774"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[1]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[8]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[17]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[22]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[29]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
<List index="1">
<ConditionAttr name="条件属性2">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-10234204"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[2]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[9]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[16]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[21]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[29]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
<List index="2">
<ConditionAttr name="条件属性3">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-78997"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[3]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[10]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[17]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[24]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[31]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
<List index="3">
<ConditionAttr name="条件属性4">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-487545"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[4]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[11]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[18]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[25]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[32]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
<List index="4">
<ConditionAttr name="条件属性5">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-6885931"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[5]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[12]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[19]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[33]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
<List index="5">
<ConditionAttr name="条件属性6">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-6951467"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[6]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[13]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[20]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[34]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
<List index="6">
<ConditionAttr name="条件属性7">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-27427"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[7]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[14]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[21]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[分类序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[35]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Microsoft YaHei" style="0" size="80" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="company"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="2"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-10698774"/>
<OColor colvalue="-7017004"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-1709587"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-9997189"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="false" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-2171170" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72" foreground="-9997189"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[公司简称]]></Name>
</TableData>
<CategoryName value="地区"/>
<ChartSummaryColumn name="个数" function="com.fr.data.util.function.NoneFunction" customName="个数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="310" y="61" width="580" height="442"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[AQF(9e8$"(Vc(['/X^9<+XC8#L<?Y:CUV5nDofYRXc:1g(_'rq*\uES%8KW`Z%a+-6d3IcP\
&'4_ag8=?0>JM5O\LIIb/Yscgor]ArOd-I@!te1di<1?Cn-4a2pL%.rqLQ\(-d+UDq1UbUp.,
o`#7F]AYmemOB!Y]AF\hfq\<`MArE8F:#J>OA9d=04qp;!B>,#3-Q!m:GRDBuA(%B.lA-dK0bB
K&u_,tdGK%ToT/2b>TdR]A.c]Ah5[KJmtNe8Y;)rNC;*]AThq)ISOu;HD^1)e$X5Q2Hi]A5?omA&
j9n6FjTP+#6L3ZuD6YY7Nek,Q^aDN^qF@cQ3.^A`&'>oRg#na&KsCR+,Y(Psa7pdG@u<Edne
r:Z,KZ^W@AQ'WJ<'GkRB8J<ndrdQ!"1mI6)#LE%5T7qRerseWk6h-AU;nqA^>i-S[hRQJPn_
(s\4TEN9+CFK)VF44-hKF1e-#+!E*kAq/eM)dS3WD<_N!#9C$&$'uNm9]A/\"VQ!r`/Ol%+jA
Y4BbtOZ\+tOWfX".h32n.Vq(@Qam[Mn*CROK-L_tbhqP9@\s-X!C[h#F6h-Le+Hf-Hq%gkno
luur$AX_W.Bi-?AZ0!s;m\/19KdZr+!(m/C9'J3"1k1APTrbI>p+/TGOQSES)7b11N,@/D31
>MAF3P"jln$ZpNmk!o&b:lJ8S;+3:or.IJ4hClH,]AmL[f9*ULK:K=!-ud7=OCqG>B>hq-:6$
Cl4ZmrB1mH%ruu;TiHRGb3MLk!Gq.\9ZP,fie+O_bE+#OoQeKO(R#Z]A&gO3U.j\O\jf:,uKe
^`&V)\75$4kBU)\3otR>[iKc&up$ih>m<S:%9tr,S:;P0YH]AkNDME?52t??DM7=$E,g?9-8S
2B>V==2)4R[bn3U8+oq_tg4GAe0:.o>b^Kk08GUS:O]AHBfE]ABO"\t>Gb(N<"[-0N/eQb3lc5
"c$s4!H>[)>pIBG$9,H)/fl?E\82(=1G8N05p'<YCE[(I1_X/kaQi4?u`f)3\r#!;sR1]AjmD
*qU%L-%21n_PS&O(n%jtYebZ<!!@SUR6?4Z&M`#J7V_-l?/A5@4enB@G_`l5`i2sg;^a%[op
cGiIG+QW^%0EVc)2H!^C[]AJ?(l.-Mo6sn>YB.B,@1dr"Q^^Vtub51asT=TIa&VD/u/HK1jK"
hnH9r7dP_<e@VG"A;@$\Q1=C%0sF!_&oj?C=&<8(!UVd.To4@ocqaHp4Ue7_eCJe?`L!\LB]A
TR]ATD8C^TNrSNe[YkM%qkk,"nDVLTa=$@[VV)WgX\,E+4ECmTJc,j_TL%aSc>03IQ7)5sTCQ
Fiu7X<8!`*K``u$N.fU10Oh>d;&o;`(N=_Xi/H+Di^h[\n#Q<>kr?gDU+'Mgg0Ior1pc!%mM
n\(Vl^$!%"0[V65K^g(=?Qb/@4(5b@#]A@cl,QlcG.s=*l(:FjrM!^8jCnBRihWQjF$K9WG-!
X6@VK(-6Ibou+eKR>!;Vp$isiFSX1(2lrGo#ebqsN^WM@1$Bi`<eGa'2;Oh%=j)G9URnYgO`
=^lXddl6N`rJ#inGg.G"'=@'s\*Z8!KuIN4M)#^f]AQ$p3%EUXIlRRF0jM\p=sB3G(Z4@R'Sd
?F.rs\^$>*B"l#['m8;fA`35A*kEuc7TZW5Y+8-#L;GFEg>`>Hg1`tPgTrlR.(*i`84V<]As^
#]A<+lH,C>)MlZ!dYi;fN\4)&(HWIr4^!3[EO(3LIgC;P5UsjnA>_pm@6l5KbVRo?U-%Un:J^
`<I?!dA";<E<+<cu<ltQ`fKsO*E8JRGL+B<Z_kF>E&%d(PJ&mK6D&6udgmUmpAr;nce&N$MM
j#7kg>G&c`(>m^Xe)FoeXSZ2Dq'-16EKT1h2aRM<JtK`:N^a?#:.lM/"G?q]A//DV8MYj)1LG
K6\3M9qH*r37J>8L"&5O5fE,qRBO&"I;H_FF@<ZO[\Es3]A:I8VNE+$"J7ihT'nn!q5'Uin%*
e5X8HWqrN,?=Y\:8UO/Z-fDQ,iJjTrF$5t0!Bg=4anf>q&#G;:lCg+3!CJ"##h!OVX*,htcf
8@NG/X-ZGK5--4[pIBrHS6Opl$tjY/`3kPl/Sp$-$V:7g_WH]AlJTkpCqotWB<sWhNmd'rdOG
N;ndW)FO^h9ejE7e^.[XZXqR5)DS>FV9_hWU(\LVc*hmcjn-V\kQNe$>T1>L$LG`7@KPni"?
D\'p">J#W.d:`a]ANR6%_VLXU]Ap2E8lS1^8[9N36JG29-N&L*aNe!gEQodlS*haoi6VodCgY6
,GTETR1,ng:9E$l_7#k3:^YD]A7CYbqgX2:g"EZE#[\ABc`hib]A$q1OG!?%UWTHiJugk5BN1H
f_qL;K-^&9<p*<-jV"hsWEtK;ui*NrD,8ZWM\"1qF&qtfuZ:E-I:(A?&C;@udOot:9nY^%WR
aceX%q^nc72Uo8FPI'r)C[f*QojZ&ECF@L3l7qso\5de(_cTS47G62Yl!9F"^F-;%%\G^h-m
h"0W#/<\ffR+e1I<@nQegU<rs?7/:P98U+fU=Wo^Zg(Y^T(`4J)V-DE)WnZQh'CVU`5*6]A4N
LaHu=>Qe,V=Wnrl1KQH9FhY>UO,&7Wr1X<@W"Y$">,E81C=ucUbRZ8.8^W["5R`rZ@qtT(1<
.FWE`QAQ]AOl>S4$1p+@]AY!HCU\[[GI-GI<>,g]A<RN%ah':T0Pa+Ti_)0RBD3!iPYJ_^gAa@c
?IQj1eA@P7JCdmbKTAJa=Zk=S?^s5:ffS]AG:56kdM[5#AXiitJ0ZoVI"%"r6pkF1Pdq3?%-k
10s%#%@N+eMGi4m+aShZq9Xt@-d4UloOTMDMs$b?lc3P#c/>@A1k-M]A8]A0;?WikA`"f@\C9^
(?35da"B+jp9=fZgt#-.&YGY@Jra@UlNI.\f-2qsNO`Jf%Z$`7YKDM^7NrRPGHIFDiZ@Wf-1
<P`$BrmVI<)6Rp5_lVL[^7qD=:G:GbC43M2D-8i"7W2?9R*o%ZFKkt.NQ0WpcBeC4'\sU0nd
N'KFbnTsZp-o`qW2H_lT[MhVKTXS/$K'X$Wq&FFhV;W($D_*@c+:MnSt(>F-o,]A`b1'8,@As
pVH*!"06R&Rcfil?e!tP5f;sM.3(%XED7b]A-,8!??p?W[=a%J58C(IOPq-/O8(Cr%%7GJDAl
6nnlJuALo!SO158F3AL?Dk=u@JFBF<O48.(&]A)AWi;ClO6'7pP;ItP<EjQ*M3/)gSR\A>1r*
H8r50>=BhCluh5@4\VVd5;eN_\$?J^+e/)qpJL>i&:_[N(X_@24T*"7V@=@TLTkZoB7"Gmsi
bq.@bbAi>[q''ZE?9A([rI`'M5>""koA!-e'?(!W4YC%:QS3^"2gdDQ7G4iYDn;!biJsOrIt
8W;i!,*?=^@Fe%$^MW=bn9)lK(_F3;sg#kN<HeC17!62&i-1_!E61_"qS3i((jjeKeth4O*/
>F!oRE<7f4]A-s,*5k(R#QNHGh08@$k@kdbGkm)55g9u1_s):JA/X^l"15Lm5MLjY6jgmFdiL
%+Ioj@#"[i]A[eLYegdA<,Ae)?Id%tXhW.c7K:o9PdMh#_0BZWJk1f5r]A(*!5^\4nmr1P39"@
W250nS%3994=g,ameCDIEXXCuIA54CX2DF9%I9k%luIL%\U\KTu8Rtj_]AD-GFm1!@Rh\']A?C
'Wf>-1,Q-.?5(Fi&2@"I]A83ffrp&YVT/(bNggp.70+5%gYc2DrGZ[R7H<iaUXWeS'LZ]A20<:
oXKf>o!e8N$tKk&iV,32^p>RHOSpn[o68n)NN^P>r_AAD_o:9>^*@I03Y-^n%WN-cPj_oag-
ZC>o)Rjb*A`C,&lU\Ed)S_t9Sc.Br[f0iG?M"Au'X8E_NUN3/r-EgZ:E1Y45oMGS$C7+(Kr/
`0dZr(UWV):S4@h;,0eh*FANAfX=Jr[FTt-(]AaCdB*HpZn$ZVFP2#HXNoH>k^)*/TQKK4VQ(
GSZW=Rn>4D"049#%^<:RN5nHsSQ\fO$[aP7XE$!+^GeLsnjGYu"bIf00UFCqd'@/EULEaK)A
F=?EjYfV2o_IEpHnF/uT3-RBjS;*SP!LC6<]AQuP"O`^B,&*nVG7sEpETN=<1%[5c4GRQ%=g5
;U:kb+PH`m\/"qDo<JeY7coJDd,k/D&`Yi1S.bXcR9*>fK.KV)]AA77BtV^m</d[Y6cc%\oj?
7`*P(d38Ks+Pru-I=V^VsB09XZhSiXI0;If*:+L)c5^BHd?U/j#b'OG2W^6D$<+m`M0TCT9j
CUpg4>%75F(a>^EDjg/jXO^*9(!$0Jec"K9$t?&1n(uu.p_lX8uaU0,Lb.0.BG7A%^'ogFMN
(=G"hX!g<>O.pf)GKD$cSJi04@rm"Y$,jTWk@V,'/FTl;Bo&ko*;!Sti#56R9$fBsfFc'\in
]AX4%=e3W%k9>1@/cX>+0^95o\l>DYJ+8N,f_LrF%=8G$73^&2JqtX1g&NhimEe`'"B))B&Hd
IGNW_A\[8<3te.<pSQX1K[Q4]A4_9PF_R7f$cG4&q,94]ArT[mI)O,NBCE`Vh),gq/Sie!m6:K
k7<[=&!h(FqJE&M(TS'Y-7m)!M-['`OW`dC*ic6u/1L.CjQVX!OQ63;tOlc:+=D\19:lO/B_
dlff1rYOXU?[u,@E\LY-)m<=+8_>mF48CcPdVXPM?H"#IuLCXK?0To;>`T<RXEY(g-!^ueB:
p4&ctn5hg1.T(4`RP%bt_/PKa>"K`rZ%8uE#NLfo$%d#^YT*I2HmWbOqA<eE*PqI8p/5dC(T
7.i6K6RNuP_BJm/Ck8]A#ZLPmL58De23u0:&Z(fO2.AdtKZ"_6J/XRNlo\""[-.Z:;nFX5_Mh
T!#aFmlj=G1H4POc)U7Plq_Y^;.0^CG3WFKfM&BV60b;Qco.b<K$EnNU(T?`!"Ccl>%'PK_h
nSDd<m8u(VlCU$^qj"Kt%-d4rTSN-)^KE7^9WfJ>k>R$Qnmt*X,ru@qkFNtVV9Ur(cRlPZC"
%uaG1l>%>G-XFE%VLD($^XWlfi)@RFe)ruU%f?7(It!oALg]AJ8UGbj*8Aj^\U,#S9nO@cHd4
aQ&\TMLfXQ:cJFXj@ItB0X0`IWZmQ:u;:9=a)%_Y>==$8@Q[II-pPn2N]A**Q%^R#3L2WMAF)
^;B(;JNk4dL%00O=t+lt@W0:TYH]A9`^[Jlt*OO=@Ks=JoQX&m@R%PLnN5FsGFp?9onR7l!ch
K(LL+a>VZXPOreS[hZ[`(_o/dI4J)nRsS]AlGnLjlL#;0)bYUfe`tYV%2n[8,.:hLn+M8^@hU
iF8s"K0)0%tot-:a#6~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[AQF(9e8$"(Vc(['/X^9<+XC8#L<?Y:CUV5nDofYRXc:1g(_'rq*\uES%8KW`Z%a+-6d3IcP\
&'4_ag8=?0>JM5O\LIIb/Yscgor]ArOd-I@!te1di<1?Cn-4a2pL%.rqLQ\(-d+UDq1UbUp.,
o`#7F]AYmemOB!Y]AF\hfq\<`MArE8F:#J>OA9d=04qp;!B>,#3-Q!m:GRDBuA(%B.lA-dK0bB
K&u_,tdGK%ToT/2b>TdR]A.c]Ah5[KJmtNe8Y;)rNC;*]AThq)ISOu;HD^1)e$X5Q2Hi]A5?omA&
j9n6FjTP+#6L3ZuD6YY7Nek,Q^aDN^qF@cQ3.^A`&'>oRg#na&KsCR+,Y(Psa7pdG@u<Edne
r:Z,KZ^W@AQ'WJ<'GkRB8J<ndrdQ!"1mI6)#LE%5T7qRerseWk6h-AU;nqA^>i-S[hRQJPn_
(s\4TEN9+CFK)VF44-hKF1e-#+!E*kAq/eM)dS3WD<_N!#9C$&$'uNm9]A/\"VQ!r`/Ol%+jA
Y4BbtOZ\+tOWfX".h32n.Vq(@Qam[Mn*CROK-L_tbhqP9@\s-X!C[h#F6h-Le+Hf-Hq%gkno
luur$AX_W.Bi-?AZ0!s;m\/19KdZr+!(m/C9'J3"1k1APTrbI>p+/TGOQSES)7b11N,@/D31
>MAF3P"jln$ZpNmk!o&b:lJ8S;+3:or.IJ4hClH,]AmL[f9*ULK:K=!-ud7=OCqG>B>hq-:6$
Cl4ZmrB1mH%ruu;TiHRGb3MLk!Gq.\9ZP,fie+O_bE+#OoQeKO(R#Z]A&gO3U.j\O\jf:,uKe
^`&V)\75$4kBU)\3otR>[iKc&up$ih>m<S:%9tr,S:;P0YH]AkNDME?52t??DM7=$E,g?9-8S
2B>V==2)4R[bn3U8+oq_tg4GAe0:.o>b^Kk08GUS:O]AHBfE]ABO"\t>Gb(N<"[-0N/eQb3lc5
"c$s4!H>[)>pIBG$9,H)/fl?E\82(=1G8N05p'<YCE[(I1_X/kaQi4?u`f)3\r#!;sR1]AjmD
*qU%L-%21n_PS&O(n%jtYebZ<!!@SUR6?4Z&M`#J7V_-l?/A5@4enB@G_`l5`i2sg;^a%[op
cGiIG+QW^%0EVc)2H!^C[]AJ?(l.-Mo6sn>YB.B,@1dr"Q^^Vtub51asT=TIa&VD/u/HK1jK"
hnH9r7dP_<e@VG"A;@$\Q1=C%0sF!_&oj?C=&<8(!UVd.To4@ocqaHp4Ue7_eCJe?`L!\LB]A
TR]ATD8C^TNrSNe[YkM%qkk,"nDVLTa=$@[VV)WgX\,E+4ECmTJc,j_TL%aSc>03IQ7)5sTCQ
Fiu7X<8!`*K``u$N.fU10Oh>d;&o;`(N=_Xi/H+Di^h[\n#Q<>kr?gDU+'Mgg0Ior1pc!%mM
n\(Vl^$!%"0[V65K^g(=?Qb/@4(5b@#]A@cl,QlcG.s=*l(:FjrM!^8jCnBRihWQjF$K9WG-!
X6@VK(-6Ibou+eKR>!;Vp$isiFSX1(2lrGo#ebqsN^WM@1$Bi`<eGa'2;Oh%=j)G9URnYgO`
=^lXddl6N`rJ#inGg.G"'=@'s\*Z8!KuIN4M)#^f]AQ$p3%EUXIlRRF0jM\p=sB3G(Z4@R'Sd
?F.rs\^$>*B"l#['m8;fA`35A*kEuc7TZW5Y+8-#L;GFEg>`>Hg1`tPgTrlR.(*i`84V<]As^
#]A<+lH,C>)MlZ!dYi;fN\4)&(HWIr4^!3[EO(3LIgC;P5UsjnA>_pm@6l5KbVRo?U-%Un:J^
`<I?!dA";<E<+<cu<ltQ`fKsO*E8JRGL+B<Z_kF>E&%d(PJ&mK6D&6udgmUmpAr;nce&N$MM
j#7kg>G&c`(>m^Xe)FoeXSZ2Dq'-16EKT1h2aRM<JtK`:N^a?#:.lM/"G?q]A//DV8MYj)1LG
K6\3M9qH*r37J>8L"&5O5fE,qRBO&"I;H_FF@<ZO[\Es3]A:I8VNE+$"J7ihT'nn!q5'Uin%*
e5X8HWqrN,?=Y\:8UO/Z-fDQ,iJjTrF$5t0!Bg=4anf>q&#G;:lCg+3!CJ"##h!OVX*,htcf
8@NG/X-ZGK5--4[pIBrHS6Opl$tjY/`3kPl/Sp$-$V:7g_WH]AlJTkpCqotWB<sWhNmd'rdOG
N;ndW)FO^h9ejE7e^.[XZXqR5)DS>FV9_hWU(\LVc*hmcjn-V\kQNe$>T1>L$LG`7@KPni"?
D\'p">J#W.d:`a]ANR6%_VLXU]Ap2E8lS1^8[9N36JG29-N&L*aNe!gEQodlS*haoi6VodCgY6
,GTETR1,ng:9E$l_7#k3:^YD]A7CYbqgX2:g"EZE#[\ABc`hib]A$q1OG!?%UWTHiJugk5BN1H
f_qL;K-^&9<p*<-jV"hsWEtK;ui*NrD,8ZWM\"1qF&qtfuZ:E-I:(A?&C;@udOot:9nY^%WR
aceX%q^nc72Uo8FPI'r)C[f*QojZ&ECF@L3l7qso\5de(_cTS47G62Yl!9F"^F-;%%\G^h-m
h"0W#/<\ffR+e1I<@nQegU<rs?7/:P98U+fU=Wo^Zg(Y^T(`4J)V-DE)WnZQh'CVU`5*6]A4N
LaHu=>Qe,V=Wnrl1KQH9FhY>UO,&7Wr1X<@W"Y$">,E81C=ucUbRZ8.8^W["5R`rZ@qtT(1<
.FWE`QAQ]AOl>S4$1p+@]AY!HCU\[[GI-GI<>,g]A<RN%ah':T0Pa+Ti_)0RBD3!iPYJ_^gAa@c
?IQj1eA@P7JCdmbKTAJa=Zk=S?^s5:ffS]AG:56kdM[5#AXiitJ0ZoVI"%"r6pkF1Pdq3?%-k
10s%#%@N+eMGi4m+aShZq9Xt@-d4UloOTMDMs$b?lc3P#c/>@A1k-M]A8]A0;?WikA`"f@\C9^
(?35da"B+jp9=fZgt#-.&YGY@Jra@UlNI.\f-2qsNO`Jf%Z$`7YKDM^7NrRPGHIFDiZ@Wf-1
<P`$BrmVI<)6Rp5_lVL[^7qD=:G:GbC43M2D-8i"7W2?9R*o%ZFKkt.NQ0WpcBeC4'\sU0nd
N'KFbnTsZp-o`qW2H_lT[MhVKTXS/$K'X$Wq&FFhV;W($D_*@c+:MnSt(>F-o,]A`b1'8,@As
pVH*!"06R&Rcfil?e!tP5f;sM.3(%XED7b]A-,8!??p?W[=a%J58C(IOPq-/O8(Cr%%7GJDAl
6nnlJuALo!SO158F3AL?Dk=u@JFBF<O48.(&]A)AWi;ClO6'7pP;ItP<EjQ*M3/)gSR\A>1r*
H8r50>=BhCluh5@4\VVd5;eN_\$?J^+e/)qpJL>i&:_[N(X_@24T*"7V@=@TLTkZoB7"Gmsi
bq.@bbAi>[q''ZE?9A([rI`'M5>""koA!-e'?(!W4YC%:QS3^"2gdDQ7G4iYDn;!biJsOrIt
8W;i!,*?=^@Fe%$^MW=bn9)lK(_F3;sg#kN<HeC17!62&i-1_!E61_"qS3i((jjeKeth4O*/
>F!oRE<7f4]A-s,*5k(R#QNHGh08@$k@kdbGkm)55g9u1_s):JA/X^l"15Lm5MLjY6jgmFdiL
%+Ioj@#"[i]A[eLYegdA<,Ae)?Id%tXhW.c7K:o9PdMh#_0BZWJk1f5r]A(*!5^\4nmr1P39"@
W250nS%3994=g,ameCDIEXXCuIA54CX2DF9%I9k%luIL%\U\KTu8Rtj_]AD-GFm1!@Rh\']A?C
'Wf>-1,Q-.?5(Fi&2@"I]A83ffrp&YVT/(bNggp.70+5%gYc2DrGZ[R7H<iaUXWeS'LZ]A20<:
oXKf>o!e8N$tKk&iV,32^p>RHOSpn[o68n)NN^P>r_AAD_o:9>^*@I03Y-^n%WN-cPj_oag-
ZC>o)Rjb*A`C,&lU\Ed)S_t9Sc.Br[f0iG?M"Au'X8E_NUN3/r-EgZ:E1Y45oMGS$C7+(Kr/
`0dZr(UWV):S4@h;,0eh*FANAfX=Jr[FTt-(]AaCdB*HpZn$ZVFP2#HXNoH>k^)*/TQKK4VQ(
GSZW=Rn>4D"049#%^<:RN5nHsSQ\fO$[aP7XE$!+^GeLsnjGYu"bIf00UFCqd'@/EULEaK)A
F=?EjYfV2o_IEpHnF/uT3-RBjS;*SP!LC6<]AQuP"O`^B,&*nVG7sEpETN=<1%[5c4GRQ%=g5
;U:kb+PH`m\/"qDo<JeY7coJDd,k/D&`Yi1S.bXcR9*>fK.KV)]AA77BtV^m</d[Y6cc%\oj?
7`*P(d38Ks+Pru-I=V^VsB09XZhSiXI0;If*:+L)c5^BHd?U/j#b'OG2W^6D$<+m`M0TCT9j
CUpg4>%75F(a>^EDjg/jXO^*9(!$0Jec"K9$t?&1n(uu.p_lX8uaU0,Lb.0.BG7A%^'ogFMN
(=G"hX!g<>O.pf)GKD$cSJi04@rm"Y$,jTWk@V,'/FTl;Bo&ko*;!Sti#56R9$fBsfFc'\in
]AX4%=e3W%k9>1@/cX>+0^95o\l>DYJ+8N,f_LrF%=8G$73^&2JqtX1g&NhimEe`'"B))B&Hd
IGNW_A\[8<3te.<pSQX1K[Q4]A4_9PF_R7f$cG4&q,94]ArT[mI)O,NBCE`Vh),gq/Sie!m6:K
k7<[=&!h(FqJE&M(TS'Y-7m)!M-['`OW`dC*ic6u/1L.CjQVX!OQ63;tOlc:+=D\19:lO/B_
dlff1rYOXU?[u,@E\LY-)m<=+8_>mF48CcPdVXPM?H"#IuLCXK?0To;>`T<RXEY(g-!^ueB:
p4&ctn5hg1.T(4`RP%bt_/PKa>"K`rZ%8uE#NLfo$%d#^YT*I2HmWbOqA<eE*PqI8p/5dC(T
7.i6K6RNuP_BJm/Ck8]A#ZLPmL58De23u0:&Z(fO2.AdtKZ"_6J/XRNlo\""[-.Z:;nFX5_Mh
T!#aFmlj=G1H4POc)U7Plq_Y^;.0^CG3WFKfM&BV60b;Qco.b<K$EnNU(T?`!"Ccl>%'PK_h
nSDd<m8u(VlCU$^qj"Kt%-d4rTSN-)^KE7^9WfJ>k>R$Qnmt*X,ru@qkFNtVV9Ur(cRlPZC"
%uaG1l>%>G-XFE%VLD($^XWlfi)@RFe)ruU%f?7(It!oALg]AJ8UGbj*8Aj^\U,#S9nO@cHd4
aQ&\TMLfXQ:cJFXj@ItB0X0`IWZmQ:u;:9=a)%_Y>==$8@Q[II-pPn2N]A**Q%^R#3L2WMAF)
^;B(;JNk4dL%00O=t+lt@W0:TYH]A9`^[Jlt*OO=@Ks=JoQX&m@R%PLnN5FsGFp?9onR7l!ch
K(LL+a>VZXPOreS[hZ[`(_o/dI4J)nRsS]AlGnLjlL#;0)bYUfe`tYV%2n[8,.:hLn+M8^@hU
iF8s"K0)0%tot-:a#6~
]]></IM>
</FineImage>
</Background>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[1143000,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[8138160,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[XX公司人力资源分析]]></O>
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="144" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9FF+;cak`6lJNKi3ZO%$!AgGF]Au#*fID`@)7;m`'usM!8<G<#C)&Jo#TtD/U+RH-e#K&n+Z
_)d#nf[5njn-bWC6p3(n^q-@uM[(KG\1TF(;+_lhrRHa#/Str87hCSt(/Qfk9dPIGY>mQ7NL
`1]A"6$:/4Po(+tuheB)D=ccO:3m.qXkQeDr2Hmbhrb(]AHeZ7P*&DqA@2r\ANCT<F?SKO*8R\
)W%;aZ&fd$RLSi5/$@Ah!g"f9.qS"2Pk@u0MtJYCO=Om)a5`3Q!9t2B^=)7>7JN<Cq]A1#h%k
(kL:a!.[>,CW.CP)R'Y[;1RCM46T(E-_*i[\aNg$_LB@fmc1#X_=hgbBn(G@im7d)c!WLf0C
^ubm'\WjDjX(OWnDWaL*FCFl2J+qiEYs!Z9$MObG[N>9N(Y]AVd0hi;_f@3TcJb$[KklQKn@d
.MOB\W%+4%_/'If\Z#e4]AZCc>BokR[D(kY3KZLC3PS[^!/!$/s7be#^;3e'>%7!f@Q=R,R/J
*STBp`p5'tHcC.s.i.[>U%k4o/2E\?!=ZlY]A7Wa6XQ>mrVfGCPCY$%o@]AcbusZ?GK@&Ue9`e
44(RQ&9:$1QfZQN"i-E>hL^#@\#ZggKK')okrsLX/W/&l\^h0RL@_Z84-DAqqKSc.elK`5mD
2`qJLZ`11nM>_/n)n9s'C.:+U-%Cj<ECbi3YjAc.M*meC-MIqkjI[N6$I4S9"BN43i3q5MEu
K'BB8C]A3'LB#PjRmLnnq>PHVVVCq30.j9[)9MWV)X[fre,B8#EimWnW`C[n<Y+Kc?,;gjVjE
^0#+)EB5^sZukMY?N.G:-QOURn&FEFhV,D9[YCo3s,,@VnSBR:bh9e\XXghraN@?Kd10]APus
1A]ATmep@LiSK7jYlbZQ^RE4iF9K=9Kaj-mC!V))=IS9LujrhW[T,2`cu:6[*IUQAfUW@R_3;
Vrlg?C7NhE"kX$\93(!mqcP(pu8<JX83[tg'6:i24(Gi$2]A\"1+?+MVR/+b=qF(9f1uc+q(5
j5[2u:$Z5B![!eKgY^h;Spo57u.j8"%gGDf[XBG4m/g#KP)$XrQBS6sL:^&@+tSq1iR/R?]A*
9X)Sdf%&FS"U^:?<"^=D&<i+OdTGtFRnrD3)ksh'"B92Q&HC,$Hj8aGUfa-/]A6kAGWSRsK@'
RaunOP1_BEpr`MPh*b"__gI)hm>7Nk^-c)W.'n"WK(7gPBmMga8Q1Pfd6GSseS4[S-<'p9f?
^Zm4r8nOA'b"nV(XS41=hHMQ$Id:6.^n4;.RoWH'3=@m9+-@p4n0T?d:6S)B*BkeDpc747Yd
Ml`houW>s"_Ul*(;5'e0XCNSj0XL6;9q;OBm.0bh0@(l:kR3$-;fkj`_>b,ig?3q#7(hc]ARR
T,EC9GLpn7WW8`S+4#cl^ZJ3.jA.H,*Ii`7VlR9fGRA1'HheWF/uW?#hN1*8X/_TSb%"^cIY
6sV27\ppHSVV?dq-dZGW=ig2'f6)7%mnRsk"GVct6-`\PFG7QO$+4EkC)f/cUm0n^d^0W+d6
8%GqZRXr5=.(<_Ye#^^n`L^*kc[r.gaCWL`e_d;!1EXq"3*^"-)]A#'<.g9cSU2]AQ7pbA52am
[L-G?c0(','_jj.TY0@(b-3Ru[.Tsirl<FN&j)GiWPa\Wn(q12\eCD#K_ei7PcqdC0ap9i5L
9O(i&0^O*_t_KM&OcT`_C;84W'*rMZF0X<Bm3:oVl[Wf)BID\o/oT$9PFasgGX=RR9?-K5G0
X_IJhI$o=e!BF9Ja%BFPiF4jAdlTl51@g%D4_]A9?`V%N\)_'r1'AbEEVF4B?&Y`oEmN)1a9)
LH`k;`+4G*pD1dP*B["@Sgs$/YeJCt(Q8HQlk`GQe0BAUa/qP?#Q#k:)\D5!YN0Md?5do^1(
N[@^nA/#N-*;I`f25"l9k:0COif1[!O7):+m8dn1]Anl>;>S$#7;h(W"@j1h\1FH2Ml$aV`?*
8@.?@5Q-d5>Q[aUs+lAcU*"5Xhr$L`hA'\G5+L?MQ%ZtSjn-X]AFVInd1e8/WF;d:TmPR$d3K
`Dl;F))omn^:QX"G`&jNUCAKC!UXmZ"a[`a#4Mogpe(EZ;sGZq#TP3jZ#KI>h@h4&C'I[cD=
6\%]A-Wj/,n+c=;n%HJ?0!@9H:<n%W2l\CZP)Wcpt_)SVcPWG7?UX"jtQ';j+LTAfY*!U-qsa
$M!9eSc).U]AM?K5+VXcHB9E<Y26>KGS?,8*I6rlI)[L3*r6^R+?m4*C?jT)SO-D/1j"^Y='[
qV1af[%iK_U,LdE]AmiJF6-lj#1*u4!@s=EqL=@DE`0OP9<u*J54cjf;><F8ZS^[,n;,jH_SB
(,=Q->BYWcqA\6/-jd:jULI'-jY3b*PYQuTa$n8+AMQe*sM"'@*OfKADn3dIj<VIp'kn)4F9
BY/cg"*=dI-sH/G"f(L._QBmlD0o:pYs%5V5!90WesC")#Qi`jSl$V"j[cD^Yl(6_$u/r(2P
gl6&>,*`J+&53Xn$OoEC(0B+D\jmsrF@M&^0[-IlTLj!WV,`e.D6a%]A.E>f<RQ"^@loB:/96
EU4(1'o%+nN;jlC8-1r-XNX=\(5(//bh:qm>&>NUp]AGd/Xc]Aj@ECJeI,]A#AA,/rXQQZq.L@n
-#$6XO%T`1A@<"=c/2:dM')qU?$4E59b&G*3i*F)oEZ[i,LE`nQ&,OOI#!`quR<:8_5FRk=I
!,/&aV7UM\hp**]ABgDD$N+8jJ%g8Z&9>MJ\MZ7]A<A!%=AAMLe1?7LLlu(I6HE*"M^9e@2cLj
gP]ATQKgUN1`R'38`r;(\FNQL%HEA<qcVF>q>!SVm(hTO-7!skB/OZIFh@"^2=CA4ggr$(G8O
H/2;nFpnL"FD#tB&U*O9nX!Q>PM`#)4G@7l)5&tV]ApFG$:\e1<;#cej&`[/'%ghN'Q)E%>JU
cB5]A1A()d\1oSPi:db$h#*B]Aq?BW._SH35i343/))5,K?#:=#5N4bh\;:T1s&2N4S*2XF1oa
+TE^^T7N;[EbOc=F?@]AohUm%\3D+U(Kd#^>*7TO;`K45dQT[[))Sp_bU'U<pGZ:D7^!NCYGN
k4J)V6!1QdV)$NtI^E`S#$Co"h\rEanDDrdg+5]AM[s7n@ekPGBg;,QrmL03p?"GFWe;KjIPh
N<4!Mt=R>j-W]A&Sa+.1G*3[!R]AfklL@l.@n#2m*6&7r3X#*E=c'6^$?T(/gH"L"R)8cj+`qU
Dr>q+k(TFjCKSGf7JeYKCMrYq*!'6`?Ec+2#nAcnDDWiF)t@GKSa-0+tMd-sW2&l,40ogMnm
IY$R#]AfTnF7m,i_&UGZWNURPu+AiYa">dXn^3j?!j>@i,Yf8$u$l=&*n`OAQkD9nW`+b.Ha/
XUR="5@@1;]ALjSrtrT8hMTs*Is?u2(Aa'=-R.@I)XJ$9Q\5$)CS6er$#cA9&P#HU!@3./PB.
agjYKOWP-kP!8Hg%O:]A,m>m=e`H]A=ISEo6Cu-d30:QH`lFNg3(K+WcTZEV"/,lhD7WYp:'/o
`n:SQjlLB[E4BZCf*iG"]AWlc,V??4!eW.no@<>!2B*K;e^iJOqsXgM8DNg8S38A]A3^KpKL+_
B3gG#Xb_q-l-biU'US.V?-()W8Dj<:[o;##t/DU,Z]AXdK3^5)S2l&_lZt<SMdT>=qP)n(!nZ
04a8dog4b2IHt<W+.*8X,`&p*H\0U+P(#M!_?^blbT/]A_+q1.^&`'hO2jt6mrot&P,a;FQBK
(S$YM(6Uo]A`qP7r!Zjmj"4f!F`"R?%s>K/q?=&V_6ikL.oMH<L!.g-N03$2:lp^ZgBHGN397
d;kWq>9Y/i!$[<DN7lC#T;W6'NG91jAmlEPlT7#d:Ots`WN4`s_$r`e,eUT!-=O1D`4emuI)
sdbprR8-+.Q'E&HKYC3T@'3&0tZ9Fo//GlR5RJH@HXAT/JPe:b/D$9"7B__Y_DpdOS17K+A8
3i"t"rZrAs%+Q^UG4\@l"E2b42NBU*[(f7V*p<fbj^X?L[pSPbRfDtN\Wg6-FETZI!fqY82t
Fh[Mm<J8GoMDH$e8jRm4-/n]A5Bf-lj=l0V_\S<d(Y">TD#MnHQS>=p>KPB4H0LL\"V_4WG7f
2]AK"*K:=IJf$cCsXP?i$9&^YRP9_ECCnf%6oAdFi>rpE3uZig"J_bJ#X]A@cI:n!=HPh/?%BX
e?FDr6ou$2-nZPLF:md2'&Fh?hE>oBpqj+(m"<s,PA!0n)1-dchk(tYoj]AsQ5M5Ni(7YJQce
K8pb;D[O8O/`?fQ&Z#JZ=F%S(#>?$%;%Ln("<X^hXr)?)B)`Bh*KH'alI[<]AD:9MN';]AH/ZS
@`G#9!k1ND"c'gF0W6%=tEpmJeMXOd.Vr@87Mh3mhS:M0m!PT%bO7\OMNr+I\_bqA9l))#FR
W9=U^2;l$M*&:WP/b#Fq<Tq#%DpiU@gukViVOVokDRD+#RB04JXo:UsAgYHOZ3@[uC%AK,"[
0IQm^g\:DZUZc!)MDj;JOHab+BnW:ZaCFO*b)X@5SOhh'3nLHCqF*Y4nhu[?MVH(I)$K]A+l+
]A/1BkJ@jSG6b\AI!+)1Z;I,k7g<cN#O"o!5hWp,d1[RN;$579\Z4TE?)g6R3j~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[AQF(9e8$"(Vc(['/X^9<+XC8#L<?Y:CUV5nDofYRXc:1g(_'rq*\uES%8KW`Z%a+-6d3IcP\
&'4_ag8=?0>JM5O\LIIb/Yscgor]ArOd-I@!te1di<1?Cn-4a2pL%.rqLQ\(-d+UDq1UbUp.,
o`#7F]AYmemOB!Y]AF\hfq\<`MArE8F:#J>OA9d=04qp;!B>,#3-Q!m:GRDBuA(%B.lA-dK0bB
K&u_,tdGK%ToT/2b>TdR]A.c]Ah5[KJmtNe8Y;)rNC;*]AThq)ISOu;HD^1)e$X5Q2Hi]A5?omA&
j9n6FjTP+#6L3ZuD6YY7Nek,Q^aDN^qF@cQ3.^A`&'>oRg#na&KsCR+,Y(Psa7pdG@u<Edne
r:Z,KZ^W@AQ'WJ<'GkRB8J<ndrdQ!"1mI6)#LE%5T7qRerseWk6h-AU;nqA^>i-S[hRQJPn_
(s\4TEN9+CFK)VF44-hKF1e-#+!E*kAq/eM)dS3WD<_N!#9C$&$'uNm9]A/\"VQ!r`/Ol%+jA
Y4BbtOZ\+tOWfX".h32n.Vq(@Qam[Mn*CROK-L_tbhqP9@\s-X!C[h#F6h-Le+Hf-Hq%gkno
luur$AX_W.Bi-?AZ0!s;m\/19KdZr+!(m/C9'J3"1k1APTrbI>p+/TGOQSES)7b11N,@/D31
>MAF3P"jln$ZpNmk!o&b:lJ8S;+3:or.IJ4hClH,]AmL[f9*ULK:K=!-ud7=OCqG>B>hq-:6$
Cl4ZmrB1mH%ruu;TiHRGb3MLk!Gq.\9ZP,fie+O_bE+#OoQeKO(R#Z]A&gO3U.j\O\jf:,uKe
^`&V)\75$4kBU)\3otR>[iKc&up$ih>m<S:%9tr,S:;P0YH]AkNDME?52t??DM7=$E,g?9-8S
2B>V==2)4R[bn3U8+oq_tg4GAe0:.o>b^Kk08GUS:O]AHBfE]ABO"\t>Gb(N<"[-0N/eQb3lc5
"c$s4!H>[)>pIBG$9,H)/fl?E\82(=1G8N05p'<YCE[(I1_X/kaQi4?u`f)3\r#!;sR1]AjmD
*qU%L-%21n_PS&O(n%jtYebZ<!!@SUR6?4Z&M`#J7V_-l?/A5@4enB@G_`l5`i2sg;^a%[op
cGiIG+QW^%0EVc)2H!^C[]AJ?(l.-Mo6sn>YB.B,@1dr"Q^^Vtub51asT=TIa&VD/u/HK1jK"
hnH9r7dP_<e@VG"A;@$\Q1=C%0sF!_&oj?C=&<8(!UVd.To4@ocqaHp4Ue7_eCJe?`L!\LB]A
TR]ATD8C^TNrSNe[YkM%qkk,"nDVLTa=$@[VV)WgX\,E+4ECmTJc,j_TL%aSc>03IQ7)5sTCQ
Fiu7X<8!`*K``u$N.fU10Oh>d;&o;`(N=_Xi/H+Di^h[\n#Q<>kr?gDU+'Mgg0Ior1pc!%mM
n\(Vl^$!%"0[V65K^g(=?Qb/@4(5b@#]A@cl,QlcG.s=*l(:FjrM!^8jCnBRihWQjF$K9WG-!
X6@VK(-6Ibou+eKR>!;Vp$isiFSX1(2lrGo#ebqsN^WM@1$Bi`<eGa'2;Oh%=j)G9URnYgO`
=^lXddl6N`rJ#inGg.G"'=@'s\*Z8!KuIN4M)#^f]AQ$p3%EUXIlRRF0jM\p=sB3G(Z4@R'Sd
?F.rs\^$>*B"l#['m8;fA`35A*kEuc7TZW5Y+8-#L;GFEg>`>Hg1`tPgTrlR.(*i`84V<]As^
#]A<+lH,C>)MlZ!dYi;fN\4)&(HWIr4^!3[EO(3LIgC;P5UsjnA>_pm@6l5KbVRo?U-%Un:J^
`<I?!dA";<E<+<cu<ltQ`fKsO*E8JRGL+B<Z_kF>E&%d(PJ&mK6D&6udgmUmpAr;nce&N$MM
j#7kg>G&c`(>m^Xe)FoeXSZ2Dq'-16EKT1h2aRM<JtK`:N^a?#:.lM/"G?q]A//DV8MYj)1LG
K6\3M9qH*r37J>8L"&5O5fE,qRBO&"I;H_FF@<ZO[\Es3]A:I8VNE+$"J7ihT'nn!q5'Uin%*
e5X8HWqrN,?=Y\:8UO/Z-fDQ,iJjTrF$5t0!Bg=4anf>q&#G;:lCg+3!CJ"##h!OVX*,htcf
8@NG/X-ZGK5--4[pIBrHS6Opl$tjY/`3kPl/Sp$-$V:7g_WH]AlJTkpCqotWB<sWhNmd'rdOG
N;ndW)FO^h9ejE7e^.[XZXqR5)DS>FV9_hWU(\LVc*hmcjn-V\kQNe$>T1>L$LG`7@KPni"?
D\'p">J#W.d:`a]ANR6%_VLXU]Ap2E8lS1^8[9N36JG29-N&L*aNe!gEQodlS*haoi6VodCgY6
,GTETR1,ng:9E$l_7#k3:^YD]A7CYbqgX2:g"EZE#[\ABc`hib]A$q1OG!?%UWTHiJugk5BN1H
f_qL;K-^&9<p*<-jV"hsWEtK;ui*NrD,8ZWM\"1qF&qtfuZ:E-I:(A?&C;@udOot:9nY^%WR
aceX%q^nc72Uo8FPI'r)C[f*QojZ&ECF@L3l7qso\5de(_cTS47G62Yl!9F"^F-;%%\G^h-m
h"0W#/<\ffR+e1I<@nQegU<rs?7/:P98U+fU=Wo^Zg(Y^T(`4J)V-DE)WnZQh'CVU`5*6]A4N
LaHu=>Qe,V=Wnrl1KQH9FhY>UO,&7Wr1X<@W"Y$">,E81C=ucUbRZ8.8^W["5R`rZ@qtT(1<
.FWE`QAQ]AOl>S4$1p+@]AY!HCU\[[GI-GI<>,g]A<RN%ah':T0Pa+Ti_)0RBD3!iPYJ_^gAa@c
?IQj1eA@P7JCdmbKTAJa=Zk=S?^s5:ffS]AG:56kdM[5#AXiitJ0ZoVI"%"r6pkF1Pdq3?%-k
10s%#%@N+eMGi4m+aShZq9Xt@-d4UloOTMDMs$b?lc3P#c/>@A1k-M]A8]A0;?WikA`"f@\C9^
(?35da"B+jp9=fZgt#-.&YGY@Jra@UlNI.\f-2qsNO`Jf%Z$`7YKDM^7NrRPGHIFDiZ@Wf-1
<P`$BrmVI<)6Rp5_lVL[^7qD=:G:GbC43M2D-8i"7W2?9R*o%ZFKkt.NQ0WpcBeC4'\sU0nd
N'KFbnTsZp-o`qW2H_lT[MhVKTXS/$K'X$Wq&FFhV;W($D_*@c+:MnSt(>F-o,]A`b1'8,@As
pVH*!"06R&Rcfil?e!tP5f;sM.3(%XED7b]A-,8!??p?W[=a%J58C(IOPq-/O8(Cr%%7GJDAl
6nnlJuALo!SO158F3AL?Dk=u@JFBF<O48.(&]A)AWi;ClO6'7pP;ItP<EjQ*M3/)gSR\A>1r*
H8r50>=BhCluh5@4\VVd5;eN_\$?J^+e/)qpJL>i&:_[N(X_@24T*"7V@=@TLTkZoB7"Gmsi
bq.@bbAi>[q''ZE?9A([rI`'M5>""koA!-e'?(!W4YC%:QS3^"2gdDQ7G4iYDn;!biJsOrIt
8W;i!,*?=^@Fe%$^MW=bn9)lK(_F3;sg#kN<HeC17!62&i-1_!E61_"qS3i((jjeKeth4O*/
>F!oRE<7f4]A-s,*5k(R#QNHGh08@$k@kdbGkm)55g9u1_s):JA/X^l"15Lm5MLjY6jgmFdiL
%+Ioj@#"[i]A[eLYegdA<,Ae)?Id%tXhW.c7K:o9PdMh#_0BZWJk1f5r]A(*!5^\4nmr1P39"@
W250nS%3994=g,ameCDIEXXCuIA54CX2DF9%I9k%luIL%\U\KTu8Rtj_]AD-GFm1!@Rh\']A?C
'Wf>-1,Q-.?5(Fi&2@"I]A83ffrp&YVT/(bNggp.70+5%gYc2DrGZ[R7H<iaUXWeS'LZ]A20<:
oXKf>o!e8N$tKk&iV,32^p>RHOSpn[o68n)NN^P>r_AAD_o:9>^*@I03Y-^n%WN-cPj_oag-
ZC>o)Rjb*A`C,&lU\Ed)S_t9Sc.Br[f0iG?M"Au'X8E_NUN3/r-EgZ:E1Y45oMGS$C7+(Kr/
`0dZr(UWV):S4@h;,0eh*FANAfX=Jr[FTt-(]AaCdB*HpZn$ZVFP2#HXNoH>k^)*/TQKK4VQ(
GSZW=Rn>4D"049#%^<:RN5nHsSQ\fO$[aP7XE$!+^GeLsnjGYu"bIf00UFCqd'@/EULEaK)A
F=?EjYfV2o_IEpHnF/uT3-RBjS;*SP!LC6<]AQuP"O`^B,&*nVG7sEpETN=<1%[5c4GRQ%=g5
;U:kb+PH`m\/"qDo<JeY7coJDd,k/D&`Yi1S.bXcR9*>fK.KV)]AA77BtV^m</d[Y6cc%\oj?
7`*P(d38Ks+Pru-I=V^VsB09XZhSiXI0;If*:+L)c5^BHd?U/j#b'OG2W^6D$<+m`M0TCT9j
CUpg4>%75F(a>^EDjg/jXO^*9(!$0Jec"K9$t?&1n(uu.p_lX8uaU0,Lb.0.BG7A%^'ogFMN
(=G"hX!g<>O.pf)GKD$cSJi04@rm"Y$,jTWk@V,'/FTl;Bo&ko*;!Sti#56R9$fBsfFc'\in
]AX4%=e3W%k9>1@/cX>+0^95o\l>DYJ+8N,f_LrF%=8G$73^&2JqtX1g&NhimEe`'"B))B&Hd
IGNW_A\[8<3te.<pSQX1K[Q4]A4_9PF_R7f$cG4&q,94]ArT[mI)O,NBCE`Vh),gq/Sie!m6:K
k7<[=&!h(FqJE&M(TS'Y-7m)!M-['`OW`dC*ic6u/1L.CjQVX!OQ63;tOlc:+=D\19:lO/B_
dlff1rYOXU?[u,@E\LY-)m<=+8_>mF48CcPdVXPM?H"#IuLCXK?0To;>`T<RXEY(g-!^ueB:
p4&ctn5hg1.T(4`RP%bt_/PKa>"K`rZ%8uE#NLfo$%d#^YT*I2HmWbOqA<eE*PqI8p/5dC(T
7.i6K6RNuP_BJm/Ck8]A#ZLPmL58De23u0:&Z(fO2.AdtKZ"_6J/XRNlo\""[-.Z:;nFX5_Mh
T!#aFmlj=G1H4POc)U7Plq_Y^;.0^CG3WFKfM&BV60b;Qco.b<K$EnNU(T?`!"Ccl>%'PK_h
nSDd<m8u(VlCU$^qj"Kt%-d4rTSN-)^KE7^9WfJ>k>R$Qnmt*X,ru@qkFNtVV9Ur(cRlPZC"
%uaG1l>%>G-XFE%VLD($^XWlfi)@RFe)ruU%f?7(It!oALg]AJ8UGbj*8Aj^\U,#S9nO@cHd4
aQ&\TMLfXQ:cJFXj@ItB0X0`IWZmQ:u;:9=a)%_Y>==$8@Q[II-pPn2N]A**Q%^R#3L2WMAF)
^;B(;JNk4dL%00O=t+lt@W0:TYH]A9`^[Jlt*OO=@Ks=JoQX&m@R%PLnN5FsGFp?9onR7l!ch
K(LL+a>VZXPOreS[hZ[`(_o/dI4J)nRsS]AlGnLjlL#;0)bYUfe`tYV%2n[8,.:hLn+M8^@hU
iF8s"K0)0%tot-:a#6~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[AQF(9e8$"(Vc(['/X^9<+XC8#L<?Y:CUV5nDofYRXc:1g(_'rq*\uES%8KW`Z%a+-6d3IcP\
&'4_ag8=?0>JM5O\LIIb/Yscgor]ArOd-I@!te1di<1?Cn-4a2pL%.rqLQ\(-d+UDq1UbUp.,
o`#7F]AYmemOB!Y]AF\hfq\<`MArE8F:#J>OA9d=04qp;!B>,#3-Q!m:GRDBuA(%B.lA-dK0bB
K&u_,tdGK%ToT/2b>TdR]A.c]Ah5[KJmtNe8Y;)rNC;*]AThq)ISOu;HD^1)e$X5Q2Hi]A5?omA&
j9n6FjTP+#6L3ZuD6YY7Nek,Q^aDN^qF@cQ3.^A`&'>oRg#na&KsCR+,Y(Psa7pdG@u<Edne
r:Z,KZ^W@AQ'WJ<'GkRB8J<ndrdQ!"1mI6)#LE%5T7qRerseWk6h-AU;nqA^>i-S[hRQJPn_
(s\4TEN9+CFK)VF44-hKF1e-#+!E*kAq/eM)dS3WD<_N!#9C$&$'uNm9]A/\"VQ!r`/Ol%+jA
Y4BbtOZ\+tOWfX".h32n.Vq(@Qam[Mn*CROK-L_tbhqP9@\s-X!C[h#F6h-Le+Hf-Hq%gkno
luur$AX_W.Bi-?AZ0!s;m\/19KdZr+!(m/C9'J3"1k1APTrbI>p+/TGOQSES)7b11N,@/D31
>MAF3P"jln$ZpNmk!o&b:lJ8S;+3:or.IJ4hClH,]AmL[f9*ULK:K=!-ud7=OCqG>B>hq-:6$
Cl4ZmrB1mH%ruu;TiHRGb3MLk!Gq.\9ZP,fie+O_bE+#OoQeKO(R#Z]A&gO3U.j\O\jf:,uKe
^`&V)\75$4kBU)\3otR>[iKc&up$ih>m<S:%9tr,S:;P0YH]AkNDME?52t??DM7=$E,g?9-8S
2B>V==2)4R[bn3U8+oq_tg4GAe0:.o>b^Kk08GUS:O]AHBfE]ABO"\t>Gb(N<"[-0N/eQb3lc5
"c$s4!H>[)>pIBG$9,H)/fl?E\82(=1G8N05p'<YCE[(I1_X/kaQi4?u`f)3\r#!;sR1]AjmD
*qU%L-%21n_PS&O(n%jtYebZ<!!@SUR6?4Z&M`#J7V_-l?/A5@4enB@G_`l5`i2sg;^a%[op
cGiIG+QW^%0EVc)2H!^C[]AJ?(l.-Mo6sn>YB.B,@1dr"Q^^Vtub51asT=TIa&VD/u/HK1jK"
hnH9r7dP_<e@VG"A;@$\Q1=C%0sF!_&oj?C=&<8(!UVd.To4@ocqaHp4Ue7_eCJe?`L!\LB]A
TR]ATD8C^TNrSNe[YkM%qkk,"nDVLTa=$@[VV)WgX\,E+4ECmTJc,j_TL%aSc>03IQ7)5sTCQ
Fiu7X<8!`*K``u$N.fU10Oh>d;&o;`(N=_Xi/H+Di^h[\n#Q<>kr?gDU+'Mgg0Ior1pc!%mM
n\(Vl^$!%"0[V65K^g(=?Qb/@4(5b@#]A@cl,QlcG.s=*l(:FjrM!^8jCnBRihWQjF$K9WG-!
X6@VK(-6Ibou+eKR>!;Vp$isiFSX1(2lrGo#ebqsN^WM@1$Bi`<eGa'2;Oh%=j)G9URnYgO`
=^lXddl6N`rJ#inGg.G"'=@'s\*Z8!KuIN4M)#^f]AQ$p3%EUXIlRRF0jM\p=sB3G(Z4@R'Sd
?F.rs\^$>*B"l#['m8;fA`35A*kEuc7TZW5Y+8-#L;GFEg>`>Hg1`tPgTrlR.(*i`84V<]As^
#]A<+lH,C>)MlZ!dYi;fN\4)&(HWIr4^!3[EO(3LIgC;P5UsjnA>_pm@6l5KbVRo?U-%Un:J^
`<I?!dA";<E<+<cu<ltQ`fKsO*E8JRGL+B<Z_kF>E&%d(PJ&mK6D&6udgmUmpAr;nce&N$MM
j#7kg>G&c`(>m^Xe)FoeXSZ2Dq'-16EKT1h2aRM<JtK`:N^a?#:.lM/"G?q]A//DV8MYj)1LG
K6\3M9qH*r37J>8L"&5O5fE,qRBO&"I;H_FF@<ZO[\Es3]A:I8VNE+$"J7ihT'nn!q5'Uin%*
e5X8HWqrN,?=Y\:8UO/Z-fDQ,iJjTrF$5t0!Bg=4anf>q&#G;:lCg+3!CJ"##h!OVX*,htcf
8@NG/X-ZGK5--4[pIBrHS6Opl$tjY/`3kPl/Sp$-$V:7g_WH]AlJTkpCqotWB<sWhNmd'rdOG
N;ndW)FO^h9ejE7e^.[XZXqR5)DS>FV9_hWU(\LVc*hmcjn-V\kQNe$>T1>L$LG`7@KPni"?
D\'p">J#W.d:`a]ANR6%_VLXU]Ap2E8lS1^8[9N36JG29-N&L*aNe!gEQodlS*haoi6VodCgY6
,GTETR1,ng:9E$l_7#k3:^YD]A7CYbqgX2:g"EZE#[\ABc`hib]A$q1OG!?%UWTHiJugk5BN1H
f_qL;K-^&9<p*<-jV"hsWEtK;ui*NrD,8ZWM\"1qF&qtfuZ:E-I:(A?&C;@udOot:9nY^%WR
aceX%q^nc72Uo8FPI'r)C[f*QojZ&ECF@L3l7qso\5de(_cTS47G62Yl!9F"^F-;%%\G^h-m
h"0W#/<\ffR+e1I<@nQegU<rs?7/:P98U+fU=Wo^Zg(Y^T(`4J)V-DE)WnZQh'CVU`5*6]A4N
LaHu=>Qe,V=Wnrl1KQH9FhY>UO,&7Wr1X<@W"Y$">,E81C=ucUbRZ8.8^W["5R`rZ@qtT(1<
.FWE`QAQ]AOl>S4$1p+@]AY!HCU\[[GI-GI<>,g]A<RN%ah':T0Pa+Ti_)0RBD3!iPYJ_^gAa@c
?IQj1eA@P7JCdmbKTAJa=Zk=S?^s5:ffS]AG:56kdM[5#AXiitJ0ZoVI"%"r6pkF1Pdq3?%-k
10s%#%@N+eMGi4m+aShZq9Xt@-d4UloOTMDMs$b?lc3P#c/>@A1k-M]A8]A0;?WikA`"f@\C9^
(?35da"B+jp9=fZgt#-.&YGY@Jra@UlNI.\f-2qsNO`Jf%Z$`7YKDM^7NrRPGHIFDiZ@Wf-1
<P`$BrmVI<)6Rp5_lVL[^7qD=:G:GbC43M2D-8i"7W2?9R*o%ZFKkt.NQ0WpcBeC4'\sU0nd
N'KFbnTsZp-o`qW2H_lT[MhVKTXS/$K'X$Wq&FFhV;W($D_*@c+:MnSt(>F-o,]A`b1'8,@As
pVH*!"06R&Rcfil?e!tP5f;sM.3(%XED7b]A-,8!??p?W[=a%J58C(IOPq-/O8(Cr%%7GJDAl
6nnlJuALo!SO158F3AL?Dk=u@JFBF<O48.(&]A)AWi;ClO6'7pP;ItP<EjQ*M3/)gSR\A>1r*
H8r50>=BhCluh5@4\VVd5;eN_\$?J^+e/)qpJL>i&:_[N(X_@24T*"7V@=@TLTkZoB7"Gmsi
bq.@bbAi>[q''ZE?9A([rI`'M5>""koA!-e'?(!W4YC%:QS3^"2gdDQ7G4iYDn;!biJsOrIt
8W;i!,*?=^@Fe%$^MW=bn9)lK(_F3;sg#kN<HeC17!62&i-1_!E61_"qS3i((jjeKeth4O*/
>F!oRE<7f4]A-s,*5k(R#QNHGh08@$k@kdbGkm)55g9u1_s):JA/X^l"15Lm5MLjY6jgmFdiL
%+Ioj@#"[i]A[eLYegdA<,Ae)?Id%tXhW.c7K:o9PdMh#_0BZWJk1f5r]A(*!5^\4nmr1P39"@
W250nS%3994=g,ameCDIEXXCuIA54CX2DF9%I9k%luIL%\U\KTu8Rtj_]AD-GFm1!@Rh\']A?C
'Wf>-1,Q-.?5(Fi&2@"I]A83ffrp&YVT/(bNggp.70+5%gYc2DrGZ[R7H<iaUXWeS'LZ]A20<:
oXKf>o!e8N$tKk&iV,32^p>RHOSpn[o68n)NN^P>r_AAD_o:9>^*@I03Y-^n%WN-cPj_oag-
ZC>o)Rjb*A`C,&lU\Ed)S_t9Sc.Br[f0iG?M"Au'X8E_NUN3/r-EgZ:E1Y45oMGS$C7+(Kr/
`0dZr(UWV):S4@h;,0eh*FANAfX=Jr[FTt-(]AaCdB*HpZn$ZVFP2#HXNoH>k^)*/TQKK4VQ(
GSZW=Rn>4D"049#%^<:RN5nHsSQ\fO$[aP7XE$!+^GeLsnjGYu"bIf00UFCqd'@/EULEaK)A
F=?EjYfV2o_IEpHnF/uT3-RBjS;*SP!LC6<]AQuP"O`^B,&*nVG7sEpETN=<1%[5c4GRQ%=g5
;U:kb+PH`m\/"qDo<JeY7coJDd,k/D&`Yi1S.bXcR9*>fK.KV)]AA77BtV^m</d[Y6cc%\oj?
7`*P(d38Ks+Pru-I=V^VsB09XZhSiXI0;If*:+L)c5^BHd?U/j#b'OG2W^6D$<+m`M0TCT9j
CUpg4>%75F(a>^EDjg/jXO^*9(!$0Jec"K9$t?&1n(uu.p_lX8uaU0,Lb.0.BG7A%^'ogFMN
(=G"hX!g<>O.pf)GKD$cSJi04@rm"Y$,jTWk@V,'/FTl;Bo&ko*;!Sti#56R9$fBsfFc'\in
]AX4%=e3W%k9>1@/cX>+0^95o\l>DYJ+8N,f_LrF%=8G$73^&2JqtX1g&NhimEe`'"B))B&Hd
IGNW_A\[8<3te.<pSQX1K[Q4]A4_9PF_R7f$cG4&q,94]ArT[mI)O,NBCE`Vh),gq/Sie!m6:K
k7<[=&!h(FqJE&M(TS'Y-7m)!M-['`OW`dC*ic6u/1L.CjQVX!OQ63;tOlc:+=D\19:lO/B_
dlff1rYOXU?[u,@E\LY-)m<=+8_>mF48CcPdVXPM?H"#IuLCXK?0To;>`T<RXEY(g-!^ueB:
p4&ctn5hg1.T(4`RP%bt_/PKa>"K`rZ%8uE#NLfo$%d#^YT*I2HmWbOqA<eE*PqI8p/5dC(T
7.i6K6RNuP_BJm/Ck8]A#ZLPmL58De23u0:&Z(fO2.AdtKZ"_6J/XRNlo\""[-.Z:;nFX5_Mh
T!#aFmlj=G1H4POc)U7Plq_Y^;.0^CG3WFKfM&BV60b;Qco.b<K$EnNU(T?`!"Ccl>%'PK_h
nSDd<m8u(VlCU$^qj"Kt%-d4rTSN-)^KE7^9WfJ>k>R$Qnmt*X,ru@qkFNtVV9Ur(cRlPZC"
%uaG1l>%>G-XFE%VLD($^XWlfi)@RFe)ruU%f?7(It!oALg]AJ8UGbj*8Aj^\U,#S9nO@cHd4
aQ&\TMLfXQ:cJFXj@ItB0X0`IWZmQ:u;:9=a)%_Y>==$8@Q[II-pPn2N]A**Q%^R#3L2WMAF)
^;B(;JNk4dL%00O=t+lt@W0:TYH]A9`^[Jlt*OO=@Ks=JoQX&m@R%PLnN5FsGFp?9onR7l!ch
K(LL+a>VZXPOreS[hZ[`(_o/dI4J)nRsS]AlGnLjlL#;0)bYUfe`tYV%2n[8,.:hLn+M8^@hU
iF8s"K0)0%tot-:a#6~
]]></IM>
</FineImage>
</Background>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[1143000,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[8138160,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[XX公司人力资源分析]]></O>
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="144" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9FF+;cak`6lJNKi3ZO%$!AgGF]Au#*fID`@)7;m`'usM!8<G<#C)&Jo#TtD/U+RH-e#K&n+Z
_)d#nf[5njn-bWC6p3(n^q-@uM[(KG\1TF(;+_lhrRHa#/Str87hCSt(/Qfk9dPIGY>mQ7NL
`1]A"6$:/4Po(+tuheB)D=ccO:3m.qXkQeDr2Hmbhrb(]AHeZ7P*&DqA@2r\ANCT<F?SKO*8R\
)W%;aZ&fd$RLSi5/$@Ah!g"f9.qS"2Pk@u0MtJYCO=Om)a5`3Q!9t2B^=)7>7JN<Cq]A1#h%k
(kL:a!.[>,CW.CP)R'Y[;1RCM46T(E-_*i[\aNg$_LB@fmc1#X_=hgbBn(G@im7d)c!WLf0C
^ubm'\WjDjX(OWnDWaL*FCFl2J+qiEYs!Z9$MObG[N>9N(Y]AVd0hi;_f@3TcJb$[KklQKn@d
.MOB\W%+4%_/'If\Z#e4]AZCc>BokR[D(kY3KZLC3PS[^!/!$/s7be#^;3e'>%7!f@Q=R,R/J
*STBp`p5'tHcC.s.i.[>U%k4o/2E\?!=ZlY]A7Wa6XQ>mrVfGCPCY$%o@]AcbusZ?GK@&Ue9`e
44(RQ&9:$1QfZQN"i-E>hL^#@\#ZggKK')okrsLX/W/&l\^h0RL@_Z84-DAqqKSc.elK`5mD
2`qJLZ`11nM>_/n)n9s'C.:+U-%Cj<ECbi3YjAc.M*meC-MIqkjI[N6$I4S9"BN43i3q5MEu
K'BB8C]A3'LB#PjRmLnnq>PHVVVCq30.j9[)9MWV)X[fre,B8#EimWnW`C[n<Y+Kc?,;gjVjE
^0#+)EB5^sZukMY?N.G:-QOURn&FEFhV,D9[YCo3s,,@VnSBR:bh9e\XXghraN@?Kd10]APus
1A]ATmep@LiSK7jYlbZQ^RE4iF9K=9Kaj-mC!V))=IS9LujrhW[T,2`cu:6[*IUQAfUW@R_3;
Vrlg?C7NhE"kX$\93(!mqcP(pu8<JX83[tg'6:i24(Gi$2]A\"1+?+MVR/+b=qF(9f1uc+q(5
j5[2u:$Z5B![!eKgY^h;Spo57u.j8"%gGDf[XBG4m/g#KP)$XrQBS6sL:^&@+tSq1iR/R?]A*
9X)Sdf%&FS"U^:?<"^=D&<i+OdTGtFRnrD3)ksh'"B92Q&HC,$Hj8aGUfa-/]A6kAGWSRsK@'
RaunOP1_BEpr`MPh*b"__gI)hm>7Nk^-c)W.'n"WK(7gPBmMga8Q1Pfd6GSseS4[S-<'p9f?
^Zm4r8nOA'b"nV(XS41=hHMQ$Id:6.^n4;.RoWH'3=@m9+-@p4n0T?d:6S)B*BkeDpc747Yd
Ml`houW>s"_Ul*(;5'e0XCNSj0XL6;9q;OBm.0bh0@(l:kR3$-;fkj`_>b,ig?3q#7(hc]ARR
T,EC9GLpn7WW8`S+4#cl^ZJ3.jA.H,*Ii`7VlR9fGRA1'HheWF/uW?#hN1*8X/_TSb%"^cIY
6sV27\ppHSVV?dq-dZGW=ig2'f6)7%mnRsk"GVct6-`\PFG7QO$+4EkC)f/cUm0n^d^0W+d6
8%GqZRXr5=.(<_Ye#^^n`L^*kc[r.gaCWL`e_d;!1EXq"3*^"-)]A#'<.g9cSU2]AQ7pbA52am
[L-G?c0(','_jj.TY0@(b-3Ru[.Tsirl<FN&j)GiWPa\Wn(q12\eCD#K_ei7PcqdC0ap9i5L
9O(i&0^O*_t_KM&OcT`_C;84W'*rMZF0X<Bm3:oVl[Wf)BID\o/oT$9PFasgGX=RR9?-K5G0
X_IJhI$o=e!BF9Ja%BFPiF4jAdlTl51@g%D4_]A9?`V%N\)_'r1'AbEEVF4B?&Y`oEmN)1a9)
LH`k;`+4G*pD1dP*B["@Sgs$/YeJCt(Q8HQlk`GQe0BAUa/qP?#Q#k:)\D5!YN0Md?5do^1(
N[@^nA/#N-*;I`f25"l9k:0COif1[!O7):+m8dn1]Anl>;>S$#7;h(W"@j1h\1FH2Ml$aV`?*
8@.?@5Q-d5>Q[aUs+lAcU*"5Xhr$L`hA'\G5+L?MQ%ZtSjn-X]AFVInd1e8/WF;d:TmPR$d3K
`Dl;F))omn^:QX"G`&jNUCAKC!UXmZ"a[`a#4Mogpe(EZ;sGZq#TP3jZ#KI>h@h4&C'I[cD=
6\%]A-Wj/,n+c=;n%HJ?0!@9H:<n%W2l\CZP)Wcpt_)SVcPWG7?UX"jtQ';j+LTAfY*!U-qsa
$M!9eSc).U]AM?K5+VXcHB9E<Y26>KGS?,8*I6rlI)[L3*r6^R+?m4*C?jT)SO-D/1j"^Y='[
qV1af[%iK_U,LdE]AmiJF6-lj#1*u4!@s=EqL=@DE`0OP9<u*J54cjf;><F8ZS^[,n;,jH_SB
(,=Q->BYWcqA\6/-jd:jULI'-jY3b*PYQuTa$n8+AMQe*sM"'@*OfKADn3dIj<VIp'kn)4F9
BY/cg"*=dI-sH/G"f(L._QBmlD0o:pYs%5V5!90WesC")#Qi`jSl$V"j[cD^Yl(6_$u/r(2P
gl6&>,*`J+&53Xn$OoEC(0B+D\jmsrF@M&^0[-IlTLj!WV,`e.D6a%]A.E>f<RQ"^@loB:/96
EU4(1'o%+nN;jlC8-1r-XNX=\(5(//bh:qm>&>NUp]AGd/Xc]Aj@ECJeI,]A#AA,/rXQQZq.L@n
-#$6XO%T`1A@<"=c/2:dM')qU?$4E59b&G*3i*F)oEZ[i,LE`nQ&,OOI#!`quR<:8_5FRk=I
!,/&aV7UM\hp**]ABgDD$N+8jJ%g8Z&9>MJ\MZ7]A<A!%=AAMLe1?7LLlu(I6HE*"M^9e@2cLj
gP]ATQKgUN1`R'38`r;(\FNQL%HEA<qcVF>q>!SVm(hTO-7!skB/OZIFh@"^2=CA4ggr$(G8O
H/2;nFpnL"FD#tB&U*O9nX!Q>PM`#)4G@7l)5&tV]ApFG$:\e1<;#cej&`[/'%ghN'Q)E%>JU
cB5]A1A()d\1oSPi:db$h#*B]Aq?BW._SH35i343/))5,K?#:=#5N4bh\;:T1s&2N4S*2XF1oa
+TE^^T7N;[EbOc=F?@]AohUm%\3D+U(Kd#^>*7TO;`K45dQT[[))Sp_bU'U<pGZ:D7^!NCYGN
k4J)V6!1QdV)$NtI^E`S#$Co"h\rEanDDrdg+5]AM[s7n@ekPGBg;,QrmL03p?"GFWe;KjIPh
N<4!Mt=R>j-W]A&Sa+.1G*3[!R]AfklL@l.@n#2m*6&7r3X#*E=c'6^$?T(/gH"L"R)8cj+`qU
Dr>q+k(TFjCKSGf7JeYKCMrYq*!'6`?Ec+2#nAcnDDWiF)t@GKSa-0+tMd-sW2&l,40ogMnm
IY$R#]AfTnF7m,i_&UGZWNURPu+AiYa">dXn^3j?!j>@i,Yf8$u$l=&*n`OAQkD9nW`+b.Ha/
XUR="5@@1;]ALjSrtrT8hMTs*Is?u2(Aa'=-R.@I)XJ$9Q\5$)CS6er$#cA9&P#HU!@3./PB.
agjYKOWP-kP!8Hg%O:]A,m>m=e`H]A=ISEo6Cu-d30:QH`lFNg3(K+WcTZEV"/,lhD7WYp:'/o
`n:SQjlLB[E4BZCf*iG"]AWlc,V??4!eW.no@<>!2B*K;e^iJOqsXgM8DNg8S38A]A3^KpKL+_
B3gG#Xb_q-l-biU'US.V?-()W8Dj<:[o;##t/DU,Z]AXdK3^5)S2l&_lZt<SMdT>=qP)n(!nZ
04a8dog4b2IHt<W+.*8X,`&p*H\0U+P(#M!_?^blbT/]A_+q1.^&`'hO2jt6mrot&P,a;FQBK
(S$YM(6Uo]A`qP7r!Zjmj"4f!F`"R?%s>K/q?=&V_6ikL.oMH<L!.g-N03$2:lp^ZgBHGN397
d;kWq>9Y/i!$[<DN7lC#T;W6'NG91jAmlEPlT7#d:Ots`WN4`s_$r`e,eUT!-=O1D`4emuI)
sdbprR8-+.Q'E&HKYC3T@'3&0tZ9Fo//GlR5RJH@HXAT/JPe:b/D$9"7B__Y_DpdOS17K+A8
3i"t"rZrAs%+Q^UG4\@l"E2b42NBU*[(f7V*p<fbj^X?L[pSPbRfDtN\Wg6-FETZI!fqY82t
Fh[Mm<J8GoMDH$e8jRm4-/n]A5Bf-lj=l0V_\S<d(Y">TD#MnHQS>=p>KPB4H0LL\"V_4WG7f
2]AK"*K:=IJf$cCsXP?i$9&^YRP9_ECCnf%6oAdFi>rpE3uZig"J_bJ#X]A@cI:n!=HPh/?%BX
e?FDr6ou$2-nZPLF:md2'&Fh?hE>oBpqj+(m"<s,PA!0n)1-dchk(tYoj]AsQ5M5Ni(7YJQce
K8pb;D[O8O/`?fQ&Z#JZ=F%S(#>?$%;%Ln("<X^hXr)?)B)`Bh*KH'alI[<]AD:9MN';]AH/ZS
@`G#9!k1ND"c'gF0W6%=tEpmJeMXOd.Vr@87Mh3mhS:M0m!PT%bO7\OMNr+I\_bqA9l))#FR
W9=U^2;l$M*&:WP/b#Fq<Tq#%DpiU@gukViVOVokDRD+#RB04JXo:UsAgYHOZ3@[uC%AK,"[
0IQm^g\:DZUZc!)MDj;JOHab+BnW:ZaCFO*b)X@5SOhh'3nLHCqF*Y4nhu[?MVH(I)$K]A+l+
]A/1BkJ@jSG6b\AI!+)1Z;I,k7g<cN#O"o!5hWp,d1[RN;$579\Z4TE?)g6R3j~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="13" y="0" width="1174" height="83"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8s0l'6i$)omN[?<nLGWZs;%E.TMCK7>qjMZrW;*+]AoGDgK61!$kP+5%:O"`PuRt"!=FD2'p
Tu)X?rY4OOPK,!_JV\8chR7RV8jYO:96mbXUT0GCS9F_"mAfo/t>ZhjMf'cLX1>)\]AJed0GT
bgt\2`meZhh2n&qp2n(`MD_6'CA:M^]AEH>?m.e<1JHOC\%8`X?/0)^PFoV=i(l3Ps`pg64?q
g!>(L$5/i>6CcoBD*p)E3-TbgX3E1PIuCtop/\KRcW`4Z7U7]A49"!CrFg]ATXZ*dOiRQ!K]A<U
Xk)csYU,b%S:YF=&-a!Vl<=5\iAl`Wj:$"E2YgGB:*A`^\Y6S!11NIr0rRPi[`c)0[tk9CRH
>r?'!V'A>CRns5HF?EuX`?CZ/CN5i\)W#Cr2+X-KAAX8P[WKR7Y$kWN^J:1PX4rGt'YE'>B&
uM20G"sm\@"@9/KIQPQH!9e]A?q!*FYcl^aBJ7h@=5o->0G$diL(*_FA(AO[&!:P82l@ISsBi
_X77Q.<$hfsEq@4G"O"!ROBLKY*0%r`L8t\u=+JA"LTu$d!Gb-6f)m/"P)tl1RklBS>[KJ1I
[ZJ<jHN?mIN5HV1QG*p.agr=I2cB#J+_PF-!/meSYg4\^khMpp5drKKel3#2JmAgVB$UR8Ou
Mh:Hn\U6No0SN&m.[EJS%$oCr#O`$EUGiiHhY,4Arp8OM$F:,JqLbdmKi*[i(,fo4h=/YWSY
k`eYqhWo#58rGOBGl;lsoeC?O(Sj%C]A(AVoF*Qp3)L9E+!GmsBIkg_$YGm5^>>*>`/8"IB:5
jo8\L#4iSnGR$jtU_W\aaX3;JQ,!RNnlXCt@-K5#$QZ(QV;32W)X-<B`rLU]AI>q)\Rjq:gB;
t/Z83G-bjMol^A_B]A\:/^")[&GrfHhi]A;7/0^L()gD+?3:I"FYM@`,40UXXa05(nSiCCYrMF
uL/*:7p_\,T\Zl/YB=jQ4oa6]AqPJXh8/P!/>&(Pq'\6%?!4aEAHMbsk"o%n\;<qB?>7Rta#g
3j,t(2d#G5[W`Don/Cfeuk<=7H^)kiFPG#_8&4Lr/e!0.RTq&uKJakPJAJD;C1Ls5N*agc^B
Qr$0RDWNGp"$%$'&uaN5oFRdlX_LI4`6XO%aeopM_B.g4.@,m*5AtIL0S/JPAt!lr3H$3Tg3
4g\%go4`ZiFfZAPJBt=C6?O&s#!OVV!ts2e"mZ1StK`GSfAW**jRd0+[.;:9g*^<>SdshBVS
(>'oFmclI4,;BA-AptRj\%f/l(qfn1**7kqi\+XRZ\^V5Wi*uH=dUS_m,M$==<p6C*r9c^:7
\Ok^L%;t1&C)]AX@'*G9;]A@5`cN\&P%/m3r7dSO/G,!YJg42J*GNPtO@*(iaEDmHo6qj+jguP
4:I)5t,(t#HVlpe[^o;;nJ34e-DmVOZ^:Rn\q)CRW>mo+bN'JL[6\g_(d+-R>(N+qS</&fFh
qKf#JBsM3="KdDOeb2tErVEJ+kr<o=(VQeEXR8QQF&7VFOa($pW3:R1!5]AJ+dF_%KX:4HW6@
1:MD&05+/j=kD2I#mql,746!AXfb&Y-:o2HhTb/2a2GeRa)RMk-@Gb)W6R1(:Dio]Am-&ERBM
N."9Z3+CHE#b>pj.49fr,C\H,-3FIGa%i\-.g".fXX_Vj`A_k986I<-51'cjC`"ouW-:Lj>#
DQIA/LE']A=+aFGYaW(&g1m'B.*/&b%beWZf!S'r:s9Db5<,:*RRLR$kR`S34q^h*l%5:Ye\4
X@aX+e$\V<-r6CWuHo,H.#q:i_i"UN9`#08LjkH&E8_:'M?gHMc6!k\dcNSn2W.LN,qBd>$`
91%\tRGKFtkt7\.,hL:?RKqjr)hnHZC=?IgfFCDl8N<(cQ;gCr;WVMi2X(7-2)[@@2`.-kC\
ud$#=D'VQC$b26GpM*#%4&3bm0^c`n4"PTGCQ]A%uI-^)RbuTj^H`!jd>hqd*R0aJTu^"9Nu^
0U]AL+N`l<DF4V>#i`HjF%At)>mLO#Z4PAkn5^S2LCUIt;E9sDUG"D!NfE"1pF7Bl6L]Adfns*
s<E96lAj4X'Cr5m$6_Z$k=TW9"X&;%mlfoRjGQX1K36?8dUPtflmr<=bG:/!gnN;7^UBIn2N
[#-'E@rh"5Y"h]AKPM)VkJQi%^?(G<CW?Lgo?o6RCY!FV:ZeOA1<_'=KUn`",)3W6#En<;Rr`
iX?Itn5`Q[p'6UC#j:WlqF%\dFY=KVl3YqQHcRI0MBo4N]AeYEfoTDb4rS5FoIUc*dkM&6/5U
02VF?Ls^<(88__2#N8Fmc.1([)+tH]Ac\llFsK0(pnu3ag*MO):<gA"0%eQ7urt7aPoRTI]A\7
_<r'j:9p,=uA$sha9`9k1r8t@Rf5>EqN)pelL,mC@6Q[?"km0^To^u]AAVn?a5&o\l.p*P(jX
2r:i`HbV<_MZQdM427q>2hhFqtb9dduHVk-k955478H)p$A%+EVT!,p-TGbhEKs9m%'LkC==
ic8\73\5Bk6&%&$'AQ!SXk=i)"h&?n4Q[k.%n)]AfpRD]A@7:-[Db1LEM<GM;ZX"&-%#lVk:fO
Vm(:qXj('m7cdqg?=fH]AR6kQiXR]Ar3hpQ2HLqIKIUO$A-[4Ru!+PtVN78!>Z0GMn9MF$]A49\
XJW1Lu=j2,nOQoC&OsE:EX+$!]Ap\Cf5$2IWuIJnK8^hs&Q4>'7\d.D)e"G%\Z8S"G5YAHD\c
u\(7.5UA$6/Si.Q(^3t1-'9=12gD&sE8WCE72568[h;s"Y-m7i!9=GZMN;aD<6I.q".5K"^F
RGq?%Z?&+<2Vj>#kWm';G;RsGe9AJaP>k@JJ!n7N'l1k5=rk2N:-@%j0cD%3"_Y)+DYrVRb>
>JJrAT,$mh>lJhYcc9ka-L(/q;`(0Y#-W(2OG6E^M2p'A[<&W58M(D-M$7;7kiCo2<TM<nUQ
335NCj,2R)TC3gd6^7rZZj!IgLEO!^4J?5DWnM=@9XBuj3Wcn^]A6!1;e@*&!KQ2'@n@89p*o
CdG+1GnFdeuK^]AD6YqNr91..0HFe)>FJleeiqI8T[#f2Nl(:3J%%h14/Co73I<O[@G8'"HRR
,h\uj%?iC$a/Cd[:r.9c;$i6V[#m2kmWSXkFg1((;f<7i/_+%Z%`'c2M6"E2a"E?K$8IBcXN
Z`:1I5VFc9<M`AWY3MfCgs"sWf5hO+V*FkFc:]A9&(q1VO60Za+u.6jg4<`V7m5WL`hXm*:tV
%iAniPLi44CdVM6<1<Q54Wq`'J.RgjPp]A+:t*TF4&H_pO9qEa@$b_m_`6'jQ.4#m._fcQEO,
$H2/eWdLW/%;2PV!.Ap!V;*?+2`'\XlqDeDS-Bct^14AS<^-=\k,Xi[n8Z/NGh\Y]A<'DSo)C
V7,3oYAlgt]A>U/MkTl>[]A%ui`F6mRNMOZdQ1]A85To`q55'-9h[Ch(1m3)L8T:3I_\p3$QinL
7@E=[,#.WS22I'hSs/MsPa#V?C1VtRbY:u>q'VDnX:/<)o$XX>%^ZnkPmUa?"5J?)D?c(7J0
i1;E48u@<0[-]AF[H@"jrW:0pm+j`6hRJS^=Xd-VYiB$Q?r8;l.?,ui(UuO;qK7baffg%0Lmq
T/RP\2QgO0j'#G'.a7W0<45-MM-/GZOgpk.$q]ADWmRY&tedH`Kf_+FK#OV(.tTIS@kQQT46(
`lqqukGS/gkrbFihssk#.9pe#o/u-kCW^G8CW^G8CW^G8J#D)Xab0"n>^s/kIX8PPQpGBI"e
OO_Q9!QgjlCrMYgNGec0/[7pAM/b;-PL8P:=*1$Ll?RpqsG'5[5BdfZm[s.OI3[N(C"ZFm8@
A%b,9#m8U[re-<H5l0it(H]AKWhYg:1??<Qa.aLng[TIKc%.)W[s,k'ZJJ_W>i6'X"c(Oa@`+
n2O0maYp4G]AtL@"g7Fn.3Rhr&n?TU>e>IjQJdG"\J+[,=3P_($_UVaiEFRo1h"OTjFX*&_ti
TJQaR"[,ZVF_693c8WU<a1f<2>=dsLHrI=-TRR;5R\Xaa+gdd2hYr7sBSXYV7Ul)2C_5(r5#
b)oYW3).D'Qi,CjLW_*r7QKm87ORO9P8rYiHX27DRKa^'X:#0CXbtF--VVaE-P`X7OM7TcX*
'fN9^DoSIOc3b"PBc.aE2jArk#`bL++XY?$m4[MQZC%3/%soh>drWe`POc4a2@h+^.U_\X3K
>n]All1.&WG3"GgiJ4+`m4!p[=i_Th/c>4\$7F:6]AILp8oig;KV_hhY#8e*l[VYAlDqW3S,b;
5Yi!^fGFP"0MF=bu:J2k#>K"K&bI6o\PM-)ko10j_=?fG6$:t(]A<H;+.Vp2g?.o"&m3]AOD!r
.M,Na(0B8=4s#6~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8s0l'6i$)omN[?<nLGWZs;%E.TMCK7>qjMZrW;*+]AoGDgK61!$kP+5%:O"`PuRt"!=FD2'p
Tu)X?rY4OOPK,!_JV\8chR7RV8jYO:96mbXUT0GCS9F_"mAfo/t>ZhjMf'cLX1>)\]AJed0GT
bgt\2`meZhh2n&qp2n(`MD_6'CA:M^]AEH>?m.e<1JHOC\%8`X?/0)^PFoV=i(l3Ps`pg64?q
g!>(L$5/i>6CcoBD*p)E3-TbgX3E1PIuCtop/\KRcW`4Z7U7]A49"!CrFg]ATXZ*dOiRQ!K]A<U
Xk)csYU,b%S:YF=&-a!Vl<=5\iAl`Wj:$"E2YgGB:*A`^\Y6S!11NIr0rRPi[`c)0[tk9CRH
>r?'!V'A>CRns5HF?EuX`?CZ/CN5i\)W#Cr2+X-KAAX8P[WKR7Y$kWN^J:1PX4rGt'YE'>B&
uM20G"sm\@"@9/KIQPQH!9e]A?q!*FYcl^aBJ7h@=5o->0G$diL(*_FA(AO[&!:P82l@ISsBi
_X77Q.<$hfsEq@4G"O"!ROBLKY*0%r`L8t\u=+JA"LTu$d!Gb-6f)m/"P)tl1RklBS>[KJ1I
[ZJ<jHN?mIN5HV1QG*p.agr=I2cB#J+_PF-!/meSYg4\^khMpp5drKKel3#2JmAgVB$UR8Ou
Mh:Hn\U6No0SN&m.[EJS%$oCr#O`$EUGiiHhY,4Arp8OM$F:,JqLbdmKi*[i(,fo4h=/YWSY
k`eYqhWo#58rGOBGl;lsoeC?O(Sj%C]A(AVoF*Qp3)L9E+!GmsBIkg_$YGm5^>>*>`/8"IB:5
jo8\L#4iSnGR$jtU_W\aaX3;JQ,!RNnlXCt@-K5#$QZ(QV;32W)X-<B`rLU]AI>q)\Rjq:gB;
t/Z83G-bjMol^A_B]A\:/^")[&GrfHhi]A;7/0^L()gD+?3:I"FYM@`,40UXXa05(nSiCCYrMF
uL/*:7p_\,T\Zl/YB=jQ4oa6]AqPJXh8/P!/>&(Pq'\6%?!4aEAHMbsk"o%n\;<qB?>7Rta#g
3j,t(2d#G5[W`Don/Cfeuk<=7H^)kiFPG#_8&4Lr/e!0.RTq&uKJakPJAJD;C1Ls5N*agc^B
Qr$0RDWNGp"$%$'&uaN5oFRdlX_LI4`6XO%aeopM_B.g4.@,m*5AtIL0S/JPAt!lr3H$3Tg3
4g\%go4`ZiFfZAPJBt=C6?O&s#!OVV!ts2e"mZ1StK`GSfAW**jRd0+[.;:9g*^<>SdshBVS
(>'oFmclI4,;BA-AptRj\%f/l(qfn1**7kqi\+XRZ\^V5Wi*uH=dUS_m,M$==<p6C*r9c^:7
\Ok^L%;t1&C)]AX@'*G9;]A@5`cN\&P%/m3r7dSO/G,!YJg42J*GNPtO@*(iaEDmHo6qj+jguP
4:I)5t,(t#HVlpe[^o;;nJ34e-DmVOZ^:Rn\q)CRW>mo+bN'JL[6\g_(d+-R>(N+qS</&fFh
qKf#JBsM3="KdDOeb2tErVEJ+kr<o=(VQeEXR8QQF&7VFOa($pW3:R1!5]AJ+dF_%KX:4HW6@
1:MD&05+/j=kD2I#mql,746!AXfb&Y-:o2HhTb/2a2GeRa)RMk-@Gb)W6R1(:Dio]Am-&ERBM
N."9Z3+CHE#b>pj.49fr,C\H,-3FIGa%i\-.g".fXX_Vj`A_k986I<-51'cjC`"ouW-:Lj>#
DQIA/LE']A=+aFGYaW(&g1m'B.*/&b%beWZf!S'r:s9Db5<,:*RRLR$kR`S34q^h*l%5:Ye\4
X@aX+e$\V<-r6CWuHo,H.#q:i_i"UN9`#08LjkH&E8_:'M?gHMc6!k\dcNSn2W.LN,qBd>$`
91%\tRGKFtkt7\.,hL:?RKqjr)hnHZC=?IgfFCDl8N<(cQ;gCr;WVMi2X(7-2)[@@2`.-kC\
ud$#=D'VQC$b26GpM*#%4&3bm0^c`n4"PTGCQ]A%uI-^)RbuTj^H`!jd>hqd*R0aJTu^"9Nu^
0U]AL+N`l<DF4V>#i`HjF%At)>mLO#Z4PAkn5^S2LCUIt;E9sDUG"D!NfE"1pF7Bl6L]Adfns*
s<E96lAj4X'Cr5m$6_Z$k=TW9"X&;%mlfoRjGQX1K36?8dUPtflmr<=bG:/!gnN;7^UBIn2N
[#-'E@rh"5Y"h]AKPM)VkJQi%^?(G<CW?Lgo?o6RCY!FV:ZeOA1<_'=KUn`",)3W6#En<;Rr`
iX?Itn5`Q[p'6UC#j:WlqF%\dFY=KVl3YqQHcRI0MBo4N]AeYEfoTDb4rS5FoIUc*dkM&6/5U
02VF?Ls^<(88__2#N8Fmc.1([)+tH]Ac\llFsK0(pnu3ag*MO):<gA"0%eQ7urt7aPoRTI]A\7
_<r'j:9p,=uA$sha9`9k1r8t@Rf5>EqN)pelL,mC@6Q[?"km0^To^u]AAVn?a5&o\l.p*P(jX
2r:i`HbV<_MZQdM427q>2hhFqtb9dduHVk-k955478H)p$A%+EVT!,p-TGbhEKs9m%'LkC==
ic8\73\5Bk6&%&$'AQ!SXk=i)"h&?n4Q[k.%n)]AfpRD]A@7:-[Db1LEM<GM;ZX"&-%#lVk:fO
Vm(:qXj('m7cdqg?=fH]AR6kQiXR]Ar3hpQ2HLqIKIUO$A-[4Ru!+PtVN78!>Z0GMn9MF$]A49\
XJW1Lu=j2,nOQoC&OsE:EX+$!]Ap\Cf5$2IWuIJnK8^hs&Q4>'7\d.D)e"G%\Z8S"G5YAHD\c
u\(7.5UA$6/Si.Q(^3t1-'9=12gD&sE8WCE72568[h;s"Y-m7i!9=GZMN;aD<6I.q".5K"^F
RGq?%Z?&+<2Vj>#kWm';G;RsGe9AJaP>k@JJ!n7N'l1k5=rk2N:-@%j0cD%3"_Y)+DYrVRb>
>JJrAT,$mh>lJhYcc9ka-L(/q;`(0Y#-W(2OG6E^M2p'A[<&W58M(D-M$7;7kiCo2<TM<nUQ
335NCj,2R)TC3gd6^7rZZj!IgLEO!^4J?5DWnM=@9XBuj3Wcn^]A6!1;e@*&!KQ2'@n@89p*o
CdG+1GnFdeuK^]AD6YqNr91..0HFe)>FJleeiqI8T[#f2Nl(:3J%%h14/Co73I<O[@G8'"HRR
,h\uj%?iC$a/Cd[:r.9c;$i6V[#m2kmWSXkFg1((;f<7i/_+%Z%`'c2M6"E2a"E?K$8IBcXN
Z`:1I5VFc9<M`AWY3MfCgs"sWf5hO+V*FkFc:]A9&(q1VO60Za+u.6jg4<`V7m5WL`hXm*:tV
%iAniPLi44CdVM6<1<Q54Wq`'J.RgjPp]A+:t*TF4&H_pO9qEa@$b_m_`6'jQ.4#m._fcQEO,
$H2/eWdLW/%;2PV!.Ap!V;*?+2`'\XlqDeDS-Bct^14AS<^-=\k,Xi[n8Z/NGh\Y]A<'DSo)C
V7,3oYAlgt]A>U/MkTl>[]A%ui`F6mRNMOZdQ1]A85To`q55'-9h[Ch(1m3)L8T:3I_\p3$QinL
7@E=[,#.WS22I'hSs/MsPa#V?C1VtRbY:u>q'VDnX:/<)o$XX>%^ZnkPmUa?"5J?)D?c(7J0
i1;E48u@<0[-]AF[H@"jrW:0pm+j`6hRJS^=Xd-VYiB$Q?r8;l.?,ui(UuO;qK7baffg%0Lmq
T/RP\2QgO0j'#G'.a7W0<45-MM-/GZOgpk.$q]ADWmRY&tedH`Kf_+FK#OV(.tTIS@kQQT46(
`lqqukGS/gkrbFihssk#.9pe#o/u-kCW^G8CW^G8CW^G8J#D)Xab0"n>^s/kIX8PPQpGBI"e
OO_Q9!QgjlCrMYgNGec0/[7pAM/b;-PL8P:=*1$Ll?RpqsG'5[5BdfZm[s.OI3[N(C"ZFm8@
A%b,9#m8U[re-<H5l0it(H]AKWhYg:1??<Qa.aLng[TIKc%.)W[s,k'ZJJ_W>i6'X"c(Oa@`+
n2O0maYp4G]AtL@"g7Fn.3Rhr&n?TU>e>IjQJdG"\J+[,=3P_($_UVaiEFRo1h"OTjFX*&_ti
TJQaR"[,ZVF_693c8WU<a1f<2>=dsLHrI=-TRR;5R\Xaa+gdd2hYr7sBSXYV7Ul)2C_5(r5#
b)oYW3).D'Qi,CjLW_*r7QKm87ORO9P8rYiHX27DRKa^'X:#0CXbtF--VVaE-P`X7OM7TcX*
'fN9^DoSIOc3b"PBc.aE2jArk#`bL++XY?$m4[MQZC%3/%soh>drWe`POc4a2@h+^.U_\X3K
>n]All1.&WG3"GgiJ4+`m4!p[=i_Th/c>4\$7F:6]AILp8oig;KV_hhY#8e*l[VYAlDqW3S,b;
5Yi!^fGFP"0MF=bu:J2k#>K"K&bI6o\PM-)ko10j_=?fG6$:t(]A<H;+.Vp2g?.o"&m3]AOD!r
.M,Na(0B8=4s#6~
]]></IM>
</FineImage>
</Background>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList/>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8s0l'6i$)omN[?<nLGWZs;%E.TMCK7>qjMZrW;*+]AoGDgK61!$kP+5%:O"`PuRt"!=FD2'p
Tu)X?rY4OOPK,!_JV\8chR7RV8jYO:96mbXUT0GCS9F_"mAfo/t>ZhjMf'cLX1>)\]AJed0GT
bgt\2`meZhh2n&qp2n(`MD_6'CA:M^]AEH>?m.e<1JHOC\%8`X?/0)^PFoV=i(l3Ps`pg64?q
g!>(L$5/i>6CcoBD*p)E3-TbgX3E1PIuCtop/\KRcW`4Z7U7]A49"!CrFg]ATXZ*dOiRQ!K]A<U
Xk)csYU,b%S:YF=&-a!Vl<=5\iAl`Wj:$"E2YgGB:*A`^\Y6S!11NIr0rRPi[`c)0[tk9CRH
>r?'!V'A>CRns5HF?EuX`?CZ/CN5i\)W#Cr2+X-KAAX8P[WKR7Y$kWN^J:1PX4rGt'YE'>B&
uM20G"sm\@"@9/KIQPQH!9e]A?q!*FYcl^aBJ7h@=5o->0G$diL(*_FA(AO[&!:P82l@ISsBi
_X77Q.<$hfsEq@4G"O"!ROBLKY*0%r`L8t\u=+JA"LTu$d!Gb-6f)m/"P)tl1RklBS>[KJ1I
[ZJ<jHN?mIN5HV1QG*p.agr=I2cB#J+_PF-!/meSYg4\^khMpp5drKKel3#2JmAgVB$UR8Ou
Mh:Hn\U6No0SN&m.[EJS%$oCr#O`$EUGiiHhY,4Arp8OM$F:,JqLbdmKi*[i(,fo4h=/YWSY
k`eYqhWo#58rGOBGl;lsoeC?O(Sj%C]A(AVoF*Qp3)L9E+!GmsBIkg_$YGm5^>>*>`/8"IB:5
jo8\L#4iSnGR$jtU_W\aaX3;JQ,!RNnlXCt@-K5#$QZ(QV;32W)X-<B`rLU]AI>q)\Rjq:gB;
t/Z83G-bjMol^A_B]A\:/^")[&GrfHhi]A;7/0^L()gD+?3:I"FYM@`,40UXXa05(nSiCCYrMF
uL/*:7p_\,T\Zl/YB=jQ4oa6]AqPJXh8/P!/>&(Pq'\6%?!4aEAHMbsk"o%n\;<qB?>7Rta#g
3j,t(2d#G5[W`Don/Cfeuk<=7H^)kiFPG#_8&4Lr/e!0.RTq&uKJakPJAJD;C1Ls5N*agc^B
Qr$0RDWNGp"$%$'&uaN5oFRdlX_LI4`6XO%aeopM_B.g4.@,m*5AtIL0S/JPAt!lr3H$3Tg3
4g\%go4`ZiFfZAPJBt=C6?O&s#!OVV!ts2e"mZ1StK`GSfAW**jRd0+[.;:9g*^<>SdshBVS
(>'oFmclI4,;BA-AptRj\%f/l(qfn1**7kqi\+XRZ\^V5Wi*uH=dUS_m,M$==<p6C*r9c^:7
\Ok^L%;t1&C)]AX@'*G9;]A@5`cN\&P%/m3r7dSO/G,!YJg42J*GNPtO@*(iaEDmHo6qj+jguP
4:I)5t,(t#HVlpe[^o;;nJ34e-DmVOZ^:Rn\q)CRW>mo+bN'JL[6\g_(d+-R>(N+qS</&fFh
qKf#JBsM3="KdDOeb2tErVEJ+kr<o=(VQeEXR8QQF&7VFOa($pW3:R1!5]AJ+dF_%KX:4HW6@
1:MD&05+/j=kD2I#mql,746!AXfb&Y-:o2HhTb/2a2GeRa)RMk-@Gb)W6R1(:Dio]Am-&ERBM
N."9Z3+CHE#b>pj.49fr,C\H,-3FIGa%i\-.g".fXX_Vj`A_k986I<-51'cjC`"ouW-:Lj>#
DQIA/LE']A=+aFGYaW(&g1m'B.*/&b%beWZf!S'r:s9Db5<,:*RRLR$kR`S34q^h*l%5:Ye\4
X@aX+e$\V<-r6CWuHo,H.#q:i_i"UN9`#08LjkH&E8_:'M?gHMc6!k\dcNSn2W.LN,qBd>$`
91%\tRGKFtkt7\.,hL:?RKqjr)hnHZC=?IgfFCDl8N<(cQ;gCr;WVMi2X(7-2)[@@2`.-kC\
ud$#=D'VQC$b26GpM*#%4&3bm0^c`n4"PTGCQ]A%uI-^)RbuTj^H`!jd>hqd*R0aJTu^"9Nu^
0U]AL+N`l<DF4V>#i`HjF%At)>mLO#Z4PAkn5^S2LCUIt;E9sDUG"D!NfE"1pF7Bl6L]Adfns*
s<E96lAj4X'Cr5m$6_Z$k=TW9"X&;%mlfoRjGQX1K36?8dUPtflmr<=bG:/!gnN;7^UBIn2N
[#-'E@rh"5Y"h]AKPM)VkJQi%^?(G<CW?Lgo?o6RCY!FV:ZeOA1<_'=KUn`",)3W6#En<;Rr`
iX?Itn5`Q[p'6UC#j:WlqF%\dFY=KVl3YqQHcRI0MBo4N]AeYEfoTDb4rS5FoIUc*dkM&6/5U
02VF?Ls^<(88__2#N8Fmc.1([)+tH]Ac\llFsK0(pnu3ag*MO):<gA"0%eQ7urt7aPoRTI]A\7
_<r'j:9p,=uA$sha9`9k1r8t@Rf5>EqN)pelL,mC@6Q[?"km0^To^u]AAVn?a5&o\l.p*P(jX
2r:i`HbV<_MZQdM427q>2hhFqtb9dduHVk-k955478H)p$A%+EVT!,p-TGbhEKs9m%'LkC==
ic8\73\5Bk6&%&$'AQ!SXk=i)"h&?n4Q[k.%n)]AfpRD]A@7:-[Db1LEM<GM;ZX"&-%#lVk:fO
Vm(:qXj('m7cdqg?=fH]AR6kQiXR]Ar3hpQ2HLqIKIUO$A-[4Ru!+PtVN78!>Z0GMn9MF$]A49\
XJW1Lu=j2,nOQoC&OsE:EX+$!]Ap\Cf5$2IWuIJnK8^hs&Q4>'7\d.D)e"G%\Z8S"G5YAHD\c
u\(7.5UA$6/Si.Q(^3t1-'9=12gD&sE8WCE72568[h;s"Y-m7i!9=GZMN;aD<6I.q".5K"^F
RGq?%Z?&+<2Vj>#kWm';G;RsGe9AJaP>k@JJ!n7N'l1k5=rk2N:-@%j0cD%3"_Y)+DYrVRb>
>JJrAT,$mh>lJhYcc9ka-L(/q;`(0Y#-W(2OG6E^M2p'A[<&W58M(D-M$7;7kiCo2<TM<nUQ
335NCj,2R)TC3gd6^7rZZj!IgLEO!^4J?5DWnM=@9XBuj3Wcn^]A6!1;e@*&!KQ2'@n@89p*o
CdG+1GnFdeuK^]AD6YqNr91..0HFe)>FJleeiqI8T[#f2Nl(:3J%%h14/Co73I<O[@G8'"HRR
,h\uj%?iC$a/Cd[:r.9c;$i6V[#m2kmWSXkFg1((;f<7i/_+%Z%`'c2M6"E2a"E?K$8IBcXN
Z`:1I5VFc9<M`AWY3MfCgs"sWf5hO+V*FkFc:]A9&(q1VO60Za+u.6jg4<`V7m5WL`hXm*:tV
%iAniPLi44CdVM6<1<Q54Wq`'J.RgjPp]A+:t*TF4&H_pO9qEa@$b_m_`6'jQ.4#m._fcQEO,
$H2/eWdLW/%;2PV!.Ap!V;*?+2`'\XlqDeDS-Bct^14AS<^-=\k,Xi[n8Z/NGh\Y]A<'DSo)C
V7,3oYAlgt]A>U/MkTl>[]A%ui`F6mRNMOZdQ1]A85To`q55'-9h[Ch(1m3)L8T:3I_\p3$QinL
7@E=[,#.WS22I'hSs/MsPa#V?C1VtRbY:u>q'VDnX:/<)o$XX>%^ZnkPmUa?"5J?)D?c(7J0
i1;E48u@<0[-]AF[H@"jrW:0pm+j`6hRJS^=Xd-VYiB$Q?r8;l.?,ui(UuO;qK7baffg%0Lmq
T/RP\2QgO0j'#G'.a7W0<45-MM-/GZOgpk.$q]ADWmRY&tedH`Kf_+FK#OV(.tTIS@kQQT46(
`lqqukGS/gkrbFihssk#.9pe#o/u-kCW^G8CW^G8CW^G8J#D)Xab0"n>^s/kIX8PPQpGBI"e
OO_Q9!QgjlCrMYgNGec0/[7pAM/b;-PL8P:=*1$Ll?RpqsG'5[5BdfZm[s.OI3[N(C"ZFm8@
A%b,9#m8U[re-<H5l0it(H]AKWhYg:1??<Qa.aLng[TIKc%.)W[s,k'ZJJ_W>i6'X"c(Oa@`+
n2O0maYp4G]AtL@"g7Fn.3Rhr&n?TU>e>IjQJdG"\J+[,=3P_($_UVaiEFRo1h"OTjFX*&_ti
TJQaR"[,ZVF_693c8WU<a1f<2>=dsLHrI=-TRR;5R\Xaa+gdd2hYr7sBSXYV7Ul)2C_5(r5#
b)oYW3).D'Qi,CjLW_*r7QKm87ORO9P8rYiHX27DRKa^'X:#0CXbtF--VVaE-P`X7OM7TcX*
'fN9^DoSIOc3b"PBc.aE2jArk#`bL++XY?$m4[MQZC%3/%soh>drWe`POc4a2@h+^.U_\X3K
>n]All1.&WG3"GgiJ4+`m4!p[=i_Th/c>4\$7F:6]AILp8oig;KV_hhY#8e*l[VYAlDqW3S,b;
5Yi!^fGFP"0MF=bu:J2k#>K"K&bI6o\PM-)ko10j_=?fG6$:t(]A<H;+.Vp2g?.o"&m3]AOD!r
.M,Na(0B8=4s#6~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[m8s0l'6i$)omN[?<nLGWZs;%E.TMCK7>qjMZrW;*+]AoGDgK61!$kP+5%:O"`PuRt"!=FD2'p
Tu)X?rY4OOPK,!_JV\8chR7RV8jYO:96mbXUT0GCS9F_"mAfo/t>ZhjMf'cLX1>)\]AJed0GT
bgt\2`meZhh2n&qp2n(`MD_6'CA:M^]AEH>?m.e<1JHOC\%8`X?/0)^PFoV=i(l3Ps`pg64?q
g!>(L$5/i>6CcoBD*p)E3-TbgX3E1PIuCtop/\KRcW`4Z7U7]A49"!CrFg]ATXZ*dOiRQ!K]A<U
Xk)csYU,b%S:YF=&-a!Vl<=5\iAl`Wj:$"E2YgGB:*A`^\Y6S!11NIr0rRPi[`c)0[tk9CRH
>r?'!V'A>CRns5HF?EuX`?CZ/CN5i\)W#Cr2+X-KAAX8P[WKR7Y$kWN^J:1PX4rGt'YE'>B&
uM20G"sm\@"@9/KIQPQH!9e]A?q!*FYcl^aBJ7h@=5o->0G$diL(*_FA(AO[&!:P82l@ISsBi
_X77Q.<$hfsEq@4G"O"!ROBLKY*0%r`L8t\u=+JA"LTu$d!Gb-6f)m/"P)tl1RklBS>[KJ1I
[ZJ<jHN?mIN5HV1QG*p.agr=I2cB#J+_PF-!/meSYg4\^khMpp5drKKel3#2JmAgVB$UR8Ou
Mh:Hn\U6No0SN&m.[EJS%$oCr#O`$EUGiiHhY,4Arp8OM$F:,JqLbdmKi*[i(,fo4h=/YWSY
k`eYqhWo#58rGOBGl;lsoeC?O(Sj%C]A(AVoF*Qp3)L9E+!GmsBIkg_$YGm5^>>*>`/8"IB:5
jo8\L#4iSnGR$jtU_W\aaX3;JQ,!RNnlXCt@-K5#$QZ(QV;32W)X-<B`rLU]AI>q)\Rjq:gB;
t/Z83G-bjMol^A_B]A\:/^")[&GrfHhi]A;7/0^L()gD+?3:I"FYM@`,40UXXa05(nSiCCYrMF
uL/*:7p_\,T\Zl/YB=jQ4oa6]AqPJXh8/P!/>&(Pq'\6%?!4aEAHMbsk"o%n\;<qB?>7Rta#g
3j,t(2d#G5[W`Don/Cfeuk<=7H^)kiFPG#_8&4Lr/e!0.RTq&uKJakPJAJD;C1Ls5N*agc^B
Qr$0RDWNGp"$%$'&uaN5oFRdlX_LI4`6XO%aeopM_B.g4.@,m*5AtIL0S/JPAt!lr3H$3Tg3
4g\%go4`ZiFfZAPJBt=C6?O&s#!OVV!ts2e"mZ1StK`GSfAW**jRd0+[.;:9g*^<>SdshBVS
(>'oFmclI4,;BA-AptRj\%f/l(qfn1**7kqi\+XRZ\^V5Wi*uH=dUS_m,M$==<p6C*r9c^:7
\Ok^L%;t1&C)]AX@'*G9;]A@5`cN\&P%/m3r7dSO/G,!YJg42J*GNPtO@*(iaEDmHo6qj+jguP
4:I)5t,(t#HVlpe[^o;;nJ34e-DmVOZ^:Rn\q)CRW>mo+bN'JL[6\g_(d+-R>(N+qS</&fFh
qKf#JBsM3="KdDOeb2tErVEJ+kr<o=(VQeEXR8QQF&7VFOa($pW3:R1!5]AJ+dF_%KX:4HW6@
1:MD&05+/j=kD2I#mql,746!AXfb&Y-:o2HhTb/2a2GeRa)RMk-@Gb)W6R1(:Dio]Am-&ERBM
N."9Z3+CHE#b>pj.49fr,C\H,-3FIGa%i\-.g".fXX_Vj`A_k986I<-51'cjC`"ouW-:Lj>#
DQIA/LE']A=+aFGYaW(&g1m'B.*/&b%beWZf!S'r:s9Db5<,:*RRLR$kR`S34q^h*l%5:Ye\4
X@aX+e$\V<-r6CWuHo,H.#q:i_i"UN9`#08LjkH&E8_:'M?gHMc6!k\dcNSn2W.LN,qBd>$`
91%\tRGKFtkt7\.,hL:?RKqjr)hnHZC=?IgfFCDl8N<(cQ;gCr;WVMi2X(7-2)[@@2`.-kC\
ud$#=D'VQC$b26GpM*#%4&3bm0^c`n4"PTGCQ]A%uI-^)RbuTj^H`!jd>hqd*R0aJTu^"9Nu^
0U]AL+N`l<DF4V>#i`HjF%At)>mLO#Z4PAkn5^S2LCUIt;E9sDUG"D!NfE"1pF7Bl6L]Adfns*
s<E96lAj4X'Cr5m$6_Z$k=TW9"X&;%mlfoRjGQX1K36?8dUPtflmr<=bG:/!gnN;7^UBIn2N
[#-'E@rh"5Y"h]AKPM)VkJQi%^?(G<CW?Lgo?o6RCY!FV:ZeOA1<_'=KUn`",)3W6#En<;Rr`
iX?Itn5`Q[p'6UC#j:WlqF%\dFY=KVl3YqQHcRI0MBo4N]AeYEfoTDb4rS5FoIUc*dkM&6/5U
02VF?Ls^<(88__2#N8Fmc.1([)+tH]Ac\llFsK0(pnu3ag*MO):<gA"0%eQ7urt7aPoRTI]A\7
_<r'j:9p,=uA$sha9`9k1r8t@Rf5>EqN)pelL,mC@6Q[?"km0^To^u]AAVn?a5&o\l.p*P(jX
2r:i`HbV<_MZQdM427q>2hhFqtb9dduHVk-k955478H)p$A%+EVT!,p-TGbhEKs9m%'LkC==
ic8\73\5Bk6&%&$'AQ!SXk=i)"h&?n4Q[k.%n)]AfpRD]A@7:-[Db1LEM<GM;ZX"&-%#lVk:fO
Vm(:qXj('m7cdqg?=fH]AR6kQiXR]Ar3hpQ2HLqIKIUO$A-[4Ru!+PtVN78!>Z0GMn9MF$]A49\
XJW1Lu=j2,nOQoC&OsE:EX+$!]Ap\Cf5$2IWuIJnK8^hs&Q4>'7\d.D)e"G%\Z8S"G5YAHD\c
u\(7.5UA$6/Si.Q(^3t1-'9=12gD&sE8WCE72568[h;s"Y-m7i!9=GZMN;aD<6I.q".5K"^F
RGq?%Z?&+<2Vj>#kWm';G;RsGe9AJaP>k@JJ!n7N'l1k5=rk2N:-@%j0cD%3"_Y)+DYrVRb>
>JJrAT,$mh>lJhYcc9ka-L(/q;`(0Y#-W(2OG6E^M2p'A[<&W58M(D-M$7;7kiCo2<TM<nUQ
335NCj,2R)TC3gd6^7rZZj!IgLEO!^4J?5DWnM=@9XBuj3Wcn^]A6!1;e@*&!KQ2'@n@89p*o
CdG+1GnFdeuK^]AD6YqNr91..0HFe)>FJleeiqI8T[#f2Nl(:3J%%h14/Co73I<O[@G8'"HRR
,h\uj%?iC$a/Cd[:r.9c;$i6V[#m2kmWSXkFg1((;f<7i/_+%Z%`'c2M6"E2a"E?K$8IBcXN
Z`:1I5VFc9<M`AWY3MfCgs"sWf5hO+V*FkFc:]A9&(q1VO60Za+u.6jg4<`V7m5WL`hXm*:tV
%iAniPLi44CdVM6<1<Q54Wq`'J.RgjPp]A+:t*TF4&H_pO9qEa@$b_m_`6'jQ.4#m._fcQEO,
$H2/eWdLW/%;2PV!.Ap!V;*?+2`'\XlqDeDS-Bct^14AS<^-=\k,Xi[n8Z/NGh\Y]A<'DSo)C
V7,3oYAlgt]A>U/MkTl>[]A%ui`F6mRNMOZdQ1]A85To`q55'-9h[Ch(1m3)L8T:3I_\p3$QinL
7@E=[,#.WS22I'hSs/MsPa#V?C1VtRbY:u>q'VDnX:/<)o$XX>%^ZnkPmUa?"5J?)D?c(7J0
i1;E48u@<0[-]AF[H@"jrW:0pm+j`6hRJS^=Xd-VYiB$Q?r8;l.?,ui(UuO;qK7baffg%0Lmq
T/RP\2QgO0j'#G'.a7W0<45-MM-/GZOgpk.$q]ADWmRY&tedH`Kf_+FK#OV(.tTIS@kQQT46(
`lqqukGS/gkrbFihssk#.9pe#o/u-kCW^G8CW^G8CW^G8J#D)Xab0"n>^s/kIX8PPQpGBI"e
OO_Q9!QgjlCrMYgNGec0/[7pAM/b;-PL8P:=*1$Ll?RpqsG'5[5BdfZm[s.OI3[N(C"ZFm8@
A%b,9#m8U[re-<H5l0it(H]AKWhYg:1??<Qa.aLng[TIKc%.)W[s,k'ZJJ_W>i6'X"c(Oa@`+
n2O0maYp4G]AtL@"g7Fn.3Rhr&n?TU>e>IjQJdG"\J+[,=3P_($_UVaiEFRo1h"OTjFX*&_ti
TJQaR"[,ZVF_693c8WU<a1f<2>=dsLHrI=-TRR;5R\Xaa+gdd2hYr7sBSXYV7Ul)2C_5(r5#
b)oYW3).D'Qi,CjLW_*r7QKm87ORO9P8rYiHX27DRKa^'X:#0CXbtF--VVaE-P`X7OM7TcX*
'fN9^DoSIOc3b"PBc.aE2jArk#`bL++XY?$m4[MQZC%3/%soh>drWe`POc4a2@h+^.U_\X3K
>n]All1.&WG3"GgiJ4+`m4!p[=i_Th/c>4\$7F:6]AILp8oig;KV_hhY#8e*l[VYAlDqW3S,b;
5Yi!^fGFP"0MF=bu:J2k#>K"K&bI6o\PM-)ko10j_=?fG6$:t(]A<H;+.Vp2g?.o"&m3]AOD!r
.M,Na(0B8=4s#6~
]]></IM>
</FineImage>
</Background>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList/>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="310" y="511" width="580" height="201"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="chart6"/>
<Widget widgetName="chart0"/>
<Widget widgetName="chart5"/>
<Widget widgetName="report1"/>
<Widget widgetName="chart11"/>
<Widget widgetName="report3"/>
<Widget widgetName="chart3"/>
<Widget widgetName="chart2"/>
<Widget widgetName="chart4"/>
<Widget widgetName="chart1"/>
<Widget widgetName="chart8"/>
<Widget widgetName="report2"/>
</MobileWidgetList>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="1000" height="599"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="body"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="1000" height="599"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="1"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="806d9eb5-730f-40b7-9910-300487433dc9"/>
</TemplateIdAttMark>
</Form>
