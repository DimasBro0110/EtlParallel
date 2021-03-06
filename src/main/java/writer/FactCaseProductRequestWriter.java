package writer;

/**
 * Created by dmitriybrosalin on 02.08.17.
 */

import models.FactCaseProductRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmitriybrosalin on 21.07.17.
 */
public class FactCaseProductRequestWriter implements ItemWriter<FactCaseProductRequest>{

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicLongFactCaseProductRequestKey;
    private static Logger LOGGER = Logger.getLogger(FactCaseProductRequestWriter.class.getName());

    @Override
    public void write(List<? extends FactCaseProductRequest> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> {
            entity.setEntityId(atomicLongFactCaseProductRequestKey.getAndIncrement());
            session.save(entity);
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
//        sessionFactory.close();
        LOGGER.log(Level.INFO, threadName + " " +
                "BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE FactCaseProductRequestWriter");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setAtomicLongFactCaseProductRequestKey(AtomicLong atomicLongFactCaseProductRequestKey) {
        this.atomicLongFactCaseProductRequestKey = atomicLongFactCaseProductRequestKey;
    }
}