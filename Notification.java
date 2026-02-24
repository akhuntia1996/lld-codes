/*
You are asked to design a Notification System for a large-scale application like Amazon or Flipkart.

The system should send notifications for events like:
Order placed
Order shipped
Order delivered
Payment failed
Promotional campaigns

Notifications can be sent via:
📧 Email
📱 SMS
🔔 Push Notification (Mobile App)
💬 WhatsApp

Entities --
Order 
OrderState
PaymentState
Observer
    EmailObserver
    SMSObserver
    WhatsappObserver

Class -- 
Client -> Order

## When the user switches on the new observer we need to add to all order, which is difficult tast ##
## Separate the logic for Observer ##

NotificationPublisher
- List<Observer>
* subscribe(Observer)
* unsubscibe(Observer)
* updateSubscriber(Message)
* updateBulkMessage(List<Message>)
* scheduleMessage(Message, Timestamp)

Order
- OrderState
- PaymentState
* updateSubscriber(Message)
* updateBulkMessage(List<Message>)
* scheduleMessage(Message, Timestamp)
* updateOrderState(OrderState) -> true | false - send notification to all observers
* updatePaymentState(PaymentState)

Observer 
* updateSubscriber(Message)
* updateBulkMessage(List<Message>)
* scheduleMessage(Message, Timestamp)

EmailObserver 
WhatappsObserver
SMSObserver

Message
- id
- message
- username
- timestamp
* setMessage
* getMessage

PaymentStatusMessage
- Message = "Payment Status : " + PaymentStatus
OrderStatusMessage
- Message = "Order Status : " + OrderStatus

*/

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Observer;

import javax.management.Notification;

enum OrderState {
    DELIVERED, INITIATE, PAYMENT_PENDING
}

enum PaymentState {
    INITIATE, PENIND, PAID
}

class Message {
    int id;
    String msg;
    String link;
    String username;
}

class Order{
    OrderState orderState;
    PaymentState paymentState;

    NotificationPublisher notificationPublisher;
    
    public boolean updateOrderState(OrderState orderState, String usernmae) {
        this.orderState = orderState;
        Message message = new Message("Order State Changed to " + orderState.getName(), "http://amazon.com?orderid=101", usernmae);
        notificationPublisher.updateSubscriber(message);

        List<Message> messages;
        notificationPublisher.updateSubscriber(messages);

        return true;
    }
}

class NotificationPublisher {
    List<Observer> observers;

    public void updateSubscribers(Message message) {
        for(Observer observer : observers) {
            observer.update(message);
        }
    }

    public void updateSubscribers(List<Message> messages) {
        for(Observer observer : observers) {
            observer.update(messages);
        }
    }

    public void subscribe(Observer observer) {
        this.observers.add(observers);
    }

    public void unsubscibe(Observer observer) {
        this.remove(observer);
    }
}

interface Observer {

    public final KafkaTemplate<String, Message> kafkaTemplate;

    public void update(Message message);
    public void update(List<Message> messages);
}

class EmailObserver implements Observer {
    public void update(Message message) {
        emailService.send(message);
    }

    public void update(List<Message> messages) {
        for(Message m : messages) {
            kafkaTemplate.send("email-nofication-topic", message.getUserId(), m); // UserId = PartitionKey
        }
    }

    @KafkaListener(topics = "email-notification-topic", groupId = "email-consumer-group")
    public void routeEmail(Message message) { 
        try {
            emailService.send(message);
        } catch(Exception ee) {
            handleRetry(message);
        }
    }

    // RETRY MECHANISM
    private void handleRetry(Message message) {
        if(message.getRetryCount() < 3) {
            message.setRetryCount(message.getRetryCount() - 1);
            kafkaTemplate.send("email-notification-topic", message.getUserId(), message);
        } else {
            // Move the message to Dead letter queue
            kafkaTemplate.send("email-notification-dlq", message.getUserId(), message);
        }
    }
}

/*
Avoid sending the message twice --
Store the message in DB with a PK
Check if PK is present in DB, before sending

How would you prevent hot partition issue?
UserId + something else as KEY like Location

What if one campaign is 100M users?
How will you pause/resume campaign?
How do you guarantee ordering across channels?
Kakfa, Rate Limiting, Write ahead log

How do you handle GDPR delete during processing? -> if the user is deactivated, how to remove its messages from kakfa
Stop publish
Move to dead letter queue
Avoid processing in consumer

SMS provider down	            Circuit breaker + Retry topic
Campaign too large	            Batch publishing
User opts out mid-campaign	    Check preference before send
Duplicate publish	            Idempotency key
Kafka crash	                    Replication factor = 3
*/

/**
 * Scheduling Notification
 */

class ScheduledMessage {
    Message message;
    LocalDateTime timestamp;
}

// the messages in DB with timestamp
@Scheduled(fixedDelay = 5000)  // every 5 sec
public void updateScheduledNotification() {
    List<ScheduledMessage> scheduleMessage ; // get data from DB
    scheduleMessage = repository.findByTimeStamp(LocalDateTime.now(), "PENDING"); // with status

    for(ScheduledMessage message : scheduleMessages) {
        kafkaTemplate.send("email-notification-topic", message.getUserId(), message);
        // OR
        this.update(message);
    }
}
