select
  uid,
  'activity1' as activity
from
  exam_record
where
  year(start_time) = 2021
  and year(submit_time) = 2021
group by
  uid
having -- 每次试卷得分都能到85分的人
  min(score) >= 85

union all

select
  distinct uid, -- 可能有多次达成，所以要去重（也是因为这里没用 group by）
  'activity2' as activity
from
  examination_info ei
  inner join exam_record er on ei.exam_id = er.exam_id
where
  year(start_time) = 2021
  and year(submit_time) = 2021
  and difficulty = 'hard'
  -- duration 表示考试时长，start_time 是开始作答时间, submit_time 是交卷时间
  -- duration 的单位是分钟，所以 *60 就是秒，再 *0.5 就是考试时长的一半
  -- timestampdiff 可以计算出时间差，这里第一个参数是 second，所以计算出来的是秒
  and timestampdiff(second, start_time, submit_time) <= duration * 60 * 0.5
  and score > 80
order by
  uid;
