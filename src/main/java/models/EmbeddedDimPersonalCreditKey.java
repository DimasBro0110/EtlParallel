package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DmitriyBrosalin on 04/08/2017.
 */

@Embeddable
public class EmbeddedDimPersonalCreditKey implements Serializable {

    @Column
    private long atomicKeys;

    @Column
    private String compositePersonalCreditRequestKey;

    @Column
    private String compositePersonalCustomerKey;

    @Column
    private String compositePersonalNumberOfDependents;

    @Column
    private String compositepersonalProductRequestKey;

    public String getCompositePersonalCreditRequestKey() {
        return compositePersonalCreditRequestKey;
    }

    public void setCompositePersonalCreditRequestKey(String compositePersonalCreditRequestKey) {
        this.compositePersonalCreditRequestKey = compositePersonalCreditRequestKey;
    }

    public String getCompositePersonalCustomerKey() {
        return compositePersonalCustomerKey;
    }

    public void setCompositePersonalCustomerKey(String compositePersonalCustomerKey) {
        this.compositePersonalCustomerKey = compositePersonalCustomerKey;
    }

    public String getCompositePersonalNumberOfDependents() {
        return compositePersonalNumberOfDependents;
    }

    public void setCompositePersonalNumberOfDependents(String compositePersonalNumberOfDependents) {
        this.compositePersonalNumberOfDependents = compositePersonalNumberOfDependents;
    }

    public String getCompositepersonalProductRequestKey() {
        return compositepersonalProductRequestKey;
    }

    public void setCompositepersonalProductRequestKey(String compositepersonalProductRequestKey) {
        this.compositepersonalProductRequestKey = compositepersonalProductRequestKey;
    }

    public long getAtomicKeys() {
        return atomicKeys;
    }

    public void setAtomicKeys(long atomicKeys) {
        this.atomicKeys = atomicKeys;
    }
}
