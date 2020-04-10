package controller;

import domain.*;
import util.StringUtil;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private static final List<Table> tables = TableRepository.tables();
    private static Map<Table, OrderedMenus> tableOrderedMenus = TableRepository.tableOrder();
    private static final List<Menu> menus = MenuRepository.menus();

    public static boolean run() {
        int mainNumber = selectMain();
        if (mainNumber == 1) {
            buyMenu();
            return true;
        }
        if (mainNumber == 2) {
            return true;
        }
        if (mainNumber == 3) {
            return false;
        }
        OutputView.printMainErrorMessage();
        return true;
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
        int menuNumber = MenuRepository.changeMenuNumber(menuAndNumber.get(0));
        tableOrderedMenus.get(tables.get(tableNumber)).order(menus.get(menuNumber), menuAndNumber.get(1));
    }

    private static int selectNumberToBuy() {
        int numberToBuy;
        try {
            numberToBuy = StringUtil.toInteger(InputView.inputNumberToBuyNumber());
        } catch (Exception e) {
            numberToBuy = selectNumberToBuy();
        }
        return numberToBuy;
    }

    private static int selectMenu() {
        int menuNumber;
        OutputView.printMenus(menus);
        try {
            menuNumber = StringUtil.toInteger(InputView.inputMenuNumber());
        } catch (NumberFormatException e) {
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
            OutputView.printTables(tables);
            tableNumber = StringUtil.toInteger(InputView.inputTableNumber());
            TableRepository.validateTableNumber(tableNumber);
        } catch (Exception e) {
            OutputView.printError(e);
            tableNumber = selectTable();
        }
        return tableNumber;
    }
}
