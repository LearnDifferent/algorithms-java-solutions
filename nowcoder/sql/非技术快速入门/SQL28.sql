select
    day(date) as day,
    count(question_id) as question_cnt
from question_practice_detail
where year(date) = 2021 and month(date) = 8
group by day;
-- 使用了 count 这样的聚合函数后，没有使用聚合函数的字段要放在 group by 后面
