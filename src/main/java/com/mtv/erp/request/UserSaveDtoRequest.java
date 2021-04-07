package com.mtv.erp.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtv.erp.model.Hours;
import com.mtv.erp.model.LaborRecord;
import com.mtv.erp.model.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(value = "handler")
public class UserSaveDtoRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String passwordConfirm;
    private Role role;
}
