(ns ldnclj-goose-201407.core
	(:require [quil.core :as q :include-macros true]))
(enable-console-print!)
(def bkgrnd-colour (atom [0 240 0]))
(def circle-1 (atom [200 200 550 550]))
(def circle-2 (atom [1000 1000 200 200]))

(defn draw []
  (let [[r g b] @bkgrnd-colour]
    (q/background r g b)
    (q/fill b g r)
    (apply q/ellipse @circle-1)
    (q/fill r b b)
    (apply q/ellipse @circle-2)))

(defn key-press []
  (let [[r g b] @bkgrnd-colour
        circle-1-size (rand-int 800)
        circle-2-size 500
        key-pressed (q/key-as-keyword)]
    (println key-pressed)
    (reset! bkgrnd-colour [g b r])
    (reset! circle-1 [(rand-int 1800)
                      (rand-int 900)
                      circle-1-size
                      circle-1-size])
    (reset! circle-2 [(rand-int 1800)
                      (rand-int 900)
                      (cond
                       (= key-pressed :w) (* circle-2-size 4)
                       :else circle-2-size)
                      (cond
                       (= key-pressed :h) (* circle-2-size 4)
                       :else circle-2-size)])))

(q/defsketch ldnclj-goose-201407
	:draw draw
	:host "ldnclj-goose-201407"
	:size [1920 1080]
        :key-pressed key-press)
