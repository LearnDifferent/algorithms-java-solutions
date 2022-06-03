select 
    device_id, gender, age, gpa 
from user_profile
where university = '山东大学'

union all

select 
    device_id, gender, age, gpa 
from user_profile
where gender = 'male';
