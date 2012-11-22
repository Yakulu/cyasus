; Copyright (c) 2012 Fabien Bourgeois
; See the file LICENSE for copying permission.

(ns cyasus.core
  (:gen-class :main true)
  (:require [ring.util.response :as response]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [net.cgrand.enlive-html :as html])
  (:use cyasus.views
        cyasus.common
        [compojure.core :only (GET POST defroutes)]
        [ring.adapter.jetty :only (run-jetty)]))

; Controlers helpers
(defn redirect
  "Redirects the user from the link id."
  [id] (response/redirect (:url (@db id))))

; Routes
(defroutes main-routes
  (GET "/" request (render-empty-homepage))
  (POST "/" [id url]
        (let [id (if (empty? id) (gen-key) id)
              render-home (partial render-default (home {:old-key id :old-url url}))]
          (cond
            (not (url? url)) (render-home (lang :ERROR_URL))
            (contains? @db id) (render-home (lang :KEY_USED))
            (< 2 (count id) 10) (let [code (gen-key 4)
                                      id (save! id url code)]
                                  (render-default (post {:id id :code code}) (lang :POST_SUCCESS)))
            :else (render-home (lang :ERROR_KEY_LEN)))))
  (GET "/:id" [id]
       (if (contains? @db id)
         (if (params :AUTOMATIC_REDIRECT)
           (redirect id)
           (render-default (no-redirect (:url (@db id)))))
         (render-empty-homepage (lang :KEY_INVALID))))
  (GET "/admin/:id/:code" [id code]
       (if (= (:validation-code (@db id)) code)
         (do 
           (delete! id)
           (render-empty-homepage (lang :LINK_SUCCESS_RM)))
         (render-empty-homepage (lang :VKEY_INVALID))))
  (route/resources "/")
  (route/not-found (render-empty-homepage (lang :KEY_INVALID))))

(def app
  (handler/site main-routes))

; Jetty launching
(defn -main []
  (run-jetty app {:port (params :PORT)}))
