select
    tag,
    difficulty,
    round(
        -- 计算去掉一个最大值和一个最小值后的总分数
        (sum(score) - max(score) - min(score))
        -- 除以：计算去掉最大和最小值后的数据量
        / (count(score) - 2)
    , 1) as clip_avg_score
from
    examination_info i
inner join
    exam_record r on i.exam_id = r.exam_id
where
    tag = 'SQL' and difficulty = 'hard';
