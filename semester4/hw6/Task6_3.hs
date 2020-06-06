module Task6_3 where

import Data.List(sortBy)
import Data.Map
import Data.Function(on)
import Control.Monad

data Graph v e = Graph [(Int,v)] [(Int,Int,e)]
    deriving (Show)

search (Graph vl el) src = toList $ handleUnvisited (Prelude.map fst vl) el $ insert src (0,[[src]]) empty

handleUnvisited [] _ map = map
handleUnvisited notVisited@(nv:nvs) el map =
    let
        toVisit = (Prelude.filter (`elem` notVisited) . Prelude.map fst . sortBy (compare `on` fst.snd) . toList) map
        v = if toVisit == []
            then nv
            else head toVisit
        notVisited' = Prelude.filter (/=v) notVisited
    in handleUnvisited notVisited' el (handleNeighbours notVisited' v el map)

handleNeighbours _ _ [] map = map
handleNeighbours nvs v ((a,b,length):es) map
    | v == a && b `elem` nvs = case (Data.Map.lookup a map, Data.Map.lookup b map) of
        (Just (len,paths), Just (len',paths')) | (len+length < len') -> handleNeighbours nvs v es $ insert b (len+length,Prelude.map (++[b]) paths) map
        (Just (len,paths), Just (len',paths')) | (len+length == len') -> handleNeighbours nvs v es $ insert b (len',(Prelude.map (++[b]) paths) ++ paths') map
        (Just (len,paths), Nothing) -> handleNeighbours nvs v es $ Data.Map.insert b (len+length,Prelude.map (++[b]) paths) map
        _ -> handleNeighbours nvs v es map
    | otherwise = handleNeighbours nvs v es map

printPaths [] = return ()
printPaths ((v,(len,paths)):ps) = do
    putStrLn $ "to " ++ show v ++ " (min length = " ++ show len ++ "):"
    forM_ paths (putStrLn.show)
    printPaths ps

graph1 = Graph [(1,"a"), (2,"b"), (3,"c"), (4,"d"), (5,"e")] [(1,2,10), (1,3,3), (5,2,1), (3,5,4), (5,4,2), (4,2,2)]
graph2 = Graph [(1,"a"), (2,"b"), (3,"c"), (4,"d"), (5,"e"), (6,"f")] [(1,3,9), (1,2,7), (1,6,14), (3,6,2), (3,4,11), (6,5,9), (5,4,6), (2,4,15), (2,3,10)]
graph3 = Graph [(3,"c"), (1,"a"), (5,"e"), (2,"b"), (4,"d")] [(1,2,1), (3,4,1), (2,3,1), (3,5,1), (1,4,1), (4,5,1), (2,5,1), (1,3,1)]
graph4 = Graph [(1,"a"), (2,"b"), (3,"c")] [(1,2,2), (1,3,1), (3,2,1)]

test1 = printPaths $ search graph1 1
test2 = printPaths $ search graph2 1
test3 = printPaths $ search graph3 1
test4 = printPaths $ search graph4 1