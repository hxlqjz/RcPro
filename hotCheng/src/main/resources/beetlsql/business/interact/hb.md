getList
===
select * 
from hb
order by pp,cj,cx,pl,nk,scplx;

insert
===
insert into hb_copy(pp,cj,cx,pl,nk,scplx,d1,d2,d3,d4,d5,d6,d7,t1,t2,fl1,fl2,o1,o2,o3,c1,c2,c3,c4,c5 )
VALUES(#pp#,#cj#,#cx#,#pl#,#nk#,#scplx#,#d1#,#d2#,#d3#,#d4#,#d5#,#d6#,#d7#,#t1#,#t2#,#fl1#,#fl2#,#o1#
,#o2#,#o3#,#c1#,#c2#,#c3#,#c4#,#c5#);

getBf
===
select * 
from bf
order by pp,cj,cx,pl,nk,scplx;

getBds
===
select * 
from bds
order by pp,cj,cx,pl,nk,scplx;

updateBf
===
update hb_copy
set bf1=#bf1#,
bf2=#bf2#,
bf3=#bf3#,
bf4=#bf4#,
bf5=#bf5#
where  pp=#pp# 
and cj=#cj# 
and cx=#cx# 
and pl=#pl# 
and nk=#nk# 
and scplx=#scplx#;

updateBds
===
update hb_copy
set bds1=#bds1#,
bds2=#bds2#,
bds3=#bds3#,
bds4=#bds4#
where  pp=#pp# 
and cj=#cj# 
and cx=#cx# 
and pl=#pl# 
and nk=#nk# 
and scplx=#scplx#;

insertBf
===
insert into bf_copy(pp,cj,cx,pl,nk,scplx,bm1,bm2,bm3,bm4,bm5)
VALUES(#pp#,#cj#,#cx#,#pl#,#nk#,#scplx#,#bm1#,#bm2#,#bm3#,#bm4#,#bm5#);

insertBds
===
insert into bds_copy(pp,cj,cx,pl,nk,scplx,bm1,bm2,bm3,bm4)
VALUES(#pp#,#cj#,#cx#,#pl#,#nk#,#scplx#,#bm1#,#bm2#,#bm3#,#bm4#);  

deleteBds
===
delete from bds_copy 
where pp=#pp#
and cj=#cj#
and cx=#cx#
and pl=#pl#
and nk=#nk#
and scplx=#scplx#;

insertDb
===
insert into dbtable(pp,cj,cx,pl,nk,scplx,d1,d2,d3,d4,d5,d6,d7,t1,t2,fl1,fl2,o1,o2,o3,
c11,c12,c13,c14,c21,c22,c23,c24,c31,c32,c33,c34,c41,c42,c43,c44,
bf1,bf2,bf3,bf4,bds1,bds2,bds3,bds4,chang1,chang2,chang3,chang4,zh1,zh2,zh3,zh4,cy)
VALUES(#pp#,#cj#,#cx#,#pl#,#nk#,#scplx#,#d1#,#d2#,#d3#,#d4#,#d5#,#d6#,#d7#,#t1#,#t2#,#fl1#,#fl2#,#o1#
,#o2#,#o3#,#c11#,#c12#,#c13#,#c14#,#c21#,#c22#,#c23#,#c24#,#c31#,#c32#,#c33#,#c34#,#c41#,#c42#,#c43#,#c44#
,#bf1#,#bf2#,#bf3#,#bf4#,#bds1#,#bds2#,#bds3#,#bds4#,#chang1#,#chang2#,#chang3#,#chang4#,#zh1#,#zh2#,#zh3#,#zh4#,#cy#);

getHbList
===
select * 
from hbtable
order by pp,cj,cx,pl,nk,scplx; 

getBfByBds
===
select bf,bds from cp where bds in (#join(bds)#);