findMsgLogList
===
select t.msg_id,
       t.title,
       t.msg,
       t.msg_type,
       t.user_code,
       t.type,
       t.create_time,
       t.is_use,
       t.content 
       from msg_log t
       where 1=1
       and t.type in ( '0')
       @if(!isEmpty(userCode)){
		 or( t.user_code = #userCode# and t.type in('1','2','3','4') and t.is_use='Y')
	   @}
	   order by t.type asc
	   
	   