select
  er.exam_id,
  -- 计算答题人数
  count(distinct ui.uid) as uv,
  -- 计算平均分（保留 1 为小数）
  round(avg(score), 1) as avg_score
from
  user_info ui
  inner join exam_record er on ui.uid = er.uid
  inner join examination_info ei on er.exam_id = ei.exam_id
where
  tag = 'SQL'
    -- 用户等级大于 5
    and level > 5
    -- 发布时间（年月日） = 提交时间（年月日）
    and date_format(release_time, '%Y%m%d') = date_format(start_time, '%Y%m%d')
group by
  er.exam_id
order by
  uv desc,
  avg_score asc;
