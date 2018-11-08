;Connect to server by:
;telnet localhost 8080
;Eugene Boado
(ns constantia.core
  (:gen-class)
  (:use server.socket))
(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(def serverport 8080)

(def i 0) ; Example of variable that exists during entire server program
(def mystr "")

(defn WriteToConsole
"Writes text to console"
  [message]
  (print message)) ; defn WriteToConsole

(defn WritelnToConsole
"Writelns text to console"
  [message]
  (println message)) ; defn WriteToConsole

(defn Execute
  [command]
  (WriteToConsole (str "Execute " command "\n"))
  (str command)) ; defn execute

(defn ReadFromConsole
"Reads text from console"
  ([]
   (print "console> ")
   (flush)
   (read-line))
  ([promptMessage]
   (print "console> " promptMessage)
   (flush)
   (read-line))) ; defn ReadFromConsole


(defn menu [prompt choices]
"Menu interface"
  (if (empty? choices)
    ""
    (let [menutxt (apply str (interleave
                              (iterate inc 1)
                              (map #(str \space % \newline) choices)))]
      (WritelnToConsole prompt)
      (WritelnToConsole menutxt)
      (flush)
      (let [index (read-string (read-line))]
        ; verify
        (if (or (not (integer? index))
                (> index (count choices))
                (< index 1))
          ; try again
          (recur prompt choices)

          (nth choices (dec index))) ; if
) ; let
) ; let
) ; if
) ;(println "ConstantIA") defn menu


(defn Run
"REPL main loop"
  []
  (def menuchoice "")
  (def j 0)
  (while (not= "Quit" menuchoice)
    (try
      (def menuchoice (menu "Which is from the three\n>"
                            ["huff and puff" "mirror mirror" "Quit"])) ; def menuchoice
      (def i (+ i 1))
      (def j (+ j 1))
      (def mystr (str mystr "+" i))
      (WriteToConsole (str "The answer is " menuchoice " i:" i " j:" j " mystr:" mystr "\n"))

      (catch Exception e
        (WriteToConsole (str "ERROR " (.getMessage e) "\n"))) ; catch
      (finally
        (WritelnToConsole "Release some resource"))) ; try
) ; while
) ; defn run

(defn echo-server []
  (letfn [(echo [in out]
            (binding [*in* (BufferedReader. (InputStreamReader. in))
                      *out* (OutputStreamWriter. out)]
              (WritelnToConsole "")
              (WritelnToConsole "Constantia Client")
              (WritelnToConsole "-----------------")
              (WritelnToConsole "")
 
              (Run)
              ))] ; echo
    (create-server 8080 echo)) ; leftfn
) ; defn


(defn -main
  "REPL framework for main b"
  [& args]
  (println "<Starting server>") 
  (echo-server)
  (println (str "<Server started on " serverport ">"))) ;defn -main 
