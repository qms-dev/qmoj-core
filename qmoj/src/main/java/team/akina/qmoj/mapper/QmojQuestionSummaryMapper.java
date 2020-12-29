package team.akina.qmoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.akina.qmoj.entity.QmojQuestionSummary;

import java.util.List;

@Mapper
@Repository
public interface QmojQuestionSummaryMapper {
    List<QmojQuestionSummary> getAll();
}