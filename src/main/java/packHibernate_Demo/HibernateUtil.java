package packHibernate_Demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;

public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }
}
