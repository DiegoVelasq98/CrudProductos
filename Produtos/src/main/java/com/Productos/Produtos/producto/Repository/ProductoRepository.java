package com.Productos.Produtos.producto.Repository;

import com.Productos.Produtos.producto.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findProductoByNombre(String nombre);
}
