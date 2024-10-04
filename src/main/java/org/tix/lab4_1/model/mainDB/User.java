package org.tix.lab4_1.model.mainDB;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tix.lab4_1.model.util.Role;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlElement
    private Long id;

    @XmlElement
    private String username;

    @XmlElement
    private String name;

    @XmlElement
    private String surname;

    @XmlElement
    private String email;

    @XmlElement
    private String password;

    @XmlElement
    private String passport;

    @XmlElement
    private Double salary;

    @XmlElement
    private Boolean is_fill;


    @XmlElement
    private Role role;




}
