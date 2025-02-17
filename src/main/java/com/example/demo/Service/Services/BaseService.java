package com.example.demo.Service.Services;

import com.example.demo.Domain.Models.BaseModel;
import com.example.demo.Repository.IBaseRepository;
import com.example.demo.Service.Interfaces.IBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BaseService<TModel extends BaseModel> implements IBaseService<TModel> {

    protected final IBaseRepository<TModel> repository;

    protected BaseService(IBaseRepository<TModel> repository) {
        this.repository = repository;
    }

    @Override
    public List<TModel> get() {
        return repository.findAll();
    }

    @Override
    public Optional<TModel> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void insert(TModel model) {
        repository.save(model);
    }

    @Override
    @Transactional
    public void update(TModel model) {
        if (model.getId() == null || !repository.existsById(model.getId())) {
            throw new RuntimeException("Entity not found!");
        }
        repository.save(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found!");
        }
        repository.deleteById(id);
    }
}
