package com.rasmoo.cliente.escola.gradecurricular.repositories;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMateriaRepository extends JpaRepository<MateriaEntity,Long> {
}
