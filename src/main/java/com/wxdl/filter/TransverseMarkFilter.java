package com.wxdl.filter;

import com.wxdl.TransverseFilter;

/* 过滤4行文字：头两行准确匹配，第三行前面的数字会变，第4行跟着空行
-----------------------  本图书由www.downshu.cn(geqwxf)为您整理制作，
更多txt好书 敬请登录http://downshu.cn/?fromuid=127。
09-----------------------

 */
public class TransverseMarkFilter extends TransverseBaseFilter {
    private int stage = 0;  // 0尚未匹配本过滤器， 1 匹配了第一行， 2匹配了第2行，3匹配了第3行， 4匹配了第4行
    private static final String testText1 = "-----------------------  本图书由www.downshu.cn(geqwxf)为您整理制作，";
    private static final String testText2 = "更多txt好书 敬请登录http://downshu.cn/?fromuid=127。";
    private static final String testText3 = "-----------------------";
    private static final String testText4 = "";

    // todo: else分支，前面几行已经完成过滤，但中间第2-4步不满足，需要恢复或者记录日志？？？
    @Override
    public boolean filterTest(String line) {
        switch (stage) {
            case 0:
                if (testText1.equals(line)) {
                    stage = 1;
                } else {
                    stage = 0;
                }
                break;
            case 1:
                if (testText2.equals(line)) {
                    stage = 2;
                } else {
                    stage = 0;
                }
                break;
            case 2:
                if (line.endsWith(testText3)) {
                    stage = 3;
                } else {
                    stage = 0;
                }
                break;
            case 3:
                if (testText4.equals(line)) {
                    stage = 4;
                } else {
                    stage = 0;
                }
                break;
            default:
                stage = 0;
                break;
        }

        return stage != 0;
    }
}
