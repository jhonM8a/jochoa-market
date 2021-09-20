package com.jochoa.market.persistence.crud;

import com.jochoa.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepostiroy extends CrudRepository<Producto,Integer> {
}
