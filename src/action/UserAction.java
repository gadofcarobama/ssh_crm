package action;

import Service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserAction extends ActionSupport {
    private UserService userService;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public String login(){
        //属性驱动封装
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
       User user1 = userService.login(user);
      HttpSession session = ServletActionContext.getRequest().getSession();
      session.setAttribute("user",user1);
       if (user1!=null) {
           return "loginsuccess";
       }else {
           ServletActionContext.getRequest().setAttribute("msg","您输入的用户名或密码错误");
           return "login";
       }
    }

}
