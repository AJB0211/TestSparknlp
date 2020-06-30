# TestSparknlp
Getting Sparknlp by JohnSnowLabs to run locally using their example code
[https://github.com/JohnSnowLabs/spark-nlp-workshop](https://github.com/JohnSnowLabs/spark-nlp-workshop)

Particularly [2- Pre-trained pipelines - ont_recognize_entities_sm.html](https://github.com/JohnSnowLabs/spark-nlp-workshop/blob/master/databricks/scala/annotation/2-%20Pre-trained%20Pipelines%20-%20onto_recognize_entities_sm.html)  
but using the sentiment analyzer as the pipeline

Requires:  
- Java 8   
- sbt   

ex.  

    $ sbt
    ...
    sbt: Main> compile   
    sbt: Main> run
    ...
    **************************************************  
    Input or :q to exit  
    > why are you like this  
    +----------+  
    |    result|  
    +----------+  
    |[positive]|  
    +----------+  

It doesn't appear to work very well.
