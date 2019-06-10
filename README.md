# NLP-Scala-code-using-StanfordNLP

Description

This is an exemple of code which merges two different representations of the same document.
The first representation is the result of the Stanford parser execution on the input document.
The second representation is a list of noun phrases present in the document as well as their start and end indexes.

This code is meant to show the resolution of this problem in a functional manner.

Nevertheless, the use of Stanford NLP here is deprecated, it was replaced by Simple Core NLP (https://stanfordnlp.github.io/CoreNLP/simple.html). I kept the program as it is because of the start / end indexing in a document which is not possible in Simple Core NLP.



