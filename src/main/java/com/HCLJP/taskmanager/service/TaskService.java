package com.HCLJP.taskmanager.service;

import com.HCLJP.taskmanager.dto.TaskDto;
import com.HCLJP.taskmanager.entity.Task;
import com.HCLJP.taskmanager.entity.User;
import com.HCLJP.taskmanager.repository.TaskRepo;
import com.HCLJP.taskmanager.repository.UserRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Log
public class TaskService {

    @Autowired
    TaskRepo taskRepository;

    @Autowired
    UserRepo userRepository;

    public void removeTask(Long id) {
        taskRepository.deleteById(id);
    }


    public boolean taskIdExist(Long id) {
        return taskRepository.existsById(id);
    }


    public Task getTaskById(String id) {
        return taskRepository.findTaskById(Long.valueOf(id));
    }


    public Iterable<Task> retrieveAllTasks(Principal principal) {
        if (principal == null) {
            throw new UsernameNotFoundException("principal is null");
        }

        Optional<User> user = userRepository.findByUsername(principal.getName());
        user.orElseThrow(() -> new UsernameNotFoundException("unable to find user within TaskService"));

        User user1 = user.get();

        return taskRepository.findAllByUser(user1);
    }


    public boolean createTaskSuccessful(TaskDto taskDto, Principal principal) {

        try {

            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(taskDto.getStartDate());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(taskDto.getEndDate());

            if (!dateIsValid(taskDto, startDate, endDate))
                return false;

            Optional<User> user = userRepository.findByUsername(principal.getName());
            user.orElseThrow(() -> new UsernameNotFoundException("Username could not be found!"));


            Task task = Task.builder()
                    .severity(taskDto.getSeverity())
                    .user(user.get())
                    .startDate(startDate)
                    .endDate(endDate)
                    .taskName(taskDto.getTaskName())
                    .description(taskDto.getDescription())
                    .build();


            taskRepository.save(task);

        } catch (ParseException e) {
            log.warning(e.getMessage());
        }

        return true;
    }


    public boolean updateTaskSuccessful(String id, TaskDto taskDto) {

        Task task = taskRepository.findTaskById(Long.valueOf(id));

        try {

            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(taskDto.getStartDate());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(taskDto.getEndDate());


            if (!dateIsValid(taskDto, startDate, endDate))
                return false;

            if (task.getDescription() != null)
                task.setDescription(taskDto.getDescription());

            if (task.getTaskName() != null)
                task.setTaskName(taskDto.getTaskName());

            if (task.getSeverity() != null)
                task.setSeverity(taskDto.getSeverity());


            task.setStartDate(startDate);
            task.setEndDate(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        taskRepository.save(task);

        return true;
    }


    private boolean dateIsValid(TaskDto taskDto, Date startDate, Date endDate) throws ParseException {

        if (taskDto.getStartDate() == null || taskDto.getEndDate() == null ||
                taskDto.getStartDate().equals("") || taskDto.getEndDate().equals("")) {
            return false;
        }

        return startDate.compareTo(endDate) <= 0;
    }

}
