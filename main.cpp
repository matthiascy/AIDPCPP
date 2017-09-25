#include <iostream>
#include <vector>
#include "dictionary.hpp"

using namespace dictionary;


int main() {
  std::cout << "Hello, World!" << std::endl;
  OrderedDictionary od(8);

  Record records[6] = {{"v1", "111"}, {"v2", "222"},
                       {"v3", "333"}, {"v4", "444"},
                       {"v5", "5555"}, {"v6", "666"}};

  Record records1[6] = {{"a", "111"}, {"g", "222"},
                       {"x", "333"}, {"f", "444"},
                       {"b", "555"}, {"e", "666"}};

  SortedDictionary sd(10);

  for (auto& r : records)
    od.put(r.key, r.value);

  for (auto& r : records1) {
    sd.put(r.key, r.value);
    sd.print();
    std::cout << "--------------" << std::endl;
  }
    //od.addRecord(r);

  od.put("p1", "put value one");

  if (!od.isEmpty())
    od.print();

  std::cout << "SortedDictionary is outputing..." << std::endl;
  if (!sd.isEmpty())
    sd.print();

  std::cout << "the value is : " << od.get("v1") << std::endl;

  return 0;
}