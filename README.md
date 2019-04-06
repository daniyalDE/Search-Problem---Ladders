# Search-Problem-Ladders


## Introduction
Ladders is a word game where a player tries to get from one word (the “start word”) to another (the
“goal word”) in as few steps as possible. In each step, the player must either add one letter to the word
from the previous step, or take away one letter, and then rearrange the letters to make a new word.
Example: croissant to baritone
croissant
( - C )
arsonist
( - S )
aroints
( + E )
notaries
( + B )
baritones
( - S )
baritone
This can be modeled as a search problem, where each node represents a word and the children (actions)
of this node are the words that can follow in the next step

## Instructions
Execute the file ladders.jar which takes
the two words as arguments like this:
>> java -jar ladders.jar startword goalword

## Output Files
1. output 1.txt – the answer to croissant – baritone
2. output 2.txt – the answer to crumpet – treacle
3. output 3.txt – the answer to apple – pear
4. output 4.txt – the answer to lead – gold
