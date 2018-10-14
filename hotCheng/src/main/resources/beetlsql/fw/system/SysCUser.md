select
===
select 
t.USER_ID,
t.USER_CODE,
t.USER_NAME,
t.PSWD,
t.USER_ACNT,
t.THM,
t.TEL,
t.MOBILE,
t.MAIL,
t.IS_LONGTERM,
t.VALID_TIME,
t.INVALID_TIME,
t.IS_USE,
t.USER_TYP,
t.EMP_CODE,
t.RMK,
t.ORD_BY,
t.CREATE_BY,
t.CREATE_TIME,
t.MODIFY_BY,
t.MODIFY_TIME,
t.FLAG,
t.USER_PROFESSION,
t.DEPT_NAME,
t.DEPT_CODE
from SYS_C_USER t
where t.is_use = 'Y'

@if(!isEmpty(deptCode)){
and  t.DEPT_CODE = #deptCode#
@}

@if(!isEmpty(userName)){
and t.USER_NAME like #userName#
@}

@pageIgnoreTag(){
    order by t.CREATE_TIME desc
@}

unselect
===

select @pageTag(){
d.USER_ID,
d.USER_CODE,
d.USER_NAME,
d.DEPT_NAME
 @}
 
from SYS_C_USER d
where d.IS_USE='Y'

@if(!isEmpty(userName)){
and (d.USER_NAME like #userName# OR d.USER_CODE like #userName#)
@}

@if(!isEmpty(deptName)){
and d.DEPT_NAME = #deptName#
@}

and d.USER_NAME not in(
select case when EMP_NAME is null then 'null' else EMP_NAME end
from INS_C_STATION_USER where IS_USE='Y' and STATION_ID = #stationId#
)

@pageIgnoreTag(){
 order by d.USER_ID desc
@}

