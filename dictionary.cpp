#include "dictionary.hpp"

namespace dictionary {

  int OrderedDictionary::index_of(const std::string& key) const {
    if (_cur >= 0) {
      for (int i = 0; i <= _cur; ++i) {
        if (_keys[i] == key)
          return i;
      }
    } else {
      return -1;
    }
  }

  bool OrderedDictionary::insert(const std::string& key, const std::string& value) {

    int index = index_of(key);

    if (index != -1) {
      _keys[index] = key;
      _values[index] = value;
    }

    if (_cur < _max_size-1) {
      ++_cur;
      _keys[_cur] = key;
      _values[_cur] = value;
      return true;
    } else {
      std::string* tmp_keys = new std::string[++_max_size];
      std::string* tmp_values = new std::string[_max_size];

      for (int i = 0; i < _cur, ++i) {
        tmp_keys[i] = _keys[i];
        tmp_values[i] = _values[i];
      }

      delete [] _keys;
      delete [] _values;

      _keys = tmp_keys;
      _values = tmp_values;

      ++_cur;
      _keys[_cur] = key;
      _values[_cur] = value;

      return true;
    }
  }

  void OrderedDictionary::print() {
    if (isEmpty())
      std::cout << "Ordered dictionary is empty." << std::endl;
    else
      for (int i = 0; i <= _cur; ++i) {
        std::cout << "{" << _keys[i] << "-" << _values[i] << "}" << std::endl;
      }
  }

  int SortedDictionary::index_of(const std::string& key) const {
    if (_cur >= 0) {
        for (int i = 0; i <= _cur; ++i) {
          if (_keys[i] == key)
            return i;
        }
      } else {
        return -1;
      }
  }

  bool SortedDictionary::insert(const std::string& key, const std::string& value) {
    if (_cur < _max_size-1) {
      if (_cur == -1) {
        _keys[++_cur] = key;
        _values[_cur] = value;
        return true;
      }

      int index = 0;
      for (int i = 0; i <= _cur; ++i) {
        if (_keys[i] >= key) {
          index = i;
          break;
        }
      }

      if (index == 0) {
        ++_cur;
        _keys[_cur] = key;
        _values[_cur] = value;
      } else {
        for (int i = ++_cur; i > index; --i) {
          _keys[i] = _keys[i - 1];
          _values[i] = _values[i - 1];
        }
        _keys[index] = key;
        _values[index] = value;
      }
      return true;
    } else {
      return false;
    }
  }

  void SortedDictionary::print() {
    if (isEmpty())
      std::cout << "Sorted dictionary is empty." << std::endl;
    else
      for (int i = 0; i <= _cur; ++i) {
        std::cout << "{" << _keys[i] << "-" << _values[i] << "}" << std::endl;
      }
  }

  void FastDictionary::print() {
    if (isEmpty())
      std::cout << "Fast dictionary is empty." << std::endl;
    else
      for (int i = 0; i <= _cur; ++i) {
        std::cout << "{" << _keys[i] << "-" << _values[i] << "}" << std::endl;
      }
  }

  int FastDictionary::index_of(const std::string& key) const {
    int index = hash_fn(key) % _max_size;
    int i = index + 1;

    if (_keys[index] == key) {
      return index;
    }

    while(_keys[i] != key) {
      i = (i + 1) % _max_size;
      if (i == index) {
        return -1;
      }
    }

    return i;
  }

  bool FastDictionary::insert(const std::string& key, const std::string& value) {
    if (mustGrow()) {
      grow();
    }

    int index = calculate_index(key);
    _keys[index] = key;
    _values[index] = value;
    ++_cur_size;
  }

  int FastDictionary::calculate_index(const std::string& key) const {
    size_t hash_code = hash_fn(key);

    int index = hash_code % _max_size;

    // handle the conflict
    while (_values[index]) {
      index = (index+1) % _max_size;
    }
  }

  int FastDictionary::size() override {
    return _cur_size;
  }

  bool FastDictionary::must_grow() {
    if (static_cast<float>(_cur_size) / static_cast<float>(_max_size) >= 0.75) {
      return true;
    } else {
      return false;
    }
  }

  bool FastDictionary::grow() {
    std::string* tmp_keys = new std::string[_max_size * 3];
    std::string* tmp_values = new std::string[_max_size * 3];

    for (int i = 0; i < _max_size; ++i) {
      tmp_keys[i] = _keys[i];
      tmp_values[i] = _values[i];
    }

    _keys = tmp_keys;
    _values = tmp_values;
    _max_size *= 3;
  }

}  // !namespace dictionary
