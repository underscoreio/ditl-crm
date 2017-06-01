# Day in the Life CRM

This code base is used in a talk outlining ADTs and Typeclasses.

## Getting started

Install Java 8, for example [from Oracle][jdk].

Install the sbt build tool for Scala as described in the [reference manual](http://www.scala-sbt.org/download.html).

Then run the following from your terminal/shell:

```
sbt run
```

This will start the project on http://localhost:8080/ 

First time you run it, libraries will be downloaded from the internet....

## Example API calls

These examples use [HTTPie][httpie] to call the server:

* List all customers:  `http :8080/customers`  (with cURL this would be `curl http://localhost:8080/customers`)

* List a customer: `http :8080/customers/2`

* Add a customer: `http POST :8080/customers name=Connie phone='+1 555 9876'`

* Update a customer: `http PATCH :8080/customers/2 phone="+44 555 555" name="Robert"`

# Code

- `api.scala` defines the REST endpoints.
- `database.scala` defines all the queries and establishes an in-memory database.
- `model.scala` gives the data model.
- `main.scala` pulls everything together into a web server.

Also:

- `type-class.scala` contains the code used in the presentation for building up the idea of a type class.

# Libraries and Concepts

This code base makes use of:

- [circe] to encode and decode JSON text into case classes.
- [doobie] for database access.
- [http4s] as a web server.

It makes use of the types `Stream` and `Task`, which are concurrency concepts.
These are from the [fs2] project. For example, `Task` describes an effect.
The effect isn't performed until one of the `unsafeRun` methods is called.
The http4s web server expects `Task` to be used, and arranges for them to be run
on an appropriate thread pool.

## Credit

This is in part a mash-up of parts from [Typelevel todomvc][tltodo], [http4s todomvc][htodo], and [Bulletin]

[Bulletin]:  https://github.com/davegurnell/bulletin
[tltodo]:  https://github.com/davegurnell/typelevel-todomvc
[htodo]:  https://github.com/tomwadeson/todobackend-http4s
[httpie]:  https://httpie.org/
[jdk]: http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
[circe]: https://circe.github.io/circe/
[doobie]: https://circe.github.io/circe/
[http4s]: http://http4s.org/
[fs2]: https://github.com/functional-streams-for-scala/fs2/blob/series/0.10/docs/guide.md


