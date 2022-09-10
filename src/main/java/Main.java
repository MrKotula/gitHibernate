import javax.persistence.*;
import java.util.List;

public class Main {

    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate_main");

    public static void main(String[] args) {

        Item i = new Item("BOHDAN", 999, "Learn");
        //ItemService.editItem(1, i);
        //editItem(1, 17900, "New text for test");
        ItemService.getItem(4);
        MANAGER_FACTORY.close();
    }
}