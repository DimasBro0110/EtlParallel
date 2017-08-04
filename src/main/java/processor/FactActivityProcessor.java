package processor;

import models.FactActivity;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

/**
 * Created by DmitriyBrosalin on 03/08/2017.
 */

public class FactActivityProcessor implements ItemProcessor<List<FactActivity>, List<FactActivity>>{
    @Override
    public List<FactActivity> process(List<FactActivity> factActivities) throws Exception {
        factActivities.forEach(obj -> {
            obj.setFactActivityDescription("");
            obj.setFactActivitySubject("");
            obj.setFactActivitySource("");
        });
        return factActivities;
    }
}
