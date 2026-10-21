import com.yourdomain.dao.DatabaseConnection;
import com.yourdomain.ui.ScenarioMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize SQLite Database and Table
        DatabaseConnection.initializeDatabase();

        // Start Interactive UI Menu
        ScenarioMenu menu = new ScenarioMenu();
        menu.start();
    }
}