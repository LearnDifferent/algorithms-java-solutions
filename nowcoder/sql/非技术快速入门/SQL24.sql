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
where
    university = '山东大学'
group by
    university, difficult_level;
-- 可以不用 group by university，因为这里都是同一个大学。但是一般情况下，非聚合函数的字段都要使用 group by 比较好
