module Task6_1 where

data Polynom a = Null | a :+: Polynom a

instance Show a => Show (Polynom a) where
    show Null = ""
    show (a:+:p) = show a ++ show' p 1 where
        show' Null _ = ""
        show' (a:+:p) n = " + " ++ show a ++ " * x^" ++ show n ++ show' p (n+1)

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