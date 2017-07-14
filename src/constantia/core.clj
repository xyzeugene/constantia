;Document Here
;Eugene Boado
(ns constantia.core
  (:gen-class)
  (:use server.socket))
(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(defn WriteToConsole             
  [message]
  (print message)
  );End defn WriteToConsole

(defn WritelnToConsole             
  [message]
  (println message)
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


(defn menu [prompt choices]
  (if (empty? choices) 
    ""
    (let [menutxt (apply str (interleave
                              (iterate inc 1)
                              (map #(str \space % \newline) choices)))]
      (WritelnToConsole menutxt)
      (WriteToConsole prompt)
      (flush)
      (let [index (read-string (read-line))]
        ; verify
        (if (or (not (integer? index))
                (> index (count choices))
                (< index 1))
          ; try again
          (recur prompt choices)
                                        ; ok
          (nth choices (dec index))
          ) ; if
        ) ; let
      ) ; let
    ) ; if
  ) ; defn menu


(defn Run
  []
  (def menuchoice "")
  (while (not= "Quit" menuchoice)
    (try
      (def menuchoice (menu "Which is from the three\n>"
                            ["huff and puff" "mirror mirror" "Quit"])
        ) ; def menuchoice
      (WriteToConsole (str "The answer is " menuchoice "\n"))
      
      
      (catch Exception e
        (WriteToConsole (str "ERROR " (.getMessage e) "\n"))
        ) ; catch
      (finally
        (WritelnToConsole "Release some resource")
        )
      ) ; try
    ) ; while
  ) ; defn run

(defn -main
  "REPL framework for main b"
  [& args]
  
  (println "ConstantIA")
  (println)
  (Run) 
  (println "<End Program>")
  ) ;defn -main 
