package Service;

import dao.CustomerDao;
import entity.Customer;
import entity.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void add(Customer customer) {
        customerDao.add(customer);
    }

    public List<Customer> findAll() {
        List<Customer> list=customerDao.findAll();
        return list;
    }



    public Customer findOne(int cid) {
       Customer customer = customerDao.findOne(cid);
       return customer;
    }

    public void delete(Customer c) {
        customerDao.delete(c);
    }

    public void update(Customer customer) {
        customerDao.update(customer);
    }

    public PageBean listpage(Integer currentPage) {
        PageBean pageBean=new PageBean();
        //封装当前页
        pageBean.setCurrentPage(currentPage);
        //封装总记录数
        int totalCount=customerDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装每页记录数
        int pageSize=5;
        pageBean.setPageSize(pageSize);
        //封装开始位置
        int begin = (currentPage-1)*pageSize;
        //封装总页数
        int totalPage;
        if (totalCount%pageSize==0) {
             totalPage = totalCount /pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        pageBean.setTotalPage(totalPage);
         //封装customer集合
         List<Customer> list =  customerDao.findPage(begin,pageSize);
         pageBean.setList(list);
        return pageBean;
    }

    public List<Customer> listcondition(String customerName) {
      List<Customer> list =  customerDao.listcondition(customerName);
      return list;
    }
}
