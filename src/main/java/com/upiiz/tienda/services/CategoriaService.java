package com.upiiz.tienda.services;

import com.upiiz.tienda.dto.CategoriaDto;
import com.upiiz.tienda.models.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private List<Categoria> listadoCategoria = new ArrayList<Categoria>();
    private Long contadorId= 3L;

    //Constructor
    public CategoriaService(){
        listadoCategoria.add(new Categoria(1L, "Comida", "Cereal corn pops"));
    }

    //CRUD
    //Leer
    public List<Categoria> getListaCategoria(){
        return listadoCategoria;
    }

    public Optional<Categoria> getCategoriaById(Long id){
        return listadoCategoria.stream().filter(categoria -> categoria.getId().equals(id)).findFirst();
    }

    //Crear
    public Categoria crearCategoria(CategoriaDto categoriaDto){
        Categoria nuevaCategoria = new Categoria(contadorId, categoriaDto.getNombre(), categoriaDto.getDescripcion());
        listadoCategoria.add(nuevaCategoria);
        contadorId++;
        return nuevaCategoria;
    }

    //Modificar
    public Optional<Categoria> modificarCategoria(Long id, CategoriaDto categoriaDto){
        Optional<Categoria> categoriaActualizar = getCategoriaById(id);
        if(categoriaActualizar.isPresent())
        {
            Categoria categoriaActual = categoriaActualizar.get();
            categoriaActual.setNombre(categoriaDto.getNombre());
            categoriaActual.setDescripcion(categoriaDto.getDescripcion());
            return Optional.of(categoriaActual);
        }
        else{
            return Optional.empty();
        }
    }

    //Eliminar
    public boolean eliminarCategoria(Long id){
        return listadoCategoria.removeIf(categoria -> categoria.getId().equals(id));
    }
}
