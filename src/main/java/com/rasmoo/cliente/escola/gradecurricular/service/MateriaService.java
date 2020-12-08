package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MateriaService implements IMateriaService{
    @Autowired
    private IMateriaRepository materiaRepository;

    @Override
    public Boolean atualizar(MateriaEntity materia) {
        try{
            MateriaEntity materiaAtualizada = this.materiaRepository.findById(materia.getId()).get();

            materiaAtualizada.setNome(materia.getNome());
            materiaAtualizada.setHoras(materia.getHoras());
            materiaAtualizada.setCodigo(materia.getCodigo());
            materiaAtualizada.setFrequencia(materia.getFrequencia());

            this.materiaRepository.save(materiaAtualizada);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try{
            this.materiaRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
