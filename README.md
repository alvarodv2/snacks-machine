# Snack Vending Machine

A simple Java console application that simulates a snack vending machine. This project demonstrates basic Java programming concepts including file I/O, object-oriented programming, and console interface design.

## Features

- View available snacks with prices
- Purchase snacks by ID
- View purchase receipt
- Add new snacks to the machine
- Persistent storage using text file
- Price formatting with Euro currency

## Project Structure

```
snacks-machine/
├── src/
│   ├── application/
│   │   └── SnacksMachine.java     # Main application class
│   ├── domain/
│   │   └── Snack.java            # Snack entity class
│   └── service/
│       ├── ISnacksService.java    # Service interface
│       ├── SnacksFileService.java # File-based implementation
│       └── SnacksListService.java # Memory-based implementation
└── snacks.txt                     # Persistent storage file
```

## How to Run

1. Make sure you have Java JDK 17 or higher installed
2. Compile the project:
   ```bash
   javac src/application/*.java src/domain/*.java src/service/*.java
   ```
3. Run the application:
   ```bash
   java application.SnacksMachine
   ```

## Usage

The application presents a menu with the following options:

1. **Buy snack**: Purchase a snack by entering its ID
2. **Show receipt**: Display current purchase receipt with total
3. **Add new snack**: Add a new snack to the vending machine
4. **Exit**: Close the application

## Data Storage

Snacks are stored in `snacks.txt` with the following format:
```
id,name,price€
```
Example:
```
1,Chips,2.70€
2,Pepsi,1.20€
3,Sandwich,2.30€
```

## Implementation Details

- Uses the Service pattern for data access
- Implements both file-based and in-memory storage options
- Handles currency formatting with Euro symbol
- Provides error handling for invalid inputs
- Uses Java text blocks for menu display

## Requirements

- Java JDK 17 or higher
- Text file read/write permissions in the application directory

## Future Improvements

- Add stock management
- Add unit tests
