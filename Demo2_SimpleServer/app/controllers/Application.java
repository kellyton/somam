package controllers;

import play.mvc.*;
import static play.libs.Json.toJson;

public class Application extends Controller {
	
    public static Result chamada1() {
        return ok(toJson("Esta é a chamada UM"));
    }
    
    public static Result chamada2() {
        return ok(toJson("Esta é a chamada DOIS"));
    }
    
    public static Result chamada3() {
        return ok(toJson("Esta é a chamada TRÊS"));
    }
  
}
