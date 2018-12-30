package com.wxdl.book;

import com.wxdl.LineUtil;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    public static class Paragraph {
        private static final String SECTION_START = "    ";
        private final List<String> sectionList = new ArrayList<>();
        private final StringBuilder sectionBuilder = new StringBuilder();

        void done() {
            sectionList.add(sectionBuilder.toString());
            sectionBuilder.setLength(0);
        }

        List<String> getSectionList() {
            if (sectionBuilder.length() > 0) {
                done();
            }
            return sectionList;
        }

        void add(String line) {
            boolean isNewSection = line.startsWith(SECTION_START);
            line = line.trim();
            if (LineUtil.spaceMatch(line)) {
                return;
            }

            if (isNewSection) {
                done();
                sectionBuilder.append(SECTION_START);
            }

            sectionBuilder.append(line);
        }

        public String toString() {
            return String.join("\n", getSectionList());
        }
    }

    private final List<String> titleList = new ArrayList<>();
    private final List<Paragraph> paragraphList = new ArrayList<>();
    private Paragraph paragraph;

    private int titleIndex = -1;

    public void add(String line) {
        if (titleIndex < titleList.size() - 1 && LineUtil.trimMatch(line, titleList.get(titleIndex + 1))) {
            beginNextParagraph();
        } else {
            if (!LineUtil.spaceMatch(line) && titleIndex > -1) {
                paragraph.add(line);
            }
        }
    }

    private void beginNextParagraph() {
        titleIndex++;
        paragraph = new Paragraph();
        paragraphList.add(paragraph);
    }

    public void activate(List<String> titleList) {
        if (null != titleList) {
            this.titleList.clear();
            this.titleList.addAll(titleList);
        }
    }

    public List<Paragraph> getParagraphList() {
        return paragraphList;
    }
}
