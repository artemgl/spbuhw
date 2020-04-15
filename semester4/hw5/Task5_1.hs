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
sums n = [1..n `div` 2] >>= \k -> map (k:) $ filter (\sum -> all (not.(`elem` sum)) [1..(k-1)]) $ [n-k] : sums (n-k)

printSums :: [[Int]] -> IO ()
printSums [] = return ()
printSums (x:xs) = do
    putStr $ show $ head x
    putStrLn $ concatMap (('+':).show) $ tail x
    printSums xs