import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design and implement an in-memory LRU (Least Recently Used) Cache.

Functional Requirements
Cache has fixed capacity N.

Support operations:
get(key) → returns value or -1
put(key, value) → inserts/updates key

When capacity is full:
Evict the least recently used entry.
Every get() should make that key most recently used.

 */

class LRUCache {

    Map<Integer, Integer> map; // store key and values
    List<Integer> list; // store keys only, last is more recent, first is least used
    int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        int idx = list.indexOf(key);
        //System.out.println("Removing key - " + key + ", at : " + idx);
        list.remove(idx);
        list.addLast(key);
        return map.get(key);
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            int idx = list.indexOf(key);
            list.remove(idx);
            list.addLast(key);
            map.put(key, value);
        } else {
            map.put(key, value);
            list.addLast(key);
            if(this.cap < map.size()){
                int firstKey = list.removeFirst();
                map.remove(firstKey);
            }
        }
    } 
}

/*(
Thread safety -- 
Reenterant Lock - Multiple Threads can aquire same lock
Read Write Lock - user can read, but not write - read heavy

Segmenting - Distribute LRU Cache
Each segment will have its own LRU running + Locking
Async Processing to co-odinate - Zookeeper
Consistent hashing for the key
High Latency 
Accuracy Loss

TTL / Expiry 
Eviction Policy and startegy 

) */
