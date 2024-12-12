import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Bookingseat {
    static class Booking {
        String seatnum;
        String bookingDate;

    public Booking(String seatsnum, String bookingDate) {
        this.seatnum = seatsnum;
        this.bookingDate = bookingDate;
    }
    public String toString() {
        return "Seat: " + seatnum + ", Date: " + bookingDate;
    }
}
    public static void main(String[] args) {
        int rows, cols;
        int option=0;
        int i,j;
        int totalBookedSeats=0;
        String[][] seats = null;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the row number: ");
        rows = input.nextInt();
        System.out.print("Enter the column number: ");
        cols = input.nextInt();
        String[][] array2D = new String[rows][cols];
        char rowLetter = 'A';
        for ( i = 0; i < rows; i++) {
            for ( j = 0; j < cols; j++) {
                array2D[i][j] = rowLetter + "-" +(j+1) + ": AV";
                System.out.println(array2D[i][j] + " ");

            }
            rowLetter++;
        }
        ArrayList<Booking> bookings = new ArrayList<>();

        do{
            System.out.print("Welcome to the booking seats!\n");
            System.out.println("1.View seat");
            System.out.println("2.Book seat");
            System.out.println("3.Cancel a booking seat");
            System.out.println("4.View a history seat");
            System.out.println("5.Exit");
            System.out.print("Please choose an option: ");
            option = input.nextInt();
            switch(option) {
                case 1: {
                    System.out.println("====================================");
                    System.out.println("1. View seat ");
                    for (i = 0; i < rows; i++) {
                        System.out.print("[");
                        for (j = 0; j < cols; j++) {
                            System.out.print(array2D[i][j]);
                            if (j < cols - 1) {
                                System.out.print(",");
                            }

                        }
                        System.out.println("]");
                    }
                    System.out.println("==================================");
                }
                break;
                case 2: {
                    System.out.println("====================================");
                    System.out.println("2. Book seat");
                    System.out.print("Enter the seat code to book (e.g., A-1): ");
                    String seatcode = input.next();

                    boolean found = false;
                    for (i = 0; i < rows; i++) {
                        for (j = 0; j < cols; j++) {
                            if (array2D[i][j].startsWith(seatcode)) {
                                found = true;
                                if (array2D[i][j].endsWith("OB")) {
                                    System.out.println("Seat is already booked.");
                                } else {
                                    array2D[i][j] = seatcode + "-OB";
                                    totalBookedSeats++;
                                    String bookingDate = new Date().toString();
                                    bookings.add(new Booking(seatcode,bookingDate));
                                    System.out.println("Seat: " + seatcode + ", Date: " + bookingDate);
                                    System.out.println("Seat booked successfully.");
                                    System.out.println("===========================================");

                                }
                                break;
                            }
                        }
                        if (found) break;
                    }

                    if (!found) {
                        System.out.println("Seat code not found.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("===================================");
                    System.out.println("3. Cancel a booking seat");
                    System.out.print("Enter the seat code to book (A-1): ");
                    String seatcode = input.next();
                    boolean found = false;
                    for (i = 0; i < rows; i++) {
                        for (j = 0; j < cols; j++) {
                            if (array2D[i][j].startsWith(seatcode)) {
                                found = true;
                                if (array2D[i][j].endsWith("AV")) {
                                    System.out.println("Seat is already available.");
                                }else {
                                    array2D[i][j] = seatcode + "AV";
                                    totalBookedSeats--;
                                    String bookingDate = new Date().toString();
                                    bookings.removeIf(booking -> booking.seatnum.equals(seatcode));
                                    System.out.println("Seat canceled successfully.");
                                    System.out.println("===================================================");
                                }
                                break;
                            }
                        }
                        if (found) break;
                    }
                    if (!found) {
                        System.out.println("Seat code not found.");
                    }

                }break;
                case 4: {
                    System.out.println("=====================================");
                    System.out.println("4.View a history seat");
                    if (bookings.isEmpty()) {
                        System.out.println("No seats have been booked yet.");
                    } else {
                        for (Booking booking : bookings) {
                            System.out.println(booking);
                        }
                    }
                }
                break;
                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (option != 5);
    }
}