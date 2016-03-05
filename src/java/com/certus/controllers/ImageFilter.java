/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author shanaka
 */
public class ImageFilter {

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "jpg", "png" // and other formats you need
    };
    // filter to identify images based on their extensions
   public static final FilenameFilter IMAGE_FILTER = (final File dir1, final String name) -> {
        for (final String ext : EXTENSIONS) {
            if (name.endsWith("." + ext)) {
                return (true);
            }
        }
        return (false);
    };
}
