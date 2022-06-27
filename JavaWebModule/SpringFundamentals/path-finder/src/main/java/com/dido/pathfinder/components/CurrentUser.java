package com.dido.pathfinder.components;

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
    private String username;
    private String password;
    private boolean loggedIn;


    public void clear() {
        this.setUsername(null);
        this.setLoggedIn(false);
        this.setId(null);
    }
}
