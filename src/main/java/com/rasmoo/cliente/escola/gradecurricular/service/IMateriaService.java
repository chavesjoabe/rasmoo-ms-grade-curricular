package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;

import java.util.List;

public interface IMateriaService {
    public List<MateriaEntity> listar();
    public MateriaEntity buscarPorId(Long id);
    public Boolean cadastrar(MateriaEntity materia);
    public Boolean atualizar(MateriaEntity materia);
    public Boolean excluir(Long id);

}
