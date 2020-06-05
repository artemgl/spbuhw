module Task5_4 where

data Operation = Plus | Minus | Multiply | Divide
    deriving (Eq)

data Operand a = Const a | Variable
    deriving (Eq)

data Tree a = Leaf (Operand a) | Branch (Tree a) Operation (Tree a)
    deriving (Eq)

main = do
    putStrLn "Enter an expression:"
    inf <- getLine
    putStrLn $ "Derivative: " ++ (show $ reduce $ diff $ reduce $ getTree $ getPrefix $ inf)

instance Show Operation where
    show opn = case opn of
        Plus -> "+"
        Minus -> "-"
        Multiply -> "*"
        Divide -> "/"

instance (Show a) => Show (Operand a) where
    show opnd = case opnd of
        Const a -> show a
        Variable -> "x"

instance (Show a) => Show (Tree a) where
    show tree = case tree of
        Leaf a -> show a
        Branch tL opn tR -> "(" ++ show tL ++ " " ++ show opn ++ " " ++ show tR ++ ")"

apply opn (Const a) (Const b) = case opn of
    Plus -> Leaf (Const (a + b))
    Minus -> Leaf (Const (a - b))
    Multiply -> Leaf (Const (a * b))
    Divide -> Leaf (Const (a / b))
apply opn x y = Branch (Leaf x) opn (Leaf y)

reduce tree = case tree of
    Branch tL opn tR -> let
        newTL = reduce tL
        newTR = reduce tR in
            case (opn, newTL, newTR) of
                (Plus, x, Leaf (Const 0)) -> x
                (Plus, Leaf (Const 0), y) -> y
                (Plus, x, y) ->
                    if x == y
                    then Branch (Leaf (Const 2)) Multiply x
                    else Branch newTL opn newTR
                (Minus, x, Leaf (Const 0)) -> x
                (Multiply, _, Leaf (Const 0)) -> Leaf (Const 0)
                (Multiply, Leaf (Const 0), _) -> Leaf (Const 0)
                (Multiply, x, Leaf (Const 1)) -> x
                (Multiply, Leaf (Const 1), y) -> y
                (Divide, x, Leaf (Const 1)) -> x
                (Divide, Leaf (Const 0), _) -> Leaf (Const 0)
                (_, Leaf opnd1, Leaf opnd2) -> apply opn opnd1 opnd2
                _ -> Branch newTL opn newTR
    _ -> tree

diff tree = case tree of
    Leaf opnd -> case opnd of
        Const a -> Leaf (Const 0)
        Variable -> Leaf (Const 1)
    Branch tL opn tR -> case opn of
        Plus -> Branch (diff tL) opn (diff tR)
        Minus -> Branch (diff tL) opn (diff tR)
        Multiply -> Branch (Branch (diff tL) Multiply (tR)) Plus (Branch (tL) Multiply (diff tR))
        Divide -> Branch (Branch (Branch (diff tL) Multiply (tR)) Minus (Branch (tL) Multiply (diff tR))) Divide (Branch (tR) Multiply (tR))

getPrefix inf = getPrefix' inf [] [] where
    getPrefix' [] [] res = res
    getPrefix' [] (y:ys) res = getPrefix' [] ys ([y]:res)
    getPrefix' (x:xs) stack res
        | x `elem` "+-*/" = loopOperator x xs stack res
        | x == '(' = getPrefix' xs (x:stack) res
        | x == ')' = loopBracket xs stack res
        | otherwise = loopOperand [x] xs stack res
    
    loopOperator x xs [] res = getPrefix' xs [x] res
    loopOperator x xs stack res = let pop = head stack in
        if priority pop < priority x
        then getPrefix' xs (x:stack) res
        else loopOperator x xs (tail stack) ([pop]:res)
    
    loopBracket xs [] res = getPrefix' xs [] res
    loopBracket xs stack res = let pop = head stack in
        if pop == '('
        then getPrefix' xs (tail stack) res
        else loopBracket xs (tail stack) ([pop]:res)
    
    loopOperand operand [] stack res = getPrefix' [] stack (operand:res)
    loopOperand operand (x:xs) stack res =
        if x `elem` "+-*/()"
        then getPrefix' (x:xs) stack (operand:res)
        else loopOperand (operand ++ [x]) xs stack res

    priority a
        | a `elem` "+-" = 0
        | a `elem` "*/" = 1
        | otherwise = -1

getTree [] = Leaf (Const 0)
getTree pref = getTree' (reverse pref) [] where
    getTree' [] stack = head stack
    getTree' (x:xs) stack = case x of
        "+" -> getTree' xs $ (Branch (head $ tail stack) Plus (head stack)):(tail $ tail stack)
        "-" -> getTree' xs $ (Branch (head $ tail stack) Minus (head stack)):(tail $ tail stack)
        "*" -> getTree' xs $ (Branch (head $ tail stack) Multiply (head stack)):(tail $ tail stack)
        "/" -> getTree' xs $ (Branch (head $ tail stack) Divide (head stack)):(tail $ tail stack)
        "x" -> getTree' xs $ (Leaf Variable):stack
        op -> getTree' xs $ (Leaf (Const (read op :: Double))):stack