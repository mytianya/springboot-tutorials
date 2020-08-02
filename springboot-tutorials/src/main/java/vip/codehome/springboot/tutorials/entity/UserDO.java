package vip.codehome.springboot.tutorials.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDO implements Serializable {
    String userName;
    String account;
    String passwd;
}
