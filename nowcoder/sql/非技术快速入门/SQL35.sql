select
    difficult_level,
    avg(if(result = 'right', 1, 0)) as correct_rate
from
    user_profile
join
    question_practice_detail using(device_id)
join
    question_detail using(question_id)
where
    university = '浙江大学'
group by
    difficult_level
order by
    correct_rate;
