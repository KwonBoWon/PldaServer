package me.bowon.springbootdeveloper.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity // 엔티티로 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    /*
     * diaryId  bigint
     * userCode varchar
     * content varchar
     * createTime timestamp
     * updateTime timestamp
     * tag varchar
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diaryId", updatable = false)
    private Long diaryId;

    @Column(name = "userCode", nullable = false)
    private String userCode;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Diary(Long diaryId, String userCode, String content){
        this.diaryId = diaryId;
        this.userCode = userCode;
        this.content = content;
    }
    public void update(String userCode, String content){
        this.userCode = userCode;
        this.content = content;
    }

}
