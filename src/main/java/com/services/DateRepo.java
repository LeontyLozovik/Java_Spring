package com.services;

import com.domain.DataBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepo extends CrudRepository<DataBase, Integer> {
}
