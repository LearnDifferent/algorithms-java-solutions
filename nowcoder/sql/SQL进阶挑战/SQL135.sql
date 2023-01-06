-- 【思路】
-- 【比较简单的部分】
-- 每个6/7级用户：
select
  uid
from
  user_info
where
  level = 6
  or level = 7;

-- 2021年试卷作答活跃天数（注：提交了，就代表活跃）：
select
  uid,
  count(distinct date(start_time))
from
  exam_record
where
  year(start_time) = 2021
group by
  uid;

-- 2021年答题（练习题）活跃天数（注：完成了，就代表活跃）：
select
  uid,
  count(distinct date(submit_time))
from
  practice_record
where
  year(submit_time) = 2021
group by
  uid;

-- 【比较简单的部分 - 整合】
select
  ui.uid,
  -- 在这里作年份筛选，是因为后续有一个列的统计，是不分年份的
  count(
    distinct if(year(er.start_time) = 2021, er.start_time, null)
  ) act_days_2021_exam,
  count(
    distinct if(year(pr.submit_time) = 2021, pr.submit_time, null)
  ) act_days_2021_question
from
  user_info ui
  left join exam_record er on ui.uid = er.uid
  left join practice_record pr on ui.uid = pr.uid
where
  level = 6
  or level = 7
group by
  ui.uid;

-- 【比较难的部分】
-- 总活跃月份数 和 2021年活跃天数：
select
  uid,
  -- 统计活跃月份数（无年份筛选）
  count(distinct date_format(act_time, '%Y%m')) act_month_total,
  -- 统计2021年活跃天数
  count(
    distinct if(
      year(act_time) = 2021,
      date_format(act_time, '%Y%m%d'),
      null
    )
  ) act_days_2021
from
  (
    select
      uid,
      start_time as act_time
    from
      exam_record
    union all
    select
      uid,
      submit_time as act_time
    from
      practice_record
  ) act
group by
  uid;

-- 【答题】
select
  ui.uid,
  -- 总活跃月份数（没有 2021 年的限制）
  count(distinct date_format(act_time, '%Y%m')) act_month_total,
  -- 2021年活跃天数
  count(
    distinct if(
      year(act_time) = 2021,
      date_format(act_time, '%Y%m%d'),
      null
    )
  ) act_days_2021,
  -- 2021年试卷作答活跃天数
  count(
    distinct if(
      tag = 'exam'
      and year(act_time) = 2021,
      date(act_time),
      null
    )
  ) act_days_2021_exam,
  -- 2021年答题（练习题）活跃天数
  count(
    distinct if(
      tag = 'practice'
      and year(act_time) = 2021,
      date(act_time),
      null
    )
  ) act_days_2021_question
from
  user_info ui
  left join (
    select
      uid,
      start_time as act_time,
      'exam' as tag -- 对当前结果手动添加一个用于识别的标签
    from
      exam_record
    union all
    select
      uid,
      submit_time as act_time,
      'practice' as tag -- 对当前结果手动添加一个用于识别的标签
    from
      practice_record
  ) act on ui.uid = act.uid
where
  level = 6
  or level = 7
group by
  ui.uid
order by
  act_month_total desc,
  act_days_2021 desc;
