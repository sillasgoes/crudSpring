package com.sillas.sillas.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long role_id;

    private String name;

    @Getter
    public enum TypeUser {

        ADMIN(1L),
        BASIC(2L);

        private final long role_id;

        TypeUser(long role_id){
            this.role_id = role_id;
        }
    }
}

