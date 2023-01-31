import java.sql.SQLException;
import java.util.ArrayList;
import java.io.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        String url = "jdbc:mysql://localhost:3306/jdbc";
        //user default
        String user = "root";
        //your password. root is default
        String password = "root";
        personjdbc pjdbc = new personjdbc(url, user, password);

        person p = new person();
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("name:  ");
        String name= br.readLine();
        System.out.println("age : ");
        int age= Integer.parseInt(br.readLine());
        System.out.println("city : ");
        String city= br.readLine();
        System.out.println("salary : ");
        double salary= Double.parseDouble(br.readLine());

        p.setName(name);
        p.setAge(age);
        p.setCity(city);
        p.setSalary(salary);
        pjdbc.addPerson(p);
        ArrayList<person> array = pjdbc.getAllPersons();

        for (person i : array) {
            System.out.println(i.getName()+ ", your id is "+ i.getAge()+
                    ", "+ i.getSalary()+" ,"+i.getCity());
        }



      System.out.println(pjdbc.getPerson("manju").getSalary());

        pjdbc.removePerson("raj");
        for (person i : array) {
            System.out.println(i.getName()+ ", your id is "+ i.getAge()+
                    ", "+ i.getSalary()+" ,"+i.getCity());
        }
    }

}