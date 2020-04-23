module Test1_3 where

import Control.Monad
import System.IO

rhombus n = forM_ ([1,3..2*n-1] ++ reverse [1,3..2*n-3]) (\k -> do
    forM_ [1..(2*n-1-k) `div` 2] $ const $ putStr " "
    forM_ [1..k] $ const $ putStr "X"
    forM_ [1..(2*n-1-k) `div` 2] $ const $ putStr " "
    putStrLn "")
    