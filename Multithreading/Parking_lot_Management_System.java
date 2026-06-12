class ParkingLot {
    private int availableSlots = 3;

    public synchronized void parkCar(String carName) {
        if (availableSlots > 0) {
            System.out.println(carName + " Parked");

            availableSlots--;

            System.out.println("Available Slots: " + availableSlots);
        } else {
            System.out.println("No Parking Available for " + carName);
        }
    }
}

class CarThread extends Thread {
    private ParkingLot parkingLot;
    private String carName;

    public CarThread(ParkingLot parkingLot, String carName) {
        this.parkingLot = parkingLot;
        this.carName = carName;
    }

    @Override
    public void run() {
        parkingLot.parkCar(carName);
    }
}

public class Parking_lot_Management_System {
    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot();

        CarThread car1 = new CarThread(parkingLot, "Car1");
        CarThread car2 = new CarThread(parkingLot, "Car2");
        CarThread car3 = new CarThread(parkingLot, "Car3");
        CarThread car4 = new CarThread(parkingLot, "Car4");
        CarThread car5 = new CarThread(parkingLot, "Car5");
        CarThread car6 = new CarThread(parkingLot, "Car6");

        car1.start();
        car2.start();
        car3.start();
        car4.start();
        car5.start();
        car6.start();
    }
}
