package action;

import Service.CustomerService;
import Service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.Customer;
import entity.LinkMan;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven{
    private LinkMan linkMan=new LinkMan();
    private LinkManService linkManService;
    private CustomerService customerService;
    private File upload;
    private String uploadFileName;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    public LinkMan getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(LinkMan linkMan) {
        this.linkMan = linkMan;
    }

    public Object getModel() {
        return linkMan;
    }
    public String toAdd(){
        //查询数据库中的所有的customer返回到add页面
        List<Customer> list = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "toLinkAdd";
    }
    public String add() throws IOException {
        if (upload!=null){
            File serviceFile=new File("F:\\upload"+"/"+uploadFileName);
            FileUtils.copyFile(upload,serviceFile);
        }
        linkManService.add(linkMan);
        return "add";
    }
    public String findAll(){
        //到web.xml中配置过滤器，让本地session延迟关闭，这样查到linkman集合再返回根据里面存放的clid对
        // 其对应的customer进行查询
       List<LinkMan> list = linkManService.findAll();
       ServletActionContext.getRequest().setAttribute("list",list);
       return "findAll";
    }
    public String showLinkMan(){
         List<Customer> listCustomer =  customerService.findAll();
        HttpServletRequest request = ServletActionContext.getRequest();
         LinkMan linkMan1 = linkManService.findOne(linkMan);
         //错误，用一个request，放到一个域里，要不取不到
//         ServletActionContext.getRequest().setAttribute("linkman",linkMan1);
        request.setAttribute("listCustomer",listCustomer);;
        request.setAttribute("linkman",linkMan1);
        return "showLinkMan";
    }
    public String updateLinkMan(){
        //对联系人进行修改操作
        linkManService.updateLinkMan(linkMan);
        return "updateLinkMan";
    }
}
