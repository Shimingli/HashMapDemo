package hashmap.shiming.com.hashmapdemo;

import java.util.ArrayList;

/**
 * author： Created by shiming on 2018/2/28 16:02
 * mailbox：lamshiming@sina.com
 */

public class HashMap<K,V> implements Map<K,V> {

    private static int defaultLength=1 << 4;//底层数组的默认长度为16
   //以加载因子的大小需要结合时间和空间效率考虑。
    private static double defaultLoader=.75f;//默认的负载因子.  超过3/4的时候，扩容，为了更高的效率获取数组

    private HashMapEntry<K,V,Entry<K, V>>[] table=null;

    private int size=0;
    private HashMapEntry<K, V, Entry<K, V>>[] mHashMapEntries;

    public HashMap() {
        this(defaultLoader,defaultLength);
    }

    public HashMap(double defaultLoader, int defaultLength) {
        HashMap.defaultLength = defaultLength;
        HashMap.defaultLoader = defaultLoader;
        table= new HashMapEntry[defaultLength];
    }
    private int getIndex(K k){
        int m=defaultLength;
        //这里算出来有可能为负数
        return k.hashCode()%m>=0?k.hashCode()%m:-k.hashCode()%m;
    }
    @Override
    public V put(K k, V v) {
        //判断是否需要扩容
        if (size>=defaultLength*defaultLoader){
            upSize();
        }
        //创建一个hash函数，根据key和hash函数算出来数组下表
        int index = getIndex(k);
        Entry<K, V> kvEntry = table[index];
        //kvEntry 为null 的，说明table的index位置上没有元素
        if (kvEntry==null){
            //给与元素
            table[index]= (HashMapEntry<K, V, Entry<K, V>>) getEntry(k,  v, null);
            size++;
        }else {
            //有元素，就必须要进行一个替换，
            table[index]= (HashMapEntry<K, V, Entry<K, V>>) getEntry(k, v, kvEntry);
        }
        return table[index].getValue();
    }

    /**
     * 数据扩容table,扩容完成了，进行位置的重新的分配
     * 再散列
     */
    private void upSize() {
        //新创建数组，要对原来的数组在散列的动作
        mHashMapEntries = new HashMapEntry[2 * defaultLength];
        toHash();
    }
    private void toHash(){
        ArrayList<HashMapEntry<K, V,Entry<K,V>>> entries = new ArrayList<>();
        for (int i=0;i<table.length;i++){
            if (table[i]==null){
                continue;
            }
            findEntryByNext(table[i],entries);
        }
        //拿到数据了
        if (entries.size()>0){
            //在散列
            size=0;
            defaultLength=defaultLength*2;
            table=mHashMapEntries;
            for (HashMapEntry<K, V,Entry<K,V>> hashMapEntry:entries){
                if (hashMapEntry.next!=null){
                    //以前的指向的数据不准确了
                    hashMapEntry.next=null;
                }
                put(hashMapEntry.getKey(),hashMapEntry.getValue());
            }
        }
    }

    private void findEntryByNext(HashMapEntry<K, V,Entry<K, V>> entry, ArrayList<HashMapEntry<K, V,Entry<K,V>>> entries) {
        if (entry!=null&&entry.next!=null){
            entries.add(entry);
            findEntryByNext((HashMapEntry<K, V, Entry<K, V>>) entry.next,entries);
        }else {
            entries.add(entry);
        }

    }

    @Override
    public V get(K k) {
        //不同的K可能取出来的值可能是一样的
        int index = getIndex(k);
        if (table[index]==null){
            return null;
        }
        return findValueEqualKey(k,table[index]);
    }
    public V findValueEqualKey(K k, HashMapEntry<K, V, Entry<K, V>> kvEntryHashMapEntry){
        if (k==kvEntryHashMapEntry.getKey()||k.equals(kvEntryHashMapEntry.getKey())){
            return kvEntryHashMapEntry.getValue();
        }else {
            if (kvEntryHashMapEntry.next!=null){
                return findValueEqualKey(k, (HashMapEntry<K, V, Entry<K, V>>) kvEntryHashMapEntry.next);
            }
        }
        return kvEntryHashMapEntry.getValue();
    }
    private Entry<K,V> getEntry(K k,V v ,Entry<K,V> entry){
        return new HashMapEntry<K, V, Entry<K,V>>(k,v,entry);
    }
    @Override
    public int getSize() {
        return size;
    }
    class HashMapEntry<K, V, E> implements Entry<K,V>{
        K k;
        V v;
        Entry<K,V> next;

        public HashMapEntry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
