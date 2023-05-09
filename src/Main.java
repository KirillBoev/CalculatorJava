import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Введите матеманическое выражение без пробелов:");
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws Exception {
        ToRoman transformRome = new ToRoman();
        String[] operation = {"+", "-", "*", "/"};
        String[] regexOperation = {"\\+", "-", "\\*", "/"};

        int j = -1;
        for (int i = 0; i < operation.length; i++) {
            if (input.contains(operation[i])) {
                j = i;
                break;
            }
        }
        if (j == -1) {
            throw new Exception("Неверное матаматическое выражение, попробуйте снова");
        }

        String[] arr = input.split(regexOperation[j]);
        if (arr.length != 2) {
            throw new Exception("Неверный формат математической операции - два операнда и один оператор");
        }
        if (transformRome.isRoman(arr[0]) == transformRome.isRoman(arr[1])) {
            int a, b;
            boolean isRoman = transformRome.isRoman(arr[0]);
            if (isRoman) {
                a = transformRome.ToInt(arr[0]);
                b = transformRome.ToInt(arr[1]);
            } else {
                a = Integer.parseInt(arr[0]);
                b = Integer.parseInt(arr[1]);
            }
            int result;
            if ((a > 0 && b > 0) && (a <= 10 && b <= 10)) {
                switch (operation[j]) {
                    case "+":
                        result = (a + b);
                        break;
                    case "-":
                        result = (a - b);
                        break;
                    case "*":
                        result = (a * b);
                        break;
                    default:
                        result = (a / b);
                        break;
                }

                if (isRoman) {
                    if (result > 0) {
                        return String.valueOf((transformRome.ToRoman(result)));
                    } else throw new Exception("В5 римской системе нет отрицательных чисел");
                } else {

                    return String.valueOf(result);
                }
            } else throw new Exception("Введите число от 1 до 10");
        } else throw new Exception("Вводимые числа должны быть в одной системе исчисления");
    }
}