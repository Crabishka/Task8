import util.JTableUtils;

import javax.swing.*;
import java.util.Arrays;


public class Logic {

    static int i = 0;
    static int j = 0;



    /*
    Проверка разбита на 4 подпроверки, после каждого проверяется условие, есть ли элементы для еще одной подпроверки
    1 проверка - вниз на один |
    2 проверка - по диагонали вверх-вправо /
    3 проверка - вправо на один -
    4 проверка - по диагонали вниз-влево /
    Пока проверяется только на возрастание
     */

    // может получиться ситуация, когда после 1 проверки должна идти 3 и наоборот
    // 2 и 4 проверки всегда на своих местах


    // 1 и 3 проверку можно было бы вынести в отдельный метод, но пусть будет так

    public static String Operation(int[][] a) throws ArrayIndexOutOfBoundsException {  // я думаю, как сделать так, чтобы не передавать массив каждый раз
        // boolean wasFirstCheck = false;           // я не умею обрабатывать исключения, поэтому оно не работает\
        // a.length - ряды
        // a[0].length - столбцы
        if (a == null) return "Incorrect array";
        if (a.length < 1 && a[0].length < 1) return "Incorrect array";
        if (a.length == 1 && a[0].length == 1) return "Try a larger array";
        if (a.length == 1 || a[0].length == 1) return IsThisSorted(a) ? "YES" : "NO";

        while (true) {  // написать условие как выйти из цикла при правильном решении

            if ((j == 0 || j == a[0].length - 1) && i != a.length - 1) {   // первая проверка в приоритете
                if (!firstCheckForIncrease(a)) return "NO";
            } else if (i == a.length - 1 || i == 0) {
                if (!thirdCheckForIncrease(a)) return "NO";
            }

            if (i == a.length - 1 & j == a[0].length - 1) break;

            if (!secondCheckForIncrease(a)) return "NO";

            if ((j == 0 || j == a[0].length - 1) && i != a.length - 1) {
                if (!firstCheckForIncrease(a)) return "NO";
            } else if (i == a.length - 1 || i == 0) {
                if (!thirdCheckForIncrease(a)) return "NO";
            }

            if (i == a.length - 1 & j == a[0].length - 1) break;

            if (!fourthCheckForIncrease(a)) return "NO";
        }
        i = 0;
        j = 0;
        return "YES";

    }

    // i - ряд, j - столбец
    // для каждой проверке передается 1 значение - массив (надо как-то сделать, чтобы массив можно было не передовать),
    // которая в зависимости от нумерации проверки сравнивается со значениями и возвращает логическое значение.
    // КАЖДАЯ проверка изменяет статические i и j, в которых хранятся координаты последней точки
    public static boolean firstCheckForIncrease(int[][] a) {
        if (a[i][j] >= a[i + 1][j]) {
            return false;
        }
        i++;
        return true;
    }

    public static boolean secondCheckForIncrease(int[][] a) {
        while (i > 0 && j < a[0].length - 1) {
            if (a[i][j] >= a[i - 1][j + 1]) return false;  // если прошлое больше следующего - false
            i--;
            j++;
        }
        return true;
    }

    public static boolean thirdCheckForIncrease(int[][] a) {
        if (a[0][j] >= a[0][j + 1]) return false;
        j++;
        return true;
    }

    public static boolean fourthCheckForIncrease(int[][] a) {
        while (i < a.length - 1 && j > 0) {
            if (a[i][j] >= a[i + 1][j - 1]) return false;
            i++;
            j--;
        }
        return true;
    }

    public static boolean IsThisSorted(int[][] a) {  // возрастание/убывания для стобца и для строки
        int k = 0; // счетчик для чисел
        if (a.length == 1) {
            for (int i = 0; i < a[0].length - 1; i++) {
                if (a[0][i] > a[0][i + 1]) k++;
            }
            if (k == a.length - 1) return true;
            k = 0;
            for (int i = 0; i < a[0].length - 1; i++) {
                if (a[0][i] < a[0][i + 1]) k++;
            }
            if (k == a.length - 1) return true;
            k = 0;
        }
        if (a[0].length == 1) {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i][0] > a[i + 1][0]) k++;
            }
            if (k == a.length - 1) return true;
            k = 0;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i][0] < a[i + 1][0]) k++;
            }
            if (k == a.length - 1) return true;
        }
        return false;
    }
}


