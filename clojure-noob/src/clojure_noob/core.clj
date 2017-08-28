(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))
(println "Cleanliness is next to godliness")


;; Chapter 5

; Pure functions are referentially transparent 

; referentially transparent function
; (the string ", Daniel-san" is immutable):
(defn wisdom
  [words]
  (str words ", Daniel-san"))

(wisdom "Always bathe on Fridays")

; this function does not yeild the same arguments
; not referentially transparent
(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))

; if a function reads from a file it's not referentially transparent
; this is because the file content can change
; analyze-file is not referentially transparent
; analysis is referentially transparent


(defn analysis
  [text]
   (str "Character count: " (count text)))

; Pure functions have no side effects
; to perform a side effect is to change the association between a name and its value within a given scope
; immutable data structure ensure that code won't have side effects


; Immutable data structures
(def great-baby-name "Rosanthony")
great-baby-name

(let [great-baby-name "Bloodthunder"]
  great-baby-name)

great-baby-name

(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (sum (rest vals) (+ (first vals) accumulating-total)))))

(sum [39 5 1])

(defn sum
  ([vals]
   (sum vals 0)))

;(sum 2)

(defn sum
  ([vals]
     (sum vals 0))
  ([vals accumulating-total]
     (if (empty? vals)
       accumulating-total
       (recur (rest vals) (+ (first vals) accumulating-total)))))


