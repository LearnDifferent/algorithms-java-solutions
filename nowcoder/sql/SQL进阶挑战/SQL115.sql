delete from
  exam_record
where
  timestampdiff(minute, start_time, submit_time) < 5
  and score < 60;
-- 注意，timestampdiff 是：开始时间, 提交时间
