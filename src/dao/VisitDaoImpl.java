package dao;

import entity.Visit;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

    public void add(Visit visit) {
        this.getHibernateTemplate().save(visit);
    }

    @Override
    public List<Visit> findAll() {
        List<Visit> list = (List<Visit>) this.getHibernateTemplate().find("from Visit");
        return list;
    }
}
