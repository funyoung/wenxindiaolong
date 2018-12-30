package com.wxdl;

import com.wxdl.filter.TransverseMarkFilter;
import com.wxdl.filter.TransversePageFilter;
import com.wxdl.filter.TransverseSpaceFilter;
import com.wxdl.modifier.TransverseCharModifier;
import com.wxdl.modifier.TransverseLogModifier;
import com.sun.istack.internal.NotNull;
import com.wxdl.modifier.TransverseParagraphModifier;
import com.wxdl.modifier.TransverseReferenceModifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// todo: backlog - 引用了哪些书（书名号括起来的），引用了哪些话（引号括起来的），并且在同一段落，句子里
public class TextResource {
    private static final String WXDL_FILE_NAME = "textbook_origin.txt";

    private final List<TransverseFilter> filterList = new ArrayList<>();
    private final List<TransverseModifier> modifierList = new ArrayList<>();
    private final List<String> textList = new ArrayList<>();

    public String getFilePath(String fileName) {
        return getClass().getResource(File.separator + fileName).getFile();
    }

    public void load() {
        String path = getFilePath(WXDL_FILE_NAME);
        File file = new File(path);
        if (file.exists()) {
            initFilters();
            initModifiers();

            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while (true) {
                    String line = reader.readLine();
                    if (null == line) {
                        break;
                    }

                    if (applyFilter(line)) {
                        textList.add("\n");
                    } else {
                        textList.add(line);
                        applyModifier(line);
                    }
                }

                reportFilter();
                reportModifier();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println( "File Not Found!" );
        }
    }

    private void reportModifier() {
        for (TransverseModifier modifier : modifierList) {
            modifier.report();
        }
    }

    private void reportFilter() {
        for (TransverseFilter filter : filterList) {
            filter.report();
        }
    }

    private boolean applyFilter(String line) {
        for (TransverseFilter filter : filterList) {
            if (filter.filterTest(line)) {
                return true;
            }
        }
        return false;
    }

    private void applyModifier(@NotNull String line) {
        for (TransverseModifier modifier : modifierList) {
            modifier.transverses(textList, line);
        }
    }

    private void initFilters() {
        filterList.add(new TransverseMarkFilter());
        filterList.add(new TransversePageFilter());
        filterList.add(new TransverseSpaceFilter());
    }

    private void initModifiers() {
        modifierList.add(new TransverseLogModifier());
        modifierList.add(new TransverseCharModifier());
        modifierList.add(new TransverseParagraphModifier());
        modifierList.add(new TransverseReferenceModifier());
    }
}
