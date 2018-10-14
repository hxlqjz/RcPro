findMySnapList
===
select t.snap_id,
       t.snap_title,
       t.snap_content,
       t.type_code,
       t.type_name,
       t.create_time,
       t.is_use,
       t.create_by,
       t.send_man  
       from my_snap t
       where 1=1
	   order by t.create_time desc