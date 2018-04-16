package Service;

import dao.UserDao;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

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
}
