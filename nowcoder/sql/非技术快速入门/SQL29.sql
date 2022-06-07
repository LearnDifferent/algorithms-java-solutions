select
    count(distinct b.device_id, b.date) 
    / count(distinct a.device_id, a.date)
from
    question_practice_detail a
left join
    question_practice_detail b
on
    a.device_id = b.device_id
    and b.date - a.date = 1;
