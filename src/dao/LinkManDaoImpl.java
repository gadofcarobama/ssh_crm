package dao;

import entity.LinkMan;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao  {
    @Override
    public void add(LinkMan linkMan) {
        this.getHibernateTemplate().save(linkMan);
    }

    @Override
    public List<LinkMan> findAll() {
        List<LinkMan> list =(List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
        return list;
    }

    @Override
    //根据linkid查询一条记录的方法
    public LinkMan findOne(LinkMan linkMan) {
       return this.getHibernateTemplate().get(LinkMan.class,linkMan.getLinkid());

    }

    @Override
    //修改联系人的方法
    public void updateLinkMan(LinkMan linkMan) {
        this.getHibernateTemplate().update(linkMan);
    }

    @Override
    public List<LinkMan> moreCondition(LinkMan linkMan) {
        //多条件组合查询
        //拼接hql语句进行多条件组合查询
        String hql = "from LinkMan where 1=1 ";
        List<Object> list =new ArrayList<Object>();
        if (linkMan.getLkmName()!=null&&!"".equals(linkMan.getLkmName())){
            hql+="and lkmName=?";
            list.add(linkMan.getLkmName());
        }
        if (linkMan.getCustomer().getCid()!=null&&linkMan.getCustomer().getCid()>0){
            hql+="and customer.cid=?";
            list.add(linkMan.getCustomer().getCid());
        }
        List<LinkMan> list1 = (List<LinkMan>) this.getHibernateTemplate().find(hql,list.toArray());
        return list1;
    }
}
