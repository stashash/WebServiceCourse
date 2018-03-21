package WebServiceCourse.hibernate.dao;

import WebServiceCourse.hibernate.dataset.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDao {

    private Session session;

    public UsersDao(Session session) {
        this.session = session;
    }

    public UserDataSet get(long id) throws HibernateException {
        return (UserDataSet) session.get(UserDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UserDataSet(name));
    }

    public void deleteUser(String name) {
        session.delete( get(getUserId(name)));
    }
}