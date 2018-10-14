getList
===
select id,wechat_no,id_code,user_name,nick_name,tel,info,query_power,role_power,is_black
from rp_user where 1=1

@if(!isEmpty(name)){
	and (wechat_no like  #'%'+name+'%'# or user_name like  #'%'+name+'%'# )
@}
@if(!isEmpty(idCode)){
	
	and id_code = #idCode#
@}
;

getInfo
===
select id,wechat_no,id_code,user_name,nick_name,tel,info,query_power,role_power,is_black
from rp_user   where id=#id#;

getInfoByWechat
===
select id,wechat_no,id_code,user_name,nick_name,tel,info,query_power,role_power,is_black
from rp_user  where wechat_no=#wechatNo#;

delete
===
delete from rp_user where id= #id# ;

insert
===
insert into rp_user( wechat_no,id_code,user_name,nick_name,
tel,info,query_power,role_power,is_black)
VALUES(#wechatNo#,#idCode#,#userName#,#nickName#,
,#tel#,#info#,#queryPower#,#rolePower#,#isBlack#);

update
===
update  rp_user
set user_name = #userName#,
tel=#tel#,
info=#info#,
query_power=#queryPower#,
role_power=#rolePower#,
is_black=#isBlack#
where id = #id#;

reg
===
insert into rp_user( wechat_no,nick_name,id_code,query_power,role_power,is_black)
VALUES(#wechatNo#,#nickName#,#idCode#,#queryPower#,#rolePower#,#isBlack#);

getUsersByStore
===
select wechat_no,user_name ,nick_name from rp_user where id_code=#idCode#;