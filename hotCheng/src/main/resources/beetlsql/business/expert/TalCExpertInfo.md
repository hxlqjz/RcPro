select
===

select @pageTag(){
t.EXPERT_ID,
t.USER_CODE,
t.USER_NAME,
t.GENDER,
t.DEPT_CODE,
t.POLITICS,
t.BIRTHDAY,
t.ID_CARD,
t.MAIL,
t.MEMO,
t.STATUS,
t.TEL,
t.HIGHEST_EDU,
t.MARRIAGE,
t.NATIVE_PLACE,
t.PERFATIONAL_DUTY,
t.EXPERT_GRADE,
t.EXPERT_SCORE,
t.CREATE_BY,
t.CREATE_TIME,
t.MODIFY_BY,
t.MODIFY_TIME,
t.IS_USE,
a.DEPT_NAME
 @}
from TAL_C_EXPERT_INFO t,
     HR_C_DEPT a
where t.IS_USE = 'Y'
  and t.DEPT_CODE = a.DEPT_CODE
@if(!isEmpty(userName)){
and t.USER_NAME like #userName#
@}
@if(!isEmpty(expertGrade)){
and t.EXPERT_GRADE = #expertGrade#
@}
@if(!isEmpty(deptCode)){
and t.DEPT_CODE = #deptCode#
@}
@pageIgnoreTag(){
    order by t.EXPERT_SCORE desc
@}

findByExpertGrade
===

select @pageTag(){
t.EXPERT_ID,
t.USER_CODE,
t.USER_NAME,
t.GENDER,
t.DEPT_CODE,
t.POLITICS,
t.BIRTHDAY,
t.ID_CARD,
t.MAIL,
t.MEMO,
t.STATUS,
t.TEL,
t.HIGHEST_EDU,
t.MARRIAGE,
t.NATIVE_PLACE,
t.PERFATIONAL_DUTY,
(select c2.dict_name 
      from COM_C_DICT_CAT c1, 
           COM_C_DICT c2 
      where c1.cat_code = c2.cat_code  
            and c1.cat_code = 'expertGrade' and c2.dict_code = t.EXPERT_GRADE ) EXPERT_GRADE,
t.EXPERT_SCORE,
t.CREATE_BY,
t.CREATE_TIME,
t.MODIFY_BY,
t.MODIFY_TIME,
t.IS_USE,
a.DEPT_NAME,
b.FILE_PATH
 @}
from TAL_C_EXPERT_INFO t,
     HR_C_DEPT a,
     COM_C_ATT b
where t.IS_USE = 'Y'
  and t.DEPT_CODE = a.DEPT_CODE
  and t.EXPERT_ID = b.TBL_KEY(+)
  and b.TBL_NAME(+) = #tblName#
@if(!isEmpty(userName)){
and t.USER_NAME like #userName#
@}
@if(!isEmpty(expertGrade)){
and t.EXPERT_GRADE = #expertGrade#
@}
@pageIgnoreTag(){
    order by t.EXPERT_SCORE desc
@}	     

getExpertByDeptCode
===
select @pageTag(){
t.EXPERT_ID ,
u.user_code ,
t.USER_NAME ,
de.dept_code ,
de.dept_name ,
org.dept_code org_code,
org.dept_name org_name,
t.expert_grade,
t.memo
 @}
from TAL_C_EXPERT_INFO t,SYS_C_USER_DEPT d,HR_C_DEPT de,SYS_C_USER u,HR_C_DEPT org
where t.is_use = 'Y'
and de.dept_code = d.dept_code
and u.user_code = t.user_code
and u.user_code = d.user_code
and d.org_code = org.dept_code
@if(!isEmpty(userName)){
and t.USER_NAME like #userName#
@}
@if(notInclude){
   and  t.EXPERT_ID not in (select i.EXPERT_ID  from TR_J_LECTUER_INFO i where i.IS_USE = 'Y')
@}
@if(!isEmpty(orgCode)){
       START WITH  de.dept_code = #orgCode#
       CONNECT BY PRIOR de.pdept_code = de.dept_code
@}



zyCExpertInfoAndDeptId
===
select @pageTag(){
t.EXPERT_ID,
t.USER_CODE,
t.USER_NAME,
t.GENDER,
t.DEPT_CODE,
t.POLITICS,
to_char(t.BIRTHDAY,'yyyy-MM-dd') BIRTHDAY,
t. ID_CARD,
t.MAIL,
t.MEMO,
t.STATUS,
t.TEL,
t.HIGHEST_EDU,
t.MARRIAGE,
t.NATIVE_PLACE,
t.PERFATIONAL_DUTY,
t.EXPERT_GRADE,
t.EXPERT_SCORE,
t.CREATE_BY,
t.CREATE_TIME,
t.MODIFY_BY,
t.MODIFY_TIME,
t.IS_USE,
a.DEPT_ID
 @}
from TAL_C_EXPERT_INFO t,
     HR_C_DEPT a
where t.is_use = 'Y'
  and t.dept_code = a.dept_code
  and t.expert_id = #expertId#

  
findZyCExpertInfoByUserCode
===
select @pageTag(){
t.EXPERT_ID,
t.USER_CODE,
t.USER_NAME,
(select c2.dict_name 
      from COM_C_DICT_CAT c1, 
           COM_C_DICT c2 
      where c1.cat_code = c2.cat_code  
            and c1.cat_code = 'gender' and c2.dict_code = t.gender ) GENDER,
t.DEPT_CODE,
t.POLITICS,
to_char(t.BIRTHDAY,'yyyy-MM-dd') BIRTHDAY,
t.ID_CARD,
t.MAIL,
t.MEMO,
t.STATUS,
t.TEL,
t.HIGHEST_EDU,
t.MARRIAGE,
t.NATIVE_PLACE,
t.PERFATIONAL_DUTY,
(select c2.dict_name 
      from COM_C_DICT_CAT c1, 
           COM_C_DICT c2 
      where c1.cat_code = c2.cat_code  
            and c1.cat_code = 'expertGrade' and c2.dict_code = t.EXPERT_GRADE ) EXPERT_GRADE,
t.EXPERT_SCORE,
t.CREATE_BY,
t.CREATE_TIME,
t.MODIFY_BY,
t.MODIFY_TIME,
t.IS_USE,
a.DEPT_NAME
 @}
from TAL_C_EXPERT_INFO t,
     HR_C_DEPT a
where t.is_use = 'Y'
  and t.dept_code = a.dept_code
  and t.user_code = #userCode#