(ns clojure-noob.core
  (:gen-class))

1
"a string"
["a" "vector" "of" "strings"]
(+ 1 2 3)
(str "It was the panda " "in the library " "with a dust buster")

(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!")

(if false
  "By Zeu's hammer!"
  "By Aquaman's trident!")

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

(when true
  (println "Success!")
  "abra cadabra")

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
           "MILDLY INCONVENIENCED!"
           "DOOOOMED!")))
(error-message "mild")

(defn train
  []
  (println "Choo choo!"))

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST" "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))
 ;; to run
(too-enthusiastic "Zelda")

(defn no-params
  []
  "I take no parameters!")

(defn one-param
  [x]
  (str "I take one parameter: " x))

;; to run:
;; (one-param "Mariana")

(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
  "together to spite you! " x y))


(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
   [& whippersnappers]
  (map codger-communication whippersnappers))

;;(codger "Billy" "Anne-Marie" "The Incredible Bulk")


(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))
;; (favorite-things "Doreen" "gum" "shoes" "kara-te")

                                        ; Destructuring

;; Return the first elemtn of a collection
(defn my-first
  [[first-thing]] ;Notice that first-thing is within a vector
  first-thing)
;; (my-first ["oven" "bike" "war-axe"])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))
;; (chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
;(announce-treasure-location {:lat 28.0 :lng 81.33})
; or:

(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

                                        ; Function body
(defn illustrative-function
  []
  (+ 1 304)
  30
  "Joe")

(defn illustrative-function2
  []
  (+ 1 304)
  30
  "Joe"
  (+ 17 2))

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

                                        ; Anonymous Functions
(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])

((fn [x] (* x 3)) 8)

(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)

(#(* % 3) 8)

(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])

(#(str %1 " and " %2) "cornbread" "butter beans")

(#(identity %&) 1 "blarg" :yip)

                                        ; Returning Functions
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)

                                        ; Pulling it all together
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
;asym-hobbit-body-parts
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
    (let [[part & remaining] remaining-asym-parts]
      (recur remaining
      (into final-body-parts
            (set [part (matching-part part)])))))))
;(symmetrize-body-parts asym-hobbit-body-parts)

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

;(hit asym-hobbit-body-parts)

                                        ; Exercises
;; 1. Use the str, vector, list, hash-map and hash-set functions
(defn say-hi
  [name]
  (str "Hi, " name))
;(say-hi "Mariana")

(reduce + (vector 1 2 3))

(conj (list 1 2 3) "test")

(defn exercise-hashmap
  [x y]
  (hash-map :first x :second y))
;(exercise-hashmap "Sleep" "Food")

(defn exercise-hashset
  [a & b]
 (apply hash-set (when (= a 8) b)))
;(exercise-hashset 8 3)


;; 2. Write a function that takes a number and adds 100 to it.
(defn add-num
  [num]
  (reduce + 100 [num]))
;(add-num 4)

;; 3. Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction
(defn dec-maker
  [dec-by]
  #(- % dec-by ))
(def dec9 (dec-maker 9))
;(dec9 8)
;(dec9 19)

;;4. Write a function, mapset, that works like map except the return value is a set
(defn mapset [f coll]
  (set (map f coll)))

;(mapset inc [1 1 11 11 23 2])



