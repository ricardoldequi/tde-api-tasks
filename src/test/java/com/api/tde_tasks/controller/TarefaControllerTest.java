package com.api.tde_tasks.controller;

import com.api.tde_tasks.Repositorys.TarefaRepository;
import com.api.tde_tasks.controllers.TarefaController;
import com.api.tde_tasks.entities.Tarefa;
import com.api.tde_tasks.services.TarefaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TarefaController.class)
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TarefaService  tarefaService;
    @InjectMocks
    private TarefaController tarefaController;

    @Test
    public void testFindAll() throws Exception {
        Tarefa tarefa1 = new Tarefa(1L, "Lavar roupa", LocalDateTime.now(), LocalDateTime.now().plusDays(1), false);
        Tarefa tarefa2 = new Tarefa(2L, "Comprar alimentos", LocalDateTime.now(), LocalDateTime.now().plusDays(2), false);
        List<Tarefa> tarefaList = Arrays.asList(tarefa1, tarefa2);

        given(tarefaService.findAll()).willReturn(tarefaList);

        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].descricao", is("Lavar roupa")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].descricao", is("Comprar alimentos")));

                System.out.println("O get para as tarefas deu bom!");

    }
}

