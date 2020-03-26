module Task3_4 where

import Prelude

sumOfEven1 = sum . map (\x -> 1 - x `mod` 2)

sumOfEven2 l = sum [1 | x <- filter (\x -> x `mod` 2 == 0) l]

sumOfEven3 :: [Int] -> Int
sumOfEven3 = foldr (\x y -> y + 1 - x `mod` 2) 0