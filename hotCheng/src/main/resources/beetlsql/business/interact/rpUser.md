getList
===
select id,wechat_no,id_code,user_name,nick_name,tel,info,query_power,role_power,is_black
from rp_user where 1=1

@if(!isEmpty(name)){
	and (nick_name like  #'%'+name+'%'# or user_name like  #'%'+name+'%'#  or tel like  #'%'+name+'%'#)
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
id_code = #idCode#,
tel=#tel#,
info=#info#,
query_power=#queryPower#,
role_power=#rolePower#,
is_black=#isBlack#
where id = #id#;

reg
===
insert into rp_user( wechat_no,nick_name,id_code,user_name,tel,info,query_power,role_power,is_black)
VALUES(#wechatNo#,#nickName#,#idCode#,#userName#,#tel#,#info#,#queryPower#,#rolePower#,#isBlack#);

getUsersByStore
===
select u.wechat_no,u.user_name ,u.nick_name,u.tel,u.info from rp_user as u where 1=1
and u.id_code in 
(
select s.id_code from rp_store  as s
where s.province=#province# and s.store_name=#storeName#
);