judgeCodeIsRepeat
===
select count(1)
  from #text(tableName)# t
 where t.is_use = 'Y'
   and #text(codeName)# = #code#
   @if(!isEmpty(id)){
   	and #text(primeryKeyName)# != #id#
   @}

getAttList
===
select @pageTag(){t.att_id,
t.tbl_name,
t.tbl_col,
t.tbl_key,
t.file_name,
t.file_typ,
t.file_path,
t.file_size,
t.file_ver,
t.memo,
t.create_by,
t.create_date
 @}
from com_c_att t
where t.att_id = #attId#
and t.tbl_name = #tblName#
and t.tbl_col = #tblCol#
and t.tbl_key = #tblKey#
@pageIgnoreTag(){
    order by t.create_date
@}

delAtt
===
delete from com_c_att t 
where t.att_id = #attId#

findByTblKeyAndTblName
===
select
t.att_id,
t.tbl_name,
t.tbl_col,
t.tbl_key,
t.file_name,
t.file_typ,
t.file_path,
t.file_size,
t.file_ver,
t.memo,
t.create_by,
t.create_date,
t.modify_by,
t.modify_date,
m.user_name
from com_c_att t,
     sys_c_user m
where t.tbl_name = #tblName#
  and t.tbl_key = #tblKey#
  and m.is_use = 'Y'
  and t.create_by = m.user_code
    order by t.create_date desc
