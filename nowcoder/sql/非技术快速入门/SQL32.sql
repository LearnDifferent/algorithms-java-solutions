select
    substring_index(substring_index(profile, ',', -2), ',', 1) as age,
    count(device_id)
from
    user_submit
group by
    age;
