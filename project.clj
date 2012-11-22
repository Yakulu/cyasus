(defproject cyasus "0.0.1-SNAPSHOT"
  :description "Clojure Yet Another Stupid Url Shortener"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.6"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [compojure "1.1.3"]
                 [enlive "1.0.1"]]
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :plugins [[lein-ring "0.7.1"]]
  :ring {:handler cyasus.core/app
         :auto-reload? true}
  :main cyasus.core)
