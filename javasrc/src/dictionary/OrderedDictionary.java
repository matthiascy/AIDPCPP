package dictionary;

import java.util.Arrays;

public class OrderedDictionary extends AbstractDictionary {
    public OrderedDictionary(int initSize) {
        super(initSize);
    }

    @Override
    protected boolean insert(Object key, Object value) {
        int index = indexOf(key);

        // The key already exists in the dictionary => modify the value
        if (index != -1) {
            //_keys[index] = key;
            _values[index] = _values;
        }

        // There is still enough space to store the key-value
        if (_cur < _max_size - 1) {
            _keys[++_cur] = key;
            _values[_cur] = value;
            return true;

        } else {
            // resize the inner storage arrays.
            Object tmp_keys[] = new Object[++_max_size];
            Object tmp_values[] = new Object[_max_size];

            for (int i = 0; i <= _cur; ++i) {
                tmp_keys[i] = _keys[i];
                tmp_values[i] = _values[i];
            }

            _keys = tmp_keys;
            _values = tmp_values;

            _keys[++_cur] = key;
            _values[_cur] = value;

            return true;
        }
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "Ordered dictionary is empty.";
        else
            return "OrderedDictionary " + "{" + Arrays.toString(_keys) +
                    "-" + Arrays.toString(_values) + "}\n";
    }
}
