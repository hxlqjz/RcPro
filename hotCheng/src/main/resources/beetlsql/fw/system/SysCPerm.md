delPermByMenuCode
===
* 根据菜单编码删除所有权限
begin
  delete from SYS_C_ROLE_PERM t
   where t.perm_code in
         (select a.perm_code from SYS_C_PERM a where a.menu_code = #menuCode#);
  commit;
  delete from SYS_C_PERM t where t.menu_code = #menuCode#;
end;

delPermByMenuCodePerTyp
===
* 根据菜单编码及权限类别删除所有权限
begin
  delete from SYS_C_ROLE_PERM t
   where t.perm_code in (select a.perm_code
                           from SYS_C_PERM a
                          where a.menu_code = #menuCode#
                            and a.perm_typ = #permTyp#);
  commit;
  delete from SYS_C_PERM t
   where t.menu_code = #menuCode#
     and t.perm_typ = #permTyp#;
end;

delPermByMenuPermTypAndCodes
===
* 根据菜单编码、权限类别、权限编码删除权限
delete from SYS_C_ROLE_PERM t
 where t.perm_code in (select a.perm_code
                         from SYS_C_PERM a
                        where a.menu_code = #menuCode#
                          and a.perm_typ = #permTyp#
                          and a.perm_code not in (#join(permCodes)#))

delSysCPerm
===
delete from SYS_C_PERM t
 where t.menu_code = #menuCode#
   and t.perm_typ = #permTyp#
   and t.perm_code not in (#join(permCodes)#)
   
getNewAddSysCPerm
===
   WITH A AS
 (SELECT #permCodes# A FROM DUAL)
select t.perm_code
  from (SELECT DECODE(B, 0, SUBSTR(A, C), SUBSTR(A, C, B - C)) perm_code
          FROM (SELECT A, B, (LAG(B, 1, 0) OVER(ORDER BY LV)) + 1 C
                  FROM (SELECT A, INSTR(A, ',', 1, LEVEL) B, LEVEL LV
                          FROM A
                        CONNECT BY LEVEL <=
                                   (LENGTH(A) - LENGTH(REPLACE(A, ',', ''))) + 1))) t
 where t.perm_code not in (select tt.perm_code
                             from SYS_C_PERM tt
                            where tt.menu_code = #menuCode#
                              and tt.perm_typ = #permTyp#)
getModulePermCodes
===                      
select t.perm_id,
       t.perm_name,
       t.perm_typ,
       substr(t.perm_code,
              length(t.menu_code) + 2,
              length(t.perm_code) - length(t.menu_code)) perm_code
  from SYS_C_PERM t
 where t.menu_code = #menuCode#
   and t.perm_typ = #permTyp#
   
getRoleMenuPermCodes
=== 
select a.perm_code, a.perm_name, b.rp_id, #permTyp# cat_code
  from SYS_C_PERM a, SYS_C_ROLE_PERM b
 where a.menu_code = #menuCode#
   and a.perm_typ = #permTyp#
 @if(operate == 'one'){
	and a.perm_code = b.perm_code
   and b.role_code = #roleCode#
 @}
 @if(operate == 'two'){
	and a.perm_code = b.perm_code(+)
   and b.role_code(+) = #roleCode#
 @}
 order by a.ord_by

getPermNumByRoleMenu
=== 
* 根据角色编码、模块编码获取已分配的权限数量
select count(1)
  from SYS_C_ROLE_PERM t
 where t.role_code = #roleCode#
   and substr(t.perm_code, 1, instr(t.perm_code, ':', 1) - 1) = #menuCode#


initRoleMenuPermCodes
===
 select a.perm_code, a.perm_name, #rpId# rp_id, #permTyp# cat_code
  from SYS_C_PERM a
 where a.menu_code = #menuCode#
   and a.perm_typ = #permTyp#
 order by a.ord_by

delRolePermByPermCode
===
delete from sys_c_role_perm tt
 where tt.role_code = #roleCode#
   and substr(tt.perm_code, 1, instr(tt.perm_code, ':', 1) - 1) = #menuCode#
@if(!isEmpty(permCodes)){
	and tt.perm_code not in (#join(permCodes)#)
@}
   
getRoleNewAddPerm
===
WITH A AS
 (SELECT #permCodes# A
    FROM DUAL)
select t.perm_code, t1.ord_by
  from (SELECT DECODE(B, 0, SUBSTR(A, C), SUBSTR(A, C, B - C)) perm_code
          FROM (SELECT A, B, (LAG(B, 1, 0) OVER(ORDER BY LV)) + 1 C
                  FROM (SELECT A, INSTR(A, ',', 1, LEVEL) B, LEVEL LV
                          FROM A
                        CONNECT BY LEVEL <=
                                   (LENGTH(A) - LENGTH(REPLACE(A, ',', ''))) + 1))) t,
       sys_c_perm t1
 where t.perm_code = t1.perm_code
   and t.perm_code not in
       (select tt.perm_code
          from sys_c_role_perm tt
         where tt.role_code = #roleCode#)

delSysCRolePerm
===
delete from SYS_C_ROLE_PERM t
 where t.rp_id in
       (select b.rp_id
          from SYS_C_ROLE_PERM b
         where b.role_code = #roleCode#
           and substr(b.perm_code, 1, INSTR(b.perm_code, ':', 1, 1) - 1) =
               #menuCode#)
