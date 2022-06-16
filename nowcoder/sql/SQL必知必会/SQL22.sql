select
    cust_id, 
    cust_name, 
    upper(
        concat(
            substring(cust_contact,1, 2), 
            substring(cust_city, 1, 3)
        )
    ) as user_login
from
    Customers;
