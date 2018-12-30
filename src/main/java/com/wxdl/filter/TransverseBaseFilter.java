package com.wxdl.filter;

import com.wxdl.TransverseFilter;

public class TransverseBaseFilter implements TransverseFilter {
    @Override
    public boolean filterTest(String line) {
        return false;
    }

    @Override
    public void report() {
    }
}
