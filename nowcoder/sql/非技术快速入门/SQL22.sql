select
    university,
    round(
        count(question_id) / count(distinct u.device_id)
    , 4) as avg_answer_cnt
from
    user_profile u
join
    question_practice_detail q using(device_id)
group by
    university
order by
    university;
