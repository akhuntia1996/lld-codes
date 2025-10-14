Models -- 
Users
WeatherForecast - EventHandler
Weather

PhoneNotification - PHONE_TYPE event
EmailNotification - EMAIL_TYPE event

DB ---
User 
EventHandlers
UserEventHandler - id, user id, event Handler Id

API
POST /login
POST /signup
GET /events
POST /events
DELETE /event
Weather apis

Code ---
class WeatherForecast {
    List<EventListerns> eventListerns;

    subscribe(EventListerns){
        eventListerns.add(eventListern);
    }

    unsubscribe(eventListern) {
        eventListerns.remove(eventListern);
    }

    notify(event_type, Weather) {
        for(eventListern : eventListerns) 
            if(eventListern.getType == event_type)
                eventListern.update(Weather)
    }
}

class Weather {
    int temp, int preci;
    WeatherForecast EventHandler;
    setTemp() {
        this.temp = temp
        EventHandler.notify(this);
    }
    setPrecipitation {
        this.preci = preci;
        EventHandler.notify(this);
    }
}

interface EventListerns {
    update(Weather);
}
class PhoneNotification implements EventListerns {
    update(Weather);
}
class EmailNotification implements EventListerns {
    update(Weather);
}

enum event_type {
    PHONE_TYPE,
    EMAIL_TYPE
}