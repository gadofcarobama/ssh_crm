package Service;

import dao.UserDao;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    public User login(User user) {
        User user1=userDao.login(user);
        return user1;
    }
    //查询所有用户方法
    public List<User> findAll(){
        List<User> list = userDao.findAll();
        return list;
    }
}
