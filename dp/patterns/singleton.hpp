#ifndef ALGORITHM_AI_DESIGN_PATTERN_DP_PATTERNS_SINGLETON_HPP
#define ALGORITHM_AI_DESIGN_PATTERN_DP_PATTERNS_SINGLETON_HPP

#include <memory>
#include <vector>

namespace dp {

  class singleton {
  public:
    static singleton* GetInstance();
  protected:
    singleton() = default;

  private:
    static singleton* _instance;
  };

  singleton* singleton::_instance = nullptr;

  singleton* singleton::GetInstance()
  {
    if (_instance == nullptr) {
      _instance = new singleton;
    }

    return _instance;
  }

  class singleton_registry {
  public:
    static void Register(const char* name, singleton_registry*);
    static singleton_registry* GetInstance();

  protected:
    static singleton_registry* lookup(const char* name);

  private:
    static singleton_registry* _instance;
    static std::vector<NameSingletonPair>* _registry;
  };

  singleton_registry* singleton_registry::GetInstance()
  {
    if (_instance == nullptr) {
      /* Get environment variable */
      const char* name = getenv("SINGLETON");
      _instance = lookup(name);
    }

    return _instance;
  }
}
#endif //ALGORITHM_AI_DESIGN_PATTERN_DP_PATTERNS_SINGLETON_HPP
