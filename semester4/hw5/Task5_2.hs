module Task5_2 where

import Prelude

multList n = [1..n] >>= (\x -> map (*x) [1..n])