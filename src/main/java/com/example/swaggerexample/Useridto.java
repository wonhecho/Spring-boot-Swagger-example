package com.example.swaggerexample;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @Getter
@NoArgsConstructor
public class Useridto {
    private String id;
    private String name;
    private String email;
    public Useridto(String id, String name, String email){
        this.id = id;
        this.name=name;
        this.email=email;
    }
}
