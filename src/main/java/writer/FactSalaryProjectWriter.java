package writer;

/**
 * Created by dmitriybrosalin on 01.08.17.
 */

import models.FactSalaryProject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactSalaryProjectWriter implements ItemWriter<FactSalaryProject> {

    private SessionFactory sessionFactory;
    private String threadName;
    private AtomicLong atomicLongFactSalaryProjectKey;
    private static Logger LOGGER = Logger.getLogger(FactSalaryProjectWriter.class.getName());

    @Override
    public void write(List<? extends FactSalaryProject> list) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list.forEach(entity -> {
            entity.setEntityId(atomicLongFactSalaryProjectKey.getAndIncrement());
            session.save(entity);
        });
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
//        sessionFactory.close();
        LOGGER.log(Level.INFO, threadName + " " +
                "[ BATCH WITH SIZE OF " + list.size() + " SENT TO TABLE FactSalaryProject]");
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public AtomicLong getAtomicLongFactSalaryProjectKey() {
        return atomicLongFactSalaryProjectKey;
    }

    public void setAtomicLongFactSalaryProjectKey(AtomicLong atomicLongFactSalaryProjectKey) {
        this.atomicLongFactSalaryProjectKey = atomicLongFactSalaryProjectKey;
    }
}