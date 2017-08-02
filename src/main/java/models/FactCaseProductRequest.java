package models;

/**
 * Created by dmitriybrosalin on 02.08.17.
 */
import javax.persistence.*;

@Entity
@Table(name = "FACT_CASE_PRODUCT_REQUEST")
public class FactCaseProductRequest {

    @Id
    private long entityId;

    @Column(name = "FACT_CASE_KEY")
    private String factCaseKey;

    @Column(name = "FACT_CASE_PRODUCT_REQUEST_KEY")
    private String factCaseProductRequestKey;

    public String getFactCaseKey() {
        return factCaseKey;
    }

    public void setFactCaseKey(String factCaseKey) {
        this.factCaseKey = factCaseKey;
    }

    public String getFactCaseProductRequestKey() {
        return factCaseProductRequestKey;
    }

    public void setFactCaseProductRequestKey(String factCaseProductRequestKey) {
        this.factCaseProductRequestKey = factCaseProductRequestKey;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}