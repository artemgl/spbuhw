module Task2_1 where

import Prelude

reverse' :: [a] -> [a]
reverse' = foldl (flip (:)) []