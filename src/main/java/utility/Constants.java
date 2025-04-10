package utility;

public final class Constants {
    // Application URLs
    public static final String BASE_URL = "https://example.com";
    public static final String LOGIN_URL = BASE_URL + "/login";
    
    // Login Credentials
    public static final String VALID_USERNAME = "testuser";
    public static final String VALID_PASSWORD = "password123";

    // Expected Messages
    public static final String LOGIN_SUCCESS_MESSAGE = "Welcome to the Dashboard!";
    public static final String LOGIN_FAILURE_MESSAGE = "Invalid username or password.";

    // File Paths
    public static final String EXCEL_PATH = "C:\\Users\\darek\\git\\Selenium-Java\\HybridFramework\\testdata\\data1.xlsx";
    public static final String CONFIG_PATH = "src/test/resources/config.properties";
    
    public static enum TestType{NEGATIVE,POSITIVE};
    
}
