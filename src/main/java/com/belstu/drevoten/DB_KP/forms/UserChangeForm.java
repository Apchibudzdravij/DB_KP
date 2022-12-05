package com.belstu.drevoten.DB_KP.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangeForm {
    private String firstName;
    private String familyName;
    private String fatherName;
    private String gender;
    private String newPassword;
    private String checkNewPassword;
    private String password;

}
