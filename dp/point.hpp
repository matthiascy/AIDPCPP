#ifndef ALGORITHM_AI_DESIGN_PATTERN_DP_POINT_HPP
#define ALGORITHM_AI_DESIGN_PATTERN_DP_POINT_HPP

#include <iostream>

namespace dp {

  typedef float Coord;

  class Point {
  public:
    static const Point Zero;

    explicit Point(Coord x = 0.0, Coord y = 0.0);

    Coord GetX() const;
    void SetX(Coord x);
    Coord GetY() const;
    void SetY(Coord y);

    friend Point operator+(const Point&, const Point&);
    friend Point operator-(const Point&, const Point&);
    friend Point operator*(const Point&, const Point&);
    friend Point operator/(const Point&, const Point&);

    Point& operator+=(const Point&);
    Point& operator-=(const Point&);
    Point& operator*=(const Point&);
    Point& operator/=(const Point&);

    Point operator-();

    friend bool operator==(const Point&, const Point&);
    friend bool operator!=(const Point&, const Point&);

    friend std::ostream& operator<<(std::ostream&, const Point&);
    friend std::istream& operator>>(std::istream&, Point&);

  private:
    Coord x;
    Coord y;
  };

  //Point::Zero

}

#endif //ALGORITHM_AI_DESIGN_PATTERN_DP_POINT_HPP
