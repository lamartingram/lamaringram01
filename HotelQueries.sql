use Hotel;

select BillID, GrandTotal
from Bill
order by GrandTotal desc
limit 3