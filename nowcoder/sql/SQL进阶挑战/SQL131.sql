select
  level,
  count(distinct u.uid) level_cnt
from
  user_info u
  inner join exam_record er on er.uid = u.uid
  inner join examination_info ei on ei.exam_id = er.exam_id
where
  tag = 'SQL'
  and score > 80
group by
  level
order by
  level_cnt desc,
  level desc;
