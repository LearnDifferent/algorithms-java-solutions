update
  exam_record
set
  submit_time = '2099-01-01 00:00:00',
  score = 0
where
  start_time < '2021-09-01'
  and submit_time is null;
