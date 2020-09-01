package vip.codehome.springboot.tutorials.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "User")
public class UserDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    String name;
    String account;
    String passwd;
    int age;
    boolean forbidden;
//    @Temporal(value = TemporalType.TIMESTAMP)
    LocalDateTime loginTime;
    @Transient
    String token;
}
