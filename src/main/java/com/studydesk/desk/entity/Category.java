package com.studydesk.desk.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "category")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Category implements Comparable<Category> {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private byte[] image;

    @NonNull
    private String fileName;

    @Transient
    private String base64String;

    @OneToMany(mappedBy = "category")
    private List<Topic> topics;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Category o) {
        return this.getId().compareTo(o.getId());
    }
}
