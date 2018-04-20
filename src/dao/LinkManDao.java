package dao;

import entity.LinkMan;

import java.util.List;

public interface LinkManDao {
    void add(LinkMan linkMan);

    List<LinkMan> findAll();

    LinkMan findOne(LinkMan linkMan);

    void updateLinkMan(LinkMan linkMan);
}
