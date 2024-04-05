package org.bilgeadam.controller;

import org.bilgeadam.constant.SessionContext;
import org.bilgeadam.entity.Menu;
import org.bilgeadam.entity.User;
import org.bilgeadam.service.CarService;
import org.bilgeadam.service.RentalService;
import org.bilgeadam.service.UserService;
import org.bilgeadam.utility.InputHelper;
import org.bilgeadam.utility.OutputHelper;

import java.util.List;
import java.util.Optional;

public class Controller {
    private static final UserService userService = new UserService();
    private static final CarService carService = new CarService();
    private static final RentalService rentalService = new RentalService();

    public void mainMenu() {
        Menu mainMenu = Menu.builder()
                .title("Main Menu")
                .menuHeaders(List.of("Exit", "Login", "Register"))
                .build();
        int choice;
        do {
            mainMenu.showMenu();
            choice = InputHelper.getIntegerInput("Select: ");
            switch (choice) {
                case 0:
                    mainMenu.showHeader(choice);
                    System.exit(0);
                    break;
                case 1:
                    mainMenu.showHeader(choice);
                    Optional<User> optionalUser = userService.login();
                    if(optionalUser.isPresent()) {
                        SessionContext.setUser(optionalUser.get());
                        if(optionalUser.get().getIsAdmin()) {
                            adminOperations();
                        }else{
                            userOperations();
                        }
                    }
                    break;
                case 2:
                    mainMenu.showHeader(choice);
                    userService.register();
                    break;
                default:
                    OutputHelper.errorMessage("Invalid choice!");
            }
        } while (true);
    }

    private void userOperations() {
        Menu userOperationsMenu = Menu.builder()
                .title("User Operations")
                .menuHeaders(List.of("Exit","Rent A Car","Edit Profile"))
                .build();
        int choice;
        do {
            userOperationsMenu.showMenu();
            choice = InputHelper.getIntegerInput("Select: ");
            switch (choice) {
                case 0:
                    userOperationsMenu.showHeader(choice);
                    choice=0;
                    break;
                case 1:
                    userOperationsMenu.showHeader(choice);
                    rentalService.rentCar();
                    break;
                case 2:
                    userOperationsMenu.showHeader(choice);
                    userService.editProfile();
                    break;
                default:
                    OutputHelper.errorMessage("Invalid choice!");
            }
        } while (choice!=0);
    }

    private void adminOperations() {
        Menu adminOperationsMenu = Menu.builder()
                .title("Admin Operations")
                .menuHeaders(List.of("Return Previous Menu", "Add Car", "Car Search", "Add a User","List Users", "Report Menu"))
                .build();
        int choice;
        do {
            adminOperationsMenu.showMenu();
            choice = InputHelper.getIntegerInput("Select: ");
            switch (choice) {
                case 0:
                    choice = 0;
                    break;
                case 1:
                    adminOperationsMenu.showHeader(choice);
                    carService.addCar();
                    break;
                case 2:
                    adminOperationsMenu.showHeader(choice);
                    carService.searchCar();
                    break;
                case 3:
                    adminOperationsMenu.showHeader(choice);
                    userService.addUser();
                    break;
                case 4:
                    adminOperationsMenu.showHeader(choice);
                    userService.listUsers();
                    break;
                case 5:
                    adminOperationsMenu.showHeader(choice);
                    reportOperations();
                    break;
                default:
                    OutputHelper.errorMessage("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void reportOperations() {
        Menu reportOperationsMenu = Menu.builder()
                .title("Report Operations")
                .menuHeaders(List.of("Return Previous Menu", "List Cars On Rent", "List Idle Cars", "List Cars Rented by Specific User"))
                .build();
        int choice;
        do {
            reportOperationsMenu.showMenu();
            choice = InputHelper.getIntegerInput("Select: ");
            switch (choice) {
                case 0:
                    reportOperationsMenu.showHeader(choice);
                    choice = 0;
                    break;
                case 1:
                    reportOperationsMenu.showHeader(choice);
                    carService.listCarsOnRent();
                    break;
                case 2:
                    reportOperationsMenu.showHeader(choice);
                    carService.listIdleCars();
                    break;
                case 3:
                    reportOperationsMenu.showHeader(choice);
                    carService.listCarsRentedBySpecificUser();
                    break;
                default:
                    OutputHelper.errorMessage("Invalid choice!");
            }
        } while (choice != 0);
    }
}
