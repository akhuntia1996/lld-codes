package ParkingLot;

/*
 * Basic Needs for LLD Parking Lot
 */
enum VehicleType{
    MotarCycle,
    LightWeight,
    HeavyWeight
}

enum ParkingLotTicketStatus{
    Active,
    Paid,
    Unpaid
}

/*
 * Address Information
 */

class Address{
    String block;
    String street;
    String city;
    String state;
    String country;
    int pin;
}

/*
 * User related Information - Customer, Admin, Attendees
 */
class User {
    int id;
    String name;
    String username;
    String password;

    resetPassword();
}

class Customer extends User {
    Vehicle vehicle;
    VehicleSlot assignedVehicleSlot;
    
    vehicleCRUD();
    assignVehicleSlot(VehicleSlot vehicleSlot); 
}

class Admin extends User {
    cutomerCRUD();
    adminCRUD();
    parkingLotCRUD();
    prarkingLotFloorsCRUD();
    attendeesCRUD();
}

class Attendees extends User{
    processTicket(int ticketNumer);
}

/*
 * Mimimum Information for Vechile
 */
class Vehicle{
    int vehicleNumber;
    VehicleType vehicleType;
}

class VehicleSlot {
    
    int id;
    int parkingLotId;
    int parkingLotFloorId;
    
    boolean isFull;
    double costPerHour;

    VehicleType vehicleType;
}

/*
 * Ticket Related Information
 */

class Ticket{
    
    int id;
    int parkingLotFloorId;
    int parkingLotId;

    Date startTime;
    Date endTime;

    double cost;
    VehicleType vehicleType;

    ParkingLotTicketStatus parkingLotTicketStatus;    

    calculateCost();
}

/*
 * DisplayBoard
 */
class DisplayBoard {
    String name;
    Map<VehicleType, Integer> totalAvaliableSlots;
    Map<VehicleType, Integer> slotsLeft;
    Map<VehicleType, Integer> avaliableSlots;

    updateAvaliableSlot(List<VehicleType, Integer> avaliableSlots);
    display();
}


/*
 * 
 * Gates of Floors
 */

class Gate {
    int id;
    String description;
}
class Entrance extends Gate{
    int ticketNumber = processTicket(Customer customer);
}

class Exit extends Gate{
    processPayment(Customer customer, int ticketNumber);
}

/*
 * Parking Lot related Information
 */
class ParkingLotFloors {
    int id;
    String name;
    String description;

    Map<VehicleType, Integer> totalAvaliableSlots;
    Map<VehicleType, Integer> avaliableSlots;
    List<Entrance> entrances;
    List<Exit> exits;

    List<Attendees> attendees;

    boolean isFull;
    DisplayBoard displayBorad;

    crudOnAttendees(attendees);
    boolean checkVacency(VehicleType vehicleType);
    void updateDisplayBoard(List<VehicleType, Integer> avaliableSlots);
    
    void assignSpot(Vehicle vehicle) throws Exception{
        if(checkVacency(vehicle.getVehicleType())){
            avaliableSlots.put(VehicleType, avaliableSlots.get(vehicleType) - 1);
            updateDisplayBoard(avaliableSlots);
            user.assignedVehicleSlot();
        } else
            throw new ParkingLotApplicationException("Slot Unavaliable");
    }

}

class ParkingLotTemp{
    
    int id;
    String name;
    Address address;

    List<ParkingLotFloors> ParkingLotFloors;
    List<Entrance> entrances;
    List<Exit> exits;

    checkVacency(int parkingLotFloorId, VehicleType vehicleType);
    floorsCRUD(ParkingLotFloors parkingLotFloor);
}