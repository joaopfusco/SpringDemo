package com.example.demo.Controllers;

import com.example.demo.Domain.Models.BaseModel;
import com.example.demo.Service.Services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

public abstract class BaseController<TModel extends BaseModel> {

    protected final BaseService<TModel> service;

    public BaseController(BaseService<TModel> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<TModel> modelOpt = service.get(id);
        return modelOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TModel model) {
        service.insert(model);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody TModel model) {
        model.setId(id);
        service.update(model);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(id);
    }
}

