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
    if (_cur < _size-1) {
      ++_cur;
      _keys[_cur] = key;
      _values[_cur] = value;
      return true;
    } else {
      return false;
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
    if (_cur < _size-1) {
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

}