package dao;

import jakarta.persistence.EntityManager;
import model.ProgressReport;

public class ProgressReportDAO extends GenericDAO<ProgressReport> {
    public ProgressReportDAO(EntityManager entityManager) {
        super(ProgressReport.class, entityManager);
    }
    public void update(ProgressReport report) {
        try {
            entityManager.merge(report);
        } catch (Exception e) {
            throw new RuntimeException("Version conflict");
        }
    }
}
