import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LuckyTicketsCounterApplication {
    public static void main(String[] args) {
        int from = 0;
        int to = 99;
        System.out.printf("There are %s lucky tickets in range [%s; %s]", calculateCountOfLuckyTickets(from, to), from, to);
    }

    private static int calculateCountOfLuckyTickets(int from, int to) {
        int d = 10 * String.valueOf(to).length() / 2;
        Map<Integer, Integer> allPossibleSums = new HashMap<>();
        for (int i = from; i <= to/d; i++) {
            int sum = Arrays.stream(String.valueOf(i).split("\\s*")).mapToInt(Integer::parseInt).sum(); //считаем сумму цифр в числе
            allPossibleSums.put(sum, allPossibleSums.getOrDefault(sum, 0) + 1); //для каждой возможной суммы храним кол-во чисел, которые могут дать такой результат
        }
        return (int) allPossibleSums.values().stream().mapToDouble(value -> Math.pow(value, 2)).sum(); //считаем кол-во возможных комбинаций для каждой суммы как n^2 и суммируем
    }
}
