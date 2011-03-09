(defproject broca "1.0.0"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.2.0"]]
  :aot [broca.CharsetProvider])

(ns leiningen.char-set-install
  (:require [leiningen.jar]
            [robert.hooke]))

(declare *project*)

(robert.hooke/add-hook
 (resolve 'leiningen.jar/filespecs)
 (fn [f & args]
   (cons {:type :bytes
          :path "META-INF/services/java.nio.charset.spi.CharsetProvider"
          :bytes (.getBytes
                  (apply str
                         (interpose \space
                                    (:aot *project*))))}
         (apply f args))))

(robert.hooke/add-hook
 #'leiningen.jar/jar
 (fn [f project & args]
   (binding [*project* project]
     (apply f project args))))
