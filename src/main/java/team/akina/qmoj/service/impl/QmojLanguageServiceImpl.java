package team.akina.qmoj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.akina.qmoj.entity.QmojLanguage;
import team.akina.qmoj.mapper.QmojLanguageMapper;
import team.akina.qmoj.service.QmojLanguageService;

/**
 * @author chenbo
 * @date 2020/12/4 0:00
 */
@Service
public class QmojLanguageServiceImpl implements QmojLanguageService {
    @Autowired
    private QmojLanguageMapper qmojLanguageMapper;

    @Override
    public QmojLanguage findQmojLanguageById(Long id) {
        return qmojLanguageMapper.selectByPrimaryKey(id);
    }
}
