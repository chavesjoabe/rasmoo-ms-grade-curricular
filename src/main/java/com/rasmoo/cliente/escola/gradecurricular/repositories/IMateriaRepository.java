package com.rasmoo.cliente.escola.gradecurricular.repositories;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMateriaRepository extends JpaRepository<MateriaEntity,Long> {

    @Query("select  m from MateriaEntity m where m.horas >= :horaMinima")
    public List<MateriaEntity> listarPorHoraMinima(@Param("horaMinima") int horaMinima);
}
