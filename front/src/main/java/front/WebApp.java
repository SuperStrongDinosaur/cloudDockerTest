package front;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Path("shelf")
public class WebApp {

  Client client = ClientBuilder.newClient();

  private String restCall(String func){
    WebTarget resource = client.target("http://172.17.0.2:8123/book/" + func);
    Invocation.Builder request = resource.request();
    request.accept(MediaType.APPLICATION_JSON);

    Response response = request.get();

    if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
      System.out.println("Success! " + response.getStatus());
    } else {
      System.out.println("ERROR! " + response.getStatus());
    }
    return response.readEntity(String.class);
  }

  private HashMap<String, Integer> stringToMap(String map) {
    Properties props = new Properties();
    try {
      props.load(new StringReader(map.substring(1, map.length() - 1).replace(", ", "\n")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    HashMap<String, Integer> map2 = new HashMap<>();
    for (Map.Entry<Object, Object> e : props.entrySet()) {
      map2.put((String)e.getKey(), Integer.parseInt((String)e.getValue()));
    }
    return map2;
  }

  @GET
  @Path("status")
  public String status() {
    return restCall("list");
  }

  @GET
  @Path("take/{book}")
  public String takeBook(@PathParam("book") String name) {
    HashMap<String, Integer> books = stringToMap(restCall("list"));
    if (books.containsKey(name) && books.get(name) > 0) {
      restCall("take/" + name);
      return "Book " + name + " taken";
    } else {
      return "Book " + name + " doesn't exists or non left";
    }
  }

  @GET
  @Path("return/{book}")
  public String returnBook(@PathParam("book") String name) {
    HashMap<String, Integer> books = stringToMap(restCall("list"));
    if (books.containsKey(name)) {
      restCall("return/" + name);
      return "Book " + name + " returned";
    } else {
      restCall("new/" + name);
      return "Book " + name + " added";
    }
  }
}