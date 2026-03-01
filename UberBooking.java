/*
Design Uber
-------------
Functional --
Flow
    User provider src and dest
    Distance and time are calculated
    List of vehicles are shown with amount
    Choose one vehicle 
    Start searching for nearby drivers
    Send a request to driver
    Show the driver details and location, once driver accepts it
    Trip started once otp is entered
    Trip end and payment is done

User
- Book a ride
- Cancel ride
- Ride Status

Driver
- Go Online / offline
- accept the ride
- start the ride after OTP
- complete the ride

System 
- Find the nearest driver online
- calculate fare

Entities --
Uber - Client Class
User
Driver
Payment
Ride
PricingStrategy
DriverFindingStrategy

Class --
User
- id
- details
* bookRide(src, dest) -> rideId
* cancelRide(rideId) -> boolean
* getRideStatus(rideId) -> RideStatus

RideStatus
DRIVER_NOT_REACHED_PICKUP, TRIP_STARTED, REACHED_PAYMENT_PENDING, TRIP_COMPLETE

Location
- long, lat

Driver
- id
- details
- currentLocation

Ride
- id
- userid
- driverid
- VechileType
- amount
- PaymentStrategy

PaymentStrategy
    CardPayment
    CashPayment
    WalletPayment

DriverFindingStrategy
    FindNearestDriver

*/

import java.time.LocalDateTime;

class Uber {
    public static void main(String[] args) {
        User u1;
        Ride ride = u1.bookRide(src, dest);

        // Cancel
        u1.cancel(ride.getId());

        RideStatus rideStatus = u1.getRideStatus(ride.getId());
    }
}

enum RideStatus {
    DRIVER_NOT_REACHED_PICKUP, TRIP_STARTED, REACHED_PAYMENT_PENDING, TRIP_COMPLETE
}

enum VehicleType {
    BIKE, AUTO, CAR, CAR_XL
}

class Trip {
    Location src, dst;
    int distance;
    int timeTaken;
    List<Location> route;
}

class Ride {
    int id;
    int userId;
    int driverId;
    Location driverLocation;
    List<Location> route;
    int timeLeft;
    RideStatus rideStatus;
    int amount;
    PaymentStrategy paymentStrategy;

    @KafkaListener(topics = "request-ride-events")
    public void requestRideListener(RequestRideEvent event) {
        for(Driver driver : event.getListOfNearbyDrivers()) {
            if(!driver.isOccpied()) 
                driver.notifyDriver(event.getTrip(), userId);
        }
    }

    @KafkaListener(topics = "driver-accept-response")
    public Ride driverAccptResponse(RideAcceptanceResponse rideAcceptanceResponse) {
        Driver driver = rideAcceptanceResponse.getDriver();
        rideAcceptanceResponse.getUser().showDriverInfo(driver);
    }
}

class Payment {
    @KafkaListener(topics = "driver-complete-trip-response")
    public Ride driverAccptResponse(RideCompleteResponse rideCompleteResponse) {
        if(rideCompleteResponse.getRide().getAmount() != 0)
            rideCompleteResponse.getPaymentStrategy().pay(rideCompleteResponse.getAmount);
    }
}

class PaymentInput {
    int upiaddress;
    int cardno;
    int cvv;
    LocalDateTime expiry;
}
abstract class PaymentStrategy {
    public abstract void pay(PaymentInput input);
}
class UPIPaymentStrategy extends PaymentStrategy {
    public void pay(PaymentInput input) {}
}

class Driver {
    int id;
    Location currentLocation;

    KafkaTemplate<Long, RideAcceptanceResponse> kafkaTemplateRide;

    public void notifyDriver(Trip trip, Long userId) {
        // show trip details
        int choose = input.isRideAcceptable(trip.getRide().getRideId());
        if(choose == 1)
            acceptRide(trip, userId);
        else
            return;
    }

    public void acceptRide(Trip trip, Long userId) {
        RideAcceptanceResponse response = new RideAcceptanceResponse(this, trip, userId);
        kafkaTemplateRide.send("driver-accept-response", response.getId(), response);
    }

    public void driverRideComplete(Ride ride) {
        RideCompleteResponse response = new RideCompleteResponse(this, ride, userId);
        kafkaTemplateRide.send("driver-complete-trip-response", response.getId(), response);
    }

}

class User {
    int id;

    KafkaTemplate<Long, RequestRideEvent> kafkaRequestRideProducer;

    public void bookRide(Location src, Location dest) {
        Trip trip = mapService.getTripDetails(src, dest);

        // display and choose vehicle type
        Map<VehicleType, Double> vehiclePriceMap = displayVehicleChoice(trip);
        VehicleType choosenVehicleType = input.getVehicleType();

        trip.setPrice(vehiclePriceMap.get(choosenVehicleType));

        // Sending request to nearby drivers
        List<Driver> nearByDrivers = driverService.getNearbyDrivers(trip, choosenVehicleType);
        RequestRideEvent event = new RequestRideEvent(trip, nearByDrivers);

        kafkaRequestRideProducer.send("request-ride-events", event.getEventId(), event);
    }

    public void showDriverInfo(Driver d) {
        // 
    }

    public void displayVehicleChoice(Trip trip) {
        Map<VehicleType, Double> vehiclePriceMap = new HashMap();
        for(VehicleType vehicleType : VehicleType.values()) {
            vehiclePriceMap.put(vehicleType, pricingService(trip, vehicleType));
        }
        return vehiclePriceMap;
    }

    public void cancelRide(Ride ride) {
        ride.setDriverId(null);
        ride.setRideStatus(RideStatus.CANCELLED);
    }
}

/*  

FINDING RIDER STRATEGIES --
- Nearby drivers
- top rate drivers 1st
- Car pooling
- Radius based - first find 2KM, then 4 KM, then 6KM etc....


FINDOUT --
Concurrency Handling
How will you avoid 2 riders getting same driver? 
- in a txn, check the driver status to SEARCHING

How will you handle race condition when multiple drivers are near?
- Reenterant Lock

Edge Cases -----
Driver cancels after accept - maintain atomicity

Payment failure
Retry 3 attempts, Block user or add outstanding amount to next trip

Rider cancels mid-trip 
Calculate the midtrip balance, check with user if the payment status

Advanced Follow-up (Important for 7 YOE)
How would you scale matching service? 
- Kakfa (Stateless)
- parition by city
- Geo spartial Index

Would you use in-memory store? 
- For online driver locations, trips, user location
- Not for history

How would you design surge pricing?
- demand and supply implance 
- depend on time and place and weather / traffic - store this info
- surge = 1 + (Demand / supply)
- price = price * surcharge multipler

How will you handle 1M concurrent ride requests?
rate limiting
Load balancing
parition 
kafka
circuit breaker - show no cabs found

----
*/
