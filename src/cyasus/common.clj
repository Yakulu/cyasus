; Copyright (c) 2012 Fabien Bourgeois
; See the file LICENSE for copying permission.

(ns cyasus.common
  (:require cyasus.lang
            cyasus.config))

(def params cyasus.config/params)
(def lang (get cyasus.lang/labels (params :LANG)))

; In-memory database
(def db (ref {}))

; Utilities
(defn gen-key
  "Generates an alphanumeric key according to the configuration-given size"
  ([]
   (gen-key (params :KEY_LENGTH)))
  ([n] {:pre [(pos? n)]}
   (let [alphan "abcdefghijklmopqrstuvwxyzABCDEFGHIJKLMOPQRSTUVWXYZ0123456789"]
     (apply str (repeatedly n #(rand-nth alphan))))))

(defn save!
  "Takes an URL, its key and the validation for removal cide to store this as a map element into the database"
  ([id url code] {:pre [(every? string? [id url code])]}
   (dosync
     (alter db assoc id {:url url :validation-code code})
   id)))
   
(defn delete!
  "Removes the link stored, according to the right validation-code."
  [id] {:pre ([string? id])}
  (dosync
    (alter db dissoc id)))

; Validation
(defn url?
  "Takes a string and checks the validity of this as an URL."
  [s] {:pre ([string? s])}
  (try
    (java.net.URL. s) true
    (catch java.net.MalformedURLException _ false)))
