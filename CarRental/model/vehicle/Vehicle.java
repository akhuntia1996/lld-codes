public abstract class Vehicle {
    
    private int id;
    private int vehicleNumber;
    private String model;
    private String companyName;
    private VechileType vechileType;
    private double pricePerHour;
    private VechileStatus vehicleStatus;

    public Vehicle(){}

    public void bookVehicle(){
        this.vehicleStatus = VechileStatus.BOOKED;
    }

    public void unBookVehicle(){
        this.vehicleStatus = VechileStatus.ACTIVE;
    }

    // views ...
    // We can use Template Design Pattern here
    public void showShortViewVehicle(){
        System.out.println(this.model + ", " + this.vechileType + ", " + this.pricePerHour);
    }

    public void showLargeViewVehicle(){
        System.out.println(this.model + ", " + this.vechileType + ", " + this.pricePerHour
            + ", " + this.vehicleStatus + ", " + this.companyName);
    }

    
    // Getter and setters
}