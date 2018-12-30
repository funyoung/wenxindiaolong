package com.wxdl;

import com.sun.istack.internal.NotNull;

import java.util.List;

public interface TransverseModifier {
    void transverses(@NotNull List<String> textList, @NotNull String line);

    void report();
}
