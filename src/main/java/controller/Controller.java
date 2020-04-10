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
        OutputView.printTables(tables);
        int tableNumber = StringUtil.toInteger(InputView.inputTableNumber());
        OutputView.printMenus(menus);
    }
}
