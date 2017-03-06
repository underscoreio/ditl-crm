# Day in the Life CRM

## Getting started

Install Java 8, for example [from Oracle][jdk].

Install the sbt build tool for Scala as described in the [reference manual](http://www.scala-sbt.org/download.html).

Then run the following from your shell:

```
sbt run
```

This will start the project on http://localhost:8080/ 

First time you run it, libraries will be downloaded.

## Example API calls

These examples use [HTTPie][httpie]:

* List all customers:  `http :8080/customers`  (with cURL this would be `curl http://localhost:8080/customers`)

* List a customer: `http :8080/customers/2`

* Add a customer: `http POST :8080/customers name=Connie phone='+1 555 9876'`

* Update a customer: `http PATCH :8080/customers/2 phone="+44 555 555" name="Robert"`

# Code

- `api.scala` defines the REST endpoints.
- `database.scala` defines all the queries and establishes an in-memory database.
- `model.scala` gives the data model.
- `main.scala` pulls everything together into a web server.

## Credit

This is in part a mash-up of parts from [Typelevel todomvc][tltodo], [http4s todomvc][htodo], and [Bulletin]

[Bulletin]:  https://github.com/davegurnell/bulletin
[tltodo]:  https://github.com/davegurnell/typelevel-todomvc
[htodo]:  https://github.com/tomwadeson/todobackend-http4s
[httpie]:  https://httpie.org/
[jdk]: http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

