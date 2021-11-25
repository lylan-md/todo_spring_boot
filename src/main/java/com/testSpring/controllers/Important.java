package com.testSpring.controllers;

import com.testSpring.entities.Task;
import com.testSpring.entities.User;
import com.testSpring.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String index(Model model, @AuthenticationPrincipal User user) {
        return show(model, user);
    }

    @RequestMapping("/show")
    public String show(Model model, @AuthenticationPrincipal User user) {
        ArrayList<Task> tasks = new ArrayList<Task>(tasksRepository.findByImportantAndUserId(true, user.getId()));
        model.addAttribute("tasks", tasks);
        return "important";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam int task_id, @AuthenticationPrincipal User user) {
        tasksRepository.deleteByIdAndUserId(task_id, user.getId());
        return "redirect:show";
    }

    @GetMapping("/switchIsDoneTaskFlag")
    public String switchIsDoneTaskFlag(@RequestParam int task_id, @AuthenticationPrincipal User user) {
        Optional<Task> optionalTask = tasksRepository.findByIdAndUserId(task_id, user.getId());
        optionalTask.ifPresent(task -> {
            task.setDone(!task.isDone());
            tasksRepository.save(task);
        });
        return "redirect:show";
    }

    @GetMapping("/switchImportantFlag")
    public String switchImportantFlag(@RequestParam int task_id, @AuthenticationPrincipal User user) {
        Optional<Task> optionalTask = tasksRepository.findByIdAndUserId(task_id, user.getId());
        optionalTask.ifPresent(task -> {
            task.setImportant(!task.isImportant());
            tasksRepository.save(task);
        });
        return "redirect:show";
    }
}
