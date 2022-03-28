package sample;

public class config {
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "Mabe.131852";
    protected String dbName = "a4";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/javafx_registration?useSSL=false";
    private static final String INSERT_QUERY = "INSERT INTO registration (full_name, email_id, password) VALUES (?, ?, ?)";
}
