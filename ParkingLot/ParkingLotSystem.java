import java.util.*;

public class ParkingLotSystem {
    
    // Enum for different vehicle types
    enum VehicleType {
        CAR, TRUCK, VAN, MOTORCYCLE
    }

    // Enum for different parking spot types
    enum SpotType {
        COMPACT, LARGE, DISABLED, MOTORCYCLE
    }

    // Enum for Payment Methods
    enum PaymentMethod {
        CASH, CARD
    }

    // Represents the ParkingSpot
    static class ParkingSpot {
        SpotType type;
        boolean isAvailable;


        public ParkingSpot(SpotType type) {
            this.type = type;
            this.isAvailable = true;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void park() {
            isAvailable = false;
        }

        public void vacate() {
            isAvailable = true;
        }
    }

    // Represents a Vehicle
    static class Vehicle {
        VehicleType type;
        double hourlyRate;

        public Vehicle(VehicleType type) {
            this.type = type;
            switch (type) {
                case CAR:
                    this.hourlyRate = 2.0;
                    break;
                case TRUCK:
                    this.hourlyRate = 4.0;
                    break;
                case VAN:
                    this.hourlyRate = 3.0;
                    break;
                case MOTORCYCLE:
                    this.hourlyRate = 1.0;
                    break;
            }
        }

        public double getHourlyRate() {
            return hourlyRate;
        }
    }

    // Payment Strategy interface
    interface Payment {
        boolean pay(double amount);
    }

    // Cash Payment Strategy
    static class CashPayment implements Payment {
        public boolean pay(double amount) {
            System.out.println("Paid " + amount + " in cash.");
            return true;
        }
    }

    // Credit Card Payment Strategy
    static class CardPayment implements Payment {
        public boolean pay(double amount) {
            System.out.println("Paid " + amount + " via credit card.");
            return true;
        }
    }

    // Represents a Ticket issued when a vehicle enters the parking lot
    static class ParkingTicket {
        Vehicle vehicle;
        long entryTime;


        public ParkingTicket(Vehicle vehicle) {
            this.vehicle = vehicle;
            this.entryTime = System.currentTimeMillis();
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public long getEntryTime() {
            return entryTime;
        }

        public double calculateFee() {
            long duration = System.currentTimeMillis() - entryTime;
            double hours = duration / (1000 * 60 * 60);
            return vehicle.getHourlyRate() * hours;
        }
    }

    //TODO:populate this class properly
    public class ParkingSpotMapper{
        Map<Vehicle, ParkingSpot> parkedVahicleMap;
        public ParkingSpotMapper(){
            parkedVahicleMap = new HashMap<Vehicle, ParkingSpot>();
        }
        //TODO: parking mapper CURD operation - thread  safe
    }

    // Represents the entire parking lot
    //TODO: create dao layer for parking-lot/spot access
    static class ParkingLot {
        int capacity;
        int currentVehicles;
        List<List<ParkingSpot>> floors;

        public ParkingLot( int numFloors, int spotsPerFloor) {
            this.capacity = numFloors*spotsPerFloor;
            this.currentVehicles = 0;
            this.floors = new ArrayList<>();
            for (int i = 0; i < numFloors; i++) {
                List<ParkingSpot> floorSpots = new ArrayList<>();
                for (int j = 0; j < spotsPerFloor; j++) {
                    floorSpots.add(new ParkingSpot(SpotType.COMPACT)); // Default to COMPACT
                }
                this.floors.add(floorSpots);
            }
        }

        //TODO: next parking spot find login should be written separately(decoupled)
        //TODO: parking spot access should be thread safe
        //TODO: should return parking ticket on success
        public boolean parkVehicle(Vehicle vehicle) {
            if (currentVehicles >= capacity) {
                System.out.println("Parking Lot Full");
                return false;
            }
            for (List<ParkingSpot> floor : floors) {
                for (ParkingSpot spot : floor) {
                    if (spot.isAvailable()) {
                        spot.park();
                        currentVehicles++;
                        System.out.println(vehicle.type + " parked.");
                        return true;
                    }
                }
            }
            System.out.println("No parking available.");
            return false;
        }
        //TODO: simply spot should be identified from ticket , verified with vehicle number (ticket vehicle number and parking spot vehicle number)
        public boolean exitVehicle(ParkingTicket ticket, Payment paymentMethod) {
            double fee = ticket.calculateFee();
            if (paymentMethod.pay(fee)) {
                for (List<ParkingSpot> floor : floors) {
                    for (ParkingSpot spot : floor) {
                        if (!spot.isAvailable()) {
                            spot.vacate();
                            currentVehicles--;
                            System.out.println("Vehicle exited. Fee: " + fee);
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        //TODO: make it simple by creating data structure to save parking spot in some hash map/set

        public List<ParkingSpot> getAvailableSpots() {
            List<ParkingSpot> availableSpots = new ArrayList<>();
            for (List<ParkingSpot> floor : floors) {
                for (ParkingSpot spot : floor) {
                    if (spot.isAvailable()) {
                        availableSpots.add(spot);
                    }
                }
            }
            return availableSpots;
        }

        public boolean addFloor(int numSpots) {
            List<ParkingSpot> newFloor = new ArrayList<>();
            for (int i = 0; i < numSpots; i++) {
                newFloor.add(new ParkingSpot(SpotType.COMPACT)); // Default to COMPACT
            }
            this.floors.add(newFloor);
            return true;
        }
    }

    // Display Board showing available spots
    static class DisplayBoard {
        public void displayAvailableSpots(ParkingLot parkingLot) {
            List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots();
            if (availableSpots.isEmpty()) {
                System.out.println("Parking is full.");
            } else {
                System.out.println("Available Parking Spots:");
                for (ParkingSpot spot : availableSpots) {
                    System.out.println(spot.type);
                }
            }
        }
    }

    // Main Controller - Entry/Exit management
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot( 3, 5);  // 10 vehicles capacity, 3 floors, 5 spots per floor
        DisplayBoard displayBoard = new DisplayBoard();

        // Display available spots
        displayBoard.displayAvailableSpots(parkingLot);

        // Create vehicles
        Vehicle car = new Vehicle(VehicleType.CAR);
        Vehicle truck = new Vehicle(VehicleType.TRUCK);
        
        // Park vehicles
        //TODO: parkVehicle call should return ticket object
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);

        // Display available spots again
        displayBoard.displayAvailableSpots(parkingLot);

        // Exit vehicles with payment
        ParkingTicket ticketForCar = new ParkingTicket(car);
        ParkingTicket ticketForTruck = new ParkingTicket(truck);

        Payment cashPayment = new CashPayment();
        parkingLot.exitVehicle(ticketForCar, cashPayment);
        parkingLot.exitVehicle(ticketForTruck, cashPayment);

        // Add a new floor
        parkingLot.addFloor(5);
        displayBoard.displayAvailableSpots(parkingLot);  // Show available spots after adding new floor
    }
}
