package com.todolistpackage.todolist

import java.io.File

fun initList(): ArrayList<String> {
    val todoFile = File("todolist.txt")
    return if (todoFile.createNewFile()) {
        ArrayList<String>()
    } else {
        val tasks = ArrayList<String>()
        todoFile.forEachLine { tasks.add(it) }
        tasks
    }
}

fun writeTodo(list:ArrayList<String>) {
    println("Current ToDo List:")
    for (i in 0..list.size-1) {
        println("\t${i+1} ${list[i]}")
    }
}

fun addTodo(list:ArrayList<String>) {
    println("Enter your new ToDo:")
    val todo:String = readLine().toString()
    list.add(todo)
    println("ToDo added!")
}

fun remTodo(list:ArrayList<String>) {
    println("Enter the number of the ToDo to remove:")
    val todoIndex:Int = Integer.valueOf(readLine())
    list.removeAt(todoIndex-1)
    println("ToDo removed!")
}

fun main() {
    var tasks = initList()
    var option:String
    println("Welcome to your Kotlin ToDo list!")
    do {
        println("(show/add/rem/qs):")
        option = readLine().toString()
        when (option) {
            "show" -> writeTodo(tasks)
            "add" -> addTodo(tasks)
            "rem" -> remTodo(tasks)
        }
    } while (option != "qs")
    val todoFile = File("todolist.txt")
    val todos:String = tasks.joinToString(separator="\n")
    todoFile.writeText(todos)
}