package sample;

public class Flyers {
    private static String name;
    private static String lastname;
    private static String userrole;
    private static String email;
    private static String office;

    public Flyers(String name, String lastname, String userrole, String email, String office) {
        Flyers.name =name;
        Flyers.lastname =lastname;
        Flyers.userrole =userrole;
        Flyers.email =email;
        Flyers.office =office;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Flyers.name = name;
    }

    public static String getLastName() {
        return lastname;
    }

    public static void setLastName(String lastName) {
        Flyers.lastname = lastName;
    }

    public static String getUserRole() {
        return userrole;
    }

    public static void setUserRole(String userRole) {
        Flyers.userrole = userRole;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Flyers.email = email;
    }

    public static String getOffice() {
        return office;
    }

    public static void setOffice(String office) {
        Flyers.office = office;
    }
}
