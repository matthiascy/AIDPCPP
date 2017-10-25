package dictionary;

import java.util.Arrays;

public class FastDictionary extends AbstractDictionary {
    public FastDictionary(int initSize) {
        super(initSize);
    }

    @Override
    int indexOf(Object key) {
        int index = key.hashCode() % _max_size;
        int i = index + 1;

        if (_keys[index] == key) {
            return index;
        }

        while (_keys[i] != key) {
            i = (i + 1) % _max_size;
            if (i == index) {
                return -1;
            }
        }

        return i;
    }

    @Override
    protected boolean insert(Object key, Object value) {
        if (must_grow()) {
            grow();
        }

        int index = calc_idx(key, _max_size);
        _keys[index] = key;
        _values[index] = value;
        ++_cur;

        return true;
    }

    private void grow() {
        int new_size = _max_size * 3;
        Object tmp_keys[] = new Object[++_max_size];
        Object tmp_values[] = new Object[_max_size];

        // recalculate the index of all key - value
        for (int i = 0; i < _max_size; ++i) {
            if (_keys[i] != null) {
                int index = calc_idx(_keys[i], new_size);
                tmp_keys[index] = _keys[i];
                tmp_values[index] = _values[i];
            }
        }

        _keys = tmp_keys;
        _values = tmp_values;

        _max_size = new_size;
    }

    private boolean must_grow() {
        return (float)_cur / (float)_max_size >= 0.75;
    }

    private int calc_idx(Object key, int maxSize) {
        int index = key.hashCode() % maxSize;

        // handle the conflict
        while (_values[index] != null) {
            index = (index + 1) % maxSize;
        }

        return index;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "Fast dictionary is empty.";
        else
            return "FastDictionary " + "{" + Arrays.toString(_keys) +
                    "-" + Arrays.toString(_values) + "}\n";
    }
}
