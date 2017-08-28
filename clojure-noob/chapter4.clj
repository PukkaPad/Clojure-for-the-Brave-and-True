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
(sort ["aaa" "c" "bb"])

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  "this will return a record if it passes the vampire test"
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))
(time (vampire-related-details 0))

(concat (take 8 (repeat "na")) ["Batman!"])

(take 3 (repeatedly (fn [] (rand-int 10))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))
(take 10 (even-numbers))

(empty? [])
(empty? ["no!"])

(map identity {:sunlight-reaction "Glitter!"})
(into {} (map identity {:sunlight-reaction "Glitter!"}))

(map identity [:garlic :sesame-oil :fried-eggs])
(into [] (map identity [:garlic :sesame-oil :fried-eggs]))

(map identity [:garlic-clove :garlic-clove])
(into #{} (map identity [:garlic-clove :garlic-clove]))

(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])
(into ["cherry"] '("pine" "spruce"))
(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                  :relationship-with-teenager "creepy"})

(conj [0] [1])
(into [0] [1])
(conj [0] 1)
(conj [0] 1 2 3 4 5 6 7)
(conj {:time "midnight"} [:place "ye olde cemetarium"])

(max 0 1 3 2)
(apply max [0 1 2 -2])

(def add10 (partial + 10))
(add10 3)
(add10 5)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))
(add-missing-elements "unobtainium" "adamantium")


(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))

(warn "Red light ahead")
(lousy-logger :warn "Red light ahead")
