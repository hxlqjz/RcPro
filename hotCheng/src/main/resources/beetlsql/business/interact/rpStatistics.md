getList
===
select id,presale_area,qr_code,qr_no,product_model,activiate_status, date_format(scan_time,'%Y-%m-%d %H:%i:%s') as  scan_time,
 date_format(quality_start,'%Y-%m-%d') as quality_start,date_format(quality_end,'%Y-%m-%d') as quality_end,date_format(cash_time,'%Y-%m-%d %H:%i:%s') as cash_time,
 scan_wechat,province,store_name,wechat_name,nick_name,
wechat_tel,wechat_info,query_power,role_power,plate_number,car_tel
 from rp_statistics
where 1=1 
@if(!isEmpty(qrNo)){
	and qr_no=#qrNo#
@}
@if(!isEmpty(startTime)){
	and scan_time >= #startTime# 
@}
@if(!isEmpty(endTime)){
	and scan_time <=#endTime#
@}
order by qr_no
;
getTjList
===
select date_format(scan_time,'%Y-%m-%d') as scanTime ,scan_wechat,nick_name,wechat_name,wechat_tel,count(1) as scanNum from rp_statistics
where activiate_status=2
@if(!isEmpty(wechat)){
	and (scan_wechat like #'%'+wechat+'%'# or wechat_name like #'%'+wechat+'%'# or wechat_tel like #'%'+wechat+'%'#)
@}
@if(!isEmpty(startTime)){
	and scan_time >= #startTime# 
@}
@if(!isEmpty(endTime)){
	and scan_time <=#endTime#
@}
group by date_format(scan_time,'%Y-%m-%d'),scan_wechat
order by date_format(scan_time,'%Y-%m-%d') desc
;
getWeList
===
select id,product_model,quality_start,quality_end
@if(queryPower == 1 ){
	,scan_wechat,wechat_name,nick_name,
	wechat_tel,wechat_info,plate_number,car_tel
@}
from rp_statistics
where  activiate_status= 2
@if(!isEmpty(productModel)){
	and product_model like  #'%'+productModel+'%'#
@}
;

getInfo
===
select id,presale_area,qr_code,qr_no,product_model,activiate_status, date_format(scan_time,'%Y-%m-%d %H:%i:%s') as  scan_time,
 date_format(quality_start,'%Y-%m-%d') as quality_start,date_format(quality_end,'%Y-%m-%d') as quality_end,date_format(cash_time,'%Y-%m-%d %H:%i:%s') as cash_time,
 scan_wechat,province,store_name,wechat_name,nick_name,
wechat_tel,wechat_info,query_power,role_power,plate_number,car_tel
 from rp_statistics
where id=#id#;

getInfoByQp
===
select id,product_model,date_format(quality_start,'%Y-%m-%d') as quality_start,date_format(quality_end,'%Y-%m-%d') as quality_end
@if(queryPower == 1 ){
	,scan_wechat,wechat_name,nick_name,
	wechat_tel,wechat_info,plate_number,car_tel
@}
from rp_statistics
where  activiate_status= 2
@if(!isEmpty(qrCode)){
	and qr_code = #qrCode#
@}
;
checkQr
===
select id,presale_area,qr_code,qr_no,product_model,activiate_status,scan_time,
quality_start,quality_end,cash_time,scan_wechat,province,store_name,wechat_name,nick_name,
wechat_tel,wechat_info,query_power,role_power,plate_number,car_tel,is_ys
 from rp_statistics
where 1=1 
@if(!isEmpty(qrCode)){
	and qr_code = #qrCode# 
@}
@if(!isEmpty(qrNo)){
	and qr_no =#qrNo#
@}
;

delete
===
delete from rp_statistics where id= #id# ;

insert
===
insert into rp_statistics(qr_code,qr_no,product_model,activiate_status)
VALUE(#qrCode#,#qrNo#,#productModel#,0);

update
===
UPDATE rp_statistics
SET
qr_code=#qrCode#,
qr_no=#qrNo#,
product_model=#productModel#,
activiate_status=#activiateStatus#
where id=#id#;

role_power1_query
===
select product_model,count(1) as count
from rp_statistics
where activiate_status=2
and is_ys=0
@if(!isEmpty(province)){
	and province = #province#
@}
@if(!isEmpty(storeName)){
	and store_name = #storeName#
@}
@if(!isEmpty(startTime)){
	and scan_time >= #startTime# 
@}
@if(!isEmpty(endTime)){
	and scan_time <=#endTime#
@}
group by product_model ;

role_power2_query
===
select product_model,count(1) as count
from rp_statistics
where activiate_status=2
and is_ys=0
@if(!isEmpty(province)){
	and province = #province#
@}
@if(!isEmpty(storeName)){
	and store_name = #storeName#
@}
@if(!isEmpty(wechatNo)){
	and scan_wechat = #wechatNo#
@}
@if(!isEmpty(startTime)){
	and scan_time >= #startTime# 
@}
@if(!isEmpty(endTime)){
	and scan_time <=#endTime#
@}
group by product_model ;

role_power3_query
===
select product_model,count(1) as count
from rp_statistics
where activiate_status=2
and is_ys=0
@if(!isEmpty(wechatNo)){
	and scan_wechat = #wechatNo#
@}
@if(!isEmpty(startTime)){
	and scan_time >= #startTime# 
@}
@if(!isEmpty(endTime)){
	and scan_time <=#endTime#
@}
group by product_model ;

role_power3_sum
===
select count(1)*5 as totalMoney from rp_statistics
where activiate_status=2
and is_ys=0 
@if(!isEmpty(wechatNo)){
	and scan_wechat = #wechatNo#
@}
;

activate
===
update rp_statistics
set activiate_status = 1,
presale_area=#presaleArea#
where 
qr_no BETWEEN #start# and #end#
;

getActiveByCode
===
select activiate_status
 from rp_statistics
 where qr_code=#qrCode#
 ;
 
getActivateCount
===
select count(1) from 
rp_statistics
where 
activiate_status=2
and qr_no BETWEEN #start# and #end# ;

scanToBack
===
UPDATE rp_statistics
SET
activiate_status=#activiateStatus#,
scan_time=#scanTime#,
quality_start=#qualityStart#,
quality_end=#qualityEnd#,
cash_time=#cashTime#,
scan_wechat=#scanWechat#,
province=#province#,
store_name=#storeName#,
nick_name= #nickName#,
wechat_name=#wechatName#,
wechat_tel=#wechatTel#,
wechat_info=#wechatInfo#,
query_power=#queryPower#,
role_power=#rolePower#,
plate_number=#plateNumber#,
car_tel=#carTel#
where qr_code=#qrCode#;

getProvince
===
select distinct(province) as province from rp_statistics where province is not null;

getStore
===
select distinct(store_name) as storeName from rp_statistics where province=#province#  and store_name is not null;