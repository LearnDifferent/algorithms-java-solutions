select
    order_num,
    sum(item_price * quantity) as total_price
from OrderItems
group by order_num
having total_price >= 1000
order by order_num;
