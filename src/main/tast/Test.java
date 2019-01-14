import com.google.gson.JsonObject;
import com.hyout.dao.LogEventMapper;
import com.hyout.pojo.Order;
import com.hyout.util.SmallTools;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/8/28.
 */
public class Test {
    public static void main(String[] args){
        JsonObject jo = new JsonObject();
        jo.addProperty("a","1");

        System.out.println(jo.toString());
    }
}
