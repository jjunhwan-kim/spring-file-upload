package com.example.fileupload.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String downloadPath;

    private String analyzePath;

    @Enumerated(EnumType.STRING)
    private SourceStatus status;
}
