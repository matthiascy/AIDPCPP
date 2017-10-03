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
      auto* tmp_keys = new std::string[++_max_size];
      auto* tmp_values = new std::string[_max_size];

      for (int i = 0; i < _cur; ++i) {
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
    std::uint32_t index = std::hash<std::string>{}(key) % _max_size;
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
    if (must_grow()) {
      grow();
    }

    int index = calculate_index(key, _max_size);
    _keys[index] = key;
    _values[index] = value;
    ++_cur_size;
  }

  int FastDictionary::calculate_index(const std::string& key, const int max_size) const {
    std::uint32_t hash_code = std::hash<std::string>{}(key);

    int index = hash_code % max_size;

    // handle the conflict
    while (!_values[index].empty()) {
      index = (index+1) % max_size;
    }
  }

  int FastDictionary::size() const {
    return _cur_size;
  }

  bool FastDictionary::must_grow() {
    return static_cast<float>(_cur_size) / static_cast<float>(_max_size) >= 0.75;
  }

  bool FastDictionary::grow()
  {
    int new_size = _max_size * 3;
    auto* tmp_keys = new std::string[new_size];
    auto* tmp_values = new std::string[new_size];

    // recalculate the index of all key - value
    for (int i = 0; i < _max_size; ++i) {
      if (!_keys[i].empty()) {
        int index = calculate_index(_keys[i], new_size);
        tmp_keys[index] = _keys[i];
        tmp_values[index] = _values[i];
      }
    }

    delete [] _keys;
    delete [] _values;

    _keys = tmp_keys;
    _values = tmp_values;

    _max_size = new_size;
  }

}  // !namespace dictionary
