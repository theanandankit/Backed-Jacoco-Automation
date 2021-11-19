package com.Rakuten.Automation.Model;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String username;

    private String firstName;

    private String lastName;

    private String gender;

    private String password;
}
