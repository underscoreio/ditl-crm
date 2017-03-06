package crm

import doobie.h2.h2transactor._
import doobie.imports._
import cats.syntax.cartesian._
import fs2.Task

trait Database {
  def init: Task[Unit]
  def list: Task[Seq[Customer]]
  def insert(c: Customer): Task[Customer]
  def find(id: Int): Task[Option[Customer]]
  def update(c: Customer): Task[Customer]
}

object DoobieDatabase {

  val createFreshDatabase: Task[DoobieDatabase] =
    for {
      xa <- H2Transactor[Task]("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")
      _  <- xa.setMaxConnections(10)
      db  = new DoobieDatabase(xa)
      _  <- db.init
    } yield db

}

class DoobieDatabase(xa: Transactor[Task]) extends Database {

  val init: Task[Unit] = {
    val action = for {
      _ <- createCustomerTable
      _ <- insertQuery(Customer("Alice", "+1 555 1234"))
      _ <- insertQuery(Customer("Bob",   "+1 555 5678"))
    } yield ()
    action.transact(xa)
  }

  def list: Task[List[Customer]] = listQuery.transact(xa)

  def insert(c: Customer): Task[Customer] = insertQuery(c).transact(xa)
  
  def update(c: Customer): Task[Customer] = updateQuery(c).transact(xa)

  def find(id: Int): Task[Option[Customer]] = findQuery(id).transact(xa)

  lazy val createCustomerTable: ConnectionIO[Int] =
    sql"""
    create table customer(name varchar not null, phone varchar not null, id serial primary key)
    """.update.run

  lazy val listQuery: ConnectionIO[List[Customer]] =
    sql""" select name, phone, id from customer """.query[Customer].list

  def findQuery(id: Int): ConnectionIO[Option[Customer]] =
    sql""" select name, phone, id from customer where id = $id """.query[Customer].option

  def insertQuery(cust: Customer): ConnectionIO[Customer] = for {
    id <- sql""" insert into customer (name, phone) values (${cust.name}, ${cust.phone}) """.update.withUniqueGeneratedKeys[Int]("id")
  } yield cust.copy(id = Some(id))

  def updateQuery(cust: Customer): ConnectionIO[Customer] = for {
    rowsAffected <- sql""" update customer set name=${cust.name}, phone=${cust.phone} where id=${cust.id} """.update.run
  } yield cust

}
