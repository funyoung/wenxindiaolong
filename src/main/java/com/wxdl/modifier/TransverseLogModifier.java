package com.wxdl.modifier;

import com.wxdl.TransverseModifier;
import com.sun.istack.internal.NotNull;

import java.util.List;

/* 打印文本行，并统计总行数与字符数
 */
public class TransverseLogModifier implements TransverseModifier {
    private int lineCount = 0;
    private int charCount = 0;
    @Override
    public void transverses(@NotNull List<String> textList, @NotNull String line) {
        System.out.println(line);
        lineCount++;
        charCount += line.length();
    }

    @Override
    public void report() {
        System.out.println("TransverseLogModifier, total(line/char):" + lineCount + "/" + charCount);
    }
}
