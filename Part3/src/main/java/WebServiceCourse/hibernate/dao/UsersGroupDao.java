package WebServiceCourse.hibernate.dao;

import WebServiceCourse.hibernate.dataset.UsersGroup;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class UsersGroupDao {

    private Session session;

    public UsersGroupDao(Session session){
        this.session = session;
    }

    UsersGroup get(long id){
        return (UsersGroup) session.get(UsersGroup.class, id);
    }

    public long insertGroup(String name) throws HibernateException {
        return (Long) session.save(new UsersGroup(name));
    }
}
