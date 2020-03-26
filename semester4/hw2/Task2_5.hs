module Task2_5 where

import Prelude

sumOfLists :: (Num a) => [a] -> [a] -> [a] -> [a]
sumOfLists [] [] [] = []
sumOfLists (a:at) (b:bt) (c:ct) = (a + b + c) : sumOfLists at bt ct
sumOfLists [] (a:at) (b:bt) = (a + b) : sumOfLists [] at bt
sumOfLists [] [] (a:at) = a : sumOfLists [] [] at
sumOfLists a [] b = sumOfLists [] a b
sumOfLists a b [] = sumOfLists [] a b
sumOfLists [] a [] = sumOfLists [] [] a
sumOfLists a [] [] = sumOfLists [] [] a