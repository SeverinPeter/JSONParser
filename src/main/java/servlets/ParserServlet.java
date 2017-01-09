package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.junittests.Person;
import servlets.workingClass.Frame;

public class ParserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static List<Person> people = new ArrayList<Person>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		Frame frame = new Frame();
		try {
			for (Person person : people) {
				writer.println(Frame.toJSONString(frame.convertToJSON(person)));
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		Person p = new Person();
		p.setName(nachname);
		p.setVorname(vorname);
		people.add(p);
		PrintWriter writer = response.getWriter();
		Frame frame = new Frame();
		try {
			writer.println(Frame.toJSONString(frame.convertToJSON(p)));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
