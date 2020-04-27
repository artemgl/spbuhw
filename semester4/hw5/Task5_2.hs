module Task5_2 where

import Prelude

multList n = [1..n] >>= \k -> [1..n] >>= return . (*k)