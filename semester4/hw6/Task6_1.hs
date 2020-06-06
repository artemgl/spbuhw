module Task6_1 where

data Polynom a = Null | a :+: Polynom a

instance (Num a, Ord a, Show a) => Show (Polynom a) where
    show p = showHelper p 0 where
        showHelper Null _ = ""
        showHelper (a:+:p) n
            | a == fromInteger 0 = showHelper p (n + 1)
            | otherwise = showFirst a n ++ showTail p (n + 1) where
            showFirst a n
                | n == 0 = show a
                | a < fromInteger 0 = "-" ++ showCoeff a ++ showDeg n
                | otherwise = showCoeff a ++ showDeg n
            showTail Null _ = ""
            showTail (a:+:p) n = showSummand a n ++ showTail p (n + 1)
            showSummand a n
                | a == fromInteger 0 = ""
                | otherwise = showSign a ++ showCoeff a ++ showDeg n
            showSign a
                | a < fromInteger 0 = " - "
                | otherwise = " + "
            showCoeff a
                | a == fromInteger (-1) || a == fromInteger 0 || a == fromInteger 1 = ""
                | a < fromInteger 0 = show (negate a)
                | otherwise = show a
            showDeg n
                | n == 0 = ""
                | n == 1 = "x"
                | otherwise = "x^" ++ show n

instance Num a => Num (Polynom a) where
    f + Null = f
    Null + g = g
    (f:+:fs) + (g:+:gs) = (f + g) :+: (fs + gs)
    
    f * Null = Null
    Null * g = Null
    (f:+:fs) * (g:+:gs) = (f * g) :+: (f .* gs + fs * (g :+: gs)) where
        k .* Null = Null
        k .* (f:+:fs) = (k * f) :+: (k .* fs)

toPolynom [] = Null
toPolynom (x:xs) = x :+: toPolynom xs