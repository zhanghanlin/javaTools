package com.demo.java.utils.collection;

import java.text.Collator;
import java.util.*;

/**
 * 排序工具
 *
 * @author zhanghanlin
 */
public class SortUtils {

    enum Order {
        /**
         * 倒序
         */
        DESC(-1, 1),
        /**
         * 正序
         */
        ASC(1, -1);

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
     * @param list  List
     * @param order Order
     */
    public static void sortWords(List<String> list, final Order order) {
        Collections.sort(list, new Comparator<String>() {
            Comparator comparator = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);

            @Override
            public int compare(String o1, String o2) {
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
