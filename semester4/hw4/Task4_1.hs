module Task4_1 where

import Prelude

sumOfEven1 = sum . map (\x -> 1 - x `mod` 2)

sumOfEven2 = length . filter (\x -> x `mod` 2 == 0)

sumOfEven3 :: Integral a => [a] -> a
sumOfEven3 = foldr (\x y -> y + 1 - x `mod` 2) 0