# Day in the Life CRM

## Getting started

Install Java 8, for example [from Oracle][jdk].

Install the sbt build tool for Scala as described in the [reference manual](http://www.scala-sbt.org/download.html).

Then run the following from your shell:

```
sbt ~re-start
```

This will start the project on http://localhost:8080/ and restart it anytime you change and save the code.

## Example API calls

These examples use [HTTPie][httpie]:

* List all customers:  `http :8080/customers`  (with cURL this would be `curl http://localhost:8080/customers`)

* List a customer: `http :8080/customers/2`

* Add a customer: `http POST :8080/customers name=Connie phone='+1 555 9876'`

* Update a customer: `http PATCH :8080/customers/2 phone="+44 555 555" name="Robert"`

## Credit

This is in part a mashup of parts from [Typelevel todomvc][tltodo], [http4s todomvc][htodo], and includes [Bulletin]

[Bulletin]:  https://github.com/davegurnell/bulletin
[tltodo]:  https://github.com/davegurnell/typelevel-todomvc
[htodo]:  https://github.com/tomwadeson/todobackend-http4s
[httpie]:  https://httpie.org/
[jdk]: http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
