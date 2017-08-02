package writer;

/**
 * Created by dmitriybrosalin on 02.08.17.
 */

import models.FactIBLoginHistory;
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
public class FactIbLoginHistoryWriter implements ItemWriter<FactIBLoginHistory> {

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicLongFactIbLoginHistoryKey;
    private static Logger LOGGER = Logger.getLogger(FactIbLoginHistoryWriter.class.getName());

    @Override
    public void write(List<? extends FactIBLoginHistory> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> {
            entity.setEntityId(atomicLongFactIbLoginHistoryKey.getAndIncrement());
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
        LOGGER.log(Level.INFO, threadName + " " +
                "BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE FactIbLoginHistoryWriter");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getAtomicLongFactIbLoginHistoryKey() {
        return atomicLongFactIbLoginHistoryKey;
    }

    public void setAtomicLongFactIbLoginHistoryKey(AtomicLong atomicLongFactIbLoginHistoryKey) {
        this.atomicLongFactIbLoginHistoryKey = atomicLongFactIbLoginHistoryKey;
    }
}