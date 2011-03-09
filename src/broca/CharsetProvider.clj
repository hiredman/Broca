(ns broca.CharSetProvider
  (:use [broca.core :only [*charsets* *charset-debug*]])
  (:import (java.nio.charset Charset))
  (:gen-class :extends java.nio.charset.spi.CharsetProvider))

(defn -charsetForName [this name]
  (let [charsets (into {} (map (fn [[k v]]
                                 [(.toLowerCase k)
                                  (.toLowerCase v)])
                               *charsets*))]
    (when *charset-debug*
      (println "Asked for charset" name))
    (when (contains? charsets (.toLowerCase name))
      (Charset/forName (get charsets (.toLowerCase name))))))

(defn -charsets [])
