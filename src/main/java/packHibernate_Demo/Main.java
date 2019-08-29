package packHibernate_Demo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        StudentDao dao = new StudentDao();
//
//        dao.saveOrUpdate(new Student(null, "Marcin", true, 50, 2.3));
//        dao.saveOrUpdate(new Student(null, "Marek", true, 45, 2.3));
//        dao.saveOrUpdate(new Student(null, "Janek", true, 21, 2.3));
//
//        dao.getAll().forEach(System.out::println);
//
//        // usuń po id
//        dao.delete(3L);
//
//        // znajdź i usuń jeśli jest
//        Optional<Student> studentDelete = dao.getById(2L);
//        if (studentDelete.isPresent()) {
//            dao.delete(studentDelete.get());
//        }



        EntityDao dao = new EntityDao();

        Scanner scanner = new Scanner(System.in);

        String komenda;
        do {
            komenda = scanner.nextLine();

            if (komenda.equalsIgnoreCase("dodajS")) {
                // dodaj studenta
                dodajStudenta(dao);
            } else if (komenda.equalsIgnoreCase("dodajG")) {
//                // dodaj Grade
//                Long id = null;
//                GradeSubject[] tab = GradeSubject.values();
//                for (GradeSubject gradeSubject : tab) {
//                    System.out.print(gradeSubject + " ");
//                }
//                System.out.println("Podaj subject:");
//                String subjectString = scanner.nextLine();
//                GradeSubject gradeSubject = GradeSubject.valueOf(subjectString);
                dodajGrade(dao);
            } else if (komenda.equalsIgnoreCase("listS")) {
                System.out.println();
                dao.getAll(Student.class).forEach(System.out::println);
            } else if (komenda.equalsIgnoreCase("listG")) {
                System.out.println();
                dao.getAll(Grade.class).forEach(System.out::println);
            }
        } while (!komenda.equalsIgnoreCase("quit"));
    }

//    private static void dodajStudenta(EntityDao dao) {
//        Student student = new Student();
//
//        System.out.println("Podaj imie:");
//        student.setName(scanner.nextLine());
//        System.out.println("Podaj wiek");
//        student.setAge(scanner.nextLine());
//        System.out.println("Podaj średnią");
//        student.setAverage();
//        System.out.println("Podaj czy żyje");
//        student.setAlive();
//
//        dao.saveOrUpdate(student);
//    }

    private static void dodajStudenta(EntityDao dao) {
        Student student = new Student();
        System.out.println("Podaj imie:");
        student.setName(scanner.nextLine());
        System.out.println("Podaj wiek:");
        student.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.println("Podaj srednia:");
        student.setAverage(Double.valueOf(scanner.nextLine()));
        System.out.println("Podaj czy zyje:");
        student.setAlive(Boolean.parseBoolean(scanner.nextLine()));
        dao.saveOrUpdate(student);
    }
    private static void dodajGrade(EntityDao dao) {
        // Na poczatek pobieramy studenta. Jeśli uda się go znależć, to przechodzimy do oceny
        System.out.println("Podaj id studenta:");
        Long idStudent = Long.valueOf(scanner.nextLine());
        Optional<Student> studentOptional = dao.getById(Student.class, idStudent);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            Grade grade = new Grade();
            grade.setStudent(student);
            System.out.println("Podaj przedmiot:");
            grade.setSubject(GradeSubject.valueOf(scanner.nextLine()));
            System.out.println("Podaj ocene:");
            grade.setValue(Double.parseDouble(scanner.nextLine()));

            dao.saveOrUpdate(grade);
        }
    }
}
