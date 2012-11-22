# CYASUS

Clojure Yet Another Stupid URL Shortener

## Description

This project is just a first try for a Clojure project.
It has no goal to be efficient, stable nor production-ready.

## Features

Very small set :

* Stores with in-memory database (have I ever said it's just a toy project ?)
* Shortens given URL, with entered key or auto-generated one
* Gives the ability to delete a link with a generated code
* Lists URL stored on the homepage
* Automatic redirection; or not (configuration)
* Ridiculous testing coverage
* Bad english-speaking (not sure this is a feature)

No roadmap ATM.

## Dependencies

CYASUS is a server-side micro-application built upon :

* Clojure, of course
* Ring
* Compojure
* Enlive
* Bootstrap
* Samples given in the excellent _Programming Cojure_ book
* Good intentions

Thanks all authors, contributors for that.

## Defaults

The `config.clj` file is used to accomplish some kind of configuration.
By default, CYASUS and `lein run` launch a Jetty server on _0.0.0.0:8042_.

## License

Published under MIT license. See LICENSE file.
