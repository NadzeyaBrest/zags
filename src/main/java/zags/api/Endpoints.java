package zags.api;

public class Endpoints {
    public static  String getApplications = "/getApplications";
    public static String getApplicationById ="/getApplStatus/{applicationId}";
    public static String createUser ="/sendUserRequest";
    public static String createAdmin = "/sendAdminRequest";
    public static String changeApplicationStatus = "/requestProcess";
}
