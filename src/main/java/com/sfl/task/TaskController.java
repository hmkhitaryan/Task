package com.sfl.task;

import com.sfl.model.Task;
import com.sfl.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = {"/", "/list-tasks"}, method = RequestMethod.GET)
    public String showTasksList(ModelMap model) {
        model.addAttribute("tasks", taskService.retrieveTasks(getPrincipal()));
        return "list-tasks";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.GET)
    public String showAddTaskPage(ModelMap model) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(ModelMap model, @Valid Task task, BindingResult result) {

        if (result.hasErrors())
            return "task";

        taskService.addTask(getPrincipal(), task.getDesc(), task.getTargetDate(), false);
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-tasks";
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.GET)
    public String showUpdateTaskPage(ModelMap model, @RequestParam int id) {
        model.addAttribute("task", taskService.retrieveTask(id));
        return "task";
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.POST)
    public String updateTask(ModelMap model, @Valid Task task,
                             BindingResult result) {
        if (result.hasErrors())
            return "task";

        task.setUser(getPrincipal());
        taskService.updateTask(task);

        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-tasks";
    }

    @RequestMapping(value = "/delete-task", method = RequestMethod.GET)
    public String deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);

        return "redirect:/list-tasks";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}