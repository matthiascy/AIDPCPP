#ifndef ALGORITHM_AI_DESIGN_PATTERN_DP_ITERATOR_HPP
#define ALGORITHM_AI_DESIGN_PATTERN_DP_ITERATOR_HPP

namespace dp {

  template<typename ItemType>
  class Iterator {
  public:
    /*
     * Positions the iterator to the first object in the aggregate.
     */
    virtual void First() = 0;

    /*
     * Positions the iterator to the next object in the sequence.
     */
    virtual void Next() = 0;

    /*
     * Returns true when there are no more objects in the sequence.
     */
    virtual bool Empty() const = 0;

    /*
     * Returns the object at the current position in the sequence.
     */
    virtual ItemType CurrentItem() const = 0;

  protected:
    Iterator() = default;
  };

}

#endif //ALGORITHM_AI_DESIGN_PATTERN_DP_ITERATOR_HPP
