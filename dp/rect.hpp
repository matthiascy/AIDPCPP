#ifndef ALGORITHM_AI_DESIGN_PATTERN_RECT_HPP
#define ALGORITHM_AI_DESIGN_PATTERN_RECT_HPP

#include "point.hpp"

namespace dp {

  class Rect {
  public:
    static const Rect Zero;

    Rect(Coord x, Coord y, Coord w, Coord h);
    Rect(const Point& origin, const Point& extent);

    Coord GetWidth() const;
    void SetWidth(Coord);
    Coord GetHeight() const;
    void SetHeight(Coord);
    Coord GetLeft() const;
    void SetLeft(Coord);
    Coord GetBottom() const;
    void SetBottom(Coord);

    Point& GetOrigin() const;
    void SetOrigin(const Point&);
    Point& GetExtent() const;
    void SetExtent(const Point&);

    void MoveTo(const Point&);
    void MoveBy(const Point&);

    bool IsEmpty() const;
    bool Contains(const Point&) const;
  };

}

#endif //ALGORITHM_AI_DESIGN_PATTERN_RECT_HPP
