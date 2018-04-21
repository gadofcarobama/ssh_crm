package dao;

import entity.Visit;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

    //多条件组合查询
    public List<Visit> moreCondition(Visit visit) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Visit.class);
        if (visit.getCustomer().getCid()>0&&!"".equals(visit.getCustomer().getCid())){
        detachedCriteria.add(Restrictions.eq("customer.cid",visit.getCustomer().getCid()));
        }
        if (visit.getUser().getUid()>0 &&!"".equals(visit.getUser().getUid())){
            detachedCriteria.add(Restrictions.eq("user.uid",visit.getUser().getUid()));
        }
        if (visit.getVaddress()!=null&&!"".equals(visit.getVaddress())){
            detachedCriteria.add(Restrictions.eq("vaddress",visit.getVaddress()));
        }
        if (visit.getVcontent()!=null&&!"".equals(visit.getVcontent())){
            detachedCriteria.add(Restrictions.eq("vcontent",visit.getVcontent()));
        }

        //离线对象查询
        List<Visit> list = (List<Visit>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }


}
