package team.akina.qmoj.service;

import team.akina.qmoj.dto.QmojJudgementDTO;

public interface QmojJudgementService {
    QmojJudgementDTO queryJudgementResult(String submissionId);
}
