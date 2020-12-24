package com.rasmoo.cliente.escola.gradecurricular.repositories;

import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICursoRepository extends JpaRepository<CursoEntity, Long> {
    @Query("select c from CursoEntity c where c.codigo = :codigo")
    public CursoEntity buscarPorCodigo(@Param("codigo") String codigo);
}
