package com.empresa.model;


import lombok.Data;

import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@Data
public class Empleado {

    private int id;
    private String  nombre;
    private String  apellido;
    private String especialidad;
}
