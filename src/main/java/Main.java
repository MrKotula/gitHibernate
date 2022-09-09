import javax.persistence.*;
import java.util.List;

public class Main {

    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate_main");

    public static void main(String[] args) {

        //addItems("Richard", 1567, "Good tea");
        getItems();

        MANAGER_FACTORY.close();
    }

    public static void addItems(String name, int price, String text){
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Item i1 = new Item(name, price, text);
            entityManager.persist(i1);
            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null)
                entityTransaction.rollback();
            e.printStackTrace();
        }

        finally {
            entityManager.close();
        }
    }

    public static void getItems(){
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

        String query = "SELECT i FROM Item i WHERE i.id > :id1 AND i.id < :id2 ORDER BY i.price";
        TypedQuery<Item> typedQuery = entityManager.createQuery(query, Item.class);
        List<Item> items;
        typedQuery.setParameter("id1", 2);
        typedQuery.setParameter("id2", 6);

        try {
            items = typedQuery.getResultList();
            for(Item item : items)
                System.out.println("Name: " + item.getName() + ", price: " + item.getPrice() + ", info: " + item.getInfo());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

}
