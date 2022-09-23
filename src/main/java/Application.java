import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        System.out.printf("There are %s lucky tickets in range [000 000; 999 999]", calculateCountOfLuckyTickets());
    }

    private static int calculateCountOfLuckyTickets() {
        Map<Integer, Integer> allPossibleSums = new HashMap<>();
        for (int i = 0; i <= 999; i++) {
            int sum = Arrays.stream(String.valueOf(i).split("\\s*")).mapToInt(Integer::parseInt).sum(); //считаем сумму цифр в числе
            allPossibleSums.put(sum, allPossibleSums.getOrDefault(sum, 0) + 1); //для каждой возможной суммы храним кол-во чисел, которые могут дать такой результат
        }
        return (int) allPossibleSums.values().stream().mapToDouble(value -> Math.pow(value, 2)).sum(); //считаем кол-во возможных комбинаций для каждой суммы как n^2 и суммируем
    }
}
