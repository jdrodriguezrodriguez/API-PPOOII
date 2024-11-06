
package com.PPOOII.Laboratorio.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

/**
 *
 * @author juand
 */

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L; 
            
    @Id
    private Long idpersona;
    
    private String login;
    private String password;
    private String apikey;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "idpersona", referencedColumnName = "id")
    private Persona persona;

    
    public Usuario() {}
     
    public Usuario(String login, String password, String apikey){
        this.login = login;
        this.password = password;
        this.apikey = apikey;
    }
    
    /*@PrePersist
    public void generateAPIKey() {
        this.apikey = UUID.randomUUID().toString();     //GENERAR VALOR ALFANUMERICO CON UUID
    }*/
    
    // Getters y setters manuales (opcional debido a @Data)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
}
