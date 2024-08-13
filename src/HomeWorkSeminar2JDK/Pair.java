package HomeWorkSeminar2JDK;

// Определяем обобщенный класс Pair
public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    // Конструктор
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    // Геттеры
    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    // Метод combine, который возвращает сумму или конкатенацию
    public Object combine() {
        if (first instanceof Number && second instanceof Number) {
            // Оба значения являются числами
            double num1 = ((Number) first).doubleValue();
            double num2 = ((Number) second).doubleValue();
            return num1 + num2;
        } else if (first instanceof String && second instanceof String) {
            // Оба значения являются строками
            return (String) first + (String) second;
        } else {
            // Несовместимые типы
            return null;
        }
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", second=" + second + '}';
    }
}