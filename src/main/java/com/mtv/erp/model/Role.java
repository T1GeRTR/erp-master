package com.mtv.erp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public enum Role implements GrantedAuthority {
    ROLE_EMPLOYEE(1, "ROLE_EMPLOYEE"), ROLE_ADMIN(2, "ROLE_ADMIN"), ROLE_GIP(3, "ROLE_GIP"), ROLE_BOOKER(4, "ROLE_BOOKER");

    private int id;
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
