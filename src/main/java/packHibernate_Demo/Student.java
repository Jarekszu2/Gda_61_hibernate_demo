package packHibernate_Demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // to jest encja bazodanowa
public class Student {
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
    private Double average; // nullable - nie ma "not null"
}
