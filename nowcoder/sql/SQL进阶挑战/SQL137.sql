select
  a.exam_id,
  duration,
  release_time
from
  (
    select
      -- 这里使用 sum() 是因为题目是要按照 group by exam_id 来分组，
      -- 获取每个 exam_id 第2快和第2慢的数据
      sum(
        -- 这里是通过 sum 来求【第2慢的时间-第2快的时间】
        case
          -- slow_rank = 2 表示【第2慢】，used_time 表示 【第2慢的时间】
          when slow_rank = 2 then used_time
          -- fast_rank = 2 表示【第2快】，-used_time 表示 【-第2快的时间】
          when fast_rank = 2 then - used_time
          else 0
        end
      ) as target_time,
      exam_id
    from
      (
        select
          exam_id,
          -- 获取考试使用的时间
          timestampdiff(minute, start_time, submit_time) as used_time,
          -- 按照 exam_id 分组。从快到慢的，按照 asc 排序；从慢到快的，按照 desc 排序
          row_number() over(
            partition by exam_id
            order by
              timestampdiff(minute, start_time, submit_time)
          ) as fast_rank,
          row_number() over(
            partition by exam_id
            order by
              timestampdiff(minute, start_time, submit_time) desc
          ) as slow_rank
        from
          exam_record
        -- 排除无效的数据
        where
          submit_time is not null
      ) rank_tbl
    group by
      exam_id
  ) a
  inner join examination_info b on a.exam_id = b.exam_id
where
  target_time >= 0.5 * duration
order by
  a.exam_id desc
