getRolesList
===
select @pageTag(){
	t.role_id, t.role_typ_code, a.typ_name, t.role_name, t.role_code
	@}
  from SYS_C_ROLE t, sys_c_role_typ a
 where t.is_use = 'Y'
   and t.role_name like #roleName#
   and t.role_typ_code = a.typ_code(+)
   and a.is_use(+) = 'Y'
   @pageIgnoreTag(){
 order by t.ord_by
 @}
 
getSelectedUsersList
===
 select @pageTag(){
 		t.ur_id,
       t.user_code,
       a.user_name,
       (select wm_concat(c.dept_name)
          from sys_c_user_dept b, hr_c_dept c
         where b.user_code = a.user_code
           and b.dept_code = c.dept_code
           and c.is_use = 'Y') dept_name
        @}
  from sys_c_user_role t, sys_c_user a
 where t.role_code = #roleCode#
   and t.user_code = a.user_code
   and a.user_name || ',' || a.user_code like #userName#
   and a.is_use = 'Y'
   @pageIgnoreTag(){
 order by a.ord_by
 @}

getUnSelectedUsersList
=== 
select @pageTag(){
		t.user_id,
       t.user_code,
       t.user_name,
       (select wm_concat(c.dept_name)
          from sys_c_user_dept b, hr_c_dept c
         where b.user_code = t.user_code
           and b.dept_code = c.dept_code
           and c.is_use = 'Y') dept_name
       @}
  from sys_c_user t
 where t.user_code not in
       (select a.user_code from sys_c_user_role a where a.role_code = #roleCode#)
   and t.user_name || ',' || t.user_code like #userName#
   and t.is_use = 'Y'
   @pageIgnoreTag(){
 	order by t.ord_by
 @}

querySelectedRole
===
select @pageTag(){
	t.ur_id, t.role_code, a.role_name, b.dept_name
	@}
  from SYS_C_USER_ROLE t, sys_c_role a, hr_c_dept b
 where t.user_code = #userCode#
 @if(!isEmpty(roleName)){
 	and (a.role_name like #roleName# or a.role_code like #roleName#)
 @}
   and t.role_code = a.role_code
   and a.is_use(+) = 'Y'
   and a.org_code = b.org_code(+)
   and b.is_use(+) = 'Y'
@pageIgnoreTag(){
 order by a.ord_by
@}

queryUnselectedRole
===
select @pageTag(){
	t.role_code, t.role_name, b.dept_name
@}
  from sys_c_role t, hr_c_dept b
 where t.role_code not in (select a.role_code
                             from SYS_C_USER_ROLE a
                            where a.user_code = #userCode#)
 @if(!isEmpty(roleName)){
 	and (t.role_name like #roleName# or t.role_code like #roleName#)
 @}
   and t.org_code = b.org_code(+)
   and b.is_use(+) = 'Y'
   and t.is_use = 'Y'
@pageIgnoreTag(){
 order by t.ord_by
@}

getUserInfoByUserCode
===
select a.user_id,
       a.user_code,
       a.user_name,
       a.user_acnt,
       a.enable_time,
       a.invalid_time,
       a.is_longterm,
       (select wm_concat(c.dept_name)
          from sys_c_user_dept b, hr_c_dept c
         where b.user_code = a.user_code
           and b.org_code = c.dept_code) org_code,
       (select d.dict_name
          from com_c_dict d
         where d.cat_code = 'acntUserTyp'
           and d.dict_code = a.user_typ
           and d.is_use = 'Y') user_typ,
       a.remark,
       a.ord_by
  from sys_c_user a
 where a.is_use = 'Y'
   and a.user_code = #userCode#
