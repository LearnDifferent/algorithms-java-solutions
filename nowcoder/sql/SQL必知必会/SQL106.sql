select
  prod_id,
  quantity
from
  OrderItems
where
  quantity = 100
union all
select
  prod_id,
  quantity
from
  OrderItems
where
  prod_id like 'BNBG%';
