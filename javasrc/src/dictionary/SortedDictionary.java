package dictionary;

import java.util.Arrays;

public class SortedDictionary extends AbstractDictionary {
    public SortedDictionary(int initSize) {
        super(initSize);
    }

    int indexOf(Comparable<Object> key) {
        System.out.println("indexOf - SortedDictionary");
        if (_cur >= 0) {
            for (int i = 0; i <= _cur; ++i) {
                if (key.compareTo(_keys[i]) <= 0)
                    return i;
            }
        }

        return -1;
    }

    @Override
    protected boolean insert(Object key, Object value) {
        if (_cur == -1) {
            _keys[++_cur] = key;
            _values[_cur] = value;
            return true;
        }

        // TODO: which indexOf is invoked ?
        int index = indexOf((Comparable)key);

        if (_cur >= _max_size - 1) {
            // resize the storage
            Object tmp_keys[] = new Object[++_max_size];
            Object tmp_values[] = new Object[_max_size];

            for (int i = 0; i <= _cur; ++i) {
                tmp_keys[i] = _keys[i];
                tmp_values[i] = _values[i];
            }

            _keys = tmp_keys;
            _values = tmp_values;
        }

        if (index != -1) {
            if (_keys[index] == key) {
                _values[index] = value;
            }

            for (int i = ++_cur; i > index; --i) {
                _keys[i] = _keys[i - 1];
                _values[i] = _values[i - 1];
            }

            _keys[index] = key;
            _values[index] = value;

            return true;

        } else {
            _keys[++_cur] = key;
            _values[_cur] = value;

            return true;
        }
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "Sorted dictionary is empty.";
        else
            return "SortedDictionary " + "{" + Arrays.toString(_keys) +
                    "-" + Arrays.toString(_values) + "}\n";
    }
}
