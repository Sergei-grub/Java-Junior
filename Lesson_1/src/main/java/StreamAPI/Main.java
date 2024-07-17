package StreamAPI;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> myList = Arrays.asList("Привет", "мир", "!", "Я", "родился", "!");
        myList.stream().filter(s -> s.length() > 1).forEach(System.out::println);
    }
}
