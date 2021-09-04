package com.studydesk.desk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private byte[] image;

    @Transient
    private String base64String;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
