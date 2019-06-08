case class Entity(
                   unique_id:Int, // unique identifier
                   id:Int, // identifier of the noun entity
                   document_id:Int,
                   start_index:Int, // start index in the document
                   end_index:Int // end index in the document
                 )