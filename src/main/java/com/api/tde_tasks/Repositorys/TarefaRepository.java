package com.api.tde_tasks.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.tde_tasks.entities.Tarefa;


public interface TarefaRepository extends JpaRepository <Tarefa, Long> {
}
