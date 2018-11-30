getList
===
select id,wheel,bfd,bf_code,bf_memo,bds_code,bds_memo,
d_code,fmsi_code,fldm1,fldm2,trw1,trw2,oe1,oe2,oe3,oe4,oe5,img_src
from rc_bfd_brake
where 1=1
@if(!isEmpty(bfd)){
	and bfd = #bfd#
@}
@if(!isEmpty(bCode)){
	and (bf_code=#bCode# OR bds_code=#bCode#)
@}
;


getInfo
===
select id,wheel,bfd,bf_code,bf_memo,bds_code,bds_memo,
d_code,fmsi_code,fldm1,fldm2,trw1,trw2,oe1,oe2,oe3,oe4,oe5,img_src
from rc_bfd_brake
where id=#id#;



delete
===
delete from rc_bfd_brake where id= #id# ;

insert
===
insert into rc_bfd_brake(wheel,bfd,bf_code,bf_memo,bds_code,bds_memo,
d_code,fmsi_code,fldm1,fldm2,trw1,trw2,oe1,oe2,oe3,oe4,oe5,img_src)
VALUE(#wheel#,#bfd#,#bfCode#,#bfMemo#,#bdsCode#,#bdsMemo#
,#dCode#,#fmsiCode#,#fldm1#,#fldm2#,#trw1#,#trw2#,#oe1#,#oe2#,#oe3#,#oe4#,#oe5#,#imgSrc#);

update
===
UPDATE rc_bfd_brake
SET
wheel=#wheel#,
bfd=#bfd#,
bf_code=#bfCode#,
bf_memo=#bfMemo#,
bds_code=#bdsCode#,
bds_memo=#bdsMemo#,
d_code=#dCode#,
fmsi_code=#fmsiCode#,
fldm1=#fldm1#,
fldm2=#fldm2#,
trw1=#trw1#,
trw2=#trw2#,
oe1=#oe1#,
oe2=#oe2#,
oe3=#oe3#,
oe4=#oe4#,
oe5=#oe5#,
img_src=#imgSrc#
where id=#id#;

updateImg
===
UPDATE rc_bfd_brake
SET
img_src=#imgSrc#
where id=#id#;

