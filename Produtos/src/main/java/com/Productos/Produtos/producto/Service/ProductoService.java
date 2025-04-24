package com.Productos.Produtos.producto.Service;

import com.Productos.Produtos.producto.Entity.Producto;
import com.Productos.Produtos.producto.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    HashMap<String, Object> datos;

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {

        this.productoRepository = productoRepository;
    }

    public List<Producto> getProductos() {
        return this.productoRepository.findAll();
    }


    public Optional<Producto> getProductoById(Long id) {
        return this.productoRepository.findById(id);
    }



    public ResponseEntity<Object> guardarProducto(Producto producto) {
        datos = new HashMap<>();


        Optional<Producto> res = productoRepository.findProductoByNombre(producto.getNombre());
        if (res.isPresent() && producto.getIdProducto() == null) {
            datos.put("ERROR", true);
            datos.put("Message", "Ya existe ese producto");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }


        productoRepository.save(producto);
        datos.put("Message", "Producto guardado correctamente");
        datos.put("data", producto);
        return new ResponseEntity<>(datos, HttpStatus.CREATED);
    }


    public ResponseEntity<Object> actualizaProducto(Long id, Producto producto) {
        datos = new HashMap<>();


        Optional<Producto> productoExistente = productoRepository.findById(id);

        if (!productoExistente.isPresent()) {
            datos.put("ERROR", true);
            datos.put("Message", "Producto no encontrado");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }

        Producto prod = productoExistente.get();


        if (producto.getNombre() != null) {
            prod.setNombre(producto.getNombre());
        }
        if (producto.getDescripcion() != null) {
            prod.setDescripcion(producto.getDescripcion());
        }
        if (producto.getPrecio() != 0.0f) {
            prod.setPrecio(producto.getPrecio());
        }
        if (producto.getFechaRegistro() != null) {
            prod.setFechaRegistro(producto.getFechaRegistro());
        }


        productoRepository.save(prod);

        datos.put("Message", "Producto actualizado correctamente");
        datos.put("data", prod);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }


    public ResponseEntity<Object> eliminarProducto(Long idProducto) {
        datos= new HashMap<>();
        boolean existe = this.productoRepository.existsById(idProducto);
        if (!existe) {
            datos.put("error", true);
            datos.put("Message", "No existe ese producto");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);

        }

        productoRepository.deleteById(idProducto);
        datos.put("Message", "Producto eliminado!!!");
        return new ResponseEntity<>(datos, HttpStatus.OK);


}


}
