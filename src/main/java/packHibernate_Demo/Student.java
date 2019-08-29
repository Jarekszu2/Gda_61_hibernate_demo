package packHibernate_Demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // to jest encja bazodanowa
public class Student implements IBaseEntity {
    @Id
    // dodatkowa tabela "hibernate sequence" - sztuczne identyfikatory
    // identity - generuj identyfikator z bazy, pobierz go i przypisz do pola
    // sequence - pobierz identyfikator, przypisz go do pola, zapisz obiekt
    // table -
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    private boolean alive; // "not null" bo jak nyśmy chcieli z nullem to Boolean
    private int age;

    @Formula(value = "(SELECT AVG(g.value) FROM Grade g WHERE g.student_id = id)") // AND g.subject = HISTORY
    private Double average; // nullable - nie ma "not null"

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Grade> gradeList;

    // wewnątrz modelu może istnieć tylko jedna relacja fetch type eager z listą
}
