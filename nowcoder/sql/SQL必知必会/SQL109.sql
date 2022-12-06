SELECT
  cust_name,
  cust_contact,
  cust_email
FROM
  Customers
WHERE
  cust_state = 'MI'
UNION
SELECT
  cust_name,
  cust_contact,
  cust_email
FROM
  Customers
WHERE
  cust_state = 'IL'
ORDER BY
  cust_name;
-- 使用 union 的时候，只能有一个 order by
