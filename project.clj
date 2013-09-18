(defproject shoreleave/shoreleave-browser "0.3.0"
  :description "A smarter client-side with ClojureScript : Shoreleave's enhanced browser utilities"
  :url "http://github.com/shoreleave"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "See the notice in README.mkd or details in LICENSE_epl.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [shoreleave/shoreleave-core "0.3.0"]]
  :profiles {:dev {:dependencies [[lein-marginalia "0.7.1"]]}})

