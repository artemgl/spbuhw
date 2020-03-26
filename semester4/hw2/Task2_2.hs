module Task2_2 where

import Prelude

powsOf2 :: Int -> [Int]
powsOf2 0 = []
powsOf2 n = 2 : (map (*2) (powsOf2 (n - 1)))