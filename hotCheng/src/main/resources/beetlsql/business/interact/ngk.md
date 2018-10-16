findNgkBrandList
===
select distinct t.ngk_brand from ngk t  order by t.ngk_brand asc;

findCarFormListByBrand
===
select distinct t.ngk_type from ngk t where  t.ngk_brand =#ngkBrand# order by t.ngk_type asc;

findCarFormListByBrandAndType
===
select distinct t.output_amount from ngk t where  t.ngk_brand =#ngkBrand#  and t.ngk_type=#ngkType# order by t.output_amount asc;

findCarListByBrandAndTypeAndOutPut
===
select  distinct t.market_year from ngk t where  t.ngk_brand =#ngkBrand#  and t.ngk_type=#ngkType# and t.output_amount=#outputAmount# order by t.market_year asc;

findCarListByBrandAndTypeAndOutPutAndYear
===
select @pageTag(){
t.ngk_brand,
t.ngk_type,
t.output_amount,
t.market_year,
t.engine_type,
t.spark_plug,
t.stock_num,
t.ix_sprak_plug,
t.gear_num
 @}
from ngk t where  t.ngk_brand =#ngkBrand#  and t.ngk_type=#ngkType# and t.output_amount=#outputAmount#
and t.market_year=#markeyYear# order by t.output_amount,t.market_year asc;