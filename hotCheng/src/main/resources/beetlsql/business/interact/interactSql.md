findNewsList
===
select t.news_id,
       t.the_column,
       date_format(t.create_date, '%Y-%m-%d') create_date,
       t.group_news_url,
       t.theme,
       (select e.is_to_praise
          from PORTAL_TO_PRAISE e
         where e.news_id = t.news_id
         @if(!isEmpty(toPraiseBy)){
         and e.to_praise_by = #toPraiseBy#
         @}          
          ) isToPraise,
       (select count(*)
          from PORTAL_TO_PRAISE e
         where e.news_id = t.news_id
           and e.is_to_praise = '1') goodNum
  from PP_J_PUBLISTH_NEWS t
 where t.is_important_news = 'Y'
   and t.is_use = 'Y'
 order by t.create_date desc
 
findTheNewsDetail
===

select t.news_id,
       t.the_column,
       date_format(t.create_date, '%Y-%m-%d %H:%i:%s') createDate,
       t.publish_by,
       t.auditor_by,
       t.publish_content publish_Content,
       t.publish_status,
       t.publish_dept,
       str_to_date(date_format(t.modify_date, '%Y-%m-%d'), '%Y-%m-%d') modifyDate,
       t.file_url,
       t.image_url,
       t.is_use,
       t.lastmodify_by,
       t.is_pic_news,
       t.pic_url,
       t.theme,
       t.is_check,
       t.approve_status,
       t.enterprise_code,
       t.group_news_url,
       t.information_sources
  from pp_j_publisth_news t
 where t.is_use = 'Y'
   and t.news_id = #newsId#;
   
findAllComments
===
select t.comments_id,
       t.create_by,
       date_format(t.create_time, '%Y-%m-%d %H:%i:%s') create_time,
       t.comments_text,
       e.user_name,
       (select count(*)
          from PORTAL_TO_PRAISE_FOR_COMMENTS pt
         where pt.comments_id = t.comments_id) commentsNum,
       (select count(*)
          from PORTAL_TO_PRAISE_FOR_COMMENTS cc
         where cc.create_by = #empId#
           and cc.comments_id = t.comments_id) isToPraise
  from PORTAL_COMMENTS t, SYS_C_USER e
 where t.news_id = #newsId#
   and t.create_by = e.user_id
 order by t.create_time desc;
 
findUserHeadpic
===
select t.HEAD_PORTRAIT, t.HEAD_PORTRAIT_ID
  from PORTAL_W_HEAD_PORTRAIT t
 where user_id = #userId#;
 
 getCommentsList
===
select t.*, e.hrname, e2.hrname hrname2
  from PORTAL_COMMENTS_FOR_USER t, Portal_w_Emp e, Portal_w_Emp e2
 where t.comments_id = #commentsId#
   and t.create_by = e.emp_id
   and t.reply_to = e2.emp_id
 order by t.create_date
 

