package writer;

/**
 * Created by dmitriybrosalin on 01.08.17.
 */

import models.DimCustomer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by DmitriyBrosalin on 19/07/2017.
 */
public class DimCustomerWriter implements ItemWriter<DimCustomer> {

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicDimCustomerKey;
    private static Logger LOGGER = Logger.getLogger(DimCustomerWriter.class.getName());

    @Override
    public void write(List<? extends DimCustomer> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> {
            entity.setEntityId(atomicDimCustomerKey.getAndIncrement());
            session.save(entity);
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
        LOGGER.log(Level.INFO, threadName + " " + "BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE DIM_CUSTOMER");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getAtomicDimCustomerKey() {
        return atomicDimCustomerKey;
    }

    public void setAtomicDimCustomerKey(AtomicLong atomicDimCustomerKey) {
        this.atomicDimCustomerKey = atomicDimCustomerKey;
    }
}
