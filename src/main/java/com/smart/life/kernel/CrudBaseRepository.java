package com.smart.life.kernel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudBaseRepository<T, S> {

    T save(T model);

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(S id);

}
