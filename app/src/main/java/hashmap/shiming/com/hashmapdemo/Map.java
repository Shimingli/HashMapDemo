package hashmap.shiming.com.hashmapdemo;

/**
 * author： Created by shiming on 2018/2/28 15:56
 * mailbox：lamshiming@sina.com
 */

public interface Map<K,V> {
    V put(K k,V v);
    V get(K k);
    int getSize();

    public interface Entry<K,V> {
        public K getKey();
        public V getValue();
    }
}
