# Kata19 DFS Optimized Sort (Depth First Search) 

This is the depth first solution for [code kata 19 word chain problem](http://codekata.com/kata/kata19-word-chains/).

## Optimizations considered

- Maintain a set of words that did NOT lead to the final word and do not use those words again.   A deadend list so to speak.
- When building a Set of words to check at each node level sort the list by the number of matches to the final word so the closest matches will be attempted first.  Consider pruning the tree after each depth run to remove items that may have appeared in the deadend list.

## running

Once built and started you can run from a web browser.

     http://localhost:8080/kata19?sourceWord=goat&targetWord=ball

