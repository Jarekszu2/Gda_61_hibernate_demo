package packHibernate_Demo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        dao.getAll().forEach(System.out::println);
    }
}
