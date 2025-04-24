package com.Productos.Produtos.producto.Controller;

import com.Productos.Produtos.producto.Entity.Producto;
import com.Productos.Produtos.producto.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/productos")

public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

@GetMapping
    public List<Producto> getProductos() {
        return productoService.getProducto();
}


    @PostMapping
        public ResponseEntity<Object> getProducto(@RequestBody Producto producto) {

        return this.productoService.newProducto(producto);

    }


    @PutMapping
    public ResponseEntity<Object> actualizarProducto(@RequestBody Producto producto) {


        return this.productoService.newProducto(producto);

    }

    @DeleteMapping(path= "{productoId}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable("productoId")Long idProducto){
        return this.productoService.eliminarProducto(idProducto);
    }

}
