module Test1_1 where

minOne = 1 : map (*(-1)) minOne

minNum = zipWith (*) minOne [1..]