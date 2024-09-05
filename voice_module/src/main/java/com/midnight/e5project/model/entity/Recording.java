package com.midnight.e5project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table
@Entity
public class Recording {

    @Id
    @Column(name="recording_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="file_path")
    private String filePath;

    @OneToOne
    @JoinColumn(name = "slide_id")
    private Slide slide;

    @OneToOne(mappedBy = "recording", cascade = CascadeType.ALL)
    private Feedback feedback;
}