package com.mtv.erp.response;

import com.mtv.erp.model.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserGetAllDtoResponse {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private boolean saved;
}
