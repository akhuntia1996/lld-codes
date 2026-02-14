/*
Parking Lot
------------
Questions --
- Kind of vehicle
- Multi parking lot over city - each parking lot will have multi floor parking - each floor 
slots of parking - 2/4 wheeler
- Payment at exit - 2 wheeler = 10 per hour - 4 wheeler = 20 per hour
- Admin to CRUD on all 

Entities --
ParkingLot
Floor
Slot
Payment

Class --
ParkingLot
- Floor[]
- Map<WHEELER, Price> priceMap 
- Payment
* doEntry(VEHICLE_TYPE)
* doExit(VEHICLE_TYPE)
* checkAvalibility(VEHICLE_TYPE) -> Long 
* doPayment(VEHICLE_TYPE, int hours) -> boolean
* CRUD on Floors / slots

Floor
- Slot[]
* CRUD on Slots

Slot
- VEHICLE_TYPE
- isFUll
* isOccupied() -> boolean
* setOccupied() -> void

*/

import java.time.Duration;
import java.time.LocalDateTime;

enum VehicleType {
    TWO_WHEELER, FOUR_WHEELER
}

class ParkingLot {
    List<Floor> floors;
    Map<VehicleType, Long> priceMap;
    Payment payment;

    List<Booking> bookings;

    ParkingLot() {
        priceMap.put(VEHICLE_TYPE.TWO_WHEELER, 10);
        priceMap.put(VEHICLE_TYPE.FOUR_WHEELER, 20);
    }

    boolean checkAvalibility(VehicleType vehicleType) {
        /*
        Loop through floors
            if floor not full
                check for all slots for that vehicle type
            else
                continue;
        */

        for(Floor floor : floors) {
            for(Slot slot : floor.getSlots())
                if(slot.VEHICLE_TYPE == vehicleType && !slot.isOccupied())
                    return true;
        }

        return false;
    }

    boolean doPayment(VehicleType vehicleType, int hours) {
        int totalPay = priceMap.get(vehicleType) * hours;
        payment = new Payment(CARD_PAYMENT, totalPay);
        boolean isSuccessPayment = payment.doPayment();
        if(!isSuccessPayment)
            return false;
        return true;
    }

    public void doEntry(VehicleType vehicleType) {
        if(checkAvalibility(vehicleType)) {
            Booking booking = new Booking(vehicleType, startTime, floorId, slotId);
            floors.get(floorId).get(slotId).setOccupied(true);
        }
    }

    public void doExit(Long bookingId) {
        Booking booking = bookings.get(bookingId);
        
        Duration duration = Duration.between(new LocalDateTime(startTime), new LocalDateTime().now());
        
        Slot slot = floors.get(booking.getFloorId()).get(slotId);
        slot.setOccupied(false);

        doPayment(booking.getVehicleType(), duration.toHours());
    }
}

class Booking {
    Long id;
    VehicleType vehicleType;
    LocalDateTime startTime;
    Long floorId;
    Long slotId;
}

class Floor {
    List<Slot> slots;
}

class Slot {
    VEHICLE_TYPE vehicleType;
    boolean isFull;

    boolean isOccupied(){
        return isFull;
    }
    void setOccupied(boolean isFull) {
        this.isFull = isFUll;
    }
}

/**
 * Locking 
 * doEntry - read/write lock or reenterant locks
 * doExit - CHECK lock type
 * And atomic counters
 * 
 * Unique booking id generation
 * - Snowflake ID
 * - UUID
 * - DB sequence
 * 
 * Partial Failure - Payment succeeded but gate didn’t open. Now what?
 * - Save payment state and Retry mechanism (policies)
 * - Dead letter queues = Keep the state in one queue and then debug later
 * - Manually
 * - Saga design Pattern
 * - idempotent APIs
 * 
 * Tomorrow we add EV charging / VIP parking. What changes?
 * - Add to vehicleType and priceMap
 * 
 * How do you debug production issues?
 * - Metrics (Prometheus), Logs, Tracing
 * 
 * Peak hour: 10k cars entering per minute. Bottleneck?
 * - Async billing
 * - queue entry
 * - shard by floor
 * - Read replicas, horizontal scaling, load balanceing, api gateway, rate limit
 * 
 * What if ParkingLot runs on multiple servers?
 * - Distribute lock like redis / zookeeper
 * - eventual consistancy
 */
