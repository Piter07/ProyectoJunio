package com.roshka.bootcamp.ProyectoJunio.service;

public class ActivaCorreo {
    //actica el correo por el token
    public void activaCorreo(String token){
        //envia el correo
        getClass().getClassLoader().getResource("/templates/activacion.html");
    }
}
