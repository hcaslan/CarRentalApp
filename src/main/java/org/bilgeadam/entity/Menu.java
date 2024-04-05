package org.bilgeadam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.utility.OutputHelper;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class Menu {
    private final String title;
    private final List<String> menuHeaders;

    public Menu(String title, List<String> menuHeaders) {
        this.title = title;
        this.menuHeaders = menuHeaders;
    }

    public void showMenu() {
        System.out.println("*************************************************");
        System.out.println("*************CAR  RENTAL APPLICATION*************");
        System.out.println("*************************************************");
        System.out.println("***" + title + "***");
        for (int i = 0; i < menuHeaders.size(); i++) {
            System.out.println((i) + "- " + menuHeaders.get(i));
        }
    }

    public void showHeader(int headerIndex) {
        System.out.println("***" + menuHeaders.get(headerIndex) + "***");
    }
}
