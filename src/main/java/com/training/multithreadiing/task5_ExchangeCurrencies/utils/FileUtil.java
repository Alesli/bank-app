package com.training.multithreadiing.task5_ExchangeCurrencies.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileUtil {
    public static String readFileToString(File file) throws IOException {
        return FileUtils.readFileToString(file, "UTF-8");
    }

    public static void writeStringToFile(File file, String data) throws IOException {
        FileUtils.writeStringToFile(file, data, "UTF-8");
    }
}

