judeHaveColumn
===
select count(1)
  from COM_C_RPT_HEAD t, com_c_rpt a
 where a.rpt_id = #rptId#
   and t.rpt_code = a.rpt_code
   and t.is_use = 'Y'
   
getComCRptHeadList
===
select t.head_id,
       t.rpt_code,
       t.head_code,
       t.head_name,
       t.is_fld,
       t.ord_by,
       t.is_mege
  from COM_C_RPT_HEAD t, com_c_rpt a
 where a.rpt_id = #rptId#
   and t.rpt_code = a.rpt_code
   and t.is_use = 'Y'
 order by t.ord_by
 
getPHeadCodeList
=== 
 select t.head_code, t.head_name
  from COM_C_RPT_HEAD t
 where t.rpt_code = #rptCode#
   and t.is_mege = 'Y'
   and t.is_use = 'Y'