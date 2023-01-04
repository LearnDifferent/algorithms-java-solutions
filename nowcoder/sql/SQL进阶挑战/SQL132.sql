select * from
  (
    select
      -- 试卷 ID
      exam_id as tid,
      -- 作答人数
      count(distinct uid) as uv,
      -- 作答次数
      count(*) pv
    from
      exam_record
    group by
      exam_id
    order by
      uv desc,
      pv desc
  ) a -- 因为 union 的 order by 只能 写在最后，所以使用括号 () 来排好序后再 union

union all

select * from
  (
    select
      question_id as tid,
      count(distinct uid) as uv,
      count(*) pv
    from
      practice_record
    group by
      question_id
    order by
      uv desc,
      pv desc
  ) b
