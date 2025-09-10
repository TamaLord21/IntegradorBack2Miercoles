package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import com.example.API.MODELO.MIERCOLES.modelos.dtos.UsuarioDTO;
import com.example.API.MODELO.MIERCOLES.modelos.mapas.IMapaUsuarioDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {

    //LLamar al repositorio
    //Inyectar la depedencia al repositorio
    @Autowired
    IUsuarioRepositorio repositorio;

    @Autowired
    IMapaUsuarioDTO mapa;


    //Funcion o servicio para guardar un usuario en BD
    public UsuarioDTO guardarUsuario(Usuario datosUsuario)throws Exception{
        try{

            return mapa.convertirADTO(repositorio.save(datosUsuario));

        }catch(Exception error){
            throw new Exception("upss tuvimos un problema "+error.getMessage());

        }
    }

    public List<UsuarioDTO> buscarTodosLosUsuarios()throws Exception{
        try{

            return mapa.convertirListaDTO(repositorio.findAll());

        }catch(Exception error){
            throw new Exception("upss tuvimos un problema "+error.getMessage());

        }
    }

}
