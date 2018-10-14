judeHaveColumn
===
select count(1)
  from ui_c_page a, ui_c_page_fld b
 where a.page_id = #pageId#
   and a.page_code = b.page_code
   and a.tbl_codes = b.tbl_code

initUiCPageFldList
=== 
select a.page_code,
       b.tbl_code,
       b.fld_code,
       b.fld_name,
       b.fld_id,
       b.is_form,
       b.is_list,
       b.is_query,
       b.is_single,
       b.query_typ,
       b.data_len,
       b.data_scale,
       b.ord_by
  from ui_c_page a, ui_c_page_fld b
 where a.page_id = #pageId#
   and a.page_code = b.page_code
   and a.tbl_codes = b.tbl_code
 order by b.ord_by

getUiCPageFldList
===
select t.page_code,
       a.TABLE_NAME tbl_code,
       a.COLUMN_NAME fld_code,
       b.comments fld_name,
       null fld_id,
       'Y' is_form,
       'Y' is_list,
       'N' is_query,
       'N' is_single,
       null query_typ,
       a.DATA_LENGTH data_len,
       a.DATA_SCALE,
       rownum ord_by
  from ui_c_page t, USER_TAB_COLUMNS a, USER_COL_COMMENTS b
 where t.page_id = #pageId#
   and t.tbl_codes = a.TABLE_NAME
   and t.tbl_codes = b.table_name
   and a.COLUMN_NAME = b.column_name
 order by rownum
   