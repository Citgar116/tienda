package com.upiiz.tienda.services;

import com.upiiz.tienda.dto.ProductoDto;
import com.upiiz.tienda.models.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private List<Producto> listadoProducto = new ArrayList<Producto>();
    private Long contadorId=3L;

    public ProductoService(){
        listadoProducto.add(new Producto(1L, "Jabon", 120.0f, "Detergente"));
        listadoProducto.add(new Producto( 2L, "Cereal", 89.0f, "Comida"));
    }

    //CRUD
    //Leer --- read
    public List<Producto> getListadoProducto(){
        return listadoProducto;
    }

    public Optional<Producto> getProductoById(Long id){
        return listadoProducto.stream().filter(producto -> producto.getId().equals(id)).findFirst();
    }

    //Crear
    public Producto crearProducto(ProductoDto productoDto){
        Producto nuevoProducto = new Producto(contadorId, productoDto.getNombre(), productoDto.getPrecio(), productoDto.getCategoria());
        listadoProducto.add(nuevoProducto);
        contadorId++;
        return nuevoProducto;
    }

    //Modificar
    public Optional<Producto> modificarProducto(Long id, ProductoDto productoDto){
        Optional<Producto> productoActualizar = getProductoById(id);
        if(productoActualizar.isPresent())
        {
            Producto productoActual = productoActualizar.get();
            productoActual.setNombre(productoDto.getNombre());
            productoActual.setPrecio(productoDto.getPrecio());
            productoActual.setCategoria(productoDto.getCategoria());
            return Optional.of(productoActual);
        }
        else {
            return Optional.empty();
        }
    }

    //Eliminar
    public boolean eliminarProducto(Long id) {
        return listadoProducto.removeIf(producto -> producto.getId().equals(id));
    }

}
