select
  min(score) min_score_over_avg
from
  exam_record r
  inner join examination_info i on i.exam_id = r.exam_id
where
  tag = 'SQL'
  and score >= (
    select avg(score)
    from exam_record r
      inner join examination_info i on i.exam_id = r.exam_id
    where tag = 'SQL'
  );
