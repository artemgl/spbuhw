module Task4_4 where

import Prelude
import System.IO
import Control.Exception

main = do
    putStrLn "Please enter a command:"
    putStrLn "0 - exit"
    putStrLn "1 - add a note (name and phone)"
    putStrLn "2 - find phone by name"
    putStrLn "3 - find name by phone"
    putStrLn "4 - save current data to file"
    putStrLn "5 - read data from file"
    contents <- readFromFile "phonebook.txt"
    doLoop [] contents

doLoop x contents = do
    cmd <- getLine
    case cmd of
        "0" -> return ()
        "1" -> do
            x' <- addNote x
            doLoop x' contents
        "2" -> do
            findPhone contents
            doLoop x contents
        "3" -> do
            findName contents
            doLoop x contents
        "4" -> do
            writeToFile "phonebook.txt" x
            contents' <- readFromFile "phonebook.txt"
            doLoop [] contents'
        "5" -> do
            putStrLn $ show contents
            doLoop x contents
        _ -> doLoop x contents

readFromFile fileName =
    bracket (openFile fileName ReadMode) hClose
        (\h -> do
            notes <- readNotes h
            return notes)
    where
        readNotes h = do
            isEOF <- hIsEOF h
            if isEOF
            then return []
            else do
                note <- readOneNote h
                nextNotes <- readNotes h
                return $ note:nextNotes
        readOneNote h = do
            name <- hGetLine h
            phone <- hGetLine h
            return (name, phone)

addNote x = do
    putStrLn "Enter the name:"
    name <- getLine
    putStrLn "Enter the phone:"
    phone <- getLine
    return $ (name, phone):x

findPhone contents = do
    putStrLn "Enter the name:"
    name <- getLine
    putStrLn $ show $ map snd $ filter ((==name).fst) contents

findName contents = do  
    putStrLn "Enter the phone:"
    phone <- getLine
    putStrLn $ show $ map fst $ filter ((==phone).snd) contents

writeToFile fileName notes =
    bracket (openFile fileName AppendMode) hClose
        (\h -> writeNotes h notes)
    where
        writeNotes _ [] = return ()
        writeNotes h (note:restNotes) = do
            hPutStrLn h $ fst note
            hPutStrLn h $ snd note
            writeNotes h restNotes