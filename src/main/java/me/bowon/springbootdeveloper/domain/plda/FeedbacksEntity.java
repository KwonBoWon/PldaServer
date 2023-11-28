package me.bowon.springbootdeveloper.domain.plda;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "feedbacks", schema = "plda", catalog = "")
public class FeedbacksEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "feedback_id")
    private int feedbackId;
    @Basic
    @Column(name = "prompt_id")
    private int promptId;
    @Basic
    @Column(name = "feedback")
    private Integer feedback;
    @Basic
    @CreationTimestamp
    @Column(name = "feedback_date")
    private Timestamp feedbackDate;
    @Basic
    @Column(name = "title")
    private String title;


    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getPromptId() {
        return promptId;
    }

    public void setPromptId(int promptId) {
        this.promptId = promptId;
    }

    public Integer getFeedback() {
        return feedback;
    }

    public void setFeedback(Integer feedback) {
        this.feedback = feedback;
    }

    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbacksEntity that = (FeedbacksEntity) o;
        return feedbackId == that.feedbackId && promptId == that.promptId && Objects.equals(feedback, that.feedback) && Objects.equals(feedbackDate, that.feedbackDate) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, promptId, feedback, feedbackDate, title);
    }
}
