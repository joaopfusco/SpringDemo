package com.example.demo.Service.Interfaces;

import com.example.demo.Domain.Models.BaseModel;

import java.util.List;
import java.util.Optional;

public interface IBaseService<TModel extends BaseModel> {

    List<TModel> get();

    Optional<TModel> get(Long id);

    void insert(TModel model);

    void update(TModel model);

    void delete(Long id);
}
