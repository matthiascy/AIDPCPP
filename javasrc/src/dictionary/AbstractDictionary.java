package dictionary;

import java.util.Arrays;

public abstract class AbstractDictionary implements IDictionary {
    AbstractDictionary(int initSize) {
        _max_size = (initSize > 0 ? initSize : 1);

        _keys = new Object[_max_size];
        _values = new Object[_max_size];
        _cur = -1;    /* Current cursor which indicates current size of inner storage */
    }

    protected abstract boolean insert(Object key, Object value);

    @Override
    public boolean isEmpty() {
        return _cur == -1;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.indexOf(key) != -1;
    }

    @Override
    public Object get(Object key) {
        int index = indexOf(key);

        return index == -1 ? null : _values[index];
    }

    @Override
    public boolean put(Object key, Object value) {
        return insert(key, value);
    }

    @Override
    public int size() {
        return _cur == -1 ? 0 : _cur;
    }

    int indexOf(Object key) {
        System.out.println("indexOf - AbstractDictionary");
        if (_cur >= 0) {
            for (int i = 0; i <= _cur; ++i) {
                if (_keys[i] == key)
                    return i;
            }
        }

        return -1;
    }

    Object[] _keys;
    Object[] _values;
    int _cur;
    int _max_size;
}
