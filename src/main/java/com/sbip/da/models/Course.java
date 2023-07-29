package com.sbip.da.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COURSE")
//@NamedQueries({
//        @NamedQuery(name = "Course.findAllByCategory",
//        query = "select c from Course c where c.category=?1"),
//        @NamedQuery(name = "Course.findAllByCategoryOrderByName",
//        query = "select c from Course c where c.category=?1 order by c.name")
//})
public class Course {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "RATING")
    private int rating;
    @Column(name = "DESCRIPTION")
    private String description;

    public Course(String name, String category, int rating, String description) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }

    public Course() {

    }
}
