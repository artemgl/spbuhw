module Task3_1 where

import Prelude

func :: Num a => a -> [a] -> [a]
{-
func x l = map (\y -> y*x) l
func x = map (\y -> y*x)
func x = map (*x)
func x = map . (*) x
func = map . (*)
-}
func = map . (*)