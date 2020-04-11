package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderedMenus {

    private Map<Menu, OrderedMenu> orderedMenus = new HashMap<>();

    public void order(Menu menu, int number) {
        if (isMenuContain(menu)) {
            orderedMenus.get(menu).orderMore(number);
            return;
        }
        orderedMenus.put(menu, new OrderedMenu(menu, number));
    }

    boolean isMenuContain(Menu menu) {
        return orderedMenus.containsKey(menu);
    }

    public Map<Menu, OrderedMenu> getOrderedMenus() {
        return Collections.unmodifiableMap(orderedMenus);
    }
}
