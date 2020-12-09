package classes;

import java.util.Arrays;
import java.util.Scanner;


/**
 * @author Alex Giazitzis
 */

public class Cinema {

    private static final Scanner scanner = new Scanner(System.in);
    private final char[][] seats;
    private int MAX_SEATS;
    private int income;
    private int ticketsSold;
    private int totalIncome;

    public Cinema() {
        seats = cinemaRoom();
    }

    private char[][] cinemaRoom() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        char[][] seats = new char[rows][seatsInRow];
        for (char[] vector : seats) {
            Arrays.fill(vector, 'S');
        }

        MAX_SEATS = seats.length * seats[0].length;

        if (MAX_SEATS <= 60) {
            totalIncome = MAX_SEATS * 10;
        } else {
            for (int i = 0; i <= seats.length / 2; i++) {
                totalIncome += 8 * seats[0].length;
            }
            for (int i = seats.length / 2 + 1; i < seats.length; i++) {
                totalIncome += 10 * seats[0].length;
            }
        }

        return seats;
    }

    public void menu() {
        loop:
        do {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            switch (scanner.nextInt()) {
                case 1:
                    showSeats(seats);
                    break;
                case 2:
                    buy(seats);
                    break;
                case 0:
                    break loop;
                case 3:
                    statistics();
                    break;
                default:
                    System.out.println("Invalid menu choice inputted, please write an option's number based on the ones shown!");
            }
        } while (true);
    }


    private void buy(char[][] seats) {
        do {
            System.out.println("\nEnter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row < 0 || row > seats.length || seat < 0 || seat > seats[0].length) {
                System.out.println("\nWrong input!");
                continue;
            } else if (seats[row - 1][seat - 1] == 'B') {
                System.out.println("\nThat ticket has already been purchased");
                continue;
            }

            int price;

            if (MAX_SEATS <= 60) {
                price = 10;
            } else {
                if (row <= seats.length / 2) {
                    price = 10;

                } else {
                    price = 8;
                }
            }
            System.out.println("\nTicket price: $" + price);
            seats[row - 1][seat - 1] = 'B';
            income += price;
            ticketsSold++;
            break;
        } while (true);

    }

    private void showSeats(char[][] seats) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < seats[0].length; i++) {
            System.out.print((i + 1) + " ");
        }

        for (int i = 0; i < seats.length; i++) {
            System.out.println();
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
        }

        System.out.println();
    }

    private void statistics() {

        double percentage = (ticketsSold * 100.00f) / MAX_SEATS;

        System.out.printf("%nNumber of purchased tickets: %d%nPercentage: %.2f%c%nCurrent income: $%d%nTotal income: $%d%n",
                ticketsSold, percentage, '%', income, totalIncome);
    }

}