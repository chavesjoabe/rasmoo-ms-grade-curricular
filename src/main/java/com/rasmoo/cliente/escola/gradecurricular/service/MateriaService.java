package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService{
    @Autowired
    private IMateriaRepository materiaRepository;

    @Override
    public List<MateriaEntity> listar() {
        try{
            return materiaRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public MateriaEntity buscarPorId(Long id) {
        try{
            Optional<MateriaEntity> materia = materiaRepository.findById(id);
            if(materia.isPresent()){
                return materia.get();
            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean cadastrar(MateriaEntity materia) {
        try{
            this.materiaRepository.save(materia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
