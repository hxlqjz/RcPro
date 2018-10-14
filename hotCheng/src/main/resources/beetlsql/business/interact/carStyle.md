findCarBrandList
===
SELECT  DISTINCT  t.car_brand FROM  car_style  t;

findfatoryList
===
SELECT  DISTINCT  t.fatory FROM  car_style  t where t.car_brand=#carBrand# order by t.fatory desc;

findFactorList
===
SELECT  DISTINCT  t.fatory FROM  car_style  t where t.car_brand=#carBrand# order by t.fatory desc;

findCarFormList
===
SELECT  DISTINCT  t.car_form FROM  car_style  t where t.car_brand=#carBrand#  and t.fatory=#fatory# ;

findCarOutputList
===
SELECT  DISTINCT  t.car_output_tl FROM  car_style  t where t.car_brand=#carBrand#  and t.fatory=#fatory#  and t.car_form=#carForm# order by t.car_output_tl asc;


findCarYearList
===
SELECT  DISTINCT  t.car_year FROM  car_style  t where t.car_brand=#carBrand#  and t.fatory=#fatory#  and t.car_form=#carForm# and t.car_output_tl=#carOutput# order by t.car_year asc;
 


findMarketYearList
===
SELECT  DISTINCT  t.produce_year FROM  car_style  t where t.car_brand=#carBrand#  and t.fatory=#fatory#  and t.car_form=#carForm# and t.car_output_tl=#carOutput# and t.car_year=#carYear# and t.sale_name=#saleName#  order by t.produce_year asc;

findSaleNameList
===
SELECT  DISTINCT  t.sale_name FROM  car_style  t where t.car_brand=#carBrand#  and t.fatory=#fatory#  and t.car_form=#carForm# and t.car_output_tl=#carOutput# and t.car_year=#carYear# ;

queryCarList
===
SELECT  t.ly_id,t.car_brand,t.fatory,t.car_form,t.car_output_tl,t.car_year,t.produce_year,t.sale_name,t.fatory,t.output_stand,t.engine_type,
t.gas_form,t.gas_grade,t.cylinder_num,t.shift_memo,t.gears_num,t.prefix_braking_type,t.suffix_braking_type,t.assistance_type,t.wheelbase,
t.preffix_tyre_scale,t.suffix_tyre_scale
FROM  car_style  t where t.car_brand=#carBrand#  and t.fatory=#fatory#  and t.car_form=#carForm# and t.car_output_tl=#carOutput# and t.car_year=#carYear# and t.produce_year=#produceYear# and t.sale_name=#saleName# order by t.car_output_tl, t.produce_year, t.car_year asc;

findCarGoodById
===
SELECT  t.ly_id,t.car_brand,t.fatory,t.car_form,t.car_output_tl,t.car_year,t.produce_year,t.sale_name,t.fatory,t.output_stand,t.engine_type,
t.gas_form,t.gas_grade,t.cylinder_num,t.shift_memo,t.gears_num,t.prefix_braking_type,t.suffix_braking_type,t.assistance_type,t.wheelbase,
t.preffix_tyre_scale,t.suffix_tyre_scale,t.chassis,t.good_a,t.good_b,t.good_c,t.good_d,t.good_e,t.good_f,t.good_g,t.good_h
FROM  car_style  t where t.ly_id=#lyId#;

findCarGoodByIds
===
SELECT  t.ly_id,t.car_brand,t.fatory,t.car_form,t.car_output_tl,t.car_year,t.produce_year,t.sale_name,t.fatory,t.output_stand,t.engine_type,
t.gas_form,t.gas_grade,t.cylinder_num,t.shift_memo,t.gears_num,t.prefix_braking_type,t.suffix_braking_type,t.assistance_type,t.wheelbase,
t.preffix_tyre_scale,t.suffix_tyre_scale,t.chassis
FROM  car_style  t where t.ly_id in  ( #join(ids)#);

updateCarStyleData
===
update car_style t set t.shift_memo=#shiftMemo# where t.ly_id=#lyId#;

