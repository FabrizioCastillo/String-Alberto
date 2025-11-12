package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("--------------------------------------------");
        System.out.println("* BIENVENIDO AL GESTOR DE TAREAS (DEV) *");
        System.out.println("* >> Entorno de Desarrollo Activo <<     *");
        System.out.println("---------------------------------------------");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("-------------------------------------------");
        System.out.println("* Cerrando aplicación...       *");
        System.out.println("* ¡Hasta luego DEV!         *");
        System.out.println("--------------------------------------------");
    }
}