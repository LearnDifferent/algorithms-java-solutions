-- 方法 1：
select
    count(*) as total_pv,
    count(score) as complete_pv,
    count(
        -- 这里是统计 已完成的试卷数：
        -- `if(score is not null, exam_id, null)`
        -- 或者 `case when score is null then null else exam_id end`
        -- 的意思是，如果是 score 是 null，就设置值为 null，
        -- 这样的话，count 统计的时候就不会统计 null，也就是不会统计没完成的试卷 ID；
        -- 如果 score 不是 null 的时候，就统计 exam_id 的数量，也就是统计完成的试卷 ID。
        -- 最后去重后再使用 count 统计就可以了。
        distinct if(score is not null, exam_id, null)
    ) as complete_exam_cnt
from
    exam_record;

-- 方法 2：
select
  count(*) as total_pv,
  count(score) as complete_pv,
  count(
    -- count 本身是无法对 多列求行数，
    -- 加入 distinct 后，多列就是一个整体，就可以求行数。
    -- 如果 distinct 的整体的列中，有一个 null，
    -- count 在统计的时候就不会返回该列。
    distinct
    exam_id,
    -- 判断是否不为 null，如果不为 null 就为 true，也就是返回 1；
    -- 否则（or），也就是为 null 的时候，就返回 null
    score is not null or null
  ) as complete_exam_cnt
from
  exam_record;
