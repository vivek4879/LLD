"Design an inventory management system that tracks products across multiple warehouses. The system needs to handle adding and removing inventory, transferring stock between locations, and alerting when inventory runs low."

What operations does the system support?
What can go wrong
What's in scope 
What might we extend later

Questions:
1. Will the warehousees be configured on startup or will we be adding and removing warehouses?
2. low inventory alerts are per product across all warehouses or per product per warehouse?
3. the alert shoudl be an email, call a webhook or just return something to the caller for now?

4.Should we reject operations that would take stock below zero?
5.What about concurrent access? if two processes are trying to modify the same resourse, do we need to take care of that?
6.What's out of scope? Are we managing product catalogs, handling orders, that kind of thing?"

Requirements:
1. Track inventory for products across multiple warehouses
2. Add stock to a specific warehouse (receiving shipments)
3. Remove stock from a specific warehouse (fulfilling orders)
4. Check availability: given a product and quantity, return which warehouses can fulfill it
5. Transfer stock between warehouses
6. Low-stock alerts
7. Reject operations that would result in negative inventory
8. System must be thread-safe to handle concurrent operations

Out of Scope:
- Product catalog management (products exist externally)
- Order processing / payment / serviceability
- Persistence


WarehouseManager
-List<WareHouse>


+addStock(Product, count)
+removeStock(Product, count)
+checkAvailability(product, quantity) -> warehouseId
+transferStock(Product, quantity, From, To)



Warehouse
-Id
-Map<Products,Integer>
+alert()

