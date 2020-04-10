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

    public static void validateTableNumber(int number) {
        if ((int) tables().stream().filter(table -> table.isNumber(number)).count() == 0) {
            throw new IllegalArgumentException("존재하지 않는 테이블 번호입니다.");
        }
    }

    public static int changeTableNumber(int input) {
        if (input <= 3) {
            return input - 1;
        }
        if (input == 5) {
            return 3;
        }
        if (input == 6) {
            return 4;
        }
        if (input == 8) {
            return 5;
        }
        throw new IllegalArgumentException("테이블번호 오류입니다.");
    }

    public static Map<Table, OrderedMenus> tableOrder() {
        return Collections.unmodifiableMap(tableOrderedMenus);
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

}
