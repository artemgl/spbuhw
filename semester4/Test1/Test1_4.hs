module Test1_4 where

supermap list f = foldl (\b a -> b ++ (f a)) [] list