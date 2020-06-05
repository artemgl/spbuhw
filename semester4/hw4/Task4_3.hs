module Task4_3 where

data Tree a = Null | Branch (Tree a) a (Tree a)

instance Foldable Tree where
    foldr f b tree = case tree of
        Null -> b
        Branch aL a aR -> foldr f (f a $ foldr f b aR) aL

getList :: Tree a -> [a]
getList x = foldr (:) [] x

tree = Branch (Branch (Branch Null 94 (Branch Null 21 Null)) 51 (Branch (Branch Null 32 (Branch Null 81 Null)) 40 Null)) 67 (Branch Null 75 (Branch (Branch Null 23 Null) 93 Null))

test = getList tree