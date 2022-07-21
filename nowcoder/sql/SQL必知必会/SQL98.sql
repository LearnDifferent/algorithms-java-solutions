select
    cust_id, order_date
from
    Orders
join
    OrderItems using(order_num)
where
    prod_id = "BR01"
order by
    order_date;
