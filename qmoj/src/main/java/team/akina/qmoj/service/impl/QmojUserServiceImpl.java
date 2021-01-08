package team.akina.qmoj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.akina.qmoj.entity.QmojUser;
import team.akina.qmoj.entity.QmojUserKey;
import team.akina.qmoj.mapper.QmojUserMapper;
import team.akina.qmoj.service.QmojUserService;

@Service
public class QmojUserServiceImpl implements QmojUserService {

    @Autowired
    private QmojUserMapper qmojUserMapper;

    @Override
    public QmojUser GetUserByKey(QmojUserKey key) {
        return qmojUserMapper.selectByPrimaryKey(key);
    }

    @Override
    public int InsertUserInfo(QmojUser user) {
        return qmojUserMapper.insert(user);
    }

    @Override
    public void UpdateUserInfo(QmojUser user) {
        qmojUserMapper.updateByPrimaryKey(user);
    }
}
