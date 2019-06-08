import java.util.Properties
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.ling.CoreLabel
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.util.CoreMap
import scala.collection.JavaConverters._
import scala.collection.JavaConverters

class MergeDocumnents(val language:String) {
  private val props = new Properties()
  props.put("annotators", "tokenize, ssplit, parse")
  language match {
    case "fr" => {
      this.props.put("parse.model","edu/stanford/nlp/models/lexparser/frenchFactored.ser.gz")
      //this.props.put("pos.model","edu/stanford/nlp/models/pos-tagger/french/french.tagger")
      this.props.put("tokenize.language","fr")
    }
    case "de" => {
      this.props.put("parse.model", "edu/stanford/nlp/models/lexparser/germanFactored.ser.gz")
      //this.props.put("pos.model", "edu/stanford/nlp/models/pos-tagger/german/german.tagger")
      this.props.put("tokenize.language", "de")

    }
    case "es" => {
      this.props.put("parse.model", "edu/stanford/nlp/models/lexparser/spanishPCFG.ser.gz")
      //this.props.put("pos.model", "edu/stanford/nlp/models/pos-tagger/spanish/spanish.tagger")
      this.props.put("tokenize.language", "es")
    }
    case _ => { // English & ohers :
      this.props.put("parse.model","edu/stanford/nlp/models/lexparser/englishFactored.ser.gz")
      this.props.put("tokenize.language","en")
    }
  }

  val pipeline: StanfordCoreNLP = new StanfordCoreNLP(props)

  def annotateDocument(myDocument: String): Document1 = {
    val document = new Annotation(myDocument)
    this.pipeline.annotate(document)
    val d:Document1 = this.createDocument(document)
    d
  }

  // Create tokens from Stanford NLP and transform them into local class instances Token
  def createTokens(s: CoreMap):Seq[Token] = {
    val ta = s.get(classOf[CoreAnnotations.TokensAnnotation])
    val t = JavaConverters.collectionAsScalaIterableConverter(ta).asScala.toSeq
        .asInstanceOf[Seq[CoreLabel]]
        .map(e => (e, s))
        .map(es => Token(
          es._2.get(classOf[CoreAnnotations.TokensAnnotation]).indexOf(es._1),
          es._1.get(classOf[CoreAnnotations.TextAnnotation]),
          es._1.get(classOf[CoreAnnotations.TextAnnotation]),
          es._1.get(classOf[CoreAnnotations.CharacterOffsetBeginAnnotation]),
          es._1.get(classOf[CoreAnnotations.CharacterOffsetEndAnnotation]),
          0
        ))
    t
  }

  // Create sentences from Stanford NLP and transform them into local class instances Sentence
  def createSentences(d:Annotation):Seq[Sentence] = {
    def getIndexOfSentence(sen:CoreMap):Int = {
      d.get(classOf[CoreAnnotations.SentencesAnnotation]).indexOf(sen)
    }

    val s:Seq[Sentence] =
      d.get(classOf[CoreAnnotations.SentencesAnnotation])
      .asScala
        .asInstanceOf[Seq[CoreMap]]
        .map(e => Sentence(getIndexOfSentence(e),
          e.toString,
          e.get(classOf[CoreAnnotations.CharacterOffsetBeginAnnotation]),
          e.get(classOf[CoreAnnotations.CharacterOffsetEndAnnotation]),
          this.createTokens(e)))
          .asInstanceOf[Seq[Sentence]]

    s
  }

  // Create annotation from Stanford NLP for a given document and transform it into local class instance Document1
  def createDocument(d:Annotation):Document1 = {
    Document1(1, this.createSentences(d))
  }

  // Within merging the two representations of the document,
  // merge at the sentence level :
  def mergeTokensInSentence(sd2:(Sentence,Document2)):Sentence = {
    val s = sd2._1
    val d2 = sd2._2

    // Merge token per entity :
    def getTokensForEntityMap(i:(Entity, Token)):Token = {
        if ((i._2.start_index >= i._1.start_index) && (i._2.end_index <= i._1.end_index)){
          val new_token = i._2.copy(entity_id = i._1.id)
          new_token
        }
        else
          i._2
    }

    // Merge sentence per entity:
    def mergeSentenceAndEntities(acc:(Sentence,Entity),se:(Sentence,Entity)):(Sentence, Entity) = {
      val current_entity = se._2
      val acc_sentence = acc._1

      val new_tokens:Seq[Token] =
        acc_sentence
          .tokens
          .map(t => (current_entity, t))
          .map(getTokensForEntityMap)

      val new_sentence:Sentence = acc_sentence.copy(tokens=new_tokens)

      (new_sentence, current_entity)

    }

    val sentence_entities:Seq[Entity] = d2.entities
      .filter(e => e.start_index>=s.start_index_in_document && e.end_index<=s.end_index_in_document)
    if (sentence_entities.isEmpty)
      s
    else {
      val ss:Seq[(Sentence, Entity)] = sentence_entities.map(se => (s, se))
      val final_result_fold:(Sentence,Entity) = ss.foldLeft(s,Entity(0,-1,1,-1,-1))(mergeSentenceAndEntities)
      final_result_fold._1
    }
  }

  def mergeSentencesInDocument(d1d2:(Document1,Document2)):Document1 = {
    val d1:Document1 = d1d2._1
    val d2:Document2 = d1d2._2
    val ss:Seq[Sentence] = d1.sentences.map(s => (s, d2)).map(mergeTokensInSentence)
    val new_d1:Document1 = d1.copy(sentences = ss)
    new_d1
  }

}
