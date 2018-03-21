package WebServiceCourse;


import WebServiceCourse.dbDervice.DBException;
import WebServiceCourse.hibernate.dataset.UserDataSet;

public class App
{
    public static void main( String[] args ){

        WebServiceCourse.hibernate.DBService dbService = new WebServiceCourse.hibernate.DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("Капрал");
            System.out.println("Added user id: " + userId);

            userId = dbService.addUser("Hash");
            System.out.println("Added user id: " + userId);

            userId = dbService.addUser("Tom");
            System.out.println("Added user id: " + userId);

            //UserDataSet dataSet = dbService.getUser(userId);
            //System.out.println("User data set: " + dataSet);

            dbService.delUser("Капрал");

        } catch (DBException e) {
            e.printStackTrace();
        }

    }

}
