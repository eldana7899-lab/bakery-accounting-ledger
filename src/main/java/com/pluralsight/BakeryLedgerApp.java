package com.pluralsight;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public class BakeryLedgerApp {
    // Bakery theme colors
    private static final String PINK = "\u001B[95m";
    private static final String YELLOW = "\u001B[93m";
    private static final String RESET = "\u001B[0m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);;
        boolean running = true;

        //Create a file manager to save and read transactions

        TransactionFileManager fileManager = new TransactionFileManager();


    // keep the application running until the user exits
        while (running) {

            System.out.println();
            System.out.println(PINK + "========================================" + RESET);
            System.out.println(PINK + "          SWEET TREATS BAKERY" + RESET);
            System.out.println(YELLOW + "          Freshly Baked, Every Day!" + RESET);
            System.out.println(PINK + "========================================" + RESET);
            System.out.println(YELLOW + "          ACCOUNTING LEDGER" + RESET);
            System.out.println("----------------------------------------");
            System.out.println(PINK + "  D) Add Deposit" + RESET);
            System.out.println(YELLOW + "  P) Make Payment" + RESET);
            System.out.println(PINK + "  L) View Ledger" + RESET);
            System.out.println(YELLOW + "  X) Exit" + RESET);
            System.out.println("----------------------------------------");
            System.out.print(PINK + "Please select an option: " + RESET);

            // read the option selected by the user
            String choice = scanner.nextLine().toUpperCase();

            //Check which option the user selected
            switch (choice) {

                case "D":
                    System.out.println("Enter Deposit description:");
                    String description = scanner.nextLine();;

                    // Ask who the money came from
                    System.out.print("Enter customer name: ");
                    String vendor = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    String date = LocalDate.now().toString();
                    String time = LocalTime.now().withNano(0).toString();
                    // Create a new deposit transaction
                    Transaction transaction = new Transaction(
                            date, time, description, vendor, amount
                    );

                    // Save the deposit
                    fileManager.saveTransaction(transaction);

                    System.out.println("Deposit saved successfully!");
                    break;

                case "P":

                    // Ask what the bakery purchased
                    System.out.print("Enter payment description: ");
                    String paymentDescription = scanner.nextLine();

                    // Ask who received the payment
                    System.out.print("Enter vendor name: ");
                    String paymentVendor = scanner.nextLine();

                    // Ask how much the bakery paid
                    System.out.print("Enter payment amount: ");
                    double paymentAmount = Double.parseDouble(scanner.nextLine());

                    // Get today's date
                    String paymentDate = LocalDate.now().toString();

                    // Get the current time
                    String paymentTime = LocalTime.now().withNano(0).toString();

                    // Create a payment transaction
                    Transaction paymentTransaction = new Transaction(
                            paymentDate, paymentTime, paymentDescription,
                            paymentVendor, -Math.abs(paymentAmount)
                    );

                    // Save the payment
                    fileManager.saveTransaction(paymentTransaction);

                    System.out.println("Payment saved successfully!");

                    break;

                case "L": {

                    List<Transaction> ledgerTransactions = fileManager.readTransactions();

                    System.out.println("\n========== BAKERY LEDGER ==========");
                    System.out.println("A) All Transactions");
                    System.out.println("D) Deposits Only");
                    System.out.println("P) Payments Only");
                    System.out.println("R) Reports");
                    System.out.println("H) Home");
                    System.out.print("Choose an option: ");

                    String ledgerChoice = scanner.nextLine().trim().toUpperCase();

                    if (ledgerChoice.equals("R")) {

                        System.out.println("\n========== FINANCIAL REPORTS ==========");
                        System.out.println("1) Month to Date");
                        System.out.println("2) Previous Month");
                        System.out.println("3) Year to Date");
                        System.out.println("4) Previous Year");
                        System.out.println("5) Search by Vendor");
                        System.out.println("0) Back");
                        System.out.print("Choose a report: ");

                        String reportChoice = scanner.nextLine();
                        if (reportChoice.equals("0")) {
                            break;
                        }

                        String searchVendor = "";

                        if (reportChoice.equals("5")) {
                            System.out.print("Enter vendor name: ");
                            searchVendor = scanner.nextLine();
                        }

                        LocalDate today = LocalDate.now();
                        double reportTotal = 0;

                        for (Transaction reportTransaction : ledgerTransactions) {

                            LocalDate transactionDate =
                                    LocalDate.parse(reportTransaction.getDate());

                            boolean matches = false;

                            switch (reportChoice) {
                                case "1":
                                    matches = transactionDate.getYear() == today.getYear()
                                            && transactionDate.getMonthValue() == today.getMonthValue();
                                    break;

                                case "2":
                                    LocalDate previousMonth = today.minusMonths(1);
                                    matches = transactionDate.getYear() == previousMonth.getYear()
                                            && transactionDate.getMonthValue() == previousMonth.getMonthValue();
                                    break;

                                case "3":
                                    matches = transactionDate.getYear() == today.getYear();
                                    break;

                                case "4":
                                    matches = transactionDate.getYear() == today.getYear() - 1;
                                    break;

                                case "5":
                                    matches = reportTransaction.getVendor()
                                            .toLowerCase().contains(searchVendor.toLowerCase());
                                    break;
                            }

                            if (matches) {
                                System.out.println("----------------------------------");
                                System.out.println("Date: " + reportTransaction.getDate());
                                System.out.println("Description: " + reportTransaction.getDescription());
                                System.out.println("Vendor: " + reportTransaction.getVendor());
                                System.out.printf("Amount: $%.2f%n", reportTransaction.getAmount());

                                reportTotal += reportTransaction.getAmount();
                            }
                        }

                        System.out.println("----------------------------------");
                        System.out.printf("Total: $%.2f%n", reportTotal);

                    } else if (ledgerChoice.equals("A")
                            || ledgerChoice.equals("D")
                            || ledgerChoice.equals("P")) {

                        for (int i = ledgerTransactions.size() - 1; i >= 0; i--) {

                            Transaction ledgerItem = ledgerTransactions.get(i);
                            double ledgerAmount = ledgerItem.getAmount();

                            if (ledgerChoice.equals("A")
                                    || (ledgerChoice.equals("D") && ledgerAmount > 0)
                                    || (ledgerChoice.equals("P") && ledgerAmount < 0)) {

                                System.out.println("----------------------------------");
                                System.out.println("Date: " + ledgerItem.getDate());
                                System.out.println("Description: " + ledgerItem.getDescription());
                                System.out.println("Vendor: " + ledgerItem.getVendor());
                                System.out.printf("Amount: $%.2f%n", ledgerAmount);
                            }
                        }

                    }

                    break;
                }

                case "X":
                    System.out.println("Exiting Bakery Accounting Ledger...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please select one of the provided options.");
                    break;

            }
        }//end of while loop

        //close the scanner

        scanner.close();


    }
}
