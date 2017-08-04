package writer;

/**
 * Created by dmitriybrosalin on 01.08.17.
 */

import models.FactDLCards;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactDLCardsWriter implements ItemWriter<FactDLCards> {

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicLongFactDLCardsWriter;
    private static Logger LOGGER = Logger.getLogger(FactDLCardsWriter.class.getName());

    @Override
    public void write(List<? extends FactDLCards> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> {
            entity.setEntityId(atomicLongFactDLCardsWriter.getAndIncrement());
            session.save(entity);
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
//        sessionFactory.close();
        LOGGER.log(Level.INFO, threadName + " " +
                "[ BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE FactDLCards]");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getAtomicLongFactDLCardsWriter() {
        return atomicLongFactDLCardsWriter;
    }

    public void setAtomicLongFactDLCardsWriter(AtomicLong atomicLongFactDLCardsWriter) {
        this.atomicLongFactDLCardsWriter = atomicLongFactDLCardsWriter;
    }
}