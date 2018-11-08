(defproject constantia "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [server-socket "1.0.0"]]
  :dev-dependencies [[speclj "2.1.1"]]
  :main ^:skip-aot constantia.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-cljfmt "0.5.6"]]
  )
