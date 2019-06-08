name := "w2v"
version := "0.1"
scalaVersion := "2.11.0"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"
// https://mvnrepository.com/artifact/edu.stanford.nlp/stanford-parser
libraryDependencies += "edu.stanford.nlp" % "stanford-parser" % "3.8.0"
// https://mvnrepository.com/artifact/edu.stanford.nlp/stanford-corenlp
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models-english"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models-french"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models-spanish"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models-german"