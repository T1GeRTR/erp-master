package com.mtv.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = "handler")
public class User implements UserDetails {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<Hours> hours;
    private List<LaborRecord> userHours;
    private boolean saved;
    private Role role;

    public User(int id, String firstname, String lastname, String email, String password, List<Hours> hours, List<LaborRecord> userHours, boolean saved) {
        this(id, firstname, lastname, email, password, hours, userHours, saved, Role.ROLE_EMPLOYEE);
    }

    public User(int id, String firstname, String lastname, String email, String password, List<Hours> hours, List<LaborRecord> userHours) {
        this(id, firstname, lastname, email, password, hours, userHours, false);
    }

    public User(int id, String firstname, String lastname, String email, String password, List<Hours> hours) {
        this(id, firstname, lastname, email, password, hours, new ArrayList<>());
    }

    public User(String firstname, String lastname) {
        this(0, firstname, lastname, "", "", new ArrayList<>());
    }

    public User(int id, String firstname, String lastname, String email, String password, Role role) {
        this(id, firstname, lastname, email, password, new ArrayList<>(), new ArrayList<>(), false, role);
    }

    public User(String firstname, String lastname, String email, String password, Role role) {
        this(0, firstname, lastname, email, password, role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getFirstname(), user.getFirstname()) && Objects.equals(getLastname(), user.getLastname()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail());
    }
}
