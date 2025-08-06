package com.moksh.skyreserve.dto;
import com.moksh.skyreserve.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String user_first_name;
    private String user_last_name;
    private String userEmail;
    private String user_contact_no;
    private String user_password;
    private Role role;
}
