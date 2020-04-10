module Test1_5 where

areAll pred list = foldl (\b a -> b && (pred a)) True list