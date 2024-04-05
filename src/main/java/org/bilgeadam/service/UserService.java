package org.bilgeadam.service;

import org.bilgeadam.constant.SessionContext;
import org.bilgeadam.entity.Car;
import org.bilgeadam.entity.Menu;
import org.bilgeadam.entity.User;
import org.bilgeadam.repository.UserRepository;
import org.bilgeadam.utility.InputHelper;
import org.bilgeadam.utility.OutputHelper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public Optional<User> login() {
        User loggingUser;
        try {
            loggingUser = userRepository.findByColumnAndValue("username", InputHelper.getStringInput("Enter username: ")).getFirst();
            if (loggingUser.getPassword().equals(InputHelper.getStringInput("Enter password: "))){
                OutputHelper.successMessage("Logged in as " + loggingUser.getUsername());
                return Optional.of(loggingUser);
            }
            else{
                OutputHelper.errorMessage("Username or Password Incorrect");
                return Optional.empty();
            }
        } catch (NoSuchElementException e) {
            OutputHelper.errorMessage("User Not Found!");
            return Optional.empty();
        }
    }


    public void register() {
        String checkedEmail = emailControl();
        String checkedUsername = usernameControl();
        userRepository.save(User.builder()
                .email(checkedEmail)
                .username(checkedUsername)
                .password(InputHelper.getStringInput("Enter password: "))
                .build());
    }

    private String usernameControl() {
        String username = null;
        boolean isUsernameOk = false;
        while (!isUsernameOk) {
            username = InputHelper.getStringInput("Enter username: ");

            if (isUsernameUnique(username)) {
                isUsernameOk = true;
            } else {
                System.out.println("Username is already taken. Please choose a different username.");
            }
        }
        return username;
    }

    private String emailControl() {
        boolean isMailOk = false;
        String email = null;
        while (!isMailOk) {
            email = InputHelper.getStringInput("Enter mail address: ");
            if (isEmailValid(email) && isEmailUnique(email)) {
                isMailOk = true;
            }
        }
        return email;
    }

    private boolean isEmailValid(String email) {
        boolean matches = Pattern.matches("[_a-zA-Z0-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
        if (matches != true) {
            OutputHelper.errorMessage("Mail address invalid!");
        }
        return matches;
    }

    private boolean isEmailUnique(String email) {
        return userRepository.findByColumnAndValue("email", email).isEmpty();
    }

    private boolean isUsernameUnique(String userName) {
        return userRepository.findByColumnAndValue("username", userName).isEmpty();
    }

    public void addUser() {
        userRepository.save(User.builder()
                .username(InputHelper.getStringInput("Enter username: "))
                .password(InputHelper.getStringInput("Enter password: "))
                .email(InputHelper.getStringInput("Enter mail address: "))
                        .isAdmin(InputHelper.getBooleanInput("Enter T for true and F for false: "))
                .build());
    }

    public Optional<User> searchUser() {
        String keyword = InputHelper.getStringInput("Enter the Keyword for Search(To search by id, type ID): ");
        if (keyword.equalsIgnoreCase("ID")) {
            Optional<User> userOptional = searchUserById();
            if (userOptional.isPresent()) {
                return userOptional;
            } else {
                OutputHelper.errorMessage("No user with this ID!");
            }
        } else {
            List<User> userList = searchUserByKeyword(keyword);
            if (!userList.isEmpty()) {
                if (userList.size() > 1) {
                    userList.forEach(System.out::println);
                    int choice;
                    do {
                        choice = InputHelper.getIntegerInput("Select By Id (Select 0 to Exit): ");
                        return Optional.of(userList.get(choice));
                    } while (choice != 0);
                } else {
                    return Optional.of(userList.getFirst());
                }
            } else {
                OutputHelper.errorMessage("No user with this keyword!");
            }
        }
        return Optional.empty();
    }

    private List<User> searchUserByKeyword(String keyword) {
        List<User> userList = userRepository.searchUserByKeyword(keyword);
        return userList;
    }

    private Optional<User> searchUserById() {
        Long id = InputHelper.getLongInput("Enter the user id: ");
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional;
        } else {
            OutputHelper.errorMessage("No user with this ID!");
        }
        return Optional.empty();
    }

    public void editProfile() {
        Menu editProfileMenu = Menu.builder()
                .title("Edit Profile")
                .menuHeaders(List.of("Exit", "Name", "Lastname", "Username", "Email", "Password"))
                .build();
        int choice;
        do {
            editProfileMenu.showMenu();
            choice = InputHelper.getIntegerInput("Select: ");
            switch (choice) {
                case 0:
                    editProfileMenu.showHeader(choice);
                    choice = 0;
                    break;
                case 1:
                    editProfileMenu.showHeader(choice);
                    String name = InputHelper.getStringInput("Name :");
                    SessionContext.getUser().setName(name);
                    try{
                        userRepository.update(SessionContext.getUser());
                        OutputHelper.successMessage("Update Successful");
                    }catch (Exception e){
                        OutputHelper.errorMessage("Error During Update");
                    }
                    break;
                case 2:
                    editProfileMenu.showHeader(choice);
                    String lastname = InputHelper.getStringInput("Lastname :");
                    SessionContext.getUser().setLastname(lastname);
                    try{
                        userRepository.update(SessionContext.getUser());
                        OutputHelper.successMessage("Update Successful");
                    }catch (Exception e){
                        OutputHelper.errorMessage("Error During Update");
                    }
                    break;
                case 3:
                    editProfileMenu.showHeader(choice);
                    String checkedUsername = usernameControl();
                    SessionContext.getUser().setUsername(checkedUsername);
                    try{
                        userRepository.update(SessionContext.getUser());
                        OutputHelper.successMessage("Update Successful");
                    }catch (Exception e){
                        OutputHelper.errorMessage("Error During Update");
                    }
                    break;
                case 4:
                    editProfileMenu.showHeader(choice);
                    String checkedEmail = emailControl();
                    SessionContext.getUser().setEmail(checkedEmail);
                    try{
                        userRepository.update(SessionContext.getUser());
                        OutputHelper.successMessage("Update Successful");
                    }catch (Exception e){
                        OutputHelper.errorMessage("Error During Update");
                    }
                    break;
                case 5:
                    editProfileMenu.showHeader(choice);
                    String password = InputHelper.getStringInput("Current Password :");
                    if(SessionContext.getUser().getPassword().equals(password)){
                        String newPassword = InputHelper.getStringInput("New Password :");
                        SessionContext.getUser().setPassword(newPassword);
                        try{
                            userRepository.update(SessionContext.getUser());
                            OutputHelper.successMessage("Update Successful");
                        }catch (Exception e){
                            OutputHelper.errorMessage("Error During Update");
                        }
                    } else {
                        OutputHelper.errorMessage("Current password is incorrect!");
                    }
                    break;
                default:
                    OutputHelper.errorMessage("Invalid choice!");
            }
        } while (choice!=0);
    }

    public void saveAdmin(){
        List<User> adminList = userRepository.findByColumnAndValue("username", "admin");
        if(!adminList.isEmpty()){
            userRepository.deleteById(adminList.getFirst().getId());
        }
        User admin = User.builder()
                        .username("admin")
                                .isAdmin(true)
                                        .email("admin@bilgeadam.com")
                                                .password("admin")
                                                        .lastname("admin")
                                                                .name("admin").build();
        userRepository.save(admin);
    }

    public void listUsers() {
        userRepository.getAll().forEach(System.out::println);
    }
}
