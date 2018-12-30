package com.wxdl.modifier;

import com.wxdl.TransverseModifier;
import com.wxdl.book.Chapter;
import com.wxdl.book.Content;

import java.util.List;

/* 构建目录与章节
 */
public class TransverseParagraphModifier implements TransverseModifier {
    private final Content content = new Content();
    private final Chapter chapter = new Chapter();

    private boolean contentReady = false;

    @Override
    public void transverses(List<String> textList, String line) {
        if (contentReady) {
            chapter.add(line);
        } else {
            if(content.add(line)) {
                contentReady = true;
                chapter.activate(content.getTitleList());
                chapter.add(line);
            }
        }
    }

    @Override
    public void report() {
        List<String> titleList = content.getItemList();
        System.out.println("TransverseParagraphModifier, content begin: " + titleList.size());
        for (String title : titleList) {
            System.out.println(title);
        }
        System.out.println("TransverseParagraphModifier, content end");

        List<String> shortTitleList = content.getShortTitleList();
        List<Chapter.Paragraph> chapterList = chapter.getParagraphList();
        System.out.println("TransverseParagraphModifier, chapter begin: " + shortTitleList.size() + "/" + chapterList.size());
        for (int i = 0; i < shortTitleList.size(); i++) {
            System.out.println();
            System.out.println(shortTitleList.get(i));
            System.out.println(chapterList.get(i));
        }
        System.out.println("TransverseParagraphModifier, chapter end");
    }
}
