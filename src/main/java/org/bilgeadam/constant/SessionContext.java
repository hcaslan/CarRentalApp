package org.bilgeadam.constant;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bilgeadam.entity.User;
import org.bilgeadam.service.UserService;

public class SessionContext {
    @Getter
    @Setter
    private static User user;
}
