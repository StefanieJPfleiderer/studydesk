package com.studydesk.desk.entity;

import lombok.*;

import javax.persistence.*;

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

    @Transient
    private String base64String;

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
