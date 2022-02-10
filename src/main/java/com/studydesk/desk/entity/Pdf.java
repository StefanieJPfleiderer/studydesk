package com.studydesk.desk.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "pdf")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Pdf {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @Column(name = "pdf_path")
    private String pdfPath;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;




}
