Requirements 
-----------------
Build a Sports car

Models --
Car
AutomaticCar
ManualCar

Builder (I)
AutomaticCarBuilder
ManualCarBuilder

Director
Application

Code 
--------
class Car {
    String number
    String color
    String company
}
class AutomaticCar extends Car {
    String batteryCompany;
}
class ManualCar extends Car {
    int gears;
}

interface Builder {
    reset();
    setSeats();
    setEngines();
    setGPS();
}
class ManualCarBuilder implements Builder {
    private Car car;
    public ManualCarBuilder(Car car) {
        this.car = car;
    }
    ...
}
class AutomaticCarBuilderCarBuilder implements Builder {}

class Director {
    makeSUV(Builder builder) {
        builder.reset();
        builder.setSeat(7);
        builder.setEngines(1);

        return builder.getCar();
    }
    makeSportCar(Builder builder) {
        builder.reset();
        builder.setSeat(3);
        builder.setEngines(2);

        return builder.getCar();
    }
}

class Application {
    main() {
        Director director = new Director();
        Car sportsCar = director.makeSportCar(new ManualCarBuilder());
        Car manualCar = director.makeSportCar(new AutomaticCarBuilder());
    }
}