package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class RefMain {

    public static class Car {
        public String name;
        private String price;
        public String engType;
        public int engPower;
        public int maxSpeed;

        public Car() {
            this.name = name;
            engType = "DOHC 2.4L";
            engPower = 137;
            maxSpeed = 190;
            price = "Не доступно!";
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public String getEngType() {
            return engType;
        }

        public int getEngPower() {
            return engPower;
        }

        public int getMaxSpeed() {
            return maxSpeed;
        }

        @Override
        public String toString() {
            return "Car {name = " + name + '}';
        }
    }

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        Car machinka = new Car();

        Field[] fields = machinka.getClass().getFields();
        int tmp = fields[fields.length-1].getInt(machinka);
        fields[fields.length-1].setInt(machinka, tmp+100);

        System.out.println(machinka);
        System.out.println(machinka.getMaxSpeed());
//        Class<?> car = Class.forName("Car");
//        Constructor<?>[] constructor = car.getConstructors();
//        Object gaz = constructor[0].newInstance("ГАЗ-311055");
//        System.out.println(gaz);

//        Field[] fields = gaz.getClass().getFields();
//        int tmp = fields[fields.length-1].getInt(gaz);
//        fields[fields.length-1].setInt(gaz, tmp+100);
    }
}