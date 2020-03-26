package lt.school.mell.square.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import lt.school.mell.common.entity.Diary;
import lt.school.mell.common.entity.DiaryComment;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.mapper.DiaryCommentMapper;
import lt.school.mell.diary.service.DiaryService;
import lt.school.mell.square.entity.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/20
 **/
@Service
public class SquareService {
    @Autowired
    DiaryCommentMapper diaryCommentMapper;
    @Autowired
    DiaryService diaryService;


    public BaseEnum<List<Square>> getSquares(String userid, int pageNum, int pageSize) {
        List<Square> squares = new ArrayList<>();
        //获取日记
        Page<Diary> diaryPages = diaryService.findListBySquare(pageNum, pageSize).getData();
        //遍历获取对应的评论
        for (Diary diary : diaryPages) {
            List<DiaryComment> diaryComments = diaryCommentMapper.selectList(Wrappers.lambdaQuery(DiaryComment.class)
                    .eq(DiaryComment::getDiaryId, diary.getId())
                    .orderByDesc(DiaryComment::getCreateDate));
            squares.add(new Square(diary, diaryComments));
        }
        return BaseEnum.GET_SUCCESS(squares);
    }


    public BaseEnum saveComment(String userId, String comment, String diaryId) {
        DiaryComment diaryComment = new DiaryComment();
        diaryComment.setCommentContent(comment);
//        StringEscapeUtils
//        StringEscapeUtils


        return null;
    }
}
