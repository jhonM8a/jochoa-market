package com.jochoa.market.persistence;

import com.jochoa.market.persistence.crud.ProductoCrudRepostiroy;
import com.jochoa.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepostiroy productoCrudRepostiroy;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepostiroy.findAll();
    }
}
