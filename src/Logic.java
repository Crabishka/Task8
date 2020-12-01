import util.JTableUtils;

import javax.swing.*;
import java.util.Arrays;


public class Logic {

    int i = 0;
    int j = 0;

    boolean IsSnakeIncrease = true;
    boolean IsSnakeDecrease = true;

    // i - rows = a.length
    // j - columns = a[0].length


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


    public String Operation(int[][] a) throws ArrayIndexOutOfBoundsException {
        // я думаю, как сделать так, чтобы не передавать массив каждый раз
        // boolean wasFirstCheck = false;           // я не умею обрабатывать исключения, поэтому оно не работает\
        // a.length - ряды
        // a[0].length - столбцы
        if (a == null) return "Incorrect array";
        if (a.length < 1 && a[0].length < 1) return "Incorrect array";
        if (a.length == 1 && a[0].length == 1) return "Try a larger array";
        if (a.length == 1 || a[0].length == 1) return IsThisSorted(a) ? "YES" : "NO";

        while (true) {  // написать условие как выйти из цикла при правильном решении

            if ((j == 0 || j == a[0].length - 1) && i != a.length - 1) {   // первая проверка в приоритете
                if (!firstCheck(a, "INCREASE")) {
                    IsSnakeIncrease = false;
                    break;
                }
            } else if (i == a.length - 1 || i == 0) {
                if (!thirdCheck(a, "INCREASE")) {
                    IsSnakeIncrease = false;
                    break;
                }
            }

            if (i == a.length - 1 & j == a[0].length - 1) break;

            if (!secondCheck(a, "INCREASE")) {
                IsSnakeIncrease = false;
                break;
            }

            if ((j == 0 || j == a[0].length - 1) && i != a.length - 1) {
                if (!firstCheck(a, "INCREASE")) {
                    IsSnakeIncrease = false;
                    break;
                }
            } else if (i == a.length - 1 || i == 0) {
                if (!thirdCheck(a, "INCREASE")) {
                    IsSnakeIncrease = false;
                    break;
                }
            }

            if (i == a.length - 1 & j == a[0].length - 1) break;

            if (!fourthCheck(a, "INCREASE")) {
                IsSnakeIncrease = false;
                break;
            }
        }
        if (IsSnakeIncrease) return "YES";
        i = 0;
        j = 0;
        while (true) {  // написать условие как выйти из цикла при правильном решении

            if ((j == 0 || j == a[0].length - 1) && i != a.length - 1) {   // первая проверка в приоритете
                if (!firstCheck(a, "DECREASE")) {
                    IsSnakeDecrease = false;
                    break;
                }
            } else if (i == a.length - 1 || i == 0) {
                if (!thirdCheck(a, "DECREASE")) {
                    IsSnakeDecrease = false;
                    break;
                }
            }

            if (i == a.length - 1 & j == a[0].length - 1) break;

            if (!secondCheck(a, "DECREASE")) {
                IsSnakeDecrease = false;
                break;
            }

            if ((j == 0 || j == a[0].length - 1) && i != a.length - 1) {
                if (!firstCheck(a, "DECREASE")) {
                    IsSnakeDecrease = false;
                    break;
                }
            } else if (i == a.length - 1 || i == 0) {
                if (!thirdCheck(a, "DECREASE")) {
                    IsSnakeDecrease = false;
                    break;
                }
            }

            if (i == a.length - 1 & j == a[0].length - 1) break;

            if (!fourthCheck(a, "DECREASE")) {
                IsSnakeDecrease = false;
                break;
            }
        }
        if (IsSnakeDecrease) return "YES";
        return "NO";
    }

    // i - ряд, j - столбец
    // для каждой проверке передается 1 значение - массив (надо как-то сделать, чтобы массив можно было не передовать),
    // которая в зависимости от нумерации проверки сравнивается со значениями и возвращает логическое значение.
    // КАЖДАЯ проверка изменяет статические i и j, в которых хранятся координаты последней точки

    //

    public boolean firstCheck(int[][] a, String type) {
        if (type.equals("INCREASE")) {
            if (a[i][j] >= a[i + 1][j]) {
                return false;
            }
            i++;
        } else {
            if (a[i][j] <= a[i + 1][j]) {
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean secondCheck(int[][] a, String type) {
        if (type.equals("INCREASE")) {
            while (i > 0 && j < a[0].length - 1) {
                if (a[i][j] >= a[i - 1][j + 1]) return false;  // если прошлое больше следующего - false
                i--;
                j++;
            }

        } else {
            while (i > 0 && j < a[0].length - 1) {
                if (a[i][j] <= a[i - 1][j + 1]) return false;
                i--;
                j++;
            }

        }
        return true;
    }

    public boolean thirdCheck(int[][] a, String type) {
        if (type.equals("INCREASE")) {
            if (a[0][j] >= a[0][j + 1]) return false;
            j++;
        } else {
            if (a[0][j] <= a[0][j + 1]) return false;
            j++;
        }
        return true;
    }

    public boolean fourthCheck(int[][] a, String type) {
        if (type.equals("INCREASE")) {
            while (i < a.length - 1 && j > 0) {
                if (a[i][j] >= a[i + 1][j - 1]) return false;
                i++;
                j--;
            }
        }
        else {
            while (i < a.length - 1 && j > 0) {
                if (a[i][j] <= a[i + 1][j - 1]) return false;
                i++;
                j--;
            }
        }
        return true;
    }

    public boolean IsThisSorted(int[][] a) {  // возрастание/убывания для стобца и для строки
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

    /* МУСОР

    // в основе алгоритма лежит проход по диагоналям, параллельные побочной (включая ее)
    // и проход по отрезочкам края, в зависимости от четности высоты и ширины

    public boolean isSnakeIncrease(int[][] a){
    for (int i = 1; i < a[0].length; i++){
        if (!IsDiagonalIncrease(a,i)) return false;
    }
    for (int i = 1; i <= a.length -2; i++ ){
        if ()
    }
    return false;
    }

    public boolean isSnakeDecrease(int[][] a){

    }

    public String Operation(int[][] a){
        return (isSnakeDecrease(a)||isSnakeIncrease(a)) ? "YES" : "NO";
    }

    public boolean IsDiagonalIncrease(int[][] a, int column){
    int i = 0;
    while (column > 0){
        if (a[i+1][column-1] > a[i][column]) return false;
        i++;
        column--;
    }
    return true;
    }

    public boolean IsBottomDiagonalIncrease(int[][] a, int row){

    }

    public boolean IsDiagonalDecrease(int[][] a, int column){


    }
*/
}
