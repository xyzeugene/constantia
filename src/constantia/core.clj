;Document Here
(ns constantia.core
  (:gen-class))

(defn WriteToConsole             
  [message]
  (print message)
  );End defn WriteToConsole

(defn Execute
  [command]
  (WriteToConsole (str "Execute " command "\n"))
  (str command)
  );defn execute

(defn ReadFromConsole
  ([]
   (print "console> " )
   (flush)
   (read-line)
   )
  ([promptMessage]
   (print "console> " promptMessage)
   (flush)
   (read-line)
   )
  );defn ReadFromConsole

(defn Run
  []
  (try

    (while (="Q"(Execute (ReadFromConsole))))
    
    (catch Exception e
      (WriteToConsole (str "ERROR " (.getMessage e) "\n"))
      )
    (finally
      (WriteToConsole "Release some resource\n")
      )
    ) ; End try
  ) 

(defn -main
  "REPL framework for main b"
  [& args]
  
  (println "ConstantIA")
  (println)
  (Run) 
  (println "<End Program>")
  ) ;End defn -main 
