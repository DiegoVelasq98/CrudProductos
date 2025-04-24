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

    public List<Producto> getProducto() {

    return this.productoRepository.findAll();
}


public ResponseEntity<Object>newProducto(Producto producto) {

    Optional<Producto> res = productoRepository.findProductoByNombre(producto.getNombre());
    datos = new  HashMap<>();
    if (res.isPresent() && producto.getIdProducto()==null){
        datos.put("ERROR",true);
        datos.put("Message", "Ya existe ese cliente");
        return new ResponseEntity<>(datos, HttpStatus.CONFLICT);

    }
    datos.put("Message", "Se guardo correctamente");

    if(producto.getIdProducto()!=null){
        datos.put("Message", "Se actualizo el producto");


    }
    productoRepository.save(producto);
    datos.put("data", producto);

    return new ResponseEntity<>(datos, HttpStatus.CREATED);



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
