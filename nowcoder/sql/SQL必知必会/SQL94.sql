select
    o.cust_id,
    sum(quantity * item_price) as total_ordered
from
    OrderItems oi
join
    Orders o using(order_num)
group by
    o.cust_id
order by 
	total_ordered desc;
