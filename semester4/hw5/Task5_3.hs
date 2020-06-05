module Task5_3 where

import Control.Monad

g (a:b:c:xs) = mplus (takeIfBigger a b c) $ g (b:c:xs)
    where
        takeIfBigger a b c =
            if b > max a c
            then Just b
            else Nothing
g _ = Nothing

