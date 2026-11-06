package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This class manages reading and saving bakery transactions
public class TransactionFileManager {

    // Store the location of our transaction file
    private static final String FILE_NAME = "transactions.csv";

    // Save a transaction to the CSV file
    public void saveTransaction(Transaction transaction) {

        // Open the file in append mode so we keep existing transactions
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            // Write the transaction details into the file
            writer.write(transaction.getDate() + "|"
                    + transaction.getTime() + "|"
                    + transaction.getDescription() + "|"
                    + transaction.getVendor() + "|"
                    + transaction.getAmount() + "\n");

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Read all transactions from the CSV file
    public List<Transaction> readTransactions() {

        // Create a list to store the transactions
        List<Transaction> transactions = new ArrayList<>();

        // Open the file so we can read its contents
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            // Skip the header row
            String line = reader.readLine();

            // Read each transaction until there are no more lines
            while ((line = reader.readLine()) != null) {

                // Separate the transaction information using |
                String[] parts = line.split("\\|", -1);

                // Make sure the row contains all five pieces of information
                if (parts.length == 5) {

                    try {
                        // Create a Transaction object
                        Transaction transaction = new Transaction(
                                parts[0],
                                parts[1],
                                parts[2],
                                parts[3],
                                Double.parseDouble(parts[4])
                        );

                        // Add the transaction to the list
                        transactions.add(transaction);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid transaction amount: " + line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        // Return the list of transactions
        return transactions;
    }
}