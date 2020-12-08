package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;

public interface IMateriaService {
    public Boolean atualizar(MateriaEntity materia);
    public Boolean excluir(Long id);

}
