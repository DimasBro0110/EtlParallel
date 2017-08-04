package processor;

import models.FactAccount_Oper_CDW;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

/**
 * Created by DmitriyBrosalin on 03/08/2017.
 */
public class FactAccountOperCDWProcessor implements ItemProcessor<List<FactAccount_Oper_CDW>, List<FactAccount_Oper_CDW>> {
    @Override
    public List<FactAccount_Oper_CDW> process(List<FactAccount_Oper_CDW> factAccount_oper_cdws) throws Exception {
        factAccount_oper_cdws.forEach(obj -> obj.setFactAccountGround(""));
        return factAccount_oper_cdws;
    }
}
