cmake_minimum_required(VERSION 3.8)
project(Algorithm_AI_Design_Pattern)

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(src)
add_subdirectory(test)

set(SOURCE_FILES main.cpp dictionary.hpp dictionary.cpp tree.hpp linked_list.hpp)
add_executable(Algorithm_AI_Design_Pattern ${SOURCE_FILES})