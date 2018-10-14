selectQueryFld
===
select * from UI_C_PAGE_FLD t 
where t.is_query = 'Y' 
and t.tbl_code = #tablename# 
and t.page_code=#pageCode# 
order by t.ord_by

buildTable
===
select t.TABLE_NAME,
       t.COLUMN_NAME,
       t.DATA_TYPE,
       t.DATA_LENGTH,
       t.DATA_PRECISION,
       t.DATA_SCALE,
       t.NULLABLE,
       c.comments
  from user_tab_columns t
  left join user_col_comments c
    on c.table_name = t.TABLE_NAME
   and c.column_name = t.COLUMN_NAME
 where t.TABLE_NAME = #tableName#
 order by t.column_id

pageFldSql
=== 
 select *
  from UI_C_PAGE_FLD t
 where t.tbl_code = #tblCode#
   and t.page_code = #pageCode#
 order by t.ord_by
 
getPK
=== 
select cu.column_name
  from user_cons_columns cu, user_constraints au
 where cu.constraint_name = au.constraint_name
   and au.constraint_type = 'P'
   and au.table_name = #tableName#
 
getrowList
===  
select * from UI_C_PAGE_FLD t where t.tbl_code = #tblCode#

getLayList
===
select * from UI_C_PAGE_LAYOUT t where t.PAGE_CODE = #pageCode#
