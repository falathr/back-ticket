package com.famisanar.req.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestionPersonasRequest {
    @NotEmpty(message = "El numIdentificacion es obligatorio")
    @Pattern(regexp = "^-?[0-9]+$", message = "El campo número de identificacion solo debe contener numeros")
    private String numIdentificacion;
    @NotEmpty(message = "El nombres es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo nombres solo debe contener letras")
    private String nombres;
    @NotEmpty(message = "El apellidos es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellido solo debe contener letras")
    private String apellidos;
    @NotEmpty(message = "El direccion es obligatorio")
    private String direccion;
    @NotEmpty(message = "El telefono es obligatorio")
    @Pattern(regexp = "^-?[0-9]+$", message = "El campo telefono solo debe contener numeros")
    private String telefono;
    @NotEmpty(message = "El email es obligatorio")
    @Email(message = "El correo no es valido")
    private String email;
    @NotEmpty(message = "El fechaNacimiento es obligatorio")
    private String fechaNacimiento;
    @NotEmpty(message = "El sexo es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El campo sexo solo debe contener letras")
    private String sexo;
    @NotEmpty(message = "El actividad es obligatorio")
    @Pattern(regexp = "^-?[1-2]+$", message = "El campo actividad  solo debe contener numeros")
    private String actividad;
    @NotEmpty(message = "El actividad es obligatorio")
    @Pattern(regexp = "^-?[1-2]+$", message = "El campo actividad  solo debe contener numeros")
    private String estado;
    public GestionPersonasRequest() {
    }

}
