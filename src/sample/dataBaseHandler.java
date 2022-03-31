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

    public ResultSet loadDataFromDataBaseUser(Flyers flyer) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " +constants.FLYERS_TABLE + " WHERE " +
                constants.FLYERS_FIRSTNAME + "=? AND " + constants.FLYERS_LASTNAME + "=? AND " +
                constants.FLYERS_LOGIN + "=? AND " + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, String.valueOf(Flyers.getName()));
            prst.setString(2, String.valueOf(Flyers.getLastName()));
            prst.setString(3, String.valueOf(Flyers.getUserRole()));
            /*prst.setString(4, String.valueOf(Flyers.getEmail()));
            prst.setString(5, String.valueOf(Flyers.getOffice()));*/
            resSet = prst.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }


}