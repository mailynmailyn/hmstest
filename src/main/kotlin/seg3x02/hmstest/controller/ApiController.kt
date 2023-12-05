package seg3x02.hmstest.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.hateoas.CollectionModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import seg3x02.hmstest.user.assemblers.UserAccountModelAssembler
import seg3x02.hmstest.user.assemblers.UserRoleModelAssembler
import seg3x02.hmstest.user.entities.UserAccount
import seg3x02.hmstest.user.entities.UserRole
import seg3x02.hmstest.user.repository.UserAccountRepository
import seg3x02.hmstest.user.repository.UserRoleRepository

import seg3x02.hmstest.patient.assemblers.PatientModelAssembler
// import seg3x02.hmstest.patient.assemblers.AddressModelAssembler
import seg3x02.hmstest.patient.entities.Patient
import seg3x02.hmstest.patient.entities.Address
// import seg3x02.hmstest.patient.repository.AddressRepository
import seg3x02.hmstest.patient.repository.PatientRepository

import seg3x02.hmstest.user.representation.*
import seg3x02.hmstest.patient.representation.*
import java.net.URI

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("hms-test", produces = ["application/hal+json"])
class ApiController(val userAccountRepository: UserAccountRepository,
                    val roleRepository: UserRoleRepository,
                    val userAccountAssembler: UserAccountModelAssembler,
                    val roleAssembler: UserRoleModelAssembler,
                    val patientRepository: PatientRepository,
                    val patientAssembler: PatientModelAssembler
) {


    @Operation(summary = "Get all user accounts")
    @GetMapping("/useraccounts")
    fun allUserAccounts(): ResponseEntity<CollectionModel<UserAccountRepresentation>> {
        val users = userAccountRepository.findAll()
        return ResponseEntity(
            userAccountAssembler.toCollectionModel(users),
            HttpStatus.OK)
    }

    @Operation(summary = "Get all patients")
    @GetMapping("/patients")
    fun allPatients(): ResponseEntity<CollectionModel<PatientRepresentation>> {
        val patients = patientRepository.findAll()
        return ResponseEntity(
            patientAssembler.toCollectionModel(patients),
            HttpStatus.OK)
    }

    @Operation(summary = "Get all users by firstName and lastName")
    @GetMapping("/useraccountsname", params = ["firstName", "lastName"])
    fun getUserAccountsByName(@RequestParam("firstName") firstName: String,
                         @RequestParam("lastName") lastName: String):
            ResponseEntity<CollectionModel<UserAccountRepresentation>> {
        val authors = userAccountRepository.findUserAccountsByName(firstName, lastName)
        return ResponseEntity(
            userAccountAssembler.toCollectionModel(authors),
            HttpStatus.OK)
    }

    @Operation(summary = "Get a user by id")
    @GetMapping("/useraccounts/{id}")
    fun getUserAccountById(@PathVariable("id") id: Long): ResponseEntity<UserAccountRepresentation> {
        return userAccountRepository.findById(id)
            .map { entity: UserAccount -> userAccountAssembler.toModel(entity) }
            .map { body: UserAccountRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get a patient by id")
    @GetMapping("/patients/{id}")
    fun getPatientById(@PathVariable("id") id: Long): ResponseEntity<PatientRepresentation> {
        return patientRepository.findById(id)
            .map { entity: Patient -> patientAssembler.toModel(entity) }
            .map { body: PatientRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    // @Operation(summary = "Get all the books of and author")
    // @GetMapping("/authors/{id}/books")
    // fun getAuthorBooksById(@PathVariable("id") id: Long): ResponseEntity<List<BookTitleRepresentation>> {
    //     return authorRepository.findById(id)
    //         .map { author: Author -> authorAssembler.toBooksRepresentation(author.books) }
    //         .map { body: List<BookTitleRepresentation> -> ResponseEntity.ok(body) }
    //         .orElse(ResponseEntity.notFound().build())
    // }

    @Operation(summary = "Get a user's role")
    @GetMapping("/useraccount/{id}/role")
    fun getUserRoleById(@PathVariable("id") id: Long): ResponseEntity<UserRoleRepresentation> {
        return userAccountRepository.findById(id)
            .map { user: UserAccount ->  roleAssembler.toModel(user.role)}
            .map { role: UserRoleRepresentation -> ResponseEntity.ok(role) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get all roles")
    @GetMapping("/roles")
    fun allRoles(): ResponseEntity<CollectionModel<UserRoleRepresentation>> {
        val roles = roleRepository.findAll()
        return ResponseEntity(
            roleAssembler.toCollectionModel(roles),
            HttpStatus.OK)
    }

    @Operation(summary = "Get a role by id")
    @GetMapping("/roles/{id}")
    fun getRoleById(@PathVariable("id") id: Long): ResponseEntity<UserRoleRepresentation> {
        return roleRepository.findById(id)
            .map { entity: UserRole -> roleAssembler.toModel(entity)}
            .map { body: UserRoleRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    
    @Operation(summary = "Add a new user")
    @PostMapping("/adduser")
    fun addUser(@RequestBody user: UserAccount): ResponseEntity<Any> {
        return try {
            val newUser = this.userAccountRepository.save(user)
            val location: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.id)
                .toUri()
            ResponseEntity.created(location).body(userAccountAssembler.toModel(newUser))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Add a new patient")
    @PostMapping("/addpatient")
    fun addPatient(@RequestBody patient: Patient): ResponseEntity<Any> {
        return try {
            val newPatient = this.patientRepository.save(patient)
            val location: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPatient.id)
                .toUri()
            ResponseEntity.created(location).body(patientAssembler.toModel(newPatient))

        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    // @Operation(summary = "Add an order to a book")
    // @PostMapping("/books/{id}/orders")
    // fun addOrderToBook(@PathVariable("id") id: Long, @RequestBody order: Order): ResponseEntity<Any> {
    //     return try {
    //         val book = bookRepository.findById(id).get()
    //         book.orders.add(order)
    //         val newOrder = orderRepository.save(order)
    //         val location: URI = ServletUriComponentsBuilder
    //             .fromCurrentContextPath()
    //             .path("/books-api")
    //             .path("/orders")
    //             .path("/{id}")
    //             .buildAndExpand(newOrder.id)
    //             .toUri()
    //         ResponseEntity.created(location).body(orderAssembler.toModel(newOrder))
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Add a new author to a book")
    // @PostMapping("/books/{id}/authors")
    // fun addAuthorToBook(@PathVariable("id") id: Long, @RequestBody author: Author): ResponseEntity<Any> {
    //     return try {
    //         val book = bookRepository.findById(id).get()
    //         author.books.add(book)
    //         val newAuthor = authorRepository.save(author)
    //         val location: URI = ServletUriComponentsBuilder
    //             .fromCurrentContextPath()
    //             .path("/books-api")
    //             .path("/authors")
    //             .path("/{id}")
    //             .buildAndExpand(newAuthor.id)
    //             .toUri()
    //         ResponseEntity.created(location).body(authorAssembler.toModel(newAuthor))
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Add a new author")
    // @PostMapping("/authors")
    // fun addAuthor(@RequestBody author: Author): ResponseEntity<Any> {
    //     return try {
    //         val newAuthor = this.authorRepository.save(author)
    //         val location: URI = ServletUriComponentsBuilder
    //             .fromCurrentRequest()
    //             .path("/{id}")
    //             .buildAndExpand(newAuthor.id)
    //             .toUri()
    //         ResponseEntity.created(location).body(authorAssembler.toModel(newAuthor))
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Add a new book to an author")
    // @PostMapping("/authors/{id}/books")
    // fun addBookToAuthor(@PathVariable("id") id: Long, @RequestBody book: Book): ResponseEntity<Any> {
    //     return try {
    //         val author = authorRepository.findById(id).get()
    //         book.authors.add(author)
    //         author.books.add(book)
    //         val newBook = bookRepository.save(book)
    //         val location: URI = ServletUriComponentsBuilder
    //             .fromCurrentContextPath()
    //             .path("/books-api")
    //             .path("/books")
    //             .path("/{id}")
    //             .buildAndExpand(newBook.id)
    //             .toUri()
    //         ResponseEntity.created(location).body(bookAssembler.toModel(newBook))
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Add an existing author to an existing book")
    // @PatchMapping("/books/{bid}/authors/{aid}")
    // fun updateBookAuthors(@PathVariable("bid") bid: Long,
    //                       @PathVariable("aid") aid: Long): ResponseEntity<Any> {
    //     return try {
    //         val book = bookRepository.findById(bid).get()
    //         val author = authorRepository.findById(aid).get()
    //         if (!book.authors.contains(author)) {
    //             book.authors.add(author)
    //             author.books.add(book)
    //             bookRepository.save(book)
    //         }
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Update the firstName and lastName of an author")
    // @PutMapping("/authors/{id}")
    // fun updateAuthor(@PathVariable("id") id: Long, @RequestBody author: Author): ResponseEntity<Any> {
    //     return try {
    //         val currAuthor = authorRepository.findById(id).get()
    //         currAuthor.firstName = author.firstName
    //         currAuthor.lastName = author.lastName
    //         authorRepository.save(currAuthor)
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Update the information of a book")
    // @PutMapping("/books/{id}")
    // fun updateBook(@PathVariable("id") id: Long, @RequestBody book: Book): ResponseEntity<Any> {
    //     return try {
    //         val currBook = bookRepository.findById(id).get()
    //         currBook.title = book.title
    //         currBook.isbn = book.isbn
    //         currBook.cost = book.cost
    //         currBook.category = book.category
    //         currBook.description = book.description
    //         currBook.year = book.year
    //         bookRepository.save(currBook)
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Update an order")
    // @PutMapping("/orders/{id}")
    // fun updateOrder(@PathVariable("id") id: Long, @RequestBody order: Order): ResponseEntity<Any> {
    //     return try {
    //         val currOrder = orderRepository.findById(id).get()
    //         currOrder.quantity = order.quantity
    //         orderRepository.save(currOrder)
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Update a biographical information")
    // @PutMapping("/bios/{id}")
    // fun updateBio(@PathVariable("id") id: Long, @RequestBody bio: Bio): ResponseEntity<Any> {
    //     return try {
    //         val currBio = bioRepository.findById(id).get()
    //         currBio.biodata = bio.biodata
    //         bioRepository.save(currBio)
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Remove an author")
    // @DeleteMapping("/authors/{id}")
    // fun deleteAuthor(@PathVariable("id") id: Long): ResponseEntity<Any> {
    //     return try {
    //         this.authorRepository.deleteById(id)
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Remove an book")
    // @DeleteMapping("/books/{id}")
    // fun deleteBook(@PathVariable("id") id: Long): ResponseEntity<Any> {
    //     return try {
    //         this.bookRepository.deleteById(id)
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }

    // @Operation(summary = "Remove an order from a book")
    // @DeleteMapping("/books/{bkId}/orders/{ordId}")
    // fun deleteAuthorBio(@PathVariable("bkId") bkId: Long,
    //                     @PathVariable("ordId") ordId: Long): ResponseEntity<Any> {
    //     return try {
    //         val book = this.bookRepository.findById(bkId).get()
    //         val order = book.orders.find {  it.id == ordId }
    //         book.orders.remove(order)
    //         this.bookRepository.save(book)
    //         this.orderRepository.deleteById(ordId)
    //         ResponseEntity.noContent().build<Any>()
    //     } catch (e: NoSuchElementException) {
    //         ResponseEntity.badRequest().build()
    //     }catch (e: IllegalArgumentException) {
    //         ResponseEntity.badRequest().build()
    //     }
    // }
}