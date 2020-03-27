package lt.school.mell.diary.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.DiaryEnum;
import lt.school.mell.common.enums.UsersStateEnum;
import lt.school.mell.common.entity.Diary;
import lt.school.mell.common.entity.URelate;
import lt.school.mell.common.mapper.DiaryMapper;
import lt.school.mell.common.mapper.URelateMapper;
import lt.school.mell.common.mapper.UsersMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/17
 **/
@Service
@Transactional(readOnly = true)
@Slf4j
public class DiaryService {
    @Autowired
    DiaryMapper diaryMapper;

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    URelateMapper uRelateMapper;

    public BaseEnum<Diary> get(String id) {
        return null;
    }

    @Transactional(readOnly = false)
    public BaseEnum<Diary> saveOrUpdate(Diary entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //save
            try {
                int insert = diaryMapper.insert(entity);
                return DiaryEnum.SAVE_SUCCESS();
            } catch (Exception e) {
                e.printStackTrace();
                return DiaryEnum.OPERATION_FAILURE();
            }
        } else {
            //update
            try {
                int update = diaryMapper.update(entity, new UpdateWrapper<Diary>().lambda().eq(Diary::getId, entity.getId()));
                return DiaryEnum.UPDATE_SUCCESS();
            } catch (Exception e) {
                e.printStackTrace();
                return DiaryEnum.OPERATION_FAILURE();
            }
        }
    }

    @Transactional(readOnly = false)
    public BaseEnum<Diary> remove(String diaryId) {
        try {
            if (diaryMapper.delete(new QueryWrapper<Diary>().lambda().eq(Diary::getId, diaryId)) == 1) {
                return BaseEnum.DELETE_SUCCESS();
            } else {
                return BaseEnum.OPERATION_FAILURE();
            }
        } catch (Exception e) {
            return BaseEnum.OPERATION_FAILURE();
        }

    }

    public BaseEnum<Page<Diary>> findList(String userId, int pageNum, int pageSize) {
        PageHelper.offsetPage(pageNum, pageSize);
        List<Diary> diaryList = diaryMapper.selectList(new QueryWrapper<Diary>().lambda()
                .eq(Diary::getUserId, userId)
                .orderBy(true, true, Diary::getCreateDate));

        return BaseEnum.GET_SUCCESS((Page) diaryList);
    }


    public BaseEnum<Diary> findByDate(String createDate, String userId) {
        Diary diary = diaryMapper.selectOne(new QueryWrapper<Diary>().lambda()
                .eq(Diary::getUserId, userId)
                .and(i -> i.apply(" to_char(create_date,'yyyy-MM-dd') = {0}", createDate)));
        if (diary != null) {
            return BaseEnum.GET_SUCCESS(diary);
        } else {
            return DiaryEnum.QUERY_NULL();
        }
    }


    public BaseEnum<Page<Diary>> findListBySquare(int pageNum, int pageSize) {
        PageHelper.offsetPage(pageNum, pageSize);
        List<Diary> diaries = diaryMapper.selectList(new QueryWrapper<Diary>().lambda()
                .eq(Diary::getOpenLevel, 2)
                .or().eq(Diary::getOpenLevel, 3));

        return BaseEnum.GET_SUCCESS((Page) diaries);
    }

    public BaseEnum<Page<Diary>> findListByOtherSide(String userId, int pageNum, int pageSize) {
        URelate uRelate = uRelateMapper.selectOne(Wrappers.lambdaQuery(URelate.class)
                .eq(URelate::getUserId1, userId)
                .or().eq(URelate::getUserId2, userId));

        if (uRelate == null) {
            return UsersStateEnum.NULL_MATCH_USERS();
        } else {
            String otherSideUid = uRelate.getUserId1() == userId ? uRelate.getUserId2() : uRelate.getUserId1();
            PageHelper.offsetPage(pageNum, pageSize);
            List<Diary> diaryList = diaryMapper.selectList(new QueryWrapper<Diary>().lambda()
                    .eq(Diary::getUserId, otherSideUid).and(
                            i -> i.eq(Diary::getOpenLevel, 1).or(j -> j.eq(Diary::getOpenLevel, 3))
                    ));
            return DiaryEnum.GET_SUCCESS((Page) diaryList);
        }

    }
}
