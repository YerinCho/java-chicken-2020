package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderedMenus {
    private List<OrderedMenu> orderedMenu = new ArrayList<>();

    OrderedMenus(){
    }

    public List<OrderedMenu> getOrderedMenu() {
        return Collections.unmodifiableList(orderedMenu);
    }
}
