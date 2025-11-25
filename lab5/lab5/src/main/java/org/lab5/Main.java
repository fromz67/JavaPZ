package org.lab5;

/**
 * Main.
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Create a passenger train with maximum 5 cars
            PassengerTrain train = new PassengerTrain(5);

            // Create several cars with different comfort levels
            PassengerCar economyCar = new EconomyPassengerCar("EC-01", 30.5, 100, 2000);
            PassengerCar coupeCar = new CoupePassengerCar("CP-01", 32.0, 60, 1500);
            PassengerCar luxuryCar = new LuxuryPassengerCar("LX-01", 35.0, 40, 1000);

            // Add cars to the train
            train.addCar(economyCar);
            train.addCar(coupeCar);
            train.addCar(luxuryCar);

            // Load passengers and baggage with exception handling
            try {
                economyCar.addPassengers(90);
                economyCar.addBaggage(1500);

                coupeCar.addPassengers(55);
                coupeCar.addBaggage(1200);

                luxuryCar.addPassengers(30);
                luxuryCar.addBaggage(800);
            } catch (CapacityExceededException e) {
                System.err.println("Capacity error: " + e.getMessage());
            }

            // Calculate totals
            int totalPassengers = train.calculateTotalPassengers();
            double totalBaggage = train.calculateTotalBaggageWeight();

            System.out.println("Total passengers in train: " + totalPassengers);
            System.out.println("Total baggage weight in train: " + totalBaggage + " kg");

            // Sort cars by comfort level
            System.out.println("\nCars before sorting:");
            for (PassengerCar car : train.getCarsSnapshot()) {
                System.out.println(car);
            }

            train.sortByComfortLevel();

            System.out.println("\nCars after sorting by comfort level:");
            for (PassengerCar car : train.getCarsSnapshot()) {
                System.out.println(car);
            }

            // Find car by passenger count range
            int minPassengers = 50;
            int maxPassengers = 70;

            try {
                PassengerCar found = train.findCarByPassengerRange(minPassengers, maxPassengers);
                System.out.println(
                        "\nCar found in passenger range [" + minPassengers + ", " + maxPassengers + "]:"
                );
                System.out.println(found);
            } catch (CarNotFoundException e) {
                System.err.println("Search error: " + e.getMessage());
            }

        } catch (RailwayException | IllegalArgumentException e) {

            System.err.println("Error during train initialization: " + e.getMessage());
        }
    }
}
