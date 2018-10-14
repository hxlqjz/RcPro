findCarFormList
===
SELECT t.car_brand,t.fatory,t.car_form FROM car_style_sublist t where t.car_brand=#carBrand# order by t.fatory, t.car_form asc; 

findCarOutputList
===
SELECT DISTINCT t.car_output_tl FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#;

findCarYearList
===
SELECT t.car_year FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm# and t.car_output_tl=#outputTl# order by t.car_year asc;

findChassisList
===
SELECT t.chassis FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm# and t.car_output_tl=#outputTl#  and
t.car_year=#carYear#
order by t.chassis asc;

findEngineTypeList
===
SELECT  t.engine_type FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm# and t.car_output_tl=#outputTl#  and
t.car_year=#carYear# and t.chassis=#chassis#
order by t.engine_type asc;

findShiftMemoList
===
SELECT  t.shift_memo FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm# and t.car_output_tl=#outputTl#  and
t.car_year=#carYear# and t.chassis=#chassis# and t.engine_type=#engineType#
order by t.shift_memo asc;

findPreffixTyreScaleList
===
SELECT  t.preffix_tyre_scale FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm# and t.car_output_tl=#outputTl#  and
t.car_year=#carYear# and t.chassis=#chassis# and t.engine_type=#engineType# and t.shift_memo=#shiftMemo#
order by t.preffix_tyre_scale asc;

findSaleNameList
===
SELECT t.sale_name FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm# and t.car_output_tl=#outputTl#  and
t.car_year=#carYear# and t.chassis=#chassis# and t.engine_type=#engineType# and t.shift_memo=#shiftMemo#  and t.preffix_tyre_scale=#preffixTyreScale#
order by t.sale_name asc;

findProduceYearList
===
SELECT t.produce_year FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm# and t.car_output_tl=#outputTl#  and
t.car_year=#carYear# and t.chassis=#chassis# and t.engine_type=#engineType# and t.shift_memo=#shiftMemo#  and t.preffix_tyre_scale=#preffixTyreScale# and t.sale_name=#saleName#
order by t.produce_year asc;

findProducesByOpt
===
SELECT  t.car_output_tl,t.car_year,t.chassis,t.shift_memo, t.preffix_tyre_scale,t.sale_name, t.engine_type,t.produce_year FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#  and t.fatory=#fatory#
  @if(!isEmpty(outputTl)){
		and t.car_output_tl=#outputTl#  
   @}
   @if(!isEmpty(carYear)){
		and t.car_year=#carYear# 
   @}
   @if(!isEmpty(chassis)){
		and t.chassis=#chassis# 
   @}
   @if(!isEmpty(shiftMemo)){
		and t.shift_memo=#shiftMemo# 
   @}
   @if(!isEmpty(engineType)){
		and t.engine_type=#engineType# 
   @}
   @if(!isEmpty(preffixTyreScale)){
		 and t.preffix_tyre_scale=#preffixTyreScale# 
   @}
   @if(!isEmpty(saleName)){
		and t.sale_name=#saleName#
   @}
   
order by t.car_output_tl,t.car_year,t.chassis,t.shift_memo, t.preffix_tyre_scale,t.sale_name, t.engine_type,t.produce_year asc;

findPreBrakeByOpt
===
SELECT
	t.ly_id,
	t.f_bds_brake1,
	t.f_bds_brake2,
	t.f_bds_brake3,
	t.f_bf_brake1,
	t.f_bf_brake2,
	t.f_bf_brake3,
	t.f_bf_brake4,
	t.f_bf_brake5
FROM
	product_front_brake t
where t.ly_id in( SELECT  t.ly_id  FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#  and t.fatory=#fatory#
  @if(!isEmpty(outputTl)){
		and t.car_output_tl=#outputTl#  
   @}
   @if(!isEmpty(carYear)){
		and t.car_year=#carYear# 
   @}
   @if(!isEmpty(chassis)){
		and t.chassis=#chassis# 
   @}
   @if(!isEmpty(shiftMemo)){
		and t.shift_memo=#shiftMemo# 
   @}
   @if(!isEmpty(engineType)){
		and t.engine_type=#engineType# 
   @}
   @if(!isEmpty(preffixTyreScale)){
		 and t.preffix_tyre_scale=#preffixTyreScale# 
   @}
   @if(!isEmpty(saleName)){
		and t.sale_name=#saleName#
   @}
   
)

