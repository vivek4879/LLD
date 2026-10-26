
public class WarehouseManager{
    //what does this entitiy need to remember - state
    //what operations does this entity need to support - methods
    // WarehouseManager
    // -List<WareHouse>


    // +addStock(Product, count)
    // +removeStock(Product, count)
    // +checkAvailability(product, quantity) -> warehouseId
    // +transferStock(Product, quantity, From, To)

    //we make it immutabkle if dont need adding adn removing warehouses funcuoits later.
    //we using a map because we want to map Warehouse name string to the warehouse obkject so that we can look up on O(1);
    //names as string as it helps give better contextual descriptive names than just numbers.
    private final Map<String, WareHouse> warehouses;

    //constructor takes in a list of warehouses and creates a new warehouse instance for each one and puts in the map.
    WarhouseManager(List<Warehouse> warehouses){
        this.warehouses = new HashMap<>();
        for(wareHouse warehouse:warehouses){
            String newName = generateName();
            this.warehouses.put(newName, wareHouse);
        }
    }

    //assuming capacity is unlimited so addstock always succeeds. if the capacity is limited then we can add a capacity and current capacity field to warehouse and check on that.
    //by capacity we also need to check if the capacity limit is per product or per warehouse total.
    //here if wareHouseId is wrong we throw, not return false. Throwing is for unexpected cliengt or server behaviour like client put wrong warehouseID or server db conneciotn lost.
    //for expected business behaviour like removing stock might not work when stock low, we reutrn false, not throw.
    public void addStock(String productId, int count, String warehouseId){
        //1.get warehouse object from warehouse map, throw if id wrong.
        //2.add count to the productId.
        if(! warehouses.containsKey(wareHouseId))throw;
        Warehouse warehouse = this.warehouses.get(warehouseId);
        wareHouse.addQuantity(productId, count);


    }
    //looks up the warehouse and removes the count if that much is available. this operation can fail if the quantity is less than required so we return a boolean.
    public boolean removeStock(String productId, int count, String warehouseId){
        if(! warehouses.containsKey(wareHouseId))throw;
        Warehouse warehouse = this.warehouses.get(warehouseId);
        return warehouse.removeQuantity(productID, count);

    }

    public List<String> getWareHousesIdWithAvailability(String productId, int quantity){
        List<String> eligibleWarehouses = new ArrayList<>();
        for(Warehouse warehouse:this.warehouses){
            if(warehouse.checkAvailability(productId,quantity)){
                eligibleWarehouses.add(warehouse);
            }
        }
        return eligibleWarehouses;

    }

    //1. check if from has enough quantity, then in one atomic transaction remove from fromWarehouse nad add to toWareHouse.
    public boolean transferStock(String productId, int quantity, String fromWareHouseId, String toWarehouseId){
        //check if ids valid and quantity positive or throw
        //acquire locks in consistent order.
        //lockA = max(both warehouseId);
        //lockB = min(both warehouseId);
        //we acquire locks in this sequence so that there is not deadlock.
        //check if from has required quantity else return false
        ///removestock from from
        //add stock to towarehouse.
        //rmeove lock
    }

    public void setLowStockAlert(String warehouseId, String productId, int threshold,Listener listener){
        //check warehouse valid or throw
        //check prductid valid or throw

        Warehouse warehouse = this.warehouses.get(warehouseId);
        warehouse.addAlert(productId, threshold, listener);
    }
}

// Warehouse
// -Id
// -Map<Products,Integer>
// +alert()

public class Warehouse{
    //id we need to identify a warehouse but we will also use it to send alerts in alertConfig because we need to tell which warehouse is triggering which alertConfig.

    String Id;
    Map<String, Integer> products;
    //We will have a map of product to alertConfigs the product has subscribed to. We are giving the option to subscribing to different configs like when stock is mid, low or maybe critically low after a threshold.

    Map<String,List<AlertConfig>> alerts;

    Warehouse(){

    }
    private void addQuantity(String productId, int count){
        // put product if does not exist or just add quantity.Cannot fail.

    }

    private boolean removeQuantity(String productId, int count){
        // should throw if wrong productId, return false if not enough quantity.

    }

    private getStock(String productID){

    }

    private boolean checkAvailability(String productId, int count){
        //throw on wrong productID, false if doesnt exist.
    }
    //will set alerts for the product.
    private void setLowStockAlert(String productID, int threshold, Listener listener){

    }

    //will run after each removeQuantity to get all the alerts on this product and check their filters. we check prev to current because 
    //we might want to fire when moved from 15 to below 10 but not again for 10 to 7 becase next threshold is at 5.
    private List<alertConfig> getAlertsToFire(int productId, int prevQuantity, int newQUantity){

    }
}

class AlertConfig{

    int threshold;
    Listener listener;

    AlertConfig(int threshold, Listener listerner){
        this.threshold = threshold;
        this.listener = listener;
    }


}


class emailListener implements Listener{
    public void onEvent(String warehouseId, String productID, int quantity){

    }

}

//Listener is an interface so that we can plug in difernet implementations as needed for each product.
interface Listener{
    public void onEvent(String warehouseId, String productID, int quantity);
}