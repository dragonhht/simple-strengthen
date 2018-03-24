package simple.strengthen.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-24
 */
@Data
public class AopModel {

    private String className;
    private String execut;
    private List<AdviceModel> advices = new ArrayList<>();

    public boolean addAdvice(AdviceModel advice) {
        return this.getAdvices().add(advice);
    }

}
