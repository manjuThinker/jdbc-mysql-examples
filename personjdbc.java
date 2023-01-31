
import java.util.ArrayList;
import  java.sql.*;

public class personjdbc implements persondao{

    private Connection connection;

    public personjdbc(String url, String user, String password) throws ClassNotFoundException, SQLException {
        //load driver communication of postgresql.
        Class.forName("com.mysql.cj.jdbc.Driver");
        //open the connection
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void addPerson(person p) throws SQLException {
        //query of postgresql
        String sql = "insert into jdbc.table2(name,age,city,salary)"
                + "values (?,?,?,?)";

        PreparedStatement ps = this.connection.prepareStatement(sql);


        // 1 = first '?'
        ps.setString(1, p.getName());
        ps.setInt(2,p.getAge());
        ps.setString(3, p.getCity());
        ps.setDouble(4,p.getSalary());

        //use execute update when the database return nothing
        ps.executeUpdate();

    }

    public void removePerson( String s) throws SQLException {
        String sql = "delete from jdbc.table2 where name = ?";
        ArrayList<person> array = getAllPersons();
        for (person p : array) {
            if(p.getName().equals(s)) {
                PreparedStatement ps = this.connection.prepareStatement(sql);
                ps.setString(1, p.getName());
                ps.executeUpdate();
                break;
            }
        }

    }

    public person getPerson(String name) throws SQLException {
        //get all persons
        ArrayList<person> array = getAllPersons();
        for (person p : array) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<person> getAllPersons() throws SQLException {
        ArrayList<person> array = new ArrayList<person>();

        //get all persons
        //query of postgresql
        ResultSet result = this.connection.prepareStatement("select * from table2").executeQuery();
        while(result.next()) {
            //new Person
            person p = new person();
            //get column of name
            p.setName(result.getString("name"));
            p.setAge(result.getInt("age"));
            p.setCity(result.getString("city"));
            p.setSalary(result.getDouble("salary"));
            array.add(p);
        }
        result.close();
        return array;

    }
}
