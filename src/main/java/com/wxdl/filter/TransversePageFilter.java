package com.wxdl.filter;

import com.wxdl.LineUtil;
import com.wxdl.TransverseFilter;

/* 过滤以下样式，第一行页面分隔，跟着空行，然后文心雕龙开始以页码结束行，后面若干空行
----------------------- 页面 2-----------------------

文心雕龙                                                                  ·1·

 */
public class TransversePageFilter extends TransverseBaseFilter {
    private static final String TEST_START = "----------------------- 页面 ";
    private static final String TEST_END = "-----------------------";

    private static final String START_SEC = "文心雕龙                                                               ";
    private static final String END_SEC = "· ";

    private int stage = 0; // 0未过滤 1匹配了第一个非空行 2匹配了第二个非空行. 可以从0到1到2，也可以0直接到2

    @Override
    public boolean filterTest(String line) {

        switch (stage) {
            case 0:
                if (isFirstLine(line)) {
                    stage = 1;
                } else if (isSecondFilter(line)) {
                    stage = 2;
                }
                break;
            case 1:
                if (isSecondFilter(line)) {
                    stage = 2;
                } else {
                    checkToReset(line);
                }
                break;
            case 2:
                checkToReset(line);
                break;
            default:
                break;
        }
        return stage != 0;
    }

    private boolean isSecondFilter(String line) {
        return LineUtil.match(line, START_SEC, END_SEC);
    }

    private boolean isFirstLine(String line) {
        return LineUtil.match(line, TEST_START, TEST_END);
    }

    private void checkToReset(String line) {
        if (!LineUtil.spaceMatch(line)) {
            stage = 0;
        }
    }
}
