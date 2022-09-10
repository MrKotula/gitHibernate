import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ItemDAO {

    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate_main");

    @PersistenceContext
    public static int add(Item item) {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(item);
        entityTransaction.commit();

        return item.getId();
    }

    @PersistenceContext
    public static void editItem(int id, Item item) {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Item newItem = entityManager.find(Item.class, id);
        newItem.setName(item.getName());
        newItem.setPrice(item.getPrice());
        newItem.setInfo(item.getInfo());

        entityManager.persist(newItem);
        entityTransaction.commit();
    }

    public static void removeItem(int id) {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Item item = entityManager.find(Item.class, id);
        entityManager.remove(item);
        entityTransaction.commit();
    }

    public static List<Item> getItems() {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

        String query = "SELECT i FROM Item i ";
        TypedQuery<Item> typedQuery = entityManager.createQuery(query, Item.class);
        List<Item> items = typedQuery.getResultList();
        for (Item item : items)
            System.out.println("Name: " + item.getName() + ", price: " + item.getPrice() + ", info: " + item.getInfo());
        return items;
    }

    public static Item getItem(int id){
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

        String query = "SELECT i FROM Item i WHERE i.id = :id1";
        TypedQuery<Item> typedQuery = entityManager.createQuery(query, Item.class);
        typedQuery.setParameter("id1", id);
        Item item = typedQuery.getSingleResult();
            System.out.println("Name: " + item.getName() + ", price: " + item.getPrice() + ", info: " + item.getInfo());
        return item;
    }
}