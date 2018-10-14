getMenuPath
===
 select replace(wm_concat(a.menu_name || '>>'), '>>,', '>>')
  from (select t.menu_name, level
          from SYS_C_MENU t
         start with t.menu_id = #menuId#
        connect by prior t.pmenu_code = t.menu_code
               and t.is_use = 'Y'
               and t.menu_id != 1
         order by level desc) a

getSysCMenuEasyUI
===      
 select t.menu_id id,
       t.menu_name text,
       t.menu_code code,
       case
         when ((select count(1)
                  from SYS_C_MENU a
                 where a.pmenu_code = t.menu_code
                   and a.is_use = 'Y') > 0 or t.is_page = 'N') then
          'closed'
         else
          'open'
       end state,
       t.url
  from SYS_C_MENU t
 where t.pmenu_code = #pmenuCode#
   and t.is_use = 'Y'
 order by t.ord_by

getSysCMenuTreeByRole
=== 
 with a as
 (select substr(b.perm_code, 1, INSTR(b.perm_code, ':', 1, 1) - 1) menu_code,
         count(1) select_num
    from sys_c_role_perm b
   where b.role_code = #roleCode#
   group by substr(b.perm_code, 1, INSTR(b.perm_code, ':', 1, 1) - 1))
select t.menu_id id,
       t.menu_name text,
       t.menu_code code,
       case
         when ((select count(1)
                  from SYS_C_MENU a
                 where a.pmenu_code = t.menu_code
                   and a.is_use = 'Y') > 0 or t.is_page = 'N') then
          'closed'
         else
          'open'
       end state,
       case
         when a.menu_code is null then
          'false'
         else
          'true'
       end checked,
       a.select_num business,
       (select count(1) from SYS_C_PERM c where c.menu_code = t.menu_code) openway
  from SYS_C_MENU t, a
 where t.pmenu_code = #pmenuCode#
   and t.is_use = 'Y'
   and t.menu_code = a.menu_code(+)
 order by t.ord_by



queryChildByCode
===
select distinct 
t.menu_id id,
t.menu_name text,
t.menu_code code,
case
when ((select count(1)
from SYS_C_MENU a
where a.pmenu_code = t.menu_code
and a.is_use = 'Y') > 0 or t.is_page = 'N') then
'closed'
else
'open'
end state,
t.url,
t.ord_by
from SYS_C_MENU t
where t.pmenu_code = #pmenuCode#
and t.is_use = 'Y'

order by t.ord_by asc
