-- 方法 1：
-- 使用 if 来做 result 的判断。
-- left join 产生临时表的时候，直接使用 on 来连接 month(date) = 8，如果符合 month(date) = 8，就会显示 q 表的相关信息，如何不符合就会以 null 填充。此时的临时表包含用户所有的情况信息，包括没有参与 8 月答题的用户（没答题就用 null 填充）。
select
    u.device_id,
    university,
    count(question_id) as question_cnt,
    sum(if(result = 'right', 1, 0)) as right_question_cnt
from
    user_profile u
left join
    question_practice_detail q
    on u.device_id = q.device_id and month(date) = 8 
where
    university = '复旦大学'
group by
    u.device_id, university;

-- 方法 2
-- 判断 result 的时候，使用 case end
-- 这里 left join 的时候，不使用 on 判断 month(date) = 8，而是在 where 的时候判断 month(date) = 8 and month(date) is null。这里要加上 month(date) is null 的判断，是为了把没有在 8 月答题的用户也包括进来。
select
  u.device_id,
  university,
  count(question_id) question_cnt,
  sum(
    case
      when result = 'right' then 1
      else 0
    end
  ) right_question_cnt
from
  user_profile u
  left join question_practice_detail q on u.device_id = q.device_id
where
  university = '复旦大学'
  and (
    month(date) = 8
    or month(date) is null
  )
group by
  u.device_id,
  university
