package com.wxdl;

import java.util.*;

public class LineUtil {
    public static boolean match(String line, String start, String end) {
        return null != line && line.startsWith(start) && line.endsWith(end);
    }

    public static boolean spaceMatch(String line) {
        return null != line && line.trim().isEmpty();
    }

    public static String pop(List<String> textList) {
        if (null != textList && !textList.isEmpty()) {
            return textList.remove(textList.size() - 1);
        }
        return null;
    }

    public static boolean trimMatch(String line, String text) {
        return null != line && line.trim().equals(text);
    }

    private static final String END_SIGN = ".?!。？！";
    public static boolean isSentenceCompleted(String line) {
        return null != line && END_SIGN.contains(line.substring(line.length() - 2));
    }

    public static Map<String, Integer> getCountStastic(List<String> stringList) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String book : stringList) {
            Integer count = countMap.get(book);
            if (null == count) {
                countMap.put(book, 1);
            } else {
                countMap.put(book, count + 1);
            }
        }
        return countMap;
    }

    public static Map<Integer, List<String>> getSortedMapByCount(Map<String, Integer> countMap) {
        Map<Integer, List<String>> countListMap = new HashMap<>();
        for (String b : countMap.keySet()) {
            int count = countMap.get(b);
            List<String> bookList = countListMap.get(count);
            if (null == bookList) {
                bookList = new ArrayList<>();
                countListMap.put(count, bookList);
            }
            bookList.add(b);
        }
        return countListMap;
    }

    public static List<Integer> getSortedCountList(Map<Integer, List<String>> countBookListMap) {
        List<Integer> countList = new ArrayList<>(countBookListMap.keySet());
        Collections.sort(countList);
        Collections.reverse(countList);
        return countList;
    }
}
