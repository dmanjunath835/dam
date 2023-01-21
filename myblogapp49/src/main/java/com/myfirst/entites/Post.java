package com.myfirst.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts",
uniqueConstraints = {@UniqueConstraint(columnNames = {"person_name"})}
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="person_name",nullable = false)
    private String  name;
    @Column(name="person_age",nullable = false)
    private int age;
}
