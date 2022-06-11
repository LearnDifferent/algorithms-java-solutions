select
    u.device_id,
    university,
    count(question_id) as question_cnt,
    sum(if(result = 'right', 1, 0)) as right_question_cnt
from
    user_profile u
left join
    question_practice_detail q
    on u.device_id = q.device_id and month(date) = 8 
where
    university = '复旦大学'
group by
    u.device_id, university;
