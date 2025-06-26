import java.util.Scanner;

class Flight {
    String flightNumber;
    String origin;
    String destination;
    String departureTime;
    String[] economySeats;
    String[] businessSeats;
    boolean[] economyBooked;
    boolean[] businessBooked;

    Flight(String flightNumber, String origin, String destination, String departureTime, int ecoCount, int busCount) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;

        economySeats = new String[ecoCount];
        businessSeats = new String[busCount];
        economyBooked = new boolean[ecoCount];
        businessBooked = new boolean[busCount];

        for (int i = 0; i < ecoCount; i++) economySeats[i] = "E" + (i + 1);
        for (int i = 0; i < busCount; i++) businessSeats[i] = "B" + (i + 1);
    }

    void showSeats() {
        System.out.print("Available Economy Seats: ");
        for (int i = 0; i < economySeats.length; i++) {
            if (!economyBooked[i]) System.out.print(economySeats[i] + " ");
        }
        System.out.println();

        System.out.print("Available Business Seats: ");
        for (int i = 0; i < businessSeats.length; i++) {
            if (!businessBooked[i]) System.out.print(businessSeats[i] + " ");
        }
        System.out.println();
    }

    boolean isSeatAvailable(String seat) {
        for (int i = 0; i < economySeats.length; i++) {
            if (economySeats[i].equals(seat) && !economyBooked[i]) return true;
        }
        for (int i = 0; i < businessSeats.length; i++) {
            if (businessSeats[i].equals(seat) && !businessBooked[i]) return true;
        }
        return false;
    }

    boolean bookSeat(String seat) {
        for (int i = 0; i < economySeats.length; i++) {
            if (economySeats[i].equals(seat)) {
                if (!economyBooked[i]) {
                    economyBooked[i] = true;
                    return true;
                } else return false;
            }
        }
        for (int i = 0; i < businessSeats.length; i++) {
            if (businessSeats[i].equals(seat)) {
                if (!businessBooked[i]) {
                    businessBooked[i] = true;
                    return true;
                } else return false;
            }
        }
        return false;
    }

    void showFlight(int index) {
        System.out.println(index + ". " + flightNumber + ": " + origin + " → " + destination + " at " + departureTime);
    }
}

public class FlightBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Flight[] flights = new Flight[3];
        flights[0] = new Flight("AI101", "Delhi", "Mumbai", "2025-07-30 14:30", 6, 3);
        flights[1] = new Flight("AI202", "Bangalore", "Kolkata", "2025-08-01 09:00", 5, 2);
        flights[2] = new Flight("AI303", "Chennai", "Hyderabad", "2025-08-02 11:00", 4, 2);

        System.out.println("🛫 Welcome to Indian Flight Booking System 🇮🇳");

        while (true) {
            System.out.println("\nAvailable Flights:");
            for (int i = 0; i < flights.length; i++) {
                flights[i].showFlight(i + 1);
            }

            System.out.print("Enter flight number to book (0 to exit): ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Thank you for using the Indian Flight Booking System!");
                break;
            }

            if (choice < 1 || choice > flights.length) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            Flight selected = flights[choice - 1];
            selected.showSeats();

            System.out.print("Enter seat to book (E1, B1...): ");
            String seat = sc.nextLine().toUpperCase();

            if (!selected.isSeatAvailable(seat)) {
                System.out.println("Seat not available or invalid.");
                continue;
            }

            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            double price = seat.startsWith("B") ? 9000.0 : 4500.0;

            System.out.print("Enter your card number: ");
            String card = sc.nextLine();

            System.out.println("Processing payment of ₹" + price + "...");
            System.out.println("✅ Payment successful!");

            boolean booked = selected.bookSeat(seat);
            if (booked) {
                System.out.println("✅ Booking Confirmed!");
                System.out.println("Passenger: " + name);
                System.out.println("Flight: " + selected.flightNumber);
                System.out.println("Route: " + selected.origin + " → " + selected.destination);
                System.out.println("Time: " + selected.departureTime);
                System.out.println("Seat: " + seat);
                System.out.println("Fare: ₹" + price);
            } else {
                System.out.println("❌ Booking failed. Seat may already be booked.");
            }
        }

        sc.close();
    }
}