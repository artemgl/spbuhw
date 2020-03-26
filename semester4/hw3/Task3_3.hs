module Task3_3 where

import Prelude

fstPos :: (Ord a, Num a) => [a] -> Int
fstPos [] = error "Empty list"
fstPos (x:[]) = 1
fstPos (x:y:[]) = if x > y then 2 else 1
fstPos (x:y:z:[]) = if x + z > y then 2 else 1
fstPos x = fstPos' (head x) 0 2 x where
                                fstPos' :: (Ord a, Num a) => a -> Int -> Int -> [a] -> Int
                                fstPos' a n m (x:y:[]) = n
                                fstPos' a n m (x:xs) = if sum > a
                                                       then k sum m (m+1) xs
                                                       else k a n (m+1) xs
                                                       where
                                                           sum = x + head (tail xs)