package team.akina.qmoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.akina.qmoj.entity.QmojUser;
import team.akina.qmoj.entity.QmojUserKey;

@Mapper
@Repository
public interface QmojUserMapper {
    int deleteByPrimaryKey(QmojUserKey key);

    int insert(QmojUser record);

    int insertSelective(QmojUser record);

    QmojUser selectByPrimaryKey(QmojUserKey key);

    int updateByPrimaryKeySelective(QmojUser record);

    int updateByPrimaryKey(QmojUser record);
}