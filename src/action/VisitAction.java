package action;

import Service.CustomerService;
import Service.UserService;
import Service.VisitService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.Customer;
import entity.User;
import entity.Visit;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class VisitAction extends ActionSupport implements ModelDriven {
    private Visit visit=new Visit();

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    CustomerService customerService;
    UserService userService;
    @Override
    public Object getModel() {
        return visit;
    }
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    VisitService visitService;
    public void setVisitService(VisitService visitService) {
        this.visitService = visitService;
    }
    public String toAdd(){
      //从数据库中将user和customer全部查询
       List<Customer> customerList = customerService.findAll();
       List<User> userList = userService.findAll();
        HttpServletRequest request= ServletActionContext.getRequest();
        request.setAttribute("listCustomer",customerList);
        request.setAttribute("listUser",userList);
       return "toAdd";
    }
    public String addVisit(){
        visitService.add(visit);
        return "addVisit";
    }
    public String toList(){
        //到数据库里查询所有拜访记录然后返回到拜访记录页面上
        List<Visit> list = visitService.findAll();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "toList";
    }
    //到组合查询的页面上
    public String toSelectVisitPage(){
        //将所有用户和客户从数据库中查询出来
       List<User> listUser = userService.findAll();
       List<Customer> listCustomer = customerService.findAll();
       HttpServletRequest request = ServletActionContext.getRequest();
       request.setAttribute("listUser",listUser);
       request.setAttribute("listCustomer",listCustomer);
       return "toSelectVisitPage";
    }
    public String moreCondition(){
      List<Visit> list = visitService.moreCondition(visit);
      ServletActionContext.getRequest().setAttribute("list",list);
      return "moreCondition";
    }
}
