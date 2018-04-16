package dao;

import entity.Customer;

import java.util.List;

public interface CustomerDao {

    void add(Customer customer);

    List<Customer> findAll();

    Customer findOne(int cid);

    void delete(Customer c);

    void update(Customer customer);

    int findCount();

    List<Customer> findPage(int begin, int pageSize);

    List<Customer> listcondition(String customerName);
}
