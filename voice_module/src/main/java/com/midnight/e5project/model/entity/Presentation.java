package com.midnight.e5project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table
@Entity
public class Presentation {

    @Id
    @Column(name = "presentation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long presentationId;

    @Column(name="title")
    private String title;

    @Column(name="create_at")
    private LocalDateTime createdAt;

    @Column(name="slides")
    @OneToMany(mappedBy = "presentation", cascade = CascadeType.ALL)
    private List<Slide> slides;

}