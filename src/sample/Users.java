package sample;

public class Users {
    private static String userName;
    private static String password;

    public Users() {

    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Users.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Users.password = password;
    }

    public Users(String userName, String password){
        Users.userName =userName;
        Users.password =password;
    }
}
