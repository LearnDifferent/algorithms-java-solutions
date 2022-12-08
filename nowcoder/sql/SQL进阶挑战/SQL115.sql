delete from
  exam_record
where
  timestampdiff(minute, start_time, submit_time) < 5
  and score < 60;
