package HomeWorkSeminar2JDK;

public class Main {
    public static void main(String[] args) {
        // Примеры использования класса Pair

        // Пример с числовыми значениями
        Pair<Integer, Integer> pair1 = new Pair<>(10, 20);
        System.out.println("Сумма чисел: " + pair1.combine());  // Output: Сумма чисел: 30.0

        // Пример со строками
        Pair<String, String> pair2 = new Pair<>("Hello, ", "world!");
        System.out.println("Конкатенация строк: " + pair2.combine());  // Output: Конкатенация строк: Hello, world!

        // Пример с несовместимыми типами
        Pair<Integer, String> pair3 = new Pair<>(10, "world!");
        System.out.println("Результат для несовместимых типов: " + pair3.combine());  // Output: Результат для несовместимых типов: null
    }
}
