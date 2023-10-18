package com.famisanar.req.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestionPersonasUpRequest {

    @Pattern(regexp = "^-?[0-9]+$", message = "El campo número de identificacion solo debe contener numeros")
    private String numIdentificacion;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo nombres solo debe contener letras")
    private String nombres;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellido solo debe contener letras")
    private String apellidos;

    private String direccion;

    @Pattern(regexp = "^-?[0-9]+$", message = "El campo telefono solo debe contener numeros")
    private String telefono;

    @Email(message = "El correo no es valido")
    private String email;

    private String fechaNacimiento;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "El campo sexo solo debe contener letras")
    private String sexo;

    @Pattern(regexp = "^-?[1-2]+$", message = "El campo actividad  solo debe contener numeros")
    private String actividad;

    @NotEmpty(message = "El actividad es obligatorio")
    @Pattern(regexp = "^-?[1-2]+$", message = "El campo actividad  solo debe contener numeros")
    private String estado;

    public GestionPersonasUpRequest() {
    }
    

}
