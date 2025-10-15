package com.example.API.MODELO.MIERCOLES.servicios;

import com.example.API.MODELO.MIERCOLES.ayudas.MensajeError;
import com.example.API.MODELO.MIERCOLES.modelos.Usuario;
import com.example.API.MODELO.MIERCOLES.dtos.UsuarioDTO;
import com.example.API.MODELO.MIERCOLES.mapas.IMapaUsuarioDTO;
import com.example.API.MODELO.MIERCOLES.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion() +error.getMessage());

        }
    }

    public List<UsuarioDTO> buscarTodosLosUsuarios()throws Exception{
        try{
            return mapa.convertirListaDTO(repositorio.findAll());

        }catch(Exception error){
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion()+error.getMessage());
        }
    }

    //servicio para buscar un usuario por ID
    public UsuarioDTO buscarUsuarioPorId(Integer id)throws Exception{
        try{
            Optional<Usuario> usuarioQueEstoyBuscando=this.repositorio.findById(id);
            if(usuarioQueEstoyBuscando.isPresent()){ //SI SI ESTA
                return mapa.convertirADTO(usuarioQueEstoyBuscando.get());
            }else{ //SI NO ESTA
                throw new Exception(MensajeError.ERROR_USUARIO_NO_ENCONTRADO.getDescripcion());
            }
        }catch(Exception error){
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion()+error.getMessage());
        }
    }

    //Servicio para buscar por correo
    public UsuarioDTO buscarUsuarioPorCorreo(String correo)throws Exception{
        try{
            Optional<Usuario> usuarioBuscado =this.repositorio.findByCorreo(correo);
            if(usuarioBuscado.isPresent()){
                return mapa.convertirADTO(usuarioBuscado.get());
            }else{
                throw new Exception(MensajeError.ERROR_USUARIO_NO_ENCONTRADO.getDescripcion());
            }
        }catch(Exception error){
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion()+error.getMessage());
        }
    }

    //servicio para buscar por nombre
    public List<UsuarioDTO> buscarUsuariosConNombre(String nombre)throws Exception{
        try{
            return mapa.convertirListaDTO(repositorio.findByNombreContaining(nombre));
        }catch(Exception error){
            throw new Exception(MensajeError.ERROR_GENERAL_USUARIO.getDescripcion()+error.getMessage());
        }
    }


}
