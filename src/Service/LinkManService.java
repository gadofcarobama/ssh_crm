package Service;

import dao.LinkManDao;
import entity.LinkMan;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    public void add(LinkMan linkMan) {
        linkManDao.add(linkMan);
    }

    public List<LinkMan> findAll() {

        List<LinkMan> list = linkManDao.findAll();
        return list;
    }

    public LinkMan findOne(LinkMan linkMan) {
        LinkMan linkMan1 = linkManDao.findOne(linkMan);
        return linkMan1;
    }

    public void updateLinkMan(LinkMan linkMan) {
        linkManDao.updateLinkMan(linkMan);
    }

    public List<LinkMan> moreCondition(LinkMan linkMan) {
        List<LinkMan> list = linkManDao.moreCondition(linkMan);
        return list;
    }
}
