package com.example.swaggerexample;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Getter
@NoArgsConstructor @Entity
@Data @Table(name = "user")
public class Useri {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
}