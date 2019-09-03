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
    private boolean alive; // "not null" bo jak byśmy chcieli z nullem to Boolean
    private int age;

    @Formula(value = "(SELECT AVG(g.value) FROM Grade g WHERE g.student_id = id)") // AND g.subject = HISTORY
    private Double average; // "nullable = false" == "not null" - piszemy dla Stringa; w reszcie rozpoznaje się: int, Integer

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER) // można fetch nie ustawiać, standard to: FetchType.LAZy; eager na wszytko to np. wczytanie całego Facebooka
    private Set<Grade> gradeList; // gdy gradeList to Lista nie Set to nie trzeba Exlud na HashCode - to jest, żeby się nie zapętlił przy selekcie w kółka szukając danych dla Greda i sprawdzając czy się nie powtórzyły w Studencie... stack overflow

    // wewnątrz modelu może istnieć tylko jedna relacja fetch type eager z listą
    // może istnieć nieskończenie wiele relacji typu eager z Setem
}
