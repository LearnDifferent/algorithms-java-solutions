select 
    case
        when age >= 25 then '25岁及以上'
        else '25岁以下'
    end as age_cut,
    count(distinct device_id) as number
from user_profile
group by age_cut;
