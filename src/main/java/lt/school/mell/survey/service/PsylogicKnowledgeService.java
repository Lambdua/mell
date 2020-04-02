package lt.school.mell.survey.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lt.school.mell.common.entity.PsychologicalKnowledge;
import lt.school.mell.common.entity.PsylogicKnowledgeType;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.mapper.PsychologicalKnowledgeMapper;
import lt.school.mell.common.mapper.PsylogicKnowledgeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/4/1
 **/
@Service
public class PsylogicKnowledgeService {
    @Autowired
    PsychologicalKnowledgeMapper psychologicalKnowledgeMapper;
    @Autowired
    PsylogicKnowledgeTypeMapper knowledgeTypeMapper;


    public BaseEnum getPsychologicalKnowledgeList() {
        List<PsylogicKnowledgeType> psylogicKnowledgeTypes = knowledgeTypeMapper.selectList(null);

        for (PsylogicKnowledgeType psylogicKnowledgeType : psylogicKnowledgeTypes) {
            List<PsychologicalKnowledge> list = psychologicalKnowledgeMapper.selectList(Wrappers.lambdaQuery(PsychologicalKnowledge.class)
                    .eq(PsychologicalKnowledge::getType, psylogicKnowledgeType.getType()));

            psylogicKnowledgeType.setPsychologicalKnowledgeList(list);
        }
        return BaseEnum.GET_SUCCESS(psylogicKnowledgeTypes);
    }
}
