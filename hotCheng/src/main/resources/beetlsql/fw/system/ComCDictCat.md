searchComCDictCat
===
select @pageTag(){
	*
	@}
  from COM_C_DICT_CAT t
 where t.is_use = 'Y'
 @if(!isEmpty(catName)){
 	and (t.cat_code || ',' || t.cat_name like #catName#)
 @}
   @pageIgnoreTag(){
    order by t.ord_by
   @}

getComCDictList
===  
select @pageTag(){
	*
	@}
  from COM_C_DICT t
 where t.is_use = 'Y'
 	and t.cat_code = #catCode#
 @if(!isEmpty(dictName)){
   and (t.dict_code || ',' || t.dict_name like #dictName#)
 @}
   @pageIgnoreTag(){
    order by t.ord_by
   @}

getComCDictTree
===
select @pageTag(){
		t.dict_id id,
       t.dict_code code,
       t.dict_name text,
       case
         when (select count(1)
                 from COM_C_DICT a
                where a.pdict_code = t.dict_code
                  and a.is_use = 'Y') > 0 then
          'closed'
         else
          'open'
       end state
       @}
  from COM_C_DICT t
 where t.pdict_code = #pdictCode#
   and t.is_use = 'Y'
   and t.cat_code = #catCode#
 @pageIgnoreTag(){
 order by t.ord_by
 @}
 
judgeDictCodeIsExit
=== 
 select count(1)
  from COM_C_DICT t
 where t.is_use = 'Y'
   and t.dict_code = #dictCode#
   and t.cat_code = #catCode#
   @if(!isEmpty(dictId)){
   and t.dict_id != #dictId#
   @}

getDicData
===
select t.dict_code, t.dict_name, t.cat_code,t.MNEMONIC
  from COM_C_DICT t
 where t.cat_code = #catCode#
   and t.is_use = 'Y'
 order by t.ord_by
 
getDicTreeData
=== 
select t.dict_code , t.dict_name
  from COM_C_DICT t
 where t.cat_code = #catCode#
   and t.pdict_code = #pdictCode#
   and t.is_use = 'Y'
 order by t.ord_by

getDictCatData
===
select t.cat_code, t.cat_name
  from COM_C_DICT_CAT t
 where t.is_use = 'Y'
 order by t.ord_by
 
getComCDictByTypAndCode
===
select t.dict_id, t.cat_code, t.dict_code, t.dict_name, t.ord_by
  from com_c_dict t
 where t.cat_code = #catCode#
   and t.dict_code = #dictCode#
 