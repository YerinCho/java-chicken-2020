import controller.Controller;

public class Application {
    public static void main(String[] args) {

        while (true) {
            if (!Controller.run()) {
                break;
            }
        }

    }
}
