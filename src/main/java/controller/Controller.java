package controller;

import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import util.StringUtil;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {
    private static final List<Table> tables = TableRepository.tables();
    private static final List<Menu> menus = MenuRepository.menus();

    public static void run() {
        int mainNumber = selectMain();
        if(mainNumber == 1) {
            int tableNumber = selectTable();
            int menuNumber = StringUtil.toInteger(InputView.inputMenuNumber());
        }
        if(mainNumber == 2){

        }
        if(mainNumber == 3){

        }
    }

    private static int selectMain() {
        int mainNumber;
        try {
            OutputView.printMenus(menus);
            mainNumber = StringUtil.toInteger(InputView.inputTableNumber());
        }catch (Exception e){
            mainNumber = selectTable();
        }
        return mainNumber;
    }

    private static int selectTable() {
        int tableNumber;
        try {
            OutputView.printTables(tables);
            tableNumber = StringUtil.toInteger(InputView.inputTableNumber());
        }catch (Exception e){
            tableNumber = selectTable();
        }
        return tableNumber;
    }
}
