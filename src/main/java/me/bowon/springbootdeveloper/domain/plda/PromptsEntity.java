package me.bowon.springbootdeveloper.domain.plda;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prompts", schema = "plda", catalog = "")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromptsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prompt_id")
    private int prompt_id;

    @Column(name = "feedback", nullable = false)
    private int feedback;

    @Column(name = "prompt", nullable = false)
    private String prompt;

    public void addFeedback(int feedback){
        this.feedback += feedback;
    }
}
