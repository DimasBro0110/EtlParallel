package writer;

/**
 * Created by dmitriybrosalin on 02.08.17.
 */

import models.FactActivity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmitriybrosalin on 22.07.17.
 */
public class FactActivityWriter implements ItemWriter<FactActivity>{

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicLongFactActivityKey;
    private static Logger LOGGER = Logger.getLogger(FactActivityWriter.class.getName());

    @Override
    public void write(List<? extends FactActivity> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> entity.setEntityId(atomicLongFactActivityKey.getAndIncrement()));
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
        LOGGER.log(Level.INFO, this.threadName + " " + "[ BATCH WITH SIZE OF "
                + list.size() + " SENT TO TABLE FactActivity]");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getAtomicLongFactActivityKey() {
        return atomicLongFactActivityKey;
    }

    public void setAtomicLongFactActivityKey(AtomicLong atomicLongFactActivityKey) {
        this.atomicLongFactActivityKey = atomicLongFactActivityKey;
    }
}