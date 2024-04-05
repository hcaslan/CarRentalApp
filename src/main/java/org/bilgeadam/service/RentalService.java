package org.bilgeadam.service;

import org.bilgeadam.constant.SessionContext;
import org.bilgeadam.entity.Car;
import org.bilgeadam.entity.Rental;
import org.bilgeadam.repository.RentalRepository;
import org.bilgeadam.repository.UserRepository;
import org.bilgeadam.utility.InputHelper;
import org.bilgeadam.utility.OutputHelper;

import java.sql.Date;
import java.util.List;

public class RentalService {
    private final RentalRepository rentalRepository;
    private static final CarService carService = new CarService();

    public RentalService() {
        this.rentalRepository = new RentalRepository();
    }

    public void rentCar() {
        String startDate = InputHelper.getStringInput("Start Date (YYYY-MM-DD): ");
        String endDate = InputHelper.getStringInput("End Date (YYYY-MM-DD): ");
        try{
            List<Car> availableCars = carService.findAvailableCars(Date.valueOf(startDate), Date.valueOf(endDate));
            if (availableCars.isEmpty()) {
                System.out.println("No cars available.");
            }else {
                availableCars.forEach(System.out::println);
                int select = InputHelper.getIntegerInput("1: Select \n2: Filter");
                if(select==1){
                    Long choice = InputHelper.getLongInput("Select a car by Id to rent: ");
                    if(availableCars.stream().anyMatch(car -> car.getId().equals(choice))) {
                        Rental rental = Rental.builder()
                                .startDate(Date.valueOf(startDate))
                                .endDate(Date.valueOf(endDate))
                                .user(SessionContext.getUser())
                                .car(availableCars.stream().filter(car -> car.getId().equals(choice)).findFirst().get())
                                .build();
                        rentalRepository.save(rental);
                        System.out.println("Car rented successfully.");
                    }else {
                        OutputHelper.errorMessage("Invalid choice!");
                    }
                }else if(select==2){
                    List<Car> carList = carService.searchCar();
                    if(!carList.isEmpty()){
                        Long choice = InputHelper.getLongInput("Select a car by Id to rent: ");
                        if(carList.stream().anyMatch(car -> car.getId().equals(choice))) {
                            Rental rental = Rental.builder()
                                    .startDate(Date.valueOf(startDate))
                                    .endDate(Date.valueOf(endDate))
                                    .user(SessionContext.getUser())
                                    .car(carList.stream().filter(car -> car.getId().equals(choice)).findFirst().get())
                                    .build();
                            rentalRepository.save(rental);
                            System.out.println("Car rented successfully.");
                        }else {
                            OutputHelper.errorMessage("Invalid choice!");
                        }
                    }
                }else{
                    OutputHelper.errorMessage("Invalid choice!");
                }
            }
        }catch(Exception e){
            OutputHelper.errorMessage("Invalid Date or Date Format!");
        }
    }
}
