import com.hyout.dao.LogEventMapper;
import com.hyout.pojo.Order;
import com.hyout.util.SmallTools;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/8/28.
 */
public class Test {
    public static void main(String[] args){
        String s = "a=1&sign=";
        System.out.println(SmallTools.lastSplitString(s,"="));
    }
}
