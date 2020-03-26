module Task3_4 where

import Prelude

areBracketsCorrect = g 0 0 0 [] . filter (`elem` "()[]{}")
                     where
                         g :: Int -> Int -> Int -> [Int] -> String -> Bool
                         g 0 0 0 _ [] = True
                         g _ _ _ _ [] = False
                         g a b c p (x:xs) =
                             case (x, hp) of
                                 ('(', _) -> g (a+1) b c (1:p) xs
                                 ('[', _) -> g a (b+1) c (2:p) xs
                                 ('{', _) -> g a b (c+1) (3:p) xs
                                 (')', 1) -> g (a-1) b c tp xs
                                 (']', 2) -> g a (b-1) c tp xs
                                 ('}', 3) -> g a b (c-1) tp xs
                                 otherwise -> False
                             where
                                 (hp, tp) = if p == [] then (0, []) else (head p, tail p)