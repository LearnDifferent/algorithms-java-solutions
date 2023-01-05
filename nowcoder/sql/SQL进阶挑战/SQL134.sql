select
  a.uid,
  exam_cnt,
  -- 因为可能有用户在 2021 年有完成试卷，但是没有完成练习题，所以要判断一下
  if (question_cnt is null, 0, question_cnt) as question_cnt
from
  (
    -- 找到高难度SQL试卷得分平均值大于80并且是7级的红名大佬
    select
      ui.uid
    from
      user_info ui
      inner join exam_record er on er.uid = ui.uid
      inner join examination_info ei on ei.exam_id = er.exam_id
    where
      tag = 'SQL'
      and difficulty = 'hard'
      and `level` = 7
    group by
      uid
    having
      avg(score) > 80
  ) a
  -- 只保留 2021 年有试卷完成记录的用户，所以使用 inner join
  inner join ( -- 统计 2021 年试卷总完成次数
    select
      uid,
      count(exam_id) as exam_cnt
    from
      exam_record
    where
      year(submit_time) = 2021
    group by
      uid
  ) b on a.uid = b.uid
  -- 这里使用 left join，因为 a 和 b 表的数据是必要的
  left join ( -- 统计他们的2021年题目总练习次数
    select
      uid,
      count(question_id) as question_cnt
    from
      practice_record
    where
      year(submit_time) = 2021
    group by
      uid
  ) c on a.uid = c.uid
order by
  exam_cnt,
  question_cnt desc;