findSufBrakeByOpt
===
SELECT
	t.ly_id,
	t.r_bds_brake1,
	t.r_bds_brake2,
	t.r_bds_brake3,
	t.r_bf_brake1,
	t.r_bf_brake2,
	t.r_bf_brake3,
	t.r_bf_brake4,
	t.r_bf_brake5
FROM
	product_rear_brake t
where t.ly_id in(SELECT  t.ly_id  FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#  and t.fatory=#fatory#
  @if(!isEmpty(outputTl)){
		and t.car_output_tl=#outputTl#  
   @}
   @if(!isEmpty(carYear)){
		and t.car_year=#carYear# 
   @}
   @if(!isEmpty(chassis)){
		and t.chassis=#chassis# 
   @}
   @if(!isEmpty(shiftMemo)){
		and t.shift_memo=#shiftMemo# 
   @}
   @if(!isEmpty(engineType)){
		and t.engine_type=#engineType# 
   @}
   @if(!isEmpty(preffixTyreScale)){
		 and t.preffix_tyre_scale=#preffixTyreScale# 
   @}
   @if(!isEmpty(saleName)){
		and t.sale_name=#saleName#
   @}
   )
   
findOilsByOpt
===
SELECT t1.ly_id,t1.oil_fill ,t2.bz jybz,t2.avia1,t2.avia2,t2.avia3,t2.avia4 ,t2.sp_bsxrg_yl,t3.bz bsxbz ,t3.avia_bsxyp_xh FROM product_oil_1 t1  
join product_oil_2 t2 
on t2.ly_id=t1.ly_id
join product_oil_3 t3
on t3.ly_id=t1.ly_id
where t1.ly_id in(SELECT  t.ly_id  FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#  and t.fatory=#fatory#
  @if(!isEmpty(outputTl)){
		and t.car_output_tl=#outputTl#  
   @}
   @if(!isEmpty(carYear)){
		and t.car_year=#carYear# 
   @}
   @if(!isEmpty(chassis)){
		and t.chassis=#chassis# 
   @}
   @if(!isEmpty(shiftMemo)){
		and t.shift_memo=#shiftMemo# 
   @}
   @if(!isEmpty(engineType)){
		and t.engine_type=#engineType# 
   @}
   @if(!isEmpty(preffixTyreScale)){
		 and t.preffix_tyre_scale=#preffixTyreScale# 
   @}
   @if(!isEmpty(saleName)){
		and t.sale_name=#saleName#
   @}
   )
   
findBatterysByOpt
===
SELECT
	t.ly_id,
	t.battery_product1,
	t.battery_product2,
	t.battery_remark
FROM
	product_battery t
where t.ly_id in(SELECT  t.ly_id  FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#  and t.fatory=#fatory#
  @if(!isEmpty(outputTl)){
		and t.car_output_tl=#outputTl#  
   @}
   @if(!isEmpty(carYear)){
		and t.car_year=#carYear# 
   @}
   @if(!isEmpty(chassis)){
		and t.chassis=#chassis# 
   @}
   @if(!isEmpty(shiftMemo)){
		and t.shift_memo=#shiftMemo# 
   @}
   @if(!isEmpty(engineType)){
		and t.engine_type=#engineType# 
   @}
   @if(!isEmpty(preffixTyreScale)){
		 and t.preffix_tyre_scale=#preffixTyreScale# 
   @}
   @if(!isEmpty(saleName)){
		and t.sale_name=#saleName#
   @}
   ) 
   
findSparksByOpt
===
SELECT
	t.ly_id,
	t.hhs_product1,t.hhs_product2,t.hhs_bz
FROM
	product_spark t
