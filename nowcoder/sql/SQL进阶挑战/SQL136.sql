-- 窗口函数的执行顺序是在 where 和 group by 之后：
-- 1. 所以要对 排序后 的结果再筛选（也就是下面的 where ranking <= 3），只能用子查询（嵌套查询）。
-- 2. partition by 是窗口函数的一部分，所以是在 where 和 group by 之后执行。也就是对运算结果后的表，再进行分组计算的。
select
  tid,
  uid,
  ranking
from
(
    select
      tag as tid,
      uid,
      row_number() over(
        partition by tag
        order by max(score) desc, min(score) desc, uid desc
      ) as ranking
    from
      examination_info i
      inner join exam_record r on i.exam_id = r.exam_id
    -- 需要根据 tag 和 uid 分组后，需要使用聚合函数计算最大的分数和最小分数
    group by
      tag, uid
  ) a
where
  ranking <= 3;
