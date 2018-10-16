findNgkBrandList
===
SELECT DISTINCT t.pping FROM wetdata t order by t.pping asc;

findCarFormListByBrand
===
select distinct t.xche from wetdata t where  t.pping =#ngkBrand# order by t.xche asc;

findCarFormListByBrandAndType
===
select distinct t.lpai from wetdata t where  t.pping =#ngkBrand#  and t.xche=#ngkType# order by t.lpai asc;

findCarListByBrandAndTypeAndOutPut
===
select  distinct t.bban from wetdata t where  t.pping =#ngkBrand#  and t.xche=#ngkType# and t.lpai=#outputAmount# order by t.bban asc;

findCarListByBrandAndTypeAndOutPutAndYear
===
select @pageTag(){
t.pping,
t.xche,
t.bban,
t.lpai,
t.xh1,
t.xh2
 @}
from wetdata t where  t.pping =#ngkBrand#  and t.xche=#ngkType# and t.lpai=#outputAmount#
and t.bban=#markeyYear# order by t.bban,t.xche,t.lpai,t.pping asc;