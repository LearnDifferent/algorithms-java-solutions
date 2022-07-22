select
    cust_name,
    sum(quantity * item_price) as total_price
from
    OrderItems
inner join
    Orders using(order_num)
inner join
    Customers using(cust_id)
group by
    cust_name
having
    total_price >= 1000
order by
    total_price;
