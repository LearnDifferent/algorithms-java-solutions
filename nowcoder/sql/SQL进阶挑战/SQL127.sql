select
  -- date_format() 用于截取日期
  date_format(submit_time, '%Y%m') as submit_month,
  -- any_value() 函数用来制止 ONLY_FULL_GROUP_BY 值被拒绝。
  -- 所以需要在非 group by 的列上加 any_value()
  any_value(count(question_id)) as month_q_cnt,
  any_value(
    round(
        -- last_day() 返回一个月的最后一天的日期，再包上 day()，就能计算出一个月的天数
        count(question_id) / day(last_day(submit_time))
        , 3)
  ) as avg_day_q_cnt
from
  practice_record
where
  date_format(submit_time, '%Y') = '2021'
group by
  submit_month
union all
select
  '2021汇总' as submit_month,
  count(question_id) as month_q_cnt,
  round(count(id) / 31, 3) as avg_day_q_cnt
from
  practice_record
where
  date_format(submit_time, '%Y') = '2021'
order by
  submit_month;
