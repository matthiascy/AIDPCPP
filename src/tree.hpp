namespace bluebell {

  template <typename DataType>
  struct TreeNode {
    DataType* data;
    TreeNode* left;
    TreeNode* right;

    explicit TreeNode() {
      left = nullptr;
      right = nullptr;
      data = nullptr;
    }

    explicit TreeNode(const DataType& d, TreeNode* l, TreeNode* r) {
      data = &d;
      left = l;
      right = r;
    }

    ~TreeNode() {
      delete left;
      left = nullptr;
      delete right;
      right = nullptr;
      delete data;
      data = nullptr;
    }
  };

  template <typename DataType>
  struct tree_interface {
    virtual ~tree_interface() = default;
    virtual bool insert(const DataType& item) = 0;
    virtual bool remove(const DataType& item) = 0;
    virtual DataType retrieve(const DataType& item) const = 0;
    virtual constexpr bool empty() const = 0;
  };

  template <typename DataType>
  class Tree : public tree_interface<DataType> {
  public:
    explicit Tree() : _root(nullptr) { };

    virtual ~Tree() {
      cleanup();
    };

    bool insert(const DataType& item) override {
      if (_root == nullptr) {
        _root->data = item;
        _root->left = _root->right = nullptr;
        return true;

      } else {
        return append(_root, item);
      }
    }

    bool remove(const DataType& item) override {
      TreeNode* tmp = nullptr;
      if (_root == nullptr) {
        return false;

      } else {
        tmp = search(_root, item);

        if (tmp) {
          eliminate(tmp);
          return true;
        } else {
          return false;
        }
      }
    }

    DataType retrieve(const DataType& item) const {
      return search(_root, item);
    }

    constexpr bool empty() const override {
      return _root == nullptr;
    }

    virtual void print();

  protected:
    TreeNode<DataType>* _root;

    virtual bool append(TreeNode* root, const DataType& item) { }

    virtual bool eliminate(const DataType& item) { }

    virtual TreeNode* search(TreeNode* root, const DataType& item) const { }

    virtual void cleanup() { }
  };


  template <typename DataType>
  class BinarySearchTree : public Tree<DataType> {
  public:
    explicit BinarySearchTree() = default;
    ~BinarySearchTree() = default;

    void print() override;

  protected:
    bool append(TreeNode* root, const DataType& item) override;

    bool eliminate(const DataType& item) override;

    TreeNode* search(TreeNode* root, const DataType& item) const override;

    void cleanup() override;
  };

  template <typename DataType>
  void BinarySearchTree<DataType>::print()
  {
    
  }

  template <typename DataType>
  bool BinarySearchTree<DataType>::append(TreeNode* root, const DataType& item)
  {
    if (item < root->data) {
      // insert to left tree
      if (root->left == nullptr) {
        root->left = new TreeNode<DataType>{item, nullptr, nullptr};
        return true;
      } else {
        return append(root->left, item);
      }
    } else {
      // insert to right tree
      if (root->right == nullptr) {
        root->right = new TreeNode<DataType>{item, nullptr, nullptr};
        return true;
      } else {
        return append(root->right, item);
      }
    }
  }

  template <typename DataType>
  bool BinarySearchTree::eliminate(const DataType& item) {
    return Tree::eliminate(item);
  }

  template <typename DataType>
  TreeNode* BinarySearchTree::search(TreeNode* root, const DataType& item) const {
    if (root->data == item) {
      return root;
    } else if (root->left != nullptr) {
      search(root->left, item);
    } else if (root->right != nullptr) {
      search(root->right, item);
    }
    return nullptr;
  }

  template <typename DataType>
  void BinarySearchTree::cleanup() {
    Tree::cleanup();
  }

  template <typename DataType>
  using BST = BinarySearchTree<DataType>

  class BTree;

  class BStarTree;

  class BPlusTree;

  class TrieTree;

}