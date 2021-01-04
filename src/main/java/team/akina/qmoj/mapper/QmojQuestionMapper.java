package team.akina.qmoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;
@Mapper
@Repository
public interface QmojQuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QmojQuestionWithBLOBs record);

    int insertSelective(QmojQuestionWithBLOBs record);

    QmojQuestionWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QmojQuestionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(QmojQuestionWithBLOBs record);

    int updateByPrimaryKey(QmojQuestion record);
}