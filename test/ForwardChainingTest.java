import com.fc.ForwardChainigAlgorithm;
import org.junit.Assert;
import org.junit.Test;

public class ForwardChainingTest {
    @Test
    public void shouldReturnTrueIfThe(){
        ForwardChainigAlgorithm forwardChainigAlgorithm=new ForwardChainigAlgorithm("(P=>Q),(L,M=>P),(B,L=>M),(P,A=>L),(A,B=>L),(A),(B)");
        forwardChainigAlgorithm.execute();
    }
}
