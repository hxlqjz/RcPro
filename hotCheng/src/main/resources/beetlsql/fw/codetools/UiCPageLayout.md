findPageLayoutList
===
SELECT t.layout_id,
       t.layout_code,
       t.layout_name,
       t.direction,
       (SELECT a.dict_name
          FROM com_c_dict a
         WHERE a.cat_code = 'direction'
           AND a.dict_code = t.direction) direction_name,
       t.height,
       t.width,
       t.units,
       t.url,
       t.remark,
       t.ord_by
  FROM ui_c_page_layout t
 WHERE t.page_code = #pageCode#
 ORDER BY t.ord_by

createUnionLayoutCode
=== 
 select (select to_char(sysdate, 'yyyymmdd') ||
               trim(to_char(nvl(substr(max(t.layout_code), 9, 4), 0) + 1,
                            '0000'))
          from ui_c_page_layout t
         where substr(t.layout_code, 1, 8) = to_char(sysdate, 'yyyymmdd'))
  from dual
  
checkLayoutDirectionUnion
===  
  SELECT COUNT(0)
  FROM ui_c_page_layout t
 WHERE t.page_code = #pageCode#
   AND t.direction = #direction#
   @if(!isEmpty(layoutId)){
   AND t.layout_id <> #layoutId#
   @}
   
findPageUrlList
===   
   SELECT t.page_name,
       t.url page_url
  FROM ui_c_page t
 WHERE t.is_use = 'Y'
   --AND t.page_typ = 'PAGE'
   ORDER BY t.ord_by
