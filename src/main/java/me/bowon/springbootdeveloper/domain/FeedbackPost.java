package me.bowon.springbootdeveloper.domain;


import lombok.Getter;

@Getter
public class FeedbackPost {
    private int prompts_id;
    private String title;
    private int feedback;

    public FeedbackPost(int prompts_id, String title, int feedback){
        this.prompts_id = prompts_id;
        this.title = title;
        this.feedback = feedback;
    }
}
