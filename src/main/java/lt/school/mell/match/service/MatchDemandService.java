package lt.school.mell.match.service;

import lt.school.mell.common.entity.MatchDemand;
import lt.school.mell.common.mapper.MatchDemandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liangtao
 * @Date 2020/3/20
 **/
@Service
public class MatchDemandService {
    @Autowired
    MatchDemandMapper matchDemandMapper;

    public int saveOrUpdate(MatchDemand matchDemand) {
        MatchDemand demand = matchDemandMapper.selectById(matchDemand.getId());
        if (demand == null) {
            return matchDemandMapper.insert(matchDemand);
        } else {
            return matchDemandMapper.updateById(matchDemand);
        }
    }

    public int deleteByUserId(String userId){
        return matchDemandMapper.deleteById(userId);
    }

}
