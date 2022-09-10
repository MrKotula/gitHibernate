import java.util.List;

public class ItemService {

    private static final ItemDAO _itemDao = new ItemDAO();

    public static int add(Item item){
        return _itemDao.add(item);
    }
    public static void editItem(int id, Item item){
        _itemDao.editItem(id, item);
    }

    public static void removeItem(int id){
        _itemDao.removeItem(id);
    }

    public static List<Item> getItems(){
        return _itemDao.getItems();
    }

    public static Item getItem(int id){
        return _itemDao.getItem(id);
    }
}
