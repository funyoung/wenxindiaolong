package com.wxdl.filter;

import com.wxdl.LineUtil;

public class TransverseSpaceFilter extends TransverseBaseFilter {
    @Override
    public boolean filterTest(String line) {
        return LineUtil.spaceMatch(line);
    }
}
