package com.jsoft;

import com.wxdl.TextResource;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args) {
        TextResource textResource = new TextResource();
        textResource.load();

        System.out.println("");
        System.out.println("Goodbye!" );
    }
}
