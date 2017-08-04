package writer;

/**
 * Created by dmitriybrosalin on 02.08.17.
 */

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import models.DimPersonalCreditRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by DmitriyBrosalin on 20/07/2017.
 */
public class DimPersonalCreditRequestWriter implements ItemWriter<DimPersonalCreditRequest> {

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong idGenerator;
    private static Logger LOGGER = Logger.getLogger(DimPersonalCreditRequestWriter.class.getName());

    @Override
    public void write(List<? extends DimPersonalCreditRequest> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(obj -> {
            obj.setEntityId(idGenerator.getAndIncrement());
            session.save(obj);
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
        LOGGER.log(Level.INFO, this.threadName + " " +
                "BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE DimPersonalCreditRequestWriter");

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(AtomicLong idGenerator) {
        this.idGenerator = idGenerator;
    }
}