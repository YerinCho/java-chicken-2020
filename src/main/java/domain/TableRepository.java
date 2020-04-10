package domain;

import java.util.*;

public class TableRepository {
    private static final List<Table> tables = new ArrayList<>();
    private static Map<Table, OrderedMenus> tableOrderedMenus = new HashMap<>();

    static {
        tables.add(new Table(1));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(5));
        tables.add(new Table(6));
        tables.add(new Table(8));

        for (Table table : tables()) {
            tableOrderedMenus.put(table, new OrderedMenus());
        }
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

    public static void validateTableNumber(int number) {

    }
}
