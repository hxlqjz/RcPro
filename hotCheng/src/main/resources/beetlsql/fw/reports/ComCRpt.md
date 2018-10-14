searchComCRpt
===
select @pageTag(){
		t.RPT_ID,
       t.PRPT_CODE,
       t.RPT_CODE,
       t.RPT_NAME,
       t.APP_CODE,
       t.RPT_CAT,
       t.RPT_TYP,
       (select a.SQL_CONTENT
          from COM_C_SQL a
         where a.SQL_CODE = t.EXEC_SQL
           and a.is_use = 'Y') EXEC_SQL,
       t.SHOW_TYP,
       t.WIN_EVENT,
       t.WIN_PARM,
       t.OPEN_MODE,
       t.REMARK,
       t.IS_USE,
       t.ORD_BY,
       t.CREATE_BY,
       t.CREATE_TIME,
       t.MODIFY_BY,
       t.MODIFY_TIME
       @}
  from COM_C_RPT t
 where t.is_use = 'Y'
 @if(!isEmpty(rptName)){
 	and t.RPT_NAME like #rptName#
 @}
 @pageIgnoreTag(){
 order by t.ord_by
 @}
 
createNewRptCode
===
 select to_char(sysdate, 'yyyymmdd') ||
       (select trim(to_char(nvl(max(substr(t.rpt_code, 9, 4)), 0) + 1,
                            '0000'))
          from COM_C_RPT t
         where substr(t.rpt_code, 1, 8) = to_char(sysdate, 'yyyymmdd'))
  from dual
 