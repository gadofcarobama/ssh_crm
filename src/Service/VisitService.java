package Service;

import dao.VisitDao;
import entity.Visit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class VisitService {
    VisitDao visitDao;

    public void setVisitDao(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    public void add(Visit visit) {
        visitDao.add(visit);
    }

    public List<Visit> findAll() {
        List<Visit> list = visitDao.findAll();
        return list;
    }

    public List<Visit> moreCondition(Visit visit) {
        List<Visit> list = visitDao.moreCondition(visit);
        return list;
    }
}
