package controller;

import domain.*;
import util.StringUtil;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Controller {
    private static final List<Table> tables = TableRepository.tables();
    private static Map<Table, OrderedMenus> tableOrderedMenus = TableRepository.tableOrder();
    private static final List<Menu> menus = MenuRepository.menus();
    private static final List<Boolean> isOrder = new ArrayList<>(Arrays.asList(false, false, false, false, false, false));

    public static boolean run() {
        int mainNumber = selectMain();
        if (mainNumber == 1) {
            buyMenu();
            return true;
        }
        if (mainNumber == 2) {
            pay();
            return true;
        }
        if (mainNumber == 3) {
            return false;
        }
        OutputView.printMainErrorMessage();
        return true;
    }

    private static void pay() {
        try {
            int tableNumber = selectTable();
            checkTableOrdered(TableRepository.changeTableNumber(tableNumber));
            OrderedMenus orderedMenus = payTable(tableNumber);
            clear(tableNumber, orderedMenus);
        } catch (IllegalArgumentException e) {
            OutputView.printPaymentMethodErrorMessage();
        } catch (UnsupportedOperationException e) {
            OutputView.printError(e);
        }
    }

    private static OrderedMenus payTable(int tableNumber) {
        OrderedMenus orderedMenus = tableOrderedMenus.get(tables.get(TableRepository.changeTableNumber(tableNumber)));
        OutputView.printPayInformation(tableNumber, orderedMenus);
        PaymentMethod paymentMethod = PaymentMethod.getPaymentMethodByNumber(selectInputPaymentMethod());
        double money = Payment.calculatePay(orderedMenus.getOrderedMenus(), paymentMethod);
        OutputView.printPayMoney(money);
        return orderedMenus;
    }

    private static void checkTableOrdered(int changeTableNumber) {
        if (!isOrder.get(changeTableNumber)) {
            throw new UnsupportedOperationException("주문이 이루어지지 않은 테이블입니다.");
        }
    }

    private static void clear(int tableNumber, OrderedMenus orderedMenus) {
        orderedMenus.clearOrder();
        isOrder.set(TableRepository.changeTableNumber(tableNumber), false);
    }


    private static void buyMenu() {
        int tableNumber = selectTable();
        List<Integer> menuAndNumber = new ArrayList<>();
        menuAndNumber.add(selectMenu());
        menuAndNumber.add(selectNumberToBuy());
        addMenu(tableNumber, menuAndNumber);
    }

    private static void addMenu(int tableNumber, List<Integer> menuAndNumber) {
        tableNumber = TableRepository.changeTableNumber(tableNumber);
        try {
            int menuNumber = MenuRepository.changeMenuNumber(menuAndNumber.get(0));
            tableOrderedMenus.get(tables.get(tableNumber)).order(menus.get(menuNumber), menuAndNumber.get(1));
            isOrder.set(tableNumber, true);
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }

    private static int selectInputPaymentMethod() {
        int paymentMethod;
        try {
            paymentMethod = StringUtil.toInteger(InputView.inputPaymentMethod());
        } catch (Exception e) {
            OutputView.printError(e);
            paymentMethod = selectInputPaymentMethod();
        }
        return paymentMethod;
    }

    private static int selectNumberToBuy() {
        int numberToBuy;
        try {
            numberToBuy = StringUtil.toInteger(InputView.inputNumberToBuyNumber());
        } catch (Exception e) {
            OutputView.printError(e);
            numberToBuy = selectNumberToBuy();
        }
        return numberToBuy;
    }

    private static int selectMenu() {
        int menuNumber;
        OutputView.printMenus(menus);
        try {
            menuNumber = StringUtil.toInteger(InputView.inputMenuNumber());
            MenuRepository.validateMenuNumber(menuNumber);
        } catch (Exception e) {
            OutputView.printError(e);
            menuNumber = selectMenu();
        }
        return menuNumber;
    }

    public static int selectMain() {
        int mainNumber;
        try {
            OutputView.printMain();
            mainNumber = StringUtil.toInteger(InputView.inputMainNumber());
        } catch (NumberFormatException e) {
            OutputView.printError(e);
            mainNumber = selectMain();
        }
        return mainNumber;
    }

    private static int selectTable() {
        int tableNumber;
        try {
            OutputView.printTables(tables, isOrder);
            tableNumber = StringUtil.toInteger(InputView.inputTableNumber());
            TableRepository.validateTableNumber(tableNumber);
        } catch (Exception e) {
            OutputView.printError(e);
            tableNumber = selectTable();
        }
        return tableNumber;
    }
}
