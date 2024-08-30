package com.sam.crickstatloginservice.dto;

import com.sam.crickstatloginservice.entity.ERole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private Long userid;
    private String username;
    private String password;
    private String email;
    private ERole role;


    public void setRole(ERole role) {
        this.role = role;
    }
}
