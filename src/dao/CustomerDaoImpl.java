package dao;

import com.sun.org.apache.xml.internal.security.encryption.Transforms;
import entity.Customer;
import entity.Dict;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

//    @Override
//    public void add(Customer customer) {
//        this.getHibernateTemplate().save(customer);
//    }
//
//    @Override
//    public List<Customer> findAll() {
//        List<Customer> list=(List<Customer>) this.getHibernateTemplate().find("from Customer");
//        return list;
//    }
//
//    @Override
//    public Customer findOne(int cid) {
//       Customer customer = this.getHibernateTemplate().get(Customer.class,cid);
//       return customer;
//    }
//
//    @Override
//    public void delete(Customer c) {
//        this.getHibernateTemplate().delete(c);
//    }
//
//    @Override
//    public void update(Customer customer) {
//        this.getHibernateTemplate().update(customer);
//    }

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

    @Override
    public List<Customer> moreCondition(Customer customer) {
        //拼接hql进行多条件组合查询
//        String hql="from Customer where 1=1 ";
//        List<Object> condition=new ArrayList<Object>();
//        //判断条件是否为空，为空就不查询，不为空就查询，所有的都为空所有的就都查询
//        if (customer.getCustName()!=null&&!"".equals(customer.getCustName())){
//            //拼接hql语句
//            hql+="and custName=?";
//            //将值放入list集合中
//            condition.add(customer.getCustName());
//        }
//        if (customer.getCustLevel()!=null&&!"".equals(customer.getCustLevel())){
//            //拼接hql语句
//            hql+="and custLevel=?";
//            condition.add(customer.getCustLevel());
//        }
//        if (customer.getCustSource()!=null&&!"".equals(customer.getCustSource())){
//            hql+="and custSource=?";
//            condition.add(customer.getCustSource());
//        }
        //这里的参数需要传递数组类型所有要将condition转换成数组形式
//        List<Customer> list = (List<Customer>) this.getHibernateTemplate().find(hql,condition.toArray());
        //用离线对象进行多条件组合查询
        //获取离线对象

        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        //判断属性值是否为空
        if (customer.getCustName()!=null&&!"".equals(customer.getCustName())){
            criteria.add(Restrictions.eq("custName",customer.getCustName()));
        }
//       if (customer.getCustLevel()!=null&&!"".equals(customer.getCustLevel())){
//            criteria.add(Restrictions.eq("custLevel",customer.getCustLevel()));
//       }
       if (customer.getCustSource()!=null&&!"".equals(customer.getCustSource())){
           criteria.add(Restrictions.eq("custSource",customer.getCustSource()));
       }
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
        return list;
    }

    @Override
    public List<Dict> finAllCustLevel() {
        //查询所有级别
       List<Dict> list = (List<Dict>) this.getHibernateTemplate().find("from Dict ");
       return list;
    }

    //客户级别统计
    public List countLevel() {
        //使用底层sql语句进行复杂查询
        //获取session
        Session session = this.getSessionFactory().getCurrentSession();
        //获取SQLquery
        //先根据分组查询查询到不同级别对应的客户，再进行多表查询将级别名查到
        //分组查询的sql语句：select count(*) as num from t_customer c group by custLevel将其作为子类进行查询
        //select c.num,d.dname from (select count(*) as num from t_customer group by custLevel) c,t_dict d where c.custLevel=d.did
        SQLQuery sqlquery = session.createSQLQuery
    ("SELECT c.num,d.dname FROM (SELECT COUNT(*) AS num,custLevel FROM t_customer GROUP BY custLevel) c , t_dict d WHERE c.custLevel=d.did");
        //返回list中原本的object数组类型，将其转换为map数组，方便进行操作
        sqlquery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
        List list = sqlquery.list();
        return list;
    }

  //客户来源统计
    public List countSource() {
        //获取session
       Session session = this.getSessionFactory().getCurrentSession();
       //获取SQLQuery
       SQLQuery sqlQuery=session.createSQLQuery("SELECT COUNT(*) AS num,custSource FROM t_customer GROUP BY custSource");
       //将底层的数组转换成map形式
        sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
        List list = sqlQuery.list();
       return list;
    }

}
