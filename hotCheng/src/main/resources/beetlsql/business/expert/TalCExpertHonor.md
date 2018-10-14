select
===

select @pageTag(){
t.HONOR_ID,
t.EXPERT_ID,
t.HONOR_INFO,
to_char(t.HONOR_TIME,'yyyy-MM-dd hh24:mi:ss') HONOR_TIME,
t.CREATE_BY,
t.CREATE_TIME,
t.MODIFY_BY,
t.MODIFY_TIME,
t.IS_USE
 @}
from TAL_C_EXPERT_HONOR t
where t.is_use = 'Y'
and t.EXPERT_ID = #expertId#
@pageIgnoreTag(){
    order by t.HONOR_TIME desc
@}

findByExpertId
===
*通过专家ID查询荣誉信息
select @pageTag(){
t.HONOR_ID,
t.EXPERT_ID,
t.HONOR_INFO,
to_char(t.HONOR_TIME,'yyyy-MM-dd') HONOR_TIME,
t.CREATE_BY,
t.CREATE_TIME,
t.MODIFY_BY,
t.MODIFY_TIME,
t.IS_USE
 @}
from TAL_C_EXPERT_HONOR t
where t.is_use = 'Y'
and t.EXPERT_ID = #expertId#
@pageIgnoreTag(){
    order by t.HONOR_TIME desc
@}