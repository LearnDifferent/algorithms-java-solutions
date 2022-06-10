select
    a.device_id, a.university, a.gpa
from user_profile as a
join
    (
        select university, min(gpa) as gpa
        from user_profile
        group by university
    ) as b
	on a.university = b.university and a.gpa = b.gpa
order by a.university;
