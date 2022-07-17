select
    cust_name,
    o.order_num,
    sum(quantity * item_price) as OrderTotal
from
    Customers c
join
    Orders o using(cust_id)
join
    OrderItems oi using(order_num)
group by
    o.order_num, cust_name
order by
    cust_name, o.order_num;
