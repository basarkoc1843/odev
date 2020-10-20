package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users",indexes = {@Index(name = "idx_username",columnList = "uname")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uname",length = 200,unique = true)
    private String username;

    @Column(name = "name_surname",length = 100)
    private String nameSurname;

    @Column(name = "pws",length = 100)
    private String password;

    @Column(name = "email",length = 100)
    private String email;
}
