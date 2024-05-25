package com.api.tde_tasks.controllers;


import com.api.tde_tasks.entities.Tarefa;
import com.api.tde_tasks.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/tasks")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll() {
        List<Tarefa> result = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable Long id) {

        Tarefa result = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefa) {
        Tarefa result = service.create(tarefa);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id,
                                         @RequestBody Tarefa newTarefa) {

        Tarefa tarefa = service.update(id, newTarefa);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(tarefa);
    }

    @PutMapping(value = "/{id}/done")
    public ResponseEntity<Tarefa> update(@PathVariable Long id) {

        Tarefa tarefa = service.updateFinalizada(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(tarefa);
    }

}
