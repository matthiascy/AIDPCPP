#include <iostream>
#include <vector>
#include "dictionary.hpp"


int main() {
  std::cout << "Hello, World!" << std::endl;
  OrderedDictionary od(8);

  Record records[6] = {{"v1", "111"}, {"v2", "222"},
                       {"v3", "333"}, {"v4", "444"},
                       {"v5", "555"}, {"v6", "666"}};

  for (auto& r : records)
    od.addRecord(r);

  if (!od.isEmpty())
    od.print();

  std::cout << "the value is : " << od.get("v1") << std::endl;

  return 0;
}