select
===
select @pageTag(){
t.ROLE_ID,
       a.typ_name    ROLE_TYP_CODE,
       t.ROLE_CODE,
       t.ROLE_NAME,
       b.dept_name   ORG_CODE,
       t.IS_USABLE,
       t.IS_PERM,
       t.RMK,
       t.IS_USE,
       t.ORD_BY,
       c.user_name CREATE_BY,
       t.CREATE_TIME
       @}
  from SYS_C_ROLE t, SYS_C_ROLE_TYP a, hr_c_dept b, sys_c_user c
 where t.is_use = 'Y'
   and t.role_typ_code = a.typ_code(+)
   and a.is_use(+) = 'Y'
   and t.org_code = b.org_code(+)
   and b.is_org(+) = 'Y'
   and b.is_use(+) = 'Y'
   and t.create_by = c.user_code(+)
   and c.is_use(+) = 'Y'
@if(!isEmpty(roleName)){
and t.role_Name like #roleName#
@}
@if(!isEmpty(orgCode) && orgCode!="all"){
	     and t.org_code = #orgCode#
	     and t.role_typ_code != 'administrator'
@}
@pageIgnoreTag(){
    order by t.ord_by
@}

getRoleToPerm
===
select @pageTag(){
		c.role_code,
       c.menu_code,
       wm_concat(c.perm_code) perm_codes,
       wm_concat(d.dict_name) dict_names,
       e.menu_name,
       e.ord_by
       @}
  from (select b.role_code,
               substr(b.perm_code, 1, INSTR(b.perm_code, ':', 1, 1) - 1) menu_code,
               substr(b.perm_code,
                      INSTR(b.perm_code, ':', 1, 1) + 1,
                      length(b.perm_code)) perm_code
          from sys_c_role a, SYS_C_ROLE_PERM b
         where a.role_id = #roleId#
           and a.role_code = b.role_code
           and b.is_usable = 'Y') c,
       com_c_dict d,
       sys_c_menu e
 where d.cat_code in ('permButton', 'permData')
   and c.perm_code = d.dict_code
   and d.is_use = 'Y'
   and c.menu_code = e.menu_code
   and e.is_use = 'Y'
   and e.menu_name like #menuName#
 group by c.role_code, c.menu_code, e.menu_name, e.ord_by
 @pageIgnoreTag(){
	order by e.ord_by
 @}


getUserListByDeptCode
===
select @pageTag(){
       a.user_code,
       b.user_name,
       c.dept_name,
       c.dept_id,
       c.dept_code,
       info.expert_grade
        @}
  from sys_c_user_dept a,
       sys_c_user b,
       hr_c_dept c,
       tal_c_expert_info info
 where b.is_use = 'Y'
   and c.dept_code = a.dept_code
   and c.org_code = a.org_code
   and b.user_code = a.user_code
   and info.user_code(+) = b.user_code
 @if(!isEmpty(userName)){
 	and b.user_name like #userName#
 @}
 @if(!isEmpty(deptCode)){
       START WITH  c.dept_code = #deptCode#
       CONNECT BY PRIOR c.pdept_code = c.dept_code
@}

   
=======
getSysCRoleTypeList 
===
select t.typ_code, t.typ_name
  from SYS_C_ROLE_TYP t
 where t.is_use = 'Y'
 @if(superAdmin != userCode){
 	and t.is_admin = 'N'
 @}
 order by t.ord_by

getOrgCodeList 
===
 select t.org_code, t.dept_name
  from hr_c_dept t
 where t.is_org = 'Y'
   and t.is_use = 'Y'
   and t.org_code != '0'
   order by t.ord_by


