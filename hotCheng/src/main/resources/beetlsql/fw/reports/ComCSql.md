searchComCSql
===
select @pageTag(){
		t.SQL_ID,
       t.SQL_CODE,
       t.SQL_NAME,
       (select b.DICT_NAME
          from COM_C_DICT_CAT a, COM_C_DICT b
         where b.DICT_CODE = t.SQL_TYP
           and a.CAT_CODE = 'sqlType'
           and a.CAT_CODE = b.CAT_CODE
           and a.IS_USE = 'Y'
           and b.IS_USE = 'Y') as SQL_TYPText,
       t.SQL_TYP,
       t.SQL_CONTENT,
       t.REMARK,
       t.IS_USE,
       t.ORD_BY,
       t.CREATE_BY,
       t.CREATE_TIME,
       t.MODIFY_BY,
       t.MODIFY_TIME
      @}
  from COM_C_SQL t
 where t.is_use = 'Y'
 @if(!isEmpty(sqlName)){
 and t.SQL_NAME like #sqlName#
 @}
 @pageIgnoreTag(){
 order by t.ord_by
 @}
 
getComCSqlList
===
select t.SQL_CODE as id, t.SQL_NAME as text
  from COM_C_SQL t
 where t.is_use = 'Y'

createNewSqlCode
===
select to_char(sysdate, 'yyyymmdd') ||
       (select trim(to_char(nvl(max(substr(t.SQL_CODE, 9, 4)), 0) + 1,
                            '0000'))
          from COM_C_SQL t
         where substr(t.SQL_CODE, 1, 8) = to_char(sysdate, 'yyyymmdd'))
  from dual

