create database postgres_test;

create or replace view statistics as
select to_char(date,'Mon') as month
,sum( price * count) as "total_purchased_amount"
,sum( count) as "total_book_count"
,count(distinct(co.id)) as "total_order_count"
from order_detail od inner join book b2
on od.book_id  = b2.id
inner join customer_order co
on od.order_id = co .id

group by month