package com.jochoa.market.persistence;

import com.jochoa.market.persistence.crud.ProductoCrudRepostiroy;
import com.jochoa.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    private ProductoCrudRepostiroy productoCrudRepostiroy;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepostiroy.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepostiroy.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepostiroy.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public  Optional<Producto> getProducto(int idProducto){
        return productoCrudRepostiroy.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepostiroy.save(producto);
    }

    public void delete(int idProducto){
        productoCrudRepostiroy.deleteById(idProducto);
    }
}
