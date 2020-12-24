package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.CursoException;
import com.rasmoo.cliente.escola.gradecurricular.model.CursoModel;
import com.rasmoo.cliente.escola.gradecurricular.repositories.ICursoRepository;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements ICursoService {

    private static final String CURSO_NAO_ENCONTRADO = "Curso nao encontrado";
    private static final String ERRO_INTERNO_DE_SERVIDOR = "Erro interno do servidor";

    @Autowired
    private ICursoRepository repository;
    @Autowired
    private IMateriaRepository materiaRepository;

    @Override
    public List<CursoEntity> listar() {
        try {
            List<CursoEntity> lista = this.repository.findAll();
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CursoEntity consultarPorId(Long id) {
        try {
            Optional<CursoEntity> cursoOptional = this.repository.findById(id);
            if (cursoOptional.isPresent()) {
                CursoEntity curso = cursoOptional.get();
                return curso;
            }
            throw new CursoException(CURSO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
        } catch (CursoException m) {
            throw m;
        } catch (Exception e) {
            throw new CursoException(ERRO_INTERNO_DE_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CursoEntity buscarPorCodigo(String codCurso) {
        try{
            CursoEntity curso = this.repository.buscarPorCodigo(codCurso);
            if(curso == null){
                throw new CursoException(CURSO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
            }
            return curso;
        }catch (CursoException c){
            throw c;
        }catch (Exception e){
            throw new CursoException(ERRO_INTERNO_DE_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrar(CursoModel curso) {
        try {

            return this.cadastrarOuAtualizar(curso);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean atualizar(CursoModel curso) {
        try {
            this.buscarPorCodigo(curso.getCodCurso());
            return this.cadastrarOuAtualizar(curso);
        } catch (CursoException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean deletar(Long id) {
        try{
            this.consultarPorId(id);
            this.repository.deleteById(id);

            return Boolean.TRUE;
        }catch (CursoException m){
            throw m;
        }catch (Exception e){
            throw e;
        }

    }

    public Boolean cadastrarOuAtualizar(CursoModel cursoModel){
        List<MateriaEntity> listaMaterias = new ArrayList<>();
        if(!cursoModel.getMaterias().isEmpty()){

            cursoModel.getMaterias().forEach(materia->{
                if(this.materiaRepository.findById(materia).isPresent())
                    listaMaterias.add(this.materiaRepository.findById(materia).get());

        });

        }
        CursoEntity cursoEntity = new CursoEntity();
        if(cursoModel.getId()!=null){
            cursoEntity.setId(cursoModel.getId());
        }
        cursoEntity.setNome(cursoModel.getNome());
        cursoEntity.setCodigo(cursoModel.getCodCurso());
        cursoEntity.setMaterias(listaMaterias);

        this.repository.save(cursoEntity);

        return Boolean.TRUE;
    }


}
