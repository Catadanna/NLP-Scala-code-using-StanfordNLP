case class Sentence(
                     id:Int,
                     content:String,
                     start_index_in_document:Int,
                     end_index_in_document:Int,
                     tokens:Seq[Token]){

  def toString2(): String = {
    def concatTokens(acc:String, s:String):String = {
      acc+",\t\n"+s
    }
    "\n\nSentence("+this.id+","+this.content+","+this.tokens.map(_.toString).reduceLeft(concatTokens)+")"
  }


  override def toString(): String = {
    def concatTokens(acc:String, s:String):String = {
      acc+",\t\n"+s
    }
    "\nSentence("+this.id+","+this.start_index_in_document+", "+this.end_index_in_document+", "+this.content.length+", "+this.content+")"
  }


}

