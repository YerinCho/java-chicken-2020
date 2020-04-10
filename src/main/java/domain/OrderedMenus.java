package domain;

import java.util.*;
import java.util.stream.Collectors;

public class OrderedMenus {
    private Map<Menu, OrderedMenu> orderedMenus = new HashMap<>();

    void order(Menu menu, int number) {
        if(isMenuContain(menu)){
            orderedMenus.get(menu).orderMore(number);
            return;
        }
        orderedMenus.put(menu,new OrderedMenu(menu, number));
    }

    boolean isMenuContain(Menu menu) {
        return orderedMenus.containsKey(menu);
    }



    public Map<Menu, OrderedMenu> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenus);
    }
}
