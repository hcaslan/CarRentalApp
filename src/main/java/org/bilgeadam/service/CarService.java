package org.bilgeadam.service;

import org.bilgeadam.entity.Car;
import org.bilgeadam.entity.Rental;
import org.bilgeadam.entity.User;
import org.bilgeadam.repository.CarRepository;
import org.bilgeadam.utility.InputHelper;
import org.bilgeadam.utility.OutputHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarService {
    private CarRepository carRepository;
    private static final UserService userService = new UserService();
    public CarService() {
        this.carRepository = new CarRepository();
    }
    public void addCar() {
        String brand = InputHelper.getStringInput("Enter the car brand: ");
        String model = InputHelper.getStringInput("Enter the car model: ");
        int year = InputHelper.getIntegerInput("Enter the year: ");
        carRepository.save(Car.builder().brand(brand).model(model).year(year).build());
    }

    public List<Car> searchCar() {
        String keyword = InputHelper.getStringInput("Enter the Keyword for Search(To search by id, type ID): ");
        List<Car> list = new ArrayList<>();
        if(keyword.equalsIgnoreCase("ID")){
            Optional<Car> car = searchCarById();
            car.ifPresent(list::add);
        }else{
            list.addAll(searchCarByKeyword(keyword));
        }
        return list;
    }

    private List<Car> searchCarByKeyword(String keyword) {
        List<Car> carList = carRepository.searchCarByKeyword(keyword);
        if(!carList.isEmpty()){
            carList.forEach(System.out::println);
        }else{
            OutputHelper.errorMessage("No car matching this keyword!");
        }
        return carList;
    }

    private Optional<Car> searchCarById(){
        Long id = InputHelper.getLongInput("Enter the car id: ");
        Optional<Car> carOptional = carRepository.findById(id);
        if(carOptional.isPresent()){
            System.out.println(carOptional.get());
        }else{
            OutputHelper.errorMessage("No cars with this ID!");
        }
        return carOptional;
    }

    public void listCarsOnRent() {
        carRepository.listCarsOnRent().forEach(System.out::println);
    }

    public List<Car> listIdleCars() {
        String startDate = InputHelper.getStringInput("Start Date: ");
        String endDate = InputHelper.getStringInput("End Date: ");
        List<Car> carList = new ArrayList<Car>();
        try{
            carList = carRepository.findAvailableCars(Date.valueOf(startDate), Date.valueOf(endDate));
        }catch (Exception e){
            OutputHelper.errorMessage("Invalid Date or Date Format!");
        }
        if(!carList.isEmpty())
            carList.forEach(System.out::println);
        else
            OutputHelper.errorMessage("No cars available!");
        return carList;
    }

    public void listCarsRentedBySpecificUser() {
        Optional<User> userOptional = userService.searchUser();
        if(userOptional.isPresent()){
            List<Car> carList = carRepository.listCarsRentedBySpecificUser(userOptional.get().getId());
            carList.forEach(System.out::println);
        }else{
            OutputHelper.errorMessage("No user for search!");
        }
    }

    public List<Car> findAvailableCars(Date startDate, Date endDate) {
        return carRepository.findAvailableCars(startDate,endDate);
    }
}
