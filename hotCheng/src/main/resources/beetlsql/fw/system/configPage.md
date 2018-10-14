findPageMainList
===

select @pageTag(){
t.page_id,
t.create_time,
t.is_use,
t.page_superior,
t.page_title,
t.page_type,
t.page_url
 @}
 from config_page_main t
 where t.is_use='Y'
 order by t.page_superior desc
 
 findPageDetailList
===

select @pageTag(){
t.detail_id,
t.create_time,
t.is_use,
t.page_id,
t.page_title,
t.h_type,
t.page_url
 @}
 from config_page_detail t
 where t.is_use='Y'
  @if(!isEmpty(pageId)){
   and t.page_id = #pageId#
 @}
 
 findMainPageBySup
===

select @pageTag(){
t.page_id,
t.create_time,
t.is_use,
t.page_superior,
t.page_title,
t.page_type,
t.page_url
 @}
 from config_page_main t
 where t.is_use='Y'
  @if(!isEmpty(pageSuperior)){
   and t.page_superior = #pageSuperior#
 @}