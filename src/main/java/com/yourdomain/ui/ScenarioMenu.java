package com.yourdomain.ui;

import com.yourdomain.dao.ScenarioDao;
import com.yourdomain.model.ScenarioEntity;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final ScenarioDao scenarioDao = new ScenarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            printHeader();
            System.out.println("[1] Book Movie Ticket");
            System.out.println("[2] View Booked Tickets");
            System.out.println("[3] Exit System");
            printDivider();
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter choice (1-3): ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline buffer

            switch (choice) {
                case 1:
                    bookMovieTicket();
                    break;
                case 2:
                    viewBookedTickets();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid option! Please choose between 1 and 3.");
            }
        } while (choice != 3);
    }

    private void bookMovieTicket() {
        System.out.println("\n--- BOOK MOVIE TICKET ---");
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Movie Title: ");
        String movieTitle = scanner.nextLine();

        System.out.print("Enter Seat Number: ");
        String seatNo = scanner.nextLine();

        System.out.print("Enter Ticket Price: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid price. Enter Ticket Price: ");
            scanner.next();
        }
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume leftover newline

        scenarioDao.addTicket(customerName, movieTitle, seatNo, price);
        System.out.println("\nSUCCESS: Movie ticket booked successfully!\n");
    }

    private void viewBookedTickets() {
        List<ScenarioEntity> tickets = scenarioDao.getAllTickets();

        printDivider();
        System.out.println("                         BOOKED MOVIE TICKETS");
        printDivider();
        System.out.printf("| %-3s | %-16s | %-24s | %-7s | %-8s |\n", "ID", "CUSTOMER NAME", "MOVIE TITLE", "SEAT NO", "PRICE");
        printDivider();

        for (ScenarioEntity t : tickets) {
            System.out.printf("| %-3d | %-16s | %-24s | %-7s | %-8.2f |\n",
                    t.id(), t.customerName(), t.movieTitle(), t.seatNo(), t.price());
        }
        printDivider();
        System.out.println("Total Records Displayed: " + tickets.size() + "\n");
    }

    private void printHeader() {
        printDivider();
        System.out.println("               CINEMA MOVIE TICKET BOOKING");
        printDivider();
    }

    private void printDivider() {
        System.out.println("-------------------------------------------------------------------------");
    }
}