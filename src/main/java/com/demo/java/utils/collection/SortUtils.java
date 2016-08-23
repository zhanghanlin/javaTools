package com.demo.java.utils.collection;

import java.text.Collator;
import java.util.*;

public class SortUtils {

    enum Order {

        DESC(-1, 1), ASC(1, -1);

        private int one;
        private int two;

        Order(int one, int two) {
            this.one = one;
            this.two = two;
        }

        public int getOne() {
            return one;
        }

        public int getTwo() {
            return two;
        }
    }

    /**
     * 首字母排序
     *
     * @param list
     * @param order
     */
    public static void sortWords(List list, final Order order) {
        Collections.sort(list, new Comparator() {
            Comparator comparator = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);

            @Override
            public int compare(Object o1, Object o2) {
                if (comparator.compare(o1, o2) > 0) {
                    return order.getOne();
                } else if (comparator.compare(o1, o2) < 0) {
                    return order.getTwo();
                }
                return 0;
            }
        });
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        String[] arr = {"张三", "李四", "王五", "赵六"};
        String[] arr = {"1", "5", "2", "3"};
        for (String a : arr) {
            list.add(a);
        }
        sortWords(list, Order.DESC);
        for (String a : list) {
            System.out.print(a + " ");
        }
    }
}
