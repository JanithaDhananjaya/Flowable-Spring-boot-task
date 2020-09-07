package lk.janitha.flowable.controller;

import lk.janitha.flowable.dto.TaskRepresentation;
import lk.janitha.flowable.service.MyService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    private MyService myService;

    @PostMapping(value = "/process")
    public void startProcessInstance(){
        myService.startProcess();
    }

    @RequestMapping(value = "/tasks", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee){
        List<Task> tasks = myService.getTasks(assignee)
                ;
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks){
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }

        return dtos;
    }
}
