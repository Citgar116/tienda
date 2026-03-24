package com.upiiz.tienda.controllers;

import com.upiiz.tienda.dto.ProductoDto;
import com.upiiz.tienda.models.Producto;
import com.upiiz.tienda.services.CategoriaService;
import com.upiiz.tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProductoService productoService;

    //Listar productos
    @GetMapping
    public String listarProducto(Model model){
        model.addAttribute("producto",productoService.getListadoProducto());
        return "listado-productos";
    }

    //mostrar el formulario
    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("producto", new ProductoDto());
        model.addAttribute("categorias", categoriaService.getListaCategoria());
        return "formulario-agregar-producto";
    }

    //mostrar form para actualizar
    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Long id, Model model){
        Optional<Producto> producto = productoService.getProductoById(id);
        if(producto.isPresent()){
            model.addAttribute("producto", producto.get());
            model.addAttribute("categorias", categoriaService.getListaCategoria());
            return "formulario-actualizar-producto";
        }
        return "redirect:/producto";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto){
        ProductoDto productoDto = new ProductoDto(producto.getNombre(), producto.getPrecio(), producto.getCategoria());
        productoService.modificarProducto(producto.getId(), productoDto);
        return "redirect:/producto";
    }

    //Guardar por el metodo POST
    @PostMapping
    public String guardarProducto(@ModelAttribute ProductoDto productoDto){
        productoService.crearProducto(productoDto);
        return "redirect:/producto";
    }


    // Eliminar (POST)
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return "redirect:/producto";
    }

}
