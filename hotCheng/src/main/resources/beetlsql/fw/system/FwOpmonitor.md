searchFwOpmonitor
===
* 方法访问日志查询
select @pageTag(){
		t.ID,
       t.CREATE_DATE,
       t.MODIFY_DATE,
       t.OP_ACTIONAME,
       t.OP_CONTENT,
       t.OP_IP,
       t.OP_OPTIME,
       t.OP_RESULT,
       t.OP_TOTALTIME,
       t.OP_URL,
       t.OP_USERID,
       t.OP_USERNAME
       @}
  from FW_OPMONITOR t
 where 1 = 1
 @if(!isEmpty(operateBy)){
 	and t.op_username like #operateBy#
 @}
 @if(!isEmpty(actionDesc)){
 	and t.op_content like #actionDesc#
 @}
 @if(!isEmpty(startDate)){
 	and t.op_optime > =
       to_date(#startDate#, 'yyyy-mm-dd hh24:mi:ss')
 @}
 @if(!isEmpty(endDate)){
    and t.op_optime <=
       to_date(#endDate#, 'yyyy-mm-dd hh24:mi:ss')
 @}

       @pageIgnoreTag(){
 order by t.op_optime desc
 @}