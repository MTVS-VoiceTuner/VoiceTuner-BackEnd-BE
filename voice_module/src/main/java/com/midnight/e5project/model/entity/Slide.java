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
public class Slide {

    @Id
    @Column(name="slide_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SlideId;

    @Column(name="slideNumber")
    private int slideNumber;

    @ManyToOne
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;

    @OneToOne(mappedBy = "slide", cascade = CascadeType.ALL)
    private Script script;

    @OneToOne(mappedBy = "slide", cascade = CascadeType.ALL)
    private Recording recording; // 슬라이드 녹음 파일

    @Column(name = "match_rate")
    private double matchRate; // 녹음 파일과 대본의 일치도 (숫자를 문자열로)
}