where t.ly_id in(SELECT  t.ly_id  FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#  and t.fatory=#fatory#
  @if(!isEmpty(outputTl)){
		and t.car_output_tl=#outputTl#  
   @}
   @if(!isEmpty(carYear)){
		and t.car_year=#carYear# 
   @}
   @if(!isEmpty(chassis)){
		and t.chassis=#chassis# 
   @}
   @if(!isEmpty(shiftMemo)){
		and t.shift_memo=#shiftMemo# 
   @}
   @if(!isEmpty(engineType)){
		and t.engine_type=#engineType# 
   @}
   @if(!isEmpty(preffixTyreScale)){
		 and t.preffix_tyre_scale=#preffixTyreScale# 
   @}
   @if(!isEmpty(saleName)){
		and t.sale_name=#saleName#
   @}
   )

findCarGoodByOpt
===
SELECT  t.ly_id,t.car_brand,t.fatory,t.car_form,t.car_output_tl,t.car_year,t.produce_year,t.sale_name,t.fatory,t.output_stand,t.engine_type,
t.gas_form,t.gas_grade,t.cylinder_num,t.shift_memo,t.gears_num,t.prefix_braking_type,t.suffix_braking_type,t.assistance_type,t.wheelbase,
t.preffix_tyre_scale,t.suffix_tyre_scale,t.chassis
FROM  car_style  t where t.ly_id in (SELECT  t.ly_id  FROM car_style_sublist t where t.car_brand=#carBrand# and t.car_form=#carForm#  and t.fatory=#fatory#
  @if(!isEmpty(outputTl)){
		and t.car_output_tl=#outputTl#  
   @}
   @if(!isEmpty(carYear)){
		and t.car_year=#carYear# 
   @}
   @if(!isEmpty(chassis)){
		and t.chassis=#chassis# 
   @}
   @if(!isEmpty(shiftMemo)){
		and t.shift_memo=#shiftMemo# 
   @}
   @if(!isEmpty(engineType)){
		and t.engine_type=#engineType# 
   @}
   @if(!isEmpty(preffixTyreScale)){
		 and t.preffix_tyre_scale=#preffixTyreScale# 
   @}
   @if(!isEmpty(saleName)){
		and t.sale_name=#saleName#
   @}
   );
         
    
findCarBrandFatoryForm
===  
SELECT DISTINCT t.car_brand_fatory_form,t.car_brand,t.fatory,t.car_form FROM car_style_sublist t order by t.car_brand_fatory_form;   
   
   
   
findPreBrakeByIds
===
SELECT
	t.ly_id,
	t.f_bds_brake1,
	t.f_bds_brake2,
	t.f_bds_brake3,
	t.f_bf_brake1,
	t.f_bf_brake2,
	t.f_bf_brake3,
	t.f_bf_brake4,
	t.f_bf_brake5
FROM
	product_front_brake t
where t.ly_id in(#join(ids)#)   


findSufBrakeByIds
===
SELECT
	t.ly_id,
	t.r_bds_brake1,
	t.r_bds_brake2,
	t.r_bds_brake3,
	t.r_bf_brake1,
	t.r_bf_brake2,
	t.r_bf_brake3,
	t.r_bf_brake4,
	t.r_bf_brake5
FROM
	product_rear_brake t
where t.ly_id in (#join(ids)#)   

findOilsByIds
===
SELECT t1.ly_id,t1.oil_fill ,t2.bz jybz,t2.avia1,t2.avia2,t2.avia3,t2.avia4 ,t2.sp_bsxrg_yl,t3.bz bsxbz ,t3.avia_bsxyp_xh FROM product_oil_1 t1  
join product_oil_2 t2 
on t2.ly_id=t1.ly_id
join product_oil_3 t3
on t3.ly_id=t1.ly_id
where t1.ly_id in(#join(ids)#)   


findBatterysByIds
===
SELECT
	t.ly_id,
	t.battery_product1,
	t.battery_product2,
	t.battery_remark
FROM
	product_battery t
where  t.ly_id in (#join(ids)#)   
   
findSparksByIds
===
SELECT
	t.ly_id,
	t.hhs_product1,t.hhs_product2,t.hhs_bz
FROM
	product_spark t
where  t.ly_id in (#join(ids)#)   