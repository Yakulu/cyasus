; Copyright (c) 2012 Fabien Bourgeois
; See the file LICENSE for copying permission.

(ns cyasus.views
  (:use [cyasus.common :only (db lang params)])
  (:require [net.cgrand.enlive-html :as html]))

; Views utilities
(defn abs-url
  "Generates a string URL prefixed by configured HOST and PORT."
  [s]
  (str "http://" (params :HOST) ":" (params :PORT) "/" s))

(defn make-redir-link
  "Sets href attribute and link content according to given string, absolute URL or not."
  ([s]
   (make-redir-link s true))
  ([s abs]
   (html/do->
     (html/set-attr :href s)
     (if abs (html/content (abs-url s)) (html/content s)))))

; Templates and snippets
; Homepage snippet. Takes optional invalids key and URL to avoid re-entering.
(html/defsnippet home "public/html/home.html" [:div#new]
  [{:keys [inv-id inv-url]}]
  [:h3] (if (empty? @db)
            (html/content (lang :LINK_LIST_EMPTY))
            (html/content (lang :LINK_LIST)))
  [:#all :li] (html/clone-for
                    [[id link] @db]
                    [:a] (html/do->
                           (html/content (link :url))
                           (html/set-attr :href (str "/" id))))
  [:#id] (html/set-attr :value inv-id :placeholder (lang :LABEL_KEY))
  [:#url] (html/set-attr :value inv-url)
  [:#submit] (html/set-attr :value (lang :LABEL_GENERATE)))

; After successfull post snippet. Takes link id and removal code.
(html/defsnippet post "public/html/post.html" [:div#post]
  [{:keys [id code]}]
  [:#intro] (html/prepend (lang :POST_GENERATED_INTRO))
  [:#share] (html/prepend (lang :POST_GENERATED_SHARE))
  [:#share :a] (make-redir-link id)
  [:#delete] (html/prepend (lang :POST_GENERATED_DELETE))
  [:#delete :a] (make-redir-link (str "admin/" id "/" code)))

; Snipper for no-redirection action : when it's configured, CYASUS doesn't automatically redirects users.
(html/defsnippet no-redirect "public/html/no-redirect.html" [:p#no-redirect]
  [id]
  [:#no-redirect] (html/do->
                    (html/prepend (lang :GO_TO_LINK_PRE))
                    (html/append (lang :GO_TO_LINK_POST)))
  [:#no-redirect :a] (make-redir-link id false))

; Global layout. Uses snip function to render appropriate page. Takes an optional message to display.
(html/deftemplate layout "public/html/layout.html"
    [{:keys [snip msg]}]
    [:title] (html/content (params :TITLE))
    [:header :h1 :a] (html/content (params :TITLE))
    [:header :h3] (html/content (params :SUBTITLE))
    [:footer :p] (html/content (params :FOOTER))
    [:#content] (html/append snip)
    [:#info] (if msg (html/content msg)))

; Views shortcuts
(defn render-default
  "Default controller : layout with enlive snippet function and an optional message"
  ([snip] (render-default snip nil))
  ([snip msg] (layout {:msg msg :snip snip})))

(defn render-empty-homepage
  "Render with empty homepage content and an optional message."
  ([] (render-empty-homepage nil))
  ([msg] (render-default (home {}) msg)))
