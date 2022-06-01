select
    university,
    difficult_level,
    round(
        count(qpd.question_id) / count(distinct u.device_id)
    , 4) as avg_answer_cnt
from
    user_profile u
join
    question_practice_detail qpd using(device_id)
join
    question_detail qd using(question_id)
group by
    university, difficult_level;
