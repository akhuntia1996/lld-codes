
/*
    THis class used as a Facade design pattern
    This class's responsibily is to only serve the client request, 
        irrespective for all the calling of models
 */

public class RentCarsService {

    List<String, Store> stores;

    public RentCarsService(){
        // initialize stores
    }

    public List<Stores> fetchStore(String city){
        return stores.get(city);
    }

    public List<String> fetchVehicles(Store store){
        List<String> shortViewVehicle;
        List<Vehicle> vehicles = store.getVehicleInventory.getVehicles();
        for(Vehicle v:vehicles){
            shortViewVehicle.add(v.showShortViewVehicle());
        }

        return shortViewVehicle;
    }

    public String fetchVechileDetails(Vehicle vehicle){
        return vehicle.showLargeViewVehicle();
    }

    // CRUD on Vehicle / Store / Iventory ... Only By ADMIN

}