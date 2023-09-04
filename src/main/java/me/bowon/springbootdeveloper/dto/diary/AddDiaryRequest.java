package me.bowon.springbootdeveloper.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bowon.springbootdeveloper.domain.Diary;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddDiaryRequest {

    private String userCode;
    private String content;

    public Diary toEntity(){
        return Diary.builder()
                .userCode(userCode)
                .content(content)
                .build();
    }
}
