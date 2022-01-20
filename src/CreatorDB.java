import java.sql.*;

public class CreatorDB {
	//DB login details
    private static final String user = "root";
    private static final String password = "123456789";
    //JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    public static void createDB() {
        try {
            //Opening local connection by using username and password
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/", user, password);
            //Create variable for managing database
            stmt = con.createStatement();
            //Creating database with name house_database
            stmt.executeUpdate("CREATE DATABASE house_database");
            /*Creating table of users with name "users"
            which contains columns password of integer to save password,
            username of string to save user login,
            access_level of integer to save access level
            and lang of string to save user language
             */
            stmt.executeUpdate("CREATE TABLE `house_database`.`users` (\n" +
                    "  `password` INT NOT NULL,\n" +
                    "  `username` VARCHAR(45) NOT NULL,\n" +
                    "  `access_level` INT NOT NULL, \n" +
                    "  `lang` VARCHAR(3) NOT NULL);");
            /*Creating table of house data with name "houses_data"
            which contains columns "date" of date to save record date,
            total_birds_h of integer to save total bird count,
            dead_birds_h of integer to save dead bird count,
            food_total of float to save total food count,
            food_cons of float to save food consumptions,
            water_total of float to save total water,
            water_cons of float to save water consumptions
            and house_id of integer to identify house
             */
            stmt.executeUpdate("CREATE TABLE `house_database`.`house_data` (\n" +
                    "  `house_id` INT NOT NULL,\n" +
                    "  `date` DATE NOT NULL,\n" +
                    "  `total_birds_h` INT NOT NULL,\n" +
                    "  `dead_birds_h` INT NOT NULL,\n" +
                    "  `food_total` FLOAT NOT NULL,\n" +
                    "  `food_cons` FLOAT NOT NULL,\n" +
                    "  `water_total` FLOAT NOT NULL,\n" +
                    "  `water_cons` FLOAT NOT NULL);");
        } catch (SQLException sqlEx) {
            //Catching exceptions and print it into console
            sqlEx.printStackTrace();
        } finally {
            try {
                //Close connection
                con.close();
            } catch (SQLException se) {
                //Catching exceptions and print it into console
                se.printStackTrace();
            }
            try {
                //Close manager
                stmt.close();
            } catch (SQLException se) {
                //Catching exceptions and print it into console
                se.printStackTrace();
            }
        }
    }
}
