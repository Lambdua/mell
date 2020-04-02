package lt.school.mell.square.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lt.school.mell.common.entity.Diary;
import lt.school.mell.common.entity.DiaryComment;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.mapper.DiaryCommentMapper;
import lt.school.mell.common.mapper.DiaryMapper;
import lt.school.mell.common.mapper.UsersMapper;
import lt.school.mell.square.entity.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    DiaryMapper diaryMapper;
    @Autowired
    UsersMapper usersMapper;


    public BaseEnum<List<Square>> getSquares(int pageNum, int pageSize) {
        Page<Square> squares = new Page<>();
        //获取日记
        PageHelper.offsetPage(pageNum, pageSize);
        Page<Diary> diaries = (Page<Diary>) diaryMapper.selectList(new QueryWrapper<Diary>().lambda()
                .eq(Diary::getOpenLevel, 2)
                .or().eq(Diary::getOpenLevel, 3)
                .orderByDesc(Diary::getStarCount));
        //遍历获取对应的评论
        Users users;
        for (Diary diary : diaries) {
            List<DiaryComment> diaryComments = diaryCommentMapper.selectList(Wrappers.lambdaQuery(DiaryComment.class)
                    .eq(DiaryComment::getDiaryId, diary.getId())
                    .orderByDesc(DiaryComment::getCreateDate));

            users = usersMapper.selectById(diary.getUserId());
            squares.add(new Square(users.getNickName(), users.getAvatar(), diary, diaryComments));
        }
        return BaseEnum.GET_SUCCESS(squares);
    }


    public BaseEnum saveComment(String userId, String comment, String diaryId) {
        DiaryComment diaryComment = new DiaryComment();
        diaryComment.setCommentContent(comment);
        return null;
    }
}
