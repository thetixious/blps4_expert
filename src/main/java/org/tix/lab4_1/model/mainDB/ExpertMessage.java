package org.tix.lab4_1.model.mainDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "message")
public class ExpertMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long creditOfferId;
    private String candidateName;
    private String candidateSurname;
    private String candidatePassport;
    private Double candidateCreditLimit;
    private Double candidateSalary;
    @ElementCollection(targetClass = Cards.class, fetch = FetchType.EAGER)
    private Set<Cards> preferredCards = new HashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ExpertMessage that = (ExpertMessage) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


    @Override
    public String toString() {
        return "ExpertMessage{" +
                "id=" + id +
                ", userId=" + userId +
                ", creditOfferId=" + creditOfferId +
                ", candidateName='" + candidateName + '\'' +
                ", candidateSurname='" + candidateSurname + '\'' +
                ", candidatePassport='" + candidatePassport + '\'' +
                ", candidateCreditLimit=" + candidateCreditLimit +
                ", candidateSalary=" + candidateSalary +
                ", preferredCards=" + preferredCards +
                '}';
    }
}

