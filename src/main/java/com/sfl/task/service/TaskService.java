package com.sfl.task.service;

import com.sfl.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskService {
    private static List<Task> tasks = new ArrayList<Task>();
    private static int taskCount = 3;

    static {
        tasks.add(new Task(1, "in28Minutes", "Learn Spring MVC", new Date(),
                false));
        tasks.add(new Task(2, "in28Minutes", "Learn Struts", new Date(), false));
        tasks.add(new Task(3, "in28Minutes", "Learn Hibernate", new Date(),
                false));
    }

    public List<Task> retrieveTasks(String user) {
        List<Task> filteredTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getUser().equals(user))
                filteredTasks.add(task);
        }
        return filteredTasks;
    }

    public Task retrieveTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id)
                return task;
        }
        return null;
    }

    public void updateTask(Task task) {
        tasks.remove(task);
        tasks.add(task);
    }

    public void addTask(String name, String desc, Date targetDate,
                        boolean isDone) {
        tasks.add(new Task(++taskCount, name, desc, targetDate, isDone));
    }

    public void deleteTask(int id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
            }
        }
    }
}