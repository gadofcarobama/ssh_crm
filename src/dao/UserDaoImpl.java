package dao;

import entity.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public User login(User user) {
        List<User> list=(List<User>) this.getHibernateTemplate().find("from User where username=? and password=?",
                user.getUsername(),user.getPassword());
        if (list.size()!=0&&list!=null){
            User u=list.get(0);
            return u;
        }
        return null;
    }
}
