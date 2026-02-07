/*
Amazon Locker System

Requirement
Delivery guys can come and put the package in the locker using a pass key
Customer can use same pass key to get the package from the box

Question 
Will the pass keys for the delivery person and the customer ? - yes
The system should auto open the box that is empty or empty boxes will be already open ?
All the boxes will be same size or it will be different ?
Will the return part will be same ? like customer will keep in the box and the delivery guy will take the parcel ?
What will happen when all the boxes are full  ? will the package return back or it will delivered to the home of the customer ?
How long the package last ? 

Entities
User, DeliveryBoy (Not required)
Locker -> Box
Package (Only size)

class Locker
	- Box[]
	* deposite(string) -> string (OTP) | error
	* openForUser(string OTP) -> boolean
	* openExpiredBox() -> boolean

class Box
	- id
	- size (Enum - SMALL, LARGE)
	* getSize() 
	* getId()
	* open() / close() - Out of scope


*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Box;

class Locker {
	
	Box[] boxes;
	Map<String, Long> accessOtp; // Otp, BoxId

	Locker(Box[] boxes) {
		this.boxes = boxes;
		this.accessOtp = new HashMap<>();
	}

	String deposite(String size) {
		/*
		- Delivery boy will request for box size
		- Box open and delivery boy keeps the package 
		- Generate OTP and send to user
		- Box close
		Edge case - if all the boxes are full
		*/
		AtomicInteger counter = new AtomicInteger(0);
		synchronized(this){
			for(Box b : boxes) {
				if(b.getSize().equals(size) && !b.isOccupied()){
					counter.incrementAndGet();
					String otp = generateOTP(b.getId());
					this.accessOtp.put(otp, b.getId());
					b.open();
					b.setOccupied(true);
					return otp;
				}
			}
		}

		if(counter.get() == 0) {
			System.out.println("No box available of size " + size);
		}

		return null;
	}

	boolean pickup(String otp) {
		if(!isOTPValid(otp)) {
			System.out.println("Opt Is expired or invalid");
			return false;
		}
		Long boxId = this.accessOtp.get(otp);
		boxId.open();
		boxId.setOccupied(false);
		this.accessOtp.remove(otp);
		boxId.close();

		return true;
	}	

	boolean pickupAfterExpiry() {
		for(Box b : boxes) {
			if(b.isOccupied() && this.accessOtp.get(b.getId()).isExpired()) {
				b.open();
				b.setOccupied(false);
			}
		}
	}
}


/*
Extensibility - Can we keep the small package in the larger box when the same size boxes are full ??
*/

String deposite(String size) {
	/*
	- Delivery boy will request for box size
	- Box open and delivery boy keeps the package 
	- Generate OTP and send to user
	- Box close
	Edge case - if all the boxes are full
	*/

	List<String> sizes = Arrays.asList("SMALL", "MEDIUM", "LARGE");
	int requestedSizeIndex = sizes.indexOf(size);
	AtomicInteger counter = new AtomicInteger(0);
	synchronized(this){
		for(int i=requestedSizeIndex;i<sizes.size();i++){
			for(Box b : boxes) {
				if(b.getSize().equals(sizes.get(i)) && !b.isOccupied()){
					counter.incrementAndGet();
					String otp = generateOTP(b.getId());
					this.accessOtp.put(otp, b.getId());
					b.open();
					b.setOccupied(true);
					return otp;
				}
			}
		}
	}
}

/**
 * How to handle broken boxes
 * Prev we have isOccupied() that return boolean, now we need to have more than 2 states, isBroken, so we go Enum
 */

Enum BoxState {
	EMPTY, BROKEN, FULL
}

/**
 * How to make sure that the delivery boy actually kept the box
 * 2 phase commit
 * 1st phase will open the box 
 * 2nd phase the delivery boy has to confirm that the box is kept
 * deposite() -> reserve() + confirmDeposite()
 */

Enum BoxState {
	EMPTY, BROKEN, FULL, RESERVED
}

boolean reserve(String size) {
	
	/**
	 * When the box is empty and not occupied -> Just reserve it and get the reservation id
	 */
	
	AtomicInteger counter = new AtomicInteger(0);
		synchronized(this){
			for(Box b : boxes) {
				if(b.getSize().equals(size) && !b.isOccupied()){
					counter.incrementAndGet();
					int reserverId = b.reserve();
					reserveMap.put(reserverId, b.getId());
					b.open();
					b.setOccupied(LockerStatus.RESERVED);
					break;
				}
			}
		}

		if(counter.get() == 0) {
			System.out.println("No box available of size " + size);
			return null;
		}

		return null;
}

String confirmDeposite(Long reserveId) {

	/**
	 * One the delivery boy places the package, this method will be called 
	 * We will get reservation id for the box and generate token
	 */
	int boxId = reserveMap.get(reserveId);
	Box b = boxes[boxId];
	if(b.getOccupied() != LockerStatus.RESERVED) {
		System.out.println("Locker Not Reserved");
		return null;
	}

	String otp = generateOTP(b.getId());
	this.accessOtp.put(otp, b.getId());
	b.open();
	b.setOccupied(LockerStatus.FULL);
	return otp;
}