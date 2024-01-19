package other;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Other {

    @DisplayName("В строке все буквы с заглавной")
    @Test
    public void firstCharToUpper() {
        //Напишите код, который сделает у входящей строки все буквы с заглавной
        String exam = "привет странный странный мир";
        char[] array = exam.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        List<Integer> spaces = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                spaces.add(i);
            }
        }
        for (Integer space : spaces) {
            array[space + 1] = Character.toUpperCase(array[space + 1]);
        }
        String resultString = new String(array);
        System.out.println(resultString);
    }

    @DisplayName("Дубликаты")
    @Test
    public void duplicates() {
        //Напишите код, который профильтрует дублирующиеся элементы в массиве и выведет их в виде списка
        int[] array = {1, 2, 3, 4, 4, 2, 5, 5, 5};
        Arrays.sort(array);
        List<Integer> listInt = new ArrayList<>();
        Set<Integer> setInt = new HashSet<>();

        for (int i = 0; i < array.length - 1; i++) {
            listInt.add(array[i]);
            if (listInt.contains(array[i + 1])) {
                setInt.add(array[i + 1]);
            }
        }

        List<Integer> result = new ArrayList<>(setInt);
        System.out.println(result);
    }

    @DisplayName("Переворот строки")
    @Test
    public void revertString() {

        //Напишите код, который перевернет строку
        String exam = "привет странный странный мир";
        char[] array = exam.toCharArray();
        List<Character> result = new ArrayList<>();
        for (int i = array.length - 1; i >= 0; i--) {
            result.add(array[i]);
        }
        StringBuilder stringBuilder = new StringBuilder(result.size());
        for (int i = 0; i < result.size(); i++) {
            stringBuilder.append(result.get(i));
        }

        System.out.println(result);
        System.out.println(stringBuilder);

        //простой вариант с билдером
        StringBuilder stringBuilder2 = new StringBuilder("привет странный странный мир");
        stringBuilder2.reverse();
        System.out.println(stringBuilder2);
    }

    @DisplayName("достать из массива только четные числа")
    @Test
    public void sortSimpleNumbers() {
        //Напишите код, который достанет из массива только четные числа и вывести в виде списка
        int[] array = {1, 2, 3, 44, 66, 5, 7};
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result.add(array[i]);
            }
        }
        System.out.println(result);
    }

    @DisplayName("достать из массива уникальные числа")
    @Test
    public void getSimpleNumbers() {
        //Напишите код, который достанет из массива только уникальные числа
        int[] array = {1, 2, 3, 44, 3, 66, 5, 7, 5};
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (result.contains(array[i])) {
                continue;
            } else {
                result.add(array[i]);
            }

        }
        System.out.println(result);

        //либо через set
        Set<Integer> result2 = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            result2.add(array[i]);
        }
        System.out.println(result2);
    }

    @DisplayName("Дайте пример кода, который сортирует список строк с использованием коллекции")
    @Test
    public void sortListCollection() {
        ArrayList<Integer> exam = new ArrayList<>();
        Collections.addAll(exam, 5, 3, 12, 122, 4, 1);
        Collections.sort(exam);
        System.out.println(exam);
    }

    @DisplayName("Напишите функцию, которая переворачивает число (порядок цифр в нем изменяет на противоположный, инвертирует)")
    @Test
    public void revertNum() {
        int exam = 123456;

        //stringbuilder
        String examString = String.valueOf(exam);
        StringBuilder stringBuilder = new StringBuilder(examString);
        stringBuilder.reverse();
        Integer result = Integer.parseInt(stringBuilder.toString());
        System.out.println(result);

        //algorithm
        char[] array = examString.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (int i = array.length - 1; i >= 0; i--) {
            list.add(array[i]);
        }
        StringBuilder stringBuilder1 = new StringBuilder(list.size());
        for (int i = 0; i < list.size(); i++) {
            stringBuilder1.append(list.get(i));
        }
        Integer result2= Integer.parseInt(stringBuilder1.toString());
        System.out.println(result2);
    }
}






