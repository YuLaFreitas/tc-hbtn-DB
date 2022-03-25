import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSQLite {
    public static void main(String[] args){
        initConnection();
    }

    public static void initConnection(){
        Connection cnn = null;

        try {
            cnn = DriverManager.getConnection("jdbc:sqlite:sqlite_database_2022.db");
            System.out.println(cnn);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if(cnn != null) {
                    cnn.close();
                }
                else {
                    System.out.println("Probelam com a conexão!");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
