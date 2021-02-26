package com.HCLJP.taskmanager;

import com.HCLJP.taskmanager.entity.Task;
import com.HCLJP.taskmanager.entity.User;
import com.HCLJP.taskmanager.repository.TaskRepo;
import com.HCLJP.taskmanager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class SimplilearnTaskManagerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SimplilearnTaskManagerApplication.class, args);
    }

    @Autowired
    UserRepo userRepository;

    @Autowired
    TaskRepo taskRepository;

    @Override
    public void run(String... args) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .username("josh")
                .password(bCryptPasswordEncoder.encode("pass"))
                .email("josh.persaud@hcl.com")
                .build();

        Task task = Task.builder()
                .taskName("Task 1")
                .description("File paper work")
                .severity("low")
                .startDate(new Date())
                .endDate(new Date())
                .user(user)
                .build();

        Task task1 = Task.builder()
                .taskName("Take 2")
                .description("Finish Projects")
                .severity("high")
                .startDate(new Date())
                .endDate(new Date())
                .user(user)
                .build();

        User user1 = User.builder()
                .username("hcl")
                .password(bCryptPasswordEncoder.encode("pass"))
                .email("hcl@hcl.com")
                .build();

        Task task2 = Task.builder()
                .taskName("example task")
                .description("example description")
                .severity("high")
                .startDate(new Date())
                .endDate(new Date())
                .user(user1)
                .build();


        userRepository.save(user);
        taskRepository.save(task);
        taskRepository.save(task1);
        userRepository.save(user1);
        taskRepository.save(task2);

    }
}
