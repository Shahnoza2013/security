package uz.pdp.SpringSecurity.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.SpringSecurity.dto.TaskCreateDTO;
import uz.pdp.SpringSecurity.exeption.UsernameOrPasswordWrong;
import uz.pdp.SpringSecurity.model.Task;
import uz.pdp.SpringSecurity.repository.TaskRepository;
import uz.pdp.SpringSecurity.utils.SecurityUtils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "TaskUI",description = "Bu yerda faqat tasklar ustida amallar bajariladi")
public class TaskController {


    private final TaskRepository taskRepository;
    private final SecurityUtils securityUtils;

    public TaskController(TaskRepository taskRepository, SecurityUtils securityUtils) {
        this.taskRepository = taskRepository;
        this.securityUtils = securityUtils;
    }

    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody TaskCreateDTO taskDto){
        int userId = securityUtils.getUser();


        Task task = Task.builder().name(taskDto.name())
                .description(taskDto.description())
                .deadLine(taskDto.deadLine())
                .build();

        Task save = taskRepository.save(task);
        return ResponseEntity.ok(save);
    }
    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody TaskCreateDTO taskDto){
        if (true)
            throw new UsernameOrPasswordWrong("Your username or password not correct.", HttpStatus.NOT_FOUND);
        Task task = taskRepository.findById(taskDto.id()).get();

        task.setName(taskDto.name());
        task.setDescription(taskDto.description());
        task.setDeadLine(taskDto.deadLine());

        Task save = taskRepository.save(task);
        return ResponseEntity.ok(save);
    }
    @DeleteMapping("/delete/{taskId}")
    public void delete(@PathVariable("taskId") Integer taskId){
        if (true)
            throw new UsernameOrPasswordWrong("Your username or password not correct.", HttpStatus.NOT_FOUND);

        taskRepository.deleteById(taskId);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<Task>> getByName(@PathVariable("name") String name){
        List<Task> byName = taskRepository.findByName(name);
        return ResponseEntity.ok(byName);
    }
    @DeleteMapping("/delete-by-name/{name}/{deadLine}")
    public void deleteByName(@PathVariable("name") String name, @PathVariable("deadLine")LocalDate deadLine){
        taskRepository.deleteByNameAndDeadLine(name,deadLine);
    }

    @GetMapping("/get-page/{page}/{count}")
    public ResponseEntity<List<Task>> getPage(@PathVariable("page") Integer page, @PathVariable("count")Integer count){
        Pageable pageRequest = PageRequest.of(page, count);

        List<Task> tasksForPage = taskRepository.getTasksForPage(pageRequest);

        return ResponseEntity.ok(tasksForPage);

    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    public ResponseEntity<List<Task>> getPage(HttpServletRequest request){
        List<Task> tasksForPage = taskRepository.findAll();
        return ResponseEntity.ok(tasksForPage);

    }

    @GetMapping("/get-page-info/{page}/{count}")
    public ResponseEntity<Page<Task>> getPageInfo(@PathVariable("page") Integer page, @PathVariable("count")Integer count){
        Pageable pageRequest = PageRequest.of(page, count);
        Page<Task> tasksForPage = taskRepository.findAll(pageRequest);
        return ResponseEntity.ok(tasksForPage);

    }

}
