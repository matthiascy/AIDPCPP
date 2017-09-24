#include <iostream>
#include <string>


struct Record {
  std::string key;
  std::string value;
};

struct dictionary_interface {
  virtual ~dictionary_interface() = default;
  virtual bool isEmpty() const = 0;
  virtual bool containsKey(std::string key) const = 0;
  virtual std::string get(std::string key) const = 0;
  virtual int put(std::string key, std::string value) = 0;
};


class Dictionary : public dictionary_interface {
public:
  Dictionary() {
    _keys = nullptr;
    _values = nullptr;
    _cur = -1;
  }

  ~Dictionary() override = default;

  bool isEmpty() const override {
    return (_keys == nullptr || _values == nullptr);
  }

  bool containsKey(std::string key) const override {
    return (this->indexOf(key) != -1);
  }

  std::string get(std::string key) const override {
    int index = indexOf(key);

    if (index == -1)
      return nullptr;
    else
      return _values[index];
  }

  int put(std::string key, std::string value) override {
    std::cout << "Not implemented error!!!" << std::endl;
  }

  virtual void print() { };

protected:
  std::string* _keys;
  std::string* _values;
  int _cur;

  virtual int indexOf(const std::string& key) const { }

  virtual bool insert(const std::string& key, const std::string& value) { }
};


class OrderedDictionary : public Dictionary {
public:
  explicit OrderedDictionary(std::uint8_t size) : kSize(size) {
    _keys = new std::string[kSize];
    _values = new std::string[kSize];
  }

  void print() override;

  void addRecord(const Record& r) {
    insert(r.key, r.value);
  }

private:
  const std::uint8_t kSize;

  int indexOf(const std::string& key) const override;

  bool insert(const std::string& key, const std::string& value) override;
};