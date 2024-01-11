package com.training;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.itemsList();
        app.switchCase();
        app.useRawTypes();
        app.unnecessaryLocalBeforeReturn();
        app.manualArrayToCollectionCopy();
        app.incorrectStringComparison();
        app.potentialNullPointerException();
        app.inefficientStringConcatenation();
        app.uncheckedException();
    }

    @SuppressWarnings("all")
    private void itemsList() {
        List<String> items = Arrays.asList("A", "B", "C");
        items.forEach(item -> System.out.println(item));
    }

    @SuppressWarnings("all")
    private void switchCase() {
        int number = 2;
        switch (number) {
            default:
                System.out.println("Default case");
                break;
            case 1:
                System.out.println("One");
                break;
            case 2:
                System.out.println("Two");
                break;
        }
    }

    @SuppressWarnings("unchecked")
    private void useRawTypes() {
        List numbers = Arrays.asList(1, 2, 3);
        for (Object number : numbers) {
            System.out.println(number);
        }
    }

    @SuppressWarnings("all")
    private int unnecessaryLocalBeforeReturn() {
        int result = compute();
        return result;
    }

    private int compute() {
        return 42;
    }

    @SuppressWarnings("all")
    private void manualArrayToCollectionCopy() {
        String[] array = {"A", "B", "C"};
        List<String> list = new ArrayList<>(Arrays.asList(new String[array.length]));
        for (int i = 0; i < array.length; i++) {
            list.set(i, array[i]);
        }
    }

    @SuppressWarnings("all")
    private void incorrectStringComparison() {
        String a = new String("Test");
        String b = new String("Test");
        if (a == b) {
            System.out.println("Incorrect string comparison using == operator");
        }
    }

    @SuppressWarnings("all")
    private void potentialNullPointerException() {
        String str = null;
        if (str.equals("test")) {
            System.out.println("This will throw a NullPointerException");
        }
    }

    @SuppressWarnings("all")
    private void inefficientStringConcatenation() {
        String str = "This is";
        for (int i = 0; i < 10; i++) {
            str = str + " inefficient";
        }
        System.out.println(str);
    }

    @SuppressWarnings("all")
    private void uncheckedException() {
        try {
            Integer.parseInt("Not a number");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

