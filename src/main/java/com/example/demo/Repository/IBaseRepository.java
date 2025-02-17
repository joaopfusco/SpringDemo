package com.example.demo.Repository;

import com.example.demo.Domain.Models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
@NoRepositoryBean
public interface IBaseRepository<TModel extends BaseModel> extends JpaRepository<TModel, Long> {
}
