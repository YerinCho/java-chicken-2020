package domain;

public class OrderedMenu {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 99;
    private Menu menu;
    private int number;

    OrderedMenu(Menu menu, int number) {
        validateMenu(menu);
        validateNumber(number);
        this.menu = menu;
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("주문수량은 1~99 입니다.");
        }
    }

    private void validateMenu(Menu menu) {
        if (!MenuRepository.menus().contains(menu)) {
            throw new IllegalArgumentException("존재하는 메뉴가 아닙니다.");
        }
    }

    public boolean isChicken() {
        return menu.isChicken();
    }

    public double calculatePrice() {
        return menu.getPrice() * number;
    }


    //Todo TestCode
    public boolean isContain(Menu menu) {
        return this.menu.equals(menu);
    }

    //Todo TestCode
    public void orderMore(int moreNumber) {
        this.number += moreNumber;
    }

    public int getNumber() {
        return number;
    }
}
