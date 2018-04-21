package action;

import Service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.Customer;
import entity.Dict;
import entity.PageBean;
import org.apache.struts2.ServletActionContext;

import java.rmi.server.ServerCloneException;
import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven {
    private CustomerService customerService;
    //模型驱动封装要new一个新的实例
    private Customer customer = new Customer();
    //模型驱动封装
    public Object getModel() {
        return customer;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    public String toAdd(){
        //调用方法从数据字典中查询所有的级别
        List<Dict> list = customerService.finAllCustLevel();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "toAdd";
    }
    public String add(){
        customerService.add(customer);
        return "add";
    }
    private List<Customer> list;
    //生成变量的get方法
    public List<Customer> getList() {
        return list;
    }
    public String list(){
      list = customerService.findAll();
       //放入值栈
//        ServletActionContext.getRequest().setAttribute("list",list);

       return "list";
    }
    //模型驱动封装，只封装一个cid
    public String delete(){
        int cid=customer.getCid();
        //根据cid查询判断是否为空，为空的话调用方法删除
          Customer c=customerService.findOne(cid);
        //判断cid是否为空
        if (c!=null){
        customerService.delete(c);
        }
        return "delete";
    }
    public String showCustomer(){
        int cid = customer.getCid();
        Customer customer1=customerService.findOne(cid);
        ServletActionContext.getRequest().setAttribute("customer",customer1);
        return "showCustomer";
    }
   public String update(){
        customerService.update(customer);
        return "update";
   }
   //使用属性封装
    private Integer currentPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String listpage(){

       PageBean pageBean = customerService.listpage(currentPage);
       ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
       return "listpage";
   }
   //条件查询所有
   public String listcondition(){
       String customerName = customer.getCustName();
       if (customerName!=null&&!"".equals(customerName)){
           List<Customer> list =  customerService.listcondition(customerName);
           ServletActionContext.getRequest().setAttribute("list",list);
       }else {
           list();
       }
       return "listcondition";
   }
   //跳转到多条件组合查询页面
    public String toSelectCustomerPage(){
       return "toSelectCustomerPage";

    }
  //多条件组合查询
  public String moreCondition(){
     List<Customer> list = customerService.moreCondition(customer);
     ServletActionContext.getRequest().setAttribute("list",list);
     return "moreCondition";
  }
  //客户级别统计
    public String countLevel(){
     List list = customerService.countLevel();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "countLevel";
    }
    //客户来源统计
    public String countSource(){
        List list = customerService.countSource();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "countSource";
    }

}
