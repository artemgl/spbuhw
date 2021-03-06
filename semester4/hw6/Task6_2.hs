module Task6_2 where

import System.Random
import Control.Monad.State
import Data.List(sort)

data BST a = Null | Branch (BST a) a (BST a)
    deriving (Show)

size :: BST a -> Int
size Null = 0
size (Branch tL _ tR) = size tL + 1 + size tR

insert :: Ord a => a -> BST a -> BST a
insert e Null = Branch Null e Null
insert e tree@(Branch tL a tR)
    | e < a = Branch (insert e tL) a tR
    | e > a = Branch tL a (insert e tR)
    | otherwise = tree

remove :: Ord a => a -> BST a -> BST a
remove _ Null = Null
remove e (Branch tL a tR)
    | e < a = Branch (remove e tL) a tR
    | e > a = Branch tL a (remove e tR)
    | otherwise = case (tL, tR) of
        (Null, _) -> tR
        (_, Null) -> tL
        _ ->
            let
                (a', tL') = seizeMax tL
                seizeMax (Branch left el Null) = (el, left)
                seizeMax (Branch left el right) = (fst pr, Branch left el (snd pr)) where
                    pr = seizeMax right
            in Branch tL' a' tR
        

exists :: Ord a => a -> BST a -> Bool
exists e Null = False
exists e (Branch tL a tR)
    | e == a = True
    | e < a = exists e tL
    | otherwise = exists e tR

height :: BST a -> Int
height Null = 0
height (Branch tL a tR) = 1 + max (height tL) (height tR)

getAny :: Random a => State StdGen a
getAny = do
    g <- get
    (x,g') <- return $ random g
    put g'
    return x

getOne :: Random a => (a,a) -> State StdGen a
getOne bounds = do
    g <- get
    (x,g') <- return $ randomR bounds g
    put g'
    return x

getRandomTree :: Random a => BST a -> State StdGen (BST a)
getRandomTree Null = return Null
getRandomTree (Branch tL a tR) = do
    tL' <- getRandomTree tL
    a' <- getAny
    tR' <- getRandomTree tR
    return $ Branch tL' a' tR'

getRandomRTree :: Random a => (a,a) -> BST a -> State StdGen (BST a)
getRandomRTree bounds Null = return Null
getRandomRTree bounds (Branch tL a tR) = do
    tL' <- getRandomRTree bounds tL
    a' <- getOne bounds
    tR' <- getRandomRTree bounds tR
    return $ Branch tL' a' tR'

randomize :: Random a => BST a -> StdGen -> BST a
randomize = evalState . getRandomTree

randomizeR :: Random a => (a, a) -> BST a -> StdGen -> BST a
randomizeR = (evalState .) . getRandomRTree --(\bounds tree g -> evalState (getRandomRTree bounds tree) g)

fromList :: Ord a => [a] -> BST a
fromList =
    let
        fromList' [] = Null
        fromList' list = Branch (fromList left) mid (fromList right) where
            left = take ((n - 1) `div` 2) list
            right = drop ((n + 1) `div` 2) list
            mid = head $ drop ((n - 1) `div` 2) list
            n = length list
    in fromList' . sort

tree1 = fromList [1..15] :: BST Int
tree2 = Branch (Branch (Branch Null 3 (Branch Null 5 Null)) 8 (Branch (Branch Null 11 (Branch Null 14 Null)) 27 Null)) 32 (Branch Null 39 (Branch (Branch Null 41 Null) 50 Null))  :: BST Int

testRandomize1 = randomize tree1 $ mkStdGen 1
testRandomize2 = randomize tree2 $ mkStdGen 3
testRandomizeR1 = randomizeR (0,100) tree1 $ mkStdGen 2
testRandomizeR2 = randomizeR (100,200) tree2 $ mkStdGen 4