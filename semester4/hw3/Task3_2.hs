module Task3_2 where

import Prelude

osn = 1 : 7 : 9 : concatMap (\x -> map (10*x+) [1,7,9]) osn