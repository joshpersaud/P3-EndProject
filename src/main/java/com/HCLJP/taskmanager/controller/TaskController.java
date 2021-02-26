package com.HCLJP.taskmanager.controller;

import com.HCLJP.taskmanager.exceptions.TaskException;
import com.HCLJP.taskmanager.dto.TaskDto;
import com.HCLJP.taskmanager.service.TaskService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Log
@Controller
public class TaskController {
    String startDateError = "Start date is greater than end date, revisit.";

    @Autowired
    TaskService taskService;

    @PostMapping("/create")
    public ModelAndView createNewTask(TaskDto taskDto, Principal principal, ModelMap modelMap) {

        if (taskService.createTaskSuccessful(taskDto, principal)) {
            return new ModelAndView(new RedirectView("http://localhost:8080/all"), modelMap);
        }

        modelMap.addAttribute("message", startDateError);
        return new ModelAndView("createform");
    }


    @PostMapping("/task/create/{id}")
    public ModelAndView editTask(@PathVariable String id, Principal principal, TaskDto taskDto, ModelMap modelMap) {

        if (taskService.updateTaskSuccessful(id, taskDto)) {
            modelMap.addAttribute("tasks", taskService.retrieveAllTasks(principal));
            return new ModelAndView(new RedirectView("http://localhost:8080/all"), modelMap);
        }

        modelMap.addAttribute("task", taskService.getTaskById(id));
        modelMap.addAttribute("message", startDateError);
        return new ModelAndView("editform");
    }

    @GetMapping("/task/{id}")
    public ModelAndView showEdit(@PathVariable String id, ModelMap modelMap) {

        try {

            if (!taskService.taskIdExist(Long.valueOf(id))) {
                throw new TaskException("The task at id " + id + " does not exist");
            }

            modelMap.addAttribute("task", taskService.getTaskById(id));
            modelMap.addAttribute("message", "");

            return new ModelAndView("editform", modelMap);
        } catch (NumberFormatException e) {
            log.warning("NumberFormatException: " + e.getMessage());
        }

        return new ModelAndView("index");
    }

    @PostMapping("/task/delete/{id}")
    public ModelAndView deleteTask(@PathVariable String id, Principal principal, ModelMap modelMap) {

        if (!taskService.taskIdExist(Long.valueOf(id))) {
            throw new TaskException("The task at id " + id + " does not exist");
        }

        taskService.removeTask(Long.valueOf(id));

        modelMap.addAttribute("tasks", taskService.retrieveAllTasks(principal));

        return new ModelAndView("alltasks", modelMap);
    }


    @GetMapping("/all")
    public ModelAndView showIndex(Principal principal, ModelMap modelMap) {

        modelMap.addAttribute("tasks", taskService.retrieveAllTasks(principal));
        return new ModelAndView("alltasks", modelMap);
    }

}
