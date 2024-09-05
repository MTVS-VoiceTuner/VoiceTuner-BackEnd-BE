package com.midnight.e5project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FeedbackId;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT") // 피드백 내용을 저장하는 필드
    private String content;

    @Column(name = "match_rate") // 일치도
    private double matchRate; // 녹음 파일과 대본의 일치도 (숫자를 문자열로)

    @ManyToOne
    @JoinColumn(name = "script_id")
    private Script script;

    @OneToOne
    @JoinColumn(name = "recording_id")
    private Recording recording;

    public void setContent(String content) {
        this.content = content;
    }

    public void setScript(Script script) {
        this.script = script;
    }
}
