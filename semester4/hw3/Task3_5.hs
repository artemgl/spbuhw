module Task3_5 where

import Prelude

fib = fib' 0 1 where
                   fib' a _ 0 = a
                   fib' _ b 1 = b
                   fib' a b n = if n<0
                                then fib' (b-a) a (n+1)
                                else fib' b (a+b) (n-1)