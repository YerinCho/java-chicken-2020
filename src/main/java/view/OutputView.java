package view;

import domain.Menu;
import domain.OrderedMenu;
import domain.OrderedMenus;
import domain.Table;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";
    private static final String ORDERED_LINE = "└ ₩ ┘";


    public static void printMain() {
        System.out.println("## 메인 화면");
        System.out.println("1 - 주문 등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 프로그램 종료");
    }

    public static void printMainErrorMessage() {
        System.out.println("메뉴입력이 유효하지 않습니다.");
    }

    public static void printTables(final List<Table> tables, List<Boolean> isOrder) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(size);
        printTableNumbers(tables);
        printBottomLine(size, isOrder);
    }

    private static void printBottomLine(int count, List<Boolean> isOrder) {
        for (int index = 0; index < count; index++) {
            printBottomLineByOrder(isOrder, index);
        }
        System.out.println();
    }

    private static void printBottomLineByOrder(List<Boolean> isOrder, int index) {
        if (isOrder.get(index)) {
            System.out.print(ORDERED_LINE);
            return;
        }
        System.out.print(BOTTOM_LINE);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(TOP_LINE);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void printPayInformation(final int tablaNumber, final OrderedMenus orderedMenus) {
        System.out.println("## 주문 내역");
        System.out.println("메뉴 수량 금액");
        OutputView.printOrderList(orderedMenus);
        System.out.printf("## %d번 테이블의 결제를 진행합니다.\n", tablaNumber);
    }

    private static void printOrderList(final OrderedMenus table) {
        for (Map.Entry<Menu, OrderedMenu> orderedMenu : table.getOrderedMenus().entrySet()) {
            System.out.println(orderedMenu.getKey().getName() + " " + orderedMenu.getValue().getNumber() + " " + orderedMenu.getKey().getPrice());
        }
    }

    public static void printPayMoney(final double money) {
        System.out.println("## 최종 결제할 금액");
        System.out.println((int) money + "원");
    }

    public static void printError(final Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printPaymentMethodErrorMessage() {
        System.out.println("잘못된 결제수단입니다.");
    }
}
