package webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainString {
    public static void main(String[] args) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
//        String result = "";
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str).append(", ");
        }
        System.out.println(sb.toString());

        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
        System.out.println(str1 == str2);
        int[] ints = {9, 8};
        System.out.println(minValue(ints));
        List<Integer> list = new ArrayList<>();
//        list.add(1);
        list.add(2);
//        list.add(3);
        list.add(4);
//        list.add(7);
        System.out.println(oddOrEven(list));

    }

    static int minValue(int[] values) {
        return Integer.parseInt(Arrays.stream(values)
                .distinct()
                .sorted()
                .mapToObj(Integer::toString)
                .collect(Collectors.joining("")));

    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0 ?
                integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList())
                : integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());

    }
}
