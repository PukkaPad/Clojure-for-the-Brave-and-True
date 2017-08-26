(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))
(println "Cleanliness is next to godliness")




;; Exercises

;; 1. Use the str, vector, list, hash-map, and hash-set functions.
;; str
(str "Clojure is a functional pregramming language")
;; list
'(17 12 21 02 07)
(list 17 12 21 02 07)
;; hash-map
(hash-map :Mariana 0 :Clojure 10)
;;hash-set
(hash-set 3 3 3 7 2 7 2 0 8 0 9)

;; 2. Write a function that takes a number and adds 100 to it.
(defn inc-maker
  "Create a function that generates  inc-number incrememt"
  [inc-number]
  #(+ % inc-number))

(def inc-funct
  "Add 100 to a number"
  (inc-maker 100))

(inc-funct 50)

;; 3. Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction
(defn dec-maker
  [sub-num]
  #(- % sub-num))

(def subtract-function
  "Removes 9"
  (dec-maker 9))

(subtract-function 10)

;; Write a function, mapset, that works like map except the return value is a set:
(defn mapset [f coll]
  (set (map f coll)))


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


