package team.akina.qmoj.service;

import team.akina.qmoj.entity.QmojUser;
import team.akina.qmoj.entity.QmojUserKey;

public interface QmojUserService {
    QmojUser GetUserByKey(QmojUserKey key);
    int InsertUserInfo(QmojUser user);
    void UpdateUserInfo(QmojUser user);

}
