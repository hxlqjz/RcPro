findVinRecordList
===
SELECT * FROM vin_code_record t where t.ly_id=#lyId#;

insertVinRecordLs
===
 insert into vin_code_record (ly_id,Transmission_model,vin_code,create_date) VALUES(#lyId#,#transmissionModel#,#vinCode#,#createDate#);