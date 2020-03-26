module Task2_4 where

import Prelude

fstOcc :: (Eq a) => a -> [a] -> Int
fstOcc _ [] = error "Value is not found"
fstOcc a (x:xs) = if a == x then 1 else 1 + fstOcc a xs