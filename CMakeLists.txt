cmake_minimum_required(VERSION 3.8)
project(Algorithm_AI_Design_Pattern)

set(CMAKE_CXX_STANDARD 17)

set(SOURCE_FILES main.cpp dictionary.hpp dictionary.cpp tree.hpp linked_list.hpp dp/list.hpp dp/iterator.hpp dp/point.hpp dp/rect.hpp dp/patterns/singleton.hpp)
add_executable(Algorithm_AI_Design_Pattern ${SOURCE_FILES})