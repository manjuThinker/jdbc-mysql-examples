import java.sql.SQLException;
import java.util.ArrayList;

public interface persondao {


    public void addPerson(person p) throws SQLException;

    public void removePerson(String s) throws SQLException;

    public person getPerson(String name) throws SQLException;

    public ArrayList<person> getAllPersons() throws SQLException;
}
