package sample;
import java.sql.*;

public class dataBaseHandler extends config{
    Connection dbconnection;
    public Connection getDbConnection()                                                    //метод подключения к базе данных (записывать В
            throws ClassNotFoundException, SQLException {                                  // нее, что-то считывать С нее)
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbconnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbconnection;
    }

    public ResultSet getUsers(Users user){                                             // регистрация
        ResultSet resSet = null;
        String select = "SELECT * FROM " +constants.USER_TABLE + " WHERE " +
                constants.USER_NAME + "=? AND " + constants.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, Users.getUserName());
            prst.setString(2, Users.getPassword());

            resSet = prst.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}