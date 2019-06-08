object RunDocumentMerge {
  def main(args: Array[String]): Unit = {
    val language = "fr"

    var d = ""
    var entitiesList:List[Entity]=List()
    language match {
      case "en" => {
        d = DataEn.d
        entitiesList = DataEn.listEntities
      }
      case "fr" => {
        d = DataFr.d
        entitiesList = DataFr.listEntities
      }
      case "de" => {
        d = DataDe.d
        entitiesList = DataDe.listEntities
      }
      case "es" => {
        d = DataEs.d
        entitiesList = DataEs.listEntities
      }
      case _ => {
        d = DataEn.d
        entitiesList = DataEn.listEntities
      }
    }
    // Create the first representation fo the document : parsing with StanfordNLP
    val md = new MergeDocumnents(language)
    val d1:Document1 = md.annotateDocument(d)


    // Create the second representation of the document:
    // noun entities in the document and their start and end position, given in an "Entity" class instance
    val d2:Document2 = Document2(1, d, entitiesList)

    // Merge two types of documents into a Document1:
    val d1d2 = (d1,d2)
    val d1_res:Document1 = md.mergeSentencesInDocument(d1d2)

    // Test & print :
    d1_res.sentences.foreach {
      _.tokens.filter(t => t.entity_id>0).foreach {
        println(_)
      }
    }
  }
}
