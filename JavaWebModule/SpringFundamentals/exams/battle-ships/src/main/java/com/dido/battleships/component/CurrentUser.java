package com.dido.battleships.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class CurrentUser {

    private Long id;
    private String name;
    private String username;
    private boolean isLoggedIn;

    public void clear() {
        isLoggedIn = false;
        name = null;
    }
}
