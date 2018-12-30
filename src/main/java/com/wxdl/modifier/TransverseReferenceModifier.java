package com.wxdl.modifier;

import com.wxdl.LineUtil;
import com.wxdl.TransverseModifier;

import java.util.*;

public class TransverseReferenceModifier implements TransverseModifier {
    private final List<String> bookReferenceList = new ArrayList<>();
    private final List<String> wordReferenceList = new ArrayList<>();

    private final StringBuilder bookNameBuilder = new StringBuilder();
    private final StringBuilder wordBuilder = new StringBuilder();

    private boolean bookBuilding = false;
    private boolean wordBuilding = false;
    @Override
    public void transverses(List<String> textList, String line) {
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '《') {
                bookBuilding = true;
            } else if (ch == '》') {
                bookBuilt();
            } else if (ch == '“') {
                wordBuilding = true;
            } else if (ch == '”') {
                wordBuilt();
            } else {
                tryBuildBook(line, ch);
                tryBuildWord(line, ch);
            }
        }
    }

    private void tryBuildBook(String line, char ch) {
        if (bookBuilding && ' ' != ch) {
            bookNameBuilder.append(ch);
        }
    }

    private void tryBuildWord(String line, char ch) {
        if (wordBuilding && ' ' != ch) {
            wordBuilder.append(ch);
        }
    }

    private void wordBuilt() {
        wordReferenceList.add(wordBuilder.toString().trim());
        wordBuilder.setLength(0);
        wordBuilding = false;
    }

    private void bookBuilt() {
        bookReferenceList.add(bookNameBuilder.toString().trim());
        bookNameBuilder.setLength(0);
        bookBuilding = false;
    }

    @Override
    public void report() {
        System.out.println("TransverseReferenceModifier, report begin:");
        Map<String, Integer> countMap = LineUtil.getCountStastic(bookReferenceList);
        System.out.println("total unique book count: " + countMap.size() + "/"  + bookReferenceList.size());

        Map<Integer, List<String>> sortedListMap = LineUtil.getSortedMapByCount(countMap);
        List<Integer> countList = LineUtil.getSortedCountList(sortedListMap);

        for (Integer count : countList) {
            List<String> bookList = sortedListMap.get(count);
            System.out.println(count + "/" + bookList.size() + "：《" + String.join("》,《", bookList) + "》");
        }

        countMap = LineUtil.getCountStastic(wordReferenceList);
        System.out.println("total unique word count: " + countMap.size() + "/" + wordReferenceList.size());

        sortedListMap = LineUtil.getSortedMapByCount(countMap);
        countList = LineUtil.getSortedCountList(sortedListMap);
        for (Integer count : countList) {
            List<String> wordList = sortedListMap.get(count);
            System.out.println(count + "/" + wordList.size() + ": \n" + String.join("\n", wordList));
        }


        System.out.println("TransverseReferenceModifier, report end");
    }
}
