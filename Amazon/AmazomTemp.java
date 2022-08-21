package Amazon;

/*
 * CONFIRM WITH THE INTERVIEWER TO DESGIN IT IN CUSTOMER POINT OF VIEW OR DELIVERY BOY 
 * POINT OF VIEW
 * 
 * Functional and Non Functional Requirements
 * -----------------------------------------------
 * Add and Sell products
 * Search By name and category
 * User login
 * CRD on Shopping card
 * Check and buy from shopping cart
 * rate and add review on product
 * Add a shipping address
 * Cancel an order
 * Notifcation when we order status is changes
 * Payment option
 * Track Order
 * 
 * Actors
 * ------------
 * Customers
 * Admin
 * System
 * 
 * Usecases Diagram
 * ----------------
 * User login and SignUp
 * Add/ Update Products
 * Search
 * Shopping Cart
 * Payment 
 * Notification Service
 * 
 * Class Diagram
 * --------------------------
 * User
 * Customer
 * Admin
 * 
 * Product
 * ProductCategory
 * ProductReview
 * ShoppingCart
 * 
 * Address
 * Order
 * OrderHistory
 * PaymentDetails
 * Notification
 */

 /*
  * User Classes
  */
enum Gender{
    Male, 
    Female,
    Other
}

class User{
    int id;
    String name;
    String username;
    String password;
    int age;
    Gender gender;
}

class Customer extends User{
    
    List<Address> address;
    List<PaymentDetails> paymentDetails;
    List<OrderHistory> orderHistory;

    updateProfile();
    updatePaymentOptions();
    order(int productId, int addressid);
    getOrderDetails(int orderId);

}

class Admin{
    
    String password2;

    crudCustomers();
    crudAdmin();
    crudProducts();
}

/*
 * Other classes
 */

class Address{
    int id;
    String block;
    String street;
    String city;
    String state;
    String country;
    int pin;
    String landmark;
}

class Order {
    int id;
    int productid;
    Customer customer;
    Address address;
    PaymentDetails paymentDetails;
    List<Address> trackAddresses;

    getTrackingAddresses();
    getPaymentDetails();
    getOrderDetails();
    + getterMethods ...
}

enum ModeOfPayment{
    Card, UPI, CashOnDelivery, NetBanking
}

enum PaymentStatus{
    SUCCESS, FAILDED, ABORTED
}

class PaymentDetails{
    ModeOfPayment modeOfPayment;
    double amount;
    PaymentStatus paymentStatus;

    getDetails();
}

enum DeliveryStatus{
    YetToStart, Active, Delivered, Returned, ActiveReturn
}

class OrderHistory {
    int productId;
    Address address;
    DeliveryStatus deliveryStatus;
    PaymentDetails paymentDetails;

    getDetails();
}

enum NotificationStatus {
    SENT, Delivered, SentButNotDelivered
}

class Notifcation {
    int userid;
    Date startTime;
    NotificationStatus notificationStatus;
    String message;

    prepareMessage();
}

/*
 * product related classes
 */
public class AmazomTemp {
    
}
