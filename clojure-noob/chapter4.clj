(ns clojure-noob.core
  (:gen-class))

(defn titleize
  [topic]
  (str topic " for the Brave and True."))

(titleize "mariana")
(titleize ["mariana" "joao"])
(map titleize ["mariana" "joao"])
(map titleize '("mae" "tia" "vitoria"))
(map titleize #{"Elbows" "Soap Carving"})
(map #(titleize (second %)) {:key "value"})
(map #(titleize (first %)) {:key "value"})

(seq {:a 1 :b 2 :c 3})
(into {} (seq {:a 1 :b 2 :c 3}))

(map inc [1 2 3])
(map str ["a" "b" "c"] ["A" "B" "C"])
(map str ["a" "b" "c"] ["A" "B" "C"] ["1" "2" "3"])

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})
(map unify-diet-data human-consumption critter-consumption)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [ 2 2 2])
(stats [2 3 4])
(stats [3 4 66 29 0])

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])
(map :real identities)

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})

(take 3 [ 1 2 3 4 5 6 7 8 9 10])
(drop 3 [1 2 3 4 5 6 7 8 9 10])

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
(drop-while #(< (:month %) 3) food-journal)
(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))

(filter #(< (:human %) 5) food-journal)
(filter #(< (:month %) 3) food-journal)

;; some returns true is ANY value is not false or nil
(some #(> (:critter %) 5) food-journal)
(some #(> (:critter %) 3) food-journal)
(some #(and (> (:critter %) 3) %) food-journal)

(sort [3 1 2])
(sort-by count ["aaa" "c" "bb"])
