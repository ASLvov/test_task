import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * Праздничные дни:
 * 1 января — Новый год;
 * 8 марта — Международный женский день;
 * 21 марта — Навруз;
 * 9 мая — День Памяти и Почестей;
 * 1 сентября — День Независимости;
 * 1 октября — День учителей и наставников;
 * 8 декабря — День Конституции.
 */
public class CalendarPlannerApplication {
    private static final List<String> MONTHS = Arrays.asList("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");
    private static final List<String> HOLIDAYS = Arrays.asList("01.01", "08.03", "21.03", "09.05", "01.09", "01.10",
            "08.12");
    private static final String BORDER = "====================";

    public static void main(String[] args) {
        System.out.print("Введите год: ");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line.matches("^[1-9]\\d*$")) {
            System.out.printf("Производственный календарь на %s год:%n", line);
            printCalendarToStdout(Integer.parseInt(line));
        } else {
            System.out.printf("Введенное значение '%s' не является корректным годом%n", line);
        }
    }

    private static void printCalendarToStdout(int year) {
        String[] stateOfTheDayLine = new String[7];
        Arrays.fill(stateOfTheDayLine, "  ");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        while (calendar.get(Calendar.YEAR) == year) {
            if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
                printMonth(calendar.get(Calendar.MONTH));
            }
            int dayInWeekNumber = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
            stateOfTheDayLine[dayInWeekNumber] = getStateOfTheDay(calendar, dayInWeekNumber);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                printLineAndSetToDefault(stateOfTheDayLine);
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
                printLineAndSetToDefault(stateOfTheDayLine);
                System.out.println(BORDER);
            }
        }
    }

    private static String getStateOfTheDay(Calendar calendar, int dayInWeekNumber) {
        if (dayInWeekNumber == 6 || dayInWeekNumber == 5) {
            return " 0";
        }
        String day = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2, "0");
        String month = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2, "0");
        if (HOLIDAYS.contains(day + "." + month)) {
            return " 0";
        }
        return " 1";
    }

    private static void printLineAndSetToDefault(String[] stringArray) {
        System.out.println(String.join(" ", stringArray));
        Arrays.fill(stringArray, "  ");
    }

    private static void printMonth(int monthNumber) {
        System.out.println();
        System.out.println(MONTHS.get(monthNumber));
        System.out.println(BORDER);
    }
}
