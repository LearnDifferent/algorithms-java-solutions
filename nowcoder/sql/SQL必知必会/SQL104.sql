select
  prod_name,
  count(order_num) as orders
from
  Products p
  left join OrderItems o on p.prod_id = o.prod_id
group by
  prod_name
order by
  prod_name;
