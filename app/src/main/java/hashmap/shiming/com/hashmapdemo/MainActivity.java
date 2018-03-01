package hashmap.shiming.com.hashmapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * HasmMap的
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, String> integerIntegerHashMap = new HashMap<>();
        for (int i=0;i<100;i++){
            integerIntegerHashMap.put("key"+i,"value"+i+"");
        }
       String s="";
        s.hashCode();
        for (int i=0;i<integerIntegerHashMap.getSize();i++){
            System.out.println("shiming value" +integerIntegerHashMap.get("key"+i));
        }

    }
}
