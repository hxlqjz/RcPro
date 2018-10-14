getInfo
===
select id,wechat_no,time,num
from rp_reg   where wechat_no=#wechatNo# and time=#time#;


insert
===
insert into rp_reg( wechat_no,time,num)
VALUES(#wechatNo#,#time#,#num#);

update
===
update  rp_reg
set num = #num#
where wechat_no = #wechatNo#
and time = #time#
;