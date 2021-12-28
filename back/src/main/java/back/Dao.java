package back;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("book")
public class Dao {

  BookList a = new BookList();

  @GET
  @Path("list")
  public String bookInformation() {
    return a.getBookList().toString();
  }

  @GET
  @Path("take/{book}")
  public String take(@PathParam("book") String name) {
    a.takeBook(name);
    return "success!";
  }

  @GET
  @Path("return/{book}")
  public String returnB(@PathParam("book") String name) {
    a.returnBook(name);
    return "success!";
  }

  @GET
  @Path("new/{book}")
  public String newB(@PathParam("book") String name) {
    a.newBook(name);
    return "success!";
  }

  @GET
  @Path("getamount/{book}")
  public String getBookAmount(@PathParam("book") String name) {
    a.getBooksAmount(name);
    return "success!";
  }
}