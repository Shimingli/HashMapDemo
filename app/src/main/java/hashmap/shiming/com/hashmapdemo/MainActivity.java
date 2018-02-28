package hashmap.shiming.com.hashmapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * HasmMap的原理
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i=0;i<100000;i++){
            integerIntegerHashMap.put(i,i);
        }

        for (int i=0;i<integerIntegerHashMap.getSize();i++){

        }

    }
}
