package dictionary;

public interface IDictionary {
    boolean isEmpty();
    boolean containsKey(Object key);
    Object get(Object key);
    boolean put(Object key, Object value);
    int size();
}
