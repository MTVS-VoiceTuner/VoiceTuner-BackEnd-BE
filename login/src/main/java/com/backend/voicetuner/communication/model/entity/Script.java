package com.backend.voicetuner.communication.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table
@Entity
public class Script {

    @Id
    @Column(name = "script_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scriptId;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToOne
    @JoinColumn(name = "slide_id")
    private Slide slide;

    @Column(name="is_origin")
    private boolean isOrigin;

    @Column(name = "feedbackList")
    @OneToMany(mappedBy = "script", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList;

    public void setContent(String scriptContent) {
        this.content = scriptContent;
    }

    public void setOrigin(boolean origin) {
        isOrigin = origin;
    }

    public void setOriginal(boolean b) {

    }
}
