package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return this.materiaRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public MateriaEntity buscarPorId(Long id) {
        try{
            Optional<MateriaEntity> materia = this.materiaRepository.findById(id);
            if(materia.isPresent()){
                return materia.get();
            }
            throw new MateriaException("materia nao encontrada", HttpStatus.NOT_FOUND);
        }catch (MateriaException m){
            throw m;
        }catch (Exception e) {
            throw new MateriaException("Erro Interno identificado, contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrar(MateriaDto materia) {
        try{
            ModelMapper mapper = new ModelMapper();
            MateriaEntity materiaEntity = mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean atualizar(MateriaDto materia) {
        try{
            this.buscarPorId(materia.getId());
            ModelMapper mapper = new ModelMapper();
            MateriaEntity materiaAtualizada = mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaAtualizada);

            return true;
        }catch (MateriaException m){
            throw m;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try{
            this.buscarPorId(id);
            this.materiaRepository.deleteById(id);

            return true;
        }catch (MateriaException m){
            throw m;
        }

        catch (Exception e) {
            throw e;
        }
    }
}
