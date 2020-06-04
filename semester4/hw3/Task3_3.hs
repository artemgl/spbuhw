module Task3_3 where

import Prelude

fstPos [] = 0
fstPos s@(x:xs) = fstPos' s x 0 0 where
    fstPos' (a:b:xs) max index ans = let sum = a + b in
        if sum > max
        then fstPos' (b:xs) sum (index + 1) (index + 1)
        else fstPos' (b:xs) max (index + 1) ans
    fstPos' _ _ _ ans = ans