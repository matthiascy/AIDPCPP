#include "dictionary.hpp"

int OrderedDictionary::indexOf(const std::string& key) const {
  for (int i = 0; i < kSize; ++i) {
    if (_keys[i] == key)
      return i;
    else
      return -1;
  }
}

bool OrderedDictionary::insert(const std::string& key, const std::string& value) {
  if (_cur < kSize) {
    _keys[++_cur] = key;
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
    for (int i = 0; i < _cur; ++i) {
      std::cout << "{" << _keys[i] << "-" << _values[i] << "}" << std::endl;
    }
}