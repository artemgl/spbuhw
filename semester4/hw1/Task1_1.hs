module Task1_1 where

import Prelude

factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)