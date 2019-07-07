package com.bitcamp.web.domain;

import java.sql.Timestamp;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * UserDTO
 */
@Data @Lazy @Component
public class UserDTO {

    private String emailId, userName, password, photo;
    private Timestamp signupDate;

}