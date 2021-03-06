package lt.school.mell.square.entity;

import lombok.Data;
import lt.school.mell.common.entity.Diary;
import lt.school.mell.common.entity.DiaryComment;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/20
 **/
@Data
public class Square {
    String nickName;
    String avatar;
    Diary diary;
    List<DiaryComment> diaryCommentList;

    public Square(String nickName, String avatar, Diary diary, List<DiaryComment> diaryCommentList) {
        this.nickName = nickName;
        this.avatar = avatar;
        this.diary = diary;
        this.diaryCommentList = diaryCommentList;
    }


}
