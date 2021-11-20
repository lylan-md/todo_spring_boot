package com.testSpring.controllers;

import com.testSpring.entities.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/myday")
public class MyDay {
    @GetMapping
    public String index(Model model) {
        return show(model);
    }

    @RequestMapping("/show")
    public String show(Model model) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        Task task1 = new Task();
        task1.id = 1;
        task1.description = "Task 1";
        task1.category = "MyDay";
        task1.important = true;
        task1.isDone = false;

        Task task2 = new Task();
        task2.id = 2;
        task2.description = "Task 2";
        task2.category = "MyDay";
        task2.important = false;
        task2.isDone = true;

        tasks.add(task1);
        tasks.add(task2);

        model.addAttribute("tasks", tasks);

        return "my_day";
    }
}
