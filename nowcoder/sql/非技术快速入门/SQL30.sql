select 
    substring_index(profile, ',', -1) as gender,
    count(device_id)
from
    user_submit
group by
    gender;
