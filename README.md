# clojure-noob

Little project to start learning the basics of Clojure.
I have decided to follow the guide [Clojure for the Brave and True](http://www.braveclojure.com/), for obvious reasons! :muscle:

## Installation

Find [here](http://www.lispcast.com/clojure-mac) how to install Clojure for Mac

## Usage

To create Clojure project called clojure-noob:

```
$ lein new app clojure-noob
```

To run the Clojure Project:

```
$ lein run
```

To build Clojure Project (for sharing project with people who do not have Leiningen installed):

```
$ lein uberjar
```

To use REPL:

```
$ lein repl
clojure-noob.core=> (-main)
```

To start CIDER REPL:

```
M-x cider-jack-in
```

To compile current file within REPL session:

```
C-c M-n
```

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
