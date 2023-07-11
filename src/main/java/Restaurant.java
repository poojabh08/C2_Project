import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        if((getCurrentTime().compareTo(openingTime) >= 0) && (getCurrentTime().compareTo(closingTime) <= 0)){
            return true;
        }
        return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        RestaurantService rs = new RestaurantService();
        Restaurant r = rs.addRestaurant("abc","bengaluru",LocalTime.of(9,0,0), LocalTime.of(22,0,0));

        r.addToMenu("noodles",120);
        r.addToMenu("momos",80);
        r.addToMenu("pasta",250);
        List<Item> arr = r.getMenu();
        for(Item i: arr){
            System.out.println(i.toString());
        }

//        System.out.println("Restaurant ABC is Open: "+r.isRestaurantOpen());
//        Restaurant rst = rs.findRestaurantByName("abc");
//        System.out.println(rst.getName());
    }

}