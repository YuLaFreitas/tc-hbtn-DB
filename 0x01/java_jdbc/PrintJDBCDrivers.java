import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class PrintJDBCDrivers {
    public static void main(String[] args) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null;
        do{
            driver = drivers.nextElement();
            System.out.println("DRIVE: " + driver.getClass().getCanonicalName() + " VERSÃO: " + driver.getMajorVersion());
        }while (drivers.hasMoreElements());

    }
}
