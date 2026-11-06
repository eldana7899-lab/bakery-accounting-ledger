# Sweet Treats Bakery – Accounting Ledger

A Java command-line application that tracks the money coming in and going out of a small bakery. Every sale (deposit) and every supply purchase (payment) is saved to a `transactions.csv` file, and the ledger screens let you review and report on them.

This is Capstone 1 for the Year Up / Pluralsight Java Developer Learn to Code Academy.

## Features

**Home screen**
- **D) Add Deposit**: record a sale (description, customer, amount)
- **P) Make Payment**: record a purchase (description, vendor, amount), saved as a negative amount
- **L) View Ledger**: open the ledger screen
- **X) Exit**: close the application

**Ledger screen** (newest entries first)
- **A) All**: every transaction
- **D) Deposits**: money coming in only
- **P) Payments**: money going out only
- **R) Reports**: Month To Date, Previous Month, Year To Date, Previous Year, and Search by Vendor, each with a total
- **H) Home**: return to the home screen

## Transaction file format

Each transaction is one line in `transactions.csv`, separated by `|`:

```
date|time|description|vendor|amount
2026-11-05|22:30:00|Birthday Cake Order|Emily|85.00
2026-11-05|22:45:29|Flour Supplies|ABC Baking Supplies|-45.50
```

## How to run

**Requirements:** Java 17 or newer.

**In IntelliJ IDEA**
1. Open the project folder (IntelliJ detects it as a Maven project).
2. Open `src/main/java/com/pluralsight/BakeryLedgerApp.java`.
3. Click the green Run arrow next to `main`.

**From a terminal**
```bash
javac -d out src/main/java/com/pluralsight/*.java
java -cp out com.pluralsight.BakeryLedgerApp
```

Run the program from the project's root folder so it finds `transactions.csv`.

## Project structure

| File | Purpose |
|------|---------|
| `BakeryLedgerApp.java` | Menus, user input, and reports |
| `Transaction.java` | A single transaction (date, time, description, vendor, amount) |
| `TransactionFileManager.java` | Reads and writes `transactions.csv` |
| `transactions.csv` | The saved transactions |
