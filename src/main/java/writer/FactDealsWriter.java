package writer;

/**
 * Created by dmitriybrosalin on 02.08.17.
 */
import models.FactDeals;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmitriybrosalin on 21.07.17.
 */
public class FactDealsWriter implements ItemWriter<FactDeals> {

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicLongFactDealsWriterKey;
    private static Logger LOGGER = Logger.getLogger(FactDealsWriter.class.getName());

    @Override
    public void write(List<? extends FactDeals> list) throws Exception {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> {
            entity.setEntityId(atomicLongFactDealsWriterKey.getAndIncrement());
            session.save(entity);
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
//        sessionFactory.close();
        LOGGER.log(Level.INFO, threadName + " "
                + "BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE FactDealsWriter");

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getAtomicLongFactDealsWriterKey() {
        return atomicLongFactDealsWriterKey;
    }

    public void setAtomicLongFactDealsWriterKey(AtomicLong atomicLongFactDealsWriterKey) {
        this.atomicLongFactDealsWriterKey = atomicLongFactDealsWriterKey;
    }
}