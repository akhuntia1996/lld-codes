import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledFuture;

import apple.laf.JRSUIConstants.Direction;

/**
 * Design an Elevator System for a commercial building.
Requirements:
Building has N floors
Multiple elevators

Users can:
Press UP/DOWN on floor
Select destination inside elevator

System should:
Assign elevator efficiently
Minimize waiting time
Handle concurrent requests

Elevators have:
Capacity limit
Current direction
Current floor

Entities --
ElevatorSystem -> Orcestrators
Scheduler -> Assign Elevator
Elevator
Floor
Button

Relationships --
ElevatorSystem -> Scheduler -> Elevators -> Door and Buttons
Floor -> Button

Flow -- 
User presses the button on the floor
Request will be generated and added to BlockingQueue
Scheduler will consumer the request and assign the elevator using Strategy design pattern
Once the elevator arrives the button light is off

As the user be in floor to press the button, And floor will be operate by ElevatorSystem -> producer
Consumer will be Scheduler

Class --
ElevatorSystem
- Scheduler
- BlockingQueue<Request> requests
* processRequest()

Scheduler
- List<Elevator> elevators
* processRequests(Queue<Request> requests) 

Elevator 
- id
- ElevatorState
- List<Button>
* moveUp(), moveDown()

Floor 
- id
- Button up, downn
* sendRequest(floorId, direction)

Button 
- id
- name
- boolean isPressed()
* setPressed(boolean ) -> void

ElevatorState -> Idle, Open, Close, OutOfService
Request -> request id, floor id, direction, elevatorId

*/

@Producer
class ElevatorSystem {
    BlockingQueue<Request> requests;
    List<Floor> floors;

    public void press(Long floorId, Direction direction) {
        if(direction == Direction.UP)
            floors.get(floorId).getUp(true);
        else
            floors.get(floorId).getDown(true);

        requests.put(new Request(floorId, direction));
    }
}

class Floor {
    Long id;
    Button up, down;

    public void clearButtom(Direction direction) {
        if(Direction.UP == direction)
            this.getUp().isPressed(false);
        else
            this.getDown().isPressed(false);
    }
}

@Consumer
class Scheduler implements Runnable{
    BlockingQueue<Request> requests;
    List<Elevator> elevators;

    Scheduler(BlockingQueue<Request> requests) {
        this.requests = requests;
    }

    @Override
    public void run() {
        while(!requests.isEmpty()) {
            try {
                Request req = requests.take();
                Elevator e = getBestElevator(req);
                e.addStop(e.getFloorId);
            } catch(InterruptedException ee) {}
        }
    }

    public Elevator getBesElevator(Request req) {
        // Strategy design pattern to use find elevator 
        return new Elevator();
    }

    public void arrive(int requestId) { // clearing the floor buttob
        requests.get(requestId).getFloorId().clearButton();
    }
}

class Elevator {
    Queue<Long> floorQueue = new ConcurrentLinkedQueue<>();

    public void addStop(Long floorId) {
        floorQueue.add(floorId);
    }

    public void arrive(Long floorId) { // Clearing the inside button
        openDoor();
        clearButtom();
    }
}

/*
Extend this above class to support internal request from the elevator
*/

class Elevator {
    //Queue<Long> floorQueue = new ConcurrentLinkedQueue<>();

    PriorityQueue<Long> floorQueue = new PriorityQueue<>();
    Button one, two....;

    public void onIntenalButtonPress(int floorId) {
        floorQueue.add(floorId);
    }

    public void addStop(Long floorId) {
        floorQueue.add(floorId);
    }

    public void arrive(Long floorId) {
        openDoor();
        clearButtom();
    }
}

/*
Concurrency

Two users press UP on same floor simultaneously.
How do you prevent duplicate assignment?

In this case the request will be added multiple times to the blocking queue.
*/

@Producer
class ElevatorSystem {
    BlockingQueue<Request> requests;
    List<Floor> floors;
    Set<String> set;

    public void press(Long floorId, Direction direction) {
        if(direction == Direction.UP)
            floors.get(floorId).getUp(true);
        else
            floors.get(floorId).getDown(true);

        if(set.add(floorId + "@#@" + direction))
            requests.put(new Request(floorId, direction));
    }
}

/*
 What happens when elevator gets stuck ? 
 - Make as OUT_OF_SERVICE
 - make sure your keep the processing reques in one queue
 - Reassin the elevators using scheduler
 - notify admin
*/

/**
 * 
 * Elevator Assigning Strategies --
 * First Come First Server
 * Shortest Peek time
 * 
 */
