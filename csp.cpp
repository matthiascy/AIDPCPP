#include "csp.hpp"

int Constraint::getArity()
{
  return _vars.size();
}

std::string Constraint::getName()
{
  return _name;
}

std::vector<std::string> Constraint::getVars()
{
  return _vars;
}

std::ostream& Constraint::operator<<(const Constraint& c, std::ostream& os)
{
  os << "Constraint : " << _name << " with { "<< _vars << " }" << std::endl;

  return os;
}
