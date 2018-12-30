package com.wxdl.book;

import com.sun.istack.internal.NotNull;
import com.wxdl.LineUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Content {
    private static final String ID_TEXT = "目录";


    public static class Item {
        private static final String SEPERATOR = "…………………………………………………";
        private static final int SHORT_TITLE_LEN = 2;

        private String line;
        private String title;
        private String shortTitle;
        private String pageNum;

        public static Item from(@NotNull String line) {
            if (line.contains(SEPERATOR)) {
                Item item = new Item();
                item.line = line;
                item.title = line.substring(0, line.indexOf(SEPERATOR)).trim();
                item.shortTitle = line.substring(0, SHORT_TITLE_LEN);
                item.pageNum = line.substring(line.lastIndexOf(SEPERATOR) + SEPERATOR.length()).trim();
                return item;
            }
            return null;
        }

        public String displayTitle() {
            StringBuilder builder = new StringBuilder();
            builder.append(shortTitle).append("\t").append(title).append(" ").append(pageNum).append("\t").append(line);
            return builder.toString();
        }
    }

    private boolean active = false;
    private final List<Item> itemList = new ArrayList<>();

    public boolean add(String line) {
        if (active) {
            Item item = Item.from(line);
            if (null != item) {
                itemList.add(item);
            } else {
                if (!LineUtil.spaceMatch(line)) {
                    active = false;
                }
            }
        } else {
            active = LineUtil.trimMatch(line, ID_TEXT);
        }

        return !(active || itemList.isEmpty());
    }

    public List<String> getItemList() {
        if (itemList.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();
        for (Item item : itemList) {
            list.add(item.displayTitle());
        }
        return list;
    }

    public List<String> getTitleList() {
        if (itemList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        for (Item item : itemList) {
            list.add(item.title);
        }
        return list;
    }

    public List<String> getShortTitleList() {
        if (itemList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        for (Item item : itemList) {
            list.add(item.shortTitle);
        }
        return list;
    }

}
