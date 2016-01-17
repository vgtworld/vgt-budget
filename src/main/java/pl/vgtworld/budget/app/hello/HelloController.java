package pl.vgtworld.budget.app.hello;

import com.googlecode.htmleasy.View;
import pl.vgtworld.budget.app.hello.models.HelloModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/")
public class HelloController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View getResourceList() {
		HelloModel model = new HelloModel();
		model.setCurrentDate(new Date());
		return new View("/views/hello.jsp", model);
	}

}
