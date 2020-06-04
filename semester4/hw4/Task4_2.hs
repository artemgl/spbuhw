module Task4_2 where

import Prelude
import Data.List

main = do
    putStrLn "Please enter a command:"
    putStrLn "0 - exit"
    putStrLn "1 - add value to sorted list"
    putStrLn "2 - remove value from list"
    putStrLn "3 - print list"
    doLoop []

doLoop x = do
    cmd <- getLine
    case cmd of
        "0" -> return ()
        "1" -> do
            x' <- addValue x
            doLoop x'
        "2" -> do
            x' <- removeValue x
            doLoop x'
        "3" -> do
            putStrLn $ show x
            doLoop x
        _ -> doLoop x

addValue x = do
    putStrLn "Enter the value to add:"
    val <- getLine
    return $ sort $ (read val :: Int):x

removeValue x = do
    putStrLn "Enter the value to remove:"
    val <- getLine
    return $ filter (/=(read val :: Int)) x