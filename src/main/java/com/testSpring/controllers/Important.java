package com.testSpring.controllers;

import com.testSpring.entities.Task;
import com.testSpring.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/important")
public class Important {
    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping
    public String index(Model model) {
        return show(model);
    }

    @RequestMapping("/show")
    public String show(Model model) {
        ArrayList<Task> tasks = new ArrayList<Task>(tasksRepository.findByImportant(true));
        model.addAttribute("tasks", tasks);
        return "important";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam int task_id) {
        tasksRepository.deleteById(task_id);
        return "redirect:show";
    }

    @GetMapping("/switchIsDoneTaskFlag")
    public String switchIsDoneTaskFlag(@RequestParam int task_id) {
        Optional<Task> optionalTask = tasksRepository.findById(task_id);
        optionalTask.ifPresent(task -> {
            task.setDone(!task.isDone());
            tasksRepository.save(task);
        });
        return "redirect:show";
    }

    @GetMapping("/switchImportantFlag")
    public String switchImportantFlag(@RequestParam int task_id) {
        Optional<Task> optionalTask = tasksRepository.findById(task_id);
        optionalTask.ifPresent(task -> {
            task.setImportant(!task.isImportant());
            tasksRepository.save(task);
        });
        return "redirect:show";
    }
}
