getList
===
select t.id,t.id_code,t.province,t.marks,t.query_power,t.role_power,t.store_name
from rp_store  t  where 1=1 
@if(!isEmpty(idCode)){
	and t.id_code =#idCode#
@}
@if(!isEmpty(storeName)){
	and t.store_name like  #'%'+storeName+'%'#
@}
;

getInfo
===
select t.id,t.id_code,t.province,t.marks,t.query_power,t.role_power,t.store_name
from rp_store  t  where  id=#id#;

getInfoByIdCode
===
select t.id,t.id_code,t.province,t.marks,t.query_power,t.role_power,t.store_name
from rp_store  t  where  id_code=#idCode#;

delete
===
delete from rp_store where id= #id# ;

insert
===
insert into rp_store(id_code,store_name,province,query_power,role_power,marks)
VALUES (#idCode#,#storeName#,#province#,#queryPower#,#rolePower#,#marks#);

update
===
UPDATE rp_store
set
store_name=#storeName#,
province=#province#,
query_power=#queryPower#,
role_power=#rolePower#,
marks=#marks#
where id=#id#;