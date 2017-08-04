package processor;

import models.DimPersonalCreditRequest;
import org.springframework.batch.item.ItemProcessor;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by DmitriyBrosalin on 03/08/2017.
 */
public class DimPersonalCreditRequestProcessor implements ItemProcessor<DimPersonalCreditRequest, DimPersonalCreditRequest> {

    @Override
    public DimPersonalCreditRequest process(DimPersonalCreditRequest dimPersonalCreditRequest) throws Exception {
        dimPersonalCreditRequest.setPersonalMcsEmployerName("");
        return dimPersonalCreditRequest;
    }
}
