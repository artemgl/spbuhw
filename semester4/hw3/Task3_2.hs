module Task3_2 where

import Prelude

ost = 1 : map next' ost 
    where
        next' 1 = 7
        next' 7 = 9
        next' 9 = 11
        next' x = case (m) of
                    9 -> 1 + 10 * next' d
                    _ -> 10 * d + next' m
                  where
                    d = div x 10
                    m = mod x 10