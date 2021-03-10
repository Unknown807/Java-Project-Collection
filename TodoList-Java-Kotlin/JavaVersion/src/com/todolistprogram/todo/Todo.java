package com.todolistprogram.todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Todo {
    public static ArrayList<String> initList() {
        try {
            File todoFile = new File("todolist.txt");
            if (todoFile.createNewFile()) {
                return new ArrayList<String>();
            } else {
                ArrayList<String> tasks = new ArrayList<String>();
                Scanner fileReader = new Scanner(todoFile);
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    tasks.add(data);
                }
                fileReader.close();
                return tasks;
            }
        } catch (Exception e) {
            return new ArrayList<String>();
        }
    }

    public static void writeTodo(ArrayList<String> list) {
        System.out.println("Current ToDo List:");
        for (int i=0; i<list.size(); i++) {
            System.out.println("\t"+(i+1)+". "+list.get(i));
        }
    }

    public static void addTodo(ArrayList<String> list) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your new ToDo:");
        String todo = userInput.nextLine();
        list.add(todo);
        System.out.println("ToDo added!");
    }

    public static void remTodo(ArrayList<String> list) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the number of the Todo to remove:");
        int todoIndex = userInput.nextInt();
        list.remove(todoIndex-1);
        System.out.println("ToDo removed!");
    }

    public static void main(String[] args) {
        ArrayList<String> tasks = initList();
        String option;
        System.out.println("Welcome to your Java ToDo list!");
        do {
            Scanner userInput = new Scanner(System.in);
            System.out.println("(show/add/rem/qs):");
            option = userInput.nextLine();
            switch (option) {
                case "show":
                    writeTodo(tasks);
                    break;
                case "add":
                    addTodo(tasks);
                    break;
                case "rem":
                    remTodo(tasks);
                    break;
            }
        } while (!option.equals("qs"));
        try {
            FileWriter fileWriter = new FileWriter("todolist.txt");
            for (String todo: tasks) {
                fileWriter.write(todo+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("There was an error saving the todolist!");
        }
    }
}
