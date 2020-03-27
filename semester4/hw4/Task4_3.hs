module Task4_3 where

data Tree a = Null | Leaf a | Branch (Tree a) a (Tree a)

instance Foldable Tree where
    foldr f b tree = case tree of
        Null -> b
        Leaf a -> f a b
        Branch aL a aR -> foldr f (f a $ foldr f b aR) aL

getList :: Tree a -> [a]
getList x = foldr (:) [] x