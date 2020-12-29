package team.akina.qmoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.akina.qmoj.entity.QmojQuestionStatus;
@Mapper
@Repository
public interface QmojQuestionStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QmojQuestionStatus record);

    int insertSelective(QmojQuestionStatus record);

    QmojQuestionStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QmojQuestionStatus record);

    int updateByPrimaryKey(QmojQuestionStatus record);
}