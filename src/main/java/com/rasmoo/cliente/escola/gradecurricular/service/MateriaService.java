package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.controler.MateriaControler;
import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateriaService{

    private static final String MENSAGEM_ERRO = "Erro Interno identificado, contate o suporte";
    private static final String MATERIA_NAO_ENCONTRADA =  "materia nao encontrada";
    private IMateriaRepository materiaRepository;
    private ModelMapper mapper;
    @Autowired
    MateriaService(IMateriaRepository materiaRepository){
        this.mapper = new ModelMapper();
        this.materiaRepository = materiaRepository;
    }
    @Override
    public List<MateriaDto> listar() {
        try{
            List<MateriaDto> materiaDto = this.mapper.map(this.materiaRepository.findAll(), new TypeToken<List<MateriaDto>>(){

            }.getType());
            materiaDto.forEach(materia ->{
                materia.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                        .buscarMateria(materia.getId())).withSelfRel());
            });

            return materiaDto;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);        }
    }
    @Cacheable(key = "#id")
    @Override
    public MateriaEntity buscarPorId(Long id) {
        try{
            Optional<MateriaEntity> materia = this.materiaRepository.findById(id);
            if(materia.isPresent()){
                return materia.get();
            }
            throw new MateriaException(MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
        }catch (MateriaException m){
            throw m;
        }catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrar(MateriaDto materia) {
        try{
            MateriaEntity materiaEntity = this.mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);        }
    }
    @CacheEvict(key = "#materia.id")
    @Override
    public Boolean atualizar(MateriaDto materia) {
        try{
            this.buscarPorId(materia.getId());
            MateriaEntity materiaAtualizada = this.mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaAtualizada);

            return Boolean.TRUE;
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

            return Boolean.TRUE;
        }catch (MateriaException m){
            throw m;
        }

        catch (Exception e) {
            throw e;
        }
    }
}
