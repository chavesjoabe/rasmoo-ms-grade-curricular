package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;

import java.util.List;

public interface IMateriaService {
    public List<MateriaDto> listar();
    public MateriaEntity buscarPorId(Long id);
    public Boolean cadastrar(MateriaDto materia);
    public Boolean atualizar(MateriaDto materia);
    public Boolean excluir(Long id);

}
