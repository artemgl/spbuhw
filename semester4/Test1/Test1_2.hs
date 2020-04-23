module Test1_2 where

mat n = [[k | x <- [1..k]] ++ [k+1..n] | k <- [1..n]]