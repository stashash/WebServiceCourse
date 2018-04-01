package WebServiceCourse.hibernate.dataset;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_group")
public class UsersGroup implements Serializable {
    private static final long serialVersionUID = -7706689714326132797L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersGroup() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersGroup(long id, String name) {
        setName(name);
        setGroupId(id);
    }
    public UsersGroup(String name) {
        setName(name);
        setGroupId(-1);
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UsersGroup{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
