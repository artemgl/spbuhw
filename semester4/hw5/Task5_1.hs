module Task5_1 where

import Control.Monad
import System.IO

main = do
    putStrLn "Enter a number:"
    number <- getLine
    putStrLn "Different sums:"
    printSums $ sums (read number :: Int)

sums :: Int -> [[Int]]
sums 1 = []
sums n = [1..n `div` 2] >>= \k -> map (k:) $ specialSums k $ [n-k] : sums (n-k) where
    specialSums 1 = id
    specialSums k = filter cond where
        cond sum = not $ foldl (||) False $ [1..(k-1)] >>= (\x -> [x `elem` sum])

printSums :: [[Int]] -> IO ()
printSums [] = return ()
printSums (x:xs) = do
    putStrLn $ foldl (\a b -> a ++ "+" ++ show b ) (show $ head x) (tail x)
    printSums xs