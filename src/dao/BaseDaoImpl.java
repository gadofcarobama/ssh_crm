package dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class pClass;
    public BaseDaoImpl(){
        //获取当前正在运行类的class
        Class clzz=this.getClass();
        //获取父类的参数化类型
        Type type = clzz.getGenericSuperclass();
        //用Type的子接口参数化类型接收值
        ParameterizedType tType= (ParameterizedType) type;
        //得到实际类型参数
        Type[] types = tType.getActualTypeArguments();
        Class tclass= (Class) types[0];
        this.pClass=tclass;
    }
    //增加
    public void add(T t) {
     this.getHibernateTemplate().save(t);

    }

    //删除
    public void delete(T t) {
    this.getHibernateTemplate().delete(t);
    }

    //修改
    public void update(T t) {
     this.getHibernateTemplate().update(t);
    }

    //查询所有
    public List<T> findAll() {
        return (List<T>)this.getHibernateTemplate().find("from "+pClass.getSimpleName());
    }

//    根据id查询
    public T findOne(int id) {
        return (T)this.getHibernateTemplate().get(pClass,id);
    }
}
