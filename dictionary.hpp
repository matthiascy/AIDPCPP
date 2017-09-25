#include <iostream>
#include <string>

namespace dictionary {

  const int kInitSize = 64;

  struct Record {
    std::string key;
    std::string value;
  };

  struct IDictionary {
    virtual ~IDictionary() = default;

    virtual bool isEmpty() const = 0;

    virtual bool containsKey(const std::string& key) const = 0;

    virtual std::string get(const std::string& key) const = 0;

    virtual bool put(const std::string& key, const std::string& value) = 0;
  };


  class AbstractDictionary : public IDictionary {
  public:
    explicit AbstractDictionary() {
      _keys = nullptr;
      _values = nullptr;
      _cur = -1;
    }

    ~AbstractDictionary() override = default;

    bool isEmpty() const override {
      return (_keys == nullptr || _values == nullptr);
    }

    bool containsKey(const std::string& key) const override {
      return (this->index_of(key) != -1);
    }

    std::string get(const std::string& key) const override {
      int index = index_of(key);

      if (index == -1)
        return nullptr;
      else
        return _values[index];
    }

    bool put(const std::string& key, const std::string& value) override {
      return insert(key, value);
    }

    virtual void print() {};

  protected:
    std::string* _keys;
    std::string* _values;
    int _cur;

	virtual int index_of(const std::string& key) const { return -1; }

	virtual bool insert(const std::string& key, const std::string& value) { return false; }
  };

  class OrderedDictionary : public AbstractDictionary {
  public:
    explicit OrderedDictionary() : _size(kInitSize) {
      _keys = new std::string[_size];
      _values = new std::string[_size];
    }

    explicit OrderedDictionary(int size) {
      size > 0 ? _size = size : _size = kInitSize;
      _keys = new std::string[_size];
      _values = new std::string[_size];
    }

    ~OrderedDictionary() override {
      delete []_keys;
      delete []_values;
      _keys = nullptr;
      _values = nullptr;
    }

    void print() override;

  private:
    int _size;

    int index_of(const std::string& key) const override;

    bool insert(const std::string& key, const std::string& value) override;
  };

  class SortedDictionary : public AbstractDictionary {
  public:
    explicit SortedDictionary() : _size(kInitSize) {
      _keys = new std::string[_size];
      _values = new std::string[_size];
    }

    explicit SortedDictionary(int size) {
      size > 0 ? _size = size : _size = kInitSize;
      _keys = new std::string[_size];
      _values = new std::string[_size];
    }

    ~SortedDictionary() override {
      delete [] _keys;
      delete [] _values;
      _keys = nullptr;
      _values = nullptr;
    }

    void print() override;

  private:
    int _size;

    int index_of(const std::string& key) const override;

    bool insert(const std::string& key, const std::string& value) override;
  };

  class FastDictionary : public AbstractDictionary {
  public:
    explicit FastDictionary() : _size(kInitSize) {
      _keys = new std::string[_size];
      _values = new std::string[_size];
    }

    explicit FastDictionary(int size) {
      size > 0 ? _size = size : _size = kInitSize;
      _keys = new std::string[_size];
      _values = new std::string[_size];
    }

    ~FastDictionary() override {
      delete [] _keys;
      delete [] _values;
      _keys = nullptr;
      _values = nullptr;
    }

    void print() override;

  private:
    int _size;

    int index_of(const std::string& key) const override;

    bool insert(const std::string& key, const std::string& value) override;
  };
}  // !namespace dictionary