module Test1_4 where

data PriorityQueue k a = PriorityQueue [(k,a)]

empty = PriorityQueue []

insert ::  Ord k => k -> a -> PriorityQueue k a -> PriorityQueue k a
insert k a (PriorityQueue q) = PriorityQueue (insert' k a q) where
    insert' k a [] = [(k,a)]
    insert' k a s@((u,v):xs)
        | u == k = (k,a):xs
        | u < k = (k,a):s
        | otherwise = (u,v):(insert' k a xs)

lookup :: Ord k => k -> PriorityQueue k a -> Maybe a
lookup k (PriorityQueue []) = Nothing
lookup k (PriorityQueue ((u,v):xs))
    | k > u = Nothing
    | k == u = Just v
    | otherwise = Test1_4.lookup k (PriorityQueue xs)

isEmpty :: PriorityQueue k a -> Bool
isEmpty (PriorityQueue []) = True
isEmpty _ = False

toList :: PriorityQueue k a -> [(k,a)]
toList (PriorityQueue q) = q

instance (Show k, Show a) => Show (PriorityQueue k a) where
    show (PriorityQueue q) = show q

main = do
    putStrLn "Enter command:"
    putStrLn "0 - exit"
    putStrLn "1 - add value with priority"
    putStrLn "2 - get value by priority"
    putStrLn "3 - get value with max priority"
    putStrLn "4 - print queue"
    doLoop empty

doLoop queue = do
    cmd <- getLine
    case cmd of
        "0" -> return ()
        "1" -> do
            putStrLn "Enter the value:"
            value <- getLine
            putStrLn "Enter the priority:"
            priority <- getLine
            doLoop $ insert (read priority :: Int) value queue
        "2" -> do
            putStrLn "Enter the priority:"
            priority <- getLine
            putStrLn $ show $ Test1_4.lookup (read priority :: Int) queue
            doLoop queue
        "3" -> do
            putStrLn $
                if isEmpty queue
                then "Queue is empty"
                else "Value with max priority: " ++ (show $ snd $ head $ toList queue)
            doLoop queue
        "4" -> do
            putStrLn $ show queue
            doLoop queue
        _ -> doLoop queue