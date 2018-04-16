package dao;

import entity.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    @Override
    public void add(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list=(List<Customer>) this.getHibernateTemplate().find("from Customer");
        return list;
    }

    @Override
    public Customer findOne(int cid) {
       Customer customer = this.getHibernateTemplate().get(Customer.class,cid);
       return customer;
    }

    @Override
    public void delete(Customer c) {
        this.getHibernateTemplate().delete(c);
    }

    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }

    @Override
    public int findCount() {
        List<Object> list= (List<Object>) this.getHibernateTemplate().find("select count (*) from Customer ");
        if (list!=null&&list.size()!=0)
        {
           Long lobj =(Long) list.get(0);
           int count =lobj.intValue();
           return count;
        }
        return 0;
    }

    @Override
    public List<Customer> findPage(int begin, int pageSize) {
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        List<Customer> list =(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
        return list;
    }

    @Override
    public List<Customer> listcondition(String customerName) {
        //第一种实现方法
//        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//         Session session = sessionFactory.getCurrentSession();
//        Query query=session.createQuery("from Customer where custName like ?");
//        query.setParameter("0","%"+customerName+"%");
//        List<Customer> list=query.list();
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.like("custName","%"+customerName+"%"));
        List<Customer> list =(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
        return list;
    }

}
