package com.empresa.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empleado {

    private int id;
    private String  nombre;
    private String  email;
    private String puesto;
    private double salario;


}
