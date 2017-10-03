#include <iostream>
#include <string>
#include <vector>
#include <map>


class Assignment;
class BufferedReader;


class Constraint {
public:
  explict Constraint(const std::vector<std::string>& vars) : _name("C"), _vars(vars) {
    ++_num;
    _name += std::to_string(num);
  }

  Constraint(const std::vector<std::string>& vars, const std::string name) : _name(name), _vars(vars) {
    ++_num;
  }

  /*
   * Constructor from a BufferedReader.
   */

  friend std::ostream& operator<<(const Constraint& c, std::ostream& os);

  virtual ~Constraint();

  int getArity();

  std::string getName();

  std::vector<std::string> getVars();

  virtual bool violation(Assignment a);

protected:
  static int _num;
  std::string _name;
  std::vector<string> _vars;
};

class Object;

class Assignment : public std::map<std::string, Object> {
public:
  explicit Assignment() = default;
  Assignment(const Assignment& a) = default;

  std::vector<std::string> getVars() {
    std::vector<std::string> vec{};

    std::transfrom(this->begin(), this->end(), std::back_inserter(vec), this->second());
  }
};

class Network {
public:
  explicit Network() {
    _domaines = new std::map<std::string, std::vector<Object>>;
    _constraints = new std::vector<Constraint>;disp
  }

private:
  std::unique_ptr<std::map<std::string, std::vector<Object>>> _domaines;
  std::unique_ptr<std::vector<Constraint>> _constraints;
};


class CSP {
public:
  explicit CSP(const Network n) {
    net
  }

private:
  Network _network;
  std::vector<Assignment> _solutions;
  Assignment _assignment;
  int _explored_nodes;
}
