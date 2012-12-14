; Copyright (c) 2012 Fabien Bourgeois
; See the file LICENSE for copying permission.

; Tests are far from exhaustive... ATM only few utilities covered.

(ns cyasus.test.core
  (:use cyasus.common
        clojure.test
        clojure.set))

(deftest test-gen-key
  (is (thrown? AssertionError (gen-key -12)) "Argument must be a positive number.")
  (is (thrown? ClassCastException (gen-key "aloa")) "Argument must be a number, not a string.")
  (is (= (params :KEY_LENGTH) (count (gen-key))) "Default size have to be fixed by configuration.")
  (is (= 20 (count (gen-key 20))) "Size must be the same as asked.")
  (is (= 21 (count (gen-key 20.34))) "With float as argument, size must be absolute +1")
  (is (string? (gen-key)))
  (is (empty? (difference (set (gen-key)) (set "abcdefghijklmopqrstuvwxyzABCDEFGHIJKLMOPQRSTUVWXYZ0123456789"))) "Must return an alphanumeric string."))

(deftest test-url?
  (is (thrown? ClassCastException (url? 12)) "Argument must be a string.")
  (is (not (url? "aloa")) "Simple word isn't a valid URL.")
  (is (not (url? "nntp://example.com")) "Only FTP and HTTP are valid schemes.")
  (is (url? "ftp://example.com"))
  (is (url? "http://clojure.org"))
  (is (url? "https://www.clojure.org/some/path/into/it"))
  (is (url? "https://www.clojure.org/?query=ok")))
