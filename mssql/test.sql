create database linux_test
GO
/*
use master
go
drop database linux_test
go
*/
use linux_test
GO
/*
drop table tbl
GO
*/
create table tbl (
    id bigint IDENTITY(1, 1) not NULL,
    valint bigint,
    val varchar(50) primary key
)
GO

;with
  a1 as (select 1 as C union all select 1),
  a2 as (select 1 as C from a1 as t, a1),
  a3 as (select 1 as C from a2 as t, a2),
  a4 as (select 1 as C from a3 as t, a3),
  a5 as (select 1 as C from a4 as t, a4),
  a6 as (select 1 as C from a5 as t, a5),
  a7 as (select 1 as C from a6 as t, a6),
  r as (
    select row_number() over(order by C) as n from a7)
insert into tbl (valint, val)
select n, 'Number #' + cast(n as varchar(20))
from r
where n <= 10000000
GO

select top(10) valint, val
from tbl
order by val desc
GO

select count(*)
from tbl
GO