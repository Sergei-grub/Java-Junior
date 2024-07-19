package gb.example;

public class Main {
    static PlainInterface anInterface;

    public static void main(String[] args) {
// 1 вариант исполнения Лямбда. Добавлен интерфейс PlainInterface и в нем аннотация @FunctionalInterface
//        PlainInterface anInterface = (x, y) -> String.valueOf((x + y - x + x + y * x / y));
//        System.out.println(anInterface.action(5, 5));


// 2 вариант исполнения Лямбда. Используется интерфейс PlainInterface.
//        PlainInterface anInterface2 = (x, y) -> {
//            String str = String.valueOf(x + y);
//            return str + "!";
//        };
//        System.out.println(anInterface2.action(5, 5));
// 3 вариант исполнения Лямбда. Используется интерфейс PlainInterface.
        anInterface = (x, y) -> String.valueOf(x + y);
        print(15, 15);
        anInterface = (x, y) -> String.valueOf(x - y);
        print(15, 15);
        anInterface = (x, y) -> String.valueOf(x * y);
        print(25, 14);
    }

    private static void print(int x, int y) {
        System.out.println(anInterface.action(x, y));
    }
}
