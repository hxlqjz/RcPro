getAllCarStyle
===
select  car_brand,car_factory,car_model,car_query 
from rc_car_query;

getOptByStyle
===
select 
	group_concat(distinct plms) as pl,
  group_concat(distinct nk) as nk,
  group_concat(distinct fdjxh) as fdj,
	group_concat(distinct pth) as pt,
	GROUP_CONCAT(distinct bsqms) as bsx,
	GROUP_CONCAT(distinct lggg) as lg,
	GROUP_CONCAT(DISTINCT xsmc) as xsmc,
	GROUP_CONCAT(DISTINCT scnf) as scnf
from rc_car_style
where pp= #carBrand#
and cj= #carFactory#
and cx = #carModel#;

getCarStyleByKey
===
select ly_id,pp,cj,cx,nk,plms,pth,fdjxh,bsqms,lggg,xsmc,scnf,pfbz,
jqxs,rylx,rybh,qgs,dws,qzdqlx,hzdqlx,zllx,zj,ltgg,
rhybyjzl,rhy_avia_1,rhy_avia_2,rhy_avia_3,rhy_avia_4,
bsxy_avia,bsxybz,bsxbyjzl,bsxdxjzl,
bf_qzd_1,bf_qzd_2,bf_qzd_3,bf_hzd_1,bf_hzd_2,bf_hzd_3,
bds_qzd_1,bds_qzd_2,bds_qzd_3,bds_hzd_1,bds_hzd_2,bds_hzd_3,
nbe_1,nbe_2,lg_1,lg_2
from rc_car_style
where pp=#carBrand# 
and cj=#carFactory#
and cx = #carModel#
@if(!isEmpty(pl)){
	and plms=#pl#
@}
@if(!isEmpty(nk)){
	and nk=#nk#
@}
@if(!isEmpty(bsx)){
	and bsqms=#bsx#
@}
@if(!isEmpty(pt)){
	and pth=#pt#
@}
@if(!isEmpty(fdj)){
	and fdjxh=#fdj#
@}
@if(!isEmpty(lg)){
	and lggg=#lg#
@}
@if(!isEmpty(xsmc)){
	and xsmc=#xsmc#
@}
@if(!isEmpty(scnf)){
	and scnf=#scnf#
@}
;

getFactoryAndModelByBrand
===
select car_brand,car_factory,car_model from rc_car_query
where car_brand=#carBrand# order by car_factory, car_model;

getCarStyleByLyId
===
select ly_id,pp,cj,cx,nk,plms,pth,fdjxh,bsqms,lggg,xsmc,scnf,pfbz,
jqxs,rylx,rybh,qgs,dws,qzdqlx,hzdqlx,zllx,zj,ltgg,
rhybyjzl,rhy_avia_1,rhy_avia_2,rhy_avia_3,rhy_avia_4,
bsxy_avia,bsxybz,bsxbyjzl,bsxdxjzl,
bf_qzd_1,bf_qzd_2,bf_qzd_3,bf_hzd_1,bf_hzd_2,bf_hzd_3,
bds_qzd_1,bds_qzd_2,bds_qzd_3,bds_hzd_1,bds_hzd_2,bds_hzd_3,
nbe_1,nbe_2,lg_1,lg_2
from rc_car_style
where ly_id  in ( #join(ids)#);