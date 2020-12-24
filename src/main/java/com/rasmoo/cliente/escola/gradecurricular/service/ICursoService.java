package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.model.CursoModel;

import java.util.List;

public interface ICursoService {

    public List<CursoEntity> listar();
    public CursoEntity consultarPorId(Long id);
    public CursoEntity buscarPorCodigo(String codigo);
    public Boolean cadastrar(CursoModel curso);
    public Boolean atualizar(CursoModel curso);
    public Boolean deletar(Long id);
}
