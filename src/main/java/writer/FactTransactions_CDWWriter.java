package writer;

/**
 * Created by dmitriybrosalin on 02.08.17.
 */
import models.FactTransactions_CDW;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactTransactions_CDWWriter implements ItemWriter<FactTransactions_CDW>{

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicLong;
    private static Logger LOGGER = Logger.getLogger(FactTransactions_CDWWriter.class.getName());

    @Override
    public void write(List<? extends FactTransactions_CDW> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> {
            entity.setEntityId(atomicLong.getAndIncrement());
            session.save(entity);
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
//        sessionFactory.close();
        LOGGER.log(Level.INFO, threadName + " " + "BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE FactTransactions_CDWWriter");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getAtomicLong() {
        return atomicLong;
    }

    public void setAtomicLong(AtomicLong atomicLong) {
        this.atomicLong = atomicLong;
    }
}