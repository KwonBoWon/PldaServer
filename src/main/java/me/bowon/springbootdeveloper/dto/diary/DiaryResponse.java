package me.bowon.springbootdeveloper.dto.diary;

import lombok.Getter;
import me.bowon.springbootdeveloper.domain.Diary;

@Getter
public class DiaryResponse
{
    private final String userCode;
    private final String content;

    public DiaryResponse(Diary diary){
        this.userCode = diary.getUserCode();
        this.content = diary.getContent();
    }
}
