package com.Productos.Produtos.producto.Controller;

import com.Productos.Produtos.producto.Entity.Producto;
import com.Productos.Produtos.producto.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/productos")

public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductoById(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        List<Producto> productos = productoService.getProductos();
        return ResponseEntity.ok(productos);
    }




    @PostMapping
        public ResponseEntity<Object> registrarProducto(@RequestBody Producto producto) {

        return this.productoService.guardarProducto(producto);

    }


    @PutMapping(path = "/{productoId}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable("productoId") Long idProducto,@RequestBody Producto producto) {
        producto.setIdProducto(idProducto);
        return this.productoService.actualizaProducto(idProducto, producto);
    }


    @DeleteMapping(path= "{productoId}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable("productoId")Long idProducto){
        return this.productoService.eliminarProducto(idProducto);
    }

}
