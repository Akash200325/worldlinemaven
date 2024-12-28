package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Maven with Jenkins and SonarQube integration!");
        int sum = add(5, 10);
        System.out.println("The sum of 5 and 10 is: " + sum);
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
