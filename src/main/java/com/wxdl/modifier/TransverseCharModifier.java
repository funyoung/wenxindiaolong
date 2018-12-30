package com.wxdl.modifier;

import com.wxdl.TransverseModifier;

import java.util.*;

/* 统计字符与出现频率
 */
public class TransverseCharModifier implements TransverseModifier {
    private final Map<Character, List<String>> charLineMap = new HashMap<>();

    private static final String fakeText = "  ，。…；之而以也矣亦乎于者则不夫；∶、！：？0123456789‘’";

    @Override
    public void transverses(List<String> textList, String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (fakeText.contains(String.valueOf(c))) {
                continue;
            }

            List<String> lineList = charLineMap.get(c);
            if (null == lineList) {
                lineList = new ArrayList<>();
                charLineMap.put(c, lineList);
            }
            lineList.add(line);
        }
    }

    @Override
    public void report() {
        Map<Integer, List<Character>> counterCharMap = new HashMap<>();

        for (Character character : charLineMap.keySet()) {
            int count = charLineMap.get(character).size();
            List<Character> characterList = counterCharMap.get(count);
            if (null == characterList) {
                characterList = new ArrayList<>();
                counterCharMap.put(count, characterList);
            }
            characterList.add(character);
        }

        List<Integer> countList = new ArrayList<>(counterCharMap.keySet());
        Collections.sort(countList);
        System.out.println("TransverseCharModifier, total char:" + charLineMap.size() + ", count size: " + countList.size());
        for (int count : countList) {
            List<Character> characterList = counterCharMap.get(count);
            System.out.print(count + "/" + characterList.size() + " -> ");
            for (Character c : characterList) {
                System.out.print(c);
            }
            System.out.println("");
        }
        System.out.println("TransverseCharModifier, report completed!");
    }
}
