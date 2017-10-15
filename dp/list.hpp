#ifndef ALGORITHM_AI_DESIGN_PATTERN_DP_LIST_HPP
#define ALGORITHM_AI_DESIGN_PATTERN_DP_LIST_HPP

#include "iterator.hpp"

namespace dp {

#define DEFAULT_LIST_CAPACITY 16

  template<typename ItemType>
  class List {
  public:
    explicit List(int size = DEFAULT_LIST_CAPACITY);

    explicit List(const List&);

    List& operator=(const List&);

    ~List();

    /* Accessing */
    /*
     * Returns the number of objects in the list.
     */
    int Count() const;

    /*
     * Returns the object at the given index.
     */
    ItemType& Get(int index) const;

    /*
     * Returns the first object in the list.
     */
    ItemType& First() const;

    /*
     * Returns the last object in the list.
     */
    ItemType& Last() const;

    bool Includes(const ItemType&) const;


    /* Adding */
    /*
     * Adds the argument to the list, making it the last element.
     */
    void Append(const ItemType&);

    /*
     * Adds the argument to the list, making it the first element.
     */
    void Prepend(const ItemType&);


    /* Removing */
    /*
     * Removes the given element from the list. This operation requires
     * that the type of elements int the list supports the == operator for
     * comparision.
     */
    void Remove(const ItemType&);

    /*
     * Removes the first element from the list.
     */
    void RemoveLast();

    /*
     * Removes the last element from the list.
     */
    void RemoveFirst();

    /*
     * Removes all elements from the list.
     */
    void RemoveAll();


    /* Stack interface */
    /*
     * Returns the top element (when the list is viewed as a stack).
     */
    ItemType& Top() const;

    /*
     * Pushes the element onto the stack.
     */
    void Push(const ItemType&);

    /*
     * Pops the top element from the stack.
     */
    ItemType& Pop();
  };

  template<typename ItemType>
  class ListIterator : public Iterator<ItemType> {
  public:
    explicit ListIterator(const List<ItemType>* list);

    virtual void First();

    virtual void Next();

    virtual bool Empty() const;

    virtual ItemType CurrentItem() const;
  };
}  // !namespace dp

#endif //ALGORITHM_AI_DESIGN_PATTERN_DP_LIST_HPP
