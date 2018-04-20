package dao;

import entity.LinkMan;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

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
}
