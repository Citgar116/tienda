package com.upiiz.tienda.controllers;


import com.upiiz.tienda.dto.CategoriaDto;
import com.upiiz.tienda.models.Categoria;
import com.upiiz.tienda.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //Listar categorias
     @GetMapping
    public String listarCategorias(Model model){
         model.addAttribute("categoria", categoriaService.getListaCategoria());
         return "listado-categorias";
     }

    //mostrar el formulario
    @GetMapping ("/nueva")
    public String mostrarFormularioCrear(Model model){
         model.addAttribute("categoria", new CategoriaDto());
         return"formulario-agregar-categoria";
    }

    //Mostrar form para actualizar
    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Long id, Model model){
        Optional<Categoria> categoria = categoriaService.getCategoriaById(id);
        if (categoria.isPresent()){
            model.addAttribute("categoria", categoria.get());
            return "formulario-actualizar-categoria";
        }
        return "redirect:/categoria";
    }

    @PostMapping("/actualizar")
    public String actualizarCategoria(@ModelAttribute Categoria categoria){
         CategoriaDto categoriaDto = new CategoriaDto(categoria.getNombre(), categoria.getDescripcion());
         categoriaService.modificarCategoria(categoria.getId(), categoriaDto);
         return "redirect:/categoria";
    }

    //Guardar categoria por el metodo POST
    @PostMapping
    public String guardarCategoria(@ModelAttribute CategoriaDto categoriaDto){
         categoriaService.crearCategoria(categoriaDto);
         return "redirect:/categoria";
    }

    //Eliminar

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return "redirect:/categoria";
    }




}
