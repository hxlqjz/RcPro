getHrCDeptEasyUI
===
select t.dept_id id,
       t.dept_code code,
       t.dept_name text,
       case
         when (select count(1)
                 from hr_c_dept a
                where a.is_use = 'Y'
                  and a.pdept_code = t.dept_code) > 0 then
          'closed'
         else
          'open'
       end state,
       t.org_code business
  from HR_C_DEPT t
 where t.is_use = 'Y'
   and t.PDEPT_CODE = #pdeptCode#
 order by t.ord_by
 
getHrCDeptEasyUI2
===
* dept_code作为id

select t.dept_id code,
       t.dept_code id,
       t.dept_name text,
       case
         when (select count(1)
                 from hr_c_dept a
                where a.is_use = 'Y'
                  and a.pdept_code = t.dept_code) > 0 then
          'closed'
         else
          'open'
       end state,
       t.org_code business
  from HR_C_DEPT t
 where t.is_use = 'Y'
   and t.PDEPT_CODE = #pdeptCode#
 order by t.ord_by
 
getHrCDeptByOrgCode
=== 
* 根据机构编码获取部门树
with aa as
 (select distinct a.*
    from HR_C_DEPT a
   start with a.org_code = #orgCode#
          and a.is_org = 'N'
          and a.is_use = 'Y'
  connect by prior a.pdept_code = a.dept_code)
select t.dept_id code,
       t.dept_code id,
       t.dept_name text,
       case
         when (select count(1)
                 from hr_c_dept a
                where a.is_use = 'Y'
                  and a.pdept_code = t.dept_code) > 0 then
          'closed'
         else
          'open'
       end state,
       t.is_org business
  from aa t
 where t.PDEPT_CODE = #pdeptCode#
 order by t.ord_by
