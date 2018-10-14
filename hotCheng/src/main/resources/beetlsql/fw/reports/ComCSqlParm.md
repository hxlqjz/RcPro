searchComCSqlParm
===
select @pageTag(){
		t.PARM_ID,
       t.SQL_CODE,
       t.PARM_CODE,
       t.PARM_NAME,
       t.DEF_VALUE,
       t.IS_TOOLBAR,
       t.QUERY_TYP,
       (select b.DICT_NAME
          from COM_C_DICT_CAT a, COM_C_DICT b
         where b.DICT_CODE = t.CTRL_TYP
           and a.CAT_CODE = 'ctrlTyp'
           and a.CAT_CODE = b.CAT_CODE
           and a.IS_USE = 'Y'
           and b.IS_USE = 'Y') as CTRL_TYPText,
       t.CTRL_TYP,
       t.CAT_CODE,
       t.URL,
       t.REMARK,
       t.IS_USE,
       t.ORD_BY,
       t.CREATE_BY,
       t.CREATE_TIME,
       t.MODIFY_BY,
       t.MODIFY_TIME
     @}
  from COM_C_SQL_PARM t
 where t.is_use = 'Y'
   and t.SQL_CODE = #sqlCode#
   and t.PARM_NAME like #parmName#
 @pageIgnoreTag(){
 order by t.ORD_BY
 @}

getparmList
=== 
 select *
  from COM_C_SQL_PARM t
 where t.SQL_CODE = #sqlCode#
   and t.is_use = 'Y'
 order by t.ORD_BY
