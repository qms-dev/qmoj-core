package team.akina.qmoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.akina.qmoj.entity.QmojLanguage;
@Mapper
@Repository
public interface QmojLanguageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QmojLanguage record);

    int insertSelective(QmojLanguage record);

    QmojLanguage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QmojLanguage record);

    int updateByPrimaryKeyWithBLOBs(QmojLanguage record);

    int updateByPrimaryKey(QmojLanguage record);
}