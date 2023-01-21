package com.myblog.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post extends AbstractClass {
 @Column(name="person_name",nullable = false)
 //@Lob
    private String name;
    @Column(name="person_age",nullable = false)
    private int age;

}
