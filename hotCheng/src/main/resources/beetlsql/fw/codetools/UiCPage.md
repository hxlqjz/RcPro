getUiCPageList
===
select @pageTag(){ 
t.page_id,
t.page_code,
t.page_name,
t.tmpl_code,
nvl(a.tmpl_name, '多表-组合布局') tmpl_name,
t.page_typ,
c.dict_name page_typ_name,
t.tips,
t.wk_space,
t.pkg_name,
t.url,
t.tbl_codes,
(select wm_concat(b.comments)
from user_tab_comments b
where ',' || t.tbl_codes || ',' like
'%,' || b.table_name || ',%') tbl_names,
t.rmk
@}
from UI_C_PAGE t, UI_C_PAGE_TMPL a, com_c_dict c
where t.is_use = 'Y'
and t.tmpl_code = a.tmpl_code(+)
and t.page_typ = c.dict_code(+)
and c.cat_code(+) = 'pageTyp'
and c.is_use(+) = 'Y'
and a.is_use(+) = 'Y'
@if(!isEmpty(pageName)){
and t.page_name like #pageName#
@}
@pageIgnoreTag(){
order by t.ord_by
@}

createPageCode
===
select (select to_char(sysdate, 'yyyymmdd') ||
               trim(to_char(nvl(substr(max(t.page_code), 9, 4), 0) + 1,
                            '0000'))
          from ui_c_page t
         where substr(t.page_code, 1, 8) = to_char(sysdate, 'yyyymmdd'))
  from dual
  
getTmplTypeList
===  
  select t.tmpl_code, t.tmpl_name
  from UI_C_PAGE_TMPL t
 where t.is_use = 'Y'
 order by t.ord_by
 
getDbCTblList
===
 select t.TABLE_NAME tbl_code, a.comments tbl_name
  from user_tables t, user_tab_comments a
 where t.TABLE_NAME = a.table_name
 @if(!isEmpty(tblCode)){
 and (t.TABLE_NAME like #tblCode# or t.TABLE_NAME like #tblCode#)
 @}

getUiCPageInfoById   
===
select t.page_id,
       t.page_code,
       t.page_name,
       t.tmpl_code,
       a.tmpl_name,
       t.page_typ,
       t.tips,
       t.wk_space,
       t.pkg_name,
       t.url,
       t.tbl_codes,
       /*(select wm_concat(b.tbl_name)
        from DB_C_TBL b
       where ',' || t.tbl_codes || ',' like '%,' || b.tbl_code || ',%') tbl_names,*/
       (select wm_concat(b.comments)
          from user_tab_comments b
         where ',' || t.tbl_codes || ',' like '%,' || b.table_name || ',%') tbl_names,
       t.rmk,
       t.ord_by
  from UI_C_PAGE t, UI_C_PAGE_TMPL a, com_c_dict c
 where t.is_use = 'Y'
   and t.tmpl_code = a.tmpl_code(+)
   and t.page_typ = c.dict_code(+)
   and c.cat_code(+) = 'pageTyp'
   and c.is_use(+) = 'Y'
   and a.is_use(+) = 'Y'
   and t.page_id = #pageId#
   
getTableColumnsList
===
select b.column_name col_code, b.comments col_name
  from ui_c_page a, user_col_comments b
 where a.page_id = #pageId#
   and a.tbl_codes = b.table_name

findColInfoList   
===
select *
  from COM_C_RPT_HEAD t
 where t.RPT_CODE = #rptCode#
   and t.is_use = 'Y'
   and t.IS_FLD = 'Y'
   and t.PHEAD_CODE is null
 order by t.ORD_BY
 
getIsSingle
===
select count(1)
  from COM_C_RPT_HEAD t
 where t.PHEAD_CODE = #pheadCode#
   and t.is_use = 'Y'

findrolColInfoList  
=== 
select *
  from COM_C_RPT_HEAD t
 where t.RPT_CODE = #rptCode#
   and t.is_use = 'Y'
   and t.IS_FLD = 'Y'
   @if(!isEmpty(pheadCode)){
   and t.phead_code = #pheadCode#
   @}
   and t.PHEAD_CODE is not null
 order by t.ORD_BY