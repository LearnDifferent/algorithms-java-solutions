delete from
  exam_record
where
  timestampdiff(minute, start_time, submit_time) < 5
  or submit_time is null
order by
  start_time
limit
  3;
-- delete from 的时候可以使用 where ... order by ... limit 来筛选需要删除的结果
