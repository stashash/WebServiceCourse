package WebServiceCourse;

import WebServiceCourse.dbDervice.DBException;
import WebServiceCourse.dbDervice.DBService;

/**
 * Hello world!
 *
 */
class X{
    int a=10;
}
class Y extends X{
    int a=20;
}

public class App 
{
    public static void main( String[] args ){

        DBService dbService = new DBService();
        dbService.printConnectInfo();
        try {
            dbService.create();
            dbService.addUser(1,"Капрал");
            System.out.println(dbService.getUser(1).toString());
            dbService.cleanUp();
        } catch (DBException e) {
            System.out.println("Провал");
            e.printStackTrace();
        }
    }

}
