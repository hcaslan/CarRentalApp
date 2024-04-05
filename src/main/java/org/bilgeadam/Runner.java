package org.bilgeadam;

import org.bilgeadam.constant.SessionContext;
import org.bilgeadam.controller.Controller;
import org.bilgeadam.entity.User;
import org.bilgeadam.service.UserService;

public class Runner {
    public static void main(String[] args) {
        Controller c = new Controller();
        setAdmin(); //uygulama başlatıldığında "admin" kullanıcı adında ve "admin" şifresiyle bir admin oluşturur.
        c.mainMenu();
    }
    public static void setAdmin(){
        new UserService().saveAdmin();
    }
}
